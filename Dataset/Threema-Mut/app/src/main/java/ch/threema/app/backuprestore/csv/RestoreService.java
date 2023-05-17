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

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.StrictMode;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.widget.Toast;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.io.inputstream.ZipInputStream;
import net.lingala.zip4j.model.FileHeader;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import ch.threema.app.BuildConfig;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.DummyActivity;
import ch.threema.app.activities.HomeActivity;
import ch.threema.app.backuprestore.BackupRestoreDataService;
import ch.threema.app.collections.Functional;
import ch.threema.app.collections.IPredicateNonNull;
import ch.threema.app.exceptions.RestoreCanceledException;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.notifications.NotificationBuilderWrapper;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.FileService;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.UserService;
import ch.threema.app.utils.BackupUtils;
import ch.threema.app.utils.CSVReader;
import ch.threema.app.utils.CSVRow;
import ch.threema.app.utils.ColorUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.MessageUtil;
import ch.threema.app.utils.MimeUtil;
import ch.threema.app.utils.StringConversionUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.base.ThreemaException;
import ch.threema.base.VerificationLevel;
import ch.threema.client.GroupId;
import ch.threema.client.ProtocolDefines;
import ch.threema.client.ThreemaConnection;
import ch.threema.client.Utils;
import ch.threema.storage.DatabaseServiceNew;
import ch.threema.storage.factories.ContactModelFactory;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.DistributionListMemberModel;
import ch.threema.storage.models.DistributionListMessageModel;
import ch.threema.storage.models.DistributionListModel;
import ch.threema.storage.models.GroupMemberModel;
import ch.threema.storage.models.GroupMessageModel;
import ch.threema.storage.models.GroupModel;
import ch.threema.storage.models.MessageModel;
import ch.threema.storage.models.MessageState;
import ch.threema.storage.models.MessageType;
import ch.threema.storage.models.ballot.BallotChoiceModel;
import ch.threema.storage.models.ballot.BallotModel;
import ch.threema.storage.models.ballot.BallotVoteModel;
import ch.threema.storage.models.ballot.GroupBallotModel;
import ch.threema.storage.models.ballot.IdentityBallotModel;
import ch.threema.storage.models.ballot.LinkBallotModel;
import ch.threema.storage.models.data.MessageContentsType;
import ch.threema.storage.models.data.media.BallotDataModel;
import ch.threema.storage.models.data.media.FileDataModel;
import static ch.threema.app.services.NotificationService.NOTIFICATION_CHANNEL_ALERT;
import static ch.threema.app.services.NotificationService.NOTIFICATION_CHANNEL_BACKUP_RESTORE_IN_PROGRESS;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class RestoreService extends Service {

    private static final Logger logger = LoggerFactory.getLogger(RestoreService.class);

    public static final String EXTRA_RESTORE_BACKUP_FILE = "file";

    public static final String EXTRA_RESTORE_BACKUP_PASSWORD = "pwd";

    private ServiceManager serviceManager;

    private ContactService contactService;

    private FileService fileService;

    private UserService userService;

    private GroupService groupService;

    private DatabaseServiceNew databaseServiceNew;

    private PreferenceService preferenceService;

    private ThreemaConnection threemaConnection;

    private PowerManager.WakeLock wakeLock;

    private NotificationManager notificationManager;

    private NotificationCompat.Builder notificationBuilder;

    private static final int RESTORE_NOTIFICATION_ID = 981772;

    private static final int RESTORE_COMPLETION_NOTIFICATION_ID = 981773;

    private static final String EXTRA_ID_CANCEL = "cnc";

    private final RestoreResultImpl restoreResult = new RestoreResultImpl();

    private long currentProgressStep = 0;

    private long progressSteps = 0;

    private int latestPercentStep = -1;

    private long startTime = 0;

    private static boolean restoreSuccess = false;

    private ZipFile zipFile;

    private String password;

    private final int STEP_SIZE_PREPARE = 100;

    private final int STEP_SIZE_IDENTITY = 100;

    private final int STEP_SIZE_MAIN_FILES = 200;

    // per message
    private final int STEP_SIZE_MESSAGES = 1;

    private final int STEP_SIZE_GRPOUP_AVATARS = 50;

    // per media file
    private final int STEP_SIZE_MEDIA = 25;

    private long stepSizeTotal = STEP_SIZE_PREPARE + STEP_SIZE_IDENTITY + STEP_SIZE_MAIN_FILES + STEP_SIZE_GRPOUP_AVATARS;

    private static boolean isCanceled = false;

    private static boolean isRunning = false;

    public static boolean isRunning() {
        return isRunning;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!ListenerUtil.mutListener.listen(10570)) {
            logger.debug("onStartCommand flags = " + flags + " startId " + startId);
        }
        if (!ListenerUtil.mutListener.listen(10571)) {
            startForeground(RESTORE_NOTIFICATION_ID, getPersistentNotification());
        }
        if (!ListenerUtil.mutListener.listen(10601)) {
            if (intent != null) {
                if (!ListenerUtil.mutListener.listen(10574)) {
                    logger.debug("onStartCommand intent != null");
                }
                if (!ListenerUtil.mutListener.listen(10575)) {
                    isCanceled = intent.getBooleanExtra(EXTRA_ID_CANCEL, false);
                }
                if (!ListenerUtil.mutListener.listen(10600)) {
                    if (!isCanceled) {
                        File file = (File) intent.getSerializableExtra(EXTRA_RESTORE_BACKUP_FILE);
                        if (!ListenerUtil.mutListener.listen(10577)) {
                            password = intent.getStringExtra(EXTRA_RESTORE_BACKUP_PASSWORD);
                        }
                        if (!ListenerUtil.mutListener.listen(10582)) {
                            if ((ListenerUtil.mutListener.listen(10578) ? (file == null && TextUtils.isEmpty(password)) : (file == null || TextUtils.isEmpty(password)))) {
                                if (!ListenerUtil.mutListener.listen(10579)) {
                                    showRestoreErrorNotification("Invalid input");
                                }
                                if (!ListenerUtil.mutListener.listen(10580)) {
                                    stopSelf();
                                }
                                if (!ListenerUtil.mutListener.listen(10581)) {
                                    isRunning = false;
                                }
                                return START_NOT_STICKY;
                            }
                        }
                        PowerManager powerManager = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
                        if (!ListenerUtil.mutListener.listen(10589)) {
                            if (powerManager != null) {
                                String tag = BuildConfig.APPLICATION_ID + ":restore";
                                if (!ListenerUtil.mutListener.listen(10585)) {
                                    if ((ListenerUtil.mutListener.listen(10583) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M || Build.MANUFACTURER.equals("Huawei")) : (Build.VERSION.SDK_INT == Build.VERSION_CODES.M && Build.MANUFACTURER.equals("Huawei")))) {
                                        if (!ListenerUtil.mutListener.listen(10584)) {
                                            // see https://dontkillmyapp.com/huawei
                                            tag = "LocationManagerService";
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(10586)) {
                                    wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, tag);
                                }
                                if (!ListenerUtil.mutListener.listen(10588)) {
                                    if (wakeLock != null) {
                                        if (!ListenerUtil.mutListener.listen(10587)) {
                                            wakeLock.acquire(DateUtils.DAY_IN_MILLIS);
                                        }
                                    }
                                }
                            }
                        }
                        try {
                            if (!ListenerUtil.mutListener.listen(10592)) {
                                serviceManager.stopConnection();
                            }
                        } catch (InterruptedException e) {
                            if (!ListenerUtil.mutListener.listen(10590)) {
                                showRestoreErrorNotification("RestoreService interrupted");
                            }
                            if (!ListenerUtil.mutListener.listen(10591)) {
                                stopSelf();
                            }
                            return START_NOT_STICKY;
                        }
                        if (!ListenerUtil.mutListener.listen(10598)) {
                            new AsyncTask<Void, Void, Boolean>() {

                                @Override
                                protected Boolean doInBackground(Void... params) {
                                    if (!ListenerUtil.mutListener.listen(10593)) {
                                        zipFile = new ZipFile(file, password.toCharArray());
                                    }
                                    if (!ListenerUtil.mutListener.listen(10596)) {
                                        if (!zipFile.isValidZipFile()) {
                                            if (!ListenerUtil.mutListener.listen(10594)) {
                                                showRestoreErrorNotification(getString(R.string.restore_zip_invalid_file));
                                            }
                                            if (!ListenerUtil.mutListener.listen(10595)) {
                                                isRunning = false;
                                            }
                                            return false;
                                        }
                                    }
                                    return restore();
                                }

                                @Override
                                protected void onPostExecute(Boolean success) {
                                    if (!ListenerUtil.mutListener.listen(10597)) {
                                        stopSelf();
                                    }
                                }
                            }.execute();
                        }
                        if (!ListenerUtil.mutListener.listen(10599)) {
                            if (isRunning) {
                                return START_STICKY;
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(10576)) {
                            Toast.makeText(this, R.string.restore_data_cancelled, Toast.LENGTH_LONG).show();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10572)) {
                    logger.debug("onStartCommand intent == null");
                }
                if (!ListenerUtil.mutListener.listen(10573)) {
                    onFinished(null);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10602)) {
            isRunning = false;
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(10603)) {
            logger.debug("onCreate");
        }
        if (!ListenerUtil.mutListener.listen(10604)) {
            super.onCreate();
        }
        if (!ListenerUtil.mutListener.listen(10605)) {
            isRunning = true;
        }
        if (!ListenerUtil.mutListener.listen(10606)) {
            serviceManager = ThreemaApplication.getServiceManager();
        }
        if (!ListenerUtil.mutListener.listen(10608)) {
            if (serviceManager == null) {
                if (!ListenerUtil.mutListener.listen(10607)) {
                    stopSelf();
                }
                return;
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(10611)) {
                fileService = serviceManager.getFileService();
            }
            if (!ListenerUtil.mutListener.listen(10612)) {
                databaseServiceNew = serviceManager.getDatabaseServiceNew();
            }
            if (!ListenerUtil.mutListener.listen(10613)) {
                contactService = serviceManager.getContactService();
            }
            if (!ListenerUtil.mutListener.listen(10614)) {
                userService = serviceManager.getUserService();
            }
            if (!ListenerUtil.mutListener.listen(10615)) {
                groupService = serviceManager.getGroupService();
            }
            if (!ListenerUtil.mutListener.listen(10616)) {
                preferenceService = serviceManager.getPreferenceService();
            }
            if (!ListenerUtil.mutListener.listen(10617)) {
                threemaConnection = serviceManager.getConnection();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(10609)) {
                logger.error("Exception", e);
            }
            if (!ListenerUtil.mutListener.listen(10610)) {
                stopSelf();
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(10618)) {
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(10619)) {
            logger.debug("onDestroy success = " + restoreSuccess + " canceled = " + isCanceled);
        }
        if (!ListenerUtil.mutListener.listen(10621)) {
            if (isCanceled) {
                if (!ListenerUtil.mutListener.listen(10620)) {
                    onFinished(getString(R.string.restore_data_cancelled));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10622)) {
            super.onDestroy();
        }
    }

    @Override
    public void onLowMemory() {
        if (!ListenerUtil.mutListener.listen(10623)) {
            logger.debug("onLowMemory");
        }
        if (!ListenerUtil.mutListener.listen(10624)) {
            super.onLowMemory();
        }
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        if (!ListenerUtil.mutListener.listen(10625)) {
            logger.debug("onTaskRemoved");
        }
        Intent intent = new Intent(this, DummyActivity.class);
        if (!ListenerUtil.mutListener.listen(10626)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (!ListenerUtil.mutListener.listen(10627)) {
            startActivity(intent);
        }
    }

    // ---------------------------------------------------------------------------
    private class RestoreResultImpl implements BackupRestoreDataService.RestoreResult {

        private long contactSuccess = 0;

        private long contactFailed = 0;

        private long messageSuccess = 0;

        private long messageFailed = 0;

        @Override
        public long getContactSuccess() {
            return this.contactSuccess;
        }

        @Override
        public long getContactFailed() {
            return this.contactFailed;
        }

        @Override
        public long getMessageSuccess() {
            return this.messageSuccess;
        }

        @Override
        public long getMessageFailed() {
            return this.messageFailed;
        }

        protected void incContactSuccess() {
            if (!ListenerUtil.mutListener.listen(10628)) {
                this.contactSuccess++;
            }
        }

        protected void incContactFailed() {
            if (!ListenerUtil.mutListener.listen(10629)) {
                this.contactFailed++;
            }
        }

        protected void incMessageSuccess() {
            if (!ListenerUtil.mutListener.listen(10630)) {
                this.messageSuccess++;
            }
        }

        protected void incMessageFailed() {
            if (!ListenerUtil.mutListener.listen(10631)) {
                this.messageFailed++;
            }
        }
    }

    private interface ProcessCsvFile {

        void row(CSVRow row) throws RestoreCanceledException;
    }

    private interface GetMessageModel {

        AbstractMessageModel get(String uid);
    }

    private RestoreSettings restoreSettings;

    private final HashMap<String, Integer> groupIdMap = new HashMap<String, Integer>();

    private final HashMap<String, Integer> ballotIdMap = new HashMap<String, Integer>();

    private final HashMap<Integer, Integer> ballotOldIdMap = new HashMap<Integer, Integer>();

    private final HashMap<String, Integer> ballotChoiceIdMap = new HashMap<String, Integer>();

    private final HashMap<String, Integer> distributionListIdMap = new HashMap<String, Integer>();

    private boolean writeToDb = false;

    public boolean restore() {
        int mediaCount;
        int messageCount;
        String message;
        if (!ListenerUtil.mutListener.listen(10633)) {
            if (BuildConfig.DEBUG) {
                if (!ListenerUtil.mutListener.listen(10632)) {
                    // zipFile.getInputStream() currently causes "Explicit termination method 'end' not called" exception
                    StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
                }
            }
        }
        try {
            {
                long _loopCounter101 = 0;
                // we use two passes for a restore
                for (int nTry = 0; (ListenerUtil.mutListener.listen(10718) ? (nTry >= 2) : (ListenerUtil.mutListener.listen(10717) ? (nTry <= 2) : (ListenerUtil.mutListener.listen(10716) ? (nTry > 2) : (ListenerUtil.mutListener.listen(10715) ? (nTry != 2) : (ListenerUtil.mutListener.listen(10714) ? (nTry == 2) : (nTry < 2)))))); nTry++) {
                    ListenerUtil.loopListener.listen("_loopCounter101", ++_loopCounter101);
                    if (!ListenerUtil.mutListener.listen(10645)) {
                        if ((ListenerUtil.mutListener.listen(10642) ? (nTry >= 0) : (ListenerUtil.mutListener.listen(10641) ? (nTry <= 0) : (ListenerUtil.mutListener.listen(10640) ? (nTry < 0) : (ListenerUtil.mutListener.listen(10639) ? (nTry != 0) : (ListenerUtil.mutListener.listen(10638) ? (nTry == 0) : (nTry > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(10643)) {
                                this.writeToDb = true;
                            }
                            if (!ListenerUtil.mutListener.listen(10644)) {
                                this.initProgress(stepSizeTotal);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(10646)) {
                        this.groupIdMap.clear();
                    }
                    if (!ListenerUtil.mutListener.listen(10647)) {
                        this.ballotIdMap.clear();
                    }
                    if (!ListenerUtil.mutListener.listen(10648)) {
                        this.ballotOldIdMap.clear();
                    }
                    if (!ListenerUtil.mutListener.listen(10649)) {
                        this.ballotChoiceIdMap.clear();
                    }
                    if (!ListenerUtil.mutListener.listen(10650)) {
                        this.distributionListIdMap.clear();
                    }
                    if (!ListenerUtil.mutListener.listen(10666)) {
                        if (this.writeToDb) {
                            if (!ListenerUtil.mutListener.listen(10651)) {
                                updateProgress(STEP_SIZE_PREPARE);
                            }
                            if (!ListenerUtil.mutListener.listen(10652)) {
                                // clear tables!!
                                databaseServiceNew.getMessageModelFactory().deleteAll();
                            }
                            if (!ListenerUtil.mutListener.listen(10653)) {
                                databaseServiceNew.getContactModelFactory().deleteAll();
                            }
                            if (!ListenerUtil.mutListener.listen(10654)) {
                                databaseServiceNew.getGroupMessageModelFactory().deleteAll();
                            }
                            if (!ListenerUtil.mutListener.listen(10655)) {
                                databaseServiceNew.getGroupMemberModelFactory().deleteAll();
                            }
                            if (!ListenerUtil.mutListener.listen(10656)) {
                                databaseServiceNew.getGroupModelFactory().deleteAll();
                            }
                            if (!ListenerUtil.mutListener.listen(10657)) {
                                databaseServiceNew.getDistributionListMessageModelFactory().deleteAll();
                            }
                            if (!ListenerUtil.mutListener.listen(10658)) {
                                databaseServiceNew.getDistributionListMemberModelFactory().deleteAll();
                            }
                            if (!ListenerUtil.mutListener.listen(10659)) {
                                databaseServiceNew.getDistributionListModelFactory().deleteAll();
                            }
                            if (!ListenerUtil.mutListener.listen(10660)) {
                                databaseServiceNew.getBallotModelFactory().deleteAll();
                            }
                            if (!ListenerUtil.mutListener.listen(10661)) {
                                databaseServiceNew.getBallotVoteModelFactory().deleteAll();
                            }
                            if (!ListenerUtil.mutListener.listen(10662)) {
                                databaseServiceNew.getBallotChoiceModelFactory().deleteAll();
                            }
                            if (!ListenerUtil.mutListener.listen(10663)) {
                                databaseServiceNew.getGroupMessagePendingMessageIdModelFactory().deleteAll();
                            }
                            if (!ListenerUtil.mutListener.listen(10664)) {
                                databaseServiceNew.getGroupRequestSyncLogModelFactory().deleteAll();
                            }
                            if (!ListenerUtil.mutListener.listen(10665)) {
                                // remove all media files (don't remove recursive, tmp folder contain the restoring files
                                fileService.clearDirectory(fileService.getAppDataPath(), false);
                            }
                        }
                    }
                    /* make map of file headers for quick access */
                    @SuppressWarnings({ "unchecked" })
                    List<FileHeader> fileHeaders = zipFile.getFileHeaders();
                    FileHeader settingsHeader = Functional.select(fileHeaders, new IPredicateNonNull<FileHeader>() {

                        @Override
                        public boolean apply(@NonNull FileHeader type) {
                            return TestUtil.compare(type.getFileName(), Tags.SETTINGS_FILE_NAME);
                        }
                    });
                    if (!ListenerUtil.mutListener.listen(10667)) {
                        this.restoreSettings = new RestoreSettings();
                    }
                    if (!ListenerUtil.mutListener.listen(10669)) {
                        if (settingsHeader != null) {
                            try (InputStream inputStream = zipFile.getInputStream(settingsHeader);
                                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                                CSVReader csvReader = new CSVReader(inputStreamReader)) {
                                if (!ListenerUtil.mutListener.listen(10668)) {
                                    restoreSettings.parse(csvReader.readAll());
                                }
                            }
                        }
                    }
                    // try to restore the identity
                    FileHeader identityHeader = Functional.select(fileHeaders, new IPredicateNonNull<FileHeader>() {

                        @Override
                        public boolean apply(@NonNull FileHeader type) {
                            return TestUtil.compare(type.getFileName(), Tags.IDENTITY_FILE_NAME);
                        }
                    });
                    if (!ListenerUtil.mutListener.listen(10676)) {
                        if ((ListenerUtil.mutListener.listen(10670) ? (identityHeader != null || this.writeToDb) : (identityHeader != null && this.writeToDb))) {
                            String identityContent;
                            try (InputStream inputStream = zipFile.getInputStream(identityHeader)) {
                                identityContent = IOUtils.toString(inputStream);
                            }
                            if (!ListenerUtil.mutListener.listen(10673)) {
                                if ((ListenerUtil.mutListener.listen(10671) ? (threemaConnection != null || threemaConnection.isRunning()) : (threemaConnection != null && threemaConnection.isRunning()))) {
                                    if (!ListenerUtil.mutListener.listen(10672)) {
                                        threemaConnection.stop();
                                    }
                                }
                            }
                            try {
                                if (!ListenerUtil.mutListener.listen(10674)) {
                                    if (!userService.restoreIdentity(identityContent, this.password)) {
                                        throw new ThreemaException("failed");
                                    }
                                }
                            } catch (UnknownHostException e) {
                                throw e;
                            } catch (Exception e) {
                                throw new ThreemaException("failed to restore identity: " + e.getMessage());
                            }
                            if (!ListenerUtil.mutListener.listen(10675)) {
                                updateProgress(STEP_SIZE_IDENTITY);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(10678)) {
                        // contacts, groups and distribution lists
                        if (!this.restoreMainFiles(fileHeaders)) {
                            if (!ListenerUtil.mutListener.listen(10677)) {
                                logger.error("restore main files failed");
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(10679)) {
                        updateProgress(STEP_SIZE_MAIN_FILES);
                    }
                    messageCount = this.restoreMessageFiles(fileHeaders);
                    if (!ListenerUtil.mutListener.listen(10686)) {
                        if ((ListenerUtil.mutListener.listen(10684) ? (messageCount >= 0) : (ListenerUtil.mutListener.listen(10683) ? (messageCount <= 0) : (ListenerUtil.mutListener.listen(10682) ? (messageCount > 0) : (ListenerUtil.mutListener.listen(10681) ? (messageCount < 0) : (ListenerUtil.mutListener.listen(10680) ? (messageCount != 0) : (messageCount == 0))))))) {
                            if (!ListenerUtil.mutListener.listen(10685)) {
                                logger.error("restore message files failed");
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(10688)) {
                        if (!this.restoreGroupAvatarFiles(fileHeaders)) {
                            if (!ListenerUtil.mutListener.listen(10687)) {
                                logger.error("restore group avatar files failed");
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(10689)) {
                        updateProgress(STEP_SIZE_GRPOUP_AVATARS);
                    }
                    mediaCount = this.restoreMessageMediaFiles(fileHeaders);
                    if (!ListenerUtil.mutListener.listen(10697)) {
                        if ((ListenerUtil.mutListener.listen(10694) ? (mediaCount >= 0) : (ListenerUtil.mutListener.listen(10693) ? (mediaCount <= 0) : (ListenerUtil.mutListener.listen(10692) ? (mediaCount > 0) : (ListenerUtil.mutListener.listen(10691) ? (mediaCount < 0) : (ListenerUtil.mutListener.listen(10690) ? (mediaCount != 0) : (mediaCount == 0))))))) {
                            if (!ListenerUtil.mutListener.listen(10696)) {
                                logger.error("restore message media files failed");
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(10695)) {
                                logger.info(mediaCount + " media files found");
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(10699)) {
                        // restore all avatars
                        if (!this.restoreContactAvatars(fileHeaders)) {
                            if (!ListenerUtil.mutListener.listen(10698)) {
                                logger.error("restore contact avatar files failed");
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(10713)) {
                        if (!writeToDb) {
                            if (!ListenerUtil.mutListener.listen(10712)) {
                                stepSizeTotal += (ListenerUtil.mutListener.listen(10711) ? (((ListenerUtil.mutListener.listen(10703) ? (messageCount % STEP_SIZE_MESSAGES) : (ListenerUtil.mutListener.listen(10702) ? (messageCount / STEP_SIZE_MESSAGES) : (ListenerUtil.mutListener.listen(10701) ? (messageCount - STEP_SIZE_MESSAGES) : (ListenerUtil.mutListener.listen(10700) ? (messageCount + STEP_SIZE_MESSAGES) : (messageCount * STEP_SIZE_MESSAGES)))))) % ((ListenerUtil.mutListener.listen(10707) ? (mediaCount % STEP_SIZE_MEDIA) : (ListenerUtil.mutListener.listen(10706) ? (mediaCount / STEP_SIZE_MEDIA) : (ListenerUtil.mutListener.listen(10705) ? (mediaCount - STEP_SIZE_MEDIA) : (ListenerUtil.mutListener.listen(10704) ? (mediaCount + STEP_SIZE_MEDIA) : (mediaCount * STEP_SIZE_MEDIA))))))) : (ListenerUtil.mutListener.listen(10710) ? (((ListenerUtil.mutListener.listen(10703) ? (messageCount % STEP_SIZE_MESSAGES) : (ListenerUtil.mutListener.listen(10702) ? (messageCount / STEP_SIZE_MESSAGES) : (ListenerUtil.mutListener.listen(10701) ? (messageCount - STEP_SIZE_MESSAGES) : (ListenerUtil.mutListener.listen(10700) ? (messageCount + STEP_SIZE_MESSAGES) : (messageCount * STEP_SIZE_MESSAGES)))))) / ((ListenerUtil.mutListener.listen(10707) ? (mediaCount % STEP_SIZE_MEDIA) : (ListenerUtil.mutListener.listen(10706) ? (mediaCount / STEP_SIZE_MEDIA) : (ListenerUtil.mutListener.listen(10705) ? (mediaCount - STEP_SIZE_MEDIA) : (ListenerUtil.mutListener.listen(10704) ? (mediaCount + STEP_SIZE_MEDIA) : (mediaCount * STEP_SIZE_MEDIA))))))) : (ListenerUtil.mutListener.listen(10709) ? (((ListenerUtil.mutListener.listen(10703) ? (messageCount % STEP_SIZE_MESSAGES) : (ListenerUtil.mutListener.listen(10702) ? (messageCount / STEP_SIZE_MESSAGES) : (ListenerUtil.mutListener.listen(10701) ? (messageCount - STEP_SIZE_MESSAGES) : (ListenerUtil.mutListener.listen(10700) ? (messageCount + STEP_SIZE_MESSAGES) : (messageCount * STEP_SIZE_MESSAGES)))))) * ((ListenerUtil.mutListener.listen(10707) ? (mediaCount % STEP_SIZE_MEDIA) : (ListenerUtil.mutListener.listen(10706) ? (mediaCount / STEP_SIZE_MEDIA) : (ListenerUtil.mutListener.listen(10705) ? (mediaCount - STEP_SIZE_MEDIA) : (ListenerUtil.mutListener.listen(10704) ? (mediaCount + STEP_SIZE_MEDIA) : (mediaCount * STEP_SIZE_MEDIA))))))) : (ListenerUtil.mutListener.listen(10708) ? (((ListenerUtil.mutListener.listen(10703) ? (messageCount % STEP_SIZE_MESSAGES) : (ListenerUtil.mutListener.listen(10702) ? (messageCount / STEP_SIZE_MESSAGES) : (ListenerUtil.mutListener.listen(10701) ? (messageCount - STEP_SIZE_MESSAGES) : (ListenerUtil.mutListener.listen(10700) ? (messageCount + STEP_SIZE_MESSAGES) : (messageCount * STEP_SIZE_MESSAGES)))))) - ((ListenerUtil.mutListener.listen(10707) ? (mediaCount % STEP_SIZE_MEDIA) : (ListenerUtil.mutListener.listen(10706) ? (mediaCount / STEP_SIZE_MEDIA) : (ListenerUtil.mutListener.listen(10705) ? (mediaCount - STEP_SIZE_MEDIA) : (ListenerUtil.mutListener.listen(10704) ? (mediaCount + STEP_SIZE_MEDIA) : (mediaCount * STEP_SIZE_MEDIA))))))) : (((ListenerUtil.mutListener.listen(10703) ? (messageCount % STEP_SIZE_MESSAGES) : (ListenerUtil.mutListener.listen(10702) ? (messageCount / STEP_SIZE_MESSAGES) : (ListenerUtil.mutListener.listen(10701) ? (messageCount - STEP_SIZE_MESSAGES) : (ListenerUtil.mutListener.listen(10700) ? (messageCount + STEP_SIZE_MESSAGES) : (messageCount * STEP_SIZE_MESSAGES)))))) + ((ListenerUtil.mutListener.listen(10707) ? (mediaCount % STEP_SIZE_MEDIA) : (ListenerUtil.mutListener.listen(10706) ? (mediaCount / STEP_SIZE_MEDIA) : (ListenerUtil.mutListener.listen(10705) ? (mediaCount - STEP_SIZE_MEDIA) : (ListenerUtil.mutListener.listen(10704) ? (mediaCount + STEP_SIZE_MEDIA) : (mediaCount * STEP_SIZE_MEDIA)))))))))));
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(10719)) {
                restoreSuccess = true;
            }
            if (!ListenerUtil.mutListener.listen(10720)) {
                onFinished(null);
            }
            return true;
        } catch (InterruptedException e) {
            if (!ListenerUtil.mutListener.listen(10634)) {
                logger.error("Interrupted while restoring identity", e);
            }
            if (!ListenerUtil.mutListener.listen(10635)) {
                Thread.currentThread().interrupt();
            }
            message = "Interrupted while restoring identity";
        } catch (RestoreCanceledException e) {
            if (!ListenerUtil.mutListener.listen(10636)) {
                logger.error("Exception", e);
            }
            message = getString(R.string.restore_data_cancelled);
        } catch (Exception x) {
            if (!ListenerUtil.mutListener.listen(10637)) {
                // wrong password? no connection? throw
                logger.error("Exception", x);
            }
            message = x.getMessage();
        }
        if (!ListenerUtil.mutListener.listen(10721)) {
            onFinished(message);
        }
        return false;
    }

    /**
     *  restore the main files (contacts, groups, distribution lists)
     */
    private boolean restoreMainFiles(List<FileHeader> fileHeaders) throws IOException, RestoreCanceledException {
        FileHeader ballotMain = null;
        FileHeader ballotChoice = null;
        FileHeader ballotVote = null;
        if (!ListenerUtil.mutListener.listen(10733)) {
            {
                long _loopCounter102 = 0;
                for (FileHeader fileHeader : fileHeaders) {
                    ListenerUtil.loopListener.listen("_loopCounter102", ++_loopCounter102);
                    String fileName = fileHeader.getFileName();
                    if (!ListenerUtil.mutListener.listen(10732)) {
                        if (fileName.endsWith(Tags.CSV_FILE_POSTFIX)) {
                            if (!ListenerUtil.mutListener.listen(10731)) {
                                if (fileName.startsWith(Tags.CONTACTS_FILE_NAME)) {
                                    if (!ListenerUtil.mutListener.listen(10730)) {
                                        if (!this.restoreContactFile(fileHeader)) {
                                            if (!ListenerUtil.mutListener.listen(10729)) {
                                                logger.error("restore contact file failed");
                                            }
                                            return false;
                                        }
                                    }
                                } else if (fileName.startsWith(Tags.GROUPS_FILE_NAME)) {
                                    if (!ListenerUtil.mutListener.listen(10728)) {
                                        if (!this.restoreGroupFile(fileHeader)) {
                                            if (!ListenerUtil.mutListener.listen(10727)) {
                                                logger.error("restore group file failed");
                                            }
                                        }
                                    }
                                } else if (fileName.startsWith(Tags.DISTRIBUTION_LISTS_FILE_NAME)) {
                                    if (!ListenerUtil.mutListener.listen(10726)) {
                                        if (!this.restoreDistributionListFile(fileHeader)) {
                                            if (!ListenerUtil.mutListener.listen(10725)) {
                                                logger.error("restore distribution list file failed");
                                            }
                                        }
                                    }
                                } else if (fileName.startsWith(Tags.BALLOT_FILE_NAME + Tags.CSV_FILE_POSTFIX)) {
                                    if (!ListenerUtil.mutListener.listen(10724)) {
                                        ballotMain = fileHeader;
                                    }
                                } else if (fileName.startsWith(Tags.BALLOT_CHOICE_FILE_NAME + Tags.CSV_FILE_POSTFIX)) {
                                    if (!ListenerUtil.mutListener.listen(10723)) {
                                        ballotChoice = fileHeader;
                                    }
                                } else if (fileName.startsWith(Tags.BALLOT_VOTE_FILE_NAME + Tags.CSV_FILE_POSTFIX)) {
                                    if (!ListenerUtil.mutListener.listen(10722)) {
                                        ballotVote = fileHeader;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10735)) {
            if (TestUtil.required(ballotMain, ballotChoice, ballotVote)) {
                if (!ListenerUtil.mutListener.listen(10734)) {
                    this.restoreBallotFile(ballotMain, ballotChoice, ballotVote);
                }
            }
        }
        return true;
    }

    /**
     *  restore all avatars and profile pics
     */
    private boolean restoreContactAvatars(List<FileHeader> fileHeaders) {
        if (!ListenerUtil.mutListener.listen(10741)) {
            {
                long _loopCounter103 = 0;
                for (FileHeader fileHeader : fileHeaders) {
                    ListenerUtil.loopListener.listen("_loopCounter103", ++_loopCounter103);
                    String fileName = fileHeader.getFileName();
                    if (!ListenerUtil.mutListener.listen(10740)) {
                        if (fileName.startsWith(Tags.CONTACT_AVATAR_FILE_PREFIX)) {
                            if (!ListenerUtil.mutListener.listen(10739)) {
                                if (!this.restoreContactAvatarFile(fileHeader)) {
                                    if (!ListenerUtil.mutListener.listen(10738)) {
                                        logger.error("restore contact avatar " + fileName + " file failed or skipped");
                                    }
                                }
                            }
                        } else if (fileName.startsWith(Tags.CONTACT_PROFILE_PIC_FILE_PREFIX)) {
                            if (!ListenerUtil.mutListener.listen(10737)) {
                                if (!this.restoreContactPhotoFile(fileHeader)) {
                                    if (!ListenerUtil.mutListener.listen(10736)) {
                                        logger.error("restore contact profile pic " + fileName + " file failed or skipped");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     *  restore all message files
     */
    private int restoreMessageFiles(List<FileHeader> fileHeaders) throws IOException, RestoreCanceledException {
        int count = 0;
        if (!ListenerUtil.mutListener.listen(10750)) {
            {
                long _loopCounter104 = 0;
                for (FileHeader fileHeader : fileHeaders) {
                    ListenerUtil.loopListener.listen("_loopCounter104", ++_loopCounter104);
                    String fileName = fileHeader.getFileName();
                    if (!ListenerUtil.mutListener.listen(10742)) {
                        if (!fileName.endsWith(Tags.CSV_FILE_POSTFIX)) {
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(10749)) {
                        if (fileName.startsWith(Tags.MESSAGE_FILE_PREFIX)) {
                            try {
                                if (!ListenerUtil.mutListener.listen(10748)) {
                                    count += this.restoreContactMessageFile(fileHeader);
                                }
                            } catch (ThreemaException e) {
                                if (!ListenerUtil.mutListener.listen(10747)) {
                                    logger.error("restore contact message file failed");
                                }
                                return 0;
                            }
                        } else if (fileName.startsWith(Tags.GROUP_MESSAGE_FILE_PREFIX)) {
                            try {
                                if (!ListenerUtil.mutListener.listen(10746)) {
                                    count += this.restoreGroupMessageFile(fileHeader);
                                }
                            } catch (ThreemaException e) {
                                if (!ListenerUtil.mutListener.listen(10745)) {
                                    logger.error("restore group message file failed");
                                }
                                return 0;
                            }
                        } else if (fileName.startsWith(Tags.DISTRIBUTION_LIST_MESSAGE_FILE_PREFIX)) {
                            try {
                                if (!ListenerUtil.mutListener.listen(10744)) {
                                    count += this.restoreDistributionListMessageFile(fileHeader);
                                }
                            } catch (ThreemaException e) {
                                if (!ListenerUtil.mutListener.listen(10743)) {
                                    logger.error("restore distributionList message file failed");
                                }
                                return 0;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    /**
     *  restore all group avatars!
     */
    private boolean restoreGroupAvatarFiles(List<FileHeader> fileHeaders) {
        boolean success = true;
        if (!ListenerUtil.mutListener.listen(10756)) {
            {
                long _loopCounter105 = 0;
                for (FileHeader fileHeader : fileHeaders) {
                    ListenerUtil.loopListener.listen("_loopCounter105", ++_loopCounter105);
                    String fileName = fileHeader.getFileName();
                    if (!ListenerUtil.mutListener.listen(10751)) {
                        if (!fileName.startsWith(Tags.GROUP_AVATAR_PREFIX)) {
                            continue;
                        }
                    }
                    final String groupUid = fileName.substring(Tags.GROUP_AVATAR_PREFIX.length());
                    if (!ListenerUtil.mutListener.listen(10755)) {
                        if (groupIdMap.containsKey(groupUid)) {
                            GroupModel m = databaseServiceNew.getGroupModelFactory().getById(groupIdMap.get(groupUid));
                            if (!ListenerUtil.mutListener.listen(10754)) {
                                if (m != null) {
                                    try (InputStream inputStream = zipFile.getInputStream(fileHeader)) {
                                        if (!ListenerUtil.mutListener.listen(10753)) {
                                            this.fileService.writeGroupAvatar(m, IOUtils.toByteArray(inputStream));
                                        }
                                    } catch (Exception e) {
                                        if (!ListenerUtil.mutListener.listen(10752)) {
                                            // ignore, just the avatar :)
                                            success = false;
                                        }
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

    /**
     *  restore all message media
     */
    private int restoreMessageMediaFiles(List<FileHeader> fileHeaders) throws RestoreCanceledException {
        int count = 0;
        if (!ListenerUtil.mutListener.listen(10757)) {
            count += this.restoreMessageMediaFiles(fileHeaders, Tags.MESSAGE_MEDIA_FILE_PREFIX, Tags.MESSAGE_MEDIA_THUMBNAIL_FILE_PREFIX, new GetMessageModel() {

                @Override
                public AbstractMessageModel get(String uid) {
                    return databaseServiceNew.getMessageModelFactory().getByUid(uid);
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(10758)) {
            count += this.restoreMessageMediaFiles(fileHeaders, Tags.GROUP_MESSAGE_MEDIA_FILE_PREFIX, Tags.GROUP_MESSAGE_MEDIA_THUMBNAIL_FILE_PREFIX, new GetMessageModel() {

                @Override
                public AbstractMessageModel get(String uid) {
                    return databaseServiceNew.getGroupMessageModelFactory().getByUid(uid);
                }
            });
        }
        return count;
    }

    /**
     *  restore all message media
     */
    private int restoreMessageMediaFiles(List<FileHeader> fileHeaders, String filePrefix, String thumbnailPrefix, GetMessageModel getMessageModel) throws RestoreCanceledException {
        int count = 0;
        // process all thumbnails
        Map<String, FileHeader> thumbnailFileHeaders = new HashMap<String, FileHeader>();
        if (!ListenerUtil.mutListener.listen(10762)) {
            {
                long _loopCounter106 = 0;
                for (FileHeader fileHeader : fileHeaders) {
                    ListenerUtil.loopListener.listen("_loopCounter106", ++_loopCounter106);
                    String fileName = fileHeader.getFileName();
                    if (!ListenerUtil.mutListener.listen(10761)) {
                        if ((ListenerUtil.mutListener.listen(10759) ? (!TestUtil.empty(fileName) || fileName.startsWith(thumbnailPrefix)) : (!TestUtil.empty(fileName) && fileName.startsWith(thumbnailPrefix)))) {
                            if (!ListenerUtil.mutListener.listen(10760)) {
                                thumbnailFileHeaders.put(fileName, fileHeader);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10777)) {
            {
                long _loopCounter107 = 0;
                for (FileHeader fileHeader : fileHeaders) {
                    ListenerUtil.loopListener.listen("_loopCounter107", ++_loopCounter107);
                    String fileName = fileHeader.getFileName();
                    String messageUid;
                    if (fileName.startsWith(filePrefix)) {
                        messageUid = fileName.substring(filePrefix.length());
                    } else if (fileName.startsWith(thumbnailPrefix)) {
                        messageUid = fileName.substring(thumbnailPrefix.length());
                    } else {
                        continue;
                    }
                    AbstractMessageModel model = getMessageModel.get(messageUid);
                    if (!ListenerUtil.mutListener.listen(10776)) {
                        if (model != null) {
                            try {
                                if (!ListenerUtil.mutListener.listen(10773)) {
                                    if (fileName.startsWith(thumbnailPrefix)) {
                                        if (!ListenerUtil.mutListener.listen(10772)) {
                                            // restore thumbnail
                                            if (this.writeToDb) {
                                                FileHeader thumbnailFileHeader = thumbnailFileHeaders.get(thumbnailPrefix + messageUid);
                                                if (!ListenerUtil.mutListener.listen(10771)) {
                                                    if (thumbnailFileHeader != null) {
                                                        try (ZipInputStream inputStream = zipFile.getInputStream(thumbnailFileHeader)) {
                                                            if (!ListenerUtil.mutListener.listen(10770)) {
                                                                this.fileService.writeConversationMediaThumbnail(model, IOUtils.toByteArray(inputStream));
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(10769)) {
                                            if (this.writeToDb) {
                                                byte[] imageData;
                                                try (ZipInputStream inputStream = zipFile.getInputStream(fileHeader)) {
                                                    imageData = IOUtils.toByteArray(inputStream);
                                                    if (!ListenerUtil.mutListener.listen(10765)) {
                                                        this.fileService.writeConversationMedia(model, imageData);
                                                    }
                                                }
                                                if (!ListenerUtil.mutListener.listen(10768)) {
                                                    if (MessageUtil.canHaveThumbnailFile(model)) {
                                                        // check if a thumbnail file is in backup
                                                        FileHeader thumbnailFileHeader = thumbnailFileHeaders.get(thumbnailPrefix + messageUid);
                                                        if (!ListenerUtil.mutListener.listen(10767)) {
                                                            // if no thumbnail file exist in backup, generate one
                                                            if (thumbnailFileHeader == null) {
                                                                if (!ListenerUtil.mutListener.listen(10766)) {
                                                                    this.fileService.writeConversationMediaThumbnail(model, imageData);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(10774)) {
                                    count++;
                                }
                                if (!ListenerUtil.mutListener.listen(10775)) {
                                    updateProgress(STEP_SIZE_MEDIA);
                                }
                            } catch (RestoreCanceledException e) {
                                throw new RestoreCanceledException();
                            } catch (Exception x) {
                                if (!ListenerUtil.mutListener.listen(10764)) {
                                    logger.error("Exception", x);
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(10763)) {
                                count++;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    private boolean restoreContactFile(FileHeader fileHeader) throws IOException, RestoreCanceledException {
        return this.processCsvFile(fileHeader, new ProcessCsvFile() {

            @Override
            public void row(CSVRow row) {
                try {
                    ContactModel contactModel = createContactModel(row, restoreSettings);
                    if (!ListenerUtil.mutListener.listen(10783)) {
                        if (writeToDb) {
                            // set the default color
                            ContactModelFactory contactModelFactory = databaseServiceNew.getContactModelFactory();
                            if (!ListenerUtil.mutListener.listen(10780)) {
                                contactModel.setColor(ColorUtil.getInstance().getRecordColor((int) contactModelFactory.count()));
                            }
                            if (!ListenerUtil.mutListener.listen(10781)) {
                                contactModelFactory.createOrUpdate(contactModel);
                            }
                            if (!ListenerUtil.mutListener.listen(10782)) {
                                restoreResult.incContactSuccess();
                            }
                        }
                    }
                } catch (Exception x) {
                    if (!ListenerUtil.mutListener.listen(10779)) {
                        if (writeToDb) {
                            if (!ListenerUtil.mutListener.listen(10778)) {
                                // process next
                                restoreResult.incContactFailed();
                            }
                        }
                    }
                }
            }
        });
    }

    private boolean restoreContactAvatarFile(FileHeader fileHeader) {
        if (!ListenerUtil.mutListener.listen(10790)) {
            if (fileHeader != null) {
                // fileHeader.getFileName().startsWith(Tags.CONTACT_AVATAR_FILE_PREFIX)) {
                String filename = fileHeader.getFileName();
                if (!ListenerUtil.mutListener.listen(10789)) {
                    if (!TestUtil.empty(filename)) {
                        String identity = filename.substring(Tags.CONTACT_AVATAR_FILE_PREFIX.length());
                        if (!ListenerUtil.mutListener.listen(10788)) {
                            if (!TestUtil.empty(identity)) {
                                ContactModel contactModel = contactService.getByIdentity(identity);
                                if (!ListenerUtil.mutListener.listen(10787)) {
                                    if (contactModel != null) {
                                        try (ZipInputStream inputStream = zipFile.getInputStream(fileHeader)) {
                                            boolean success = fileService.writeContactAvatar(contactModel, IOUtils.toByteArray(inputStream));
                                            if (!ListenerUtil.mutListener.listen(10786)) {
                                                if (contactModel.getIdentity().equals(contactService.getMe().getIdentity())) {
                                                    if (!ListenerUtil.mutListener.listen(10785)) {
                                                        preferenceService.setProfilePicLastUpdate(new Date());
                                                    }
                                                }
                                            }
                                            return success;
                                        } catch (Exception e) {
                                            if (!ListenerUtil.mutListener.listen(10784)) {
                                                logger.error("Exception", e);
                                            }
                                        } finally {
                                            // 
                                            ;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean restoreContactPhotoFile(FileHeader fileHeader) {
        if (!ListenerUtil.mutListener.listen(10795)) {
            if (fileHeader != null) {
                String filename = fileHeader.getFileName();
                if (!ListenerUtil.mutListener.listen(10794)) {
                    if (!TestUtil.empty(filename)) {
                        String identity = filename.substring(Tags.CONTACT_PROFILE_PIC_FILE_PREFIX.length());
                        if (!ListenerUtil.mutListener.listen(10793)) {
                            if (!TestUtil.empty(identity)) {
                                ContactModel contactModel = contactService.getByIdentity(identity);
                                if (!ListenerUtil.mutListener.listen(10792)) {
                                    if (contactModel != null) {
                                        try (ZipInputStream inputStream = zipFile.getInputStream(fileHeader)) {
                                            return fileService.writeContactPhoto(contactModel, IOUtils.toByteArray(inputStream));
                                        } catch (Exception e) {
                                            if (!ListenerUtil.mutListener.listen(10791)) {
                                                logger.error("Exception", e);
                                            }
                                        } finally {
                                            // 
                                            ;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean restoreGroupFile(FileHeader fileHeader) throws IOException, RestoreCanceledException {
        return this.processCsvFile(fileHeader, new ProcessCsvFile() {

            @Override
            public void row(CSVRow row) {
                try {
                    GroupModel groupModel = createGroupModel(row, restoreSettings);
                    if (!ListenerUtil.mutListener.listen(10801)) {
                        if (writeToDb) {
                            if (!ListenerUtil.mutListener.listen(10798)) {
                                databaseServiceNew.getGroupModelFactory().create(groupModel);
                            }
                            if (!ListenerUtil.mutListener.listen(10799)) {
                                groupIdMap.put(BackupUtils.buildGroupUid(groupModel), groupModel.getId());
                            }
                            if (!ListenerUtil.mutListener.listen(10800)) {
                                restoreResult.incContactSuccess();
                            }
                        }
                    }
                    List<GroupMemberModel> groupMemberModels = createGroupMembers(row, groupModel.getId());
                    if (!ListenerUtil.mutListener.listen(10808)) {
                        if (writeToDb) {
                            if (!ListenerUtil.mutListener.listen(10803)) {
                                {
                                    long _loopCounter108 = 0;
                                    for (GroupMemberModel groupMemberModel : groupMemberModels) {
                                        ListenerUtil.loopListener.listen("_loopCounter108", ++_loopCounter108);
                                        if (!ListenerUtil.mutListener.listen(10802)) {
                                            databaseServiceNew.getGroupMemberModelFactory().create(groupMemberModel);
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(10807)) {
                                if (!groupModel.isDeleted()) {
                                    if (!ListenerUtil.mutListener.listen(10806)) {
                                        if (groupService.isGroupOwner(groupModel)) {
                                            if (!ListenerUtil.mutListener.listen(10805)) {
                                                groupService.sendSync(groupModel);
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(10804)) {
                                                groupService.requestSync(groupModel.getCreatorIdentity(), new GroupId(Utils.hexStringToByteArray(groupModel.getApiGroupId())));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception x) {
                    if (!ListenerUtil.mutListener.listen(10797)) {
                        if (writeToDb) {
                            if (!ListenerUtil.mutListener.listen(10796)) {
                                // process next
                                restoreResult.incContactFailed();
                            }
                        }
                    }
                }
            }
        });
    }

    private boolean restoreDistributionListFile(FileHeader fileHeader) throws IOException, RestoreCanceledException {
        return this.processCsvFile(fileHeader, new ProcessCsvFile() {

            @Override
            public void row(CSVRow row) {
                try {
                    DistributionListModel distributionListModel = createDistributionListModel(row);
                    if (!ListenerUtil.mutListener.listen(10814)) {
                        if (writeToDb) {
                            if (!ListenerUtil.mutListener.listen(10811)) {
                                databaseServiceNew.getDistributionListModelFactory().create(distributionListModel);
                            }
                            if (!ListenerUtil.mutListener.listen(10812)) {
                                distributionListIdMap.put(BackupUtils.buildDistributionListUid(distributionListModel), distributionListModel.getId());
                            }
                            if (!ListenerUtil.mutListener.listen(10813)) {
                                restoreResult.incContactSuccess();
                            }
                        }
                    }
                    List<DistributionListMemberModel> distributionListMemberModels = createDistributionListMembers(row, distributionListModel.getId());
                    if (!ListenerUtil.mutListener.listen(10817)) {
                        if (writeToDb) {
                            if (!ListenerUtil.mutListener.listen(10816)) {
                                {
                                    long _loopCounter109 = 0;
                                    for (DistributionListMemberModel distributionListMemberModel : distributionListMemberModels) {
                                        ListenerUtil.loopListener.listen("_loopCounter109", ++_loopCounter109);
                                        if (!ListenerUtil.mutListener.listen(10815)) {
                                            databaseServiceNew.getDistributionListMemberModelFactory().create(distributionListMemberModel);
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception x) {
                    if (!ListenerUtil.mutListener.listen(10810)) {
                        if (writeToDb) {
                            if (!ListenerUtil.mutListener.listen(10809)) {
                                // process next
                                restoreResult.incContactFailed();
                            }
                        }
                    }
                }
            }
        });
    }

    private void restoreBallotFile(FileHeader ballotMain, final FileHeader ballotChoice, FileHeader ballotVote) throws IOException, RestoreCanceledException {
        if (!ListenerUtil.mutListener.listen(10831)) {
            this.processCsvFile(ballotMain, new ProcessCsvFile() {

                @Override
                public void row(CSVRow row) {
                    try {
                        BallotModel ballotModel = createBallotModel(row);
                        if (!ListenerUtil.mutListener.listen(10823)) {
                            if (writeToDb) {
                                if (!ListenerUtil.mutListener.listen(10820)) {
                                    databaseServiceNew.getBallotModelFactory().create(ballotModel);
                                }
                                if (!ListenerUtil.mutListener.listen(10821)) {
                                    ballotIdMap.put(BackupUtils.buildBallotUid(ballotModel), ballotModel.getId());
                                }
                                if (!ListenerUtil.mutListener.listen(10822)) {
                                    ballotOldIdMap.put(row.getInteger(Tags.TAG_BALLOT_ID), ballotModel.getId());
                                }
                            }
                        }
                        LinkBallotModel ballotLinkModel = createLinkBallotModel(row, ballotModel.getId());
                        if (!ListenerUtil.mutListener.listen(10830)) {
                            if (writeToDb) {
                                if (!ListenerUtil.mutListener.listen(10825)) {
                                    if (ballotLinkModel == null) {
                                        if (!ListenerUtil.mutListener.listen(10824)) {
                                            // link failed
                                            logger.error("link failed");
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(10829)) {
                                    if (ballotLinkModel instanceof GroupBallotModel) {
                                        if (!ListenerUtil.mutListener.listen(10828)) {
                                            databaseServiceNew.getGroupBallotModelFactory().create((GroupBallotModel) ballotLinkModel);
                                        }
                                    } else if (ballotLinkModel instanceof IdentityBallotModel) {
                                        if (!ListenerUtil.mutListener.listen(10827)) {
                                            databaseServiceNew.getIdentityBallotModelFactory().create((IdentityBallotModel) ballotLinkModel);
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(10826)) {
                                            logger.error("not handled link");
                                        }
                                    }
                                }
                            }
                        }
                    } catch (Exception x) {
                        if (!ListenerUtil.mutListener.listen(10819)) {
                            if (writeToDb) {
                                if (!ListenerUtil.mutListener.listen(10818)) {
                                    // process next
                                    restoreResult.incContactFailed();
                                }
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(10837)) {
            this.processCsvFile(ballotChoice, new ProcessCsvFile() {

                @Override
                public void row(CSVRow row) {
                    try {
                        BallotChoiceModel ballotChoiceModel = createBallotChoiceModel(row);
                        if (!ListenerUtil.mutListener.listen(10836)) {
                            if ((ListenerUtil.mutListener.listen(10833) ? (ballotChoiceModel != null || writeToDb) : (ballotChoiceModel != null && writeToDb))) {
                                if (!ListenerUtil.mutListener.listen(10834)) {
                                    databaseServiceNew.getBallotChoiceModelFactory().create(ballotChoiceModel);
                                }
                                if (!ListenerUtil.mutListener.listen(10835)) {
                                    ballotChoiceIdMap.put(BackupUtils.buildBallotChoiceUid(ballotChoiceModel), ballotChoiceModel.getId());
                                }
                            }
                        }
                    } catch (Exception x) {
                        if (!ListenerUtil.mutListener.listen(10832)) {
                            logger.error("Exception", x);
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(10842)) {
            this.processCsvFile(ballotVote, new ProcessCsvFile() {

                @Override
                public void row(CSVRow row) {
                    try {
                        BallotVoteModel ballotVoteModel = createBallotVoteModel(row);
                        if (!ListenerUtil.mutListener.listen(10841)) {
                            if ((ListenerUtil.mutListener.listen(10839) ? (ballotVoteModel != null || writeToDb) : (ballotVoteModel != null && writeToDb))) {
                                if (!ListenerUtil.mutListener.listen(10840)) {
                                    databaseServiceNew.getBallotVoteModelFactory().create(ballotVoteModel);
                                }
                            }
                        }
                    } catch (Exception x) {
                        if (!ListenerUtil.mutListener.listen(10838)) {
                            logger.error("Exception", x);
                        }
                    }
                }
            });
        }
    }

    private GroupModel createGroupModel(CSVRow row, RestoreSettings restoreSettings) throws ThreemaException {
        GroupModel groupModel = new GroupModel();
        if (!ListenerUtil.mutListener.listen(10843)) {
            groupModel.setApiGroupId(row.getString(Tags.TAG_GROUP_ID));
        }
        if (!ListenerUtil.mutListener.listen(10844)) {
            groupModel.setCreatorIdentity(row.getString(Tags.TAG_GROUP_CREATOR));
        }
        if (!ListenerUtil.mutListener.listen(10845)) {
            groupModel.setName(row.getString(Tags.TAG_GROUP_NAME));
        }
        if (!ListenerUtil.mutListener.listen(10846)) {
            groupModel.setCreatedAt(row.getDate(Tags.TAG_GROUP_CREATED_AT));
        }
        if (!ListenerUtil.mutListener.listen(10854)) {
            if ((ListenerUtil.mutListener.listen(10851) ? (restoreSettings.getVersion() <= 4) : (ListenerUtil.mutListener.listen(10850) ? (restoreSettings.getVersion() > 4) : (ListenerUtil.mutListener.listen(10849) ? (restoreSettings.getVersion() < 4) : (ListenerUtil.mutListener.listen(10848) ? (restoreSettings.getVersion() != 4) : (ListenerUtil.mutListener.listen(10847) ? (restoreSettings.getVersion() == 4) : (restoreSettings.getVersion() >= 4))))))) {
                if (!ListenerUtil.mutListener.listen(10853)) {
                    groupModel.setDeleted(row.getBoolean(Tags.TAG_GROUP_DELETED));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10852)) {
                    groupModel.setDeleted(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10861)) {
            if ((ListenerUtil.mutListener.listen(10859) ? (restoreSettings.getVersion() <= 14) : (ListenerUtil.mutListener.listen(10858) ? (restoreSettings.getVersion() > 14) : (ListenerUtil.mutListener.listen(10857) ? (restoreSettings.getVersion() < 14) : (ListenerUtil.mutListener.listen(10856) ? (restoreSettings.getVersion() != 14) : (ListenerUtil.mutListener.listen(10855) ? (restoreSettings.getVersion() == 14) : (restoreSettings.getVersion() >= 14))))))) {
                if (!ListenerUtil.mutListener.listen(10860)) {
                    groupModel.setArchived(row.getBoolean(Tags.TAG_GROUP_ARCHIVED));
                }
            }
        }
        return groupModel;
    }

    private BallotModel createBallotModel(CSVRow row) throws ThreemaException {
        BallotModel ballotModel = new BallotModel();
        if (!ListenerUtil.mutListener.listen(10862)) {
            ballotModel.setApiBallotId(row.getString(Tags.TAG_BALLOT_API_ID));
        }
        if (!ListenerUtil.mutListener.listen(10863)) {
            ballotModel.setCreatorIdentity(row.getString(Tags.TAG_BALLOT_API_CREATOR));
        }
        if (!ListenerUtil.mutListener.listen(10864)) {
            ballotModel.setName(row.getString(Tags.TAG_BALLOT_NAME));
        }
        String state = row.getString(Tags.TAG_BALLOT_STATE);
        if (!ListenerUtil.mutListener.listen(10868)) {
            if (TestUtil.compare(state, BallotModel.State.CLOSED.toString())) {
                if (!ListenerUtil.mutListener.listen(10867)) {
                    ballotModel.setState(BallotModel.State.CLOSED);
                }
            } else if (TestUtil.compare(state, BallotModel.State.OPEN.toString())) {
                if (!ListenerUtil.mutListener.listen(10866)) {
                    ballotModel.setState(BallotModel.State.OPEN);
                }
            } else if (TestUtil.compare(state, BallotModel.State.TEMPORARY.toString())) {
                if (!ListenerUtil.mutListener.listen(10865)) {
                    ballotModel.setState(BallotModel.State.TEMPORARY);
                }
            }
        }
        String assessment = row.getString(Tags.TAG_BALLOT_ASSESSMENT);
        if (!ListenerUtil.mutListener.listen(10871)) {
            if (TestUtil.compare(assessment, BallotModel.Assessment.MULTIPLE_CHOICE.toString())) {
                if (!ListenerUtil.mutListener.listen(10870)) {
                    ballotModel.setAssessment(BallotModel.Assessment.MULTIPLE_CHOICE);
                }
            } else if (TestUtil.compare(assessment, BallotModel.Assessment.SINGLE_CHOICE.toString())) {
                if (!ListenerUtil.mutListener.listen(10869)) {
                    ballotModel.setAssessment(BallotModel.Assessment.SINGLE_CHOICE);
                }
            }
        }
        String type = row.getString(Tags.TAG_BALLOT_TYPE);
        if (!ListenerUtil.mutListener.listen(10874)) {
            if (TestUtil.compare(type, BallotModel.Type.INTERMEDIATE.toString())) {
                if (!ListenerUtil.mutListener.listen(10873)) {
                    ballotModel.setType(BallotModel.Type.INTERMEDIATE);
                }
            } else if (TestUtil.compare(type, BallotModel.Type.RESULT_ON_CLOSE.toString())) {
                if (!ListenerUtil.mutListener.listen(10872)) {
                    ballotModel.setType(BallotModel.Type.RESULT_ON_CLOSE);
                }
            }
        }
        String choiceType = row.getString(Tags.TAG_BALLOT_C_TYPE);
        if (!ListenerUtil.mutListener.listen(10876)) {
            if (TestUtil.compare(choiceType, BallotModel.ChoiceType.TEXT.toString())) {
                if (!ListenerUtil.mutListener.listen(10875)) {
                    ballotModel.setChoiceType(BallotModel.ChoiceType.TEXT);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10877)) {
            ballotModel.setLastViewedAt(row.getDate(Tags.TAG_BALLOT_LAST_VIEWED_AT));
        }
        if (!ListenerUtil.mutListener.listen(10878)) {
            ballotModel.setCreatedAt(row.getDate(Tags.TAG_BALLOT_CREATED_AT));
        }
        if (!ListenerUtil.mutListener.listen(10879)) {
            ballotModel.setModifiedAt(row.getDate(Tags.TAG_BALLOT_MODIFIED_AT));
        }
        return ballotModel;
    }

    private LinkBallotModel createLinkBallotModel(CSVRow row, int ballotId) throws ThreemaException {
        String reference = row.getString(Tags.TAG_BALLOT_REF);
        String referenceId = row.getString(Tags.TAG_BALLOT_REF_ID);
        Integer groupId = null;
        String identity = null;
        if (!ListenerUtil.mutListener.listen(10887)) {
            if (reference.endsWith("GroupBallotModel")) {
                if (!ListenerUtil.mutListener.listen(10886)) {
                    groupId = this.groupIdMap.get(referenceId);
                }
            } else if (reference.endsWith("IdentityBallotModel")) {
                if (!ListenerUtil.mutListener.listen(10885)) {
                    identity = referenceId;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10880)) {
                    // first try to get the reference as group
                    groupId = this.groupIdMap.get(referenceId);
                }
                if (!ListenerUtil.mutListener.listen(10884)) {
                    if (groupId == null) {
                        if (!ListenerUtil.mutListener.listen(10883)) {
                            if ((ListenerUtil.mutListener.listen(10881) ? (referenceId != null || referenceId.length() == ProtocolDefines.IDENTITY_LEN) : (referenceId != null && referenceId.length() == ProtocolDefines.IDENTITY_LEN))) {
                                if (!ListenerUtil.mutListener.listen(10882)) {
                                    identity = referenceId;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10892)) {
            if (groupId != null) {
                GroupBallotModel linkBallotModel = new GroupBallotModel();
                if (!ListenerUtil.mutListener.listen(10890)) {
                    linkBallotModel.setBallotId(ballotId);
                }
                if (!ListenerUtil.mutListener.listen(10891)) {
                    linkBallotModel.setGroupId(groupId);
                }
                return linkBallotModel;
            } else if (identity != null) {
                IdentityBallotModel linkBallotModel = new IdentityBallotModel();
                if (!ListenerUtil.mutListener.listen(10888)) {
                    linkBallotModel.setBallotId(ballotId);
                }
                if (!ListenerUtil.mutListener.listen(10889)) {
                    linkBallotModel.setIdentity(referenceId);
                }
                return linkBallotModel;
            }
        }
        if (!ListenerUtil.mutListener.listen(10894)) {
            if (writeToDb) {
                if (!ListenerUtil.mutListener.listen(10893)) {
                    logger.error("invalid ballot reference " + reference + " with id " + referenceId);
                }
                return null;
            }
        }
        // not a valid reference!
        return null;
    }

    private BallotChoiceModel createBallotChoiceModel(CSVRow row) throws ThreemaException {
        Integer ballotId = ballotIdMap.get(row.getString(Tags.TAG_BALLOT_CHOICE_BALLOT_UID));
        if (!ListenerUtil.mutListener.listen(10896)) {
            if (ballotId == null) {
                if (!ListenerUtil.mutListener.listen(10895)) {
                    logger.error("invalid ballotId");
                }
                return null;
            }
        }
        BallotChoiceModel ballotChoiceModel = new BallotChoiceModel();
        if (!ListenerUtil.mutListener.listen(10897)) {
            ballotChoiceModel.setBallotId(ballotId);
        }
        if (!ListenerUtil.mutListener.listen(10898)) {
            ballotChoiceModel.setApiBallotChoiceId(row.getInteger(Tags.TAG_BALLOT_CHOICE_API_ID));
        }
        if (!ListenerUtil.mutListener.listen(10899)) {
            ballotChoiceModel.setApiBallotChoiceId(row.getInteger(Tags.TAG_BALLOT_CHOICE_API_ID));
        }
        String type = row.getString(Tags.TAG_BALLOT_CHOICE_TYPE);
        if (!ListenerUtil.mutListener.listen(10901)) {
            if (TestUtil.compare(type, BallotChoiceModel.Type.Text.toString())) {
                if (!ListenerUtil.mutListener.listen(10900)) {
                    ballotChoiceModel.setType(BallotChoiceModel.Type.Text);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10902)) {
            ballotChoiceModel.setName(row.getString(Tags.TAG_BALLOT_CHOICE_NAME));
        }
        if (!ListenerUtil.mutListener.listen(10903)) {
            ballotChoiceModel.setVoteCount(row.getInteger(Tags.TAG_BALLOT_CHOICE_VOTE_COUNT));
        }
        if (!ListenerUtil.mutListener.listen(10904)) {
            ballotChoiceModel.setOrder(row.getInteger(Tags.TAG_BALLOT_CHOICE_ORDER));
        }
        if (!ListenerUtil.mutListener.listen(10905)) {
            ballotChoiceModel.setCreatedAt(row.getDate(Tags.TAG_BALLOT_CHOICE_CREATED_AT));
        }
        if (!ListenerUtil.mutListener.listen(10906)) {
            ballotChoiceModel.setModifiedAt(row.getDate(Tags.TAG_BALLOT_CHOICE_MODIFIED_AT));
        }
        return ballotChoiceModel;
    }

    private BallotVoteModel createBallotVoteModel(CSVRow row) throws ThreemaException {
        Integer ballotId = ballotIdMap.get(row.getString(Tags.TAG_BALLOT_VOTE_BALLOT_UID));
        Integer ballotChoiceId = ballotChoiceIdMap.get(row.getString(Tags.TAG_BALLOT_VOTE_CHOICE_UID));
        if (!ListenerUtil.mutListener.listen(10907)) {
            if (!TestUtil.required(ballotId, ballotChoiceId)) {
                return null;
            }
        }
        BallotVoteModel ballotVoteModel = new BallotVoteModel();
        if (!ListenerUtil.mutListener.listen(10908)) {
            ballotVoteModel.setBallotId(ballotId);
        }
        if (!ListenerUtil.mutListener.listen(10909)) {
            ballotVoteModel.setBallotChoiceId(ballotChoiceId);
        }
        if (!ListenerUtil.mutListener.listen(10910)) {
            ballotVoteModel.setVotingIdentity(row.getString(Tags.TAG_BALLOT_VOTE_IDENTITY));
        }
        if (!ListenerUtil.mutListener.listen(10911)) {
            ballotVoteModel.setChoice(row.getInteger(Tags.TAG_BALLOT_VOTE_CHOICE));
        }
        if (!ListenerUtil.mutListener.listen(10912)) {
            ballotVoteModel.setCreatedAt(row.getDate(Tags.TAG_BALLOT_VOTE_CREATED_AT));
        }
        if (!ListenerUtil.mutListener.listen(10913)) {
            ballotVoteModel.setModifiedAt(row.getDate(Tags.TAG_BALLOT_VOTE_MODIFIED_AT));
        }
        return ballotVoteModel;
    }

    private int restoreContactMessageFile(FileHeader fileHeader) throws IOException, ThreemaException, RestoreCanceledException {
        final int[] count = { 0 };
        String fileName = fileHeader.getFileName();
        if (!ListenerUtil.mutListener.listen(10914)) {
            if (fileName == null) {
                throw new ThreemaException(null);
            }
        }
        final String identity = fileName.substring(Tags.MESSAGE_FILE_PREFIX.length(), fileName.indexOf(Tags.CSV_FILE_POSTFIX));
        if (!ListenerUtil.mutListener.listen(10915)) {
            if (TestUtil.empty(identity)) {
                throw new ThreemaException(null);
            }
        }
        if (!ListenerUtil.mutListener.listen(10916)) {
            if (!this.processCsvFile(fileHeader, row -> {
                try {
                    MessageModel messageModel = createMessageModel(row, restoreSettings);
                    messageModel.setIdentity(identity);
                    count[0]++;
                    if (writeToDb) {
                        updateProgress(STEP_SIZE_MESSAGES);
                        // faster, do not make a createORupdate to safe queries
                        databaseServiceNew.getMessageModelFactory().create(messageModel);
                        restoreResult.incMessageSuccess();
                    }
                } catch (RestoreCanceledException e) {
                    throw new RestoreCanceledException();
                } catch (Exception x) {
                    if (writeToDb) {
                        restoreResult.incMessageFailed();
                    }
                }
            })) {
                throw new ThreemaException(null);
            }
        }
        return count[0];
    }

    private int restoreGroupMessageFile(FileHeader fileHeader) throws IOException, ThreemaException, RestoreCanceledException {
        final int[] count = { 0 };
        String fileName = fileHeader.getFileName();
        if (!ListenerUtil.mutListener.listen(10917)) {
            if (fileName == null) {
                throw new ThreemaException(null);
            }
        }
        String[] pieces = fileName.substring(Tags.GROUP_MESSAGE_FILE_PREFIX.length(), fileName.indexOf(Tags.CSV_FILE_POSTFIX)).split("-");
        if (!ListenerUtil.mutListener.listen(10923)) {
            if ((ListenerUtil.mutListener.listen(10922) ? (pieces.length >= 2) : (ListenerUtil.mutListener.listen(10921) ? (pieces.length <= 2) : (ListenerUtil.mutListener.listen(10920) ? (pieces.length > 2) : (ListenerUtil.mutListener.listen(10919) ? (pieces.length < 2) : (ListenerUtil.mutListener.listen(10918) ? (pieces.length == 2) : (pieces.length != 2))))))) {
                throw new ThreemaException(null);
            }
        }
        final String apiId = pieces[0];
        final String identity = pieces[1];
        if (!ListenerUtil.mutListener.listen(10924)) {
            if (TestUtil.empty(apiId, identity)) {
                throw new ThreemaException(null);
            }
        }
        if (!ListenerUtil.mutListener.listen(10925)) {
            if (!this.processCsvFile(fileHeader, row -> {
                try {
                    GroupMessageModel groupMessageModel = createGroupMessageModel(row, restoreSettings);
                    count[0]++;
                    if (writeToDb) {
                        updateProgress(STEP_SIZE_MESSAGES);
                        Integer groupId = null;
                        if (groupIdMap.containsKey(BackupUtils.buildGroupUid(apiId, identity))) {
                            groupId = groupIdMap.get(BackupUtils.buildGroupUid(apiId, identity));
                        }
                        if (groupId != null) {
                            groupMessageModel.setGroupId(groupId);
                            databaseServiceNew.getGroupMessageModelFactory().create(groupMessageModel);
                        }
                        restoreResult.incMessageSuccess();
                    }
                } catch (RestoreCanceledException e) {
                    throw new RestoreCanceledException();
                } catch (Exception x) {
                    if (writeToDb) {
                        restoreResult.incMessageFailed();
                    }
                }
            })) {
                throw new ThreemaException(null);
            }
        }
        return count[0];
    }

    private int restoreDistributionListMessageFile(FileHeader fileHeader) throws IOException, ThreemaException, RestoreCanceledException {
        final int[] count = { 0 };
        String fileName = fileHeader.getFileName();
        if (!ListenerUtil.mutListener.listen(10926)) {
            if (fileName == null) {
                throw new ThreemaException(null);
            }
        }
        String[] pieces = fileName.substring(Tags.DISTRIBUTION_LIST_MESSAGE_FILE_PREFIX.length(), fileName.indexOf(Tags.CSV_FILE_POSTFIX)).split("-");
        if (!ListenerUtil.mutListener.listen(10932)) {
            if ((ListenerUtil.mutListener.listen(10931) ? (pieces.length >= 1) : (ListenerUtil.mutListener.listen(10930) ? (pieces.length <= 1) : (ListenerUtil.mutListener.listen(10929) ? (pieces.length > 1) : (ListenerUtil.mutListener.listen(10928) ? (pieces.length < 1) : (ListenerUtil.mutListener.listen(10927) ? (pieces.length == 1) : (pieces.length != 1))))))) {
                throw new ThreemaException(null);
            }
        }
        final String apiId = pieces[0];
        if (!ListenerUtil.mutListener.listen(10933)) {
            if (TestUtil.empty(apiId)) {
                throw new ThreemaException(null);
            }
        }
        if (!ListenerUtil.mutListener.listen(10934)) {
            if (!this.processCsvFile(fileHeader, row -> {
                try {
                    DistributionListMessageModel distributionListMessageModel = createDistributionListMessageModel(row, restoreSettings);
                    count[0]++;
                    if (writeToDb) {
                        updateProgress(STEP_SIZE_MESSAGES);
                        Integer distributionListId = null;
                        if (distributionListIdMap.containsKey(apiId)) {
                            distributionListId = distributionListIdMap.get(apiId);
                        }
                        if (distributionListId != null) {
                            distributionListMessageModel.setDistributionListId(distributionListId);
                            databaseServiceNew.getDistributionListMessageModelFactory().createOrUpdate(distributionListMessageModel);
                        }
                        restoreResult.incContactSuccess();
                    }
                } catch (RestoreCanceledException e) {
                    throw new RestoreCanceledException();
                } catch (Exception x) {
                    if (writeToDb) {
                        restoreResult.incMessageFailed();
                    }
                }
            })) {
                throw new ThreemaException(null);
            }
        }
        return count[0];
    }

    private DistributionListModel createDistributionListModel(CSVRow row) throws ThreemaException {
        DistributionListModel distributionListModel = new DistributionListModel();
        if (!ListenerUtil.mutListener.listen(10935)) {
            distributionListModel.setName(row.getString(Tags.TAG_DISTRIBUTION_LIST_NAME));
        }
        if (!ListenerUtil.mutListener.listen(10936)) {
            distributionListModel.setCreatedAt(row.getDate(Tags.TAG_DISTRIBUTION_CREATED_AT));
        }
        if (!ListenerUtil.mutListener.listen(10943)) {
            if ((ListenerUtil.mutListener.listen(10941) ? (restoreSettings.getVersion() <= 14) : (ListenerUtil.mutListener.listen(10940) ? (restoreSettings.getVersion() > 14) : (ListenerUtil.mutListener.listen(10939) ? (restoreSettings.getVersion() < 14) : (ListenerUtil.mutListener.listen(10938) ? (restoreSettings.getVersion() != 14) : (ListenerUtil.mutListener.listen(10937) ? (restoreSettings.getVersion() == 14) : (restoreSettings.getVersion() >= 14))))))) {
                if (!ListenerUtil.mutListener.listen(10942)) {
                    distributionListModel.setArchived(row.getBoolean(Tags.TAG_DISTRIBUTION_LIST_ARCHIVED));
                }
            }
        }
        return distributionListModel;
    }

    private List<GroupMemberModel> createGroupMembers(CSVRow row, int groupId) throws ThreemaException {
        List<GroupMemberModel> res = new ArrayList<GroupMemberModel>();
        if (!ListenerUtil.mutListener.listen(10949)) {
            {
                long _loopCounter110 = 0;
                for (String identity : row.getStrings(Tags.TAG_GROUP_MEMBERS)) {
                    ListenerUtil.loopListener.listen("_loopCounter110", ++_loopCounter110);
                    if (!ListenerUtil.mutListener.listen(10948)) {
                        if (!TestUtil.empty(identity)) {
                            GroupMemberModel m = new GroupMemberModel();
                            if (!ListenerUtil.mutListener.listen(10944)) {
                                m.setGroupId(groupId);
                            }
                            if (!ListenerUtil.mutListener.listen(10945)) {
                                m.setIdentity(identity);
                            }
                            if (!ListenerUtil.mutListener.listen(10946)) {
                                m.setActive(true);
                            }
                            if (!ListenerUtil.mutListener.listen(10947)) {
                                res.add(m);
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    private List<DistributionListMemberModel> createDistributionListMembers(CSVRow row, int distributionListId) throws ThreemaException {
        List<DistributionListMemberModel> res = new ArrayList<DistributionListMemberModel>();
        if (!ListenerUtil.mutListener.listen(10955)) {
            {
                long _loopCounter111 = 0;
                for (String identity : row.getStrings(Tags.TAG_DISTRIBUTION_MEMBERS)) {
                    ListenerUtil.loopListener.listen("_loopCounter111", ++_loopCounter111);
                    if (!ListenerUtil.mutListener.listen(10954)) {
                        if (!TestUtil.empty(identity)) {
                            DistributionListMemberModel m = new DistributionListMemberModel();
                            if (!ListenerUtil.mutListener.listen(10950)) {
                                m.setDistributionListId(distributionListId);
                            }
                            if (!ListenerUtil.mutListener.listen(10951)) {
                                m.setIdentity(identity);
                            }
                            if (!ListenerUtil.mutListener.listen(10952)) {
                                m.setActive(true);
                            }
                            if (!ListenerUtil.mutListener.listen(10953)) {
                                res.add(m);
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    private ContactModel createContactModel(CSVRow row, RestoreSettings restoreSettings) throws ThreemaException {
        ContactModel contactModel = new ContactModel(row.getString(Tags.TAG_CONTACT_IDENTITY), Utils.hexStringToByteArray(row.getString(Tags.TAG_CONTACT_PUBLIC_KEY)));
        String verificationString = row.getString(Tags.TAG_CONTACT_VERIFICATION_LEVEL);
        VerificationLevel verification = VerificationLevel.UNVERIFIED;
        if (!ListenerUtil.mutListener.listen(10958)) {
            if (verificationString.equals(VerificationLevel.SERVER_VERIFIED.name())) {
                if (!ListenerUtil.mutListener.listen(10957)) {
                    verification = VerificationLevel.SERVER_VERIFIED;
                }
            } else if (verificationString.equals(VerificationLevel.FULLY_VERIFIED.name())) {
                if (!ListenerUtil.mutListener.listen(10956)) {
                    verification = VerificationLevel.FULLY_VERIFIED;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10959)) {
            contactModel.setVerificationLevel(verification);
        }
        if (!ListenerUtil.mutListener.listen(10960)) {
            contactModel.setFirstName(row.getString(Tags.TAG_CONTACT_FIRST_NAME));
        }
        if (!ListenerUtil.mutListener.listen(10961)) {
            contactModel.setLastName(row.getString(Tags.TAG_CONTACT_LAST_NAME));
        }
        if (!ListenerUtil.mutListener.listen(10968)) {
            if ((ListenerUtil.mutListener.listen(10966) ? (restoreSettings.getVersion() <= 3) : (ListenerUtil.mutListener.listen(10965) ? (restoreSettings.getVersion() > 3) : (ListenerUtil.mutListener.listen(10964) ? (restoreSettings.getVersion() < 3) : (ListenerUtil.mutListener.listen(10963) ? (restoreSettings.getVersion() != 3) : (ListenerUtil.mutListener.listen(10962) ? (restoreSettings.getVersion() == 3) : (restoreSettings.getVersion() >= 3))))))) {
                if (!ListenerUtil.mutListener.listen(10967)) {
                    contactModel.setPublicNickName(row.getString(Tags.TAG_CONTACT_NICK_NAME));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10975)) {
            if ((ListenerUtil.mutListener.listen(10973) ? (restoreSettings.getVersion() <= 13) : (ListenerUtil.mutListener.listen(10972) ? (restoreSettings.getVersion() > 13) : (ListenerUtil.mutListener.listen(10971) ? (restoreSettings.getVersion() < 13) : (ListenerUtil.mutListener.listen(10970) ? (restoreSettings.getVersion() != 13) : (ListenerUtil.mutListener.listen(10969) ? (restoreSettings.getVersion() == 13) : (restoreSettings.getVersion() >= 13))))))) {
                if (!ListenerUtil.mutListener.listen(10974)) {
                    contactModel.setIsHidden(row.getBoolean(Tags.TAG_CONTACT_HIDDEN));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10982)) {
            if ((ListenerUtil.mutListener.listen(10980) ? (restoreSettings.getVersion() <= 14) : (ListenerUtil.mutListener.listen(10979) ? (restoreSettings.getVersion() > 14) : (ListenerUtil.mutListener.listen(10978) ? (restoreSettings.getVersion() < 14) : (ListenerUtil.mutListener.listen(10977) ? (restoreSettings.getVersion() != 14) : (ListenerUtil.mutListener.listen(10976) ? (restoreSettings.getVersion() == 14) : (restoreSettings.getVersion() >= 14))))))) {
                if (!ListenerUtil.mutListener.listen(10981)) {
                    contactModel.setArchived(row.getBoolean(Tags.TAG_CONTACT_ARCHIVED));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10983)) {
            contactModel.setIsRestored(true);
        }
        return contactModel;
    }

    private void fillMessageModel(AbstractMessageModel messageModel, CSVRow row, RestoreSettings restoreSettings) throws ThreemaException {
        if (!ListenerUtil.mutListener.listen(10984)) {
            messageModel.setApiMessageId(row.getString(Tags.TAG_MESSAGE_API_MESSAGE_ID));
        }
        if (!ListenerUtil.mutListener.listen(10985)) {
            messageModel.setOutbox(row.getBoolean(Tags.TAG_MESSAGE_IS_OUTBOX));
        }
        if (!ListenerUtil.mutListener.listen(10986)) {
            messageModel.setRead(row.getBoolean(Tags.TAG_MESSAGE_IS_READ));
        }
        if (!ListenerUtil.mutListener.listen(10987)) {
            messageModel.setSaved(row.getBoolean(Tags.TAG_MESSAGE_IS_SAVED));
        }
        String messageState = row.getString(Tags.TAG_MESSAGE_MESSAGE_STATE);
        MessageState state = null;
        if (!ListenerUtil.mutListener.listen(10997)) {
            if (messageState.equals(MessageState.PENDING.name())) {
                if (!ListenerUtil.mutListener.listen(10996)) {
                    state = MessageState.PENDING;
                }
            } else if (messageState.equals(MessageState.SENDFAILED.name())) {
                if (!ListenerUtil.mutListener.listen(10995)) {
                    state = MessageState.SENDFAILED;
                }
            } else if (messageState.equals(MessageState.USERACK.name())) {
                if (!ListenerUtil.mutListener.listen(10994)) {
                    state = MessageState.USERACK;
                }
            } else if (messageState.equals(MessageState.USERDEC.name())) {
                if (!ListenerUtil.mutListener.listen(10993)) {
                    state = MessageState.USERDEC;
                }
            } else if (messageState.equals(MessageState.DELIVERED.name())) {
                if (!ListenerUtil.mutListener.listen(10992)) {
                    state = MessageState.DELIVERED;
                }
            } else if (messageState.equals(MessageState.READ.name())) {
                if (!ListenerUtil.mutListener.listen(10991)) {
                    state = MessageState.READ;
                }
            } else if (messageState.equals(MessageState.SENDING.name())) {
                if (!ListenerUtil.mutListener.listen(10990)) {
                    state = MessageState.SENDING;
                }
            } else if (messageState.equals(MessageState.SENT.name())) {
                if (!ListenerUtil.mutListener.listen(10989)) {
                    state = MessageState.SENT;
                }
            } else if (messageState.equals(MessageState.CONSUMED.name())) {
                if (!ListenerUtil.mutListener.listen(10988)) {
                    state = MessageState.CONSUMED;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10998)) {
            messageModel.setState(state);
        }
        MessageType messageType = MessageType.TEXT;
        @MessageContentsType
        int messageContentsType = MessageContentsType.UNDEFINED;
        String typeAsString = row.getString(Tags.TAG_MESSAGE_TYPE);
        if (!ListenerUtil.mutListener.listen(11017)) {
            if (typeAsString.equals(MessageType.VIDEO.name())) {
                if (!ListenerUtil.mutListener.listen(11015)) {
                    messageType = MessageType.VIDEO;
                }
                if (!ListenerUtil.mutListener.listen(11016)) {
                    messageContentsType = MessageContentsType.VIDEO;
                }
            } else if (typeAsString.equals(MessageType.VOICEMESSAGE.name())) {
                if (!ListenerUtil.mutListener.listen(11013)) {
                    messageType = MessageType.VOICEMESSAGE;
                }
                if (!ListenerUtil.mutListener.listen(11014)) {
                    messageContentsType = MessageContentsType.VOICE_MESSAGE;
                }
            } else if (typeAsString.equals(MessageType.LOCATION.name())) {
                if (!ListenerUtil.mutListener.listen(11011)) {
                    messageType = MessageType.LOCATION;
                }
                if (!ListenerUtil.mutListener.listen(11012)) {
                    messageContentsType = MessageContentsType.LOCATION;
                }
            } else if (typeAsString.equals(MessageType.IMAGE.name())) {
                if (!ListenerUtil.mutListener.listen(11009)) {
                    messageType = MessageType.IMAGE;
                }
                if (!ListenerUtil.mutListener.listen(11010)) {
                    messageContentsType = MessageContentsType.IMAGE;
                }
            } else if (typeAsString.equals(MessageType.CONTACT.name())) {
                if (!ListenerUtil.mutListener.listen(11007)) {
                    messageType = MessageType.CONTACT;
                }
                if (!ListenerUtil.mutListener.listen(11008)) {
                    messageContentsType = MessageContentsType.CONTACT;
                }
            } else if (typeAsString.equals(MessageType.BALLOT.name())) {
                if (!ListenerUtil.mutListener.listen(11005)) {
                    messageType = MessageType.BALLOT;
                }
                if (!ListenerUtil.mutListener.listen(11006)) {
                    messageContentsType = MessageContentsType.BALLOT;
                }
            } else if (typeAsString.equals(MessageType.FILE.name())) {
                if (!ListenerUtil.mutListener.listen(11001)) {
                    messageType = MessageType.FILE;
                }
                // get mime type from body
                String body = row.getString(Tags.TAG_MESSAGE_BODY);
                if (!ListenerUtil.mutListener.listen(11004)) {
                    if (!TestUtil.empty(body)) {
                        FileDataModel fileDataModel = FileDataModel.create(body);
                        if (!ListenerUtil.mutListener.listen(11003)) {
                            messageContentsType = MimeUtil.getContentTypeFromFileData(fileDataModel);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(11002)) {
                            messageContentsType = MessageContentsType.FILE;
                        }
                    }
                }
            } else if (typeAsString.equals(MessageType.VOIP_STATUS.name())) {
                if (!ListenerUtil.mutListener.listen(10999)) {
                    messageType = MessageType.VOIP_STATUS;
                }
                if (!ListenerUtil.mutListener.listen(11000)) {
                    messageContentsType = MessageContentsType.VOIP_STATUS;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11018)) {
            messageModel.setType(messageType);
        }
        if (!ListenerUtil.mutListener.listen(11019)) {
            messageModel.setMessageContentsType(messageContentsType);
        }
        if (!ListenerUtil.mutListener.listen(11020)) {
            messageModel.setBody(row.getString(Tags.TAG_MESSAGE_BODY));
        }
        if (!ListenerUtil.mutListener.listen(11024)) {
            if (messageModel.getType() == MessageType.BALLOT) {
                // try to update to new ballot id
                BallotDataModel ballotData = messageModel.getBallotData();
                if (!ListenerUtil.mutListener.listen(11023)) {
                    if (ballotData != null) {
                        if (!ListenerUtil.mutListener.listen(11022)) {
                            if (this.ballotOldIdMap.containsKey(ballotData.getBallotId())) {
                                BallotDataModel newBallotData = new BallotDataModel(ballotData.getType(), this.ballotOldIdMap.get(ballotData.getBallotId()));
                                if (!ListenerUtil.mutListener.listen(11021)) {
                                    messageModel.setBallotData(newBallotData);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11031)) {
            if ((ListenerUtil.mutListener.listen(11029) ? (restoreSettings.getVersion() <= 2) : (ListenerUtil.mutListener.listen(11028) ? (restoreSettings.getVersion() > 2) : (ListenerUtil.mutListener.listen(11027) ? (restoreSettings.getVersion() < 2) : (ListenerUtil.mutListener.listen(11026) ? (restoreSettings.getVersion() != 2) : (ListenerUtil.mutListener.listen(11025) ? (restoreSettings.getVersion() == 2) : (restoreSettings.getVersion() >= 2))))))) {
                if (!ListenerUtil.mutListener.listen(11030)) {
                    messageModel.setIsStatusMessage(row.getBoolean(Tags.TAG_MESSAGE_IS_STATUS_MESSAGE));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11038)) {
            if ((ListenerUtil.mutListener.listen(11036) ? (restoreSettings.getVersion() <= 10) : (ListenerUtil.mutListener.listen(11035) ? (restoreSettings.getVersion() > 10) : (ListenerUtil.mutListener.listen(11034) ? (restoreSettings.getVersion() < 10) : (ListenerUtil.mutListener.listen(11033) ? (restoreSettings.getVersion() != 10) : (ListenerUtil.mutListener.listen(11032) ? (restoreSettings.getVersion() == 10) : (restoreSettings.getVersion() >= 10))))))) {
                if (!ListenerUtil.mutListener.listen(11037)) {
                    messageModel.setCaption(row.getString(Tags.TAG_MESSAGE_CAPTION));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11046)) {
            if ((ListenerUtil.mutListener.listen(11043) ? (restoreSettings.getVersion() <= 15) : (ListenerUtil.mutListener.listen(11042) ? (restoreSettings.getVersion() > 15) : (ListenerUtil.mutListener.listen(11041) ? (restoreSettings.getVersion() < 15) : (ListenerUtil.mutListener.listen(11040) ? (restoreSettings.getVersion() != 15) : (ListenerUtil.mutListener.listen(11039) ? (restoreSettings.getVersion() == 15) : (restoreSettings.getVersion() >= 15))))))) {
                String quotedMessageId = row.getString(Tags.TAG_MESSAGE_QUOTED_MESSAGE_ID);
                if (!ListenerUtil.mutListener.listen(11045)) {
                    if (!TestUtil.empty(quotedMessageId)) {
                        if (!ListenerUtil.mutListener.listen(11044)) {
                            messageModel.setQuotedMessageId(quotedMessageId);
                        }
                    }
                }
            }
        }
    }

    private MessageModel createMessageModel(CSVRow row, RestoreSettings restoreSettings) throws ThreemaException {
        MessageModel messageModel = new MessageModel();
        if (!ListenerUtil.mutListener.listen(11047)) {
            this.fillMessageModel(messageModel, row, restoreSettings);
        }
        if (!ListenerUtil.mutListener.listen(11048)) {
            messageModel.setPostedAt(row.getDate(Tags.TAG_MESSAGE_POSTED_AT));
        }
        if (!ListenerUtil.mutListener.listen(11049)) {
            messageModel.setCreatedAt(row.getDate(Tags.TAG_MESSAGE_CREATED_AT));
        }
        if (!ListenerUtil.mutListener.listen(11056)) {
            if ((ListenerUtil.mutListener.listen(11054) ? (restoreSettings.getVersion() <= 5) : (ListenerUtil.mutListener.listen(11053) ? (restoreSettings.getVersion() > 5) : (ListenerUtil.mutListener.listen(11052) ? (restoreSettings.getVersion() < 5) : (ListenerUtil.mutListener.listen(11051) ? (restoreSettings.getVersion() != 5) : (ListenerUtil.mutListener.listen(11050) ? (restoreSettings.getVersion() == 5) : (restoreSettings.getVersion() >= 5))))))) {
                if (!ListenerUtil.mutListener.listen(11055)) {
                    messageModel.setModifiedAt(row.getDate(Tags.TAG_MESSAGE_MODIFIED_AT));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11057)) {
            messageModel.setUid(row.getString(Tags.TAG_MESSAGE_UID));
        }
        if (!ListenerUtil.mutListener.listen(11065)) {
            if ((ListenerUtil.mutListener.listen(11062) ? (restoreSettings.getVersion() <= 9) : (ListenerUtil.mutListener.listen(11061) ? (restoreSettings.getVersion() > 9) : (ListenerUtil.mutListener.listen(11060) ? (restoreSettings.getVersion() < 9) : (ListenerUtil.mutListener.listen(11059) ? (restoreSettings.getVersion() != 9) : (ListenerUtil.mutListener.listen(11058) ? (restoreSettings.getVersion() == 9) : (restoreSettings.getVersion() >= 9))))))) {
                if (!ListenerUtil.mutListener.listen(11064)) {
                    messageModel.setIsQueued(row.getBoolean(Tags.TAG_MESSAGE_IS_QUEUED));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11063)) {
                    messageModel.setIsQueued(true);
                }
            }
        }
        return messageModel;
    }

    private GroupMessageModel createGroupMessageModel(CSVRow row, RestoreSettings restoreSettings) throws ThreemaException {
        GroupMessageModel messageModel = new GroupMessageModel();
        if (!ListenerUtil.mutListener.listen(11066)) {
            this.fillMessageModel(messageModel, row, restoreSettings);
        }
        if (!ListenerUtil.mutListener.listen(11067)) {
            messageModel.setIdentity(row.getString(Tags.TAG_MESSAGE_IDENTITY));
        }
        if (!ListenerUtil.mutListener.listen(11068)) {
            messageModel.setPostedAt(row.getDate(Tags.TAG_MESSAGE_POSTED_AT));
        }
        if (!ListenerUtil.mutListener.listen(11069)) {
            messageModel.setCreatedAt(row.getDate(Tags.TAG_MESSAGE_CREATED_AT));
        }
        if (!ListenerUtil.mutListener.listen(11076)) {
            if ((ListenerUtil.mutListener.listen(11074) ? (restoreSettings.getVersion() <= 5) : (ListenerUtil.mutListener.listen(11073) ? (restoreSettings.getVersion() > 5) : (ListenerUtil.mutListener.listen(11072) ? (restoreSettings.getVersion() < 5) : (ListenerUtil.mutListener.listen(11071) ? (restoreSettings.getVersion() != 5) : (ListenerUtil.mutListener.listen(11070) ? (restoreSettings.getVersion() == 5) : (restoreSettings.getVersion() >= 5))))))) {
                if (!ListenerUtil.mutListener.listen(11075)) {
                    messageModel.setModifiedAt(row.getDate(Tags.TAG_MESSAGE_MODIFIED_AT));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11084)) {
            if ((ListenerUtil.mutListener.listen(11081) ? (restoreSettings.getVersion() <= 9) : (ListenerUtil.mutListener.listen(11080) ? (restoreSettings.getVersion() > 9) : (ListenerUtil.mutListener.listen(11079) ? (restoreSettings.getVersion() < 9) : (ListenerUtil.mutListener.listen(11078) ? (restoreSettings.getVersion() != 9) : (ListenerUtil.mutListener.listen(11077) ? (restoreSettings.getVersion() == 9) : (restoreSettings.getVersion() >= 9))))))) {
                if (!ListenerUtil.mutListener.listen(11083)) {
                    messageModel.setIsQueued(row.getBoolean(Tags.TAG_MESSAGE_IS_QUEUED));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11082)) {
                    messageModel.setIsQueued(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11085)) {
            messageModel.setUid(row.getString(Tags.TAG_MESSAGE_UID));
        }
        return messageModel;
    }

    private DistributionListMessageModel createDistributionListMessageModel(CSVRow row, RestoreSettings restoreSettings) throws ThreemaException {
        DistributionListMessageModel messageModel = new DistributionListMessageModel();
        if (!ListenerUtil.mutListener.listen(11086)) {
            this.fillMessageModel(messageModel, row, restoreSettings);
        }
        if (!ListenerUtil.mutListener.listen(11087)) {
            messageModel.setIdentity(row.getString(Tags.TAG_MESSAGE_IDENTITY));
        }
        if (!ListenerUtil.mutListener.listen(11088)) {
            messageModel.setPostedAt(row.getDate(Tags.TAG_MESSAGE_POSTED_AT));
        }
        if (!ListenerUtil.mutListener.listen(11089)) {
            messageModel.setCreatedAt(row.getDate(Tags.TAG_MESSAGE_CREATED_AT));
        }
        if (!ListenerUtil.mutListener.listen(11096)) {
            if ((ListenerUtil.mutListener.listen(11094) ? (restoreSettings.getVersion() <= 5) : (ListenerUtil.mutListener.listen(11093) ? (restoreSettings.getVersion() > 5) : (ListenerUtil.mutListener.listen(11092) ? (restoreSettings.getVersion() < 5) : (ListenerUtil.mutListener.listen(11091) ? (restoreSettings.getVersion() != 5) : (ListenerUtil.mutListener.listen(11090) ? (restoreSettings.getVersion() == 5) : (restoreSettings.getVersion() >= 5))))))) {
                if (!ListenerUtil.mutListener.listen(11095)) {
                    messageModel.setModifiedAt(row.getDate(Tags.TAG_MESSAGE_MODIFIED_AT));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11104)) {
            if ((ListenerUtil.mutListener.listen(11101) ? (restoreSettings.getVersion() <= 9) : (ListenerUtil.mutListener.listen(11100) ? (restoreSettings.getVersion() > 9) : (ListenerUtil.mutListener.listen(11099) ? (restoreSettings.getVersion() < 9) : (ListenerUtil.mutListener.listen(11098) ? (restoreSettings.getVersion() != 9) : (ListenerUtil.mutListener.listen(11097) ? (restoreSettings.getVersion() == 9) : (restoreSettings.getVersion() >= 9))))))) {
                if (!ListenerUtil.mutListener.listen(11103)) {
                    messageModel.setIsQueued(row.getBoolean(Tags.TAG_MESSAGE_IS_QUEUED));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11102)) {
                    messageModel.setIsQueued(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11105)) {
            messageModel.setUid(row.getString(Tags.TAG_MESSAGE_UID));
        }
        return messageModel;
    }

    private boolean processCsvFile(FileHeader fileHeader, ProcessCsvFile processCsvFile) throws IOException, RestoreCanceledException {
        if (!ListenerUtil.mutListener.listen(11106)) {
            if (processCsvFile == null) {
                return false;
            }
        }
        try (ZipInputStream inputStream = this.zipFile.getInputStream(fileHeader);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            CSVReader csvReader = new CSVReader(inputStreamReader, true)) {
            CSVRow row;
            if (!ListenerUtil.mutListener.listen(11108)) {
                {
                    long _loopCounter112 = 0;
                    while ((row = csvReader.readNextRow()) != null) {
                        ListenerUtil.loopListener.listen("_loopCounter112", ++_loopCounter112);
                        if (!ListenerUtil.mutListener.listen(11107)) {
                            processCsvFile.row(row);
                        }
                    }
                }
            }
        }
        return true;
    }

    private void initProgress(long steps) {
        if (!ListenerUtil.mutListener.listen(11109)) {
            this.currentProgressStep = 0;
        }
        if (!ListenerUtil.mutListener.listen(11110)) {
            this.progressSteps = steps;
        }
        if (!ListenerUtil.mutListener.listen(11111)) {
            this.latestPercentStep = 0;
        }
        if (!ListenerUtil.mutListener.listen(11112)) {
            this.startTime = System.currentTimeMillis();
        }
        if (!ListenerUtil.mutListener.listen(11113)) {
            this.handleProgress();
        }
    }

    private void updateProgress(long increment) throws RestoreCanceledException {
        if (!ListenerUtil.mutListener.listen(11114)) {
            if (isCanceled) {
                throw new RestoreCanceledException();
            }
        }
        if (!ListenerUtil.mutListener.listen(11117)) {
            if (writeToDb) {
                if (!ListenerUtil.mutListener.listen(11115)) {
                    this.currentProgressStep += increment;
                }
                if (!ListenerUtil.mutListener.listen(11116)) {
                    handleProgress();
                }
            }
        }
    }

    /**
     *  only call progress on 100 steps
     */
    private void handleProgress() {
        int p = (int) ((ListenerUtil.mutListener.listen(11125) ? ((ListenerUtil.mutListener.listen(11121) ? (100d % (double) this.progressSteps) : (ListenerUtil.mutListener.listen(11120) ? (100d * (double) this.progressSteps) : (ListenerUtil.mutListener.listen(11119) ? (100d - (double) this.progressSteps) : (ListenerUtil.mutListener.listen(11118) ? (100d + (double) this.progressSteps) : (100d / (double) this.progressSteps))))) % (double) this.currentProgressStep) : (ListenerUtil.mutListener.listen(11124) ? ((ListenerUtil.mutListener.listen(11121) ? (100d % (double) this.progressSteps) : (ListenerUtil.mutListener.listen(11120) ? (100d * (double) this.progressSteps) : (ListenerUtil.mutListener.listen(11119) ? (100d - (double) this.progressSteps) : (ListenerUtil.mutListener.listen(11118) ? (100d + (double) this.progressSteps) : (100d / (double) this.progressSteps))))) / (double) this.currentProgressStep) : (ListenerUtil.mutListener.listen(11123) ? ((ListenerUtil.mutListener.listen(11121) ? (100d % (double) this.progressSteps) : (ListenerUtil.mutListener.listen(11120) ? (100d * (double) this.progressSteps) : (ListenerUtil.mutListener.listen(11119) ? (100d - (double) this.progressSteps) : (ListenerUtil.mutListener.listen(11118) ? (100d + (double) this.progressSteps) : (100d / (double) this.progressSteps))))) - (double) this.currentProgressStep) : (ListenerUtil.mutListener.listen(11122) ? ((ListenerUtil.mutListener.listen(11121) ? (100d % (double) this.progressSteps) : (ListenerUtil.mutListener.listen(11120) ? (100d * (double) this.progressSteps) : (ListenerUtil.mutListener.listen(11119) ? (100d - (double) this.progressSteps) : (ListenerUtil.mutListener.listen(11118) ? (100d + (double) this.progressSteps) : (100d / (double) this.progressSteps))))) + (double) this.currentProgressStep) : ((ListenerUtil.mutListener.listen(11121) ? (100d % (double) this.progressSteps) : (ListenerUtil.mutListener.listen(11120) ? (100d * (double) this.progressSteps) : (ListenerUtil.mutListener.listen(11119) ? (100d - (double) this.progressSteps) : (ListenerUtil.mutListener.listen(11118) ? (100d + (double) this.progressSteps) : (100d / (double) this.progressSteps))))) * (double) this.currentProgressStep))))));
        if (!ListenerUtil.mutListener.listen(11133)) {
            if ((ListenerUtil.mutListener.listen(11130) ? (p >= this.latestPercentStep) : (ListenerUtil.mutListener.listen(11129) ? (p <= this.latestPercentStep) : (ListenerUtil.mutListener.listen(11128) ? (p < this.latestPercentStep) : (ListenerUtil.mutListener.listen(11127) ? (p != this.latestPercentStep) : (ListenerUtil.mutListener.listen(11126) ? (p == this.latestPercentStep) : (p > this.latestPercentStep))))))) {
                if (!ListenerUtil.mutListener.listen(11131)) {
                    this.latestPercentStep = p;
                }
                if (!ListenerUtil.mutListener.listen(11132)) {
                    updatePersistentNotification(latestPercentStep, 100, false);
                }
            }
        }
    }

    public void onFinished(String message) {
        if (!ListenerUtil.mutListener.listen(11134)) {
            logger.debug("onFinished success = " + restoreSuccess);
        }
        if (!ListenerUtil.mutListener.listen(11135)) {
            cancelPersistentNotification();
        }
        if (!ListenerUtil.mutListener.listen(11142)) {
            if ((ListenerUtil.mutListener.listen(11136) ? (restoreSuccess || userService.hasIdentity()) : (restoreSuccess && userService.hasIdentity()))) {
                if (!ListenerUtil.mutListener.listen(11140)) {
                    preferenceService.setWizardRunning(true);
                }
                if (!ListenerUtil.mutListener.listen(11141)) {
                    showRestoreSuccessNotification();
                }
            } else {
                try {
                    if (!ListenerUtil.mutListener.listen(11138)) {
                        this.userService.removeIdentity();
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(11137)) {
                        logger.error("Exception", e);
                    }
                }
                if (!ListenerUtil.mutListener.listen(11139)) {
                    showRestoreErrorNotification(message);
                }
            }
        }
        // try to reopen connection
        try {
            if (!ListenerUtil.mutListener.listen(11145)) {
                if (!serviceManager.getConnection().isRunning()) {
                    if (!ListenerUtil.mutListener.listen(11144)) {
                        serviceManager.startConnection();
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(11143)) {
                logger.error("Exception", e);
            }
        }
        if (!ListenerUtil.mutListener.listen(11149)) {
            if ((ListenerUtil.mutListener.listen(11146) ? (wakeLock != null || wakeLock.isHeld()) : (wakeLock != null && wakeLock.isHeld()))) {
                if (!ListenerUtil.mutListener.listen(11147)) {
                    logger.debug("releasing wakelock");
                }
                if (!ListenerUtil.mutListener.listen(11148)) {
                    wakeLock.release();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11150)) {
            stopForeground(true);
        }
        if (!ListenerUtil.mutListener.listen(11151)) {
            isRunning = false;
        }
        if (!ListenerUtil.mutListener.listen(11162)) {
            if ((ListenerUtil.mutListener.listen(11156) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(11155) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(11154) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(11153) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(11152) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.P) : (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P))))))) {
                if (!ListenerUtil.mutListener.listen(11161)) {
                    ConfigUtils.scheduleAppRestart(getApplicationContext(), (ListenerUtil.mutListener.listen(11160) ? (2 % (int) DateUtils.SECOND_IN_MILLIS) : (ListenerUtil.mutListener.listen(11159) ? (2 / (int) DateUtils.SECOND_IN_MILLIS) : (ListenerUtil.mutListener.listen(11158) ? (2 - (int) DateUtils.SECOND_IN_MILLIS) : (ListenerUtil.mutListener.listen(11157) ? (2 + (int) DateUtils.SECOND_IN_MILLIS) : (2 * (int) DateUtils.SECOND_IN_MILLIS))))), getApplicationContext().getResources().getString(R.string.ipv6_restart_now));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11163)) {
            stopSelf();
        }
    }

    private Notification getPersistentNotification() {
        if (!ListenerUtil.mutListener.listen(11164)) {
            logger.debug("getPersistentNotification");
        }
        Intent cancelIntent = new Intent(this, RestoreService.class);
        if (!ListenerUtil.mutListener.listen(11165)) {
            cancelIntent.putExtra(EXTRA_ID_CANCEL, true);
        }
        PendingIntent cancelPendingIntent;
        if ((ListenerUtil.mutListener.listen(11170) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(11169) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(11168) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(11167) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(11166) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O))))))) {
            cancelPendingIntent = PendingIntent.getForegroundService(this, (int) System.currentTimeMillis(), cancelIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        } else {
            cancelPendingIntent = PendingIntent.getService(this, (int) System.currentTimeMillis(), cancelIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        }
        if (!ListenerUtil.mutListener.listen(11171)) {
            notificationBuilder = new NotificationBuilderWrapper(this, NOTIFICATION_CHANNEL_BACKUP_RESTORE_IN_PROGRESS, null).setContentTitle(getString(R.string.restoring_backup)).setContentText(getString(R.string.please_wait)).setOngoing(true).setSmallIcon(R.drawable.ic_notification_small).setPriority(NotificationCompat.PRIORITY_DEFAULT).addAction(R.drawable.ic_close_white_24dp, getString(R.string.cancel), cancelPendingIntent);
        }
        return notificationBuilder.build();
    }

    private void updatePersistentNotification(int currentStep, int steps, boolean indeterminate) {
        if (!ListenerUtil.mutListener.listen(11172)) {
            logger.debug("updatePersistentNoti " + currentStep + " of " + steps);
        }
        if (!ListenerUtil.mutListener.listen(11199)) {
            if ((ListenerUtil.mutListener.listen(11177) ? (currentStep >= 0) : (ListenerUtil.mutListener.listen(11176) ? (currentStep <= 0) : (ListenerUtil.mutListener.listen(11175) ? (currentStep > 0) : (ListenerUtil.mutListener.listen(11174) ? (currentStep < 0) : (ListenerUtil.mutListener.listen(11173) ? (currentStep == 0) : (currentStep != 0))))))) {
                final long millisPassed = (ListenerUtil.mutListener.listen(11181) ? (System.currentTimeMillis() % startTime) : (ListenerUtil.mutListener.listen(11180) ? (System.currentTimeMillis() / startTime) : (ListenerUtil.mutListener.listen(11179) ? (System.currentTimeMillis() * startTime) : (ListenerUtil.mutListener.listen(11178) ? (System.currentTimeMillis() + startTime) : (System.currentTimeMillis() - startTime)))));
                final long millisRemaining = (ListenerUtil.mutListener.listen(11193) ? ((ListenerUtil.mutListener.listen(11189) ? ((ListenerUtil.mutListener.listen(11185) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(11184) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(11183) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(11182) ? (millisPassed + steps) : (millisPassed * steps))))) % currentStep) : (ListenerUtil.mutListener.listen(11188) ? ((ListenerUtil.mutListener.listen(11185) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(11184) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(11183) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(11182) ? (millisPassed + steps) : (millisPassed * steps))))) * currentStep) : (ListenerUtil.mutListener.listen(11187) ? ((ListenerUtil.mutListener.listen(11185) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(11184) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(11183) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(11182) ? (millisPassed + steps) : (millisPassed * steps))))) - currentStep) : (ListenerUtil.mutListener.listen(11186) ? ((ListenerUtil.mutListener.listen(11185) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(11184) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(11183) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(11182) ? (millisPassed + steps) : (millisPassed * steps))))) + currentStep) : ((ListenerUtil.mutListener.listen(11185) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(11184) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(11183) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(11182) ? (millisPassed + steps) : (millisPassed * steps))))) / currentStep))))) % millisPassed) : (ListenerUtil.mutListener.listen(11192) ? ((ListenerUtil.mutListener.listen(11189) ? ((ListenerUtil.mutListener.listen(11185) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(11184) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(11183) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(11182) ? (millisPassed + steps) : (millisPassed * steps))))) % currentStep) : (ListenerUtil.mutListener.listen(11188) ? ((ListenerUtil.mutListener.listen(11185) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(11184) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(11183) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(11182) ? (millisPassed + steps) : (millisPassed * steps))))) * currentStep) : (ListenerUtil.mutListener.listen(11187) ? ((ListenerUtil.mutListener.listen(11185) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(11184) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(11183) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(11182) ? (millisPassed + steps) : (millisPassed * steps))))) - currentStep) : (ListenerUtil.mutListener.listen(11186) ? ((ListenerUtil.mutListener.listen(11185) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(11184) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(11183) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(11182) ? (millisPassed + steps) : (millisPassed * steps))))) + currentStep) : ((ListenerUtil.mutListener.listen(11185) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(11184) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(11183) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(11182) ? (millisPassed + steps) : (millisPassed * steps))))) / currentStep))))) / millisPassed) : (ListenerUtil.mutListener.listen(11191) ? ((ListenerUtil.mutListener.listen(11189) ? ((ListenerUtil.mutListener.listen(11185) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(11184) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(11183) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(11182) ? (millisPassed + steps) : (millisPassed * steps))))) % currentStep) : (ListenerUtil.mutListener.listen(11188) ? ((ListenerUtil.mutListener.listen(11185) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(11184) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(11183) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(11182) ? (millisPassed + steps) : (millisPassed * steps))))) * currentStep) : (ListenerUtil.mutListener.listen(11187) ? ((ListenerUtil.mutListener.listen(11185) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(11184) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(11183) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(11182) ? (millisPassed + steps) : (millisPassed * steps))))) - currentStep) : (ListenerUtil.mutListener.listen(11186) ? ((ListenerUtil.mutListener.listen(11185) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(11184) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(11183) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(11182) ? (millisPassed + steps) : (millisPassed * steps))))) + currentStep) : ((ListenerUtil.mutListener.listen(11185) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(11184) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(11183) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(11182) ? (millisPassed + steps) : (millisPassed * steps))))) / currentStep))))) * millisPassed) : (ListenerUtil.mutListener.listen(11190) ? ((ListenerUtil.mutListener.listen(11189) ? ((ListenerUtil.mutListener.listen(11185) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(11184) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(11183) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(11182) ? (millisPassed + steps) : (millisPassed * steps))))) % currentStep) : (ListenerUtil.mutListener.listen(11188) ? ((ListenerUtil.mutListener.listen(11185) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(11184) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(11183) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(11182) ? (millisPassed + steps) : (millisPassed * steps))))) * currentStep) : (ListenerUtil.mutListener.listen(11187) ? ((ListenerUtil.mutListener.listen(11185) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(11184) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(11183) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(11182) ? (millisPassed + steps) : (millisPassed * steps))))) - currentStep) : (ListenerUtil.mutListener.listen(11186) ? ((ListenerUtil.mutListener.listen(11185) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(11184) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(11183) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(11182) ? (millisPassed + steps) : (millisPassed * steps))))) + currentStep) : ((ListenerUtil.mutListener.listen(11185) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(11184) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(11183) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(11182) ? (millisPassed + steps) : (millisPassed * steps))))) / currentStep))))) + millisPassed) : ((ListenerUtil.mutListener.listen(11189) ? ((ListenerUtil.mutListener.listen(11185) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(11184) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(11183) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(11182) ? (millisPassed + steps) : (millisPassed * steps))))) % currentStep) : (ListenerUtil.mutListener.listen(11188) ? ((ListenerUtil.mutListener.listen(11185) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(11184) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(11183) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(11182) ? (millisPassed + steps) : (millisPassed * steps))))) * currentStep) : (ListenerUtil.mutListener.listen(11187) ? ((ListenerUtil.mutListener.listen(11185) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(11184) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(11183) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(11182) ? (millisPassed + steps) : (millisPassed * steps))))) - currentStep) : (ListenerUtil.mutListener.listen(11186) ? ((ListenerUtil.mutListener.listen(11185) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(11184) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(11183) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(11182) ? (millisPassed + steps) : (millisPassed * steps))))) + currentStep) : ((ListenerUtil.mutListener.listen(11185) ? (millisPassed % steps) : (ListenerUtil.mutListener.listen(11184) ? (millisPassed / steps) : (ListenerUtil.mutListener.listen(11183) ? (millisPassed - steps) : (ListenerUtil.mutListener.listen(11182) ? (millisPassed + steps) : (millisPassed * steps))))) / currentStep))))) - millisPassed)))));
                String timeRemaining = StringConversionUtil.secondsToString((ListenerUtil.mutListener.listen(11197) ? (millisRemaining % DateUtils.SECOND_IN_MILLIS) : (ListenerUtil.mutListener.listen(11196) ? (millisRemaining * DateUtils.SECOND_IN_MILLIS) : (ListenerUtil.mutListener.listen(11195) ? (millisRemaining - DateUtils.SECOND_IN_MILLIS) : (ListenerUtil.mutListener.listen(11194) ? (millisRemaining + DateUtils.SECOND_IN_MILLIS) : (millisRemaining / DateUtils.SECOND_IN_MILLIS))))), false);
                if (!ListenerUtil.mutListener.listen(11198)) {
                    notificationBuilder.setContentText(String.format(getString(R.string.time_remaining), timeRemaining));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11200)) {
            notificationBuilder.setProgress(steps, currentStep, indeterminate);
        }
        if (!ListenerUtil.mutListener.listen(11201)) {
            notificationManager.notify(RESTORE_NOTIFICATION_ID, notificationBuilder.build());
        }
    }

    private void cancelPersistentNotification() {
        if (!ListenerUtil.mutListener.listen(11202)) {
            notificationManager.cancel(RESTORE_NOTIFICATION_ID);
        }
    }

    private void showRestoreErrorNotification(String message) {
        String contentText;
        if (!TestUtil.empty(message)) {
            contentText = message;
        } else {
            contentText = getString(R.string.restore_error_body);
        }
        NotificationCompat.Builder builder = new NotificationBuilderWrapper(this, NOTIFICATION_CHANNEL_ALERT, null).setSmallIcon(R.drawable.ic_notification_small).setTicker(getString(R.string.restore_error_body)).setContentTitle(getString(R.string.restoring_backup)).setContentText(contentText).setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE).setColor(getResources().getColor(R.color.material_red)).setPriority(NotificationCompat.PRIORITY_MAX).setStyle(new NotificationCompat.BigTextStyle().bigText(contentText)).setAutoCancel(false);
        if (!ListenerUtil.mutListener.listen(11203)) {
            notificationManager.notify(RESTORE_COMPLETION_NOTIFICATION_ID, builder.build());
        }
    }

    private void showRestoreSuccessNotification() {
        String text;
        NotificationCompat.Builder builder = new NotificationBuilderWrapper(this, NOTIFICATION_CHANNEL_ALERT, null).setSmallIcon(R.drawable.ic_notification_small).setTicker(getString(R.string.restore_success_body)).setContentTitle(getString(R.string.restoring_backup)).setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE).setColor(getResources().getColor(R.color.material_green)).setPriority(NotificationCompat.PRIORITY_MAX).setAutoCancel(true);
        if ((ListenerUtil.mutListener.listen(11208) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(11207) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(11206) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(11205) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(11204) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.P) : (Build.VERSION.SDK_INT > Build.VERSION_CODES.P))))))) {
            // Android Q does not allow restart in the background
            Intent backupIntent = new Intent(this, HomeActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), backupIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            if (!ListenerUtil.mutListener.listen(11209)) {
                builder.setContentIntent(pendingIntent);
            }
            text = getString(R.string.restore_success_body) + "\n" + getString(R.string.tap_to_start, getString(R.string.app_name));
        } else {
            text = getString(R.string.restore_success_body);
        }
        if (!ListenerUtil.mutListener.listen(11210)) {
            builder.setContentText(text);
        }
        if (!ListenerUtil.mutListener.listen(11211)) {
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(text));
        }
        if (!ListenerUtil.mutListener.listen(11212)) {
            notificationManager.notify(RESTORE_COMPLETION_NOTIFICATION_ID, builder.build());
        }
    }
}
