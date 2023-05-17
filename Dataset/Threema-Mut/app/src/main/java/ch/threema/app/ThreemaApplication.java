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
package ch.threema.app;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Environment;
import android.os.PowerManager;
import android.os.StrictMode;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.text.format.DateUtils;
import android.widget.Toast;
import com.datatheorem.android.trustkit.TrustKit;
import com.datatheorem.android.trustkit.reporting.BackgroundReporter;
import com.google.common.util.concurrent.ListenableFuture;
import com.mapbox.android.telemetry.TelemetryEnabler;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.TelemetryDefinition;
import net.sqlcipher.database.SQLiteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetSocketAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.multidex.MultiDexApplication;
import androidx.preference.PreferenceManager;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import ch.threema.app.backuprestore.csv.BackupService;
import ch.threema.app.exceptions.DatabaseMigrationFailedException;
import ch.threema.app.exceptions.FileSystemNotPresentException;
import ch.threema.app.jobs.WorkSyncJobService;
import ch.threema.app.jobs.WorkSyncService;
import ch.threema.app.listeners.BallotVoteListener;
import ch.threema.app.listeners.ContactListener;
import ch.threema.app.listeners.ContactSettingsListener;
import ch.threema.app.listeners.ContactTypingListener;
import ch.threema.app.listeners.ConversationListener;
import ch.threema.app.listeners.DistributionListListener;
import ch.threema.app.listeners.GroupListener;
import ch.threema.app.listeners.NewSyncedContactsListener;
import ch.threema.app.listeners.ServerMessageListener;
import ch.threema.app.listeners.SynchronizeContactsListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.messagereceiver.ContactMessageReceiver;
import ch.threema.app.messagereceiver.GroupMessageReceiver;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.push.PushService;
import ch.threema.app.receivers.ConnectivityChangeReceiver;
import ch.threema.app.receivers.PinningFailureReportBroadcastReceiver;
import ch.threema.app.receivers.RestrictBackgroundChangedReceiver;
import ch.threema.app.routines.OnFirstConnectRoutine;
import ch.threema.app.routines.SynchronizeContactsRoutine;
import ch.threema.app.services.AppRestrictionService;
import ch.threema.app.services.AvatarCacheService;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.ConversationService;
import ch.threema.app.services.DeadlineListService;
import ch.threema.app.services.FileService;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.MessageService;
import ch.threema.app.services.MessageServiceImpl;
import ch.threema.app.services.NotificationService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.SynchronizeContactsService;
import ch.threema.app.services.UpdateSystemService;
import ch.threema.app.services.UpdateSystemServiceImpl;
import ch.threema.app.services.UserService;
import ch.threema.app.services.ballot.BallotService;
import ch.threema.app.stores.IdentityStore;
import ch.threema.app.stores.PreferenceStore;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.ConnectionIndicatorUtil;
import ch.threema.app.utils.ConversationNotificationUtil;
import ch.threema.app.utils.FileUtil;
import ch.threema.app.utils.LinuxSecureRandom;
import ch.threema.app.utils.LoggingUEH;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.utils.PushUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.StateBitmapUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.utils.WidgetUtil;
import ch.threema.app.voip.Config;
import ch.threema.app.voip.listeners.VoipCallEventListener;
import ch.threema.app.voip.managers.VoipListenerManager;
import ch.threema.app.webclient.listeners.WebClientServiceListener;
import ch.threema.app.webclient.listeners.WebClientWakeUpListener;
import ch.threema.app.webclient.manager.WebClientListenerManager;
import ch.threema.app.webclient.services.SessionAndroidService;
import ch.threema.app.webclient.services.SessionWakeUpServiceImpl;
import ch.threema.app.webclient.services.instance.DisconnectContext;
import ch.threema.app.webclient.state.WebClientSessionState;
import ch.threema.app.workers.IdentityStatesWorker;
import ch.threema.base.ThreemaException;
import ch.threema.client.AppVersion;
import ch.threema.client.ConnectionState;
import ch.threema.client.ConnectionStateListener;
import ch.threema.client.NonceFactory;
import ch.threema.client.ThreemaConnection;
import ch.threema.client.Utils;
import ch.threema.localcrypto.MasterKey;
import ch.threema.localcrypto.MasterKeyLockedException;
import ch.threema.logging.backend.DebugLogFileBackend;
import ch.threema.storage.DatabaseServiceNew;
import ch.threema.storage.NonceDatabaseBlobService;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.ConversationModel;
import ch.threema.storage.models.DistributionListModel;
import ch.threema.storage.models.GroupModel;
import ch.threema.storage.models.MessageType;
import ch.threema.storage.models.ServerMessageModel;
import ch.threema.storage.models.WebClientSessionModel;
import ch.threema.storage.models.ballot.BallotModel;
import ch.threema.storage.models.ballot.GroupBallotModel;
import ch.threema.storage.models.ballot.IdentityBallotModel;
import ch.threema.storage.models.ballot.LinkBallotModel;
import ch.threema.storage.models.data.status.VoipStatusDataModel;
import static android.app.NotificationManager.ACTION_NOTIFICATION_CHANNEL_GROUP_BLOCK_STATE_CHANGED;
import static android.app.NotificationManager.ACTION_NOTIFICATION_POLICY_CHANGED;
import static android.app.NotificationManager.EXTRA_BLOCKED_STATE;
import static android.app.NotificationManager.EXTRA_NOTIFICATION_CHANNEL_GROUP_ID;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ThreemaApplication extends MultiDexApplication implements DefaultLifecycleObserver {

    private static final Logger logger = LoggerFactory.getLogger(ThreemaApplication.class);

    public static final String INTENT_DATA_CONTACT = "identity";

    public static final String INTENT_DATA_CONTACT_READONLY = "readonly";

    public static final String INTENT_DATA_TEXT = "text";

    public static final String INTENT_DATA_ID_BACKUP = "idbackup";

    public static final String INTENT_DATA_ID_BACKUP_PW = "idbackuppw";

    public static final String INTENT_DATA_PASSPHRASE_CHECK = "check";

    public static final String INTENT_DATA_IS_FORWARD = "is_forward";

    public static final String INTENT_DATA_TIMESTAMP = "timestamp";

    public static final String INTENT_DATA_EDITFOCUS = "editfocus";

    public static final String INTENT_DATA_GROUP = "group";

    public static final String INTENT_DATA_DISTRIBUTION_LIST = "distribution_list";

    public static final String INTENT_DATA_ARCHIVE_FILTER = "archiveFilter";

    public static final String INTENT_DATA_QRCODE = "qrcodestring";

    public static final String INTENT_DATA_QRCODE_TYPE_OK = "qrcodetypeok";

    public static final String INTENT_DATA_MESSAGE_ID = "messageid";

    public static final String EXTRA_VOICE_REPLY = "voicereply";

    public static final String EXTRA_OUTPUT_FILE = "output";

    public static final String EXTRA_ORIENTATION = "rotate";

    public static final String EXTRA_FLIP = "flip";

    public static final String EXTRA_EXIF_ORIENTATION = "rotateExif";

    public static final String EXTRA_EXIF_FLIP = "flipExif";

    public static final String INTENT_DATA_CHECK_ONLY = "check";

    public static final String INTENT_DATA_ANIM_CENTER = "itemPos";

    public static final String INTENT_DATA_PICK_FROM_CAMERA = "useCam";

    public static final String INTENT_PUSH_REGISTRATION_COMPLETE = "registrationComplete";

    public static final String INTENT_DATA_PIN = "ppin";

    public static final String INTENT_DATA_HIDE_RECENTS = "hiderec";

    public static final String INTENT_ACTION_FORWARD = "ch.threema.app.intent.FORWARD";

    public static final String CONFIRM_TAG_CLOSE_BALLOT = "cb";

    // Notification IDs
    public static final int NEW_MESSAGE_NOTIFICATION_ID = 723;

    public static final int MASTER_KEY_LOCKED_NOTIFICATION_ID = 724;

    public static final int NEW_MESSAGE_LOCKED_NOTIFICATION_ID = 725;

    public static final int NEW_MESSAGE_PIN_LOCKED_NOTIFICATION_ID = 726;

    public static final int SAFE_FAILED_NOTIFICATION_ID = 727;

    public static final int SERVER_MESSAGE_NOTIFICATION_ID = 730;

    public static final int NOT_ENOUGH_DISK_SPACE_NOTIFICATION_ID = 731;

    public static final int UNSENT_MESSAGE_NOTIFICATION_ID = 732;

    public static final int NETWORK_BLOCKED_NOTIFICATION_ID = 733;

    public static final int WORK_SYNC_NOTIFICATION_ID = 735;

    public static final int NEW_SYNCED_CONTACTS_NOTIFICATION_ID = 736;

    public static final int WEB_RESUME_FAILED_NOTIFICATION_ID = 737;

    public static final int PASSPHRASE_SERVICE_NOTIFICATION_ID = 587;

    public static final int INCOMING_CALL_NOTIFICATION_ID = 800;

    private static final String THREEMA_APPLICATION_LISTENER_TAG = "al";

    public static final String AES_KEY_FILE = "key.dat";

    public static final String ECHO_USER_IDENTITY = "ECHOECHO";

    public static final String PHONE_LINKED_PLACEHOLDER = "***";

    public static final String EMAIL_LINKED_PLACEHOLDER = "***@***";

    public static final long ACTIVITY_CONNECTION_LIFETIME = 60000;

    public static final int MAX_BLOB_SIZE_MB = 50;

    public static final int MAX_BLOB_SIZE = MAX_BLOB_SIZE_MB * 1024 * 1024;

    public static final int MIN_PIN_LENGTH = 4;

    public static final int MAX_PIN_LENGTH = 8;

    public static final int MIN_GROUP_MEMBERS_COUNT = 1;

    public static final int MIN_PW_LENGTH_BACKUP = 8;

    public static final int MAX_PW_LENGTH_BACKUP = 256;

    // extremely ancient versions of the app on some platform accepted four-letter passwords when generating ID exports
    public static final int MIN_PW_LENGTH_ID_EXPORT_LEGACY = 4;

    private static final int WORK_SYNC_JOB_ID = 63339;

    private static final String WORKER_IDENTITY_STATES_PERIODIC_NAME = "IdentityStates";

    private static Context context;

    private static volatile ServiceManager serviceManager;

    private static volatile AppVersion appVersion;

    private static volatile MasterKey masterKey;

    private static Date lastLoggedIn;

    private static long lastNotificationTimeStamp;

    private static boolean isDeviceIdle;

    private static boolean ipv6 = false;

    private static HashMap<String, String> messageDrafts = new HashMap<>();

    public static ExecutorService sendMessageExecutorService = Executors.newFixedThreadPool(4);

    public static ExecutorService sendMessageSingleThreadExecutorService = Executors.newSingleThreadExecutor();

    private static boolean checkAppReplacingState(Context context) {
        if (!ListenerUtil.mutListener.listen(65299)) {
            // workaround https://code.google.com/p/android/issues/detail?id=56296
            if (context.getResources() == null) {
                if (!ListenerUtil.mutListener.listen(65297)) {
                    logger.debug("App is currently installing. Killing it.");
                }
                if (!ListenerUtil.mutListener.listen(65298)) {
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
                return false;
            }
        }
        return true;
    }

    private void logStackTrace(StackTraceElement[] stackTraceElements) {
        if (!ListenerUtil.mutListener.listen(65306)) {
            {
                long _loopCounter796 = 0;
                for (int i = 1; (ListenerUtil.mutListener.listen(65305) ? (i >= stackTraceElements.length) : (ListenerUtil.mutListener.listen(65304) ? (i <= stackTraceElements.length) : (ListenerUtil.mutListener.listen(65303) ? (i > stackTraceElements.length) : (ListenerUtil.mutListener.listen(65302) ? (i != stackTraceElements.length) : (ListenerUtil.mutListener.listen(65301) ? (i == stackTraceElements.length) : (i < stackTraceElements.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter796", ++_loopCounter796);
                    if (!ListenerUtil.mutListener.listen(65300)) {
                        logger.info("\tat " + stackTraceElements[i]);
                    }
                }
            }
        }
    }

    private static void showNotesGroupNotice(GroupModel groupModel, @GroupService.GroupState int oldState, @GroupService.GroupState int newState) {
        if (!ListenerUtil.mutListener.listen(65321)) {
            if ((ListenerUtil.mutListener.listen(65311) ? (oldState >= newState) : (ListenerUtil.mutListener.listen(65310) ? (oldState <= newState) : (ListenerUtil.mutListener.listen(65309) ? (oldState > newState) : (ListenerUtil.mutListener.listen(65308) ? (oldState < newState) : (ListenerUtil.mutListener.listen(65307) ? (oldState == newState) : (oldState != newState))))))) {
                try {
                    GroupService groupService = serviceManager.getGroupService();
                    MessageService messageService = serviceManager.getMessageService();
                    if (!ListenerUtil.mutListener.listen(65320)) {
                        if ((ListenerUtil.mutListener.listen(65313) ? (groupService != null || messageService != null) : (groupService != null && messageService != null))) {
                            String notice = null;
                            if (!ListenerUtil.mutListener.listen(65317)) {
                                if (newState == GroupService.NOTES) {
                                    if (!ListenerUtil.mutListener.listen(65316)) {
                                        notice = serviceManager.getContext().getString(R.string.status_create_notes);
                                    }
                                } else if ((ListenerUtil.mutListener.listen(65314) ? (newState == GroupService.PEOPLE || oldState != GroupService.UNDEFINED) : (newState == GroupService.PEOPLE && oldState != GroupService.UNDEFINED))) {
                                    if (!ListenerUtil.mutListener.listen(65315)) {
                                        notice = serviceManager.getContext().getString(R.string.status_create_notes_off);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(65319)) {
                                if (notice != null) {
                                    if (!ListenerUtil.mutListener.listen(65318)) {
                                        messageService.createStatusMessage(notice, groupService.createReceiver(groupModel));
                                    }
                                }
                            }
                        }
                    }
                } catch (ThreemaException e) {
                    if (!ListenerUtil.mutListener.listen(65312)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
    }

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(65329)) {
            if ((ListenerUtil.mutListener.listen(65327) ? (BuildConfig.DEBUG || (ListenerUtil.mutListener.listen(65326) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(65325) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(65324) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(65323) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(65322) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.P) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P))))))) : (BuildConfig.DEBUG && (ListenerUtil.mutListener.listen(65326) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(65325) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(65324) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(65323) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(65322) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.P) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P))))))))) {
                if (!ListenerUtil.mutListener.listen(65328)) {
                    StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyListener(Executors.newSingleThreadExecutor(), v -> {
                        logger.info("STRICTMODE VMPolicy: " + v.getCause());
                        logStackTrace(v.getStackTrace());
                    }).build());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(65330)) {
            super.onCreate();
        }
        if (!ListenerUtil.mutListener.listen(65331)) {
            // always log database migration
            setupLogging(null);
        }
        if (!ListenerUtil.mutListener.listen(65332)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }
        if (!ListenerUtil.mutListener.listen(65333)) {
            context = getApplicationContext();
        }
        if (!ListenerUtil.mutListener.listen(65334)) {
            if (!checkAppReplacingState(context)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(65335)) {
            // Initialize TrustKit for CA pinning
            TrustKit.initializeWithNetworkSecurityConfiguration(this);
        }
        LoggingUEH loggingUEH = new LoggingUEH(getAppContext());
        if (!ListenerUtil.mutListener.listen(65336)) {
            loggingUEH.setRunOnUncaughtException(() -> {
                // if the message queue contents caused the crash.
                final File messageQueueFile = new File(getAppContext().getFilesDir(), MessageServiceImpl.MESSAGE_QUEUE_SAVE_FILE);
                FileUtil.deleteFileOrWarn(messageQueueFile, "message queue file", LoggerFactory.getLogger("LoggingUEH.runOnUncaughtException"));
            });
        }
        if (!ListenerUtil.mutListener.listen(65337)) {
            Thread.setDefaultUncaughtExceptionHandler(loggingUEH);
        }
        if (!ListenerUtil.mutListener.listen(65338)) {
            ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        }
        if (!ListenerUtil.mutListener.listen(65339)) {
            /* Instantiate our own SecureRandom implementation to make sure this gets used everywhere */
            new LinuxSecureRandom();
        }
        if (!ListenerUtil.mutListener.listen(65340)) {
            /* prepare app version object */
            appVersion = new AppVersion(ConfigUtils.getAppVersion(getAppContext()), "A", Locale.getDefault().getLanguage(), Locale.getDefault().getCountry(), Build.MODEL, Build.VERSION.RELEASE);
        }
        // create master key
        File filesDir = getAppContext().getFilesDir();
        if (!ListenerUtil.mutListener.listen(65421)) {
            if (filesDir != null) {
                if (!ListenerUtil.mutListener.listen(65341)) {
                    filesDir.mkdirs();
                }
                if (!ListenerUtil.mutListener.listen(65420)) {
                    if ((ListenerUtil.mutListener.listen(65342) ? (filesDir.exists() || filesDir.isDirectory()) : (filesDir.exists() && filesDir.isDirectory()))) {
                        File masterKeyFile = new File(filesDir, AES_KEY_FILE);
                        try {
                            boolean reset = !masterKeyFile.exists();
                            if (!ListenerUtil.mutListener.listen(65364)) {
                                if (reset) {
                                    if (!ListenerUtil.mutListener.listen(65345)) {
                                        logger.info("master key is missing or does not match. rename database files.");
                                    }
                                    File databaseFile = getAppContext().getDatabasePath(DatabaseServiceNew.DATABASE_NAME);
                                    if (!ListenerUtil.mutListener.listen(65348)) {
                                        if (databaseFile.exists()) {
                                            File databaseBackup = new File(databaseFile.getPath() + ".backup");
                                            if (!ListenerUtil.mutListener.listen(65347)) {
                                                if (!databaseFile.renameTo(databaseBackup)) {
                                                    if (!ListenerUtil.mutListener.listen(65346)) {
                                                        FileUtil.deleteFileOrWarn(databaseFile, "threema database", logger);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(65349)) {
                                        databaseFile = getAppContext().getDatabasePath(DatabaseServiceNew.DATABASE_NAME_V4);
                                    }
                                    if (!ListenerUtil.mutListener.listen(65352)) {
                                        if (databaseFile.exists()) {
                                            File databaseBackup = new File(databaseFile.getPath() + ".backup");
                                            if (!ListenerUtil.mutListener.listen(65351)) {
                                                if (!databaseFile.renameTo(databaseBackup)) {
                                                    if (!ListenerUtil.mutListener.listen(65350)) {
                                                        FileUtil.deleteFileOrWarn(databaseFile, "threema4 database", logger);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(65353)) {
                                        databaseFile = getAppContext().getDatabasePath(NonceDatabaseBlobService.DATABASE_NAME);
                                    }
                                    if (!ListenerUtil.mutListener.listen(65355)) {
                                        if (databaseFile.exists()) {
                                            if (!ListenerUtil.mutListener.listen(65354)) {
                                                FileUtil.deleteFileOrWarn(databaseFile, "nonce database", logger);
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(65356)) {
                                        databaseFile = getAppContext().getDatabasePath(NonceDatabaseBlobService.DATABASE_NAME_V4);
                                    }
                                    if (!ListenerUtil.mutListener.listen(65358)) {
                                        if (databaseFile.exists()) {
                                            if (!ListenerUtil.mutListener.listen(65357)) {
                                                FileUtil.deleteFileOrWarn(databaseFile, "nonce4 database", logger);
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(65359)) {
                                        // remove all settings!
                                        logger.info("initialize", "remove preferences");
                                    }
                                    PreferenceStore preferenceStore = new PreferenceStore(getAppContext(), masterKey);
                                    if (!ListenerUtil.mutListener.listen(65360)) {
                                        preferenceStore.clear();
                                    }
                                    // TODO: create a static getter for the file
                                    File messageQueueFile = new File(filesDir, MessageServiceImpl.MESSAGE_QUEUE_SAVE_FILE);
                                    if (!ListenerUtil.mutListener.listen(65363)) {
                                        if (messageQueueFile.exists()) {
                                            if (!ListenerUtil.mutListener.listen(65361)) {
                                                logger.info("remove message queue file");
                                            }
                                            if (!ListenerUtil.mutListener.listen(65362)) {
                                                FileUtil.deleteFileOrWarn(messageQueueFile, "message queue file", logger);
                                            }
                                        }
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(65344)) {
                                        logger.info("OK, masterKeyFile exists");
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(65365)) {
                                masterKey = new MasterKey(masterKeyFile, null, true);
                            }
                            if (!ListenerUtil.mutListener.listen(65367)) {
                                if (!masterKey.isLocked()) {
                                    if (!ListenerUtil.mutListener.listen(65366)) {
                                        reset();
                                    }
                                }
                            }
                        } catch (IOException e) {
                            if (!ListenerUtil.mutListener.listen(65343)) {
                                logger.error("IOException", e);
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(65368)) {
                            getAppContext().registerReceiver(new ConnectivityChangeReceiver(), new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
                        }
                        if (!ListenerUtil.mutListener.listen(65375)) {
                            if ((ListenerUtil.mutListener.listen(65373) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(65372) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(65371) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(65370) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(65369) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N))))))) {
                                if (!ListenerUtil.mutListener.listen(65374)) {
                                    getAppContext().registerReceiver(new RestrictBackgroundChangedReceiver(), new IntentFilter(ConnectivityManager.ACTION_RESTRICT_BACKGROUND_CHANGED));
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(65395)) {
                            if ((ListenerUtil.mutListener.listen(65380) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(65379) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(65378) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(65377) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(65376) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                                if (!ListenerUtil.mutListener.listen(65391)) {
                                    getAppContext().registerReceiver(new BroadcastReceiver() {

                                        @TargetApi(Build.VERSION_CODES.M)
                                        @Override
                                        public void onReceive(Context context, Intent intent) {
                                            PowerManager powerManager = (PowerManager) context.getApplicationContext().getSystemService(Context.POWER_SERVICE);
                                            if (!ListenerUtil.mutListener.listen(65390)) {
                                                if ((ListenerUtil.mutListener.listen(65381) ? (powerManager != null || powerManager.isDeviceIdleMode()) : (powerManager != null && powerManager.isDeviceIdleMode()))) {
                                                    if (!ListenerUtil.mutListener.listen(65384)) {
                                                        logger.info("*** Device going to deep sleep");
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(65385)) {
                                                        isDeviceIdle = true;
                                                    }
                                                    try {
                                                        if (!ListenerUtil.mutListener.listen(65387)) {
                                                            serviceManager.getLifetimeService().releaseConnection("doze");
                                                        }
                                                    } catch (Exception e) {
                                                        if (!ListenerUtil.mutListener.listen(65386)) {
                                                            logger.error("Exception while releasing connection", e);
                                                        }
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(65389)) {
                                                        if (BackupService.isRunning()) {
                                                            if (!ListenerUtil.mutListener.listen(65388)) {
                                                                context.stopService(new Intent(context, BackupService.class));
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    if (!ListenerUtil.mutListener.listen(65382)) {
                                                        logger.info("*** Device waking up");
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(65383)) {
                                                        isDeviceIdle = false;
                                                    }
                                                }
                                            }
                                        }
                                    }, new IntentFilter(PowerManager.ACTION_DEVICE_IDLE_MODE_CHANGED));
                                }
                                if (!ListenerUtil.mutListener.listen(65394)) {
                                    getAppContext().registerReceiver(new BroadcastReceiver() {

                                        @Override
                                        public void onReceive(Context context, Intent intent) {
                                            try {
                                                NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                                                NotificationManager.Policy policy = notificationManager.getNotificationPolicy();
                                                if (!ListenerUtil.mutListener.listen(65393)) {
                                                    logger.info("*** Notification Policy changed: " + policy.toString());
                                                }
                                            } catch (Exception e) {
                                                if (!ListenerUtil.mutListener.listen(65392)) {
                                                    logger.error("Could not get notification policy", e);
                                                }
                                            }
                                        }
                                    }, new IntentFilter(ACTION_NOTIFICATION_POLICY_CHANGED));
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(65404)) {
                            if ((ListenerUtil.mutListener.listen(65400) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(65399) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(65398) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(65397) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(65396) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.P) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P))))))) {
                                if (!ListenerUtil.mutListener.listen(65403)) {
                                    getAppContext().registerReceiver(new BroadcastReceiver() {

                                        @Override
                                        public void onReceive(Context context, Intent intent) {
                                            try {
                                                boolean blockedState = intent.getBooleanExtra(EXTRA_BLOCKED_STATE, false);
                                                String groupName = intent.getStringExtra(EXTRA_NOTIFICATION_CHANNEL_GROUP_ID);
                                                if (!ListenerUtil.mutListener.listen(65402)) {
                                                    logger.info("*** Channel group {} blocked: {}", groupName != null ? groupName : "<not specified>", blockedState);
                                                }
                                            } catch (Exception e) {
                                                if (!ListenerUtil.mutListener.listen(65401)) {
                                                    logger.error("Could not get data from intent", e);
                                                }
                                            }
                                        }
                                    }, new IntentFilter(ACTION_NOTIFICATION_CHANNEL_GROUP_BLOCK_STATE_CHANGED));
                                }
                            }
                        }
                        // Add a local broadcast receiver to receive PinningFailureReports
                        PinningFailureReportBroadcastReceiver receiver = new PinningFailureReportBroadcastReceiver();
                        if (!ListenerUtil.mutListener.listen(65405)) {
                            LocalBroadcastManager.getInstance(context).registerReceiver(receiver, new IntentFilter(BackgroundReporter.REPORT_VALIDATION_EVENT));
                        }
                        if (!ListenerUtil.mutListener.listen(65416)) {
                            // register a broadcast receiver for changes in app restrictions
                            if ((ListenerUtil.mutListener.listen(65411) ? (ConfigUtils.isWorkRestricted() || (ListenerUtil.mutListener.listen(65410) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(65409) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(65408) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(65407) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(65406) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) : (ConfigUtils.isWorkRestricted() && (ListenerUtil.mutListener.listen(65410) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(65409) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(65408) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(65407) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(65406) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))))) {
                                if (!ListenerUtil.mutListener.listen(65415)) {
                                    getAppContext().registerReceiver(new BroadcastReceiver() {

                                        @Override
                                        public void onReceive(Context context, Intent intent) {
                                            if (!ListenerUtil.mutListener.listen(65412)) {
                                                AppRestrictionService.getInstance().reload();
                                            }
                                            Intent syncIntent = new Intent();
                                            if (!ListenerUtil.mutListener.listen(65413)) {
                                                syncIntent.putExtra(WorkSyncService.EXTRA_WORK_UPDATE_RESTRICTIONS_ONLY, true);
                                            }
                                            if (!ListenerUtil.mutListener.listen(65414)) {
                                                WorkSyncService.enqueueWork(getAppContext(), syncIntent, true);
                                            }
                                        }
                                    }, new IntentFilter(Intent.ACTION_APPLICATION_RESTRICTIONS_CHANGED));
                                }
                            }
                        }
                        // setup locale override
                        try {
                            if (!ListenerUtil.mutListener.listen(65419)) {
                                if (getServiceManager() != null) {
                                    if (!ListenerUtil.mutListener.listen(65418)) {
                                        ConfigUtils.setLocaleOverride(this, getServiceManager().getPreferenceService());
                                    }
                                }
                            }
                        } catch (Exception e) {
                            if (!ListenerUtil.mutListener.listen(65417)) {
                                logger.error("Exception", e);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        if (!ListenerUtil.mutListener.listen(65422)) {
            logger.info("*** Lifecycle: App now visible");
        }
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        if (!ListenerUtil.mutListener.listen(65423)) {
            logger.info("*** Lifecycle: App now stopped");
        }
        if (!ListenerUtil.mutListener.listen(65426)) {
            if ((ListenerUtil.mutListener.listen(65425) ? ((ListenerUtil.mutListener.listen(65424) ? (masterKey == null && masterKey.isLocked()) : (masterKey == null || masterKey.isLocked())) && serviceManager == null) : ((ListenerUtil.mutListener.listen(65424) ? (masterKey == null && masterKey.isLocked()) : (masterKey == null || masterKey.isLocked())) || serviceManager == null))) {
                return;
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(65428)) {
                serviceManager.getMessageService().saveMessageQueue(masterKey);
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(65427)) {
                logger.error("Exception", e);
            }
        }
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        if (!ListenerUtil.mutListener.listen(65429)) {
            logger.info("*** Lifecycle: App now created");
        }
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        if (!ListenerUtil.mutListener.listen(65430)) {
            logger.info("*** Lifecycle: App now resumed");
        }
        if (!ListenerUtil.mutListener.listen(65433)) {
            if ((ListenerUtil.mutListener.listen(65431) ? (serviceManager != null || serviceManager.getLifetimeService() != null) : (serviceManager != null && serviceManager.getLifetimeService() != null))) {
                if (!ListenerUtil.mutListener.listen(65432)) {
                    serviceManager.getLifetimeService().acquireConnection("appResumed");
                }
            }
        }
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        if (!ListenerUtil.mutListener.listen(65434)) {
            logger.info("*** Lifecycle: App now paused");
        }
        if (!ListenerUtil.mutListener.listen(65437)) {
            if ((ListenerUtil.mutListener.listen(65435) ? (serviceManager != null || serviceManager.getLifetimeService() != null) : (serviceManager != null && serviceManager.getLifetimeService() != null))) {
                if (!ListenerUtil.mutListener.listen(65436)) {
                    serviceManager.getLifetimeService().releaseConnectionLinger("appPaused", ACTIVITY_CONNECTION_LIFETIME);
                }
            }
        }
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        if (!ListenerUtil.mutListener.listen(65438)) {
            logger.info("*** Lifecycle: App now destroyed");
        }
        if (!ListenerUtil.mutListener.listen(65441)) {
            if ((ListenerUtil.mutListener.listen(65439) ? (serviceManager != null || serviceManager.getLifetimeService() != null) : (serviceManager != null && serviceManager.getLifetimeService() != null))) {
                if (!ListenerUtil.mutListener.listen(65440)) {
                    serviceManager.getLifetimeService().releaseConnectionLinger("appDestroyed", ACTIVITY_CONNECTION_LIFETIME);
                }
            }
        }
    }

    @Override
    public void onLowMemory() {
        if (!ListenerUtil.mutListener.listen(65442)) {
            super.onLowMemory();
        }
        if (!ListenerUtil.mutListener.listen(65443)) {
            logger.info("*** App is low on memory");
        }
    }

    @SuppressLint("SwitchIntDef")
    @Override
    public void onTrimMemory(int level) {
        if (!ListenerUtil.mutListener.listen(65444)) {
            super.onTrimMemory(level);
        }
        if (!ListenerUtil.mutListener.listen(65461)) {
            switch(level) {
                case TRIM_MEMORY_RUNNING_MODERATE:
                    if (!ListenerUtil.mutListener.listen(65445)) {
                        logger.trace("onTrimMemory (level={})", level);
                    }
                    break;
                case TRIM_MEMORY_UI_HIDDEN:
                    if (!ListenerUtil.mutListener.listen(65446)) {
                        logger.debug("onTrimMemory (level={}, ui hidden)", level);
                    }
                /* fallthrough */
                default:
                    if (!ListenerUtil.mutListener.listen(65448)) {
                        if (level != TRIM_MEMORY_UI_HIDDEN) {
                            if (!ListenerUtil.mutListener.listen(65447)) {
                                // See above
                                logger.debug("onTrimMemory (level={})", level);
                            }
                        }
                    }
                    /* save our master key now if necessary, as we may get killed and if the user was still in the
			     * initial setup procedure, this can lead to trouble as the database may already be there
			     * but we may no longer be able to access it due to missing master key
				 */
                    try {
                        if (!ListenerUtil.mutListener.listen(65454)) {
                            if ((ListenerUtil.mutListener.listen(65450) ? (getMasterKey() != null || !getMasterKey().isProtected()) : (getMasterKey() != null && !getMasterKey().isProtected()))) {
                                if (!ListenerUtil.mutListener.listen(65453)) {
                                    if ((ListenerUtil.mutListener.listen(65451) ? (serviceManager != null || serviceManager.getPreferenceService().getWizardRunning()) : (serviceManager != null && serviceManager.getPreferenceService().getWizardRunning()))) {
                                        if (!ListenerUtil.mutListener.listen(65452)) {
                                            getMasterKey().setPassphrase(null);
                                        }
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(65449)) {
                            logger.error("Exception", e);
                        }
                    }
                    /* take the opportunity to save the message queue */
                    try {
                        if (!ListenerUtil.mutListener.listen(65457)) {
                            if (serviceManager != null)
                                if (!ListenerUtil.mutListener.listen(65456)) {
                                    serviceManager.getMessageService().saveMessageQueueAsync();
                                }
                        }
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(65455)) {
                            logger.error("Exception", e);
                        }
                    }
                    try {
                        if (!ListenerUtil.mutListener.listen(65460)) {
                            if (serviceManager != null) {
                                if (!ListenerUtil.mutListener.listen(65459)) {
                                    serviceManager.getAvatarCacheService().clear();
                                }
                            }
                        }
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(65458)) {
                            logger.error("Exception", e);
                        }
                    }
                    break;
            }
        }
    }

    @Nullable
    public static ServiceManager getServiceManager() {
        return serviceManager;
    }

    public static MasterKey getMasterKey() {
        return masterKey;
    }

    public static void putMessageDraft(String chatId, CharSequence value) {
        if (!ListenerUtil.mutListener.listen(65470)) {
            if ((ListenerUtil.mutListener.listen(65467) ? (value == null && (ListenerUtil.mutListener.listen(65466) ? (value.toString().trim().length() >= 1) : (ListenerUtil.mutListener.listen(65465) ? (value.toString().trim().length() <= 1) : (ListenerUtil.mutListener.listen(65464) ? (value.toString().trim().length() > 1) : (ListenerUtil.mutListener.listen(65463) ? (value.toString().trim().length() != 1) : (ListenerUtil.mutListener.listen(65462) ? (value.toString().trim().length() == 1) : (value.toString().trim().length() < 1))))))) : (value == null || (ListenerUtil.mutListener.listen(65466) ? (value.toString().trim().length() >= 1) : (ListenerUtil.mutListener.listen(65465) ? (value.toString().trim().length() <= 1) : (ListenerUtil.mutListener.listen(65464) ? (value.toString().trim().length() > 1) : (ListenerUtil.mutListener.listen(65463) ? (value.toString().trim().length() != 1) : (ListenerUtil.mutListener.listen(65462) ? (value.toString().trim().length() == 1) : (value.toString().trim().length() < 1))))))))) {
                if (!ListenerUtil.mutListener.listen(65469)) {
                    messageDrafts.remove(chatId);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(65468)) {
                    messageDrafts.put(chatId, value.toString());
                }
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(65472)) {
                getServiceManager().getPreferenceService().setMessageDrafts(messageDrafts);
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(65471)) {
                logger.error("Exception", e);
            }
        }
    }

    public static String getMessageDraft(String chatId) {
        if (!ListenerUtil.mutListener.listen(65473)) {
            if (messageDrafts.containsKey(chatId)) {
                return messageDrafts.get(chatId);
            }
        }
        return null;
    }

    private static void retrieveMessageDraftsFromStorage() {
        try {
            if (!ListenerUtil.mutListener.listen(65475)) {
                messageDrafts = getServiceManager().getPreferenceService().getMessageDrafts();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(65474)) {
                logger.error("Exception", e);
            }
        }
    }

    @SuppressLint("ApplySharedPref")
    private static void resetPreferences() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getAppContext());
        if (!ListenerUtil.mutListener.listen(65480)) {
            // while disabling master key passphrase).
            if ((ListenerUtil.mutListener.listen(65477) ? ((ListenerUtil.mutListener.listen(65476) ? (masterKey.isProtected() || prefs != null) : (masterKey.isProtected() && prefs != null)) || !prefs.getBoolean(getAppContext().getString(R.string.preferences__masterkey_switch), false)) : ((ListenerUtil.mutListener.listen(65476) ? (masterKey.isProtected() || prefs != null) : (masterKey.isProtected() && prefs != null)) && !prefs.getBoolean(getAppContext().getString(R.string.preferences__masterkey_switch), false)))) {
                if (!ListenerUtil.mutListener.listen(65478)) {
                    logger.debug("Master key is protected, but switch preference is disabled - fixing");
                }
                if (!ListenerUtil.mutListener.listen(65479)) {
                    prefs.edit().putBoolean(getAppContext().getString(R.string.preferences__masterkey_switch), true).commit();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(65488)) {
            // update the shared preference.
            if ((ListenerUtil.mutListener.listen(65481) ? (prefs != null || prefs.getString(getAppContext().getString(R.string.preferences__voip_echocancel), "none").equals("none")) : (prefs != null && prefs.getString(getAppContext().getString(R.string.preferences__voip_echocancel), "none").equals("none")))) {
                // Determine whether device is excluded from hardware AEC
                final String modelInfo = Build.MANUFACTURER + ";" + Build.MODEL;
                boolean exclude = !Config.allowHardwareAec();
                // Set default preference
                final SharedPreferences.Editor editor = prefs.edit();
                if (!ListenerUtil.mutListener.listen(65486)) {
                    if (exclude) {
                        if (!ListenerUtil.mutListener.listen(65484)) {
                            logger.debug("Device {} is on AEC exclusion list, switching to software echo cancellation", modelInfo);
                        }
                        if (!ListenerUtil.mutListener.listen(65485)) {
                            editor.putString(getAppContext().getString(R.string.preferences__voip_echocancel), "sw");
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(65482)) {
                            logger.debug("Device {} is not on AEC exclusion list", modelInfo);
                        }
                        if (!ListenerUtil.mutListener.listen(65483)) {
                            editor.putString(getAppContext().getString(R.string.preferences__voip_echocancel), "hw");
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(65487)) {
                    editor.commit();
                }
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(65490)) {
                PreferenceManager.setDefaultValues(getAppContext(), R.xml.preference_chat, true);
            }
            if (!ListenerUtil.mutListener.listen(65491)) {
                PreferenceManager.setDefaultValues(getAppContext(), R.xml.preference_privacy, true);
            }
            if (!ListenerUtil.mutListener.listen(65492)) {
                PreferenceManager.setDefaultValues(getAppContext(), R.xml.preference_appearance, true);
            }
            if (!ListenerUtil.mutListener.listen(65493)) {
                PreferenceManager.setDefaultValues(getAppContext(), R.xml.preference_notifications, true);
            }
            if (!ListenerUtil.mutListener.listen(65494)) {
                PreferenceManager.setDefaultValues(getAppContext(), R.xml.preference_media, true);
            }
            if (!ListenerUtil.mutListener.listen(65495)) {
                PreferenceManager.setDefaultValues(getAppContext(), R.xml.preference_calls, true);
            }
            if (!ListenerUtil.mutListener.listen(65496)) {
                PreferenceManager.setDefaultValues(getAppContext(), R.xml.preference_troubleshooting, true);
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(65489)) {
                logger.error("Exception", e);
            }
        }
    }

    private static void setupLogging(PreferenceStore preferenceStore) {
        // check if a THREEMA_MESSAGE_LOG exist on the
        final File forceMessageLog = new File(Environment.getExternalStorageDirectory() + "/ENABLE_THREEMA_MESSAGE_LOG");
        final File forceDebugLog = new File(Environment.getExternalStorageDirectory() + "/ENABLE_THREEMA_DEBUG_LOG");
        if (!ListenerUtil.mutListener.listen(65502)) {
            // enable message logging if necessary
            if ((ListenerUtil.mutListener.listen(65499) ? ((ListenerUtil.mutListener.listen(65498) ? ((ListenerUtil.mutListener.listen(65497) ? (preferenceStore == null && preferenceStore.getBoolean(getAppContext().getString(R.string.preferences__message_log_switch))) : (preferenceStore == null || preferenceStore.getBoolean(getAppContext().getString(R.string.preferences__message_log_switch)))) && forceMessageLog.exists()) : ((ListenerUtil.mutListener.listen(65497) ? (preferenceStore == null && preferenceStore.getBoolean(getAppContext().getString(R.string.preferences__message_log_switch))) : (preferenceStore == null || preferenceStore.getBoolean(getAppContext().getString(R.string.preferences__message_log_switch)))) || forceMessageLog.exists())) && forceDebugLog.exists()) : ((ListenerUtil.mutListener.listen(65498) ? ((ListenerUtil.mutListener.listen(65497) ? (preferenceStore == null && preferenceStore.getBoolean(getAppContext().getString(R.string.preferences__message_log_switch))) : (preferenceStore == null || preferenceStore.getBoolean(getAppContext().getString(R.string.preferences__message_log_switch)))) && forceMessageLog.exists()) : ((ListenerUtil.mutListener.listen(65497) ? (preferenceStore == null && preferenceStore.getBoolean(getAppContext().getString(R.string.preferences__message_log_switch))) : (preferenceStore == null || preferenceStore.getBoolean(getAppContext().getString(R.string.preferences__message_log_switch)))) || forceMessageLog.exists())) || forceDebugLog.exists()))) {
                if (!ListenerUtil.mutListener.listen(65501)) {
                    DebugLogFileBackend.setEnabled(true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(65500)) {
                    DebugLogFileBackend.setEnabled(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(65505)) {
            // temporary - testing native crash in CompletableFuture while loading emojis
            if (preferenceStore != null) {
                final File forceAndroidEmojis = new File(Environment.getExternalStorageDirectory() + "/FORCE_SYSTEM_EMOJIS");
                if (!ListenerUtil.mutListener.listen(65504)) {
                    if (forceAndroidEmojis.exists()) {
                        if (!ListenerUtil.mutListener.listen(65503)) {
                            preferenceStore.save(getAppContext().getString(R.string.preferences__emoji_style), "1");
                        }
                    }
                }
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static synchronized void reset() {
        if (!ListenerUtil.mutListener.listen(65506)) {
            // set default preferences
            resetPreferences();
        }
        if (!ListenerUtil.mutListener.listen(65507)) {
            // init state bitmap cache singleton
            StateBitmapUtil.init(getAppContext());
        }
        if (!ListenerUtil.mutListener.listen(65508)) {
            // init connection state colors
            ConnectionIndicatorUtil.init(getAppContext());
        }
        try {
            // Load preference store
            PreferenceStore preferenceStore = new PreferenceStore(getAppContext(), masterKey);
            if (!ListenerUtil.mutListener.listen(65512)) {
                ipv6 = preferenceStore.getBoolean(getAppContext().getString(R.string.preferences__ipv6_preferred));
            }
            if (!ListenerUtil.mutListener.listen(65513)) {
                // Set logging to "always on"
                setupLogging(null);
            }
            // Make database key from master key
            String databaseKey = "x\"" + Utils.byteArrayToHexString(masterKey.getKey()) + "\"";
            // Migrate database to v4 format if necessary
            int sqlcipherVersion = 4;
            try {
                if (!ListenerUtil.mutListener.listen(65517)) {
                    DatabaseServiceNew.tryMigrateToV4(getAppContext(), databaseKey);
                }
            } catch (DatabaseMigrationFailedException m) {
                if (!ListenerUtil.mutListener.listen(65514)) {
                    logger.error("Exception", m);
                }
                if (!ListenerUtil.mutListener.listen(65515)) {
                    Toast.makeText(getAppContext(), "Database migration failed. Please free some space on your internal memory.", Toast.LENGTH_LONG).show();
                }
                if (!ListenerUtil.mutListener.listen(65516)) {
                    sqlcipherVersion = 3;
                }
            }
            UpdateSystemService updateSystemService = new UpdateSystemServiceImpl();
            DatabaseServiceNew databaseServiceNew = new DatabaseServiceNew(getAppContext(), databaseKey, updateSystemService, sqlcipherVersion);
            if (!ListenerUtil.mutListener.listen(65518)) {
                databaseServiceNew.executeNull();
            }
            // Migrate nonce database to unencrypted DB
            int nonceSqlcipherVersion = 4;
            if (!ListenerUtil.mutListener.listen(65529)) {
                // do not attempt a nonce DB migration if the main DB is still on version 3
                if ((ListenerUtil.mutListener.listen(65523) ? (sqlcipherVersion >= 4) : (ListenerUtil.mutListener.listen(65522) ? (sqlcipherVersion <= 4) : (ListenerUtil.mutListener.listen(65521) ? (sqlcipherVersion > 4) : (ListenerUtil.mutListener.listen(65520) ? (sqlcipherVersion < 4) : (ListenerUtil.mutListener.listen(65519) ? (sqlcipherVersion != 4) : (sqlcipherVersion == 4))))))) {
                    try {
                        if (!ListenerUtil.mutListener.listen(65528)) {
                            NonceDatabaseBlobService.tryMigrateToV4(getAppContext(), databaseKey);
                        }
                    } catch (DatabaseMigrationFailedException m) {
                        if (!ListenerUtil.mutListener.listen(65525)) {
                            logger.error("Exception", m);
                        }
                        if (!ListenerUtil.mutListener.listen(65526)) {
                            Toast.makeText(getAppContext(), "Nonce database migration failed. Please free some space on your internal memory.", Toast.LENGTH_LONG).show();
                        }
                        if (!ListenerUtil.mutListener.listen(65527)) {
                            nonceSqlcipherVersion = 3;
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(65524)) {
                        nonceSqlcipherVersion = 3;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(65530)) {
                logger.info("*** App launched. Device/Android Version/Flavor: {} Version: {} Build: {}", ConfigUtils.getDeviceInfo(getAppContext(), false), BuildConfig.VERSION_NAME, ConfigUtils.getBuildNumber(getAppContext()));
            }
            if (!ListenerUtil.mutListener.listen(65531)) {
                // Set up logging
                setupLogging(preferenceStore);
            }
            IdentityStore identityStore = new IdentityStore(preferenceStore);
            NonceDatabaseBlobService nonceDatabaseBlobService = new NonceDatabaseBlobService(getAppContext(), masterKey, nonceSqlcipherVersion, identityStore);
            if (!ListenerUtil.mutListener.listen(65532)) {
                logger.info("Nonce count: " + nonceDatabaseBlobService.getCount());
            }
            final ThreemaConnection connection = new ThreemaConnection(identityStore, new NonceFactory(nonceDatabaseBlobService), BuildConfig.CHAT_SERVER_PREFIX, BuildConfig.CHAT_SERVER_IPV6_PREFIX, BuildConfig.CHAT_SERVER_SUFFIX, BuildFlavor.getServerPort(), BuildFlavor.getServerPortAlt(), getIPv6(), BuildConfig.SERVER_PUBKEY, BuildConfig.SERVER_PUBKEY_ALT, BuildConfig.CHAT_SERVER_GROUPS);
            if (!ListenerUtil.mutListener.listen(65533)) {
                connection.setVersion(appVersion);
            }
            if (!ListenerUtil.mutListener.listen(65534)) {
                // push token needs to be updated.
                connection.addConnectionStateListener((newConnectionState, address) -> {
                    if (newConnectionState == ConnectionState.LOGGEDIN) {
                        final Context appContext = getAppContext();
                        if (PushService.servicesInstalled(appContext)) {
                            if (PushUtil.isPushEnabled(appContext)) {
                                if (PushUtil.pushTokenNeedsRefresh(appContext)) {
                                    PushUtil.enqueuePushTokenUpdate(appContext, false, false);
                                } else {
                                    logger.debug("Push token is still fresh. No update needed");
                                }
                            }
                        }
                    }
                });
            }
            if (!ListenerUtil.mutListener.listen(65535)) {
                serviceManager = new ServiceManager(connection, databaseServiceNew, identityStore, preferenceStore, masterKey, updateSystemService);
            }
            if (!ListenerUtil.mutListener.listen(65537)) {
                // get application restrictions
                if (ConfigUtils.isWorkBuild()) {
                    if (!ListenerUtil.mutListener.listen(65536)) {
                        AppRestrictionService.getInstance().reload();
                    }
                }
            }
            final OnFirstConnectRoutine firstConnectRoutine = new OnFirstConnectRoutine(serviceManager.getUserService());
            if (!ListenerUtil.mutListener.listen(65544)) {
                connection.addConnectionStateListener(new ConnectionStateListener() {

                    @Override
                    public void updateConnectionState(ConnectionState connectionState, InetSocketAddress socketAddress) {
                        if (!ListenerUtil.mutListener.listen(65538)) {
                            logger.info("ThreemaConnection state changed: {} (port={}, ipv6={})", connectionState, socketAddress.getPort(), socketAddress.getAddress() instanceof Inet6Address);
                        }
                        if (!ListenerUtil.mutListener.listen(65543)) {
                            if (connectionState == ConnectionState.LOGGEDIN) {
                                if (!ListenerUtil.mutListener.listen(65539)) {
                                    lastLoggedIn = new Date();
                                }
                                if (!ListenerUtil.mutListener.listen(65542)) {
                                    if (firstConnectRoutine.getRunCount() == 0) {
                                        if (!ListenerUtil.mutListener.listen(65540)) {
                                            logger.debug("Run feature mask update");
                                        }
                                        if (!ListenerUtil.mutListener.listen(65541)) {
                                            new Thread(firstConnectRoutine).start();
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
            /* cancel any "new message" notification */
            NotificationManager notificationManager = (NotificationManager) getAppContext().getSystemService(Context.NOTIFICATION_SERVICE);
            if (!ListenerUtil.mutListener.listen(65546)) {
                if (notificationManager != null) {
                    if (!ListenerUtil.mutListener.listen(65545)) {
                        notificationManager.cancel(NEW_MESSAGE_LOCKED_NOTIFICATION_ID);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(65547)) {
                /* trigger a connection now, just to be sure we're up-to-date and any broken connection
			   (e.g. from before a reboot) is preempted.
			 */
                serviceManager.getLifetimeService().acquireConnection("reset");
            }
            if (!ListenerUtil.mutListener.listen(65548)) {
                serviceManager.getLifetimeService().releaseConnectionLinger("reset", ACTIVITY_CONNECTION_LIFETIME);
            }
            if (!ListenerUtil.mutListener.listen(65549)) {
                configureListeners();
            }
            if (!ListenerUtil.mutListener.listen(65550)) {
                databaseServiceNew.getMessageModelFactory().markUnqueuedMessagesAsFailed();
            }
            if (!ListenerUtil.mutListener.listen(65551)) {
                databaseServiceNew.getGroupMessageModelFactory().markUnqueuedMessagesAsFailed();
            }
            if (!ListenerUtil.mutListener.listen(65552)) {
                databaseServiceNew.getDistributionListMessageModelFactory().markUnqueuedMessagesAsFailed();
            }
            if (!ListenerUtil.mutListener.listen(65553)) {
                retrieveMessageDraftsFromStorage();
            }
            if (!ListenerUtil.mutListener.listen(65554)) {
                // process webclient wakeups
                SessionWakeUpServiceImpl.getInstance().processPendingWakeupsAsync();
            }
            if (!ListenerUtil.mutListener.listen(65555)) {
                // start threema safe scheduler
                serviceManager.getThreemaSafeService().scheduleUpload();
            }
            if (!ListenerUtil.mutListener.listen(65556)) {
                new Thread(() -> {
                    // schedule work synchronization
                    scheduleWorkSync(preferenceStore);
                    // schedule identity states / feature masks etc.
                    scheduleIdentityStatesSync(preferenceStore);
                }).start();
            }
            if (!ListenerUtil.mutListener.listen(65557)) {
                initMapbox();
            }
        } catch (MasterKeyLockedException e) {
            if (!ListenerUtil.mutListener.listen(65509)) {
                logger.error("Exception", e);
            }
        } catch (SQLiteException e) {
            if (!ListenerUtil.mutListener.listen(65510)) {
                logger.error("Exception", e);
            }
        } catch (ThreemaException e) {
            if (!ListenerUtil.mutListener.listen(65511)) {
                // no identity
                logger.info("No valid identity.");
            }
        }
    }

    private static void initMapbox() {
        if (!ListenerUtil.mutListener.listen(65565)) {
            if (!ConfigUtils.hasNoMapboxSupport()) {
                if (!ListenerUtil.mutListener.listen(65559)) {
                    // Mapbox Access token
                    Mapbox.getInstance(getAppContext(), String.valueOf(new Random().nextInt()));
                }
                if (!ListenerUtil.mutListener.listen(65560)) {
                    TelemetryEnabler.updateTelemetryState(TelemetryEnabler.State.DISABLED);
                }
                TelemetryDefinition telemetryDefinition = Mapbox.getTelemetry();
                if (!ListenerUtil.mutListener.listen(65563)) {
                    if (telemetryDefinition != null) {
                        if (!ListenerUtil.mutListener.listen(65561)) {
                            telemetryDefinition.setDebugLoggingEnabled(BuildConfig.DEBUG);
                        }
                        if (!ListenerUtil.mutListener.listen(65562)) {
                            telemetryDefinition.setUserTelemetryRequestState(false);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(65564)) {
                    logger.debug("*** Mapbox telemetry: " + TelemetryEnabler.retrieveTelemetryStateFromPreferences());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(65558)) {
                    logger.debug("*** Mapbox disabled due to faulty firmware");
                }
            }
        }
    }

    private static long getSchedulePeriod(PreferenceStore preferenceStore, int key) {
        Integer schedulePeriod = preferenceStore.getInt(getAppContext().getString(key));
        if (!ListenerUtil.mutListener.listen(65574)) {
            if ((ListenerUtil.mutListener.listen(65571) ? (schedulePeriod == null && (ListenerUtil.mutListener.listen(65570) ? (schedulePeriod >= 0) : (ListenerUtil.mutListener.listen(65569) ? (schedulePeriod <= 0) : (ListenerUtil.mutListener.listen(65568) ? (schedulePeriod > 0) : (ListenerUtil.mutListener.listen(65567) ? (schedulePeriod < 0) : (ListenerUtil.mutListener.listen(65566) ? (schedulePeriod != 0) : (schedulePeriod == 0))))))) : (schedulePeriod == null || (ListenerUtil.mutListener.listen(65570) ? (schedulePeriod >= 0) : (ListenerUtil.mutListener.listen(65569) ? (schedulePeriod <= 0) : (ListenerUtil.mutListener.listen(65568) ? (schedulePeriod > 0) : (ListenerUtil.mutListener.listen(65567) ? (schedulePeriod < 0) : (ListenerUtil.mutListener.listen(65566) ? (schedulePeriod != 0) : (schedulePeriod == 0))))))))) {
                if (!ListenerUtil.mutListener.listen(65573)) {
                    schedulePeriod = (int) DateUtils.DAY_IN_MILLIS;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(65572)) {
                    schedulePeriod *= (int) DateUtils.SECOND_IN_MILLIS;
                }
            }
        }
        return (long) schedulePeriod;
    }

    @WorkerThread
    private static boolean scheduleIdentityStatesSync(PreferenceStore preferenceStore) {
        long schedulePeriod = getSchedulePeriod(preferenceStore, R.string.preferences__identity_states_check_interval);
        if (!ListenerUtil.mutListener.listen(65575)) {
            logger.info("Initializing Identity States sync. Requested schedule period: {} ms", schedulePeriod);
        }
        try {
            WorkManager workManager = WorkManager.getInstance(context);
            // check if work is already scheduled or running, if yes, do not attempt launch a new request
            ListenableFuture<List<WorkInfo>> workInfos = workManager.getWorkInfosForUniqueWork(WORKER_IDENTITY_STATES_PERIODIC_NAME);
            try {
                List<WorkInfo> workInfoList = workInfos.get();
                if (!ListenerUtil.mutListener.listen(65592)) {
                    {
                        long _loopCounter797 = 0;
                        for (WorkInfo workInfo : workInfoList) {
                            ListenerUtil.loopListener.listen("_loopCounter797", ++_loopCounter797);
                            WorkInfo.State state = workInfo.getState();
                            if (!ListenerUtil.mutListener.listen(65591)) {
                                if ((ListenerUtil.mutListener.listen(65580) ? (state == WorkInfo.State.RUNNING && state == WorkInfo.State.ENQUEUED) : (state == WorkInfo.State.RUNNING || state == WorkInfo.State.ENQUEUED))) {
                                    if (!ListenerUtil.mutListener.listen(65581)) {
                                        logger.debug("a job of the same name is already running or queued");
                                    }
                                    Set<String> tags = workInfo.getTags();
                                    if (!ListenerUtil.mutListener.listen(65590)) {
                                        if ((ListenerUtil.mutListener.listen(65587) ? ((ListenerUtil.mutListener.listen(65586) ? (tags.size() >= 0) : (ListenerUtil.mutListener.listen(65585) ? (tags.size() <= 0) : (ListenerUtil.mutListener.listen(65584) ? (tags.size() < 0) : (ListenerUtil.mutListener.listen(65583) ? (tags.size() != 0) : (ListenerUtil.mutListener.listen(65582) ? (tags.size() == 0) : (tags.size() > 0)))))) || tags.contains(String.valueOf(schedulePeriod))) : ((ListenerUtil.mutListener.listen(65586) ? (tags.size() >= 0) : (ListenerUtil.mutListener.listen(65585) ? (tags.size() <= 0) : (ListenerUtil.mutListener.listen(65584) ? (tags.size() < 0) : (ListenerUtil.mutListener.listen(65583) ? (tags.size() != 0) : (ListenerUtil.mutListener.listen(65582) ? (tags.size() == 0) : (tags.size() > 0)))))) && tags.contains(String.valueOf(schedulePeriod))))) {
                                            if (!ListenerUtil.mutListener.listen(65589)) {
                                                logger.debug("job has same schedule period");
                                            }
                                            return false;
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(65588)) {
                                                logger.debug("jobs has a different schedule period");
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                if (!ListenerUtil.mutListener.listen(65578)) {
                    logger.info("WorkManager Exception");
                }
                if (!ListenerUtil.mutListener.listen(65579)) {
                    workManager.cancelUniqueWork(WORKER_IDENTITY_STATES_PERIODIC_NAME);
                }
            }
            if (!ListenerUtil.mutListener.listen(65593)) {
                logger.debug("Scheduling new job");
            }
            // schedule the start of the service according to schedule period
            Constraints constraints = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
            PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(IdentityStatesWorker.class, schedulePeriod, TimeUnit.MILLISECONDS).setConstraints(constraints).addTag(String.valueOf(schedulePeriod)).setInitialDelay(1000, TimeUnit.MILLISECONDS).build();
            if (!ListenerUtil.mutListener.listen(65594)) {
                workManager.enqueueUniquePeriodicWork(WORKER_IDENTITY_STATES_PERIODIC_NAME, ExistingPeriodicWorkPolicy.REPLACE, workRequest);
            }
            return true;
        } catch (IllegalStateException e) {
            if (!ListenerUtil.mutListener.listen(65576)) {
                logger.info("Unable to initialize WorkManager");
            }
            if (!ListenerUtil.mutListener.listen(65577)) {
                logger.error("Exception", e);
            }
        }
        return false;
    }

    private static boolean scheduleWorkSync(PreferenceStore preferenceStore) {
        if (!ListenerUtil.mutListener.listen(65595)) {
            if (!ConfigUtils.isWorkBuild()) {
                return false;
            }
        }
        long schedulePeriod = getSchedulePeriod(preferenceStore, R.string.preferences__work_sync_check_interval);
        if (!ListenerUtil.mutListener.listen(65596)) {
            logger.info("Scheduling Work Sync. Schedule period: {}", schedulePeriod);
        }
        if (!ListenerUtil.mutListener.listen(65606)) {
            // schedule the start of the service according to schedule period
            if ((ListenerUtil.mutListener.listen(65601) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(65600) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(65599) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(65598) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(65597) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
                if (!ListenerUtil.mutListener.listen(65605)) {
                    if (jobScheduler != null) {
                        ComponentName serviceComponent = new ComponentName(context, WorkSyncJobService.class);
                        JobInfo.Builder builder = new JobInfo.Builder(WORK_SYNC_JOB_ID, serviceComponent).setPeriodic(schedulePeriod).setRequiredNetworkType(android.app.job.JobInfo.NETWORK_TYPE_ANY);
                        if (!ListenerUtil.mutListener.listen(65604)) {
                            jobScheduler.schedule(builder.build());
                        }
                        return true;
                    }
                }
            } else {
                AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                if (!ListenerUtil.mutListener.listen(65603)) {
                    if (alarmMgr != null) {
                        Intent intent = new Intent(context, WorkSyncService.class);
                        PendingIntent pendingIntent = PendingIntent.getService(context, WORK_SYNC_JOB_ID, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                        if (!ListenerUtil.mutListener.listen(65602)) {
                            alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), schedulePeriod, pendingIntent);
                        }
                        return true;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(65607)) {
            logger.debug("unable to schedule work sync");
        }
        return false;
    }

    private static void configureListeners() {
        if (!ListenerUtil.mutListener.listen(65660)) {
            ListenerManager.groupListeners.add(new GroupListener() {

                @Override
                public void onCreate(GroupModel newGroupModel) {
                    try {
                        if (!ListenerUtil.mutListener.listen(65609)) {
                            serviceManager.getConversationService().refresh(newGroupModel);
                        }
                        if (!ListenerUtil.mutListener.listen(65610)) {
                            serviceManager.getMessageService().createStatusMessage(serviceManager.getContext().getString(R.string.status_create_group), serviceManager.getGroupService().createReceiver(newGroupModel));
                        }
                    } catch (ThreemaException e) {
                        if (!ListenerUtil.mutListener.listen(65608)) {
                            logger.error("Exception", e);
                        }
                    }
                }

                @Override
                public void onRename(GroupModel groupModel) {
                    try {
                        if (!ListenerUtil.mutListener.listen(65612)) {
                            serviceManager.getConversationService().refresh(groupModel);
                        }
                        if (!ListenerUtil.mutListener.listen(65613)) {
                            serviceManager.getMessageService().createStatusMessage(serviceManager.getContext().getString(R.string.status_rename_group, groupModel.getName()), serviceManager.getGroupService().createReceiver(groupModel));
                        }
                        if (!ListenerUtil.mutListener.listen(65614)) {
                            serviceManager.getShortcutService().updateShortcut(groupModel);
                        }
                    } catch (ThreemaException e) {
                        if (!ListenerUtil.mutListener.listen(65611)) {
                            logger.error("Exception", e);
                        }
                    }
                }

                @Override
                public void onUpdatePhoto(GroupModel groupModel) {
                    try {
                        if (!ListenerUtil.mutListener.listen(65616)) {
                            serviceManager.getConversationService().refresh(groupModel);
                        }
                        if (!ListenerUtil.mutListener.listen(65617)) {
                            serviceManager.getMessageService().createStatusMessage(serviceManager.getContext().getString(R.string.status_group_new_photo), serviceManager.getGroupService().createReceiver(groupModel));
                        }
                        if (!ListenerUtil.mutListener.listen(65618)) {
                            serviceManager.getShortcutService().updateShortcut(groupModel);
                        }
                    } catch (ThreemaException e) {
                        if (!ListenerUtil.mutListener.listen(65615)) {
                            logger.error("Exception", e);
                        }
                    }
                }

                @Override
                public void onRemove(GroupModel groupModel) {
                    try {
                        if (!ListenerUtil.mutListener.listen(65620)) {
                            serviceManager.getConversationService().removed(groupModel);
                        }
                        if (!ListenerUtil.mutListener.listen(65621)) {
                            serviceManager.getNotificationService().cancel(new GroupMessageReceiver(groupModel, null, null, null, null));
                        }
                    } catch (ThreemaException e) {
                        if (!ListenerUtil.mutListener.listen(65619)) {
                            logger.error("Exception", e);
                        }
                    }
                }

                @Override
                public void onNewMember(GroupModel group, String newIdentity, int previousMemberCount) {
                    String memberName = newIdentity;
                    ContactModel contactModel;
                    try {
                        if ((contactModel = serviceManager.getContactService().getByIdentity(newIdentity)) != null) {
                            if (!ListenerUtil.mutListener.listen(65623)) {
                                memberName = NameUtil.getDisplayNameOrNickname(contactModel, true);
                            }
                        }
                    } catch (MasterKeyLockedException | FileSystemNotPresentException e) {
                        if (!ListenerUtil.mutListener.listen(65622)) {
                            logger.error("Exception", e);
                        }
                    }
                    try {
                        final MessageReceiver receiver = serviceManager.getGroupService().createReceiver(group);
                        final String myIdentity = serviceManager.getUserService().getIdentity();
                        if (!ListenerUtil.mutListener.listen(65637)) {
                            if ((ListenerUtil.mutListener.listen(65625) ? (receiver != null || !TestUtil.empty(myIdentity)) : (receiver != null && !TestUtil.empty(myIdentity)))) {
                                if (!ListenerUtil.mutListener.listen(65626)) {
                                    serviceManager.getMessageService().createStatusMessage(serviceManager.getContext().getString(R.string.status_group_new_member, memberName), receiver);
                                }
                                if (!ListenerUtil.mutListener.listen(65636)) {
                                    if ((ListenerUtil.mutListener.listen(65632) ? ((!myIdentity.equals(group.getCreatorIdentity())) && (ListenerUtil.mutListener.listen(65631) ? (previousMemberCount >= 1) : (ListenerUtil.mutListener.listen(65630) ? (previousMemberCount <= 1) : (ListenerUtil.mutListener.listen(65629) ? (previousMemberCount < 1) : (ListenerUtil.mutListener.listen(65628) ? (previousMemberCount != 1) : (ListenerUtil.mutListener.listen(65627) ? (previousMemberCount == 1) : (previousMemberCount > 1))))))) : ((!myIdentity.equals(group.getCreatorIdentity())) || (ListenerUtil.mutListener.listen(65631) ? (previousMemberCount >= 1) : (ListenerUtil.mutListener.listen(65630) ? (previousMemberCount <= 1) : (ListenerUtil.mutListener.listen(65629) ? (previousMemberCount < 1) : (ListenerUtil.mutListener.listen(65628) ? (previousMemberCount != 1) : (ListenerUtil.mutListener.listen(65627) ? (previousMemberCount == 1) : (previousMemberCount > 1))))))))) {
                                        // send all open ballots to the new group member
                                        BallotService ballotService = serviceManager.getBallotService();
                                        if (!ListenerUtil.mutListener.listen(65635)) {
                                            if (ballotService != null) {
                                                List<BallotModel> openBallots = ballotService.getBallots(new BallotService.BallotFilter() {

                                                    @Override
                                                    public MessageReceiver getReceiver() {
                                                        return receiver;
                                                    }

                                                    @Override
                                                    public BallotModel.State[] getStates() {
                                                        return new BallotModel.State[] { BallotModel.State.OPEN };
                                                    }

                                                    @Override
                                                    public boolean filter(BallotModel ballotModel) {
                                                        // only my ballots please
                                                        return ballotModel.getCreatorIdentity().equals(myIdentity);
                                                    }
                                                });
                                                if (!ListenerUtil.mutListener.listen(65634)) {
                                                    {
                                                        long _loopCounter798 = 0;
                                                        for (BallotModel ballotModel : openBallots) {
                                                            ListenerUtil.loopListener.listen("_loopCounter798", ++_loopCounter798);
                                                            if (!ListenerUtil.mutListener.listen(65633)) {
                                                                ballotService.publish(receiver, ballotModel, null, newIdentity);
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
                    } catch (ThreemaException x) {
                        if (!ListenerUtil.mutListener.listen(65624)) {
                            logger.error("Exception", x);
                        }
                    }
                    // reset avatar to recreate it!
                    try {
                        if (!ListenerUtil.mutListener.listen(65639)) {
                            serviceManager.getAvatarCacheService().reset(group);
                        }
                    } catch (FileSystemNotPresentException e) {
                        if (!ListenerUtil.mutListener.listen(65638)) {
                            logger.error("Exception", e);
                        }
                    }
                }

                @Override
                public void onMemberLeave(GroupModel group, String identity, int previousMemberCount) {
                    String memberName = identity;
                    ContactModel contactModel;
                    try {
                        if ((contactModel = serviceManager.getContactService().getByIdentity(identity)) != null) {
                            if (!ListenerUtil.mutListener.listen(65641)) {
                                memberName = NameUtil.getDisplayNameOrNickname(contactModel, true);
                            }
                        }
                    } catch (MasterKeyLockedException | FileSystemNotPresentException e) {
                        if (!ListenerUtil.mutListener.listen(65640)) {
                            logger.error("Exception", e);
                        }
                    }
                    try {
                        final MessageReceiver receiver = serviceManager.getGroupService().createReceiver(group);
                        if (!ListenerUtil.mutListener.listen(65643)) {
                            serviceManager.getMessageService().createStatusMessage(serviceManager.getContext().getString(R.string.status_group_member_left, memberName), receiver);
                        }
                        BallotService ballotService = serviceManager.getBallotService();
                        if (!ListenerUtil.mutListener.listen(65644)) {
                            ballotService.removeVotes(receiver, identity);
                        }
                    } catch (ThreemaException e) {
                        if (!ListenerUtil.mutListener.listen(65642)) {
                            logger.error("Exception", e);
                        }
                    }
                }

                @Override
                public void onMemberKicked(GroupModel group, String identity, int previousMemberCount) {
                    final String myIdentity = serviceManager.getUserService().getIdentity();
                    if (!ListenerUtil.mutListener.listen(65648)) {
                        if ((ListenerUtil.mutListener.listen(65645) ? (myIdentity != null || myIdentity.equals(identity)) : (myIdentity != null && myIdentity.equals(identity)))) {
                            // my own member status has changed
                            try {
                                if (!ListenerUtil.mutListener.listen(65647)) {
                                    serviceManager.getConversationService().refresh(group);
                                }
                            } catch (ThreemaException e) {
                                if (!ListenerUtil.mutListener.listen(65646)) {
                                    logger.error("Exception", e);
                                }
                            }
                        }
                    }
                    String memberName = identity;
                    ContactModel contactModel;
                    try {
                        if ((contactModel = serviceManager.getContactService().getByIdentity(identity)) != null) {
                            if (!ListenerUtil.mutListener.listen(65650)) {
                                memberName = NameUtil.getDisplayNameOrNickname(contactModel, true);
                            }
                        }
                    } catch (MasterKeyLockedException | FileSystemNotPresentException e) {
                        if (!ListenerUtil.mutListener.listen(65649)) {
                            logger.error("Exception", e);
                        }
                    }
                    try {
                        final MessageReceiver receiver = serviceManager.getGroupService().createReceiver(group);
                        if (!ListenerUtil.mutListener.listen(65652)) {
                            serviceManager.getMessageService().createStatusMessage(serviceManager.getContext().getString(R.string.status_group_member_kicked, memberName), receiver);
                        }
                        BallotService ballotService = serviceManager.getBallotService();
                        if (!ListenerUtil.mutListener.listen(65653)) {
                            ballotService.removeVotes(receiver, identity);
                        }
                    } catch (ThreemaException e) {
                        if (!ListenerUtil.mutListener.listen(65651)) {
                            logger.error("Exception", e);
                        }
                    }
                }

                @Override
                public void onUpdate(GroupModel groupModel) {
                    try {
                        if (!ListenerUtil.mutListener.listen(65655)) {
                            serviceManager.getConversationService().refresh(groupModel);
                        }
                    } catch (ThreemaException e) {
                        if (!ListenerUtil.mutListener.listen(65654)) {
                            logger.error("Exception", e);
                        }
                    }
                }

                @Override
                public void onLeave(GroupModel groupModel) {
                    try {
                        if (!ListenerUtil.mutListener.listen(65657)) {
                            serviceManager.getConversationService().refresh(groupModel);
                        }
                    } catch (ThreemaException e) {
                        if (!ListenerUtil.mutListener.listen(65656)) {
                            logger.error("Exception", e);
                        }
                    }
                }

                @Override
                public void onGroupStateChanged(GroupModel groupModel, @GroupService.GroupState int oldState, @GroupService.GroupState int newState) {
                    if (!ListenerUtil.mutListener.listen(65658)) {
                        logger.debug("&&& onGroupStateChanged: {} -> {}", oldState, newState);
                    }
                    if (!ListenerUtil.mutListener.listen(65659)) {
                        showNotesGroupNotice(groupModel, oldState, newState);
                    }
                }
            }, THREEMA_APPLICATION_LISTENER_TAG);
        }
        if (!ListenerUtil.mutListener.listen(65668)) {
            ListenerManager.distributionListListeners.add(new DistributionListListener() {

                @Override
                public void onCreate(DistributionListModel distributionListModel) {
                    try {
                        if (!ListenerUtil.mutListener.listen(65662)) {
                            serviceManager.getConversationService().refresh(distributionListModel);
                        }
                    } catch (ThreemaException e) {
                        if (!ListenerUtil.mutListener.listen(65661)) {
                            logger.error("Exception", e);
                        }
                    }
                }

                @Override
                public void onModify(DistributionListModel distributionListModel) {
                    try {
                        if (!ListenerUtil.mutListener.listen(65664)) {
                            serviceManager.getConversationService().refresh(distributionListModel);
                        }
                        if (!ListenerUtil.mutListener.listen(65665)) {
                            serviceManager.getShortcutService().updateShortcut(distributionListModel);
                        }
                    } catch (ThreemaException e) {
                        if (!ListenerUtil.mutListener.listen(65663)) {
                            logger.error("Exception", e);
                        }
                    }
                }

                @Override
                public void onRemove(DistributionListModel distributionListModel) {
                    try {
                        if (!ListenerUtil.mutListener.listen(65667)) {
                            serviceManager.getConversationService().removed(distributionListModel);
                        }
                    } catch (ThreemaException e) {
                        if (!ListenerUtil.mutListener.listen(65666)) {
                            logger.error("Exception", e);
                        }
                    }
                }
            }, THREEMA_APPLICATION_LISTENER_TAG);
        }
        if (!ListenerUtil.mutListener.listen(65692)) {
            ListenerManager.messageListeners.add(new ch.threema.app.listeners.MessageListener() {

                @Override
                public void onNew(AbstractMessageModel newMessage) {
                    if (!ListenerUtil.mutListener.listen(65669)) {
                        logger.debug("MessageListener.onNewMessage");
                    }
                    if (!ListenerUtil.mutListener.listen(65671)) {
                        if (!newMessage.isStatusMessage()) {
                            if (!ListenerUtil.mutListener.listen(65670)) {
                                showConversationNotification(newMessage, false);
                            }
                        }
                    }
                }

                @Override
                public void onModified(List<AbstractMessageModel> modifiedMessageModels) {
                    if (!ListenerUtil.mutListener.listen(65672)) {
                        logger.debug("MessageListener.onModified");
                    }
                    if (!ListenerUtil.mutListener.listen(65679)) {
                        {
                            long _loopCounter799 = 0;
                            for (final AbstractMessageModel modifiedMessageModel : modifiedMessageModels) {
                                ListenerUtil.loopListener.listen("_loopCounter799", ++_loopCounter799);
                                if (!ListenerUtil.mutListener.listen(65675)) {
                                    if (!modifiedMessageModel.isStatusMessage()) {
                                        try {
                                            if (!ListenerUtil.mutListener.listen(65674)) {
                                                serviceManager.getConversationService().refresh(modifiedMessageModel);
                                            }
                                        } catch (ThreemaException e) {
                                            if (!ListenerUtil.mutListener.listen(65673)) {
                                                logger.error("Exception", e);
                                            }
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(65678)) {
                                    if ((ListenerUtil.mutListener.listen(65676) ? (!modifiedMessageModel.isStatusMessage() || modifiedMessageModel.getType() == MessageType.IMAGE) : (!modifiedMessageModel.isStatusMessage() && modifiedMessageModel.getType() == MessageType.IMAGE))) {
                                        if (!ListenerUtil.mutListener.listen(65677)) {
                                            // update notification with image preview
                                            showConversationNotification(modifiedMessageModel, true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                @Override
                public void onRemoved(AbstractMessageModel removedMessageModel) {
                    if (!ListenerUtil.mutListener.listen(65680)) {
                        logger.debug("MessageListener.onRemoved");
                    }
                    if (!ListenerUtil.mutListener.listen(65683)) {
                        if (!removedMessageModel.isStatusMessage()) {
                            try {
                                if (!ListenerUtil.mutListener.listen(65682)) {
                                    serviceManager.getConversationService().refreshWithDeletedMessage(removedMessageModel);
                                }
                            } catch (ThreemaException e) {
                                if (!ListenerUtil.mutListener.listen(65681)) {
                                    logger.error("Exception", e);
                                }
                            }
                        }
                    }
                }

                @Override
                public void onProgressChanged(AbstractMessageModel messageModel, int newProgress) {
                }

                private void showConversationNotification(AbstractMessageModel newMessage, boolean updateExisting) {
                    try {
                        ConversationService conversationService = serviceManager.getConversationService();
                        ConversationModel conversationModel = conversationService.refresh(newMessage);
                        if (!ListenerUtil.mutListener.listen(65691)) {
                            if ((ListenerUtil.mutListener.listen(65687) ? ((ListenerUtil.mutListener.listen(65686) ? ((ListenerUtil.mutListener.listen(65685) ? (conversationModel != null || !newMessage.isOutbox()) : (conversationModel != null && !newMessage.isOutbox())) || !newMessage.isStatusMessage()) : ((ListenerUtil.mutListener.listen(65685) ? (conversationModel != null || !newMessage.isOutbox()) : (conversationModel != null && !newMessage.isOutbox())) && !newMessage.isStatusMessage())) || !newMessage.isRead()) : ((ListenerUtil.mutListener.listen(65686) ? ((ListenerUtil.mutListener.listen(65685) ? (conversationModel != null || !newMessage.isOutbox()) : (conversationModel != null && !newMessage.isOutbox())) || !newMessage.isStatusMessage()) : ((ListenerUtil.mutListener.listen(65685) ? (conversationModel != null || !newMessage.isOutbox()) : (conversationModel != null && !newMessage.isOutbox())) && !newMessage.isStatusMessage())) && !newMessage.isRead()))) {
                                NotificationService notificationService = serviceManager.getNotificationService();
                                ContactService contactService = serviceManager.getContactService();
                                GroupService groupService = serviceManager.getGroupService();
                                DeadlineListService hiddenChatsListService = serviceManager.getHiddenChatsListService();
                                if (!ListenerUtil.mutListener.listen(65690)) {
                                    if (TestUtil.required(notificationService, contactService, groupService)) {
                                        if (!ListenerUtil.mutListener.listen(65688)) {
                                            notificationService.addConversationNotification(ConversationNotificationUtil.convert(getAppContext(), newMessage, contactService, groupService, hiddenChatsListService), updateExisting);
                                        }
                                        if (!ListenerUtil.mutListener.listen(65689)) {
                                            // update widget on incoming message
                                            WidgetUtil.updateWidgets(serviceManager.getContext());
                                        }
                                    }
                                }
                            }
                        }
                    } catch (ThreemaException e) {
                        if (!ListenerUtil.mutListener.listen(65684)) {
                            logger.error("Exception", e);
                        }
                    }
                }
            }, THREEMA_APPLICATION_LISTENER_TAG);
        }
        if (!ListenerUtil.mutListener.listen(65697)) {
            ListenerManager.serverMessageListeners.add(new ServerMessageListener() {

                @Override
                public void onAlert(ServerMessageModel serverMessage) {
                    NotificationService n = serviceManager.getNotificationService();
                    if (!ListenerUtil.mutListener.listen(65694)) {
                        if (n != null) {
                            if (!ListenerUtil.mutListener.listen(65693)) {
                                n.showServerMessage(serverMessage);
                            }
                        }
                    }
                }

                @Override
                public void onError(ServerMessageModel serverMessage) {
                    NotificationService n = serviceManager.getNotificationService();
                    if (!ListenerUtil.mutListener.listen(65696)) {
                        if (n != null) {
                            if (!ListenerUtil.mutListener.listen(65695)) {
                                n.showServerMessage(serverMessage);
                            }
                        }
                    }
                }
            }, THREEMA_APPLICATION_LISTENER_TAG);
        }
        if (!ListenerUtil.mutListener.listen(65711)) {
            ListenerManager.contactListeners.add(new ContactListener() {

                @Override
                public void onModified(ContactModel modifiedContactModel) {
                    // validate contact integration
                    try {
                        if (!ListenerUtil.mutListener.listen(65699)) {
                            serviceManager.getConversationService().refresh(modifiedContactModel);
                        }
                        if (!ListenerUtil.mutListener.listen(65700)) {
                            serviceManager.getShortcutService().updateShortcut(modifiedContactModel);
                        }
                    } catch (ThreemaException e) {
                        if (!ListenerUtil.mutListener.listen(65698)) {
                            logger.error("Exception", e);
                        }
                    }
                }

                @Override
                public void onNew(ContactModel createdContactModel) {
                    // validate contact integration
                    try {
                        ContactService contactService = serviceManager.getContactService();
                        if (!ListenerUtil.mutListener.listen(65704)) {
                            if (contactService != null) {
                                SynchronizeContactsService synchronizeContactService = serviceManager.getSynchronizeContactsService();
                                boolean inSyncProcess = (ListenerUtil.mutListener.listen(65702) ? (synchronizeContactService != null || synchronizeContactService.isSynchronizationInProgress()) : (synchronizeContactService != null && synchronizeContactService.isSynchronizationInProgress()));
                                if (!ListenerUtil.mutListener.listen(65703)) {
                                    if (!inSyncProcess) {
                                    }
                                }
                            }
                        }
                    } catch (MasterKeyLockedException | FileSystemNotPresentException e) {
                        if (!ListenerUtil.mutListener.listen(65701)) {
                            logger.error("Exception", e);
                        }
                    }
                }

                @Override
                public void onRemoved(ContactModel removedContactModel) {
                    try {
                        if (!ListenerUtil.mutListener.listen(65706)) {
                            serviceManager.getConversationService().removed(removedContactModel);
                        }
                        if (!ListenerUtil.mutListener.listen(65707)) {
                            // hack. create a receiver to become the notification id
                            serviceManager.getNotificationService().cancel(new ContactMessageReceiver(removedContactModel, serviceManager.getContactService(), null, null, null, null));
                        }
                        // remove custom avatar (ANDR-353)
                        FileService f = serviceManager.getFileService();
                        if (!ListenerUtil.mutListener.listen(65710)) {
                            if (f != null) {
                                if (!ListenerUtil.mutListener.listen(65708)) {
                                    f.removeContactAvatar(removedContactModel);
                                }
                                if (!ListenerUtil.mutListener.listen(65709)) {
                                    f.removeContactPhoto(removedContactModel);
                                }
                            }
                        }
                    } catch (ThreemaException e) {
                        if (!ListenerUtil.mutListener.listen(65705)) {
                            logger.error("Exception", e);
                        }
                    }
                }
            }, THREEMA_APPLICATION_LISTENER_TAG);
        }
        if (!ListenerUtil.mutListener.listen(65717)) {
            ListenerManager.contactSettingsListeners.add(new ContactSettingsListener() {

                @Override
                public void onSortingChanged() {
                }

                @Override
                public void onNameFormatChanged() {
                }

                @Override
                public void onAvatarSettingChanged() {
                    if (!ListenerUtil.mutListener.listen(65716)) {
                        // reset the avatar cache!
                        if (serviceManager != null) {
                            try {
                                AvatarCacheService s = null;
                                if (!ListenerUtil.mutListener.listen(65713)) {
                                    s = serviceManager.getAvatarCacheService();
                                }
                                if (!ListenerUtil.mutListener.listen(65715)) {
                                    if (s != null) {
                                        if (!ListenerUtil.mutListener.listen(65714)) {
                                            s.clear();
                                        }
                                    }
                                }
                            } catch (FileSystemNotPresentException e) {
                                if (!ListenerUtil.mutListener.listen(65712)) {
                                    logger.error("Exception", e);
                                }
                            }
                        }
                    }
                }

                @Override
                public void onInactiveContactsSettingChanged() {
                }

                @Override
                public void onNotificationSettingChanged(String uid) {
                }
            }, THREEMA_APPLICATION_LISTENER_TAG);
        }
        if (!ListenerUtil.mutListener.listen(65720)) {
            ListenerManager.conversationListeners.add(new ConversationListener() {

                @Override
                public void onNew(ConversationModel conversationModel) {
                }

                @Override
                public void onModified(ConversationModel modifiedConversationModel, Integer oldPosition) {
                }

                @Override
                public void onRemoved(ConversationModel conversationModel) {
                    // remove notification!
                    NotificationService notificationService = serviceManager.getNotificationService();
                    if (!ListenerUtil.mutListener.listen(65719)) {
                        if (notificationService != null) {
                            if (!ListenerUtil.mutListener.listen(65718)) {
                                notificationService.cancel(conversationModel);
                            }
                        }
                    }
                }

                @Override
                public void onModifiedAll() {
                }
            }, THREEMA_APPLICATION_LISTENER_TAG);
        }
        if (!ListenerUtil.mutListener.listen(65740)) {
            ListenerManager.ballotVoteListeners.add(new BallotVoteListener() {

                @Override
                public void onSelfVote(BallotModel ballotModel) {
                }

                @Override
                public void onVoteChanged(BallotModel ballotModel, String votingIdentity, boolean isFirstVote) {
                    // DISABLED
                    ServiceManager s = ThreemaApplication.getServiceManager();
                    if (!ListenerUtil.mutListener.listen(65739)) {
                        if (s != null) {
                            try {
                                BallotService ballotService = s.getBallotService();
                                ContactService contactService = s.getContactService();
                                GroupService groupService = s.getGroupService();
                                MessageService messageService = s.getMessageService();
                                UserService userService = s.getUserService();
                                if (!ListenerUtil.mutListener.listen(65738)) {
                                    if (TestUtil.required(ballotModel, contactService, groupService, messageService, userService)) /*&& BallotUtil.isMine(ballotModel, userService)*/
                                    {
                                        LinkBallotModel b = ballotService.getLinkedBallotModel(ballotModel);
                                        if (!ListenerUtil.mutListener.listen(65737)) {
                                            if (b != null) {
                                                String message = null;
                                                MessageReceiver receiver = null;
                                                if (!ListenerUtil.mutListener.listen(65726)) {
                                                    if (b instanceof GroupBallotModel) {
                                                        GroupModel groupModel = groupService.getById(((GroupBallotModel) b).getGroupId());
                                                        if (!ListenerUtil.mutListener.listen(65724)) {
                                                            // its a group ballot,write status
                                                            receiver = groupService.createReceiver(groupModel);
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(65725)) {
                                                            // reset archived status
                                                            groupService.setIsArchived(groupModel, false);
                                                        }
                                                    } else if (b instanceof IdentityBallotModel) {
                                                        String identity = ((IdentityBallotModel) b).getIdentity();
                                                        if (!ListenerUtil.mutListener.listen(65722)) {
                                                            // not implemented
                                                            receiver = contactService.createReceiver(contactService.getByIdentity(identity));
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(65723)) {
                                                            // reset archived status
                                                            contactService.setIsArchived(identity, false);
                                                        }
                                                    }
                                                }
                                                if (!ListenerUtil.mutListener.listen(65732)) {
                                                    if (ballotModel.getType() == BallotModel.Type.RESULT_ON_CLOSE) {
                                                        if (!ListenerUtil.mutListener.listen(65731)) {
                                                            // on private voting, only show default update msg!
                                                            message = serviceManager.getContext().getString(R.string.status_ballot_voting_changed, ballotModel.getName());
                                                        }
                                                    } else {
                                                        if (!ListenerUtil.mutListener.listen(65730)) {
                                                            if (receiver != null) {
                                                                ContactModel votingContactModel = contactService.getByIdentity(votingIdentity);
                                                                if (!ListenerUtil.mutListener.listen(65729)) {
                                                                    if (isFirstVote) {
                                                                        if (!ListenerUtil.mutListener.listen(65728)) {
                                                                            message = serviceManager.getContext().getString(R.string.status_ballot_user_first_vote, NameUtil.getDisplayName(votingContactModel), ballotModel.getName());
                                                                        }
                                                                    } else {
                                                                        if (!ListenerUtil.mutListener.listen(65727)) {
                                                                            message = serviceManager.getContext().getString(R.string.status_ballot_user_modified_vote, NameUtil.getDisplayName(votingContactModel), ballotModel.getName());
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                                if (!ListenerUtil.mutListener.listen(65734)) {
                                                    if (TestUtil.required(message, receiver)) {
                                                        if (!ListenerUtil.mutListener.listen(65733)) {
                                                            messageService.createStatusMessage(message, receiver);
                                                        }
                                                    }
                                                }
                                                if (!ListenerUtil.mutListener.listen(65736)) {
                                                    // now check if every participant has voted
                                                    if (ballotService.getPendingParticipants(ballotModel.getId()).size() == 0) {
                                                        String ballotAllVotesMessage = serviceManager.getContext().getString(R.string.status_ballot_all_votes, ballotModel.getName());
                                                        if (!ListenerUtil.mutListener.listen(65735)) {
                                                            messageService.createStatusMessage(ballotAllVotesMessage, receiver);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            } catch (ThreemaException x) {
                                if (!ListenerUtil.mutListener.listen(65721)) {
                                    logger.error("Exception", x);
                                }
                            }
                        }
                    }
                }

                @Override
                public void onVoteRemoved(BallotModel ballotModel, String votingIdentity) {
                }

                @Override
                public boolean handle(BallotModel ballotModel) {
                    // handle all
                    return true;
                }
            }, THREEMA_APPLICATION_LISTENER_TAG);
        }
        final ContentObserver contentObserverChangeContactNames = new ContentObserver(null) {

            private boolean isRunning = false;

            @Override
            public boolean deliverSelfNotifications() {
                return super.deliverSelfNotifications();
            }

            @Override
            public void onChange(boolean selfChange) {
                if (!ListenerUtil.mutListener.listen(65741)) {
                    super.onChange(selfChange);
                }
                if (!ListenerUtil.mutListener.listen(65754)) {
                    if ((ListenerUtil.mutListener.listen(65743) ? ((ListenerUtil.mutListener.listen(65742) ? (!selfChange || serviceManager != null) : (!selfChange && serviceManager != null)) || !isRunning) : ((ListenerUtil.mutListener.listen(65742) ? (!selfChange || serviceManager != null) : (!selfChange && serviceManager != null)) && !isRunning))) {
                        if (!ListenerUtil.mutListener.listen(65744)) {
                            this.isRunning = true;
                        }
                        boolean cont;
                        // check if a sync is in progress.. wait!
                        try {
                            SynchronizeContactsService synchronizeContactService = serviceManager.getSynchronizeContactsService();
                            cont = (ListenerUtil.mutListener.listen(65746) ? (synchronizeContactService != null || !synchronizeContactService.isSynchronizationInProgress()) : (synchronizeContactService != null && !synchronizeContactService.isSynchronizationInProgress()));
                        } catch (MasterKeyLockedException | FileSystemNotPresentException e) {
                            if (!ListenerUtil.mutListener.listen(65745)) {
                                logger.error("Exception", e);
                            }
                            // do nothing
                            cont = false;
                        }
                        if (!ListenerUtil.mutListener.listen(65752)) {
                            if (cont) {
                                PreferenceService preferencesService = serviceManager.getPreferenceService();
                                if (!ListenerUtil.mutListener.listen(65751)) {
                                    if ((ListenerUtil.mutListener.listen(65747) ? (preferencesService != null || preferencesService.isSyncContacts()) : (preferencesService != null && preferencesService.isSyncContacts()))) {
                                        try {
                                            ContactService c = serviceManager.getContactService();
                                            if (!ListenerUtil.mutListener.listen(65750)) {
                                                if (c != null) {
                                                    if (!ListenerUtil.mutListener.listen(65749)) {
                                                        // update contact names if changed!
                                                        c.updateAllContactNamesAndAvatarsFromAndroidContacts();
                                                    }
                                                }
                                            }
                                        } catch (MasterKeyLockedException | FileSystemNotPresentException e) {
                                            if (!ListenerUtil.mutListener.listen(65748)) {
                                                logger.error("Exception", e);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(65753)) {
                            this.isRunning = false;
                        }
                    }
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(65758)) {
            ListenerManager.synchronizeContactsListeners.add(new SynchronizeContactsListener() {

                @Override
                public void onStarted(SynchronizeContactsRoutine startedRoutine) {
                    if (!ListenerUtil.mutListener.listen(65755)) {
                        // disable contact observer
                        serviceManager.getContext().getContentResolver().unregisterContentObserver(contentObserverChangeContactNames);
                    }
                }

                @Override
                public void onFinished(SynchronizeContactsRoutine finishedRoutine) {
                    if (!ListenerUtil.mutListener.listen(65756)) {
                        // enable contact observer
                        serviceManager.getContext().getContentResolver().registerContentObserver(ContactsContract.Contacts.CONTENT_URI, false, contentObserverChangeContactNames);
                    }
                }

                @Override
                public void onError(SynchronizeContactsRoutine finishedRoutine) {
                    if (!ListenerUtil.mutListener.listen(65757)) {
                        // enable contact observer
                        serviceManager.getContext().getContentResolver().registerContentObserver(ContactsContract.Contacts.CONTENT_URI, false, contentObserverChangeContactNames);
                    }
                }
            }, THREEMA_APPLICATION_LISTENER_TAG);
        }
        if (!ListenerUtil.mutListener.listen(65761)) {
            ListenerManager.contactTypingListeners.add(new ContactTypingListener() {

                @Override
                public void onContactIsTyping(ContactModel fromContact, boolean isTyping) {
                    // update the conversations
                    try {
                        if (!ListenerUtil.mutListener.listen(65760)) {
                            serviceManager.getConversationService().setIsTyping(fromContact, isTyping);
                        }
                    } catch (ThreemaException e) {
                        if (!ListenerUtil.mutListener.listen(65759)) {
                            logger.error("Exception", e);
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(65763)) {
            ListenerManager.newSyncedContactListener.add(new NewSyncedContactsListener() {

                @Override
                public void onNew(List<ContactModel> contactModels) {
                    NotificationService notificationService = serviceManager.getNotificationService();
                    if (!ListenerUtil.mutListener.listen(65762)) {
                        notificationService.showNewSyncedContactsNotification(contactModels);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(65772)) {
            WebClientListenerManager.serviceListener.add(new WebClientServiceListener() {

                @Override
                public void onEnabled() {
                    if (!ListenerUtil.mutListener.listen(65764)) {
                        SessionWakeUpServiceImpl.getInstance().processPendingWakeupsAsync();
                    }
                }

                @Override
                public void onStarted(@NonNull final WebClientSessionModel model, @NonNull final byte[] permanentKey, @NonNull final String browser) {
                    if (!ListenerUtil.mutListener.listen(65765)) {
                        logger.info("WebClientListenerManager: onStarted", true);
                    }
                    if (!ListenerUtil.mutListener.listen(65766)) {
                        RuntimeUtil.runOnUiThread(() -> {
                            String toastText = getAppContext().getString(R.string.webclient_new_connection_toast);
                            if (model.getLabel() != null) {
                                toastText += " (" + model.getLabel() + ")";
                            }
                            Toast.makeText(getAppContext(), toastText, Toast.LENGTH_LONG).show();
                            final Intent intent = new Intent(context, SessionAndroidService.class);
                            if (SessionAndroidService.isRunning()) {
                                intent.setAction(SessionAndroidService.ACTION_UPDATE);
                                logger.info("sending ACTION_UPDATE to SessionAndroidService");
                                context.startService(intent);
                            } else {
                                logger.info("SessionAndroidService not running...starting");
                                intent.setAction(SessionAndroidService.ACTION_START);
                                logger.info("sending ACTION_START to SessionAndroidService");
                                ContextCompat.startForegroundService(context, intent);
                            }
                        });
                    }
                }

                @Override
                public void onStateChanged(@NonNull final WebClientSessionModel model, @NonNull final WebClientSessionState oldState, @NonNull final WebClientSessionState newState) {
                    if (!ListenerUtil.mutListener.listen(65767)) {
                        logger.info("WebClientListenerManager: onStateChanged", true);
                    }
                    if (!ListenerUtil.mutListener.listen(65769)) {
                        if (newState == WebClientSessionState.DISCONNECTED) {
                            if (!ListenerUtil.mutListener.listen(65768)) {
                                RuntimeUtil.runOnUiThread(() -> {
                                    logger.info("updating SessionAndroidService", true);
                                    if (SessionAndroidService.isRunning()) {
                                        final Intent intent = new Intent(context, SessionAndroidService.class);
                                        intent.setAction(SessionAndroidService.ACTION_UPDATE);
                                        logger.info("sending ACTION_UPDATE to SessionAndroidService");
                                        context.startService(intent);
                                    } else {
                                        logger.info("SessionAndroidService not running...not updating");
                                    }
                                });
                            }
                        }
                    }
                }

                @Override
                public void onStopped(@NonNull final WebClientSessionModel model, @NonNull final DisconnectContext reason) {
                    if (!ListenerUtil.mutListener.listen(65770)) {
                        logger.info("WebClientListenerManager: onStopped", true);
                    }
                    if (!ListenerUtil.mutListener.listen(65771)) {
                        RuntimeUtil.runOnUiThread(() -> {
                            if (SessionAndroidService.isRunning()) {
                                final Intent intent = new Intent(context, SessionAndroidService.class);
                                intent.setAction(SessionAndroidService.ACTION_STOP);
                                logger.info("sending ACTION_STOP to SessionAndroidService");
                                context.startService(intent);
                            } else {
                                logger.info("SessionAndroidService not running...not stopping");
                            }
                        });
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(65774)) {
            // called if a fcm message with a newer session received
            WebClientListenerManager.wakeUpListener.add(new WebClientWakeUpListener() {

                @Override
                public void onProtocolError() {
                    if (!ListenerUtil.mutListener.listen(65773)) {
                        RuntimeUtil.runOnUiThread(() -> Toast.makeText(getAppContext(), R.string.webclient_protocol_version_to_old, Toast.LENGTH_LONG).show());
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(65793)) {
            VoipListenerManager.callEventListener.add(new VoipCallEventListener() {

                private final Logger logger = LoggerFactory.getLogger("VoipCallEventListener");

                @Override
                public void onRinging(String peerIdentity) {
                    if (!ListenerUtil.mutListener.listen(65775)) {
                        this.logger.debug("onRinging {}", peerIdentity);
                    }
                }

                @Override
                public void onStarted(String peerIdentity, boolean outgoing) {
                    final String direction = outgoing ? "to" : "from";
                    if (!ListenerUtil.mutListener.listen(65776)) {
                        this.logger.info("Call {} {} started", direction, peerIdentity);
                    }
                }

                @Override
                public void onFinished(@NonNull String peerIdentity, boolean outgoing, int duration) {
                    final String direction = outgoing ? "to" : "from";
                    if (!ListenerUtil.mutListener.listen(65777)) {
                        this.logger.info("Call {} {} finished", direction, peerIdentity);
                    }
                    if (!ListenerUtil.mutListener.listen(65778)) {
                        this.saveStatus(peerIdentity, outgoing, VoipStatusDataModel.createFinished(duration), true);
                    }
                }

                @Override
                public void onRejected(String peerIdentity, boolean outgoing, byte reason) {
                    final String direction = outgoing ? "to" : "from";
                    if (!ListenerUtil.mutListener.listen(65779)) {
                        this.logger.info("Call {} {} rejected (reason {})", direction, peerIdentity, reason);
                    }
                    if (!ListenerUtil.mutListener.listen(65780)) {
                        this.saveStatus(peerIdentity, // on rejected incoming, the outgoing was rejected!
                        !outgoing, VoipStatusDataModel.createRejected(reason), true);
                    }
                }

                @Override
                public void onMissed(String peerIdentity, boolean accepted) {
                    if (!ListenerUtil.mutListener.listen(65781)) {
                        this.logger.info("Call from {} missed", peerIdentity);
                    }
                    if (!ListenerUtil.mutListener.listen(65782)) {
                        this.saveStatus(peerIdentity, false, VoipStatusDataModel.createMissed(), accepted);
                    }
                }

                @Override
                public void onAborted(String peerIdentity) {
                    if (!ListenerUtil.mutListener.listen(65783)) {
                        this.logger.info("Call to {} aborted", peerIdentity);
                    }
                    if (!ListenerUtil.mutListener.listen(65784)) {
                        this.saveStatus(peerIdentity, true, VoipStatusDataModel.createAborted(), true);
                    }
                }

                private void saveStatus(@NonNull String identity, boolean isOutbox, @NonNull VoipStatusDataModel status, boolean isRead) {
                    try {
                        if (!ListenerUtil.mutListener.listen(65788)) {
                            if ((ListenerUtil.mutListener.listen(65786) ? (serviceManager == null && serviceManager.getMessageService() == null) : (serviceManager == null || serviceManager.getMessageService() == null))) {
                                if (!ListenerUtil.mutListener.listen(65787)) {
                                    this.logger.error("Could not save voip status, servicemanager or messageservice are null");
                                }
                                return;
                            }
                        }
                        // If an incoming status message is not targeted at our own identity, something's wrong
                        final String appIdentity = serviceManager.getIdentityStore().getIdentity();
                        if (!ListenerUtil.mutListener.listen(65791)) {
                            if ((ListenerUtil.mutListener.listen(65789) ? (TestUtil.compare(identity, appIdentity) || !isOutbox) : (TestUtil.compare(identity, appIdentity) && !isOutbox))) {
                                if (!ListenerUtil.mutListener.listen(65790)) {
                                    this.logger.error("Could not save voip status (identity={}, appIdentity={}, outbox={})", identity, appIdentity, isOutbox);
                                }
                                return;
                            }
                        }
                        final ContactModel contactModel = serviceManager.getContactService().getByIdentity(identity);
                        final ContactMessageReceiver receiver = serviceManager.getContactService().createReceiver(contactModel);
                        if (!ListenerUtil.mutListener.listen(65792)) {
                            serviceManager.getMessageService().createVoipStatus(status, receiver, isOutbox, isRead);
                        }
                    } catch (ThreemaException e) {
                        if (!ListenerUtil.mutListener.listen(65785)) {
                            logger.error("Exception", e);
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(65801)) {
            if ((ListenerUtil.mutListener.listen(65799) ? ((ListenerUtil.mutListener.listen(65798) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(65797) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(65796) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(65795) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(65794) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M)))))) && ContextCompat.checkSelfPermission(serviceManager.getContext(), android.Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(65798) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(65797) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(65796) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(65795) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(65794) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M)))))) || ContextCompat.checkSelfPermission(serviceManager.getContext(), android.Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED))) {
                if (!ListenerUtil.mutListener.listen(65800)) {
                    serviceManager.getContext().getContentResolver().registerContentObserver(ContactsContract.Contacts.CONTENT_URI, false, contentObserverChangeContactNames);
                }
            }
        }
    }

    public static boolean activityResumed(Activity currentActivity) {
        if (!ListenerUtil.mutListener.listen(65802)) {
            logger.debug("*** App ActivityResumed");
        }
        if (!ListenerUtil.mutListener.listen(65804)) {
            if (serviceManager != null) {
                if (!ListenerUtil.mutListener.listen(65803)) {
                    serviceManager.getActivityService().resume(currentActivity);
                }
                return true;
            }
        }
        return false;
    }

    public static void activityPaused(Activity pausedActivity) {
        if (!ListenerUtil.mutListener.listen(65805)) {
            logger.debug("*** App ActivityPaused");
        }
        if (!ListenerUtil.mutListener.listen(65807)) {
            if (serviceManager != null) {
                if (!ListenerUtil.mutListener.listen(65806)) {
                    serviceManager.getActivityService().pause(pausedActivity);
                }
            }
        }
    }

    public static void activityDestroyed(Activity destroyedActivity) {
        if (!ListenerUtil.mutListener.listen(65808)) {
            logger.debug("*** App ActivityDestroyed");
        }
        if (!ListenerUtil.mutListener.listen(65810)) {
            if (serviceManager != null) {
                if (!ListenerUtil.mutListener.listen(65809)) {
                    serviceManager.getActivityService().destroy(destroyedActivity);
                }
            }
        }
    }

    public static boolean activityUserInteract(Activity interactedActivity) {
        if (!ListenerUtil.mutListener.listen(65812)) {
            if (serviceManager != null) {
                if (!ListenerUtil.mutListener.listen(65811)) {
                    serviceManager.getActivityService().userInteract(interactedActivity);
                }
            }
        }
        return true;
    }

    public static Date getLastLoggedIn() {
        return lastLoggedIn;
    }

    public static boolean isIsDeviceIdle() {
        return isDeviceIdle;
    }

    public static AppVersion getAppVersion() {
        return appVersion;
    }

    public static int getFeatureLevel() {
        return 3;
    }

    public static Context getAppContext() {
        return ThreemaApplication.context;
    }

    public static boolean getIPv6() {
        return ipv6;
    }
}
