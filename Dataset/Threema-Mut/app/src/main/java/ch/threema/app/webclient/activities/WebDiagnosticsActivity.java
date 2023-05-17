/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2018-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.webclient.activities;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.neovisionaries.ws.client.DualStackMode;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;
import com.neovisionaries.ws.client.WebSocketListener;
import com.neovisionaries.ws.client.WebSocketState;
import org.saltyrtc.client.helpers.UnsignedHelper;
import org.saltyrtc.client.keystore.Box;
import org.saltyrtc.client.nonce.SignalingChannelNonce;
import org.saltyrtc.client.signaling.CloseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webrtc.DataChannel;
import org.webrtc.IceCandidate;
import org.webrtc.MediaConstraints;
import org.webrtc.PeerConnection;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.SdpObserver;
import org.webrtc.SessionDescription;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.ActionBar;
import ch.threema.app.R;
import ch.threema.app.activities.ThreemaToolbarActivity;
import ch.threema.app.dialogs.TextEntryDialog;
import ch.threema.app.exceptions.FileSystemNotPresentException;
import ch.threema.app.messagereceiver.ContactMessageReceiver;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.MessageService;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.utils.WebRTCUtil;
import ch.threema.app.webclient.utils.DefaultNoopPeerConnectionObserver;
import ch.threema.app.webclient.utils.DefaultNoopWebSocketListener;
import ch.threema.app.webclient.webrtc.PeerConnectionWrapper;
import ch.threema.base.ThreemaException;
import ch.threema.localcrypto.MasterKeyLockedException;
import ch.threema.storage.models.ContactModel;
import static ch.threema.app.preference.SettingsTroubleshootingFragment.THREEMA_SUPPORT_IDENTITY;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@SuppressWarnings("FieldCanBeLocal")
@UiThread
public class WebDiagnosticsActivity extends ThreemaToolbarActivity implements TextEntryDialog.TextEntryDialogClickListener {

    private static final Logger logger = LoggerFactory.getLogger(WebDiagnosticsActivity.class);

    private static final String DIALOG_TAG_SEND_VOIP_DEBUG = "svd";

    // Config
    private static final String WS_HOST = "saltyrtc-ee.threema.ch";

    private static final String WS_BASE_URL = "wss://" + WS_HOST;

    private static final String WS_PATH = "ffffffffffffffff000000000000eeeeeeee000000000000ffffffffffffffff";

    private static final String WS_PROTOCOL = "v1.saltyrtc.org";

    private static final int WS_CONNECT_TIMEOUT_MS = 10000;

    private static final int WS_TEST_TIMEOUT_MS = WS_CONNECT_TIMEOUT_MS + 3000;

    private static final int RTC_TEST_TIMEOUT_MS = 12000;

    // Threema services
    @Nullable
    private ContactService contactService;

    // Views
    @Nullable
    private ProgressBar progressBar;

    @Nullable
    private TextView introText;

    @Nullable
    private TextView doneText;

    @Nullable
    private Button copyButton;

    @Nullable
    private Button sendButton;

    @Nullable
    private View footerButtons;

    // String that will be copied to clipboard
    @Nullable
    private String clipboardString;

    // Event logging
    @NonNull
    private final List<String> eventLog = new ArrayList<>();

    @Nullable
    private ArrayAdapter<String> adapter;

    private long startTime = 0;

    // Websocket
    @Nullable
    private WebSocket ws;

    private boolean wsDone = false;

    // WebRTC
    @Nullable
    private PeerConnection pc;

    @Nullable
    private PeerConnectionFactory pcFactory;

    private final AtomicInteger candidateCount = new AtomicInteger(0);

    private boolean rtcDone = false;

