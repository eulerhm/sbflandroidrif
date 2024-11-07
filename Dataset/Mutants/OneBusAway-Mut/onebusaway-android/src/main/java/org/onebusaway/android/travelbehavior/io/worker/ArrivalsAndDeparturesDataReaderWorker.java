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
import org.onebusaway.android.travelbehavior.model.ArrivalAndDepartureData;
import org.onebusaway.android.travelbehavior.model.ArrivalAndDepartureInfo;
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

public class ArrivalsAndDeparturesDataReaderWorker extends Worker {

    private static final String TAG = "ArrDprtDataReadWorker";

    public ArrivalsAndDeparturesDataReaderWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        if (!ListenerUtil.mutListener.listen(9558)) {
            readAndPostArrivalsAndDeparturesData();
        }
        return Result.success();
    }

    private void readAndPostArrivalsAndDeparturesData() {
        try {
            File subFolder = new File(Application.get().getApplicationContext().getFilesDir().getAbsolutePath() + File.separator + TravelBehaviorConstants.LOCAL_ARRIVAL_AND_DEPARTURE_FOLDER);
            if (!ListenerUtil.mutListener.listen(9561)) {
                // If the directory does not exist do not read
                if ((ListenerUtil.mutListener.listen(9560) ? (subFolder == null && !subFolder.isDirectory()) : (subFolder == null || !subFolder.isDirectory())))
                    return;
            }
            Collection<File> files = FileUtils.listFiles(subFolder, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
            if (!ListenerUtil.mutListener.listen(9595)) {
                if ((ListenerUtil.mutListener.listen(9562) ? (files != null || !files.isEmpty()) : (files != null && !files.isEmpty()))) {
                    List<ArrivalAndDepartureData> l = new ArrayList<>();
                    Gson gson = new Gson();
                    if (!ListenerUtil.mutListener.listen(9592)) {
                        {
                            long _loopCounter121 = 0;
                            for (File f : files) {
                                ListenerUtil.loopListener.listen("_loopCounter121", ++_loopCounter121);
                                try {
                                    String jsonStr = FileUtils.readFileToString(f);
                                    ArrivalAndDepartureData data = gson.fromJson(jsonStr, ArrivalAndDepartureData.class);
                                    if (!ListenerUtil.mutListener.listen(9591)) {
                                        if ((ListenerUtil.mutListener.listen(9568) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(9567) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(9566) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(9565) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(9564) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN_MR1) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1))))))) {
                                            if (!ListenerUtil.mutListener.listen(9590)) {
                                                if ((ListenerUtil.mutListener.listen(9588) ? ((ListenerUtil.mutListener.listen(9583) ? (SystemClock.elapsedRealtimeNanos() % data.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9582) ? (SystemClock.elapsedRealtimeNanos() / data.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9581) ? (SystemClock.elapsedRealtimeNanos() * data.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9580) ? (SystemClock.elapsedRealtimeNanos() + data.getLocalElapsedRealtimeNanos()) : (SystemClock.elapsedRealtimeNanos() - data.getLocalElapsedRealtimeNanos()))))) >= TravelBehaviorConstants.MOST_RECENT_DATA_THRESHOLD_NANO) : (ListenerUtil.mutListener.listen(9587) ? ((ListenerUtil.mutListener.listen(9583) ? (SystemClock.elapsedRealtimeNanos() % data.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9582) ? (SystemClock.elapsedRealtimeNanos() / data.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9581) ? (SystemClock.elapsedRealtimeNanos() * data.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9580) ? (SystemClock.elapsedRealtimeNanos() + data.getLocalElapsedRealtimeNanos()) : (SystemClock.elapsedRealtimeNanos() - data.getLocalElapsedRealtimeNanos()))))) <= TravelBehaviorConstants.MOST_RECENT_DATA_THRESHOLD_NANO) : (ListenerUtil.mutListener.listen(9586) ? ((ListenerUtil.mutListener.listen(9583) ? (SystemClock.elapsedRealtimeNanos() % data.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9582) ? (SystemClock.elapsedRealtimeNanos() / data.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9581) ? (SystemClock.elapsedRealtimeNanos() * data.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9580) ? (SystemClock.elapsedRealtimeNanos() + data.getLocalElapsedRealtimeNanos()) : (SystemClock.elapsedRealtimeNanos() - data.getLocalElapsedRealtimeNanos()))))) > TravelBehaviorConstants.MOST_RECENT_DATA_THRESHOLD_NANO) : (ListenerUtil.mutListener.listen(9585) ? ((ListenerUtil.mutListener.listen(9583) ? (SystemClock.elapsedRealtimeNanos() % data.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9582) ? (SystemClock.elapsedRealtimeNanos() / data.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9581) ? (SystemClock.elapsedRealtimeNanos() * data.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9580) ? (SystemClock.elapsedRealtimeNanos() + data.getLocalElapsedRealtimeNanos()) : (SystemClock.elapsedRealtimeNanos() - data.getLocalElapsedRealtimeNanos()))))) != TravelBehaviorConstants.MOST_RECENT_DATA_THRESHOLD_NANO) : (ListenerUtil.mutListener.listen(9584) ? ((ListenerUtil.mutListener.listen(9583) ? (SystemClock.elapsedRealtimeNanos() % data.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9582) ? (SystemClock.elapsedRealtimeNanos() / data.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9581) ? (SystemClock.elapsedRealtimeNanos() * data.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9580) ? (SystemClock.elapsedRealtimeNanos() + data.getLocalElapsedRealtimeNanos()) : (SystemClock.elapsedRealtimeNanos() - data.getLocalElapsedRealtimeNanos()))))) == TravelBehaviorConstants.MOST_RECENT_DATA_THRESHOLD_NANO) : ((ListenerUtil.mutListener.listen(9583) ? (SystemClock.elapsedRealtimeNanos() % data.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9582) ? (SystemClock.elapsedRealtimeNanos() / data.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9581) ? (SystemClock.elapsedRealtimeNanos() * data.getLocalElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(9580) ? (SystemClock.elapsedRealtimeNanos() + data.getLocalElapsedRealtimeNanos()) : (SystemClock.elapsedRealtimeNanos() - data.getLocalElapsedRealtimeNanos()))))) < TravelBehaviorConstants.MOST_RECENT_DATA_THRESHOLD_NANO))))))) {
                                                    if (!ListenerUtil.mutListener.listen(9589)) {
                                                        l.add(data);
                                                    }
                                                }
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(9579)) {
                                                if ((ListenerUtil.mutListener.listen(9577) ? ((ListenerUtil.mutListener.listen(9572) ? (System.currentTimeMillis() % data.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9571) ? (System.currentTimeMillis() / data.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9570) ? (System.currentTimeMillis() * data.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9569) ? (System.currentTimeMillis() + data.getLocalSystemCurrMillis()) : (System.currentTimeMillis() - data.getLocalSystemCurrMillis()))))) >= TravelBehaviorConstants.MOST_RECENT_DATA_THRESHOLD_MILLIS) : (ListenerUtil.mutListener.listen(9576) ? ((ListenerUtil.mutListener.listen(9572) ? (System.currentTimeMillis() % data.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9571) ? (System.currentTimeMillis() / data.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9570) ? (System.currentTimeMillis() * data.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9569) ? (System.currentTimeMillis() + data.getLocalSystemCurrMillis()) : (System.currentTimeMillis() - data.getLocalSystemCurrMillis()))))) <= TravelBehaviorConstants.MOST_RECENT_DATA_THRESHOLD_MILLIS) : (ListenerUtil.mutListener.listen(9575) ? ((ListenerUtil.mutListener.listen(9572) ? (System.currentTimeMillis() % data.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9571) ? (System.currentTimeMillis() / data.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9570) ? (System.currentTimeMillis() * data.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9569) ? (System.currentTimeMillis() + data.getLocalSystemCurrMillis()) : (System.currentTimeMillis() - data.getLocalSystemCurrMillis()))))) > TravelBehaviorConstants.MOST_RECENT_DATA_THRESHOLD_MILLIS) : (ListenerUtil.mutListener.listen(9574) ? ((ListenerUtil.mutListener.listen(9572) ? (System.currentTimeMillis() % data.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9571) ? (System.currentTimeMillis() / data.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9570) ? (System.currentTimeMillis() * data.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9569) ? (System.currentTimeMillis() + data.getLocalSystemCurrMillis()) : (System.currentTimeMillis() - data.getLocalSystemCurrMillis()))))) != TravelBehaviorConstants.MOST_RECENT_DATA_THRESHOLD_MILLIS) : (ListenerUtil.mutListener.listen(9573) ? ((ListenerUtil.mutListener.listen(9572) ? (System.currentTimeMillis() % data.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9571) ? (System.currentTimeMillis() / data.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9570) ? (System.currentTimeMillis() * data.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9569) ? (System.currentTimeMillis() + data.getLocalSystemCurrMillis()) : (System.currentTimeMillis() - data.getLocalSystemCurrMillis()))))) == TravelBehaviorConstants.MOST_RECENT_DATA_THRESHOLD_MILLIS) : ((ListenerUtil.mutListener.listen(9572) ? (System.currentTimeMillis() % data.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9571) ? (System.currentTimeMillis() / data.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9570) ? (System.currentTimeMillis() * data.getLocalSystemCurrMillis()) : (ListenerUtil.mutListener.listen(9569) ? (System.currentTimeMillis() + data.getLocalSystemCurrMillis()) : (System.currentTimeMillis() - data.getLocalSystemCurrMillis()))))) < TravelBehaviorConstants.MOST_RECENT_DATA_THRESHOLD_MILLIS))))))) {
                                                    if (!ListenerUtil.mutListener.listen(9578)) {
                                                        l.add(data);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } catch (IOException e) {
                                    if (!ListenerUtil.mutListener.listen(9563)) {
                                        Log.e(TAG, e.toString());
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9593)) {
                        FileUtils.cleanDirectory(subFolder);
                    }
                    String uid = getInputData().getString(TravelBehaviorConstants.USER_ID);
                    String recordId = getInputData().getString(TravelBehaviorConstants.RECORD_ID);
                    if (!ListenerUtil.mutListener.listen(9594)) {
                        TravelBehaviorFirebaseIOUtils.saveArrivalsAndDepartures(l, uid, recordId);
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(9559)) {
                Log.e(TAG, e.toString());
            }
        }
    }
}
