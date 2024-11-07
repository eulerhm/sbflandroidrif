/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2013-2021 Threema GmbH
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

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.AgeFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.annotation.WorkerThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.preference.PreferenceManager;
import ch.threema.app.BuildConfig;
import ch.threema.app.NamedFileProvider;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.cache.ThumbnailCache;
import ch.threema.app.dialogs.CancelableHorizontalProgressDialog;
import ch.threema.app.listeners.AppIconListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.ui.SingleToast;
import ch.threema.app.utils.AndroidContactUtil;
import ch.threema.app.utils.BitmapUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.ConfigUtils.AppTheme;
import ch.threema.app.utils.DialogUtil;
import ch.threema.app.utils.FileUtil;
import ch.threema.app.utils.IconUtil;
import ch.threema.app.utils.LogUtil;
import ch.threema.app.utils.MessageUtil;
import ch.threema.app.utils.MimeUtil;
import ch.threema.app.utils.RingtoneUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.SecureDeleteUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.base.ThreemaException;
import ch.threema.client.Base32;
import ch.threema.localcrypto.MasterKey;
import ch.threema.localcrypto.MasterKeyLockedException;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.GroupModel;
import ch.threema.storage.models.MessageType;
import static android.provider.MediaStore.MEDIA_IGNORE_FILENAME;
import static ch.threema.app.services.MessageServiceImpl.THUMBNAIL_SIZE_PX;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FileServiceImpl implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    private static final String JPEG_EXTENSION = ".jpg";

    public static final String MPEG_EXTENSION = ".mp4";

    public static final String VOICEMESSAGE_EXTENSION = ".aac";

    private static final String THUMBNAIL_EXTENSION = "_T";

    private static final String WALLPAPER_FILENAME = "/wallpaper" + JPEG_EXTENSION;

    private static final String DIALOG_TAG_SAVING_MEDIA = "savingToGallery";

    private final Context context;

    private final MasterKey masterKey;

    private final PreferenceService preferenceService;

    private final File imagePath;

    private final File videoPath;

    private final File audioPath;

    private final File downloadsPath;

    private final File appDataPath;

    private final File backupPath;

    public FileServiceImpl(Context c, MasterKey masterKey, PreferenceService preferenceService) {
        this.context = c;
        this.preferenceService = preferenceService;
        this.masterKey = masterKey;
        String mediaPathPrefix = Environment.getExternalStorageDirectory() + "/" + BuildConfig.MEDIA_PATH + "/";
        // secondary storage directory for files that do not need any security enforced such as encrypted media
        this.appDataPath = new File(context.getExternalFilesDir(null), "data");
        if (!ListenerUtil.mutListener.listen(37691)) {
            getAppDataPath();
        }
        if (!ListenerUtil.mutListener.listen(37692)) {
            // temporary file path used for sharing media from / with external applications (i.e. system camera) on older Android versions
            createNomediaFile(getExtTmpPath());
        }
        this.imagePath = new File(mediaPathPrefix, "Threema Pictures");
        if (!ListenerUtil.mutListener.listen(37693)) {
            getImagePath();
        }
        this.videoPath = new File(mediaPathPrefix + "Threema Videos");
        if (!ListenerUtil.mutListener.listen(37694)) {
            getVideoPath();
        }
        this.audioPath = new File(mediaPathPrefix, "Threema Audio");
        if (!ListenerUtil.mutListener.listen(37695)) {
            getAudioPath();
        }
        this.backupPath = new File(mediaPathPrefix, "Backups");
        if (!ListenerUtil.mutListener.listen(37696)) {
            getBackupPath();
        }
        this.downloadsPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        if (!ListenerUtil.mutListener.listen(37697)) {
            getDownloadsPath();
        }
        if (!ListenerUtil.mutListener.listen(37699)) {
            // initialize ringtone
            if (needRingtonePreferencesUpdate(context.getContentResolver())) {
                if (!ListenerUtil.mutListener.listen(37698)) {
                    preferenceService.setVoiceCallSound(RingtoneUtil.THREEMA_CALL_RINGTONE_URI);
                }
            }
        }
    }

    /*
	 * Check if current ringtone prefs point to a valid ringtone or if an update is needed
	 */
    private boolean needRingtonePreferencesUpdate(ContentResolver contentResolver) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        String uriString = sharedPreferences.getString(this.context.getString(R.string.preferences__voip_ringtone), null);
        if (!ListenerUtil.mutListener.listen(37711)) {
            // check if we need to update preferences to point to new file
            if (TestUtil.empty(uriString)) {
                // silent ringtone -> OK
                return false;
            } else if (!"null".equals(uriString)) {
                Uri oldUri = Uri.parse(uriString);
                if (!ListenerUtil.mutListener.listen(37700)) {
                    if (oldUri.toString().equals("content://settings/system/ringtone")) {
                        // default system ringtone -> OK
                        return false;
                    }
                }
                String[] projection = { MediaStore.MediaColumns.DATA, MediaStore.Audio.Media.IS_RINGTONE };
                try (Cursor cursor = contentResolver.query(oldUri, projection, null, null, null)) {
                    if (!ListenerUtil.mutListener.listen(37710)) {
                        if (cursor != null) {
                            if (!ListenerUtil.mutListener.listen(37709)) {
                                if (cursor.moveToFirst()) {
                                    String path = cursor.getString(0);
                                    int isRingtone = cursor.getInt(1);
                                    if (!ListenerUtil.mutListener.listen(37708)) {
                                        // if preferences point to a valid file -> OK
                                        if ((ListenerUtil.mutListener.listen(37707) ? ((ListenerUtil.mutListener.listen(37701) ? (path != null || new File(path).exists()) : (path != null && new File(path).exists())) || (ListenerUtil.mutListener.listen(37706) ? (isRingtone >= 1) : (ListenerUtil.mutListener.listen(37705) ? (isRingtone <= 1) : (ListenerUtil.mutListener.listen(37704) ? (isRingtone > 1) : (ListenerUtil.mutListener.listen(37703) ? (isRingtone < 1) : (ListenerUtil.mutListener.listen(37702) ? (isRingtone != 1) : (isRingtone == 1))))))) : ((ListenerUtil.mutListener.listen(37701) ? (path != null || new File(path).exists()) : (path != null && new File(path).exists())) && (ListenerUtil.mutListener.listen(37706) ? (isRingtone >= 1) : (ListenerUtil.mutListener.listen(37705) ? (isRingtone <= 1) : (ListenerUtil.mutListener.listen(37704) ? (isRingtone > 1) : (ListenerUtil.mutListener.listen(37703) ? (isRingtone < 1) : (ListenerUtil.mutListener.listen(37702) ? (isRingtone != 1) : (isRingtone == 1))))))))) {
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
        return true;
    }

    @Deprecated
    public File getBackupPath() {
        if (!ListenerUtil.mutListener.listen(37719)) {
            if ((ListenerUtil.mutListener.listen(37716) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(37715) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(37714) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(37713) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(37712) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q))))))) {
                if (!ListenerUtil.mutListener.listen(37718)) {
                    if (!this.backupPath.exists()) {
                        if (!ListenerUtil.mutListener.listen(37717)) {
                            this.backupPath.mkdirs();
                        }
                    }
                }
            }
        }
        return this.backupPath;
    }

    @Override
    @Nullable
    public Uri getBackupUri() {
        // check if backup path is overridden by user
        Uri backupUri = preferenceService.getDataBackupUri();
        if (!ListenerUtil.mutListener.listen(37720)) {
            if (backupUri != null) {
                return backupUri;
            }
        }
        if (!ListenerUtil.mutListener.listen(37726)) {
            if ((ListenerUtil.mutListener.listen(37725) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(37724) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(37723) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(37722) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(37721) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q))))))) {
                return null;
            }
        }
        return Uri.fromFile(getBackupPath());
    }

    @Override
    public File getBlobDownloadPath() {
        File blobDownloadPath = new File(getAppDataPathAbsolute(), ".blob");
        if (!ListenerUtil.mutListener.listen(37730)) {
            if ((ListenerUtil.mutListener.listen(37727) ? (blobDownloadPath.exists() || !blobDownloadPath.isDirectory()) : (blobDownloadPath.exists() && !blobDownloadPath.isDirectory()))) {
                try {
                    if (!ListenerUtil.mutListener.listen(37729)) {
                        FileUtil.deleteFileOrWarn(blobDownloadPath, "Blob File", logger);
                    }
                } catch (SecurityException e) {
                    if (!ListenerUtil.mutListener.listen(37728)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(37733)) {
            if (!blobDownloadPath.exists()) {
                try {
                    if (!ListenerUtil.mutListener.listen(37732)) {
                        blobDownloadPath.mkdirs();
                    }
                } catch (SecurityException e) {
                    if (!ListenerUtil.mutListener.listen(37731)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
        return blobDownloadPath;
    }

    /**
     *  Get path where persistent app-specific data may be stored, that does not need any security enforced
     *  @return path
     */
    @Override
    public File getAppDataPath() {
        if (!ListenerUtil.mutListener.listen(37735)) {
            if (!this.appDataPath.exists()) {
                if (!ListenerUtil.mutListener.listen(37734)) {
                    this.appDataPath.mkdirs();
                }
            }
        }
        return this.appDataPath;
    }

    // TODO: Is this really necessary. According to documentation, paths returned by getExternalFilesDir() are already absolute
    private String getAppDataPathAbsolute() {
        return getAppDataPath().getAbsolutePath();
    }

    @Deprecated
    private File getImagePath() {
        if (!ListenerUtil.mutListener.listen(37743)) {
            if ((ListenerUtil.mutListener.listen(37740) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(37739) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(37738) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(37737) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(37736) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q))))))) {
                if (!ListenerUtil.mutListener.listen(37742)) {
                    if (!this.imagePath.exists()) {
                        if (!ListenerUtil.mutListener.listen(37741)) {
                            this.imagePath.mkdirs();
                        }
                    }
                }
            }
        }
        return this.imagePath;
    }

    @Deprecated
    private File getVideoPath() {
        if (!ListenerUtil.mutListener.listen(37751)) {
            if ((ListenerUtil.mutListener.listen(37748) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(37747) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(37746) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(37745) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(37744) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q))))))) {
                if (!ListenerUtil.mutListener.listen(37750)) {
                    if (!this.videoPath.exists()) {
                        if (!ListenerUtil.mutListener.listen(37749)) {
                            this.videoPath.mkdirs();
                        }
                    }
                }
            }
        }
        return this.videoPath;
    }

    @Deprecated
    private File getAudioPath() {
        if (!ListenerUtil.mutListener.listen(37759)) {
            if ((ListenerUtil.mutListener.listen(37756) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(37755) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(37754) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(37753) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(37752) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q))))))) {
                if (!ListenerUtil.mutListener.listen(37758)) {
                    if (!this.audioPath.exists()) {
                        if (!ListenerUtil.mutListener.listen(37757)) {
                            this.audioPath.mkdirs();
                        }
                    }
                }
            }
        }
        return this.audioPath;
    }

    @Deprecated
    private File getDownloadsPath() {
        if (!ListenerUtil.mutListener.listen(37770)) {
            if ((ListenerUtil.mutListener.listen(37764) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(37763) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(37762) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(37761) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(37760) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q))))))) {
                try {
                    if (!ListenerUtil.mutListener.listen(37769)) {
                        if (!this.downloadsPath.exists()) {
                            if (!ListenerUtil.mutListener.listen(37768)) {
                                this.downloadsPath.mkdirs();
                            }
                        } else if (!downloadsPath.isDirectory()) {
                            if (!ListenerUtil.mutListener.listen(37766)) {
                                FileUtil.deleteFileOrWarn(this.downloadsPath, "Download Path", logger);
                            }
                            if (!ListenerUtil.mutListener.listen(37767)) {
                                this.downloadsPath.mkdirs();
                            }
                        }
                    }
                } catch (SecurityException e) {
                    if (!ListenerUtil.mutListener.listen(37765)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
        return this.downloadsPath;
    }

    @Override
    public File getWallpaperDirPath() {
        File wallpaperPath = new File(getAppDataPathAbsolute(), ".wallpaper");
        if (!ListenerUtil.mutListener.listen(37772)) {
            if (!wallpaperPath.exists()) {
                if (!ListenerUtil.mutListener.listen(37771)) {
                    wallpaperPath.mkdirs();
                }
            }
        }
        return wallpaperPath;
    }

    @Override
    public File getAvatarDirPath() {
        File avatarPath = new File(getAppDataPathAbsolute(), ".avatar");
        if (!ListenerUtil.mutListener.listen(37774)) {
            if (!avatarPath.exists()) {
                if (!ListenerUtil.mutListener.listen(37773)) {
                    avatarPath.mkdirs();
                }
            }
        }
        return avatarPath;
    }

    @Override
    public File getGroupAvatarDirPath() {
        File grpAvatarPath = new File(getAppDataPathAbsolute(), ".grp-avatar");
        if (!ListenerUtil.mutListener.listen(37776)) {
            if (!grpAvatarPath.exists()) {
                if (!ListenerUtil.mutListener.listen(37775)) {
                    grpAvatarPath.mkdirs();
                }
            }
        }
        return grpAvatarPath;
    }

    @Override
    public String getGlobalWallpaperFilePath() {
        return getAppDataPathAbsolute() + WALLPAPER_FILENAME;
    }

    @Override
    public File getTempPath() {
        return context.getCacheDir();
    }

    @Override
    public File getExtTmpPath() {
        File extTmpPath = new File(context.getExternalFilesDir(null), "tmp");
        if (!ListenerUtil.mutListener.listen(37778)) {
            if (!extTmpPath.exists()) {
                if (!ListenerUtil.mutListener.listen(37777)) {
                    extTmpPath.mkdirs();
                }
            }
        }
        return extTmpPath;
    }

    private void createNomediaFile(File directory) {
        if (!ListenerUtil.mutListener.listen(37782)) {
            if (directory.exists()) {
                File nomedia = new File(directory, MEDIA_IGNORE_FILENAME);
                if (!ListenerUtil.mutListener.listen(37781)) {
                    if (!nomedia.exists()) {
                        try {
                            if (!ListenerUtil.mutListener.listen(37780)) {
                                FileUtil.createNewFileOrLog(nomedia, logger);
                            }
                        } catch (IOException e) {
                            if (!ListenerUtil.mutListener.listen(37779)) {
                                logger.error("Exception", e);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public File createTempFile(String prefix, String suffix) throws IOException {
        return createTempFile(prefix, suffix, false);
    }

    @Override
    public File createTempFile(String prefix, String suffix, boolean isPublic) throws IOException {
        return File.createTempFile(prefix, suffix, isPublic ? getExtTmpPath() : getTempPath());
    }

    @WorkerThread
    private void cleanDirectory(File path, final Runnable runAfter) {
        if (!ListenerUtil.mutListener.listen(37785)) {
            if (!path.isDirectory()) {
                if (!ListenerUtil.mutListener.listen(37784)) {
                    if (path.delete()) {
                        if (!ListenerUtil.mutListener.listen(37783)) {
                            path.mkdirs();
                        }
                    }
                }
                return;
            }
        }
        Date thresholdDate = new Date((ListenerUtil.mutListener.listen(37793) ? (System.currentTimeMillis() % ((ListenerUtil.mutListener.listen(37789) ? (15 % DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(37788) ? (15 / DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(37787) ? (15 - DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(37786) ? (15 + DateUtils.MINUTE_IN_MILLIS) : (15 * DateUtils.MINUTE_IN_MILLIS))))))) : (ListenerUtil.mutListener.listen(37792) ? (System.currentTimeMillis() / ((ListenerUtil.mutListener.listen(37789) ? (15 % DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(37788) ? (15 / DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(37787) ? (15 - DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(37786) ? (15 + DateUtils.MINUTE_IN_MILLIS) : (15 * DateUtils.MINUTE_IN_MILLIS))))))) : (ListenerUtil.mutListener.listen(37791) ? (System.currentTimeMillis() * ((ListenerUtil.mutListener.listen(37789) ? (15 % DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(37788) ? (15 / DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(37787) ? (15 - DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(37786) ? (15 + DateUtils.MINUTE_IN_MILLIS) : (15 * DateUtils.MINUTE_IN_MILLIS))))))) : (ListenerUtil.mutListener.listen(37790) ? (System.currentTimeMillis() + ((ListenerUtil.mutListener.listen(37789) ? (15 % DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(37788) ? (15 / DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(37787) ? (15 - DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(37786) ? (15 + DateUtils.MINUTE_IN_MILLIS) : (15 * DateUtils.MINUTE_IN_MILLIS))))))) : (System.currentTimeMillis() - ((ListenerUtil.mutListener.listen(37789) ? (15 % DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(37788) ? (15 / DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(37787) ? (15 - DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(37786) ? (15 + DateUtils.MINUTE_IN_MILLIS) : (15 * DateUtils.MINUTE_IN_MILLIS))))))))))));
        // this will crash if path is not a directory
        try {
            final Iterator<File> filesToDelete = FileUtils.iterateFiles(path, new AgeFileFilter(thresholdDate), TrueFileFilter.INSTANCE);
            if (!ListenerUtil.mutListener.listen(37803)) {
                if ((ListenerUtil.mutListener.listen(37795) ? (filesToDelete != null || filesToDelete.hasNext()) : (filesToDelete != null && filesToDelete.hasNext()))) {
                    if (!ListenerUtil.mutListener.listen(37802)) {
                        new Thread() {

                            @Override
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(37799)) {
                                    {
                                        long _loopCounter396 = 0;
                                        while (filesToDelete.hasNext()) {
                                            ListenerUtil.loopListener.listen("_loopCounter396", ++_loopCounter396);
                                            File file = filesToDelete.next();
                                            try {
                                                if (!ListenerUtil.mutListener.listen(37798)) {
                                                    SecureDeleteUtil.secureDelete(file);
                                                }
                                            } catch (IOException e) {
                                                if (!ListenerUtil.mutListener.listen(37796)) {
                                                    logger.error("Exception", e);
                                                }
                                                if (!ListenerUtil.mutListener.listen(37797)) {
                                                    FileUtils.deleteQuietly(file);
                                                }
                                            }
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(37801)) {
                                    if (runAfter != null) {
                                        if (!ListenerUtil.mutListener.listen(37800)) {
                                            runAfter.run();
                                        }
                                    }
                                }
                            }
                        }.start();
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            if (!ListenerUtil.mutListener.listen(37794)) {
                logger.error("Exception", e);
            }
        }
    }

    @Override
    public void cleanTempDirs() {
        if (!ListenerUtil.mutListener.listen(37804)) {
            logger.debug("Cleaning temp files");
        }
        if (!ListenerUtil.mutListener.listen(37805)) {
            cleanDirectory(getTempPath(), null);
        }
        if (!ListenerUtil.mutListener.listen(37807)) {
            cleanDirectory(getExtTmpPath(), new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(37806)) {
                        createNomediaFile(getExtTmpPath());
                    }
                }
            });
        }
    }

    @Override
    public String getWallpaperFilePath(MessageReceiver messageReceiver) {
        if (!ListenerUtil.mutListener.listen(37808)) {
            if (messageReceiver != null) {
                return getWallpaperFilePath(messageReceiver.getUniqueIdString());
            }
        }
        return null;
    }

    @Override
    public String getWallpaperFilePath(String uniqueIdString) {
        if (!ListenerUtil.mutListener.listen(37809)) {
            if (!TextUtils.isEmpty(uniqueIdString)) {
                return getWallpaperDirPath() + "/.w-" + uniqueIdString + MEDIA_IGNORE_FILENAME;
            }
        }
        return null;
    }

    @Override
    public File createWallpaperFile(MessageReceiver messageReceiver) throws IOException {
        File wallpaperFile;
        if (messageReceiver != null) {
            wallpaperFile = new File(getWallpaperFilePath(messageReceiver));
        } else {
            wallpaperFile = new File(getGlobalWallpaperFilePath());
        }
        if (!ListenerUtil.mutListener.listen(37811)) {
            if (!wallpaperFile.exists()) {
                if (!ListenerUtil.mutListener.listen(37810)) {
                    FileUtil.createNewFileOrLog(wallpaperFile, logger);
                }
            }
        }
        return wallpaperFile;
    }

    @Override
    public boolean hasContactAvatarFile(ContactModel contactModel) {
        File avatar = getContactAvatarFile(contactModel);
        return (ListenerUtil.mutListener.listen(37812) ? (avatar != null || avatar.exists()) : (avatar != null && avatar.exists()));
    }

    @Override
    public boolean hasContactPhotoFile(ContactModel contactModel) {
        File avatar = getContactPhotoFile(contactModel);
        return (ListenerUtil.mutListener.listen(37813) ? (avatar != null || avatar.exists()) : (avatar != null && avatar.exists()));
    }

    private File getPictureFile(File path, String prefix, String identity) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            if (!ListenerUtil.mutListener.listen(37814)) {
                messageDigest.update(("c-" + identity).getBytes());
            }
            String filename = prefix + Base32.encode(messageDigest.digest()) + MEDIA_IGNORE_FILENAME;
            return new File(path, filename);
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }

    private File getContactAvatarFile(ContactModel contactModel) {
        return getPictureFile(getAvatarDirPath(), ".c-", contactModel.getIdentity());
    }

    private File getContactPhotoFile(ContactModel contactModel) {
        return getPictureFile(getAvatarDirPath(), ".p-", contactModel.getIdentity());
    }

    private File getAndroidContactAvatarFile(ContactModel contactModel) {
        return getPictureFile(getAvatarDirPath(), ".a-", contactModel.getIdentity());
    }

    @Override
    public boolean decryptFileToFile(File from, File to) {
        try (InputStream is = new FileInputStream(from);
            FileOutputStream fos = new FileOutputStream(to)) {
            int result = 0;
            try (CipherOutputStream cos = masterKey.getCipherOutputStream(fos)) {
                if (!ListenerUtil.mutListener.listen(37817)) {
                    if (cos != null) {
                        if (!ListenerUtil.mutListener.listen(37816)) {
                            result = IOUtils.copy(is, cos);
                        }
                    }
                }
            }
            return ((ListenerUtil.mutListener.listen(37822) ? (result >= 0) : (ListenerUtil.mutListener.listen(37821) ? (result <= 0) : (ListenerUtil.mutListener.listen(37820) ? (result < 0) : (ListenerUtil.mutListener.listen(37819) ? (result != 0) : (ListenerUtil.mutListener.listen(37818) ? (result == 0) : (result > 0)))))));
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(37815)) {
                logger.error("Exception", e);
            }
        }
        return false;
    }

    public boolean removeMessageFiles(AbstractMessageModel messageModel, boolean withThumbnails) {
        boolean success = false;
        File messageFile = this.getMessageFile(messageModel);
        if (!ListenerUtil.mutListener.listen(37826)) {
            if ((ListenerUtil.mutListener.listen(37823) ? (messageFile != null || messageFile.exists()) : (messageFile != null && messageFile.exists()))) {
                if (!ListenerUtil.mutListener.listen(37825)) {
                    if (messageFile.delete()) {
                        if (!ListenerUtil.mutListener.listen(37824)) {
                            success = true;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(37832)) {
            if (withThumbnails) {
                File thumbnailFile = this.getMessageThumbnail(messageModel);
                if (!ListenerUtil.mutListener.listen(37831)) {
                    if ((ListenerUtil.mutListener.listen(37828) ? ((ListenerUtil.mutListener.listen(37827) ? (thumbnailFile != null || thumbnailFile.exists()) : (thumbnailFile != null && thumbnailFile.exists())) || thumbnailFile.delete()) : ((ListenerUtil.mutListener.listen(37827) ? (thumbnailFile != null || thumbnailFile.exists()) : (thumbnailFile != null && thumbnailFile.exists())) && thumbnailFile.delete()))) {
                        if (!ListenerUtil.mutListener.listen(37830)) {
                            logger.debug("Thumbnail deleted");
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(37829)) {
                            logger.debug("No thumbnail to delete");
                        }
                    }
                }
            }
        }
        return success;
    }

    public File getDecryptedMessageFile(AbstractMessageModel messageModel) throws Exception {
        String ext = getMediaFileExtension(messageModel);
        CipherInputStream is = null;
        FileOutputStream fos = null;
        try {
            if (!ListenerUtil.mutListener.listen(37837)) {
                is = getDecryptedMessageStream(messageModel);
            }
            if (!ListenerUtil.mutListener.listen(37840)) {
                if (is != null) {
                    File decoded = this.createTempFile(messageModel.getId() + "" + messageModel.getCreatedAt().getTime(), ext, !ConfigUtils.useContentUris());
                    if (!ListenerUtil.mutListener.listen(37838)) {
                        fos = new FileOutputStream(decoded);
                    }
                    if (!ListenerUtil.mutListener.listen(37839)) {
                        IOUtils.copy(is, fos);
                    }
                    return decoded;
                }
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(37834)) {
                if (fos != null) {
                    try {
                        if (!ListenerUtil.mutListener.listen(37833)) {
                            fos.close();
                        }
                    } catch (IOException e) {
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(37836)) {
                if (is != null) {
                    try {
                        if (!ListenerUtil.mutListener.listen(37835)) {
                            is.close();
                        }
                    } catch (IOException e) {
                    }
                }
            }
        }
        return null;
    }

    public File getDecryptedMessageFile(@NonNull AbstractMessageModel messageModel, @Nullable String filename) throws Exception {
        if (!ListenerUtil.mutListener.listen(37841)) {
            if (filename == null) {
                return getDecryptedMessageFile(messageModel);
            }
        }
        InputStream is = getDecryptedMessageStream(messageModel);
        if (!ListenerUtil.mutListener.listen(37847)) {
            if (is != null) {
                FileOutputStream fos = null;
                try {
                    File decrypted = new File(ConfigUtils.useContentUris() ? this.getTempPath() : this.getExtTmpPath(), messageModel.getApiMessageId() + "-" + filename);
                    if (!ListenerUtil.mutListener.listen(37845)) {
                        fos = new FileOutputStream(decrypted);
                    }
                    if (!ListenerUtil.mutListener.listen(37846)) {
                        IOUtils.copy(is, fos);
                    }
                    return decrypted;
                } finally {
                    if (!ListenerUtil.mutListener.listen(37843)) {
                        if (fos != null) {
                            try {
                                if (!ListenerUtil.mutListener.listen(37842)) {
                                    fos.close();
                                }
                            } catch (IOException e) {
                            }
                        }
                    }
                    try {
                        if (!ListenerUtil.mutListener.listen(37844)) {
                            is.close();
                        }
                    } catch (IOException e) {
                    }
                }
            }
        }
        return null;
    }

    @Override
    public CipherInputStream getDecryptedMessageStream(AbstractMessageModel messageModel) throws Exception {
        File file = this.getMessageFile(messageModel);
        if (!ListenerUtil.mutListener.listen(37849)) {
            if ((ListenerUtil.mutListener.listen(37848) ? (file != null || file.exists()) : (file != null && file.exists()))) {
                return masterKey.getCipherInputStream(new FileInputStream(file));
            }
        }
        return null;
    }

    @Override
    public CipherInputStream getDecryptedMessageThumbnailStream(AbstractMessageModel messageModel) throws Exception {
        File thumbnailFile = this.getMessageThumbnail(messageModel);
        if (!ListenerUtil.mutListener.listen(37851)) {
            if ((ListenerUtil.mutListener.listen(37850) ? (thumbnailFile != null || thumbnailFile.exists()) : (thumbnailFile != null && thumbnailFile.exists()))) {
                return masterKey.getCipherInputStream(new FileInputStream(thumbnailFile));
            }
        }
        return null;
    }

    /**
     *  return the filename of a message file saved in the gallery (if exist)
     */
    @Nullable
    private String constructGalleryMediaFilename(AbstractMessageModel messageModel) {
        String title = FileUtil.getMediaFilenamePrefix(messageModel);
        if (!ListenerUtil.mutListener.listen(37854)) {
            switch(messageModel.getType()) {
                case IMAGE:
                    return title + JPEG_EXTENSION;
                case VIDEO:
                    return title + MPEG_EXTENSION;
                case VOICEMESSAGE:
                    return title + VOICEMESSAGE_EXTENSION;
                case FILE:
                    String filename = messageModel.getFileData().getFileName();
                    if (!ListenerUtil.mutListener.listen(37853)) {
                        if (TestUtil.empty(filename)) {
                            if (!ListenerUtil.mutListener.listen(37852)) {
                                filename = title + getMediaFileExtension(messageModel);
                            }
                        }
                    }
                    return filename;
                default:
                    break;
            }
        }
        return null;
    }

    /**
     *  Returns the file name "extension" matching the provided message model
     *  In case of file messages, the provided mime type is used to guess a valid extension.
     *  If no mime type is found, as a last resort, the extension provided in the file's file name is used.
     *  @param messageModel
     *  @return The extension including a leading "." or null if no extension could be guessed
     */
    private String getMediaFileExtension(AbstractMessageModel messageModel) {
        if (messageModel == null) {
            return null;
        }
        switch(messageModel.getType()) {
            case IMAGE:
                return JPEG_EXTENSION;
            case VIDEO:
                return MPEG_EXTENSION;
            case VOICEMESSAGE:
                return VOICEMESSAGE_EXTENSION;
            case FILE:
                String extension = null;
                try {
                    if (!ListenerUtil.mutListener.listen(37856)) {
                        extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(messageModel.getFileData().getMimeType());
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(37855)) {
                        logger.error("Exception", e);
                    }
                }
                if (!TestUtil.empty(extension)) {
                    return "." + extension;
                } else {
                    if (messageModel.getFileData().getFileName() != null) {
                        if (!ListenerUtil.mutListener.listen(37857)) {
                            extension = MimeTypeMap.getFileExtensionFromUrl(messageModel.getFileData().getFileName());
                        }
                        if (!TestUtil.empty(extension)) {
                            return "." + extension;
                        }
                    }
                    return null;
                }
            default:
                return null;
        }
    }

    private void copyMediaFileIntoPublicDirectory(InputStream inputStream, String filename, String mimeType) throws Exception {
        if (!ListenerUtil.mutListener.listen(37875)) {
            if ((ListenerUtil.mutListener.listen(37862) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(37861) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(37860) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(37859) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(37858) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q))))))) {
                String relativePath;
                Uri contentUri;
                if (MimeUtil.isAudioFile(mimeType)) {
                    relativePath = Environment.DIRECTORY_MUSIC;
                    contentUri = MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
                } else if (MimeUtil.isVideoFile(mimeType)) {
                    relativePath = Environment.DIRECTORY_MOVIES;
                    contentUri = MediaStore.Video.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
                } else if (MimeUtil.isImageFile(mimeType)) {
                    relativePath = Environment.DIRECTORY_PICTURES;
                    contentUri = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
                } else if (MimeUtil.isPdfFile(mimeType)) {
                    relativePath = Environment.DIRECTORY_DOCUMENTS;
                    contentUri = MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
                } else {
                    relativePath = Environment.DIRECTORY_DOWNLOADS;
                    contentUri = MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
                }
                final ContentValues contentValues = new ContentValues();
                if (!ListenerUtil.mutListener.listen(37866)) {
                    contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, filename);
                }
                if (!ListenerUtil.mutListener.listen(37867)) {
                    contentValues.put(MediaStore.MediaColumns.MIME_TYPE, mimeType);
                }
                if (!ListenerUtil.mutListener.listen(37868)) {
                    contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, relativePath + "/" + BuildConfig.MEDIA_PATH);
                }
                if (!ListenerUtil.mutListener.listen(37869)) {
                    contentValues.put(MediaStore.MediaColumns.IS_PENDING, true);
                }
                Uri fileUri = context.getContentResolver().insert(contentUri, contentValues);
                if (!ListenerUtil.mutListener.listen(37874)) {
                    if (fileUri != null) {
                        try (OutputStream outputStream = context.getContentResolver().openOutputStream(fileUri)) {
                            if (!ListenerUtil.mutListener.listen(37870)) {
                                IOUtils.copy(inputStream, outputStream);
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(37871)) {
                            contentValues.clear();
                        }
                        if (!ListenerUtil.mutListener.listen(37872)) {
                            contentValues.put(MediaStore.MediaColumns.IS_PENDING, false);
                        }
                        if (!ListenerUtil.mutListener.listen(37873)) {
                            context.getContentResolver().update(fileUri, contentValues, null, null);
                        }
                    } else {
                        throw new Exception("Unable to open file");
                    }
                }
            } else {
                File destPath;
                if (MimeUtil.isAudioFile(mimeType)) {
                    destPath = getAudioPath();
                } else if (MimeUtil.isVideoFile(mimeType)) {
                    destPath = getVideoPath();
                } else if (MimeUtil.isImageFile(mimeType)) {
                    destPath = getImagePath();
                } else if (MimeUtil.isPdfFile(mimeType)) {
                    destPath = getDownloadsPath();
                } else {
                    destPath = getDownloadsPath();
                }
                File destFile = new File(destPath, filename);
                if (!ListenerUtil.mutListener.listen(37863)) {
                    destFile = FileUtil.getUniqueFile(destFile.getParent(), destFile.getName());
                }
                try (FileOutputStream outputStream = new FileOutputStream(destFile)) {
                    if (!ListenerUtil.mutListener.listen(37864)) {
                        IOUtils.copy(inputStream, outputStream);
                    }
                    if (!ListenerUtil.mutListener.listen(37865)) {
                        // let the system know, media store has changed
                        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(destFile)));
                    }
                }
            }
        }
    }

    /**
     *  save the data of a message model into the gallery
     */
    private void insertMessageIntoGallery(AbstractMessageModel messageModel) throws Exception {
        String mediaFilename = this.constructGalleryMediaFilename(messageModel);
        if (!ListenerUtil.mutListener.listen(37876)) {
            if (mediaFilename == null) {
                return;
            }
        }
        File messageFile = this.getMessageFile(messageModel);
        if (!ListenerUtil.mutListener.listen(37878)) {
            if (!FileUtil.isFilePresent(messageFile)) {
                if (!ListenerUtil.mutListener.listen(37877)) {
                    // fall back to thumbnail
                    messageFile = this.getMessageThumbnail(messageModel);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(37880)) {
            if (FileUtil.isFilePresent(messageFile)) {
                try (CipherInputStream cis = masterKey.getCipherInputStream(new FileInputStream(messageFile))) {
                    if (!ListenerUtil.mutListener.listen(37879)) {
                        copyMediaFileIntoPublicDirectory(cis, mediaFilename, MimeUtil.getMimeTypeFromMessageModel(messageModel));
                    }
                }
            } else {
                throw new ThreemaException("File not found.");
            }
        }
    }

    @Override
    public void copyDecryptedFileIntoGallery(Uri sourceUri, AbstractMessageModel messageModel) throws Exception {
        String mediaFilename = this.constructGalleryMediaFilename(messageModel);
        if (!ListenerUtil.mutListener.listen(37881)) {
            if (mediaFilename == null) {
                return;
            }
        }
        ContentResolver cr = context.getContentResolver();
        try (final InputStream inputStream = cr.openInputStream(sourceUri)) {
            if (!ListenerUtil.mutListener.listen(37883)) {
                if (inputStream != null) {
                    if (!ListenerUtil.mutListener.listen(37882)) {
                        copyMediaFileIntoPublicDirectory(inputStream, mediaFilename, MimeUtil.getMimeTypeFromMessageModel(messageModel));
                    }
                }
            }
        }
    }

    private String convert(String uid) {
        if (!ListenerUtil.mutListener.listen(37884)) {
            if (TestUtil.empty(uid)) {
                return uid;
            }
        }
        return uid.replaceAll("[^a-zA-Z0-9\\\\s]", "");
    }

    private String getGroupAvatarFileName(GroupModel groupModel) {
        return ".grp-avatar-" + groupModel.getId();
    }

    private File getGroupAvatarFile(GroupModel groupModel) {
        // new in 3.0 - save group avatars in separate directory
        File avatarFile = new File(getGroupAvatarDirPath(), getGroupAvatarFileName(groupModel));
        if (!ListenerUtil.mutListener.listen(37887)) {
            if ((ListenerUtil.mutListener.listen(37886) ? ((ListenerUtil.mutListener.listen(37885) ? (avatarFile.exists() || avatarFile.isFile()) : (avatarFile.exists() && avatarFile.isFile())) || avatarFile.canRead()) : ((ListenerUtil.mutListener.listen(37885) ? (avatarFile.exists() || avatarFile.isFile()) : (avatarFile.exists() && avatarFile.isFile())) && avatarFile.canRead()))) {
                return avatarFile;
            }
        }
        return new File(getAppDataPathAbsolute(), getGroupAvatarFileName(groupModel));
    }

    @Override
    public File getMessageFile(AbstractMessageModel messageModel) {
        String uid = this.convert(messageModel.getUid());
        if (!ListenerUtil.mutListener.listen(37888)) {
            if (TestUtil.empty(uid)) {
                return null;
            }
        }
        return new File(getAppDataPathAbsolute(), "." + uid);
    }

    private File getMessageThumbnail(AbstractMessageModel messageModel) {
        if (!ListenerUtil.mutListener.listen(37889)) {
            // locations do not have a file, do not check for existing!
            if (messageModel == null) {
                return null;
            }
        }
        String uid = this.convert(messageModel.getUid());
        if (!ListenerUtil.mutListener.listen(37890)) {
            if (TestUtil.empty(uid)) {
                return null;
            }
        }
        return new File(getAppDataPathAbsolute(), "." + uid + THUMBNAIL_EXTENSION);
    }

    @Override
    public boolean writeConversationMedia(AbstractMessageModel messageModel, byte[] data) {
        return this.writeConversationMedia(messageModel, data, 0, data.length);
    }

    @Override
    public boolean writeConversationMedia(AbstractMessageModel messageModel, byte[] data, int pos, int length) {
        return this.writeConversationMedia(messageModel, data, pos, length, false);
    }

    @Override
    public boolean writeConversationMedia(AbstractMessageModel messageModel, byte[] data, int pos, int length, boolean overwrite) {
        boolean success = false;
        if (!ListenerUtil.mutListener.listen(37891)) {
            if (this.masterKey.isLocked()) {
                return false;
            }
        }
        File messageFile = this.getMessageFile(messageModel);
        if (!ListenerUtil.mutListener.listen(37892)) {
            if (messageFile == null) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(37895)) {
            if (messageFile.exists()) {
                if (!ListenerUtil.mutListener.listen(37894)) {
                    if (overwrite) {
                        if (!ListenerUtil.mutListener.listen(37893)) {
                            FileUtil.deleteFileOrWarn(messageFile, "writeConversationMedia", logger);
                        }
                    } else {
                        return false;
                    }
                }
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(37898)) {
                if (messageFile.createNewFile()) {
                    if (!ListenerUtil.mutListener.listen(37897)) {
                        success = this.writeFile(data, pos, length, messageFile);
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(37896)) {
                logger.error("Exception", e);
            }
        }
        if (!ListenerUtil.mutListener.listen(37904)) {
            if (success) {
                if (!ListenerUtil.mutListener.listen(37903)) {
                    // try to generate a thumbnail
                    if (MessageUtil.autoGenerateThumbnail(messageModel)) {
                        File f = this.getMessageThumbnail(messageModel);
                        if (!ListenerUtil.mutListener.listen(37902)) {
                            if ((ListenerUtil.mutListener.listen(37899) ? (f != null || !f.exists()) : (f != null && !f.exists()))) {
                                // load the data
                                try {
                                    if (!ListenerUtil.mutListener.listen(37901)) {
                                        this.generateConversationMediaThumbnail(messageModel, data, pos, length);
                                    }
                                } catch (Exception e) {
                                    if (!ListenerUtil.mutListener.listen(37900)) {
                                        // unable to create thumbnail - ignore this
                                        logger.error("Exception", e);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return success;
    }

    @Override
    public boolean writeGroupAvatar(GroupModel groupModel, byte[] photoData) throws Exception {
        return this.writeFile(photoData, new File(getGroupAvatarDirPath(), getGroupAvatarFileName(groupModel)));
    }

    @Override
    public InputStream getGroupAvatarStream(GroupModel groupModel) throws Exception {
        File f = this.getGroupAvatarFile(groupModel);
        if (!ListenerUtil.mutListener.listen(37905)) {
            if (f.exists()) {
                return masterKey.getCipherInputStream(new FileInputStream(f));
            }
        }
        return null;
    }

    @Override
    public Bitmap getGroupAvatar(GroupModel groupModel) throws Exception {
        if (!ListenerUtil.mutListener.listen(37906)) {
            if (this.masterKey.isLocked()) {
                throw new Exception("no masterkey or locked");
            }
        }
        return decryptBitmapFromFile(this.getGroupAvatarFile(groupModel));
    }

    @Override
    public void removeGroupAvatar(GroupModel groupModel) {
        File f = this.getGroupAvatarFile(groupModel);
        if (!ListenerUtil.mutListener.listen(37908)) {
            if (f.exists()) {
                if (!ListenerUtil.mutListener.listen(37907)) {
                    FileUtil.deleteFileOrWarn(f, "removeGroupAvatar", logger);
                }
            }
        }
    }

    @Override
    public boolean hasGroupAvatarFile(GroupModel groupModel) {
        File f = this.getGroupAvatarFile(groupModel);
        return f.exists();
    }

    @Override
    public boolean writeContactAvatar(ContactModel contactModel, File file) throws Exception {
        return this.decryptFileToFile(file, this.getContactAvatarFile(contactModel));
    }

    @Override
    public boolean writeContactAvatar(ContactModel contactModel, byte[] avatarFile) throws Exception {
        return this.writeFile(avatarFile, this.getContactAvatarFile(contactModel));
    }

    @Override
    public boolean writeContactPhoto(ContactModel contactModel, byte[] encryptedBlob) throws Exception {
        return this.writeFile(encryptedBlob, this.getContactPhotoFile(contactModel));
    }

    @Override
    public boolean writeAndroidContactAvatar(ContactModel contactModel, byte[] avatarFile) throws Exception {
        return this.writeFile(avatarFile, this.getAndroidContactAvatarFile(contactModel));
    }

    @Override
    public Bitmap getContactAvatar(ContactModel contactModel) throws Exception {
        if (!ListenerUtil.mutListener.listen(37909)) {
            if (this.masterKey.isLocked()) {
                throw new Exception("no masterkey or locked");
            }
        }
        return decryptBitmapFromFile(this.getContactAvatarFile(contactModel));
    }

    @Override
    public Bitmap getAndroidContactAvatar(ContactModel contactModel) throws Exception {
        if (!ListenerUtil.mutListener.listen(37910)) {
            if (this.masterKey.isLocked()) {
                throw new Exception("no masterkey or locked");
            }
        }
        long now = System.currentTimeMillis();
        long expiration = contactModel.getAvatarExpires() != null ? contactModel.getAvatarExpires().getTime() : 0;
        if (!ListenerUtil.mutListener.listen(37920)) {
            if ((ListenerUtil.mutListener.listen(37915) ? (expiration >= now) : (ListenerUtil.mutListener.listen(37914) ? (expiration <= now) : (ListenerUtil.mutListener.listen(37913) ? (expiration > now) : (ListenerUtil.mutListener.listen(37912) ? (expiration != now) : (ListenerUtil.mutListener.listen(37911) ? (expiration == now) : (expiration < now))))))) {
                ServiceManager serviceManager = ThreemaApplication.getServiceManager();
                if (!ListenerUtil.mutListener.listen(37919)) {
                    if (serviceManager != null) {
                        if (!ListenerUtil.mutListener.listen(37918)) {
                            if (AndroidContactUtil.getInstance().updateAvatarByAndroidContact(contactModel)) {
                                ContactService contactService = serviceManager.getContactService();
                                if (!ListenerUtil.mutListener.listen(37917)) {
                                    if (contactService != null) {
                                        if (!ListenerUtil.mutListener.listen(37916)) {
                                            contactService.save(contactModel);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return decryptBitmapFromFile(this.getAndroidContactAvatarFile(contactModel));
    }

    @Override
    public InputStream getContactAvatarStream(ContactModel contactModel) throws IOException, MasterKeyLockedException {
        if (!ListenerUtil.mutListener.listen(37929)) {
            if (contactModel != null) {
                File f = this.getContactAvatarFile(contactModel);
                if (!ListenerUtil.mutListener.listen(37928)) {
                    if ((ListenerUtil.mutListener.listen(37927) ? ((ListenerUtil.mutListener.listen(37921) ? (f != null || f.exists()) : (f != null && f.exists())) || (ListenerUtil.mutListener.listen(37926) ? (f.length() >= 0) : (ListenerUtil.mutListener.listen(37925) ? (f.length() <= 0) : (ListenerUtil.mutListener.listen(37924) ? (f.length() < 0) : (ListenerUtil.mutListener.listen(37923) ? (f.length() != 0) : (ListenerUtil.mutListener.listen(37922) ? (f.length() == 0) : (f.length() > 0))))))) : ((ListenerUtil.mutListener.listen(37921) ? (f != null || f.exists()) : (f != null && f.exists())) && (ListenerUtil.mutListener.listen(37926) ? (f.length() >= 0) : (ListenerUtil.mutListener.listen(37925) ? (f.length() <= 0) : (ListenerUtil.mutListener.listen(37924) ? (f.length() < 0) : (ListenerUtil.mutListener.listen(37923) ? (f.length() != 0) : (ListenerUtil.mutListener.listen(37922) ? (f.length() == 0) : (f.length() > 0))))))))) {
                        return masterKey.getCipherInputStream(new FileInputStream(f));
                    }
                }
            }
        }
        return null;
    }

    @Override
    public InputStream getContactPhotoStream(ContactModel contactModel) throws IOException, MasterKeyLockedException {
        if (!ListenerUtil.mutListener.listen(37938)) {
            if (contactModel != null) {
                File f = this.getContactPhotoFile(contactModel);
                if (!ListenerUtil.mutListener.listen(37937)) {
                    if ((ListenerUtil.mutListener.listen(37936) ? ((ListenerUtil.mutListener.listen(37930) ? (f != null || f.exists()) : (f != null && f.exists())) || (ListenerUtil.mutListener.listen(37935) ? (f.length() >= 0) : (ListenerUtil.mutListener.listen(37934) ? (f.length() <= 0) : (ListenerUtil.mutListener.listen(37933) ? (f.length() < 0) : (ListenerUtil.mutListener.listen(37932) ? (f.length() != 0) : (ListenerUtil.mutListener.listen(37931) ? (f.length() == 0) : (f.length() > 0))))))) : ((ListenerUtil.mutListener.listen(37930) ? (f != null || f.exists()) : (f != null && f.exists())) && (ListenerUtil.mutListener.listen(37935) ? (f.length() >= 0) : (ListenerUtil.mutListener.listen(37934) ? (f.length() <= 0) : (ListenerUtil.mutListener.listen(37933) ? (f.length() < 0) : (ListenerUtil.mutListener.listen(37932) ? (f.length() != 0) : (ListenerUtil.mutListener.listen(37931) ? (f.length() == 0) : (f.length() > 0))))))))) {
                        return masterKey.getCipherInputStream(new FileInputStream(f));
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Bitmap getContactPhoto(ContactModel contactModel) throws Exception {
        if (!ListenerUtil.mutListener.listen(37939)) {
            if (this.masterKey.isLocked()) {
                throw new Exception("no masterkey or locked");
            }
        }
        if (!ListenerUtil.mutListener.listen(37940)) {
            if (this.preferenceService.getProfilePicReceive()) {
                return decryptBitmapFromFile(this.getContactPhotoFile(contactModel));
            }
        }
        return null;
    }

    private Bitmap decryptBitmapFromFile(File file) throws Exception {
        if (!ListenerUtil.mutListener.listen(37944)) {
            if (file.exists()) {
                InputStream inputStream = masterKey.getCipherInputStream(new FileInputStream(file));
                if (!ListenerUtil.mutListener.listen(37943)) {
                    if (inputStream != null) {
                        try {
                            return BitmapFactory.decodeStream(inputStream);
                        } catch (Exception e) {
                            if (!ListenerUtil.mutListener.listen(37941)) {
                                logger.error("Exception", e);
                            }
                        } finally {
                            if (!ListenerUtil.mutListener.listen(37942)) {
                                inputStream.close();
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public boolean removeContactAvatar(ContactModel contactModel) {
        File f = this.getContactAvatarFile(contactModel);
        return (ListenerUtil.mutListener.listen(37946) ? ((ListenerUtil.mutListener.listen(37945) ? (f != null || f.exists()) : (f != null && f.exists())) || f.delete()) : ((ListenerUtil.mutListener.listen(37945) ? (f != null || f.exists()) : (f != null && f.exists())) && f.delete()));
    }

    @Override
    public boolean removeContactPhoto(ContactModel contactModel) {
        File f = this.getContactPhotoFile(contactModel);
        return (ListenerUtil.mutListener.listen(37948) ? ((ListenerUtil.mutListener.listen(37947) ? (f != null || f.exists()) : (f != null && f.exists())) || f.delete()) : ((ListenerUtil.mutListener.listen(37947) ? (f != null || f.exists()) : (f != null && f.exists())) && f.delete()));
    }

    @Override
    public boolean removeAndroidContactAvatar(ContactModel contactModel) {
        File f = this.getAndroidContactAvatarFile(contactModel);
        return (ListenerUtil.mutListener.listen(37950) ? ((ListenerUtil.mutListener.listen(37949) ? (f != null || f.exists()) : (f != null && f.exists())) || f.delete()) : ((ListenerUtil.mutListener.listen(37949) ? (f != null || f.exists()) : (f != null && f.exists())) && f.delete()));
    }

    @Override
    public void removeAllAvatars() {
        try {
            if (!ListenerUtil.mutListener.listen(37952)) {
                FileUtils.cleanDirectory(getAvatarDirPath());
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(37951)) {
                logger.debug("Unable to empty avatar dir");
            }
        }
    }

    private boolean writeFile(byte[] data, File file) throws Exception {
        if (!ListenerUtil.mutListener.listen(37961)) {
            if ((ListenerUtil.mutListener.listen(37958) ? (data != null || (ListenerUtil.mutListener.listen(37957) ? (data.length >= 0) : (ListenerUtil.mutListener.listen(37956) ? (data.length <= 0) : (ListenerUtil.mutListener.listen(37955) ? (data.length < 0) : (ListenerUtil.mutListener.listen(37954) ? (data.length != 0) : (ListenerUtil.mutListener.listen(37953) ? (data.length == 0) : (data.length > 0))))))) : (data != null && (ListenerUtil.mutListener.listen(37957) ? (data.length >= 0) : (ListenerUtil.mutListener.listen(37956) ? (data.length <= 0) : (ListenerUtil.mutListener.listen(37955) ? (data.length < 0) : (ListenerUtil.mutListener.listen(37954) ? (data.length != 0) : (ListenerUtil.mutListener.listen(37953) ? (data.length == 0) : (data.length > 0))))))))) {
                try (FileOutputStream fileOutputStream = new FileOutputStream(file);
                    CipherOutputStream cipherOutputStream = this.masterKey.getCipherOutputStream(fileOutputStream)) {
                    if (!ListenerUtil.mutListener.listen(37960)) {
                        cipherOutputStream.write(data);
                    }
                    return true;
                } catch (FileNotFoundException e) {
                    if (!ListenerUtil.mutListener.listen(37959)) {
                        logger.error("Unable to save file to " + file.getAbsolutePath(), e);
                    }
                    throw new FileNotFoundException(e.getMessage());
                }
            }
        }
        return false;
    }

    private boolean writeFile(byte[] data, int pos, int length, File file) throws Exception {
        if (!ListenerUtil.mutListener.listen(37970)) {
            if ((ListenerUtil.mutListener.listen(37967) ? (data != null || (ListenerUtil.mutListener.listen(37966) ? (data.length >= 0) : (ListenerUtil.mutListener.listen(37965) ? (data.length <= 0) : (ListenerUtil.mutListener.listen(37964) ? (data.length < 0) : (ListenerUtil.mutListener.listen(37963) ? (data.length != 0) : (ListenerUtil.mutListener.listen(37962) ? (data.length == 0) : (data.length > 0))))))) : (data != null && (ListenerUtil.mutListener.listen(37966) ? (data.length >= 0) : (ListenerUtil.mutListener.listen(37965) ? (data.length <= 0) : (ListenerUtil.mutListener.listen(37964) ? (data.length < 0) : (ListenerUtil.mutListener.listen(37963) ? (data.length != 0) : (ListenerUtil.mutListener.listen(37962) ? (data.length == 0) : (data.length > 0))))))))) {
                try (FileOutputStream fileOutputStream = new FileOutputStream(file);
                    CipherOutputStream cipherOutputStream = this.masterKey.getCipherOutputStream(fileOutputStream)) {
                    if (!ListenerUtil.mutListener.listen(37969)) {
                        cipherOutputStream.write(data, pos, length);
                    }
                    return true;
                } catch (OutOfMemoryError e) {
                    throw new IOException("Out of memory");
                } catch (FileNotFoundException e) {
                    if (!ListenerUtil.mutListener.listen(37968)) {
                        logger.error("Unable to save file to " + file.getAbsolutePath(), e);
                    }
                    throw new FileNotFoundException(e.getMessage());
                }
            }
        }
        return false;
    }

    private void generateConversationMediaThumbnail(AbstractMessageModel messageModel, byte[] originalPicture, int pos, int length) throws Exception {
        if (!ListenerUtil.mutListener.listen(37971)) {
            if (this.masterKey.isLocked()) {
                throw new Exception("no masterkey or locked");
            }
        }
        int preferredThumbnailWidth = ConfigUtils.getPreferredThumbnailWidth(context, false);
        int maxWidth = THUMBNAIL_SIZE_PX << 1;
        byte[] resizedThumbnailBytes = BitmapUtil.resizeBitmapByteArrayToMaxWidth(originalPicture, (ListenerUtil.mutListener.listen(37976) ? (preferredThumbnailWidth >= maxWidth) : (ListenerUtil.mutListener.listen(37975) ? (preferredThumbnailWidth <= maxWidth) : (ListenerUtil.mutListener.listen(37974) ? (preferredThumbnailWidth < maxWidth) : (ListenerUtil.mutListener.listen(37973) ? (preferredThumbnailWidth != maxWidth) : (ListenerUtil.mutListener.listen(37972) ? (preferredThumbnailWidth == maxWidth) : (preferredThumbnailWidth > maxWidth)))))) ? maxWidth : preferredThumbnailWidth, pos, length);
        File thumbnailFile = this.getMessageThumbnail(messageModel);
        if (!ListenerUtil.mutListener.listen(37980)) {
            if (thumbnailFile != null) {
                if (!ListenerUtil.mutListener.listen(37977)) {
                    FileUtil.createNewFileOrLog(thumbnailFile, logger);
                }
                if (!ListenerUtil.mutListener.listen(37978)) {
                    logger.info("Writing thumbnail...");
                }
                if (!ListenerUtil.mutListener.listen(37979)) {
                    this.writeFile(resizedThumbnailBytes, thumbnailFile);
                }
            }
        }
    }

    @Override
    public void writeConversationMediaThumbnail(AbstractMessageModel messageModel, byte[] originalPicture) throws Exception {
        if (!ListenerUtil.mutListener.listen(37982)) {
            if (originalPicture != null) {
                if (!ListenerUtil.mutListener.listen(37981)) {
                    generateConversationMediaThumbnail(messageModel, originalPicture, 0, originalPicture.length);
                }
            }
        }
    }

    /**
     *  Return whether a thumbnail file exists for the specified message model.
     */
    @Override
    public boolean hasMessageThumbnail(AbstractMessageModel messageModel) {
        return this.getMessageThumbnail(messageModel).exists();
    }

    @Override
    @Nullable
    public Bitmap getMessageThumbnailBitmap(AbstractMessageModel messageModel, @Nullable ThumbnailCache thumbnailCache) throws Exception {
        if (!ListenerUtil.mutListener.listen(37985)) {
            if (thumbnailCache != null) {
                Bitmap cached = thumbnailCache.get(messageModel.getId());
                if (!ListenerUtil.mutListener.listen(37984)) {
                    if ((ListenerUtil.mutListener.listen(37983) ? (cached != null || !cached.isRecycled()) : (cached != null && !cached.isRecycled()))) {
                        return cached;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(37986)) {
            if (this.masterKey.isLocked()) {
                throw new Exception("no masterkey or locked");
            }
        }
        // Open thumbnail file
        final File f = this.getMessageThumbnail(messageModel);
        Bitmap thumbnailBitmap = null;
        FileInputStream fis = null;
        try {
            try {
                if (!ListenerUtil.mutListener.listen(37989)) {
                    fis = new FileInputStream(f);
                }
            } catch (FileNotFoundException e) {
                return null;
            }
            // Get cipher input streams
            BufferedInputStream bis = null;
            try {
                CipherInputStream cis = masterKey.getCipherInputStream(fis);
                if (!ListenerUtil.mutListener.listen(37992)) {
                    bis = new BufferedInputStream(cis);
                }
                BitmapFactory.Options options = new BitmapFactory.Options();
                if (!ListenerUtil.mutListener.listen(37993)) {
                    options.inJustDecodeBounds = false;
                }
                Bitmap originalBitmap = null;
                try {
                    if (!ListenerUtil.mutListener.listen(37995)) {
                        originalBitmap = BitmapFactory.decodeStream(bis, null, options);
                    }
                } catch (OutOfMemoryError e) {
                    if (!ListenerUtil.mutListener.listen(37994)) {
                        logger.error("Exception", e);
                    }
                }
                if (!ListenerUtil.mutListener.listen(37998)) {
                    if (originalBitmap != null) {
                        try {
                            if (!ListenerUtil.mutListener.listen(37997)) {
                                thumbnailBitmap = BitmapUtil.resizeBitmapExactlyToMaxWidth(originalBitmap, THUMBNAIL_SIZE_PX);
                            }
                        } catch (OutOfMemoryError e) {
                            if (!ListenerUtil.mutListener.listen(37996)) {
                                logger.error("Exception", e);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                throw e;
            } finally {
                if (!ListenerUtil.mutListener.listen(37991)) {
                    if (bis != null) {
                        try {
                            if (!ListenerUtil.mutListener.listen(37990)) {
                                bis.close();
                            }
                        } catch (IOException e) {
                        }
                    }
                }
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(37988)) {
                if (fis != null) {
                    try {
                        if (!ListenerUtil.mutListener.listen(37987)) {
                            fis.close();
                        }
                    } catch (IOException e) {
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(38001)) {
            if ((ListenerUtil.mutListener.listen(37999) ? (thumbnailCache != null || thumbnailBitmap != null) : (thumbnailCache != null && thumbnailBitmap != null))) {
                if (!ListenerUtil.mutListener.listen(38000)) {
                    thumbnailCache.set(messageModel.getId(), thumbnailBitmap);
                }
            }
        }
        return thumbnailBitmap;
    }

    @Override
    public Bitmap getDefaultMessageThumbnailBitmap(Context context, AbstractMessageModel messageModel, ThumbnailCache thumbnailCache, String mimeType) {
        if (!ListenerUtil.mutListener.listen(38004)) {
            if (thumbnailCache != null) {
                Bitmap cached = thumbnailCache.get(messageModel.getId());
                if (!ListenerUtil.mutListener.listen(38003)) {
                    if ((ListenerUtil.mutListener.listen(38002) ? (cached != null || !cached.isRecycled()) : (cached != null && !cached.isRecycled()))) {
                        return cached;
                    }
                }
            }
        }
        // supply fallback thumbnail based on mime type
        int icon = IconUtil.getMimeIcon(mimeType);
        Bitmap thumbnailBitmap = null;
        if (!ListenerUtil.mutListener.listen(38011)) {
            if ((ListenerUtil.mutListener.listen(38009) ? (icon >= 0) : (ListenerUtil.mutListener.listen(38008) ? (icon <= 0) : (ListenerUtil.mutListener.listen(38007) ? (icon > 0) : (ListenerUtil.mutListener.listen(38006) ? (icon < 0) : (ListenerUtil.mutListener.listen(38005) ? (icon == 0) : (icon != 0))))))) {
                if (!ListenerUtil.mutListener.listen(38010)) {
                    thumbnailBitmap = BitmapUtil.getBitmapFromVectorDrawable(AppCompatResources.getDrawable(context, icon), Color.WHITE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(38014)) {
            if ((ListenerUtil.mutListener.listen(38012) ? (thumbnailBitmap != null || thumbnailCache != null) : (thumbnailBitmap != null && thumbnailCache != null))) {
                if (!ListenerUtil.mutListener.listen(38013)) {
                    thumbnailCache.set(messageModel.getId(), thumbnailBitmap);
                }
            }
        }
        return thumbnailBitmap;
    }

    @Override
    public void clearDirectory(File directory, boolean recursive) throws IOException, ThreemaException {
        if (!ListenerUtil.mutListener.listen(38026)) {
            // use SecureDeleteUtil.secureDelete() for a secure version of this
            if (directory.isDirectory()) {
                File[] children = directory.listFiles();
                if (!ListenerUtil.mutListener.listen(38025)) {
                    if (children != null) {
                        if (!ListenerUtil.mutListener.listen(38024)) {
                            {
                                long _loopCounter397 = 0;
                                for (int i = 0; (ListenerUtil.mutListener.listen(38023) ? (i >= children.length) : (ListenerUtil.mutListener.listen(38022) ? (i <= children.length) : (ListenerUtil.mutListener.listen(38021) ? (i > children.length) : (ListenerUtil.mutListener.listen(38020) ? (i != children.length) : (ListenerUtil.mutListener.listen(38019) ? (i == children.length) : (i < children.length)))))); i++) {
                                    ListenerUtil.loopListener.listen("_loopCounter397", ++_loopCounter397);
                                    if (!ListenerUtil.mutListener.listen(38018)) {
                                        if (children[i].isDirectory()) {
                                            if (!ListenerUtil.mutListener.listen(38017)) {
                                                if (recursive) {
                                                    if (!ListenerUtil.mutListener.listen(38016)) {
                                                        this.clearDirectory(children[i], recursive);
                                                    }
                                                }
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(38015)) {
                                                FileUtil.deleteFileOrWarn(children[i], "clearDirectory", logger);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean remove(File directory, boolean removeWithContent) throws IOException, ThreemaException {
        if (!ListenerUtil.mutListener.listen(38036)) {
            if (directory.isDirectory()) {
                if (!ListenerUtil.mutListener.listen(38033)) {
                    if ((ListenerUtil.mutListener.listen(38032) ? (!removeWithContent || (ListenerUtil.mutListener.listen(38031) ? (directory.list().length >= 0) : (ListenerUtil.mutListener.listen(38030) ? (directory.list().length <= 0) : (ListenerUtil.mutListener.listen(38029) ? (directory.list().length < 0) : (ListenerUtil.mutListener.listen(38028) ? (directory.list().length != 0) : (ListenerUtil.mutListener.listen(38027) ? (directory.list().length == 0) : (directory.list().length > 0))))))) : (!removeWithContent && (ListenerUtil.mutListener.listen(38031) ? (directory.list().length >= 0) : (ListenerUtil.mutListener.listen(38030) ? (directory.list().length <= 0) : (ListenerUtil.mutListener.listen(38029) ? (directory.list().length < 0) : (ListenerUtil.mutListener.listen(38028) ? (directory.list().length != 0) : (ListenerUtil.mutListener.listen(38027) ? (directory.list().length == 0) : (directory.list().length > 0))))))))) {
                        throw new ThreemaException("cannot remove directory. directory contains files");
                    }
                }
                if (!ListenerUtil.mutListener.listen(38035)) {
                    {
                        long _loopCounter398 = 0;
                        for (File file : directory.listFiles()) {
                            ListenerUtil.loopListener.listen("_loopCounter398", ++_loopCounter398);
                            if (!ListenerUtil.mutListener.listen(38034)) {
                                this.remove(file, removeWithContent);
                            }
                        }
                    }
                }
            }
        }
        return directory.delete();
    }

    @Override
    @WorkerThread
    public File copyUriToTempFile(Uri uri, String prefix, String suffix, boolean isPublic) {
        try {
            File outputFile = createTempFile(prefix, suffix, isPublic);
            if (!ListenerUtil.mutListener.listen(38038)) {
                if (FileUtil.copyFile(uri, outputFile, context.getContentResolver())) {
                    return outputFile;
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(38037)) {
                logger.error("Exception", e);
            }
        }
        return null;
    }

    @Override
    public Uri copyToShareFile(AbstractMessageModel messageModel, File srcFile) {
        if (!ListenerUtil.mutListener.listen(38043)) {
            // copy file to public dir
            if (messageModel != null) {
                if (!ListenerUtil.mutListener.listen(38042)) {
                    if ((ListenerUtil.mutListener.listen(38039) ? (srcFile != null || srcFile.exists()) : (srcFile != null && srcFile.exists()))) {
                        String destFilePrefix = FileUtil.getMediaFilenamePrefix(messageModel);
                        String destFileExtension = getMediaFileExtension(messageModel);
                        File destFile = copyUriToTempFile(Uri.fromFile(srcFile), destFilePrefix, destFileExtension, !ConfigUtils.useContentUris());
                        String filename = null;
                        if (!ListenerUtil.mutListener.listen(38041)) {
                            if (messageModel.getType() == MessageType.FILE) {
                                if (!ListenerUtil.mutListener.listen(38040)) {
                                    filename = messageModel.getFileData().getFileName();
                                }
                            }
                        }
                        return getShareFileUri(destFile, filename);
                    }
                }
            }
        }
        return null;
    }

    /**
     *  Get an Uri for the destination file that can be shared to other apps. On Android 5+ our own content provider will be used to serve the file.
     *  @param destFile File to get an Uri for
     *  @param filename Desired filename for this file. Can be different from the filename of destFile
     *  @return Uri (Content Uri on Android 5+, File Uri otherwise)
     */
    @Override
    public Uri getShareFileUri(@NonNull File destFile, @Nullable String filename) {
        if (!ListenerUtil.mutListener.listen(38045)) {
            if (destFile != null) {
                if (!ListenerUtil.mutListener.listen(38044)) {
                    // see https://code.google.com/p/android/issues/detail?id=76683
                    if (ConfigUtils.useContentUris()) {
                        /* content uri */
                        return NamedFileProvider.getUriForFile(ThreemaApplication.getAppContext(), ThreemaApplication.getAppContext().getPackageName() + ".fileprovider", destFile, filename);
                    } else {
                        /* file uri */
                        return Uri.fromFile(destFile);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public long getInternalStorageUsage() {
        return getFolderSize(getAppDataPath());
    }

    private static long getFolderSize(File folderPath) {
        long totalSize = 0;
        if (!ListenerUtil.mutListener.listen(38046)) {
            if (folderPath == null) {
                return 0;
            }
        }
        if (!ListenerUtil.mutListener.listen(38047)) {
            if (!folderPath.isDirectory()) {
                return 0;
            }
        }
        File[] files = folderPath.listFiles();
        if (!ListenerUtil.mutListener.listen(38053)) {
            if (files != null) {
                if (!ListenerUtil.mutListener.listen(38052)) {
                    {
                        long _loopCounter399 = 0;
                        for (File file : files) {
                            ListenerUtil.loopListener.listen("_loopCounter399", ++_loopCounter399);
                            if (!ListenerUtil.mutListener.listen(38051)) {
                                if (file.isFile()) {
                                    if (!ListenerUtil.mutListener.listen(38050)) {
                                        totalSize += file.length();
                                    }
                                } else if (file.isDirectory()) {
                                    if (!ListenerUtil.mutListener.listen(38048)) {
                                        totalSize += file.length();
                                    }
                                    if (!ListenerUtil.mutListener.listen(38049)) {
                                        totalSize += getFolderSize(file);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return totalSize;
    }

    @Override
    public long getInternalStorageSize() {
        return getAppDataPath().getTotalSpace();
    }

    @Override
    public long getInternalStorageFree() {
        return getAppDataPath().getUsableSpace();
    }

    @WorkerThread
    public void loadDecryptedMessageFiles(final List<AbstractMessageModel> models, final OnDecryptedFilesComplete onDecryptedFilesComplete) {
        final ArrayList<Uri> shareFileUris = new ArrayList<>();
        int errorCount = 0;
        if (!ListenerUtil.mutListener.listen(38059)) {
            {
                long _loopCounter400 = 0;
                for (AbstractMessageModel model : models) {
                    ListenerUtil.loopListener.listen("_loopCounter400", ++_loopCounter400);
                    try {
                        File file;
                        if ((ListenerUtil.mutListener.listen(38054) ? (model.getType() == MessageType.FILE || model.getFileData() != null) : (model.getType() == MessageType.FILE && model.getFileData() != null))) {
                            file = getDecryptedMessageFile(model, model.getFileData().getFileName());
                        } else {
                            file = getDecryptedMessageFile(model);
                        }
                        if (!ListenerUtil.mutListener.listen(38056)) {
                            if (file != null) {
                                if (!ListenerUtil.mutListener.listen(38055)) {
                                    shareFileUris.add(getShareFileUri(file, null));
                                }
                                continue;
                            }
                        }
                    } catch (Exception ignore) {
                    }
                    if (!ListenerUtil.mutListener.listen(38057)) {
                        errorCount++;
                    }
                    if (!ListenerUtil.mutListener.listen(38058)) {
                        shareFileUris.add(null);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(38068)) {
            if (onDecryptedFilesComplete != null) {
                if (!ListenerUtil.mutListener.listen(38067)) {
                    if ((ListenerUtil.mutListener.listen(38064) ? (errorCount <= models.size()) : (ListenerUtil.mutListener.listen(38063) ? (errorCount > models.size()) : (ListenerUtil.mutListener.listen(38062) ? (errorCount < models.size()) : (ListenerUtil.mutListener.listen(38061) ? (errorCount != models.size()) : (ListenerUtil.mutListener.listen(38060) ? (errorCount == models.size()) : (errorCount >= models.size()))))))) {
                        if (!ListenerUtil.mutListener.listen(38066)) {
                            onDecryptedFilesComplete.error(context.getString(R.string.media_file_not_found));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(38065)) {
                            // at least some of the provided media could be decrypted. we consider this a success
                            onDecryptedFilesComplete.complete(shareFileUris);
                        }
                    }
                }
            }
        }
    }

    /*
	 * Load encrypted media file, decrypt it and save it to a temporary file
	 */
    @MainThread
    public void loadDecryptedMessageFile(final AbstractMessageModel model, final OnDecryptedFileComplete onDecryptedFileComplete) {
        if (!ListenerUtil.mutListener.listen(38072)) {
            if ((ListenerUtil.mutListener.listen(38070) ? ((ListenerUtil.mutListener.listen(38069) ? (model.getType() == MessageType.TEXT && model.getType() == MessageType.BALLOT) : (model.getType() == MessageType.TEXT || model.getType() == MessageType.BALLOT)) && model.getType() == MessageType.LOCATION) : ((ListenerUtil.mutListener.listen(38069) ? (model.getType() == MessageType.TEXT && model.getType() == MessageType.BALLOT) : (model.getType() == MessageType.TEXT || model.getType() == MessageType.BALLOT)) || model.getType() == MessageType.LOCATION))) {
                if (!ListenerUtil.mutListener.listen(38071)) {
                    onDecryptedFileComplete.complete(null);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(38082)) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        // HACK: save to a readable temporary filename
                        final File file;
                        if ((ListenerUtil.mutListener.listen(38078) ? (model.getType() == MessageType.FILE || model.getFileData() != null) : (model.getType() == MessageType.FILE && model.getFileData() != null))) {
                            file = getDecryptedMessageFile(model, model.getFileData().getFileName());
                        } else {
                            file = getDecryptedMessageFile(model);
                        }
                        if (!ListenerUtil.mutListener.listen(38081)) {
                            if (file != null) {
                                if (!ListenerUtil.mutListener.listen(38080)) {
                                    if (onDecryptedFileComplete != null) {
                                        if (!ListenerUtil.mutListener.listen(38079)) {
                                            onDecryptedFileComplete.complete(file);
                                        }
                                    }
                                }
                            } else {
                                throw new FileNotFoundException(context.getString(R.string.media_file_not_found));
                            }
                        }
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(38077)) {
                            if (onDecryptedFileComplete != null) {
                                String message = e.getMessage();
                                if (!ListenerUtil.mutListener.listen(38075)) {
                                    if ((ListenerUtil.mutListener.listen(38073) ? (message != null || message.contains("ENOENT")) : (message != null && message.contains("ENOENT")))) {
                                        if (!ListenerUtil.mutListener.listen(38074)) {
                                            message = context.getString(R.string.media_file_not_found);
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(38076)) {
                                    onDecryptedFileComplete.error(message);
                                }
                            }
                        }
                    }
                }
            }).start();
        }
    }

    @SuppressLint("StaticFieldLeak")
    @RequiresPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    @Override
    public void saveMedia(final AppCompatActivity activity, final View feedbackView, final CopyOnWriteArrayList<AbstractMessageModel> selectedMessages, final boolean quiet) {
        if (!ListenerUtil.mutListener.listen(38112)) {
            new AsyncTask<Void, Integer, Integer>() {

                boolean cancelled = false;

                @Override
                protected void onPreExecute() {
                    if (!ListenerUtil.mutListener.listen(38092)) {
                        if ((ListenerUtil.mutListener.listen(38088) ? (activity != null || (ListenerUtil.mutListener.listen(38087) ? (selectedMessages.size() >= 3) : (ListenerUtil.mutListener.listen(38086) ? (selectedMessages.size() <= 3) : (ListenerUtil.mutListener.listen(38085) ? (selectedMessages.size() < 3) : (ListenerUtil.mutListener.listen(38084) ? (selectedMessages.size() != 3) : (ListenerUtil.mutListener.listen(38083) ? (selectedMessages.size() == 3) : (selectedMessages.size() > 3))))))) : (activity != null && (ListenerUtil.mutListener.listen(38087) ? (selectedMessages.size() >= 3) : (ListenerUtil.mutListener.listen(38086) ? (selectedMessages.size() <= 3) : (ListenerUtil.mutListener.listen(38085) ? (selectedMessages.size() < 3) : (ListenerUtil.mutListener.listen(38084) ? (selectedMessages.size() != 3) : (ListenerUtil.mutListener.listen(38083) ? (selectedMessages.size() == 3) : (selectedMessages.size() > 3))))))))) {
                            CancelableHorizontalProgressDialog dialog = CancelableHorizontalProgressDialog.newInstance(R.string.saving_media, 0, R.string.cancel, selectedMessages.size());
                            if (!ListenerUtil.mutListener.listen(38090)) {
                                dialog.setOnCancelListener(new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (!ListenerUtil.mutListener.listen(38089)) {
                                            cancelled = true;
                                        }
                                    }
                                });
                            }
                            if (!ListenerUtil.mutListener.listen(38091)) {
                                dialog.show(activity.getSupportFragmentManager(), DIALOG_TAG_SAVING_MEDIA);
                            }
                        }
                    }
                }

                @Override
                protected Integer doInBackground(Void... params) {
                    int i = 0, saved = 0;
                    Iterator<AbstractMessageModel> checkedItemsIterator = selectedMessages.iterator();
                    if (!ListenerUtil.mutListener.listen(38104)) {
                        {
                            long _loopCounter401 = 0;
                            while ((ListenerUtil.mutListener.listen(38103) ? (checkedItemsIterator.hasNext() || !cancelled) : (checkedItemsIterator.hasNext() && !cancelled))) {
                                ListenerUtil.loopListener.listen("_loopCounter401", ++_loopCounter401);
                                if (!ListenerUtil.mutListener.listen(38093)) {
                                    publishProgress(i++);
                                }
                                AbstractMessageModel messageModel = checkedItemsIterator.next();
                                try {
                                    if (!ListenerUtil.mutListener.listen(38100)) {
                                        insertMessageIntoGallery(messageModel);
                                    }
                                    if (!ListenerUtil.mutListener.listen(38101)) {
                                        saved++;
                                    }
                                    if (!ListenerUtil.mutListener.listen(38102)) {
                                        logger.debug("Saved message " + messageModel.getUid());
                                    }
                                } catch (Exception e) {
                                    if (!ListenerUtil.mutListener.listen(38098)) {
                                        if (activity != null) {
                                            if (!ListenerUtil.mutListener.listen(38097)) {
                                                if (quiet) {
                                                    if (!ListenerUtil.mutListener.listen(38096)) {
                                                        RuntimeUtil.runOnUiThread(new Runnable() {

                                                            @Override
                                                            public void run() {
                                                                if (!ListenerUtil.mutListener.listen(38095)) {
                                                                    SingleToast.getInstance().showShortText(activity.getString(R.string.error_saving_file));
                                                                }
                                                            }
                                                        });
                                                    }
                                                } else {
                                                    if (!ListenerUtil.mutListener.listen(38094)) {
                                                        LogUtil.exception(e, activity);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(38099)) {
                                        logger.error("Exception", e);
                                    }
                                }
                            }
                        }
                    }
                    return saved;
                }

                @Override
                protected void onPostExecute(Integer saved) {
                    if (!ListenerUtil.mutListener.listen(38109)) {
                        if (activity != null) {
                            if (!ListenerUtil.mutListener.listen(38105)) {
                                DialogUtil.dismissDialog(activity.getSupportFragmentManager(), DIALOG_TAG_SAVING_MEDIA, true);
                            }
                            if (!ListenerUtil.mutListener.listen(38108)) {
                                if (feedbackView != null) {
                                    if (!ListenerUtil.mutListener.listen(38107)) {
                                        Snackbar.make(feedbackView, String.format(activity.getString(R.string.file_saved), saved), Snackbar.LENGTH_SHORT).show();
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(38106)) {
                                        Toast.makeText(activity, String.format(activity.getString(R.string.file_saved), saved), Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        }
                    }
                }

                @Override
                protected void onProgressUpdate(Integer... index) {
                    if (!ListenerUtil.mutListener.listen(38111)) {
                        if (activity != null) {
                            if (!ListenerUtil.mutListener.listen(38110)) {
                                DialogUtil.updateProgress(activity.getSupportFragmentManager(), DIALOG_TAG_SAVING_MEDIA, index[0] + 1);
                            }
                        }
                    }
                }
            }.execute();
        }
    }

    @Override
    public void saveAppLogo(File logo, @AppTheme int theme) {
        File existingLogo = this.getAppLogo(theme);
        if (!ListenerUtil.mutListener.listen(38118)) {
            if ((ListenerUtil.mutListener.listen(38113) ? (logo == null && !logo.exists()) : (logo == null || !logo.exists()))) {
                if (!ListenerUtil.mutListener.listen(38117)) {
                    // remove existing icon
                    if ((ListenerUtil.mutListener.listen(38115) ? (existingLogo != null || existingLogo.exists()) : (existingLogo != null && existingLogo.exists()))) {
                        if (!ListenerUtil.mutListener.listen(38116)) {
                            FileUtil.deleteFileOrWarn(existingLogo, "saveAppLogo", logger);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(38114)) {
                    FileUtil.copyFile(logo, existingLogo);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(38119)) {
            // call listener
            ListenerManager.appIconListeners.handle(AppIconListener::onChanged);
        }
    }

    @Override
    public File getAppLogo(@AppTheme int theme) {
        String key = "light";
        if (!ListenerUtil.mutListener.listen(38121)) {
            if (theme == ConfigUtils.THEME_DARK) {
                if (!ListenerUtil.mutListener.listen(38120)) {
                    key = "dark";
                }
            }
        }
        return new File(getAppDataPathAbsolute(), "appicon_" + key + ".png");
    }
}
