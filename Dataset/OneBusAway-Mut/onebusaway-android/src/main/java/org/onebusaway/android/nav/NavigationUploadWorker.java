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
package org.onebusaway.android.nav;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.ObaAnalytics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import static android.text.TextUtils.isEmpty;
import static org.onebusaway.android.nav.NavigationService.LOG_DIRECTORY;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NavigationUploadWorker extends Worker {

    public static final String TAG = "NavigationUploadWorker";

    private FirebaseAnalytics mFirebaseAnalytics;

    public NavigationUploadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        if (!ListenerUtil.mutListener.listen(12730)) {
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
        }
    }

    @NonNull
    @Override
    public Result doWork() {
        if (!ListenerUtil.mutListener.listen(12731)) {
            uploadLog(Application.get().getString(R.string.analytics_label_destination_reminder_yes));
        }
        if (!ListenerUtil.mutListener.listen(12732)) {
            uploadLog(Application.get().getString(R.string.analytics_label_destination_reminder_no));
        }
        return Result.success();
    }

    private void uploadLog(String response) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        File dir = new File(Application.get().getApplicationContext().getFilesDir().getAbsolutePath() + File.separator + LOG_DIRECTORY + File.separator + response);
        if (!ListenerUtil.mutListener.listen(12748)) {
            if (dir.exists()) {
                if (!ListenerUtil.mutListener.listen(12733)) {
                    Log.d(TAG, "Directory exists");
                }
                if (!ListenerUtil.mutListener.listen(12747)) {
                    {
                        long _loopCounter176 = 0;
                        for (File lFile : dir.listFiles()) {
                            ListenerUtil.loopListener.listen("_loopCounter176", ++_loopCounter176);
                            Uri file = Uri.fromFile(lFile);
                            String logFileName = lFile.getName();
                            StorageReference logRef = storageRef.child("android/destination_reminders/" + response + "/" + logFileName);
                            if (!ListenerUtil.mutListener.listen(12734)) {
                                Log.d(TAG, "Location : " + response + logFileName);
                            }
                            String sCurrentLine, feedbackText = "";
                            ;
                            try {
                                BufferedReader br = new BufferedReader(new FileReader(lFile.getAbsolutePath()));
                                if (!ListenerUtil.mutListener.listen(12738)) {
                                    {
                                        long _loopCounter175 = 0;
                                        while ((sCurrentLine = br.readLine()) != null) {
                                            ListenerUtil.loopListener.listen("_loopCounter175", ++_loopCounter175);
                                            if (!ListenerUtil.mutListener.listen(12736)) {
                                                System.out.println(sCurrentLine);
                                            }
                                            if (!ListenerUtil.mutListener.listen(12737)) {
                                                feedbackText = sCurrentLine;
                                            }
                                        }
                                    }
                                }
                            } catch (IOException e) {
                                if (!ListenerUtil.mutListener.listen(12735)) {
                                    e.printStackTrace();
                                }
                            }
                            StorageMetadata metadata = new StorageMetadata.Builder().setCustomMetadata("Response", response).setCustomMetadata("FeedbackText", feedbackText).build();
                            UploadTask uploadTask = logRef.putFile(file, metadata);
                            if (!ListenerUtil.mutListener.listen(12746)) {
                                uploadTask.addOnFailureListener(new OnFailureListener() {

                                    @Override
                                    public void onFailure(@NonNull Exception exception) {
                                        if (!ListenerUtil.mutListener.listen(12745)) {
                                            Log.e(TAG, "Log upload failed");
                                        }
                                    }
                                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        if (!ListenerUtil.mutListener.listen(12739)) {
                                            Log.d(TAG, logFileName + " uploaded successful");
                                        }
                                        String userResponse = taskSnapshot.getMetadata().getCustomMetadata(Application.get().getString(R.string.analytics_label_custom_metadata_response));
                                        String feedbackText = taskSnapshot.getMetadata().getCustomMetadata(Application.get().getString(R.string.analytics_label_custom_metadata_feedback));
                                        String fileURL = taskSnapshot.getStorage().getDownloadUrl().toString();
                                        if (!ListenerUtil.mutListener.listen(12740)) {
                                            Log.d(TAG, "Response - " + userResponse);
                                        }
                                        if (!ListenerUtil.mutListener.listen(12741)) {
                                            Log.d(TAG, "FeedbackText - " + feedbackText);
                                        }
                                        if (!ListenerUtil.mutListener.listen(12742)) {
                                            Log.d(TAG, "Download URL - " + fileURL);
                                        }
                                        if (!ListenerUtil.mutListener.listen(12743)) {
                                            logFeedback(feedbackText, userResponse, fileURL);
                                        }
                                        boolean deleted = lFile.delete();
                                        if (!ListenerUtil.mutListener.listen(12744)) {
                                            Log.v(TAG, logFileName + " deleted : " + deleted);
                                        }
                                    }
                                });
                            }
                        }
                    }
                }
            }
        }
    }

    private void logFeedback(String feedbackText, String userResponse, String fileName) {
        Boolean wasGoodReminder;
        if (userResponse.equals(Application.get().getString(R.string.analytics_label_destination_reminder_yes))) {
            wasGoodReminder = true;
        } else {
            wasGoodReminder = false;
        }
        if (!ListenerUtil.mutListener.listen(12749)) {
            ObaAnalytics.reportDestinationReminderFeedback(mFirebaseAnalytics, wasGoodReminder, ((!isEmpty(feedbackText)) ? feedbackText : null), fileName);
        }
        if (!ListenerUtil.mutListener.listen(12750)) {
            Log.d(TAG, "User feedback logged to Firebase Analytics :: wasGoodReminder - " + wasGoodReminder + ", feedbackText - " + ((!isEmpty(feedbackText)) ? feedbackText : null) + ", filename - " + fileName);
        }
    }
}
