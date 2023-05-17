/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2016-2021 Threema GmbH
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

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import org.saltyrtc.client.crypto.CryptoException;
import org.saltyrtc.client.exceptions.InvalidKeyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.annotation.WorkerThread;
import androidx.appcompat.app.ActionBar;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ch.threema.app.utils.QRScannerUtil;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.DisableBatteryOptimizationsActivity;
import ch.threema.app.activities.ThreemaToolbarActivity;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.dialogs.SelectorDialog;
import ch.threema.app.dialogs.SimpleStringAlertDialog;
import ch.threema.app.dialogs.TextEntryDialog;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.ui.EmptyRecyclerView;
import ch.threema.app.ui.SilentSwitchCompat;
import ch.threema.app.utils.AppRestrictionUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.DialogUtil;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.app.utils.LogUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.webclient.Protocol;
import ch.threema.app.webclient.adapters.SessionListAdapter;
import ch.threema.app.webclient.exceptions.HandshakeException;
import ch.threema.app.webclient.listeners.WebClientServiceListener;
import ch.threema.app.webclient.listeners.WebClientSessionListener;
import ch.threema.app.webclient.manager.WebClientListenerManager;
import ch.threema.app.webclient.manager.WebClientServiceManager;
import ch.threema.app.webclient.services.QRCodeParser;
import ch.threema.app.webclient.services.QRCodeParserImpl;
import ch.threema.app.webclient.services.SessionService;
import ch.threema.app.webclient.services.instance.DisconnectContext;
import ch.threema.app.webclient.services.instance.SessionInstanceService;
import ch.threema.app.webclient.state.WebClientSessionState;
import ch.threema.base.ThreemaException;
import ch.threema.client.Base64;
import ch.threema.storage.DatabaseServiceNew;
import ch.threema.storage.models.WebClientSessionModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@UiThread
public class SessionsActivity extends ThreemaToolbarActivity implements SelectorDialog.SelectorDialogClickListener, GenericAlertDialog.DialogClickListener, TextEntryDialog.TextEntryDialogClickListener {

    @NonNull
    private static final Logger logger = LoggerFactory.getLogger(SessionsActivity.class);

    @NonNull
    private static final String LOG_TAG = "WebClient.SessionFragment";

    @NonNull
    private static final String DIALOG_TAG_ITEM_MENU = "itemMenu";

    @NonNull
    private static final String DIALOG_TAG_REALLY_DELETE_SESSION = "deleteSession";

    @NonNull
    private static final String DIALOG_TAG_REALLY_DELETE_ALL_SESSIONS = "deleteAllSession";

    @NonNull
    private static final String DIALOG_TAG_REALLY_START_SESSION_BY_PAYLOAD = "startByPayload";

    @NonNull
    private static final String DIALOG_TAG_EDIT_LABEL = "editLabel";

    private static final int REQUEST_ID_INTRO_WIZARD = 338;

    private static final int REQUEST_ID_DISABLE_BATTERY_OPTIMIZATIONS = 339;

    private static final int MENU_POS_RENAME = 0;

    private static final int MENU_POS_START_STOP = 1;

    private static final int MENU_POS_REMOVE = 2;

    private static final int PERMISSION_REQUEST_CAMERA = 1;

    @NonNull
    private static final String DIALOG_TAG_MDM_CONSTRAINTS = "webConstrainedByAdmin";

    // Threema services
    private WebClientServiceManager webClientServiceManager;

    private SessionService sessionService;

    private DatabaseServiceNew databaseService;

    private EmptyRecyclerView listView;

    private SessionListAdapter listAdapter;

    private boolean initialized = false;

    private SilentSwitchCompat enableSwitch;

    private ExtendedFloatingActionButton floatingActionButton;

    /**
     *  Called for all WebClientService related events.
     */
    @NonNull
    private final WebClientServiceListener webClientServiceListener = new WebClientServiceListener() {

        @Override
        @AnyThread
        public void onEnabled() {
            if (!ListenerUtil.mutListener.listen(61978)) {
                this.updateView(false);
            }
        }

        @Override
        @AnyThread
        public void onDisabled() {
            if (!ListenerUtil.mutListener.listen(61979)) {
                this.updateView(false);
            }
        }

        @Override
        @AnyThread
        public void onStarted(@NonNull final WebClientSessionModel model, @NonNull final byte[] permanentKey, @NonNull final String browser) {
            if (!ListenerUtil.mutListener.listen(61980)) {
                this.updateView(false);
            }
        }

        @Override
        @AnyThread
        public void onStateChanged(@NonNull final WebClientSessionModel model, @NonNull final WebClientSessionState oldState, @NonNull final WebClientSessionState newState) {
            if (!ListenerUtil.mutListener.listen(61981)) {
                this.updateView(true);
            }
        }

        @Override
        @AnyThread
        public void onStopped(@NonNull final WebClientSessionModel model, @NonNull final DisconnectContext reason) {
            if (!ListenerUtil.mutListener.listen(61982)) {
                this.updateView(true);
            }
        }

        @Override
        @AnyThread
        public void onPushTokenChanged(@NonNull final WebClientSessionModel model, @Nullable final String newPushToken) {
            if (!ListenerUtil.mutListener.listen(61983)) {
                this.updateView(true);
            }
        }

        private void updateView(final boolean notifyDataSetChanged) {
            if (!ListenerUtil.mutListener.listen(61988)) {
                RuntimeUtil.runOnUiThread(new Runnable() {

                    @Override
                    @UiThread
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(61986)) {
                            if ((ListenerUtil.mutListener.listen(61984) ? (notifyDataSetChanged || SessionsActivity.this.listAdapter != null) : (notifyDataSetChanged && SessionsActivity.this.listAdapter != null))) {
                                if (!ListenerUtil.mutListener.listen(61985)) {
                                    SessionsActivity.this.listAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(61987)) {
                            SessionsActivity.this.updateView();
                        }
                    }
                });
            }
        }
    };

