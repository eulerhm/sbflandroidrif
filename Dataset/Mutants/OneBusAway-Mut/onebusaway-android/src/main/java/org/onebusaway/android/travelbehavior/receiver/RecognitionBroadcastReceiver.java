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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;
import com.google.firebase.firestore.DocumentReference;
import org.onebusaway.android.travelbehavior.constants.TravelBehaviorConstants;
import org.onebusaway.android.travelbehavior.model.TravelBehaviorInfo;
import org.onebusaway.android.travelbehavior.utils.TravelBehaviorFirebaseIOUtils;
import org.onebusaway.android.travelbehavior.utils.TravelBehaviorUtils;
import org.onebusaway.android.util.PreferenceUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class RecognitionBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "ActivityRecognition";

    private Map<String, Integer> mDetectedActivityMap;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!ListenerUtil.mutListener.listen(9774)) {
            if (intent != null) {
                if (!ListenerUtil.mutListener.listen(9773)) {
                    if (ActivityRecognitionResult.hasResult(intent)) {
                        ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
                        if (!ListenerUtil.mutListener.listen(9766)) {
                            mDetectedActivityMap = new HashMap<>();
                        }
                        StringBuilder sb = new StringBuilder();
                        if (!ListenerUtil.mutListener.listen(9770)) {
                            {
                                long _loopCounter126 = 0;
                                for (DetectedActivity da : result.getProbableActivities()) {
                                    ListenerUtil.loopListener.listen("_loopCounter126", ++_loopCounter126);
                                    if (!ListenerUtil.mutListener.listen(9767)) {
                                        mDetectedActivityMap.put(TravelBehaviorUtils.toActivityString(da.getType()), da.getConfidence());
                                    }
                                    if (!ListenerUtil.mutListener.listen(9768)) {
                                        sb.append(TravelBehaviorUtils.toActivityString(da.getType())).append(" -- ");
                                    }
                                    if (!ListenerUtil.mutListener.listen(9769)) {
                                        sb.append("confidence level: ").append(da.getConfidence()).append("\n");
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(9771)) {
                            Log.d(TAG, "Detected activity recognition: " + sb.toString());
                        }
                        String recordId = intent.getStringExtra(TravelBehaviorConstants.RECORD_ID);
                        if (!ListenerUtil.mutListener.listen(9772)) {
                            readActivitiesByRecordId(recordId);
                        }
                    }
                }
            }
        }
    }

    private void readActivitiesByRecordId(String recordId) {
        String uid = PreferenceUtils.getString(TravelBehaviorConstants.USER_ID);
        DocumentReference document = TravelBehaviorFirebaseIOUtils.getFirebaseDocReferenceByUserIdAndRecordId(uid, recordId, TravelBehaviorConstants.FIREBASE_ACTIVITY_TRANSITION_FOLDER);
        if (!ListenerUtil.mutListener.listen(9775)) {
            document.get().addOnSuccessListener(documentSnapshot -> {
                if (documentSnapshot.exists()) {
                    Log.d(TAG, "Read document successful RecognitionBroadcastReceiver");
                    TravelBehaviorInfo tbi = documentSnapshot.toObject(TravelBehaviorInfo.class);
                    if (tbi != null) {
                        updateTravelBehavior(tbi, recordId);
                    } else {
                        Log.d(TAG, "TravelBehaviorInfo is null");
                    }
                } else {
                    Log.d(TAG, "Read document FAILED RecognitionBroadcastReceiver");
                }
            });
        }
    }

    private void updateTravelBehavior(TravelBehaviorInfo tbi, String recordId) {
        String uid = PreferenceUtils.getString(TravelBehaviorConstants.USER_ID);
        DocumentReference document = TravelBehaviorFirebaseIOUtils.getFirebaseDocReferenceByUserIdAndRecordId(uid, recordId, TravelBehaviorConstants.FIREBASE_ACTIVITY_TRANSITION_FOLDER);
        List<Map<String, Object>> list = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(9786)) {
            {
                long _loopCounter127 = 0;
                for (TravelBehaviorInfo.TravelBehaviorActivity tba : tbi.activities) {
                    ListenerUtil.loopListener.listen("_loopCounter127", ++_loopCounter127);
                    if (!ListenerUtil.mutListener.listen(9776)) {
                        tba.confidenceLevel = mDetectedActivityMap.get(tba.detectedActivity);
                    }
                    ;
                    Map<String, Object> updateMap = new HashMap();
                    if (!ListenerUtil.mutListener.listen(9777)) {
                        updateMap.put("detectedActivity", tba.detectedActivity);
                    }
                    if (!ListenerUtil.mutListener.listen(9778)) {
                        updateMap.put("detectedActivityType", tba.detectedActivityType);
                    }
                    if (!ListenerUtil.mutListener.listen(9779)) {
                        updateMap.put("confidenceLevel", tba.confidenceLevel);
                    }
                    if (!ListenerUtil.mutListener.listen(9780)) {
                        updateMap.put("eventElapsedRealtimeNanos", tba.eventElapsedRealtimeNanos);
                    }
                    if (!ListenerUtil.mutListener.listen(9781)) {
                        updateMap.put("systemClockElapsedRealtimeNanos", tba.systemClockElapsedRealtimeNanos);
                    }
                    if (!ListenerUtil.mutListener.listen(9782)) {
                        updateMap.put("systemClockCurrentTimeMillis", tba.systemClockCurrentTimeMillis);
                    }
                    if (!ListenerUtil.mutListener.listen(9783)) {
                        updateMap.put("numberOfNanosInThePastWhenEventHappened", tba.numberOfNanosInThePastWhenEventHappened);
                    }
                    if (!ListenerUtil.mutListener.listen(9784)) {
                        updateMap.put("eventTimeMillis", tba.eventTimeMillis);
                    }
                    if (!ListenerUtil.mutListener.listen(9785)) {
                        list.add(updateMap);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9787)) {
            document.update("activities", list).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Log.d(TAG, "Update travel behavior successful.");
                } else {
                    TravelBehaviorFirebaseIOUtils.logErrorMessage(task.getException(), "Update travel behavior failed: ");
                }
            });
        }
    }
}
