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
package ch.threema.app.webclient.services;

import android.content.Context;
import android.os.PowerManager;
import android.text.format.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import ch.threema.app.BuildConfig;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.storage.models.WebClientSessionModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WakeLockServiceImpl implements WakeLockService {

    private static final Logger logger = LoggerFactory.getLogger(WakeLockServiceImpl.class);

    private static final String WAKELOCK_TAG = BuildConfig.APPLICATION_ID + ":webClientWakeLock";

    private final Context context;

    /**
     *  all acquired webclient session
     */
    private final List<Integer> acquiredSessionIds = new ArrayList<>();

    /**
     *  the webclient wakelock
     */
    private PowerManager.WakeLock wakeLock;

    public WakeLockServiceImpl(Context context) {
        this.context = context;
    }

    @Override
    public boolean acquire(WebClientSessionModel session) {
        if (!ListenerUtil.mutListener.listen(64727)) {
            logger.debug("acquire webclient wakelock for session " + session.getId());
        }
        if (!ListenerUtil.mutListener.listen(64729)) {
            if (!this.acquiredSessionIds.contains(session.getId())) {
                if (!ListenerUtil.mutListener.listen(64728)) {
                    this.acquiredSessionIds.add(session.getId());
                }
            }
        }
        return this.execute();
    }

    @Override
    public boolean release(WebClientSessionModel session) {
        if (!ListenerUtil.mutListener.listen(64730)) {
            logger.debug("release webclient wakelock for session " + session.getId());
        }
        if (!ListenerUtil.mutListener.listen(64732)) {
            if (this.acquiredSessionIds.contains(session.getId())) {
                if (!ListenerUtil.mutListener.listen(64731)) {
                    this.acquiredSessionIds.remove((Integer) session.getId());
                }
            }
        }
        return this.execute();
    }

    @Override
    public boolean releaseAll() {
        if (!ListenerUtil.mutListener.listen(64733)) {
            this.acquiredSessionIds.clear();
        }
        return this.execute();
    }

    @Override
    public boolean isHeld() {
        return (ListenerUtil.mutListener.listen(64734) ? (this.wakeLock != null || this.wakeLock.isHeld()) : (this.wakeLock != null && this.wakeLock.isHeld()));
    }

    private boolean execute() {
        if ((ListenerUtil.mutListener.listen(64739) ? (this.acquiredSessionIds.size() >= 0) : (ListenerUtil.mutListener.listen(64738) ? (this.acquiredSessionIds.size() <= 0) : (ListenerUtil.mutListener.listen(64737) ? (this.acquiredSessionIds.size() < 0) : (ListenerUtil.mutListener.listen(64736) ? (this.acquiredSessionIds.size() != 0) : (ListenerUtil.mutListener.listen(64735) ? (this.acquiredSessionIds.size() == 0) : (this.acquiredSessionIds.size() > 0))))))) {
            if (!ListenerUtil.mutListener.listen(64748)) {
                if (this.wakeLock == null) {
                    if (!ListenerUtil.mutListener.listen(64746)) {
                        logger.debug("create new wakelock");
                    }
                    PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
                    if (!ListenerUtil.mutListener.listen(64747)) {
                        this.wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, WAKELOCK_TAG);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(64758)) {
                if (!this.wakeLock.isHeld()) {
                    if (!ListenerUtil.mutListener.listen(64756)) {
                        if (ConfigUtils.isNokiaDevice()) {
                            if (!ListenerUtil.mutListener.listen(64755)) {
                                // do not hold wake lock for longer than 15 minutes to prevent evenwell power "saver" from killing the app
                                this.wakeLock.acquire((ListenerUtil.mutListener.listen(64754) ? (15 % DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(64753) ? (15 / DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(64752) ? (15 - DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(64751) ? (15 + DateUtils.MINUTE_IN_MILLIS) : (15 * DateUtils.MINUTE_IN_MILLIS))))));
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(64750)) {
                                this.wakeLock.acquire();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(64757)) {
                        logger.debug("acquired");
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(64749)) {
                        logger.debug("already acquired");
                    }
                }
            }
            return true;
        } else {
            if (!ListenerUtil.mutListener.listen(64745)) {
                if ((ListenerUtil.mutListener.listen(64740) ? (this.wakeLock != null || this.wakeLock.isHeld()) : (this.wakeLock != null && this.wakeLock.isHeld()))) {
                    if (!ListenerUtil.mutListener.listen(64742)) {
                        this.wakeLock.release();
                    }
                    if (!ListenerUtil.mutListener.listen(64743)) {
                        // to be sure, remove the wakelock
                        this.wakeLock = null;
                    }
                    if (!ListenerUtil.mutListener.listen(64744)) {
                        logger.debug("released");
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(64741)) {
                        logger.debug("already released");
                    }
                }
            }
            return false;
        }
    }
}
