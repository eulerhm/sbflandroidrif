package fr.free.nrw.commons.contributions;

import android.Manifest.permission;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.work.ExistingWorkPolicy;
import butterknife.BindView;
import butterknife.ButterKnife;
import fr.free.nrw.commons.CommonsApplication;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.WelcomeActivity;
import fr.free.nrw.commons.auth.SessionManager;
import fr.free.nrw.commons.bookmarks.BookmarkFragment;
import fr.free.nrw.commons.explore.ExploreFragment;
import fr.free.nrw.commons.kvstore.JsonKvStore;
import fr.free.nrw.commons.location.LocationServiceManager;
import fr.free.nrw.commons.media.MediaDetailPagerFragment;
import fr.free.nrw.commons.navtab.MoreBottomSheetFragment;
import fr.free.nrw.commons.navtab.MoreBottomSheetLoggedOutFragment;
import fr.free.nrw.commons.navtab.NavTab;
import fr.free.nrw.commons.navtab.NavTabLayout;
import fr.free.nrw.commons.navtab.NavTabLoggedOut;
import fr.free.nrw.commons.nearby.Place;
import fr.free.nrw.commons.nearby.fragments.NearbyParentFragment;
import fr.free.nrw.commons.nearby.fragments.NearbyParentFragment.NearbyParentFragmentInstanceReadyCallback;
import fr.free.nrw.commons.notification.NotificationActivity;
import fr.free.nrw.commons.notification.NotificationController;
import fr.free.nrw.commons.quiz.QuizChecker;
import fr.free.nrw.commons.settings.SettingsFragment;
import fr.free.nrw.commons.theme.BaseActivity;
import fr.free.nrw.commons.upload.worker.WorkRequestHelper;
import fr.free.nrw.commons.utils.PermissionUtils;
import fr.free.nrw.commons.utils.ViewUtilWrapper;
import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MainActivity extends BaseActivity implements FragmentManager.OnBackStackChangedListener {

    @Inject
    SessionManager sessionManager;

    @Inject
    ContributionController controller;

    @Inject
    ContributionDao contributionDao;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.pager)
    public UnswipableViewPager viewPager;

    @BindView(R.id.fragmentContainer)
    public FrameLayout fragmentContainer;

    @BindView(R.id.fragment_main_nav_tab_layout)
    NavTabLayout tabLayout;

    private ContributionsFragment contributionsFragment;

    private NearbyParentFragment nearbyParentFragment;

    private ExploreFragment exploreFragment;

    private BookmarkFragment bookmarkFragment;

    public ActiveFragment activeFragment;

    private MediaDetailPagerFragment mediaDetailPagerFragment;

    private NavTabLayout.OnNavigationItemSelectedListener navListener;

    @Inject
    public LocationServiceManager locationManager;

    @Inject
    NotificationController notificationController;

    @Inject
    QuizChecker quizChecker;

    @Inject
    @Named("default_preferences")
    public JsonKvStore applicationKvStore;

    @Inject
    ViewUtilWrapper viewUtilWrapper;

    public Menu menu;

    /**
     * Consumers should be simply using this method to use this activity.
     *
     * @param context A Context of the application package implementing this class.
     */
    public static void startYourself(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        if (!ListenerUtil.mutListener.listen(619)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        if (!ListenerUtil.mutListener.listen(620)) {
            context.startActivity(intent);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (!ListenerUtil.mutListener.listen(624)) {
            if (activeFragment == ActiveFragment.CONTRIBUTIONS) {
                if (!ListenerUtil.mutListener.listen(623)) {
                    if (!contributionsFragment.backButtonClicked()) {
                        return false;
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(621)) {
                    onBackPressed();
                }
                if (!ListenerUtil.mutListener.listen(622)) {
                    showTabs();
                }
            }
        }
        return true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(625)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(626)) {
            loadLocale();
        }
        if (!ListenerUtil.mutListener.listen(627)) {
            setContentView(R.layout.main);
        }
        if (!ListenerUtil.mutListener.listen(628)) {
            ButterKnife.bind(this);
        }
        if (!ListenerUtil.mutListener.listen(629)) {
            setSupportActionBar(toolbar);
        }
        if (!ListenerUtil.mutListener.listen(630)) {
            toolbar.setNavigationOnClickListener(view -> {
                onSupportNavigateUp();
            });
        }
        if (!ListenerUtil.mutListener.listen(631)) {
            /*
        "first_edit_depict" is a key for getting information about opening the depiction editor
        screen for the first time after opening the app.

        Getting true by the key means the depiction editor screen is opened for the first time
        after opening the app.
        Getting false by the key means the depiction editor screen is not opened for the first time
        after opening the app.
         */
            applicationKvStore.putBoolean("first_edit_depict", true);
        }
        if (!ListenerUtil.mutListener.listen(652)) {
            if (applicationKvStore.getBoolean("login_skipped") == true) {
                if (!ListenerUtil.mutListener.listen(650)) {
                    setTitle(getString(R.string.navigation_item_explore));
                }
                if (!ListenerUtil.mutListener.listen(651)) {
                    setUpLoggedOutPager();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(633)) {
                    if (applicationKvStore.getBoolean("firstrun", true)) {
                        if (!ListenerUtil.mutListener.listen(632)) {
                            applicationKvStore.putBoolean("hasAlreadyLaunchedBigMultiupload", false);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(640)) {
                    if (savedInstanceState == null) {
                        if (!ListenerUtil.mutListener.listen(639)) {
                            // Open Last opened screen if it is Contributions or Nearby, otherwise Contributions
                            if (applicationKvStore.getBoolean("last_opened_nearby")) {
                                if (!ListenerUtil.mutListener.listen(636)) {
                                    setTitle(getString(R.string.nearby_fragment));
                                }
                                if (!ListenerUtil.mutListener.listen(637)) {
                                    showNearby();
                                }
                                if (!ListenerUtil.mutListener.listen(638)) {
                                    loadFragment(NearbyParentFragment.newInstance(), false);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(634)) {
                                    setTitle(getString(R.string.contributions_fragment));
                                }
                                if (!ListenerUtil.mutListener.listen(635)) {
                                    loadFragment(ContributionsFragment.newInstance(), false);
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(641)) {
                    setUpPager();
                }
                if (!ListenerUtil.mutListener.listen(648)) {
                    /**
                     * Ask the user for media location access just after login
                     * so that location in the EXIF metadata of the images shared by the user
                     * is retained on devices running Android 10 or above
                     */
                    if ((ListenerUtil.mutListener.listen(646) ? (VERSION.SDK_INT <= VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(645) ? (VERSION.SDK_INT > VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(644) ? (VERSION.SDK_INT < VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(643) ? (VERSION.SDK_INT != VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(642) ? (VERSION.SDK_INT == VERSION_CODES.Q) : (VERSION.SDK_INT >= VERSION_CODES.Q))))))) {
                        if (!ListenerUtil.mutListener.listen(647)) {
                            PermissionUtils.checkPermissionsAndPerformAction(this, () -> {
                            }, R.string.media_location_permission_denied, R.string.add_location_manually, permission.ACCESS_MEDIA_LOCATION);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(649)) {
                    checkAndResumeStuckUploads();
                }
            }
        }
    }

    public void setSelectedItemId(int id) {
        if (!ListenerUtil.mutListener.listen(653)) {
            tabLayout.setSelectedItemId(id);
        }
    }

    private void setUpPager() {
        if (!ListenerUtil.mutListener.listen(654)) {
            tabLayout.setOnNavigationItemSelectedListener(navListener = (item) -> {
                if (!item.getTitle().equals(getString(R.string.more))) {
                    // do not change title for more fragment
                    setTitle(item.getTitle());
                }
                // set last_opened_nearby true if item is nearby screen else set false
                applicationKvStore.putBoolean("last_opened_nearby", item.getTitle().equals(getString(R.string.nearby_fragment)));
                final Fragment fragment = NavTab.of(item.getOrder()).newInstance();
                return loadFragment(fragment, true);
            });
        }
    }

    private void setUpLoggedOutPager() {
        if (!ListenerUtil.mutListener.listen(655)) {
            loadFragment(ExploreFragment.newInstance(), false);
        }
        if (!ListenerUtil.mutListener.listen(656)) {
            tabLayout.setOnNavigationItemSelectedListener(item -> {
                if (!item.getTitle().equals(getString(R.string.more))) {
                    // do not change title for more fragment
                    setTitle(item.getTitle());
                }
                Fragment fragment = NavTabLoggedOut.of(item.getOrder()).newInstance();
                return loadFragment(fragment, true);
            });
        }
    }

    private boolean loadFragment(Fragment fragment, boolean showBottom) {
        if (!ListenerUtil.mutListener.listen(674)) {
            // from the saved instance state.
            if (fragment instanceof ContributionsFragment) {
                if (!ListenerUtil.mutListener.listen(671)) {
                    if (activeFragment == ActiveFragment.CONTRIBUTIONS) {
                        if (!ListenerUtil.mutListener.listen(670)) {
                            // scroll to top if already on the Contributions tab
                            contributionsFragment.scrollToTop();
                        }
                        return true;
                    }
                }
                if (!ListenerUtil.mutListener.listen(672)) {
                    contributionsFragment = (ContributionsFragment) fragment;
                }
                if (!ListenerUtil.mutListener.listen(673)) {
                    activeFragment = ActiveFragment.CONTRIBUTIONS;
                }
            } else if (fragment instanceof NearbyParentFragment) {
                if (!ListenerUtil.mutListener.listen(667)) {
                    if (activeFragment == ActiveFragment.NEARBY) {
                        // Do nothing if same tab
                        return true;
                    }
                }
                if (!ListenerUtil.mutListener.listen(668)) {
                    nearbyParentFragment = (NearbyParentFragment) fragment;
                }
                if (!ListenerUtil.mutListener.listen(669)) {
                    activeFragment = ActiveFragment.NEARBY;
                }
            } else if (fragment instanceof ExploreFragment) {
                if (!ListenerUtil.mutListener.listen(664)) {
                    if (activeFragment == ActiveFragment.EXPLORE) {
                        // Do nothing if same tab
                        return true;
                    }
                }
                if (!ListenerUtil.mutListener.listen(665)) {
                    exploreFragment = (ExploreFragment) fragment;
                }
                if (!ListenerUtil.mutListener.listen(666)) {
                    activeFragment = ActiveFragment.EXPLORE;
                }
            } else if (fragment instanceof BookmarkFragment) {
                if (!ListenerUtil.mutListener.listen(661)) {
                    if (activeFragment == ActiveFragment.BOOKMARK) {
                        // Do nothing if same tab
                        return true;
                    }
                }
                if (!ListenerUtil.mutListener.listen(662)) {
                    bookmarkFragment = (BookmarkFragment) fragment;
                }
                if (!ListenerUtil.mutListener.listen(663)) {
                    activeFragment = ActiveFragment.BOOKMARK;
                }
            } else if ((ListenerUtil.mutListener.listen(657) ? (fragment == null || showBottom) : (fragment == null && showBottom))) {
                if (!ListenerUtil.mutListener.listen(660)) {
                    if (applicationKvStore.getBoolean("login_skipped") == true) {
                        // If logged out, more sheet is different
                        MoreBottomSheetLoggedOutFragment bottomSheet = new MoreBottomSheetLoggedOutFragment();
                        if (!ListenerUtil.mutListener.listen(659)) {
                            bottomSheet.show(getSupportFragmentManager(), "MoreBottomSheetLoggedOut");
                        }
                    } else {
                        MoreBottomSheetFragment bottomSheet = new MoreBottomSheetFragment();
                        if (!ListenerUtil.mutListener.listen(658)) {
                            bottomSheet.show(getSupportFragmentManager(), "MoreBottomSheet");
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(676)) {
            if (fragment != null) {
                if (!ListenerUtil.mutListener.listen(675)) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
                }
                return true;
            }
        }
        return false;
    }

    public void hideTabs() {
        if (!ListenerUtil.mutListener.listen(677)) {
            tabLayout.setVisibility(View.GONE);
        }
    }

    public void showTabs() {
        if (!ListenerUtil.mutListener.listen(678)) {
            tabLayout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Adds number of uploads next to tab text "Contributions" then it will look like
     * "Contributions (NUMBER)"
     * @param uploadCount
     */
    public void setNumOfUploads(int uploadCount) {
        if (!ListenerUtil.mutListener.listen(685)) {
            if (activeFragment == ActiveFragment.CONTRIBUTIONS) {
                if (!ListenerUtil.mutListener.listen(684)) {
                    setTitle(getResources().getString(R.string.contributions_fragment) + " " + (!((ListenerUtil.mutListener.listen(683) ? (uploadCount >= 0) : (ListenerUtil.mutListener.listen(682) ? (uploadCount <= 0) : (ListenerUtil.mutListener.listen(681) ? (uploadCount > 0) : (ListenerUtil.mutListener.listen(680) ? (uploadCount < 0) : (ListenerUtil.mutListener.listen(679) ? (uploadCount != 0) : (uploadCount == 0))))))) ? getResources().getQuantityString(R.plurals.contributions_subtitle, uploadCount, uploadCount) : getString(R.string.contributions_subtitle_zero)));
                }
            }
        }
    }

    /**
     * Resume the uploads that got stuck because of the app being killed
     * or the device being rebooted.
     *
     * When the app is terminated or the device is restarted, contributions remain in the
     * 'STATE_IN_PROGRESS' state. This status persists and doesn't change during these events.
     * So, retrieving contributions labeled as 'STATE_IN_PROGRESS'
     * from the database will provide the list of uploads that appear as stuck on opening the app again
     */
    @SuppressLint("CheckResult")
    private void checkAndResumeStuckUploads() {
        List<Contribution> stuckUploads = contributionDao.getContribution(Collections.singletonList(Contribution.STATE_IN_PROGRESS)).subscribeOn(Schedulers.io()).blockingGet();
        if (!ListenerUtil.mutListener.listen(686)) {
            Timber.d("Resuming " + stuckUploads.size() + " uploads...");
        }
        if (!ListenerUtil.mutListener.listen(691)) {
            if (!stuckUploads.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(689)) {
                    {
                        long _loopCounter12 = 0;
                        for (Contribution contribution : stuckUploads) {
                            ListenerUtil.loopListener.listen("_loopCounter12", ++_loopCounter12);
                            if (!ListenerUtil.mutListener.listen(687)) {
                                contribution.setState(Contribution.STATE_QUEUED);
                            }
                            if (!ListenerUtil.mutListener.listen(688)) {
                                Completable.fromAction(() -> contributionDao.saveSynchronous(contribution)).subscribeOn(Schedulers.io()).subscribe();
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(690)) {
                    WorkRequestHelper.Companion.makeOneTimeWorkRequest(this, ExistingWorkPolicy.APPEND_OR_REPLACE);
                }
            }
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(692)) {
            super.onPostCreate(savedInstanceState);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(693)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(694)) {
            outState.putInt("viewPagerCurrentItem", viewPager.getCurrentItem());
        }
        if (!ListenerUtil.mutListener.listen(695)) {
            outState.putString("activeFragment", activeFragment.name());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(696)) {
            super.onRestoreInstanceState(savedInstanceState);
        }
        String activeFragmentName = savedInstanceState.getString("activeFragment");
        if (!ListenerUtil.mutListener.listen(698)) {
            if (activeFragmentName != null) {
                if (!ListenerUtil.mutListener.listen(697)) {
                    restoreActiveFragment(activeFragmentName);
                }
            }
        }
    }

    private void restoreActiveFragment(@NonNull String fragmentName) {
        if (!ListenerUtil.mutListener.listen(707)) {
            if (fragmentName.equals(ActiveFragment.CONTRIBUTIONS.name())) {
                if (!ListenerUtil.mutListener.listen(705)) {
                    setTitle(getString(R.string.contributions_fragment));
                }
                if (!ListenerUtil.mutListener.listen(706)) {
                    loadFragment(ContributionsFragment.newInstance(), false);
                }
            } else if (fragmentName.equals(ActiveFragment.NEARBY.name())) {
                if (!ListenerUtil.mutListener.listen(703)) {
                    setTitle(getString(R.string.nearby_fragment));
                }
                if (!ListenerUtil.mutListener.listen(704)) {
                    loadFragment(NearbyParentFragment.newInstance(), false);
                }
            } else if (fragmentName.equals(ActiveFragment.EXPLORE.name())) {
                if (!ListenerUtil.mutListener.listen(701)) {
                    setTitle(getString(R.string.navigation_item_explore));
                }
                if (!ListenerUtil.mutListener.listen(702)) {
                    loadFragment(ExploreFragment.newInstance(), false);
                }
            } else if (fragmentName.equals(ActiveFragment.BOOKMARK.name())) {
                if (!ListenerUtil.mutListener.listen(699)) {
                    setTitle(getString(R.string.bookmarks));
                }
                if (!ListenerUtil.mutListener.listen(700)) {
                    loadFragment(BookmarkFragment.newInstance(), false);
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(723)) {
            if ((ListenerUtil.mutListener.listen(708) ? (contributionsFragment != null || activeFragment == ActiveFragment.CONTRIBUTIONS) : (contributionsFragment != null && activeFragment == ActiveFragment.CONTRIBUTIONS))) {
                if (!ListenerUtil.mutListener.listen(722)) {
                    // Means that contribution fragment is visible
                    if (!contributionsFragment.backButtonClicked()) {
                        if (!ListenerUtil.mutListener.listen(721)) {
                            // the back press, let the activity do so
                            super.onBackPressed();
                        }
                    }
                }
            } else if ((ListenerUtil.mutListener.listen(709) ? (nearbyParentFragment != null || activeFragment == ActiveFragment.NEARBY) : (nearbyParentFragment != null && activeFragment == ActiveFragment.NEARBY))) {
                if (!ListenerUtil.mutListener.listen(720)) {
                    /* If function nearbyParentFragment.backButtonClick() returns false, it means that the bottomsheet is
              not expanded. So if the back button is pressed, then go back to the Contributions tab */
                    if (!nearbyParentFragment.backButtonClicked()) {
                        if (!ListenerUtil.mutListener.listen(718)) {
                            getSupportFragmentManager().beginTransaction().remove(nearbyParentFragment).commit();
                        }
                        if (!ListenerUtil.mutListener.listen(719)) {
                            setSelectedItemId(NavTab.CONTRIBUTIONS.code());
                        }
                    }
                }
            } else if ((ListenerUtil.mutListener.listen(710) ? (exploreFragment != null || activeFragment == ActiveFragment.EXPLORE) : (exploreFragment != null && activeFragment == ActiveFragment.EXPLORE))) {
                if (!ListenerUtil.mutListener.listen(717)) {
                    // Means that explore fragment is visible
                    if (!exploreFragment.onBackPressed()) {
                        if (!ListenerUtil.mutListener.listen(716)) {
                            if (applicationKvStore.getBoolean("login_skipped")) {
                                if (!ListenerUtil.mutListener.listen(715)) {
                                    super.onBackPressed();
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(714)) {
                                    setSelectedItemId(NavTab.CONTRIBUTIONS.code());
                                }
                            }
                        }
                    }
                }
            } else if ((ListenerUtil.mutListener.listen(711) ? (bookmarkFragment != null || activeFragment == ActiveFragment.BOOKMARK) : (bookmarkFragment != null && activeFragment == ActiveFragment.BOOKMARK))) {
                if (!ListenerUtil.mutListener.listen(713)) {
                    // Means that bookmark fragment is visible
                    bookmarkFragment.onBackPressed();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(712)) {
                    super.onBackPressed();
                }
            }
        }
    }

    @Override
    public void onBackStackChanged() {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.notifications:
                if (!ListenerUtil.mutListener.listen(724)) {
                    // Starts notification activity on click to notification icon
                    NotificationActivity.startYourself(this, "unread");
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Retry all failed uploads as soon as the user returns to the app
     */
    @SuppressLint("CheckResult")
    private void retryAllFailedUploads() {
        if (!ListenerUtil.mutListener.listen(725)) {
            contributionDao.getContribution(Collections.singletonList(Contribution.STATE_FAILED)).subscribeOn(Schedulers.io()).subscribe(failedUploads -> {
                {
                    long _loopCounter13 = 0;
                    for (Contribution contribution : failedUploads) {
                        ListenerUtil.loopListener.listen("_loopCounter13", ++_loopCounter13);
                        contributionsFragment.retryUpload(contribution);
                    }
                }
            });
        }
    }

    public void toggleLimitedConnectionMode() {
        if (!ListenerUtil.mutListener.listen(726)) {
            defaultKvStore.putBoolean(CommonsApplication.IS_LIMITED_CONNECTION_MODE_ENABLED, !defaultKvStore.getBoolean(CommonsApplication.IS_LIMITED_CONNECTION_MODE_ENABLED, false));
        }
        if (!ListenerUtil.mutListener.listen(730)) {
            if (defaultKvStore.getBoolean(CommonsApplication.IS_LIMITED_CONNECTION_MODE_ENABLED, false)) {
                if (!ListenerUtil.mutListener.listen(729)) {
                    viewUtilWrapper.showShortToast(getBaseContext(), getString(R.string.limited_connection_enabled));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(727)) {
                    WorkRequestHelper.Companion.makeOneTimeWorkRequest(getApplicationContext(), ExistingWorkPolicy.APPEND_OR_REPLACE);
                }
                if (!ListenerUtil.mutListener.listen(728)) {
                    viewUtilWrapper.showShortToast(getBaseContext(), getString(R.string.limited_connection_disabled));
                }
            }
        }
    }

    public void centerMapToPlace(Place place) {
        if (!ListenerUtil.mutListener.listen(731)) {
            setSelectedItemId(NavTab.NEARBY.code());
        }
        if (!ListenerUtil.mutListener.listen(733)) {
            nearbyParentFragment.setNearbyParentFragmentInstanceReadyCallback(new NearbyParentFragmentInstanceReadyCallback() {

                // so that nearbyParentFragemt.centerMaptoPlace(place) not throw any null pointer exception
                @Override
                public void onReady() {
                    if (!ListenerUtil.mutListener.listen(732)) {
                        nearbyParentFragment.centerMapToPlace(place);
                    }
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(734)) {
            Timber.d(data != null ? data.toString() : "onActivityResult data is null");
        }
        if (!ListenerUtil.mutListener.listen(735)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(736)) {
            controller.handleActivityResult(this, requestCode, resultCode, data);
        }
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(737)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(741)) {
            if ((ListenerUtil.mutListener.listen(738) ? ((applicationKvStore.getBoolean("firstrun", true)) || (!applicationKvStore.getBoolean("login_skipped"))) : ((applicationKvStore.getBoolean("firstrun", true)) && (!applicationKvStore.getBoolean("login_skipped"))))) {
                if (!ListenerUtil.mutListener.listen(739)) {
                    defaultKvStore.putBoolean("inAppCameraFirstRun", true);
                }
                if (!ListenerUtil.mutListener.listen(740)) {
                    WelcomeActivity.startYourself(this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(742)) {
            retryAllFailedUploads();
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(743)) {
            quizChecker.cleanup();
        }
        if (!ListenerUtil.mutListener.listen(744)) {
            locationManager.unregisterLocationManager();
        }
        if (!ListenerUtil.mutListener.listen(745)) {
            // Remove ourself from hashmap to prevent memory leaks
            locationManager = null;
        }
        if (!ListenerUtil.mutListener.listen(746)) {
            super.onDestroy();
        }
    }

    /**
     * Public method to show nearby from the reference of this.
     */
    public void showNearby() {
        if (!ListenerUtil.mutListener.listen(747)) {
            tabLayout.setSelectedItemId(NavTab.NEARBY.code());
        }
    }

    public enum ActiveFragment {

        CONTRIBUTIONS, NEARBY, EXPLORE, BOOKMARK, MORE
    }

    /**
     * Load default language in onCreate from SharedPreferences
     */
    private void loadLocale() {
        final SharedPreferences preferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        final String language = preferences.getString("language", "");
        final SettingsFragment settingsFragment = new SettingsFragment();
        if (!ListenerUtil.mutListener.listen(748)) {
            settingsFragment.setLocale(this, language);
        }
    }

    public NavTabLayout.OnNavigationItemSelectedListener getNavListener() {
        return navListener;
    }
}
