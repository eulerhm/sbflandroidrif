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
package org.onebusaway.android.travelbehavior.receiver;

import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.ActivityRecognitionClient;
import com.google.android.gms.location.ActivityTransitionEvent;
import com.google.android.gms.location.ActivityTransitionResult;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.firestore.DocumentReference;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.travelbehavior.constants.TravelBehaviorConstants;
import org.onebusaway.android.travelbehavior.io.worker.ArrivalsAndDeparturesDataReaderWorker;
import org.onebusaway.android.travelbehavior.io.worker.DestinationReminderReaderWorker;
import org.onebusaway.android.travelbehavior.io.worker.TripPlanDataReaderWorker;
import org.onebusaway.android.travelbehavior.model.TravelBehaviorInfo;
import org.onebusaway.android.travelbehavior.utils.TravelBehaviorFirebaseIOUtils;
import org.onebusaway.android.travelbehavior.utils.TravelBehaviorUtils;
import org.onebusaway.android.util.PermissionUtils;
import org.onebusaway.android.util.PreferenceUtils;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TransitionBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "ActivityTransition";

    private Context mContext;

    private List<TravelBehaviorInfo.TravelBehaviorActivity> mActivityList;

    private String mUid;

    private String mRecordId;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!ListenerUtil.mutListener.listen(9722)) {
            if (intent != null) {
                if (!ListenerUtil.mutListener.listen(9721)) {
                    if (ActivityTransitionResult.hasResult(intent)) {
                        if (!ListenerUtil.mutListener.listen(9710)) {
                            mContext = context;
                        }
                        ActivityTransitionResult result = ActivityTransitionResult.extractResult(intent);
                        if (!ListenerUtil.mutListener.listen(9711)) {
                            if (result == null) {
                                return;
                            }
                        }
                        StringBuilder sb = new StringBuilder();
                        if (!ListenerUtil.mutListener.listen(9712)) {
                            mActivityList = new ArrayList<>();
                        }
                        if (!ListenerUtil.mutListener.listen(9716)) {
                            {
                                long _loopCounter124 = 0;
                                for (ActivityTransitionEvent event : result.getTransitionEvents()) {
                                    ListenerUtil.loopListener.listen("_loopCounter124", ++_loopCounter124);
                                    if (!ListenerUtil.mutListener.listen(9713)) {
                                        sb.append(TravelBehaviorUtils.toActivityString(event.getActivityType())).append(" -- ");
                                    }
                                    if (!ListenerUtil.mutListener.listen(9714)) {
                                        sb.append(TravelBehaviorUtils.toTransitionType(event.getTransitionType())).append("\n");
                                    }
                                    if (!ListenerUtil.mutListener.listen(9715)) {
                                        mActivityList.add(new TravelBehaviorInfo.TravelBehaviorActivity(TravelBehaviorUtils.toActivityString(event.getActivityType()), TravelBehaviorUtils.toTransitionType(event.getTransitionType()), event.getElapsedRealTimeNanos()));
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(9717)) {
                            Log.d(TAG, "Detected activity transition: " + sb.toString());
                        }
                        if (!ListenerUtil.mutListener.listen(9718)) {
                            TravelBehaviorUtils.showDebugToastMessageWithVibration("Detected activity transition: " + sb.toString(), mContext);
                        }
                        if (!ListenerUtil.mutListener.listen(9719)) {
                            mUid = PreferenceUtils.getString(TravelBehaviorConstants.USER_ID);
                        }
                        if (!ListenerUtil.mutListener.listen(9720)) {
                            saveTravelBehavior();
                        }
                    }
                }
            }
        }
    }

    private void saveTravelBehavior() {
        if (!ListenerUtil.mutListener.listen(9723)) {
            saveTravelBehavior(new TravelBehaviorInfo(mActivityList, Application.isIgnoringBatteryOptimizations(mContext)));
        }
        if (!ListenerUtil.mutListener.listen(9724)) {
            startSaveTripPlansWorker();
        }
        if (!ListenerUtil.mutListener.listen(9725)) {
            startSaveArrivalAndDepartureWorker();
        }
        if (!ListenerUtil.mutListener.listen(9726)) {
            startSaveDestinationRemindersWorker();
        }
        if (!ListenerUtil.mutListener.listen(9727)) {
            requestActivityRecognition();
        }
        if (!ListenerUtil.mutListener.listen(9728)) {
            requestLocationUpdates();
        }
    }

    private void saveTravelBehavior(TravelBehaviorInfo tbi) {
        long riPrefix = PreferenceUtils.getLong(TravelBehaviorConstants.RECORD_ID, 0);
        if (!ListenerUtil.mutListener.listen(9729)) {
            mRecordId = riPrefix++ + "-" + UUID.randomUUID().toString();
        }
        if (!ListenerUtil.mutListener.listen(9730)) {
            PreferenceUtils.saveLong(TravelBehaviorConstants.RECORD_ID, riPrefix);
        }
        DocumentReference document = TravelBehaviorFirebaseIOUtils.getFirebaseDocReferenceByUserIdAndRecordId(mUid, mRecordId, TravelBehaviorConstants.FIREBASE_ACTIVITY_TRANSITION_FOLDER);
        if (!ListenerUtil.mutListener.listen(9731)) {
            document.set(tbi).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Log.d(TAG, "Activity transition document added with ID " + document.getId());
                } else {
                    TravelBehaviorFirebaseIOUtils.logErrorMessage(task.getException(), "Activity transition document failed to be added: ");
                }
            });
        }
    }

    private void startSaveArrivalAndDepartureWorker() {
        Data myData = new Data.Builder().putString(TravelBehaviorConstants.USER_ID, mUid).putString(TravelBehaviorConstants.RECORD_ID, mRecordId).build();
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(ArrivalsAndDeparturesDataReaderWorker.class).setInputData(myData).build();
        if (!ListenerUtil.mutListener.listen(9732)) {
            WorkManager.getInstance().enqueue(workRequest);
        }
    }

    private void startSaveTripPlansWorker() {
        Data myData = new Data.Builder().putString(TravelBehaviorConstants.USER_ID, mUid).putString(TravelBehaviorConstants.RECORD_ID, mRecordId).build();
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(TripPlanDataReaderWorker.class).setInputData(myData).build();
        if (!ListenerUtil.mutListener.listen(9733)) {
            WorkManager.getInstance().enqueue(workRequest);
        }
    }

    private void startSaveDestinationRemindersWorker() {
        Data myData = new Data.Builder().putString(TravelBehaviorConstants.USER_ID, mUid).putString(TravelBehaviorConstants.RECORD_ID, mRecordId).build();
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(DestinationReminderReaderWorker.class).setInputData(myData).build();
        if (!ListenerUtil.mutListener.listen(9734)) {
            WorkManager.getInstance().enqueue(workRequest);
        }
    }

    private void requestActivityRecognition() {
        ActivityRecognitionClient client = ActivityRecognition.getClient(mContext);
        Intent intent = new Intent(mContext, RecognitionBroadcastReceiver.class);
        if (!ListenerUtil.mutListener.listen(9735)) {
            intent.putExtra(TravelBehaviorConstants.RECORD_ID, mRecordId);
        }
        int reqCode = PreferenceUtils.getInt(TravelBehaviorConstants.RECOGNITION_REQUEST_CODE, 0);
        int flags;
        if ((ListenerUtil.mutListener.listen(9740) ? (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(9739) ? (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(9738) ? (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(9737) ? (android.os.Build.VERSION.SDK_INT != android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(9736) ? (android.os.Build.VERSION.SDK_INT == android.os.Build.VERSION_CODES.S) : (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S))))))) {
            flags = PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_MUTABLE;
        } else {
            flags = PendingIntent.FLAG_ONE_SHOT;
        }
        PendingIntent pi = PendingIntent.getBroadcast(mContext, reqCode++, intent, flags);
        if (!ListenerUtil.mutListener.listen(9741)) {
            PreferenceUtils.saveInt(TravelBehaviorConstants.RECOGNITION_REQUEST_CODE, reqCode);
        }
        if (!ListenerUtil.mutListener.listen(9742)) {
            client.requestActivityUpdates(TimeUnit.SECONDS.toMillis(10), pi);
        }
    }

    private void requestLocationUpdates() {
        String[] requiredPermissions = { Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION };
        if (!ListenerUtil.mutListener.listen(9747)) {
            if (PermissionUtils.hasGrantedAllPermissions(mContext, requiredPermissions)) {
                if (!ListenerUtil.mutListener.listen(9744)) {
                    Log.d(TAG, "Location permissions are granted, requesting fused, GPS, and Network" + "locations");
                }
                if (!ListenerUtil.mutListener.listen(9745)) {
                    requestFusedLocation();
                }
                if (!ListenerUtil.mutListener.listen(9746)) {
                    requestGPSNetworkLocation();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9743)) {
                    Log.d(TAG, "Location permissions not granted. Skipping location requests");
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void requestFusedLocation() {
        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(mContext);
        if (!ListenerUtil.mutListener.listen(9748)) {
            client.getLastLocation().addOnSuccessListener(location -> {
                if (location != null) {
                    TravelBehaviorFirebaseIOUtils.saveLocation(location, mUid, mRecordId);
                }
            });
        }
    }

    @SuppressLint("MissingPermission")
    private void requestGPSNetworkLocation() {
        LocationManager lm = (LocationManager) Application.get().getBaseContext().getSystemService(Context.LOCATION_SERVICE);
        if (!ListenerUtil.mutListener.listen(9749)) {
            if (lm == null)
                return;
        }
        List<String> providers = lm.getProviders(true);
        if (!ListenerUtil.mutListener.listen(9759)) {
            {
                long _loopCounter125 = 0;
                for (String provider : providers) {
                    ListenerUtil.loopListener.listen("_loopCounter125", ++_loopCounter125);
                    if (!ListenerUtil.mutListener.listen(9750)) {
                        if (LocationManager.PASSIVE_PROVIDER.equals(provider))
                            continue;
                    }
                    int reqCode = PreferenceUtils.getInt(TravelBehaviorConstants.LOCATION_REQUEST_CODE, 0);
                    Intent intent = new Intent(mContext, LocationBroadcastReceiver.class);
                    if (!ListenerUtil.mutListener.listen(9751)) {
                        intent.putExtra(TravelBehaviorConstants.RECORD_ID, mRecordId);
                    }
                    int flags;
                    if ((ListenerUtil.mutListener.listen(9756) ? (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(9755) ? (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(9754) ? (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(9753) ? (android.os.Build.VERSION.SDK_INT != android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(9752) ? (android.os.Build.VERSION.SDK_INT == android.os.Build.VERSION_CODES.S) : (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S))))))) {
                        flags = PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_MUTABLE;
                    } else {
                        flags = PendingIntent.FLAG_ONE_SHOT;
                    }
                    PendingIntent pi = PendingIntent.getBroadcast(mContext, reqCode++, intent, flags);
                    if (!ListenerUtil.mutListener.listen(9757)) {
                        lm.requestLocationUpdates(provider, 0, 0, pi);
                    }
                    if (!ListenerUtil.mutListener.listen(9758)) {
                        PreferenceUtils.saveInt(TravelBehaviorConstants.LOCATION_REQUEST_CODE, reqCode);
                    }
                }
            }
        }
    }
}
