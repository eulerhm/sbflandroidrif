/*
 * Copyright (C) 2005-2019 University of South Florida
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.android.nav;

import static android.app.PendingIntent.*;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import org.apache.commons.io.FileUtils;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.ObaAnalytics;
import org.onebusaway.android.nav.model.Path;
import org.onebusaway.android.nav.model.PathLink;
import org.onebusaway.android.provider.ObaContract;
import org.onebusaway.android.ui.FeedbackActivity;
import org.onebusaway.android.ui.TripDetailsListFragment;
import org.onebusaway.android.util.LocationHelper;
import org.onebusaway.android.util.LocationUtils;
import org.onebusaway.android.util.PreferenceUtils;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Implements the "destination reminders" feature in the app that notifies the user as they
 * are approaching their destination stop on-board the transit vehicle.
 * <p>
 * The NavigationService is started when the user begins a trip, this service listens for location
 * updates and passes the locations to its instance of NavigationServiceProvider each time.
 * NavigationServiceProvider is responsible for computing the statuses of the trips and issuing
 * notifications/TTS messages. Once the NavigationServiceProvider is completed, the
 * NavigationService will stop itself.
 */
public class NavigationService extends Service implements LocationHelper.Listener {

    public static final String TAG = "NavigationService";

    public static final String DESTINATION_ID = ".DestinationId";

    public static final String BEFORE_STOP_ID = ".BeforeId";

    public static final String TRIP_ID = ".TripId";

    public static final String FIRST_FEEDBACK = "firstFeedback";

    public static final String KEY_TEXT_REPLY = "trip_feedback";

    public static final String LOG_DIRECTORY = "ObaNavLog";

    public static boolean mFirstFeedback = true;

    private static final int RECORDING_THRESHOLD = NavigationServiceProvider.DISTANCE_THRESHOLD + 100;

    private LocationHelper mLocationHelper = null;

    private Location mLastLocation = null;

    // Destination Stop ID
    private String mDestinationStopId;

    // Before Destination Stop ID
    private String mBeforeStopId;

    // Trip ID
    private String mTripId;

    private int mCoordId = 0;

    private NavigationServiceProvider mNavProvider;

    private File mLogFile = null;

    private long mFinishedTime;

