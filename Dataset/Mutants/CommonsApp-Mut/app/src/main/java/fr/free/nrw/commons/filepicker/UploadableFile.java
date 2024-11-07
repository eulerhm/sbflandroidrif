package fr.free.nrw.commons.filepicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.exifinterface.media.ExifInterface;
import fr.free.nrw.commons.upload.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class UploadableFile implements Parcelable {

    public static final Creator<UploadableFile> CREATOR = new Creator<UploadableFile>() {

        @Override
        public UploadableFile createFromParcel(Parcel in) {
            return new UploadableFile(in);
        }

        @Override
        public UploadableFile[] newArray(int size) {
            return new UploadableFile[size];
        }
    };

    private final Uri contentUri;

    private final File file;

    public UploadableFile(Uri contentUri, File file) {
        this.contentUri = contentUri;
        this.file = file;
    }

    public UploadableFile(File file) {
        this.file = file;
        this.contentUri = Uri.fromFile(new File(file.getPath()));
    }

    public UploadableFile(Parcel in) {
        this.contentUri = in.readParcelable(Uri.class.getClassLoader());
        file = (File) in.readSerializable();
    }

    public Uri getContentUri() {
        return contentUri;
    }

    public File getFile() {
        return file;
    }

    public String getFilePath() {
        return file.getPath();
    }

    public Uri getMediaUri() {
        return Uri.parse(getFilePath());
    }

    public String getMimeType(Context context) {
        return FileUtils.getMimeType(context, getMediaUri());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * First try to get the file creation date from EXIF else fall back to CP
     * @param context
     * @return
     */
    @Nullable
    public DateTimeWithSource getFileCreatedDate(Context context) {
        DateTimeWithSource dateTimeFromExif = getDateTimeFromExif();
        if (dateTimeFromExif == null) {
            return getFileCreatedDateFromCP(context);
        } else {
            return dateTimeFromExif;
        }
    }

    /**
     * Get filePath creation date from uri from all possible content providers
     *
     * @return
     */
    private DateTimeWithSource getFileCreatedDateFromCP(Context context) {
        try {
            Cursor cursor = context.getContentResolver().query(contentUri, null, null, null, null);
            if (cursor == null) {
                // Could not fetch last_modified
                return null;
            }
            // If gallery is opened from in app
            int lastModifiedColumnIndex = cursor.getColumnIndex("last_modified");
            if (!ListenerUtil.mutListener.listen(6224)) {
                if ((ListenerUtil.mutListener.listen(6222) ? (lastModifiedColumnIndex >= -1) : (ListenerUtil.mutListener.listen(6221) ? (lastModifiedColumnIndex <= -1) : (ListenerUtil.mutListener.listen(6220) ? (lastModifiedColumnIndex > -1) : (ListenerUtil.mutListener.listen(6219) ? (lastModifiedColumnIndex < -1) : (ListenerUtil.mutListener.listen(6218) ? (lastModifiedColumnIndex != -1) : (lastModifiedColumnIndex == -1))))))) {
                    if (!ListenerUtil.mutListener.listen(6223)) {
                        lastModifiedColumnIndex = cursor.getColumnIndex("datetaken");
                    }
                }
            }
            // If both the content providers do not give the data, lets leave it to Jesus
            if ((ListenerUtil.mutListener.listen(6229) ? (lastModifiedColumnIndex >= -1) : (ListenerUtil.mutListener.listen(6228) ? (lastModifiedColumnIndex <= -1) : (ListenerUtil.mutListener.listen(6227) ? (lastModifiedColumnIndex > -1) : (ListenerUtil.mutListener.listen(6226) ? (lastModifiedColumnIndex < -1) : (ListenerUtil.mutListener.listen(6225) ? (lastModifiedColumnIndex != -1) : (lastModifiedColumnIndex == -1))))))) {
                if (!ListenerUtil.mutListener.listen(6230)) {
                    cursor.close();
                }
                return null;
            }
            if (!ListenerUtil.mutListener.listen(6231)) {
                cursor.moveToFirst();
            }
            return new DateTimeWithSource(cursor.getLong(lastModifiedColumnIndex), DateTimeWithSource.CP_SOURCE);
        } catch (Exception e) {
            // //Could not fetch last_modified
            return null;
        }
    }

    /**
     * Indicate whether the EXIF contains the location (both latitude and longitude).
     *
     * @return whether the location exists for the file's EXIF
     */
    public boolean hasLocation() {
        try {
            ExifInterface exif = new ExifInterface(file.getAbsolutePath());
            final String latitude = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
            final String longitude = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
            return (ListenerUtil.mutListener.listen(6234) ? (latitude != null || longitude != null) : (latitude != null && longitude != null));
        } catch (IOException | NumberFormatException | IndexOutOfBoundsException e) {
            if (!ListenerUtil.mutListener.listen(6232)) {
                Timber.tag("UploadableFile");
            }
            if (!ListenerUtil.mutListener.listen(6233)) {
                Timber.d(e);
            }
        }
        return false;
    }

    /**
     * Get filePath creation date from uri from EXIF
     *
     * @return
     */
    private DateTimeWithSource getDateTimeFromExif() {
        try {
            ExifInterface exif = new ExifInterface(file.getAbsolutePath());
            // See issue https://github.com/commons-app/apps-android-commons/issues/1971
            String dateTimeSubString = exif.getAttribute(ExifInterface.TAG_DATETIME_ORIGINAL);
            if (!ListenerUtil.mutListener.listen(6244)) {
                if (dateTimeSubString != null) {
                    // getAttribute may return null
                    String year = dateTimeSubString.substring(0, 4);
                    String month = dateTimeSubString.substring(5, 7);
                    String day = dateTimeSubString.substring(8, 10);
                    // This date is stored as a string (not as a date), the rason is we don't want to include timezones
                    String dateCreatedString = String.format("%04d-%02d-%02d", Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
                    if (!ListenerUtil.mutListener.listen(6243)) {
                        if ((ListenerUtil.mutListener.listen(6241) ? (dateCreatedString.length() >= 10) : (ListenerUtil.mutListener.listen(6240) ? (dateCreatedString.length() <= 10) : (ListenerUtil.mutListener.listen(6239) ? (dateCreatedString.length() > 10) : (ListenerUtil.mutListener.listen(6238) ? (dateCreatedString.length() < 10) : (ListenerUtil.mutListener.listen(6237) ? (dateCreatedString.length() != 10) : (dateCreatedString.length() == 10))))))) {
                            // yyyy-MM-dd format of date is expected
                            @SuppressLint("RestrictedApi")
                            Long dateTime = exif.getDateTimeOriginal();
                            if (!ListenerUtil.mutListener.listen(6242)) {
                                if (dateTime != null) {
                                    Date date = new Date(dateTime);
                                    return new DateTimeWithSource(date, dateCreatedString, DateTimeWithSource.EXIF_SOURCE);
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException | NumberFormatException | IndexOutOfBoundsException e) {
            if (!ListenerUtil.mutListener.listen(6235)) {
                Timber.tag("UploadableFile");
            }
            if (!ListenerUtil.mutListener.listen(6236)) {
                Timber.d(e);
            }
        }
        return null;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (!ListenerUtil.mutListener.listen(6245)) {
            parcel.writeParcelable(contentUri, 0);
        }
        if (!ListenerUtil.mutListener.listen(6246)) {
            parcel.writeSerializable(file);
        }
    }

    /**
     * This class contains the epochDate along with the source from which it was extracted
     */
    public class DateTimeWithSource {

        public static final String CP_SOURCE = "contentProvider";

        public static final String EXIF_SOURCE = "exif";

        private final long epochDate;

        // this does not includes timezone information
        private String dateString;

        private final String source;

        public DateTimeWithSource(long epochDate, String source) {
            this.epochDate = epochDate;
            this.source = source;
        }

        public DateTimeWithSource(Date date, String source) {
            this.epochDate = date.getTime();
            this.source = source;
        }

        public DateTimeWithSource(Date date, String dateString, String source) {
            this.epochDate = date.getTime();
            if (!ListenerUtil.mutListener.listen(6247)) {
                this.dateString = dateString;
            }
            this.source = source;
        }

        public long getEpochDate() {
            return epochDate;
        }

        public String getDateString() {
            return dateString;
        }

        public String getSource() {
            return source;
        }
    }
}
