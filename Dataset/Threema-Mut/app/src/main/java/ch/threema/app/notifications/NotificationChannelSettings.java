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
package ch.threema.app.notifications;

import android.content.SharedPreferences;
import android.net.Uri;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import androidx.annotation.NonNull;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.RingtoneUtil;
import ch.threema.client.Base32;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NotificationChannelSettings {

    private String prefix;

    private long seqNum;

    private int importance;

    private boolean showBadge;

    private long[] vibrationPattern;

    private Integer lightColor;

    private Uri sound;

    private String channelGroupId;

    private String groupName;

    private String description;

    private String seqPrefKey;

    private int visibility;

    public NotificationChannelSettings(String channelGroupId, @NonNull String prefix, SharedPreferences sharedPreferences, int importance, boolean showBadge, int visibility, String groupName, String description, String seqPrefKey) {
        if (!ListenerUtil.mutListener.listen(31684)) {
            this.prefix = prefix;
        }
        if (!ListenerUtil.mutListener.listen(31685)) {
            this.importance = importance;
        }
        if (!ListenerUtil.mutListener.listen(31686)) {
            this.showBadge = showBadge;
        }
        if (!ListenerUtil.mutListener.listen(31687)) {
            this.visibility = visibility;
        }
        if (!ListenerUtil.mutListener.listen(31688)) {
            this.channelGroupId = channelGroupId;
        }
        if (!ListenerUtil.mutListener.listen(31689)) {
            this.groupName = groupName;
        }
        if (!ListenerUtil.mutListener.listen(31690)) {
            this.description = description;
        }
        if (!ListenerUtil.mutListener.listen(31691)) {
            this.seqPrefKey = seqPrefKey;
        }
        if (!ListenerUtil.mutListener.listen(31692)) {
            this.seqNum = sharedPreferences.getLong(seqPrefKey, System.currentTimeMillis());
        }
    }

    public String getDescription() {
        if (!ListenerUtil.mutListener.listen(31698)) {
            if ((ListenerUtil.mutListener.listen(31697) ? (ConfigUtils.getMIUIVersion() <= 10) : (ListenerUtil.mutListener.listen(31696) ? (ConfigUtils.getMIUIVersion() > 10) : (ListenerUtil.mutListener.listen(31695) ? (ConfigUtils.getMIUIVersion() < 10) : (ListenerUtil.mutListener.listen(31694) ? (ConfigUtils.getMIUIVersion() != 10) : (ListenerUtil.mutListener.listen(31693) ? (ConfigUtils.getMIUIVersion() == 10) : (ConfigUtils.getMIUIVersion() >= 10))))))) {
                return toString();
            }
        }
        return description;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getChannelGroupId() {
        return channelGroupId;
    }

    public String getSeqPrefKey() {
        return seqPrefKey;
    }

    public long getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(long seqNum) {
        if (!ListenerUtil.mutListener.listen(31699)) {
            this.seqNum = seqNum;
        }
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(@NonNull String prefix) {
        if (!ListenerUtil.mutListener.listen(31700)) {
            this.prefix = prefix;
        }
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        if (!ListenerUtil.mutListener.listen(31701)) {
            this.importance = importance;
        }
    }

    public boolean isShowBadge() {
        return showBadge;
    }

    public void setShowBadge(boolean showBadge) {
        if (!ListenerUtil.mutListener.listen(31702)) {
            this.showBadge = showBadge;
        }
    }

    public long[] getVibrationPattern() {
        return vibrationPattern;
    }

    public void setVibrationPattern(long[] vibratePattern) {
        if (!ListenerUtil.mutListener.listen(31703)) {
            this.vibrationPattern = vibratePattern;
        }
    }

    public Integer getLightColor() {
        return lightColor;
    }

    public void setLightColor(Integer lightColor) {
        if (!ListenerUtil.mutListener.listen(31704)) {
            this.lightColor = lightColor;
        }
    }

    public Uri getSound() {
        return sound;
    }

    public void setSound(Uri ringtoneUri) {
        if (!ListenerUtil.mutListener.listen(31705)) {
            this.sound = ringtoneUri;
        }
    }

    public int getVisibility() {
        return visibility;
    }

    /**
     *  Get a unique hash of all the settings to be used as the notification channel id
     *  @return hash
     */
    public String hash() {
        String result = prefix + Long.toString(seqNum) + Integer.toString(importance) + Boolean.toString(showBadge) + Arrays.toString(vibrationPattern) + (lightColor != null ? Integer.toString(lightColor) : "0") + (sound != null ? sound.toString() : "null");
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            if (!ListenerUtil.mutListener.listen(31706)) {
                messageDigest.update(result.getBytes());
            }
            return Base32.encode(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
        }
        return prefix + String.valueOf(System.currentTimeMillis());
    }

    @NonNull
    @Override
    public String toString() {
        String lightColorString = "No light";
        if (!ListenerUtil.mutListener.listen(31715)) {
            if (lightColor != null) {
                int[] colorHexValues = ThreemaApplication.getAppContext().getResources().getIntArray(R.array.list_light_color_hex);
                if (!ListenerUtil.mutListener.listen(31714)) {
                    {
                        long _loopCounter216 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(31713) ? (i >= colorHexValues.length) : (ListenerUtil.mutListener.listen(31712) ? (i <= colorHexValues.length) : (ListenerUtil.mutListener.listen(31711) ? (i > colorHexValues.length) : (ListenerUtil.mutListener.listen(31710) ? (i != colorHexValues.length) : (ListenerUtil.mutListener.listen(31709) ? (i == colorHexValues.length) : (i < colorHexValues.length)))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter216", ++_loopCounter216);
                            if (!ListenerUtil.mutListener.listen(31708)) {
                                if (lightColor.equals(colorHexValues[i])) {
                                    if (!ListenerUtil.mutListener.listen(31707)) {
                                        lightColorString = ThreemaApplication.getAppContext().getResources().getStringArray(R.array.list_light_color)[i];
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return sound == null ? "Silent" : RingtoneUtil.getRingtoneNameFromUri(ThreemaApplication.getAppContext(), sound) + ": " + (vibrationPattern != null ? "Vibrate" : "No vibrate") + ": " + lightColorString + ": Importance " + importance;
    }
}
