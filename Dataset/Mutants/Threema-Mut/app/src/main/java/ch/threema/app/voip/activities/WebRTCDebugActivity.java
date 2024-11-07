/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2017-2021 Threema GmbH
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
package ch.threema.app.voip.activities;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webrtc.IceCandidate;
import org.webrtc.PeerConnection;
import org.webrtc.SessionDescription;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.ActionBar;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.ThreemaToolbarActivity;
import ch.threema.app.dialogs.TextEntryDialog;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.messagereceiver.ContactMessageReceiver;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.MessageService;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.LocaleUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.utils.WebRTCUtil;
import ch.threema.app.voip.PeerConnectionClient;
import ch.threema.app.voip.util.SdpPatcher;
import ch.threema.logging.WebRTCLoggable;
import ch.threema.protobuf.callsignaling.CallSignaling;
import ch.threema.storage.models.ContactModel;
import static ch.threema.app.preference.SettingsTroubleshootingFragment.THREEMA_SUPPORT_IDENTITY;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * An activity to debug problems with WebRTC (in the context of Threema Calls).
 */
public class WebRTCDebugActivity extends ThreemaToolbarActivity implements PeerConnectionClient.Events, TextEntryDialog.TextEntryDialogClickListener {

    private static final Logger logger = LoggerFactory.getLogger(WebRTCDebugActivity.class);

    private static final String DIALOG_TAG_SEND_WEBRTC_DEBUG = "swd";

    // Threema services
    @NonNull
    private MessageService messageService;

    @NonNull
    private ContactService contactService;

    // Views
    @NonNull
    private ProgressBar progressBar;

    @NonNull
    private TextView introText;

    @NonNull
    private TextView doneText;

    @Nullable
    private Button copyButton;

    @Nullable
    private Button sendButton;

    @Nullable
    private View footerButtons;

    @Nullable
    private PeerConnectionClient peerConnectionClient;

    @NonNull
    private final List<String> eventLog = new ArrayList<>();

    @NonNull
    private ArrayAdapter adapter;

    private boolean gatheringComplete = false;

