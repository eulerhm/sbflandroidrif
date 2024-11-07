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
package org.onebusaway.android.travelbehavior;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.ActivityTransition;
import com.google.android.gms.location.ActivityTransitionRequest;
import com.google.android.gms.location.DetectedActivity;
import com.google.android.gms.tasks.Task;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.analytics.FirebaseAnalytics;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.ObaAnalytics;
import org.onebusaway.android.io.elements.ObaArrivalInfo;
import org.onebusaway.android.travelbehavior.constants.TravelBehaviorConstants;
import org.onebusaway.android.travelbehavior.io.TravelBehaviorFileSaverExecutorManager;
import org.onebusaway.android.travelbehavior.io.task.ArrivalAndDepartureDataSaverTask;
import org.onebusaway.android.travelbehavior.io.task.DestinationReminderDataSaverTask;
import org.onebusaway.android.travelbehavior.io.task.TripPlanDataSaverTask;
import org.onebusaway.android.travelbehavior.io.worker.OptOutTravelBehaviorParticipantWorker;
import org.onebusaway.android.travelbehavior.io.worker.RegisterTravelBehaviorParticipantWorker;
import org.onebusaway.android.travelbehavior.io.worker.UpdateDeviceInfoWorker;
import org.onebusaway.android.travelbehavior.receiver.TransitionBroadcastReceiver;
import org.onebusaway.android.travelbehavior.utils.TravelBehaviorFirebaseIOUtils;
import org.onebusaway.android.travelbehavior.utils.TravelBehaviorUtils;
import org.onebusaway.android.ui.HomeActivity;
import org.onebusaway.android.util.PermissionUtils;
import org.onebusaway.android.util.PreferenceUtils;
import org.opentripplanner.api.model.TripPlan;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TravelBehaviorManager {

    private static final String TAG = "TravelBehaviorManager";

    private Context mActivityContext;

    private Context mApplicationContext;

    private FirebaseAnalytics mFirebaseAnalytics;

    public TravelBehaviorManager(Context activityContext, Context applicationContext) {
        if (!ListenerUtil.mutListener.listen(9901)) {
            mActivityContext = activityContext;
        }
        if (!ListenerUtil.mutListener.listen(9902)) {
            mApplicationContext = applicationContext;
        }
        if (!ListenerUtil.mutListener.listen(9903)) {
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(activityContext);
        }
    }

    public void registerTravelBehaviorParticipant() {
        if (!ListenerUtil.mutListener.listen(9904)) {
            registerTravelBehaviorParticipant(false);
        }
    }

    public void registerTravelBehaviorParticipant(boolean forceStart) {
        if (!ListenerUtil.mutListener.listen(9905)) {
            // Do not register if enrolling is no more allowed;
            if (!TravelBehaviorUtils.allowEnrollMoreParticipantsInStudy()) {
                return;
            }
        }
        boolean isUserOptOut = PreferenceUtils.getBoolean(TravelBehaviorConstants.USER_OPT_OUT, false);
        if (!ListenerUtil.mutListener.listen(9907)) {
            if (forceStart)
                if (!ListenerUtil.mutListener.listen(9906)) {
                    isUserOptOut = false;
                }
        }
        if (!ListenerUtil.mutListener.listen(9910)) {
            // If user opt out or Global switch is off then do nothing
            if ((ListenerUtil.mutListener.listen(9908) ? (!TravelBehaviorUtils.isTravelBehaviorActiveInRegion() && isUserOptOut) : (!TravelBehaviorUtils.isTravelBehaviorActiveInRegion() || isUserOptOut))) {
                if (!ListenerUtil.mutListener.listen(9909)) {
                    stopCollectingData();
                }
                return;
            }
        }
        boolean isUserOptIn = PreferenceUtils.getBoolean(TravelBehaviorConstants.USER_OPT_IN, false);
        if (!ListenerUtil.mutListener.listen(9912)) {
            // If the user not opt in yet
            if (!isUserOptIn) {
                if (!ListenerUtil.mutListener.listen(9911)) {
                    showParticipationDialog();
                }
            }
        }
    }

    private void showParticipationDialog() {
        View v = LayoutInflater.from(mActivityContext).inflate(R.layout.research_participation_dialog, null);
        CheckBox neverShowDialog = v.findViewById(R.id.research_never_ask_again);
        if (!ListenerUtil.mutListener.listen(9913)) {
            new AlertDialog.Builder(mActivityContext).setView(v).setTitle(R.string.travel_behavior_opt_in_title).setIcon(createIcon()).setCancelable(false).setPositiveButton(R.string.travel_behavior_dialog_learn_more, (dialog, which) -> showAgeDialog()).setNegativeButton(R.string.travel_behavior_dialog_not_now, (dialog, which) -> {
                // If the user has chosen not to see the dialog again opt them out of the study, otherwise do nothing so they are prompted again later
                if (neverShowDialog.isChecked()) {
                    optOutUser();
                    ObaAnalytics.reportUiEvent(mFirebaseAnalytics, mApplicationContext.getString(R.string.analytics_label_button_travel_behavior_opt_out_at_first_dialog), null);
                } else {
                    ObaAnalytics.reportUiEvent(mFirebaseAnalytics, mApplicationContext.getString(R.string.analytics_label_button_travel_behavior_enroll_not_now), null);
                }
            }).create().show();
        }
    }

    private void showAgeDialog() {
        if (!ListenerUtil.mutListener.listen(9914)) {
            new AlertDialog.Builder(mActivityContext).setMessage(R.string.travel_behavior_age_message).setTitle(R.string.travel_behavior_opt_in_title).setIcon(createIcon()).setCancelable(false).setPositiveButton(R.string.travel_behavior_dialog_yes, (dialog, which) -> {
                showInformedConsent();
                dialog.dismiss();
                ObaAnalytics.reportUiEvent(mFirebaseAnalytics, mApplicationContext.getString(R.string.analytics_label_button_travel_behavior_opt_in_over_18), null);
            }).setNegativeButton(R.string.travel_behavior_dialog_no, (dialog, which) -> {
                Toast.makeText(mApplicationContext, R.string.travel_behavior_age_invalid_message, Toast.LENGTH_LONG).show();
                optOutUser();
                dialog.dismiss();
                ObaAnalytics.reportUiEvent(mFirebaseAnalytics, mApplicationContext.getString(R.string.analytics_label_button_travel_behavior_opt_out_under_18), null);
            }).create().show();
        }
    }

    private void showInformedConsent() {
        String consentHtml = getHtmlConsentDocument();
        if (!ListenerUtil.mutListener.listen(9915)) {
            new AlertDialog.Builder(mActivityContext).setMessage(Html.fromHtml(consentHtml)).setTitle(R.string.travel_behavior_opt_in_title).setIcon(createIcon()).setCancelable(false).setPositiveButton(R.string.travel_behavior_dialog_consent_agree, (dialog, which) -> {
                showEmailDialog();
                ObaAnalytics.reportUiEvent(mFirebaseAnalytics, mApplicationContext.getString(R.string.analytics_label_button_travel_behavior_opt_in_informed_consent), null);
            }).setNegativeButton(R.string.travel_behavior_dialog_consent_disagree, (dialog, which) -> {
                optOutUser();
                ObaAnalytics.reportUiEvent(mFirebaseAnalytics, mApplicationContext.getString(R.string.analytics_label_button_travel_behavior_opt_out_informed_consent), null);
            }).create().show();
        }
    }

    private String getHtmlConsentDocument() {
        InputStream inputStream = mApplicationContext.getResources().openRawResource(R.raw.travel_behavior_informed_consent);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int len;
        try {
            if (!ListenerUtil.mutListener.listen(9923)) {
                {
                    long _loopCounter130 = 0;
                    while ((ListenerUtil.mutListener.listen(9922) ? ((len = inputStream.read(buf)) >= -1) : (ListenerUtil.mutListener.listen(9921) ? ((len = inputStream.read(buf)) <= -1) : (ListenerUtil.mutListener.listen(9920) ? ((len = inputStream.read(buf)) > -1) : (ListenerUtil.mutListener.listen(9919) ? ((len = inputStream.read(buf)) < -1) : (ListenerUtil.mutListener.listen(9918) ? ((len = inputStream.read(buf)) == -1) : ((len = inputStream.read(buf)) != -1))))))) {
                        ListenerUtil.loopListener.listen("_loopCounter130", ++_loopCounter130);
                        if (!ListenerUtil.mutListener.listen(9917)) {
                            outputStream.write(buf, 0, len);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(9924)) {
                outputStream.close();
            }
            if (!ListenerUtil.mutListener.listen(9925)) {
                inputStream.close();
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(9916)) {
                e.printStackTrace();
            }
        }
        return outputStream.toString();
    }

    private void showEmailDialog() {
        if (!ListenerUtil.mutListener.listen(9926)) {
            showEmailDialog(null);
        }
    }

    private void showEmailDialog(String email) {
        LayoutInflater inflater = ((AppCompatActivity) mActivityContext).getLayoutInflater();
        final View editTextView = inflater.inflate(R.layout.travel_behavior_email_dialog, null);
        EditText emailEditText = editTextView.findViewById(R.id.tb_email_edittext);
        EditText emailEditTextConfirm = editTextView.findViewById(R.id.tb_email_edittext_confirm);
        if (!ListenerUtil.mutListener.listen(9928)) {
            if (email != null) {
                if (!ListenerUtil.mutListener.listen(9927)) {
                    emailEditText.setText(email);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9929)) {
            new AlertDialog.Builder(mActivityContext).setTitle(R.string.travel_behavior_opt_in_title).setMessage(R.string.travel_behavior_email_message).setIcon(createIcon()).setCancelable(false).setView(editTextView).setPositiveButton(R.string.travel_behavior_dialog_email_save, (dialog, which) -> {
                String currentEmail = emailEditText.getText().toString();
                String currentEmailConfirm = emailEditTextConfirm.getText().toString();
                if (!TextUtils.isEmpty(currentEmail) && Patterns.EMAIL_ADDRESS.matcher(currentEmail).matches() && currentEmail.equalsIgnoreCase(currentEmailConfirm)) {
                    registerUser(currentEmail);
                    checkPermissions();
                } else {
                    Toast.makeText(mApplicationContext, R.string.travel_behavior_email_invalid, Toast.LENGTH_LONG).show();
                    // Show the dialog again if the email is invalid
                    showEmailDialog(currentEmail);
                }
            }).create().show();
        }
    }

    private void checkPermissions() {
        if (!ListenerUtil.mutListener.listen(9937)) {
            if (!PermissionUtils.hasGrantedAllPermissions(mApplicationContext, TravelBehaviorConstants.PERMISSIONS)) {
                HomeActivity homeActivity = (HomeActivity) mActivityContext;
                if (!ListenerUtil.mutListener.listen(9936)) {
                    if ((ListenerUtil.mutListener.listen(9934) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9933) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9932) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9931) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(9930) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                        if (!ListenerUtil.mutListener.listen(9935)) {
                            // Request activity permission here, and then background location the subsequent callback set up in HomeActivity
                            homeActivity.requestPhysicalActivityPermission();
                        }
                    }
                }
            }
        }
    }

    private void registerUser(String email) {
        Data myData = new Data.Builder().putString(TravelBehaviorConstants.USER_EMAIL, email).build();
        Constraints constraints = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(RegisterTravelBehaviorParticipantWorker.class).setInputData(myData).setConstraints(constraints).build();
        WorkManager workManager = WorkManager.getInstance();
        if (!ListenerUtil.mutListener.listen(9938)) {
            workManager.enqueue(workRequest);
        }
        ListenableFuture<WorkInfo> listenableFuture = workManager.getWorkInfoById(workRequest.getId());
        if (!ListenerUtil.mutListener.listen(9941)) {
            Futures.addCallback(listenableFuture, new FutureCallback<WorkInfo>() {

                @Override
                public void onSuccess(@NullableDecl WorkInfo result) {
                    AppCompatActivity activity = (AppCompatActivity) mActivityContext;
                    if (!ListenerUtil.mutListener.listen(9939)) {
                        activity.runOnUiThread(() -> Toast.makeText(mApplicationContext, R.string.travel_behavior_enroll_success, Toast.LENGTH_LONG).show());
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    AppCompatActivity activity = (AppCompatActivity) mActivityContext;
                    if (!ListenerUtil.mutListener.listen(9940)) {
                        activity.runOnUiThread(() -> Toast.makeText(mApplicationContext, R.string.travel_behavior_enroll_fail, Toast.LENGTH_LONG).show());
                    }
                }
            }, TravelBehaviorFileSaverExecutorManager.getInstance().getThreadPoolExecutor());
        }
    }

    public static void startCollectingData(Context applicationContext) {
        if (!ListenerUtil.mutListener.listen(9943)) {
            if (TravelBehaviorUtils.isUserParticipatingInStudy()) {
                if (!ListenerUtil.mutListener.listen(9942)) {
                    new TravelBehaviorManager(null, applicationContext).startCollectingData();
                }
            }
        }
    }

    private void startCollectingData() {
        int[] activities = { DetectedActivity.IN_VEHICLE, DetectedActivity.ON_BICYCLE, DetectedActivity.WALKING, DetectedActivity.STILL, DetectedActivity.RUNNING };
        List<ActivityTransition> transitions = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(9946)) {
            {
                long _loopCounter131 = 0;
                for (int activity : activities) {
                    ListenerUtil.loopListener.listen("_loopCounter131", ++_loopCounter131);
                    if (!ListenerUtil.mutListener.listen(9944)) {
                        transitions.add(new ActivityTransition.Builder().setActivityType(activity).setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER).build());
                    }
                    if (!ListenerUtil.mutListener.listen(9945)) {
                        transitions.add(new ActivityTransition.Builder().setActivityType(activity).setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_EXIT).build());
                    }
                }
            }
        }
        ActivityTransitionRequest atr = new ActivityTransitionRequest(transitions);
        Intent intent = new Intent(mApplicationContext, TransitionBroadcastReceiver.class);
        int flags;
        if ((ListenerUtil.mutListener.listen(9951) ? (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(9950) ? (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(9949) ? (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(9948) ? (android.os.Build.VERSION.SDK_INT != android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(9947) ? (android.os.Build.VERSION.SDK_INT == android.os.Build.VERSION_CODES.S) : (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S))))))) {
            flags = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE;
        } else {
            flags = PendingIntent.FLAG_UPDATE_CURRENT;
        }
        PendingIntent pi = PendingIntent.getBroadcast(mApplicationContext, 100, intent, flags);
        Task<Void> task = ActivityRecognition.getClient(mApplicationContext).requestActivityTransitionUpdates(atr, pi);
        if (!ListenerUtil.mutListener.listen(9952)) {
            task.addOnCompleteListener(task1 -> {
                if (task1.isSuccessful()) {
                    Log.d(TAG, "Travel behavior activity-transition-update set up");
                } else {
                    TravelBehaviorFirebaseIOUtils.logErrorMessage(task1.getException(), "Travel behavior activity-transition-update failed set up: ");
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(9953)) {
            saveDeviceInformation();
        }
    }

    private void saveDeviceInformation() {
        String uid = PreferenceUtils.getString(TravelBehaviorConstants.USER_ID);
        Data myData = new Data.Builder().putString(TravelBehaviorConstants.USER_ID, uid).build();
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(UpdateDeviceInfoWorker.class).setInputData(myData).build();
        if (!ListenerUtil.mutListener.listen(9954)) {
            WorkManager.getInstance().enqueue(workRequest);
        }
    }

    public void stopCollectingData() {
        Intent intent = new Intent(mApplicationContext, TransitionBroadcastReceiver.class);
        int flags;
        if ((ListenerUtil.mutListener.listen(9959) ? (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(9958) ? (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(9957) ? (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(9956) ? (android.os.Build.VERSION.SDK_INT != android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(9955) ? (android.os.Build.VERSION.SDK_INT == android.os.Build.VERSION_CODES.S) : (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S))))))) {
            flags = PendingIntent.FLAG_NO_CREATE | PendingIntent.FLAG_MUTABLE;
        } else {
            flags = PendingIntent.FLAG_NO_CREATE;
        }
        PendingIntent pi = PendingIntent.getBroadcast(mApplicationContext, 100, intent, flags);
        if (!ListenerUtil.mutListener.listen(9962)) {
            if (pi != null) {
                if (!ListenerUtil.mutListener.listen(9960)) {
                    ActivityRecognition.getClient(mApplicationContext).removeActivityUpdates(pi);
                }
                if (!ListenerUtil.mutListener.listen(9961)) {
                    pi.cancel();
                }
            }
        }
    }

    private Drawable createIcon() {
        Drawable icon = mApplicationContext.getResources().getDrawable(R.drawable.ic_light_bulb);
        if (!ListenerUtil.mutListener.listen(9963)) {
            DrawableCompat.setTint(icon, mApplicationContext.getResources().getColor(R.color.theme_primary));
        }
        return icon;
    }

    public static void optOutUser() {
        if (!ListenerUtil.mutListener.listen(9964)) {
            PreferenceUtils.saveBoolean(TravelBehaviorConstants.USER_OPT_OUT, true);
        }
        if (!ListenerUtil.mutListener.listen(9965)) {
            PreferenceUtils.saveBoolean(TravelBehaviorConstants.USER_OPT_IN, false);
        }
    }

    public static void optOutUserOnServer() {
        String uid = PreferenceUtils.getString(TravelBehaviorConstants.USER_ID);
        Data myData = new Data.Builder().putString(TravelBehaviorConstants.USER_ID, uid).build();
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(OptOutTravelBehaviorParticipantWorker.class).setInputData(myData).build();
        if (!ListenerUtil.mutListener.listen(9966)) {
            WorkManager.getInstance().enqueue(workRequest);
        }
    }

    public static void optInUser(String uid) {
        if (!ListenerUtil.mutListener.listen(9967)) {
            PreferenceUtils.saveString(TravelBehaviorConstants.USER_ID, uid);
        }
        if (!ListenerUtil.mutListener.listen(9968)) {
            PreferenceUtils.saveBoolean(TravelBehaviorConstants.USER_OPT_IN, true);
        }
        if (!ListenerUtil.mutListener.listen(9969)) {
            PreferenceUtils.saveBoolean(TravelBehaviorConstants.USER_OPT_OUT, false);
        }
    }

    public static void saveDestinationReminders(String currStopId, String destStopId, String tripId, String routeId, Long serverTime) {
        if (!ListenerUtil.mutListener.listen(9971)) {
            if (TravelBehaviorUtils.isUserParticipatingInStudy()) {
                DestinationReminderDataSaverTask saverTask = new DestinationReminderDataSaverTask(currStopId, destStopId, tripId, routeId, serverTime, Application.get().getApplicationContext());
                TravelBehaviorFileSaverExecutorManager manager = TravelBehaviorFileSaverExecutorManager.getInstance();
                if (!ListenerUtil.mutListener.listen(9970)) {
                    manager.runTask(saverTask);
                }
            }
        }
    }

    public static void saveArrivalInfo(ObaArrivalInfo[] info, String url, long serverTime, String stopId) {
        if (!ListenerUtil.mutListener.listen(9973)) {
            if (TravelBehaviorUtils.isUserParticipatingInStudy()) {
                ArrivalAndDepartureDataSaverTask saverTask = new ArrivalAndDepartureDataSaverTask(info, serverTime, url, stopId, Application.get().getApplicationContext());
                TravelBehaviorFileSaverExecutorManager manager = TravelBehaviorFileSaverExecutorManager.getInstance();
                if (!ListenerUtil.mutListener.listen(9972)) {
                    manager.runTask(saverTask);
                }
            }
        }
    }

    public static void saveTripPlan(TripPlan tripPlan, String url, Context applicationContext) {
        if (!ListenerUtil.mutListener.listen(9975)) {
            if (TravelBehaviorUtils.isUserParticipatingInStudy()) {
                TripPlanDataSaverTask dataSaverTask = new TripPlanDataSaverTask(tripPlan, url, applicationContext);
                TravelBehaviorFileSaverExecutorManager executorManager = TravelBehaviorFileSaverExecutorManager.getInstance();
                if (!ListenerUtil.mutListener.listen(9974)) {
                    executorManager.runTask(dataSaverTask);
                }
            }
        }
    }
}
