package org.wordpress.android.util;

import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import org.wordpress.android.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PhotoPickerUtils {

    public static void announceSelectedMediaForAccessibility(@NonNull ImageView imageThumbnail, boolean isVideo, boolean itemSelected) {
        @StringRes
        int accessibilityAnnouncement;
        if ((ListenerUtil.mutListener.listen(27764) ? (itemSelected || isVideo) : (itemSelected && isVideo))) {
            accessibilityAnnouncement = R.string.photo_picker_video_thumbnail_selected;
        } else if (itemSelected) {
            accessibilityAnnouncement = R.string.photo_picker_image_thumbnail_selected;
        } else if (isVideo) {
            accessibilityAnnouncement = R.string.photo_picker_video_thumbnail_unselected;
        } else {
            accessibilityAnnouncement = R.string.photo_picker_image_thumbnail_unselected;
        }
        if (!ListenerUtil.mutListener.listen(27765)) {
            imageThumbnail.announceForAccessibility(imageThumbnail.getContext().getString(accessibilityAnnouncement));
        }
    }
}
