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

import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import androidx.core.app.NotificationCompat;
import java.util.HashMap;
import ch.threema.app.R;
import ch.threema.app.utils.RingtoneUtil;
import ch.threema.app.utils.TestUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class RingtoneServiceImpl implements RingtoneService {

    private PreferenceService preferenceService;

    private HashMap<String, String> ringtones;

    public RingtoneServiceImpl(PreferenceService preferenceService) {
        if (!ListenerUtil.mutListener.listen(40777)) {
            this.preferenceService = preferenceService;
        }
        if (!ListenerUtil.mutListener.listen(40778)) {
            init();
        }
    }

    @Override
    public void init() {
        if (!ListenerUtil.mutListener.listen(40779)) {
            ringtones = preferenceService.getRingtones();
        }
    }

    @Override
    public void setRingtone(String uniqueId, Uri ringtoneUri) {
        String ringtone = null;
        if (!ListenerUtil.mutListener.listen(40781)) {
            if (ringtoneUri != null) {
                if (!ListenerUtil.mutListener.listen(40780)) {
                    ringtone = ringtoneUri.toString();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(40786)) {
            if ((ListenerUtil.mutListener.listen(40782) ? (ringtoneUri != null || RingtoneManager.isDefault(ringtoneUri)) : (ringtoneUri != null && RingtoneManager.isDefault(ringtoneUri)))) {
                if (!ListenerUtil.mutListener.listen(40785)) {
                    if (ringtones.containsKey(uniqueId)) {
                        if (!ListenerUtil.mutListener.listen(40784)) {
                            ringtones.remove(uniqueId);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(40783)) {
                    ringtones.put(uniqueId, ringtone);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(40787)) {
            preferenceService.setRingtones(ringtones);
        }
    }

    @Override
    public Uri getRingtoneFromUniqueId(String uniqueId) {
        String ringtone = ringtones.get(uniqueId);
        // check for "null" string (HTC bug)
        if ((ListenerUtil.mutListener.listen(40788) ? (ringtone != null || !ringtone.equals("null")) : (ringtone != null && !ringtone.equals("null")))) {
            return Uri.parse(ringtone);
        } else {
            // silent
            return null;
        }
    }

    @Override
    public boolean hasCustomRingtone(String uniqueId) {
        return (ListenerUtil.mutListener.listen(40789) ? (ringtones != null || ringtones.containsKey(uniqueId)) : (ringtones != null && ringtones.containsKey(uniqueId)));
    }

    @Override
    public void removeCustomRingtone(String uniqueId) {
        if (!ListenerUtil.mutListener.listen(40793)) {
            if ((ListenerUtil.mutListener.listen(40790) ? (ringtones != null || ringtones.containsKey(uniqueId)) : (ringtones != null && ringtones.containsKey(uniqueId)))) {
                if (!ListenerUtil.mutListener.listen(40791)) {
                    ringtones.remove(uniqueId);
                }
                if (!ListenerUtil.mutListener.listen(40792)) {
                    preferenceService.setRingtones(ringtones);
                }
            }
        }
    }

    @Override
    public void resetRingtones(Context context) {
        if (!ListenerUtil.mutListener.listen(40796)) {
            if (ringtones != null) {
                if (!ListenerUtil.mutListener.listen(40794)) {
                    ringtones.clear();
                }
                if (!ListenerUtil.mutListener.listen(40795)) {
                    preferenceService.setRingtones(ringtones);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(40797)) {
            preferenceService.setGroupNotificationSound(Uri.parse(context.getString(R.string.default_notification_sound)));
        }
        if (!ListenerUtil.mutListener.listen(40798)) {
            preferenceService.setNotificationSound(Uri.parse(context.getString(R.string.default_notification_sound)));
        }
        if (!ListenerUtil.mutListener.listen(40799)) {
            preferenceService.setVoiceCallSound(RingtoneUtil.THREEMA_CALL_RINGTONE_URI);
        }
        if (!ListenerUtil.mutListener.listen(40800)) {
            preferenceService.setNotificationPriority(NotificationCompat.PRIORITY_HIGH);
        }
    }

    @Override
    public Uri getContactRingtone(String uniqueId) {
        if (ringtones.containsKey(uniqueId)) {
            return getRingtoneFromUniqueId(uniqueId);
        } else {
            return preferenceService.getNotificationSound();
        }
    }

    @Override
    public Uri getGroupRingtone(String uniqueId) {
        if (ringtones.containsKey(uniqueId)) {
            return getRingtoneFromUniqueId(uniqueId);
        } else {
            return preferenceService.getGroupNotificationSound();
        }
    }

    @Override
    public Uri getVoiceCallRingtone(String uniqueId) {
        return preferenceService.getVoiceCallSound();
    }

    @Override
    public Uri getDefaultContactRingtone() {
        return preferenceService.getNotificationSound();
    }

    @Override
    public Uri getDefaultGroupRingtone() {
        return preferenceService.getGroupNotificationSound();
    }

    private boolean hasNoRingtone(String uniqueId) {
        Uri ringtone = getRingtoneFromUniqueId(uniqueId);
        return ((ListenerUtil.mutListener.listen(40802) ? ((ListenerUtil.mutListener.listen(40801) ? (ringtone == null && ringtone.toString() == null) : (ringtone == null || ringtone.toString() == null)) && ringtone.toString().equals("null")) : ((ListenerUtil.mutListener.listen(40801) ? (ringtone == null && ringtone.toString() == null) : (ringtone == null || ringtone.toString() == null)) || ringtone.toString().equals("null"))));
    }

    @Override
    public boolean isSilent(String uniqueId, boolean isGroup) {
        if (!ListenerUtil.mutListener.listen(40806)) {
            if (!TestUtil.empty(uniqueId)) {
                Uri defaultRingtone, selectedRingtone;
                if (isGroup) {
                    defaultRingtone = getDefaultGroupRingtone();
                    selectedRingtone = getGroupRingtone(uniqueId);
                } else {
                    defaultRingtone = getDefaultContactRingtone();
                    selectedRingtone = getContactRingtone(uniqueId);
                }
                return (ListenerUtil.mutListener.listen(40805) ? (!((ListenerUtil.mutListener.listen(40804) ? ((ListenerUtil.mutListener.listen(40803) ? (defaultRingtone != null || selectedRingtone != null) : (defaultRingtone != null && selectedRingtone != null)) || defaultRingtone.equals(selectedRingtone)) : ((ListenerUtil.mutListener.listen(40803) ? (defaultRingtone != null || selectedRingtone != null) : (defaultRingtone != null && selectedRingtone != null)) && defaultRingtone.equals(selectedRingtone)))) || hasNoRingtone(uniqueId)) : (!((ListenerUtil.mutListener.listen(40804) ? ((ListenerUtil.mutListener.listen(40803) ? (defaultRingtone != null || selectedRingtone != null) : (defaultRingtone != null && selectedRingtone != null)) || defaultRingtone.equals(selectedRingtone)) : ((ListenerUtil.mutListener.listen(40803) ? (defaultRingtone != null || selectedRingtone != null) : (defaultRingtone != null && selectedRingtone != null)) && defaultRingtone.equals(selectedRingtone)))) && hasNoRingtone(uniqueId)));
            }
        }
        return false;
    }
}
