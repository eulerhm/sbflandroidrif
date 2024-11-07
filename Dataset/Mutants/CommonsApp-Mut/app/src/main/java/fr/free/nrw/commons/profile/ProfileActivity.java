package fr.free.nrw.commons.profile;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.material.tabs.TabLayout;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.Utils;
import fr.free.nrw.commons.ViewPagerAdapter;
import fr.free.nrw.commons.auth.SessionManager;
import fr.free.nrw.commons.contributions.ContributionsFragment;
import fr.free.nrw.commons.explore.ParentViewPager;
import fr.free.nrw.commons.profile.achievements.AchievementsFragment;
import fr.free.nrw.commons.profile.leaderboard.LeaderboardFragment;
import fr.free.nrw.commons.theme.BaseActivity;
import fr.free.nrw.commons.utils.DialogUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * This activity will set two tabs, achievements and
 * each tab will have their own fragments
 */
public class ProfileActivity extends BaseActivity {

    private FragmentManager supportFragmentManager;

    @BindView(R.id.viewPager)
    ParentViewPager viewPager;

    @BindView(R.id.tab_layout)
    public TabLayout tabLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    SessionManager sessionManager;

    private ViewPagerAdapter viewPagerAdapter;

    private AchievementsFragment achievementsFragment;

    private LeaderboardFragment leaderboardFragment;

    public static final String KEY_USERNAME = "username";

    public static final String KEY_SHOULD_SHOW_CONTRIBUTIONS = "shouldShowContributions";

    String userName;

    private boolean shouldShowContributions;

    ContributionsFragment contributionsFragment;

    public void setScroll(boolean canScroll) {
        if (!ListenerUtil.mutListener.listen(5521)) {
            viewPager.setCanScroll(canScroll);
        }
    }

