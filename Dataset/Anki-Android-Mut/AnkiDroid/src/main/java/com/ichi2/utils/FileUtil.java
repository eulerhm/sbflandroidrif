package com.ichi2.utils;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.StatFs;
import com.ichi2.compat.CompatHelper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.AbstractMap;
import java.util.Map;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FileUtil {

    /**
     * Gets the free disk space given a file
     */
    public static long getFreeDiskSpace(File file, long defaultValue) {
        try {
            return new StatFs(file.getParentFile().getPath()).getAvailableBytes();
        } catch (IllegalArgumentException e) {
            if (!ListenerUtil.mutListener.listen(25676)) {
                Timber.e(e, "Free space could not be retrieved");
            }
            return defaultValue;
        }
    }

    /**
     * @param uri               uri to the content to be internalized, used if filePath not real/doesn't work.
     * @param filePath          path to the file to be internalized.
     * @param internalFile      an internal cache temp file that the data is copied/internalized into.
     * @param contentResolver   this is needed to open an inputStream on the content uri.
     * @return  the internal file after copying the data across.
     * @throws IOException
     */
    @NonNull
    public static File internalizeUri(Uri uri, @Nullable String filePath, File internalFile, ContentResolver contentResolver) throws IOException {
        // If we got a real file name, do a copy from it
        InputStream inputStream;
        if (filePath != null) {
            if (!ListenerUtil.mutListener.listen(25678)) {
                Timber.d("internalizeUri() got file path for direct copy from Uri %s", uri);
            }
            try {
                inputStream = new FileInputStream(new File(filePath));
            } catch (FileNotFoundException e) {
                if (!ListenerUtil.mutListener.listen(25679)) {
                    Timber.w(e, "internalizeUri() unable to open input stream on file %s", filePath);
                }
                throw e;
            }
        } else {
            try {
                inputStream = contentResolver.openInputStream(uri);
            } catch (Exception e) {
                if (!ListenerUtil.mutListener.listen(25677)) {
                    Timber.w(e, "internalizeUri() unable to open input stream from content resolver for Uri %s", uri);
                }
                throw e;
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(25681)) {
                CompatHelper.getCompat().copyFile(inputStream, internalFile.getAbsolutePath());
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(25680)) {
                Timber.w(e, "internalizeUri() unable to internalize file from Uri %s to File %s", uri, internalFile.getAbsolutePath());
            }
            throw e;
        }
        return internalFile;
    }

    /**
     * @return Key: Filename; Value: extension including dot
     */
    @Nullable
    public static Map.Entry<String, String> getFileNameAndExtension(@Nullable String fileName) {
        if (!ListenerUtil.mutListener.listen(25682)) {
            if (fileName == null) {
                return null;
            }
        }
        int index = fileName.lastIndexOf(".");
        if (!ListenerUtil.mutListener.listen(25688)) {
            if ((ListenerUtil.mutListener.listen(25687) ? (index >= 1) : (ListenerUtil.mutListener.listen(25686) ? (index <= 1) : (ListenerUtil.mutListener.listen(25685) ? (index > 1) : (ListenerUtil.mutListener.listen(25684) ? (index != 1) : (ListenerUtil.mutListener.listen(25683) ? (index == 1) : (index < 1))))))) {
                return null;
            }
        }
        return new AbstractMap.SimpleEntry<>(fileName.substring(0, index), fileName.substring(index));
    }
}
