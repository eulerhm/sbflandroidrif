/*
 * Copyright (C) 2012-2017 Paul Watts (paulcwatts@gmail.com), 
 * University of South Florida, Microsoft Corporation.
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
package org.onebusaway.android.app;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.hardware.GeomagneticField;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import androidx.multidex.MultiDexApplication;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import org.onebusaway.android.BuildConfig;
import org.onebusaway.android.R;
import org.onebusaway.android.io.ObaAnalytics;
import org.onebusaway.android.io.ObaApi;
import org.onebusaway.android.io.elements.ObaRegion;
import org.onebusaway.android.provider.ObaContract;
import org.onebusaway.android.travelbehavior.TravelBehaviorManager;
import org.onebusaway.android.util.BuildFlavorUtils;
import org.onebusaway.android.util.LocationUtils;
import org.onebusaway.android.util.PreferenceUtils;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import edu.usf.cutr.open311client.Open311Manager;
import edu.usf.cutr.open311client.models.Open311Option;
import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;
import static org.onebusaway.android.util.UIUtils.setAppTheme;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class Application extends MultiDexApplication {

    public static final String APP_UID = "app_uid";

    // Region preference (long id)
    private static final String TAG = "Application";

    public static final String CHANNEL_TRIP_PLAN_UPDATES_ID = "trip_plan_updates";

    public static final String CHANNEL_ARRIVAL_REMINDERS_ID = "arrival_reminders";

    public static final String CHANNEL_DESTINATION_ALERT_ID = "destination_alerts";

    private SharedPreferences mPrefs;

    private static Application mApp;

    /**
     * We centralize location tracking in the Application class to allow all objects to make
     * use of the last known location that we've seen.  This is more reliable than using the
     * getLastKnownLocation() method of the location providers, and allows us to track both
     * Location
     * API v1 and fused provider.  It allows us to avoid strange behavior like animating a map view
     * change when opening a new Activity, even when the previous Activity had a current location.
     */
    private static Location mLastKnownLocation = null;

    // Magnetic declination is based on location, so track this centrally too.
    static GeomagneticField mGeomagneticField = null;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(6274)) {
            super.onCreate();
        }
        if (!ListenerUtil.mutListener.listen(6275)) {
            mApp = this;
        }
        if (!ListenerUtil.mutListener.listen(6276)) {
            mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        }
        if (!ListenerUtil.mutListener.listen(6277)) {
            initOba();
        }
        if (!ListenerUtil.mutListener.listen(6278)) {
            initObaRegion();
        }
        if (!ListenerUtil.mutListener.listen(6279)) {
            initOpen311(getCurrentRegion());
        }
        if (!ListenerUtil.mutListener.listen(6280)) {
            reportAnalytics();
        }
        if (!ListenerUtil.mutListener.listen(6281)) {
            createNotificationChannels();
        }
        if (!ListenerUtil.mutListener.listen(6282)) {
            TravelBehaviorManager.startCollectingData(getApplicationContext());
        }
    }

    /**
     * Per http://developer.android.com/reference/android/app/Application.html#onTerminate(),
     * this code is only executed in emulated process environments - it will never be called
     * on a production Android device.
     */
    @Override
    public void onTerminate() {
        if (!ListenerUtil.mutListener.listen(6283)) {
            super.onTerminate();
        }
        if (!ListenerUtil.mutListener.listen(6284)) {
            mApp = null;
        }
    }

    // 
    public static Application get() {
        return mApp;
    }

    public static SharedPreferences getPrefs() {
        return get().mPrefs;
    }

    /**
     * Returns the last known location that the application has seen, or null if we haven't seen a
     * location yet.  When trying to get a most recent location in one shot, this method should
     * always be called.
     *
     * @param cxt    The Context being used, or null if one isn't available
     * @param client The GoogleApiClient being used to obtain fused provider updates, or null if
     *               one
     *               isn't available
     * @return the last known location that the application has seen, or null if we haven't seen a
     * location yet
     */
    public static synchronized Location getLastKnownLocation(Context cxt, GoogleApiClient client) {
        if (!ListenerUtil.mutListener.listen(6287)) {
            if (mLastKnownLocation == null) {
                // Try to get a last known location from the location providers
                try {
                    if (!ListenerUtil.mutListener.listen(6286)) {
                        mLastKnownLocation = getLocation2(cxt, client);
                    }
                } catch (SecurityException e) {
                    if (!ListenerUtil.mutListener.listen(6285)) {
                        Log.e(TAG, "User may have denied location permission - " + e);
                    }
                }
            }
        }
        // Pass back last known saved location, hopefully from past location listener updates
        return mLastKnownLocation;
    }

    /**
     * Sets the last known location observed by the application via an instance of LocationHelper
     *
     * @param l a location received by a LocationHelper instance
     */
    public static synchronized void setLastKnownLocation(Location l) {
        if (!ListenerUtil.mutListener.listen(6292)) {
            // If the new location is better than the old one, save it
            if (LocationUtils.compareLocations(l, mLastKnownLocation)) {
                if (!ListenerUtil.mutListener.listen(6289)) {
                    if (mLastKnownLocation == null) {
                        if (!ListenerUtil.mutListener.listen(6288)) {
                            mLastKnownLocation = new Location("Last known location");
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(6290)) {
                    mLastKnownLocation.set(l);
                }
                if (!ListenerUtil.mutListener.listen(6291)) {
                    mGeomagneticField = new GeomagneticField((float) l.getLatitude(), (float) l.getLongitude(), (float) l.getAltitude(), System.currentTimeMillis());
                }
            }
        }
    }

    /**
     * Returns the declination of the horizontal component of the magnetic field from true north,
     * in
     * degrees (i.e. positive means the magnetic field is rotated east that much from true north).
     *
     * @return declination of the horizontal component of the magnetic field from true north, in
     * degrees (i.e. positive means the magnetic field is rotated east that much from true north),
     * or null if its not available
     */
    public static Float getMagneticDeclination() {
        if (mGeomagneticField != null) {
            return mGeomagneticField.getDeclination();
        } else {
            return null;
        }
    }

    /**
     * We need to provide the API for a location used to disambiguate stop IDs in case of
     * collision,
     * or to provide multiple results in the case multiple agencies. But we really don't need it to
     * be very accurate.
     * <p/>
     * Note that the GoogleApiClient must already have been initialized and connected prior to
     * calling
     * this method, since GoogleApiClient.connect() is asynchronous and doesn't connect before it
     * returns,
     * which requires additional initialization time (prior to calling this method)
     *
     * @param client an initialized and connected GoogleApiClient, or null if Google Play Services
     *               isn't available
     * @return a recent location, considering both Google Play Services (if available) and the
     * Android Location API
     */
    private static Location getLocation(Context cxt, GoogleApiClient client) {
        Location last = getLocation2(cxt, client);
        if (last != null) {
            return last;
        } else {
            return LocationUtils.getDefaultSearchCenter();
        }
    }

    /**
     * Returns a location, considering both Google Play Services (if available) and the Android
     * Location API
     * <p/>
     * Note that the GoogleApiClient must already have been initialized and connected prior to
     * calling
     * this method, since GoogleApiClient.connect() is asynchronous and doesn't connect before it
     * returns,
     * which requires additional initialization time (prior to calling this method)
     *
     * @param client an initialized and connected GoogleApiClient, or null if Google Play Services
     *               isn't available
     * @return a recent location, considering both Google Play Services (if available) and the
     * Android Location API
     * @throws SecurityException if the user has remove location permissions
     */
    private static Location getLocation2(Context cxt, GoogleApiClient client) throws SecurityException {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        Location playServices = null;
        if (!ListenerUtil.mutListener.listen(6299)) {
            if ((ListenerUtil.mutListener.listen(6295) ? ((ListenerUtil.mutListener.listen(6294) ? ((ListenerUtil.mutListener.listen(6293) ? (client != null || cxt != null) : (client != null && cxt != null)) || api.isGooglePlayServicesAvailable(cxt) == ConnectionResult.SUCCESS) : ((ListenerUtil.mutListener.listen(6293) ? (client != null || cxt != null) : (client != null && cxt != null)) && api.isGooglePlayServicesAvailable(cxt) == ConnectionResult.SUCCESS)) || client.isConnected()) : ((ListenerUtil.mutListener.listen(6294) ? ((ListenerUtil.mutListener.listen(6293) ? (client != null || cxt != null) : (client != null && cxt != null)) || api.isGooglePlayServicesAvailable(cxt) == ConnectionResult.SUCCESS) : ((ListenerUtil.mutListener.listen(6293) ? (client != null || cxt != null) : (client != null && cxt != null)) && api.isGooglePlayServicesAvailable(cxt) == ConnectionResult.SUCCESS)) && client.isConnected()))) {
                FusedLocationProviderClient fusedClient = getFusedLocationProviderClient(cxt);
                Task<Location> task = fusedClient.getLastLocation();
                if (!ListenerUtil.mutListener.listen(6298)) {
                    if (task.isComplete()) {
                        if (!ListenerUtil.mutListener.listen(6296)) {
                            playServices = task.getResult();
                        }
                        if (!ListenerUtil.mutListener.listen(6297)) {
                            Log.d(TAG, "Got location from Google Play Services, testing against API v1...");
                        }
                    }
                }
            }
        }
        Location apiV1 = getLocationApiV1(cxt);
        if (LocationUtils.compareLocationsByTime(playServices, apiV1)) {
            if (!ListenerUtil.mutListener.listen(6301)) {
                Log.d(TAG, "Using location from Google Play Services");
            }
            return playServices;
        } else {
            if (!ListenerUtil.mutListener.listen(6300)) {
                Log.d(TAG, "Using location from Location API v1");
            }
            return apiV1;
        }
    }

    private static Location getLocationApiV1(Context cxt) {
        if (!ListenerUtil.mutListener.listen(6302)) {
            if (cxt == null) {
                return null;
            }
        }
        LocationManager mgr = (LocationManager) cxt.getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = mgr.getProviders(true);
        Location last = null;
        if (!ListenerUtil.mutListener.listen(6307)) {
            {
                long _loopCounter64 = 0;
                for (Iterator<String> i = providers.iterator(); i.hasNext(); ) {
                    ListenerUtil.loopListener.listen("_loopCounter64", ++_loopCounter64);
                    Location loc = null;
                    try {
                        if (!ListenerUtil.mutListener.listen(6304)) {
                            loc = mgr.getLastKnownLocation(i.next());
                        }
                    } catch (SecurityException e) {
                        if (!ListenerUtil.mutListener.listen(6303)) {
                            Log.w(TAG, "User may have denied location permission - " + e);
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(6306)) {
                        // 2. Our last location is older than this location.
                        if (LocationUtils.compareLocationsByTime(loc, last)) {
                            if (!ListenerUtil.mutListener.listen(6305)) {
                                last = loc;
                            }
                        }
                    }
                }
            }
        }
        return last;
    }

    // 
    public synchronized ObaRegion getCurrentRegion() {
        return ObaApi.getDefaultContext().getRegion();
    }

    public synchronized void setCurrentRegion(ObaRegion region) {
        if (!ListenerUtil.mutListener.listen(6308)) {
            setCurrentRegion(region, true);
        }
    }

    public synchronized void setCurrentRegion(ObaRegion region, boolean regionChanged) {
        if (!ListenerUtil.mutListener.listen(6318)) {
            if (region != null) {
                if (!ListenerUtil.mutListener.listen(6311)) {
                    // First set it in preferences, then set it in OBA.
                    ObaApi.getDefaultContext().setRegion(region);
                }
                if (!ListenerUtil.mutListener.listen(6312)) {
                    PreferenceUtils.saveLong(mPrefs, getString(R.string.preference_key_region), region.getId());
                }
                if (!ListenerUtil.mutListener.listen(6313)) {
                    // We're using a region, so clear the custom API URL preference
                    setCustomApiUrl(null);
                }
                if (!ListenerUtil.mutListener.listen(6317)) {
                    if ((ListenerUtil.mutListener.listen(6314) ? (regionChanged || region.getOtpBaseUrl() != null) : (regionChanged && region.getOtpBaseUrl() != null))) {
                        if (!ListenerUtil.mutListener.listen(6315)) {
                            setCustomOtpApiUrl(null);
                        }
                        if (!ListenerUtil.mutListener.listen(6316)) {
                            setUseOldOtpApiUrlVersion(false);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6309)) {
                    // User must have just entered a custom API URL via Preferences, so clear the region info
                    ObaApi.getDefaultContext().setRegion(null);
                }
                if (!ListenerUtil.mutListener.listen(6310)) {
                    PreferenceUtils.saveLong(mPrefs, getString(R.string.preference_key_region), -1);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6319)) {
            // Init the reporting with the new endpoints
            initOpen311(region);
        }
    }

    /**
     * Gets the date at which the region information was last updated, in the number of
     * milliseconds
     * since January 1, 1970, 00:00:00 GMT
     * Default value is 0 if the region info has never been updated.
     *
     * @return the date at which the region information was last updated, in the number of
     * milliseconds since January 1, 1970, 00:00:00 GMT.  Default value is 0 if the region info has
     * never been updated.
     */
    public long getLastRegionUpdateDate() {
        SharedPreferences preferences = getPrefs();
        return preferences.getLong(getString(R.string.preference_key_last_region_update), 0);
    }

    /**
     * Sets the date at which the region information was last updated
     *
     * @param date the date at which the region information was last updated, in the number of
     *             milliseconds since January 1, 1970, 00:00:00 GMT
     */
    public void setLastRegionUpdateDate(long date) {
        if (!ListenerUtil.mutListener.listen(6320)) {
            PreferenceUtils.saveLong(mPrefs, getString(R.string.preference_key_last_region_update), date);
        }
    }

    /**
     * Returns the custom URL if the user has set a custom API URL manually via Preferences, or
     * null
     * if it has not been set
     *
     * @return the custom URL if the user has set a custom API URL manually via Preferences, or null
     * if it has not been set
     */
    public String getCustomApiUrl() {
        SharedPreferences preferences = getPrefs();
        return preferences.getString(getString(R.string.preference_key_oba_api_url), null);
    }

    /**
     * Sets the custom URL used to reach a OBA REST API server that is not available via the
     * Regions
     * REST API
     *
     * @param url the custom URL
     */
    public void setCustomApiUrl(String url) {
        if (!ListenerUtil.mutListener.listen(6321)) {
            PreferenceUtils.saveString(getString(R.string.preference_key_oba_api_url), url);
        }
    }

    /**
     * Returns the custom OTP URL if the user has set a custom API URL manually via Preferences, or
     * null
     * if it has not been set
     *
     * @return the custom URL if the user has set a custom API URL manually via Preferences, or null
     * if it has not been set
     */
    public String getCustomOtpApiUrl() {
        SharedPreferences preferences = getPrefs();
        return preferences.getString(getString(R.string.preference_key_otp_api_url), null);
    }

    /**
     * Sets the custom OTP URL used to reach a OBA REST API server that is not available via the
     * Regions
     * REST API
     *
     * @param url the custom URL
     */
    public void setCustomOtpApiUrl(String url) {
        if (!ListenerUtil.mutListener.listen(6322)) {
            PreferenceUtils.saveString(getString(R.string.preference_key_otp_api_url), url);
        }
    }

    /**
     * @return true if the OTP url version is old, or false  if it has not been set
     */
    public boolean getUseOldOtpApiUrlVersion() {
        SharedPreferences preferences = getPrefs();
        return preferences.getBoolean(getString(R.string.preference_key_otp_api_url_version), false);
    }

    /**
     * Sets the OTP Api url version
     *
     * @param useOldOtpApiUrlVersion indicates that if otp url structure belongs to older version
     */
    public void setUseOldOtpApiUrlVersion(boolean useOldOtpApiUrlVersion) {
        if (!ListenerUtil.mutListener.listen(6323)) {
            PreferenceUtils.saveBoolean(getString(R.string.preference_key_otp_api_url_version), useOldOtpApiUrlVersion);
        }
    }

    private static final String HEXES = "0123456789abcdef";

    public static String getHex(byte[] raw) {
        final StringBuilder hex = new StringBuilder((ListenerUtil.mutListener.listen(6327) ? (2 % raw.length) : (ListenerUtil.mutListener.listen(6326) ? (2 / raw.length) : (ListenerUtil.mutListener.listen(6325) ? (2 - raw.length) : (ListenerUtil.mutListener.listen(6324) ? (2 + raw.length) : (2 * raw.length))))));
        if (!ListenerUtil.mutListener.listen(6329)) {
            {
                long _loopCounter65 = 0;
                for (byte b : raw) {
                    ListenerUtil.loopListener.listen("_loopCounter65", ++_loopCounter65);
                    if (!ListenerUtil.mutListener.listen(6328)) {
                        hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F)));
                    }
                }
            }
        }
        return hex.toString();
    }

    private String getAppUid() {
        return UUID.randomUUID().toString();
    }

    private void initOba() {
        String uuid = mPrefs.getString(APP_UID, null);
        if (!ListenerUtil.mutListener.listen(6332)) {
            if (uuid == null) {
                if (!ListenerUtil.mutListener.listen(6330)) {
                    // Generate one and save that.
                    uuid = getAppUid();
                }
                if (!ListenerUtil.mutListener.listen(6331)) {
                    PreferenceUtils.saveString(APP_UID, uuid);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6333)) {
            checkArrivalStylePreferenceDefault();
        }
        if (!ListenerUtil.mutListener.listen(6334)) {
            checkDarkMode();
        }
        // Get the current app version.
        PackageManager pm = getPackageManager();
        PackageInfo appInfo = null;
        try {
            if (!ListenerUtil.mutListener.listen(6335)) {
                appInfo = pm.getPackageInfo(getPackageName(), PackageManager.GET_META_DATA);
            }
        } catch (NameNotFoundException e) {
            // Do nothing, perhaps we'll get to show it again? Or never.
            return;
        }
        if (!ListenerUtil.mutListener.listen(6336)) {
            ObaApi.getDefaultContext().setAppInfo(appInfo.versionCode, uuid);
        }
    }

    private void checkArrivalStylePreferenceDefault() {
        String arrivalInfoStylePrefKey = getResources().getString(R.string.preference_key_arrival_info_style);
        String arrivalInfoStylePref = mPrefs.getString(arrivalInfoStylePrefKey, null);
        if (!ListenerUtil.mutListener.listen(6344)) {
            if (arrivalInfoStylePref == null) {
                if (!ListenerUtil.mutListener.listen(6343)) {
                    // First execution of app - set the default arrival info style based on the BuildConfig value
                    switch(BuildConfig.ARRIVAL_INFO_STYLE) {
                        case BuildFlavorUtils.ARRIVAL_INFO_STYLE_A:
                            if (!ListenerUtil.mutListener.listen(6337)) {
                                // Use OBA classic style for default
                                PreferenceUtils.saveString(arrivalInfoStylePrefKey, BuildFlavorUtils.getPreferenceOptionForArrivalInfoBuildFlavorStyle(BuildFlavorUtils.ARRIVAL_INFO_STYLE_A));
                            }
                            if (!ListenerUtil.mutListener.listen(6338)) {
                                Log.d(TAG, "Using arrival info style A (OBA Classic) as default preference");
                            }
                            break;
                        case BuildFlavorUtils.ARRIVAL_INFO_STYLE_B:
                            if (!ListenerUtil.mutListener.listen(6339)) {
                                // Use a card-styled footer for default
                                PreferenceUtils.saveString(arrivalInfoStylePrefKey, BuildFlavorUtils.getPreferenceOptionForArrivalInfoBuildFlavorStyle(BuildFlavorUtils.ARRIVAL_INFO_STYLE_B));
                            }
                            if (!ListenerUtil.mutListener.listen(6340)) {
                                Log.d(TAG, "Using arrival info style B (Cards) as default preference");
                            }
                            break;
                        default:
                            if (!ListenerUtil.mutListener.listen(6341)) {
                                // Use a card-styled footer for default
                                PreferenceUtils.saveString(arrivalInfoStylePrefKey, BuildFlavorUtils.getPreferenceOptionForArrivalInfoBuildFlavorStyle(BuildFlavorUtils.ARRIVAL_INFO_STYLE_B));
                            }
                            if (!ListenerUtil.mutListener.listen(6342)) {
                                Log.d(TAG, "Using arrival info style B (Cards) as default preference");
                            }
                            break;
                    }
                }
            }
        }
    }

    private void checkDarkMode() {
        String appThemePrefKey = getResources().getString(R.string.preference_key_app_theme);
        String appThemePref = mPrefs.getString(appThemePrefKey, null);
        if (!ListenerUtil.mutListener.listen(6346)) {
            if (appThemePref != null) {
                if (!ListenerUtil.mutListener.listen(6345)) {
                    setAppTheme(appThemePref);
                }
            }
        }
    }

    private void initObaRegion() {
        // Read the region preference, look it up in the DB, then set the region.
        long id = mPrefs.getLong(getString(R.string.preference_key_region), -1);
        if (!ListenerUtil.mutListener.listen(6353)) {
            if ((ListenerUtil.mutListener.listen(6351) ? (id >= 0) : (ListenerUtil.mutListener.listen(6350) ? (id <= 0) : (ListenerUtil.mutListener.listen(6349) ? (id > 0) : (ListenerUtil.mutListener.listen(6348) ? (id != 0) : (ListenerUtil.mutListener.listen(6347) ? (id == 0) : (id < 0))))))) {
                if (!ListenerUtil.mutListener.listen(6352)) {
                    Log.d(TAG, "Regions preference ID is less than 0, returning...");
                }
                return;
            }
        }
        ObaRegion region = ObaContract.Regions.get(this, (int) id);
        if (!ListenerUtil.mutListener.listen(6355)) {
            if (region == null) {
                if (!ListenerUtil.mutListener.listen(6354)) {
                    Log.d(TAG, "Regions preference is null, returning...");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6356)) {
            ObaApi.getDefaultContext().setRegion(region);
        }
    }

    private void initOpen311(ObaRegion region) {
        if (!ListenerUtil.mutListener.listen(6360)) {
            if (BuildConfig.DEBUG) {
                if (!ListenerUtil.mutListener.listen(6357)) {
                    Open311Manager.getSettings().setDebugMode(true);
                }
                if (!ListenerUtil.mutListener.listen(6358)) {
                    Open311Manager.getSettings().setDryRun(true);
                }
                if (!ListenerUtil.mutListener.listen(6359)) {
                    Log.w(TAG, "Open311 issue reporting is in debug/dry run mode - no issues will be submitted.");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6361)) {
            // Clear all open311 endpoints
            Open311Manager.clearOpen311();
        }
        if (!ListenerUtil.mutListener.listen(6365)) {
            // Read the open311 preferences from the region and set
            if ((ListenerUtil.mutListener.listen(6362) ? (region != null || region.getOpen311Servers() != null) : (region != null && region.getOpen311Servers() != null))) {
                if (!ListenerUtil.mutListener.listen(6364)) {
                    {
                        long _loopCounter66 = 0;
                        for (ObaRegion.Open311Server open311Server : region.getOpen311Servers()) {
                            ListenerUtil.loopListener.listen("_loopCounter66", ++_loopCounter66);
                            String jurisdictionId = open311Server.getJuridisctionId();
                            Open311Option option = new Open311Option(open311Server.getBaseUrl(), open311Server.getApiKey(), TextUtils.isEmpty(jurisdictionId) ? null : jurisdictionId);
                            if (!ListenerUtil.mutListener.listen(6363)) {
                                Open311Manager.initOpen311WithOption(option);
                            }
                        }
                    }
                }
            }
        }
    }

    private void reportAnalytics() {
        if (!ListenerUtil.mutListener.listen(6366)) {
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        }
        if (!ListenerUtil.mutListener.listen(6374)) {
            if ((ListenerUtil.mutListener.listen(6367) ? (getCustomApiUrl() == null || getCurrentRegion() != null) : (getCustomApiUrl() == null && getCurrentRegion() != null))) {
                if (!ListenerUtil.mutListener.listen(6373)) {
                    ObaAnalytics.setRegion(mFirebaseAnalytics, getCurrentRegion().getName());
                }
            } else if (Application.get().getCustomApiUrl() != null) {
                String customUrl = null;
                MessageDigest digest = null;
                try {
                    if (!ListenerUtil.mutListener.listen(6369)) {
                        digest = MessageDigest.getInstance("SHA-1");
                    }
                    if (!ListenerUtil.mutListener.listen(6370)) {
                        digest.update(getCustomApiUrl().getBytes());
                    }
                    if (!ListenerUtil.mutListener.listen(6371)) {
                        customUrl = getString(R.string.analytics_label_custom_url) + ": " + getHex(digest.digest());
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(6368)) {
                        customUrl = Application.get().getString(R.string.analytics_label_custom_url);
                    }
                }
                if (!ListenerUtil.mutListener.listen(6372)) {
                    ObaAnalytics.setRegion(mFirebaseAnalytics, customUrl);
                }
            }
        }
        Boolean experimentalRegions = getPrefs().getBoolean(getString(R.string.preference_key_experimental_regions), Boolean.FALSE);
        Boolean autoRegion = getPrefs().getBoolean(getString(R.string.preference_key_auto_select_region), true);
    }

    /**
     * Method to check whether bikeshare layer is enabled or not.
     *
     * @return true if the bikeshare layer is an option that can be toggled on/off
     */
    public static boolean isBikeshareEnabled() {
        // process easier
        return ((ListenerUtil.mutListener.listen(6376) ? (((ListenerUtil.mutListener.listen(6375) ? (Application.get().getCurrentRegion() != null || Application.get().getCurrentRegion().getSupportsOtpBikeshare()) : (Application.get().getCurrentRegion() != null && Application.get().getCurrentRegion().getSupportsOtpBikeshare()))) && !TextUtils.isEmpty(Application.get().getCustomOtpApiUrl())) : (((ListenerUtil.mutListener.listen(6375) ? (Application.get().getCurrentRegion() != null || Application.get().getCurrentRegion().getSupportsOtpBikeshare()) : (Application.get().getCurrentRegion() != null && Application.get().getCurrentRegion().getSupportsOtpBikeshare()))) || !TextUtils.isEmpty(Application.get().getCustomOtpApiUrl()))));
    }

    private void createNotificationChannels() {
        if (!ListenerUtil.mutListener.listen(6388)) {
            if ((ListenerUtil.mutListener.listen(6381) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(6380) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(6379) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(6378) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(6377) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O))))))) {
                NotificationChannel channel1 = new NotificationChannel(CHANNEL_TRIP_PLAN_UPDATES_ID, "Trip plan notifications (beta)", NotificationManager.IMPORTANCE_DEFAULT);
                if (!ListenerUtil.mutListener.listen(6382)) {
                    channel1.setDescription("After planning a trip, send notifications if the trip is delayed or no longer recommended.");
                }
                NotificationChannel channel2 = new NotificationChannel(CHANNEL_ARRIVAL_REMINDERS_ID, "Bus arrival notifications", NotificationManager.IMPORTANCE_DEFAULT);
                if (!ListenerUtil.mutListener.listen(6383)) {
                    channel2.setDescription("Notifications to remind the user of an arriving bus.");
                }
                NotificationChannel channel3 = new NotificationChannel(CHANNEL_DESTINATION_ALERT_ID, "Destination alerts", NotificationManager.IMPORTANCE_LOW);
                if (!ListenerUtil.mutListener.listen(6384)) {
                    channel2.setDescription("All notifications relating to Destination alerts");
                }
                NotificationManager manager = getSystemService(NotificationManager.class);
                if (!ListenerUtil.mutListener.listen(6385)) {
                    manager.createNotificationChannel(channel1);
                }
                if (!ListenerUtil.mutListener.listen(6386)) {
                    manager.createNotificationChannel(channel2);
                }
                if (!ListenerUtil.mutListener.listen(6387)) {
                    manager.createNotificationChannel(channel3);
                }
            }
        }
    }

    public static Boolean isIgnoringBatteryOptimizations(Context applicationContext) {
        PowerManager pm = (PowerManager) applicationContext.getSystemService(Context.POWER_SERVICE);
        if (!ListenerUtil.mutListener.listen(6395)) {
            if ((ListenerUtil.mutListener.listen(6394) ? ((ListenerUtil.mutListener.listen(6393) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(6392) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(6391) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(6390) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(6389) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)))))) || pm.isIgnoringBatteryOptimizations(applicationContext.getPackageName())) : ((ListenerUtil.mutListener.listen(6393) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(6392) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(6391) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(6390) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(6389) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)))))) && pm.isIgnoringBatteryOptimizations(applicationContext.getPackageName())))) {
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(6401)) {
            if ((ListenerUtil.mutListener.listen(6400) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(6399) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(6398) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(6397) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(6396) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.M))))))) {
                return null;
            }
        }
        return false;
    }
}
