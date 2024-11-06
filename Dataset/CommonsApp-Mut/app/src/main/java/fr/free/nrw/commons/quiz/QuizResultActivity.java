package fr.free.nrw.commons.quiz;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.dinuscxj.progressbar.CircleProgressBar;
import fr.free.nrw.commons.databinding.ActivityQuizResultBinding;
import java.io.File;
import java.io.FileOutputStream;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.contributions.MainActivity;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 *  Displays the final score of quiz and congratulates the user
 */
public class QuizResultActivity extends AppCompatActivity {

    private ActivityQuizResultBinding binding;

    private final int NUMBER_OF_QUESTIONS = 5;

    private final int MULTIPLIER_TO_GET_PERCENTAGE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1950)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1951)) {
            binding = ActivityQuizResultBinding.inflate(getLayoutInflater());
        }
        if (!ListenerUtil.mutListener.listen(1952)) {
            setContentView(binding.getRoot());
        }
        if (!ListenerUtil.mutListener.listen(1953)) {
            setSupportActionBar(binding.toolbar.toolbar);
        }
        if (!ListenerUtil.mutListener.listen(1954)) {
            binding.quizResultNext.setOnClickListener(view -> launchContributionActivity());
        }
        if (!ListenerUtil.mutListener.listen(1958)) {
            if (getIntent() != null) {
                Bundle extras = getIntent().getExtras();
                int score = extras.getInt("QuizResult");
                if (!ListenerUtil.mutListener.listen(1957)) {
                    setScore(score);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1955)) {
                    startActivityWithFlags(this, MainActivity.class, Intent.FLAG_ACTIVITY_CLEAR_TOP, Intent.FLAG_ACTIVITY_SINGLE_TOP);
                }
                if (!ListenerUtil.mutListener.listen(1956)) {
                    super.onBackPressed();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(1959)) {
            binding = null;
        }
        if (!ListenerUtil.mutListener.listen(1960)) {
            super.onDestroy();
        }
    }

    /**
     * to calculate and display percentage and score
     * @param score
     */
    public void setScore(int score) {
        int per = (ListenerUtil.mutListener.listen(1964) ? (score % MULTIPLIER_TO_GET_PERCENTAGE) : (ListenerUtil.mutListener.listen(1963) ? (score / MULTIPLIER_TO_GET_PERCENTAGE) : (ListenerUtil.mutListener.listen(1962) ? (score - MULTIPLIER_TO_GET_PERCENTAGE) : (ListenerUtil.mutListener.listen(1961) ? (score + MULTIPLIER_TO_GET_PERCENTAGE) : (score * MULTIPLIER_TO_GET_PERCENTAGE)))));
        if (!ListenerUtil.mutListener.listen(1965)) {
            binding.resultProgressBar.setProgress(per);
        }
        if (!ListenerUtil.mutListener.listen(1966)) {
            binding.resultProgressBar.setProgressTextFormatPattern(score + " / " + NUMBER_OF_QUESTIONS);
        }
        String message = getResources().getString(R.string.congratulatory_message_quiz, per + "%");
        if (!ListenerUtil.mutListener.listen(1967)) {
            binding.congratulatoryMessage.setText(message);
        }
    }

    /**
     * to go to Contibutions Activity
     */
    public void launchContributionActivity() {
        if (!ListenerUtil.mutListener.listen(1968)) {
            startActivityWithFlags(this, MainActivity.class, Intent.FLAG_ACTIVITY_CLEAR_TOP, Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(1969)) {
            startActivityWithFlags(this, MainActivity.class, Intent.FLAG_ACTIVITY_CLEAR_TOP, Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        if (!ListenerUtil.mutListener.listen(1970)) {
            super.onBackPressed();
        }
    }

    /**
     * Function to call intent to an activity
     * @param context
     * @param cls
     * @param flags
     * @param <T>
     */
    public static <T> void startActivityWithFlags(Context context, Class<T> cls, int... flags) {
        Intent intent = new Intent(context, cls);
        if (!ListenerUtil.mutListener.listen(1972)) {
            {
                long _loopCounter27 = 0;
                for (int flag : flags) {
                    ListenerUtil.loopListener.listen("_loopCounter27", ++_loopCounter27);
                    if (!ListenerUtil.mutListener.listen(1971)) {
                        intent.addFlags(flag);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1973)) {
            context.startActivity(intent);
        }
    }

    /**
     * to inflate menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(1974)) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_about, menu);
        }
        return true;
    }

    /**
     * if share option selected then take screenshot and launch alert
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (!ListenerUtil.mutListener.listen(1976)) {
            if (id == R.id.share_app_icon) {
                View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
                Bitmap screenShot = getScreenShot(rootView);
                if (!ListenerUtil.mutListener.listen(1975)) {
                    showAlert(screenShot);
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * to store the screenshot of image in bitmap variable temporarily
     * @param view
     * @return
     */
    public static Bitmap getScreenShot(View view) {
        View screenView = view.getRootView();
        if (!ListenerUtil.mutListener.listen(1977)) {
            screenView.setDrawingCacheEnabled(true);
        }
        Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
        if (!ListenerUtil.mutListener.listen(1978)) {
            screenView.setDrawingCacheEnabled(false);
        }
        return bitmap;
    }

    /**
     * share the screenshot through social media
     * @param bitmap
     */
    void shareScreen(Bitmap bitmap) {
        try {
            File file = new File(this.getExternalCacheDir(), "screen.png");
            FileOutputStream fOut = new FileOutputStream(file);
            if (!ListenerUtil.mutListener.listen(1980)) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            }
            if (!ListenerUtil.mutListener.listen(1981)) {
                fOut.flush();
            }
            if (!ListenerUtil.mutListener.listen(1982)) {
                fOut.close();
            }
            if (!ListenerUtil.mutListener.listen(1983)) {
                file.setReadable(true, false);
            }
            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            if (!ListenerUtil.mutListener.listen(1984)) {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            if (!ListenerUtil.mutListener.listen(1985)) {
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            }
            if (!ListenerUtil.mutListener.listen(1986)) {
                intent.setType("image/png");
            }
            if (!ListenerUtil.mutListener.listen(1987)) {
                startActivity(Intent.createChooser(intent, getString(R.string.share_image_via)));
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(1979)) {
                e.printStackTrace();
            }
        }
    }

    /**
     * It display the alertDialog with Image of screenshot
     * @param screenshot
     */
    public void showAlert(Bitmap screenshot) {
        AlertDialog.Builder alertadd = new AlertDialog.Builder(QuizResultActivity.this);
        LayoutInflater factory = LayoutInflater.from(QuizResultActivity.this);
        final View view = factory.inflate(R.layout.image_alert_layout, null);
        ImageView screenShotImage = view.findViewById(R.id.alert_image);
        if (!ListenerUtil.mutListener.listen(1988)) {
            screenShotImage.setImageBitmap(screenshot);
        }
        TextView shareMessage = view.findViewById(R.id.alert_text);
        if (!ListenerUtil.mutListener.listen(1989)) {
            shareMessage.setText(R.string.quiz_result_share_message);
        }
        if (!ListenerUtil.mutListener.listen(1990)) {
            alertadd.setView(view);
        }
        if (!ListenerUtil.mutListener.listen(1991)) {
            alertadd.setPositiveButton(R.string.about_translate_proceed, (dialog, which) -> shareScreen(screenshot));
        }
        if (!ListenerUtil.mutListener.listen(1992)) {
            alertadd.setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.cancel());
        }
        if (!ListenerUtil.mutListener.listen(1993)) {
            alertadd.show();
        }
    }
}
