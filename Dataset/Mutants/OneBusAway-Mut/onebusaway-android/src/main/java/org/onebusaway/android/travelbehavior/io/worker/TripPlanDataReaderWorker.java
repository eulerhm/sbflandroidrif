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
package org.onebusaway.android.travelbehavior.io.worker;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.travelbehavior.constants.TravelBehaviorConstants;
import org.onebusaway.android.travelbehavior.model.TripPlanData;
import org.onebusaway.android.travelbehavior.utils.TravelBehaviorFirebaseIOUtils;
import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TripPlanDataReaderWorker extends Worker {

    private static final String TAG = "TripPlanReadWorker";

    public TripPlanDataReaderWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        if (!ListenerUtil.mutListener.listen(9596)) {
            readAndPostTripPlanData();
        }
        return Result.success();
    }

    private void readAndPostTripPlanData() {
        try {
            File subFolder = new File(Application.get().getApplicationContext().getFilesDir().getAbsolutePath() + File.separator + TravelBehaviorConstants.LOCAL_TRIP_PLAN_FOLDER);
            if (!ListenerUtil.mutListener.listen(9599)) {
                // If the directory does not exist do not read
                if ((ListenerUtil.mutListener.listen(9598) ? (subFolder == null && !subFolder.isDirectory()) : (subFolder == null || !subFolder.isDirectory())))
                    return;
            }
            Collection<File> files = FileUtils.listFiles(subFolder, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
            Gson gson = new Gson();
            if (!ListenerUtil.mutListener.listen(9633)) {
                if ((ListenerUtil.mutListener.listen(9600) ? (files != null || !files.isEmpty()) : (files != null && !files.isEmpty()))) {
                    List<TripPlanData> l = new ArrayList<>();
                    if (!ListenerUtil.mutListener.listen(9630)) {
                        {
                            long _loopCounter122 = 0;
                            for (File f : files) {
                                ListenerUtil.loopListener.listen("_loopCounter122", ++_loopCounter122);
                                try {
                                    String jsonStr = FileUtils.readFileToString(f);
                                    TripPlanData tripPlanData = gson.fromJson(jsonStr, TripPlanData.class);
                                    if (!ListenerUtil.mutListener.listen(9629)) {
                                        if ((ListenerUtil.mutListener.listen(9606) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(9605) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(9604) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(9603) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(9602) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN_MR1) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1))))))) {
                                            if (!ListenerUtil.mutListener.listen(9628)) {
                                                if ((ListenerUtil.mutListener.listen(9626) ? ((ListenerUtil.mutListener.listen(9621) ? (SystemClock.elapsedRealtimeNanos() % tripPlanData.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9620) ? (SystemClock.elapsedRealtimeNanos() / tripPlanData.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9619) ? (SystemClock.elapsedRealtimeNanos() * tripPlanData.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9618) ? (SystemClock.elapsedRealtimeNanos() + tripPlanData.getLocalElapsedRealtimeNanos()) : (SystemClock.elapsedRealtimeNanos() - tripPlanData.getLocalElapsedRealtimeNanos()))))) >= TravelBehaviorConstants.MOST_RECENT_DATA_THRESHOLD_NANO) : (ListenerUtil.mutListener.listen(9625) ? ((ListenerUtil.mutListener.listen(9621) ? (SystemClock.elapsedRealtimeNanos() % tripPlanData.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9620) ? (SystemClock.elapsedRealtimeNanos() / tripPlanData.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9619) ? (SystemClock.elapsedRealtimeNanos() * tripPlanData.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9618) ? (SystemClock.elapsedRealtimeNanos() + tripPlanData.getLocalElapsedRealtimeNanos()) : (SystemClock.elapsedRealtimeNanos() - tripPlanData.getLocalElapsedRealtimeNanos()))))) <= TravelBehaviorConstants.MOST_RECENT_DATA_THRESHOLD_NANO) : (ListenerUtil.mutListener.listen(9624) ? ((ListenerUtil.mutListener.listen(9621) ? (SystemClock.elapsedRealtimeNanos() % tripPlanData.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9620) ? (SystemClock.elapsedRealtimeNanos() / tripPlanData.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9619) ? (SystemClock.elapsedRealtimeNanos() * tripPlanData.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9618) ? (SystemClock.elapsedRealtimeNanos() + tripPlanData.getLocalElapsedRealtimeNanos()) : (SystemClock.elapsedRealtimeNanos() - tripPlanData.getLocalElapsedRealtimeNanos()))))) > TravelBehaviorConstants.MOST_RECENT_DATA_THRESHOLD_NANO) : (ListenerUtil.mutListener.listen(9623) ? ((ListenerUtil.mutListener.listen(9621) ? (SystemClock.elapsedRealtimeNanos() % tripPlanData.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9620) ? (SystemClock.elapsedRealtimeNanos() / tripPlanData.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9619) ? (SystemClock.elapsedRealtimeNanos() * tripPlanData.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9618) ? (SystemClock.elapsedRealtimeNanos() + tripPlanData.getLocalElapsedRealtimeNanos()) : (SystemClock.elapsedRealtimeNanos() - tripPlanData.getLocalElapsedRealtimeNanos()))))) != TravelBehaviorConstants.MOST_RECENT_DATA_THRESHOLD_NANO) : (ListenerUtil.mutListener.listen(9622) ? ((ListenerUtil.mutListener.listen(9621) ? (SystemClock.elapsedRealtimeNanos() % tripPlanData.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9620) ? (SystemClock.elapsedRealtimeNanos() / tripPlanData.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9619) ? (SystemClock.elapsedRealtimeNanos() * tripPlanData.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9618) ? (SystemClock.elapsedRealtimeNanos() + tripPlanData.getLocalElapsedRealtimeNanos()) : (SystemClock.elapsedRealtimeNanos() - tripPlanData.getLocalElapsedRealtimeNanos()))))) == TravelBehaviorConstants.MOST_RECENT_DATA_THRESHOLD_NANO) : ((ListenerUtil.mutListener.listen(9621) ? (SystemClock.elapsedRealtimeNanos() % tripPlanData.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9620) ? (SystemClock.elapsedRealtimeNanos() / tripPlanData.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9619) ? (SystemClock.elapsedRealtimeNanos() * tripPlanData.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9618) ? (SystemClock.elapsedRealtimeNanos() + tripPlanData.getLocalElapsedRealtimeNanos()) : (SystemClock.elapsedRealtimeNanos() - tripPlanData.getLocalElapsedRealtimeNanos()))))) < TravelBehaviorConstants.MOST_RECENT_DATA_THRESHOLD_NANO))))))) {
                                                    if (!ListenerUtil.mutListener.listen(9627)) {
                                                        l.add(tripPlanData);
                                                    }
                                                }
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(9617)) {
                                                if ((ListenerUtil.mutListener.listen(9615) ? ((ListenerUtil.mutListener.listen(9610) ? (System.currentTimeMillis() % tripPlanData.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9609) ? (System.currentTimeMillis() / tripPlanData.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9608) ? (System.currentTimeMillis() * tripPlanData.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9607) ? (System.currentTimeMillis() + tripPlanData.getLocalSystemCurrMillis()) : (System.currentTimeMillis() - tripPlanData.getLocalSystemCurrMillis()))))) >= TravelBehaviorConstants.MOST_RECENT_DATA_THRESHOLD_MILLIS) : (ListenerUtil.mutListener.listen(9614) ? ((ListenerUtil.mutListener.listen(9610) ? (System.currentTimeMillis() % tripPlanData.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9609) ? (System.currentTimeMillis() / tripPlanData.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9608) ? (System.currentTimeMillis() * tripPlanData.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9607) ? (System.currentTimeMillis() + tripPlanData.getLocalSystemCurrMillis()) : (System.currentTimeMillis() - tripPlanData.getLocalSystemCurrMillis()))))) <= TravelBehaviorConstants.MOST_RECENT_DATA_THRESHOLD_MILLIS) : (ListenerUtil.mutListener.listen(9613) ? ((ListenerUtil.mutListener.listen(9610) ? (System.currentTimeMillis() % tripPlanData.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9609) ? (System.currentTimeMillis() / tripPlanData.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9608) ? (System.currentTimeMillis() * tripPlanData.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9607) ? (System.currentTimeMillis() + tripPlanData.getLocalSystemCurrMillis()) : (System.currentTimeMillis() - tripPlanData.getLocalSystemCurrMillis()))))) > TravelBehaviorConstants.MOST_RECENT_DATA_THRESHOLD_MILLIS) : (ListenerUtil.mutListener.listen(9612) ? ((ListenerUtil.mutListener.listen(9610) ? (System.currentTimeMillis() % tripPlanData.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9609) ? (System.currentTimeMillis() / tripPlanData.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9608) ? (System.currentTimeMillis() * tripPlanData.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9607) ? (System.currentTimeMillis() + tripPlanData.getLocalSystemCurrMillis()) : (System.currentTimeMillis() - tripPlanData.getLocalSystemCurrMillis()))))) != TravelBehaviorConstants.MOST_RECENT_DATA_THRESHOLD_MILLIS) : (ListenerUtil.mutListener.listen(9611) ? ((ListenerUtil.mutListener.listen(9610) ? (System.currentTimeMillis() % tripPlanData.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9609) ? (System.currentTimeMillis() / tripPlanData.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9608) ? (System.currentTimeMillis() * tripPlanData.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9607) ? (System.currentTimeMillis() + tripPlanData.getLocalSystemCurrMillis()) : (System.currentTimeMillis() - tripPlanData.getLocalSystemCurrMillis()))))) == TravelBehaviorConstants.MOST_RECENT_DATA_THRESHOLD_MILLIS) : ((ListenerUtil.mutListener.listen(9610) ? (System.currentTimeMillis() % tripPlanData.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9609) ? (System.currentTimeMillis() / tripPlanData.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9608) ? (System.currentTimeMillis() * tripPlanData.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9607) ? (System.currentTimeMillis() + tripPlanData.getLocalSystemCurrMillis()) : (System.currentTimeMillis() - tripPlanData.getLocalSystemCurrMillis()))))) < TravelBehaviorConstants.MOST_RECENT_DATA_THRESHOLD_MILLIS))))))) {
                                                    if (!ListenerUtil.mutListener.listen(9616)) {
                                                        l.add(tripPlanData);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } catch (IOException e) {
                                    if (!ListenerUtil.mutListener.listen(9601)) {
                                        Log.e(TAG, e.toString());
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9631)) {
                        FileUtils.cleanDirectory(subFolder);
                    }
                    String uid = getInputData().getString(TravelBehaviorConstants.USER_ID);
                    String recordId = getInputData().getString(TravelBehaviorConstants.RECORD_ID);
                    if (!ListenerUtil.mutListener.listen(9632)) {
                        TravelBehaviorFirebaseIOUtils.saveTripPlans(l, uid, recordId);
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(9597)) {
                Log.e(TAG, e.toString());
            }
        }
    }
}
