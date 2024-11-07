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
package ch.threema.app.activities;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.Nullable;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.NotificationService;
import ch.threema.app.services.PassphraseService;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.client.ThreemaConnection;
import ch.threema.localcrypto.MasterKey;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Simple activity to stop passphrase service, lock master key and finish the app removing it from recents list - to be used from the persistent notification
 */
public class StopPassphraseServiceActivity extends Activity {

    private static final Logger logger = LoggerFactory.getLogger(StopPassphraseServiceActivity.class);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(6697)) {
            super.onCreate(savedInstanceState);
        }
        MasterKey masterKey = ThreemaApplication.getMasterKey();
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        ThreemaConnection threemaConnection = null;
        NotificationService notificationService = null;
        if (!ListenerUtil.mutListener.listen(6700)) {
            if (serviceManager != null) {
                if (!ListenerUtil.mutListener.listen(6698)) {
                    threemaConnection = serviceManager.getConnection();
                }
                if (!ListenerUtil.mutListener.listen(6699)) {
                    notificationService = serviceManager.getNotificationService();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6712)) {
            if (masterKey.isProtected()) {
                if (!ListenerUtil.mutListener.listen(6711)) {
                    if (!masterKey.isLocked()) {
                        if (!ListenerUtil.mutListener.listen(6705)) {
                            if ((ListenerUtil.mutListener.listen(6701) ? (threemaConnection != null || threemaConnection.isRunning()) : (threemaConnection != null && threemaConnection.isRunning()))) {
                                try {
                                    if (!ListenerUtil.mutListener.listen(6704)) {
                                        threemaConnection.stop();
                                    }
                                } catch (InterruptedException e) {
                                    if (!ListenerUtil.mutListener.listen(6702)) {
                                        logger.error("Interrupted in onCreate while stopping threema connection", e);
                                    }
                                    if (!ListenerUtil.mutListener.listen(6703)) {
                                        Thread.currentThread().interrupt();
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(6707)) {
                            if (notificationService != null) {
                                if (!ListenerUtil.mutListener.listen(6706)) {
                                    notificationService.cancelConversationNotificationsOnLockApp();
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(6708)) {
                            masterKey.lock();
                        }
                        if (!ListenerUtil.mutListener.listen(6709)) {
                            PassphraseService.stop(this);
                        }
                        if (!ListenerUtil.mutListener.listen(6710)) {
                            ConfigUtils.scheduleAppRestart(this, 2000, getString(R.string.passphrase_locked));
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6720)) {
            if ((ListenerUtil.mutListener.listen(6717) ? (Build.VERSION.SDK_INT <= 21) : (ListenerUtil.mutListener.listen(6716) ? (Build.VERSION.SDK_INT > 21) : (ListenerUtil.mutListener.listen(6715) ? (Build.VERSION.SDK_INT < 21) : (ListenerUtil.mutListener.listen(6714) ? (Build.VERSION.SDK_INT != 21) : (ListenerUtil.mutListener.listen(6713) ? (Build.VERSION.SDK_INT == 21) : (Build.VERSION.SDK_INT >= 21))))))) {
                if (!ListenerUtil.mutListener.listen(6719)) {
                    finishAndRemoveTask();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6718)) {
                    finish();
                }
            }
        }
    }
}