    // of the peer connection and related objects.
    @Nullable
    private ScheduledExecutorService webrtcExecutor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(62301)) {
            logger.trace("onCreate");
        }
        if (!ListenerUtil.mutListener.listen(62302)) {
            super.onCreate(savedInstanceState);
        }
        final ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(62305)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(62303)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(62304)) {
                    actionBar.setTitle(R.string.webclient_diagnostics);
                }
            }
        }
    }

    @Override
    protected boolean initActivity(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(62306)) {
            logger.trace("initActivity");
        }
        if (!ListenerUtil.mutListener.listen(62307)) {
            if (!super.initActivity(savedInstanceState)) {
                return false;
            }
        }
        // Initialize services
        try {
            if (!ListenerUtil.mutListener.listen(62309)) {
                this.contactService = this.serviceManager.getContactService();
            }
        } catch (MasterKeyLockedException | FileSystemNotPresentException e) {
            if (!ListenerUtil.mutListener.listen(62308)) {
                logger.error("Could not initialize services", e);
            }
        }
        if (!ListenerUtil.mutListener.listen(62310)) {
            // Get view references
            this.progressBar = findViewById(R.id.webclient_diagnostics_loading);
        }
        if (!ListenerUtil.mutListener.listen(62311)) {
            this.introText = findViewById(R.id.webclient_diagnostics_intro);
        }
        if (!ListenerUtil.mutListener.listen(62312)) {
            this.doneText = findViewById(R.id.webclient_diagnostics_done);
        }
        if (!ListenerUtil.mutListener.listen(62313)) {
            this.copyButton = findViewById(R.id.webclient_diagnostics_copy_button);
        }
        if (!ListenerUtil.mutListener.listen(62314)) {
            this.sendButton = findViewById(R.id.webclient_diagnostics_send_button);
        }
        if (!ListenerUtil.mutListener.listen(62315)) {
            this.footerButtons = findViewById(R.id.webclient_diagnostics_footer_buttons);
        }
        // Wire up start button
        final Button startButton = findViewById(R.id.webclient_diagnostics_start);
        if (!ListenerUtil.mutListener.listen(62316)) {
            startButton.setOnClickListener(view -> {
                startButton.setVisibility(View.GONE);
                WebDiagnosticsActivity.this.startTests();
            });
        }
        // Wire up copy button
        assert this.copyButton != null;
        if (!ListenerUtil.mutListener.listen(62317)) {
            this.copyButton.setOnClickListener(view -> {
                if (!TestUtil.empty(this.clipboardString)) {
                    WebDiagnosticsActivity.this.copyToClipboard(this.clipboardString);
                }
            });
        }
        // Wire up send button
        assert this.sendButton != null;
        if (!ListenerUtil.mutListener.listen(62318)) {
            this.sendButton.setOnClickListener(view -> {
                if (!TestUtil.empty(this.clipboardString)) {
                    WebDiagnosticsActivity.this.prepareSendToSupport();
                }
            });
        }
        // Initialize event log
        final ListView eventLog = findViewById(R.id.webclient_diagnostics_event_log);
        if (!ListenerUtil.mutListener.listen(62319)) {
            this.adapter = new ArrayAdapter<>(this, R.layout.item_webrtc_debug_list, this.eventLog);
        }
        if (!ListenerUtil.mutListener.listen(62320)) {
            eventLog.setAdapter(this.adapter);
        }
        return true;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_webclient_debug;
    }

    @Override
    protected void onStart() {
        if (!ListenerUtil.mutListener.listen(62321)) {
            logger.trace("onStart");
        }
        if (!ListenerUtil.mutListener.listen(62322)) {
            this.webrtcExecutor = Executors.newSingleThreadScheduledExecutor();
        }
        if (!ListenerUtil.mutListener.listen(62323)) {
            super.onStart();
        }
    }

    @Override
    protected void onStop() {
        if (!ListenerUtil.mutListener.listen(62324)) {
            logger.trace("onStop");
        }
        if (!ListenerUtil.mutListener.listen(62325)) {
            this.cleanup();
        }
        if (!ListenerUtil.mutListener.listen(62326)) {
            super.onStop();
        }
    }

    @AnyThread
    private void resetStartTime() {
        if (!ListenerUtil.mutListener.listen(62327)) {
            this.startTime = System.nanoTime();
        }
    }

    @AnyThread
    private void addLogSeparator() {
        if (!ListenerUtil.mutListener.listen(62328)) {
            this.addToLog("----------------", false);
        }
    }

    @AnyThread
    private void addToLog(final String value, boolean timestamp) {
        final long elapsedNs = (ListenerUtil.mutListener.listen(62332) ? (System.nanoTime() % this.startTime) : (ListenerUtil.mutListener.listen(62331) ? (System.nanoTime() / this.startTime) : (ListenerUtil.mutListener.listen(62330) ? (System.nanoTime() * this.startTime) : (ListenerUtil.mutListener.listen(62329) ? (System.nanoTime() + this.startTime) : (System.nanoTime() - this.startTime)))));
        final String logLine = timestamp ? String.format("+%sms %s", (ListenerUtil.mutListener.listen(62340) ? ((ListenerUtil.mutListener.listen(62336) ? (elapsedNs % 1000) : (ListenerUtil.mutListener.listen(62335) ? (elapsedNs * 1000) : (ListenerUtil.mutListener.listen(62334) ? (elapsedNs - 1000) : (ListenerUtil.mutListener.listen(62333) ? (elapsedNs + 1000) : (elapsedNs / 1000))))) % 1000) : (ListenerUtil.mutListener.listen(62339) ? ((ListenerUtil.mutListener.listen(62336) ? (elapsedNs % 1000) : (ListenerUtil.mutListener.listen(62335) ? (elapsedNs * 1000) : (ListenerUtil.mutListener.listen(62334) ? (elapsedNs - 1000) : (ListenerUtil.mutListener.listen(62333) ? (elapsedNs + 1000) : (elapsedNs / 1000))))) * 1000) : (ListenerUtil.mutListener.listen(62338) ? ((ListenerUtil.mutListener.listen(62336) ? (elapsedNs % 1000) : (ListenerUtil.mutListener.listen(62335) ? (elapsedNs * 1000) : (ListenerUtil.mutListener.listen(62334) ? (elapsedNs - 1000) : (ListenerUtil.mutListener.listen(62333) ? (elapsedNs + 1000) : (elapsedNs / 1000))))) - 1000) : (ListenerUtil.mutListener.listen(62337) ? ((ListenerUtil.mutListener.listen(62336) ? (elapsedNs % 1000) : (ListenerUtil.mutListener.listen(62335) ? (elapsedNs * 1000) : (ListenerUtil.mutListener.listen(62334) ? (elapsedNs - 1000) : (ListenerUtil.mutListener.listen(62333) ? (elapsedNs + 1000) : (elapsedNs / 1000))))) + 1000) : ((ListenerUtil.mutListener.listen(62336) ? (elapsedNs % 1000) : (ListenerUtil.mutListener.listen(62335) ? (elapsedNs * 1000) : (ListenerUtil.mutListener.listen(62334) ? (elapsedNs - 1000) : (ListenerUtil.mutListener.listen(62333) ? (elapsedNs + 1000) : (elapsedNs / 1000))))) / 1000))))), value) : value;
        if (!ListenerUtil.mutListener.listen(62341)) {
            this.clipboardString += logLine + "\n";
        }
        if (!ListenerUtil.mutListener.listen(62342)) {
            RuntimeUtil.runOnUiThread(() -> {
                synchronized (WebDiagnosticsActivity.this.eventLog) {
                    logger.info(logLine);
                    WebDiagnosticsActivity.this.eventLog.add(logLine);
                    if (WebDiagnosticsActivity.this.adapter != null) {
                        WebDiagnosticsActivity.this.adapter.notifyDataSetChanged();
                    }
                }
            });
        }
    }

    @AnyThread
    private void addToLog(final String value) {
        if (!ListenerUtil.mutListener.listen(62343)) {
            this.addToLog(value, true);
        }
    }

    @UiThread
    private void copyToClipboard(@NonNull String text) {
        final ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        if (!ListenerUtil.mutListener.listen(62346)) {
            if (clipboard != null) {
                final String label = getString(R.string.webclient_diagnostics);
                final ClipData clip = ClipData.newPlainText(label, text);
                if (!ListenerUtil.mutListener.listen(62344)) {
                    clipboard.setPrimaryClip(clip);
                }
                if (!ListenerUtil.mutListener.listen(62345)) {
                    Toast.makeText(getApplicationContext(), getString(R.string.voip_webrtc_debug_copied), Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void prepareSendToSupport() {
        TextEntryDialog dialog = TextEntryDialog.newInstance(R.string.send_to_support, R.string.enter_description, R.string.send, R.string.cancel, 5, 3000, 1);
        if (!ListenerUtil.mutListener.listen(62347)) {
            dialog.show(getSupportFragmentManager(), DIALOG_TAG_SEND_VOIP_DEBUG);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void sendToSupport(@NonNull String caption) {
        final MessageService messageService;
        try {
            messageService = serviceManager.getMessageService();
        } catch (ThreemaException e) {
            if (!ListenerUtil.mutListener.listen(62348)) {
                logger.error("Exception", e);
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(62350)) {
            if ((ListenerUtil.mutListener.listen(62349) ? (this.contactService == null && messageService == null) : (this.contactService == null || messageService == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(62356)) {
            new AsyncTask<Void, Void, ContactMessageReceiver>() {

                @Override
                protected ContactMessageReceiver doInBackground(Void... voids) {
                    try {
                        final ContactModel contactModel = contactService.getOrCreateByIdentity(THREEMA_SUPPORT_IDENTITY, true);
                        return contactService.createReceiver(contactModel);
                    } catch (Exception e) {
                        return null;
                    }
                }

                @Override
                protected void onPostExecute(ContactMessageReceiver messageReceiver) {
                    try {
                        if (!ListenerUtil.mutListener.listen(62352)) {
                            messageService.sendText(clipboardString + "\n---\n" + caption + "\n---\n" + ConfigUtils.getDeviceInfo(WebDiagnosticsActivity.this, false) + "\n" + "Threema " + ConfigUtils.getFullAppVersion(WebDiagnosticsActivity.this) + "\n" + getMyIdentity(), messageReceiver);
                        }
                        if (!ListenerUtil.mutListener.listen(62353)) {
                            Toast.makeText(getApplicationContext(), R.string.message_sent, Toast.LENGTH_LONG).show();
                        }
                        if (!ListenerUtil.mutListener.listen(62354)) {
                            finish();
                        }
                        return;
                    } catch (Exception e1) {
                        if (!ListenerUtil.mutListener.listen(62351)) {
                            logger.error("Exception", e1);
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(62355)) {
                        Toast.makeText(getApplicationContext(), R.string.an_error_occurred, Toast.LENGTH_LONG).show();
                    }
                }
            }.execute();
        }
    }

    @UiThread
    private void startTests() {
        if (!ListenerUtil.mutListener.listen(62357)) {
            logger.info("*** Starting Threema Web Diagnostics Test");
        }
        if (!ListenerUtil.mutListener.listen(62358)) {
            this.eventLog.clear();
        }
        if (!ListenerUtil.mutListener.listen(62359)) {
            this.clipboardString = "";
        }
        if (!ListenerUtil.mutListener.listen(62360)) {
            this.resetStartTime();
        }
        if (!ListenerUtil.mutListener.listen(62361)) {
            this.addToLog("Starting Threema Web Diagnostics...", false);
        }
        // Update UI visibility
        assert this.progressBar != null;
        if (!ListenerUtil.mutListener.listen(62362)) {
            this.progressBar.setVisibility(View.VISIBLE);
        }
        assert this.introText != null;
        if (!ListenerUtil.mutListener.listen(62363)) {
            this.introText.setVisibility(View.GONE);
        }
        assert this.doneText != null;
        if (!ListenerUtil.mutListener.listen(62364)) {
            this.doneText.setVisibility(View.GONE);
        }
        assert this.footerButtons != null;
        if (!ListenerUtil.mutListener.listen(62365)) {
            this.footerButtons.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(62366)) {
            // Print connectivity info
            this.queryConnectivityInfo();
        }
        if (!ListenerUtil.mutListener.listen(62367)) {
            // Start with WebSocket test
            this.startWsTest();
        }
    }

    @UiThread
    private void queryConnectivityInfo() {
        final Context appContext = getApplicationContext();
        final ConnectivityManager connectivityManager = (ConnectivityManager) appContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (!ListenerUtil.mutListener.listen(62368)) {
            this.addLogSeparator();
        }
        if (!ListenerUtil.mutListener.listen(62378)) {
            if ((ListenerUtil.mutListener.listen(62373) ? (Build.VERSION.SDK_INT <= 21) : (ListenerUtil.mutListener.listen(62372) ? (Build.VERSION.SDK_INT > 21) : (ListenerUtil.mutListener.listen(62371) ? (Build.VERSION.SDK_INT < 21) : (ListenerUtil.mutListener.listen(62370) ? (Build.VERSION.SDK_INT != 21) : (ListenerUtil.mutListener.listen(62369) ? (Build.VERSION.SDK_INT == 21) : (Build.VERSION.SDK_INT >= 21))))))) {
                // Add available networks
                final Network[] networks = connectivityManager.getAllNetworks();
                if (!ListenerUtil.mutListener.listen(62375)) {
                    this.addToLog("Networks (" + networks.length + "):", false);
                }
                if (!ListenerUtil.mutListener.listen(62377)) {
                    {
                        long _loopCounter748 = 0;
                        for (Network network : networks) {
                            ListenerUtil.loopListener.listen("_loopCounter748", ++_loopCounter748);
                            final NetworkInfo info = connectivityManager.getNetworkInfo(network);
                            final String typeName = info.getTypeName();
                            final String fullType = info.getSubtypeName().isEmpty() ? typeName : typeName + "/" + info.getSubtypeName();
                            final String detailedState = info.getDetailedState().toString();
                            final String failover = "failover=" + info.isFailover();
                            final String available = "available=" + info.isAvailable();
                            final String roaming = "roaming=" + info.isRoaming();
                            if (!ListenerUtil.mutListener.listen(62376)) {
                                this.addToLog("- " + fullType + ", " + detailedState + ", " + failover + ", " + available + ", " + roaming, false);
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(62374)) {
                    this.addToLog("API level " + Build.VERSION.SDK_INT + ", ignoring network info");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(62379)) {
            this.addLogSeparator();
        }
        try {
            final List<String> addresses = new ArrayList<>();
            if (!ListenerUtil.mutListener.listen(62386)) {
                {
                    long _loopCounter750 = 0;
                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                        ListenerUtil.loopListener.listen("_loopCounter750", ++_loopCounter750);
                        final NetworkInterface intf = en.nextElement();
                        if (!ListenerUtil.mutListener.listen(62385)) {
                            {
                                long _loopCounter749 = 0;
                                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                                    ListenerUtil.loopListener.listen("_loopCounter749", ++_loopCounter749);
                                    final InetAddress inetAddress = enumIpAddr.nextElement();
                                    if (!ListenerUtil.mutListener.listen(62384)) {
                                        if (!inetAddress.isLoopbackAddress()) {
                                            final String addr = inetAddress.getHostAddress();
                                            if (!ListenerUtil.mutListener.listen(62383)) {
                                                if (inetAddress.isLinkLocalAddress()) {
                                                    if (!ListenerUtil.mutListener.listen(62382)) {
                                                        addresses.add(addr + " [link-local]");
                                                    }
                                                } else {
                                                    if (!ListenerUtil.mutListener.listen(62381)) {
                                                        addresses.add(addr);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(62387)) {
                Collections.sort(addresses);
            }
            if (!ListenerUtil.mutListener.listen(62388)) {
                this.addToLog("Non-loopback interfaces (" + addresses.size() + "):", false);
            }
            if (!ListenerUtil.mutListener.listen(62390)) {
                {
                    long _loopCounter751 = 0;
                    for (String addr : addresses) {
                        ListenerUtil.loopListener.listen("_loopCounter751", ++_loopCounter751);
                        if (!ListenerUtil.mutListener.listen(62389)) {
                            this.addToLog("- " + addr, false);
                        }
                    }
                }
            }
        } catch (SocketException e) {
            if (!ListenerUtil.mutListener.listen(62380)) {
                this.addToLog("Socket exception when enumerating network interfaces: " + e.toString());
            }
        }
    }

    /**
     *  Start the WebSocket test.
     */
    @UiThread
    private synchronized void startWsTest() {
        if (!ListenerUtil.mutListener.listen(62391)) {
            this.wsDone = false;
        }
        final Handler handler = new Handler();
        if (!ListenerUtil.mutListener.listen(62392)) {
            handler.postDelayed(() -> {
                if (!wsDone) {
                    WebDiagnosticsActivity.this.failWs("WS test timed out");
                }
            }, WS_TEST_TIMEOUT_MS);
        }
        if (!ListenerUtil.mutListener.listen(62393)) {
            RuntimeUtil.runInAsyncTask(() -> {
                final boolean success = WebDiagnosticsActivity.this.testWebsocket();
                if (!success) {
                    addToLog("Initializing WebSocket test failed.");
                }
            });
        }
    }

    /**
     *  Start the WebRTC test.
     */
    @UiThread
    private synchronized void startRtcTest() {
        if (!ListenerUtil.mutListener.listen(62394)) {
            this.rtcDone = false;
        }
        if (!ListenerUtil.mutListener.listen(62395)) {
            this.candidateCount.set(0);
        }
        final Handler handler = new Handler();
        if (!ListenerUtil.mutListener.listen(62396)) {
            handler.postDelayed(() -> {
                if (!rtcDone) {
                    WebDiagnosticsActivity.this.addToLog("WebRTC test timed out");
                    WebDiagnosticsActivity.this.onRtcComplete(this.candidateCount.get() > 0);
                }
            }, RTC_TEST_TIMEOUT_MS);
        }
        if (!ListenerUtil.mutListener.listen(62397)) {
            RuntimeUtil.runInAsyncTask(() -> {
                final boolean success = WebDiagnosticsActivity.this.testWebRTC();
                if (!success) {
                    addToLog("Initializing WebRTC test failed.");
                }
            });
        }
    }

    /**
     *  Initialize the WebSocket tests.
     *
     *  If something during the initialization fails, return false.
     */
    @AnyThread
    private boolean testWebsocket() {
        if (!ListenerUtil.mutListener.listen(62398)) {
            this.addLogSeparator();
        }
        if (!ListenerUtil.mutListener.listen(62399)) {
            this.resetStartTime();
        }
        if (!ListenerUtil.mutListener.listen(62400)) {
            this.addToLog("Starting WS tests");
        }
        // SaltyRTC WebSocket code.
        assert this.preferenceService != null;
        DualStackMode dualStackMode = DualStackMode.BOTH;
        if (!ListenerUtil.mutListener.listen(62402)) {
            if (!this.preferenceService.allowWebrtcIpv6()) {
                if (!ListenerUtil.mutListener.listen(62401)) {
                    dualStackMode = DualStackMode.IPV4_ONLY;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(62403)) {
            this.addToLog("Setting: dualStackMode=" + dualStackMode.name());
        }
        // Create WebSocket
        final String url = WS_BASE_URL + "/" + WS_PATH;
        if (!ListenerUtil.mutListener.listen(62404)) {
            logger.info("Connecting to " + url);
        }
        try {
            if (!ListenerUtil.mutListener.listen(62406)) {
                this.ws = new WebSocketFactory().setConnectionTimeout(WS_CONNECT_TIMEOUT_MS).setSSLSocketFactory(ConfigUtils.getSSLSocketFactory(WS_HOST)).setVerifyHostname(true).setDualStackMode(dualStackMode).createSocket(url).addProtocol(WS_PROTOCOL).addListener(this.wsListener);
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(62405)) {
                this.failWs("IOException when creating WebSocket: " + e.getMessage(), e);
            }
            return false;
        }
        // Connect
        try {
            if (!ListenerUtil.mutListener.listen(62408)) {
                this.addToLog("Connecting to WebSocket");
            }
            assert this.ws != null;
            if (!ListenerUtil.mutListener.listen(62409)) {
                this.ws.connect();
            }
        } catch (WebSocketException e) {
            if (!ListenerUtil.mutListener.listen(62407)) {
                this.failWs("WebSocketException when connecting: " + e.getMessage(), e);
            }
            return false;
        }
        return true;
    }

    /**
     *  Initialize the WebRTC tests.
     *
     *  If something during the initialization fails, return false.
     */
    @AnyThread
    private boolean testWebRTC() {
        if (!ListenerUtil.mutListener.listen(62410)) {
            this.addLogSeparator();
        }
        if (!ListenerUtil.mutListener.listen(62411)) {
            this.resetStartTime();
        }
        if (!ListenerUtil.mutListener.listen(62412)) {
            this.addToLog("Starting WebRTC tests");
        }
        // Get configuration
        assert this.preferenceService != null;
        final boolean allowIpv6 = this.preferenceService.allowWebrtcIpv6();
        if (!ListenerUtil.mutListener.listen(62413)) {
            this.addToLog("Setting: allowWebrtcIpv6=" + allowIpv6);
        }
        // Set up peer connection
        assert this.webrtcExecutor != null;
        if (!ListenerUtil.mutListener.listen(62414)) {
            this.webrtcExecutor.execute(() -> {
                WebRTCUtil.initializeAndroidGlobals(this.getApplicationContext());
                final PeerConnection.RTCConfiguration rtcConfig;
                try {
                    rtcConfig = PeerConnectionWrapper.getRTCConfiguration(logger);
                } catch (Exception e) {
                    this.addToLog("Could not get RTC configuration: " + e.getMessage());
                    return;
                }
                rtcConfig.continualGatheringPolicy = PeerConnection.ContinualGatheringPolicy.GATHER_ONCE;
                this.addToLog("Using " + rtcConfig.iceServers.size() + " ICE servers:");
                {
                    long _loopCounter752 = 0;
                    for (PeerConnection.IceServer server : rtcConfig.iceServers) {
                        ListenerUtil.loopListener.listen("_loopCounter752", ++_loopCounter752);
                        this.addToLog("- " + server.urls.toString());
                    }
                }
                // Instantiate peer connection
                this.pcFactory = PeerConnectionWrapper.getPeerConnectionFactory();
                this.pc = this.pcFactory.createPeerConnection(rtcConfig, this.pcObserver);
                if (this.pc == null) {
                    this.addToLog("Could not create peer connection");
                    return;
                }
                // Create a data channel and a offer to kick off ICE gathering
                this.pc.createDataChannel("trigger-ice-gathering", new DataChannel.Init());
                this.pc.createOffer(this.sdpObserver, new MediaConstraints());
            });
        }
        return true;
    }

    private final WebSocketListener wsListener = new DefaultNoopWebSocketListener() {

        @Override
        public void onStateChanged(WebSocket websocket, WebSocketState newState) {
            if (!ListenerUtil.mutListener.listen(62415)) {
                addToLog("WS state changed to " + newState.name());
            }
        }

        @Override
        public void onConnected(WebSocket websocket, Map<String, List<String>> headers) {
            final Socket socket;
            try {
                socket = websocket.getConnectedSocket();
            } catch (WebSocketException e) {
                if (!ListenerUtil.mutListener.listen(62416)) {
                    addToLog("Unable to retrieve connected socket: " + e.toString());
                }
                return;
            }
            final String local = socket.getLocalAddress().getHostAddress() + ":" + socket.getLocalPort();
            final String remote = socket.getInetAddress().getHostAddress() + ":" + socket.getPort();
            if (!ListenerUtil.mutListener.listen(62417)) {
                addToLog("WS connected (" + local + " -> " + remote + ")");
            }
        }

        @Override
        public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) {
            if (!ListenerUtil.mutListener.listen(62420)) {
                if (closedByServer) {
                    int code = serverCloseFrame.getCloseCode();
                    if (!ListenerUtil.mutListener.listen(62419)) {
                        addToLog("WS closed by server with code " + code + " (" + CloseCode.explain(code) + ")");
                    }
                } else {
                    int code = clientCloseFrame.getCloseCode();
                    if (!ListenerUtil.mutListener.listen(62418)) {
                        addToLog("WS closed by us with code " + code + " (" + CloseCode.explain(code) + ")");
                    }
                }
            }
            final boolean success = (ListenerUtil.mutListener.listen(62421) ? (!closedByServer || clientCloseFrame.getCloseCode() == 1000) : (!closedByServer && clientCloseFrame.getCloseCode() == 1000));
            if (!ListenerUtil.mutListener.listen(62422)) {
                WebDiagnosticsActivity.this.onWsComplete(success);
            }
        }

        @Override
        public void onTextMessage(WebSocket websocket, String text) {
            if (!ListenerUtil.mutListener.listen(62423)) {
                addToLog("WS received text message, aborting");
            }
            if (!ListenerUtil.mutListener.listen(62424)) {
                websocket.disconnect();
            }
        }

        @Override
        public void onTextMessage(WebSocket websocket, byte[] data) {
            if (!ListenerUtil.mutListener.listen(62425)) {
                addToLog("WS received text message, aborting");
            }
            if (!ListenerUtil.mutListener.listen(62426)) {
                websocket.disconnect();
            }
        }

        @Override
        public void onBinaryMessage(WebSocket websocket, byte[] binary) {
            if (!ListenerUtil.mutListener.listen(62427)) {
                addToLog("WS received " + binary.length + " bytes");
            }
            if (!ListenerUtil.mutListener.listen(62435)) {
                // Validate length
                if ((ListenerUtil.mutListener.listen(62432) ? (binary.length >= 81) : (ListenerUtil.mutListener.listen(62431) ? (binary.length <= 81) : (ListenerUtil.mutListener.listen(62430) ? (binary.length > 81) : (ListenerUtil.mutListener.listen(62429) ? (binary.length != 81) : (ListenerUtil.mutListener.listen(62428) ? (binary.length == 81) : (binary.length < 81))))))) {
                    if (!ListenerUtil.mutListener.listen(62433)) {
                        addToLog("Invalid message length: " + binary.length);
                    }
                    if (!ListenerUtil.mutListener.listen(62434)) {
                        websocket.disconnect(1000);
                    }
                    return;
                }
            }
            // Wrap message
            final Box box = new Box(ByteBuffer.wrap(binary), SignalingChannelNonce.TOTAL_LENGTH);
            // Validate nonce
            final SignalingChannelNonce nonce = new SignalingChannelNonce(ByteBuffer.wrap(box.getNonce()));
            if (!ListenerUtil.mutListener.listen(62438)) {
                if (nonce.getSource() != 0) {
                    if (!ListenerUtil.mutListener.listen(62436)) {
                        addToLog("Invalid nonce source: " + nonce.getSource());
                    }
                    if (!ListenerUtil.mutListener.listen(62437)) {
                        websocket.disconnect(1000);
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(62441)) {
                if (nonce.getDestination() != 0) {
                    if (!ListenerUtil.mutListener.listen(62439)) {
                        addToLog("Invalid nonce destination: " + nonce.getDestination());
                    }
                    if (!ListenerUtil.mutListener.listen(62440)) {
                        websocket.disconnect(1000);
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(62444)) {
                if (nonce.getOverflow() != 0) {
                    if (!ListenerUtil.mutListener.listen(62442)) {
                        addToLog("Invalid nonce overflow: " + nonce.getOverflow());
                    }
                    if (!ListenerUtil.mutListener.listen(62443)) {
                        websocket.disconnect(1000);
                    }
                    return;
                }
            }
            // with either the value "type" or "key".
            final byte[] data = box.getData();
            short byte1 = UnsignedHelper.readUnsignedByte(data[0]);
            short byte2 = UnsignedHelper.readUnsignedByte(data[1]);
            short byte3 = UnsignedHelper.readUnsignedByte(data[2]);
            short byte4 = UnsignedHelper.readUnsignedByte(data[3]);
            short byte5 = UnsignedHelper.readUnsignedByte(data[4]);
            short byte6 = UnsignedHelper.readUnsignedByte(data[5]);
            if (!ListenerUtil.mutListener.listen(62452)) {
                if ((ListenerUtil.mutListener.listen(62449) ? (byte1 >= 0x82) : (ListenerUtil.mutListener.listen(62448) ? (byte1 <= 0x82) : (ListenerUtil.mutListener.listen(62447) ? (byte1 > 0x82) : (ListenerUtil.mutListener.listen(62446) ? (byte1 < 0x82) : (ListenerUtil.mutListener.listen(62445) ? (byte1 == 0x82) : (byte1 != 0x82))))))) {
                    if (!ListenerUtil.mutListener.listen(62450)) {
                        addToLog("Invalid data (does not start with 0x82)");
                    }
                    if (!ListenerUtil.mutListener.listen(62451)) {
                        websocket.disconnect(1000);
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(62473)) {
                if ((ListenerUtil.mutListener.listen(62460) ? ((ListenerUtil.mutListener.listen(62459) ? ((ListenerUtil.mutListener.listen(62458) ? ((ListenerUtil.mutListener.listen(62457) ? (byte2 >= 0xa3) : (ListenerUtil.mutListener.listen(62456) ? (byte2 <= 0xa3) : (ListenerUtil.mutListener.listen(62455) ? (byte2 > 0xa3) : (ListenerUtil.mutListener.listen(62454) ? (byte2 < 0xa3) : (ListenerUtil.mutListener.listen(62453) ? (byte2 != 0xa3) : (byte2 == 0xa3)))))) || byte3 == 'k') : ((ListenerUtil.mutListener.listen(62457) ? (byte2 >= 0xa3) : (ListenerUtil.mutListener.listen(62456) ? (byte2 <= 0xa3) : (ListenerUtil.mutListener.listen(62455) ? (byte2 > 0xa3) : (ListenerUtil.mutListener.listen(62454) ? (byte2 < 0xa3) : (ListenerUtil.mutListener.listen(62453) ? (byte2 != 0xa3) : (byte2 == 0xa3)))))) && byte3 == 'k')) || byte4 == 'e') : ((ListenerUtil.mutListener.listen(62458) ? ((ListenerUtil.mutListener.listen(62457) ? (byte2 >= 0xa3) : (ListenerUtil.mutListener.listen(62456) ? (byte2 <= 0xa3) : (ListenerUtil.mutListener.listen(62455) ? (byte2 > 0xa3) : (ListenerUtil.mutListener.listen(62454) ? (byte2 < 0xa3) : (ListenerUtil.mutListener.listen(62453) ? (byte2 != 0xa3) : (byte2 == 0xa3)))))) || byte3 == 'k') : ((ListenerUtil.mutListener.listen(62457) ? (byte2 >= 0xa3) : (ListenerUtil.mutListener.listen(62456) ? (byte2 <= 0xa3) : (ListenerUtil.mutListener.listen(62455) ? (byte2 > 0xa3) : (ListenerUtil.mutListener.listen(62454) ? (byte2 < 0xa3) : (ListenerUtil.mutListener.listen(62453) ? (byte2 != 0xa3) : (byte2 == 0xa3)))))) && byte3 == 'k')) && byte4 == 'e')) || byte5 == 'y') : ((ListenerUtil.mutListener.listen(62459) ? ((ListenerUtil.mutListener.listen(62458) ? ((ListenerUtil.mutListener.listen(62457) ? (byte2 >= 0xa3) : (ListenerUtil.mutListener.listen(62456) ? (byte2 <= 0xa3) : (ListenerUtil.mutListener.listen(62455) ? (byte2 > 0xa3) : (ListenerUtil.mutListener.listen(62454) ? (byte2 < 0xa3) : (ListenerUtil.mutListener.listen(62453) ? (byte2 != 0xa3) : (byte2 == 0xa3)))))) || byte3 == 'k') : ((ListenerUtil.mutListener.listen(62457) ? (byte2 >= 0xa3) : (ListenerUtil.mutListener.listen(62456) ? (byte2 <= 0xa3) : (ListenerUtil.mutListener.listen(62455) ? (byte2 > 0xa3) : (ListenerUtil.mutListener.listen(62454) ? (byte2 < 0xa3) : (ListenerUtil.mutListener.listen(62453) ? (byte2 != 0xa3) : (byte2 == 0xa3)))))) && byte3 == 'k')) || byte4 == 'e') : ((ListenerUtil.mutListener.listen(62458) ? ((ListenerUtil.mutListener.listen(62457) ? (byte2 >= 0xa3) : (ListenerUtil.mutListener.listen(62456) ? (byte2 <= 0xa3) : (ListenerUtil.mutListener.listen(62455) ? (byte2 > 0xa3) : (ListenerUtil.mutListener.listen(62454) ? (byte2 < 0xa3) : (ListenerUtil.mutListener.listen(62453) ? (byte2 != 0xa3) : (byte2 == 0xa3)))))) || byte3 == 'k') : ((ListenerUtil.mutListener.listen(62457) ? (byte2 >= 0xa3) : (ListenerUtil.mutListener.listen(62456) ? (byte2 <= 0xa3) : (ListenerUtil.mutListener.listen(62455) ? (byte2 > 0xa3) : (ListenerUtil.mutListener.listen(62454) ? (byte2 < 0xa3) : (ListenerUtil.mutListener.listen(62453) ? (byte2 != 0xa3) : (byte2 == 0xa3)))))) && byte3 == 'k')) && byte4 == 'e')) && byte5 == 'y'))) {
                    if (!ListenerUtil.mutListener.listen(62472)) {
                        addToLog("Received server-hello message!");
                    }
                } else if ((ListenerUtil.mutListener.listen(62469) ? ((ListenerUtil.mutListener.listen(62468) ? ((ListenerUtil.mutListener.listen(62467) ? ((ListenerUtil.mutListener.listen(62466) ? ((ListenerUtil.mutListener.listen(62465) ? (byte2 >= 0xa4) : (ListenerUtil.mutListener.listen(62464) ? (byte2 <= 0xa4) : (ListenerUtil.mutListener.listen(62463) ? (byte2 > 0xa4) : (ListenerUtil.mutListener.listen(62462) ? (byte2 < 0xa4) : (ListenerUtil.mutListener.listen(62461) ? (byte2 != 0xa4) : (byte2 == 0xa4)))))) || byte3 == 't') : ((ListenerUtil.mutListener.listen(62465) ? (byte2 >= 0xa4) : (ListenerUtil.mutListener.listen(62464) ? (byte2 <= 0xa4) : (ListenerUtil.mutListener.listen(62463) ? (byte2 > 0xa4) : (ListenerUtil.mutListener.listen(62462) ? (byte2 < 0xa4) : (ListenerUtil.mutListener.listen(62461) ? (byte2 != 0xa4) : (byte2 == 0xa4)))))) && byte3 == 't')) || byte4 == 'y') : ((ListenerUtil.mutListener.listen(62466) ? ((ListenerUtil.mutListener.listen(62465) ? (byte2 >= 0xa4) : (ListenerUtil.mutListener.listen(62464) ? (byte2 <= 0xa4) : (ListenerUtil.mutListener.listen(62463) ? (byte2 > 0xa4) : (ListenerUtil.mutListener.listen(62462) ? (byte2 < 0xa4) : (ListenerUtil.mutListener.listen(62461) ? (byte2 != 0xa4) : (byte2 == 0xa4)))))) || byte3 == 't') : ((ListenerUtil.mutListener.listen(62465) ? (byte2 >= 0xa4) : (ListenerUtil.mutListener.listen(62464) ? (byte2 <= 0xa4) : (ListenerUtil.mutListener.listen(62463) ? (byte2 > 0xa4) : (ListenerUtil.mutListener.listen(62462) ? (byte2 < 0xa4) : (ListenerUtil.mutListener.listen(62461) ? (byte2 != 0xa4) : (byte2 == 0xa4)))))) && byte3 == 't')) && byte4 == 'y')) || byte5 == 'p') : ((ListenerUtil.mutListener.listen(62467) ? ((ListenerUtil.mutListener.listen(62466) ? ((ListenerUtil.mutListener.listen(62465) ? (byte2 >= 0xa4) : (ListenerUtil.mutListener.listen(62464) ? (byte2 <= 0xa4) : (ListenerUtil.mutListener.listen(62463) ? (byte2 > 0xa4) : (ListenerUtil.mutListener.listen(62462) ? (byte2 < 0xa4) : (ListenerUtil.mutListener.listen(62461) ? (byte2 != 0xa4) : (byte2 == 0xa4)))))) || byte3 == 't') : ((ListenerUtil.mutListener.listen(62465) ? (byte2 >= 0xa4) : (ListenerUtil.mutListener.listen(62464) ? (byte2 <= 0xa4) : (ListenerUtil.mutListener.listen(62463) ? (byte2 > 0xa4) : (ListenerUtil.mutListener.listen(62462) ? (byte2 < 0xa4) : (ListenerUtil.mutListener.listen(62461) ? (byte2 != 0xa4) : (byte2 == 0xa4)))))) && byte3 == 't')) || byte4 == 'y') : ((ListenerUtil.mutListener.listen(62466) ? ((ListenerUtil.mutListener.listen(62465) ? (byte2 >= 0xa4) : (ListenerUtil.mutListener.listen(62464) ? (byte2 <= 0xa4) : (ListenerUtil.mutListener.listen(62463) ? (byte2 > 0xa4) : (ListenerUtil.mutListener.listen(62462) ? (byte2 < 0xa4) : (ListenerUtil.mutListener.listen(62461) ? (byte2 != 0xa4) : (byte2 == 0xa4)))))) || byte3 == 't') : ((ListenerUtil.mutListener.listen(62465) ? (byte2 >= 0xa4) : (ListenerUtil.mutListener.listen(62464) ? (byte2 <= 0xa4) : (ListenerUtil.mutListener.listen(62463) ? (byte2 > 0xa4) : (ListenerUtil.mutListener.listen(62462) ? (byte2 < 0xa4) : (ListenerUtil.mutListener.listen(62461) ? (byte2 != 0xa4) : (byte2 == 0xa4)))))) && byte3 == 't')) && byte4 == 'y')) && byte5 == 'p')) || byte6 == 'e') : ((ListenerUtil.mutListener.listen(62468) ? ((ListenerUtil.mutListener.listen(62467) ? ((ListenerUtil.mutListener.listen(62466) ? ((ListenerUtil.mutListener.listen(62465) ? (byte2 >= 0xa4) : (ListenerUtil.mutListener.listen(62464) ? (byte2 <= 0xa4) : (ListenerUtil.mutListener.listen(62463) ? (byte2 > 0xa4) : (ListenerUtil.mutListener.listen(62462) ? (byte2 < 0xa4) : (ListenerUtil.mutListener.listen(62461) ? (byte2 != 0xa4) : (byte2 == 0xa4)))))) || byte3 == 't') : ((ListenerUtil.mutListener.listen(62465) ? (byte2 >= 0xa4) : (ListenerUtil.mutListener.listen(62464) ? (byte2 <= 0xa4) : (ListenerUtil.mutListener.listen(62463) ? (byte2 > 0xa4) : (ListenerUtil.mutListener.listen(62462) ? (byte2 < 0xa4) : (ListenerUtil.mutListener.listen(62461) ? (byte2 != 0xa4) : (byte2 == 0xa4)))))) && byte3 == 't')) || byte4 == 'y') : ((ListenerUtil.mutListener.listen(62466) ? ((ListenerUtil.mutListener.listen(62465) ? (byte2 >= 0xa4) : (ListenerUtil.mutListener.listen(62464) ? (byte2 <= 0xa4) : (ListenerUtil.mutListener.listen(62463) ? (byte2 > 0xa4) : (ListenerUtil.mutListener.listen(62462) ? (byte2 < 0xa4) : (ListenerUtil.mutListener.listen(62461) ? (byte2 != 0xa4) : (byte2 == 0xa4)))))) || byte3 == 't') : ((ListenerUtil.mutListener.listen(62465) ? (byte2 >= 0xa4) : (ListenerUtil.mutListener.listen(62464) ? (byte2 <= 0xa4) : (ListenerUtil.mutListener.listen(62463) ? (byte2 > 0xa4) : (ListenerUtil.mutListener.listen(62462) ? (byte2 < 0xa4) : (ListenerUtil.mutListener.listen(62461) ? (byte2 != 0xa4) : (byte2 == 0xa4)))))) && byte3 == 't')) && byte4 == 'y')) || byte5 == 'p') : ((ListenerUtil.mutListener.listen(62467) ? ((ListenerUtil.mutListener.listen(62466) ? ((ListenerUtil.mutListener.listen(62465) ? (byte2 >= 0xa4) : (ListenerUtil.mutListener.listen(62464) ? (byte2 <= 0xa4) : (ListenerUtil.mutListener.listen(62463) ? (byte2 > 0xa4) : (ListenerUtil.mutListener.listen(62462) ? (byte2 < 0xa4) : (ListenerUtil.mutListener.listen(62461) ? (byte2 != 0xa4) : (byte2 == 0xa4)))))) || byte3 == 't') : ((ListenerUtil.mutListener.listen(62465) ? (byte2 >= 0xa4) : (ListenerUtil.mutListener.listen(62464) ? (byte2 <= 0xa4) : (ListenerUtil.mutListener.listen(62463) ? (byte2 > 0xa4) : (ListenerUtil.mutListener.listen(62462) ? (byte2 < 0xa4) : (ListenerUtil.mutListener.listen(62461) ? (byte2 != 0xa4) : (byte2 == 0xa4)))))) && byte3 == 't')) || byte4 == 'y') : ((ListenerUtil.mutListener.listen(62466) ? ((ListenerUtil.mutListener.listen(62465) ? (byte2 >= 0xa4) : (ListenerUtil.mutListener.listen(62464) ? (byte2 <= 0xa4) : (ListenerUtil.mutListener.listen(62463) ? (byte2 > 0xa4) : (ListenerUtil.mutListener.listen(62462) ? (byte2 < 0xa4) : (ListenerUtil.mutListener.listen(62461) ? (byte2 != 0xa4) : (byte2 == 0xa4)))))) || byte3 == 't') : ((ListenerUtil.mutListener.listen(62465) ? (byte2 >= 0xa4) : (ListenerUtil.mutListener.listen(62464) ? (byte2 <= 0xa4) : (ListenerUtil.mutListener.listen(62463) ? (byte2 > 0xa4) : (ListenerUtil.mutListener.listen(62462) ? (byte2 < 0xa4) : (ListenerUtil.mutListener.listen(62461) ? (byte2 != 0xa4) : (byte2 == 0xa4)))))) && byte3 == 't')) && byte4 == 'y')) && byte5 == 'p')) && byte6 == 'e'))) {
                    if (!ListenerUtil.mutListener.listen(62471)) {
                        addToLog("Received server-hello message!");
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(62470)) {
                        addToLog("Received invalid message (bad data)");
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(62474)) {
                websocket.disconnect(1000);
            }
        }

        @Override
        public void onConnectError(WebSocket websocket, WebSocketException cause) {
            if (!ListenerUtil.mutListener.listen(62475)) {
                WebDiagnosticsActivity.this.failWs("WS connect error: " + cause.toString());
            }
        }

        @Override
        public void onError(WebSocket websocket, WebSocketException cause) {
            if (!ListenerUtil.mutListener.listen(62476)) {
                WebDiagnosticsActivity.this.failWs("WS error: " + cause.toString());
            }
        }

        @Override
        public void onFrameError(WebSocket websocket, WebSocketException cause, WebSocketFrame frame) {
            if (!ListenerUtil.mutListener.listen(62477)) {
                WebDiagnosticsActivity.this.failWs("WS frame error: " + cause.toString());
            }
        }

        @Override
        public void onMessageError(WebSocket websocket, WebSocketException cause, List<WebSocketFrame> frames) {
            if (!ListenerUtil.mutListener.listen(62478)) {
                WebDiagnosticsActivity.this.failWs("WS message error: " + cause.toString());
            }
        }

        @Override
        public void onSendError(WebSocket websocket, WebSocketException cause, WebSocketFrame frame) {
            if (!ListenerUtil.mutListener.listen(62479)) {
                WebDiagnosticsActivity.this.failWs("WS send error: " + cause.toString());
            }
        }
    };

    private final PeerConnection.Observer pcObserver = new DefaultNoopPeerConnectionObserver() {

        @Override
        public void onSignalingChange(PeerConnection.SignalingState signalingState) {
            if (!ListenerUtil.mutListener.listen(62480)) {
                if (WebDiagnosticsActivity.this.pc == null) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(62481)) {
                WebDiagnosticsActivity.this.addToLog("PC signaling state change to " + signalingState.name());
            }
        }

        @Override
        public void onIceConnectionChange(PeerConnection.IceConnectionState iceConnectionState) {
            if (!ListenerUtil.mutListener.listen(62482)) {
                if (WebDiagnosticsActivity.this.pc == null) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(62483)) {
                WebDiagnosticsActivity.this.addToLog("ICE connection state change to " + iceConnectionState.name());
            }
            if (!ListenerUtil.mutListener.listen(62485)) {
                switch(iceConnectionState) {
                    case NEW:
                    case CHECKING:
                    case CONNECTED:
                    case COMPLETED:
                    case DISCONNECTED:
                    case CLOSED:
                        break;
                    case FAILED:
                        if (!ListenerUtil.mutListener.listen(62484)) {
                            WebDiagnosticsActivity.this.failRtc("ICE failed");
                        }
                        break;
                }
            }
        }

        @Override
        public void onIceConnectionReceivingChange(boolean b) {
            if (!ListenerUtil.mutListener.listen(62486)) {
                if (WebDiagnosticsActivity.this.pc == null) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(62487)) {
                WebDiagnosticsActivity.this.addToLog("ICE connection receiving: " + b);
            }
        }

        @Override
        public void onIceGatheringChange(PeerConnection.IceGatheringState iceGatheringState) {
            if (!ListenerUtil.mutListener.listen(62488)) {
                if (WebDiagnosticsActivity.this.pc == null) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(62489)) {
                WebDiagnosticsActivity.this.addToLog("ICE gathering state change to " + iceGatheringState.name());
            }
            if (!ListenerUtil.mutListener.listen(62491)) {
                switch(iceGatheringState) {
                    case NEW:
                    case GATHERING:
                        break;
                    case COMPLETE:
                        if (!ListenerUtil.mutListener.listen(62490)) {
                            WebDiagnosticsActivity.this.onRtcComplete(true);
                        }
                        break;
                }
            }
        }

        @Override
        public void onIceCandidate(IceCandidate candidate) {
            if (!ListenerUtil.mutListener.listen(62492)) {
                if (WebDiagnosticsActivity.this.pc == null) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(62493)) {
                WebDiagnosticsActivity.this.addToLog(WebRTCUtil.iceCandidateToString(candidate));
            }
            if (!ListenerUtil.mutListener.listen(62497)) {
                if (candidate == null) {
                    if (!ListenerUtil.mutListener.listen(62496)) {
                        WebDiagnosticsActivity.this.onRtcComplete(true);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(62494)) {
                        WebDiagnosticsActivity.this.candidateCount.incrementAndGet();
                    }
                    if (!ListenerUtil.mutListener.listen(62495)) {
                        WebDiagnosticsActivity.this.addToLog(WebRTCUtil.iceCandidateToString(candidate));
                    }
                }
            }
        }

        @Override
        public void onIceCandidatesRemoved(IceCandidate[] iceCandidates) {
            if (!ListenerUtil.mutListener.listen(62498)) {
                if (WebDiagnosticsActivity.this.pc == null) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(62500)) {
                {
                    long _loopCounter753 = 0;
                    for (IceCandidate candidate : iceCandidates) {
                        ListenerUtil.loopListener.listen("_loopCounter753", ++_loopCounter753);
                        if (!ListenerUtil.mutListener.listen(62499)) {
                            WebDiagnosticsActivity.this.addToLog("Removed: " + WebRTCUtil.iceCandidateToString(candidate));
                        }
                    }
                }
            }
        }

        @Override
        public void onRenegotiationNeeded() {
            if (!ListenerUtil.mutListener.listen(62501)) {
                if (WebDiagnosticsActivity.this.pc == null) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(62502)) {
                WebDiagnosticsActivity.this.addToLog("ICE renegotiation needed");
            }
        }
    };

    private final SdpObserver sdpObserver = new SdpObserver() {

        @Override
        public void onCreateSuccess(SessionDescription sessionDescription) {
            if (!ListenerUtil.mutListener.listen(62503)) {
                WebDiagnosticsActivity.this.addToLog("SDP create success");
            }
            assert WebDiagnosticsActivity.this.webrtcExecutor != null;
            if (!ListenerUtil.mutListener.listen(62504)) {
                WebDiagnosticsActivity.this.webrtcExecutor.execute(() -> {
                    if (WebDiagnosticsActivity.this.pc != null) {
                        WebDiagnosticsActivity.this.pc.setLocalDescription(this, sessionDescription);
                    } else {
                        WebDiagnosticsActivity.this.failRtc("Could not set local description: Peer connection is null");
                    }
                });
            }
        }

        @Override
        public void onSetSuccess() {
            if (!ListenerUtil.mutListener.listen(62505)) {
                WebDiagnosticsActivity.this.addToLog("SDP set success");
            }
        }

        @Override
        public void onCreateFailure(String s) {
            if (!ListenerUtil.mutListener.listen(62506)) {
                WebDiagnosticsActivity.this.addToLog("SDP create failure");
            }
            if (!ListenerUtil.mutListener.listen(62507)) {
                WebDiagnosticsActivity.this.failRtc("Could not create SDP: " + s);
            }
        }

        @Override
        public void onSetFailure(String s) {
            if (!ListenerUtil.mutListener.listen(62508)) {
                WebDiagnosticsActivity.this.addToLog("SDP set failure");
            }
            if (!ListenerUtil.mutListener.listen(62509)) {
                WebDiagnosticsActivity.this.failRtc("Could not set SDP: " + s);
            }
        }
    };

    @AnyThread
    private void failWs(@NonNull String message) {
        if (!ListenerUtil.mutListener.listen(62510)) {
            this.failWs(message, null);
        }
    }

    @AnyThread
    private void failWs(@NonNull String message, @Nullable Exception e) {
        if (!ListenerUtil.mutListener.listen(62512)) {
            if (e != null) {
                if (!ListenerUtil.mutListener.listen(62511)) {
                    logger.error("WS Exception", e);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(62513)) {
            this.addToLog(message);
        }
        if (!ListenerUtil.mutListener.listen(62514)) {
            this.onWsComplete(false);
        }
    }

    @AnyThread
    private void failRtc(@NonNull String message) {
        if (!ListenerUtil.mutListener.listen(62515)) {
            this.addToLog(message);
        }
        if (!ListenerUtil.mutListener.listen(62516)) {
            this.onRtcComplete(false);
        }
    }

    /**
     *  Test is complete.
     */
    @AnyThread
    private void onWsComplete(boolean success) {
        if (!ListenerUtil.mutListener.listen(62517)) {
            this.addToLog("WS tests complete (success=" + success + ")");
        }
        if (!ListenerUtil.mutListener.listen(62518)) {
            this.cleanupWs();
        }
        if (!ListenerUtil.mutListener.listen(62519)) {
            this.wsDone = true;
        }
        if (!ListenerUtil.mutListener.listen(62522)) {
            if (success) {
                if (!ListenerUtil.mutListener.listen(62521)) {
                    this.runOnUiThread(this::startRtcTest);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(62520)) {
                    RuntimeUtil.runOnUiThread(this::onComplete);
                }
            }
        }
    }

    /**
     *  Test is complete.
     */
    @AnyThread
    private void onRtcComplete(boolean success) {
        if (!ListenerUtil.mutListener.listen(62523)) {
            this.addToLog("WebRTC tests complete (success=" + success + ")");
        }
        if (!ListenerUtil.mutListener.listen(62524)) {
            this.cleanupRtc();
        }
        if (!ListenerUtil.mutListener.listen(62525)) {
            this.rtcDone = true;
        }
        if (!ListenerUtil.mutListener.listen(62526)) {
            RuntimeUtil.runOnUiThread(this::onComplete);
        }
    }

    @UiThread
    private void onComplete() {
        final Handler handler = new Handler();
        if (!ListenerUtil.mutListener.listen(62527)) {
            handler.postDelayed(() -> {
                logger.info("*** Finished Threema Web Diagnostics Test");
                this.addLogSeparator();
                this.addToLog("Done.", false);
                RuntimeUtil.runOnUiThread(() -> {
                    assert progressBar != null;
                    progressBar.setVisibility(View.GONE);
                    assert introText != null;
                    introText.setVisibility(View.GONE);
                    assert doneText != null;
                    doneText.setVisibility(View.VISIBLE);
                    assert footerButtons != null;
                    footerButtons.setVisibility(View.VISIBLE);
                });
            }, 200);
        }
    }

    @AnyThread
    private synchronized void cleanupWs() {
        if (!ListenerUtil.mutListener.listen(62528)) {
            logger.trace("cleanupWs");
        }
        if (!ListenerUtil.mutListener.listen(62532)) {
            if (this.ws != null) {
                if (!ListenerUtil.mutListener.listen(62529)) {
                    this.ws.clearListeners();
                }
                if (!ListenerUtil.mutListener.listen(62530)) {
                    this.ws.disconnect();
                }
                if (!ListenerUtil.mutListener.listen(62531)) {
                    this.ws = null;
                }
            }
        }
    }

    @AnyThread
    private synchronized void cleanupRtc() {
        if (!ListenerUtil.mutListener.listen(62533)) {
            logger.trace("cleanupRtc");
        }
        if (!ListenerUtil.mutListener.listen(62536)) {
            if (this.pc != null) {
                assert this.webrtcExecutor != null;
                if (!ListenerUtil.mutListener.listen(62534)) {
                    this.webrtcExecutor.execute(this.pc::dispose);
                }
                if (!ListenerUtil.mutListener.listen(62535)) {
                    this.pc = null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(62539)) {
            if (this.pcFactory != null) {
                assert this.webrtcExecutor != null;
                if (!ListenerUtil.mutListener.listen(62537)) {
                    this.webrtcExecutor.execute(this.pcFactory::dispose);
                }
                if (!ListenerUtil.mutListener.listen(62538)) {
                    this.pcFactory = null;
                }
            }
        }
    }

    @AnyThread
    private synchronized void cleanup() {
        if (!ListenerUtil.mutListener.listen(62540)) {
            logger.info("Cleaning up resources");
        }
        if (!ListenerUtil.mutListener.listen(62541)) {
            this.cleanupWs();
        }
        if (!ListenerUtil.mutListener.listen(62542)) {
            this.cleanupRtc();
        }
        if (!ListenerUtil.mutListener.listen(62548)) {
            if (this.webrtcExecutor != null) {
                if (!ListenerUtil.mutListener.listen(62543)) {
                    this.webrtcExecutor.shutdown();
                }
                try {
                    if (!ListenerUtil.mutListener.listen(62546)) {
                        if (!this.webrtcExecutor.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                            if (!ListenerUtil.mutListener.listen(62545)) {
                                this.webrtcExecutor.shutdownNow();
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    if (!ListenerUtil.mutListener.listen(62544)) {
                        this.webrtcExecutor.shutdownNow();
                    }
                }
                if (!ListenerUtil.mutListener.listen(62547)) {
                    this.webrtcExecutor = null;
                }
            }
        }
    }

    @Override
    public void onYes(String tag, String text) {
        if (!ListenerUtil.mutListener.listen(62550)) {
            if (DIALOG_TAG_SEND_VOIP_DEBUG.equals(tag)) {
                if (!ListenerUtil.mutListener.listen(62549)) {
                    sendToSupport(text);
                }
            }
        }
    }

    @Override
    public void onNo(String tag) {
    }

    @Override
    public void onNeutral(String tag) {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(62552)) {
            // noinspection SwitchStatementWithTooFewBranches
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(62551)) {
                        finish();
                    }
                    return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