    @Override
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5522)) {
            super.onRestoreInstanceState(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(5525)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(5523)) {
                    userName = savedInstanceState.getString(KEY_USERNAME);
                }
                if (!ListenerUtil.mutListener.listen(5524)) {
                    shouldShowContributions = savedInstanceState.getBoolean(KEY_SHOULD_SHOW_CONTRIBUTIONS);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5526)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(5527)) {
            setContentView(R.layout.activity_profile);
        }
        if (!ListenerUtil.mutListener.listen(5528)) {
            ButterKnife.bind(this);
        }
        if (!ListenerUtil.mutListener.listen(5529)) {
            setSupportActionBar(toolbar);
        }
        if (!ListenerUtil.mutListener.listen(5530)) {
            toolbar.setNavigationOnClickListener(view -> {
                onSupportNavigateUp();
            });
        }
        if (!ListenerUtil.mutListener.listen(5531)) {
            userName = getIntent().getStringExtra(KEY_USERNAME);
        }
        if (!ListenerUtil.mutListener.listen(5532)) {
            setTitle(userName);
        }
        if (!ListenerUtil.mutListener.listen(5533)) {
            shouldShowContributions = getIntent().getBooleanExtra(KEY_SHOULD_SHOW_CONTRIBUTIONS, false);
        }
        if (!ListenerUtil.mutListener.listen(5534)) {
            supportFragmentManager = getSupportFragmentManager();
        }
        if (!ListenerUtil.mutListener.listen(5535)) {
            viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        }
        if (!ListenerUtil.mutListener.listen(5536)) {
            viewPager.setAdapter(viewPagerAdapter);
        }
        if (!ListenerUtil.mutListener.listen(5537)) {
            tabLayout.setupWithViewPager(viewPager);
        }
        if (!ListenerUtil.mutListener.listen(5538)) {
            setTabs();
        }
    }

    /**
     * Navigate up event
     * @return boolean
     */
    @Override
    public boolean onSupportNavigateUp() {
        if (!ListenerUtil.mutListener.listen(5539)) {
            onBackPressed();
        }
        return true;
    }

    /**
     * Creates a way to change current activity to AchievementActivity
     *
     * @param context
     */
    public static void startYourself(final Context context, final String userName, final boolean shouldShowContributions) {
        Intent intent = new Intent(context, ProfileActivity.class);
        if (!ListenerUtil.mutListener.listen(5540)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        if (!ListenerUtil.mutListener.listen(5541)) {
            intent.putExtra(KEY_USERNAME, userName);
        }
        if (!ListenerUtil.mutListener.listen(5542)) {
            intent.putExtra(KEY_SHOULD_SHOW_CONTRIBUTIONS, shouldShowContributions);
        }
        if (!ListenerUtil.mutListener.listen(5543)) {
            context.startActivity(intent);
        }
    }

    /**
     * Set the tabs for the fragments
     */
    private void setTabs() {
        List<Fragment> fragmentList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(5544)) {
            achievementsFragment = new AchievementsFragment();
        }
        Bundle achievementsBundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(5545)) {
            achievementsBundle.putString(KEY_USERNAME, userName);
        }
        if (!ListenerUtil.mutListener.listen(5546)) {
            achievementsFragment.setArguments(achievementsBundle);
        }
        if (!ListenerUtil.mutListener.listen(5547)) {
            fragmentList.add(achievementsFragment);
        }
        if (!ListenerUtil.mutListener.listen(5548)) {
            titleList.add(getResources().getString(R.string.achievements_tab_title).toUpperCase());
        }
        if (!ListenerUtil.mutListener.listen(5549)) {
            leaderboardFragment = new LeaderboardFragment();
        }
        Bundle leaderBoardBundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(5550)) {
            leaderBoardBundle.putString(KEY_USERNAME, userName);
        }
        if (!ListenerUtil.mutListener.listen(5551)) {
            leaderboardFragment.setArguments(leaderBoardBundle);
        }
        if (!ListenerUtil.mutListener.listen(5552)) {
            fragmentList.add(leaderboardFragment);
        }
        if (!ListenerUtil.mutListener.listen(5553)) {
            titleList.add(getResources().getString(R.string.leaderboard_tab_title).toUpperCase());
        }
        if (!ListenerUtil.mutListener.listen(5554)) {
            contributionsFragment = new ContributionsFragment();
        }
        Bundle contributionsListBundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(5555)) {
            contributionsListBundle.putString(KEY_USERNAME, userName);
        }
        if (!ListenerUtil.mutListener.listen(5556)) {
            contributionsFragment.setArguments(contributionsListBundle);
        }
        if (!ListenerUtil.mutListener.listen(5557)) {
            fragmentList.add(contributionsFragment);
        }
        if (!ListenerUtil.mutListener.listen(5558)) {
            titleList.add(getString(R.string.contributions_fragment).toUpperCase());
        }
        if (!ListenerUtil.mutListener.listen(5559)) {
            viewPagerAdapter.setTabData(fragmentList, titleList);
        }
        if (!ListenerUtil.mutListener.listen(5560)) {
            viewPagerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(5561)) {
            super.onDestroy();
        }
        if (!ListenerUtil.mutListener.listen(5562)) {
            compositeDisposable.clear();
        }
    }

    /**
     * To inflate menu
     * @param menu Menu
     * @return boolean
     */
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        final MenuInflater menuInflater = getMenuInflater();
        if (!ListenerUtil.mutListener.listen(5563)) {
            menuInflater.inflate(R.menu.menu_about, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * To receive the id of selected item and handle further logic for that selected item
     * @param item MenuItem
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (!ListenerUtil.mutListener.listen(5565)) {
            // take screenshot in form of bitmap and show it in Alert Dialog
            if (item.getItemId() == R.id.share_app_icon) {
                final View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
                final Bitmap screenShot = Utils.getScreenShot(rootView);
                if (!ListenerUtil.mutListener.listen(5564)) {
                    showAlert(screenShot);
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * It displays the alertDialog with Image of screenshot
     * @param screenshot screenshot of the present screen
     */
    public void showAlert(final Bitmap screenshot) {
        final LayoutInflater factory = LayoutInflater.from(this);
        final View view = factory.inflate(R.layout.image_alert_layout, null);
        final ImageView screenShotImage = view.findViewById(R.id.alert_image);
        if (!ListenerUtil.mutListener.listen(5566)) {
            screenShotImage.setImageBitmap(screenshot);
        }
        final TextView shareMessage = view.findViewById(R.id.alert_text);
        if (!ListenerUtil.mutListener.listen(5567)) {
            shareMessage.setText(R.string.achievements_share_message);
        }
        if (!ListenerUtil.mutListener.listen(5568)) {
            DialogUtil.showAlertDialog(this, null, null, getString(R.string.about_translate_proceed), getString(R.string.cancel), () -> shareScreen(screenshot), () -> {
            }, view, true);
        }
    }

    /**
     * To take bitmap and store it temporary storage and share it
     * @param bitmap bitmap of screenshot
     */
    void shareScreen(final Bitmap bitmap) {
        try {
            final File file = new File(getExternalCacheDir(), "screen.png");
            final FileOutputStream fileOutputStream = new FileOutputStream(file);
            if (!ListenerUtil.mutListener.listen(5570)) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            }
            if (!ListenerUtil.mutListener.listen(5571)) {
                fileOutputStream.flush();
            }
            if (!ListenerUtil.mutListener.listen(5572)) {
                fileOutputStream.close();
            }
            if (!ListenerUtil.mutListener.listen(5573)) {
                file.setReadable(true, false);
            }
            final Uri fileUri = FileProvider.getUriForFile(getApplicationContext(), getPackageName() + ".provider", file);
            if (!ListenerUtil.mutListener.listen(5574)) {
                grantUriPermission(getPackageName(), fileUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            if (!ListenerUtil.mutListener.listen(5575)) {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            if (!ListenerUtil.mutListener.listen(5576)) {
                intent.putExtra(Intent.EXTRA_STREAM, fileUri);
            }
            if (!ListenerUtil.mutListener.listen(5577)) {
                intent.setType("image/png");
            }
            if (!ListenerUtil.mutListener.listen(5578)) {
                startActivity(Intent.createChooser(intent, getString(R.string.share_image_via)));
            }
        } catch (final IOException e) {
            if (!ListenerUtil.mutListener.listen(5569)) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull final Bundle outState) {
        if (!ListenerUtil.mutListener.listen(5579)) {
            outState.putString(KEY_USERNAME, userName);
        }
        if (!ListenerUtil.mutListener.listen(5580)) {
            outState.putBoolean(KEY_SHOULD_SHOW_CONTRIBUTIONS, shouldShowContributions);
        }
        if (!ListenerUtil.mutListener.listen(5581)) {
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(5587)) {
            // Checking if MediaDetailPagerFragment is visible, If visible then show ContributionListFragment else close the ProfileActivity
            if ((ListenerUtil.mutListener.listen(5583) ? ((ListenerUtil.mutListener.listen(5582) ? (contributionsFragment != null || contributionsFragment.getMediaDetailPagerFragment() != null) : (contributionsFragment != null && contributionsFragment.getMediaDetailPagerFragment() != null)) || contributionsFragment.getMediaDetailPagerFragment().isVisible()) : ((ListenerUtil.mutListener.listen(5582) ? (contributionsFragment != null || contributionsFragment.getMediaDetailPagerFragment() != null) : (contributionsFragment != null && contributionsFragment.getMediaDetailPagerFragment() != null)) && contributionsFragment.getMediaDetailPagerFragment().isVisible()))) {
                if (!ListenerUtil.mutListener.listen(5585)) {
                    contributionsFragment.backButtonClicked();
                }
                if (!ListenerUtil.mutListener.listen(5586)) {
                    tabLayout.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5584)) {
                    super.onBackPressed();
                }
            }
        }
    }
}
