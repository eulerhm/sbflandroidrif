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
package ch.threema.app.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import androidx.annotation.Nullable;
import ch.threema.app.BuildConfig;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class RingtoneUtil {

    public static final Uri THREEMA_CALL_RINGTONE_URI = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.raw.threema_call);

    public static final String THREEMA_CALL_RINGTONE_TITLE = "Threema Call";

    public static String getRingtoneNameFromUri(Context context, @Nullable Uri uri) {
        if (!ListenerUtil.mutListener.listen(55380)) {
            if (uri != null) {
                if (!ListenerUtil.mutListener.listen(55378)) {
                    if (uri.equals(THREEMA_CALL_RINGTONE_URI)) {
                        return THREEMA_CALL_RINGTONE_TITLE;
                    }
                }
                Ringtone ringtone = RingtoneManager.getRingtone(context, uri);
                if (!ListenerUtil.mutListener.listen(55379)) {
                    if (ringtone != null) {
                        try {
                            return ringtone.getTitle(context);
                        } catch (SecurityException | IllegalArgumentException e) {
                            return context.getString(R.string.no_filename);
                        }
                    }
                }
            }
        }
        return context.getString(R.string.ringtone_none);
    }

    public static Intent getRingtonePickerIntent(int type, Uri currentUri, Uri defaultUri) throws ActivityNotFoundException {
        if (!ListenerUtil.mutListener.listen(55387)) {
            // on an ACTION_RINGTONE_PICKER intent thus confusing the user. Let's use our own implementation in these cases
            if ((ListenerUtil.mutListener.listen(55386) ? ((ListenerUtil.mutListener.listen(55385) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(55384) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(55383) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(55382) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(55381) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q)))))) && !Build.MANUFACTURER.equalsIgnoreCase("Google")) : ((ListenerUtil.mutListener.listen(55385) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(55384) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(55383) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(55382) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(55381) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q)))))) || !Build.MANUFACTURER.equalsIgnoreCase("Google")))) {
                throw new ActivityNotFoundException();
            }
        }
        Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
        if (!ListenerUtil.mutListener.listen(55388)) {
            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, true);
        }
        if (!ListenerUtil.mutListener.listen(55389)) {
            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true);
        }
        if (!ListenerUtil.mutListener.listen(55390)) {
            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, type);
        }
        if (!ListenerUtil.mutListener.listen(55391)) {
            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, currentUri);
        }
        if (!ListenerUtil.mutListener.listen(55392)) {
            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_DEFAULT_URI, defaultUri);
        }
        return intent;
    }
}