    /**
     *  Called when a session is changed.
     */
    @NonNull
    private final WebClientSessionListener webClientSessionListener = new WebClientSessionListener() {

        @Override
        @AnyThread
        public void onModified(@NonNull final WebClientSessionModel model) {
            if (!ListenerUtil.mutListener.listen(62007)) {
                RuntimeUtil.runOnUiThread(new Runnable() {

                    @Override
                    @UiThread
                    public void run() {
                        final SessionListAdapter listAdapter = SessionsActivity.this.listAdapter;
                        if (!ListenerUtil.mutListener.listen(62006)) {
                            if (listAdapter != null) {
                                if (!ListenerUtil.mutListener.listen(62005)) {
                                    {
                                        long _loopCounter744 = 0;
                                        for (int pos = 0; (ListenerUtil.mutListener.listen(62004) ? (pos >= listAdapter.getItemCount()) : (ListenerUtil.mutListener.listen(62003) ? (pos <= listAdapter.getItemCount()) : (ListenerUtil.mutListener.listen(62002) ? (pos > listAdapter.getItemCount()) : (ListenerUtil.mutListener.listen(62001) ? (pos != listAdapter.getItemCount()) : (ListenerUtil.mutListener.listen(62000) ? (pos == listAdapter.getItemCount()) : (pos < listAdapter.getItemCount())))))); pos++) {
                                            ListenerUtil.loopListener.listen("_loopCounter744", ++_loopCounter744);
                                            if (!ListenerUtil.mutListener.listen(61999)) {
                                                if (listAdapter.getEntity(pos).getId() == model.getId()) {
                                                    if (!ListenerUtil.mutListener.listen(61989)) {
                                                        // Update model in list adapter
                                                        listAdapter.setEntity(pos, model);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(61990)) {
                                                        // Notify adapter about changes
                                                        listAdapter.notifyItemChanged(pos);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(61998)) {
                                                        // Move session to top
                                                        if ((ListenerUtil.mutListener.listen(61995) ? (pos >= 0) : (ListenerUtil.mutListener.listen(61994) ? (pos <= 0) : (ListenerUtil.mutListener.listen(61993) ? (pos > 0) : (ListenerUtil.mutListener.listen(61992) ? (pos < 0) : (ListenerUtil.mutListener.listen(61991) ? (pos == 0) : (pos != 0))))))) {
                                                            if (!ListenerUtil.mutListener.listen(61996)) {
                                                                SessionsActivity.this.closeAllDialogs();
                                                            }
                                                            if (!ListenerUtil.mutListener.listen(61997)) {
                                                                listAdapter.moveEntity(pos, 0);
                                                            }
                                                        }
                                                    }
                                                    return;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }

        @Override
        @AnyThread
        public void onRemoved(@NonNull final WebClientSessionModel model) {
            if (!ListenerUtil.mutListener.listen(62018)) {
                RuntimeUtil.runOnUiThread(new Runnable() {

                    @Override
                    @UiThread
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(62017)) {
                            if (listAdapter != null) {
                                final SessionListAdapter listAdapter = SessionsActivity.this.listAdapter;
                                if (!ListenerUtil.mutListener.listen(62016)) {
                                    {
                                        long _loopCounter745 = 0;
                                        for (int pos = 0; (ListenerUtil.mutListener.listen(62015) ? (pos >= listAdapter.getItemCount()) : (ListenerUtil.mutListener.listen(62014) ? (pos <= listAdapter.getItemCount()) : (ListenerUtil.mutListener.listen(62013) ? (pos > listAdapter.getItemCount()) : (ListenerUtil.mutListener.listen(62012) ? (pos != listAdapter.getItemCount()) : (ListenerUtil.mutListener.listen(62011) ? (pos == listAdapter.getItemCount()) : (pos < listAdapter.getItemCount())))))); pos++) {
                                            ListenerUtil.loopListener.listen("_loopCounter745", ++_loopCounter745);
                                            if (!ListenerUtil.mutListener.listen(62010)) {
                                                if (listAdapter.getEntity(pos).getId() == model.getId()) {
                                                    if (!ListenerUtil.mutListener.listen(62008)) {
                                                        // Remove session from list
                                                        SessionsActivity.this.closeAllDialogs();
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(62009)) {
                                                        listAdapter.deleteEntity(pos);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }

        @Override
        @AnyThread
        public void onCreated(@NonNull final WebClientSessionModel model) {
            if (!ListenerUtil.mutListener.listen(62022)) {
                RuntimeUtil.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        final SessionListAdapter listAdapter = SessionsActivity.this.listAdapter;
                        if (!ListenerUtil.mutListener.listen(62021)) {
                            if (listAdapter != null) {
                                if (!ListenerUtil.mutListener.listen(62019)) {
                                    // Move session to top
                                    SessionsActivity.this.closeAllDialogs();
                                }
                                if (!ListenerUtil.mutListener.listen(62020)) {
                                    listAdapter.addEntity(0, model);
                                }
                            }
                        }
                    }
                });
            }
        }
    };

    private boolean activityInitialized = false;

    /**
     *  Make sure that all open dialogs are closed.
     */
    private void closeAllDialogs() {
        if (!ListenerUtil.mutListener.listen(62023)) {
            DialogUtil.dismissDialog(getSupportFragmentManager(), DIALOG_TAG_ITEM_MENU, true);
        }
        if (!ListenerUtil.mutListener.listen(62024)) {
            DialogUtil.dismissDialog(getSupportFragmentManager(), DIALOG_TAG_REALLY_DELETE_SESSION, true);
        }
        if (!ListenerUtil.mutListener.listen(62025)) {
            DialogUtil.dismissDialog(getSupportFragmentManager(), DIALOG_TAG_REALLY_DELETE_ALL_SESSIONS, true);
        }
        if (!ListenerUtil.mutListener.listen(62026)) {
            DialogUtil.dismissDialog(getSupportFragmentManager(), DIALOG_TAG_EDIT_LABEL, true);
        }
    }

    private boolean requireInstances() {
        if (!ListenerUtil.mutListener.listen(62027)) {
            if (TestUtil.required(this.webClientServiceManager, this.sessionService, this.databaseService)) {
                return true;
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(62030)) {
                if (this.serviceManager == null) {
                    if (!ListenerUtil.mutListener.listen(62029)) {
                        logger.error("Service manager is null");
                    }
                    return false;
                }
            }
            if (!ListenerUtil.mutListener.listen(62031)) {
                this.webClientServiceManager = this.serviceManager.getWebClientServiceManager();
            }
            if (!ListenerUtil.mutListener.listen(62032)) {
                this.sessionService = this.webClientServiceManager.getSessionService();
            }
            if (!ListenerUtil.mutListener.listen(62033)) {
                this.databaseService = this.serviceManager.getDatabaseServiceNew();
            }
        } catch (ThreemaException e) {
            if (!ListenerUtil.mutListener.listen(62028)) {
                logger.error("Exception", e);
            }
            return false;
        }
        return TestUtil.required(this.webClientServiceManager, this.sessionService, this.databaseService);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(62034)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(62036)) {
            // Make sure that all necessary services are initialized
            if (!this.requireInstances()) {
                if (!ListenerUtil.mutListener.listen(62035)) {
                    this.finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(62040)) {
            if ((ListenerUtil.mutListener.listen(62037) ? (ConfigUtils.isWorkRestricted() || AppRestrictionUtil.isWebDisabled(this)) : (ConfigUtils.isWorkRestricted() && AppRestrictionUtil.isWebDisabled(this)))) {
                final String msg = getString(R.string.webclient_cannot_restore) + ": " + getString(R.string.webclient_disabled);
                if (!ListenerUtil.mutListener.listen(62038)) {
                    Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                }
                if (!ListenerUtil.mutListener.listen(62039)) {
                    this.finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(62041)) {
            // Remove old sessions
            this.cleanupWebclientSessions();
        }
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(62044)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(62042)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(62043)) {
                    actionBar.setTitle(R.string.webclient);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(62045)) {
            this.enableSwitch = findViewById(R.id.switch_button);
        }
        TextView enableSwitchText = findViewById(R.id.switch_text);
        if (!ListenerUtil.mutListener.listen(62046)) {
            this.enableSwitch.setOnOffLabel(enableSwitchText);
        }
        if (!ListenerUtil.mutListener.listen(62053)) {
            this.enableSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                @UiThread
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (!ListenerUtil.mutListener.listen(62052)) {
                        if (compoundButton.isShown()) {
                            if (!ListenerUtil.mutListener.listen(62051)) {
                                if (isChecked) {
                                    if (!ListenerUtil.mutListener.listen(62050)) {
                                        if (SessionsActivity.this.sessionService.getAllSessionModels().size() == 0) {
                                            if (!ListenerUtil.mutListener.listen(62049)) {
                                                // when the enable switch is enabled.
                                                SessionsActivity.this.initiateSession();
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(62048)) {
                                                SessionsActivity.this.sessionService.setEnabled(true);
                                            }
                                        }
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(62047)) {
                                        SessionsActivity.this.sessionService.setEnabled(false);
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(62054)) {
            this.enableSwitch.setCheckedSilent(this.sessionService.isEnabled());
        }
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        if (!ListenerUtil.mutListener.listen(62055)) {
            this.listView = this.findViewById(R.id.recycler);
        }
        if (!ListenerUtil.mutListener.listen(62056)) {
            this.listView.setHasFixedSize(true);
        }
        if (!ListenerUtil.mutListener.listen(62057)) {
            this.listView.setLayoutManager(linearLayoutManager);
        }
        if (!ListenerUtil.mutListener.listen(62058)) {
            this.listView.setItemAnimator(new DefaultItemAnimator());
        }
        if (!ListenerUtil.mutListener.listen(62063)) {
            this.listView.addOnScrollListener(new RecyclerView.OnScrollListener() {

                @Override
                @UiThread
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    if (!ListenerUtil.mutListener.listen(62059)) {
                        super.onScrolled(recyclerView, dx, dy);
                    }
                    if (!ListenerUtil.mutListener.listen(62062)) {
                        if (linearLayoutManager.findFirstVisibleItemPosition() == 0) {
                            if (!ListenerUtil.mutListener.listen(62061)) {
                                floatingActionButton.extend();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(62060)) {
                                floatingActionButton.shrink();
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(62064)) {
            floatingActionButton = this.findViewById(R.id.floating);
        }
        if (!ListenerUtil.mutListener.listen(62065)) {
            floatingActionButton.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(62067)) {
            floatingActionButton.setOnClickListener(new View.OnClickListener() {

                @Override
                @UiThread
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(62066)) {
                        SessionsActivity.this.initiateSession();
                    }
                }
            });
        }
        View emptyView = this.findViewById(R.id.empty_frame);
        if (!ListenerUtil.mutListener.listen(62068)) {
            this.listView.setEmptyView(emptyView);
        }
        if (!ListenerUtil.mutListener.listen(62069)) {
            this.reloadSessionList();
        }
        if (!ListenerUtil.mutListener.listen(62079)) {
            if (savedInstanceState == null) {
                final boolean welcomeScreenShown = sharedPreferences.getBoolean(getString(R.string.preferences__web_client_welcome_shown), false);
                final boolean sessionsAvailable = (ListenerUtil.mutListener.listen(62074) ? (this.sessionService.getAllSessionModels().size() >= 0) : (ListenerUtil.mutListener.listen(62073) ? (this.sessionService.getAllSessionModels().size() <= 0) : (ListenerUtil.mutListener.listen(62072) ? (this.sessionService.getAllSessionModels().size() < 0) : (ListenerUtil.mutListener.listen(62071) ? (this.sessionService.getAllSessionModels().size() != 0) : (ListenerUtil.mutListener.listen(62070) ? (this.sessionService.getAllSessionModels().size() == 0) : (this.sessionService.getAllSessionModels().size() > 0))))));
                if (!ListenerUtil.mutListener.listen(62078)) {
                    if ((ListenerUtil.mutListener.listen(62075) ? (!welcomeScreenShown || !sessionsAvailable) : (!welcomeScreenShown && !sessionsAvailable))) {
                        if (!ListenerUtil.mutListener.listen(62077)) {
                            // Show wizard
                            this.startActivityForResult(new Intent(this, SessionsIntroActivity.class), REQUEST_ID_INTRO_WIZARD);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(62076)) {
                            this.startBatteryOptimizationFlow();
                        }
                    }
                }
            }
        }
    }

    /**
     *  Make sure that battery optimizations are disabled for Threema.
     */
    private void startBatteryOptimizationFlow() {
        // start battery optimization flow. activity will return RESULT_OK is app is already whitelisted
        Intent intent = new Intent(this, DisableBatteryOptimizationsActivity.class);
        if (!ListenerUtil.mutListener.listen(62080)) {
            intent.putExtra(DisableBatteryOptimizationsActivity.EXTRA_NAME, getString(R.string.webclient));
        }
        if (!ListenerUtil.mutListener.listen(62081)) {
            intent.putExtra(DisableBatteryOptimizationsActivity.EXTRA_CONFIRM, true);
        }
        if (!ListenerUtil.mutListener.listen(62082)) {
            this.startActivityForResult(intent, REQUEST_ID_DISABLE_BATTERY_OPTIMIZATIONS);
        }
    }

    /**
     *  Called after confirm battery confirmation.
     */
    private void activityInitialized() {
        if (!ListenerUtil.mutListener.listen(62090)) {
            if (!this.activityInitialized) {
                if (!ListenerUtil.mutListener.listen(62083)) {
                    this.activityInitialized = true;
                }
                // check for a payload
                byte[] intentPayload = IntentDataUtil.getPayload(this.getIntent());
                if (!ListenerUtil.mutListener.listen(62089)) {
                    if (intentPayload != null) {
                        if (!ListenerUtil.mutListener.listen(62088)) {
                            // Ask first
                            if (RuntimeUtil.isInTest()) {
                                if (!ListenerUtil.mutListener.listen(62087)) {
                                    // Add directly
                                    this.processPayload(intentPayload);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(62084)) {
                                    logger.info("Requesting to start Threema Web session from external scan");
                                }
                                GenericAlertDialog dialogFragment = GenericAlertDialog.newInstance(R.string.webclient_session_start, R.string.webclient_really_start_webclient_by_payload_body, R.string.yes, R.string.no);
                                if (!ListenerUtil.mutListener.listen(62085)) {
                                    dialogFragment.setData(intentPayload);
                                }
                                if (!ListenerUtil.mutListener.listen(62086)) {
                                    dialogFragment.show(getSupportFragmentManager(), DIALOG_TAG_REALLY_START_SESSION_BY_PAYLOAD);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void processPayload(byte[] payload) {
        try {
            final QRCodeParserImpl qrCodeParser = new QRCodeParserImpl();
            final QRCodeParser.Result qrResult = qrCodeParser.parse(payload);
            if (!ListenerUtil.mutListener.listen(62092)) {
                this.startByQrResult(qrResult);
            }
        } catch (QRCodeParser.InvalidQrCodeException invalidQRCode) {
            if (!ListenerUtil.mutListener.listen(62091)) {
                // ignore and log
                logger.error("Invalid QR code", invalidQRCode);
            }
        }
    }

    public int getLayoutResource() {
        return R.layout.activity_sessions;
    }

    private void updateView() {
        if (!ListenerUtil.mutListener.listen(62095)) {
            if (this.enableSwitch != null) {
                if (!ListenerUtil.mutListener.listen(62094)) {
                    if (this.enableSwitch.isChecked() != this.sessionService.isEnabled()) {
                        if (!ListenerUtil.mutListener.listen(62093)) {
                            this.enableSwitch.setCheckedSilent(this.sessionService.isEnabled());
                        }
                    }
                }
            }
        }
    }

    /**
     *  Create or refresh list adapter with sessions.
     */
    private void reloadSessionList() {
        if (!ListenerUtil.mutListener.listen(62100)) {
            if (this.listAdapter == null) {
                if (!ListenerUtil.mutListener.listen(62096)) {
                    this.listAdapter = new SessionListAdapter(this, this.sessionService, this.preferenceService);
                }
                if (!ListenerUtil.mutListener.listen(62098)) {
                    this.listAdapter.setOnClickItemListener(new SessionListAdapter.OnClickItemListener() {

                        @Override
                        @UiThread
                        public void onClick(WebClientSessionModel model, int position) {
                            if (!ListenerUtil.mutListener.listen(62097)) {
                                SessionsActivity.this.onSessionItemClicked(model);
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(62099)) {
                    this.listView.setAdapter(this.listAdapter);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(62101)) {
            this.listAdapter.setData(this.sessionService.getAllSessionModels());
        }
    }

    /**
     *  Session list item was clicked.
     */
    private void onSessionItemClicked(WebClientSessionModel model) {
        if (!ListenerUtil.mutListener.listen(62111)) {
            if (model != null) {
                ArrayList<String> items = new ArrayList<>();
                ArrayList<Integer> values = new ArrayList<>();
                if (!ListenerUtil.mutListener.listen(62102)) {
                    items.add(this.getString(R.string.webclient_session_rename));
                }
                if (!ListenerUtil.mutListener.listen(62103)) {
                    values.add(MENU_POS_RENAME);
                }
                if (!ListenerUtil.mutListener.listen(62106)) {
                    if (model.getState() != WebClientSessionModel.State.INITIALIZING) {
                        if (!ListenerUtil.mutListener.listen(62104)) {
                            items.add(this.getString(!this.sessionService.isRunning(model) ? R.string.webclient_session_start : R.string.webclient_session_stop));
                        }
                        if (!ListenerUtil.mutListener.listen(62105)) {
                            values.add(MENU_POS_START_STOP);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(62107)) {
                    items.add(this.getString(R.string.webclient_session_remove));
                }
                if (!ListenerUtil.mutListener.listen(62108)) {
                    values.add(MENU_POS_REMOVE);
                }
                SelectorDialog selectorDialog = SelectorDialog.newInstance(null, items, values, null);
                if (!ListenerUtil.mutListener.listen(62109)) {
                    selectorDialog.setData(model);
                }
                if (!ListenerUtil.mutListener.listen(62110)) {
                    selectorDialog.show(getSupportFragmentManager(), DIALOG_TAG_ITEM_MENU);
                }
            }
        }
    }

    /**
     *  Session list item context menu entry was clicked.
     */
    @Override
    public void onClick(String tag, int which, Object data) {
        if (!ListenerUtil.mutListener.listen(62117)) {
            if (DIALOG_TAG_ITEM_MENU.equals(tag)) {
                if (!ListenerUtil.mutListener.listen(62116)) {
                    if (data instanceof WebClientSessionModel) {
                        WebClientSessionModel model = (WebClientSessionModel) data;
                        if (!ListenerUtil.mutListener.listen(62115)) {
                            switch(which) {
                                case MENU_POS_START_STOP:
                                    if (!ListenerUtil.mutListener.listen(62112)) {
                                        this.startStopSession(model);
                                    }
                                    break;
                                case MENU_POS_RENAME:
                                    if (!ListenerUtil.mutListener.listen(62113)) {
                                        this.renameSession(model);
                                    }
                                    break;
                                case MENU_POS_REMOVE:
                                    if (!ListenerUtil.mutListener.listen(62114)) {
                                        this.removeSession(model);
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
        }
    }

    private void removeSession(@NonNull final WebClientSessionModel sessionModel) {
        GenericAlertDialog dialog = GenericAlertDialog.newInstance(R.string.webclient_session_remove, getString(R.string.webclient_sessions_really_delete), R.string.ok, R.string.cancel);
        if (!ListenerUtil.mutListener.listen(62118)) {
            dialog.setData(sessionModel);
        }
        if (!ListenerUtil.mutListener.listen(62119)) {
            dialog.show(getSupportFragmentManager(), DIALOG_TAG_REALLY_DELETE_SESSION);
        }
    }

    private void startStopSession(@NonNull final WebClientSessionModel sessionModel) {
        if (!ListenerUtil.mutListener.listen(62131)) {
            if (!this.sessionService.isRunning(sessionModel)) {
                final SessionInstanceService service = this.sessionService.getInstanceService(sessionModel, true);
                if (!ListenerUtil.mutListener.listen(62125)) {
                    if (service == null) {
                        if (!ListenerUtil.mutListener.listen(62124)) {
                            logger.error("cannot start service, cannot instantiate session instance service");
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(62127)) {
                    // enable webclient session if disabled
                    if (!this.sessionService.isEnabled()) {
                        if (!ListenerUtil.mutListener.listen(62126)) {
                            this.sessionService.setEnabled(true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(62130)) {
                    this.webClientServiceManager.getHandler().post(new Runnable() {

                        @Override
                        @WorkerThread
                        public void run() {
                            try {
                                if (!ListenerUtil.mutListener.listen(62129)) {
                                    service.resume(null);
                                }
                            } catch (CryptoException error) {
                                if (!ListenerUtil.mutListener.listen(62128)) {
                                    logger.error("Could not resume session", error);
                                }
                            }
                        }
                    });
                }
            } else {
                final SessionInstanceService service = this.sessionService.getInstanceService(sessionModel, false);
                if (!ListenerUtil.mutListener.listen(62121)) {
                    if (service == null) {
                        if (!ListenerUtil.mutListener.listen(62120)) {
                            logger.error("cannot stop service, no running service");
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(62123)) {
                    this.webClientServiceManager.getHandler().post(new Runnable() {

                        @Override
                        @WorkerThread
                        public void run() {
                            if (!ListenerUtil.mutListener.listen(62122)) {
                                service.stop(DisconnectContext.byUs(DisconnectContext.REASON_SESSION_STOPPED));
                            }
                        }
                    });
                }
            }
        }
    }

    private void renameSession(@NonNull final WebClientSessionModel sessionModel) {
        if (!ListenerUtil.mutListener.listen(62132)) {
            TextEntryDialog.newInstance(R.string.webclient_session_rename, R.string.webclient_session_label, R.string.ok, 0, R.string.cancel, sessionModel.getLabel(), 0, TextEntryDialog.INPUT_FILTER_TYPE_NONE, 64).show(getSupportFragmentManager(), DIALOG_TAG_EDIT_LABEL + sessionModel.getId());
        }
    }

    @Override
    public void onCancel(String tag) {
    }

    @Override
    public void onNo(String tag) {
    }

    @Override
    public void onYes(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(62138)) {
            switch(tag) {
                case DIALOG_TAG_REALLY_DELETE_SESSION:
                    if (!ListenerUtil.mutListener.listen(62134)) {
                        if (data instanceof WebClientSessionModel) {
                            if (!ListenerUtil.mutListener.listen(62133)) {
                                this.sessionService.stop((WebClientSessionModel) data, DisconnectContext.byUs(DisconnectContext.REASON_SESSION_DELETED));
                            }
                        }
                    }
                    break;
                case DIALOG_TAG_REALLY_DELETE_ALL_SESSIONS:
                    if (!ListenerUtil.mutListener.listen(62135)) {
                        this.sessionService.stopAll(DisconnectContext.byUs(DisconnectContext.REASON_SESSION_DELETED));
                    }
                    break;
                case DIALOG_TAG_REALLY_START_SESSION_BY_PAYLOAD:
                    if (!ListenerUtil.mutListener.listen(62137)) {
                        if (data != null) {
                            if (!ListenerUtil.mutListener.listen(62136)) {
                                this.processPayload((byte[]) data);
                            }
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public void onNo(String tag, Object data) {
    }

    /**
     *  Handle renaming of session labels.
     */
    @Override
    public void onYes(String tag, String text) {
        if (!ListenerUtil.mutListener.listen(62150)) {
            if (tag.startsWith(DIALOG_TAG_EDIT_LABEL)) {
                // The model id is appended to the tag. To get it, strip the prefix.
                int modelId = Integer.parseInt(tag.substring(DIALOG_TAG_EDIT_LABEL.length()));
                if (!ListenerUtil.mutListener.listen(62149)) {
                    {
                        long _loopCounter746 = 0;
                        // simply search list for this id
                        for (int pos = 0; (ListenerUtil.mutListener.listen(62148) ? (pos >= listAdapter.getItemCount()) : (ListenerUtil.mutListener.listen(62147) ? (pos <= listAdapter.getItemCount()) : (ListenerUtil.mutListener.listen(62146) ? (pos > listAdapter.getItemCount()) : (ListenerUtil.mutListener.listen(62145) ? (pos != listAdapter.getItemCount()) : (ListenerUtil.mutListener.listen(62144) ? (pos == listAdapter.getItemCount()) : (pos < listAdapter.getItemCount())))))); pos++) {
                            ListenerUtil.loopListener.listen("_loopCounter746", ++_loopCounter746);
                            final WebClientSessionModel model = this.listAdapter.getEntity(pos);
                            if (!ListenerUtil.mutListener.listen(62143)) {
                                if (model.getId() == modelId) {
                                    if (!ListenerUtil.mutListener.listen(62139)) {
                                        model.setLabel(text);
                                    }
                                    if (!ListenerUtil.mutListener.listen(62142)) {
                                        // UGH!
                                        if (this.databaseService.getWebClientSessionModelFactory().createOrUpdate(model)) {
                                            if (!ListenerUtil.mutListener.listen(62141)) {
                                                WebClientListenerManager.sessionListener.handle(new ListenerManager.HandleListener<WebClientSessionListener>() {

                                                    @Override
                                                    @UiThread
                                                    public void handle(WebClientSessionListener listener) {
                                                        if (!ListenerUtil.mutListener.listen(62140)) {
                                                            listener.onModified(model);
                                                        }
                                                    }
                                                });
                                            }
                                        }
                                    }
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onNeutral(String tag) {
    }

    /**
     *  Initiate a session by starting the QR code scanner.
     */
    private void initiateSession() {
        if (!ListenerUtil.mutListener.listen(62153)) {
            // start the qr scanner
            if (ConfigUtils.requestCameraPermissions(this, null, PERMISSION_REQUEST_CAMERA)) {
                if (!ListenerUtil.mutListener.listen(62152)) {
                    this.scanQR();
                }
            } else if (sessionService.getAllSessionModels().size() == 0) {
                if (!ListenerUtil.mutListener.listen(62151)) {
                    enableSwitch.setCheckedSilent(false);
                }
            }
        }
    }

    private void scanQR() {
        if (!ListenerUtil.mutListener.listen(62154)) {
            logger.info("Initiate QR scan");
        }
        if (!ListenerUtil.mutListener.listen(62155)) {
            QRScannerUtil.getInstance().initiateScan(this, false, getString(R.string.webclient_qr_scan_message));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (!ListenerUtil.mutListener.listen(62156)) {
            super.onActivityResult(requestCode, resultCode, intent);
        }
        if (!ListenerUtil.mutListener.listen(62157)) {
            ConfigUtils.setLocaleOverride(this, this.serviceManager.getPreferenceService());
        }
        if (!ListenerUtil.mutListener.listen(62170)) {
            switch(requestCode) {
                case REQUEST_ID_INTRO_WIZARD:
                    if (!ListenerUtil.mutListener.listen(62160)) {
                        if (resultCode == RESULT_OK) {
                            if (!ListenerUtil.mutListener.listen(62159)) {
                                this.startBatteryOptimizationFlow();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(62158)) {
                                this.finish();
                            }
                        }
                    }
                    break;
                case REQUEST_ID_DISABLE_BATTERY_OPTIMIZATIONS:
                    if (!ListenerUtil.mutListener.listen(62163)) {
                        if (resultCode != RESULT_OK) {
                            if (!ListenerUtil.mutListener.listen(62162)) {
                                this.finish();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(62161)) {
                                this.activityInitialized();
                            }
                        }
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(62168)) {
                        if (resultCode == RESULT_OK) {
                            // return from QR scan
                            String payload = QRScannerUtil.getInstance().parseActivityResult(this, requestCode, resultCode, intent);
                            if (!ListenerUtil.mutListener.listen(62167)) {
                                if (!TestUtil.empty(payload)) {
                                    final QRCodeParser qrCodeParser = new QRCodeParserImpl();
                                    try {
                                        final byte[] pl = Base64.decode(payload);
                                        final QRCodeParser.Result qrResult = qrCodeParser.parse(pl);
                                        if (!ListenerUtil.mutListener.listen(62166)) {
                                            this.startByQrResult(qrResult);
                                        }
                                    } catch (QRCodeParser.InvalidQrCodeException | IOException e) {
                                        if (!ListenerUtil.mutListener.listen(62164)) {
                                            logger.error("Exception", e);
                                        }
                                        if (!ListenerUtil.mutListener.listen(62165)) {
                                            // show a generic error message
                                            GenericAlertDialog.newInstance(R.string.webclient_init_session, R.string.webclient_invalid_qr_code, R.string.ok, 0).show(getSupportFragmentManager(), "foo");
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(62169)) {
                        this.updateView();
                    }
            }
        }
    }

    private void startByQrResult(@NonNull final QRCodeParser.Result qrCodeResult) {
        if (!ListenerUtil.mutListener.listen(62178)) {
            // Validate protocol version
            if (qrCodeResult.versionNumber != Protocol.PROTOCOL_VERSION) {
                if (!ListenerUtil.mutListener.listen(62171)) {
                    // Wrong protocol version!
                    logger.error("Scanned QR code with protocol version {}, but we only support {}", qrCodeResult.versionNumber, Protocol.PROTOCOL_VERSION);
                }
                // Determine appropriate error message to show
                int errorMessage;
                if ((ListenerUtil.mutListener.listen(62176) ? (qrCodeResult.versionNumber >= Protocol.PROTOCOL_VERSION) : (ListenerUtil.mutListener.listen(62175) ? (qrCodeResult.versionNumber <= Protocol.PROTOCOL_VERSION) : (ListenerUtil.mutListener.listen(62174) ? (qrCodeResult.versionNumber < Protocol.PROTOCOL_VERSION) : (ListenerUtil.mutListener.listen(62173) ? (qrCodeResult.versionNumber != Protocol.PROTOCOL_VERSION) : (ListenerUtil.mutListener.listen(62172) ? (qrCodeResult.versionNumber == Protocol.PROTOCOL_VERSION) : (qrCodeResult.versionNumber > Protocol.PROTOCOL_VERSION))))))) {
                    errorMessage = R.string.webclient_protocol_version_to_old;
                } else {
                    if (qrCodeResult.isSelfHosted) {
                        errorMessage = R.string.webclient_protocol_version_too_new_selfhosted;
                    } else {
                        errorMessage = R.string.webclient_protocol_version_too_new_threema;
                    }
                }
                if (!ListenerUtil.mutListener.listen(62177)) {
                    // Show error message
                    GenericAlertDialog.newInstance(R.string.webclient_protocol_error, errorMessage, R.string.close, 0).show(getSupportFragmentManager(), "error");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(62181)) {
            // Check internet connection
            if (!ThreemaApplication.getServiceManager().getDeviceService().isOnline()) {
                if (!ListenerUtil.mutListener.listen(62179)) {
                    logger.error("No internet connection");
                }
                if (!ListenerUtil.mutListener.listen(62180)) {
                    GenericAlertDialog.newInstance(R.string.internet_connection_required, R.string.connection_error, R.string.close, 0).show(getSupportFragmentManager(), "error");
                }
                return;
            }
        }
        // Ensure that session does not already exist
        final WebClientSessionModel sessionModel = this.databaseService.getWebClientSessionModelFactory().getByKey(qrCodeResult.key);
        if (!ListenerUtil.mutListener.listen(62184)) {
            if (sessionModel != null) {
                if (!ListenerUtil.mutListener.listen(62182)) {
                    // We scanned the QR code of a session that already exists! Something's wrong.
                    logger.error("Session already exists");
                }
                if (!ListenerUtil.mutListener.listen(62183)) {
                    GenericAlertDialog.newInstance(R.string.webclient_protocol_error, R.string.webclient_session_already_exists, R.string.close, 0).show(getSupportFragmentManager(), "error");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(62185)) {
            // Success! Start new session.
            logger.debug(LOG_TAG, "Start with QR result: " + qrCodeResult);
        }
        try {
            if (!ListenerUtil.mutListener.listen(62187)) {
                this.vibrate();
            }
            if (!ListenerUtil.mutListener.listen(62188)) {
                this.start(qrCodeResult);
            }
        } catch (IllegalArgumentException e) {
            if (!ListenerUtil.mutListener.listen(62186)) {
                LogUtil.exception(e, this);
            }
        }
        if (!ListenerUtil.mutListener.listen(62189)) {
            this.updateView();
        }
    }

    /**
     *  Vibrate quickly to indicate that the session has been started successfully.
     */
    private void vibrate() {
        final Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        final AudioManager audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        if (!ListenerUtil.mutListener.listen(62193)) {
            if ((ListenerUtil.mutListener.listen(62190) ? (vibrator != null || audioManager != null) : (vibrator != null && audioManager != null))) {
                if (!ListenerUtil.mutListener.listen(62192)) {
                    switch(audioManager.getRingerMode()) {
                        case AudioManager.RINGER_MODE_VIBRATE:
                        case AudioManager.RINGER_MODE_NORMAL:
                            if (!ListenerUtil.mutListener.listen(62191)) {
                                vibrator.vibrate(100);
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    /**
     *  Start the webclient service.
     *  Connect asynchronously.
     */
    private void start(@NonNull final QRCodeParser.Result qrCodeResult) {
        if (!ListenerUtil.mutListener.listen(62194)) {
            logger.info("Starting Threema Web session");
        }
        if (!ListenerUtil.mutListener.listen(62200)) {
            // MDM constraints
            if (ConfigUtils.isWorkRestricted()) {
                if (!ListenerUtil.mutListener.listen(62197)) {
                    // Threema Web may be disabled
                    if (AppRestrictionUtil.isWebDisabled(this)) {
                        final String msg = getString(R.string.webclient_cannot_restore) + ": " + getString(R.string.webclient_disabled);
                        if (!ListenerUtil.mutListener.listen(62195)) {
                            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                        }
                        if (!ListenerUtil.mutListener.listen(62196)) {
                            this.finish();
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(62199)) {
                    // Signaling hosts may be constrained
                    if (!AppRestrictionUtil.isWebHostAllowed(this, qrCodeResult.saltyRtcHost)) {
                        final SimpleStringAlertDialog dialog = SimpleStringAlertDialog.newInstance(R.string.webclient_cannot_start, R.string.webclient_constrained_by_mdm);
                        if (!ListenerUtil.mutListener.listen(62198)) {
                            dialog.show(getSupportFragmentManager(), DIALOG_TAG_MDM_CONSTRAINTS);
                        }
                        return;
                    }
                }
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(62202)) {
                // Make sure that all listeners are initialized
                this.init();
            }
            if (!ListenerUtil.mutListener.listen(62203)) {
                // Create new session
                this.sessionService.create(qrCodeResult.key, qrCodeResult.authToken, qrCodeResult.saltyRtcHost, qrCodeResult.saltyRtcPort, qrCodeResult.serverKey, qrCodeResult.isPermanent, qrCodeResult.isSelfHosted, null);
            }
        } catch (HandshakeException | InvalidKeyException | ThreemaException x) {
            if (!ListenerUtil.mutListener.listen(62201)) {
                LogUtil.exception(x, this);
            }
        }
    }

    /**
     *  Make sure that all listeners are initialized.
     */
    private void init() {
        if (!ListenerUtil.mutListener.listen(62207)) {
            if (!this.initialized) {
                if (!ListenerUtil.mutListener.listen(62204)) {
                    WebClientListenerManager.sessionListener.add(this.webClientSessionListener);
                }
                if (!ListenerUtil.mutListener.listen(62205)) {
                    WebClientListenerManager.serviceListener.add(this.webClientServiceListener);
                }
                if (!ListenerUtil.mutListener.listen(62206)) {
                    this.initialized = true;
                }
            }
        }
    }

    /**
     *  Make sure that all listeners are removed.
     */
    private void deinit() {
        if (!ListenerUtil.mutListener.listen(62211)) {
            if (this.initialized) {
                if (!ListenerUtil.mutListener.listen(62208)) {
                    WebClientListenerManager.sessionListener.remove(this.webClientSessionListener);
                }
                if (!ListenerUtil.mutListener.listen(62209)) {
                    WebClientListenerManager.serviceListener.remove(this.webClientServiceListener);
                }
                if (!ListenerUtil.mutListener.listen(62210)) {
                    this.initialized = false;
                }
            }
        }
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(62212)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(62213)) {
            this.init();
        }
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(62214)) {
            this.deinit();
        }
        if (!ListenerUtil.mutListener.listen(62215)) {
            super.onStop();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(62216)) {
            super.onCreateOptionsMenu(menu);
        }
        if (!ListenerUtil.mutListener.listen(62217)) {
            this.getMenuInflater().inflate(R.menu.activity_webclient_sessions, menu);
        }
        if (!ListenerUtil.mutListener.listen(62218)) {
            ConfigUtils.addIconsToOverflowMenu(this, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(62222)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(62219)) {
                        this.finish();
                    }
                    break;
                case R.id.menu_help:
                    if (!ListenerUtil.mutListener.listen(62220)) {
                        this.startActivity(new Intent(this, SessionsIntroActivity.class));
                    }
                    break;
                case R.id.menu_clear_all:
                    GenericAlertDialog dialog = GenericAlertDialog.newInstance(R.string.webclient_clear_all_sessions, getString(R.string.webclient_clear_all_sessions_confirm), R.string.ok, R.string.cancel);
                    if (!ListenerUtil.mutListener.listen(62221)) {
                        dialog.show(getSupportFragmentManager(), DIALOG_TAG_REALLY_DELETE_ALL_SESSIONS);
                    }
                    break;
                default:
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     *  Delete all non-persistent webclient sessions that have been inactive for 24h.
     */
    private void cleanupWebclientSessions() {
        final List<WebClientSessionModel> models = this.databaseService.getWebClientSessionModelFactory().getAll();
        final long now = (ListenerUtil.mutListener.listen(62226) ? (System.currentTimeMillis() % 1000) : (ListenerUtil.mutListener.listen(62225) ? (System.currentTimeMillis() * 1000) : (ListenerUtil.mutListener.listen(62224) ? (System.currentTimeMillis() - 1000) : (ListenerUtil.mutListener.listen(62223) ? (System.currentTimeMillis() + 1000) : (System.currentTimeMillis() / 1000)))));
        if (!ListenerUtil.mutListener.listen(62266)) {
            {
                long _loopCounter747 = 0;
                for (WebClientSessionModel model : models) {
                    ListenerUtil.loopListener.listen("_loopCounter747", ++_loopCounter747);
                    if (!ListenerUtil.mutListener.listen(62227)) {
                        // Ignore persistent sessions
                        if (model.isPersistent()) {
                            continue;
                        }
                    }
                    boolean remove = true;
                    // 24h
                    final long secondsAgoThreshold = (ListenerUtil.mutListener.listen(62231) ? (3600 % 24) : (ListenerUtil.mutListener.listen(62230) ? (3600 / 24) : (ListenerUtil.mutListener.listen(62229) ? (3600 - 24) : (ListenerUtil.mutListener.listen(62228) ? (3600 + 24) : (3600 * 24)))));
                    if (!ListenerUtil.mutListener.listen(62247)) {
                        // Ignore sessions that have been created in the last 24h.
                        if (model.getCreated() != null) {
                            final long secondsAgo = (ListenerUtil.mutListener.listen(62239) ? (now % ((ListenerUtil.mutListener.listen(62235) ? (model.getCreated().getTime() % 1000) : (ListenerUtil.mutListener.listen(62234) ? (model.getCreated().getTime() * 1000) : (ListenerUtil.mutListener.listen(62233) ? (model.getCreated().getTime() - 1000) : (ListenerUtil.mutListener.listen(62232) ? (model.getCreated().getTime() + 1000) : (model.getCreated().getTime() / 1000))))))) : (ListenerUtil.mutListener.listen(62238) ? (now / ((ListenerUtil.mutListener.listen(62235) ? (model.getCreated().getTime() % 1000) : (ListenerUtil.mutListener.listen(62234) ? (model.getCreated().getTime() * 1000) : (ListenerUtil.mutListener.listen(62233) ? (model.getCreated().getTime() - 1000) : (ListenerUtil.mutListener.listen(62232) ? (model.getCreated().getTime() + 1000) : (model.getCreated().getTime() / 1000))))))) : (ListenerUtil.mutListener.listen(62237) ? (now * ((ListenerUtil.mutListener.listen(62235) ? (model.getCreated().getTime() % 1000) : (ListenerUtil.mutListener.listen(62234) ? (model.getCreated().getTime() * 1000) : (ListenerUtil.mutListener.listen(62233) ? (model.getCreated().getTime() - 1000) : (ListenerUtil.mutListener.listen(62232) ? (model.getCreated().getTime() + 1000) : (model.getCreated().getTime() / 1000))))))) : (ListenerUtil.mutListener.listen(62236) ? (now + ((ListenerUtil.mutListener.listen(62235) ? (model.getCreated().getTime() % 1000) : (ListenerUtil.mutListener.listen(62234) ? (model.getCreated().getTime() * 1000) : (ListenerUtil.mutListener.listen(62233) ? (model.getCreated().getTime() - 1000) : (ListenerUtil.mutListener.listen(62232) ? (model.getCreated().getTime() + 1000) : (model.getCreated().getTime() / 1000))))))) : (now - ((ListenerUtil.mutListener.listen(62235) ? (model.getCreated().getTime() % 1000) : (ListenerUtil.mutListener.listen(62234) ? (model.getCreated().getTime() * 1000) : (ListenerUtil.mutListener.listen(62233) ? (model.getCreated().getTime() - 1000) : (ListenerUtil.mutListener.listen(62232) ? (model.getCreated().getTime() + 1000) : (model.getCreated().getTime() / 1000)))))))))));
                            if (!ListenerUtil.mutListener.listen(62246)) {
                                if ((ListenerUtil.mutListener.listen(62244) ? (secondsAgo >= secondsAgoThreshold) : (ListenerUtil.mutListener.listen(62243) ? (secondsAgo <= secondsAgoThreshold) : (ListenerUtil.mutListener.listen(62242) ? (secondsAgo > secondsAgoThreshold) : (ListenerUtil.mutListener.listen(62241) ? (secondsAgo != secondsAgoThreshold) : (ListenerUtil.mutListener.listen(62240) ? (secondsAgo == secondsAgoThreshold) : (secondsAgo < secondsAgoThreshold))))))) {
                                    if (!ListenerUtil.mutListener.listen(62245)) {
                                        remove = false;
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(62263)) {
                        // Ignore sessions that have been active in the last 24h.
                        if (model.getLastConnection() != null) {
                            final long secondsAgo = (ListenerUtil.mutListener.listen(62255) ? (now % ((ListenerUtil.mutListener.listen(62251) ? (model.getLastConnection().getTime() % 1000) : (ListenerUtil.mutListener.listen(62250) ? (model.getLastConnection().getTime() * 1000) : (ListenerUtil.mutListener.listen(62249) ? (model.getLastConnection().getTime() - 1000) : (ListenerUtil.mutListener.listen(62248) ? (model.getLastConnection().getTime() + 1000) : (model.getLastConnection().getTime() / 1000))))))) : (ListenerUtil.mutListener.listen(62254) ? (now / ((ListenerUtil.mutListener.listen(62251) ? (model.getLastConnection().getTime() % 1000) : (ListenerUtil.mutListener.listen(62250) ? (model.getLastConnection().getTime() * 1000) : (ListenerUtil.mutListener.listen(62249) ? (model.getLastConnection().getTime() - 1000) : (ListenerUtil.mutListener.listen(62248) ? (model.getLastConnection().getTime() + 1000) : (model.getLastConnection().getTime() / 1000))))))) : (ListenerUtil.mutListener.listen(62253) ? (now * ((ListenerUtil.mutListener.listen(62251) ? (model.getLastConnection().getTime() % 1000) : (ListenerUtil.mutListener.listen(62250) ? (model.getLastConnection().getTime() * 1000) : (ListenerUtil.mutListener.listen(62249) ? (model.getLastConnection().getTime() - 1000) : (ListenerUtil.mutListener.listen(62248) ? (model.getLastConnection().getTime() + 1000) : (model.getLastConnection().getTime() / 1000))))))) : (ListenerUtil.mutListener.listen(62252) ? (now + ((ListenerUtil.mutListener.listen(62251) ? (model.getLastConnection().getTime() % 1000) : (ListenerUtil.mutListener.listen(62250) ? (model.getLastConnection().getTime() * 1000) : (ListenerUtil.mutListener.listen(62249) ? (model.getLastConnection().getTime() - 1000) : (ListenerUtil.mutListener.listen(62248) ? (model.getLastConnection().getTime() + 1000) : (model.getLastConnection().getTime() / 1000))))))) : (now - ((ListenerUtil.mutListener.listen(62251) ? (model.getLastConnection().getTime() % 1000) : (ListenerUtil.mutListener.listen(62250) ? (model.getLastConnection().getTime() * 1000) : (ListenerUtil.mutListener.listen(62249) ? (model.getLastConnection().getTime() - 1000) : (ListenerUtil.mutListener.listen(62248) ? (model.getLastConnection().getTime() + 1000) : (model.getLastConnection().getTime() / 1000)))))))))));
                            if (!ListenerUtil.mutListener.listen(62262)) {
                                if ((ListenerUtil.mutListener.listen(62260) ? (secondsAgo >= secondsAgoThreshold) : (ListenerUtil.mutListener.listen(62259) ? (secondsAgo <= secondsAgoThreshold) : (ListenerUtil.mutListener.listen(62258) ? (secondsAgo > secondsAgoThreshold) : (ListenerUtil.mutListener.listen(62257) ? (secondsAgo != secondsAgoThreshold) : (ListenerUtil.mutListener.listen(62256) ? (secondsAgo == secondsAgoThreshold) : (secondsAgo < secondsAgoThreshold))))))) {
                                    if (!ListenerUtil.mutListener.listen(62261)) {
                                        remove = false;
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(62265)) {
                        if (remove) {
                            if (!ListenerUtil.mutListener.listen(62264)) {
                                this.databaseService.getWebClientSessionModelFactory().delete(model);
                            }
                        }
                    }
                }
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(62283)) {
            if ((ListenerUtil.mutListener.listen(62271) ? (requestCode >= PERMISSION_REQUEST_CAMERA) : (ListenerUtil.mutListener.listen(62270) ? (requestCode <= PERMISSION_REQUEST_CAMERA) : (ListenerUtil.mutListener.listen(62269) ? (requestCode > PERMISSION_REQUEST_CAMERA) : (ListenerUtil.mutListener.listen(62268) ? (requestCode < PERMISSION_REQUEST_CAMERA) : (ListenerUtil.mutListener.listen(62267) ? (requestCode != PERMISSION_REQUEST_CAMERA) : (requestCode == PERMISSION_REQUEST_CAMERA))))))) {
                if (!ListenerUtil.mutListener.listen(62282)) {
                    if ((ListenerUtil.mutListener.listen(62277) ? ((ListenerUtil.mutListener.listen(62276) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(62275) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(62274) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(62273) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(62272) ? (grantResults.length == 0) : (grantResults.length > 0)))))) || grantResults[0] == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(62276) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(62275) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(62274) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(62273) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(62272) ? (grantResults.length == 0) : (grantResults.length > 0)))))) && grantResults[0] == PackageManager.PERMISSION_GRANTED))) {
                        if (!ListenerUtil.mutListener.listen(62281)) {
                            this.scanQR();
                        }
                    } else if (!this.shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                        if (!ListenerUtil.mutListener.listen(62278)) {
                            ConfigUtils.showPermissionRationale(this, findViewById(R.id.parent_layout), R.string.permission_camera_qr_required);
                        }
                        if (!ListenerUtil.mutListener.listen(62280)) {
                            if (this.sessionService.getAllSessionModels().size() == 0) {
                                if (!ListenerUtil.mutListener.listen(62279)) {
                                    this.enableSwitch.setCheckedSilent(false);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
