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

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics;
import org.apache.commons.io.FileUtils;
import org.onebusaway.android.BuildConfig;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.ObaAnalytics;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import static android.text.TextUtils.isEmpty;
import static org.onebusaway.android.nav.NavigationService.LOG_DIRECTORY;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FeedbackReceiver extends BroadcastReceiver {

    public static final String TAG = "FeedbackReceiver";

    public static final String ACTION_REPLY = BuildConfig.APPLICATION_ID + ".action.REPLY";

    public static final String ACTION_DISMISS_FEEDBACK = BuildConfig.APPLICATION_ID + ".action.DISMISS_FEEDBACK";

    public static final String TRIP_ID = ".TRIP_ID";

    public static final String NOTIFICATION_ID = ".NOTIFICATION_ID";

    public static final String RESPONSE = ".RESPONSE";

    public static final String LOG_FILE = ".LOG_FILE";

    public static final int FEEDBACK_NO = 1;

    public static final int FEEDBACK_YES = 2;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!ListenerUtil.mutListener.listen(12751)) {
            Log.d(TAG, "onReceive");
        }
        if (!ListenerUtil.mutListener.listen(12752)) {
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
        }
        int notifyId = intent.getIntExtra(NOTIFICATION_ID, NavigationServiceProvider.NOTIFICATION_ID + 1);
        // int actionNum = intent.getIntExtra(ACTION_NUM, 0);
        String action = intent.getAction();
        if (!ListenerUtil.mutListener.listen(12756)) {
            if (action == ACTION_DISMISS_FEEDBACK) {
                if (!ListenerUtil.mutListener.listen(12755)) {
                    Log.d(TAG, "Dismiss intent");
                }
            } else if (action == ACTION_REPLY) {
                if (!ListenerUtil.mutListener.listen(12753)) {
                    Log.d(TAG, "Capturing user feedback from notification");
                }
                if (!ListenerUtil.mutListener.listen(12754)) {
                    captureFeedback(context, intent, notifyId);
                }
            }
        }
    }

    private void captureFeedback(Context context, Intent intent, int notifyId) {
        int response = intent.getIntExtra(RESPONSE, 0);
        String logFile = intent.getExtras().getString(LOG_FILE);
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        String userResponse = null;
        if (!ListenerUtil.mutListener.listen(12778)) {
            if (remoteInput != null) {
                if (!ListenerUtil.mutListener.listen(12757)) {
                    Log.d(TAG, "checked remoteInput ");
                }
                String feedback = remoteInput.getCharSequence(NavigationService.KEY_TEXT_REPLY).toString();
                if (!ListenerUtil.mutListener.listen(12758)) {
                    Log.d(TAG, "Feedback captured");
                }
                NotificationCompat.Builder repliedNotification = new NotificationCompat.Builder(context, Application.CHANNEL_DESTINATION_ALERT_ID).setSmallIcon(R.drawable.ic_stat_notification).setContentTitle(Application.get().getResources().getString(R.string.feedback_notify_title)).setContentText(Application.get().getResources().getString(R.string.feedback_notify_confirmation));
                if (!ListenerUtil.mutListener.listen(12759)) {
                    Log.d(TAG, "Thank you!");
                }
                if (!ListenerUtil.mutListener.listen(12760)) {
                    repliedNotification.setOngoing(false);
                }
                NotificationManager mNotificationManager = (NotificationManager) Application.get().getSystemService(Context.NOTIFICATION_SERVICE);
                if (!ListenerUtil.mutListener.listen(12761)) {
                    mNotificationManager.notify(notifyId, repliedNotification.build());
                }
                if (!ListenerUtil.mutListener.listen(12769)) {
                    if ((ListenerUtil.mutListener.listen(12766) ? (response >= FEEDBACK_YES) : (ListenerUtil.mutListener.listen(12765) ? (response <= FEEDBACK_YES) : (ListenerUtil.mutListener.listen(12764) ? (response > FEEDBACK_YES) : (ListenerUtil.mutListener.listen(12763) ? (response < FEEDBACK_YES) : (ListenerUtil.mutListener.listen(12762) ? (response != FEEDBACK_YES) : (response == FEEDBACK_YES))))))) {
                        if (!ListenerUtil.mutListener.listen(12768)) {
                            userResponse = Application.get().getString(R.string.analytics_label_destination_reminder_yes);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(12767)) {
                            userResponse = Application.get().getString(R.string.analytics_label_destination_reminder_no);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(12770)) {
                    Log.d(TAG, "cancelling notification");
                }
                if (!ListenerUtil.mutListener.listen(12771)) {
                    mNotificationManager.cancel(notifyId);
                }
                Boolean pref = Application.getPrefs().getBoolean(Application.get().getResources().getString(R.string.preferences_key_user_share_destination_logs), true);
                if (!ListenerUtil.mutListener.listen(12777)) {
                    if (pref) {
                        if (!ListenerUtil.mutListener.listen(12775)) {
                            Log.d(TAG, "True");
                        }
                        if (!ListenerUtil.mutListener.listen(12776)) {
                            moveLog(feedback, userResponse, logFile);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(12772)) {
                            Log.d(TAG, "False");
                        }
                        if (!ListenerUtil.mutListener.listen(12773)) {
                            deleteLog(logFile);
                        }
                        if (!ListenerUtil.mutListener.listen(12774)) {
                            logFeedback(feedback, userResponse);
                        }
                    }
                }
            }
        }
    }

    private void moveLog(String feedback, String userResponse, String logFile) {
        try {
            File lFile = new File(logFile);
            if (!ListenerUtil.mutListener.listen(12780)) {
                FileUtils.write(lFile, System.getProperty("line.separator") + "User Feedback - " + feedback, true);
            }
            if (!ListenerUtil.mutListener.listen(12781)) {
                Log.d(TAG, "Feedback appended");
            }
            File destFolder = new File(Application.get().getApplicationContext().getFilesDir().getAbsolutePath() + File.separator + LOG_DIRECTORY + File.separator + userResponse);
            if (!ListenerUtil.mutListener.listen(12782)) {
                Log.d(TAG, "sourceLocation: " + lFile);
            }
            if (!ListenerUtil.mutListener.listen(12783)) {
                Log.d(TAG, "targetLocation: " + destFolder);
            }
            try {
                if (!ListenerUtil.mutListener.listen(12785)) {
                    FileUtils.moveFileToDirectory(FileUtils.getFile(lFile), FileUtils.getFile(destFolder), true);
                }
                if (!ListenerUtil.mutListener.listen(12786)) {
                    Log.d(TAG, "Move file successful.");
                }
            } catch (Exception e) {
                if (!ListenerUtil.mutListener.listen(12784)) {
                    Log.d(TAG, "File move failed");
                }
            }
            if (!ListenerUtil.mutListener.listen(12787)) {
                setupLogUploadTask();
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(12779)) {
                Log.e(TAG, "File write failed: " + e.toString());
            }
        }
    }

    private void deleteLog(String logFile) {
        File lFile = new File(logFile);
        boolean deleted = lFile.delete();
        if (!ListenerUtil.mutListener.listen(12788)) {
            Log.v(TAG, "Log deleted " + deleted);
        }
    }

    private void setupLogUploadTask() {
        PeriodicWorkRequest.Builder uploadLogsBuilder = new PeriodicWorkRequest.Builder(NavigationUploadWorker.class, 24, TimeUnit.HOURS);
        // Create the actual work object:
        PeriodicWorkRequest uploadCheckWork = uploadLogsBuilder.build();
        if (!ListenerUtil.mutListener.listen(12789)) {
            // Then enqueue the recurring task:
            WorkManager.getInstance().enqueue(uploadCheckWork);
        }
    }

    private void logFeedback(String feedbackText, String userResponse) {
        Boolean wasGoodReminder;
        if (userResponse.equals(Application.get().getString(R.string.analytics_label_destination_reminder_yes))) {
            wasGoodReminder = true;
        } else {
            wasGoodReminder = false;
        }
        if (!ListenerUtil.mutListener.listen(12790)) {
            ObaAnalytics.reportDestinationReminderFeedback(mFirebaseAnalytics, wasGoodReminder, ((!isEmpty(feedbackText)) ? feedbackText : null), null);
        }
        if (!ListenerUtil.mutListener.listen(12791)) {
            Log.d(TAG, "User feedback logged to Firebase Analytics :: wasGoodReminder - " + wasGoodReminder + ", feedbackText - " + ((!isEmpty(feedbackText)) ? feedbackText : null));
        }
    }
}
