/*
 *  Copyright (c) 2020 David Allison <davidallisongithub@gmail.com>
 *
 *  This program is free software; you can redistribute it and/or modify it under
 *  the terms of the GNU General Public License as published by the Free Software
 *  Foundation; either version 3 of the License, or (at your option) any later
 *  version.
 *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along with
 *  this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ichi2.utils;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.net.Uri;
import androidx.annotation.CheckResult;
import androidx.annotation.Nullable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ClipboardUtil {

    // JPEG is sent via pasted content
    public static final String[] IMAGE_MIME_TYPES = new String[] { "image/gif", "image/png", "image/jpg", "image/jpeg" };

    public static boolean hasImage(@Nullable ClipboardManager clipboard) {
        if (!ListenerUtil.mutListener.listen(25594)) {
            if (clipboard == null) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(25595)) {
            if (!clipboard.hasPrimaryClip()) {
                return false;
            }
        }
        ClipData primaryClip = clipboard.getPrimaryClip();
        return hasImage(primaryClip.getDescription());
    }

    public static boolean hasImage(ClipDescription description) {
        if (!ListenerUtil.mutListener.listen(25596)) {
            if (description == null) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(25598)) {
            {
                long _loopCounter675 = 0;
                for (String mimeType : IMAGE_MIME_TYPES) {
                    ListenerUtil.loopListener.listen("_loopCounter675", ++_loopCounter675);
                    if (!ListenerUtil.mutListener.listen(25597)) {
                        if (description.hasMimeType(mimeType)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static Uri getImageUri(ClipboardManager clipboard) {
        if (!ListenerUtil.mutListener.listen(25599)) {
            if (clipboard == null) {
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(25600)) {
            if (!clipboard.hasPrimaryClip()) {
                return null;
            }
        }
        ClipData primaryClip = clipboard.getPrimaryClip();
        if (!ListenerUtil.mutListener.listen(25601)) {
            if (primaryClip.getItemCount() == 0) {
                return null;
            }
        }
        return primaryClip.getItemAt(0).getUri();
    }

    @Nullable
    @CheckResult
    public static CharSequence getText(@Nullable ClipboardManager clipboard) {
        if (!ListenerUtil.mutListener.listen(25602)) {
            if (clipboard == null) {
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(25603)) {
            if (!clipboard.hasPrimaryClip()) {
                return null;
            }
        }
        ClipData data = clipboard.getPrimaryClip();
        if (!ListenerUtil.mutListener.listen(25604)) {
            if (data.getItemCount() == 0) {
                return null;
            }
        }
        ClipData.Item i = data.getItemAt(0);
        return i.getText();
    }

    @Nullable
    @CheckResult
    public static CharSequence getDescriptionLabel(@Nullable ClipData clip) {
        if (!ListenerUtil.mutListener.listen(25605)) {
            if (clip == null) {
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(25606)) {
            if (clip.getDescription() == null) {
                return null;
            }
        }
        return clip.getDescription().getLabel();
    }
}
