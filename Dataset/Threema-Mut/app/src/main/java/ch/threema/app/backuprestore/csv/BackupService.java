/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
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
package ch.threema.app.backuprestore.csv;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.SystemClock;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.widget.Toast;
import net.lingala.zip4j.io.outputstream.ZipOutputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.List;
import java.util.Set;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.documentfile.provider.DocumentFile;
import ch.threema.app.BuildConfig;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.DummyActivity;
import ch.threema.app.activities.HomeActivity;
import ch.threema.app.backuprestore.BackupRestoreDataConfig;
import ch.threema.app.collections.Functional;
import ch.threema.app.collections.IPredicateNonNull;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.notifications.NotificationBuilderWrapper;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.DistributionListService;
import ch.threema.app.services.FileService;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.UserService;
import ch.threema.app.services.ballot.BallotService;
import ch.threema.app.utils.BackupUtils;
import ch.threema.app.utils.CSVWriter;
import ch.threema.app.utils.MessageUtil;
import ch.threema.app.utils.MimeUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.StringConversionUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.utils.ZipUtil;
import ch.threema.base.ThreemaException;
import ch.threema.client.IdentityBackupGenerator;
import ch.threema.client.Utils;
import ch.threema.storage.DatabaseServiceNew;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.DistributionListMessageModel;
import ch.threema.storage.models.DistributionListModel;
import ch.threema.storage.models.GroupMessageModel;
import ch.threema.storage.models.GroupModel;
import ch.threema.storage.models.MessageModel;
import ch.threema.storage.models.MessageType;
import ch.threema.storage.models.ballot.BallotChoiceModel;
import ch.threema.storage.models.ballot.BallotModel;
import ch.threema.storage.models.ballot.BallotVoteModel;
import ch.threema.storage.models.ballot.GroupBallotModel;
import ch.threema.storage.models.ballot.IdentityBallotModel;
import ch.threema.storage.models.ballot.LinkBallotModel;
import ch.threema.storage.models.data.media.AudioDataModel;
import ch.threema.storage.models.data.media.FileDataModel;
import ch.threema.storage.models.data.media.VideoDataModel;
import static ch.threema.app.services.NotificationService.NOTIFICATION_CHANNEL_ALERT;
import static ch.threema.app.services.NotificationService.NOTIFICATION_CHANNEL_BACKUP_RESTORE_IN_PROGRESS;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BackupService extends Service {

    private static final Logger logger = LoggerFactory.getLogger(BackupService.class);

    private static final int MEDIA_STEP_FACTOR = 9;

    private static final int MEDIA_STEP_FACTOR_VIDEOS_AND_FILES = 12;

    private static final int MEDIA_STEP_FACTOR_THUMBNAILS = 3;

    private static final String EXTRA_ID_CANCEL = "cnc";

    public static final String EXTRA_BACKUP_RESTORE_DATA_CONFIG = "ebrdc";

    private static final int BACKUP_NOTIFICATION_ID = 991772;

    private static final int BACKUP_COMPLETION_NOTIFICATION_ID = 991773;

    private static final long FILE_SETTLE_DELAY = 5000;

    private static final String INCOMPLETE_BACKUP_FILENAME_PREFIX = "INCOMPLETE-";

    private int currentProgressStep = 0;

    private long processSteps = 0;

    private static boolean backupSuccess = false;

    private static boolean isCanceled = false;

    private static boolean isRunning = false;

    private ServiceManager serviceManager;

    private ContactService contactService;

    private FileService fileService;

    private UserService userService;

    private GroupService groupService;

    private BallotService ballotService;

    private DistributionListService distributionListService;

    private DatabaseServiceNew databaseServiceNew;

    private PreferenceService preferenceService;

    private PowerManager.WakeLock wakeLock;

    private NotificationManager notificationManager;

    private NotificationCompat.Builder notificationBuilder;

    private int latestPercentStep = -1;

    private long startTime = 0;

    private static DocumentFile backupFile = null;

    private BackupRestoreDataConfig config = null;

    public static boolean isRunning() {
        return isRunning;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!ListenerUtil.mutListener.listen(10271)) {
            if (intent != null) {
                if (!ListenerUtil.mutListener.listen(10232)) {
                    isCanceled = intent.getBooleanExtra(EXTRA_ID_CANCEL, false);
                }
                if (!ListenerUtil.mutListener.listen(10270)) {
                    if (!isCanceled) {
                        if (!ListenerUtil.mutListener.listen(10234)) {
                            config = (BackupRestoreDataConfig) intent.getSerializableExtra(EXTRA_BACKUP_RESTORE_DATA_CONFIG);
                        }
                        if (!ListenerUtil.mutListener.listen(10238)) {
                            if ((ListenerUtil.mutListener.listen(10236) ? ((ListenerUtil.mutListener.listen(10235) ? (config == null && userService.getIdentity() == null) : (config == null || userService.getIdentity() == null)) && userService.getIdentity().length() == 0) : ((ListenerUtil.mutListener.listen(10235) ? (config == null && userService.getIdentity() == null) : (config == null || userService.getIdentity() == null)) || userService.getIdentity().length() == 0))) {
                                if (!ListenerUtil.mutListener.listen(10237)) {
                                    stopSelf();
                                }
                                return START_NOT_STICKY;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(10239)) {
                            // acquire wake locks
                            logger.debug("Acquiring wakelock");
                        }
                        PowerManager powerManager = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
                        if (!ListenerUtil.mutListener.listen(10246)) {
                            if (powerManager != null) {
                                String tag = BuildConfig.APPLICATION_ID + ":backup";
                                if (!ListenerUtil.mutListener.listen(10242)) {
                                    if ((ListenerUtil.mutListener.listen(10240) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M || Build.MANUFACTURER.equals("Huawei")) : (Build.VERSION.SDK_INT == Build.VERSION_CODES.M && Build.MANUFACTURER.equals("Huawei")))) {
                                        if (!ListenerUtil.mutListener.listen(10241)) {
                                            // see https://dontkillmyapp.com/huawei
                                            tag = "LocationManagerService";
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(10243)) {
                                    wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, tag);
                                }
                                if (!ListenerUtil.mutListener.listen(10245)) {
                                    if (wakeLock != null) {
                                        if (!ListenerUtil.mutListener.listen(10244)) {
                                            wakeLock.acquire(DateUtils.DAY_IN_MILLIS);
                                        }
                                    }
                                }
                            }
                        }
                        // first of all, close connection
                        try {
                            if (!ListenerUtil.mutListener.listen(10249)) {
                                serviceManager.stopConnection();
                            }
                        } catch (InterruptedException e) {
                            if (!ListenerUtil.mutListener.listen(10247)) {
                                showBackupErrorNotification("BackupService interrupted");
                            }
                            if (!ListenerUtil.mutListener.listen(10248)) {
                                stopSelf();
                            }
                            return START_NOT_STICKY;
                        }
                        boolean success = false;
                        Date now = new Date();
                        DocumentFile zipFile = null;
                        Uri backupUri = this.fileService.getBackupUri();
                        if (!ListenerUtil.mutListener.listen(10252)) {
                            if (backupUri == null) {
                                if (!ListenerUtil.mutListener.listen(10250)) {
                                    showBackupErrorNotification("Destination directory has not been selected yet");
                                }
                                if (!ListenerUtil.mutListener.listen(10251)) {
                                    stopSelf();
                                }
                                return START_NOT_STICKY;
                            }
                        }
                        String filename = "threema-backup_" + userService.getIdentity() + "_" + now.getTime() + "_1";
                        if (!ListenerUtil.mutListener.listen(10262)) {
                            if (ContentResolver.SCHEME_FILE.equalsIgnoreCase(backupUri.getScheme())) {
                                if (!ListenerUtil.mutListener.listen(10260)) {
                                    zipFile = DocumentFile.fromFile(new File(backupUri.getPath(), INCOMPLETE_BACKUP_FILENAME_PREFIX + filename + ".zip"));
                                }
                                if (!ListenerUtil.mutListener.listen(10261)) {
                                    success = true;
                                }
                            } else {
                                DocumentFile directory = DocumentFile.fromTreeUri(getApplicationContext(), backupUri);
                                if (!ListenerUtil.mutListener.listen(10259)) {
                                    if ((ListenerUtil.mutListener.listen(10253) ? (directory != null || directory.exists()) : (directory != null && directory.exists()))) {
                                        try {
                                            if (!ListenerUtil.mutListener.listen(10255)) {
                                                zipFile = directory.createFile(MimeUtil.MIME_TYPE_ZIP, INCOMPLETE_BACKUP_FILENAME_PREFIX + filename);
                                            }
                                            if (!ListenerUtil.mutListener.listen(10258)) {
                                                if ((ListenerUtil.mutListener.listen(10256) ? (zipFile != null || zipFile.canWrite()) : (zipFile != null && zipFile.canWrite()))) {
                                                    if (!ListenerUtil.mutListener.listen(10257)) {
                                                        success = true;
                                                    }
                                                }
                                            }
                                        } catch (Exception e) {
                                            if (!ListenerUtil.mutListener.listen(10254)) {
                                                logger.debug("Exception", e);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(10266)) {
                            if ((ListenerUtil.mutListener.listen(10263) ? (zipFile == null && !success) : (zipFile == null || !success))) {
                                if (!ListenerUtil.mutListener.listen(10264)) {
                                    showBackupErrorNotification(getString(R.string.backup_data_no_permission));
                                }
                                if (!ListenerUtil.mutListener.listen(10265)) {
                                    stopSelf();
                                }
                                return START_NOT_STICKY;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(10267)) {
                            backupFile = zipFile;
                        }
                        if (!ListenerUtil.mutListener.listen(10269)) {
                            new AsyncTask<Void, Void, Boolean>() {

                                @Override
                                protected Boolean doInBackground(Void... params) {
                                    return backup();
                                }

                                @Override
                                protected void onPostExecute(Boolean success) {
                                    if (!ListenerUtil.mutListener.listen(10268)) {
                                        stopSelf();
                                    }
                                }
                            }.execute();
                        }
                        return START_STICKY;
                    } else {
                        if (!ListenerUtil.mutListener.listen(10233)) {
                            Toast.makeText(this, R.string.backup_data_cancelled, Toast.LENGTH_LONG).show();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10230)) {
                    logger.debug("onStartCommand intent == null");
                }
                if (!ListenerUtil.mutListener.listen(10231)) {
                    onFinished(null);
                }
            }
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(10272)) {
            logger.info("onCreate");
        }
        if (!ListenerUtil.mutListener.listen(10273)) {
            super.onCreate();
        }
        if (!ListenerUtil.mutListener.listen(10274)) {
            isRunning = true;
        }
        if (!ListenerUtil.mutListener.listen(10275)) {
            serviceManager = ThreemaApplication.getServiceManager();
        }
        if (!ListenerUtil.mutListener.listen(10277)) {
            if (serviceManager == null) {
                if (!ListenerUtil.mutListener.listen(10276)) {
                    stopSelf();
                }
                return;
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(10280)) {
                fileService = serviceManager.getFileService();
            }
            if (!ListenerUtil.mutListener.listen(10281)) {
                databaseServiceNew = serviceManager.getDatabaseServiceNew();
            }
            if (!ListenerUtil.mutListener.listen(10282)) {
                contactService = serviceManager.getContactService();
            }
            if (!ListenerUtil.mutListener.listen(10283)) {
                groupService = serviceManager.getGroupService();
            }
            if (!ListenerUtil.mutListener.listen(10284)) {
                distributionListService = serviceManager.getDistributionListService();
            }
            if (!ListenerUtil.mutListener.listen(10285)) {
                userService = serviceManager.getUserService();
            }
            if (!ListenerUtil.mutListener.listen(10286)) {
                ballotService = serviceManager.getBallotService();
            }
            if (!ListenerUtil.mutListener.listen(10287)) {
                preferenceService = serviceManager.getPreferenceService();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(10278)) {
                logger.error("Exception", e);
            }
            if (!ListenerUtil.mutListener.listen(10279)) {
                stopSelf();
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(10288)) {
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(10289)) {
            logger.info("onDestroy success={} canceled={}", backupSuccess, isCanceled);
        }
        if (!ListenerUtil.mutListener.listen(10291)) {
            if (isCanceled) {
                if (!ListenerUtil.mutListener.listen(10290)) {
                    onFinished(getString(R.string.backup_data_cancelled));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10292)) {
            super.onDestroy();
        }
    }

    @Override
    public void onLowMemory() {
        if (!ListenerUtil.mutListener.listen(10293)) {
            logger.info("onLowMemory");
        }
        if (!ListenerUtil.mutListener.listen(10294)) {
            super.onLowMemory();
        }
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        if (!ListenerUtil.mutListener.listen(10295)) {
            logger.debug("onTaskRemoved");
        }
        Intent intent = new Intent(this, DummyActivity.class);
        if (!ListenerUtil.mutListener.listen(10296)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (!ListenerUtil.mutListener.listen(10297)) {
            startActivity(intent);
        }
    }

    private int getStepFactor() {
        return this.config.backupVideoAndFiles() ? MEDIA_STEP_FACTOR_VIDEOS_AND_FILES : (this.config.backupMedia() ? MEDIA_STEP_FACTOR : (this.config.backupThumbnails() ? MEDIA_STEP_FACTOR_THUMBNAILS : 1));
    }

    private boolean backup() {
        String identity = userService.getIdentity();
        if (!ListenerUtil.mutListener.listen(10298)) {
            showPersistentNotification();
        }
        try (final ZipOutputStream zipOutputStream = ZipUtil.initializeZipOutputStream(getContentResolver(), backupFile.getUri(), config.getPassword())) {
            if (!ListenerUtil.mutListener.listen(10303)) {
                logger.debug("Creating zip file {}", backupFile.getUri());
            }
            // save settings
            RestoreSettings settings = new RestoreSettings(RestoreSettings.CURRENT_VERSION);
            ByteArrayOutputStream settingsBuffer = null;
            try {
                if (!ListenerUtil.mutListener.listen(10306)) {
                    settingsBuffer = new ByteArrayOutputStream();
                }
                CSVWriter settingsCsv = new CSVWriter(new OutputStreamWriter(settingsBuffer));
                if (!ListenerUtil.mutListener.listen(10307)) {
                    settingsCsv.writeAll(settings.toList());
                }
                if (!ListenerUtil.mutListener.listen(10308)) {
                    settingsCsv.close();
                }
            } finally {
                if (!ListenerUtil.mutListener.listen(10305)) {
                    if (settingsBuffer != null) {
                        try {
                            if (!ListenerUtil.mutListener.listen(10304)) {
                                settingsBuffer.close();
                            }
                        } catch (IOException e) {
                        }
                    }
                }
            }
            long progressContactsAndMessages = this.databaseServiceNew.getContactModelFactory().count() + this.databaseServiceNew.getMessageModelFactory().count() + this.databaseServiceNew.getGroupModelFactory().count() + this.databaseServiceNew.getGroupMessageModelFactory().count();
            long progressDistributionLists = this.databaseServiceNew.getDistributionListModelFactory().count() + this.databaseServiceNew.getDistributionListMessageModelFactory().count();
            long progressBallots = this.databaseServiceNew.getBallotModelFactory().count();
            long progress = (ListenerUtil.mutListener.listen(10320) ? ((ListenerUtil.mutListener.listen(10316) ? ((ListenerUtil.mutListener.listen(10312) ? ((this.config.backupIdentity() ? 1 : 0) % (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10311) ? ((this.config.backupIdentity() ? 1 : 0) / (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10310) ? ((this.config.backupIdentity() ? 1 : 0) * (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10309) ? ((this.config.backupIdentity() ? 1 : 0) - (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : ((this.config.backupIdentity() ? 1 : 0) + (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)))))) % (this.config.backupDistributionLists() ? progressDistributionLists : 0)) : (ListenerUtil.mutListener.listen(10315) ? ((ListenerUtil.mutListener.listen(10312) ? ((this.config.backupIdentity() ? 1 : 0) % (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10311) ? ((this.config.backupIdentity() ? 1 : 0) / (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10310) ? ((this.config.backupIdentity() ? 1 : 0) * (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10309) ? ((this.config.backupIdentity() ? 1 : 0) - (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : ((this.config.backupIdentity() ? 1 : 0) + (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)))))) / (this.config.backupDistributionLists() ? progressDistributionLists : 0)) : (ListenerUtil.mutListener.listen(10314) ? ((ListenerUtil.mutListener.listen(10312) ? ((this.config.backupIdentity() ? 1 : 0) % (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10311) ? ((this.config.backupIdentity() ? 1 : 0) / (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10310) ? ((this.config.backupIdentity() ? 1 : 0) * (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10309) ? ((this.config.backupIdentity() ? 1 : 0) - (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : ((this.config.backupIdentity() ? 1 : 0) + (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)))))) * (this.config.backupDistributionLists() ? progressDistributionLists : 0)) : (ListenerUtil.mutListener.listen(10313) ? ((ListenerUtil.mutListener.listen(10312) ? ((this.config.backupIdentity() ? 1 : 0) % (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10311) ? ((this.config.backupIdentity() ? 1 : 0) / (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10310) ? ((this.config.backupIdentity() ? 1 : 0) * (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10309) ? ((this.config.backupIdentity() ? 1 : 0) - (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : ((this.config.backupIdentity() ? 1 : 0) + (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)))))) - (this.config.backupDistributionLists() ? progressDistributionLists : 0)) : ((ListenerUtil.mutListener.listen(10312) ? ((this.config.backupIdentity() ? 1 : 0) % (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10311) ? ((this.config.backupIdentity() ? 1 : 0) / (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10310) ? ((this.config.backupIdentity() ? 1 : 0) * (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10309) ? ((this.config.backupIdentity() ? 1 : 0) - (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : ((this.config.backupIdentity() ? 1 : 0) + (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)))))) + (this.config.backupDistributionLists() ? progressDistributionLists : 0)))))) % (this.config.backupBallots() ? progressBallots : 0)) : (ListenerUtil.mutListener.listen(10319) ? ((ListenerUtil.mutListener.listen(10316) ? ((ListenerUtil.mutListener.listen(10312) ? ((this.config.backupIdentity() ? 1 : 0) % (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10311) ? ((this.config.backupIdentity() ? 1 : 0) / (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10310) ? ((this.config.backupIdentity() ? 1 : 0) * (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10309) ? ((this.config.backupIdentity() ? 1 : 0) - (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : ((this.config.backupIdentity() ? 1 : 0) + (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)))))) % (this.config.backupDistributionLists() ? progressDistributionLists : 0)) : (ListenerUtil.mutListener.listen(10315) ? ((ListenerUtil.mutListener.listen(10312) ? ((this.config.backupIdentity() ? 1 : 0) % (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10311) ? ((this.config.backupIdentity() ? 1 : 0) / (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10310) ? ((this.config.backupIdentity() ? 1 : 0) * (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10309) ? ((this.config.backupIdentity() ? 1 : 0) - (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : ((this.config.backupIdentity() ? 1 : 0) + (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)))))) / (this.config.backupDistributionLists() ? progressDistributionLists : 0)) : (ListenerUtil.mutListener.listen(10314) ? ((ListenerUtil.mutListener.listen(10312) ? ((this.config.backupIdentity() ? 1 : 0) % (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10311) ? ((this.config.backupIdentity() ? 1 : 0) / (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10310) ? ((this.config.backupIdentity() ? 1 : 0) * (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10309) ? ((this.config.backupIdentity() ? 1 : 0) - (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : ((this.config.backupIdentity() ? 1 : 0) + (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)))))) * (this.config.backupDistributionLists() ? progressDistributionLists : 0)) : (ListenerUtil.mutListener.listen(10313) ? ((ListenerUtil.mutListener.listen(10312) ? ((this.config.backupIdentity() ? 1 : 0) % (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10311) ? ((this.config.backupIdentity() ? 1 : 0) / (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10310) ? ((this.config.backupIdentity() ? 1 : 0) * (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10309) ? ((this.config.backupIdentity() ? 1 : 0) - (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : ((this.config.backupIdentity() ? 1 : 0) + (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)))))) - (this.config.backupDistributionLists() ? progressDistributionLists : 0)) : ((ListenerUtil.mutListener.listen(10312) ? ((this.config.backupIdentity() ? 1 : 0) % (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10311) ? ((this.config.backupIdentity() ? 1 : 0) / (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10310) ? ((this.config.backupIdentity() ? 1 : 0) * (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10309) ? ((this.config.backupIdentity() ? 1 : 0) - (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : ((this.config.backupIdentity() ? 1 : 0) + (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)))))) + (this.config.backupDistributionLists() ? progressDistributionLists : 0)))))) / (this.config.backupBallots() ? progressBallots : 0)) : (ListenerUtil.mutListener.listen(10318) ? ((ListenerUtil.mutListener.listen(10316) ? ((ListenerUtil.mutListener.listen(10312) ? ((this.config.backupIdentity() ? 1 : 0) % (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10311) ? ((this.config.backupIdentity() ? 1 : 0) / (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10310) ? ((this.config.backupIdentity() ? 1 : 0) * (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10309) ? ((this.config.backupIdentity() ? 1 : 0) - (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : ((this.config.backupIdentity() ? 1 : 0) + (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)))))) % (this.config.backupDistributionLists() ? progressDistributionLists : 0)) : (ListenerUtil.mutListener.listen(10315) ? ((ListenerUtil.mutListener.listen(10312) ? ((this.config.backupIdentity() ? 1 : 0) % (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10311) ? ((this.config.backupIdentity() ? 1 : 0) / (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10310) ? ((this.config.backupIdentity() ? 1 : 0) * (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10309) ? ((this.config.backupIdentity() ? 1 : 0) - (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : ((this.config.backupIdentity() ? 1 : 0) + (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)))))) / (this.config.backupDistributionLists() ? progressDistributionLists : 0)) : (ListenerUtil.mutListener.listen(10314) ? ((ListenerUtil.mutListener.listen(10312) ? ((this.config.backupIdentity() ? 1 : 0) % (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10311) ? ((this.config.backupIdentity() ? 1 : 0) / (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10310) ? ((this.config.backupIdentity() ? 1 : 0) * (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10309) ? ((this.config.backupIdentity() ? 1 : 0) - (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : ((this.config.backupIdentity() ? 1 : 0) + (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)))))) * (this.config.backupDistributionLists() ? progressDistributionLists : 0)) : (ListenerUtil.mutListener.listen(10313) ? ((ListenerUtil.mutListener.listen(10312) ? ((this.config.backupIdentity() ? 1 : 0) % (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10311) ? ((this.config.backupIdentity() ? 1 : 0) / (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10310) ? ((this.config.backupIdentity() ? 1 : 0) * (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10309) ? ((this.config.backupIdentity() ? 1 : 0) - (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : ((this.config.backupIdentity() ? 1 : 0) + (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)))))) - (this.config.backupDistributionLists() ? progressDistributionLists : 0)) : ((ListenerUtil.mutListener.listen(10312) ? ((this.config.backupIdentity() ? 1 : 0) % (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10311) ? ((this.config.backupIdentity() ? 1 : 0) / (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10310) ? ((this.config.backupIdentity() ? 1 : 0) * (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10309) ? ((this.config.backupIdentity() ? 1 : 0) - (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : ((this.config.backupIdentity() ? 1 : 0) + (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)))))) + (this.config.backupDistributionLists() ? progressDistributionLists : 0)))))) * (this.config.backupBallots() ? progressBallots : 0)) : (ListenerUtil.mutListener.listen(10317) ? ((ListenerUtil.mutListener.listen(10316) ? ((ListenerUtil.mutListener.listen(10312) ? ((this.config.backupIdentity() ? 1 : 0) % (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10311) ? ((this.config.backupIdentity() ? 1 : 0) / (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10310) ? ((this.config.backupIdentity() ? 1 : 0) * (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10309) ? ((this.config.backupIdentity() ? 1 : 0) - (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : ((this.config.backupIdentity() ? 1 : 0) + (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)))))) % (this.config.backupDistributionLists() ? progressDistributionLists : 0)) : (ListenerUtil.mutListener.listen(10315) ? ((ListenerUtil.mutListener.listen(10312) ? ((this.config.backupIdentity() ? 1 : 0) % (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10311) ? ((this.config.backupIdentity() ? 1 : 0) / (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10310) ? ((this.config.backupIdentity() ? 1 : 0) * (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10309) ? ((this.config.backupIdentity() ? 1 : 0) - (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : ((this.config.backupIdentity() ? 1 : 0) + (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)))))) / (this.config.backupDistributionLists() ? progressDistributionLists : 0)) : (ListenerUtil.mutListener.listen(10314) ? ((ListenerUtil.mutListener.listen(10312) ? ((this.config.backupIdentity() ? 1 : 0) % (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10311) ? ((this.config.backupIdentity() ? 1 : 0) / (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10310) ? ((this.config.backupIdentity() ? 1 : 0) * (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10309) ? ((this.config.backupIdentity() ? 1 : 0) - (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : ((this.config.backupIdentity() ? 1 : 0) + (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)))))) * (this.config.backupDistributionLists() ? progressDistributionLists : 0)) : (ListenerUtil.mutListener.listen(10313) ? ((ListenerUtil.mutListener.listen(10312) ? ((this.config.backupIdentity() ? 1 : 0) % (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10311) ? ((this.config.backupIdentity() ? 1 : 0) / (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10310) ? ((this.config.backupIdentity() ? 1 : 0) * (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10309) ? ((this.config.backupIdentity() ? 1 : 0) - (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : ((this.config.backupIdentity() ? 1 : 0) + (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)))))) - (this.config.backupDistributionLists() ? progressDistributionLists : 0)) : ((ListenerUtil.mutListener.listen(10312) ? ((this.config.backupIdentity() ? 1 : 0) % (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10311) ? ((this.config.backupIdentity() ? 1 : 0) / (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10310) ? ((this.config.backupIdentity() ? 1 : 0) * (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10309) ? ((this.config.backupIdentity() ? 1 : 0) - (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : ((this.config.backupIdentity() ? 1 : 0) + (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)))))) + (this.config.backupDistributionLists() ? progressDistributionLists : 0)))))) - (this.config.backupBallots() ? progressBallots : 0)) : ((ListenerUtil.mutListener.listen(10316) ? ((ListenerUtil.mutListener.listen(10312) ? ((this.config.backupIdentity() ? 1 : 0) % (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10311) ? ((this.config.backupIdentity() ? 1 : 0) / (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10310) ? ((this.config.backupIdentity() ? 1 : 0) * (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10309) ? ((this.config.backupIdentity() ? 1 : 0) - (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : ((this.config.backupIdentity() ? 1 : 0) + (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)))))) % (this.config.backupDistributionLists() ? progressDistributionLists : 0)) : (ListenerUtil.mutListener.listen(10315) ? ((ListenerUtil.mutListener.listen(10312) ? ((this.config.backupIdentity() ? 1 : 0) % (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10311) ? ((this.config.backupIdentity() ? 1 : 0) / (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10310) ? ((this.config.backupIdentity() ? 1 : 0) * (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10309) ? ((this.config.backupIdentity() ? 1 : 0) - (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : ((this.config.backupIdentity() ? 1 : 0) + (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)))))) / (this.config.backupDistributionLists() ? progressDistributionLists : 0)) : (ListenerUtil.mutListener.listen(10314) ? ((ListenerUtil.mutListener.listen(10312) ? ((this.config.backupIdentity() ? 1 : 0) % (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10311) ? ((this.config.backupIdentity() ? 1 : 0) / (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10310) ? ((this.config.backupIdentity() ? 1 : 0) * (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10309) ? ((this.config.backupIdentity() ? 1 : 0) - (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : ((this.config.backupIdentity() ? 1 : 0) + (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)))))) * (this.config.backupDistributionLists() ? progressDistributionLists : 0)) : (ListenerUtil.mutListener.listen(10313) ? ((ListenerUtil.mutListener.listen(10312) ? ((this.config.backupIdentity() ? 1 : 0) % (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10311) ? ((this.config.backupIdentity() ? 1 : 0) / (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10310) ? ((this.config.backupIdentity() ? 1 : 0) * (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10309) ? ((this.config.backupIdentity() ? 1 : 0) - (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : ((this.config.backupIdentity() ? 1 : 0) + (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)))))) - (this.config.backupDistributionLists() ? progressDistributionLists : 0)) : ((ListenerUtil.mutListener.listen(10312) ? ((this.config.backupIdentity() ? 1 : 0) % (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10311) ? ((this.config.backupIdentity() ? 1 : 0) / (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10310) ? ((this.config.backupIdentity() ? 1 : 0) * (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : (ListenerUtil.mutListener.listen(10309) ? ((this.config.backupIdentity() ? 1 : 0) - (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)) : ((this.config.backupIdentity() ? 1 : 0) + (this.config.backupContactAndMessages() ? progressContactsAndMessages : 0)))))) + (this.config.backupDistributionLists() ? progressDistributionLists : 0)))))) + (this.config.backupBallots() ? progressBallots : 0))))));
            if (!ListenerUtil.mutListener.listen(10331)) {
                if ((ListenerUtil.mutListener.listen(10321) ? (this.config.backupMedia() && this.config.backupThumbnails()) : (this.config.backupMedia() || this.config.backupThumbnails()))) {
                    try {
                        Set<MessageType> fileTypes = this.config.backupVideoAndFiles() ? MessageUtil.getFileTypes() : MessageUtil.getLowProfileMessageModelTypes();
                        MessageType[] fileTypesArray = fileTypes.toArray(new MessageType[fileTypes.size()]);
                        long mediaProgress = this.databaseServiceNew.getMessageModelFactory().countByTypes(fileTypesArray);
                        if (!ListenerUtil.mutListener.listen(10323)) {
                            mediaProgress += this.databaseServiceNew.getGroupMessageModelFactory().countByTypes(fileTypesArray);
                        }
                        if (!ListenerUtil.mutListener.listen(10325)) {
                            if (this.config.backupDistributionLists()) {
                                if (!ListenerUtil.mutListener.listen(10324)) {
                                    mediaProgress += this.databaseServiceNew.getDistributionListMessageModelFactory().countByTypes(fileTypesArray);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(10330)) {
                            progress += ((ListenerUtil.mutListener.listen(10329) ? (mediaProgress % getStepFactor()) : (ListenerUtil.mutListener.listen(10328) ? (mediaProgress / getStepFactor()) : (ListenerUtil.mutListener.listen(10327) ? (mediaProgress - getStepFactor()) : (ListenerUtil.mutListener.listen(10326) ? (mediaProgress + getStepFactor()) : (mediaProgress * getStepFactor()))))));
                        }
                    } catch (Exception x) {
                        if (!ListenerUtil.mutListener.listen(10322)) {
                            logger.error("Exception", x);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(10332)) {
                logger.debug("Calculated steps " + progress);
            }
            if (!ListenerUtil.mutListener.listen(10333)) {
                this.initProgress(progress);
            }
            if (!ListenerUtil.mutListener.listen(10334)) {
                ZipUtil.addZipStream(zipOutputStream, new ByteArrayInputStream(settingsBuffer.toByteArray()), Tags.SETTINGS_FILE_NAME);
            }
            if (!ListenerUtil.mutListener.listen(10337)) {
                if (this.config.backupIdentity()) {
                    if (!ListenerUtil.mutListener.listen(10335)) {
                        if (!this.next("backup identity")) {
                            return this.cancelBackup(backupFile);
                        }
                    }
                    byte[] privateKey = this.userService.getPrivateKey();
                    IdentityBackupGenerator identityBackupGenerator = new IdentityBackupGenerator(identity, privateKey);
                    String backupData = identityBackupGenerator.generateBackup(this.config.getPassword());
                    if (!ListenerUtil.mutListener.listen(10336)) {
                        ZipUtil.addZipStream(zipOutputStream, IOUtils.toInputStream(backupData), Tags.IDENTITY_FILE_NAME);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(10339)) {
                // backup contacts and messages
                if (this.config.backupContactAndMessages()) {
                    if (!ListenerUtil.mutListener.listen(10338)) {
                        if (!this.backupContactsAndMessages(config, zipOutputStream)) {
                            return this.cancelBackup(backupFile);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(10341)) {
                // backup groups and messages
                if (this.config.backupGroupsAndMessages()) {
                    if (!ListenerUtil.mutListener.listen(10340)) {
                        if (!this.backupGroupsAndMessages(config, zipOutputStream)) {
                            return this.cancelBackup(backupFile);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(10343)) {
                // backup distribution lists and messages
                if (this.config.backupDistributionLists()) {
                    if (!ListenerUtil.mutListener.listen(10342)) {
                        if (!this.backupDistributionListsAndMessages(config, zipOutputStream)) {
                            return this.cancelBackup(backupFile);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(10345)) {
                if (this.config.backupBallots()) {
                    if (!ListenerUtil.mutListener.listen(10344)) {
                        if (!this.backupBallots(config, zipOutputStream)) {
                            return this.cancelBackup(backupFile);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(10346)) {
                backupSuccess = true;
            }
            if (!ListenerUtil.mutListener.listen(10347)) {
                onFinished("");
            }
        } catch (final Exception e) {
            if (!ListenerUtil.mutListener.listen(10299)) {
                removeBackupFile(backupFile);
            }
            if (!ListenerUtil.mutListener.listen(10300)) {
                backupSuccess = false;
            }
            if (!ListenerUtil.mutListener.listen(10301)) {
                onFinished("Error: " + e.getMessage());
            }
            if (!ListenerUtil.mutListener.listen(10302)) {
                logger.error("Exception", e);
            }
        }
        return backupSuccess;
    }

    private boolean next(String subject) {
        return this.next(subject, 1);
    }

    private boolean next(String subject, int factor) {
        if (!ListenerUtil.mutListener.listen(10353)) {
            this.currentProgressStep += ((ListenerUtil.mutListener.listen(10352) ? (this.currentProgressStep >= this.processSteps) : (ListenerUtil.mutListener.listen(10351) ? (this.currentProgressStep <= this.processSteps) : (ListenerUtil.mutListener.listen(10350) ? (this.currentProgressStep > this.processSteps) : (ListenerUtil.mutListener.listen(10349) ? (this.currentProgressStep != this.processSteps) : (ListenerUtil.mutListener.listen(10348) ? (this.currentProgressStep == this.processSteps) : (this.currentProgressStep < this.processSteps)))))) ? factor : 0);
        }
        if (!ListenerUtil.mutListener.listen(10354)) {
            this.handleProgress();
        }
        return !isCanceled;
    }

    /**
     *  only call progress on 100 steps
     */
    private void handleProgress() {
        int p = (int) ((ListenerUtil.mutListener.listen(10362) ? ((ListenerUtil.mutListener.listen(10358) ? (100d % (double) this.processSteps) : (ListenerUtil.mutListener.listen(10357) ? (100d * (double) this.processSteps) : (ListenerUtil.mutListener.listen(10356) ? (100d - (double) this.processSteps) : (ListenerUtil.mutListener.listen(10355) ? (100d + (double) this.processSteps) : (100d / (double) this.processSteps))))) % (double) this.currentProgressStep) : (ListenerUtil.mutListener.listen(10361) ? ((ListenerUtil.mutListener.listen(10358) ? (100d % (double) this.processSteps) : (ListenerUtil.mutListener.listen(10357) ? (100d * (double) this.processSteps) : (ListenerUtil.mutListener.listen(10356) ? (100d - (double) this.processSteps) : (ListenerUtil.mutListener.listen(10355) ? (100d + (double) this.processSteps) : (100d / (double) this.processSteps))))) / (double) this.currentProgressStep) : (ListenerUtil.mutListener.listen(10360) ? ((ListenerUtil.mutListener.listen(10358) ? (100d % (double) this.processSteps) : (ListenerUtil.mutListener.listen(10357) ? (100d * (double) this.processSteps) : (ListenerUtil.mutListener.listen(10356) ? (100d - (double) this.processSteps) : (ListenerUtil.mutListener.listen(10355) ? (100d + (double) this.processSteps) : (100d / (double) this.processSteps))))) - (double) this.currentProgressStep) : (ListenerUtil.mutListener.listen(10359) ? ((ListenerUtil.mutListener.listen(10358) ? (100d % (double) this.processSteps) : (ListenerUtil.mutListener.listen(10357) ? (100d * (double) this.processSteps) : (ListenerUtil.mutListener.listen(10356) ? (100d - (double) this.processSteps) : (ListenerUtil.mutListener.listen(10355) ? (100d + (double) this.processSteps) : (100d / (double) this.processSteps))))) + (double) this.currentProgressStep) : ((ListenerUtil.mutListener.listen(10358) ? (100d % (double) this.processSteps) : (ListenerUtil.mutListener.listen(10357) ? (100d * (double) this.processSteps) : (ListenerUtil.mutListener.listen(10356) ? (100d - (double) this.processSteps) : (ListenerUtil.mutListener.listen(10355) ? (100d + (double) this.processSteps) : (100d / (double) this.processSteps))))) * (double) this.currentProgressStep))))));
        if (!ListenerUtil.mutListener.listen(10370)) {
            if ((ListenerUtil.mutListener.listen(10367) ? (p >= this.latestPercentStep) : (ListenerUtil.mutListener.listen(10366) ? (p <= this.latestPercentStep) : (ListenerUtil.mutListener.listen(10365) ? (p < this.latestPercentStep) : (ListenerUtil.mutListener.listen(10364) ? (p != this.latestPercentStep) : (ListenerUtil.mutListener.listen(10363) ? (p == this.latestPercentStep) : (p > this.latestPercentStep))))))) {
                if (!ListenerUtil.mutListener.listen(10368)) {
                    this.latestPercentStep = p;
                }
                if (!ListenerUtil.mutListener.listen(10369)) {
                    updatePersistentNotification(latestPercentStep, 100);
                }
            }
        }
    }

    private void removeBackupFile(DocumentFile zipFile) {
        if (!ListenerUtil.mutListener.listen(10374)) {
            // remove zip file
            if ((ListenerUtil.mutListener.listen(10371) ? (zipFile != null || zipFile.exists()) : (zipFile != null && zipFile.exists()))) {
                if (!ListenerUtil.mutListener.listen(10372)) {
                    logger.debug("remove " + zipFile.getUri());
                }
                if (!ListenerUtil.mutListener.listen(10373)) {
                    zipFile.delete();
                }
            }
        }
    }

    private boolean cancelBackup(DocumentFile zipFile) {
        if (!ListenerUtil.mutListener.listen(10375)) {
            removeBackupFile(zipFile);
        }
        if (!ListenerUtil.mutListener.listen(10376)) {
            backupSuccess = false;
        }
        if (!ListenerUtil.mutListener.listen(10377)) {
            onFinished(null);
        }
        return false;
    }

    private void initProgress(long steps) {
        if (!ListenerUtil.mutListener.listen(10378)) {
            this.currentProgressStep = 0;
        }
        if (!ListenerUtil.mutListener.listen(10379)) {
            this.processSteps = steps;
        }
        if (!ListenerUtil.mutListener.listen(10380)) {
            this.latestPercentStep = 0;
        }
        if (!ListenerUtil.mutListener.listen(10381)) {
            this.startTime = System.currentTimeMillis();
        }
        if (!ListenerUtil.mutListener.listen(10382)) {
            this.handleProgress();
        }
    }

    /**
     *  Create a Backup of all contacts and messages.
     *  Backup media if configured.
     */
    private boolean backupContactsAndMessages(@NonNull BackupRestoreDataConfig config, @NonNull ZipOutputStream zipOutputStream) throws ThreemaException, IOException {
        if (!ListenerUtil.mutListener.listen(10385)) {
            // first, save my own profile pic
            if (this.config.backupAvatars()) {
                try {
                    if (!ListenerUtil.mutListener.listen(10384)) {
                        ZipUtil.addZipStream(zipOutputStream, this.fileService.getContactAvatarStream(contactService.getMe()), Tags.CONTACT_AVATAR_FILE_PREFIX + contactService.getMe().getIdentity());
                    }
                } catch (IOException e) {
                    if (!ListenerUtil.mutListener.listen(10383)) {
                        logger.warn("Could not back up own avatar: {}", e.getMessage());
                    }
                }
            }
        }
        final String[] contactCsvHeader = { Tags.TAG_CONTACT_IDENTITY, Tags.TAG_CONTACT_PUBLIC_KEY, Tags.TAG_CONTACT_VERIFICATION_LEVEL, Tags.TAG_CONTACT_ANDROID_CONTACT_ID, Tags.TAG_CONTACT_THREEMA_ANDROID_CONTACT_ID, Tags.TAG_CONTACT_FIRST_NAME, Tags.TAG_CONTACT_LAST_NAME, Tags.TAG_CONTACT_NICK_NAME, Tags.TAG_CONTACT_COLOR, Tags.TAG_CONTACT_HIDDEN, Tags.TAG_CONTACT_ARCHIVED };
        final String[] messageCsvHeader = { Tags.TAG_MESSAGE_API_MESSAGE_ID, Tags.TAG_MESSAGE_UID, Tags.TAG_MESSAGE_IS_OUTBOX, Tags.TAG_MESSAGE_IS_READ, Tags.TAG_MESSAGE_IS_SAVED, Tags.TAG_MESSAGE_MESSAGE_STATE, Tags.TAG_MESSAGE_POSTED_AT, Tags.TAG_MESSAGE_CREATED_AT, Tags.TAG_MESSAGE_MODIFIED_AT, Tags.TAG_MESSAGE_TYPE, Tags.TAG_MESSAGE_BODY, Tags.TAG_MESSAGE_IS_STATUS_MESSAGE, Tags.TAG_MESSAGE_IS_QUEUED, Tags.TAG_MESSAGE_CAPTION, Tags.TAG_MESSAGE_QUOTED_MESSAGE_ID };
        // Iterate over all contacts. Then backup every contact with the corresponding messages.
        try (final ByteArrayOutputStream contactBuffer = new ByteArrayOutputStream()) {
            try (final CSVWriter contactCsv = new CSVWriter(new OutputStreamWriter(contactBuffer), contactCsvHeader)) {
                if (!ListenerUtil.mutListener.listen(10408)) {
                    {
                        long _loopCounter93 = 0;
                        for (final ContactModel contactModel : contactService.find(null)) {
                            ListenerUtil.loopListener.listen("_loopCounter93", ++_loopCounter93);
                            if (!ListenerUtil.mutListener.listen(10386)) {
                                if (!this.next("backup contact " + contactModel.getIdentity())) {
                                    return false;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(10387)) {
                                // Write contact
                                contactCsv.createRow().write(Tags.TAG_CONTACT_IDENTITY, contactModel.getIdentity()).write(Tags.TAG_CONTACT_PUBLIC_KEY, Utils.byteArrayToHexString(contactModel.getPublicKey())).write(Tags.TAG_CONTACT_VERIFICATION_LEVEL, contactModel.getVerificationLevel().toString()).write(Tags.TAG_CONTACT_ANDROID_CONTACT_ID, contactModel.getAndroidContactLookupKey()).write(Tags.TAG_CONTACT_THREEMA_ANDROID_CONTACT_ID, contactModel.getThreemaAndroidContactId()).write(Tags.TAG_CONTACT_FIRST_NAME, contactModel.getFirstName()).write(Tags.TAG_CONTACT_LAST_NAME, contactModel.getLastName()).write(Tags.TAG_CONTACT_NICK_NAME, contactModel.getPublicNickName()).write(Tags.TAG_CONTACT_COLOR, contactModel.getColor()).write(Tags.TAG_CONTACT_HIDDEN, contactModel.isHidden()).write(Tags.TAG_CONTACT_ARCHIVED, contactModel.isArchived()).write();
                            }
                            if (!ListenerUtil.mutListener.listen(10393)) {
                                // Back up contact profile pictures
                                if (this.config.backupAvatars()) {
                                    try {
                                        if (!ListenerUtil.mutListener.listen(10390)) {
                                            if (!userService.getIdentity().equals(contactModel.getIdentity())) {
                                                if (!ListenerUtil.mutListener.listen(10389)) {
                                                    ZipUtil.addZipStream(zipOutputStream, this.fileService.getContactAvatarStream(contactModel), Tags.CONTACT_AVATAR_FILE_PREFIX + contactModel.getIdentity());
                                                }
                                            }
                                        }
                                    } catch (IOException e) {
                                        if (!ListenerUtil.mutListener.listen(10388)) {
                                            // avatars are not THAT important, so we don't care if adding them fails
                                            logger.warn("Could not back up avatar for contact {}: {}", contactModel.getIdentity(), e.getMessage());
                                        }
                                    }
                                    try {
                                        if (!ListenerUtil.mutListener.listen(10392)) {
                                            ZipUtil.addZipStream(zipOutputStream, this.fileService.getContactPhotoStream(contactModel), Tags.CONTACT_PROFILE_PIC_FILE_PREFIX + contactModel.getIdentity());
                                        }
                                    } catch (IOException e) {
                                        if (!ListenerUtil.mutListener.listen(10391)) {
                                            // profile pics are not THAT important, so we don't care if adding them fails
                                            logger.warn("Could not back up profile pic for contact {}: {}", contactModel.getIdentity(), e.getMessage());
                                        }
                                    }
                                }
                            }
                            // Back up conversations
                            try (final ByteArrayOutputStream messageBuffer = new ByteArrayOutputStream()) {
                                try (final CSVWriter messageCsv = new CSVWriter(new OutputStreamWriter(messageBuffer), messageCsvHeader)) {
                                    List<MessageModel> messageModels = this.databaseServiceNew.getMessageModelFactory().getByIdentityUnsorted(contactModel.getIdentity());
                                    if (!ListenerUtil.mutListener.listen(10406)) {
                                        {
                                            long _loopCounter92 = 0;
                                            for (MessageModel messageModel : messageModels) {
                                                ListenerUtil.loopListener.listen("_loopCounter92", ++_loopCounter92);
                                                if (!ListenerUtil.mutListener.listen(10394)) {
                                                    if (!this.next("backup message " + messageModel.getId())) {
                                                        return false;
                                                    }
                                                }
                                                String apiMessageId = messageModel.getApiMessageId();
                                                if (!ListenerUtil.mutListener.listen(10403)) {
                                                    if ((ListenerUtil.mutListener.listen(10401) ? (((ListenerUtil.mutListener.listen(10400) ? (apiMessageId != null || (ListenerUtil.mutListener.listen(10399) ? (apiMessageId.length() >= 0) : (ListenerUtil.mutListener.listen(10398) ? (apiMessageId.length() <= 0) : (ListenerUtil.mutListener.listen(10397) ? (apiMessageId.length() < 0) : (ListenerUtil.mutListener.listen(10396) ? (apiMessageId.length() != 0) : (ListenerUtil.mutListener.listen(10395) ? (apiMessageId.length() == 0) : (apiMessageId.length() > 0))))))) : (apiMessageId != null && (ListenerUtil.mutListener.listen(10399) ? (apiMessageId.length() >= 0) : (ListenerUtil.mutListener.listen(10398) ? (apiMessageId.length() <= 0) : (ListenerUtil.mutListener.listen(10397) ? (apiMessageId.length() < 0) : (ListenerUtil.mutListener.listen(10396) ? (apiMessageId.length() != 0) : (ListenerUtil.mutListener.listen(10395) ? (apiMessageId.length() == 0) : (apiMessageId.length() > 0))))))))) && messageModel.getType() == MessageType.VOIP_STATUS) : (((ListenerUtil.mutListener.listen(10400) ? (apiMessageId != null || (ListenerUtil.mutListener.listen(10399) ? (apiMessageId.length() >= 0) : (ListenerUtil.mutListener.listen(10398) ? (apiMessageId.length() <= 0) : (ListenerUtil.mutListener.listen(10397) ? (apiMessageId.length() < 0) : (ListenerUtil.mutListener.listen(10396) ? (apiMessageId.length() != 0) : (ListenerUtil.mutListener.listen(10395) ? (apiMessageId.length() == 0) : (apiMessageId.length() > 0))))))) : (apiMessageId != null && (ListenerUtil.mutListener.listen(10399) ? (apiMessageId.length() >= 0) : (ListenerUtil.mutListener.listen(10398) ? (apiMessageId.length() <= 0) : (ListenerUtil.mutListener.listen(10397) ? (apiMessageId.length() < 0) : (ListenerUtil.mutListener.listen(10396) ? (apiMessageId.length() != 0) : (ListenerUtil.mutListener.listen(10395) ? (apiMessageId.length() == 0) : (apiMessageId.length() > 0))))))))) || messageModel.getType() == MessageType.VOIP_STATUS))) {
                                                        if (!ListenerUtil.mutListener.listen(10402)) {
                                                            messageCsv.createRow().write(Tags.TAG_MESSAGE_API_MESSAGE_ID, messageModel.getApiMessageId()).write(Tags.TAG_MESSAGE_UID, messageModel.getUid()).write(Tags.TAG_MESSAGE_IS_OUTBOX, messageModel.isOutbox()).write(Tags.TAG_MESSAGE_IS_READ, messageModel.isRead()).write(Tags.TAG_MESSAGE_IS_SAVED, messageModel.isSaved()).write(Tags.TAG_MESSAGE_MESSAGE_STATE, messageModel.getState()).write(Tags.TAG_MESSAGE_POSTED_AT, messageModel.getPostedAt()).write(Tags.TAG_MESSAGE_CREATED_AT, messageModel.getCreatedAt()).write(Tags.TAG_MESSAGE_MODIFIED_AT, messageModel.getModifiedAt()).write(Tags.TAG_MESSAGE_TYPE, messageModel.getType().toString()).write(Tags.TAG_MESSAGE_BODY, messageModel.getBody()).write(Tags.TAG_MESSAGE_IS_STATUS_MESSAGE, messageModel.isStatusMessage()).write(Tags.TAG_MESSAGE_IS_QUEUED, messageModel.isQueued()).write(Tags.TAG_MESSAGE_CAPTION, messageModel.getCaption()).write(Tags.TAG_MESSAGE_QUOTED_MESSAGE_ID, messageModel.getQuotedMessageId()).write();
                                                        }
                                                    }
                                                }
                                                if (!ListenerUtil.mutListener.listen(10405)) {
                                                    if (MessageUtil.hasDataFile(messageModel)) {
                                                        if (!ListenerUtil.mutListener.listen(10404)) {
                                                            this.backupMediaFile(config, zipOutputStream, Tags.MESSAGE_MEDIA_FILE_PREFIX, Tags.MESSAGE_MEDIA_THUMBNAIL_FILE_PREFIX, messageModel);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(10407)) {
                                    ZipUtil.addZipStream(zipOutputStream, new ByteArrayInputStream(messageBuffer.toByteArray()), Tags.MESSAGE_FILE_PREFIX + contactModel.getIdentity() + Tags.CSV_FILE_POSTFIX);
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(10409)) {
                ZipUtil.addZipStream(zipOutputStream, new ByteArrayInputStream(contactBuffer.toByteArray()), Tags.CONTACTS_FILE_NAME + Tags.CSV_FILE_POSTFIX);
            }
        }
        return true;
    }

    /**
     *  Backup all groups with nessages and media (if configured).
     */
    private boolean backupGroupsAndMessages(@NonNull BackupRestoreDataConfig config, @NonNull ZipOutputStream zipOutputStream) throws ThreemaException, IOException {
        final String[] groupCsvHeader = { Tags.TAG_GROUP_ID, Tags.TAG_GROUP_CREATOR, Tags.TAG_GROUP_NAME, Tags.TAG_GROUP_CREATED_AT, Tags.TAG_GROUP_MEMBERS, Tags.TAG_GROUP_DELETED, Tags.TAG_GROUP_ARCHIVED };
        final String[] groupMessageCsvHeader = { Tags.TAG_MESSAGE_API_MESSAGE_ID, Tags.TAG_MESSAGE_UID, Tags.TAG_MESSAGE_IDENTITY, Tags.TAG_MESSAGE_IS_OUTBOX, Tags.TAG_MESSAGE_IS_READ, Tags.TAG_MESSAGE_IS_SAVED, Tags.TAG_MESSAGE_MESSAGE_STATE, Tags.TAG_MESSAGE_POSTED_AT, Tags.TAG_MESSAGE_CREATED_AT, Tags.TAG_MESSAGE_MODIFIED_AT, Tags.TAG_MESSAGE_TYPE, Tags.TAG_MESSAGE_BODY, Tags.TAG_MESSAGE_IS_STATUS_MESSAGE, Tags.TAG_MESSAGE_IS_QUEUED, Tags.TAG_MESSAGE_CAPTION, Tags.TAG_MESSAGE_QUOTED_MESSAGE_ID };
        final GroupService.GroupFilter groupFilter = new GroupService.GroupFilter() {

            @Override
            public boolean sortingByDate() {
                return false;
            }

            @Override
            public boolean sortingByName() {
                return false;
            }

            @Override
            public boolean sortingAscending() {
                return false;
            }

            @Override
            public boolean withDeleted() {
                return true;
            }

            @Override
            public boolean withDeserted() {
                return true;
            }
        };
        // Iterate over all groups
        try (final ByteArrayOutputStream groupBuffer = new ByteArrayOutputStream()) {
            try (final CSVWriter groupCsv = new CSVWriter(new OutputStreamWriter(groupBuffer), groupCsvHeader)) {
                if (!ListenerUtil.mutListener.listen(10421)) {
                    {
                        long _loopCounter95 = 0;
                        for (final GroupModel groupModel : this.groupService.getAll(groupFilter)) {
                            ListenerUtil.loopListener.listen("_loopCounter95", ++_loopCounter95);
                            String groupUid = BackupUtils.buildGroupUid(groupModel);
                            if (!ListenerUtil.mutListener.listen(10410)) {
                                if (!this.next("backup group " + groupModel.getApiGroupId())) {
                                    return false;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(10411)) {
                                groupCsv.createRow().write(Tags.TAG_GROUP_ID, groupModel.getApiGroupId()).write(Tags.TAG_GROUP_CREATOR, groupModel.getCreatorIdentity()).write(Tags.TAG_GROUP_NAME, groupModel.getName()).write(Tags.TAG_GROUP_CREATED_AT, groupModel.getCreatedAt()).write(Tags.TAG_GROUP_MEMBERS, this.groupService.getGroupIdentities(groupModel)).write(Tags.TAG_GROUP_DELETED, groupModel.isDeleted()).write(Tags.TAG_GROUP_ARCHIVED, groupModel.isArchived()).write();
                            }
                            if (!ListenerUtil.mutListener.listen(10414)) {
                                // check if the group have a photo
                                if (this.config.backupAvatars()) {
                                    try {
                                        if (!ListenerUtil.mutListener.listen(10413)) {
                                            ZipUtil.addZipStream(zipOutputStream, this.fileService.getGroupAvatarStream(groupModel), Tags.GROUP_AVATAR_PREFIX + groupUid);
                                        }
                                    } catch (Exception e) {
                                        if (!ListenerUtil.mutListener.listen(10412)) {
                                            logger.warn("Could not back up group avatar: {}", e.getMessage());
                                        }
                                    }
                                }
                            }
                            // Back up group messages
                            try (final ByteArrayOutputStream groupMessageBuffer = new ByteArrayOutputStream()) {
                                try (final CSVWriter groupMessageCsv = new CSVWriter(new OutputStreamWriter(groupMessageBuffer), groupMessageCsvHeader)) {
                                    List<GroupMessageModel> groupMessageModels = this.databaseServiceNew.getGroupMessageModelFactory().getByGroupIdUnsorted(groupModel.getId());
                                    if (!ListenerUtil.mutListener.listen(10419)) {
                                        {
                                            long _loopCounter94 = 0;
                                            for (GroupMessageModel groupMessageModel : groupMessageModels) {
                                                ListenerUtil.loopListener.listen("_loopCounter94", ++_loopCounter94);
                                                if (!ListenerUtil.mutListener.listen(10415)) {
                                                    if (!this.next("backup group message " + groupMessageModel.getUid())) {
                                                        return false;
                                                    }
                                                }
                                                if (!ListenerUtil.mutListener.listen(10416)) {
                                                    groupMessageCsv.createRow().write(Tags.TAG_MESSAGE_API_MESSAGE_ID, groupMessageModel.getApiMessageId()).write(Tags.TAG_MESSAGE_UID, groupMessageModel.getUid()).write(Tags.TAG_MESSAGE_IDENTITY, groupMessageModel.getIdentity()).write(Tags.TAG_MESSAGE_IS_OUTBOX, groupMessageModel.isOutbox()).write(Tags.TAG_MESSAGE_IS_READ, groupMessageModel.isRead()).write(Tags.TAG_MESSAGE_IS_SAVED, groupMessageModel.isSaved()).write(Tags.TAG_MESSAGE_MESSAGE_STATE, groupMessageModel.getState()).write(Tags.TAG_MESSAGE_POSTED_AT, groupMessageModel.getPostedAt()).write(Tags.TAG_MESSAGE_CREATED_AT, groupMessageModel.getCreatedAt()).write(Tags.TAG_MESSAGE_MODIFIED_AT, groupMessageModel.getModifiedAt()).write(Tags.TAG_MESSAGE_TYPE, groupMessageModel.getType()).write(Tags.TAG_MESSAGE_BODY, groupMessageModel.getBody()).write(Tags.TAG_MESSAGE_IS_STATUS_MESSAGE, groupMessageModel.isStatusMessage()).write(Tags.TAG_MESSAGE_IS_QUEUED, groupMessageModel.isQueued()).write(Tags.TAG_MESSAGE_CAPTION, groupMessageModel.getCaption()).write(Tags.TAG_MESSAGE_QUOTED_MESSAGE_ID, groupMessageModel.getQuotedMessageId()).write();
                                                }
                                                if (!ListenerUtil.mutListener.listen(10418)) {
                                                    if (MessageUtil.hasDataFile(groupMessageModel)) {
                                                        if (!ListenerUtil.mutListener.listen(10417)) {
                                                            this.backupMediaFile(config, zipOutputStream, Tags.GROUP_MESSAGE_MEDIA_FILE_PREFIX, Tags.GROUP_MESSAGE_MEDIA_THUMBNAIL_FILE_PREFIX, groupMessageModel);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(10420)) {
                                    ZipUtil.addZipStream(zipOutputStream, new ByteArrayInputStream(groupMessageBuffer.toByteArray()), Tags.GROUP_MESSAGE_FILE_PREFIX + groupUid + Tags.CSV_FILE_POSTFIX);
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(10422)) {
                ZipUtil.addZipStream(zipOutputStream, new ByteArrayInputStream(groupBuffer.toByteArray()), Tags.GROUPS_FILE_NAME + Tags.CSV_FILE_POSTFIX);
            }
        }
        return true;
    }

    /**
     *  backup all ballots with votes and choices!
     */
    private boolean backupBallots(@NonNull BackupRestoreDataConfig config, @NonNull ZipOutputStream zipOutputStream) throws ThreemaException, IOException {
        final String[] ballotCsvHeader = { Tags.TAG_BALLOT_ID, Tags.TAG_BALLOT_API_ID, Tags.TAG_BALLOT_API_CREATOR, Tags.TAG_BALLOT_REF, Tags.TAG_BALLOT_REF_ID, Tags.TAG_BALLOT_NAME, Tags.TAG_BALLOT_STATE, Tags.TAG_BALLOT_ASSESSMENT, Tags.TAG_BALLOT_TYPE, Tags.TAG_BALLOT_C_TYPE, Tags.TAG_BALLOT_LAST_VIEWED_AT, Tags.TAG_BALLOT_CREATED_AT, Tags.TAG_BALLOT_MODIFIED_AT };
        final String[] ballotChoiceCsvHeader = { Tags.TAG_BALLOT_CHOICE_ID, Tags.TAG_BALLOT_CHOICE_BALLOT_UID, Tags.TAG_BALLOT_CHOICE_API_ID, Tags.TAG_BALLOT_CHOICE_TYPE, Tags.TAG_BALLOT_CHOICE_NAME, Tags.TAG_BALLOT_CHOICE_VOTE_COUNT, Tags.TAG_BALLOT_CHOICE_ORDER, Tags.TAG_BALLOT_CHOICE_CREATED_AT, Tags.TAG_BALLOT_CHOICE_MODIFIED_AT };
        final String[] ballotVoteCsvHeader = { Tags.TAG_BALLOT_VOTE_ID, Tags.TAG_BALLOT_VOTE_BALLOT_UID, Tags.TAG_BALLOT_VOTE_CHOICE_UID, Tags.TAG_BALLOT_VOTE_IDENTITY, Tags.TAG_BALLOT_VOTE_CHOICE, Tags.TAG_BALLOT_VOTE_CREATED_AT, Tags.TAG_BALLOT_VOTE_MODIFIED_AT };
        try (final ByteArrayOutputStream ballotCsvBuffer = new ByteArrayOutputStream();
            final ByteArrayOutputStream ballotChoiceCsvBuffer = new ByteArrayOutputStream();
            final ByteArrayOutputStream ballotVoteCsvBuffer = new ByteArrayOutputStream()) {
            try (final OutputStreamWriter ballotOsw = new OutputStreamWriter(ballotCsvBuffer);
                final OutputStreamWriter ballotChoiceOsw = new OutputStreamWriter(ballotChoiceCsvBuffer);
                final OutputStreamWriter ballotVoteOsw = new OutputStreamWriter(ballotVoteCsvBuffer);
                final CSVWriter ballotCsv = new CSVWriter(ballotOsw, ballotCsvHeader);
                final CSVWriter ballotChoiceCsv = new CSVWriter(ballotChoiceOsw, ballotChoiceCsvHeader);
                final CSVWriter ballotVoteCsv = new CSVWriter(ballotVoteOsw, ballotVoteCsvHeader)) {
                List<BallotModel> ballots = ballotService.getBallots(new BallotService.BallotFilter() {

                    @Override
                    public MessageReceiver getReceiver() {
                        return null;
                    }

                    @Override
                    public BallotModel.State[] getStates() {
                        return new BallotModel.State[] { BallotModel.State.OPEN, BallotModel.State.CLOSED };
                    }

                    @Override
                    public boolean filter(BallotModel ballotModel) {
                        return true;
                    }
                });
                if (!ListenerUtil.mutListener.listen(10434)) {
                    if (ballots != null) {
                        if (!ListenerUtil.mutListener.listen(10433)) {
                            {
                                long _loopCounter98 = 0;
                                for (BallotModel ballotModel : ballots) {
                                    ListenerUtil.loopListener.listen("_loopCounter98", ++_loopCounter98);
                                    if (!ListenerUtil.mutListener.listen(10423)) {
                                        if (!this.next("ballot " + ballotModel.getId())) {
                                            return false;
                                        }
                                    }
                                    LinkBallotModel link = ballotService.getLinkedBallotModel(ballotModel);
                                    if (!ListenerUtil.mutListener.listen(10424)) {
                                        if (link == null) {
                                            continue;
                                        }
                                    }
                                    String ref;
                                    String refId;
                                    if (link instanceof GroupBallotModel) {
                                        GroupModel groupModel = groupService.getById(((GroupBallotModel) link).getGroupId());
                                        if (!ListenerUtil.mutListener.listen(10426)) {
                                            if (groupModel == null) {
                                                if (!ListenerUtil.mutListener.listen(10425)) {
                                                    logger.error("invalid group for a ballot");
                                                }
                                                continue;
                                            }
                                        }
                                        ref = "GroupBallotModel";
                                        refId = BackupUtils.buildGroupUid(groupModel);
                                    } else if (link instanceof IdentityBallotModel) {
                                        ref = "IdentityBallotModel";
                                        refId = ((IdentityBallotModel) link).getIdentity();
                                    } else {
                                        continue;
                                    }
                                    if (!ListenerUtil.mutListener.listen(10427)) {
                                        ballotCsv.createRow().write(Tags.TAG_BALLOT_ID, ballotModel.getId()).write(Tags.TAG_BALLOT_API_ID, ballotModel.getApiBallotId()).write(Tags.TAG_BALLOT_API_CREATOR, ballotModel.getCreatorIdentity()).write(Tags.TAG_BALLOT_REF, ref).write(Tags.TAG_BALLOT_REF_ID, refId).write(Tags.TAG_BALLOT_NAME, ballotModel.getName()).write(Tags.TAG_BALLOT_STATE, ballotModel.getState()).write(Tags.TAG_BALLOT_ASSESSMENT, ballotModel.getAssessment()).write(Tags.TAG_BALLOT_TYPE, ballotModel.getType()).write(Tags.TAG_BALLOT_C_TYPE, ballotModel.getChoiceType()).write(Tags.TAG_BALLOT_LAST_VIEWED_AT, ballotModel.getLastViewedAt()).write(Tags.TAG_BALLOT_CREATED_AT, ballotModel.getCreatedAt()).write(Tags.TAG_BALLOT_MODIFIED_AT, ballotModel.getModifiedAt()).write();
                                    }
                                    final List<BallotChoiceModel> ballotChoiceModels = this.databaseServiceNew.getBallotChoiceModelFactory().getByBallotId(ballotModel.getId());
                                    if (!ListenerUtil.mutListener.listen(10429)) {
                                        {
                                            long _loopCounter96 = 0;
                                            for (BallotChoiceModel ballotChoiceModel : ballotChoiceModels) {
                                                ListenerUtil.loopListener.listen("_loopCounter96", ++_loopCounter96);
                                                if (!ListenerUtil.mutListener.listen(10428)) {
                                                    ballotChoiceCsv.createRow().write(Tags.TAG_BALLOT_CHOICE_ID, ballotChoiceModel.getId()).write(Tags.TAG_BALLOT_CHOICE_BALLOT_UID, BackupUtils.buildBallotUid(ballotModel)).write(Tags.TAG_BALLOT_CHOICE_API_ID, ballotChoiceModel.getApiBallotChoiceId()).write(Tags.TAG_BALLOT_CHOICE_TYPE, ballotChoiceModel.getType()).write(Tags.TAG_BALLOT_CHOICE_NAME, ballotChoiceModel.getName()).write(Tags.TAG_BALLOT_CHOICE_VOTE_COUNT, ballotChoiceModel.getVoteCount()).write(Tags.TAG_BALLOT_CHOICE_ORDER, ballotChoiceModel.getOrder()).write(Tags.TAG_BALLOT_CHOICE_CREATED_AT, ballotChoiceModel.getCreatedAt()).write(Tags.TAG_BALLOT_CHOICE_MODIFIED_AT, ballotChoiceModel.getModifiedAt()).write();
                                                }
                                            }
                                        }
                                    }
                                    final List<BallotVoteModel> ballotVoteModels = this.databaseServiceNew.getBallotVoteModelFactory().getByBallotId(ballotModel.getId());
                                    if (!ListenerUtil.mutListener.listen(10432)) {
                                        {
                                            long _loopCounter97 = 0;
                                            for (final BallotVoteModel ballotVoteModel : ballotVoteModels) {
                                                ListenerUtil.loopListener.listen("_loopCounter97", ++_loopCounter97);
                                                BallotChoiceModel ballotChoiceModel = Functional.select(ballotChoiceModels, new IPredicateNonNull<BallotChoiceModel>() {

                                                    @Override
                                                    public boolean apply(@NonNull BallotChoiceModel type) {
                                                        return type.getId() == ballotVoteModel.getBallotChoiceId();
                                                    }
                                                });
                                                if (!ListenerUtil.mutListener.listen(10430)) {
                                                    if (ballotChoiceModel == null) {
                                                        continue;
                                                    }
                                                }
                                                if (!ListenerUtil.mutListener.listen(10431)) {
                                                    ballotVoteCsv.createRow().write(Tags.TAG_BALLOT_VOTE_ID, ballotVoteModel.getId()).write(Tags.TAG_BALLOT_VOTE_BALLOT_UID, BackupUtils.buildBallotUid(ballotModel)).write(Tags.TAG_BALLOT_VOTE_CHOICE_UID, BackupUtils.buildBallotChoiceUid(ballotChoiceModel)).write(Tags.TAG_BALLOT_VOTE_IDENTITY, ballotVoteModel.getVotingIdentity()).write(Tags.TAG_BALLOT_VOTE_CHOICE, ballotVoteModel.getChoice()).write(Tags.TAG_BALLOT_VOTE_CREATED_AT, ballotVoteModel.getCreatedAt()).write(Tags.TAG_BALLOT_VOTE_MODIFIED_AT, ballotVoteModel.getModifiedAt()).write();
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
            if (!ListenerUtil.mutListener.listen(10435)) {
                ZipUtil.addZipStream(zipOutputStream, new ByteArrayInputStream(ballotCsvBuffer.toByteArray()), Tags.BALLOT_FILE_NAME + Tags.CSV_FILE_POSTFIX);
            }
            if (!ListenerUtil.mutListener.listen(10436)) {
                ZipUtil.addZipStream(zipOutputStream, new ByteArrayInputStream(ballotChoiceCsvBuffer.toByteArray()), Tags.BALLOT_CHOICE_FILE_NAME + Tags.CSV_FILE_POSTFIX);
            }
            if (!ListenerUtil.mutListener.listen(10437)) {
                ZipUtil.addZipStream(zipOutputStream, new ByteArrayInputStream(ballotVoteCsvBuffer.toByteArray()), Tags.BALLOT_VOTE_FILE_NAME + Tags.CSV_FILE_POSTFIX);
            }
        }
        return true;
    }

    /**
     *  Create the distribution list zip file.
     */
    private boolean backupDistributionListsAndMessages(@NonNull BackupRestoreDataConfig config, @NonNull ZipOutputStream zipOutputStream) throws ThreemaException, IOException {
        final String[] distributionListCsvHeader = { Tags.TAG_DISTRIBUTION_LIST_ID, Tags.TAG_DISTRIBUTION_LIST_NAME, Tags.TAG_DISTRIBUTION_CREATED_AT, Tags.TAG_DISTRIBUTION_MEMBERS, Tags.TAG_DISTRIBUTION_LIST_ARCHIVED };
        final String[] distributionListMessageCsvHeader = { Tags.TAG_MESSAGE_API_MESSAGE_ID, Tags.TAG_MESSAGE_UID, Tags.TAG_MESSAGE_IDENTITY, Tags.TAG_MESSAGE_IS_OUTBOX, Tags.TAG_MESSAGE_IS_READ, Tags.TAG_MESSAGE_IS_SAVED, Tags.TAG_MESSAGE_MESSAGE_STATE, Tags.TAG_MESSAGE_POSTED_AT, Tags.TAG_MESSAGE_CREATED_AT, Tags.TAG_MESSAGE_MODIFIED_AT, Tags.TAG_MESSAGE_TYPE, Tags.TAG_MESSAGE_BODY, Tags.TAG_MESSAGE_IS_STATUS_MESSAGE, Tags.TAG_MESSAGE_IS_QUEUED, Tags.TAG_MESSAGE_CAPTION, Tags.TAG_MESSAGE_QUOTED_MESSAGE_ID };
        try (final ByteArrayOutputStream distributionListBuffer = new ByteArrayOutputStream()) {
            try (final CSVWriter distributionListCsv = new CSVWriter(new OutputStreamWriter(distributionListBuffer), distributionListCsvHeader)) {
                if (!ListenerUtil.mutListener.listen(10453)) {
                    {
                        long _loopCounter100 = 0;
                        for (DistributionListModel distributionListModel : distributionListService.getAll()) {
                            ListenerUtil.loopListener.listen("_loopCounter100", ++_loopCounter100);
                            if (!ListenerUtil.mutListener.listen(10438)) {
                                if (!this.next("distribution list " + distributionListModel.getId())) {
                                    return false;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(10439)) {
                                distributionListCsv.createRow().write(Tags.TAG_DISTRIBUTION_LIST_ID, distributionListModel.getId()).write(Tags.TAG_DISTRIBUTION_LIST_NAME, distributionListModel.getName()).write(Tags.TAG_DISTRIBUTION_CREATED_AT, distributionListModel.getCreatedAt()).write(Tags.TAG_DISTRIBUTION_MEMBERS, distributionListService.getDistributionListIdentities(distributionListModel)).write(Tags.TAG_DISTRIBUTION_LIST_ARCHIVED, distributionListModel.isArchived()).write();
                            }
                            try (final ByteArrayOutputStream messageBuffer = new ByteArrayOutputStream()) {
                                try (final CSVWriter distributionListMessageCsv = new CSVWriter(new OutputStreamWriter(messageBuffer), distributionListMessageCsvHeader)) {
                                    final List<DistributionListMessageModel> distributionListMessageModels = this.databaseServiceNew.getDistributionListMessageModelFactory().getByDistributionListIdUnsorted(distributionListModel.getId());
                                    if (!ListenerUtil.mutListener.listen(10451)) {
                                        {
                                            long _loopCounter99 = 0;
                                            for (DistributionListMessageModel distributionListMessageModel : distributionListMessageModels) {
                                                ListenerUtil.loopListener.listen("_loopCounter99", ++_loopCounter99);
                                                String apiMessageId = distributionListMessageModel.getApiMessageId();
                                                if (!ListenerUtil.mutListener.listen(10440)) {
                                                    if (!this.next("distribution list message " + distributionListMessageModel.getId())) {
                                                        return false;
                                                    }
                                                }
                                                if (!ListenerUtil.mutListener.listen(10448)) {
                                                    if ((ListenerUtil.mutListener.listen(10446) ? (apiMessageId != null || (ListenerUtil.mutListener.listen(10445) ? (apiMessageId.length() >= 0) : (ListenerUtil.mutListener.listen(10444) ? (apiMessageId.length() <= 0) : (ListenerUtil.mutListener.listen(10443) ? (apiMessageId.length() < 0) : (ListenerUtil.mutListener.listen(10442) ? (apiMessageId.length() != 0) : (ListenerUtil.mutListener.listen(10441) ? (apiMessageId.length() == 0) : (apiMessageId.length() > 0))))))) : (apiMessageId != null && (ListenerUtil.mutListener.listen(10445) ? (apiMessageId.length() >= 0) : (ListenerUtil.mutListener.listen(10444) ? (apiMessageId.length() <= 0) : (ListenerUtil.mutListener.listen(10443) ? (apiMessageId.length() < 0) : (ListenerUtil.mutListener.listen(10442) ? (apiMessageId.length() != 0) : (ListenerUtil.mutListener.listen(10441) ? (apiMessageId.length() == 0) : (apiMessageId.length() > 0))))))))) {
                                                        if (!ListenerUtil.mutListener.listen(10447)) {
                                                            distributionListMessageCsv.createRow().write(Tags.TAG_MESSAGE_API_MESSAGE_ID, distributionListMessageModel.getApiMessageId()).write(Tags.TAG_MESSAGE_UID, distributionListMessageModel.getUid()).write(Tags.TAG_MESSAGE_IDENTITY, distributionListMessageModel.getIdentity()).write(Tags.TAG_MESSAGE_IS_OUTBOX, distributionListMessageModel.isOutbox()).write(Tags.TAG_MESSAGE_IS_READ, distributionListMessageModel.isRead()).write(Tags.TAG_MESSAGE_IS_SAVED, distributionListMessageModel.isSaved()).write(Tags.TAG_MESSAGE_MESSAGE_STATE, distributionListMessageModel.getState()).write(Tags.TAG_MESSAGE_POSTED_AT, distributionListMessageModel.getPostedAt()).write(Tags.TAG_MESSAGE_CREATED_AT, distributionListMessageModel.getCreatedAt()).write(Tags.TAG_MESSAGE_MODIFIED_AT, distributionListMessageModel.getModifiedAt()).write(Tags.TAG_MESSAGE_TYPE, distributionListMessageModel.getType()).write(Tags.TAG_MESSAGE_BODY, distributionListMessageModel.getBody()).write(Tags.TAG_MESSAGE_IS_STATUS_MESSAGE, distributionListMessageModel.isStatusMessage()).write(Tags.TAG_MESSAGE_IS_QUEUED, distributionListMessageModel.isQueued()).write(Tags.TAG_MESSAGE_CAPTION, distributionListMessageModel.getCaption()).write(Tags.TAG_MESSAGE_QUOTED_MESSAGE_ID, distributionListMessageModel.getQuotedMessageId()).write();
                                                        }
                                                    }
                                                }
                                                if (!ListenerUtil.mutListener.listen(10450)) {
                                                    switch(distributionListMessageModel.getType()) {
                                                        case VIDEO:
                                                        case VOICEMESSAGE:
                                                        case IMAGE:
                                                            if (!ListenerUtil.mutListener.listen(10449)) {
                                                                this.backupMediaFile(config, zipOutputStream, Tags.DISTRIBUTION_LIST_MESSAGE_MEDIA_FILE_PREFIX, Tags.DISTRIBUTION_LIST_MESSAGE_MEDIA_THUMBNAIL_FILE_PREFIX, distributionListMessageModel);
                                                            }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(10452)) {
                                    ZipUtil.addZipStream(zipOutputStream, new ByteArrayInputStream(messageBuffer.toByteArray()), Tags.DISTRIBUTION_LIST_MESSAGE_FILE_PREFIX + distributionListModel.getId() + Tags.CSV_FILE_POSTFIX);
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(10454)) {
                ZipUtil.addZipStream(zipOutputStream, new ByteArrayInputStream(distributionListBuffer.toByteArray()), Tags.DISTRIBUTION_LISTS_FILE_NAME + Tags.CSV_FILE_POSTFIX);
            }
        }
        return true;
    }

    /**
     *  Backup all media files of the given AbstractMessageModel
     */
    private boolean backupMediaFile(@NonNull BackupRestoreDataConfig config, ZipOutputStream zipOutputStream, String filePrefix, String thumbnailFilePrefix, AbstractMessageModel messageModel) {
        if ((ListenerUtil.mutListener.listen(10455) ? (messageModel == null && !MessageUtil.hasDataFile(messageModel)) : (messageModel == null || !MessageUtil.hasDataFile(messageModel)))) {
            // its not a message model or a media message model
            return false;
        }
        if (!this.next("media " + messageModel.getId(), getStepFactor())) {
            return false;
        }
        try {
            boolean saveMedia = false;
            boolean saveThumbnail = true;
            switch(messageModel.getType()) {
                case IMAGE:
                    if (!ListenerUtil.mutListener.listen(10457)) {
                        saveMedia = config.backupMedia();
                    }
                    if (!ListenerUtil.mutListener.listen(10458)) {
                        // image thumbnails will be generated again on restore - no need to save
                        saveThumbnail = !saveMedia;
                    }
                    break;
                case VIDEO:
                    if (!ListenerUtil.mutListener.listen(10461)) {
                        if (config.backupVideoAndFiles()) {
                            VideoDataModel videoDataModel = messageModel.getVideoData();
                            if (!ListenerUtil.mutListener.listen(10460)) {
                                saveMedia = (ListenerUtil.mutListener.listen(10459) ? (videoDataModel != null || videoDataModel.isDownloaded()) : (videoDataModel != null && videoDataModel.isDownloaded()));
                            }
                        }
                    }
                    break;
                case VOICEMESSAGE:
                    if (!ListenerUtil.mutListener.listen(10464)) {
                        if (config.backupMedia()) {
                            AudioDataModel audioDataModel = messageModel.getAudioData();
                            if (!ListenerUtil.mutListener.listen(10463)) {
                                saveMedia = (ListenerUtil.mutListener.listen(10462) ? (audioDataModel != null || audioDataModel.isDownloaded()) : (audioDataModel != null && audioDataModel.isDownloaded()));
                            }
                        }
                    }
                    break;
                case FILE:
                    if (!ListenerUtil.mutListener.listen(10467)) {
                        if (config.backupVideoAndFiles()) {
                            FileDataModel fileDataModel = messageModel.getFileData();
                            if (!ListenerUtil.mutListener.listen(10466)) {
                                saveMedia = (ListenerUtil.mutListener.listen(10465) ? (fileDataModel != null || fileDataModel.isDownloaded()) : (fileDataModel != null && fileDataModel.isDownloaded()));
                            }
                        }
                    }
                    break;
                default:
                    return false;
            }
            if (!ListenerUtil.mutListener.listen(10472)) {
                if (saveMedia) {
                    InputStream is = this.fileService.getDecryptedMessageStream(messageModel);
                    if (!ListenerUtil.mutListener.listen(10471)) {
                        if (is != null) {
                            if (!ListenerUtil.mutListener.listen(10470)) {
                                ZipUtil.addZipStream(zipOutputStream, is, filePrefix + messageModel.getUid());
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(10468)) {
                                logger.debug("Can't add media for message " + messageModel.getUid() + " (" + messageModel.getPostedAt().toString() + "): missing file");
                            }
                            if (!ListenerUtil.mutListener.listen(10469)) {
                                // try to save thumbnail if media is missing
                                saveThumbnail = true;
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(10476)) {
                if ((ListenerUtil.mutListener.listen(10473) ? (config.backupThumbnails() || saveThumbnail) : (config.backupThumbnails() && saveThumbnail))) {
                    // save thumbnail every time (if a thumbnail exists)
                    InputStream is = this.fileService.getDecryptedMessageThumbnailStream(messageModel);
                    if (!ListenerUtil.mutListener.listen(10475)) {
                        if (is != null) {
                            if (!ListenerUtil.mutListener.listen(10474)) {
                                ZipUtil.addZipStream(zipOutputStream, is, thumbnailFilePrefix + messageModel.getUid());
                            }
                        }
                    }
                }
            }
            return true;
        } catch (Exception x) {
            if (!ListenerUtil.mutListener.listen(10456)) {
                // do not abort, its only a media :-)
                logger.debug("Can't add media for message " + messageModel.getUid() + " (" + messageModel.getPostedAt().toString() + "): " + x.getMessage());
            }
            return false;
        }
    }

    public void onFinished(@Nullable String message) {
        if (!ListenerUtil.mutListener.listen(10479)) {
            if (TextUtils.isEmpty(message)) {
                if (!ListenerUtil.mutListener.listen(10478)) {
                    logger.debug("onFinished (success={})", backupSuccess);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10477)) {
                    logger.debug("onFinished (success={}): {}", backupSuccess, message);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10480)) {
            cancelPersistentNotification();
        }
        if (!ListenerUtil.mutListener.listen(10495)) {
            if (backupSuccess) {
                if (!ListenerUtil.mutListener.listen(10483)) {
                    // hacky, hacky: delay success notification for a few seconds to allow file system to settle.
                    SystemClock.sleep(FILE_SETTLE_DELAY);
                }
                if (!ListenerUtil.mutListener.listen(10494)) {
                    if (backupFile != null) {
                        // Rename to reflect that the backup has been completed successfully
                        final String filename = backupFile.getName();
                        if (!ListenerUtil.mutListener.listen(10493)) {
                            if ((ListenerUtil.mutListener.listen(10486) ? (filename != null || backupFile.renameTo(filename.replace(INCOMPLETE_BACKUP_FILENAME_PREFIX, ""))) : (filename != null && backupFile.renameTo(filename.replace(INCOMPLETE_BACKUP_FILENAME_PREFIX, ""))))) {
                                if (!ListenerUtil.mutListener.listen(10489)) {
                                    // make sure media scanner sees this file
                                    logger.debug("Sending media scanner broadcast");
                                }
                                if (!ListenerUtil.mutListener.listen(10490)) {
                                    sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, backupFile.getUri()));
                                }
                                if (!ListenerUtil.mutListener.listen(10491)) {
                                    // Completed successfully!
                                    preferenceService.setLastDataBackupDate(new Date());
                                }
                                if (!ListenerUtil.mutListener.listen(10492)) {
                                    showBackupSuccessNotification();
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(10487)) {
                                    logger.error("Backup failed: File could not be renamed");
                                }
                                if (!ListenerUtil.mutListener.listen(10488)) {
                                    showBackupErrorNotification(null);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(10484)) {
                            logger.error("Backup failed: File does not exist");
                        }
                        if (!ListenerUtil.mutListener.listen(10485)) {
                            showBackupErrorNotification(null);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10481)) {
                    logger.error("Backup failed: {}", message);
                }
                if (!ListenerUtil.mutListener.listen(10482)) {
                    showBackupErrorNotification(message);
                }
            }
        }
        // try to reopen connection
        try {
            if (!ListenerUtil.mutListener.listen(10498)) {
                if (serviceManager != null) {
                    if (!ListenerUtil.mutListener.listen(10497)) {
                        serviceManager.startConnection();
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(10496)) {
                logger.error("Exception", e);
            }
        }
        if (!ListenerUtil.mutListener.listen(10502)) {
            if ((ListenerUtil.mutListener.listen(10499) ? (wakeLock != null || wakeLock.isHeld()) : (wakeLock != null && wakeLock.isHeld()))) {
                if (!ListenerUtil.mutListener.listen(10500)) {
                    logger.debug("Releasing wakelock");
                }
                if (!ListenerUtil.mutListener.listen(10501)) {
                    wakeLock.release();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10503)) {
            stopForeground(true);
        }
        if (!ListenerUtil.mutListener.listen(10504)) {
            isRunning = false;
        }
        if (!ListenerUtil.mutListener.listen(10505)) {
            // ConfigUtils.scheduleAppRestart(getApplicationContext(), getApplicationContext().getResources().getString(R.string.ipv6_restart_now));
            stopSelf();
        }
    }

    private void showPersistentNotification() {
        if (!ListenerUtil.mutListener.listen(10506)) {
            logger.debug("showPersistentNotification");
        }
        Intent cancelIntent = new Intent(this, BackupService.class);
        if (!ListenerUtil.mutListener.listen(10507)) {
            cancelIntent.putExtra(EXTRA_ID_CANCEL, true);
        }
        PendingIntent cancelPendingIntent;
        if ((ListenerUtil.mutListener.listen(10512) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(10511) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(10510) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(10509) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(10508) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O))))))) {
            cancelPendingIntent = PendingIntent.getForegroundService(this, (int) System.currentTimeMillis(), cancelIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        } else {
            cancelPendingIntent = PendingIntent.getService(this, (int) System.currentTimeMillis(), cancelIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        }
        if (!ListenerUtil.mutListener.listen(10513)) {
            notificationBuilder = new NotificationBuilderWrapper(this, NOTIFICATION_CHANNEL_BACKUP_RESTORE_IN_PROGRESS, null).setContentTitle(getString(R.string.backup_in_progress)).setContentText(getString(R.string.please_wait)).setOngoing(true).setSmallIcon(R.drawable.ic_notification_small).setPriority(NotificationCompat.PRIORITY_DEFAULT).addAction(R.drawable.ic_close_white_24dp, getString(R.string.cancel), cancelPendingIntent);
        }
        Notification notification = notificationBuilder.build();
        if (!ListenerUtil.mutListener.listen(10514)) {
            startForeground(BACKUP_NOTIFICATION_ID, notification);
        }
    }

    private void updatePersistentNotification(int currentStep, int steps) {
        if (!ListenerUtil.mutListener.listen(10515)) {
            logger.debug("updatePersistentNoti " + currentStep + " of " + steps);
        }
        if (!ListenerUtil.mutListener.listen(10546)) {
            if ((ListenerUtil.mutListener.listen(10520) ? (currentStep >= 0) : (ListenerUtil.mutListener.listen(10519) ? (currentStep <= 0) : (ListenerUtil.mutListener.listen(10518) ? (currentStep > 0) : (ListenerUtil.mutListener.listen(10517) ? (currentStep < 0) : (ListenerUtil.mutListener.listen(10516) ? (currentStep == 0) : (currentStep != 0))))))) {
                final long millisPassed = (ListenerUtil.mutListener.listen(10524) ? (System.currentTimeMillis() % startTime) : (ListenerUtil.mutListener.listen(10523) ? (System.currentTimeMillis() / startTime) : (ListenerUtil.mutListener.listen(10522) ? (System.currentTimeMillis() * startTime) : (ListenerUtil.mutListener.listen(10521) ? (System.currentTimeMillis() + startTime) : (System.currentTimeMillis() - startTime)))));
                final long millisRemaining = (ListenerUtil.mutListener.listen(10540) ? ((ListenerUtil.mutListener.listen(10536) ? ((ListenerUtil.mutListener.listen(10532) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) % currentStep) : (ListenerUtil.mutListener.listen(10531) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) * currentStep) : (ListenerUtil.mutListener.listen(10530) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) - currentStep) : (ListenerUtil.mutListener.listen(10529) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) + currentStep) : ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) / currentStep))))) % millisPassed) : (ListenerUtil.mutListener.listen(10535) ? ((ListenerUtil.mutListener.listen(10532) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) % currentStep) : (ListenerUtil.mutListener.listen(10531) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) * currentStep) : (ListenerUtil.mutListener.listen(10530) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) - currentStep) : (ListenerUtil.mutListener.listen(10529) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) + currentStep) : ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) / currentStep))))) / millisPassed) : (ListenerUtil.mutListener.listen(10534) ? ((ListenerUtil.mutListener.listen(10532) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) % currentStep) : (ListenerUtil.mutListener.listen(10531) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) * currentStep) : (ListenerUtil.mutListener.listen(10530) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) - currentStep) : (ListenerUtil.mutListener.listen(10529) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) + currentStep) : ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) / currentStep))))) * millisPassed) : (ListenerUtil.mutListener.listen(10533) ? ((ListenerUtil.mutListener.listen(10532) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) % currentStep) : (ListenerUtil.mutListener.listen(10531) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) * currentStep) : (ListenerUtil.mutListener.listen(10530) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) - currentStep) : (ListenerUtil.mutListener.listen(10529) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) + currentStep) : ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) / currentStep))))) + millisPassed) : ((ListenerUtil.mutListener.listen(10532) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) % currentStep) : (ListenerUtil.mutListener.listen(10531) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) * currentStep) : (ListenerUtil.mutListener.listen(10530) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) - currentStep) : (ListenerUtil.mutListener.listen(10529) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) + currentStep) : ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) / currentStep))))) - millisPassed))))) % FILE_SETTLE_DELAY) : (ListenerUtil.mutListener.listen(10539) ? ((ListenerUtil.mutListener.listen(10536) ? ((ListenerUtil.mutListener.listen(10532) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) % currentStep) : (ListenerUtil.mutListener.listen(10531) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) * currentStep) : (ListenerUtil.mutListener.listen(10530) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) - currentStep) : (ListenerUtil.mutListener.listen(10529) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) + currentStep) : ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) / currentStep))))) % millisPassed) : (ListenerUtil.mutListener.listen(10535) ? ((ListenerUtil.mutListener.listen(10532) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) % currentStep) : (ListenerUtil.mutListener.listen(10531) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) * currentStep) : (ListenerUtil.mutListener.listen(10530) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) - currentStep) : (ListenerUtil.mutListener.listen(10529) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) + currentStep) : ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) / currentStep))))) / millisPassed) : (ListenerUtil.mutListener.listen(10534) ? ((ListenerUtil.mutListener.listen(10532) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) % currentStep) : (ListenerUtil.mutListener.listen(10531) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) * currentStep) : (ListenerUtil.mutListener.listen(10530) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) - currentStep) : (ListenerUtil.mutListener.listen(10529) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) + currentStep) : ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) / currentStep))))) * millisPassed) : (ListenerUtil.mutListener.listen(10533) ? ((ListenerUtil.mutListener.listen(10532) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) % currentStep) : (ListenerUtil.mutListener.listen(10531) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) * currentStep) : (ListenerUtil.mutListener.listen(10530) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) - currentStep) : (ListenerUtil.mutListener.listen(10529) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) + currentStep) : ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) / currentStep))))) + millisPassed) : ((ListenerUtil.mutListener.listen(10532) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) % currentStep) : (ListenerUtil.mutListener.listen(10531) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) * currentStep) : (ListenerUtil.mutListener.listen(10530) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) - currentStep) : (ListenerUtil.mutListener.listen(10529) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) + currentStep) : ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) / currentStep))))) - millisPassed))))) / FILE_SETTLE_DELAY) : (ListenerUtil.mutListener.listen(10538) ? ((ListenerUtil.mutListener.listen(10536) ? ((ListenerUtil.mutListener.listen(10532) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) % currentStep) : (ListenerUtil.mutListener.listen(10531) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) * currentStep) : (ListenerUtil.mutListener.listen(10530) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) - currentStep) : (ListenerUtil.mutListener.listen(10529) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) + currentStep) : ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) / currentStep))))) % millisPassed) : (ListenerUtil.mutListener.listen(10535) ? ((ListenerUtil.mutListener.listen(10532) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) % currentStep) : (ListenerUtil.mutListener.listen(10531) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) * currentStep) : (ListenerUtil.mutListener.listen(10530) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) - currentStep) : (ListenerUtil.mutListener.listen(10529) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) + currentStep) : ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) / currentStep))))) / millisPassed) : (ListenerUtil.mutListener.listen(10534) ? ((ListenerUtil.mutListener.listen(10532) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) % currentStep) : (ListenerUtil.mutListener.listen(10531) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) * currentStep) : (ListenerUtil.mutListener.listen(10530) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) - currentStep) : (ListenerUtil.mutListener.listen(10529) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) + currentStep) : ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) / currentStep))))) * millisPassed) : (ListenerUtil.mutListener.listen(10533) ? ((ListenerUtil.mutListener.listen(10532) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) % currentStep) : (ListenerUtil.mutListener.listen(10531) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) * currentStep) : (ListenerUtil.mutListener.listen(10530) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) - currentStep) : (ListenerUtil.mutListener.listen(10529) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) + currentStep) : ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) / currentStep))))) + millisPassed) : ((ListenerUtil.mutListener.listen(10532) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) % currentStep) : (ListenerUtil.mutListener.listen(10531) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) * currentStep) : (ListenerUtil.mutListener.listen(10530) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) - currentStep) : (ListenerUtil.mutListener.listen(10529) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) + currentStep) : ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) / currentStep))))) - millisPassed))))) * FILE_SETTLE_DELAY) : (ListenerUtil.mutListener.listen(10537) ? ((ListenerUtil.mutListener.listen(10536) ? ((ListenerUtil.mutListener.listen(10532) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) % currentStep) : (ListenerUtil.mutListener.listen(10531) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) * currentStep) : (ListenerUtil.mutListener.listen(10530) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) - currentStep) : (ListenerUtil.mutListener.listen(10529) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) + currentStep) : ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) / currentStep))))) % millisPassed) : (ListenerUtil.mutListener.listen(10535) ? ((ListenerUtil.mutListener.listen(10532) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) % currentStep) : (ListenerUtil.mutListener.listen(10531) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) * currentStep) : (ListenerUtil.mutListener.listen(10530) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) - currentStep) : (ListenerUtil.mutListener.listen(10529) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) + currentStep) : ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) / currentStep))))) / millisPassed) : (ListenerUtil.mutListener.listen(10534) ? ((ListenerUtil.mutListener.listen(10532) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) % currentStep) : (ListenerUtil.mutListener.listen(10531) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) * currentStep) : (ListenerUtil.mutListener.listen(10530) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) - currentStep) : (ListenerUtil.mutListener.listen(10529) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) + currentStep) : ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) / currentStep))))) * millisPassed) : (ListenerUtil.mutListener.listen(10533) ? ((ListenerUtil.mutListener.listen(10532) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) % currentStep) : (ListenerUtil.mutListener.listen(10531) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) * currentStep) : (ListenerUtil.mutListener.listen(10530) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) - currentStep) : (ListenerUtil.mutListener.listen(10529) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) + currentStep) : ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) / currentStep))))) + millisPassed) : ((ListenerUtil.mutListener.listen(10532) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) % currentStep) : (ListenerUtil.mutListener.listen(10531) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) * currentStep) : (ListenerUtil.mutListener.listen(10530) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) - currentStep) : (ListenerUtil.mutListener.listen(10529) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) + currentStep) : ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) / currentStep))))) - millisPassed))))) - FILE_SETTLE_DELAY) : ((ListenerUtil.mutListener.listen(10536) ? ((ListenerUtil.mutListener.listen(10532) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) % currentStep) : (ListenerUtil.mutListener.listen(10531) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) * currentStep) : (ListenerUtil.mutListener.listen(10530) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) - currentStep) : (ListenerUtil.mutListener.listen(10529) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) + currentStep) : ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) / currentStep))))) % millisPassed) : (ListenerUtil.mutListener.listen(10535) ? ((ListenerUtil.mutListener.listen(10532) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) % currentStep) : (ListenerUtil.mutListener.listen(10531) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) * currentStep) : (ListenerUtil.mutListener.listen(10530) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) - currentStep) : (ListenerUtil.mutListener.listen(10529) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) + currentStep) : ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) / currentStep))))) / millisPassed) : (ListenerUtil.mutListener.listen(10534) ? ((ListenerUtil.mutListener.listen(10532) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) % currentStep) : (ListenerUtil.mutListener.listen(10531) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) * currentStep) : (ListenerUtil.mutListener.listen(10530) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) - currentStep) : (ListenerUtil.mutListener.listen(10529) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) + currentStep) : ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) / currentStep))))) * millisPassed) : (ListenerUtil.mutListener.listen(10533) ? ((ListenerUtil.mutListener.listen(10532) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) % currentStep) : (ListenerUtil.mutListener.listen(10531) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) * currentStep) : (ListenerUtil.mutListener.listen(10530) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) - currentStep) : (ListenerUtil.mutListener.listen(10529) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) + currentStep) : ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) / currentStep))))) + millisPassed) : ((ListenerUtil.mutListener.listen(10532) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) % currentStep) : (ListenerUtil.mutListener.listen(10531) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) * currentStep) : (ListenerUtil.mutListener.listen(10530) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) - currentStep) : (ListenerUtil.mutListener.listen(10529) ? ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) + currentStep) : ((ListenerUtil.mutListener.listen(10528) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(10527) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(10526) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(10525) ? (millisPassed + steps) : (millisPassed * steps))))) / currentStep))))) - millisPassed))))) + FILE_SETTLE_DELAY)))));
                String timeRemaining = StringConversionUtil.secondsToString((ListenerUtil.mutListener.listen(10544) ? (millisRemaining % DateUtils.SECOND_IN_MILLIS) : (ListenerUtil.mutListener.listen(10543) ? (millisRemaining * DateUtils.SECOND_IN_MILLIS) : (ListenerUtil.mutListener.listen(10542) ? (millisRemaining - DateUtils.SECOND_IN_MILLIS) : (ListenerUtil.mutListener.listen(10541) ? (millisRemaining + DateUtils.SECOND_IN_MILLIS) : (millisRemaining / DateUtils.SECOND_IN_MILLIS))))), false);
                if (!ListenerUtil.mutListener.listen(10545)) {
                    notificationBuilder.setContentText(String.format(getString(R.string.time_remaining), timeRemaining));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10547)) {
            notificationBuilder.setProgress(steps, currentStep, false);
        }
        if (!ListenerUtil.mutListener.listen(10549)) {
            if (notificationManager != null) {
                if (!ListenerUtil.mutListener.listen(10548)) {
                    notificationManager.notify(BACKUP_NOTIFICATION_ID, notificationBuilder.build());
                }
            }
        }
    }

    private void cancelPersistentNotification() {
        if (!ListenerUtil.mutListener.listen(10551)) {
            if (notificationManager != null) {
                if (!ListenerUtil.mutListener.listen(10550)) {
                    notificationManager.cancel(BACKUP_NOTIFICATION_ID);
                }
            }
        }
    }

    private void showBackupErrorNotification(String message) {
        String contentText;
        if (!TestUtil.empty(message)) {
            contentText = message;
        } else {
            contentText = getString(R.string.backup_or_restore_error_body);
        }
        Intent backupIntent = new Intent(this, HomeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), backupIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationBuilderWrapper(this, NOTIFICATION_CHANNEL_ALERT, null).setSmallIcon(R.drawable.ic_notification_small).setTicker(getString(R.string.backup_or_restore_error_body)).setContentTitle(getString(R.string.backup_or_restore_error)).setContentText(contentText).setContentIntent(pendingIntent).setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE).setColor(getResources().getColor(R.color.material_red)).setPriority(NotificationCompat.PRIORITY_MAX).setStyle(new NotificationCompat.BigTextStyle().bigText(contentText)).setAutoCancel(false);
        if (!ListenerUtil.mutListener.listen(10554)) {
            if (notificationManager != null) {
                if (!ListenerUtil.mutListener.listen(10553)) {
                    notificationManager.notify(BACKUP_COMPLETION_NOTIFICATION_ID, builder.build());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10552)) {
                    RuntimeUtil.runOnUiThread(() -> Toast.makeText(getApplicationContext(), R.string.backup_or_restore_error_body, Toast.LENGTH_LONG).show());
                }
            }
        }
    }

    private void showBackupSuccessNotification() {
        if (!ListenerUtil.mutListener.listen(10555)) {
            logger.debug("showBackupSuccess");
        }
        String text;
        Intent backupIntent = new Intent(this, HomeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), backupIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationBuilderWrapper(this, NOTIFICATION_CHANNEL_ALERT, null).setSmallIcon(R.drawable.ic_notification_small).setTicker(getString(R.string.backup_or_restore_success_body)).setContentTitle(getString(R.string.app_name)).setContentIntent(pendingIntent).setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE).setColor(getResources().getColor(R.color.material_green)).setPriority(NotificationCompat.PRIORITY_MAX).setAutoCancel(true);
        if ((ListenerUtil.mutListener.listen(10560) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(10559) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(10558) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(10557) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(10556) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.P) : (Build.VERSION.SDK_INT > Build.VERSION_CODES.P))))))) {
            // Android Q does not allow restart in the background
            text = getString(R.string.backup_or_restore_success_body) + "\n" + getString(R.string.tap_to_start, getString(R.string.app_name));
        } else {
            text = getString(R.string.backup_or_restore_success_body);
        }
        if (!ListenerUtil.mutListener.listen(10561)) {
            builder.setContentText(text);
        }
        if (!ListenerUtil.mutListener.listen(10562)) {
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(text));
        }
        if (!ListenerUtil.mutListener.listen(10564)) {
            if (notificationManager == null) {
                if (!ListenerUtil.mutListener.listen(10563)) {
                    notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10568)) {
            if (notificationManager != null) {
                if (!ListenerUtil.mutListener.listen(10567)) {
                    notificationManager.notify(BACKUP_COMPLETION_NOTIFICATION_ID, builder.build());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10566)) {
                    RuntimeUtil.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            if (!ListenerUtil.mutListener.listen(10565)) {
                                Toast.makeText(getApplicationContext(), R.string.backup_or_restore_success_body, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        }
    }
}
