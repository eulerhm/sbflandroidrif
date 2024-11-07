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
import com.google.firebase.analytics.FirebaseAnalytics;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.ObaAnalytics;
import org.onebusaway.android.nav.model.Path;
import org.onebusaway.android.nav.model.PathLink;
import org.onebusaway.android.ui.TripDetailsActivity;
import org.onebusaway.android.util.RegionUtils;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import java.text.DecimalFormat;
import java.util.Locale;
import androidx.core.app.NotificationCompat;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * This class provides the navigation functionality for the destination reminders
 */
public class NavigationServiceProvider implements TextToSpeech.OnInitListener {

    public static final String TAG = "NavServiceProvider";

    private static final int EVENT_TYPE_NO_EVENT = 0;

    private static final int EVENT_TYPE_UPDATE_DISTANCE = 1;

    private static final int EVENT_TYPE_GET_READY = 2;

    private static final int EVENT_TYPE_PULL_CORD = 3;

    private static final int EVENT_TYPE_INITIAL_STARTUP = 4;

    private static final int ALERT_STATE_NONE = -1;

    private static final int ALERT_STATE_SHOWN_TO_RIDER = 0;

    private static final int ALERT_STATE_ENDING_PATH_LINK = 1;

    public static final int NOTIFICATION_ID = 33620;

    private static final long[] VIBRATION_PATTERN = new long[] { 2000, 1000, 2000, 1000, 2000, 1000, 2000, 1000, 2000, 1000 };

    public static final int DISTANCE_THRESHOLD = 200;

    // Number of times to repeat voice commands
    private static final int NUM_PULL_CORD_REPEAT = 10;

    private static final int NUM_GET_READY_REPEAT = 2;

    private ProximityCalculator mProxCalculator;

    // Timeout value for service provider action (default = 60 seconds);
    private int mTimeout = 60;

    // Index that defines the current path link within the path (i.e. First link in a path will have index = 0, second link index = 1, etc.)
    private int mPathLinkIndex = 0;

    // Path links being navigated
    private Path mPath;

    private float mAlertDistance = -1;

    private boolean mWaitingForConfirm = false;

    private Location mCurrentLocation = null;

    // Is Trip being resumed?
    private boolean mResuming = false;

    // Trip has finished.  //Change to public
    public boolean mFinished = false;

    // Get Ready triggered. //Change to public
    public boolean mGetReady = false;

    public float mSectoCurDistance = -1;

    // TextToSpeech for speaking commands.
    public static TextToSpeech mTTS;

    // Shared Prefs
    SharedPreferences mSettings = Application.getPrefs();

    // Trip ID
    private String mTripId;

    // Stop ID
    private String mStopId;

    private FirebaseAnalytics mFirebaseAnalytics;

