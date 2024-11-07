/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2013-2021 Threema GmbH
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

import android.content.Intent;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.widget.FrameLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.fragments.ComposeMessageFragment;
import ch.threema.app.fragments.MessageSectionFragment;
import ch.threema.app.listeners.MessagePlayerListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.preference.SettingsActivity;
import ch.threema.app.preference.SettingsSecurityFragment;
import ch.threema.app.services.DeadlineListService;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.HiddenChatUtil;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.localcrypto.MasterKey;
import ch.threema.storage.models.AbstractMessageModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ComposeMessageActivity extends ThreemaToolbarActivity implements GenericAlertDialog.DialogClickListener {

    private static final Logger logger = LoggerFactory.getLogger(ComposeMessageActivity.class);

    private static final int ID_HIDDEN_CHECK_ON_NEW_INTENT = 9291;

    private static final int ID_HIDDEN_CHECK_ON_CREATE = 9292;

    private static final String DIALOG_TAG_HIDDEN_NOTICE = "hidden";

    private ComposeMessageFragment composeMessageFragment;

    private MessageSectionFragment messageSectionFragment;

    private Intent currentIntent;

    private final String COMPOSE_FRAGMENT_TAG = "compose_message_fragment";

    private final String MESSAGES_FRAGMENT_TAG = "message_section_fragment";

    private final MessagePlayerListener messagePlayerListener = new MessagePlayerListener() {

        @Override
        public void onAudioStreamChanged(int newStreamType) {
            if (!ListenerUtil.mutListener.listen(1933)) {
                setVolumeControlStream(newStreamType == AudioManager.STREAM_VOICE_CALL ? AudioManager.STREAM_VOICE_CALL : AudioManager.USE_DEFAULT_STREAM_TYPE);
            }
        }

        @Override
        public void onAudioPlayEnded(AbstractMessageModel messageModel) {
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1934)) {
            logger.debug("onCreate");
        }
        if (!ListenerUtil.mutListener.listen(1935)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1936)) {
            this.currentIntent = getIntent();
        }
        if (!ListenerUtil.mutListener.listen(1937)) {
            ListenerManager.messagePlayerListener.add(this.messagePlayerListener);
        }
        // check master key
        MasterKey masterKey = ThreemaApplication.getMasterKey();
        if (!ListenerUtil.mutListener.listen(1940)) {
            if (!((ListenerUtil.mutListener.listen(1938) ? (masterKey != null || masterKey.isLocked()) : (masterKey != null && masterKey.isLocked())))) {
                if (!ListenerUtil.mutListener.listen(1939)) {
                    this.initActivity(savedInstanceState);
                }
            }
        }
    }

    @Override
    protected boolean initActivity(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1941)) {
            if (!super.initActivity(savedInstanceState)) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(1942)) {
            logger.debug("initActivity");
        }
        if (!ListenerUtil.mutListener.listen(1943)) {
            checkHiddenChatLock(getIntent(), ID_HIDDEN_CHECK_ON_CREATE);
        }
        if (!ListenerUtil.mutListener.listen(1944)) {
            this.getFragments();
        }
        if (!ListenerUtil.mutListener.listen(1948)) {
            if (findViewById(R.id.messages) != null) {
                if (!ListenerUtil.mutListener.listen(1947)) {
                    // add messages fragment in tablet layout
                    if (messageSectionFragment == null) {
                        if (!ListenerUtil.mutListener.listen(1945)) {
                            messageSectionFragment = new MessageSectionFragment();
                        }
                        if (!ListenerUtil.mutListener.listen(1946)) {
                            getSupportFragmentManager().beginTransaction().add(R.id.messages, messageSectionFragment, MESSAGES_FRAGMENT_TAG).commit();
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1951)) {
            if (composeMessageFragment == null) {
                if (!ListenerUtil.mutListener.listen(1949)) {
                    // fragment no longer around
                    composeMessageFragment = new ComposeMessageFragment();
                }
                if (!ListenerUtil.mutListener.listen(1950)) {
                    getSupportFragmentManager().beginTransaction().add(R.id.compose, composeMessageFragment, COMPOSE_FRAGMENT_TAG).commit();
                }
            }
        }
        return true;
    }

    @Override
    public int getLayoutResource() {
        return ConfigUtils.isTabletLayout(this) ? R.layout.activity_compose_message_tablet : R.layout.activity_compose_message;
    }

    private void getFragments() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (!ListenerUtil.mutListener.listen(1952)) {
            composeMessageFragment = (ComposeMessageFragment) fragmentManager.findFragmentByTag(COMPOSE_FRAGMENT_TAG);
        }
        if (!ListenerUtil.mutListener.listen(1953)) {
            messageSectionFragment = (MessageSectionFragment) fragmentManager.findFragmentByTag(MESSAGES_FRAGMENT_TAG);
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        if (!ListenerUtil.mutListener.listen(1954)) {
            logger.debug("onNewIntent");
        }
        if (!ListenerUtil.mutListener.listen(1955)) {
            super.onNewIntent(intent);
        }
        if (!ListenerUtil.mutListener.listen(1956)) {
            this.currentIntent = intent;
        }
        if (!ListenerUtil.mutListener.listen(1957)) {
            this.getFragments();
        }
        if (!ListenerUtil.mutListener.listen(1960)) {
            if (composeMessageFragment != null) {
                if (!ListenerUtil.mutListener.listen(1959)) {
                    if (!checkHiddenChatLock(intent, ID_HIDDEN_CHECK_ON_NEW_INTENT)) {
                        if (!ListenerUtil.mutListener.listen(1958)) {
                            composeMessageFragment.onNewIntent(intent);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(1961)) {
            logger.debug("onBackPressed");
        }
        if (!ListenerUtil.mutListener.listen(1964)) {
            if (ConfigUtils.isTabletLayout()) {
                if (!ListenerUtil.mutListener.listen(1963)) {
                    if (messageSectionFragment != null) {
                        if (!ListenerUtil.mutListener.listen(1962)) {
                            if (messageSectionFragment.onBackPressed()) {
                                return;
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1968)) {
            if (composeMessageFragment != null) {
                if (!ListenerUtil.mutListener.listen(1967)) {
                    if (!composeMessageFragment.onBackPressed()) {
                        if (!ListenerUtil.mutListener.listen(1965)) {
                            finish();
                        }
                        if (!ListenerUtil.mutListener.listen(1966)) {
                            overridePendingTransition(0, 0);
                        }
                    }
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1969)) {
            super.onBackPressed();
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(1970)) {
            logger.debug("onDestroy");
        }
        if (!ListenerUtil.mutListener.listen(1971)) {
            ListenerManager.messagePlayerListener.remove(this.messagePlayerListener);
        }
        if (!ListenerUtil.mutListener.listen(1972)) {
            super.onDestroy();
        }
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(1973)) {
            logger.debug("onStop");
        }
        if (!ListenerUtil.mutListener.listen(1974)) {
            super.onStop();
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(1975)) {
            logger.debug("onResume");
        }
        if (!ListenerUtil.mutListener.listen(1976)) {
            super.onResume();
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(1977)) {
            logger.debug("onPause");
        }
        if (!ListenerUtil.mutListener.listen(1978)) {
            super.onPause();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!ListenerUtil.mutListener.listen(1979)) {
            logger.debug("onWindowFocusChanged " + hasFocus);
        }
        if (!ListenerUtil.mutListener.listen(1980)) {
            super.onWindowFocusChanged(hasFocus);
        }
        if (!ListenerUtil.mutListener.listen(1984)) {
            if ((ListenerUtil.mutListener.listen(1982) ? ((ListenerUtil.mutListener.listen(1981) ? (ConfigUtils.isSamsungDevice() || !ConfigUtils.isTabletLayout()) : (ConfigUtils.isSamsungDevice() && !ConfigUtils.isTabletLayout())) || composeMessageFragment != null) : ((ListenerUtil.mutListener.listen(1981) ? (ConfigUtils.isSamsungDevice() || !ConfigUtils.isTabletLayout()) : (ConfigUtils.isSamsungDevice() && !ConfigUtils.isTabletLayout())) && composeMessageFragment != null))) {
                if (!ListenerUtil.mutListener.listen(1983)) {
                    composeMessageFragment.onWindowFocusChanged(hasFocus);
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent intent) {
        if (!ListenerUtil.mutListener.listen(2002)) {
            switch(requestCode) {
                case ID_HIDDEN_CHECK_ON_CREATE:
                    if (!ListenerUtil.mutListener.listen(1985)) {
                        super.onActivityResult(requestCode, resultCode, intent);
                    }
                    if (!ListenerUtil.mutListener.listen(1990)) {
                        if (resultCode == RESULT_OK) {
                            if (!ListenerUtil.mutListener.listen(1987)) {
                                serviceManager.getScreenLockService().setAuthenticated(true);
                            }
                            if (!ListenerUtil.mutListener.listen(1989)) {
                                if (composeMessageFragment != null) {
                                    if (!ListenerUtil.mutListener.listen(1988)) {
                                        // mark conversation as read as soon as it's unhidden
                                        composeMessageFragment.markAsRead();
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(1986)) {
                                finish();
                            }
                        }
                    }
                    break;
                case ID_HIDDEN_CHECK_ON_NEW_INTENT:
                    if (!ListenerUtil.mutListener.listen(1991)) {
                        super.onActivityResult(requestCode, resultCode, intent);
                    }
                    if (!ListenerUtil.mutListener.listen(1995)) {
                        if (resultCode == RESULT_OK) {
                            if (!ListenerUtil.mutListener.listen(1992)) {
                                serviceManager.getScreenLockService().setAuthenticated(true);
                            }
                            if (!ListenerUtil.mutListener.listen(1994)) {
                                if (composeMessageFragment != null) {
                                    if (!ListenerUtil.mutListener.listen(1993)) {
                                        composeMessageFragment.onNewIntent(this.currentIntent);
                                    }
                                }
                            }
                        }
                    }
                    break;
                case ThreemaActivity.ACTIVITY_ID_UNLOCK_MASTER_KEY:
                    if (!ListenerUtil.mutListener.listen(1998)) {
                        if (ThreemaApplication.getMasterKey().isLocked()) {
                            if (!ListenerUtil.mutListener.listen(1997)) {
                                finish();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(1996)) {
                                ConfigUtils.recreateActivity(this, ComposeMessageActivity.class, getIntent().getExtras());
                            }
                        }
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(1999)) {
                        super.onActivityResult(requestCode, resultCode, intent);
                    }
                    if (!ListenerUtil.mutListener.listen(2001)) {
                        // required for result of qr code scanner
                        if (composeMessageFragment != null) {
                            if (!ListenerUtil.mutListener.listen(2000)) {
                                composeMessageFragment.onActivityResult(requestCode, resultCode, intent);
                            }
                        }
                    }
            }
        }
    }

    private boolean checkHiddenChatLock(Intent intent, int requestCode) {
        MessageReceiver messageReceiver = IntentDataUtil.getMessageReceiverFromIntent(getApplicationContext(), intent);
        if (!ListenerUtil.mutListener.listen(2010)) {
            if ((ListenerUtil.mutListener.listen(2003) ? (messageReceiver != null || serviceManager != null) : (messageReceiver != null && serviceManager != null))) {
                DeadlineListService hiddenChatsListService = serviceManager.getHiddenChatsListService();
                if (!ListenerUtil.mutListener.listen(2009)) {
                    if ((ListenerUtil.mutListener.listen(2004) ? (hiddenChatsListService != null || hiddenChatsListService.has(messageReceiver.getUniqueIdString())) : (hiddenChatsListService != null && hiddenChatsListService.has(messageReceiver.getUniqueIdString())))) {
                        if (!ListenerUtil.mutListener.listen(2008)) {
                            if ((ListenerUtil.mutListener.listen(2005) ? (preferenceService != null || ConfigUtils.hasProtection(preferenceService)) : (preferenceService != null && ConfigUtils.hasProtection(preferenceService)))) {
                                if (!ListenerUtil.mutListener.listen(2007)) {
                                    HiddenChatUtil.launchLockCheckDialog(this, null, preferenceService, requestCode);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(2006)) {
                                    GenericAlertDialog.newInstance(R.string.hide_chat, R.string.hide_chat_enter_message_explain, R.string.set_lock, R.string.cancel).show(getSupportFragmentManager(), DIALOG_TAG_HIDDEN_NOTICE);
                                }
                            }
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        if (!ListenerUtil.mutListener.listen(2011)) {
            super.onConfigurationChanged(newConfig);
        }
        if (!ListenerUtil.mutListener.listen(2012)) {
            ConfigUtils.adjustToolbar(this, getToolbar());
        }
        FrameLayout messagesLayout = findViewById(R.id.messages);
        if (!ListenerUtil.mutListener.listen(2015)) {
            if (messagesLayout != null) {
                // adjust width of messages fragment in tablet layout
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) messagesLayout.getLayoutParams();
                if (!ListenerUtil.mutListener.listen(2013)) {
                    layoutParams.width = getResources().getDimensionPixelSize(R.dimen.message_fragment_width);
                }
                if (!ListenerUtil.mutListener.listen(2014)) {
                    messagesLayout.setLayoutParams(layoutParams);
                }
            }
        }
    }

    @Override
    public void onYes(String tag, Object data) {
        Intent intent = new Intent(this, SettingsActivity.class);
        if (!ListenerUtil.mutListener.listen(2016)) {
            intent.putExtra(PreferenceActivity.EXTRA_SHOW_FRAGMENT, SettingsSecurityFragment.class.getName());
        }
        if (!ListenerUtil.mutListener.listen(2017)) {
            intent.putExtra(PreferenceActivity.EXTRA_NO_HEADERS, true);
        }
        if (!ListenerUtil.mutListener.listen(2018)) {
            startActivity(intent);
        }
        if (!ListenerUtil.mutListener.listen(2019)) {
            finish();
        }
    }

    @Override
    public void onNo(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(2020)) {
            finish();
        }
    }
}
