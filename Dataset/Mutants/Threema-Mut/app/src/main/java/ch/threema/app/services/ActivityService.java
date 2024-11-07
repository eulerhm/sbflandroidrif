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
package ch.threema.app.services;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.ref.WeakReference;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.PinLockActivity;
import ch.threema.app.utils.BiometricUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.localcrypto.MasterKey;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ActivityService {

    private static final Logger logger = LoggerFactory.getLogger(ActivityService.class);

    private final Context context;

    private final LockAppService lockAppService;

    private final PreferenceService preferenceService;

    private WeakReference<Activity> currentActivityReference = new WeakReference<>(null);

    public ActivityService(final Context context, LockAppService lockAppService, PreferenceService preferenceService) {
        this.context = context;
        this.lockAppService = lockAppService;
        this.preferenceService = preferenceService;
        if (!ListenerUtil.mutListener.listen(36586)) {
            this.lockAppService.addOnLockAppStateChanged(new LockAppService.OnLockAppStateChanged() {

                @Override
                public boolean changed(final boolean locked) {
                    if (!ListenerUtil.mutListener.listen(36585)) {
                        handLockedState(locked);
                    }
                    return false;
                }
            });
        }
    }

    private synchronized void handLockedState(final boolean locked) {
        if (!ListenerUtil.mutListener.listen(36587)) {
            logger.debug("handLockedState currentActivity: " + currentActivityReference.get());
        }
        MasterKey masterKey = ThreemaApplication.getMasterKey();
        if (!ListenerUtil.mutListener.listen(36589)) {
            if ((ListenerUtil.mutListener.listen(36588) ? (masterKey != null || masterKey.isLocked()) : (masterKey != null && masterKey.isLocked()))) {
                return;
            }
        }
        boolean isPinLock = (ListenerUtil.mutListener.listen(36591) ? ((ListenerUtil.mutListener.listen(36590) ? (currentActivityReference == null && currentActivityReference.get() == null) : (currentActivityReference == null || currentActivityReference.get() == null)) && currentActivityReference.get() instanceof PinLockActivity) : ((ListenerUtil.mutListener.listen(36590) ? (currentActivityReference == null && currentActivityReference.get() == null) : (currentActivityReference == null || currentActivityReference.get() == null)) || currentActivityReference.get() instanceof PinLockActivity));
        if (!ListenerUtil.mutListener.listen(36593)) {
            if (!isPinLock) {
                if (!ListenerUtil.mutListener.listen(36592)) {
                    RuntimeUtil.runOnUiThread(() -> {
                        logger.info("handLockedState - locked = {}", locked);
                        if (locked) {
                            if (currentActivityReference.get() != null) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && (preferenceService.getLockMechanism().equals(PreferenceService.LockingMech_SYSTEM) || preferenceService.getLockMechanism().equals(PreferenceService.LockingMech_BIOMETRIC))) {
                                    BiometricUtil.showUnlockDialog(currentActivityReference.get(), false, 0, null);
                                } else {
                                    try {
                                        Intent intent = new Intent(context, PinLockActivity.class);
                                        currentActivityReference.get().startActivity(intent);
                                        currentActivityReference.get().overridePendingTransition(0, 0);
                                    } catch (Exception x) {
                                        logger.error("Exception", x);
                                    }
                                }
                            }
                        }
                    });
                }
            }
        }
    }

    public void resume(Activity currentActivity) {
        if (!ListenerUtil.mutListener.listen(36594)) {
            this.currentActivityReference = new WeakReference<>(currentActivity);
        }
        if (!ListenerUtil.mutListener.listen(36597)) {
            if (this.lockAppService.checkLock()) {
                if (!ListenerUtil.mutListener.listen(36596)) {
                    if (this.timeLocking()) {
                        if (!ListenerUtil.mutListener.listen(36595)) {
                            this.handLockedState(true);
                        }
                    }
                }
            }
        }
    }

    public void pause(Activity pausedActivity) {
        if (!ListenerUtil.mutListener.listen(36599)) {
            if (this.currentActivityReference.get() == pausedActivity) {
                if (!ListenerUtil.mutListener.listen(36598)) {
                    this.currentActivityReference.clear();
                }
            }
        }
    }

    public void destroy(Activity destroyedActivity) {
        if (!ListenerUtil.mutListener.listen(36601)) {
            if (this.currentActivityReference.get() == destroyedActivity) {
                if (!ListenerUtil.mutListener.listen(36600)) {
                    this.currentActivityReference.clear();
                }
            }
        }
    }

    public void userInteract(Activity interactedActivity) {
        if (!ListenerUtil.mutListener.listen(36602)) {
            this.currentActivityReference.clear();
        }
        if (!ListenerUtil.mutListener.listen(36603)) {
            this.currentActivityReference = new WeakReference<>(interactedActivity);
        }
        if (!ListenerUtil.mutListener.listen(36604)) {
            this.timeLocking();
        }
    }

    private boolean timeLocking() {
        if (!ListenerUtil.mutListener.listen(36605)) {
            logger.debug("timeLocking");
        }
        if (!ListenerUtil.mutListener.listen(36608)) {
            if (this.lockAppService.isLockingEnabled()) {
                if (!ListenerUtil.mutListener.listen(36607)) {
                    if (!this.lockAppService.isLocked()) {
                        if (!ListenerUtil.mutListener.listen(36606)) {
                            this.lockAppService.resetLockTimer(true);
                        }
                    } else {
                        // hand locked state to resuming activity
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
