package fr.free.nrw.commons.upload;

import android.content.Context;
import fr.free.nrw.commons.location.LatLng;
import io.reactivex.Observable;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@Singleton
public class FileUtilsWrapper {

    @Inject
    public FileUtilsWrapper() {
    }

    public String getFileExt(String fileName) {
        return FileUtils.getFileExt(fileName);
    }

    public String getSHA1(InputStream is) {
        return FileUtils.getSHA1(is);
    }

    public FileInputStream getFileInputStream(String filePath) throws FileNotFoundException {
        return FileUtils.getFileInputStream(filePath);
    }

    public String getGeolocationOfFile(String filePath, LatLng inAppPictureLocation) {
        return FileUtils.getGeolocationOfFile(filePath, inAppPictureLocation);
    }

    /**
     * Takes a file as input and returns an Observable of files with the specified chunk size
     */
    public List<File> getFileChunks(Context context, File file, final int chunkSize) throws IOException {
        final byte[] buffer = new byte[chunkSize];
        // try-with-resources to ensure closing stream
        try (final FileInputStream fis = new FileInputStream(file);
            final BufferedInputStream bis = new BufferedInputStream(fis)) {
            final List<File> buffers = new ArrayList<>();
            int size;
            if (!ListenerUtil.mutListener.listen(6879)) {
                {
                    long _loopCounter105 = 0;
                    while ((ListenerUtil.mutListener.listen(6878) ? ((size = bis.read(buffer)) >= 0) : (ListenerUtil.mutListener.listen(6877) ? ((size = bis.read(buffer)) <= 0) : (ListenerUtil.mutListener.listen(6876) ? ((size = bis.read(buffer)) < 0) : (ListenerUtil.mutListener.listen(6875) ? ((size = bis.read(buffer)) != 0) : (ListenerUtil.mutListener.listen(6874) ? ((size = bis.read(buffer)) == 0) : ((size = bis.read(buffer)) > 0))))))) {
                        ListenerUtil.loopListener.listen("_loopCounter105", ++_loopCounter105);
                        if (!ListenerUtil.mutListener.listen(6873)) {
                            buffers.add(writeToFile(context, Arrays.copyOf(buffer, size), file.getName(), getFileExt(file.getName())));
                        }
                    }
                }
            }
            return buffers;
        }
    }

    /**
     * Create a temp file containing the passed byte data.
     */
    private File writeToFile(Context context, final byte[] data, final String fileName, String fileExtension) throws IOException {
        final File file = File.createTempFile(fileName, fileExtension, context.getCacheDir());
        try {
            if (!ListenerUtil.mutListener.listen(6882)) {
                if (!file.exists()) {
                    if (!ListenerUtil.mutListener.listen(6881)) {
                        file.createNewFile();
                    }
                }
            }
            final FileOutputStream fos = new FileOutputStream(file);
            if (!ListenerUtil.mutListener.listen(6883)) {
                fos.write(data);
            }
            if (!ListenerUtil.mutListener.listen(6884)) {
                fos.close();
            }
        } catch (final Exception throwable) {
            if (!ListenerUtil.mutListener.listen(6880)) {
                Timber.e(throwable, "Failed to create file");
            }
        }
        return file;
    }
}