    public NavigationServiceProvider(String tripId, String stopId) {
        if (!ListenerUtil.mutListener.listen(12263)) {
            Log.d(TAG, "Creating NavigationServiceProvider...");
        }
        if (!ListenerUtil.mutListener.listen(12264)) {
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(Application.get().getApplicationContext());
        }
        if (!ListenerUtil.mutListener.listen(12266)) {
            if (mTTS == null) {
                if (!ListenerUtil.mutListener.listen(12265)) {
                    mTTS = new TextToSpeech(Application.get().getApplicationContext(), this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12267)) {
            mTripId = tripId;
        }
        if (!ListenerUtil.mutListener.listen(12268)) {
            mStopId = stopId;
        }
    }

    public NavigationServiceProvider(String tripId, String stopId, int flag) {
        if (!ListenerUtil.mutListener.listen(12269)) {
            Log.d(TAG, "Creating NavigationServiceProvider...");
        }
        if (!ListenerUtil.mutListener.listen(12270)) {
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(Application.get().getApplicationContext());
        }
        if (!ListenerUtil.mutListener.listen(12276)) {
            mResuming = (ListenerUtil.mutListener.listen(12275) ? (flag >= 1) : (ListenerUtil.mutListener.listen(12274) ? (flag <= 1) : (ListenerUtil.mutListener.listen(12273) ? (flag > 1) : (ListenerUtil.mutListener.listen(12272) ? (flag < 1) : (ListenerUtil.mutListener.listen(12271) ? (flag != 1) : (flag == 1))))));
        }
        if (!ListenerUtil.mutListener.listen(12278)) {
            if (mTTS == null) {
                if (!ListenerUtil.mutListener.listen(12277)) {
                    mTTS = new TextToSpeech(Application.get().getApplicationContext(), this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12279)) {
            mTripId = tripId;
        }
        if (!ListenerUtil.mutListener.listen(12280)) {
            mStopId = stopId;
        }
    }

    /**
     * Initialize ProximityCalculator
     * Proximity listener will be created only upon selection of service to navigate
     */
    private void lazyProxInitialization() {
        if (!ListenerUtil.mutListener.listen(12281)) {
            Log.d(TAG, "ProximityCalculator initializing...");
        }
        if (!ListenerUtil.mutListener.listen(12282)) {
            mProxCalculator = null;
        }
        if (!ListenerUtil.mutListener.listen(12283)) {
            mProxCalculator = new ProximityCalculator(this);
        }
    }

    /**
     * Returns true if user has been notified to get ready.
     */
    public boolean getGetReady() {
        return mGetReady;
    }

    /**
     * Returns true if trip is done.
     */
    public boolean getFinished() {
        return mFinished;
    }

    /**
     * Returns the index of the current path link
     */
    public int getPathLinkIndex() {
        return mPathLinkIndex;
    }

    /**
     * Navigates a navigation path which is composed of path links
     */
    public void navigate(Path path) {
        if (!ListenerUtil.mutListener.listen(12284)) {
            Log.d(TAG, "Starting navigation for service");
        }
        if (!ListenerUtil.mutListener.listen(12285)) {
            // Create a new instance and rewrite the old one with a blank slate of ProximityListener
            lazyProxInitialization();
        }
        if (!ListenerUtil.mutListener.listen(12286)) {
            mPath = path;
        }
        if (!ListenerUtil.mutListener.listen(12287)) {
            mPathLinkIndex = 0;
        }
        if (!ListenerUtil.mutListener.listen(12288)) {
            Log.d(TAG, "Number of path links: " + mPath.getPathLinks().size());
        }
        // Create new coordinate object using the "Ring" coordinates
        Location firstLocation = mPath.getPathLinks().get(mPathLinkIndex).getOriginLocation();
        Location secondToLastLocation = mPath.getPathLinks().get(mPathLinkIndex).getSecondToLastLocation();
        Location lastLocation = mPath.getPathLinks().get(mPathLinkIndex).getDestinationLocation();
        if (!ListenerUtil.mutListener.listen(12289)) {
            mAlertDistance = mPath.getPathLinks().get(mPathLinkIndex).getAlertDistance();
        }
        if (!ListenerUtil.mutListener.listen(12290)) {
            // Have proximity listener listen for the "Ring" location
            mProxCalculator.listenForDistance(mAlertDistance);
        }
        if (!ListenerUtil.mutListener.listen(12291)) {
            mProxCalculator.listenForLocation(firstLocation, secondToLastLocation, lastLocation);
        }
        if (!ListenerUtil.mutListener.listen(12292)) {
            mProxCalculator.mReady = false;
        }
        if (!ListenerUtil.mutListener.listen(12293)) {
            mProxCalculator.mTrigger = false;
        }
    }

    /**
     * Resets any current routes which might be currently navigated
     */
    public void reset() {
        if (!ListenerUtil.mutListener.listen(12294)) {
            mProxCalculator.listenForLocation(null, null, null);
        }
    }

    public void setTimeout(int timeout) {
        if (!ListenerUtil.mutListener.listen(12295)) {
            mTimeout = timeout;
        }
    }

    public int getTimeout() {
        return mTimeout;
    }

    /**
     * Sets the radius of detection for the ProximityListener
     */
    public void setRadius(float radius) {
        if (!ListenerUtil.mutListener.listen(12296)) {
            mProxCalculator.setRadius(radius);
        }
    }

    /**
     * Returns true if there is another path link to be navigated as part of the current path that is being navigated, false if there is not another link
     *
     * @return true if there is another path link to be navigated as part of the current path that is being navigated, false if there is not another link
     */
    public boolean hasMorePathLinks() {
        if (!ListenerUtil.mutListener.listen(12297)) {
            Log.d(TAG, "Checking if path has more path links left to be navigated");
        }
        if (!ListenerUtil.mutListener.listen(12310)) {
            if ((ListenerUtil.mutListener.listen(12307) ? (mPath == null && ((ListenerUtil.mutListener.listen(12306) ? (mPathLinkIndex <= ((ListenerUtil.mutListener.listen(12301) ? (mPath.getPathLinks().size() % 1) : (ListenerUtil.mutListener.listen(12300) ? (mPath.getPathLinks().size() / 1) : (ListenerUtil.mutListener.listen(12299) ? (mPath.getPathLinks().size() * 1) : (ListenerUtil.mutListener.listen(12298) ? (mPath.getPathLinks().size() + 1) : (mPath.getPathLinks().size() - 1))))))) : (ListenerUtil.mutListener.listen(12305) ? (mPathLinkIndex > ((ListenerUtil.mutListener.listen(12301) ? (mPath.getPathLinks().size() % 1) : (ListenerUtil.mutListener.listen(12300) ? (mPath.getPathLinks().size() / 1) : (ListenerUtil.mutListener.listen(12299) ? (mPath.getPathLinks().size() * 1) : (ListenerUtil.mutListener.listen(12298) ? (mPath.getPathLinks().size() + 1) : (mPath.getPathLinks().size() - 1))))))) : (ListenerUtil.mutListener.listen(12304) ? (mPathLinkIndex < ((ListenerUtil.mutListener.listen(12301) ? (mPath.getPathLinks().size() % 1) : (ListenerUtil.mutListener.listen(12300) ? (mPath.getPathLinks().size() / 1) : (ListenerUtil.mutListener.listen(12299) ? (mPath.getPathLinks().size() * 1) : (ListenerUtil.mutListener.listen(12298) ? (mPath.getPathLinks().size() + 1) : (mPath.getPathLinks().size() - 1))))))) : (ListenerUtil.mutListener.listen(12303) ? (mPathLinkIndex != ((ListenerUtil.mutListener.listen(12301) ? (mPath.getPathLinks().size() % 1) : (ListenerUtil.mutListener.listen(12300) ? (mPath.getPathLinks().size() / 1) : (ListenerUtil.mutListener.listen(12299) ? (mPath.getPathLinks().size() * 1) : (ListenerUtil.mutListener.listen(12298) ? (mPath.getPathLinks().size() + 1) : (mPath.getPathLinks().size() - 1))))))) : (ListenerUtil.mutListener.listen(12302) ? (mPathLinkIndex == ((ListenerUtil.mutListener.listen(12301) ? (mPath.getPathLinks().size() % 1) : (ListenerUtil.mutListener.listen(12300) ? (mPath.getPathLinks().size() / 1) : (ListenerUtil.mutListener.listen(12299) ? (mPath.getPathLinks().size() * 1) : (ListenerUtil.mutListener.listen(12298) ? (mPath.getPathLinks().size() + 1) : (mPath.getPathLinks().size() - 1))))))) : (mPathLinkIndex >= ((ListenerUtil.mutListener.listen(12301) ? (mPath.getPathLinks().size() % 1) : (ListenerUtil.mutListener.listen(12300) ? (mPath.getPathLinks().size() / 1) : (ListenerUtil.mutListener.listen(12299) ? (mPath.getPathLinks().size() * 1) : (ListenerUtil.mutListener.listen(12298) ? (mPath.getPathLinks().size() + 1) : (mPath.getPathLinks().size() - 1)))))))))))))) : (mPath == null || ((ListenerUtil.mutListener.listen(12306) ? (mPathLinkIndex <= ((ListenerUtil.mutListener.listen(12301) ? (mPath.getPathLinks().size() % 1) : (ListenerUtil.mutListener.listen(12300) ? (mPath.getPathLinks().size() / 1) : (ListenerUtil.mutListener.listen(12299) ? (mPath.getPathLinks().size() * 1) : (ListenerUtil.mutListener.listen(12298) ? (mPath.getPathLinks().size() + 1) : (mPath.getPathLinks().size() - 1))))))) : (ListenerUtil.mutListener.listen(12305) ? (mPathLinkIndex > ((ListenerUtil.mutListener.listen(12301) ? (mPath.getPathLinks().size() % 1) : (ListenerUtil.mutListener.listen(12300) ? (mPath.getPathLinks().size() / 1) : (ListenerUtil.mutListener.listen(12299) ? (mPath.getPathLinks().size() * 1) : (ListenerUtil.mutListener.listen(12298) ? (mPath.getPathLinks().size() + 1) : (mPath.getPathLinks().size() - 1))))))) : (ListenerUtil.mutListener.listen(12304) ? (mPathLinkIndex < ((ListenerUtil.mutListener.listen(12301) ? (mPath.getPathLinks().size() % 1) : (ListenerUtil.mutListener.listen(12300) ? (mPath.getPathLinks().size() / 1) : (ListenerUtil.mutListener.listen(12299) ? (mPath.getPathLinks().size() * 1) : (ListenerUtil.mutListener.listen(12298) ? (mPath.getPathLinks().size() + 1) : (mPath.getPathLinks().size() - 1))))))) : (ListenerUtil.mutListener.listen(12303) ? (mPathLinkIndex != ((ListenerUtil.mutListener.listen(12301) ? (mPath.getPathLinks().size() % 1) : (ListenerUtil.mutListener.listen(12300) ? (mPath.getPathLinks().size() / 1) : (ListenerUtil.mutListener.listen(12299) ? (mPath.getPathLinks().size() * 1) : (ListenerUtil.mutListener.listen(12298) ? (mPath.getPathLinks().size() + 1) : (mPath.getPathLinks().size() - 1))))))) : (ListenerUtil.mutListener.listen(12302) ? (mPathLinkIndex == ((ListenerUtil.mutListener.listen(12301) ? (mPath.getPathLinks().size() % 1) : (ListenerUtil.mutListener.listen(12300) ? (mPath.getPathLinks().size() / 1) : (ListenerUtil.mutListener.listen(12299) ? (mPath.getPathLinks().size() * 1) : (ListenerUtil.mutListener.listen(12298) ? (mPath.getPathLinks().size() + 1) : (mPath.getPathLinks().size() - 1))))))) : (mPathLinkIndex >= ((ListenerUtil.mutListener.listen(12301) ? (mPath.getPathLinks().size() % 1) : (ListenerUtil.mutListener.listen(12300) ? (mPath.getPathLinks().size() / 1) : (ListenerUtil.mutListener.listen(12299) ? (mPath.getPathLinks().size() * 1) : (ListenerUtil.mutListener.listen(12298) ? (mPath.getPathLinks().size() + 1) : (mPath.getPathLinks().size() - 1)))))))))))))))) {
                if (!ListenerUtil.mutListener.listen(12308)) {
                    // No more path links exist
                    Log.d(TAG, "PathLink index: " + mPathLinkIndex + " Number of PathLinks: " + (mPath.getPathLinks().size()));
                }
                if (!ListenerUtil.mutListener.listen(12309)) {
                    Log.d(TAG, "%%%%%%N%%%%%%%%% No more PathLinks left in Path %%%%%%%%%%%%%%%%%%%%%");
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(12311)) {
            // Additional path links still need to be navigated as part of this service
            Log.d(TAG, "More path links left");
        }
        return true;
    }

    /**
     * Tells the NavigationProvider to navigate the next PathLink in the Path
     */
    private void navigateNextPathLink() {
        if (!ListenerUtil.mutListener.listen(12312)) {
            Log.d(TAG, "Attempting to navigate next path link");
        }
        if (!ListenerUtil.mutListener.listen(12324)) {
            if ((ListenerUtil.mutListener.listen(12318) ? (mPath != null || (ListenerUtil.mutListener.listen(12317) ? (mPathLinkIndex >= mPath.getPathLinks().size()) : (ListenerUtil.mutListener.listen(12316) ? (mPathLinkIndex <= mPath.getPathLinks().size()) : (ListenerUtil.mutListener.listen(12315) ? (mPathLinkIndex > mPath.getPathLinks().size()) : (ListenerUtil.mutListener.listen(12314) ? (mPathLinkIndex != mPath.getPathLinks().size()) : (ListenerUtil.mutListener.listen(12313) ? (mPathLinkIndex == mPath.getPathLinks().size()) : (mPathLinkIndex < mPath.getPathLinks().size()))))))) : (mPath != null && (ListenerUtil.mutListener.listen(12317) ? (mPathLinkIndex >= mPath.getPathLinks().size()) : (ListenerUtil.mutListener.listen(12316) ? (mPathLinkIndex <= mPath.getPathLinks().size()) : (ListenerUtil.mutListener.listen(12315) ? (mPathLinkIndex > mPath.getPathLinks().size()) : (ListenerUtil.mutListener.listen(12314) ? (mPathLinkIndex != mPath.getPathLinks().size()) : (ListenerUtil.mutListener.listen(12313) ? (mPathLinkIndex == mPath.getPathLinks().size()) : (mPathLinkIndex < mPath.getPathLinks().size()))))))))) {
                if (!ListenerUtil.mutListener.listen(12319)) {
                    mPathLinkIndex++;
                }
                // Create new location using the "Ring" coordinates
                PathLink link = mPath.getPathLinks().get(mPathLinkIndex);
                if (!ListenerUtil.mutListener.listen(12320)) {
                    mAlertDistance = link.getAlertDistance();
                }
                if (!ListenerUtil.mutListener.listen(12321)) {
                    // Have proximity listener listen for the "Ring" location
                    mProxCalculator.listenForDistance(mAlertDistance);
                }
                if (!ListenerUtil.mutListener.listen(12322)) {
                    mProxCalculator.listenForLocation(link.getOriginLocation(), link.getSecondToLastLocation(), link.getDestinationLocation());
                }
                if (!ListenerUtil.mutListener.listen(12323)) {
                    Log.d(TAG, "ProxCalculator parameters were set!");
                }
            }
        }
    }

    /**
     * Called from LocationListener.locationUpdated() in order to supply the Navigation Provider with the most recent location
     */
    public void locationUpdated(Location l) {
        if (!ListenerUtil.mutListener.listen(12325)) {
            mCurrentLocation = l;
        }
        if (!ListenerUtil.mutListener.listen(12326)) {
            mProxCalculator.checkProximityAll(mCurrentLocation);
        }
    }

    private int sendCounter = 0;

    public void skipPathLink() {
        if (!ListenerUtil.mutListener.listen(12331)) {
            if (hasMorePathLinks()) {
                if (!ListenerUtil.mutListener.listen(12328)) {
                    Log.d(TAG, "About to switch link - from skipPathLink");
                }
                if (!ListenerUtil.mutListener.listen(12329)) {
                    navigateNextPathLink();
                }
                if (!ListenerUtil.mutListener.listen(12330)) {
                    // Reset the "get ready" notification alert
                    mProxCalculator.setReady(false);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(12327)) {
                    Log.d(TAG, "No more path links!");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12332)) {
            // Reset the proximity notification alert
            mProxCalculator.setTrigger(false);
        }
    }

    /**
     * Detects proximity to a latitude and longitude to help navigate a path link
     */
    public class ProximityCalculator {

        NavigationServiceProvider mNavProvider;

        private float mRadius = 100;

        private float readyRadius = 300;

        private boolean mTrigger = false;

        private Location secondToLastCoords = null;

        // Coordinates of the final bus stop of the link
        private Location lastCoords = null;

        // Coordinates of the first bus stop of the link
        private Location firstCoords = null;

        // Actual known traveled distance loaded from link object
        private float mDistance = -1;

        private float directDistance = -1;

        private float endDistance = -1;

        // Has get ready alert been played?
        private boolean mReady = false;

        private boolean m100_a, m50_a, m20_a, m20_d, m50_d, m100_d = false;

        ProximityCalculator(NavigationServiceProvider navProvider) {
            if (!ListenerUtil.mutListener.listen(12333)) {
                mNavProvider = navProvider;
            }
            if (!ListenerUtil.mutListener.listen(12334)) {
                Log.d(TAG, "Initializing ProximityCalculator");
            }
        }

        /**
         * Getter method for radius value of ProximityListener
         */
        public float getRadius() {
            return mRadius;
        }

        /**
         * Setter method for radius value of ProximityListener
         */
        public void setRadius(float radius) {
            if (!ListenerUtil.mutListener.listen(12335)) {
                mRadius = radius;
            }
        }

        /**
         * ProximityListener Functions
         */
        public void monitoringStateChanged(boolean value) {
            if (!ListenerUtil.mutListener.listen(12336)) {
                // Fired when the monitoring of the ProximityListener state changes (is or is NOT active)
                Log.d(TAG, "Fired ProximityListener.monitoringStateChanged()...");
            }
        }

        /**
         * Resets triggers for proximityEvent
         */
        public void setTrigger(boolean t) {
            if (!ListenerUtil.mutListener.listen(12337)) {
                mTrigger = mReady = t;
            }
        }

        /**
         * Updates the state of the navigation provider based on the provided eventType
         *
         * @param eventType  EVENT_TYPE_PULL_CORD for "Pull the cord now" event, or EVENT_TYPE_GET_READY for getting ready to exit the vehicle
         * @param alertState ALERT_STATE_* variable is responsible for differentiating the switch of path link and alert being played.
         */
        boolean proximityEvent(int eventType, int alertState) {
            if (!ListenerUtil.mutListener.listen(12391)) {
                if ((ListenerUtil.mutListener.listen(12342) ? (eventType >= EVENT_TYPE_PULL_CORD) : (ListenerUtil.mutListener.listen(12341) ? (eventType <= EVENT_TYPE_PULL_CORD) : (ListenerUtil.mutListener.listen(12340) ? (eventType > EVENT_TYPE_PULL_CORD) : (ListenerUtil.mutListener.listen(12339) ? (eventType < EVENT_TYPE_PULL_CORD) : (ListenerUtil.mutListener.listen(12338) ? (eventType != EVENT_TYPE_PULL_CORD) : (eventType == EVENT_TYPE_PULL_CORD))))))) {
                    if (!ListenerUtil.mutListener.listen(12390)) {
                        if (!mTrigger) {
                            if (!ListenerUtil.mutListener.listen(12350)) {
                                mTrigger = true;
                            }
                            if (!ListenerUtil.mutListener.listen(12351)) {
                                Log.d(TAG, "Proximity Event fired");
                            }
                            if (!ListenerUtil.mutListener.listen(12389)) {
                                if (mNavProvider.hasMorePathLinks()) {
                                    if (!ListenerUtil.mutListener.listen(12378)) {
                                        if ((ListenerUtil.mutListener.listen(12375) ? (alertState >= ALERT_STATE_SHOWN_TO_RIDER) : (ListenerUtil.mutListener.listen(12374) ? (alertState <= ALERT_STATE_SHOWN_TO_RIDER) : (ListenerUtil.mutListener.listen(12373) ? (alertState > ALERT_STATE_SHOWN_TO_RIDER) : (ListenerUtil.mutListener.listen(12372) ? (alertState < ALERT_STATE_SHOWN_TO_RIDER) : (ListenerUtil.mutListener.listen(12371) ? (alertState != ALERT_STATE_SHOWN_TO_RIDER) : (alertState == ALERT_STATE_SHOWN_TO_RIDER))))))) {
                                            if (!ListenerUtil.mutListener.listen(12376)) {
                                                Log.d(TAG, "Alert 1 Screen showed to rider");
                                            }
                                            if (!ListenerUtil.mutListener.listen(12377)) {
                                                mWaitingForConfirm = true;
                                            }
                                            // this.mNavProvider.navlistener.waypointReached(this.lastCoords);
                                            return true;
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(12388)) {
                                        if ((ListenerUtil.mutListener.listen(12383) ? (alertState >= ALERT_STATE_ENDING_PATH_LINK) : (ListenerUtil.mutListener.listen(12382) ? (alertState <= ALERT_STATE_ENDING_PATH_LINK) : (ListenerUtil.mutListener.listen(12381) ? (alertState > ALERT_STATE_ENDING_PATH_LINK) : (ListenerUtil.mutListener.listen(12380) ? (alertState < ALERT_STATE_ENDING_PATH_LINK) : (ListenerUtil.mutListener.listen(12379) ? (alertState != ALERT_STATE_ENDING_PATH_LINK) : (alertState == ALERT_STATE_ENDING_PATH_LINK))))))) {
                                            if (!ListenerUtil.mutListener.listen(12384)) {
                                                Log.d(TAG, "About to switch path links - from Proximity Event");
                                            }
                                            if (!ListenerUtil.mutListener.listen(12385)) {
                                                mNavProvider.navigateNextPathLink();
                                            }
                                            if (!ListenerUtil.mutListener.listen(12386)) {
                                                // Reset notification alerts
                                                mReady = false;
                                            }
                                            if (!ListenerUtil.mutListener.listen(12387)) {
                                                mTrigger = false;
                                            }
                                        }
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(12352)) {
                                        Log.d(TAG, "Got to last stop");
                                    }
                                    if (!ListenerUtil.mutListener.listen(12361)) {
                                        if ((ListenerUtil.mutListener.listen(12357) ? (alertState >= ALERT_STATE_SHOWN_TO_RIDER) : (ListenerUtil.mutListener.listen(12356) ? (alertState <= ALERT_STATE_SHOWN_TO_RIDER) : (ListenerUtil.mutListener.listen(12355) ? (alertState > ALERT_STATE_SHOWN_TO_RIDER) : (ListenerUtil.mutListener.listen(12354) ? (alertState < ALERT_STATE_SHOWN_TO_RIDER) : (ListenerUtil.mutListener.listen(12353) ? (alertState != ALERT_STATE_SHOWN_TO_RIDER) : (alertState == ALERT_STATE_SHOWN_TO_RIDER))))))) {
                                            if (!ListenerUtil.mutListener.listen(12358)) {
                                                Log.d(TAG, "Alert 1 screen before last stop");
                                            }
                                            if (!ListenerUtil.mutListener.listen(12359)) {
                                                mWaitingForConfirm = true;
                                            }
                                            if (!ListenerUtil.mutListener.listen(12360)) {
                                                Log.d(TAG, "Calling destination reached...");
                                            }
                                            return true;
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(12370)) {
                                        if ((ListenerUtil.mutListener.listen(12366) ? (alertState >= ALERT_STATE_ENDING_PATH_LINK) : (ListenerUtil.mutListener.listen(12365) ? (alertState <= ALERT_STATE_ENDING_PATH_LINK) : (ListenerUtil.mutListener.listen(12364) ? (alertState > ALERT_STATE_ENDING_PATH_LINK) : (ListenerUtil.mutListener.listen(12363) ? (alertState < ALERT_STATE_ENDING_PATH_LINK) : (ListenerUtil.mutListener.listen(12362) ? (alertState != ALERT_STATE_ENDING_PATH_LINK) : (alertState == ALERT_STATE_ENDING_PATH_LINK))))))) {
                                            if (!ListenerUtil.mutListener.listen(12367)) {
                                                Log.d(TAG, "Ending navigation");
                                            }
                                            if (!ListenerUtil.mutListener.listen(12368)) {
                                                mNavProvider.mPath = null;
                                            }
                                            if (!ListenerUtil.mutListener.listen(12369)) {
                                                mNavProvider.mPathLinkIndex = 0;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if ((ListenerUtil.mutListener.listen(12347) ? (eventType >= EVENT_TYPE_GET_READY) : (ListenerUtil.mutListener.listen(12346) ? (eventType <= EVENT_TYPE_GET_READY) : (ListenerUtil.mutListener.listen(12345) ? (eventType > EVENT_TYPE_GET_READY) : (ListenerUtil.mutListener.listen(12344) ? (eventType < EVENT_TYPE_GET_READY) : (ListenerUtil.mutListener.listen(12343) ? (eventType != EVENT_TYPE_GET_READY) : (eventType == EVENT_TYPE_GET_READY))))))) {
                    if (!ListenerUtil.mutListener.listen(12349)) {
                        if (!mReady) {
                            if (!ListenerUtil.mutListener.listen(12348)) {
                                mReady = true;
                            }
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        /**
         * Test function used to register a location to detect proximity to
         */
        void listenForLocation(Location first, Location secondToLast, Location last) {
            if (!ListenerUtil.mutListener.listen(12392)) {
                firstCoords = first;
            }
            if (!ListenerUtil.mutListener.listen(12393)) {
                secondToLastCoords = secondToLast;
            }
            if (!ListenerUtil.mutListener.listen(12394)) {
                lastCoords = last;
            }
            if (!ListenerUtil.mutListener.listen(12396)) {
                // Reset distance if the manual listener is reset
                if (secondToLast == null) {
                    if (!ListenerUtil.mutListener.listen(12395)) {
                        directDistance = -1;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(12398)) {
                if (last == null) {
                    if (!ListenerUtil.mutListener.listen(12397)) {
                        endDistance = -1;
                    }
                }
            }
        }

        /**
         * Sets the "known" distance for the path link
         */
        void listenForDistance(float d) {
            if (!ListenerUtil.mutListener.listen(12399)) {
                mDistance = d;
            }
        }

        /**
         * Fire proximity event to switch path link or go back to service menu
         * when the final stop of the path link or path is reached
         * stop_type = 0; -> final stop detection
         * stop_type = 1; -> second to last stop detection
         * speed = current speed of the bus;
         */
        boolean StopDetector(float distance_d, int stop_type, float speed) {
            /* TODO: This comment was comented to avoid path link switching when the rider is 20 meters away from the bus stop
            if ((distance_d < 20) && (distance_d != -1) && stop_type == 0) {
                Log.d(TAG,"About to fire Proximity Event from Last Stop Detected");
                this.trigger = false;
                this.proximityEvent(0, 1);
                return true;

            } else */
            float lastToSecDistance = lastCoords.distanceTo(secondToLastCoords);
            if (!ListenerUtil.mutListener.listen(12400)) {
                Log.d(TAG, "Detecting stop. distance_d=" + distance_d + ". stop_type=" + stop_type + " speed=" + speed);
            }
            if (!ListenerUtil.mutListener.listen(12527)) {
                if ((ListenerUtil.mutListener.listen(12405) ? (stop_type >= 1) : (ListenerUtil.mutListener.listen(12404) ? (stop_type <= 1) : (ListenerUtil.mutListener.listen(12403) ? (stop_type > 1) : (ListenerUtil.mutListener.listen(12402) ? (stop_type < 1) : (ListenerUtil.mutListener.listen(12401) ? (stop_type != 1) : (stop_type == 1))))))) {
                    if (!ListenerUtil.mutListener.listen(12426)) {
                        /* Check if the bus is on the second to last stop */
                        if ((ListenerUtil.mutListener.listen(12423) ? ((ListenerUtil.mutListener.listen(12422) ? ((ListenerUtil.mutListener.listen(12416) ? (((ListenerUtil.mutListener.listen(12410) ? (distance_d >= 50) : (ListenerUtil.mutListener.listen(12409) ? (distance_d <= 50) : (ListenerUtil.mutListener.listen(12408) ? (distance_d < 50) : (ListenerUtil.mutListener.listen(12407) ? (distance_d != 50) : (ListenerUtil.mutListener.listen(12406) ? (distance_d == 50) : (distance_d > 50))))))) || ((ListenerUtil.mutListener.listen(12415) ? (distance_d >= 100) : (ListenerUtil.mutListener.listen(12414) ? (distance_d <= 100) : (ListenerUtil.mutListener.listen(12413) ? (distance_d > 100) : (ListenerUtil.mutListener.listen(12412) ? (distance_d != 100) : (ListenerUtil.mutListener.listen(12411) ? (distance_d == 100) : (distance_d < 100)))))))) : (((ListenerUtil.mutListener.listen(12410) ? (distance_d >= 50) : (ListenerUtil.mutListener.listen(12409) ? (distance_d <= 50) : (ListenerUtil.mutListener.listen(12408) ? (distance_d < 50) : (ListenerUtil.mutListener.listen(12407) ? (distance_d != 50) : (ListenerUtil.mutListener.listen(12406) ? (distance_d == 50) : (distance_d > 50))))))) && ((ListenerUtil.mutListener.listen(12415) ? (distance_d >= 100) : (ListenerUtil.mutListener.listen(12414) ? (distance_d <= 100) : (ListenerUtil.mutListener.listen(12413) ? (distance_d > 100) : (ListenerUtil.mutListener.listen(12412) ? (distance_d != 100) : (ListenerUtil.mutListener.listen(12411) ? (distance_d == 100) : (distance_d < 100))))))))) || ((ListenerUtil.mutListener.listen(12421) ? (distance_d >= -1) : (ListenerUtil.mutListener.listen(12420) ? (distance_d <= -1) : (ListenerUtil.mutListener.listen(12419) ? (distance_d > -1) : (ListenerUtil.mutListener.listen(12418) ? (distance_d < -1) : (ListenerUtil.mutListener.listen(12417) ? (distance_d == -1) : (distance_d != -1)))))))) : ((ListenerUtil.mutListener.listen(12416) ? (((ListenerUtil.mutListener.listen(12410) ? (distance_d >= 50) : (ListenerUtil.mutListener.listen(12409) ? (distance_d <= 50) : (ListenerUtil.mutListener.listen(12408) ? (distance_d < 50) : (ListenerUtil.mutListener.listen(12407) ? (distance_d != 50) : (ListenerUtil.mutListener.listen(12406) ? (distance_d == 50) : (distance_d > 50))))))) || ((ListenerUtil.mutListener.listen(12415) ? (distance_d >= 100) : (ListenerUtil.mutListener.listen(12414) ? (distance_d <= 100) : (ListenerUtil.mutListener.listen(12413) ? (distance_d > 100) : (ListenerUtil.mutListener.listen(12412) ? (distance_d != 100) : (ListenerUtil.mutListener.listen(12411) ? (distance_d == 100) : (distance_d < 100)))))))) : (((ListenerUtil.mutListener.listen(12410) ? (distance_d >= 50) : (ListenerUtil.mutListener.listen(12409) ? (distance_d <= 50) : (ListenerUtil.mutListener.listen(12408) ? (distance_d < 50) : (ListenerUtil.mutListener.listen(12407) ? (distance_d != 50) : (ListenerUtil.mutListener.listen(12406) ? (distance_d == 50) : (distance_d > 50))))))) && ((ListenerUtil.mutListener.listen(12415) ? (distance_d >= 100) : (ListenerUtil.mutListener.listen(12414) ? (distance_d <= 100) : (ListenerUtil.mutListener.listen(12413) ? (distance_d > 100) : (ListenerUtil.mutListener.listen(12412) ? (distance_d != 100) : (ListenerUtil.mutListener.listen(12411) ? (distance_d == 100) : (distance_d < 100))))))))) && ((ListenerUtil.mutListener.listen(12421) ? (distance_d >= -1) : (ListenerUtil.mutListener.listen(12420) ? (distance_d <= -1) : (ListenerUtil.mutListener.listen(12419) ? (distance_d > -1) : (ListenerUtil.mutListener.listen(12418) ? (distance_d < -1) : (ListenerUtil.mutListener.listen(12417) ? (distance_d == -1) : (distance_d != -1))))))))) || !m100_a) : ((ListenerUtil.mutListener.listen(12422) ? ((ListenerUtil.mutListener.listen(12416) ? (((ListenerUtil.mutListener.listen(12410) ? (distance_d >= 50) : (ListenerUtil.mutListener.listen(12409) ? (distance_d <= 50) : (ListenerUtil.mutListener.listen(12408) ? (distance_d < 50) : (ListenerUtil.mutListener.listen(12407) ? (distance_d != 50) : (ListenerUtil.mutListener.listen(12406) ? (distance_d == 50) : (distance_d > 50))))))) || ((ListenerUtil.mutListener.listen(12415) ? (distance_d >= 100) : (ListenerUtil.mutListener.listen(12414) ? (distance_d <= 100) : (ListenerUtil.mutListener.listen(12413) ? (distance_d > 100) : (ListenerUtil.mutListener.listen(12412) ? (distance_d != 100) : (ListenerUtil.mutListener.listen(12411) ? (distance_d == 100) : (distance_d < 100)))))))) : (((ListenerUtil.mutListener.listen(12410) ? (distance_d >= 50) : (ListenerUtil.mutListener.listen(12409) ? (distance_d <= 50) : (ListenerUtil.mutListener.listen(12408) ? (distance_d < 50) : (ListenerUtil.mutListener.listen(12407) ? (distance_d != 50) : (ListenerUtil.mutListener.listen(12406) ? (distance_d == 50) : (distance_d > 50))))))) && ((ListenerUtil.mutListener.listen(12415) ? (distance_d >= 100) : (ListenerUtil.mutListener.listen(12414) ? (distance_d <= 100) : (ListenerUtil.mutListener.listen(12413) ? (distance_d > 100) : (ListenerUtil.mutListener.listen(12412) ? (distance_d != 100) : (ListenerUtil.mutListener.listen(12411) ? (distance_d == 100) : (distance_d < 100))))))))) || ((ListenerUtil.mutListener.listen(12421) ? (distance_d >= -1) : (ListenerUtil.mutListener.listen(12420) ? (distance_d <= -1) : (ListenerUtil.mutListener.listen(12419) ? (distance_d > -1) : (ListenerUtil.mutListener.listen(12418) ? (distance_d < -1) : (ListenerUtil.mutListener.listen(12417) ? (distance_d == -1) : (distance_d != -1)))))))) : ((ListenerUtil.mutListener.listen(12416) ? (((ListenerUtil.mutListener.listen(12410) ? (distance_d >= 50) : (ListenerUtil.mutListener.listen(12409) ? (distance_d <= 50) : (ListenerUtil.mutListener.listen(12408) ? (distance_d < 50) : (ListenerUtil.mutListener.listen(12407) ? (distance_d != 50) : (ListenerUtil.mutListener.listen(12406) ? (distance_d == 50) : (distance_d > 50))))))) || ((ListenerUtil.mutListener.listen(12415) ? (distance_d >= 100) : (ListenerUtil.mutListener.listen(12414) ? (distance_d <= 100) : (ListenerUtil.mutListener.listen(12413) ? (distance_d > 100) : (ListenerUtil.mutListener.listen(12412) ? (distance_d != 100) : (ListenerUtil.mutListener.listen(12411) ? (distance_d == 100) : (distance_d < 100)))))))) : (((ListenerUtil.mutListener.listen(12410) ? (distance_d >= 50) : (ListenerUtil.mutListener.listen(12409) ? (distance_d <= 50) : (ListenerUtil.mutListener.listen(12408) ? (distance_d < 50) : (ListenerUtil.mutListener.listen(12407) ? (distance_d != 50) : (ListenerUtil.mutListener.listen(12406) ? (distance_d == 50) : (distance_d > 50))))))) && ((ListenerUtil.mutListener.listen(12415) ? (distance_d >= 100) : (ListenerUtil.mutListener.listen(12414) ? (distance_d <= 100) : (ListenerUtil.mutListener.listen(12413) ? (distance_d > 100) : (ListenerUtil.mutListener.listen(12412) ? (distance_d != 100) : (ListenerUtil.mutListener.listen(12411) ? (distance_d == 100) : (distance_d < 100))))))))) && ((ListenerUtil.mutListener.listen(12421) ? (distance_d >= -1) : (ListenerUtil.mutListener.listen(12420) ? (distance_d <= -1) : (ListenerUtil.mutListener.listen(12419) ? (distance_d > -1) : (ListenerUtil.mutListener.listen(12418) ? (distance_d < -1) : (ListenerUtil.mutListener.listen(12417) ? (distance_d == -1) : (distance_d != -1))))))))) && !m100_a))) {
                            if (!ListenerUtil.mutListener.listen(12424)) {
                                m100_a = true;
                            }
                            if (!ListenerUtil.mutListener.listen(12425)) {
                                Log.d(TAG, "Case 1: false");
                            }
                            return false;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(12447)) {
                        if ((ListenerUtil.mutListener.listen(12444) ? ((ListenerUtil.mutListener.listen(12443) ? ((ListenerUtil.mutListener.listen(12437) ? (((ListenerUtil.mutListener.listen(12431) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12430) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12429) ? (distance_d < 20) : (ListenerUtil.mutListener.listen(12428) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12427) ? (distance_d == 20) : (distance_d > 20))))))) || ((ListenerUtil.mutListener.listen(12436) ? (distance_d >= 50) : (ListenerUtil.mutListener.listen(12435) ? (distance_d <= 50) : (ListenerUtil.mutListener.listen(12434) ? (distance_d > 50) : (ListenerUtil.mutListener.listen(12433) ? (distance_d != 50) : (ListenerUtil.mutListener.listen(12432) ? (distance_d == 50) : (distance_d < 50)))))))) : (((ListenerUtil.mutListener.listen(12431) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12430) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12429) ? (distance_d < 20) : (ListenerUtil.mutListener.listen(12428) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12427) ? (distance_d == 20) : (distance_d > 20))))))) && ((ListenerUtil.mutListener.listen(12436) ? (distance_d >= 50) : (ListenerUtil.mutListener.listen(12435) ? (distance_d <= 50) : (ListenerUtil.mutListener.listen(12434) ? (distance_d > 50) : (ListenerUtil.mutListener.listen(12433) ? (distance_d != 50) : (ListenerUtil.mutListener.listen(12432) ? (distance_d == 50) : (distance_d < 50))))))))) || ((ListenerUtil.mutListener.listen(12442) ? (distance_d >= -1) : (ListenerUtil.mutListener.listen(12441) ? (distance_d <= -1) : (ListenerUtil.mutListener.listen(12440) ? (distance_d > -1) : (ListenerUtil.mutListener.listen(12439) ? (distance_d < -1) : (ListenerUtil.mutListener.listen(12438) ? (distance_d == -1) : (distance_d != -1)))))))) : ((ListenerUtil.mutListener.listen(12437) ? (((ListenerUtil.mutListener.listen(12431) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12430) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12429) ? (distance_d < 20) : (ListenerUtil.mutListener.listen(12428) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12427) ? (distance_d == 20) : (distance_d > 20))))))) || ((ListenerUtil.mutListener.listen(12436) ? (distance_d >= 50) : (ListenerUtil.mutListener.listen(12435) ? (distance_d <= 50) : (ListenerUtil.mutListener.listen(12434) ? (distance_d > 50) : (ListenerUtil.mutListener.listen(12433) ? (distance_d != 50) : (ListenerUtil.mutListener.listen(12432) ? (distance_d == 50) : (distance_d < 50)))))))) : (((ListenerUtil.mutListener.listen(12431) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12430) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12429) ? (distance_d < 20) : (ListenerUtil.mutListener.listen(12428) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12427) ? (distance_d == 20) : (distance_d > 20))))))) && ((ListenerUtil.mutListener.listen(12436) ? (distance_d >= 50) : (ListenerUtil.mutListener.listen(12435) ? (distance_d <= 50) : (ListenerUtil.mutListener.listen(12434) ? (distance_d > 50) : (ListenerUtil.mutListener.listen(12433) ? (distance_d != 50) : (ListenerUtil.mutListener.listen(12432) ? (distance_d == 50) : (distance_d < 50))))))))) && ((ListenerUtil.mutListener.listen(12442) ? (distance_d >= -1) : (ListenerUtil.mutListener.listen(12441) ? (distance_d <= -1) : (ListenerUtil.mutListener.listen(12440) ? (distance_d > -1) : (ListenerUtil.mutListener.listen(12439) ? (distance_d < -1) : (ListenerUtil.mutListener.listen(12438) ? (distance_d == -1) : (distance_d != -1))))))))) || !m50_a) : ((ListenerUtil.mutListener.listen(12443) ? ((ListenerUtil.mutListener.listen(12437) ? (((ListenerUtil.mutListener.listen(12431) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12430) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12429) ? (distance_d < 20) : (ListenerUtil.mutListener.listen(12428) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12427) ? (distance_d == 20) : (distance_d > 20))))))) || ((ListenerUtil.mutListener.listen(12436) ? (distance_d >= 50) : (ListenerUtil.mutListener.listen(12435) ? (distance_d <= 50) : (ListenerUtil.mutListener.listen(12434) ? (distance_d > 50) : (ListenerUtil.mutListener.listen(12433) ? (distance_d != 50) : (ListenerUtil.mutListener.listen(12432) ? (distance_d == 50) : (distance_d < 50)))))))) : (((ListenerUtil.mutListener.listen(12431) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12430) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12429) ? (distance_d < 20) : (ListenerUtil.mutListener.listen(12428) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12427) ? (distance_d == 20) : (distance_d > 20))))))) && ((ListenerUtil.mutListener.listen(12436) ? (distance_d >= 50) : (ListenerUtil.mutListener.listen(12435) ? (distance_d <= 50) : (ListenerUtil.mutListener.listen(12434) ? (distance_d > 50) : (ListenerUtil.mutListener.listen(12433) ? (distance_d != 50) : (ListenerUtil.mutListener.listen(12432) ? (distance_d == 50) : (distance_d < 50))))))))) || ((ListenerUtil.mutListener.listen(12442) ? (distance_d >= -1) : (ListenerUtil.mutListener.listen(12441) ? (distance_d <= -1) : (ListenerUtil.mutListener.listen(12440) ? (distance_d > -1) : (ListenerUtil.mutListener.listen(12439) ? (distance_d < -1) : (ListenerUtil.mutListener.listen(12438) ? (distance_d == -1) : (distance_d != -1)))))))) : ((ListenerUtil.mutListener.listen(12437) ? (((ListenerUtil.mutListener.listen(12431) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12430) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12429) ? (distance_d < 20) : (ListenerUtil.mutListener.listen(12428) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12427) ? (distance_d == 20) : (distance_d > 20))))))) || ((ListenerUtil.mutListener.listen(12436) ? (distance_d >= 50) : (ListenerUtil.mutListener.listen(12435) ? (distance_d <= 50) : (ListenerUtil.mutListener.listen(12434) ? (distance_d > 50) : (ListenerUtil.mutListener.listen(12433) ? (distance_d != 50) : (ListenerUtil.mutListener.listen(12432) ? (distance_d == 50) : (distance_d < 50)))))))) : (((ListenerUtil.mutListener.listen(12431) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12430) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12429) ? (distance_d < 20) : (ListenerUtil.mutListener.listen(12428) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12427) ? (distance_d == 20) : (distance_d > 20))))))) && ((ListenerUtil.mutListener.listen(12436) ? (distance_d >= 50) : (ListenerUtil.mutListener.listen(12435) ? (distance_d <= 50) : (ListenerUtil.mutListener.listen(12434) ? (distance_d > 50) : (ListenerUtil.mutListener.listen(12433) ? (distance_d != 50) : (ListenerUtil.mutListener.listen(12432) ? (distance_d == 50) : (distance_d < 50))))))))) && ((ListenerUtil.mutListener.listen(12442) ? (distance_d >= -1) : (ListenerUtil.mutListener.listen(12441) ? (distance_d <= -1) : (ListenerUtil.mutListener.listen(12440) ? (distance_d > -1) : (ListenerUtil.mutListener.listen(12439) ? (distance_d < -1) : (ListenerUtil.mutListener.listen(12438) ? (distance_d == -1) : (distance_d != -1))))))))) && !m50_a))) {
                            if (!ListenerUtil.mutListener.listen(12445)) {
                                m50_a = true;
                            }
                            if (!ListenerUtil.mutListener.listen(12446)) {
                                Log.d(TAG, "Case 2: false");
                            }
                            return false;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(12475)) {
                        if ((ListenerUtil.mutListener.listen(12459) ? ((ListenerUtil.mutListener.listen(12458) ? (((ListenerUtil.mutListener.listen(12452) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12451) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12450) ? (distance_d > 20) : (ListenerUtil.mutListener.listen(12449) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12448) ? (distance_d == 20) : (distance_d < 20))))))) || ((ListenerUtil.mutListener.listen(12457) ? (distance_d >= -1) : (ListenerUtil.mutListener.listen(12456) ? (distance_d <= -1) : (ListenerUtil.mutListener.listen(12455) ? (distance_d > -1) : (ListenerUtil.mutListener.listen(12454) ? (distance_d < -1) : (ListenerUtil.mutListener.listen(12453) ? (distance_d == -1) : (distance_d != -1)))))))) : (((ListenerUtil.mutListener.listen(12452) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12451) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12450) ? (distance_d > 20) : (ListenerUtil.mutListener.listen(12449) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12448) ? (distance_d == 20) : (distance_d < 20))))))) && ((ListenerUtil.mutListener.listen(12457) ? (distance_d >= -1) : (ListenerUtil.mutListener.listen(12456) ? (distance_d <= -1) : (ListenerUtil.mutListener.listen(12455) ? (distance_d > -1) : (ListenerUtil.mutListener.listen(12454) ? (distance_d < -1) : (ListenerUtil.mutListener.listen(12453) ? (distance_d == -1) : (distance_d != -1))))))))) || !m20_a) : ((ListenerUtil.mutListener.listen(12458) ? (((ListenerUtil.mutListener.listen(12452) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12451) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12450) ? (distance_d > 20) : (ListenerUtil.mutListener.listen(12449) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12448) ? (distance_d == 20) : (distance_d < 20))))))) || ((ListenerUtil.mutListener.listen(12457) ? (distance_d >= -1) : (ListenerUtil.mutListener.listen(12456) ? (distance_d <= -1) : (ListenerUtil.mutListener.listen(12455) ? (distance_d > -1) : (ListenerUtil.mutListener.listen(12454) ? (distance_d < -1) : (ListenerUtil.mutListener.listen(12453) ? (distance_d == -1) : (distance_d != -1)))))))) : (((ListenerUtil.mutListener.listen(12452) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12451) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12450) ? (distance_d > 20) : (ListenerUtil.mutListener.listen(12449) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12448) ? (distance_d == 20) : (distance_d < 20))))))) && ((ListenerUtil.mutListener.listen(12457) ? (distance_d >= -1) : (ListenerUtil.mutListener.listen(12456) ? (distance_d <= -1) : (ListenerUtil.mutListener.listen(12455) ? (distance_d > -1) : (ListenerUtil.mutListener.listen(12454) ? (distance_d < -1) : (ListenerUtil.mutListener.listen(12453) ? (distance_d == -1) : (distance_d != -1))))))))) && !m20_a))) {
                            if (!ListenerUtil.mutListener.listen(12460)) {
                                m20_a = true;
                            }
                            if (!ListenerUtil.mutListener.listen(12473)) {
                                if ((ListenerUtil.mutListener.listen(12471) ? ((ListenerUtil.mutListener.listen(12465) ? (speed >= 15) : (ListenerUtil.mutListener.listen(12464) ? (speed <= 15) : (ListenerUtil.mutListener.listen(12463) ? (speed < 15) : (ListenerUtil.mutListener.listen(12462) ? (speed != 15) : (ListenerUtil.mutListener.listen(12461) ? (speed == 15) : (speed > 15)))))) || (ListenerUtil.mutListener.listen(12470) ? (lastToSecDistance >= 100) : (ListenerUtil.mutListener.listen(12469) ? (lastToSecDistance <= 100) : (ListenerUtil.mutListener.listen(12468) ? (lastToSecDistance > 100) : (ListenerUtil.mutListener.listen(12467) ? (lastToSecDistance != 100) : (ListenerUtil.mutListener.listen(12466) ? (lastToSecDistance == 100) : (lastToSecDistance < 100))))))) : ((ListenerUtil.mutListener.listen(12465) ? (speed >= 15) : (ListenerUtil.mutListener.listen(12464) ? (speed <= 15) : (ListenerUtil.mutListener.listen(12463) ? (speed < 15) : (ListenerUtil.mutListener.listen(12462) ? (speed != 15) : (ListenerUtil.mutListener.listen(12461) ? (speed == 15) : (speed > 15)))))) && (ListenerUtil.mutListener.listen(12470) ? (lastToSecDistance >= 100) : (ListenerUtil.mutListener.listen(12469) ? (lastToSecDistance <= 100) : (ListenerUtil.mutListener.listen(12468) ? (lastToSecDistance > 100) : (ListenerUtil.mutListener.listen(12467) ? (lastToSecDistance != 100) : (ListenerUtil.mutListener.listen(12466) ? (lastToSecDistance == 100) : (lastToSecDistance < 100))))))))) {
                                    if (!ListenerUtil.mutListener.listen(12472)) {
                                        Log.d(TAG, "Case 3: true");
                                    }
                                    return true;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(12474)) {
                                Log.d(TAG, "Case 3: false");
                            }
                            return false;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(12503)) {
                        if ((ListenerUtil.mutListener.listen(12488) ? ((ListenerUtil.mutListener.listen(12487) ? ((ListenerUtil.mutListener.listen(12486) ? (((ListenerUtil.mutListener.listen(12480) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12479) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12478) ? (distance_d > 20) : (ListenerUtil.mutListener.listen(12477) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12476) ? (distance_d == 20) : (distance_d < 20))))))) || ((ListenerUtil.mutListener.listen(12485) ? (distance_d >= -1) : (ListenerUtil.mutListener.listen(12484) ? (distance_d <= -1) : (ListenerUtil.mutListener.listen(12483) ? (distance_d > -1) : (ListenerUtil.mutListener.listen(12482) ? (distance_d < -1) : (ListenerUtil.mutListener.listen(12481) ? (distance_d == -1) : (distance_d != -1)))))))) : (((ListenerUtil.mutListener.listen(12480) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12479) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12478) ? (distance_d > 20) : (ListenerUtil.mutListener.listen(12477) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12476) ? (distance_d == 20) : (distance_d < 20))))))) && ((ListenerUtil.mutListener.listen(12485) ? (distance_d >= -1) : (ListenerUtil.mutListener.listen(12484) ? (distance_d <= -1) : (ListenerUtil.mutListener.listen(12483) ? (distance_d > -1) : (ListenerUtil.mutListener.listen(12482) ? (distance_d < -1) : (ListenerUtil.mutListener.listen(12481) ? (distance_d == -1) : (distance_d != -1))))))))) || m20_a) : ((ListenerUtil.mutListener.listen(12486) ? (((ListenerUtil.mutListener.listen(12480) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12479) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12478) ? (distance_d > 20) : (ListenerUtil.mutListener.listen(12477) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12476) ? (distance_d == 20) : (distance_d < 20))))))) || ((ListenerUtil.mutListener.listen(12485) ? (distance_d >= -1) : (ListenerUtil.mutListener.listen(12484) ? (distance_d <= -1) : (ListenerUtil.mutListener.listen(12483) ? (distance_d > -1) : (ListenerUtil.mutListener.listen(12482) ? (distance_d < -1) : (ListenerUtil.mutListener.listen(12481) ? (distance_d == -1) : (distance_d != -1)))))))) : (((ListenerUtil.mutListener.listen(12480) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12479) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12478) ? (distance_d > 20) : (ListenerUtil.mutListener.listen(12477) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12476) ? (distance_d == 20) : (distance_d < 20))))))) && ((ListenerUtil.mutListener.listen(12485) ? (distance_d >= -1) : (ListenerUtil.mutListener.listen(12484) ? (distance_d <= -1) : (ListenerUtil.mutListener.listen(12483) ? (distance_d > -1) : (ListenerUtil.mutListener.listen(12482) ? (distance_d < -1) : (ListenerUtil.mutListener.listen(12481) ? (distance_d == -1) : (distance_d != -1))))))))) && m20_a)) || !m20_d) : ((ListenerUtil.mutListener.listen(12487) ? ((ListenerUtil.mutListener.listen(12486) ? (((ListenerUtil.mutListener.listen(12480) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12479) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12478) ? (distance_d > 20) : (ListenerUtil.mutListener.listen(12477) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12476) ? (distance_d == 20) : (distance_d < 20))))))) || ((ListenerUtil.mutListener.listen(12485) ? (distance_d >= -1) : (ListenerUtil.mutListener.listen(12484) ? (distance_d <= -1) : (ListenerUtil.mutListener.listen(12483) ? (distance_d > -1) : (ListenerUtil.mutListener.listen(12482) ? (distance_d < -1) : (ListenerUtil.mutListener.listen(12481) ? (distance_d == -1) : (distance_d != -1)))))))) : (((ListenerUtil.mutListener.listen(12480) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12479) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12478) ? (distance_d > 20) : (ListenerUtil.mutListener.listen(12477) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12476) ? (distance_d == 20) : (distance_d < 20))))))) && ((ListenerUtil.mutListener.listen(12485) ? (distance_d >= -1) : (ListenerUtil.mutListener.listen(12484) ? (distance_d <= -1) : (ListenerUtil.mutListener.listen(12483) ? (distance_d > -1) : (ListenerUtil.mutListener.listen(12482) ? (distance_d < -1) : (ListenerUtil.mutListener.listen(12481) ? (distance_d == -1) : (distance_d != -1))))))))) || m20_a) : ((ListenerUtil.mutListener.listen(12486) ? (((ListenerUtil.mutListener.listen(12480) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12479) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12478) ? (distance_d > 20) : (ListenerUtil.mutListener.listen(12477) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12476) ? (distance_d == 20) : (distance_d < 20))))))) || ((ListenerUtil.mutListener.listen(12485) ? (distance_d >= -1) : (ListenerUtil.mutListener.listen(12484) ? (distance_d <= -1) : (ListenerUtil.mutListener.listen(12483) ? (distance_d > -1) : (ListenerUtil.mutListener.listen(12482) ? (distance_d < -1) : (ListenerUtil.mutListener.listen(12481) ? (distance_d == -1) : (distance_d != -1)))))))) : (((ListenerUtil.mutListener.listen(12480) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12479) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12478) ? (distance_d > 20) : (ListenerUtil.mutListener.listen(12477) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12476) ? (distance_d == 20) : (distance_d < 20))))))) && ((ListenerUtil.mutListener.listen(12485) ? (distance_d >= -1) : (ListenerUtil.mutListener.listen(12484) ? (distance_d <= -1) : (ListenerUtil.mutListener.listen(12483) ? (distance_d > -1) : (ListenerUtil.mutListener.listen(12482) ? (distance_d < -1) : (ListenerUtil.mutListener.listen(12481) ? (distance_d == -1) : (distance_d != -1))))))))) && m20_a)) && !m20_d))) {
                            if (!ListenerUtil.mutListener.listen(12489)) {
                                m20_d = true;
                            }
                            if (!ListenerUtil.mutListener.listen(12502)) {
                                if ((ListenerUtil.mutListener.listen(12494) ? (speed >= 10) : (ListenerUtil.mutListener.listen(12493) ? (speed <= 10) : (ListenerUtil.mutListener.listen(12492) ? (speed > 10) : (ListenerUtil.mutListener.listen(12491) ? (speed != 10) : (ListenerUtil.mutListener.listen(12490) ? (speed == 10) : (speed < 10))))))) {
                                    if (!ListenerUtil.mutListener.listen(12501)) {
                                        Log.d(TAG, "Case 4: false");
                                    }
                                    return false;
                                } else if ((ListenerUtil.mutListener.listen(12499) ? (speed >= 15) : (ListenerUtil.mutListener.listen(12498) ? (speed <= 15) : (ListenerUtil.mutListener.listen(12497) ? (speed < 15) : (ListenerUtil.mutListener.listen(12496) ? (speed != 15) : (ListenerUtil.mutListener.listen(12495) ? (speed == 15) : (speed > 15))))))) {
                                    if (!ListenerUtil.mutListener.listen(12500)) {
                                        Log.d(TAG, "Case 4: true");
                                    }
                                    return true;
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(12526)) {
                        if ((ListenerUtil.mutListener.listen(12523) ? ((ListenerUtil.mutListener.listen(12521) ? ((ListenerUtil.mutListener.listen(12520) ? ((ListenerUtil.mutListener.listen(12514) ? (((ListenerUtil.mutListener.listen(12508) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12507) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12506) ? (distance_d < 20) : (ListenerUtil.mutListener.listen(12505) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12504) ? (distance_d == 20) : (distance_d > 20))))))) || ((ListenerUtil.mutListener.listen(12513) ? (distance_d >= 50) : (ListenerUtil.mutListener.listen(12512) ? (distance_d <= 50) : (ListenerUtil.mutListener.listen(12511) ? (distance_d > 50) : (ListenerUtil.mutListener.listen(12510) ? (distance_d != 50) : (ListenerUtil.mutListener.listen(12509) ? (distance_d == 50) : (distance_d < 50)))))))) : (((ListenerUtil.mutListener.listen(12508) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12507) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12506) ? (distance_d < 20) : (ListenerUtil.mutListener.listen(12505) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12504) ? (distance_d == 20) : (distance_d > 20))))))) && ((ListenerUtil.mutListener.listen(12513) ? (distance_d >= 50) : (ListenerUtil.mutListener.listen(12512) ? (distance_d <= 50) : (ListenerUtil.mutListener.listen(12511) ? (distance_d > 50) : (ListenerUtil.mutListener.listen(12510) ? (distance_d != 50) : (ListenerUtil.mutListener.listen(12509) ? (distance_d == 50) : (distance_d < 50))))))))) || ((ListenerUtil.mutListener.listen(12519) ? (distance_d >= -1) : (ListenerUtil.mutListener.listen(12518) ? (distance_d <= -1) : (ListenerUtil.mutListener.listen(12517) ? (distance_d > -1) : (ListenerUtil.mutListener.listen(12516) ? (distance_d < -1) : (ListenerUtil.mutListener.listen(12515) ? (distance_d == -1) : (distance_d != -1)))))))) : ((ListenerUtil.mutListener.listen(12514) ? (((ListenerUtil.mutListener.listen(12508) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12507) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12506) ? (distance_d < 20) : (ListenerUtil.mutListener.listen(12505) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12504) ? (distance_d == 20) : (distance_d > 20))))))) || ((ListenerUtil.mutListener.listen(12513) ? (distance_d >= 50) : (ListenerUtil.mutListener.listen(12512) ? (distance_d <= 50) : (ListenerUtil.mutListener.listen(12511) ? (distance_d > 50) : (ListenerUtil.mutListener.listen(12510) ? (distance_d != 50) : (ListenerUtil.mutListener.listen(12509) ? (distance_d == 50) : (distance_d < 50)))))))) : (((ListenerUtil.mutListener.listen(12508) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12507) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12506) ? (distance_d < 20) : (ListenerUtil.mutListener.listen(12505) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12504) ? (distance_d == 20) : (distance_d > 20))))))) && ((ListenerUtil.mutListener.listen(12513) ? (distance_d >= 50) : (ListenerUtil.mutListener.listen(12512) ? (distance_d <= 50) : (ListenerUtil.mutListener.listen(12511) ? (distance_d > 50) : (ListenerUtil.mutListener.listen(12510) ? (distance_d != 50) : (ListenerUtil.mutListener.listen(12509) ? (distance_d == 50) : (distance_d < 50))))))))) && ((ListenerUtil.mutListener.listen(12519) ? (distance_d >= -1) : (ListenerUtil.mutListener.listen(12518) ? (distance_d <= -1) : (ListenerUtil.mutListener.listen(12517) ? (distance_d > -1) : (ListenerUtil.mutListener.listen(12516) ? (distance_d < -1) : (ListenerUtil.mutListener.listen(12515) ? (distance_d == -1) : (distance_d != -1))))))))) || !m50_d) : ((ListenerUtil.mutListener.listen(12520) ? ((ListenerUtil.mutListener.listen(12514) ? (((ListenerUtil.mutListener.listen(12508) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12507) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12506) ? (distance_d < 20) : (ListenerUtil.mutListener.listen(12505) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12504) ? (distance_d == 20) : (distance_d > 20))))))) || ((ListenerUtil.mutListener.listen(12513) ? (distance_d >= 50) : (ListenerUtil.mutListener.listen(12512) ? (distance_d <= 50) : (ListenerUtil.mutListener.listen(12511) ? (distance_d > 50) : (ListenerUtil.mutListener.listen(12510) ? (distance_d != 50) : (ListenerUtil.mutListener.listen(12509) ? (distance_d == 50) : (distance_d < 50)))))))) : (((ListenerUtil.mutListener.listen(12508) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12507) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12506) ? (distance_d < 20) : (ListenerUtil.mutListener.listen(12505) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12504) ? (distance_d == 20) : (distance_d > 20))))))) && ((ListenerUtil.mutListener.listen(12513) ? (distance_d >= 50) : (ListenerUtil.mutListener.listen(12512) ? (distance_d <= 50) : (ListenerUtil.mutListener.listen(12511) ? (distance_d > 50) : (ListenerUtil.mutListener.listen(12510) ? (distance_d != 50) : (ListenerUtil.mutListener.listen(12509) ? (distance_d == 50) : (distance_d < 50))))))))) || ((ListenerUtil.mutListener.listen(12519) ? (distance_d >= -1) : (ListenerUtil.mutListener.listen(12518) ? (distance_d <= -1) : (ListenerUtil.mutListener.listen(12517) ? (distance_d > -1) : (ListenerUtil.mutListener.listen(12516) ? (distance_d < -1) : (ListenerUtil.mutListener.listen(12515) ? (distance_d == -1) : (distance_d != -1)))))))) : ((ListenerUtil.mutListener.listen(12514) ? (((ListenerUtil.mutListener.listen(12508) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12507) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12506) ? (distance_d < 20) : (ListenerUtil.mutListener.listen(12505) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12504) ? (distance_d == 20) : (distance_d > 20))))))) || ((ListenerUtil.mutListener.listen(12513) ? (distance_d >= 50) : (ListenerUtil.mutListener.listen(12512) ? (distance_d <= 50) : (ListenerUtil.mutListener.listen(12511) ? (distance_d > 50) : (ListenerUtil.mutListener.listen(12510) ? (distance_d != 50) : (ListenerUtil.mutListener.listen(12509) ? (distance_d == 50) : (distance_d < 50)))))))) : (((ListenerUtil.mutListener.listen(12508) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12507) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12506) ? (distance_d < 20) : (ListenerUtil.mutListener.listen(12505) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12504) ? (distance_d == 20) : (distance_d > 20))))))) && ((ListenerUtil.mutListener.listen(12513) ? (distance_d >= 50) : (ListenerUtil.mutListener.listen(12512) ? (distance_d <= 50) : (ListenerUtil.mutListener.listen(12511) ? (distance_d > 50) : (ListenerUtil.mutListener.listen(12510) ? (distance_d != 50) : (ListenerUtil.mutListener.listen(12509) ? (distance_d == 50) : (distance_d < 50))))))))) && ((ListenerUtil.mutListener.listen(12519) ? (distance_d >= -1) : (ListenerUtil.mutListener.listen(12518) ? (distance_d <= -1) : (ListenerUtil.mutListener.listen(12517) ? (distance_d > -1) : (ListenerUtil.mutListener.listen(12516) ? (distance_d < -1) : (ListenerUtil.mutListener.listen(12515) ? (distance_d == -1) : (distance_d != -1))))))))) && !m50_d)) || ((ListenerUtil.mutListener.listen(12522) ? (m20_d && m20_a) : (m20_d || m20_a)))) : ((ListenerUtil.mutListener.listen(12521) ? ((ListenerUtil.mutListener.listen(12520) ? ((ListenerUtil.mutListener.listen(12514) ? (((ListenerUtil.mutListener.listen(12508) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12507) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12506) ? (distance_d < 20) : (ListenerUtil.mutListener.listen(12505) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12504) ? (distance_d == 20) : (distance_d > 20))))))) || ((ListenerUtil.mutListener.listen(12513) ? (distance_d >= 50) : (ListenerUtil.mutListener.listen(12512) ? (distance_d <= 50) : (ListenerUtil.mutListener.listen(12511) ? (distance_d > 50) : (ListenerUtil.mutListener.listen(12510) ? (distance_d != 50) : (ListenerUtil.mutListener.listen(12509) ? (distance_d == 50) : (distance_d < 50)))))))) : (((ListenerUtil.mutListener.listen(12508) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12507) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12506) ? (distance_d < 20) : (ListenerUtil.mutListener.listen(12505) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12504) ? (distance_d == 20) : (distance_d > 20))))))) && ((ListenerUtil.mutListener.listen(12513) ? (distance_d >= 50) : (ListenerUtil.mutListener.listen(12512) ? (distance_d <= 50) : (ListenerUtil.mutListener.listen(12511) ? (distance_d > 50) : (ListenerUtil.mutListener.listen(12510) ? (distance_d != 50) : (ListenerUtil.mutListener.listen(12509) ? (distance_d == 50) : (distance_d < 50))))))))) || ((ListenerUtil.mutListener.listen(12519) ? (distance_d >= -1) : (ListenerUtil.mutListener.listen(12518) ? (distance_d <= -1) : (ListenerUtil.mutListener.listen(12517) ? (distance_d > -1) : (ListenerUtil.mutListener.listen(12516) ? (distance_d < -1) : (ListenerUtil.mutListener.listen(12515) ? (distance_d == -1) : (distance_d != -1)))))))) : ((ListenerUtil.mutListener.listen(12514) ? (((ListenerUtil.mutListener.listen(12508) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12507) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12506) ? (distance_d < 20) : (ListenerUtil.mutListener.listen(12505) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12504) ? (distance_d == 20) : (distance_d > 20))))))) || ((ListenerUtil.mutListener.listen(12513) ? (distance_d >= 50) : (ListenerUtil.mutListener.listen(12512) ? (distance_d <= 50) : (ListenerUtil.mutListener.listen(12511) ? (distance_d > 50) : (ListenerUtil.mutListener.listen(12510) ? (distance_d != 50) : (ListenerUtil.mutListener.listen(12509) ? (distance_d == 50) : (distance_d < 50)))))))) : (((ListenerUtil.mutListener.listen(12508) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12507) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12506) ? (distance_d < 20) : (ListenerUtil.mutListener.listen(12505) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12504) ? (distance_d == 20) : (distance_d > 20))))))) && ((ListenerUtil.mutListener.listen(12513) ? (distance_d >= 50) : (ListenerUtil.mutListener.listen(12512) ? (distance_d <= 50) : (ListenerUtil.mutListener.listen(12511) ? (distance_d > 50) : (ListenerUtil.mutListener.listen(12510) ? (distance_d != 50) : (ListenerUtil.mutListener.listen(12509) ? (distance_d == 50) : (distance_d < 50))))))))) && ((ListenerUtil.mutListener.listen(12519) ? (distance_d >= -1) : (ListenerUtil.mutListener.listen(12518) ? (distance_d <= -1) : (ListenerUtil.mutListener.listen(12517) ? (distance_d > -1) : (ListenerUtil.mutListener.listen(12516) ? (distance_d < -1) : (ListenerUtil.mutListener.listen(12515) ? (distance_d == -1) : (distance_d != -1))))))))) || !m50_d) : ((ListenerUtil.mutListener.listen(12520) ? ((ListenerUtil.mutListener.listen(12514) ? (((ListenerUtil.mutListener.listen(12508) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12507) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12506) ? (distance_d < 20) : (ListenerUtil.mutListener.listen(12505) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12504) ? (distance_d == 20) : (distance_d > 20))))))) || ((ListenerUtil.mutListener.listen(12513) ? (distance_d >= 50) : (ListenerUtil.mutListener.listen(12512) ? (distance_d <= 50) : (ListenerUtil.mutListener.listen(12511) ? (distance_d > 50) : (ListenerUtil.mutListener.listen(12510) ? (distance_d != 50) : (ListenerUtil.mutListener.listen(12509) ? (distance_d == 50) : (distance_d < 50)))))))) : (((ListenerUtil.mutListener.listen(12508) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12507) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12506) ? (distance_d < 20) : (ListenerUtil.mutListener.listen(12505) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12504) ? (distance_d == 20) : (distance_d > 20))))))) && ((ListenerUtil.mutListener.listen(12513) ? (distance_d >= 50) : (ListenerUtil.mutListener.listen(12512) ? (distance_d <= 50) : (ListenerUtil.mutListener.listen(12511) ? (distance_d > 50) : (ListenerUtil.mutListener.listen(12510) ? (distance_d != 50) : (ListenerUtil.mutListener.listen(12509) ? (distance_d == 50) : (distance_d < 50))))))))) || ((ListenerUtil.mutListener.listen(12519) ? (distance_d >= -1) : (ListenerUtil.mutListener.listen(12518) ? (distance_d <= -1) : (ListenerUtil.mutListener.listen(12517) ? (distance_d > -1) : (ListenerUtil.mutListener.listen(12516) ? (distance_d < -1) : (ListenerUtil.mutListener.listen(12515) ? (distance_d == -1) : (distance_d != -1)))))))) : ((ListenerUtil.mutListener.listen(12514) ? (((ListenerUtil.mutListener.listen(12508) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12507) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12506) ? (distance_d < 20) : (ListenerUtil.mutListener.listen(12505) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12504) ? (distance_d == 20) : (distance_d > 20))))))) || ((ListenerUtil.mutListener.listen(12513) ? (distance_d >= 50) : (ListenerUtil.mutListener.listen(12512) ? (distance_d <= 50) : (ListenerUtil.mutListener.listen(12511) ? (distance_d > 50) : (ListenerUtil.mutListener.listen(12510) ? (distance_d != 50) : (ListenerUtil.mutListener.listen(12509) ? (distance_d == 50) : (distance_d < 50)))))))) : (((ListenerUtil.mutListener.listen(12508) ? (distance_d >= 20) : (ListenerUtil.mutListener.listen(12507) ? (distance_d <= 20) : (ListenerUtil.mutListener.listen(12506) ? (distance_d < 20) : (ListenerUtil.mutListener.listen(12505) ? (distance_d != 20) : (ListenerUtil.mutListener.listen(12504) ? (distance_d == 20) : (distance_d > 20))))))) && ((ListenerUtil.mutListener.listen(12513) ? (distance_d >= 50) : (ListenerUtil.mutListener.listen(12512) ? (distance_d <= 50) : (ListenerUtil.mutListener.listen(12511) ? (distance_d > 50) : (ListenerUtil.mutListener.listen(12510) ? (distance_d != 50) : (ListenerUtil.mutListener.listen(12509) ? (distance_d == 50) : (distance_d < 50))))))))) && ((ListenerUtil.mutListener.listen(12519) ? (distance_d >= -1) : (ListenerUtil.mutListener.listen(12518) ? (distance_d <= -1) : (ListenerUtil.mutListener.listen(12517) ? (distance_d > -1) : (ListenerUtil.mutListener.listen(12516) ? (distance_d < -1) : (ListenerUtil.mutListener.listen(12515) ? (distance_d == -1) : (distance_d != -1))))))))) && !m50_d)) && ((ListenerUtil.mutListener.listen(12522) ? (m20_d && m20_a) : (m20_d || m20_a)))))) {
                            if (!ListenerUtil.mutListener.listen(12524)) {
                                m50_d = true;
                            }
                            if (!ListenerUtil.mutListener.listen(12525)) {
                                Log.d(TAG, "Case 5: true");
                            }
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        /**
         * Checks the proximity to the provided location and returns the event type (EVENT_TYPE_*) that was triggered by the proximity to this location.
         *
         * @return the status update (EVENT_TYPE_*) that was triggered by the proximity to this location (if any).
         */
        private int checkProximityAll(Location currentLocation) {
            if (!ListenerUtil.mutListener.listen(12548)) {
                if (!mWaitingForConfirm) {
                    if (!ListenerUtil.mutListener.listen(12528)) {
                        // re-calculate the distance to the final bus stop from the current location
                        endDistance = lastCoords.distanceTo(currentLocation);
                    }
                    if (!ListenerUtil.mutListener.listen(12529)) {
                        // re-calculate the distance to second to last bus stop from the current location
                        directDistance = secondToLastCoords.distanceTo(currentLocation);
                    }
                    if (!ListenerUtil.mutListener.listen(12530)) {
                        mSectoCurDistance = directDistance;
                    }
                    if (!ListenerUtil.mutListener.listen(12531)) {
                        Log.d(TAG, "Second to last stop coordinates: " + secondToLastCoords.getLatitude() + ", " + secondToLastCoords.getLongitude());
                    }
                    if (!ListenerUtil.mutListener.listen(12532)) {
                        // Update distance notification
                        mNavProvider.updateUi(EVENT_TYPE_UPDATE_DISTANCE);
                    }
                    if (!ListenerUtil.mutListener.listen(12542)) {
                        // Check if distance from 2nd-to-last stop is less than threshold.
                        if ((ListenerUtil.mutListener.listen(12537) ? (directDistance >= DISTANCE_THRESHOLD) : (ListenerUtil.mutListener.listen(12536) ? (directDistance <= DISTANCE_THRESHOLD) : (ListenerUtil.mutListener.listen(12535) ? (directDistance > DISTANCE_THRESHOLD) : (ListenerUtil.mutListener.listen(12534) ? (directDistance != DISTANCE_THRESHOLD) : (ListenerUtil.mutListener.listen(12533) ? (directDistance == DISTANCE_THRESHOLD) : (directDistance < DISTANCE_THRESHOLD))))))) {
                            if (!ListenerUtil.mutListener.listen(12541)) {
                                if (proximityEvent(EVENT_TYPE_GET_READY, ALERT_STATE_NONE)) {
                                    if (!ListenerUtil.mutListener.listen(12538)) {
                                        mNavProvider.updateUi(EVENT_TYPE_GET_READY);
                                    }
                                    if (!ListenerUtil.mutListener.listen(12539)) {
                                        Log.d(TAG, "-----Get ready!");
                                    }
                                    if (!ListenerUtil.mutListener.listen(12540)) {
                                        ObaAnalytics.reportUiEvent(mFirebaseAnalytics, Application.get().getString(R.string.analytics_label_destination_reminder), Application.get().getString(R.string.analytics_label_destination_reminder_variant_get_ready));
                                    }
                                    // Get ready alert played
                                    return EVENT_TYPE_GET_READY;
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(12547)) {
                        // Check if pull the cord notification should be fired.
                        if (StopDetector(directDistance, 1, currentLocation.getSpeed())) {
                            if (!ListenerUtil.mutListener.listen(12546)) {
                                if (proximityEvent(EVENT_TYPE_PULL_CORD, ALERT_STATE_SHOWN_TO_RIDER)) {
                                    if (!ListenerUtil.mutListener.listen(12543)) {
                                        mNavProvider.updateUi(EVENT_TYPE_PULL_CORD);
                                    }
                                    if (!ListenerUtil.mutListener.listen(12544)) {
                                        Log.d(TAG, "-----Get off the bus!");
                                    }
                                    if (!ListenerUtil.mutListener.listen(12545)) {
                                        ObaAnalytics.reportUiEvent(mFirebaseAnalytics, Application.get().getString(R.string.analytics_label_destination_reminder), Application.get().getString(R.string.analytics_label_destination_reminder_variant_exit_at_next_stop));
                                    }
                                    // Get off bus alert played
                                    return EVENT_TYPE_PULL_CORD;
                                }
                            }
                        }
                    }
                }
            }
            // No alerts played.
            return EVENT_TYPE_NO_EVENT;
        }

        public void resetVariablesAfterPathLinkSwitching() {
            if (!ListenerUtil.mutListener.listen(12549)) {
                Log.d(TAG, "Reseting variables after path link switching!");
            }
            if (!ListenerUtil.mutListener.listen(12550)) {
                m100_a = false;
            }
            if (!ListenerUtil.mutListener.listen(12551)) {
                m50_a = false;
            }
            if (!ListenerUtil.mutListener.listen(12552)) {
                m20_a = false;
            }
            if (!ListenerUtil.mutListener.listen(12553)) {
                m20_d = false;
            }
            if (!ListenerUtil.mutListener.listen(12554)) {
                m50_d = false;
            }
            if (!ListenerUtil.mutListener.listen(12555)) {
                m100_d = false;
            }
        }

        public void setOnlyTrigger(boolean value) {
            if (!ListenerUtil.mutListener.listen(12556)) {
                mTrigger = value;
            }
        }

        public void setReady(boolean ready) {
            if (!ListenerUtil.mutListener.listen(12557)) {
                mReady = ready;
            }
        }
    }

    public void setWaitingForConfirm(boolean waitingForConfirm) {
        if (!ListenerUtil.mutListener.listen(12558)) {
            mWaitingForConfirm = waitingForConfirm;
        }
    }

    @Override
    public void onInit(int status) {
        if (!ListenerUtil.mutListener.listen(12561)) {
            if (status == TextToSpeech.SUCCESS) {
                if (!ListenerUtil.mutListener.listen(12559)) {
                    mTTS.setLanguage(Locale.getDefault());
                }
                if (!ListenerUtil.mutListener.listen(12560)) {
                    mTTS.setSpeechRate(0.75f);
                }
            }
        }
    }

    /**
     * Gets the notification to be used for starting a hosting service for the NavigationServiceProvider in the foreground
     *
     * @return the notification to be used for starting a hosting service for the NavigationServiceProvider in the foreground
     */
    public Notification getForegroundStartingNotification() {
        return updateUi(EVENT_TYPE_INITIAL_STARTUP);
    }

    /**
     * Updates the user interface (e.g., distance display, speech) based on navigation events
     * TODO - This method should be moved to a NavigationServiceListener class based on a listener interface
     *
     * @param eventType EVENT_TYPE_* variable defining the eventType update to act upon
     * @return the notification to use for the foreground service if eventType == EVENT_TYPE_INITIAL_STARTUP, otherwise returns null
     */
    private Notification updateUi(int eventType) {
        Application app = Application.get();
        TripDetailsActivity.Builder bldr = new TripDetailsActivity.Builder(app.getApplicationContext(), mTripId);
        if (!ListenerUtil.mutListener.listen(12562)) {
            bldr = bldr.setDestinationId(mStopId);
        }
        Intent intent = bldr.getIntent();
        if (!ListenerUtil.mutListener.listen(12563)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        int flags;
        if ((ListenerUtil.mutListener.listen(12568) ? (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(12567) ? (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(12566) ? (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(12565) ? (android.os.Build.VERSION.SDK_INT != android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(12564) ? (android.os.Build.VERSION.SDK_INT == android.os.Build.VERSION_CODES.S) : (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S))))))) {
            flags = FLAG_UPDATE_CURRENT | FLAG_MUTABLE;
        } else {
            flags = FLAG_UPDATE_CURRENT;
        }
        PendingIntent pIntent = getActivity(app.getApplicationContext(), 1, intent, flags);
        // Create deletion intent to stop repeated voice comands.
        Intent receiverIntent = new Intent(app.getApplicationContext(), NavigationReceiver.class);
        int cancelFlags;
        if ((ListenerUtil.mutListener.listen(12573) ? (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(12572) ? (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(12571) ? (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(12570) ? (android.os.Build.VERSION.SDK_INT != android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(12569) ? (android.os.Build.VERSION.SDK_INT == android.os.Build.VERSION_CODES.S) : (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S))))))) {
            cancelFlags = FLAG_MUTABLE;
        } else {
            cancelFlags = 0;
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(Application.get().getApplicationContext(), Application.CHANNEL_DESTINATION_ALERT_ID).setSmallIcon(R.drawable.ic_content_flag).setContentTitle(Application.get().getResources().getString(R.string.destination_reminder_title)).setContentIntent(pIntent);
        if (!ListenerUtil.mutListener.listen(12701)) {
            if ((ListenerUtil.mutListener.listen(12578) ? (eventType >= EVENT_TYPE_INITIAL_STARTUP) : (ListenerUtil.mutListener.listen(12577) ? (eventType <= EVENT_TYPE_INITIAL_STARTUP) : (ListenerUtil.mutListener.listen(12576) ? (eventType > EVENT_TYPE_INITIAL_STARTUP) : (ListenerUtil.mutListener.listen(12575) ? (eventType < EVENT_TYPE_INITIAL_STARTUP) : (ListenerUtil.mutListener.listen(12574) ? (eventType != EVENT_TYPE_INITIAL_STARTUP) : (eventType == EVENT_TYPE_INITIAL_STARTUP))))))) {
                if (!ListenerUtil.mutListener.listen(12697)) {
                    // Build initial notification used to start the service in the foreground
                    receiverIntent.putExtra(NavigationReceiver.ACTION_NUM, NavigationReceiver.CANCEL_TRIP);
                }
                if (!ListenerUtil.mutListener.listen(12698)) {
                    receiverIntent.putExtra(NavigationReceiver.NOTIFICATION_ID, NOTIFICATION_ID);
                }
                PendingIntent pCancelIntent = getBroadcast(app.getApplicationContext(), 0, receiverIntent, cancelFlags);
                if (!ListenerUtil.mutListener.listen(12699)) {
                    mBuilder.addAction(R.drawable.ic_navigation_close, app.getString(R.string.destination_reminder_cancel_trip), pCancelIntent);
                }
                if (!ListenerUtil.mutListener.listen(12700)) {
                    mBuilder.setOngoing(true);
                }
                return mBuilder.build();
            } else if ((ListenerUtil.mutListener.listen(12583) ? (eventType >= EVENT_TYPE_UPDATE_DISTANCE) : (ListenerUtil.mutListener.listen(12582) ? (eventType <= EVENT_TYPE_UPDATE_DISTANCE) : (ListenerUtil.mutListener.listen(12581) ? (eventType > EVENT_TYPE_UPDATE_DISTANCE) : (ListenerUtil.mutListener.listen(12580) ? (eventType < EVENT_TYPE_UPDATE_DISTANCE) : (ListenerUtil.mutListener.listen(12579) ? (eventType != EVENT_TYPE_UPDATE_DISTANCE) : (eventType == EVENT_TYPE_UPDATE_DISTANCE))))))) {
                // Retrieve preferred unit and calculate distance.
                String IMPERIAL = app.getString(R.string.preferences_preferred_units_option_imperial);
                String METRIC = app.getString(R.string.preferences_preferred_units_option_metric);
                String AUTOMATIC = app.getString(R.string.preferences_preferred_units_option_automatic);
                String preferredUnits = mSettings.getString(app.getString(R.string.preference_key_preferred_units), AUTOMATIC);
                double distance = mProxCalculator.endDistance;
                // Get miles.
                double miles = (ListenerUtil.mutListener.listen(12683) ? (distance % RegionUtils.METERS_TO_MILES) : (ListenerUtil.mutListener.listen(12682) ? (distance / RegionUtils.METERS_TO_MILES) : (ListenerUtil.mutListener.listen(12681) ? (distance - RegionUtils.METERS_TO_MILES) : (ListenerUtil.mutListener.listen(12680) ? (distance + RegionUtils.METERS_TO_MILES) : (distance * RegionUtils.METERS_TO_MILES)))));
                if (!ListenerUtil.mutListener.listen(12684)) {
                    // Get kilometers.
                    distance /= 1000;
                }
                DecimalFormat fmt = new DecimalFormat("0.0");
                Locale mLocale = Locale.getDefault();
                if (!ListenerUtil.mutListener.listen(12691)) {
                    if (preferredUnits.equalsIgnoreCase(AUTOMATIC)) {
                        if (!ListenerUtil.mutListener.listen(12687)) {
                            Log.d(TAG, "Setting units automatically");
                        }
                        if (!ListenerUtil.mutListener.listen(12690)) {
                            // TODO - Method of guessing metric/imperial can definitely be improved
                            if (mLocale.getISO3Country().equalsIgnoreCase(Locale.US.getISO3Country())) {
                                if (!ListenerUtil.mutListener.listen(12689)) {
                                    mBuilder.setContentText(Application.get().getResources().getQuantityString(R.plurals.distance_miles, (int) miles, fmt.format(miles)));
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(12688)) {
                                    mBuilder.setContentText(Application.get().getResources().getQuantityString(R.plurals.distance_kilometers, (int) distance, fmt.format(distance)));
                                }
                            }
                        }
                    } else if (preferredUnits.equalsIgnoreCase(IMPERIAL)) {
                        if (!ListenerUtil.mutListener.listen(12686)) {
                            mBuilder.setContentText(Application.get().getResources().getQuantityString(R.plurals.distance_miles, (int) miles, fmt.format(miles)));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(12685)) {
                            mBuilder.setContentText(Application.get().getResources().getQuantityString(R.plurals.distance_kilometers, (int) distance, fmt.format(distance)));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(12692)) {
                    receiverIntent.putExtra(NavigationReceiver.ACTION_NUM, NavigationReceiver.CANCEL_TRIP);
                }
                if (!ListenerUtil.mutListener.listen(12693)) {
                    receiverIntent.putExtra(NavigationReceiver.NOTIFICATION_ID, NOTIFICATION_ID);
                }
                PendingIntent pCancelIntent = getBroadcast(app.getApplicationContext(), 0, receiverIntent, cancelFlags);
                if (!ListenerUtil.mutListener.listen(12694)) {
                    mBuilder.addAction(R.drawable.ic_navigation_close, app.getString(R.string.destination_reminder_cancel_trip), pCancelIntent);
                }
                if (!ListenerUtil.mutListener.listen(12695)) {
                    mBuilder.setOngoing(true);
                }
                NotificationManager mNotificationManager = (NotificationManager) Application.get().getSystemService(Context.NOTIFICATION_SERVICE);
                if (!ListenerUtil.mutListener.listen(12696)) {
                    mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
                }
            } else if ((ListenerUtil.mutListener.listen(12588) ? (eventType >= EVENT_TYPE_GET_READY) : (ListenerUtil.mutListener.listen(12587) ? (eventType <= EVENT_TYPE_GET_READY) : (ListenerUtil.mutListener.listen(12586) ? (eventType > EVENT_TYPE_GET_READY) : (ListenerUtil.mutListener.listen(12585) ? (eventType < EVENT_TYPE_GET_READY) : (ListenerUtil.mutListener.listen(12584) ? (eventType != EVENT_TYPE_GET_READY) : (eventType == EVENT_TYPE_GET_READY))))))) {
                if (!ListenerUtil.mutListener.listen(12642)) {
                    // Get ready to pack
                    mGetReady = true;
                }
                if (!ListenerUtil.mutListener.listen(12647)) {
                    receiverIntent.putExtra(NavigationReceiver.NOTIFICATION_ID, (ListenerUtil.mutListener.listen(12646) ? (NOTIFICATION_ID % 1) : (ListenerUtil.mutListener.listen(12645) ? (NOTIFICATION_ID / 1) : (ListenerUtil.mutListener.listen(12644) ? (NOTIFICATION_ID * 1) : (ListenerUtil.mutListener.listen(12643) ? (NOTIFICATION_ID - 1) : (NOTIFICATION_ID + 1))))));
                }
                if (!ListenerUtil.mutListener.listen(12648)) {
                    receiverIntent.putExtra(NavigationReceiver.ACTION_NUM, NavigationReceiver.DISMISS_NOTIFICATION);
                }
                PendingIntent pDelIntent = getBroadcast(app.getApplicationContext(), 0, receiverIntent, cancelFlags);
                String message = Application.get().getString(R.string.destination_voice_get_ready);
                if (!ListenerUtil.mutListener.listen(12671)) {
                    {
                        long _loopCounter173 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(12670) ? (i >= NUM_GET_READY_REPEAT) : (ListenerUtil.mutListener.listen(12669) ? (i <= NUM_GET_READY_REPEAT) : (ListenerUtil.mutListener.listen(12668) ? (i > NUM_GET_READY_REPEAT) : (ListenerUtil.mutListener.listen(12667) ? (i != NUM_GET_READY_REPEAT) : (ListenerUtil.mutListener.listen(12666) ? (i == NUM_GET_READY_REPEAT) : (i < NUM_GET_READY_REPEAT)))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter173", ++_loopCounter173);
                            if (!ListenerUtil.mutListener.listen(12654)) {
                                speak(message, (ListenerUtil.mutListener.listen(12653) ? (i >= 0) : (ListenerUtil.mutListener.listen(12652) ? (i <= 0) : (ListenerUtil.mutListener.listen(12651) ? (i > 0) : (ListenerUtil.mutListener.listen(12650) ? (i < 0) : (ListenerUtil.mutListener.listen(12649) ? (i != 0) : (i == 0)))))) ? TextToSpeech.QUEUE_FLUSH : TextToSpeech.QUEUE_ADD);
                            }
                            if (!ListenerUtil.mutListener.listen(12665)) {
                                if ((ListenerUtil.mutListener.listen(12663) ? (i >= (ListenerUtil.mutListener.listen(12658) ? (NUM_GET_READY_REPEAT % 1) : (ListenerUtil.mutListener.listen(12657) ? (NUM_GET_READY_REPEAT / 1) : (ListenerUtil.mutListener.listen(12656) ? (NUM_GET_READY_REPEAT * 1) : (ListenerUtil.mutListener.listen(12655) ? (NUM_GET_READY_REPEAT + 1) : (NUM_GET_READY_REPEAT - 1)))))) : (ListenerUtil.mutListener.listen(12662) ? (i <= (ListenerUtil.mutListener.listen(12658) ? (NUM_GET_READY_REPEAT % 1) : (ListenerUtil.mutListener.listen(12657) ? (NUM_GET_READY_REPEAT / 1) : (ListenerUtil.mutListener.listen(12656) ? (NUM_GET_READY_REPEAT * 1) : (ListenerUtil.mutListener.listen(12655) ? (NUM_GET_READY_REPEAT + 1) : (NUM_GET_READY_REPEAT - 1)))))) : (ListenerUtil.mutListener.listen(12661) ? (i > (ListenerUtil.mutListener.listen(12658) ? (NUM_GET_READY_REPEAT % 1) : (ListenerUtil.mutListener.listen(12657) ? (NUM_GET_READY_REPEAT / 1) : (ListenerUtil.mutListener.listen(12656) ? (NUM_GET_READY_REPEAT * 1) : (ListenerUtil.mutListener.listen(12655) ? (NUM_GET_READY_REPEAT + 1) : (NUM_GET_READY_REPEAT - 1)))))) : (ListenerUtil.mutListener.listen(12660) ? (i != (ListenerUtil.mutListener.listen(12658) ? (NUM_GET_READY_REPEAT % 1) : (ListenerUtil.mutListener.listen(12657) ? (NUM_GET_READY_REPEAT / 1) : (ListenerUtil.mutListener.listen(12656) ? (NUM_GET_READY_REPEAT * 1) : (ListenerUtil.mutListener.listen(12655) ? (NUM_GET_READY_REPEAT + 1) : (NUM_GET_READY_REPEAT - 1)))))) : (ListenerUtil.mutListener.listen(12659) ? (i == (ListenerUtil.mutListener.listen(12658) ? (NUM_GET_READY_REPEAT % 1) : (ListenerUtil.mutListener.listen(12657) ? (NUM_GET_READY_REPEAT / 1) : (ListenerUtil.mutListener.listen(12656) ? (NUM_GET_READY_REPEAT * 1) : (ListenerUtil.mutListener.listen(12655) ? (NUM_GET_READY_REPEAT + 1) : (NUM_GET_READY_REPEAT - 1)))))) : (i < (ListenerUtil.mutListener.listen(12658) ? (NUM_GET_READY_REPEAT % 1) : (ListenerUtil.mutListener.listen(12657) ? (NUM_GET_READY_REPEAT / 1) : (ListenerUtil.mutListener.listen(12656) ? (NUM_GET_READY_REPEAT * 1) : (ListenerUtil.mutListener.listen(12655) ? (NUM_GET_READY_REPEAT + 1) : (NUM_GET_READY_REPEAT - 1)))))))))))) {
                                    if (!ListenerUtil.mutListener.listen(12664)) {
                                        silence(500, TextToSpeech.QUEUE_ADD);
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(12672)) {
                    mBuilder.setContentText(message);
                }
                if (!ListenerUtil.mutListener.listen(12673)) {
                    mBuilder.setVibrate(VIBRATION_PATTERN);
                }
                if (!ListenerUtil.mutListener.listen(12674)) {
                    mBuilder.setDeleteIntent(pDelIntent);
                }
                NotificationManager mNotificationManager = (NotificationManager) Application.get().getSystemService(Context.NOTIFICATION_SERVICE);
                if (!ListenerUtil.mutListener.listen(12679)) {
                    mNotificationManager.notify((ListenerUtil.mutListener.listen(12678) ? (NOTIFICATION_ID % 1) : (ListenerUtil.mutListener.listen(12677) ? (NOTIFICATION_ID / 1) : (ListenerUtil.mutListener.listen(12676) ? (NOTIFICATION_ID * 1) : (ListenerUtil.mutListener.listen(12675) ? (NOTIFICATION_ID - 1) : (NOTIFICATION_ID + 1))))), mBuilder.build());
                }
            } else if ((ListenerUtil.mutListener.listen(12593) ? (eventType >= EVENT_TYPE_PULL_CORD) : (ListenerUtil.mutListener.listen(12592) ? (eventType <= EVENT_TYPE_PULL_CORD) : (ListenerUtil.mutListener.listen(12591) ? (eventType > EVENT_TYPE_PULL_CORD) : (ListenerUtil.mutListener.listen(12590) ? (eventType < EVENT_TYPE_PULL_CORD) : (ListenerUtil.mutListener.listen(12589) ? (eventType != EVENT_TYPE_PULL_CORD) : (eventType == EVENT_TYPE_PULL_CORD))))))) {
                if (!ListenerUtil.mutListener.listen(12594)) {
                    // Pull the cord
                    mFinished = true;
                }
                if (!ListenerUtil.mutListener.listen(12595)) {
                    receiverIntent.putExtra(NavigationReceiver.ACTION_NUM, NavigationReceiver.DISMISS_NOTIFICATION);
                }
                if (!ListenerUtil.mutListener.listen(12600)) {
                    receiverIntent.putExtra(NavigationReceiver.NOTIFICATION_ID, (ListenerUtil.mutListener.listen(12599) ? (NOTIFICATION_ID % 2) : (ListenerUtil.mutListener.listen(12598) ? (NOTIFICATION_ID / 2) : (ListenerUtil.mutListener.listen(12597) ? (NOTIFICATION_ID * 2) : (ListenerUtil.mutListener.listen(12596) ? (NOTIFICATION_ID - 2) : (NOTIFICATION_ID + 2))))));
                }
                PendingIntent pDelIntent = getBroadcast(app.getApplicationContext(), 0, receiverIntent, cancelFlags);
                String message = Application.get().getString(R.string.destination_voice_request_stop);
                if (!ListenerUtil.mutListener.listen(12623)) {
                    {
                        long _loopCounter172 = 0;
                        // TODO: Slow down voice commands, add count as property.
                        for (int i = 0; (ListenerUtil.mutListener.listen(12622) ? (i >= NUM_PULL_CORD_REPEAT) : (ListenerUtil.mutListener.listen(12621) ? (i <= NUM_PULL_CORD_REPEAT) : (ListenerUtil.mutListener.listen(12620) ? (i > NUM_PULL_CORD_REPEAT) : (ListenerUtil.mutListener.listen(12619) ? (i != NUM_PULL_CORD_REPEAT) : (ListenerUtil.mutListener.listen(12618) ? (i == NUM_PULL_CORD_REPEAT) : (i < NUM_PULL_CORD_REPEAT)))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter172", ++_loopCounter172);
                            if (!ListenerUtil.mutListener.listen(12606)) {
                                speak(message, (ListenerUtil.mutListener.listen(12605) ? (i >= 0) : (ListenerUtil.mutListener.listen(12604) ? (i <= 0) : (ListenerUtil.mutListener.listen(12603) ? (i > 0) : (ListenerUtil.mutListener.listen(12602) ? (i < 0) : (ListenerUtil.mutListener.listen(12601) ? (i != 0) : (i == 0)))))) ? TextToSpeech.QUEUE_FLUSH : TextToSpeech.QUEUE_ADD);
                            }
                            if (!ListenerUtil.mutListener.listen(12617)) {
                                if ((ListenerUtil.mutListener.listen(12615) ? (i >= (ListenerUtil.mutListener.listen(12610) ? (NUM_PULL_CORD_REPEAT % 1) : (ListenerUtil.mutListener.listen(12609) ? (NUM_PULL_CORD_REPEAT / 1) : (ListenerUtil.mutListener.listen(12608) ? (NUM_PULL_CORD_REPEAT * 1) : (ListenerUtil.mutListener.listen(12607) ? (NUM_PULL_CORD_REPEAT + 1) : (NUM_PULL_CORD_REPEAT - 1)))))) : (ListenerUtil.mutListener.listen(12614) ? (i <= (ListenerUtil.mutListener.listen(12610) ? (NUM_PULL_CORD_REPEAT % 1) : (ListenerUtil.mutListener.listen(12609) ? (NUM_PULL_CORD_REPEAT / 1) : (ListenerUtil.mutListener.listen(12608) ? (NUM_PULL_CORD_REPEAT * 1) : (ListenerUtil.mutListener.listen(12607) ? (NUM_PULL_CORD_REPEAT + 1) : (NUM_PULL_CORD_REPEAT - 1)))))) : (ListenerUtil.mutListener.listen(12613) ? (i > (ListenerUtil.mutListener.listen(12610) ? (NUM_PULL_CORD_REPEAT % 1) : (ListenerUtil.mutListener.listen(12609) ? (NUM_PULL_CORD_REPEAT / 1) : (ListenerUtil.mutListener.listen(12608) ? (NUM_PULL_CORD_REPEAT * 1) : (ListenerUtil.mutListener.listen(12607) ? (NUM_PULL_CORD_REPEAT + 1) : (NUM_PULL_CORD_REPEAT - 1)))))) : (ListenerUtil.mutListener.listen(12612) ? (i != (ListenerUtil.mutListener.listen(12610) ? (NUM_PULL_CORD_REPEAT % 1) : (ListenerUtil.mutListener.listen(12609) ? (NUM_PULL_CORD_REPEAT / 1) : (ListenerUtil.mutListener.listen(12608) ? (NUM_PULL_CORD_REPEAT * 1) : (ListenerUtil.mutListener.listen(12607) ? (NUM_PULL_CORD_REPEAT + 1) : (NUM_PULL_CORD_REPEAT - 1)))))) : (ListenerUtil.mutListener.listen(12611) ? (i == (ListenerUtil.mutListener.listen(12610) ? (NUM_PULL_CORD_REPEAT % 1) : (ListenerUtil.mutListener.listen(12609) ? (NUM_PULL_CORD_REPEAT / 1) : (ListenerUtil.mutListener.listen(12608) ? (NUM_PULL_CORD_REPEAT * 1) : (ListenerUtil.mutListener.listen(12607) ? (NUM_PULL_CORD_REPEAT + 1) : (NUM_PULL_CORD_REPEAT - 1)))))) : (i < (ListenerUtil.mutListener.listen(12610) ? (NUM_PULL_CORD_REPEAT % 1) : (ListenerUtil.mutListener.listen(12609) ? (NUM_PULL_CORD_REPEAT / 1) : (ListenerUtil.mutListener.listen(12608) ? (NUM_PULL_CORD_REPEAT * 1) : (ListenerUtil.mutListener.listen(12607) ? (NUM_PULL_CORD_REPEAT + 1) : (NUM_PULL_CORD_REPEAT - 1)))))))))))) {
                                    if (!ListenerUtil.mutListener.listen(12616)) {
                                        silence(500, TextToSpeech.QUEUE_ADD);
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(12624)) {
                    mBuilder.setContentText(message);
                }
                if (!ListenerUtil.mutListener.listen(12625)) {
                    mBuilder.setVibrate(VIBRATION_PATTERN);
                }
                if (!ListenerUtil.mutListener.listen(12626)) {
                    mBuilder.setDeleteIntent(pDelIntent);
                }
                NotificationManager mNotificationManager = (NotificationManager) Application.get().getSystemService(Context.NOTIFICATION_SERVICE);
                if (!ListenerUtil.mutListener.listen(12631)) {
                    mNotificationManager.cancel((ListenerUtil.mutListener.listen(12630) ? (NOTIFICATION_ID % 1) : (ListenerUtil.mutListener.listen(12629) ? (NOTIFICATION_ID / 1) : (ListenerUtil.mutListener.listen(12628) ? (NOTIFICATION_ID * 1) : (ListenerUtil.mutListener.listen(12627) ? (NOTIFICATION_ID - 1) : (NOTIFICATION_ID + 1))))));
                }
                if (!ListenerUtil.mutListener.listen(12636)) {
                    mNotificationManager.notify((ListenerUtil.mutListener.listen(12635) ? (NOTIFICATION_ID % 2) : (ListenerUtil.mutListener.listen(12634) ? (NOTIFICATION_ID / 2) : (ListenerUtil.mutListener.listen(12633) ? (NOTIFICATION_ID * 2) : (ListenerUtil.mutListener.listen(12632) ? (NOTIFICATION_ID - 2) : (NOTIFICATION_ID + 2))))), mBuilder.build());
                }
                if (!ListenerUtil.mutListener.listen(12637)) {
                    mBuilder = new NotificationCompat.Builder(Application.get().getApplicationContext(), Application.CHANNEL_DESTINATION_ALERT_ID).setSmallIcon(R.drawable.ic_content_flag).setContentTitle(Application.get().getResources().getString(R.string.destination_reminder_title)).setContentIntent(pIntent).setAutoCancel(true);
                }
                if (!ListenerUtil.mutListener.listen(12638)) {
                    message = Application.get().getString(R.string.destination_voice_arriving_destination);
                }
                if (!ListenerUtil.mutListener.listen(12639)) {
                    mBuilder.setContentText(message);
                }
                if (!ListenerUtil.mutListener.listen(12640)) {
                    mBuilder.setOngoing(false);
                }
                if (!ListenerUtil.mutListener.listen(12641)) {
                    mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
                }
            }
        }
        // If we reach this point then the event_type isn't initial startup
        return null;
    }

    /**
     * Speak specified message out loud using TTS
     *
     * @param message   Message to be spoken.
     * @param queueFlag Flag to use when adding message to queue.
     */
    private void speak(String message, int queueFlag) {
        if (!ListenerUtil.mutListener.listen(12709)) {
            if ((ListenerUtil.mutListener.listen(12706) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(12705) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(12704) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(12703) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(12702) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                if (!ListenerUtil.mutListener.listen(12708)) {
                    mTTS.speak(message, queueFlag, null, "TRIPMESSAGE");
                }
            } else {
                if (!ListenerUtil.mutListener.listen(12707)) {
                    mTTS.speak(message, queueFlag, null);
                }
            }
        }
    }

    /**
     * Play silence for specified duration.
     *
     * @param duration  Time in ms to play silence.
     * @param queueFlag Flag to use when adding to the queue.
     */
    private void silence(long duration, int queueFlag) {
        if (!ListenerUtil.mutListener.listen(12717)) {
            if ((ListenerUtil.mutListener.listen(12714) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(12713) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(12712) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(12711) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(12710) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                if (!ListenerUtil.mutListener.listen(12716)) {
                    mTTS.playSilentUtterance(duration, queueFlag, "TRIPSILENCE");
                }
            } else {
                if (!ListenerUtil.mutListener.listen(12715)) {
                    mTTS.playSilence(duration, queueFlag, null);
                }
            }
        }
    }
}
