/*
 * Copyright (C) 2012-2015 Paul Watts (paulcwatts@gmail.com), University of South Florida
 * and individual contributors
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
package org.onebusaway.android.region;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.analytics.FirebaseAnalytics;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.ObaAnalytics;
import org.onebusaway.android.io.elements.ObaRegion;
import org.onebusaway.android.util.LocationUtils;
import org.onebusaway.android.util.RegionUtils;
import org.onebusaway.android.util.UIUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * AsyncTask used to refresh region info from the Regions REST API.
 * <p/>
 * Classes utilizing this task can request a callback via MapModeController.Callback.setMyLocation()
 * by passing in class implementing MapModeController.Callback in the constructor
 *
 * @author barbeau
 */
public class ObaRegionsTask extends AsyncTask<Void, Integer, ArrayList<ObaRegion>> {

    public interface Callback {

        /**
         * Called when the ObaRegionsTask is complete
         *
         * @param currentRegionChanged true if the current region changed as a result of the task,
         *                             false if it didn't change
         */
        public void onRegionTaskFinished(boolean currentRegionChanged);
    }

    private static final String TAG = "ObaRegionsTask";

    // in milliseconds
    private final int CALLBACK_DELAY = 100;

    private Context mContext;

    private ProgressDialog mProgressDialog;

    private String mProgressDialogMessage;

    private List<ObaRegionsTask.Callback> mCallbacks = new ArrayList<>();

    private final boolean mForceReload;

    private final boolean mShowProgressDialog;

    /**
     * GoogleApiClient being used for Location Services
     */
    GoogleApiClient mGoogleApiClient;

    private FirebaseAnalytics mFirebaseAnalytics;

