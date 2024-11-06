package fr.free.nrw.commons;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import fr.free.nrw.commons.databinding.ActivityWelcomeBinding;
import fr.free.nrw.commons.databinding.PopupForCopyrightBinding;
import fr.free.nrw.commons.quiz.QuizActivity;
import fr.free.nrw.commons.theme.BaseActivity;
import fr.free.nrw.commons.utils.ConfigUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WelcomeActivity extends BaseActivity {

    private ActivityWelcomeBinding binding;

    private PopupForCopyrightBinding copyrightBinding;

    private final WelcomePagerAdapter adapter = new WelcomePagerAdapter();

    private boolean isQuiz;

    private AlertDialog.Builder dialogBuilder;

    private AlertDialog dialog;

    /**
     * Initialises exiting fields and dependencies
     *
     * @param savedInstanceState WelcomeActivity bundled data
     */
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(9473)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(9474)) {
            binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        }
        final View view = binding.getRoot();
        if (!ListenerUtil.mutListener.listen(9475)) {
            setContentView(view);
        }
        if (!ListenerUtil.mutListener.listen(9479)) {
            if (getIntent() != null) {
                final Bundle bundle = getIntent().getExtras();
                if (!ListenerUtil.mutListener.listen(9478)) {
                    if (bundle != null) {
                        if (!ListenerUtil.mutListener.listen(9477)) {
                            isQuiz = bundle.getBoolean("isQuiz");
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9476)) {
                    isQuiz = false;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9487)) {
            // Enable skip button if beta flavor
            if (ConfigUtils.isBetaFlavour()) {
                if (!ListenerUtil.mutListener.listen(9480)) {
                    binding.finishTutorialButton.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(9481)) {
                    dialogBuilder = new AlertDialog.Builder(this);
                }
                if (!ListenerUtil.mutListener.listen(9482)) {
                    copyrightBinding = PopupForCopyrightBinding.inflate(getLayoutInflater());
                }
                final View contactPopupView = copyrightBinding.getRoot();
                if (!ListenerUtil.mutListener.listen(9483)) {
                    dialogBuilder.setView(contactPopupView);
                }
                if (!ListenerUtil.mutListener.listen(9484)) {
                    dialog = dialogBuilder.create();
                }
                if (!ListenerUtil.mutListener.listen(9485)) {
                    dialog.show();
                }
                if (!ListenerUtil.mutListener.listen(9486)) {
                    copyrightBinding.buttonOk.setOnClickListener(v -> dialog.dismiss());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9488)) {
            binding.welcomePager.setAdapter(adapter);
        }
        if (!ListenerUtil.mutListener.listen(9489)) {
            binding.welcomePagerIndicator.setViewPager(binding.welcomePager);
        }
        if (!ListenerUtil.mutListener.listen(9490)) {
            binding.finishTutorialButton.setOnClickListener(v -> finishTutorial());
        }
    }

    /**
     * References WelcomePageAdapter to null before the activity is destroyed
     */
    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(9492)) {
            if (isQuiz) {
                final Intent i = new Intent(this, QuizActivity.class);
                if (!ListenerUtil.mutListener.listen(9491)) {
                    startActivity(i);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9493)) {
            super.onDestroy();
        }
    }

    /**
     * Creates a way to change current activity to WelcomeActivity
     *
     * @param context Activity context
     */
    public static void startYourself(final Context context) {
        final Intent welcomeIntent = new Intent(context, WelcomeActivity.class);
        if (!ListenerUtil.mutListener.listen(9494)) {
            context.startActivity(welcomeIntent);
        }
    }

    /**
     * Override onBackPressed() to go to previous tutorial 'pages' if not on first page
     */
    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(9503)) {
            if (binding.welcomePager.getCurrentItem() != 0) {
                if (!ListenerUtil.mutListener.listen(9502)) {
                    binding.welcomePager.setCurrentItem((ListenerUtil.mutListener.listen(9501) ? (binding.welcomePager.getCurrentItem() % 1) : (ListenerUtil.mutListener.listen(9500) ? (binding.welcomePager.getCurrentItem() / 1) : (ListenerUtil.mutListener.listen(9499) ? (binding.welcomePager.getCurrentItem() * 1) : (ListenerUtil.mutListener.listen(9498) ? (binding.welcomePager.getCurrentItem() + 1) : (binding.welcomePager.getCurrentItem() - 1))))), true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9497)) {
                    if (defaultKvStore.getBoolean("firstrun", true)) {
                        if (!ListenerUtil.mutListener.listen(9496)) {
                            finishAffinity();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(9495)) {
                            super.onBackPressed();
                        }
                    }
                }
            }
        }
    }

    public void finishTutorial() {
        if (!ListenerUtil.mutListener.listen(9504)) {
            defaultKvStore.putBoolean("firstrun", false);
        }
        if (!ListenerUtil.mutListener.listen(9505)) {
            finish();
        }
    }
}
