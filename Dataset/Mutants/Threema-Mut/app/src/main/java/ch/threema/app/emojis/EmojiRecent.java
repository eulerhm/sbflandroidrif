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
package ch.threema.app.emojis;

import java.util.LinkedList;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.services.PreferenceService;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class EmojiRecent {

    private final PreferenceService preferenceService;

    private static final int RECENT_SIZE_LIMIT = 100;

    private static LinkedList<String> recentList = new LinkedList<String>(), recentListNew = new LinkedList<String>();

    private static boolean modified = false;

    public EmojiRecent() {
        this.preferenceService = ThreemaApplication.getServiceManager().getPreferenceService();
        if (!ListenerUtil.mutListener.listen(19703)) {
            readFromPrefs();
        }
    }

    public boolean add(String emojiSequence) {
        synchronized (this.recentListNew) {
            if (!ListenerUtil.mutListener.listen(19705)) {
                if (recentListNew.contains(emojiSequence)) {
                    if (!ListenerUtil.mutListener.listen(19704)) {
                        recentListNew.removeLastOccurrence(emojiSequence);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(19712)) {
                {
                    long _loopCounter146 = 0;
                    // resize list
                    while ((ListenerUtil.mutListener.listen(19711) ? (recentListNew.size() <= RECENT_SIZE_LIMIT) : (ListenerUtil.mutListener.listen(19710) ? (recentListNew.size() > RECENT_SIZE_LIMIT) : (ListenerUtil.mutListener.listen(19709) ? (recentListNew.size() < RECENT_SIZE_LIMIT) : (ListenerUtil.mutListener.listen(19708) ? (recentListNew.size() != RECENT_SIZE_LIMIT) : (ListenerUtil.mutListener.listen(19707) ? (recentListNew.size() == RECENT_SIZE_LIMIT) : (recentListNew.size() >= RECENT_SIZE_LIMIT))))))) {
                        ListenerUtil.loopListener.listen("_loopCounter146", ++_loopCounter146);
                        if (!ListenerUtil.mutListener.listen(19706)) {
                            recentListNew.removeLast();
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(19713)) {
                recentListNew.addFirst(emojiSequence);
            }
            if (!ListenerUtil.mutListener.listen(19714)) {
                modified = true;
            }
        }
        return true;
    }

    public boolean remove(String emojiSequence) {
        synchronized (this.recentListNew) {
            if (!ListenerUtil.mutListener.listen(19718)) {
                if (recentListNew.contains(emojiSequence)) {
                    if (!ListenerUtil.mutListener.listen(19715)) {
                        recentListNew.remove(emojiSequence);
                    }
                    if (!ListenerUtil.mutListener.listen(19716)) {
                        modified = true;
                    }
                    if (!ListenerUtil.mutListener.listen(19717)) {
                        syncRecents();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public LinkedList<String> getRecentList() {
        return recentList;
    }

    public int getNumberOfRecentEmojis() {
        return recentList.size();
    }

    public void saveToPrefs() {
        if (!ListenerUtil.mutListener.listen(19719)) {
            preferenceService.setRecentEmojis2(recentListNew);
        }
        if (!ListenerUtil.mutListener.listen(19720)) {
            syncRecents();
        }
    }

    public void readFromPrefs() {
        if (!ListenerUtil.mutListener.listen(19724)) {
            if (preferenceService != null) {
                if (!ListenerUtil.mutListener.listen(19721)) {
                    recentList = preferenceService.getRecentEmojis2();
                }
                if (!ListenerUtil.mutListener.listen(19723)) {
                    if (recentList != null) {
                        if (!ListenerUtil.mutListener.listen(19722)) {
                            recentListNew = (LinkedList<String>) recentList.clone();
                        }
                    }
                }
            }
        }
    }

    public boolean syncRecents() {
        if (!ListenerUtil.mutListener.listen(19727)) {
            if (modified) {
                if (!ListenerUtil.mutListener.listen(19725)) {
                    recentList = (LinkedList<String>) recentListNew.clone();
                }
                if (!ListenerUtil.mutListener.listen(19726)) {
                    modified = false;
                }
                return true;
            }
        }
        return false;
    }
}