    private String clipboardString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(58544)) {
            logger.trace("onCreate");
        }
        if (!ListenerUtil.mutListener.listen(58545)) {
            super.onCreate(savedInstanceState);
        }
        // Get services
        final ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(58548)) {
            if (serviceManager == null) {
                if (!ListenerUtil.mutListener.listen(58546)) {
                    logger.error("Could not obtain service manager");
                }
                if (!ListenerUtil.mutListener.listen(58547)) {
                    finish();
                }
                return;
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(58551)) {
                this.messageService = serviceManager.getMessageService();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(58549)) {
                logger.error("Could not obtain message service", e);
            }
            if (!ListenerUtil.mutListener.listen(58550)) {
                finish();
            }
            return;
        }
        try {
            if (!ListenerUtil.mutListener.listen(58554)) {
                this.contactService = serviceManager.getContactService();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(58552)) {
                logger.error("Could not obtain contact service", e);
            }
            if (!ListenerUtil.mutListener.listen(58553)) {
                finish();
            }
            return;
        }
        final ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(58557)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(58555)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(58556)) {
                    actionBar.setTitle(R.string.voip_webrtc_debug);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(58558)) {
            // Get view references
            this.progressBar = findViewById(R.id.webrtc_debug_loading);
        }
        if (!ListenerUtil.mutListener.listen(58559)) {
            this.introText = findViewById(R.id.webrtc_debug_intro);
        }
        if (!ListenerUtil.mutListener.listen(58560)) {
            this.doneText = findViewById(R.id.webrtc_debug_done);
        }
        if (!ListenerUtil.mutListener.listen(58561)) {
            this.copyButton = findViewById(R.id.webrtc_debug_copy_button);
        }
        if (!ListenerUtil.mutListener.listen(58562)) {
            this.sendButton = findViewById(R.id.webrtc_debug_send_button);
        }
        if (!ListenerUtil.mutListener.listen(58563)) {
            this.footerButtons = findViewById(R.id.webrtc_debug_footer_buttons);
        }
        // Wire up start button
        final Button startButton = findViewById(R.id.webrtc_debug_start);
        if (!ListenerUtil.mutListener.listen(58564)) {
            startButton.setOnClickListener(view -> {
                startButton.setVisibility(View.GONE);
                WebRTCDebugActivity.this.startGathering();
            });
        }
        // Wire up copy button
        assert this.copyButton != null;
        if (!ListenerUtil.mutListener.listen(58565)) {
            this.copyButton.setOnClickListener(view -> {
                if (!TestUtil.empty(this.clipboardString)) {
                    this.copyToClipboard(this.clipboardString);
                }
            });
        }
        // Wire up send button
        assert this.sendButton != null;
        if (!ListenerUtil.mutListener.listen(58566)) {
            this.sendButton.setOnClickListener(view -> {
                if (!TestUtil.empty(this.clipboardString)) {
                    this.prepareSendToSupport();
                }
            });
        }
        // Initialize list of candidates
        final ListView candidatesList = findViewById(R.id.webrtc_debug_candidates);
        if (!ListenerUtil.mutListener.listen(58567)) {
            this.adapter = new ArrayAdapter<>(this, R.layout.item_webrtc_debug_list, this.eventLog);
        }
        if (!ListenerUtil.mutListener.listen(58568)) {
            candidatesList.setAdapter(this.adapter);
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_webrtc_debug;
    }

    @Override
    protected void onStart() {
        if (!ListenerUtil.mutListener.listen(58569)) {
            logger.trace("onStart");
        }
        if (!ListenerUtil.mutListener.listen(58570)) {
            super.onStart();
        }
    }

    @Override
    protected void onStop() {
        if (!ListenerUtil.mutListener.listen(58571)) {
            logger.trace("onStop");
        }
        if (!ListenerUtil.mutListener.listen(58572)) {
            logger.info("*** Finished WebRTC Debugging Test");
        }
        if (!ListenerUtil.mutListener.listen(58573)) {
            this.cleanup();
        }
        if (!ListenerUtil.mutListener.listen(58574)) {
            super.onStop();
        }
    }

    @UiThread
    private void startGathering() {
        if (!ListenerUtil.mutListener.listen(58575)) {
            logger.info("*** Starting WebRTC Debugging Test ***");
        }
        if (!ListenerUtil.mutListener.listen(58576)) {
            logger.info("Setting up peer connection");
        }
        if (!ListenerUtil.mutListener.listen(58577)) {
            this.eventLog.clear();
        }
        if (!ListenerUtil.mutListener.listen(58578)) {
            this.clipboardString = "";
        }
        if (!ListenerUtil.mutListener.listen(58579)) {
            this.addToLog("Starting Call Diagnostics...");
        }
        if (!ListenerUtil.mutListener.listen(58580)) {
            this.addToLog("----------------");
        }
        if (!ListenerUtil.mutListener.listen(58581)) {
            this.addToLog("Device info: " + ConfigUtils.getDeviceInfo(this, false));
        }
        if (!ListenerUtil.mutListener.listen(58582)) {
            this.addToLog("App version: " + ConfigUtils.getFullAppVersion(this));
        }
        if (!ListenerUtil.mutListener.listen(58583)) {
            this.addToLog("App language: " + LocaleUtil.getAppLanguage());
        }
        if (!ListenerUtil.mutListener.listen(58584)) {
            this.addToLog("----------------");
        }
        if (!ListenerUtil.mutListener.listen(58585)) {
            // Show settings
            this.addToLog("Enabled: calls=" + preferenceService.isVoipEnabled() + " video=" + preferenceService.isVideoCallsEnabled());
        }
        if (!ListenerUtil.mutListener.listen(58586)) {
            this.addToLog("Settings: aec=" + preferenceService.getAECMode() + " video_codec=" + preferenceService.getVideoCodec() + " video_profile=" + preferenceService.getVideoCallsProfile() + " force_turn=" + preferenceService.getForceTURN());
        }
        if (!ListenerUtil.mutListener.listen(58587)) {
            this.addToLog("----------------");
        }
        if (!ListenerUtil.mutListener.listen(58588)) {
            // Update UI visibility
            this.progressBar.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(58589)) {
            this.introText.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(58590)) {
            this.doneText.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(58591)) {
            this.footerButtons.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(58592)) {
            // Change log level in the log forwarder
            WebRTCLoggable.setMinLevelFilter(Log.INFO);
        }
        // Initialize peer connection client
        final boolean useOpenSLES = false;
        final boolean disableBuiltInAEC = false;
        final boolean disableBuiltInAGC = false;
        final boolean disableBuiltInNS = false;
        final boolean enableLevelControl = false;
        final boolean videoCallEnabled = true;
        final boolean useVideoHwAcceleration = true;
        final boolean videoCodecEnableVP8 = true;
        final boolean videoCodecEnableH264HiP = true;
        final SdpPatcher.RtpHeaderExtensionConfig rtpHeaderExtensionConfig = SdpPatcher.RtpHeaderExtensionConfig.ENABLE_WITH_ONE_AND_TWO_BYTE_HEADER;
        final boolean forceTurn = false;
        final boolean gatherContinually = false;
        final boolean allowIpv6 = true;
        final PeerConnectionClient.PeerConnectionParameters peerConnectionParameters = new PeerConnectionClient.PeerConnectionParameters(false, useOpenSLES, disableBuiltInAEC, disableBuiltInAGC, disableBuiltInNS, enableLevelControl, videoCallEnabled, useVideoHwAcceleration, videoCodecEnableVP8, videoCodecEnableH264HiP, rtpHeaderExtensionConfig, forceTurn, gatherContinually, allowIpv6);
        if (!ListenerUtil.mutListener.listen(58593)) {
            this.peerConnectionClient = new PeerConnectionClient(getApplicationContext(), peerConnectionParameters, null, 2);
        }
        if (!ListenerUtil.mutListener.listen(58594)) {
            this.peerConnectionClient.setEventHandler(this);
        }
        // Create peer connection factory
        boolean factoryCreated = false;
        Throwable factoryCreateException = null;
        try {
            if (!ListenerUtil.mutListener.listen(58597)) {
                factoryCreated = peerConnectionClient.createPeerConnectionFactory().get(10, TimeUnit.SECONDS);
            }
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            if (!ListenerUtil.mutListener.listen(58595)) {
                factoryCreateException = e;
            }
            if (!ListenerUtil.mutListener.listen(58596)) {
                logger.error("Could not create peer connection factory", e);
            }
        }
        if (!ListenerUtil.mutListener.listen(58602)) {
            if (!factoryCreated) {
                if (!ListenerUtil.mutListener.listen(58598)) {
                    WebRTCDebugActivity.this.addToLog("Could not create peer connection factory");
                }
                if (!ListenerUtil.mutListener.listen(58600)) {
                    if (factoryCreateException != null) {
                        if (!ListenerUtil.mutListener.listen(58599)) {
                            WebRTCDebugActivity.this.addToLog(factoryCreateException.getMessage());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(58601)) {
                    WebRTCDebugActivity.this.onComplete();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(58603)) {
            // Create peer connection
            peerConnectionClient.createPeerConnection();
        }
        if (!ListenerUtil.mutListener.listen(58604)) {
            // Create offer to trigger ICE collection
            this.addToLog("ICE Candidates found:");
        }
        if (!ListenerUtil.mutListener.listen(58605)) {
            peerConnectionClient.createOffer();
        }
        // Schedule gathering timeout
        final Handler handler = new Handler();
        if (!ListenerUtil.mutListener.listen(58606)) {
            handler.postDelayed(() -> {
                if (!gatheringComplete) {
                    logger.info("Timeout");
                    WebRTCDebugActivity.this.addToLog("Timed out");
                    WebRTCDebugActivity.this.onComplete();
                }
            }, 20000);
        }
    }

    @AnyThread
    private void addToLog(final String value) {
        if (!ListenerUtil.mutListener.listen(58607)) {
            clipboardString += value + "\n";
        }
        if (!ListenerUtil.mutListener.listen(58608)) {
            RuntimeUtil.runOnUiThread(() -> {
                synchronized (WebRTCDebugActivity.this.eventLog) {
                    WebRTCDebugActivity.this.eventLog.add(value);
                    WebRTCDebugActivity.this.adapter.notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    @AnyThread
    public void onLocalDescription(long callId, SessionDescription sdp) {
        if (!ListenerUtil.mutListener.listen(58609)) {
            logger.info("onLocalDescription: {}", sdp);
        }
    }

    @Override
    @AnyThread
    public void onRemoteDescriptionSet(long callId) {
        if (!ListenerUtil.mutListener.listen(58610)) {
            logger.info("onRemoteDescriptionSet");
        }
    }

    @Override
    @AnyThread
    public void onIceCandidate(long callId, IceCandidate candidate) {
        if (!ListenerUtil.mutListener.listen(58611)) {
            logger.info("onIceCandidate: {}", candidate);
        }
        if (!ListenerUtil.mutListener.listen(58612)) {
            this.addToLog(WebRTCUtil.iceCandidateToString(candidate));
        }
    }

    @Override
    public void onIceChecking(long callId) {
        if (!ListenerUtil.mutListener.listen(58613)) {
            logger.info("onIceChecking");
        }
        if (!ListenerUtil.mutListener.listen(58614)) {
            this.addToLog("ICE Checking");
        }
    }

    @Override
    @AnyThread
    public void onIceConnected(long callId) {
        if (!ListenerUtil.mutListener.listen(58615)) {
            logger.info("onIceConnected");
        }
        if (!ListenerUtil.mutListener.listen(58616)) {
            this.addToLog("ICE Connected");
        }
    }

    @Override
    @AnyThread
    public void onIceDisconnected(long callId) {
        if (!ListenerUtil.mutListener.listen(58617)) {
            logger.info("onIceDisconnected");
        }
        if (!ListenerUtil.mutListener.listen(58618)) {
            this.addToLog("ICE Disconnected");
        }
    }

    @Override
    @AnyThread
    public void onIceFailed(long callId) {
        if (!ListenerUtil.mutListener.listen(58619)) {
            logger.info("onIceFailed");
        }
        if (!ListenerUtil.mutListener.listen(58620)) {
            this.addToLog("ICE Failed");
        }
    }

    @Override
    public void onIceGatheringStateChange(long callId, PeerConnection.IceGatheringState newState) {
        if (!ListenerUtil.mutListener.listen(58621)) {
            logger.info("onIceGatheringStateChange: {}", newState);
        }
        if (!ListenerUtil.mutListener.listen(58626)) {
            if ((ListenerUtil.mutListener.listen(58622) ? (newState == PeerConnection.IceGatheringState.COMPLETE || !this.gatheringComplete) : (newState == PeerConnection.IceGatheringState.COMPLETE && !this.gatheringComplete))) {
                if (!ListenerUtil.mutListener.listen(58623)) {
                    // We're done.
                    this.addToLog("----------------");
                }
                if (!ListenerUtil.mutListener.listen(58624)) {
                    this.addToLog("Done!");
                }
                if (!ListenerUtil.mutListener.listen(58625)) {
                    this.onComplete();
                }
            }
        }
    }

    @Override
    @AnyThread
    public void onPeerConnectionClosed(long callId) {
        if (!ListenerUtil.mutListener.listen(58627)) {
            logger.info("onPeerConnectionClosed");
        }
        if (!ListenerUtil.mutListener.listen(58628)) {
            this.addToLog("PeerConnection closed");
        }
    }

    @Override
    @AnyThread
    public void onError(long callId, @NonNull final String description, boolean abortCall) {
        final String msg = String.format("%s (abortCall: %s)", description, abortCall);
        if (!ListenerUtil.mutListener.listen(58629)) {
            logger.info("onError: " + msg);
        }
        if (!ListenerUtil.mutListener.listen(58630)) {
            this.addToLog("Error: " + msg);
        }
    }

    @Override
    public void onSignalingMessage(long callId, @NonNull CallSignaling.Envelope envelope) {
        if (!ListenerUtil.mutListener.listen(58631)) {
            logger.info("onSignalingMessage: {}", envelope);
        }
    }

    /**
     *  Test is complete.
     */
    @AnyThread
    private void onComplete() {
        if (!ListenerUtil.mutListener.listen(58632)) {
            this.gatheringComplete = true;
        }
        if (!ListenerUtil.mutListener.listen(58633)) {
            // Reset log level
            WebRTCLoggable.setMinLevelFilter(Log.WARN);
        }
        if (!ListenerUtil.mutListener.listen(58634)) {
            RuntimeUtil.runOnUiThread(() -> {
                progressBar.setVisibility(View.GONE);
                introText.setVisibility(View.GONE);
                doneText.setVisibility(View.VISIBLE);
                footerButtons.setVisibility(View.VISIBLE);
            });
        }
    }

    /**
     *  Copy the specified string to the system clipboard.
     */
    private void copyToClipboard(@NonNull String clipboardString) {
        final ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        final ClipData clip = ClipData.newPlainText(getString(R.string.voip_webrtc_debug), clipboardString);
        if (!ListenerUtil.mutListener.listen(58635)) {
            clipboard.setPrimaryClip(clip);
        }
        if (!ListenerUtil.mutListener.listen(58636)) {
            Toast.makeText(getApplicationContext(), getString(R.string.voip_webrtc_debug_copied), Toast.LENGTH_LONG).show();
        }
    }

    /**
     *  Show a dialog to confirm that the information should be sent to the support.
     */
    private void prepareSendToSupport() {
        TextEntryDialog dialog = TextEntryDialog.newInstance(R.string.send_to_support, R.string.enter_description, R.string.send, R.string.cancel, 5, 3000, 1);
        if (!ListenerUtil.mutListener.listen(58637)) {
            dialog.show(getSupportFragmentManager(), DIALOG_TAG_SEND_WEBRTC_DEBUG);
        }
    }

    @Override
    public void onYes(String tag, String text) {
        if (!ListenerUtil.mutListener.listen(58639)) {
            if (DIALOG_TAG_SEND_WEBRTC_DEBUG.equals(tag)) {
                if (!ListenerUtil.mutListener.listen(58638)) {
                    // User confirmed that log should be sent to support
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

    @SuppressLint("StaticFieldLeak")
    private void sendToSupport(@NonNull String caption) {
        if (!ListenerUtil.mutListener.listen(58642)) {
            if ((ListenerUtil.mutListener.listen(58640) ? (this.contactService == null && messageService == null) : (this.contactService == null || messageService == null))) {
                if (!ListenerUtil.mutListener.listen(58641)) {
                    logger.error("Cannot send to support, some services are null");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(58647)) {
            new AsyncTask<Void, Void, Boolean>() {

                @Override
                protected Boolean doInBackground(Void... voids) {
                    try {
                        final ContactModel contactModel = contactService.getOrCreateByIdentity(THREEMA_SUPPORT_IDENTITY, true);
                        final ContactMessageReceiver messageReceiver = contactService.createReceiver(contactModel);
                        if (!ListenerUtil.mutListener.listen(58644)) {
                            messageService.sendText(clipboardString + "\n---\n" + caption + "\n---\n" + ConfigUtils.getDeviceInfo(WebRTCDebugActivity.this, false) + "\n" + "Threema " + ConfigUtils.getFullAppVersion(WebRTCDebugActivity.this) + "\n" + getMyIdentity(), messageReceiver);
                        }
                        return true;
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(58643)) {
                            logger.error("Exception while sending information to support", e);
                        }
                        return false;
                    }
                }

                @Override
                protected void onPostExecute(Boolean success) {
                    if (!ListenerUtil.mutListener.listen(58645)) {
                        Toast.makeText(getApplicationContext(), Boolean.TRUE.equals(success) ? R.string.message_sent : R.string.an_error_occurred, Toast.LENGTH_LONG).show();
                    }
                    if (!ListenerUtil.mutListener.listen(58646)) {
                        finish();
                    }
                }
            }.execute();
        }
    }

    @AnyThread
    private synchronized void cleanup() {
        if (!ListenerUtil.mutListener.listen(58649)) {
            if (this.peerConnectionClient != null) {
                if (!ListenerUtil.mutListener.listen(58648)) {
                    this.peerConnectionClient.close();
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(58651)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(58650)) {
                        finish();
                    }
                    return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
