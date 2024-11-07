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
import android.os.Bundle;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.backuprestore.csv.BackupService;
import ch.threema.app.backuprestore.csv.RestoreService;
import ch.threema.app.services.UserService;
import ch.threema.app.utils.TestUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class ThreemaActivity extends ThreemaAppCompatActivity {

    private static final Logger logger = LoggerFactory.getLogger(ThreemaActivity.class);

    public static final int ACTIVITY_ID_WIZARDFIRST = 20001;

    public static final int ACTIVITY_ID_SETTINGS = 20002;

    public static final int ACTIVITY_ID_COMPOSE_MESSAGE = 20003;

    public static final int ACTIVITY_ID_ADD_CONTACT = 20004;

    public static final int ACTIVITY_ID_VERIFY_MOBILE = 20005;

    public static final int ACTIVITY_ID_CONTACT_DETAIL = 20007;

    public static final int ACTIVITY_ID_UNLOCK_MASTER_KEY = 20008;

    public static final int ACTIVITY_ID_PICK_CAMERA_EXTERNAL = 20011;

    public static final int ACTIVITY_ID_PICK_CAMERA_INTERNAL = 20012;

    public static final int ACTIVITY_ID_SET_PASSPHRASE = 20013;

    public static final int ACTIVITY_ID_CHANGE_PASSPHRASE = 20014;

    public static final int ACTIVITY_ID_RESET_PASSPHRASE = 20015;

    public static final int ACTIVITY_ID_RESTORE_KEY = 20016;

    public static final int ACTIVITY_ID_ENTER_SERIAL = 20017;

    public static final int ACTIVITY_ID_SHARE_CHAT = 20018;

    public static final int ACTIVITY_ID_SEND_MEDIA = 20019;

    public static final int ACTIVITY_ID_ATTACH_MEDIA = 20020;

    public static final int ACTIVITY_ID_CONFIRM_DEVICE_CREDENTIALS = 20021;

    public static final int ACTIVITY_ID_GROUP_ADD = 20028;

    public static final int ACTIVITY_ID_GROUP_DETAIL = 20029;

    public static final int ACTIVITY_ID_CHANGE_PASSPHRASE_UNLOCK = 20032;

    public static final int ACTIVITY_ID_MEDIA_VIEWER = 20035;

    public static final int ACTIVITY_ID_CREATE_BALLOT = 20037;

    public static final int ACTIVITY_ID_ID_SECTION = 20041;

    public static final int ACTIVITY_ID_BACKUP_PICKER = 20042;

    public static final int ACTIVITY_ID_COPY_BALLOT = 20043;

    public static final int ACTIVITY_ID_CHECK_LOCK = 20046;

    public static final int ACTIVITY_ID_PICK_FILE = 20047;

    public static final int ACTIVITY_ID_PAINT = 20049;

    public static final int ACTIVITY_ID_PICK_MEDIA = 20050;

    public static final int RESULT_RESTART = 40005;

    private boolean isResumed;

    private String myIdentity = null;

    @Override
    protected void onPause() {
        if (!ListenerUtil.mutListener.listen(6980)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(6984)) {
            if ((ListenerUtil.mutListener.listen(6981) ? (isPinLockable() || isResumed) : (isPinLockable() && isResumed))) {
                if (!ListenerUtil.mutListener.listen(6982)) {
                    ThreemaApplication.activityPaused(this);
                }
                if (!ListenerUtil.mutListener.listen(6983)) {
                    isResumed = false;
                }
            }
        }
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(6987)) {
            if ((ListenerUtil.mutListener.listen(6985) ? (isPinLockable() || ThreemaApplication.activityResumed(this)) : (isPinLockable() && ThreemaApplication.activityResumed(this)))) {
                if (!ListenerUtil.mutListener.listen(6986)) {
                    isResumed = true;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6991)) {
            if ((ListenerUtil.mutListener.listen(6988) ? (BackupService.isRunning() && RestoreService.isRunning()) : (BackupService.isRunning() || RestoreService.isRunning()))) {
                if (!ListenerUtil.mutListener.listen(6989)) {
                    Toast.makeText(this, "Backup or restore in progress. Try Again later.", Toast.LENGTH_LONG).show();
                }
                if (!ListenerUtil.mutListener.listen(6990)) {
                    finish();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6992)) {
            super.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(6995)) {
            // In such a case, though, any cleanup you expected to be done in onPause() and onStop() will not be executed.
            if ((ListenerUtil.mutListener.listen(6993) ? (isPinLockable() || isResumed) : (isPinLockable() && isResumed))) {
                if (!ListenerUtil.mutListener.listen(6994)) {
                    ThreemaApplication.activityDestroyed(this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6996)) {
            super.onDestroy();
        }
    }

    @Override
    public void onUserInteraction() {
        if (!ListenerUtil.mutListener.listen(6998)) {
            if (isPinLockable()) {
                if (!ListenerUtil.mutListener.listen(6997)) {
                    ThreemaApplication.activityUserInteract(this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6999)) {
            super.onUserInteraction();
        }
    }

    protected boolean isPinLockable() {
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(7000)) {
            super.onSaveInstanceState(outState);
        }
    }

    /**
     *  Return true if master key is unlocked or not protected
     *
     *  @return
     */
    public boolean isAllowed() {
        return (ListenerUtil.mutListener.listen(7001) ? (!ThreemaApplication.getMasterKey().isLocked() && !ThreemaApplication.getMasterKey().isProtected()) : (!ThreemaApplication.getMasterKey().isLocked() || !ThreemaApplication.getMasterKey().isProtected()));
    }

    protected final boolean requiredInstances() {
        if (!ListenerUtil.mutListener.listen(7004)) {
            if (!this.checkInstances()) {
                try {
                    if (!ListenerUtil.mutListener.listen(7003)) {
                        this.instantiate();
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(7002)) {
                        logger.error("Instantiation failed", e);
                    }
                    return false;
                }
            }
        }
        return this.checkInstances();
    }

    protected boolean checkInstances() {
        return true;
    }

    protected void instantiate() {
    }

    protected String getMyIdentity() {
        if (!ListenerUtil.mutListener.listen(7008)) {
            if (this.myIdentity == null) {
                UserService userService = ThreemaApplication.getServiceManager().getUserService();
                if (!ListenerUtil.mutListener.listen(7007)) {
                    if ((ListenerUtil.mutListener.listen(7005) ? (userService != null || !TestUtil.empty(userService.getIdentity())) : (userService != null && !TestUtil.empty(userService.getIdentity())))) {
                        if (!ListenerUtil.mutListener.listen(7006)) {
                            this.myIdentity = userService.getIdentity();
                        }
                    }
                }
            }
        }
        return this.myIdentity;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(7009)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        if (!ListenerUtil.mutListener.listen(7010)) {
            super.startActivityForResult(intent, requestCode);
        }
        if (!ListenerUtil.mutListener.listen(7011)) {
            overridePendingTransition(R.anim.fast_fade_in, R.anim.fast_fade_out);
        }
    }

    @Override
    public void startActivity(Intent intent) {
        if (!ListenerUtil.mutListener.listen(7012)) {
            super.startActivity(intent);
        }
        if (!ListenerUtil.mutListener.listen(7013)) {
            overridePendingTransition(R.anim.fast_fade_in, R.anim.fast_fade_out);
        }
    }

    @Override
    public void finish() {
        if (!ListenerUtil.mutListener.listen(7014)) {
            super.finish();
        }
        if (!ListenerUtil.mutListener.listen(7015)) {
            overridePendingTransition(R.anim.fast_fade_in, R.anim.fast_fade_out);
        }
    }
}