    /**
     * @param callbacks          a callback will be made to all interfaces in this list after the
     *                           task is complete (null if no callbacks are requested)
     * @param force              true if the task should be forced to update region info from the
     *                           server, false if it can return local info
     * @param showProgressDialog true if a progress dialog should be shown to the user during the
     *                           task, false if it should not
     */
    public ObaRegionsTask(Context context, List<ObaRegionsTask.Callback> callbacks, boolean force, boolean showProgressDialog) {
        if (!ListenerUtil.mutListener.listen(10406)) {
            mContext = context;
        }
        if (!ListenerUtil.mutListener.listen(10407)) {
            mCallbacks = callbacks;
        }
        mForceReload = force;
        mShowProgressDialog = showProgressDialog;
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        if (!ListenerUtil.mutListener.listen(10410)) {
            if (api.isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS) {
                if (!ListenerUtil.mutListener.listen(10408)) {
                    mGoogleApiClient = LocationUtils.getGoogleApiClientWithCallbacks(context);
                }
                if (!ListenerUtil.mutListener.listen(10409)) {
                    mGoogleApiClient.connect();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10411)) {
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
        }
    }

    @Override
    protected void onPreExecute() {
        if (!ListenerUtil.mutListener.listen(10419)) {
            if ((ListenerUtil.mutListener.listen(10412) ? (mShowProgressDialog || UIUtils.canManageDialog(mContext)) : (mShowProgressDialog && UIUtils.canManageDialog(mContext)))) {
                if (!ListenerUtil.mutListener.listen(10414)) {
                    if (mProgressDialogMessage == null) {
                        if (!ListenerUtil.mutListener.listen(10413)) {
                            mProgressDialogMessage = mContext.getString(R.string.region_detecting_server);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(10415)) {
                    mProgressDialog = ProgressDialog.show(mContext, "", mProgressDialogMessage, true);
                }
                if (!ListenerUtil.mutListener.listen(10416)) {
                    mProgressDialog.setIndeterminate(true);
                }
                if (!ListenerUtil.mutListener.listen(10417)) {
                    mProgressDialog.setCancelable(false);
                }
                if (!ListenerUtil.mutListener.listen(10418)) {
                    mProgressDialog.show();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10420)) {
            super.onPreExecute();
        }
    }

    @Override
    protected ArrayList<ObaRegion> doInBackground(Void... params) {
        return RegionUtils.getRegions(mContext, mForceReload);
    }

    @Override
    protected void onPostExecute(ArrayList<ObaRegion> results) {
        if (!ListenerUtil.mutListener.listen(10421)) {
            if (results == null) {
                // This is a catastrophic failure to load region info from all sources
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10425)) {
            // Dismiss the dialog before calling the callbacks to avoid errors referencing the dialog later
            if ((ListenerUtil.mutListener.listen(10423) ? ((ListenerUtil.mutListener.listen(10422) ? (mShowProgressDialog || UIUtils.canManageDialog(mContext)) : (mShowProgressDialog && UIUtils.canManageDialog(mContext))) || mProgressDialog.isShowing()) : ((ListenerUtil.mutListener.listen(10422) ? (mShowProgressDialog || UIUtils.canManageDialog(mContext)) : (mShowProgressDialog && UIUtils.canManageDialog(mContext))) && mProgressDialog.isShowing()))) {
                if (!ListenerUtil.mutListener.listen(10424)) {
                    mProgressDialog.dismiss();
                }
            }
        }
        SharedPreferences settings = Application.getPrefs();
        if (!ListenerUtil.mutListener.listen(10447)) {
            if (settings.getBoolean(mContext.getString(R.string.preference_key_auto_select_region), true)) {
                // Pass in the GoogleApiClient initialized in constructor
                Location myLocation = Application.getLastKnownLocation(mContext, mGoogleApiClient);
                ObaRegion closestRegion = RegionUtils.getClosestRegion(results, myLocation, true);
                if (!ListenerUtil.mutListener.listen(10446)) {
                    if (Application.get().getCurrentRegion() == null) {
                        if (!ListenerUtil.mutListener.listen(10445)) {
                            if (closestRegion != null) {
                                if (!ListenerUtil.mutListener.listen(10441)) {
                                    // No region has been set, so set region application-wide to closest region
                                    Application.get().setCurrentRegion(closestRegion);
                                }
                                if (!ListenerUtil.mutListener.listen(10442)) {
                                    Log.d(TAG, "Detected closest region '" + closestRegion.getName() + "'");
                                }
                                if (!ListenerUtil.mutListener.listen(10443)) {
                                    ObaAnalytics.setRegion(mFirebaseAnalytics, closestRegion.getName());
                                }
                                if (!ListenerUtil.mutListener.listen(10444)) {
                                    doCallback(true);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(10440)) {
                                    // or we couldn't find a closest a region, so ask the user to pick the region
                                    haveUserChooseRegion(results);
                                }
                            }
                        }
                    } else if ((ListenerUtil.mutListener.listen(10430) ? ((ListenerUtil.mutListener.listen(10429) ? (Application.get().getCurrentRegion() != null || closestRegion != null) : (Application.get().getCurrentRegion() != null && closestRegion != null)) || !Application.get().getCurrentRegion().equals(closestRegion)) : ((ListenerUtil.mutListener.listen(10429) ? (Application.get().getCurrentRegion() != null || closestRegion != null) : (Application.get().getCurrentRegion() != null && closestRegion != null)) && !Application.get().getCurrentRegion().equals(closestRegion)))) {
                        // User is closer to a different region than the current region, so change to the closest region
                        String oldRegionName = Application.get().getCurrentRegion().getName();
                        if (!ListenerUtil.mutListener.listen(10436)) {
                            Application.get().setCurrentRegion(closestRegion);
                        }
                        if (!ListenerUtil.mutListener.listen(10437)) {
                            Log.d(TAG, "Detected closer region '" + closestRegion.getName() + "', changed from " + oldRegionName + " to this region.");
                        }
                        if (!ListenerUtil.mutListener.listen(10438)) {
                            ObaAnalytics.setRegion(mFirebaseAnalytics, closestRegion.getName());
                        }
                        if (!ListenerUtil.mutListener.listen(10439)) {
                            doCallback(true);
                        }
                    } else if ((ListenerUtil.mutListener.listen(10432) ? ((ListenerUtil.mutListener.listen(10431) ? (Application.get().getCurrentRegion() != null || closestRegion != null) : (Application.get().getCurrentRegion() != null && closestRegion != null)) || Application.get().getCurrentRegion().equals(closestRegion)) : ((ListenerUtil.mutListener.listen(10431) ? (Application.get().getCurrentRegion() != null || closestRegion != null) : (Application.get().getCurrentRegion() != null && closestRegion != null)) && Application.get().getCurrentRegion().equals(closestRegion)))) {
                        if (!ListenerUtil.mutListener.listen(10434)) {
                            // Don't change the region - just refresh with latest Regions API contents
                            Application.get().setCurrentRegion(closestRegion, false);
                        }
                        if (!ListenerUtil.mutListener.listen(10435)) {
                            doCallback(false);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(10433)) {
                            doCallback(false);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10428)) {
                    if (Application.get().getCurrentRegion() == null) {
                        if (!ListenerUtil.mutListener.listen(10427)) {
                            // We don't have a region selected, and the user chose not to auto-select one, so make them pick one
                            haveUserChooseRegion(results);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(10426)) {
                            doCallback(false);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10449)) {
            // Tear down Location Services client
            if (mGoogleApiClient != null) {
                if (!ListenerUtil.mutListener.listen(10448)) {
                    mGoogleApiClient.disconnect();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10450)) {
            super.onPostExecute(results);
        }
    }

    private void haveUserChooseRegion(final ArrayList<ObaRegion> result) {
        if (!ListenerUtil.mutListener.listen(10451)) {
            if (!UIUtils.canManageDialog(mContext)) {
                return;
            }
        }
        // Create dialog for user to choose
        List<String> serverNames = new ArrayList<String>();
        if (!ListenerUtil.mutListener.listen(10454)) {
            {
                long _loopCounter136 = 0;
                for (ObaRegion region : result) {
                    ListenerUtil.loopListener.listen("_loopCounter136", ++_loopCounter136);
                    if (!ListenerUtil.mutListener.listen(10453)) {
                        if (RegionUtils.isRegionUsable(region)) {
                            if (!ListenerUtil.mutListener.listen(10452)) {
                                serverNames.add(region.getName());
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10455)) {
            Collections.sort(serverNames);
        }
        final CharSequence[] items = serverNames.toArray(new CharSequence[serverNames.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        if (!ListenerUtil.mutListener.listen(10456)) {
            builder.setTitle(mContext.getString(R.string.region_choose_region));
        }
        if (!ListenerUtil.mutListener.listen(10457)) {
            builder.setCancelable(false);
        }
        if (!ListenerUtil.mutListener.listen(10463)) {
            builder.setItems(items, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int item) {
                    if (!ListenerUtil.mutListener.listen(10462)) {
                        {
                            long _loopCounter137 = 0;
                            for (ObaRegion region : result) {
                                ListenerUtil.loopListener.listen("_loopCounter137", ++_loopCounter137);
                                if (!ListenerUtil.mutListener.listen(10461)) {
                                    if (region.getName().equals(items[item])) {
                                        if (!ListenerUtil.mutListener.listen(10458)) {
                                            // Set the region application-wide
                                            Application.get().setCurrentRegion(region);
                                        }
                                        if (!ListenerUtil.mutListener.listen(10459)) {
                                            Log.d(TAG, "User chose region '" + items[item] + "'.");
                                        }
                                        if (!ListenerUtil.mutListener.listen(10460)) {
                                            doCallback(true);
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
        AlertDialog alert = builder.create();
        if (!ListenerUtil.mutListener.listen(10464)) {
            alert.show();
        }
    }

    private void doCallback(final boolean currentRegionChanged) {
        // OBA REST API before the new region info is set in Application.  So, pause briefly.
        final Handler mPauseForCallbackHandler = new Handler();
        final Runnable mPauseForCallback = new Runnable() {

            public void run() {
                if (!ListenerUtil.mutListener.listen(10468)) {
                    // Map may not have triggered call to OBA REST API, so we force one here
                    if (mCallbacks != null) {
                        if (!ListenerUtil.mutListener.listen(10467)) {
                            {
                                long _loopCounter138 = 0;
                                for (Callback callback : mCallbacks) {
                                    ListenerUtil.loopListener.listen("_loopCounter138", ++_loopCounter138);
                                    if (!ListenerUtil.mutListener.listen(10466)) {
                                        if (callback != null) {
                                            if (!ListenerUtil.mutListener.listen(10465)) {
                                                callback.onRegionTaskFinished(currentRegionChanged);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(10469)) {
            mPauseForCallbackHandler.postDelayed(mPauseForCallback, CALLBACK_DELAY);
        }
    }

    public void setProgressDialogMessage(String progressDialogMessage) {
        if (!ListenerUtil.mutListener.listen(10470)) {
            mProgressDialogMessage = progressDialogMessage;
        }
    }
}
