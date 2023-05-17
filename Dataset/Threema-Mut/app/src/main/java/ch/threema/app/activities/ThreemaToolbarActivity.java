/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
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
package ch.threema.app.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.wizard.WizardIntroActivity;
import ch.threema.app.emojis.EmojiPicker;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.LockAppService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.ConnectionIndicatorUtil;
import ch.threema.app.utils.EditTextUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.client.ConnectionState;
import ch.threema.client.ConnectionStateListener;
import ch.threema.client.ThreemaConnection;
import ch.threema.localcrypto.MasterKey;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Helper class for activities that use the new toolbar
 */
public abstract class ThreemaToolbarActivity extends ThreemaActivity implements ConnectionStateListener {

    private static final Logger logger = LoggerFactory.getLogger(ThreemaToolbarActivity.class);

    private Toolbar toolbar;

    private View connectionIndicator;

    protected ServiceManager serviceManager;

    protected LockAppService lockAppService;

    protected PreferenceService preferenceService;

    protected ThreemaConnection threemaConnection;

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(7026)) {
            if (threemaConnection != null) {
                if (!ListenerUtil.mutListener.listen(7024)) {
                    threemaConnection.addConnectionStateListener(this);
                }
                ConnectionState connectionState = threemaConnection.getConnectionState();
                if (!ListenerUtil.mutListener.listen(7025)) {
                    ConnectionIndicatorUtil.getInstance().updateConnectionIndicator(connectionIndicator, connectionState);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7027)) {
            super.onResume();
        }
    }

    @Override
    protected void onPause() {
        if (!ListenerUtil.mutListener.listen(7029)) {
            if (threemaConnection != null) {
                if (!ListenerUtil.mutListener.listen(7028)) {
                    threemaConnection.removeConnectionStateListener(this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7030)) {
            super.onPause();
        }
    }

    @Override
    protected void onApplyThemeResource(Resources.Theme theme, int resid, boolean first) {
        if (!ListenerUtil.mutListener.listen(7031)) {
            super.onApplyThemeResource(theme, resid, first);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (!ListenerUtil.mutListener.listen(7032)) {
            super.onNewIntent(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(7033)) {
            logger.debug("onCreate");
        }
        if (!ListenerUtil.mutListener.listen(7034)) {
            ConfigUtils.configureActivityTheme(this);
        }
        if (!ListenerUtil.mutListener.listen(7035)) {
            resetKeyboard();
        }
        if (!ListenerUtil.mutListener.listen(7036)) {
            super.onCreate(savedInstanceState);
        }
        // check master key
        MasterKey masterKey = ThreemaApplication.getMasterKey();
        if (!ListenerUtil.mutListener.listen(7043)) {
            if ((ListenerUtil.mutListener.listen(7037) ? (masterKey != null || masterKey.isLocked()) : (masterKey != null && masterKey.isLocked()))) {
                if (!ListenerUtil.mutListener.listen(7042)) {
                    startActivityForResult(new Intent(this, UnlockMasterKeyActivity.class), ThreemaActivity.ACTIVITY_ID_UNLOCK_MASTER_KEY);
                }
                return;
            } else {
                if (!ListenerUtil.mutListener.listen(7041)) {
                    if ((ListenerUtil.mutListener.listen(7038) ? (ConfigUtils.isSerialLicensed() || !ConfigUtils.isSerialLicenseValid()) : (ConfigUtils.isSerialLicensed() && !ConfigUtils.isSerialLicenseValid()))) {
                        if (!ListenerUtil.mutListener.listen(7039)) {
                            startActivity(new Intent(this, EnterSerialActivity.class));
                        }
                        if (!ListenerUtil.mutListener.listen(7040)) {
                            finish();
                        }
                        return;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7044)) {
            initServices();
        }
        if (!ListenerUtil.mutListener.listen(7047)) {
            if (!(this instanceof ComposeMessageActivity)) {
                if (!ListenerUtil.mutListener.listen(7046)) {
                    if (!this.initActivity(savedInstanceState)) {
                        if (!ListenerUtil.mutListener.listen(7045)) {
                            finish();
                        }
                    }
                }
            }
        }
    }

    private void initServices() {
        if (!ListenerUtil.mutListener.listen(7054)) {
            if (serviceManager == null) {
                if (!ListenerUtil.mutListener.listen(7048)) {
                    serviceManager = ThreemaApplication.getServiceManager();
                }
                if (!ListenerUtil.mutListener.listen(7051)) {
                    if (serviceManager == null) {
                        if (!ListenerUtil.mutListener.listen(7049)) {
                            // app is probably locked
                            Toast.makeText(this, "Service Manager not available", Toast.LENGTH_LONG).show();
                        }
                        if (!ListenerUtil.mutListener.listen(7050)) {
                            finish();
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(7052)) {
                    lockAppService = serviceManager.getLockAppService();
                }
                if (!ListenerUtil.mutListener.listen(7053)) {
                    preferenceService = serviceManager.getPreferenceService();
                }
            }
        }
    }

    /**
     *  This method sets up the layout, the connection indicator, language override and screenshot blocker. It is called from onCreate() after all the basic initialization has been done.
     *  Override this to do your own initialization, such as instantiating services
     *  @param savedInstanceState the bundle provided to onCreate()
     *  @return true on success, false otherwise
     */
    protected boolean initActivity(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(7055)) {
            logger.debug("initActivity");
        }
        int layoutResource = getLayoutResource();
        if (!ListenerUtil.mutListener.listen(7056)) {
            initServices();
        }
        try {
            if (!ListenerUtil.mutListener.listen(7059)) {
                threemaConnection = serviceManager.getConnection();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(7057)) {
                logger.info("Unable to get Threema connection.");
            }
            if (!ListenerUtil.mutListener.listen(7058)) {
                finish();
            }
        }
        if (!ListenerUtil.mutListener.listen(7062)) {
            if ((ListenerUtil.mutListener.listen(7060) ? (preferenceService != null || preferenceService.getWizardRunning()) : (preferenceService != null && preferenceService.getWizardRunning()))) {
                if (!ListenerUtil.mutListener.listen(7061)) {
                    startActivity(new Intent(this, WizardIntroActivity.class));
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(7063)) {
            // hide contents in app switcher and inhibit screenshots
            ConfigUtils.setScreenshotsAllowed(this, preferenceService, lockAppService);
        }
        if (!ListenerUtil.mutListener.listen(7064)) {
            ConfigUtils.setLocaleOverride(this, preferenceService);
        }
        if (!ListenerUtil.mutListener.listen(7076)) {
            if ((ListenerUtil.mutListener.listen(7069) ? (layoutResource >= 0) : (ListenerUtil.mutListener.listen(7068) ? (layoutResource <= 0) : (ListenerUtil.mutListener.listen(7067) ? (layoutResource > 0) : (ListenerUtil.mutListener.listen(7066) ? (layoutResource < 0) : (ListenerUtil.mutListener.listen(7065) ? (layoutResource == 0) : (layoutResource != 0))))))) {
                if (!ListenerUtil.mutListener.listen(7070)) {
                    logger.debug("setContentView");
                }
                if (!ListenerUtil.mutListener.listen(7071)) {
                    setContentView(getLayoutResource());
                }
                if (!ListenerUtil.mutListener.listen(7072)) {
                    this.toolbar = findViewById(R.id.toolbar);
                }
                if (!ListenerUtil.mutListener.listen(7074)) {
                    if (toolbar != null) {
                        if (!ListenerUtil.mutListener.listen(7073)) {
                            setSupportActionBar(toolbar);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7075)) {
                    connectionIndicator = findViewById(R.id.connection_indicator);
                }
            }
        }
        return true;
    }

    public abstract int getLayoutResource();

    public void setToolbar(Toolbar toolbar) {
        if (!ListenerUtil.mutListener.listen(7077)) {
            this.toolbar = toolbar;
        }
    }

    public Toolbar getToolbar() {
        return this.toolbar;
    }

    protected View getConnectionIndicator() {
        return connectionIndicator;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(7084)) {
            switch(requestCode) {
                case ThreemaActivity.ACTIVITY_ID_UNLOCK_MASTER_KEY:
                    if (!ListenerUtil.mutListener.listen(7082)) {
                        if (ThreemaApplication.getMasterKey().isLocked()) {
                            if (!ListenerUtil.mutListener.listen(7081)) {
                                new MaterialAlertDialogBuilder(this).setTitle(R.string.master_key_locked).setMessage(R.string.master_key_locked_want_exit).setPositiveButton(R.string.try_again, new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        if (!ListenerUtil.mutListener.listen(7080)) {
                                            startActivityForResult(new Intent(ThreemaToolbarActivity.this, UnlockMasterKeyActivity.class), ThreemaActivity.ACTIVITY_ID_UNLOCK_MASTER_KEY);
                                        }
                                    }
                                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (!ListenerUtil.mutListener.listen(7079)) {
                                            finish();
                                        }
                                    }
                                }).show();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(7078)) {
                                this.initActivity(null);
                            }
                        }
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(7083)) {
                        super.onActivityResult(requestCode, resultCode, data);
                    }
            }
        }
    }

    @Override
    public void updateConnectionState(final ConnectionState connectionState, InetSocketAddress socketAddress) {
        if (!ListenerUtil.mutListener.listen(7085)) {
            RuntimeUtil.runOnUiThread(() -> ConnectionIndicatorUtil.getInstance().updateConnectionIndicator(connectionIndicator, connectionState));
        }
    }

    private static final String PORTRAIT_HEIGHT = "kbd_portrait_height";

    private static final String LANDSCAPE_HEIGHT = "kbd_landscape_height";

    private final Set<OnSoftKeyboardChangedListener> softKeyboardChangedListeners = new HashSet<>();

    private boolean softKeyboardOpen = false;

    private int minKeyboardHeight, minEmojiPickerHeight;

    public interface OnSoftKeyboardChangedListener {

        void onKeyboardHidden();

        void onKeyboardShown();
    }

    public void addOnSoftKeyboardChangedListener(OnSoftKeyboardChangedListener listener) {
        if (!ListenerUtil.mutListener.listen(7086)) {
            softKeyboardChangedListeners.add(listener);
        }
    }

    public void removeOnSoftKeyboardChangedListener(OnSoftKeyboardChangedListener listener) {
        if (!ListenerUtil.mutListener.listen(7087)) {
            softKeyboardChangedListeners.remove(listener);
        }
    }

    public void removeAllListeners() {
        if (!ListenerUtil.mutListener.listen(7088)) {
            softKeyboardChangedListeners.clear();
        }
    }

    public void notifySoftKeyboardHidden() {
        final Set<OnSoftKeyboardChangedListener> listeners = new HashSet<>(softKeyboardChangedListeners);
        if (!ListenerUtil.mutListener.listen(7090)) {
            {
                long _loopCounter60 = 0;
                for (OnSoftKeyboardChangedListener listener : listeners) {
                    ListenerUtil.loopListener.listen("_loopCounter60", ++_loopCounter60);
                    if (!ListenerUtil.mutListener.listen(7089)) {
                        listener.onKeyboardHidden();
                    }
                }
            }
        }
    }

    public void notifySoftKeyboardShown() {
        final Set<OnSoftKeyboardChangedListener> listeners = new HashSet<>(softKeyboardChangedListeners);
        if (!ListenerUtil.mutListener.listen(7092)) {
            {
                long _loopCounter61 = 0;
                for (OnSoftKeyboardChangedListener listener : listeners) {
                    ListenerUtil.loopListener.listen("_loopCounter61", ++_loopCounter61);
                    if (!ListenerUtil.mutListener.listen(7091)) {
                        listener.onKeyboardShown();
                    }
                }
            }
        }
    }

    public void onSoftKeyboardOpened(int softKeyboardHeight) {
        if (!ListenerUtil.mutListener.listen(7093)) {
            logger.info("%%% Potential keyboard height = " + softKeyboardHeight + " Min = " + minKeyboardHeight);
        }
        if (!ListenerUtil.mutListener.listen(7103)) {
            if ((ListenerUtil.mutListener.listen(7098) ? (softKeyboardHeight <= minKeyboardHeight) : (ListenerUtil.mutListener.listen(7097) ? (softKeyboardHeight > minKeyboardHeight) : (ListenerUtil.mutListener.listen(7096) ? (softKeyboardHeight < minKeyboardHeight) : (ListenerUtil.mutListener.listen(7095) ? (softKeyboardHeight != minKeyboardHeight) : (ListenerUtil.mutListener.listen(7094) ? (softKeyboardHeight == minKeyboardHeight) : (softKeyboardHeight >= minKeyboardHeight))))))) {
                if (!ListenerUtil.mutListener.listen(7099)) {
                    logger.info("%%% Soft keyboard open detected");
                }
                if (!ListenerUtil.mutListener.listen(7100)) {
                    this.softKeyboardOpen = true;
                }
                if (!ListenerUtil.mutListener.listen(7101)) {
                    saveSoftKeyboardHeight(softKeyboardHeight);
                }
                if (!ListenerUtil.mutListener.listen(7102)) {
                    notifySoftKeyboardShown();
                }
            }
        }
    }

    public void onSoftKeyboardClosed() {
        if (!ListenerUtil.mutListener.listen(7104)) {
            logger.info("%%% Soft keyboard closed");
        }
        if (!ListenerUtil.mutListener.listen(7105)) {
            this.softKeyboardOpen = false;
        }
        if (!ListenerUtil.mutListener.listen(7106)) {
            notifySoftKeyboardHidden();
        }
    }

    public void runOnSoftKeyboardClose(final Runnable runnable) {
        if (!ListenerUtil.mutListener.listen(7111)) {
            if (this.softKeyboardOpen) {
                if (!ListenerUtil.mutListener.listen(7110)) {
                    addOnSoftKeyboardChangedListener(new OnSoftKeyboardChangedListener() {

                        @Override
                        public void onKeyboardHidden() {
                            if (!ListenerUtil.mutListener.listen(7108)) {
                                removeOnSoftKeyboardChangedListener(this);
                            }
                            if (!ListenerUtil.mutListener.listen(7109)) {
                                runnable.run();
                            }
                        }

                        @Override
                        public void onKeyboardShown() {
                        }
                    });
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7107)) {
                    runnable.run();
                }
            }
        }
    }

    public void runOnSoftKeyboardOpen(@NonNull final Runnable runnable) {
        if (!ListenerUtil.mutListener.listen(7116)) {
            if (!isSoftKeyboardOpen()) {
                if (!ListenerUtil.mutListener.listen(7115)) {
                    addOnSoftKeyboardChangedListener(new OnSoftKeyboardChangedListener() {

                        @Override
                        public void onKeyboardShown() {
                            if (!ListenerUtil.mutListener.listen(7113)) {
                                removeOnSoftKeyboardChangedListener(this);
                            }
                            if (!ListenerUtil.mutListener.listen(7114)) {
                                runnable.run();
                            }
                        }

                        @Override
                        public void onKeyboardHidden() {
                        }
                    });
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7112)) {
                    runnable.run();
                }
            }
        }
    }

    @UiThread
    public void openSoftKeyboard(@NonNull final EmojiPicker emojiPicker, @NonNull final EditText messageText) {
        if (!ListenerUtil.mutListener.listen(7117)) {
            runOnSoftKeyboardOpen(() -> {
                emojiPicker.hide();
            });
        }
        if (!ListenerUtil.mutListener.listen(7118)) {
            messageText.post(() -> {
                messageText.requestFocus();
                EditTextUtil.showSoftKeyboard(messageText);
            });
        }
    }

    public boolean isSoftKeyboardOpen() {
        return softKeyboardOpen;
    }

    public void saveSoftKeyboardHeight(int softKeyboardHeight) {
        if (!ListenerUtil.mutListener.listen(7123)) {
            if (ConfigUtils.isLandscape(this)) {
                if (!ListenerUtil.mutListener.listen(7121)) {
                    logger.info("%%% Keyboard height (landscape): " + softKeyboardHeight);
                }
                if (!ListenerUtil.mutListener.listen(7122)) {
                    PreferenceManager.getDefaultSharedPreferences(this).edit().putInt(LANDSCAPE_HEIGHT, softKeyboardHeight).apply();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7119)) {
                    logger.info("%%% Keyboard height (portrait): " + softKeyboardHeight);
                }
                if (!ListenerUtil.mutListener.listen(7120)) {
                    PreferenceManager.getDefaultSharedPreferences(this).edit().putInt(PORTRAIT_HEIGHT, softKeyboardHeight).apply();
                }
            }
        }
    }

    public int loadStoredSoftKeyboardHeight() {
        boolean isLandscape = ConfigUtils.isLandscape(this);
        int savedSoftKeyboardHeight = isLandscape ? PreferenceManager.getDefaultSharedPreferences(this).getInt(LANDSCAPE_HEIGHT, getResources().getDimensionPixelSize(R.dimen.default_emoji_picker_height_landscape)) : PreferenceManager.getDefaultSharedPreferences(this).getInt(PORTRAIT_HEIGHT, getResources().getDimensionPixelSize(R.dimen.default_emoji_picker_height));
        if (!ListenerUtil.mutListener.listen(7129)) {
            if ((ListenerUtil.mutListener.listen(7128) ? (savedSoftKeyboardHeight >= minEmojiPickerHeight) : (ListenerUtil.mutListener.listen(7127) ? (savedSoftKeyboardHeight <= minEmojiPickerHeight) : (ListenerUtil.mutListener.listen(7126) ? (savedSoftKeyboardHeight > minEmojiPickerHeight) : (ListenerUtil.mutListener.listen(7125) ? (savedSoftKeyboardHeight != minEmojiPickerHeight) : (ListenerUtil.mutListener.listen(7124) ? (savedSoftKeyboardHeight == minEmojiPickerHeight) : (savedSoftKeyboardHeight < minEmojiPickerHeight))))))) {
                return getResources().getDimensionPixelSize(isLandscape ? R.dimen.default_emoji_picker_height_landscape : R.dimen.default_emoji_picker_height);
            }
        }
        return savedSoftKeyboardHeight;
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        if (!ListenerUtil.mutListener.listen(7130)) {
            loadStoredSoftKeyboardHeight();
        }
        if (!ListenerUtil.mutListener.listen(7131)) {
            super.onConfigurationChanged(newConfig);
        }
    }

    public void resetKeyboard() {
        if (!ListenerUtil.mutListener.listen(7132)) {
            minKeyboardHeight = getResources().getDimensionPixelSize(R.dimen.min_keyboard_height);
        }
        if (!ListenerUtil.mutListener.listen(7133)) {
            minEmojiPickerHeight = getResources().getDimensionPixelSize(R.dimen.min_emoji_keyboard_height);
        }
        if (!ListenerUtil.mutListener.listen(7134)) {
            removeAllListeners();
        }
        if (!ListenerUtil.mutListener.listen(7135)) {
            softKeyboardOpen = false;
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(7136)) {
            removeAllListeners();
        }
        if (!ListenerUtil.mutListener.listen(7137)) {
            super.onDestroy();
        }
    }
}
