package fr.free.nrw.commons.filepicker;

import android.content.Context;
import androidx.preference.PreferenceManager;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FilePickerConfiguration implements Constants {

    private Context context;

    FilePickerConfiguration(Context context) {
        if (!ListenerUtil.mutListener.listen(6193)) {
            this.context = context;
        }
    }

    public FilePickerConfiguration setAllowMultiplePickInGallery(boolean allowMultiple) {
        if (!ListenerUtil.mutListener.listen(6194)) {
            PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(BundleKeys.ALLOW_MULTIPLE, allowMultiple).apply();
        }
        return this;
    }

    public FilePickerConfiguration setCopyTakenPhotosToPublicGalleryAppFolder(boolean copy) {
        if (!ListenerUtil.mutListener.listen(6195)) {
            PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(BundleKeys.COPY_TAKEN_PHOTOS, copy).apply();
        }
        return this;
    }

    public String getFolderName() {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(BundleKeys.FOLDER_NAME, DEFAULT_FOLDER_NAME);
    }

    public boolean allowsMultiplePickingInGallery() {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(BundleKeys.ALLOW_MULTIPLE, false);
    }

    public boolean shouldCopyTakenPhotosToPublicGalleryAppFolder() {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(BundleKeys.COPY_TAKEN_PHOTOS, false);
    }

    public boolean shouldCopyPickedImagesToPublicGalleryAppFolder() {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(BundleKeys.COPY_PICKED_IMAGES, false);
    }
}
