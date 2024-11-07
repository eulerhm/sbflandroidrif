/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2015-2021 Threema GmbH
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import ch.threema.app.utils.LogUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DeadlineListServiceImpl implements DeadlineListService {

    private static final Logger logger = LoggerFactory.getLogger(DeadlineListServiceImpl.class);

    private final Object lock = new Object();

    private HashMap<String, String> hashMap;

    private final String uniqueListName;

    private final PreferenceService preferenceService;

    public DeadlineListServiceImpl(String uniqueListName, PreferenceService preferenceService) {
        this.uniqueListName = uniqueListName;
        this.preferenceService = preferenceService;
        if (!ListenerUtil.mutListener.listen(37491)) {
            init();
        }
    }

    @Override
    public void init() {
        if (!ListenerUtil.mutListener.listen(37492)) {
            this.hashMap = preferenceService.getStringHashMap(this.uniqueListName, false);
        }
    }

    @Override
    public boolean has(String uid) {
        if (!ListenerUtil.mutListener.listen(37505)) {
            if ((ListenerUtil.mutListener.listen(37493) ? (this.hashMap != null || uid != null) : (this.hashMap != null && uid != null))) {
                synchronized (this.lock) {
                    if (!ListenerUtil.mutListener.listen(37504)) {
                        if (this.hashMap.containsKey(uid)) {
                            long deadlineTime = 0;
                            try {
                                if (!ListenerUtil.mutListener.listen(37495)) {
                                    deadlineTime = Long.parseLong(this.hashMap.get(uid));
                                }
                            } catch (NumberFormatException e) {
                                if (!ListenerUtil.mutListener.listen(37494)) {
                                    logger.error("Exception", e);
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(37503)) {
                                if ((ListenerUtil.mutListener.listen(37501) ? (deadlineTime == DEADLINE_INDEFINITE && (ListenerUtil.mutListener.listen(37500) ? (System.currentTimeMillis() >= deadlineTime) : (ListenerUtil.mutListener.listen(37499) ? (System.currentTimeMillis() <= deadlineTime) : (ListenerUtil.mutListener.listen(37498) ? (System.currentTimeMillis() > deadlineTime) : (ListenerUtil.mutListener.listen(37497) ? (System.currentTimeMillis() != deadlineTime) : (ListenerUtil.mutListener.listen(37496) ? (System.currentTimeMillis() == deadlineTime) : (System.currentTimeMillis() < deadlineTime))))))) : (deadlineTime == DEADLINE_INDEFINITE || (ListenerUtil.mutListener.listen(37500) ? (System.currentTimeMillis() >= deadlineTime) : (ListenerUtil.mutListener.listen(37499) ? (System.currentTimeMillis() <= deadlineTime) : (ListenerUtil.mutListener.listen(37498) ? (System.currentTimeMillis() > deadlineTime) : (ListenerUtil.mutListener.listen(37497) ? (System.currentTimeMillis() != deadlineTime) : (ListenerUtil.mutListener.listen(37496) ? (System.currentTimeMillis() == deadlineTime) : (System.currentTimeMillis() < deadlineTime))))))))) {
                                    return true;
                                } else {
                                    if (!ListenerUtil.mutListener.listen(37502)) {
                                        this.remove(uid);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void remove(String uid) {
        if (!ListenerUtil.mutListener.listen(37510)) {
            if ((ListenerUtil.mutListener.listen(37506) ? (this.hashMap != null || uid != null) : (this.hashMap != null && uid != null))) {
                synchronized (this.lock) {
                    if (!ListenerUtil.mutListener.listen(37509)) {
                        if (this.hashMap.containsKey(uid)) {
                            if (!ListenerUtil.mutListener.listen(37507)) {
                                this.hashMap.remove(uid);
                            }
                            if (!ListenerUtil.mutListener.listen(37508)) {
                                this.preferenceService.setStringHashMap(this.uniqueListName, this.hashMap);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public long getDeadline(String uid) {
        if (!ListenerUtil.mutListener.listen(37513)) {
            if ((ListenerUtil.mutListener.listen(37511) ? (this.hashMap != null || uid != null) : (this.hashMap != null && uid != null))) {
                synchronized (this.lock) {
                    if (!ListenerUtil.mutListener.listen(37512)) {
                        if (this.hashMap.containsKey(uid)) {
                            return Long.parseLong(this.hashMap.get(uid));
                        }
                    }
                }
            }
        }
        return 0;
    }

    @Override
    public int getSize() {
        if (!ListenerUtil.mutListener.listen(37514)) {
            if (this.hashMap != null) {
                return this.hashMap.size();
            }
        }
        return 0;
    }

    @Override
    public void clear() {
        if (!ListenerUtil.mutListener.listen(37517)) {
            if (this.hashMap != null) {
                if (!ListenerUtil.mutListener.listen(37515)) {
                    this.hashMap.clear();
                }
                if (!ListenerUtil.mutListener.listen(37516)) {
                    this.preferenceService.setStringHashMap(this.uniqueListName, this.hashMap);
                }
            }
        }
    }

    @Override
    public void add(String uid, long timeout) {
        if (!ListenerUtil.mutListener.listen(37521)) {
            if ((ListenerUtil.mutListener.listen(37518) ? (this.hashMap != null || uid != null) : (this.hashMap != null && uid != null))) {
                synchronized (this.lock) {
                    if (!ListenerUtil.mutListener.listen(37519)) {
                        this.hashMap.put(uid, String.valueOf(timeout));
                    }
                    if (!ListenerUtil.mutListener.listen(37520)) {
                        this.preferenceService.setStringHashMap(this.uniqueListName, this.hashMap);
                    }
                }
            }
        }
    }
}
