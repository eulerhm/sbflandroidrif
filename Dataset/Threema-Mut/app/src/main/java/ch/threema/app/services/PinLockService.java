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

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.format.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.receivers.AlarmManagerBroadcastReceiver;
import ch.threema.app.utils.WidgetUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PinLockService implements LockAppService {

    private static final Logger logger = LoggerFactory.getLogger(PinLockService.class);

    private final Context context;

    private final PreferenceService preferencesService;

    private NotificationService notificationService;

    private final UserService userService;

    private boolean locked;

    private final AlarmManager alarmManager;

    private PendingIntent lockTimerIntent;

    private long lockTimeStamp = 0;

    private final CopyOnWriteArrayList<OnLockAppStateChanged> lockAppStateChangedItems = new CopyOnWriteArrayList<>();

    public PinLockService(Context context, PreferenceService preferencesService, UserService userService) {
        this.context = context;
        this.preferencesService = preferencesService;
        this.userService = userService;
        this.alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (!ListenerUtil.mutListener.listen(40249)) {
            this.locked = preferencesService.isAppLockEnabled();
        }
    }

    @Override
    public boolean isLockingEnabled() {
        if (!ListenerUtil.mutListener.listen(40250)) {
            logger.debug("isLockingEnabled");
        }
        return ((ListenerUtil.mutListener.listen(40251) ? (preferencesService.isAppLockEnabled() || this.userService.hasIdentity()) : (preferencesService.isAppLockEnabled() && this.userService.hasIdentity())));
    }

    @Override
    public boolean unlock(String pin) {
        if (!ListenerUtil.mutListener.listen(40252)) {
            logger.debug("unlock");
        }
        if (!ListenerUtil.mutListener.listen(40259)) {
            if ((ListenerUtil.mutListener.listen(40255) ? ((ListenerUtil.mutListener.listen(40254) ? (((ListenerUtil.mutListener.listen(40253) ? (PreferenceService.LockingMech_PIN.equals(preferencesService.getLockMechanism()) || this.preferencesService.isPinCodeCorrect(pin)) : (PreferenceService.LockingMech_PIN.equals(preferencesService.getLockMechanism()) && this.preferencesService.isPinCodeCorrect(pin)))) && PreferenceService.LockingMech_SYSTEM.equals(preferencesService.getLockMechanism())) : (((ListenerUtil.mutListener.listen(40253) ? (PreferenceService.LockingMech_PIN.equals(preferencesService.getLockMechanism()) || this.preferencesService.isPinCodeCorrect(pin)) : (PreferenceService.LockingMech_PIN.equals(preferencesService.getLockMechanism()) && this.preferencesService.isPinCodeCorrect(pin)))) || PreferenceService.LockingMech_SYSTEM.equals(preferencesService.getLockMechanism()))) && PreferenceService.LockingMech_BIOMETRIC.equals(preferencesService.getLockMechanism())) : ((ListenerUtil.mutListener.listen(40254) ? (((ListenerUtil.mutListener.listen(40253) ? (PreferenceService.LockingMech_PIN.equals(preferencesService.getLockMechanism()) || this.preferencesService.isPinCodeCorrect(pin)) : (PreferenceService.LockingMech_PIN.equals(preferencesService.getLockMechanism()) && this.preferencesService.isPinCodeCorrect(pin)))) && PreferenceService.LockingMech_SYSTEM.equals(preferencesService.getLockMechanism())) : (((ListenerUtil.mutListener.listen(40253) ? (PreferenceService.LockingMech_PIN.equals(preferencesService.getLockMechanism()) || this.preferencesService.isPinCodeCorrect(pin)) : (PreferenceService.LockingMech_PIN.equals(preferencesService.getLockMechanism()) && this.preferencesService.isPinCodeCorrect(pin)))) || PreferenceService.LockingMech_SYSTEM.equals(preferencesService.getLockMechanism()))) || PreferenceService.LockingMech_BIOMETRIC.equals(preferencesService.getLockMechanism())))) {
                if (!ListenerUtil.mutListener.listen(40256)) {
                    this.resetLockTimer(false);
                }
                if (!ListenerUtil.mutListener.listen(40257)) {
                    this.updateState(false);
                }
                if (!ListenerUtil.mutListener.listen(40258)) {
                    this.lockTimeStamp = 0;
                }
                return !this.locked;
            }
        }
        return false;
    }

    @Override
    public void lock() {
        if (!ListenerUtil.mutListener.listen(40260)) {
            logger.debug("lock");
        }
        if (!ListenerUtil.mutListener.listen(40267)) {
            if (isLockingEnabled()) {
                if (!ListenerUtil.mutListener.listen(40261)) {
                    this.updateState(true);
                }
                try {
                    if (!ListenerUtil.mutListener.listen(40264)) {
                        if (this.notificationService == null) {
                            if (!ListenerUtil.mutListener.listen(40263)) {
                                notificationService = ThreemaApplication.getServiceManager().getNotificationService();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(40266)) {
                        if (this.notificationService != null) {
                            if (!ListenerUtil.mutListener.listen(40265)) {
                                notificationService.cancelConversationNotificationsOnLockApp();
                            }
                        }
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(40262)) {
                        logger.warn("Could not cancel conversation notifications when locking app:");
                    }
                }
            }
        }
    }

    @Override
    public boolean checkLock() {
        if (!ListenerUtil.mutListener.listen(40280)) {
            if ((ListenerUtil.mutListener.listen(40278) ? ((ListenerUtil.mutListener.listen(40272) ? (lockTimeStamp >= 0) : (ListenerUtil.mutListener.listen(40271) ? (lockTimeStamp <= 0) : (ListenerUtil.mutListener.listen(40270) ? (lockTimeStamp < 0) : (ListenerUtil.mutListener.listen(40269) ? (lockTimeStamp != 0) : (ListenerUtil.mutListener.listen(40268) ? (lockTimeStamp == 0) : (lockTimeStamp > 0)))))) || (ListenerUtil.mutListener.listen(40277) ? (System.currentTimeMillis() >= lockTimeStamp) : (ListenerUtil.mutListener.listen(40276) ? (System.currentTimeMillis() <= lockTimeStamp) : (ListenerUtil.mutListener.listen(40275) ? (System.currentTimeMillis() < lockTimeStamp) : (ListenerUtil.mutListener.listen(40274) ? (System.currentTimeMillis() != lockTimeStamp) : (ListenerUtil.mutListener.listen(40273) ? (System.currentTimeMillis() == lockTimeStamp) : (System.currentTimeMillis() > lockTimeStamp))))))) : ((ListenerUtil.mutListener.listen(40272) ? (lockTimeStamp >= 0) : (ListenerUtil.mutListener.listen(40271) ? (lockTimeStamp <= 0) : (ListenerUtil.mutListener.listen(40270) ? (lockTimeStamp < 0) : (ListenerUtil.mutListener.listen(40269) ? (lockTimeStamp != 0) : (ListenerUtil.mutListener.listen(40268) ? (lockTimeStamp == 0) : (lockTimeStamp > 0)))))) && (ListenerUtil.mutListener.listen(40277) ? (System.currentTimeMillis() >= lockTimeStamp) : (ListenerUtil.mutListener.listen(40276) ? (System.currentTimeMillis() <= lockTimeStamp) : (ListenerUtil.mutListener.listen(40275) ? (System.currentTimeMillis() < lockTimeStamp) : (ListenerUtil.mutListener.listen(40274) ? (System.currentTimeMillis() != lockTimeStamp) : (ListenerUtil.mutListener.listen(40273) ? (System.currentTimeMillis() == lockTimeStamp) : (System.currentTimeMillis() > lockTimeStamp))))))))) {
                if (!ListenerUtil.mutListener.listen(40279)) {
                    lock();
                }
            }
        }
        return true;
    }

    private void updateState(boolean locked) {
        if (!ListenerUtil.mutListener.listen(40281)) {
            logger.info("update locked stated to: {} ", isLocked());
        }
        if (!ListenerUtil.mutListener.listen(40288)) {
            if (this.locked != locked) {
                if (!ListenerUtil.mutListener.listen(40282)) {
                    this.locked = locked;
                }
                synchronized (this.lockAppStateChangedItems) {
                    ArrayList<OnLockAppStateChanged> toRemove = new ArrayList<>();
                    if (!ListenerUtil.mutListener.listen(40285)) {
                        {
                            long _loopCounter457 = 0;
                            for (OnLockAppStateChanged c : this.lockAppStateChangedItems) {
                                ListenerUtil.loopListener.listen("_loopCounter457", ++_loopCounter457);
                                if (!ListenerUtil.mutListener.listen(40284)) {
                                    if (c.changed(locked)) {
                                        if (!ListenerUtil.mutListener.listen(40283)) {
                                            toRemove.add(c);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(40286)) {
                        this.lockAppStateChangedItems.removeAll(toRemove);
                    }
                }
                if (!ListenerUtil.mutListener.listen(40287)) {
                    // update widget
                    WidgetUtil.updateWidgets(context);
                }
            }
        }
    }

    @Override
    public boolean isLocked() {
        return this.locked;
    }

    @Override
    public LockAppService resetLockTimer(boolean restartAfterReset) {
        if (!ListenerUtil.mutListener.listen(40291)) {
            if (this.lockTimerIntent != null) {
                if (!ListenerUtil.mutListener.listen(40289)) {
                    this.lockTimeStamp = 0;
                }
                if (!ListenerUtil.mutListener.listen(40290)) {
                    alarmManager.cancel(this.lockTimerIntent);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(40307)) {
            if (restartAfterReset) {
                int time = this.preferencesService.getPinLockGraceTime();
                if (!ListenerUtil.mutListener.listen(40306)) {
                    if ((ListenerUtil.mutListener.listen(40296) ? (time >= 0) : (ListenerUtil.mutListener.listen(40295) ? (time <= 0) : (ListenerUtil.mutListener.listen(40294) ? (time < 0) : (ListenerUtil.mutListener.listen(40293) ? (time != 0) : (ListenerUtil.mutListener.listen(40292) ? (time == 0) : (time > 0))))))) {
                        Intent lockingIntent = new Intent(context, AlarmManagerBroadcastReceiver.class);
                        if (!ListenerUtil.mutListener.listen(40298)) {
                            lockingIntent.putExtra(LifetimeServiceImpl.REQUEST_CODE_KEY, LifetimeServiceImpl.REQUEST_LOCK_APP);
                        }
                        if (!ListenerUtil.mutListener.listen(40299)) {
                            this.lockTimerIntent = PendingIntent.getBroadcast(context, LifetimeServiceImpl.REQUEST_LOCK_APP, lockingIntent, 0);
                        }
                        if (!ListenerUtil.mutListener.listen(40304)) {
                            this.lockTimeStamp = System.currentTimeMillis() + (ListenerUtil.mutListener.listen(40303) ? (time % DateUtils.SECOND_IN_MILLIS) : (ListenerUtil.mutListener.listen(40302) ? (time / DateUtils.SECOND_IN_MILLIS) : (ListenerUtil.mutListener.listen(40301) ? (time - DateUtils.SECOND_IN_MILLIS) : (ListenerUtil.mutListener.listen(40300) ? (time + DateUtils.SECOND_IN_MILLIS) : (time * DateUtils.SECOND_IN_MILLIS)))));
                        }
                        if (!ListenerUtil.mutListener.listen(40305)) {
                            alarmManager.set(AlarmManager.RTC_WAKEUP, this.lockTimeStamp, this.lockTimerIntent);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(40297)) {
                            this.lockTimeStamp = 0;
                        }
                    }
                }
            }
        }
        return this;
    }

    @Override
    public void addOnLockAppStateChanged(OnLockAppStateChanged c) {
        synchronized (this.lockAppStateChangedItems) {
            if (!ListenerUtil.mutListener.listen(40308)) {
                this.lockAppStateChangedItems.add(c);
            }
        }
    }

    @Override
    public void removeOnLockAppStateChanged(OnLockAppStateChanged c) {
        synchronized (this.lockAppStateChangedItems) {
            int index = this.lockAppStateChangedItems.indexOf(c);
            if (!ListenerUtil.mutListener.listen(40315)) {
                if ((ListenerUtil.mutListener.listen(40313) ? (index <= 0) : (ListenerUtil.mutListener.listen(40312) ? (index > 0) : (ListenerUtil.mutListener.listen(40311) ? (index < 0) : (ListenerUtil.mutListener.listen(40310) ? (index != 0) : (ListenerUtil.mutListener.listen(40309) ? (index == 0) : (index >= 0))))))) {
                    if (!ListenerUtil.mutListener.listen(40314)) {
                        this.lockAppStateChangedItems.remove(index);
                    }
                }
            }
        }
    }
}
