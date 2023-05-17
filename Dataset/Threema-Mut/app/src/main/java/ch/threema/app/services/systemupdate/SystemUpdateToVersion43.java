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
package ch.threema.app.services.systemupdate;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.DeadlineListService;
import ch.threema.app.services.RingtoneService;
import ch.threema.app.services.UpdateSystemService;
import ch.threema.app.stores.PreferenceStore;
import ch.threema.app.utils.TestUtil;
import ch.threema.client.Base32;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * add profile pic field to normal, group and distribution list message models
 */
public class SystemUpdateToVersion43 extends UpdateToVersion implements UpdateSystemService.SystemUpdate {

    private static final Logger logger = LoggerFactory.getLogger(SystemUpdateToVersion43.class);

    private final SQLiteDatabase sqLiteDatabase;

    public SystemUpdateToVersion43(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public boolean runDirectly() {
        return true;
    }

    @Override
    public boolean runASync() {
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(36242)) {
            if (serviceManager == null) {
                if (!ListenerUtil.mutListener.listen(36241)) {
                    logger.error("update script 43 failed, no service manager available");
                }
                return false;
            }
        }
        DeadlineListService mutedChatsService = serviceManager.getMutedChatsListService();
        DeadlineListService hiddenChatsService = serviceManager.getHiddenChatsListService();
        RingtoneService ringtoneService = serviceManager.getRingtoneService();
        if (!ListenerUtil.mutListener.listen(36246)) {
            if ((ListenerUtil.mutListener.listen(36244) ? ((ListenerUtil.mutListener.listen(36243) ? (mutedChatsService == null && hiddenChatsService == null) : (mutedChatsService == null || hiddenChatsService == null)) && ringtoneService == null) : ((ListenerUtil.mutListener.listen(36243) ? (mutedChatsService == null && hiddenChatsService == null) : (mutedChatsService == null || hiddenChatsService == null)) || ringtoneService == null))) {
                if (!ListenerUtil.mutListener.listen(36245)) {
                    logger.error("update script 43 failed, PreferenceService not available");
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(36248)) {
            if (ThreemaApplication.getMasterKey() == null) {
                if (!ListenerUtil.mutListener.listen(36247)) {
                    logger.error("update script 43 failed, No Master key");
                }
                return false;
            }
        }
        String mutedChatsPrefs = "list_muted_chats";
        String hiddenChatsPrefs = "list_hidden_chats";
        String ringtonePrefs = "pref_individual_ringtones";
        String messageDraftPrefs = "pref_message_drafts";
        PreferenceStore preferenceStore = new PreferenceStore(ThreemaApplication.getAppContext(), ThreemaApplication.getMasterKey());
        HashMap<Integer, String> oldMutedChatsMap = preferenceStore.getHashMap(mutedChatsPrefs, false);
        HashMap<Integer, String> oldHiddenChatsMap = preferenceStore.getHashMap(hiddenChatsPrefs, false);
        HashMap<Integer, String> oldRingtoneMap = preferenceStore.getHashMap(ringtonePrefs, false);
        HashMap<Integer, String> oldMessageDraftsMap = preferenceStore.getHashMap(messageDraftPrefs, true);
        if (!ListenerUtil.mutListener.listen(36249)) {
            preferenceStore.remove(messageDraftPrefs);
        }
        HashMap<String, String> newMutedChatsMap = new HashMap<>();
        HashMap<String, String> newHiddenChatsMap = new HashMap<>();
        HashMap<String, String> newRingtoneMap = new HashMap<>();
        Cursor contacts = this.sqLiteDatabase.rawQuery("SELECT identity FROM contacts", null);
        if (!ListenerUtil.mutListener.listen(36261)) {
            if (contacts != null) {
                if (!ListenerUtil.mutListener.listen(36259)) {
                    {
                        long _loopCounter340 = 0;
                        while (contacts.moveToNext()) {
                            ListenerUtil.loopListener.listen("_loopCounter340", ++_loopCounter340);
                            final String identity = contacts.getString(0);
                            if (!ListenerUtil.mutListener.listen(36258)) {
                                if (!TestUtil.empty(identity)) {
                                    String rawUid = "c-" + identity;
                                    int oldUid = (rawUid).hashCode();
                                    if (!ListenerUtil.mutListener.listen(36251)) {
                                        if (oldMutedChatsMap.containsKey(oldUid)) {
                                            if (!ListenerUtil.mutListener.listen(36250)) {
                                                newMutedChatsMap.put(getNewUid(rawUid), oldMutedChatsMap.get(oldUid));
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(36253)) {
                                        if (oldHiddenChatsMap.containsKey(oldUid)) {
                                            if (!ListenerUtil.mutListener.listen(36252)) {
                                                newHiddenChatsMap.put(getNewUid(rawUid), oldHiddenChatsMap.get(oldUid));
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(36255)) {
                                        if (oldRingtoneMap.containsKey(oldUid)) {
                                            if (!ListenerUtil.mutListener.listen(36254)) {
                                                newRingtoneMap.put(getNewUid(rawUid), oldRingtoneMap.get(oldUid));
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(36257)) {
                                        if (oldMessageDraftsMap.containsKey(oldUid)) {
                                            if (!ListenerUtil.mutListener.listen(36256)) {
                                                ThreemaApplication.putMessageDraft(getNewUid(rawUid), oldMessageDraftsMap.get(oldUid));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(36260)) {
                    contacts.close();
                }
            }
        }
        Cursor groups = this.sqLiteDatabase.rawQuery("SELECT id FROM m_group", null);
        if (!ListenerUtil.mutListener.listen(36278)) {
            if (groups != null) {
                if (!ListenerUtil.mutListener.listen(36276)) {
                    {
                        long _loopCounter341 = 0;
                        while (groups.moveToNext()) {
                            ListenerUtil.loopListener.listen("_loopCounter341", ++_loopCounter341);
                            final int id = groups.getInt(0);
                            if (!ListenerUtil.mutListener.listen(36275)) {
                                if ((ListenerUtil.mutListener.listen(36266) ? (id <= 0) : (ListenerUtil.mutListener.listen(36265) ? (id > 0) : (ListenerUtil.mutListener.listen(36264) ? (id < 0) : (ListenerUtil.mutListener.listen(36263) ? (id != 0) : (ListenerUtil.mutListener.listen(36262) ? (id == 0) : (id >= 0))))))) {
                                    String rawUid = "g-" + String.valueOf(id);
                                    int oldUid = (rawUid).hashCode();
                                    if (!ListenerUtil.mutListener.listen(36268)) {
                                        if (oldMutedChatsMap.containsKey(oldUid)) {
                                            if (!ListenerUtil.mutListener.listen(36267)) {
                                                newMutedChatsMap.put(getNewUid(rawUid), oldMutedChatsMap.get(oldUid));
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(36270)) {
                                        if (oldHiddenChatsMap.containsKey(oldUid)) {
                                            if (!ListenerUtil.mutListener.listen(36269)) {
                                                newHiddenChatsMap.put(getNewUid(rawUid), oldHiddenChatsMap.get(oldUid));
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(36272)) {
                                        if (oldRingtoneMap.containsKey(oldUid)) {
                                            if (!ListenerUtil.mutListener.listen(36271)) {
                                                newRingtoneMap.put(getNewUid(rawUid), oldRingtoneMap.get(oldUid));
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(36274)) {
                                        if (oldMessageDraftsMap.containsKey(oldUid)) {
                                            if (!ListenerUtil.mutListener.listen(36273)) {
                                                ThreemaApplication.putMessageDraft(getNewUid(rawUid), oldMessageDraftsMap.get(oldUid));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(36277)) {
                    groups.close();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(36279)) {
            preferenceStore.remove(mutedChatsPrefs);
        }
        if (!ListenerUtil.mutListener.listen(36280)) {
            preferenceStore.saveStringHashMap(mutedChatsPrefs, newMutedChatsMap, false);
        }
        if (!ListenerUtil.mutListener.listen(36281)) {
            mutedChatsService.init();
        }
        if (!ListenerUtil.mutListener.listen(36282)) {
            preferenceStore.remove(hiddenChatsPrefs);
        }
        if (!ListenerUtil.mutListener.listen(36283)) {
            preferenceStore.saveStringHashMap(hiddenChatsPrefs, newHiddenChatsMap, false);
        }
        if (!ListenerUtil.mutListener.listen(36284)) {
            hiddenChatsService.init();
        }
        if (!ListenerUtil.mutListener.listen(36285)) {
            preferenceStore.remove(ringtonePrefs);
        }
        if (!ListenerUtil.mutListener.listen(36286)) {
            preferenceStore.saveStringHashMap(ringtonePrefs, newRingtoneMap, false);
        }
        if (!ListenerUtil.mutListener.listen(36287)) {
            ringtoneService.init();
        }
        return true;
    }

    private String getNewUid(String rawUid) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            if (!ListenerUtil.mutListener.listen(36288)) {
                messageDigest.update((rawUid).getBytes());
            }
            return Base32.encode(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    @Override
    public String getText() {
        return "version 43";
    }
}
