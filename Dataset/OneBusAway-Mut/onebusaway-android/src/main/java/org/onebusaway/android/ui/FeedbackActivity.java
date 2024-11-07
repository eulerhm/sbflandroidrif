package org.onebusaway.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.google.firebase.analytics.FirebaseAnalytics;
import org.apache.commons.io.FileUtils;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.ObaAnalytics;
import org.onebusaway.android.nav.NavigationService;
import org.onebusaway.android.nav.NavigationUploadWorker;
import org.onebusaway.android.util.PreferenceUtils;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import static android.text.TextUtils.isEmpty;
import static org.onebusaway.android.nav.NavigationService.LOG_DIRECTORY;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FeedbackActivity extends AppCompatActivity {

    public static final String TAG = "FeedbackActivity";

    public static final String TRIP_ID = ".TRIP_ID";

    public static final String NOTIFICATION_ID = ".NOTIFICATION_ID";

    public static final String RESPONSE = ".RESPONSE";

    public static final String LOG_FILE = ".LOG_FILE";

    public static final int FEEDBACK_NO = 1;

    public static final int FEEDBACK_YES = 2;

    private String mUserResponse = null;

    private String mLogFile = null;

    private ImageButton dislikeButton;

    private ImageButton likeButton;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3790)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(3791)) {
            setContentView(R.layout.activity_feedback);
        }
        if (!ListenerUtil.mutListener.listen(3792)) {
            setTitle(getResources().getString(R.string.feedback_label));
        }
        Intent intent = this.getIntent();
        CheckBox sendLogs = findViewById(R.id.feedback_send_logs);
        if (!ListenerUtil.mutListener.listen(3793)) {
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        }
        if (!ListenerUtil.mutListener.listen(3811)) {
            if (intent != null) {
                int response = intent.getIntExtra(RESPONSE, 0);
                if (!ListenerUtil.mutListener.listen(3794)) {
                    mLogFile = intent.getExtras().getString(LOG_FILE);
                }
                if (!ListenerUtil.mutListener.listen(3795)) {
                    Log.d(TAG, "Intent LOG_FILE :" + mLogFile);
                }
                if (!ListenerUtil.mutListener.listen(3803)) {
                    if ((ListenerUtil.mutListener.listen(3800) ? (response >= FEEDBACK_YES) : (ListenerUtil.mutListener.listen(3799) ? (response <= FEEDBACK_YES) : (ListenerUtil.mutListener.listen(3798) ? (response > FEEDBACK_YES) : (ListenerUtil.mutListener.listen(3797) ? (response < FEEDBACK_YES) : (ListenerUtil.mutListener.listen(3796) ? (response != FEEDBACK_YES) : (response == FEEDBACK_YES))))))) {
                        if (!ListenerUtil.mutListener.listen(3802)) {
                            mUserResponse = Application.get().getString(R.string.analytics_label_destination_reminder_yes);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3801)) {
                            mUserResponse = Application.get().getString(R.string.analytics_label_destination_reminder_no);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3810)) {
                    if (mUserResponse.equals(Application.get().getString(R.string.analytics_label_destination_reminder_no))) {
                        if (!ListenerUtil.mutListener.listen(3807)) {
                            Log.d(TAG, "Thumbs down tapped");
                        }
                        if (!ListenerUtil.mutListener.listen(3808)) {
                            dislikeButton = findViewById(R.id.ImageBtn_Dislike);
                        }
                        if (!ListenerUtil.mutListener.listen(3809)) {
                            dislikeButton.setSelected(true);
                        }
                    } else if (mUserResponse.equals(Application.get().getString(R.string.analytics_label_destination_reminder_yes))) {
                        if (!ListenerUtil.mutListener.listen(3804)) {
                            Log.d(TAG, "Thumbs up tapped");
                        }
                        if (!ListenerUtil.mutListener.listen(3805)) {
                            likeButton = findViewById(R.id.ImageBtn_like);
                        }
                        if (!ListenerUtil.mutListener.listen(3806)) {
                            likeButton.setSelected(true);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3814)) {
            if (Application.getPrefs().getBoolean(getString(R.string.preferences_key_user_share_destination_logs), true)) {
                if (!ListenerUtil.mutListener.listen(3813)) {
                    sendLogs.setChecked(true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3812)) {
                    sendLogs.setChecked(false);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(3815)) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.report_issue_action, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(3818)) {
            if (item.getItemId() == R.id.report_problem_send) {
                if (!ListenerUtil.mutListener.listen(3816)) {
                    submitFeedback();
                }
                if (!ListenerUtil.mutListener.listen(3817)) {
                    finish();
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void submitFeedback() {
        if (!ListenerUtil.mutListener.listen(3819)) {
            PreferenceUtils.saveBoolean(NavigationService.FIRST_FEEDBACK, false);
        }
        if (!ListenerUtil.mutListener.listen(3820)) {
            Log.d(TAG, "First Feedback : " + String.valueOf(Application.getPrefs().getBoolean(NavigationService.FIRST_FEEDBACK, true)));
        }
        String feedback = ((EditText) this.findViewById(R.id.editFeedbackText)).getText().toString();
        if (!ListenerUtil.mutListener.listen(3824)) {
            if (Application.getPrefs().getBoolean(getString(R.string.preferences_key_user_share_destination_logs), true)) {
                if (!ListenerUtil.mutListener.listen(3823)) {
                    moveLog(feedback);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3821)) {
                    deleteLog();
                }
                if (!ListenerUtil.mutListener.listen(3822)) {
                    logFeedback(feedback);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3825)) {
            Log.d(TAG, "Feedback send : " + feedback);
        }
        if (!ListenerUtil.mutListener.listen(3826)) {
            Toast.makeText(FeedbackActivity.this, getString(R.string.feedback_notify_confirmation), Toast.LENGTH_SHORT).show();
        }
    }

    public void likeBtnOnClick(View view) {
        if (!ListenerUtil.mutListener.listen(3827)) {
            mUserResponse = Application.get().getString(R.string.analytics_label_destination_reminder_yes);
        }
        if (!ListenerUtil.mutListener.listen(3828)) {
            likeButton = findViewById(R.id.ImageBtn_like);
        }
        if (!ListenerUtil.mutListener.listen(3829)) {
            dislikeButton = findViewById(R.id.ImageBtn_Dislike);
        }
        if (!ListenerUtil.mutListener.listen(3830)) {
            likeButton.setSelected(true);
        }
        if (!ListenerUtil.mutListener.listen(3831)) {
            dislikeButton.setSelected(false);
        }
        if (!ListenerUtil.mutListener.listen(3832)) {
            Log.d(TAG, "Feedback changed to yes");
        }
    }

    public void dislikeBtnOnClick(View view) {
        if (!ListenerUtil.mutListener.listen(3833)) {
            mUserResponse = Application.get().getString(R.string.analytics_label_destination_reminder_no);
        }
        if (!ListenerUtil.mutListener.listen(3834)) {
            likeButton = findViewById(R.id.ImageBtn_like);
        }
        if (!ListenerUtil.mutListener.listen(3835)) {
            dislikeButton = findViewById(R.id.ImageBtn_Dislike);
        }
        if (!ListenerUtil.mutListener.listen(3836)) {
            dislikeButton.setSelected(true);
        }
        if (!ListenerUtil.mutListener.listen(3837)) {
            likeButton.setSelected(false);
        }
        if (!ListenerUtil.mutListener.listen(3838)) {
            Log.d(TAG, "Feedback changed to no");
        }
    }

    private void moveLog(String feedback) {
        try {
            if (!ListenerUtil.mutListener.listen(3840)) {
                Log.d(TAG, "Log file: " + mLogFile);
            }
            File lFile = new File(mLogFile);
            if (!ListenerUtil.mutListener.listen(3841)) {
                FileUtils.write(lFile, System.getProperty("line.separator") + feedback, true);
            }
            if (!ListenerUtil.mutListener.listen(3842)) {
                Log.d(TAG, "Feedback appended");
            }
            File destFolder = new File(Application.get().getApplicationContext().getFilesDir().getAbsolutePath() + File.separator + LOG_DIRECTORY + File.separator + mUserResponse);
            if (!ListenerUtil.mutListener.listen(3843)) {
                Log.d(TAG, "sourceLocation: " + lFile);
            }
            if (!ListenerUtil.mutListener.listen(3844)) {
                Log.d(TAG, "targetLocation: " + destFolder);
            }
            try {
                if (!ListenerUtil.mutListener.listen(3846)) {
                    FileUtils.moveFileToDirectory(FileUtils.getFile(lFile), FileUtils.getFile(destFolder), true);
                }
                if (!ListenerUtil.mutListener.listen(3847)) {
                    Log.d(TAG, "Move file successful.");
                }
            } catch (Exception e) {
                if (!ListenerUtil.mutListener.listen(3845)) {
                    Log.e(TAG, "File move failed");
                }
            }
            if (!ListenerUtil.mutListener.listen(3848)) {
                setupLogUploadTask();
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(3839)) {
                Log.e(TAG, "File write failed: " + e.toString());
            }
        }
    }

    private void deleteLog() {
        File lFile = new File(mLogFile);
        boolean deleted = lFile.delete();
        if (!ListenerUtil.mutListener.listen(3849)) {
            Log.d(TAG, "Log deleted " + deleted);
        }
    }

    public void setSendLogs(View view) {
        CheckBox checkBox = (CheckBox) view;
        if (!ListenerUtil.mutListener.listen(3856)) {
            if (checkBox.isChecked()) {
                if (!ListenerUtil.mutListener.listen(3855)) {
                    if (!Application.getPrefs().getBoolean(getString(R.string.preferences_key_user_share_destination_logs), true)) {
                        if (!ListenerUtil.mutListener.listen(3853)) {
                            PreferenceUtils.saveBoolean(getString(R.string.preferences_key_user_share_destination_logs), true);
                        }
                        if (!ListenerUtil.mutListener.listen(3854)) {
                            Log.d(TAG, "User wants to share logs");
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3852)) {
                    if (Application.getPrefs().getBoolean(getString(R.string.preferences_key_user_share_destination_logs), true)) {
                        if (!ListenerUtil.mutListener.listen(3850)) {
                            PreferenceUtils.saveBoolean(getString(R.string.preferences_key_user_share_destination_logs), false);
                        }
                        if (!ListenerUtil.mutListener.listen(3851)) {
                            Log.d(TAG, "User doesn't want to share logs");
                        }
                    }
                }
            }
        }
    }

    private void setupLogUploadTask() {
        PeriodicWorkRequest.Builder uploadLogsBuilder = new PeriodicWorkRequest.Builder(NavigationUploadWorker.class, 24, TimeUnit.HOURS);
        // Create the actual work object
        PeriodicWorkRequest uploadCheckWork = uploadLogsBuilder.build();
        if (!ListenerUtil.mutListener.listen(3857)) {
            // Then enqueue the recurring task
            WorkManager.getInstance().enqueue(uploadCheckWork);
        }
    }

    private void logFeedback(String feedbackText) {
        Boolean wasGoodReminder;
        if (mUserResponse.equals(Application.get().getString(R.string.analytics_label_destination_reminder_yes))) {
            wasGoodReminder = true;
        } else {
            wasGoodReminder = false;
        }
        if (!ListenerUtil.mutListener.listen(3858)) {
            ObaAnalytics.reportDestinationReminderFeedback(mFirebaseAnalytics, wasGoodReminder, ((!isEmpty(feedbackText)) ? feedbackText : null), null);
        }
        if (!ListenerUtil.mutListener.listen(3859)) {
            Log.d(TAG, "User feedback logged to Firebase Analytics :: wasGoodReminder - " + wasGoodReminder + ", feedbackText - " + ((!isEmpty(feedbackText)) ? feedbackText : null));
        }
    }
}
