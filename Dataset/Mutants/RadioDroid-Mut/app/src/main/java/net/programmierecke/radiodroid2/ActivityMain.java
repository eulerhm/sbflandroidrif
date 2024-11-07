package net.programmierecke.radiodroid2;

import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.preference.PreferenceManager;
import com.bytehamster.lib.preferencesearch.SearchPreferenceResult;
import com.bytehamster.lib.preferencesearch.SearchPreferenceResultListener;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.mikepenz.iconics.Iconics;
import com.rustamg.filedialogs.FileDialog;
import com.rustamg.filedialogs.OpenFileDialog;
import com.rustamg.filedialogs.SaveFileDialog;
import net.programmierecke.radiodroid2.alarm.FragmentAlarm;
import net.programmierecke.radiodroid2.alarm.TimePickerFragment;
import net.programmierecke.radiodroid2.cast.CastAwareActivity;
import net.programmierecke.radiodroid2.interfaces.IFragmentSearchable;
import net.programmierecke.radiodroid2.players.PlayState;
import net.programmierecke.radiodroid2.players.PlayStationTask;
import net.programmierecke.radiodroid2.players.mpd.MPDClient;
import net.programmierecke.radiodroid2.players.mpd.MPDServersRepository;
import net.programmierecke.radiodroid2.players.selector.PlayerType;
import net.programmierecke.radiodroid2.service.MediaSessionCallback;
import net.programmierecke.radiodroid2.service.PlayerService;
import net.programmierecke.radiodroid2.service.PlayerServiceUtil;
import net.programmierecke.radiodroid2.station.DataRadioStation;
import net.programmierecke.radiodroid2.station.StationsFilter;
import java.io.File;
import java.util.Date;
import okhttp3.OkHttpClient;
import static net.programmierecke.radiodroid2.service.MediaSessionCallback.EXTRA_STATION_UUID;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ActivityMain extends AppCompatActivity implements SearchView.OnQueryTextListener, NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener, FileDialog.OnFileSelectedListener, TimePickerDialog.OnTimeSetListener, SearchPreferenceResultListener, CastAwareActivity {

    public static final String EXTRA_SEARCH_TAG = "search_tag";

    public static final int LAUNCH_EQUALIZER_REQUEST = 1;

    public static final int MAX_DYNAMIC_LAUNCHER_SHORTCUTS = 4;

    public static final int FRAGMENT_FROM_BACKSTACK = 777;

    public static final String ACTION_SHOW_LOADING = "net.programmierecke.radiodroid2.show_loading";

    public static final String ACTION_HIDE_LOADING = "net.programmierecke.radiodroid2.hide_loading";

    private static final String TAG = "RadioDroid";

    private final String TAG_SEARCH_URL = "json/stations/bytagexact";

    private final String SAVE_LAST_MENU_ITEM = "LAST_MENU_ITEM";

    public static final int PERM_REQ_STORAGE_FAV_SAVE = 1;

    public static final int PERM_REQ_STORAGE_FAV_LOAD = 2;

    private SearchView mSearchView;

    private AppBarLayout appBarLayout;

    private TabLayout tabsView;

    DrawerLayout mDrawerLayout;

    NavigationView mNavigationView;

    BottomNavigationView mBottomNavigationView;

    FragmentManager mFragmentManager;

    private BottomSheetBehavior playerBottomSheet;

    private FragmentPlayerSmall smallPlayerFragment;

    private FragmentPlayerFull fullPlayerFragment;

    BroadcastReceiver broadcastReceiver;

    MenuItem menuItemSearch;

    MenuItem menuItemDelete;

    MenuItem menuItemSleepTimer;

    MenuItem menuItemSave;

    MenuItem menuItemLoad;

    MenuItem menuItemIconsView;

    MenuItem menuItemListView;

    MenuItem menuItemAddAlarm;

    MenuItem menuItemMpd;

    private SharedPreferences sharedPref;

    private int selectedMenuItem;

    private boolean instanceStateWasSaved;

    private Date lastExitTry;

    private AlertDialog meteredConnectionAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3741)) {
            Iconics.init(this);
        }
        if (!ListenerUtil.mutListener.listen(3742)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(3745)) {
            if (sharedPref == null) {
                if (!ListenerUtil.mutListener.listen(3743)) {
                    PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
                }
                if (!ListenerUtil.mutListener.listen(3744)) {
                    sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3746)) {
            setTheme(Utils.getThemeResId(this));
        }
        if (!ListenerUtil.mutListener.listen(3747)) {
            setContentView(R.layout.layout_main);
        }
        try {
            File dir = new File(getFilesDir().getAbsolutePath());
            if (!ListenerUtil.mutListener.listen(3752)) {
                if (dir.isDirectory()) {
                    String[] children = dir.list();
                    if (!ListenerUtil.mutListener.listen(3751)) {
                        {
                            long _loopCounter47 = 0;
                            for (String aChildren : children) {
                                ListenerUtil.loopListener.listen("_loopCounter47", ++_loopCounter47);
                                if (!ListenerUtil.mutListener.listen(3749)) {
                                    if (BuildConfig.DEBUG) {
                                        if (!ListenerUtil.mutListener.listen(3748)) {
                                            Log.d("MAIN", "delete file:" + aChildren);
                                        }
                                    }
                                }
                                try {
                                    if (!ListenerUtil.mutListener.listen(3750)) {
                                        new File(dir, aChildren).delete();
                                    }
                                } catch (Exception e) {
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        final Toolbar myToolbar = findViewById(R.id.my_awesome_toolbar);
        if (!ListenerUtil.mutListener.listen(3753)) {
            setSupportActionBar(myToolbar);
        }
        if (!ListenerUtil.mutListener.listen(3754)) {
            PlayerServiceUtil.startService(getApplicationContext());
        }
        if (!ListenerUtil.mutListener.listen(3755)) {
            selectedMenuItem = sharedPref.getInt("last_selectedMenuItem", -1);
        }
        if (!ListenerUtil.mutListener.listen(3756)) {
            instanceStateWasSaved = savedInstanceState != null;
        }
        if (!ListenerUtil.mutListener.listen(3757)) {
            mFragmentManager = getSupportFragmentManager();
        }
        if (!ListenerUtil.mutListener.listen(3758)) {
            appBarLayout = findViewById(R.id.app_bar_layout);
        }
        if (!ListenerUtil.mutListener.listen(3759)) {
            tabsView = findViewById(R.id.tabs);
        }
        if (!ListenerUtil.mutListener.listen(3760)) {
            mDrawerLayout = findViewById(R.id.drawerLayout);
        }
        if (!ListenerUtil.mutListener.listen(3761)) {
            mNavigationView = findViewById(R.id.my_navigation_view);
        }
        if (!ListenerUtil.mutListener.listen(3762)) {
            mBottomNavigationView = findViewById(R.id.bottom_navigation);
        }
        if (!ListenerUtil.mutListener.listen(3772)) {
            if (Utils.bottomNavigationEnabled(this)) {
                if (!ListenerUtil.mutListener.listen(3769)) {
                    mBottomNavigationView.setOnNavigationItemSelectedListener(this);
                }
                if (!ListenerUtil.mutListener.listen(3770)) {
                    mNavigationView.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(3771)) {
                    mNavigationView.getLayoutParams().width = 0;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3763)) {
                    mNavigationView.setNavigationItemSelectedListener(this);
                }
                if (!ListenerUtil.mutListener.listen(3764)) {
                    mBottomNavigationView.setVisibility(View.GONE);
                }
                ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name);
                if (!ListenerUtil.mutListener.listen(3765)) {
                    mDrawerLayout.addDrawerListener(mDrawerToggle);
                }
                if (!ListenerUtil.mutListener.listen(3766)) {
                    mDrawerToggle.syncState();
                }
                if (!ListenerUtil.mutListener.listen(3767)) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(3768)) {
                    getSupportActionBar().setHomeButtonEnabled(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3773)) {
            smallPlayerFragment = (FragmentPlayerSmall) mFragmentManager.findFragmentById(R.id.fragment_player_small);
        }
        if (!ListenerUtil.mutListener.listen(3774)) {
            fullPlayerFragment = (FragmentPlayerFull) mFragmentManager.findFragmentById(R.id.fragment_player_full);
        }
        if (!ListenerUtil.mutListener.listen(3782)) {
            if ((ListenerUtil.mutListener.listen(3775) ? (smallPlayerFragment == null && fullPlayerFragment == null) : (smallPlayerFragment == null || fullPlayerFragment == null))) {
                if (!ListenerUtil.mutListener.listen(3776)) {
                    smallPlayerFragment = new FragmentPlayerSmall();
                }
                if (!ListenerUtil.mutListener.listen(3777)) {
                    fullPlayerFragment = new FragmentPlayerFull();
                }
                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                if (!ListenerUtil.mutListener.listen(3778)) {
                    // Hide it at start to make .onHiddenChanged be called on first show
                    fragmentTransaction.hide(fullPlayerFragment);
                }
                if (!ListenerUtil.mutListener.listen(3779)) {
                    fragmentTransaction.replace(R.id.fragment_player_small, smallPlayerFragment);
                }
                if (!ListenerUtil.mutListener.listen(3780)) {
                    fragmentTransaction.replace(R.id.fragment_player_full, fullPlayerFragment);
                }
                if (!ListenerUtil.mutListener.listen(3781)) {
                    fragmentTransaction.commit();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3784)) {
            smallPlayerFragment.setCallback(new FragmentPlayerSmall.Callback() {

                @Override
                public void onToggle() {
                    if (!ListenerUtil.mutListener.listen(3783)) {
                        toggleBottomSheetState();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(3786)) {
            fullPlayerFragment.setTouchInterceptListener(new FragmentPlayerFull.TouchInterceptListener() {

                @Override
                public void requestDisallowInterceptTouchEvent(boolean disallow) {
                    if (!ListenerUtil.mutListener.listen(3785)) {
                        findViewById(R.id.bottom_sheet).getParent().requestDisallowInterceptTouchEvent(disallow);
                    }
                }
            });
        }
        // our custom RecyclerAwareNestedScrollView
        CoordinatorLayout.LayoutParams coordinatorLayoutParams = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
        AppBarLayout.Behavior appBarLayoutBehavior = new AppBarLayout.Behavior() {

            @Override
            public boolean onStartNestedScroll(CoordinatorLayout parent, AppBarLayout child, View directTargetChild, View target, int nestedScrollAxes, int type) {
                return playerBottomSheet.getState() == BottomSheetBehavior.STATE_COLLAPSED;
            }
        };
        if (!ListenerUtil.mutListener.listen(3787)) {
            coordinatorLayoutParams.setBehavior(appBarLayoutBehavior);
        }
        if (!ListenerUtil.mutListener.listen(3788)) {
            playerBottomSheet = BottomSheetBehavior.from(findViewById(R.id.bottom_sheet));
        }
        if (!ListenerUtil.mutListener.listen(3814)) {
            playerBottomSheet.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {

                private int oldState = BottomSheetBehavior.STATE_COLLAPSED;

                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    if (!ListenerUtil.mutListener.listen(3792)) {
                        // Essentially this is a cheap hack to prevent bottom sheet from being dragged by non-scrolling elements.
                        if ((ListenerUtil.mutListener.listen(3789) ? (newState == BottomSheetBehavior.STATE_DRAGGING || oldState == BottomSheetBehavior.STATE_EXPANDED) : (newState == BottomSheetBehavior.STATE_DRAGGING && oldState == BottomSheetBehavior.STATE_EXPANDED))) {
                            if (!ListenerUtil.mutListener.listen(3791)) {
                                if (fullPlayerFragment.isScrolled()) {
                                    if (!ListenerUtil.mutListener.listen(3790)) {
                                        playerBottomSheet.setState(BottomSheetBehavior.STATE_EXPANDED);
                                    }
                                    return;
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3803)) {
                        if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                            if (!ListenerUtil.mutListener.listen(3798)) {
                                if (smallPlayerFragment.getContext() == null)
                                    return;
                            }
                            if (!ListenerUtil.mutListener.listen(3799)) {
                                appBarLayout.setExpanded(false);
                            }
                            if (!ListenerUtil.mutListener.listen(3800)) {
                                smallPlayerFragment.setRole(FragmentPlayerSmall.Role.HEADER);
                            }
                            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                            if (!ListenerUtil.mutListener.listen(3801)) {
                                fragmentTransaction.hide(mFragmentManager.findFragmentById(R.id.containerView));
                            }
                            if (!ListenerUtil.mutListener.listen(3802)) {
                                fragmentTransaction.commit();
                            }
                        } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                            if (!ListenerUtil.mutListener.listen(3793)) {
                                appBarLayout.setExpanded(true);
                            }
                            if (!ListenerUtil.mutListener.listen(3794)) {
                                smallPlayerFragment.setRole(FragmentPlayerSmall.Role.PLAYER);
                            }
                            if (!ListenerUtil.mutListener.listen(3795)) {
                                fullPlayerFragment.resetScroll();
                            }
                            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                            if (!ListenerUtil.mutListener.listen(3796)) {
                                fragmentTransaction.hide(fullPlayerFragment);
                            }
                            if (!ListenerUtil.mutListener.listen(3797)) {
                                fragmentTransaction.commit();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3807)) {
                        if ((ListenerUtil.mutListener.listen(3804) ? (oldState == BottomSheetBehavior.STATE_EXPANDED || newState != BottomSheetBehavior.STATE_EXPANDED) : (oldState == BottomSheetBehavior.STATE_EXPANDED && newState != BottomSheetBehavior.STATE_EXPANDED))) {
                            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                            if (!ListenerUtil.mutListener.listen(3805)) {
                                fragmentTransaction.show(mFragmentManager.findFragmentById(R.id.containerView));
                            }
                            if (!ListenerUtil.mutListener.listen(3806)) {
                                fragmentTransaction.commit();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3812)) {
                        if ((ListenerUtil.mutListener.listen(3808) ? (oldState == BottomSheetBehavior.STATE_COLLAPSED || newState != oldState) : (oldState == BottomSheetBehavior.STATE_COLLAPSED && newState != oldState))) {
                            if (!ListenerUtil.mutListener.listen(3809)) {
                                fullPlayerFragment.init();
                            }
                            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                            if (!ListenerUtil.mutListener.listen(3810)) {
                                fragmentTransaction.show(fullPlayerFragment);
                            }
                            if (!ListenerUtil.mutListener.listen(3811)) {
                                fragmentTransaction.commit();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3813)) {
                        oldState = newState;
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(3815)) {
            ((RadioDroidApp) getApplication()).getCastHandler().onCreate(this);
        }
        if (!ListenerUtil.mutListener.listen(3816)) {
            setupStartUpFragment();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        if (!ListenerUtil.mutListener.listen(3818)) {
            // If menuItem == null method was executed manually
            if (menuItem != null)
                if (!ListenerUtil.mutListener.listen(3817)) {
                    selectedMenuItem = menuItem.getItemId();
                }
        }
        if (!ListenerUtil.mutListener.listen(3820)) {
            if (playerBottomSheet.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                if (!ListenerUtil.mutListener.listen(3819)) {
                    playerBottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3822)) {
            if (mSearchView != null) {
                if (!ListenerUtil.mutListener.listen(3821)) {
                    mSearchView.clearFocus();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3823)) {
            mDrawerLayout.closeDrawers();
        }
        Fragment f = null;
        String backStackTag = String.valueOf(selectedMenuItem);
        if (!ListenerUtil.mutListener.listen(3829)) {
            switch(selectedMenuItem) {
                case R.id.nav_item_stations:
                    if (!ListenerUtil.mutListener.listen(3824)) {
                        f = new FragmentTabs();
                    }
                    break;
                case R.id.nav_item_starred:
                    if (!ListenerUtil.mutListener.listen(3825)) {
                        f = new FragmentStarred();
                    }
                    break;
                case R.id.nav_item_history:
                    if (!ListenerUtil.mutListener.listen(3826)) {
                        f = new FragmentHistory();
                    }
                    break;
                case R.id.nav_item_alarm:
                    if (!ListenerUtil.mutListener.listen(3827)) {
                        f = new FragmentAlarm();
                    }
                    break;
                case R.id.nav_item_settings:
                    if (!ListenerUtil.mutListener.listen(3828)) {
                        f = new FragmentSettings();
                    }
                    break;
                default:
            }
        }
        if (!ListenerUtil.mutListener.listen(3830)) {
            // I'm not sure why.
            mFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if (!ListenerUtil.mutListener.listen(3833)) {
            if (Utils.bottomNavigationEnabled(this)) {
                if (!ListenerUtil.mutListener.listen(3832)) {
                    fragmentTransaction.replace(R.id.containerView, f).commit();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3831)) {
                    fragmentTransaction.replace(R.id.containerView, f).addToBackStack(backStackTag).commit();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3834)) {
            // User selected a menuItem. Let's hide progressBar
            LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(ActivityMain.ACTION_HIDE_LOADING));
        }
        if (!ListenerUtil.mutListener.listen(3835)) {
            invalidateOptionsMenu();
        }
        if (!ListenerUtil.mutListener.listen(3836)) {
            checkMenuItems();
        }
        if (!ListenerUtil.mutListener.listen(3837)) {
            appBarLayout.setExpanded(true);
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(3839)) {
            if (playerBottomSheet.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                if (!ListenerUtil.mutListener.listen(3838)) {
                    playerBottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                return;
            }
        }
        int backStackCount = mFragmentManager.getBackStackEntryCount();
        FragmentManager.BackStackEntry backStackEntry;
        if ((ListenerUtil.mutListener.listen(3844) ? (backStackCount >= 0) : (ListenerUtil.mutListener.listen(3843) ? (backStackCount <= 0) : (ListenerUtil.mutListener.listen(3842) ? (backStackCount < 0) : (ListenerUtil.mutListener.listen(3841) ? (backStackCount != 0) : (ListenerUtil.mutListener.listen(3840) ? (backStackCount == 0) : (backStackCount > 0))))))) {
            // FRAGMENT_FROM_BACKSTACK value added as a backstack name for non-root fragments like Recordings, About, etc
            backStackEntry = mFragmentManager.getBackStackEntryAt((ListenerUtil.mutListener.listen(3848) ? (mFragmentManager.getBackStackEntryCount() % 1) : (ListenerUtil.mutListener.listen(3847) ? (mFragmentManager.getBackStackEntryCount() / 1) : (ListenerUtil.mutListener.listen(3846) ? (mFragmentManager.getBackStackEntryCount() * 1) : (ListenerUtil.mutListener.listen(3845) ? (mFragmentManager.getBackStackEntryCount() + 1) : (mFragmentManager.getBackStackEntryCount() - 1))))));
            if (!ListenerUtil.mutListener.listen(3850)) {
                if (backStackEntry.getName().equals("SearchPreferenceFragment")) {
                    if (!ListenerUtil.mutListener.listen(3849)) {
                        super.onBackPressed();
                    }
                    return;
                }
            }
            int parsedId = Integer.parseInt(backStackEntry.getName());
            if (!ListenerUtil.mutListener.listen(3858)) {
                if ((ListenerUtil.mutListener.listen(3855) ? (parsedId >= FRAGMENT_FROM_BACKSTACK) : (ListenerUtil.mutListener.listen(3854) ? (parsedId <= FRAGMENT_FROM_BACKSTACK) : (ListenerUtil.mutListener.listen(3853) ? (parsedId > FRAGMENT_FROM_BACKSTACK) : (ListenerUtil.mutListener.listen(3852) ? (parsedId < FRAGMENT_FROM_BACKSTACK) : (ListenerUtil.mutListener.listen(3851) ? (parsedId != FRAGMENT_FROM_BACKSTACK) : (parsedId == FRAGMENT_FROM_BACKSTACK))))))) {
                    if (!ListenerUtil.mutListener.listen(3856)) {
                        super.onBackPressed();
                    }
                    if (!ListenerUtil.mutListener.listen(3857)) {
                        invalidateOptionsMenu();
                    }
                    return;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3878)) {
            // Don't support backstack with BottomNavigationView
            if (Utils.bottomNavigationEnabled(this)) {
                if (!ListenerUtil.mutListener.listen(3877)) {
                    // I'm giving 3 seconds on making a choice
                    if ((ListenerUtil.mutListener.listen(3872) ? (lastExitTry != null || (ListenerUtil.mutListener.listen(3871) ? (new Date().getTime() >= (ListenerUtil.mutListener.listen(3866) ? (lastExitTry.getTime() % (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3865) ? (lastExitTry.getTime() / (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3864) ? (lastExitTry.getTime() * (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3863) ? (lastExitTry.getTime() - (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (lastExitTry.getTime() + (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000))))))))))) : (ListenerUtil.mutListener.listen(3870) ? (new Date().getTime() <= (ListenerUtil.mutListener.listen(3866) ? (lastExitTry.getTime() % (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3865) ? (lastExitTry.getTime() / (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3864) ? (lastExitTry.getTime() * (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3863) ? (lastExitTry.getTime() - (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (lastExitTry.getTime() + (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000))))))))))) : (ListenerUtil.mutListener.listen(3869) ? (new Date().getTime() > (ListenerUtil.mutListener.listen(3866) ? (lastExitTry.getTime() % (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3865) ? (lastExitTry.getTime() / (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3864) ? (lastExitTry.getTime() * (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3863) ? (lastExitTry.getTime() - (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (lastExitTry.getTime() + (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000))))))))))) : (ListenerUtil.mutListener.listen(3868) ? (new Date().getTime() != (ListenerUtil.mutListener.listen(3866) ? (lastExitTry.getTime() % (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3865) ? (lastExitTry.getTime() / (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3864) ? (lastExitTry.getTime() * (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3863) ? (lastExitTry.getTime() - (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (lastExitTry.getTime() + (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000))))))))))) : (ListenerUtil.mutListener.listen(3867) ? (new Date().getTime() == (ListenerUtil.mutListener.listen(3866) ? (lastExitTry.getTime() % (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3865) ? (lastExitTry.getTime() / (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3864) ? (lastExitTry.getTime() * (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3863) ? (lastExitTry.getTime() - (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (lastExitTry.getTime() + (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000))))))))))) : (new Date().getTime() < (ListenerUtil.mutListener.listen(3866) ? (lastExitTry.getTime() % (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3865) ? (lastExitTry.getTime() / (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3864) ? (lastExitTry.getTime() * (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3863) ? (lastExitTry.getTime() - (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (lastExitTry.getTime() + (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000))))))))))))))))) : (lastExitTry != null && (ListenerUtil.mutListener.listen(3871) ? (new Date().getTime() >= (ListenerUtil.mutListener.listen(3866) ? (lastExitTry.getTime() % (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3865) ? (lastExitTry.getTime() / (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3864) ? (lastExitTry.getTime() * (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3863) ? (lastExitTry.getTime() - (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (lastExitTry.getTime() + (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000))))))))))) : (ListenerUtil.mutListener.listen(3870) ? (new Date().getTime() <= (ListenerUtil.mutListener.listen(3866) ? (lastExitTry.getTime() % (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3865) ? (lastExitTry.getTime() / (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3864) ? (lastExitTry.getTime() * (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3863) ? (lastExitTry.getTime() - (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (lastExitTry.getTime() + (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000))))))))))) : (ListenerUtil.mutListener.listen(3869) ? (new Date().getTime() > (ListenerUtil.mutListener.listen(3866) ? (lastExitTry.getTime() % (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3865) ? (lastExitTry.getTime() / (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3864) ? (lastExitTry.getTime() * (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3863) ? (lastExitTry.getTime() - (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (lastExitTry.getTime() + (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000))))))))))) : (ListenerUtil.mutListener.listen(3868) ? (new Date().getTime() != (ListenerUtil.mutListener.listen(3866) ? (lastExitTry.getTime() % (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3865) ? (lastExitTry.getTime() / (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3864) ? (lastExitTry.getTime() * (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3863) ? (lastExitTry.getTime() - (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (lastExitTry.getTime() + (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000))))))))))) : (ListenerUtil.mutListener.listen(3867) ? (new Date().getTime() == (ListenerUtil.mutListener.listen(3866) ? (lastExitTry.getTime() % (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3865) ? (lastExitTry.getTime() / (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3864) ? (lastExitTry.getTime() * (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3863) ? (lastExitTry.getTime() - (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (lastExitTry.getTime() + (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000))))))))))) : (new Date().getTime() < (ListenerUtil.mutListener.listen(3866) ? (lastExitTry.getTime() % (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3865) ? (lastExitTry.getTime() / (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3864) ? (lastExitTry.getTime() * (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (ListenerUtil.mutListener.listen(3863) ? (lastExitTry.getTime() - (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000)))))) : (lastExitTry.getTime() + (ListenerUtil.mutListener.listen(3862) ? (3 % 1000) : (ListenerUtil.mutListener.listen(3861) ? (3 / 1000) : (ListenerUtil.mutListener.listen(3860) ? (3 - 1000) : (ListenerUtil.mutListener.listen(3859) ? (3 + 1000) : (3 * 1000))))))))))))))))))) {
                        if (!ListenerUtil.mutListener.listen(3875)) {
                            PlayerServiceUtil.shutdownService();
                        }
                        if (!ListenerUtil.mutListener.listen(3876)) {
                            finish();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3873)) {
                            Toast.makeText(this, R.string.alert_press_back_to_exit, Toast.LENGTH_SHORT).show();
                        }
                        if (!ListenerUtil.mutListener.listen(3874)) {
                            lastExitTry = new Date();
                        }
                        return;
                    }
                }
            }
        }
        if ((ListenerUtil.mutListener.listen(3883) ? (backStackCount >= 1) : (ListenerUtil.mutListener.listen(3882) ? (backStackCount <= 1) : (ListenerUtil.mutListener.listen(3881) ? (backStackCount < 1) : (ListenerUtil.mutListener.listen(3880) ? (backStackCount != 1) : (ListenerUtil.mutListener.listen(3879) ? (backStackCount == 1) : (backStackCount > 1))))))) {
            backStackEntry = mFragmentManager.getBackStackEntryAt((ListenerUtil.mutListener.listen(3888) ? (mFragmentManager.getBackStackEntryCount() % 2) : (ListenerUtil.mutListener.listen(3887) ? (mFragmentManager.getBackStackEntryCount() / 2) : (ListenerUtil.mutListener.listen(3886) ? (mFragmentManager.getBackStackEntryCount() * 2) : (ListenerUtil.mutListener.listen(3885) ? (mFragmentManager.getBackStackEntryCount() + 2) : (mFragmentManager.getBackStackEntryCount() - 2))))));
            if (!ListenerUtil.mutListener.listen(3889)) {
                selectedMenuItem = Integer.parseInt(backStackEntry.getName());
            }
            if (!ListenerUtil.mutListener.listen(3891)) {
                if (!Utils.bottomNavigationEnabled(this)) {
                    if (!ListenerUtil.mutListener.listen(3890)) {
                        mNavigationView.setCheckedItem(selectedMenuItem);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(3892)) {
                invalidateOptionsMenu();
            }
        } else {
            if (!ListenerUtil.mutListener.listen(3884)) {
                finish();
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(3893)) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (!ListenerUtil.mutListener.listen(3894)) {
            super.onNewIntent(intent);
        }
        if (!ListenerUtil.mutListener.listen(3895)) {
            setIntent(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(3897)) {
            if (BuildConfig.DEBUG) {
                if (!ListenerUtil.mutListener.listen(3896)) {
                    Log.d(TAG, "on request permissions result:" + requestCode);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3898)) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        if (!ListenerUtil.mutListener.listen(3915)) {
            switch(requestCode) {
                case PERM_REQ_STORAGE_FAV_LOAD:
                    {
                        if (!ListenerUtil.mutListener.listen(3906)) {
                            if ((ListenerUtil.mutListener.listen(3904) ? ((ListenerUtil.mutListener.listen(3903) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(3902) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(3901) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(3900) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(3899) ? (grantResults.length == 0) : (grantResults.length > 0)))))) || grantResults[0] == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(3903) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(3902) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(3901) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(3900) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(3899) ? (grantResults.length == 0) : (grantResults.length > 0)))))) && grantResults[0] == PackageManager.PERMISSION_GRANTED))) {
                                if (!ListenerUtil.mutListener.listen(3905)) {
                                    LoadFavourites();
                                }
                            }
                        }
                        return;
                    }
                case PERM_REQ_STORAGE_FAV_SAVE:
                    {
                        if (!ListenerUtil.mutListener.listen(3914)) {
                            if ((ListenerUtil.mutListener.listen(3912) ? ((ListenerUtil.mutListener.listen(3911) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(3910) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(3909) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(3908) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(3907) ? (grantResults.length == 0) : (grantResults.length > 0)))))) || grantResults[0] == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(3911) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(3910) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(3909) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(3908) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(3907) ? (grantResults.length == 0) : (grantResults.length > 0)))))) && grantResults[0] == PackageManager.PERMISSION_GRANTED))) {
                                if (!ListenerUtil.mutListener.listen(3913)) {
                                    SaveFavourites();
                                }
                            }
                        }
                        return;
                    }
            }
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(3916)) {
            super.onDestroy();
        }
        if (!ListenerUtil.mutListener.listen(3918)) {
            if (!PlayerServiceUtil.isNotificationActive()) {
                if (!ListenerUtil.mutListener.listen(3917)) {
                    /* If at this point if for whatever reason we have the service without a notification,
             * we must shut it down because user doesn't have a way to interact with it.
             * This is a safeguard since such service should have been destroyed in onPause()
             */
                    PlayerServiceUtil.shutdownService();
                }
            }
        }
    }

    @Override
    protected void onPause() {
        SharedPreferences.Editor ed = sharedPref.edit();
        if (!ListenerUtil.mutListener.listen(3919)) {
            ed.putInt("last_selectedMenuItem", selectedMenuItem);
        }
        if (!ListenerUtil.mutListener.listen(3920)) {
            ed.apply();
        }
        if (!ListenerUtil.mutListener.listen(3922)) {
            if (BuildConfig.DEBUG) {
                if (!ListenerUtil.mutListener.listen(3921)) {
                    Log.d(TAG, "PAUSED");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3923)) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
        }
        if (!ListenerUtil.mutListener.listen(3924)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(3926)) {
            if (PlayerServiceUtil.getPlayerState() == PlayState.Idle) {
                if (!ListenerUtil.mutListener.listen(3925)) {
                    PlayerServiceUtil.shutdownService();
                }
            }
        }
        CastHandler castHandler = ((RadioDroidApp) getApplication()).getCastHandler();
        if (!ListenerUtil.mutListener.listen(3927)) {
            castHandler.onPause();
        }
        if (!ListenerUtil.mutListener.listen(3928)) {
            castHandler.setActivity(null);
        }
    }

    private void handleIntent(@NonNull Intent intent) {
        String action = intent.getAction();
        final Bundle extras = intent.getExtras();
        if (!ListenerUtil.mutListener.listen(3929)) {
            if (extras == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(3946)) {
            if (MediaSessionCallback.ACTION_PLAY_STATION_BY_UUID.equals(action)) {
                final Context context = getApplicationContext();
                final String stationUUID = extras.getString(EXTRA_STATION_UUID);
                if (!ListenerUtil.mutListener.listen(3934)) {
                    if (TextUtils.isEmpty(stationUUID))
                        return;
                }
                if (!ListenerUtil.mutListener.listen(3935)) {
                    // mark intent as consumed
                    intent.removeExtra(EXTRA_STATION_UUID);
                }
                RadioDroidApp radioDroidApp = (RadioDroidApp) getApplication();
                final OkHttpClient httpClient = radioDroidApp.getHttpClient();
                if (!ListenerUtil.mutListener.listen(3945)) {
                    new AsyncTask<Void, Void, DataRadioStation>() {

                        @Override
                        protected DataRadioStation doInBackground(Void... params) {
                            return Utils.getStationByUuid(httpClient, context, stationUUID);
                        }

                        @Override
                        protected void onPostExecute(DataRadioStation station) {
                            if (!ListenerUtil.mutListener.listen(3944)) {
                                if (!isFinishing()) {
                                    if (!ListenerUtil.mutListener.listen(3943)) {
                                        if (station != null) {
                                            if (!ListenerUtil.mutListener.listen(3936)) {
                                                Utils.showPlaySelection(radioDroidApp, station, getSupportFragmentManager());
                                            }
                                            Fragment currentFragment = mFragmentManager.getFragments().get((ListenerUtil.mutListener.listen(3940) ? (mFragmentManager.getFragments().size() % 1) : (ListenerUtil.mutListener.listen(3939) ? (mFragmentManager.getFragments().size() / 1) : (ListenerUtil.mutListener.listen(3938) ? (mFragmentManager.getFragments().size() * 1) : (ListenerUtil.mutListener.listen(3937) ? (mFragmentManager.getFragments().size() + 1) : (mFragmentManager.getFragments().size() - 1))))));
                                            if (!ListenerUtil.mutListener.listen(3942)) {
                                                if (currentFragment instanceof FragmentHistory) {
                                                    if (!ListenerUtil.mutListener.listen(3941)) {
                                                        ((FragmentHistory) currentFragment).RefreshListGui();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }.execute();
                }
            } else {
                final String searchTag = extras.getString(EXTRA_SEARCH_TAG);
                if (!ListenerUtil.mutListener.listen(3930)) {
                    Log.d("MAIN", "received search request for tag 1: " + searchTag);
                }
                if (!ListenerUtil.mutListener.listen(3933)) {
                    if (searchTag != null) {
                        if (!ListenerUtil.mutListener.listen(3931)) {
                            Log.d("MAIN", "received search request for tag 2: " + searchTag);
                        }
                        if (!ListenerUtil.mutListener.listen(3932)) {
                            Search(StationsFilter.SearchStyle.ByTagExact, searchTag);
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(3947)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(3949)) {
            if (BuildConfig.DEBUG) {
                if (!ListenerUtil.mutListener.listen(3948)) {
                    Log.d(TAG, "RESUMED");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3950)) {
            setupBroadcastReceiver();
        }
        if (!ListenerUtil.mutListener.listen(3951)) {
            PlayerServiceUtil.startService(getApplicationContext());
        }
        CastHandler castHandler = ((RadioDroidApp) getApplication()).getCastHandler();
        if (!ListenerUtil.mutListener.listen(3952)) {
            castHandler.onResume();
        }
        if (!ListenerUtil.mutListener.listen(3953)) {
            castHandler.setActivity(this);
        }
        if (!ListenerUtil.mutListener.listen(3955)) {
            if (playerBottomSheet.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                if (!ListenerUtil.mutListener.listen(3954)) {
                    appBarLayout.setExpanded(false);
                }
            }
        }
        Intent intent = getIntent();
        if (!ListenerUtil.mutListener.listen(3958)) {
            if (intent != null) {
                if (!ListenerUtil.mutListener.listen(3956)) {
                    handleIntent(intent);
                }
                if (!ListenerUtil.mutListener.listen(3957)) {
                    setIntent(null);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(3959)) {
            super.onCreateOptionsMenu(menu);
        }
        if (!ListenerUtil.mutListener.listen(3960)) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
        }
        final Toolbar myToolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        if (!ListenerUtil.mutListener.listen(3961)) {
            menuItemSleepTimer = menu.findItem(R.id.action_set_sleep_timer);
        }
        if (!ListenerUtil.mutListener.listen(3962)) {
            menuItemSearch = menu.findItem(R.id.action_search);
        }
        if (!ListenerUtil.mutListener.listen(3963)) {
            menuItemDelete = menu.findItem(R.id.action_delete);
        }
        if (!ListenerUtil.mutListener.listen(3964)) {
            menuItemSave = menu.findItem(R.id.action_save);
        }
        if (!ListenerUtil.mutListener.listen(3965)) {
            menuItemLoad = menu.findItem(R.id.action_load);
        }
        if (!ListenerUtil.mutListener.listen(3966)) {
            menuItemListView = menu.findItem(R.id.action_list_view);
        }
        if (!ListenerUtil.mutListener.listen(3967)) {
            menuItemIconsView = menu.findItem(R.id.action_icons_view);
        }
        if (!ListenerUtil.mutListener.listen(3968)) {
            menuItemAddAlarm = menu.findItem(R.id.action_add_alarm);
        }
        if (!ListenerUtil.mutListener.listen(3969)) {
            menuItemMpd = menu.findItem(R.id.action_mpd);
        }
        if (!ListenerUtil.mutListener.listen(3970)) {
            mSearchView = (SearchView) MenuItemCompat.getActionView(menuItemSearch);
        }
        if (!ListenerUtil.mutListener.listen(3971)) {
            mSearchView.setOnQueryTextListener(this);
        }
        if (!ListenerUtil.mutListener.listen(3978)) {
            mSearchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

                private int prevTabsVisibility = View.GONE;

                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!ListenerUtil.mutListener.listen(3973)) {
                        if (Utils.bottomNavigationEnabled(ActivityMain.this)) {
                            if (!ListenerUtil.mutListener.listen(3972)) {
                                mBottomNavigationView.setVisibility(hasFocus ? View.GONE : View.VISIBLE);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3977)) {
                        if (hasFocus) {
                            if (!ListenerUtil.mutListener.listen(3975)) {
                                prevTabsVisibility = tabsView.getVisibility();
                            }
                            if (!ListenerUtil.mutListener.listen(3976)) {
                                tabsView.setVisibility(View.GONE);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(3974)) {
                                tabsView.setVisibility(prevTabsVisibility);
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(3979)) {
            menuItemSleepTimer.setVisible(false);
        }
        if (!ListenerUtil.mutListener.listen(3980)) {
            menuItemSearch.setVisible(false);
        }
        if (!ListenerUtil.mutListener.listen(3981)) {
            menuItemDelete.setVisible(false);
        }
        if (!ListenerUtil.mutListener.listen(3982)) {
            menuItemSave.setVisible(false);
        }
        if (!ListenerUtil.mutListener.listen(3983)) {
            menuItemLoad.setVisible(false);
        }
        if (!ListenerUtil.mutListener.listen(3984)) {
            menuItemListView.setVisible(false);
        }
        if (!ListenerUtil.mutListener.listen(3985)) {
            menuItemIconsView.setVisible(false);
        }
        if (!ListenerUtil.mutListener.listen(3986)) {
            menuItemAddAlarm.setVisible(false);
        }
        boolean mpd_is_visible = false;
        RadioDroidApp radioDroidApp = (RadioDroidApp) getApplication();
        if (!ListenerUtil.mutListener.listen(3989)) {
            if (radioDroidApp != null) {
                MPDClient mpdClient = radioDroidApp.getMpdClient();
                if (!ListenerUtil.mutListener.listen(3988)) {
                    if (mpdClient != null) {
                        MPDServersRepository repository = mpdClient.getMpdServersRepository();
                        if (!ListenerUtil.mutListener.listen(3987)) {
                            mpd_is_visible = !repository.isEmpty();
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3990)) {
            menuItemMpd.setVisible(mpd_is_visible);
        }
        if (!ListenerUtil.mutListener.listen(4013)) {
            switch(selectedMenuItem) {
                case R.id.nav_item_stations:
                    {
                        if (!ListenerUtil.mutListener.listen(3991)) {
                            menuItemSleepTimer.setVisible(true);
                        }
                        if (!ListenerUtil.mutListener.listen(3992)) {
                            menuItemSearch.setVisible(true);
                        }
                        if (!ListenerUtil.mutListener.listen(3993)) {
                            myToolbar.setTitle(R.string.nav_item_stations);
                        }
                        break;
                    }
                case R.id.nav_item_starred:
                    {
                        if (!ListenerUtil.mutListener.listen(3994)) {
                            menuItemSleepTimer.setVisible(true);
                        }
                        if (!ListenerUtil.mutListener.listen(3995)) {
                            // menuItemSearch.setVisible(true);
                            menuItemSave.setVisible(true);
                        }
                        if (!ListenerUtil.mutListener.listen(3996)) {
                            menuItemLoad.setVisible(true);
                        }
                        if (!ListenerUtil.mutListener.listen(3997)) {
                            menuItemSave.setTitle(R.string.nav_item_save_playlist);
                        }
                        if (!ListenerUtil.mutListener.listen(4000)) {
                            if (sharedPref.getBoolean("icons_only_favorites_style", false)) {
                                if (!ListenerUtil.mutListener.listen(3999)) {
                                    menuItemListView.setVisible(true);
                                }
                            } else if (sharedPref.getBoolean("load_icons", false)) {
                                if (!ListenerUtil.mutListener.listen(3998)) {
                                    menuItemIconsView.setVisible(true);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(4003)) {
                            if (radioDroidApp.getFavouriteManager().isEmpty()) {
                                if (!ListenerUtil.mutListener.listen(4002)) {
                                    menuItemDelete.setVisible(false);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(4001)) {
                                    menuItemDelete.setVisible(true).setTitle(R.string.action_delete_favorites);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(4004)) {
                            myToolbar.setTitle(R.string.nav_item_starred);
                        }
                        break;
                    }
                case R.id.nav_item_history:
                    {
                        if (!ListenerUtil.mutListener.listen(4005)) {
                            menuItemSleepTimer.setVisible(true);
                        }
                        if (!ListenerUtil.mutListener.listen(4006)) {
                            // menuItemSearch.setVisible(true);
                            menuItemSave.setVisible(true);
                        }
                        if (!ListenerUtil.mutListener.listen(4007)) {
                            menuItemSave.setTitle(R.string.nav_item_save_history_playlist);
                        }
                        if (!ListenerUtil.mutListener.listen(4009)) {
                            if (!radioDroidApp.getHistoryManager().isEmpty()) {
                                if (!ListenerUtil.mutListener.listen(4008)) {
                                    menuItemDelete.setVisible(true).setTitle(R.string.action_delete_history);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(4010)) {
                            myToolbar.setTitle(R.string.nav_item_history);
                        }
                        break;
                    }
                case R.id.nav_item_alarm:
                    {
                        if (!ListenerUtil.mutListener.listen(4011)) {
                            menuItemAddAlarm.setVisible(true);
                        }
                        if (!ListenerUtil.mutListener.listen(4012)) {
                            myToolbar.setTitle(R.string.nav_item_alarm);
                        }
                        break;
                    }
            }
        }
        if (!ListenerUtil.mutListener.listen(4014)) {
            ((RadioDroidApp) getApplication()).getCastHandler().getRouteItem(getApplicationContext(), menu);
        }
        return true;
    }

    @Override
    public void onFileSelected(FileDialog dialog, File file) {
        try {
            if (!ListenerUtil.mutListener.listen(4016)) {
                Log.i("MAIN", "save to " + file.getParent() + "/" + file.getName());
            }
            RadioDroidApp radioDroidApp = (RadioDroidApp) getApplication();
            FavouriteManager favouriteManager = radioDroidApp.getFavouriteManager();
            HistoryManager historyManager = radioDroidApp.getHistoryManager();
            if (!ListenerUtil.mutListener.listen(4021)) {
                if (dialog instanceof SaveFileDialog) {
                    if (!ListenerUtil.mutListener.listen(4020)) {
                        if (selectedMenuItem == R.id.nav_item_starred) {
                            if (!ListenerUtil.mutListener.listen(4019)) {
                                favouriteManager.SaveM3U(file.getParent(), file.getName());
                            }
                        } else if (selectedMenuItem == R.id.nav_item_history) {
                            if (!ListenerUtil.mutListener.listen(4018)) {
                                historyManager.SaveM3U(file.getParent(), file.getName());
                            }
                        }
                    }
                } else if (dialog instanceof OpenFileDialog) {
                    if (!ListenerUtil.mutListener.listen(4017)) {
                        favouriteManager.LoadM3U(file.getParent(), file.getName());
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(4015)) {
                Log.e("MAIN", e.toString());
            }
        }
    }

    void SaveFavourites() {
        SaveFileDialog dialog = new SaveFileDialog();
        if (!ListenerUtil.mutListener.listen(4022)) {
            dialog.setStyle(DialogFragment.STYLE_NO_TITLE, Utils.getThemeResId(this));
        }
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(4023)) {
            // file extension is optional
            args.putString(FileDialog.EXTENSION, ".m3u");
        }
        if (!ListenerUtil.mutListener.listen(4024)) {
            dialog.setArguments(args);
        }
        if (!ListenerUtil.mutListener.listen(4025)) {
            dialog.show(getSupportFragmentManager(), SaveFileDialog.class.getName());
        }
    }

    void LoadFavourites() {
        OpenFileDialog dialogOpen = new OpenFileDialog();
        if (!ListenerUtil.mutListener.listen(4026)) {
            dialogOpen.setStyle(DialogFragment.STYLE_NO_TITLE, Utils.getThemeResId(this));
        }
        Bundle argsOpen = new Bundle();
        if (!ListenerUtil.mutListener.listen(4027)) {
            // file extension is optional
            argsOpen.putString(FileDialog.EXTENSION, ".m3u");
        }
        if (!ListenerUtil.mutListener.listen(4028)) {
            dialogOpen.setArguments(argsOpen);
        }
        if (!ListenerUtil.mutListener.listen(4029)) {
            dialogOpen.show(getSupportFragmentManager(), OpenFileDialog.class.getName());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (!ListenerUtil.mutListener.listen(4055)) {
            switch(menuItem.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(4030)) {
                        // OPEN DRAWER
                        mDrawerLayout.openDrawer(GravityCompat.START);
                    }
                    return true;
                case R.id.action_save:
                    try {
                        if (!ListenerUtil.mutListener.listen(4033)) {
                            if (Utils.verifyStoragePermissions(this, PERM_REQ_STORAGE_FAV_SAVE)) {
                                if (!ListenerUtil.mutListener.listen(4032)) {
                                    SaveFavourites();
                                }
                            }
                        }
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(4031)) {
                            Log.e("MAIN", e.toString());
                        }
                    }
                    return true;
                case R.id.action_load:
                    try {
                        if (!ListenerUtil.mutListener.listen(4036)) {
                            if (Utils.verifyStoragePermissions(this, PERM_REQ_STORAGE_FAV_LOAD)) {
                                if (!ListenerUtil.mutListener.listen(4035)) {
                                    LoadFavourites();
                                }
                            }
                        }
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(4034)) {
                            Log.e("MAIN", e.toString());
                        }
                    }
                    return true;
                case R.id.action_set_sleep_timer:
                    if (!ListenerUtil.mutListener.listen(4037)) {
                        changeTimer();
                    }
                    return true;
                case R.id.action_mpd:
                    if (!ListenerUtil.mutListener.listen(4038)) {
                        selectMPDServer();
                    }
                    return true;
                case R.id.action_delete:
                    if (!ListenerUtil.mutListener.listen(4043)) {
                        if (selectedMenuItem == R.id.nav_item_history) {
                            if (!ListenerUtil.mutListener.listen(4042)) {
                                new AlertDialog.Builder(this).setMessage(this.getString(R.string.alert_delete_history)).setCancelable(true).setPositiveButton(this.getString(R.string.yes), new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int id) {
                                        RadioDroidApp radioDroidApp = (RadioDroidApp) getApplication();
                                        HistoryManager historyManager = radioDroidApp.getHistoryManager();
                                        if (!ListenerUtil.mutListener.listen(4039)) {
                                            historyManager.clear();
                                        }
                                        Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.notify_deleted_history), Toast.LENGTH_SHORT);
                                        if (!ListenerUtil.mutListener.listen(4040)) {
                                            toast.show();
                                        }
                                        if (!ListenerUtil.mutListener.listen(4041)) {
                                            recreate();
                                        }
                                    }
                                }).setNegativeButton(this.getString(R.string.no), null).show();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4048)) {
                        if (selectedMenuItem == R.id.nav_item_starred) {
                            if (!ListenerUtil.mutListener.listen(4047)) {
                                new AlertDialog.Builder(this).setMessage(this.getString(R.string.alert_delete_favorites)).setCancelable(true).setPositiveButton(this.getString(R.string.yes), new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int id) {
                                        RadioDroidApp radioDroidApp = (RadioDroidApp) getApplication();
                                        FavouriteManager favouriteManager = radioDroidApp.getFavouriteManager();
                                        if (!ListenerUtil.mutListener.listen(4044)) {
                                            favouriteManager.clear();
                                        }
                                        Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.notify_deleted_favorites), Toast.LENGTH_SHORT);
                                        if (!ListenerUtil.mutListener.listen(4045)) {
                                            toast.show();
                                        }
                                        if (!ListenerUtil.mutListener.listen(4046)) {
                                            recreate();
                                        }
                                    }
                                }).setNegativeButton(this.getString(R.string.no), null).show();
                            }
                        }
                    }
                    return true;
                case R.id.action_list_view:
                    if (!ListenerUtil.mutListener.listen(4049)) {
                        sharedPref.edit().putBoolean("icons_only_favorites_style", false).apply();
                    }
                    if (!ListenerUtil.mutListener.listen(4050)) {
                        recreate();
                    }
                    return true;
                case R.id.action_icons_view:
                    if (!ListenerUtil.mutListener.listen(4051)) {
                        sharedPref.edit().putBoolean("icons_only_favorites_style", true).apply();
                    }
                    if (!ListenerUtil.mutListener.listen(4052)) {
                        recreate();
                    }
                    return true;
                case R.id.action_add_alarm:
                    TimePickerFragment newFragment = new TimePickerFragment();
                    if (!ListenerUtil.mutListener.listen(4053)) {
                        newFragment.setCallback(this);
                    }
                    if (!ListenerUtil.mutListener.listen(4054)) {
                        newFragment.show(getSupportFragmentManager(), "timePicker");
                    }
                    return true;
            }
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void toggleBottomSheetState() {
        if (!ListenerUtil.mutListener.listen(4058)) {
            if (playerBottomSheet.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                if (!ListenerUtil.mutListener.listen(4057)) {
                    playerBottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4056)) {
                    playerBottomSheet.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        RadioDroidApp radioDroidApp = (RadioDroidApp) getApplication();
        HistoryManager historyManager = radioDroidApp.getHistoryManager();
        Fragment currentFragment = mFragmentManager.getFragments().get((ListenerUtil.mutListener.listen(4062) ? (mFragmentManager.getFragments().size() % 2) : (ListenerUtil.mutListener.listen(4061) ? (mFragmentManager.getFragments().size() / 2) : (ListenerUtil.mutListener.listen(4060) ? (mFragmentManager.getFragments().size() * 2) : (ListenerUtil.mutListener.listen(4059) ? (mFragmentManager.getFragments().size() + 2) : (mFragmentManager.getFragments().size() - 2))))));
        if (!ListenerUtil.mutListener.listen(4070)) {
            if ((ListenerUtil.mutListener.listen(4068) ? ((ListenerUtil.mutListener.listen(4067) ? (historyManager.size() >= 0) : (ListenerUtil.mutListener.listen(4066) ? (historyManager.size() <= 0) : (ListenerUtil.mutListener.listen(4065) ? (historyManager.size() < 0) : (ListenerUtil.mutListener.listen(4064) ? (historyManager.size() != 0) : (ListenerUtil.mutListener.listen(4063) ? (historyManager.size() == 0) : (historyManager.size() > 0)))))) || currentFragment instanceof FragmentAlarm) : ((ListenerUtil.mutListener.listen(4067) ? (historyManager.size() >= 0) : (ListenerUtil.mutListener.listen(4066) ? (historyManager.size() <= 0) : (ListenerUtil.mutListener.listen(4065) ? (historyManager.size() < 0) : (ListenerUtil.mutListener.listen(4064) ? (historyManager.size() != 0) : (ListenerUtil.mutListener.listen(4063) ? (historyManager.size() == 0) : (historyManager.size() > 0)))))) && currentFragment instanceof FragmentAlarm))) {
                DataRadioStation station = historyManager.getList().get(0);
                if (!ListenerUtil.mutListener.listen(4069)) {
                    ((FragmentAlarm) currentFragment).getRam().add(station, hourOfDay, minute);
                }
            }
        }
    }

    private void setupStartUpFragment() {
        if (!ListenerUtil.mutListener.listen(4073)) {
            // This will restore fragment that was shown before activity was recreated
            if (instanceStateWasSaved) {
                if (!ListenerUtil.mutListener.listen(4071)) {
                    invalidateOptionsMenu();
                }
                if (!ListenerUtil.mutListener.listen(4072)) {
                    checkMenuItems();
                }
                return;
            }
        }
        RadioDroidApp radioDroidApp = (RadioDroidApp) getApplication();
        HistoryManager hm = radioDroidApp.getHistoryManager();
        FavouriteManager fm = radioDroidApp.getFavouriteManager();
        final String startupAction = sharedPref.getString("startup_action", getResources().getString(R.string.startup_show_history));
        if (!ListenerUtil.mutListener.listen(4076)) {
            if ((ListenerUtil.mutListener.listen(4074) ? (startupAction.equals(getResources().getString(R.string.startup_show_history)) || hm.isEmpty()) : (startupAction.equals(getResources().getString(R.string.startup_show_history)) && hm.isEmpty()))) {
                if (!ListenerUtil.mutListener.listen(4075)) {
                    selectMenuItem(R.id.nav_item_stations);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4079)) {
            if ((ListenerUtil.mutListener.listen(4077) ? (startupAction.equals(getResources().getString(R.string.startup_show_favorites)) || fm.isEmpty()) : (startupAction.equals(getResources().getString(R.string.startup_show_favorites)) && fm.isEmpty()))) {
                if (!ListenerUtil.mutListener.listen(4078)) {
                    selectMenuItem(R.id.nav_item_stations);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4090)) {
            if (startupAction.equals(getResources().getString(R.string.startup_show_history))) {
                if (!ListenerUtil.mutListener.listen(4089)) {
                    selectMenuItem(R.id.nav_item_history);
                }
            } else if (startupAction.equals(getResources().getString(R.string.startup_show_favorites))) {
                if (!ListenerUtil.mutListener.listen(4088)) {
                    selectMenuItem(R.id.nav_item_starred);
                }
            } else if ((ListenerUtil.mutListener.listen(4085) ? (startupAction.equals(getResources().getString(R.string.startup_show_all_stations)) && (ListenerUtil.mutListener.listen(4084) ? (selectedMenuItem >= 0) : (ListenerUtil.mutListener.listen(4083) ? (selectedMenuItem <= 0) : (ListenerUtil.mutListener.listen(4082) ? (selectedMenuItem > 0) : (ListenerUtil.mutListener.listen(4081) ? (selectedMenuItem != 0) : (ListenerUtil.mutListener.listen(4080) ? (selectedMenuItem == 0) : (selectedMenuItem < 0))))))) : (startupAction.equals(getResources().getString(R.string.startup_show_all_stations)) || (ListenerUtil.mutListener.listen(4084) ? (selectedMenuItem >= 0) : (ListenerUtil.mutListener.listen(4083) ? (selectedMenuItem <= 0) : (ListenerUtil.mutListener.listen(4082) ? (selectedMenuItem > 0) : (ListenerUtil.mutListener.listen(4081) ? (selectedMenuItem != 0) : (ListenerUtil.mutListener.listen(4080) ? (selectedMenuItem == 0) : (selectedMenuItem < 0))))))))) {
                if (!ListenerUtil.mutListener.listen(4087)) {
                    selectMenuItem(R.id.nav_item_stations);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4086)) {
                    selectMenuItem(selectedMenuItem);
                }
            }
        }
    }

    private void selectMenuItem(int itemId) {
        MenuItem item;
        if (Utils.bottomNavigationEnabled(this))
            item = mBottomNavigationView.getMenu().findItem(itemId);
        else
            item = mNavigationView.getMenu().findItem(itemId);
        if (!ListenerUtil.mutListener.listen(4094)) {
            if (item != null) {
                if (!ListenerUtil.mutListener.listen(4093)) {
                    onNavigationItemSelected(item);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4091)) {
                    selectedMenuItem = R.id.nav_item_stations;
                }
                if (!ListenerUtil.mutListener.listen(4092)) {
                    onNavigationItemSelected(null);
                }
            }
        }
    }

    private void checkMenuItems() {
        if (!ListenerUtil.mutListener.listen(4096)) {
            if (mBottomNavigationView.getMenu().findItem(selectedMenuItem) != null)
                if (!ListenerUtil.mutListener.listen(4095)) {
                    mBottomNavigationView.getMenu().findItem(selectedMenuItem).setChecked(true);
                }
        }
        if (!ListenerUtil.mutListener.listen(4098)) {
            if (mNavigationView.getMenu().findItem(selectedMenuItem) != null)
                if (!ListenerUtil.mutListener.listen(4097)) {
                    mNavigationView.getMenu().findItem(selectedMenuItem).setChecked(true);
                }
        }
    }

    public void Search(StationsFilter.SearchStyle searchStyle, String query) {
        if (!ListenerUtil.mutListener.listen(4099)) {
            Log.d("MAIN", "Search() searchstyle=" + searchStyle + " query=" + query);
        }
        Fragment currentFragment = mFragmentManager.getFragments().get((ListenerUtil.mutListener.listen(4103) ? (mFragmentManager.getFragments().size() % 1) : (ListenerUtil.mutListener.listen(4102) ? (mFragmentManager.getFragments().size() / 1) : (ListenerUtil.mutListener.listen(4101) ? (mFragmentManager.getFragments().size() * 1) : (ListenerUtil.mutListener.listen(4100) ? (mFragmentManager.getFragments().size() + 1) : (mFragmentManager.getFragments().size() - 1))))));
        if (!ListenerUtil.mutListener.listen(4113)) {
            if (currentFragment instanceof FragmentTabs) {
                if (!ListenerUtil.mutListener.listen(4112)) {
                    ((FragmentTabs) currentFragment).Search(searchStyle, query);
                }
            } else {
                String backStackTag = String.valueOf(R.id.nav_item_stations);
                FragmentTabs f = new FragmentTabs();
                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                if (!ListenerUtil.mutListener.listen(4108)) {
                    if (Utils.bottomNavigationEnabled(this)) {
                        if (!ListenerUtil.mutListener.listen(4106)) {
                            fragmentTransaction.replace(R.id.containerView, f).commit();
                        }
                        if (!ListenerUtil.mutListener.listen(4107)) {
                            mBottomNavigationView.getMenu().findItem(R.id.nav_item_stations).setChecked(true);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(4104)) {
                            fragmentTransaction.replace(R.id.containerView, f).addToBackStack(backStackTag).commit();
                        }
                        if (!ListenerUtil.mutListener.listen(4105)) {
                            mNavigationView.getMenu().findItem(R.id.nav_item_stations).setChecked(true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(4109)) {
                    f.Search(searchStyle, query);
                }
                if (!ListenerUtil.mutListener.listen(4110)) {
                    selectedMenuItem = R.id.nav_item_stations;
                }
                if (!ListenerUtil.mutListener.listen(4111)) {
                    invalidateOptionsMenu();
                }
            }
        }
    }

    public void SearchStations(@NonNull String query) {
        if (!ListenerUtil.mutListener.listen(4114)) {
            Log.d("MAIN", "SearchStations() " + query);
        }
        Fragment currentFragment = mFragmentManager.getFragments().get((ListenerUtil.mutListener.listen(4118) ? (mFragmentManager.getFragments().size() % 1) : (ListenerUtil.mutListener.listen(4117) ? (mFragmentManager.getFragments().size() / 1) : (ListenerUtil.mutListener.listen(4116) ? (mFragmentManager.getFragments().size() * 1) : (ListenerUtil.mutListener.listen(4115) ? (mFragmentManager.getFragments().size() + 1) : (mFragmentManager.getFragments().size() - 1))))));
        if (!ListenerUtil.mutListener.listen(4120)) {
            if (currentFragment instanceof IFragmentSearchable) {
                if (!ListenerUtil.mutListener.listen(4119)) {
                    ((IFragmentSearchable) currentFragment).Search(StationsFilter.SearchStyle.ByName, query);
                }
            }
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        // }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (!ListenerUtil.mutListener.listen(4121)) {
            SearchStations(newText);
        }
        return true;
    }

    private void showMeteredConnectionDialog(@NonNull Runnable playFunc) {
        Resources res = this.getResources();
        String title = res.getString(R.string.alert_metered_connection_title);
        String text = res.getString(R.string.alert_metered_connection_message);
        if (!ListenerUtil.mutListener.listen(4122)) {
            meteredConnectionAlertDialog = new AlertDialog.Builder(this).setTitle(title).setMessage(text).setNegativeButton(android.R.string.cancel, null).setPositiveButton(android.R.string.ok, (dialog, which) -> playFunc.run()).setOnDismissListener(dialog -> meteredConnectionAlertDialog = null).create();
        }
        if (!ListenerUtil.mutListener.listen(4123)) {
            meteredConnectionAlertDialog.show();
        }
    }

    private void setupBroadcastReceiver() {
        IntentFilter filter = new IntentFilter();
        if (!ListenerUtil.mutListener.listen(4124)) {
            filter.addAction(ACTION_HIDE_LOADING);
        }
        if (!ListenerUtil.mutListener.listen(4125)) {
            filter.addAction(ACTION_SHOW_LOADING);
        }
        if (!ListenerUtil.mutListener.listen(4126)) {
            filter.addAction(PlayerService.PLAYER_SERVICE_STATE_CHANGE);
        }
        if (!ListenerUtil.mutListener.listen(4127)) {
            filter.addAction(PlayerService.PLAYER_SERVICE_METERED_CONNECTION);
        }
        if (!ListenerUtil.mutListener.listen(4143)) {
            broadcastReceiver = new BroadcastReceiver() {

                @Override
                public void onReceive(Context context, Intent intent) {
                    if (!ListenerUtil.mutListener.listen(4142)) {
                        if (intent.getAction().equals(ACTION_HIDE_LOADING)) {
                            if (!ListenerUtil.mutListener.listen(4141)) {
                                hideLoadingIcon();
                            }
                        } else if (intent.getAction().equals(ACTION_SHOW_LOADING)) {
                            if (!ListenerUtil.mutListener.listen(4140)) {
                                showLoadingIcon();
                            }
                        } else if (intent.getAction().equals(PlayerService.PLAYER_SERVICE_METERED_CONNECTION)) {
                            if (!ListenerUtil.mutListener.listen(4134)) {
                                if (meteredConnectionAlertDialog != null) {
                                    if (!ListenerUtil.mutListener.listen(4132)) {
                                        meteredConnectionAlertDialog.cancel();
                                    }
                                    if (!ListenerUtil.mutListener.listen(4133)) {
                                        meteredConnectionAlertDialog = null;
                                    }
                                }
                            }
                            PlayerType playerType = intent.getParcelableExtra(PlayerService.PLAYER_SERVICE_METERED_CONNECTION_PLAYER_TYPE);
                            if (!ListenerUtil.mutListener.listen(4139)) {
                                switch(playerType) {
                                    case RADIODROID:
                                        if (!ListenerUtil.mutListener.listen(4135)) {
                                            showMeteredConnectionDialog(() -> Utils.play((RadioDroidApp) getApplication(), PlayerServiceUtil.getCurrentStation()));
                                        }
                                        break;
                                    case EXTERNAL:
                                        DataRadioStation currentStation = PlayerServiceUtil.getCurrentStation();
                                        if (!ListenerUtil.mutListener.listen(4137)) {
                                            if (currentStation != null) {
                                                if (!ListenerUtil.mutListener.listen(4136)) {
                                                    showMeteredConnectionDialog(() -> PlayStationTask.playExternal(currentStation, ActivityMain.this).execute());
                                                }
                                            }
                                        }
                                        break;
                                    default:
                                        if (!ListenerUtil.mutListener.listen(4138)) {
                                            Log.e(TAG, String.format("broadcastReceiver unexpected PlayerType '%s'", playerType.toString()));
                                        }
                                }
                            }
                        } else if (intent.getAction().equals(PlayerService.PLAYER_SERVICE_STATE_CHANGE)) {
                            if (!ListenerUtil.mutListener.listen(4131)) {
                                if (PlayerServiceUtil.isPlaying()) {
                                    if (!ListenerUtil.mutListener.listen(4130)) {
                                        if (meteredConnectionAlertDialog != null) {
                                            if (!ListenerUtil.mutListener.listen(4128)) {
                                                meteredConnectionAlertDialog.cancel();
                                            }
                                            if (!ListenerUtil.mutListener.listen(4129)) {
                                                meteredConnectionAlertDialog = null;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            };
        }
        if (!ListenerUtil.mutListener.listen(4144)) {
            LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, filter);
        }
    }

    // Loading listener
    private void showLoadingIcon() {
        if (!ListenerUtil.mutListener.listen(4145)) {
            findViewById(R.id.progressBarLoading).setVisibility(View.VISIBLE);
        }
    }

    private void hideLoadingIcon() {
        if (!ListenerUtil.mutListener.listen(4146)) {
            findViewById(R.id.progressBarLoading).setVisibility(View.GONE);
        }
    }

    private void changeTimer() {
        final AlertDialog.Builder seekDialog = new AlertDialog.Builder(this);
        View seekView = View.inflate(this, R.layout.layout_timer_chooser, null);
        if (!ListenerUtil.mutListener.listen(4147)) {
            seekDialog.setTitle(R.string.sleep_timer_title);
        }
        if (!ListenerUtil.mutListener.listen(4148)) {
            seekDialog.setView(seekView);
        }
        final TextView seekTextView = (TextView) seekView.findViewById(R.id.timerTextView);
        final SeekBar seekBar = (SeekBar) seekView.findViewById(R.id.timerSeekBar);
        if (!ListenerUtil.mutListener.listen(4150)) {
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (!ListenerUtil.mutListener.listen(4149)) {
                        seekTextView.setText(String.valueOf(progress));
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
        }
        long currenTimerSeconds = PlayerServiceUtil.getTimerSeconds();
        long currentTimer;
        if ((ListenerUtil.mutListener.listen(4155) ? (currenTimerSeconds >= 0) : (ListenerUtil.mutListener.listen(4154) ? (currenTimerSeconds > 0) : (ListenerUtil.mutListener.listen(4153) ? (currenTimerSeconds < 0) : (ListenerUtil.mutListener.listen(4152) ? (currenTimerSeconds != 0) : (ListenerUtil.mutListener.listen(4151) ? (currenTimerSeconds == 0) : (currenTimerSeconds <= 0))))))) {
            currentTimer = sharedPref.getInt("sleep_timer_default_minutes", 10);
        } else if ((ListenerUtil.mutListener.listen(4160) ? (currenTimerSeconds >= 60) : (ListenerUtil.mutListener.listen(4159) ? (currenTimerSeconds <= 60) : (ListenerUtil.mutListener.listen(4158) ? (currenTimerSeconds > 60) : (ListenerUtil.mutListener.listen(4157) ? (currenTimerSeconds != 60) : (ListenerUtil.mutListener.listen(4156) ? (currenTimerSeconds == 60) : (currenTimerSeconds < 60))))))) {
            currentTimer = 1;
        } else {
            currentTimer = (ListenerUtil.mutListener.listen(4164) ? (currenTimerSeconds % 60) : (ListenerUtil.mutListener.listen(4163) ? (currenTimerSeconds * 60) : (ListenerUtil.mutListener.listen(4162) ? (currenTimerSeconds - 60) : (ListenerUtil.mutListener.listen(4161) ? (currenTimerSeconds + 60) : (currenTimerSeconds / 60)))));
        }
        if (!ListenerUtil.mutListener.listen(4165)) {
            seekBar.setProgress((int) currentTimer);
        }
        if (!ListenerUtil.mutListener.listen(4173)) {
            seekDialog.setPositiveButton(R.string.sleep_timer_apply, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!ListenerUtil.mutListener.listen(4166)) {
                        PlayerServiceUtil.clearTimer();
                    }
                    if (!ListenerUtil.mutListener.listen(4171)) {
                        PlayerServiceUtil.addTimer((ListenerUtil.mutListener.listen(4170) ? (seekBar.getProgress() % 60) : (ListenerUtil.mutListener.listen(4169) ? (seekBar.getProgress() / 60) : (ListenerUtil.mutListener.listen(4168) ? (seekBar.getProgress() - 60) : (ListenerUtil.mutListener.listen(4167) ? (seekBar.getProgress() + 60) : (seekBar.getProgress() * 60))))));
                    }
                    if (!ListenerUtil.mutListener.listen(4172)) {
                        sharedPref.edit().putInt("sleep_timer_default_minutes", seekBar.getProgress()).apply();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(4175)) {
            seekDialog.setNegativeButton(R.string.sleep_timer_clear, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!ListenerUtil.mutListener.listen(4174)) {
                        PlayerServiceUtil.clearTimer();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(4176)) {
            seekDialog.create();
        }
        if (!ListenerUtil.mutListener.listen(4177)) {
            seekDialog.show();
        }
    }

    private void selectMPDServer() {
        RadioDroidApp radioDroidApp = (RadioDroidApp) getApplication();
        if (!ListenerUtil.mutListener.listen(4178)) {
            Utils.showMpdServersDialog(radioDroidApp, getSupportFragmentManager(), null);
        }
    }

    public final Toolbar getToolbar() {
        return (Toolbar) findViewById(R.id.my_awesome_toolbar);
    }

    @Override
    public void onSearchResultClicked(SearchPreferenceResult result) {
        if (!ListenerUtil.mutListener.listen(4179)) {
            result.closeSearchPage(this);
        }
        if (!ListenerUtil.mutListener.listen(4180)) {
            getSupportFragmentManager().popBackStack();
        }
        FragmentSettings f = FragmentSettings.openNewSettingsSubFragment(this, result.getScreen());
        if (!ListenerUtil.mutListener.listen(4181)) {
            result.highlight(f, Utils.getAccentColor(this));
        }
    }

    @Override
    public void invalidateOptionsMenuForCast() {
        if (!ListenerUtil.mutListener.listen(4182)) {
            invalidateOptionsMenu();
        }
    }
}