    private FirebaseAuth mAuth;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!ListenerUtil.mutListener.listen(12105)) {
            Log.d(TAG, "Starting Service");
        }
        if (!ListenerUtil.mutListener.listen(12106)) {
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        }
        long currentTime = System.currentTimeMillis();
        if (!ListenerUtil.mutListener.listen(12123)) {
            if (intent != null) {
                if (!ListenerUtil.mutListener.listen(12118)) {
                    mDestinationStopId = intent.getStringExtra(DESTINATION_ID);
                }
                if (!ListenerUtil.mutListener.listen(12119)) {
                    mBeforeStopId = intent.getStringExtra(BEFORE_STOP_ID);
                }
                if (!ListenerUtil.mutListener.listen(12120)) {
                    mTripId = intent.getStringExtra(TRIP_ID);
                }
                if (!ListenerUtil.mutListener.listen(12121)) {
                    ObaContract.NavStops.insert(Application.get().getApplicationContext(), currentTime, 1, 1, mTripId, mDestinationStopId, mBeforeStopId);
                }
                if (!ListenerUtil.mutListener.listen(12122)) {
                    mNavProvider = new NavigationServiceProvider(mTripId, mDestinationStopId);
                }
            } else {
                String[] args = ObaContract.NavStops.getDetails(Application.get().getApplicationContext(), "1");
                if (!ListenerUtil.mutListener.listen(12117)) {
                    if ((ListenerUtil.mutListener.listen(12112) ? (args != null || (ListenerUtil.mutListener.listen(12111) ? (args.length >= 3) : (ListenerUtil.mutListener.listen(12110) ? (args.length <= 3) : (ListenerUtil.mutListener.listen(12109) ? (args.length > 3) : (ListenerUtil.mutListener.listen(12108) ? (args.length < 3) : (ListenerUtil.mutListener.listen(12107) ? (args.length != 3) : (args.length == 3))))))) : (args != null && (ListenerUtil.mutListener.listen(12111) ? (args.length >= 3) : (ListenerUtil.mutListener.listen(12110) ? (args.length <= 3) : (ListenerUtil.mutListener.listen(12109) ? (args.length > 3) : (ListenerUtil.mutListener.listen(12108) ? (args.length < 3) : (ListenerUtil.mutListener.listen(12107) ? (args.length != 3) : (args.length == 3))))))))) {
                        if (!ListenerUtil.mutListener.listen(12113)) {
                            mTripId = args[0];
                        }
                        if (!ListenerUtil.mutListener.listen(12114)) {
                            mDestinationStopId = args[1];
                        }
                        if (!ListenerUtil.mutListener.listen(12115)) {
                            mBeforeStopId = args[2];
                        }
                        if (!ListenerUtil.mutListener.listen(12116)) {
                            mNavProvider = new NavigationServiceProvider(mTripId, mDestinationStopId, 1);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12124)) {
            // Log in anonymously via Firebase
            initAnonFirebaseLogin();
        }
        if (!ListenerUtil.mutListener.listen(12126)) {
            // Setup file for logging.
            if (mLogFile == null) {
                if (!ListenerUtil.mutListener.listen(12125)) {
                    setupLog();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12128)) {
            if (mLocationHelper == null) {
                if (!ListenerUtil.mutListener.listen(12127)) {
                    mLocationHelper = new LocationHelper(this, 1);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12129)) {
            Log.d(TAG, "Requesting Location Updates");
        }
        if (!ListenerUtil.mutListener.listen(12130)) {
            mLocationHelper.registerListener(this);
        }
        Location dest = ObaContract.Stops.getLocation(Application.get().getApplicationContext(), mDestinationStopId);
        Location last = ObaContract.Stops.getLocation(Application.get().getApplicationContext(), mBeforeStopId);
        PathLink pathLink = new PathLink(currentTime, null, last, dest, mTripId);
        if (!ListenerUtil.mutListener.listen(12133)) {
            if (mNavProvider != null) {
                // TODO Support more than one path link
                ArrayList<PathLink> links = new ArrayList<>(1);
                if (!ListenerUtil.mutListener.listen(12131)) {
                    links.add(pathLink);
                }
                Path path = new Path(links);
                if (!ListenerUtil.mutListener.listen(12132)) {
                    mNavProvider.navigate(path);
                }
            }
        }
        Notification notification = mNavProvider.getForegroundStartingNotification();
        if (!ListenerUtil.mutListener.listen(12134)) {
            startForeground(NavigationServiceProvider.NOTIFICATION_ID, notification);
        }
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return false;
    }

    @Override
    public void onRebind(Intent intent) {
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(12135)) {
            Log.d(TAG, "Destroying Service.");
        }
        if (!ListenerUtil.mutListener.listen(12136)) {
            mLocationHelper.unregisterListener(this);
        }
        if (!ListenerUtil.mutListener.listen(12137)) {
            super.onDestroy();
        }
        if (!ListenerUtil.mutListener.listen(12138)) {
            // Send Broadcast
            sendBroadcast();
        }
    }

    /**
     * Sends broadcast so that flag of destination alert is removed from trip detail screen
     */
    private void sendBroadcast() {
        Intent intent = new Intent(TripDetailsListFragment.ACTION_SERVICE_DESTROYED);
        if (!ListenerUtil.mutListener.listen(12139)) {
            sendBroadcast(intent);
        }
    }

    @Override
    public synchronized void onLocationChanged(Location location) {
        if (!ListenerUtil.mutListener.listen(12140)) {
            Log.d(TAG, "Location Updated");
        }
        if (!ListenerUtil.mutListener.listen(12143)) {
            if (mLastLocation == null) {
                if (!ListenerUtil.mutListener.listen(12142)) {
                    mNavProvider.locationUpdated(location);
                }
            } else if (!LocationUtils.isDuplicate(mLastLocation, location)) {
                if (!ListenerUtil.mutListener.listen(12141)) {
                    mNavProvider.locationUpdated(location);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12150)) {
            if ((ListenerUtil.mutListener.listen(12148) ? (mNavProvider.mSectoCurDistance >= RECORDING_THRESHOLD) : (ListenerUtil.mutListener.listen(12147) ? (mNavProvider.mSectoCurDistance > RECORDING_THRESHOLD) : (ListenerUtil.mutListener.listen(12146) ? (mNavProvider.mSectoCurDistance < RECORDING_THRESHOLD) : (ListenerUtil.mutListener.listen(12145) ? (mNavProvider.mSectoCurDistance != RECORDING_THRESHOLD) : (ListenerUtil.mutListener.listen(12144) ? (mNavProvider.mSectoCurDistance == RECORDING_THRESHOLD) : (mNavProvider.mSectoCurDistance <= RECORDING_THRESHOLD))))))) {
                if (!ListenerUtil.mutListener.listen(12149)) {
                    writeToLog(location);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12151)) {
            mLastLocation = location;
        }
        if (!ListenerUtil.mutListener.listen(12172)) {
            // Is trip is finished? If so end service.
            if (mNavProvider.getFinished()) {
                if (!ListenerUtil.mutListener.listen(12171)) {
                    if ((ListenerUtil.mutListener.listen(12156) ? (mFinishedTime >= 0) : (ListenerUtil.mutListener.listen(12155) ? (mFinishedTime <= 0) : (ListenerUtil.mutListener.listen(12154) ? (mFinishedTime > 0) : (ListenerUtil.mutListener.listen(12153) ? (mFinishedTime < 0) : (ListenerUtil.mutListener.listen(12152) ? (mFinishedTime != 0) : (mFinishedTime == 0))))))) {
                        if (!ListenerUtil.mutListener.listen(12170)) {
                            mFinishedTime = System.currentTimeMillis();
                        }
                    } else if ((ListenerUtil.mutListener.listen(12165) ? ((ListenerUtil.mutListener.listen(12160) ? (System.currentTimeMillis() % mFinishedTime) : (ListenerUtil.mutListener.listen(12159) ? (System.currentTimeMillis() / mFinishedTime) : (ListenerUtil.mutListener.listen(12158) ? (System.currentTimeMillis() * mFinishedTime) : (ListenerUtil.mutListener.listen(12157) ? (System.currentTimeMillis() + mFinishedTime) : (System.currentTimeMillis() - mFinishedTime))))) <= 30000) : (ListenerUtil.mutListener.listen(12164) ? ((ListenerUtil.mutListener.listen(12160) ? (System.currentTimeMillis() % mFinishedTime) : (ListenerUtil.mutListener.listen(12159) ? (System.currentTimeMillis() / mFinishedTime) : (ListenerUtil.mutListener.listen(12158) ? (System.currentTimeMillis() * mFinishedTime) : (ListenerUtil.mutListener.listen(12157) ? (System.currentTimeMillis() + mFinishedTime) : (System.currentTimeMillis() - mFinishedTime))))) > 30000) : (ListenerUtil.mutListener.listen(12163) ? ((ListenerUtil.mutListener.listen(12160) ? (System.currentTimeMillis() % mFinishedTime) : (ListenerUtil.mutListener.listen(12159) ? (System.currentTimeMillis() / mFinishedTime) : (ListenerUtil.mutListener.listen(12158) ? (System.currentTimeMillis() * mFinishedTime) : (ListenerUtil.mutListener.listen(12157) ? (System.currentTimeMillis() + mFinishedTime) : (System.currentTimeMillis() - mFinishedTime))))) < 30000) : (ListenerUtil.mutListener.listen(12162) ? ((ListenerUtil.mutListener.listen(12160) ? (System.currentTimeMillis() % mFinishedTime) : (ListenerUtil.mutListener.listen(12159) ? (System.currentTimeMillis() / mFinishedTime) : (ListenerUtil.mutListener.listen(12158) ? (System.currentTimeMillis() * mFinishedTime) : (ListenerUtil.mutListener.listen(12157) ? (System.currentTimeMillis() + mFinishedTime) : (System.currentTimeMillis() - mFinishedTime))))) != 30000) : (ListenerUtil.mutListener.listen(12161) ? ((ListenerUtil.mutListener.listen(12160) ? (System.currentTimeMillis() % mFinishedTime) : (ListenerUtil.mutListener.listen(12159) ? (System.currentTimeMillis() / mFinishedTime) : (ListenerUtil.mutListener.listen(12158) ? (System.currentTimeMillis() * mFinishedTime) : (ListenerUtil.mutListener.listen(12157) ? (System.currentTimeMillis() + mFinishedTime) : (System.currentTimeMillis() - mFinishedTime))))) == 30000) : ((ListenerUtil.mutListener.listen(12160) ? (System.currentTimeMillis() % mFinishedTime) : (ListenerUtil.mutListener.listen(12159) ? (System.currentTimeMillis() / mFinishedTime) : (ListenerUtil.mutListener.listen(12158) ? (System.currentTimeMillis() * mFinishedTime) : (ListenerUtil.mutListener.listen(12157) ? (System.currentTimeMillis() + mFinishedTime) : (System.currentTimeMillis() - mFinishedTime))))) >= 30000))))))) {
                        if (!ListenerUtil.mutListener.listen(12166)) {
                            ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_destination_reminder), getString(R.string.analytics_label_destination_reminder_variant_ended));
                        }
                        if (!ListenerUtil.mutListener.listen(12167)) {
                            getUserFeedback();
                        }
                        if (!ListenerUtil.mutListener.listen(12168)) {
                            stopSelf();
                        }
                        if (!ListenerUtil.mutListener.listen(12169)) {
                            setupLogCleanupTask();
                        }
                    }
                }
            }
        }
    }

    private void initAnonFirebaseLogin() {
        if (!ListenerUtil.mutListener.listen(12173)) {
            mAuth = FirebaseAuth.getInstance();
        }
        int numCores = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor executor = new ThreadPoolExecutor((ListenerUtil.mutListener.listen(12177) ? (numCores % 2) : (ListenerUtil.mutListener.listen(12176) ? (numCores / 2) : (ListenerUtil.mutListener.listen(12175) ? (numCores - 2) : (ListenerUtil.mutListener.listen(12174) ? (numCores + 2) : (numCores * 2))))), (ListenerUtil.mutListener.listen(12181) ? (numCores % 2) : (ListenerUtil.mutListener.listen(12180) ? (numCores / 2) : (ListenerUtil.mutListener.listen(12179) ? (numCores - 2) : (ListenerUtil.mutListener.listen(12178) ? (numCores + 2) : (numCores * 2))))), 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        if (!ListenerUtil.mutListener.listen(12182)) {
            mAuth.signInAnonymously().addOnCompleteListener(executor, task -> {
                if (task.isSuccessful()) {
                    // Sign in success
                    FirebaseUser user = mAuth.getCurrentUser();
                    Log.d(TAG, "signInAnonymously:success");
                } else {
                    // Sign in failed
                    Log.w(TAG, "signInAnonymously:failure", task.getException());
                }
            });
        }
    }

    /**
     * Creates the log file that GPS data and navigation performance is written to - see DESTINATION_ALERTS.md
     */
    private void setupLog() {
        try {
            // Get the counter that's incremented for each test
            final String NAV_TEST_ID = getString(R.string.preference_key_nav_test_id);
            int counter = Application.getPrefs().getInt(NAV_TEST_ID, 0);
            if (!ListenerUtil.mutListener.listen(12184)) {
                counter++;
            }
            if (!ListenerUtil.mutListener.listen(12185)) {
                PreferenceUtils.saveInt(NAV_TEST_ID, counter);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d yyyy, hh:mm aaa");
            String readableDate = sdf.format(Calendar.getInstance().getTime());
            File subFolder = new File(Application.get().getApplicationContext().getFilesDir().getAbsolutePath() + File.separator + LOG_DIRECTORY);
            if (!ListenerUtil.mutListener.listen(12187)) {
                if (!subFolder.exists()) {
                    if (!ListenerUtil.mutListener.listen(12186)) {
                        subFolder.mkdirs();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(12188)) {
                mLogFile = new File(subFolder, counter + "-" + readableDate + ".csv");
            }
            if (!ListenerUtil.mutListener.listen(12189)) {
                Log.d(TAG, ":" + mLogFile.getAbsolutePath());
            }
            Location dest = ObaContract.Stops.getLocation(Application.get().getApplicationContext(), mDestinationStopId);
            Location last = ObaContract.Stops.getLocation(Application.get().getApplicationContext(), mBeforeStopId);
            String header = String.format(Locale.US, "%s,%s,%f,%f,%s,%f,%f\n", mTripId, mDestinationStopId, dest.getLatitude(), dest.getLongitude(), mBeforeStopId, last.getLatitude(), last.getLongitude());
            if (!ListenerUtil.mutListener.listen(12192)) {
                if (mLogFile != null) {
                    if (!ListenerUtil.mutListener.listen(12191)) {
                        FileUtils.write(mLogFile, header, false);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(12190)) {
                        Log.e(TAG, "Failed to write to file - null file");
                    }
                }
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(12183)) {
                Log.e(TAG, "File write failed: " + e.toString());
            }
        }
    }

    private void writeToLog(Location l) {
        try {
            String nanoTime = "";
            if (!ListenerUtil.mutListener.listen(12200)) {
                if ((ListenerUtil.mutListener.listen(12198) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(12197) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(12196) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(12195) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(12194) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN_MR1) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1))))))) {
                    if (!ListenerUtil.mutListener.listen(12199)) {
                        nanoTime = Long.toString(l.getElapsedRealtimeNanos());
                    }
                }
            }
            int satellites = 0;
            if (!ListenerUtil.mutListener.listen(12202)) {
                if (l.getExtras() != null) {
                    if (!ListenerUtil.mutListener.listen(12201)) {
                        satellites = l.getExtras().getInt("satellites", 0);
                    }
                }
            }
            // TODO: Add isMockProvider
            String log = String.format(Locale.US, "%d,%s,%s,%s,%d,%f,%f,%f,%f,%f,%f,%d,%s\n", mCoordId, mNavProvider.getGetReady(), mNavProvider.getFinished(), nanoTime, l.getTime(), l.getLatitude(), l.getLongitude(), l.getAltitude(), l.getSpeed(), l.getBearing(), l.getAccuracy(), satellites, l.getProvider());
            if (!ListenerUtil.mutListener.listen(12203)) {
                // Increments the id for each coordinate
                mCoordId++;
            }
            if (!ListenerUtil.mutListener.listen(12207)) {
                if ((ListenerUtil.mutListener.listen(12204) ? (mLogFile != null || mLogFile.canWrite()) : (mLogFile != null && mLogFile.canWrite()))) {
                    if (!ListenerUtil.mutListener.listen(12206)) {
                        FileUtils.write(mLogFile, log, true);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(12205)) {
                        Log.e(TAG, "Failed to write to file");
                    }
                }
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(12193)) {
                Log.e(TAG, "File write failed: " + e.toString());
            }
        }
    }

    public void getUserFeedback() {
        Application app = Application.get();
        NotificationCompat.Builder mBuilder;
        String message = Application.get().getString(R.string.feedback_notify_dialog_msg);
        if (!ListenerUtil.mutListener.listen(12208)) {
            mFirstFeedback = Application.getPrefs().getBoolean(FIRST_FEEDBACK, true);
        }
        // Create delete intent to set flag for snackbar creation next time the app is opened.
        Intent delIntent = new Intent(app.getApplicationContext(), FeedbackReceiver.class);
        if (!ListenerUtil.mutListener.listen(12209)) {
            delIntent.putExtra(FeedbackReceiver.NOTIFICATION_ID, mNavProvider.NOTIFICATION_ID + 1);
        }
        if ((ListenerUtil.mutListener.listen(12221) ? ((ListenerUtil.mutListener.listen(12215) ? ((mFirstFeedback) && ((ListenerUtil.mutListener.listen(12214) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(12213) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(12212) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(12211) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(12210) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.N)))))))) : ((mFirstFeedback) || ((ListenerUtil.mutListener.listen(12214) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(12213) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(12212) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(12211) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(12210) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.N))))))))) && ((ListenerUtil.mutListener.listen(12220) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(12219) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(12218) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(12217) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(12216) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.P) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)))))))) : ((ListenerUtil.mutListener.listen(12215) ? ((mFirstFeedback) && ((ListenerUtil.mutListener.listen(12214) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(12213) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(12212) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(12211) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(12210) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.N)))))))) : ((mFirstFeedback) || ((ListenerUtil.mutListener.listen(12214) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(12213) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(12212) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(12211) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(12210) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.N))))))))) || ((ListenerUtil.mutListener.listen(12220) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(12219) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(12218) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(12217) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(12216) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.P) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)))))))))) {
            Intent fdIntent = new Intent(app.getApplicationContext(), FeedbackActivity.class);
            if (!ListenerUtil.mutListener.listen(12238)) {
                fdIntent.setAction(FeedbackReceiver.ACTION_REPLY);
            }
            if (!ListenerUtil.mutListener.listen(12239)) {
                fdIntent.putExtra(FeedbackActivity.RESPONSE, FeedbackActivity.FEEDBACK_NO);
            }
            if (!ListenerUtil.mutListener.listen(12240)) {
                fdIntent.putExtra(FeedbackActivity.NOTIFICATION_ID, mNavProvider.NOTIFICATION_ID + 1);
            }
            if (!ListenerUtil.mutListener.listen(12241)) {
                fdIntent.putExtra(FeedbackActivity.TRIP_ID, mTripId);
            }
            if (!ListenerUtil.mutListener.listen(12242)) {
                fdIntent.putExtra(FeedbackActivity.LOG_FILE, mLogFile.getAbsolutePath());
            }
            int flags;
            if ((ListenerUtil.mutListener.listen(12247) ? (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(12246) ? (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(12245) ? (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(12244) ? (android.os.Build.VERSION.SDK_INT != android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(12243) ? (android.os.Build.VERSION.SDK_INT == android.os.Build.VERSION_CODES.S) : (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S))))))) {
                flags = FLAG_UPDATE_CURRENT | FLAG_MUTABLE;
            } else {
                flags = FLAG_UPDATE_CURRENT;
            }
            // Pending intent used to handle feedback when user taps on 'No'
            PendingIntent fdPendingIntentNo = getActivity(app.getApplicationContext(), 1, fdIntent, flags);
            if (!ListenerUtil.mutListener.listen(12248)) {
                fdIntent = new Intent(app.getApplicationContext(), FeedbackActivity.class);
            }
            if (!ListenerUtil.mutListener.listen(12249)) {
                fdIntent.setAction(FeedbackReceiver.ACTION_REPLY);
            }
            if (!ListenerUtil.mutListener.listen(12250)) {
                fdIntent.putExtra(FeedbackActivity.RESPONSE, FeedbackActivity.FEEDBACK_YES);
            }
            if (!ListenerUtil.mutListener.listen(12251)) {
                fdIntent.putExtra(FeedbackActivity.NOTIFICATION_ID, mNavProvider.NOTIFICATION_ID + 1);
            }
            if (!ListenerUtil.mutListener.listen(12252)) {
                fdIntent.putExtra(FeedbackActivity.TRIP_ID, mTripId);
            }
            if (!ListenerUtil.mutListener.listen(12253)) {
                fdIntent.putExtra(FeedbackActivity.LOG_FILE, mLogFile.getAbsolutePath());
            }
            // Pending intent used to handle feedback when user taps on 'Yes'
            PendingIntent fdPendingIntentYes = getActivity(app.getApplicationContext(), 2, fdIntent, flags);
            if (!ListenerUtil.mutListener.listen(12254)) {
                delIntent.setAction(FeedbackReceiver.ACTION_DISMISS_FEEDBACK);
            }
            int delFlags;
            if ((ListenerUtil.mutListener.listen(12259) ? (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(12258) ? (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(12257) ? (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(12256) ? (android.os.Build.VERSION.SDK_INT != android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(12255) ? (android.os.Build.VERSION.SDK_INT == android.os.Build.VERSION_CODES.S) : (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S))))))) {
                delFlags = FLAG_MUTABLE;
            } else {
                delFlags = 0;
            }
            PendingIntent pDelIntent = getBroadcast(app.getApplicationContext(), 0, delIntent, delFlags);
            mBuilder = new NotificationCompat.Builder(Application.get().getApplicationContext(), Application.CHANNEL_DESTINATION_ALERT_ID).setSmallIcon(R.drawable.ic_stat_notification).setContentTitle(Application.get().getResources().getString(R.string.feedback_notify_title)).setContentText(message).addAction(0, Application.get().getResources().getString(R.string.feedback_action_reply_no), fdPendingIntentNo).addAction(0, Application.get().getResources().getString(R.string.feedback_action_reply_yes), fdPendingIntentYes).setDeleteIntent(pDelIntent).setAutoCancel(true);
        } else {
            // Intent to handle user feedback when a user taps on 'No'
            Intent intentNo = new Intent(Application.get().getApplicationContext(), FeedbackReceiver.class);
            if (!ListenerUtil.mutListener.listen(12222)) {
                intentNo.setAction(FeedbackReceiver.ACTION_REPLY);
            }
            if (!ListenerUtil.mutListener.listen(12223)) {
                intentNo.putExtra(FeedbackReceiver.NOTIFICATION_ID, mNavProvider.NOTIFICATION_ID + 1);
            }
            if (!ListenerUtil.mutListener.listen(12224)) {
                intentNo.putExtra(FeedbackReceiver.TRIP_ID, mTripId);
            }
            if (!ListenerUtil.mutListener.listen(12225)) {
                intentNo.putExtra(FeedbackReceiver.RESPONSE, FeedbackReceiver.FEEDBACK_NO);
            }
            if (!ListenerUtil.mutListener.listen(12226)) {
                intentNo.putExtra(FeedbackReceiver.LOG_FILE, mLogFile.getAbsolutePath());
            }
            int flags;
            if ((ListenerUtil.mutListener.listen(12231) ? (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(12230) ? (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(12229) ? (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(12228) ? (android.os.Build.VERSION.SDK_INT != android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(12227) ? (android.os.Build.VERSION.SDK_INT == android.os.Build.VERSION_CODES.S) : (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S))))))) {
                flags = FLAG_MUTABLE;
            } else {
                flags = 0;
            }
            // PendingIntent to handle user feedback when a user taps on 'No'
            PendingIntent fdPendingIntentNo = getBroadcast(Application.get().getApplicationContext(), 100, intentNo, flags);
            String replyLabelNo = Application.get().getResources().getString(R.string.feedback_action_reply_no);
            RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY).setLabel(replyLabelNo).build();
            NotificationCompat.Action replyActionNo = new NotificationCompat.Action.Builder(0, replyLabelNo, fdPendingIntentNo).addRemoteInput(remoteInput).build();
            // Intent to handle user feedback when a user taps on 'Yes'
            Intent intentYes = new Intent(Application.get().getApplicationContext(), FeedbackReceiver.class);
            if (!ListenerUtil.mutListener.listen(12232)) {
                intentYes.setAction(FeedbackReceiver.ACTION_REPLY);
            }
            if (!ListenerUtil.mutListener.listen(12233)) {
                intentYes.putExtra(FeedbackReceiver.NOTIFICATION_ID, mNavProvider.NOTIFICATION_ID + 1);
            }
            if (!ListenerUtil.mutListener.listen(12234)) {
                intentYes.putExtra(FeedbackReceiver.TRIP_ID, mTripId);
            }
            if (!ListenerUtil.mutListener.listen(12235)) {
                intentYes.putExtra(FeedbackReceiver.RESPONSE, FeedbackReceiver.FEEDBACK_YES);
            }
            if (!ListenerUtil.mutListener.listen(12236)) {
                intentYes.putExtra(FeedbackReceiver.LOG_FILE, mLogFile.getAbsolutePath());
            }
            // PendingIntent to handle user feedback when a user taps on 'No'
            PendingIntent fdPendingIntentYes = getBroadcast(Application.get().getApplicationContext(), 101, intentYes, flags);
            String replyLabelYes = Application.get().getResources().getString(R.string.feedback_action_reply_yes);
            RemoteInput remoteInput1 = new RemoteInput.Builder(KEY_TEXT_REPLY).setLabel(replyLabelYes).build();
            NotificationCompat.Action replyActionYes = new NotificationCompat.Action.Builder(0, replyLabelYes, fdPendingIntentYes).addRemoteInput(remoteInput1).build();
            if (!ListenerUtil.mutListener.listen(12237)) {
                delIntent.setAction(FeedbackReceiver.ACTION_DISMISS_FEEDBACK);
            }
            PendingIntent pDelIntent = getBroadcast(app.getApplicationContext(), 0, delIntent, flags);
            mBuilder = new NotificationCompat.Builder(Application.get().getApplicationContext(), Application.CHANNEL_DESTINATION_ALERT_ID).setSmallIcon(R.drawable.ic_stat_notification).setContentTitle(Application.get().getResources().getString(R.string.feedback_notify_title)).setContentText(message).addAction(replyActionNo).addAction(replyActionYes).setDeleteIntent(pDelIntent).setAutoCancel(true);
        }
        if (!ListenerUtil.mutListener.listen(12260)) {
            mBuilder.setOngoing(false);
        }
        NotificationManager mNotificationManager = (NotificationManager) Application.get().getSystemService(Context.NOTIFICATION_SERVICE);
        if (!ListenerUtil.mutListener.listen(12261)) {
            mNotificationManager.notify(mNavProvider.NOTIFICATION_ID + 1, mBuilder.build());
        }
    }

    private void setupLogCleanupTask() {
        PeriodicWorkRequest.Builder cleanupLogsBuilder = new PeriodicWorkRequest.Builder(NavigationCleanupWorker.class, 24, TimeUnit.HOURS);
        // Create the actual work object:
        PeriodicWorkRequest cleanUpCheckWork = cleanupLogsBuilder.build();
        if (!ListenerUtil.mutListener.listen(12262)) {
            // Then enqueue the recurring task:
            WorkManager.getInstance().enqueue(cleanUpCheckWork);
        }
    }
}
