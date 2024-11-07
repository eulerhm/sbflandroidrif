/*
 * Copyright (C) 2019 University of South Florida
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
package org.onebusaway.android.travelbehavior.io.task;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.travelbehavior.constants.TravelBehaviorConstants;
import org.onebusaway.android.travelbehavior.io.TravelBehaviorFileSaverExecutorManager;
import org.onebusaway.android.travelbehavior.model.TripPlanData;
import org.onebusaway.android.util.PreferenceUtils;
import org.opentripplanner.api.model.TripPlan;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TripPlanDataSaverTask implements Runnable {

    private static final String TAG = "TravelBehaviorTripPlan";

    private TripPlan mTripPlan;

    private String mUrl;

    private Context mApplicationContext;

    public TripPlanDataSaverTask(TripPlan tripPlan, String url, Context applicationContext) {
        if (!ListenerUtil.mutListener.listen(9460)) {
            mUrl = url;
        }
        if (!ListenerUtil.mutListener.listen(9461)) {
            mTripPlan = tripPlan;
        }
        if (!ListenerUtil.mutListener.listen(9462)) {
            mApplicationContext = applicationContext;
        }
    }

    @Override
    public void run() {
        if (!ListenerUtil.mutListener.listen(9472)) {
            if ((ListenerUtil.mutListener.listen(9469) ? ((ListenerUtil.mutListener.listen(9468) ? ((ListenerUtil.mutListener.listen(9467) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9466) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9465) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9464) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9463) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)))))) || mApplicationContext.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(9467) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9466) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9465) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9464) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9463) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)))))) && mApplicationContext.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) || mApplicationContext.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(9468) ? ((ListenerUtil.mutListener.listen(9467) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9466) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9465) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9464) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9463) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)))))) || mApplicationContext.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(9467) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9466) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9465) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9464) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9463) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)))))) && mApplicationContext.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) && mApplicationContext.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED))) {
                if (!ListenerUtil.mutListener.listen(9471)) {
                    saveTripPlan(null);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9470)) {
                    requestFusedLocation();
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void requestFusedLocation() {
        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(mApplicationContext);
        if (!ListenerUtil.mutListener.listen(9473)) {
            client.getLastLocation().addOnSuccessListener(TravelBehaviorFileSaverExecutorManager.getInstance().getThreadPoolExecutor(), location -> {
                saveTripPlan(location);
            });
        }
    }

    private void saveTripPlan(Location location) {
        try {
            // Get the counter that's incremented for each test
            int counter = Application.getPrefs().getInt(TravelBehaviorConstants.TRIP_PLAN_COUNTER, 0);
            if (!ListenerUtil.mutListener.listen(9475)) {
                PreferenceUtils.saveInt(TravelBehaviorConstants.TRIP_PLAN_COUNTER, ++counter);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d yyyy, hh:mm aaa");
            Date time = Calendar.getInstance().getTime();
            String readableDate = sdf.format(time);
            File subFolder = new File(Application.get().getApplicationContext().getFilesDir().getAbsolutePath() + File.separator + TravelBehaviorConstants.LOCAL_TRIP_PLAN_FOLDER);
            if (!ListenerUtil.mutListener.listen(9477)) {
                if (!subFolder.exists()) {
                    if (!ListenerUtil.mutListener.listen(9476)) {
                        subFolder.mkdirs();
                    }
                }
            }
            Long localElapsedRealtimeNanos = null;
            if (!ListenerUtil.mutListener.listen(9484)) {
                if ((ListenerUtil.mutListener.listen(9482) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(9481) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(9480) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(9479) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(9478) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN_MR1) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1))))))) {
                    if (!ListenerUtil.mutListener.listen(9483)) {
                        localElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
                    }
                }
            }
            Long serverTime = null;
            if (!ListenerUtil.mutListener.listen(9486)) {
                if (mTripPlan.getDate() != null) {
                    if (!ListenerUtil.mutListener.listen(9485)) {
                        serverTime = Long.valueOf(mTripPlan.getDate());
                    }
                }
            }
            File file = new File(subFolder, counter + "-" + readableDate + ".json");
            TripPlanData tpd = new TripPlanData(mTripPlan, mUrl, Application.get().getCurrentRegion().getId(), localElapsedRealtimeNanos, time.getTime(), serverTime);
            if (!ListenerUtil.mutListener.listen(9487)) {
                tpd.setLocation(location);
            }
            Gson gson = new Gson();
            String data = gson.toJson(tpd);
            if (!ListenerUtil.mutListener.listen(9488)) {
                FileUtils.write(file, data, false);
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(9474)) {
                Log.e(TAG, "File write failed: " + e.toString());
            }
        }
    }
}
