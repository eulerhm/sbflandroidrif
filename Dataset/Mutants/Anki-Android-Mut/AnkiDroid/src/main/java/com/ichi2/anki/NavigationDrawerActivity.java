/**
 * *************************************************************************************
 *  Copyright (c) 2014 Timothy Rae <perceptualchaos2@gmail.com>                          *
 *                                                                                       *
 *  This program is free software; you can redistribute it and/or modify it under        *
 *  the terms of the GNU General Public License as published by the Free Software        *
 *  Foundation; either version 3 of the License, or (at your option) any later           *
 *  version.                                                                             *
 *                                                                                       *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                       *
 *  You should have received a copy of the GNU General Public License along with         *
 *  this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 * **************************************************************************************
 */
package com.ichi2.anki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import com.google.android.material.navigation.NavigationView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.TaskStackBuilder;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.ichi2.anki.dialogs.HelpDialog;
import com.ichi2.themes.Themes;
import androidx.drawerlayout.widget.ClosableDrawerLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import timber.log.Timber;
import static com.ichi2.anim.ActivityTransitionAnimation.Direction.*;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class NavigationDrawerActivity extends AnkiActivity implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * Navigation Drawer
     */
    protected CharSequence mTitle;

    protected Boolean mFragmented = false;

    private boolean mNavButtonGoesBack = false;

    // Other members
    private String mOldColPath;

    private int mOldTheme;

    // Navigation drawer list item entries
    private DrawerLayout mDrawerLayout;

    private NavigationView mNavigationView;

    private ActionBarDrawerToggle mDrawerToggle;

    private SwitchCompat mNightModeSwitch;

    // Intent request codes
    public static final int REQUEST_PREFERENCES_UPDATE = 100;

    public static final int REQUEST_BROWSE_CARDS = 101;

    public static final int REQUEST_STATISTICS = 102;

    private static final String NIGHT_MODE_PREFERENCE = "invertedColors";

    /**
     * runnable that will be executed after the drawer has been closed.
     */
    private Runnable pendingRunnable;

    // Navigation drawer initialisation
    protected void initNavigationDrawer(View mainView) {
        if (!ListenerUtil.mutListener.listen(9224)) {
            // Create inherited navigation drawer layout here so that it can be used by parent class
            mDrawerLayout = mainView.findViewById(R.id.drawer_layout);
        }
        if (!ListenerUtil.mutListener.listen(9225)) {
            // set a custom shadow that overlays the main content when the drawer opens
            mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        }
        if (!ListenerUtil.mutListener.listen(9226)) {
            // Force transparent status bar with primary dark color underlayed so that the drawer displays under status bar
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.transparent));
        }
        if (!ListenerUtil.mutListener.listen(9227)) {
            mDrawerLayout.setStatusBarBackgroundColor(Themes.getColorFromAttr(this, R.attr.colorPrimaryDark));
        }
        if (!ListenerUtil.mutListener.listen(9228)) {
            // Setup toolbar and hamburger
            mNavigationView = mDrawerLayout.findViewById(R.id.navdrawer_items_container);
        }
        if (!ListenerUtil.mutListener.listen(9229)) {
            mNavigationView.setNavigationItemSelectedListener(this);
        }
        Toolbar toolbar = mainView.findViewById(R.id.toolbar);
        if (!ListenerUtil.mutListener.listen(9234)) {
            if (toolbar != null) {
                if (!ListenerUtil.mutListener.listen(9230)) {
                    setSupportActionBar(toolbar);
                }
                if (!ListenerUtil.mutListener.listen(9231)) {
                    // enable ActionBar app icon to behave as action to toggle nav drawer
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(9232)) {
                    getSupportActionBar().setHomeButtonEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(9233)) {
                    // Decide which action to take when the navigation button is tapped.
                    toolbar.setNavigationOnClickListener(v -> onNavigationPressed());
                }
            }
        }
        // Configure night-mode switch
        final SharedPreferences preferences = getPreferences();
        View actionLayout = mNavigationView.getMenu().findItem(R.id.nav_night_mode).getActionView();
        if (!ListenerUtil.mutListener.listen(9235)) {
            mNightModeSwitch = actionLayout.findViewById(R.id.switch_compat);
        }
        if (!ListenerUtil.mutListener.listen(9236)) {
            mNightModeSwitch.setChecked(preferences.getBoolean(NIGHT_MODE_PREFERENCE, false));
        }
        if (!ListenerUtil.mutListener.listen(9237)) {
            mNightModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> applyNightMode(isChecked));
        }
        if (!ListenerUtil.mutListener.listen(9243)) {
            // between the sliding drawer and the action bar app icon
            mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, 0, 0) {

                @Override
                public void onDrawerClosed(View drawerView) {
                    if (!ListenerUtil.mutListener.listen(9238)) {
                        super.onDrawerClosed(drawerView);
                    }
                    if (!ListenerUtil.mutListener.listen(9239)) {
                        supportInvalidateOptionsMenu();
                    }
                    if (!ListenerUtil.mutListener.listen(9240)) {
                        // PERF: May be able to reduce this delay
                        new Handler().postDelayed(() -> {
                            if (pendingRunnable != null) {
                                new Handler().post(pendingRunnable);
                                pendingRunnable = null;
                            }
                        }, 100);
                    }
                }

                @Override
                public void onDrawerOpened(View drawerView) {
                    if (!ListenerUtil.mutListener.listen(9241)) {
                        super.onDrawerOpened(drawerView);
                    }
                    if (!ListenerUtil.mutListener.listen(9242)) {
                        supportInvalidateOptionsMenu();
                    }
                }
            };
        }
        if (!ListenerUtil.mutListener.listen(9246)) {
            if (mDrawerLayout instanceof ClosableDrawerLayout) {
                if (!ListenerUtil.mutListener.listen(9245)) {
                    ((ClosableDrawerLayout) mDrawerLayout).setAnimationEnabled(animationEnabled());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9244)) {
                    Timber.w("Unexpected Drawer layout - could not modify navigation animation");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9247)) {
            mDrawerToggle.setDrawerSlideAnimationEnabled(animationEnabled());
        }
        if (!ListenerUtil.mutListener.listen(9248)) {
            mDrawerLayout.addDrawerListener(mDrawerToggle);
        }
    }

    /**
     * Sets selected navigation drawer item
     */
    protected void selectNavigationItem(int itemId) {
        if (!ListenerUtil.mutListener.listen(9250)) {
            if (mNavigationView == null) {
                if (!ListenerUtil.mutListener.listen(9249)) {
                    Timber.e("Could not select item in navigation drawer as NavigationView null");
                }
                return;
            }
        }
        Menu menu = mNavigationView.getMenu();
        if (!ListenerUtil.mutListener.listen(9266)) {
            if ((ListenerUtil.mutListener.listen(9255) ? (itemId >= -1) : (ListenerUtil.mutListener.listen(9254) ? (itemId <= -1) : (ListenerUtil.mutListener.listen(9253) ? (itemId > -1) : (ListenerUtil.mutListener.listen(9252) ? (itemId < -1) : (ListenerUtil.mutListener.listen(9251) ? (itemId != -1) : (itemId == -1))))))) {
                if (!ListenerUtil.mutListener.listen(9265)) {
                    {
                        long _loopCounter147 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(9264) ? (i >= menu.size()) : (ListenerUtil.mutListener.listen(9263) ? (i <= menu.size()) : (ListenerUtil.mutListener.listen(9262) ? (i > menu.size()) : (ListenerUtil.mutListener.listen(9261) ? (i != menu.size()) : (ListenerUtil.mutListener.listen(9260) ? (i == menu.size()) : (i < menu.size())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter147", ++_loopCounter147);
                            if (!ListenerUtil.mutListener.listen(9259)) {
                                menu.getItem(i).setChecked(false);
                            }
                        }
                    }
                }
            } else {
                MenuItem item = menu.findItem(itemId);
                if (!ListenerUtil.mutListener.listen(9258)) {
                    if (item != null) {
                        if (!ListenerUtil.mutListener.listen(9257)) {
                            item.setChecked(true);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(9256)) {
                            Timber.e("Could not find item %d", itemId);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        if (!ListenerUtil.mutListener.listen(9267)) {
            mTitle = title;
        }
        if (!ListenerUtil.mutListener.listen(9269)) {
            if (getSupportActionBar() != null) {
                if (!ListenerUtil.mutListener.listen(9268)) {
                    getSupportActionBar().setTitle(mTitle);
                }
            }
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(9270)) {
            super.onPostCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(9272)) {
            // Sync the toggle state after onRestoreInstanceState has occurred.
            if (mDrawerToggle != null) {
                if (!ListenerUtil.mutListener.listen(9271)) {
                    mDrawerToggle.syncState();
                }
            }
        }
    }

    private SharedPreferences getPreferences() {
        return AnkiDroidApp.getSharedPrefs(NavigationDrawerActivity.this);
    }

    private void applyNightMode(boolean setToNightMode) {
        final SharedPreferences preferences = getPreferences();
        if (!ListenerUtil.mutListener.listen(9273)) {
            Timber.i("Night mode was %s", setToNightMode ? "enabled" : "disabled");
        }
        if (!ListenerUtil.mutListener.listen(9274)) {
            preferences.edit().putBoolean(NIGHT_MODE_PREFERENCE, setToNightMode).apply();
        }
        if (!ListenerUtil.mutListener.listen(9275)) {
            restartActivityInvalidateBackstack(NavigationDrawerActivity.this);
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        if (!ListenerUtil.mutListener.listen(9276)) {
            super.onConfigurationChanged(newConfig);
        }
        if (!ListenerUtil.mutListener.listen(9278)) {
            // Pass any configuration change to the drawer toggles
            if (mDrawerToggle != null) {
                if (!ListenerUtil.mutListener.listen(9277)) {
                    mDrawerToggle.onConfigurationChanged(newConfig);
                }
            }
        }
    }

    public ActionBarDrawerToggle getDrawerToggle() {
        return mDrawerToggle;
    }

    /**
     * This function locks the navigation drawer closed in regards to swipes,
     * but continues to allowed it to be opened via it's indicator button. This
     * function in a noop if the drawer hasn't been initialized.
     */
    protected void disableDrawerSwipe() {
        if (!ListenerUtil.mutListener.listen(9280)) {
            if (mDrawerLayout != null) {
                if (!ListenerUtil.mutListener.listen(9279)) {
                    mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                }
            }
        }
    }

    /**
     * This function allows swipes to open the navigation drawer. This
     * function in a noop if the drawer hasn't been initialized.
     */
    protected void enableDrawerSwipe() {
        if (!ListenerUtil.mutListener.listen(9282)) {
            if (mDrawerLayout != null) {
                if (!ListenerUtil.mutListener.listen(9281)) {
                    mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        final SharedPreferences preferences = getPreferences();
        if (!ListenerUtil.mutListener.listen(9283)) {
            Timber.i("Handling Activity Result: %d. Result: %d", requestCode, resultCode);
        }
        if (!ListenerUtil.mutListener.listen(9284)) {
            NotificationChannels.setup(getApplicationContext());
        }
        if (!ListenerUtil.mutListener.listen(9300)) {
            // Restart the activity on preference change
            if ((ListenerUtil.mutListener.listen(9289) ? (requestCode >= REQUEST_PREFERENCES_UPDATE) : (ListenerUtil.mutListener.listen(9288) ? (requestCode <= REQUEST_PREFERENCES_UPDATE) : (ListenerUtil.mutListener.listen(9287) ? (requestCode > REQUEST_PREFERENCES_UPDATE) : (ListenerUtil.mutListener.listen(9286) ? (requestCode < REQUEST_PREFERENCES_UPDATE) : (ListenerUtil.mutListener.listen(9285) ? (requestCode != REQUEST_PREFERENCES_UPDATE) : (requestCode == REQUEST_PREFERENCES_UPDATE))))))) {
                if (!ListenerUtil.mutListener.listen(9299)) {
                    if ((ListenerUtil.mutListener.listen(9291) ? (mOldColPath != null || CollectionHelper.getCurrentAnkiDroidDirectory(this).equals(mOldColPath)) : (mOldColPath != null && CollectionHelper.getCurrentAnkiDroidDirectory(this).equals(mOldColPath)))) {
                        if (!ListenerUtil.mutListener.listen(9298)) {
                            // collection path hasn't been changed so just restart the current activity
                            if ((ListenerUtil.mutListener.listen(9294) ? ((this instanceof Reviewer) || preferences.getBoolean("tts", false)) : ((this instanceof Reviewer) && preferences.getBoolean("tts", false)))) {
                                if (!ListenerUtil.mutListener.listen(9297)) {
                                    // because onDestroy() of old Activity interferes with TTS in new Activity
                                    finishWithoutAnimation();
                                }
                            } else if (mOldTheme != Themes.getCurrentTheme(getApplicationContext())) {
                                if (!ListenerUtil.mutListener.listen(9296)) {
                                    // The current theme was changed, so need to reload the stack with the new theme
                                    restartActivityInvalidateBackstack(this);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(9295)) {
                                    restartActivity();
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(9292)) {
                            // collection path has changed so kick the user back to the DeckPicker
                            CollectionHelper.getInstance().closeCollection(true, "Preference Modification: collection path changed");
                        }
                        if (!ListenerUtil.mutListener.listen(9293)) {
                            restartActivityInvalidateBackstack(this);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9290)) {
                    super.onActivityResult(requestCode, resultCode, data);
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(9304)) {
            if (isDrawerOpen()) {
                if (!ListenerUtil.mutListener.listen(9302)) {
                    Timber.i("Back key pressed");
                }
                if (!ListenerUtil.mutListener.listen(9303)) {
                    closeDrawer();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9301)) {
                    super.onBackPressed();
                }
            }
        }
    }

    /**
     * Called, when navigation button of the action bar is pressed.
     * Design pattern: template method. Subclasses can override this to define their own behaviour.
     */
    protected void onNavigationPressed() {
        if (!ListenerUtil.mutListener.listen(9307)) {
            if (mNavButtonGoesBack) {
                if (!ListenerUtil.mutListener.listen(9306)) {
                    finishWithAnimation(RIGHT);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9305)) {
                    openDrawer();
                }
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(final MenuItem item) {
        if (!ListenerUtil.mutListener.listen(9308)) {
            // Don't do anything if user selects already selected position
            if (item.isChecked()) {
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(9309)) {
            /*
         * This runnable will be executed in onDrawerClosed(...)
         * to make the animation more fluid on older devices.
         */
            pendingRunnable = () -> {
                // Take action if a different item selected
                int itemId = item.getItemId();
                if (itemId == R.id.nav_decks) {
                    Timber.i("Navigating to decks");
                    Intent deckPicker = new Intent(NavigationDrawerActivity.this, DeckPicker.class);
                    // opening DeckPicker should clear back history
                    deckPicker.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivityWithAnimation(deckPicker, RIGHT);
                } else if (itemId == R.id.nav_browser) {
                    Timber.i("Navigating to card browser");
                    openCardBrowser();
                } else if (itemId == R.id.nav_stats) {
                    Timber.i("Navigating to stats");
                    Intent intent = new Intent(NavigationDrawerActivity.this, Statistics.class);
                    startActivityForResultWithAnimation(intent, REQUEST_STATISTICS, LEFT);
                } else if (itemId == R.id.nav_night_mode) {
                    Timber.i("Toggling Night Mode");
                    mNightModeSwitch.performClick();
                } else if (itemId == R.id.nav_settings) {
                    Timber.i("Navigating to settings");
                    mOldColPath = CollectionHelper.getCurrentAnkiDroidDirectory(NavigationDrawerActivity.this);
                    // Remember the theme we started with so we can restart the Activity if it changes
                    mOldTheme = Themes.getCurrentTheme(getApplicationContext());
                    startActivityForResultWithAnimation(new Intent(NavigationDrawerActivity.this, Preferences.class), REQUEST_PREFERENCES_UPDATE, FADE);
                } else if (itemId == R.id.nav_help) {
                    Timber.i("Navigating to help");
                    showDialogFragment(HelpDialog.createInstance(this));
                }
            };
        }
        if (!ListenerUtil.mutListener.listen(9310)) {
            closeDrawer();
        }
        return true;
    }

    protected void openCardBrowser() {
        Intent intent = new Intent(NavigationDrawerActivity.this, CardBrowser.class);
        Long currentCardId = getCurrentCardId();
        if (!ListenerUtil.mutListener.listen(9312)) {
            if (currentCardId != null) {
                if (!ListenerUtil.mutListener.listen(9311)) {
                    intent.putExtra("currentCard", currentCardId);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9313)) {
            startActivityForResultWithAnimation(intent, REQUEST_BROWSE_CARDS, LEFT);
        }
    }

    // Override this to specify a specific card id
    @Nullable
    protected Long getCurrentCardId() {
        return null;
    }

    protected void showBackIcon() {
        if (!ListenerUtil.mutListener.listen(9315)) {
            if (mDrawerToggle != null) {
                if (!ListenerUtil.mutListener.listen(9314)) {
                    mDrawerToggle.setDrawerIndicatorEnabled(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9317)) {
            if (getSupportActionBar() != null) {
                if (!ListenerUtil.mutListener.listen(9316)) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9318)) {
            mNavButtonGoesBack = true;
        }
    }

    protected void restoreDrawerIcon() {
        if (!ListenerUtil.mutListener.listen(9320)) {
            if (mDrawerToggle != null) {
                if (!ListenerUtil.mutListener.listen(9319)) {
                    getDrawerToggle().setDrawerIndicatorEnabled(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9321)) {
            mNavButtonGoesBack = false;
        }
    }

    public boolean isDrawerOpen() {
        return mDrawerLayout.isDrawerOpen(GravityCompat.START);
    }

    /**
     * Restart the activity and discard old backstack, creating it new from the hierarchy in the manifest
     */
    protected void restartActivityInvalidateBackstack(AnkiActivity activity) {
        if (!ListenerUtil.mutListener.listen(9322)) {
            Timber.i("AnkiActivity -- restartActivityInvalidateBackstack()");
        }
        Intent intent = new Intent();
        if (!ListenerUtil.mutListener.listen(9323)) {
            intent.setClass(activity, activity.getClass());
        }
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(activity);
        if (!ListenerUtil.mutListener.listen(9324)) {
            stackBuilder.addNextIntentWithParentStack(intent);
        }
        if (!ListenerUtil.mutListener.listen(9325)) {
            stackBuilder.startActivities(new Bundle());
        }
        if (!ListenerUtil.mutListener.listen(9326)) {
            activity.finishWithoutAnimation();
        }
    }

    public void toggleDrawer() {
        if (!ListenerUtil.mutListener.listen(9329)) {
            if (!isDrawerOpen()) {
                if (!ListenerUtil.mutListener.listen(9328)) {
                    openDrawer();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9327)) {
                    closeDrawer();
                }
            }
        }
    }

    private void openDrawer() {
        if (!ListenerUtil.mutListener.listen(9330)) {
            mDrawerLayout.openDrawer(GravityCompat.START, animationEnabled());
        }
    }

    private void closeDrawer() {
        if (!ListenerUtil.mutListener.listen(9331)) {
            mDrawerLayout.closeDrawer(GravityCompat.START, animationEnabled());
        }
    }

    public void focusNavigation() {
        if (!ListenerUtil.mutListener.listen(9332)) {
            // mNavigationView.getMenu().getItem(0).setChecked(true);
            selectNavigationItem(R.id.nav_decks);
        }
        if (!ListenerUtil.mutListener.listen(9333)) {
            mNavigationView.requestFocus();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!ListenerUtil.mutListener.listen(9334)) {
            if (!isDrawerOpen()) {
                return super.onKeyDown(keyCode, event);
            }
        }
        if (!ListenerUtil.mutListener.listen(9336)) {
            if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                if (!ListenerUtil.mutListener.listen(9335)) {
                    closeDrawer();
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
