/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2017-2021 Threema GmbH
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
package ch.threema.app.utils;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.text.format.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.preference.PreferenceManager;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.jobs.ReConnectJobService;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.push.PushRegistrationWorker;
import ch.threema.app.receivers.AlarmManagerBroadcastReceiver;
import ch.threema.app.services.DeadlineListService;
import ch.threema.app.services.LockAppService;
import ch.threema.app.services.NotificationService;
import ch.threema.app.services.NotificationServiceImpl;
import ch.threema.app.services.PollingHelper;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.PreferenceServiceImpl;
import ch.threema.app.services.RingtoneService;
import ch.threema.app.stores.PreferenceStore;
import ch.threema.app.webclient.services.SessionWakeUpServiceImpl;
import ch.threema.base.ThreemaException;
import ch.threema.client.ThreemaConnection;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PushUtil {

    private static final Logger logger = LoggerFactory.getLogger(PushUtil.class);

    public static final String EXTRA_CLEAR_TOKEN = "clear";

    public static final String EXTRA_WITH_CALLBACK = "cb";

    public static final String EXTRA_REGISTRATION_ERROR_BROADCAST = "rer";

    private static final String WEBCLIENT_SESSION = "wcs";

    private static final String WEBCLIENT_TIMESTAMP = "wct";

    private static final String WEBCLIENT_VERSION = "wcv";

    private static final String WEBCLIENT_AFFILIATION_ID = "wca";

    private static final int RECONNECT_JOB = 89;

    /**
     *  Send push token to server
     *  @param context Context
     *  @param clear Remove token from sever
     *  @param withCallback Send broadcast after token refresh has been completed or failed
     */
    public static void enqueuePushTokenUpdate(Context context, boolean clear, boolean withCallback) {
        Data workerFlags = new Data.Builder().putBoolean(EXTRA_CLEAR_TOKEN, clear).putBoolean(EXTRA_WITH_CALLBACK, withCallback).build();
        Constraints workerConstraints = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
        // worker differs between hms and regular builds, see gcm and hms directory for for overwriting push worker versions
        WorkRequest pushTokenRegistrationRequest = new OneTimeWorkRequest.Builder(PushRegistrationWorker.class).setInputData(workerFlags).setConstraints(workerConstraints).build();
        if (!ListenerUtil.mutListener.listen(55179)) {
            WorkManager.getInstance(context).enqueue(pushTokenRegistrationRequest);
        }
    }

    /**
     *  Send a push token to the server
     *  @param context Context to access shared preferences and key strings for the the last token sent date update
     *  @param token String representing the token
     *  @param type int representing the token type (gcm, hms or none in case of a reset)
     */
    public static void sendTokenToServer(Context context, String token, int type) throws ThreemaException {
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(55187)) {
            if (serviceManager != null) {
                ThreemaConnection connection = serviceManager.getConnection();
                if (!ListenerUtil.mutListener.listen(55186)) {
                    if (connection != null) {
                        if (!ListenerUtil.mutListener.listen(55180)) {
                            connection.setPushToken(type, token);
                        }
                        if (!ListenerUtil.mutListener.listen(55181)) {
                            logger.info("push token of type {} successfully sent to server", type);
                        }
                        if (!ListenerUtil.mutListener.listen(55184)) {
                            // reset token update timestamp if it was reset, set current update time otherwise
                            if (token.isEmpty()) {
                                if (!ListenerUtil.mutListener.listen(55183)) {
                                    PreferenceManager.getDefaultSharedPreferences(context).edit().putLong(ThreemaApplication.getAppContext().getString(R.string.preferences__token_sent_date), 0L).apply();
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(55182)) {
                                    PreferenceManager.getDefaultSharedPreferences(context).edit().putLong(context.getString(R.string.preferences__token_sent_date), System.currentTimeMillis()).apply();
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(55185)) {
                            // Used in the Webclient Sessions
                            serviceManager.getPreferenceService().setPushToken(token);
                        }
                    } else {
                        throw new ThreemaException("Unable to send / clear push token. ThreemaConnection not available");
                    }
                }
            } else {
                throw new ThreemaException("Unable to send / clear push token. ServiceManager not available");
            }
        }
    }

    /**
     *  Signal a push token update through a local broadcast
     *  @param error String potential error message
     *  @param clearToken boolean whether the token was reset
     */
    public static void signalRegistrationFinished(@Nullable String error, boolean clearToken) {
        final Intent intent = new Intent(ThreemaApplication.INTENT_PUSH_REGISTRATION_COMPLETE);
        if (!ListenerUtil.mutListener.listen(55191)) {
            if (error != null) {
                if (!ListenerUtil.mutListener.listen(55189)) {
                    logger.error("Failed to get push token {}", error);
                }
                if (!ListenerUtil.mutListener.listen(55190)) {
                    intent.putExtra(PushUtil.EXTRA_REGISTRATION_ERROR_BROADCAST, true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(55188)) {
                    intent.putExtra(PushUtil.EXTRA_CLEAR_TOKEN, clearToken);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(55192)) {
            LocalBroadcastManager.getInstance(ThreemaApplication.getAppContext()).sendBroadcast(intent);
        }
    }

    /**
     *  Process the Data mapping received from a FCM message
     *  @param data Map<String, String> key value pairs with webclient session infos
     */
    public static void processRemoteMessage(Map<String, String> data) {
        if (!ListenerUtil.mutListener.listen(55193)) {
            logger.info("processRemoteMessage");
        }
        if (!ListenerUtil.mutListener.listen(55198)) {
            // Webclient push
            if ((ListenerUtil.mutListener.listen(55195) ? ((ListenerUtil.mutListener.listen(55194) ? (data != null || data.containsKey(WEBCLIENT_SESSION)) : (data != null && data.containsKey(WEBCLIENT_SESSION))) || data.containsKey(WEBCLIENT_TIMESTAMP)) : ((ListenerUtil.mutListener.listen(55194) ? (data != null || data.containsKey(WEBCLIENT_SESSION)) : (data != null && data.containsKey(WEBCLIENT_SESSION))) && data.containsKey(WEBCLIENT_TIMESTAMP)))) {
                if (!ListenerUtil.mutListener.listen(55197)) {
                    sendWebclientNotification(data);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(55196)) {
                    // New messages push, trigger a reconnect and show new message notification(s)
                    sendNotification();
                }
            }
        }
    }

    private static void sendNotification() {
        if (!ListenerUtil.mutListener.listen(55199)) {
            logger.info("sendNotification");
        }
        Context appContext = ThreemaApplication.getAppContext();
        PollingHelper pollingHelper = new PollingHelper(appContext, "FCM");
        ConnectivityManager mgr = (ConnectivityManager) appContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = mgr.getActiveNetworkInfo();
        if (!ListenerUtil.mutListener.listen(55211)) {
            if ((ListenerUtil.mutListener.listen(55200) ? (networkInfo != null || networkInfo.getDetailedState() == NetworkInfo.DetailedState.BLOCKED) : (networkInfo != null && networkInfo.getDetailedState() == NetworkInfo.DetailedState.BLOCKED))) {
                if (!ListenerUtil.mutListener.listen(55201)) {
                    logger.warn("Network blocked (background data disabled?)");
                }
                if (!ListenerUtil.mutListener.listen(55210)) {
                    // Simply schedule a poll when the device is back online
                    if ((ListenerUtil.mutListener.listen(55206) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(55205) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(55204) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(55203) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(55202) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                        JobScheduler js = (JobScheduler) appContext.getSystemService(Context.JOB_SCHEDULER_SERVICE);
                        if (!ListenerUtil.mutListener.listen(55207)) {
                            js.cancel(RECONNECT_JOB);
                        }
                        JobInfo job = new JobInfo.Builder(RECONNECT_JOB, new ComponentName(appContext, ReConnectJobService.class)).setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY).setRequiresCharging(false).build();
                        if (!ListenerUtil.mutListener.listen(55209)) {
                            if (js.schedule(job) != JobScheduler.RESULT_SUCCESS) {
                                if (!ListenerUtil.mutListener.listen(55208)) {
                                    logger.error("Job scheduling failed");
                                }
                            }
                        }
                    }
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(55213)) {
            if (networkInfo == null) {
                if (!ListenerUtil.mutListener.listen(55212)) {
                    logger.warn("No network info available");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(55214)) {
            // recheck after one minute
            AlarmManagerBroadcastReceiver.requireLoggedInConnection(appContext, (int) DateUtils.MINUTE_IN_MILLIS);
        }
        PreferenceStore preferenceStore = new PreferenceStore(appContext, null);
        PreferenceServiceImpl preferenceService = new PreferenceServiceImpl(appContext, preferenceStore);
        if (!ListenerUtil.mutListener.listen(55218)) {
            if ((ListenerUtil.mutListener.listen(55216) ? ((ListenerUtil.mutListener.listen(55215) ? (ThreemaApplication.getMasterKey() != null || ThreemaApplication.getMasterKey().isLocked()) : (ThreemaApplication.getMasterKey() != null && ThreemaApplication.getMasterKey().isLocked())) || preferenceService.isMasterKeyNewMessageNotifications()) : ((ListenerUtil.mutListener.listen(55215) ? (ThreemaApplication.getMasterKey() != null || ThreemaApplication.getMasterKey().isLocked()) : (ThreemaApplication.getMasterKey() != null && ThreemaApplication.getMasterKey().isLocked())) && preferenceService.isMasterKeyNewMessageNotifications()))) {
                if (!ListenerUtil.mutListener.listen(55217)) {
                    displayAdHocNotification();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(55220)) {
            if (!pollingHelper.poll(true)) {
                if (!ListenerUtil.mutListener.listen(55219)) {
                    logger.warn("Unable to establish connection");
                }
            }
        }
    }

    private static void displayAdHocNotification() {
        if (!ListenerUtil.mutListener.listen(55221)) {
            logger.info("displayAdHocNotification");
        }
        final Context appContext = ThreemaApplication.getAppContext();
        final ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        NotificationService notificationService;
        if (serviceManager != null) {
            notificationService = serviceManager.getNotificationService();
        } else {
            // create a temporary service class (with some implementations) to use the showMasterKeyLockedNewMessageNotification
            PreferenceStore ps = new PreferenceStore(appContext, ThreemaApplication.getMasterKey());
            PreferenceService p = new PreferenceServiceImpl(appContext, ps);
            notificationService = new NotificationServiceImpl(appContext, new LockAppService() {

                @Override
                public boolean isLockingEnabled() {
                    return false;
                }

                @Override
                public boolean unlock(String pin) {
                    return false;
                }

                @Override
                public void lock() {
                }

                @Override
                public boolean checkLock() {
                    return false;
                }

                @Override
                public boolean isLocked() {
                    return false;
                }

                @Override
                public LockAppService resetLockTimer(boolean restartAfterReset) {
                    return null;
                }

                @Override
                public void addOnLockAppStateChanged(OnLockAppStateChanged c) {
                }

                @Override
                public void removeOnLockAppStateChanged(OnLockAppStateChanged c) {
                }
            }, new DeadlineListService() {

                @Override
                public void add(String uid, long timeout) {
                }

                @Override
                public void init() {
                }

                @Override
                public boolean has(String uid) {
                    return false;
                }

                @Override
                public void remove(String uid) {
                }

                @Override
                public long getDeadline(String uid) {
                    return 0;
                }

                @Override
                public int getSize() {
                    return 0;
                }

                @Override
                public void clear() {
                }
            }, p, new RingtoneService() {

                @Override
                public void init() {
                }

                @Override
                public void setRingtone(String uniqueId, Uri ringtoneUri) {
                }

                @Override
                public Uri getRingtoneFromUniqueId(String uniqueId) {
                    return null;
                }

                @Override
                public boolean hasCustomRingtone(String uniqueId) {
                    return false;
                }

                @Override
                public void removeCustomRingtone(String uniqueId) {
                }

                @Override
                public void resetRingtones(Context context) {
                }

                @Override
                public Uri getContactRingtone(String uniqueId) {
                    return null;
                }

                @Override
                public Uri getGroupRingtone(String uniqueId) {
                    return null;
                }

                @Override
                public Uri getVoiceCallRingtone(String uniqueId) {
                    return null;
                }

                @Override
                public Uri getDefaultContactRingtone() {
                    return null;
                }

                @Override
                public Uri getDefaultGroupRingtone() {
                    return null;
                }

                @Override
                public boolean isSilent(String uniqueId, boolean isGroup) {
                    return false;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(55223)) {
            if (notificationService != null) {
                if (!ListenerUtil.mutListener.listen(55222)) {
                    notificationService.showMasterKeyLockedNewMessageNotification();
                }
            }
        }
    }

    private static void sendWebclientNotification(Map<String, String> data) {
        final String session = data.get(WEBCLIENT_SESSION);
        final String timestamp = data.get(WEBCLIENT_TIMESTAMP);
        final String version = data.get(WEBCLIENT_VERSION);
        final String affiliationId = data.get(WEBCLIENT_AFFILIATION_ID);
        if (!ListenerUtil.mutListener.listen(55230)) {
            if ((ListenerUtil.mutListener.listen(55226) ? ((ListenerUtil.mutListener.listen(55225) ? ((ListenerUtil.mutListener.listen(55224) ? (session != null || !session.isEmpty()) : (session != null && !session.isEmpty())) || timestamp != null) : ((ListenerUtil.mutListener.listen(55224) ? (session != null || !session.isEmpty()) : (session != null && !session.isEmpty())) && timestamp != null)) || !timestamp.isEmpty()) : ((ListenerUtil.mutListener.listen(55225) ? ((ListenerUtil.mutListener.listen(55224) ? (session != null || !session.isEmpty()) : (session != null && !session.isEmpty())) || timestamp != null) : ((ListenerUtil.mutListener.listen(55224) ? (session != null || !session.isEmpty()) : (session != null && !session.isEmpty())) && timestamp != null)) && !timestamp.isEmpty()))) {
                if (!ListenerUtil.mutListener.listen(55227)) {
                    logger.debug("Received webclient wakeup for session {}", session);
                }
                final Thread t = new Thread(() -> {
                    logger.info("Trying to wake up webclient session {}", session);
                    // Parse version number
                    Integer versionNumber = null;
                    if (version != null) {
                        // Can be null during beta, if an old client doesn't yet send the version field
                        try {
                            versionNumber = Integer.parseInt(version);
                        } catch (NumberFormatException e) {
                            // We should probably throw the entire wakeup notification away.
                            logger.error("Could not parse webclient protocol version number: ", e);
                            return;
                        }
                    }
                    // Try to wake up session
                    SessionWakeUpServiceImpl.getInstance().resume(session, versionNumber == null ? 0 : versionNumber, affiliationId);
                });
                if (!ListenerUtil.mutListener.listen(55228)) {
                    t.setName("webclient-wakeup");
                }
                if (!ListenerUtil.mutListener.listen(55229)) {
                    t.start();
                }
            }
        }
    }

    /**
     *  Clear the "token last updated" setting in shared preferences
     *  @param context Context
     */
    public static void clearPushTokenSentDate(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (!ListenerUtil.mutListener.listen(55232)) {
            if (sharedPreferences != null) {
                if (!ListenerUtil.mutListener.listen(55231)) {
                    sharedPreferences.edit().putLong(context.getString(R.string.preferences__token_sent_date), 0L).apply();
                }
            }
        }
    }

    /**
     *  Check if the token needs to be uploaded to the server i.e. no more than once a day.
     *  @param context Context
     *  @return true if more than a day has passed since the token has been last sent to the server, false otherwise
     */
    public static boolean pushTokenNeedsRefresh(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (!ListenerUtil.mutListener.listen(55242)) {
            if (sharedPreferences != null) {
                long lastDate = sharedPreferences.getLong(context.getString(R.string.preferences__token_sent_date), 0L);
                // refresh push token at least once a day
                return (ListenerUtil.mutListener.listen(55241) ? (((ListenerUtil.mutListener.listen(55236) ? (System.currentTimeMillis() % lastDate) : (ListenerUtil.mutListener.listen(55235) ? (System.currentTimeMillis() / lastDate) : (ListenerUtil.mutListener.listen(55234) ? (System.currentTimeMillis() * lastDate) : (ListenerUtil.mutListener.listen(55233) ? (System.currentTimeMillis() + lastDate) : (System.currentTimeMillis() - lastDate)))))) >= DateUtils.DAY_IN_MILLIS) : (ListenerUtil.mutListener.listen(55240) ? (((ListenerUtil.mutListener.listen(55236) ? (System.currentTimeMillis() % lastDate) : (ListenerUtil.mutListener.listen(55235) ? (System.currentTimeMillis() / lastDate) : (ListenerUtil.mutListener.listen(55234) ? (System.currentTimeMillis() * lastDate) : (ListenerUtil.mutListener.listen(55233) ? (System.currentTimeMillis() + lastDate) : (System.currentTimeMillis() - lastDate)))))) <= DateUtils.DAY_IN_MILLIS) : (ListenerUtil.mutListener.listen(55239) ? (((ListenerUtil.mutListener.listen(55236) ? (System.currentTimeMillis() % lastDate) : (ListenerUtil.mutListener.listen(55235) ? (System.currentTimeMillis() / lastDate) : (ListenerUtil.mutListener.listen(55234) ? (System.currentTimeMillis() * lastDate) : (ListenerUtil.mutListener.listen(55233) ? (System.currentTimeMillis() + lastDate) : (System.currentTimeMillis() - lastDate)))))) < DateUtils.DAY_IN_MILLIS) : (ListenerUtil.mutListener.listen(55238) ? (((ListenerUtil.mutListener.listen(55236) ? (System.currentTimeMillis() % lastDate) : (ListenerUtil.mutListener.listen(55235) ? (System.currentTimeMillis() / lastDate) : (ListenerUtil.mutListener.listen(55234) ? (System.currentTimeMillis() * lastDate) : (ListenerUtil.mutListener.listen(55233) ? (System.currentTimeMillis() + lastDate) : (System.currentTimeMillis() - lastDate)))))) != DateUtils.DAY_IN_MILLIS) : (ListenerUtil.mutListener.listen(55237) ? (((ListenerUtil.mutListener.listen(55236) ? (System.currentTimeMillis() % lastDate) : (ListenerUtil.mutListener.listen(55235) ? (System.currentTimeMillis() / lastDate) : (ListenerUtil.mutListener.listen(55234) ? (System.currentTimeMillis() * lastDate) : (ListenerUtil.mutListener.listen(55233) ? (System.currentTimeMillis() + lastDate) : (System.currentTimeMillis() - lastDate)))))) == DateUtils.DAY_IN_MILLIS) : (((ListenerUtil.mutListener.listen(55236) ? (System.currentTimeMillis() % lastDate) : (ListenerUtil.mutListener.listen(55235) ? (System.currentTimeMillis() / lastDate) : (ListenerUtil.mutListener.listen(55234) ? (System.currentTimeMillis() * lastDate) : (ListenerUtil.mutListener.listen(55233) ? (System.currentTimeMillis() + lastDate) : (System.currentTimeMillis() - lastDate)))))) > DateUtils.DAY_IN_MILLIS))))));
            }
        }
        return true;
    }

    /**
     *  Check if push services are enabled and polling is not used.
     *  @param context Context
     *  @return true if polling is disabled or shared preferences are not available, false otherwise
     */
    public static boolean isPushEnabled(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (!ListenerUtil.mutListener.listen(55243)) {
            if (sharedPreferences != null) {
                return !sharedPreferences.getBoolean(context.getString(R.string.preferences__polling_switch), false);
            }
        }
        return true;
    }
}
