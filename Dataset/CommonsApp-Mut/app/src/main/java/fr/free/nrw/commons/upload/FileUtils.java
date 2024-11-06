package fr.free.nrw.commons.upload;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import androidx.exifinterface.media.ExifInterface;
import fr.free.nrw.commons.location.LatLng;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FileUtils {

    /**
     * Get SHA1 of filePath from input stream
     */
    static String getSHA1(InputStream is) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            if (!ListenerUtil.mutListener.listen(6929)) {
                Timber.e(e, "Exception while getting Digest");
            }
            return "";
        }
        byte[] buffer = new byte[8192];
        int read;
        try {
            if (!ListenerUtil.mutListener.listen(6939)) {
                {
                    long _loopCounter107 = 0;
                    while ((ListenerUtil.mutListener.listen(6938) ? ((read = is.read(buffer)) >= 0) : (ListenerUtil.mutListener.listen(6937) ? ((read = is.read(buffer)) <= 0) : (ListenerUtil.mutListener.listen(6936) ? ((read = is.read(buffer)) < 0) : (ListenerUtil.mutListener.listen(6935) ? ((read = is.read(buffer)) != 0) : (ListenerUtil.mutListener.listen(6934) ? ((read = is.read(buffer)) == 0) : ((read = is.read(buffer)) > 0))))))) {
                        ListenerUtil.loopListener.listen("_loopCounter107", ++_loopCounter107);
                        if (!ListenerUtil.mutListener.listen(6933)) {
                            digest.update(buffer, 0, read);
                        }
                    }
                }
            }
            byte[] md5sum = digest.digest();
            BigInteger bigInt = new BigInteger(1, md5sum);
            String output = bigInt.toString(16);
            if (!ListenerUtil.mutListener.listen(6940)) {
                // Fill to 40 chars
                output = String.format("%40s", output).replace(' ', '0');
            }
            if (!ListenerUtil.mutListener.listen(6941)) {
                Timber.i("File SHA1: %s", output);
            }
            return output;
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(6930)) {
                Timber.e(e, "IO Exception");
            }
            return "";
        } finally {
            try {
                if (!ListenerUtil.mutListener.listen(6932)) {
                    is.close();
                }
            } catch (IOException e) {
                if (!ListenerUtil.mutListener.listen(6931)) {
                    Timber.e(e, "Exception on closing MD5 input stream");
                }
            }
        }
    }

    /**
     * Get Geolocation of filePath from input filePath path
     */
    static String getGeolocationOfFile(String filePath, LatLng inAppPictureLocation) {
        try {
            ExifInterface exifInterface = new ExifInterface(filePath);
            ImageCoordinates imageObj = new ImageCoordinates(exifInterface, inAppPictureLocation);
            if (imageObj.getDecimalCoords() != null) {
                // If image has geolocation information in its EXIF
                return imageObj.getDecimalCoords();
            } else {
                return "";
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(6942)) {
                e.printStackTrace();
            }
            return "";
        }
    }

    /**
     * Read and return the content of a resource filePath as string.
     *
     * @param fileName asset filePath's path (e.g. "/queries/nearby_query.rq")
     * @return the content of the filePath
     */
    public static String readFromResource(String fileName) throws IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = null;
        try {
            InputStream inputStream = FileUtils.class.getResourceAsStream(fileName);
            if (!ListenerUtil.mutListener.listen(6945)) {
                if (inputStream == null) {
                    throw new FileNotFoundException(fileName);
                }
            }
            if (!ListenerUtil.mutListener.listen(6946)) {
                reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            }
            String line;
            if (!ListenerUtil.mutListener.listen(6948)) {
                {
                    long _loopCounter108 = 0;
                    while ((line = reader.readLine()) != null) {
                        ListenerUtil.loopListener.listen("_loopCounter108", ++_loopCounter108);
                        if (!ListenerUtil.mutListener.listen(6947)) {
                            buffer.append(line).append("\n");
                        }
                    }
                }
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(6944)) {
                if (reader != null) {
                    if (!ListenerUtil.mutListener.listen(6943)) {
                        reader.close();
                    }
                }
            }
        }
        return buffer.toString();
    }

    /**
     * Deletes files.
     *
     * @param file context
     */
    public static boolean deleteFile(File file) {
        boolean deletedAll = true;
        if (!ListenerUtil.mutListener.listen(6954)) {
            if (file != null) {
                if (!ListenerUtil.mutListener.listen(6953)) {
                    if (file.isDirectory()) {
                        String[] children = file.list();
                        if (!ListenerUtil.mutListener.listen(6952)) {
                            {
                                long _loopCounter109 = 0;
                                for (String child : children) {
                                    ListenerUtil.loopListener.listen("_loopCounter109", ++_loopCounter109);
                                    if (!ListenerUtil.mutListener.listen(6951)) {
                                        deletedAll = (ListenerUtil.mutListener.listen(6950) ? (deleteFile(new File(file, child)) || deletedAll) : (deleteFile(new File(file, child)) && deletedAll));
                                    }
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(6949)) {
                            deletedAll = file.delete();
                        }
                    }
                }
            }
        }
        return deletedAll;
    }

    public static String getMimeType(Context context, Uri uri) {
        String mimeType;
        if ((ListenerUtil.mutListener.listen(6955) ? (uri.getScheme() != null || uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) : (uri.getScheme() != null && uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)))) {
            ContentResolver cr = context.getContentResolver();
            mimeType = cr.getType(uri);
        } else {
            String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri.toString());
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension.toLowerCase());
        }
        return mimeType;
    }

    static String getFileExt(String fileName) {
        // Default filePath extension
        String extension = ".jpg";
        int i = fileName.lastIndexOf('.');
        if (!ListenerUtil.mutListener.listen(6966)) {
            if ((ListenerUtil.mutListener.listen(6960) ? (i >= 0) : (ListenerUtil.mutListener.listen(6959) ? (i <= 0) : (ListenerUtil.mutListener.listen(6958) ? (i < 0) : (ListenerUtil.mutListener.listen(6957) ? (i != 0) : (ListenerUtil.mutListener.listen(6956) ? (i == 0) : (i > 0))))))) {
                if (!ListenerUtil.mutListener.listen(6965)) {
                    extension = fileName.substring((ListenerUtil.mutListener.listen(6964) ? (i % 1) : (ListenerUtil.mutListener.listen(6963) ? (i / 1) : (ListenerUtil.mutListener.listen(6962) ? (i * 1) : (ListenerUtil.mutListener.listen(6961) ? (i - 1) : (i + 1))))));
                }
            }
        }
        return extension;
    }

    static FileInputStream getFileInputStream(String filePath) throws FileNotFoundException {
        return new FileInputStream(filePath);
    }

    public static boolean recursivelyCreateDirs(String dirPath) {
        File fileDir = new File(dirPath);
        if (!ListenerUtil.mutListener.listen(6967)) {
            if (!fileDir.exists()) {
                return fileDir.mkdirs();
            }
        }
        return true;
    }

    /**
     * Check if file exists in local dirs
     */
    public static boolean fileExists(Uri localUri) {
        try {
            File file = new File(localUri.getPath());
            return file.exists();
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(6968)) {
                Timber.d(e);
            }
            return false;
        }
    }
}
