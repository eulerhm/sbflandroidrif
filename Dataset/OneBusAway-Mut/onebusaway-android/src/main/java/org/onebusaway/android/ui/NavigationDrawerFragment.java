/*
 * Copyright 2014-2017 Google Inc.,
 * University of South Florida (sjbarbeau@gmail.com),
 * Microsoft Corporation.
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
 *
 * Portions of code taken from the Google I/0 2014 (https://github.com/google/iosched)
 * and a generated NavigationDrawer app from Android Studio, modified for OneBusAway by USF
 */
package org.onebusaway.android.ui;

import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.elements.ObaRegion;
import org.onebusaway.android.util.UIUtils;
import org.onebusaway.android.view.ScrimInsetsScrollView;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Fragment used for managing interactions for and presentation of a navigation drawer.
 * See the <a href="https://developer.android.com/design/patterns/navigation-drawer.html#Interaction">
 * design guidelines</a> for a complete explanation of the behaviors implemented here.
 */
public class NavigationDrawerFragment extends Fragment {

    public static final String TAG = "NavDrawerFragment";

    /**
     * Remember the position of the selected item.
     */
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

    // it's a list of all possible items.
    protected static final int NAVDRAWER_ITEM_NEARBY = 0;

    protected static final int NAVDRAWER_ITEM_STARRED_STOPS = 1;

    protected static final int NAVDRAWER_ITEM_STARRED_ROUTES = 2;

    protected static final int NAVDRAWER_ITEM_MY_REMINDERS = 3;

    protected static final int NAVDRAWER_ITEM_SETTINGS = 4;

    protected static final int NAVDRAWER_ITEM_HELP = 5;

    protected static final int NAVDRAWER_ITEM_SEND_FEEDBACK = 6;

    protected static final int NAVDRAWER_ITEM_PLAN_TRIP = 7;

    @Deprecated
    protected static final int NAVDRAWER_ITEM_PINS = 8;

    @Deprecated
    protected static final int NAVDRAWER_ITEM_ACTIVITY_FEED = 9;

    @Deprecated
    protected static final int NAVDRAWER_ITEM_PROFILE = 10;

    @Deprecated
    protected static final int NAVDRAWER_ITEM_SIGN_IN = 11;

    protected static final int NAVDRAWER_ITEM_OPEN_SOURCE = 12;

    protected static final int NAVDRAWER_ITEM_PAY_FARE = 13;

    protected static final int NAVDRAWER_ITEM_INVALID = -1;

    protected static final int NAVDRAWER_ITEM_SEPARATOR = -2;

    // Currently selected navigation drawer item (must be value of one of the constants above)
    private int mCurrentSelectedPosition = NAVDRAWER_ITEM_NEARBY;

    // titles for navdrawer items (indices must correspond to the above)
    private static final int[] NAVDRAWER_TITLE_RES_ID = new int[] { R.string.navdrawer_item_nearby, R.string.navdrawer_item_starred_stops, R.string.navdrawer_item_starred_routes, R.string.navdrawer_item_my_reminders, R.string.navdrawer_item_settings, R.string.navdrawer_item_help, R.string.navdrawer_item_send_feedback, R.string.navdrawer_item_plan_trip, // Pinned discussions
    0, // Social activity feed
    0, // My profile
    0, // Sign in
    0, R.string.navdrawer_item_open_source, R.string.navdrawer_item_pay_fare };

    // icons for navdrawer items (indices must correspond to above array)
    private static final int[] NAVDRAWER_ICON_RES_ID = new int[] { // Nearby
    R.drawable.ic_drawer_maps_place, // Starred Stops
    R.drawable.ic_stop_flag_triangle, // Starred Routes
    R.drawable.ic_bus, // My reminders
    R.drawable.ic_drawer_alarm, // Settings
    0, // Help
    0, // Send feedback
    0, // Plan a trip
    R.drawable.ic_maps_directions, // Pinned discussions
    0, // Social activity feed
    0, // My profile
    0, // Sign in
    0, // Open-source
    R.drawable.ic_drawer_github, // Pay my fare
    R.drawable.ic_payment };

    // Secondary navdrawer item icons that appear align to right of list item layout
    private static final int[] NAVDRAWER_ICON_SECONDARY_RES_ID = new int[] { // Nearby
    0, // Starred Stops
    0, // Starred Routes
    0, // My reminders
    0, // Settings
    0, // Help
    0, // Send feedback
    0, // Plan a trip
    0, // Pinned discussions
    0, // Social activity feed
    0, // My profile
    0, // Sign in
    0, // Open-source
    R.drawable.ic_drawer_link, // Pay my fare
    R.drawable.ic_drawer_link };

    // list of navdrawer items that were actually added to the navdrawer, in order
    private ArrayList<Integer> mNavDrawerItems = new ArrayList<Integer>();

    // views that correspond to each navdrawer item, null if not yet created
    private View[] mNavDrawerItemViews = null;

    /**
     * A pointer to the current callbacks instance (the Activity).
     */
    private NavigationDrawerCallbacks mCallbacks;

    /**
     * Helper component that ties the action bar to the navigation drawer.
     */
    private ActionBarDrawerToggle mDrawerToggle;

    // Navigation drawer:
    private DrawerLayout mDrawerLayout;

    private View mDrawerItemsListContainer;

    private View mFragmentContainerView;

    private boolean isSignedIn;

    public NavigationDrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(739)) {
            super.onCreate(savedInstanceState);
        }
        // drawer. See PREF_USER_LEARNED_DRAWER for details.
        SharedPreferences sp = Application.getPrefs();
        if (!ListenerUtil.mutListener.listen(744)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(742)) {
                    mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
                }
                if (!ListenerUtil.mutListener.listen(743)) {
                    Log.d(TAG, "Using position from savedInstanceState = " + mCurrentSelectedPosition);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(740)) {
                    // Try to get the saved position from preferences
                    mCurrentSelectedPosition = sp.getInt(STATE_SELECTED_POSITION, NAVDRAWER_ITEM_NEARBY);
                }
                if (!ListenerUtil.mutListener.listen(741)) {
                    Log.d(TAG, "Using position from preferences = " + mCurrentSelectedPosition);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(745)) {
            // Select either the default item (0) or the last selected item.
            selectItem(mCurrentSelectedPosition);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(746)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(747)) {
            // Indicate that this fragment would like to influence the set of actions in the action bar.
            setHasOptionsMenu(true);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(748)) {
            mDrawerItemsListContainer = inflater.inflate(R.layout.navdrawer_list, container, false);
        }
        return mDrawerItemsListContainer;
    }

    /**
     * Users of this fragment must call this method to set up the navigation drawer interactions.
     *
     * @param fragmentId   The android:id of this fragment in its activity's layout.
     * @param drawerLayout The DrawerLayout containing this fragment's UI.
     */
    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        int selfItem = mCurrentSelectedPosition;
        if (!ListenerUtil.mutListener.listen(749)) {
            mFragmentContainerView = getActivity().findViewById(fragmentId);
        }
        if (!ListenerUtil.mutListener.listen(750)) {
            mDrawerLayout = drawerLayout;
        }
        if (!ListenerUtil.mutListener.listen(751)) {
            if (mDrawerLayout == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(752)) {
            // set a custom shadow that overlays the main content when the drawer opens
            mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        }
        ScrimInsetsScrollView navDrawer = (ScrimInsetsScrollView) mDrawerLayout.findViewById(R.id.navdrawer);
        if (!ListenerUtil.mutListener.listen(761)) {
            if ((ListenerUtil.mutListener.listen(757) ? (selfItem >= NAVDRAWER_ITEM_INVALID) : (ListenerUtil.mutListener.listen(756) ? (selfItem <= NAVDRAWER_ITEM_INVALID) : (ListenerUtil.mutListener.listen(755) ? (selfItem > NAVDRAWER_ITEM_INVALID) : (ListenerUtil.mutListener.listen(754) ? (selfItem < NAVDRAWER_ITEM_INVALID) : (ListenerUtil.mutListener.listen(753) ? (selfItem != NAVDRAWER_ITEM_INVALID) : (selfItem == NAVDRAWER_ITEM_INVALID))))))) {
                if (!ListenerUtil.mutListener.listen(759)) {
                    // do not show a nav drawer
                    if (navDrawer != null) {
                        if (!ListenerUtil.mutListener.listen(758)) {
                            ((ViewGroup) navDrawer.getParent()).removeView(navDrawer);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(760)) {
                    mDrawerLayout = null;
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(762)) {
            // populate the nav drawer with the correct items
            populateNavDrawer();
        }
        ActionBar actionBar = getActionBar();
        if (!ListenerUtil.mutListener.listen(763)) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(764)) {
            actionBar.setHomeButtonEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(771)) {
            // between the navigation drawer and the action bar app icon.
            mDrawerToggle = new androidx.appcompat.app.ActionBarDrawerToggle(getActivity(), /* host Activity */
            mDrawerLayout, /* DrawerLayout object */
            R.string.navigation_drawer_open, /* "open drawer" description for accessibility */
            R.string.navigation_drawer_close) {

                @Override
                public void onDrawerClosed(View drawerView) {
                    if (!ListenerUtil.mutListener.listen(765)) {
                        super.onDrawerClosed(drawerView);
                    }
                    if (!ListenerUtil.mutListener.listen(766)) {
                        if (!isAdded()) {
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(767)) {
                        // calls onPrepareOptionsMenu()
                        getActivity().supportInvalidateOptionsMenu();
                    }
                }

                @Override
                public void onDrawerOpened(View drawerView) {
                    if (!ListenerUtil.mutListener.listen(768)) {
                        super.onDrawerOpened(drawerView);
                    }
                    if (!ListenerUtil.mutListener.listen(769)) {
                        if (!isAdded()) {
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(770)) {
                        // calls onPrepareOptionsMenu()
                        getActivity().supportInvalidateOptionsMenu();
                    }
                }
            };
        }
        if (!ListenerUtil.mutListener.listen(773)) {
            // Defer code dependent on restoration of previous instance state.
            mDrawerLayout.post(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(772)) {
                        mDrawerToggle.syncState();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(774)) {
            mDrawerLayout.setDrawerListener(mDrawerToggle);
        }
    }

    /**
     * Sets the currently selected navigation drawer item, based on the provided position
     * parameter,
     * which must be one of the NAVDRAWER_ITEM_* contants in this class.
     *
     * @param position the item to select in the navigation drawer - must be one of the
     *                 NAVDRAWER_ITEM_* contants in this class
     */
    public void selectItem(int position) {
        if (!ListenerUtil.mutListener.listen(775)) {
            setSelectedNavDrawerItem(position);
        }
        if (!ListenerUtil.mutListener.listen(778)) {
            if ((ListenerUtil.mutListener.listen(776) ? (mDrawerLayout != null || mFragmentContainerView != null) : (mDrawerLayout != null && mFragmentContainerView != null))) {
                if (!ListenerUtil.mutListener.listen(777)) {
                    mDrawerLayout.closeDrawer(mFragmentContainerView);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(780)) {
            if (mCallbacks != null) {
                if (!ListenerUtil.mutListener.listen(779)) {
                    mCallbacks.onNavigationDrawerItemSelected(position);
                }
            }
        }
    }

    /**
     * Set the selected position as a preference
     */
    public void setSavedPosition(int position) {
        SharedPreferences sp = Application.getPrefs();
        if (!ListenerUtil.mutListener.listen(781)) {
            sp.edit().putInt(STATE_SELECTED_POSITION, position).apply();
        }
    }

    /**
     * Sets up the given navdrawer item's appearance to the selected state. Note: this could
     * also be accomplished (perhaps more cleanly) with state-based layouts.
     */
    private void setSelectedNavDrawerItem(int itemId) {
        if (!ListenerUtil.mutListener.listen(784)) {
            if (!isNewActivityItem(itemId)) {
                if (!ListenerUtil.mutListener.listen(782)) {
                    // We only change the selected item if it doesn't launch a new activity
                    mCurrentSelectedPosition = itemId;
                }
                if (!ListenerUtil.mutListener.listen(783)) {
                    setSavedPosition(mCurrentSelectedPosition);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(803)) {
            if (mNavDrawerItemViews != null) {
                if (!ListenerUtil.mutListener.listen(802)) {
                    {
                        long _loopCounter10 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(801) ? (i >= mNavDrawerItemViews.length) : (ListenerUtil.mutListener.listen(800) ? (i <= mNavDrawerItemViews.length) : (ListenerUtil.mutListener.listen(799) ? (i > mNavDrawerItemViews.length) : (ListenerUtil.mutListener.listen(798) ? (i != mNavDrawerItemViews.length) : (ListenerUtil.mutListener.listen(797) ? (i == mNavDrawerItemViews.length) : (i < mNavDrawerItemViews.length)))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter10", ++_loopCounter10);
                            if (!ListenerUtil.mutListener.listen(796)) {
                                if ((ListenerUtil.mutListener.listen(789) ? (i >= mNavDrawerItems.size()) : (ListenerUtil.mutListener.listen(788) ? (i <= mNavDrawerItems.size()) : (ListenerUtil.mutListener.listen(787) ? (i > mNavDrawerItems.size()) : (ListenerUtil.mutListener.listen(786) ? (i != mNavDrawerItems.size()) : (ListenerUtil.mutListener.listen(785) ? (i == mNavDrawerItems.size()) : (i < mNavDrawerItems.size()))))))) {
                                    int thisItemId = mNavDrawerItems.get(i);
                                    if (!ListenerUtil.mutListener.listen(795)) {
                                        formatNavDrawerItem(mNavDrawerItemViews[i], thisItemId, (ListenerUtil.mutListener.listen(794) ? (itemId >= thisItemId) : (ListenerUtil.mutListener.listen(793) ? (itemId <= thisItemId) : (ListenerUtil.mutListener.listen(792) ? (itemId > thisItemId) : (ListenerUtil.mutListener.listen(791) ? (itemId < thisItemId) : (ListenerUtil.mutListener.listen(790) ? (itemId != thisItemId) : (itemId == thisItemId)))))));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onAttach(final Context context) {
        if (!ListenerUtil.mutListener.listen(804)) {
            super.onAttach(context);
        }
        try {
            if (!ListenerUtil.mutListener.listen(805)) {
                mCallbacks = (NavigationDrawerCallbacks) context;
            }
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        if (!ListenerUtil.mutListener.listen(806)) {
            super.onDetach();
        }
        if (!ListenerUtil.mutListener.listen(807)) {
            mCallbacks = null;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(808)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(809)) {
            Log.d(TAG, "Saving position = " + mCurrentSelectedPosition);
        }
        if (!ListenerUtil.mutListener.listen(810)) {
            outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(811)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(813)) {
            if (isItemDeprecated(mCurrentSelectedPosition)) {
                if (!ListenerUtil.mutListener.listen(812)) {
                    // Prevent access of deprecated options
                    selectItem(NAVDRAWER_ITEM_NEARBY);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(814)) {
            populateNavDrawer();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (!ListenerUtil.mutListener.listen(815)) {
            super.onConfigurationChanged(newConfig);
        }
        if (!ListenerUtil.mutListener.listen(817)) {
            // Forward the new configuration the drawer toggle component.
            if (mDrawerToggle != null) {
                if (!ListenerUtil.mutListener.listen(816)) {
                    mDrawerToggle.onConfigurationChanged(newConfig);
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(819)) {
            if ((ListenerUtil.mutListener.listen(818) ? (mDrawerToggle != null || mDrawerToggle.onOptionsItemSelected(item)) : (mDrawerToggle != null && mDrawerToggle.onOptionsItemSelected(item)))) {
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }

    /**
     * Callbacks interface that all activities using this fragment must implement.
     */
    public interface NavigationDrawerCallbacks {

        /**
         * Called when an item in the navigation drawer is selected.
         */
        void onNavigationDrawerItemSelected(int position);
    }

    /**
     * Populates the navigation drawer with the appropriate items.
     */
    public void populateNavDrawer() {
        ObaRegion currentRegion = Application.get().getCurrentRegion();
        if (!ListenerUtil.mutListener.listen(820)) {
            mNavDrawerItems.clear();
        }
        if (!ListenerUtil.mutListener.listen(821)) {
            mNavDrawerItems.add(NAVDRAWER_ITEM_NEARBY);
        }
        if (!ListenerUtil.mutListener.listen(822)) {
            mNavDrawerItems.add(NAVDRAWER_ITEM_STARRED_STOPS);
        }
        if (!ListenerUtil.mutListener.listen(823)) {
            mNavDrawerItems.add(NAVDRAWER_ITEM_STARRED_ROUTES);
        }
        if (!ListenerUtil.mutListener.listen(824)) {
            mNavDrawerItems.add(NAVDRAWER_ITEM_MY_REMINDERS);
        }
        if (!ListenerUtil.mutListener.listen(830)) {
            if (currentRegion != null) {
                if (!ListenerUtil.mutListener.listen(827)) {
                    if ((ListenerUtil.mutListener.listen(825) ? (!TextUtils.isEmpty(currentRegion.getOtpBaseUrl()) && !TextUtils.isEmpty(Application.get().getCustomOtpApiUrl())) : (!TextUtils.isEmpty(currentRegion.getOtpBaseUrl()) || !TextUtils.isEmpty(Application.get().getCustomOtpApiUrl())))) {
                        if (!ListenerUtil.mutListener.listen(826)) {
                            mNavDrawerItems.add(NAVDRAWER_ITEM_PLAN_TRIP);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(829)) {
                    if (!TextUtils.isEmpty(currentRegion.getPaymentAndroidAppId())) {
                        if (!ListenerUtil.mutListener.listen(828)) {
                            mNavDrawerItems.add(NAVDRAWER_ITEM_PAY_FARE);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(831)) {
            mNavDrawerItems.add(NAVDRAWER_ITEM_SEPARATOR);
        }
        if (!ListenerUtil.mutListener.listen(832)) {
            mNavDrawerItems.add(NAVDRAWER_ITEM_OPEN_SOURCE);
        }
        if (!ListenerUtil.mutListener.listen(833)) {
            mNavDrawerItems.add(NAVDRAWER_ITEM_SEPARATOR);
        }
        if (!ListenerUtil.mutListener.listen(834)) {
            mNavDrawerItems.add(NAVDRAWER_ITEM_SETTINGS);
        }
        if (!ListenerUtil.mutListener.listen(835)) {
            mNavDrawerItems.add(NAVDRAWER_ITEM_HELP);
        }
        if (!ListenerUtil.mutListener.listen(836)) {
            mNavDrawerItems.add(NAVDRAWER_ITEM_SEND_FEEDBACK);
        }
        if (!ListenerUtil.mutListener.listen(837)) {
            createNavDrawerItems();
        }
    }

    private void createNavDrawerItems() {
        if (!ListenerUtil.mutListener.listen(839)) {
            if ((ListenerUtil.mutListener.listen(838) ? (mDrawerItemsListContainer == null && getActivity() == null) : (mDrawerItemsListContainer == null || getActivity() == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(840)) {
            mNavDrawerItemViews = new View[mNavDrawerItems.size()];
        }
        int i = 0;
        LinearLayout containerLayout = (LinearLayout) mDrawerItemsListContainer.findViewById(R.id.navdrawer_items_list);
        if (!ListenerUtil.mutListener.listen(841)) {
            containerLayout.removeAllViews();
        }
        if (!ListenerUtil.mutListener.listen(845)) {
            {
                long _loopCounter11 = 0;
                for (int itemId : mNavDrawerItems) {
                    ListenerUtil.loopListener.listen("_loopCounter11", ++_loopCounter11);
                    if (!ListenerUtil.mutListener.listen(842)) {
                        mNavDrawerItemViews[i] = makeNavDrawerItem(itemId, containerLayout);
                    }
                    if (!ListenerUtil.mutListener.listen(843)) {
                        containerLayout.addView(mNavDrawerItemViews[i]);
                    }
                    if (!ListenerUtil.mutListener.listen(844)) {
                        ++i;
                    }
                }
            }
        }
    }

    private View makeNavDrawerItem(final int itemId, ViewGroup container) {
        boolean selected = (ListenerUtil.mutListener.listen(850) ? (mCurrentSelectedPosition >= itemId) : (ListenerUtil.mutListener.listen(849) ? (mCurrentSelectedPosition <= itemId) : (ListenerUtil.mutListener.listen(848) ? (mCurrentSelectedPosition > itemId) : (ListenerUtil.mutListener.listen(847) ? (mCurrentSelectedPosition < itemId) : (ListenerUtil.mutListener.listen(846) ? (mCurrentSelectedPosition != itemId) : (mCurrentSelectedPosition == itemId))))));
        int layoutToInflate;
        if ((ListenerUtil.mutListener.listen(855) ? (itemId >= NAVDRAWER_ITEM_SEPARATOR) : (ListenerUtil.mutListener.listen(854) ? (itemId <= NAVDRAWER_ITEM_SEPARATOR) : (ListenerUtil.mutListener.listen(853) ? (itemId > NAVDRAWER_ITEM_SEPARATOR) : (ListenerUtil.mutListener.listen(852) ? (itemId < NAVDRAWER_ITEM_SEPARATOR) : (ListenerUtil.mutListener.listen(851) ? (itemId != NAVDRAWER_ITEM_SEPARATOR) : (itemId == NAVDRAWER_ITEM_SEPARATOR))))))) {
            layoutToInflate = R.layout.navdrawer_separator;
        } else {
            layoutToInflate = R.layout.navdrawer_item;
        }
        View view = getActivity().getLayoutInflater().inflate(layoutToInflate, container, false);
        if (!ListenerUtil.mutListener.listen(857)) {
            if (isSeparator(itemId)) {
                if (!ListenerUtil.mutListener.listen(856)) {
                    // we are done
                    UIUtils.setAccessibilityIgnore(view);
                }
                return view;
            }
        }
        ImageView iconView = (ImageView) view.findViewById(R.id.icon);
        TextView titleView = (TextView) view.findViewById(R.id.title);
        ImageView secondaryIconView = view.findViewById(R.id.secondary_icon);
        int iconId = (ListenerUtil.mutListener.listen(868) ? ((ListenerUtil.mutListener.listen(862) ? (itemId <= 0) : (ListenerUtil.mutListener.listen(861) ? (itemId > 0) : (ListenerUtil.mutListener.listen(860) ? (itemId < 0) : (ListenerUtil.mutListener.listen(859) ? (itemId != 0) : (ListenerUtil.mutListener.listen(858) ? (itemId == 0) : (itemId >= 0)))))) || (ListenerUtil.mutListener.listen(867) ? (itemId >= NAVDRAWER_ICON_RES_ID.length) : (ListenerUtil.mutListener.listen(866) ? (itemId <= NAVDRAWER_ICON_RES_ID.length) : (ListenerUtil.mutListener.listen(865) ? (itemId > NAVDRAWER_ICON_RES_ID.length) : (ListenerUtil.mutListener.listen(864) ? (itemId != NAVDRAWER_ICON_RES_ID.length) : (ListenerUtil.mutListener.listen(863) ? (itemId == NAVDRAWER_ICON_RES_ID.length) : (itemId < NAVDRAWER_ICON_RES_ID.length))))))) : ((ListenerUtil.mutListener.listen(862) ? (itemId <= 0) : (ListenerUtil.mutListener.listen(861) ? (itemId > 0) : (ListenerUtil.mutListener.listen(860) ? (itemId < 0) : (ListenerUtil.mutListener.listen(859) ? (itemId != 0) : (ListenerUtil.mutListener.listen(858) ? (itemId == 0) : (itemId >= 0)))))) && (ListenerUtil.mutListener.listen(867) ? (itemId >= NAVDRAWER_ICON_RES_ID.length) : (ListenerUtil.mutListener.listen(866) ? (itemId <= NAVDRAWER_ICON_RES_ID.length) : (ListenerUtil.mutListener.listen(865) ? (itemId > NAVDRAWER_ICON_RES_ID.length) : (ListenerUtil.mutListener.listen(864) ? (itemId != NAVDRAWER_ICON_RES_ID.length) : (ListenerUtil.mutListener.listen(863) ? (itemId == NAVDRAWER_ICON_RES_ID.length) : (itemId < NAVDRAWER_ICON_RES_ID.length)))))))) ? NAVDRAWER_ICON_RES_ID[itemId] : 0;
        int titleId = (ListenerUtil.mutListener.listen(879) ? ((ListenerUtil.mutListener.listen(873) ? (itemId <= 0) : (ListenerUtil.mutListener.listen(872) ? (itemId > 0) : (ListenerUtil.mutListener.listen(871) ? (itemId < 0) : (ListenerUtil.mutListener.listen(870) ? (itemId != 0) : (ListenerUtil.mutListener.listen(869) ? (itemId == 0) : (itemId >= 0)))))) || (ListenerUtil.mutListener.listen(878) ? (itemId >= NAVDRAWER_TITLE_RES_ID.length) : (ListenerUtil.mutListener.listen(877) ? (itemId <= NAVDRAWER_TITLE_RES_ID.length) : (ListenerUtil.mutListener.listen(876) ? (itemId > NAVDRAWER_TITLE_RES_ID.length) : (ListenerUtil.mutListener.listen(875) ? (itemId != NAVDRAWER_TITLE_RES_ID.length) : (ListenerUtil.mutListener.listen(874) ? (itemId == NAVDRAWER_TITLE_RES_ID.length) : (itemId < NAVDRAWER_TITLE_RES_ID.length))))))) : ((ListenerUtil.mutListener.listen(873) ? (itemId <= 0) : (ListenerUtil.mutListener.listen(872) ? (itemId > 0) : (ListenerUtil.mutListener.listen(871) ? (itemId < 0) : (ListenerUtil.mutListener.listen(870) ? (itemId != 0) : (ListenerUtil.mutListener.listen(869) ? (itemId == 0) : (itemId >= 0)))))) && (ListenerUtil.mutListener.listen(878) ? (itemId >= NAVDRAWER_TITLE_RES_ID.length) : (ListenerUtil.mutListener.listen(877) ? (itemId <= NAVDRAWER_TITLE_RES_ID.length) : (ListenerUtil.mutListener.listen(876) ? (itemId > NAVDRAWER_TITLE_RES_ID.length) : (ListenerUtil.mutListener.listen(875) ? (itemId != NAVDRAWER_TITLE_RES_ID.length) : (ListenerUtil.mutListener.listen(874) ? (itemId == NAVDRAWER_TITLE_RES_ID.length) : (itemId < NAVDRAWER_TITLE_RES_ID.length)))))))) ? NAVDRAWER_TITLE_RES_ID[itemId] : 0;
        int secondaryIconId = (ListenerUtil.mutListener.listen(890) ? ((ListenerUtil.mutListener.listen(884) ? (itemId <= 0) : (ListenerUtil.mutListener.listen(883) ? (itemId > 0) : (ListenerUtil.mutListener.listen(882) ? (itemId < 0) : (ListenerUtil.mutListener.listen(881) ? (itemId != 0) : (ListenerUtil.mutListener.listen(880) ? (itemId == 0) : (itemId >= 0)))))) || (ListenerUtil.mutListener.listen(889) ? (itemId >= NAVDRAWER_ICON_SECONDARY_RES_ID.length) : (ListenerUtil.mutListener.listen(888) ? (itemId <= NAVDRAWER_ICON_SECONDARY_RES_ID.length) : (ListenerUtil.mutListener.listen(887) ? (itemId > NAVDRAWER_ICON_SECONDARY_RES_ID.length) : (ListenerUtil.mutListener.listen(886) ? (itemId != NAVDRAWER_ICON_SECONDARY_RES_ID.length) : (ListenerUtil.mutListener.listen(885) ? (itemId == NAVDRAWER_ICON_SECONDARY_RES_ID.length) : (itemId < NAVDRAWER_ICON_SECONDARY_RES_ID.length))))))) : ((ListenerUtil.mutListener.listen(884) ? (itemId <= 0) : (ListenerUtil.mutListener.listen(883) ? (itemId > 0) : (ListenerUtil.mutListener.listen(882) ? (itemId < 0) : (ListenerUtil.mutListener.listen(881) ? (itemId != 0) : (ListenerUtil.mutListener.listen(880) ? (itemId == 0) : (itemId >= 0)))))) && (ListenerUtil.mutListener.listen(889) ? (itemId >= NAVDRAWER_ICON_SECONDARY_RES_ID.length) : (ListenerUtil.mutListener.listen(888) ? (itemId <= NAVDRAWER_ICON_SECONDARY_RES_ID.length) : (ListenerUtil.mutListener.listen(887) ? (itemId > NAVDRAWER_ICON_SECONDARY_RES_ID.length) : (ListenerUtil.mutListener.listen(886) ? (itemId != NAVDRAWER_ICON_SECONDARY_RES_ID.length) : (ListenerUtil.mutListener.listen(885) ? (itemId == NAVDRAWER_ICON_SECONDARY_RES_ID.length) : (itemId < NAVDRAWER_ICON_SECONDARY_RES_ID.length)))))))) ? NAVDRAWER_ICON_SECONDARY_RES_ID[itemId] : 0;
        if (!ListenerUtil.mutListener.listen(896)) {
            // set icon and text
            iconView.setVisibility((ListenerUtil.mutListener.listen(895) ? (iconId >= 0) : (ListenerUtil.mutListener.listen(894) ? (iconId <= 0) : (ListenerUtil.mutListener.listen(893) ? (iconId < 0) : (ListenerUtil.mutListener.listen(892) ? (iconId != 0) : (ListenerUtil.mutListener.listen(891) ? (iconId == 0) : (iconId > 0)))))) ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(903)) {
            if ((ListenerUtil.mutListener.listen(901) ? (iconId >= 0) : (ListenerUtil.mutListener.listen(900) ? (iconId <= 0) : (ListenerUtil.mutListener.listen(899) ? (iconId < 0) : (ListenerUtil.mutListener.listen(898) ? (iconId != 0) : (ListenerUtil.mutListener.listen(897) ? (iconId == 0) : (iconId > 0))))))) {
                if (!ListenerUtil.mutListener.listen(902)) {
                    iconView.setImageResource(iconId);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(904)) {
            titleView.setText(getString(titleId));
        }
        if (!ListenerUtil.mutListener.listen(910)) {
            // Secondary icon
            secondaryIconView.setVisibility((ListenerUtil.mutListener.listen(909) ? (secondaryIconId >= 0) : (ListenerUtil.mutListener.listen(908) ? (secondaryIconId <= 0) : (ListenerUtil.mutListener.listen(907) ? (secondaryIconId < 0) : (ListenerUtil.mutListener.listen(906) ? (secondaryIconId != 0) : (ListenerUtil.mutListener.listen(905) ? (secondaryIconId == 0) : (secondaryIconId > 0)))))) ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(917)) {
            if ((ListenerUtil.mutListener.listen(915) ? (secondaryIconId >= 0) : (ListenerUtil.mutListener.listen(914) ? (secondaryIconId <= 0) : (ListenerUtil.mutListener.listen(913) ? (secondaryIconId < 0) : (ListenerUtil.mutListener.listen(912) ? (secondaryIconId != 0) : (ListenerUtil.mutListener.listen(911) ? (secondaryIconId == 0) : (secondaryIconId > 0))))))) {
                if (!ListenerUtil.mutListener.listen(916)) {
                    secondaryIconView.setImageResource(secondaryIconId);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(918)) {
            formatNavDrawerItem(view, itemId, selected);
        }
        if (!ListenerUtil.mutListener.listen(920)) {
            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(919)) {
                        selectItem(itemId);
                    }
                }
            });
        }
        return view;
    }

    private void formatNavDrawerItem(View view, int itemId, boolean selected) {
        if (!ListenerUtil.mutListener.listen(921)) {
            if (isSeparator(itemId)) {
                // Don't do any formatting
                return;
            }
        }
        ImageView iconView = (ImageView) view.findViewById(R.id.icon);
        TextView titleView = (TextView) view.findViewById(R.id.title);
        ImageView secondaryIconView = (ImageView) view.findViewById(R.id.secondary_icon);
        if (!ListenerUtil.mutListener.listen(937)) {
            /**
             * Configure its appearance according to whether or not it's selected.  Certain items
             * (e.g., Settings) don't get formatted upon selection, since they open a new activity.
             */
            if (selected) {
                if (!ListenerUtil.mutListener.listen(936)) {
                    if (isNewActivityItem(itemId)) {
                        // Don't change any formatting, since this is a category that launches a new activity
                        return;
                    } else {
                        if (!ListenerUtil.mutListener.listen(932)) {
                            // Show the category as highlighted by changing background, text, and icon color
                            view.setSelected(true);
                        }
                        if (!ListenerUtil.mutListener.listen(933)) {
                            titleView.setTextColor(getResources().getColor(R.color.navdrawer_text_color_selected));
                        }
                        if (!ListenerUtil.mutListener.listen(934)) {
                            iconView.setColorFilter(getResources().getColor(R.color.navdrawer_icon_tint_selected));
                        }
                        if (!ListenerUtil.mutListener.listen(935)) {
                            secondaryIconView.setColorFilter(getResources().getColor(R.color.navdrawer_icon_tint_selected));
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(931)) {
                    // Show the category as not highlighted, if its not currently selected
                    if ((ListenerUtil.mutListener.listen(926) ? (itemId >= mCurrentSelectedPosition) : (ListenerUtil.mutListener.listen(925) ? (itemId <= mCurrentSelectedPosition) : (ListenerUtil.mutListener.listen(924) ? (itemId > mCurrentSelectedPosition) : (ListenerUtil.mutListener.listen(923) ? (itemId < mCurrentSelectedPosition) : (ListenerUtil.mutListener.listen(922) ? (itemId == mCurrentSelectedPosition) : (itemId != mCurrentSelectedPosition))))))) {
                        if (!ListenerUtil.mutListener.listen(927)) {
                            view.setSelected(false);
                        }
                        if (!ListenerUtil.mutListener.listen(928)) {
                            titleView.setTextColor(getResources().getColor(R.color.navdrawer_text_color));
                        }
                        if (!ListenerUtil.mutListener.listen(929)) {
                            iconView.setColorFilter(getResources().getColor(R.color.navdrawer_icon_tint));
                        }
                        if (!ListenerUtil.mutListener.listen(930)) {
                            secondaryIconView.setColorFilter(getResources().getColor(R.color.navdrawer_icon_tint));
                        }
                    }
                }
            }
        }
    }

    private boolean isSeparator(int itemId) {
        return (ListenerUtil.mutListener.listen(942) ? (itemId >= NAVDRAWER_ITEM_SEPARATOR) : (ListenerUtil.mutListener.listen(941) ? (itemId <= NAVDRAWER_ITEM_SEPARATOR) : (ListenerUtil.mutListener.listen(940) ? (itemId > NAVDRAWER_ITEM_SEPARATOR) : (ListenerUtil.mutListener.listen(939) ? (itemId < NAVDRAWER_ITEM_SEPARATOR) : (ListenerUtil.mutListener.listen(938) ? (itemId != NAVDRAWER_ITEM_SEPARATOR) : (itemId == NAVDRAWER_ITEM_SEPARATOR))))));
    }

    /**
     * Returns true if this is an item that should not allow selection (e.g., Settings),
     * because they launch a new Activity and aren't part of this screen, false if its selectable
     * and changes the current UI via a new fragment
     *
     * @return true if this is an item that should not allow selection (e.g., Settings),
     * because they launch a new Activity and aren't part of this screen, false if its selectable
     * and changes the current UI via a new fragment
     */
    private boolean isNewActivityItem(int itemId) {
        return (ListenerUtil.mutListener.listen(977) ? ((ListenerUtil.mutListener.listen(971) ? ((ListenerUtil.mutListener.listen(965) ? ((ListenerUtil.mutListener.listen(959) ? ((ListenerUtil.mutListener.listen(953) ? ((ListenerUtil.mutListener.listen(947) ? (itemId >= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(946) ? (itemId <= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(945) ? (itemId > NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(944) ? (itemId < NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(943) ? (itemId != NAVDRAWER_ITEM_SETTINGS) : (itemId == NAVDRAWER_ITEM_SETTINGS)))))) && (ListenerUtil.mutListener.listen(952) ? (itemId >= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(951) ? (itemId <= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(950) ? (itemId > NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(949) ? (itemId < NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(948) ? (itemId != NAVDRAWER_ITEM_HELP) : (itemId == NAVDRAWER_ITEM_HELP))))))) : ((ListenerUtil.mutListener.listen(947) ? (itemId >= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(946) ? (itemId <= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(945) ? (itemId > NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(944) ? (itemId < NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(943) ? (itemId != NAVDRAWER_ITEM_SETTINGS) : (itemId == NAVDRAWER_ITEM_SETTINGS)))))) || (ListenerUtil.mutListener.listen(952) ? (itemId >= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(951) ? (itemId <= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(950) ? (itemId > NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(949) ? (itemId < NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(948) ? (itemId != NAVDRAWER_ITEM_HELP) : (itemId == NAVDRAWER_ITEM_HELP)))))))) && (ListenerUtil.mutListener.listen(958) ? (itemId >= NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(957) ? (itemId <= NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(956) ? (itemId > NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(955) ? (itemId < NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(954) ? (itemId != NAVDRAWER_ITEM_SEND_FEEDBACK) : (itemId == NAVDRAWER_ITEM_SEND_FEEDBACK))))))) : ((ListenerUtil.mutListener.listen(953) ? ((ListenerUtil.mutListener.listen(947) ? (itemId >= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(946) ? (itemId <= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(945) ? (itemId > NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(944) ? (itemId < NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(943) ? (itemId != NAVDRAWER_ITEM_SETTINGS) : (itemId == NAVDRAWER_ITEM_SETTINGS)))))) && (ListenerUtil.mutListener.listen(952) ? (itemId >= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(951) ? (itemId <= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(950) ? (itemId > NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(949) ? (itemId < NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(948) ? (itemId != NAVDRAWER_ITEM_HELP) : (itemId == NAVDRAWER_ITEM_HELP))))))) : ((ListenerUtil.mutListener.listen(947) ? (itemId >= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(946) ? (itemId <= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(945) ? (itemId > NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(944) ? (itemId < NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(943) ? (itemId != NAVDRAWER_ITEM_SETTINGS) : (itemId == NAVDRAWER_ITEM_SETTINGS)))))) || (ListenerUtil.mutListener.listen(952) ? (itemId >= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(951) ? (itemId <= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(950) ? (itemId > NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(949) ? (itemId < NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(948) ? (itemId != NAVDRAWER_ITEM_HELP) : (itemId == NAVDRAWER_ITEM_HELP)))))))) || (ListenerUtil.mutListener.listen(958) ? (itemId >= NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(957) ? (itemId <= NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(956) ? (itemId > NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(955) ? (itemId < NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(954) ? (itemId != NAVDRAWER_ITEM_SEND_FEEDBACK) : (itemId == NAVDRAWER_ITEM_SEND_FEEDBACK)))))))) && (ListenerUtil.mutListener.listen(964) ? (itemId >= NAVDRAWER_ITEM_PLAN_TRIP) : (ListenerUtil.mutListener.listen(963) ? (itemId <= NAVDRAWER_ITEM_PLAN_TRIP) : (ListenerUtil.mutListener.listen(962) ? (itemId > NAVDRAWER_ITEM_PLAN_TRIP) : (ListenerUtil.mutListener.listen(961) ? (itemId < NAVDRAWER_ITEM_PLAN_TRIP) : (ListenerUtil.mutListener.listen(960) ? (itemId != NAVDRAWER_ITEM_PLAN_TRIP) : (itemId == NAVDRAWER_ITEM_PLAN_TRIP))))))) : ((ListenerUtil.mutListener.listen(959) ? ((ListenerUtil.mutListener.listen(953) ? ((ListenerUtil.mutListener.listen(947) ? (itemId >= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(946) ? (itemId <= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(945) ? (itemId > NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(944) ? (itemId < NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(943) ? (itemId != NAVDRAWER_ITEM_SETTINGS) : (itemId == NAVDRAWER_ITEM_SETTINGS)))))) && (ListenerUtil.mutListener.listen(952) ? (itemId >= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(951) ? (itemId <= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(950) ? (itemId > NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(949) ? (itemId < NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(948) ? (itemId != NAVDRAWER_ITEM_HELP) : (itemId == NAVDRAWER_ITEM_HELP))))))) : ((ListenerUtil.mutListener.listen(947) ? (itemId >= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(946) ? (itemId <= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(945) ? (itemId > NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(944) ? (itemId < NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(943) ? (itemId != NAVDRAWER_ITEM_SETTINGS) : (itemId == NAVDRAWER_ITEM_SETTINGS)))))) || (ListenerUtil.mutListener.listen(952) ? (itemId >= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(951) ? (itemId <= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(950) ? (itemId > NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(949) ? (itemId < NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(948) ? (itemId != NAVDRAWER_ITEM_HELP) : (itemId == NAVDRAWER_ITEM_HELP)))))))) && (ListenerUtil.mutListener.listen(958) ? (itemId >= NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(957) ? (itemId <= NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(956) ? (itemId > NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(955) ? (itemId < NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(954) ? (itemId != NAVDRAWER_ITEM_SEND_FEEDBACK) : (itemId == NAVDRAWER_ITEM_SEND_FEEDBACK))))))) : ((ListenerUtil.mutListener.listen(953) ? ((ListenerUtil.mutListener.listen(947) ? (itemId >= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(946) ? (itemId <= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(945) ? (itemId > NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(944) ? (itemId < NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(943) ? (itemId != NAVDRAWER_ITEM_SETTINGS) : (itemId == NAVDRAWER_ITEM_SETTINGS)))))) && (ListenerUtil.mutListener.listen(952) ? (itemId >= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(951) ? (itemId <= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(950) ? (itemId > NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(949) ? (itemId < NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(948) ? (itemId != NAVDRAWER_ITEM_HELP) : (itemId == NAVDRAWER_ITEM_HELP))))))) : ((ListenerUtil.mutListener.listen(947) ? (itemId >= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(946) ? (itemId <= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(945) ? (itemId > NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(944) ? (itemId < NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(943) ? (itemId != NAVDRAWER_ITEM_SETTINGS) : (itemId == NAVDRAWER_ITEM_SETTINGS)))))) || (ListenerUtil.mutListener.listen(952) ? (itemId >= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(951) ? (itemId <= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(950) ? (itemId > NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(949) ? (itemId < NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(948) ? (itemId != NAVDRAWER_ITEM_HELP) : (itemId == NAVDRAWER_ITEM_HELP)))))))) || (ListenerUtil.mutListener.listen(958) ? (itemId >= NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(957) ? (itemId <= NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(956) ? (itemId > NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(955) ? (itemId < NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(954) ? (itemId != NAVDRAWER_ITEM_SEND_FEEDBACK) : (itemId == NAVDRAWER_ITEM_SEND_FEEDBACK)))))))) || (ListenerUtil.mutListener.listen(964) ? (itemId >= NAVDRAWER_ITEM_PLAN_TRIP) : (ListenerUtil.mutListener.listen(963) ? (itemId <= NAVDRAWER_ITEM_PLAN_TRIP) : (ListenerUtil.mutListener.listen(962) ? (itemId > NAVDRAWER_ITEM_PLAN_TRIP) : (ListenerUtil.mutListener.listen(961) ? (itemId < NAVDRAWER_ITEM_PLAN_TRIP) : (ListenerUtil.mutListener.listen(960) ? (itemId != NAVDRAWER_ITEM_PLAN_TRIP) : (itemId == NAVDRAWER_ITEM_PLAN_TRIP)))))))) && (ListenerUtil.mutListener.listen(970) ? (itemId >= NAVDRAWER_ITEM_PAY_FARE) : (ListenerUtil.mutListener.listen(969) ? (itemId <= NAVDRAWER_ITEM_PAY_FARE) : (ListenerUtil.mutListener.listen(968) ? (itemId > NAVDRAWER_ITEM_PAY_FARE) : (ListenerUtil.mutListener.listen(967) ? (itemId < NAVDRAWER_ITEM_PAY_FARE) : (ListenerUtil.mutListener.listen(966) ? (itemId != NAVDRAWER_ITEM_PAY_FARE) : (itemId == NAVDRAWER_ITEM_PAY_FARE))))))) : ((ListenerUtil.mutListener.listen(965) ? ((ListenerUtil.mutListener.listen(959) ? ((ListenerUtil.mutListener.listen(953) ? ((ListenerUtil.mutListener.listen(947) ? (itemId >= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(946) ? (itemId <= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(945) ? (itemId > NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(944) ? (itemId < NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(943) ? (itemId != NAVDRAWER_ITEM_SETTINGS) : (itemId == NAVDRAWER_ITEM_SETTINGS)))))) && (ListenerUtil.mutListener.listen(952) ? (itemId >= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(951) ? (itemId <= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(950) ? (itemId > NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(949) ? (itemId < NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(948) ? (itemId != NAVDRAWER_ITEM_HELP) : (itemId == NAVDRAWER_ITEM_HELP))))))) : ((ListenerUtil.mutListener.listen(947) ? (itemId >= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(946) ? (itemId <= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(945) ? (itemId > NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(944) ? (itemId < NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(943) ? (itemId != NAVDRAWER_ITEM_SETTINGS) : (itemId == NAVDRAWER_ITEM_SETTINGS)))))) || (ListenerUtil.mutListener.listen(952) ? (itemId >= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(951) ? (itemId <= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(950) ? (itemId > NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(949) ? (itemId < NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(948) ? (itemId != NAVDRAWER_ITEM_HELP) : (itemId == NAVDRAWER_ITEM_HELP)))))))) && (ListenerUtil.mutListener.listen(958) ? (itemId >= NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(957) ? (itemId <= NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(956) ? (itemId > NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(955) ? (itemId < NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(954) ? (itemId != NAVDRAWER_ITEM_SEND_FEEDBACK) : (itemId == NAVDRAWER_ITEM_SEND_FEEDBACK))))))) : ((ListenerUtil.mutListener.listen(953) ? ((ListenerUtil.mutListener.listen(947) ? (itemId >= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(946) ? (itemId <= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(945) ? (itemId > NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(944) ? (itemId < NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(943) ? (itemId != NAVDRAWER_ITEM_SETTINGS) : (itemId == NAVDRAWER_ITEM_SETTINGS)))))) && (ListenerUtil.mutListener.listen(952) ? (itemId >= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(951) ? (itemId <= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(950) ? (itemId > NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(949) ? (itemId < NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(948) ? (itemId != NAVDRAWER_ITEM_HELP) : (itemId == NAVDRAWER_ITEM_HELP))))))) : ((ListenerUtil.mutListener.listen(947) ? (itemId >= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(946) ? (itemId <= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(945) ? (itemId > NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(944) ? (itemId < NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(943) ? (itemId != NAVDRAWER_ITEM_SETTINGS) : (itemId == NAVDRAWER_ITEM_SETTINGS)))))) || (ListenerUtil.mutListener.listen(952) ? (itemId >= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(951) ? (itemId <= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(950) ? (itemId > NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(949) ? (itemId < NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(948) ? (itemId != NAVDRAWER_ITEM_HELP) : (itemId == NAVDRAWER_ITEM_HELP)))))))) || (ListenerUtil.mutListener.listen(958) ? (itemId >= NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(957) ? (itemId <= NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(956) ? (itemId > NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(955) ? (itemId < NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(954) ? (itemId != NAVDRAWER_ITEM_SEND_FEEDBACK) : (itemId == NAVDRAWER_ITEM_SEND_FEEDBACK)))))))) && (ListenerUtil.mutListener.listen(964) ? (itemId >= NAVDRAWER_ITEM_PLAN_TRIP) : (ListenerUtil.mutListener.listen(963) ? (itemId <= NAVDRAWER_ITEM_PLAN_TRIP) : (ListenerUtil.mutListener.listen(962) ? (itemId > NAVDRAWER_ITEM_PLAN_TRIP) : (ListenerUtil.mutListener.listen(961) ? (itemId < NAVDRAWER_ITEM_PLAN_TRIP) : (ListenerUtil.mutListener.listen(960) ? (itemId != NAVDRAWER_ITEM_PLAN_TRIP) : (itemId == NAVDRAWER_ITEM_PLAN_TRIP))))))) : ((ListenerUtil.mutListener.listen(959) ? ((ListenerUtil.mutListener.listen(953) ? ((ListenerUtil.mutListener.listen(947) ? (itemId >= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(946) ? (itemId <= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(945) ? (itemId > NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(944) ? (itemId < NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(943) ? (itemId != NAVDRAWER_ITEM_SETTINGS) : (itemId == NAVDRAWER_ITEM_SETTINGS)))))) && (ListenerUtil.mutListener.listen(952) ? (itemId >= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(951) ? (itemId <= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(950) ? (itemId > NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(949) ? (itemId < NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(948) ? (itemId != NAVDRAWER_ITEM_HELP) : (itemId == NAVDRAWER_ITEM_HELP))))))) : ((ListenerUtil.mutListener.listen(947) ? (itemId >= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(946) ? (itemId <= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(945) ? (itemId > NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(944) ? (itemId < NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(943) ? (itemId != NAVDRAWER_ITEM_SETTINGS) : (itemId == NAVDRAWER_ITEM_SETTINGS)))))) || (ListenerUtil.mutListener.listen(952) ? (itemId >= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(951) ? (itemId <= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(950) ? (itemId > NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(949) ? (itemId < NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(948) ? (itemId != NAVDRAWER_ITEM_HELP) : (itemId == NAVDRAWER_ITEM_HELP)))))))) && (ListenerUtil.mutListener.listen(958) ? (itemId >= NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(957) ? (itemId <= NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(956) ? (itemId > NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(955) ? (itemId < NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(954) ? (itemId != NAVDRAWER_ITEM_SEND_FEEDBACK) : (itemId == NAVDRAWER_ITEM_SEND_FEEDBACK))))))) : ((ListenerUtil.mutListener.listen(953) ? ((ListenerUtil.mutListener.listen(947) ? (itemId >= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(946) ? (itemId <= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(945) ? (itemId > NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(944) ? (itemId < NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(943) ? (itemId != NAVDRAWER_ITEM_SETTINGS) : (itemId == NAVDRAWER_ITEM_SETTINGS)))))) && (ListenerUtil.mutListener.listen(952) ? (itemId >= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(951) ? (itemId <= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(950) ? (itemId > NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(949) ? (itemId < NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(948) ? (itemId != NAVDRAWER_ITEM_HELP) : (itemId == NAVDRAWER_ITEM_HELP))))))) : ((ListenerUtil.mutListener.listen(947) ? (itemId >= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(946) ? (itemId <= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(945) ? (itemId > NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(944) ? (itemId < NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(943) ? (itemId != NAVDRAWER_ITEM_SETTINGS) : (itemId == NAVDRAWER_ITEM_SETTINGS)))))) || (ListenerUtil.mutListener.listen(952) ? (itemId >= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(951) ? (itemId <= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(950) ? (itemId > NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(949) ? (itemId < NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(948) ? (itemId != NAVDRAWER_ITEM_HELP) : (itemId == NAVDRAWER_ITEM_HELP)))))))) || (ListenerUtil.mutListener.listen(958) ? (itemId >= NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(957) ? (itemId <= NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(956) ? (itemId > NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(955) ? (itemId < NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(954) ? (itemId != NAVDRAWER_ITEM_SEND_FEEDBACK) : (itemId == NAVDRAWER_ITEM_SEND_FEEDBACK)))))))) || (ListenerUtil.mutListener.listen(964) ? (itemId >= NAVDRAWER_ITEM_PLAN_TRIP) : (ListenerUtil.mutListener.listen(963) ? (itemId <= NAVDRAWER_ITEM_PLAN_TRIP) : (ListenerUtil.mutListener.listen(962) ? (itemId > NAVDRAWER_ITEM_PLAN_TRIP) : (ListenerUtil.mutListener.listen(961) ? (itemId < NAVDRAWER_ITEM_PLAN_TRIP) : (ListenerUtil.mutListener.listen(960) ? (itemId != NAVDRAWER_ITEM_PLAN_TRIP) : (itemId == NAVDRAWER_ITEM_PLAN_TRIP)))))))) || (ListenerUtil.mutListener.listen(970) ? (itemId >= NAVDRAWER_ITEM_PAY_FARE) : (ListenerUtil.mutListener.listen(969) ? (itemId <= NAVDRAWER_ITEM_PAY_FARE) : (ListenerUtil.mutListener.listen(968) ? (itemId > NAVDRAWER_ITEM_PAY_FARE) : (ListenerUtil.mutListener.listen(967) ? (itemId < NAVDRAWER_ITEM_PAY_FARE) : (ListenerUtil.mutListener.listen(966) ? (itemId != NAVDRAWER_ITEM_PAY_FARE) : (itemId == NAVDRAWER_ITEM_PAY_FARE)))))))) && (ListenerUtil.mutListener.listen(976) ? (itemId >= NAVDRAWER_ITEM_OPEN_SOURCE) : (ListenerUtil.mutListener.listen(975) ? (itemId <= NAVDRAWER_ITEM_OPEN_SOURCE) : (ListenerUtil.mutListener.listen(974) ? (itemId > NAVDRAWER_ITEM_OPEN_SOURCE) : (ListenerUtil.mutListener.listen(973) ? (itemId < NAVDRAWER_ITEM_OPEN_SOURCE) : (ListenerUtil.mutListener.listen(972) ? (itemId != NAVDRAWER_ITEM_OPEN_SOURCE) : (itemId == NAVDRAWER_ITEM_OPEN_SOURCE))))))) : ((ListenerUtil.mutListener.listen(971) ? ((ListenerUtil.mutListener.listen(965) ? ((ListenerUtil.mutListener.listen(959) ? ((ListenerUtil.mutListener.listen(953) ? ((ListenerUtil.mutListener.listen(947) ? (itemId >= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(946) ? (itemId <= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(945) ? (itemId > NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(944) ? (itemId < NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(943) ? (itemId != NAVDRAWER_ITEM_SETTINGS) : (itemId == NAVDRAWER_ITEM_SETTINGS)))))) && (ListenerUtil.mutListener.listen(952) ? (itemId >= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(951) ? (itemId <= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(950) ? (itemId > NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(949) ? (itemId < NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(948) ? (itemId != NAVDRAWER_ITEM_HELP) : (itemId == NAVDRAWER_ITEM_HELP))))))) : ((ListenerUtil.mutListener.listen(947) ? (itemId >= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(946) ? (itemId <= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(945) ? (itemId > NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(944) ? (itemId < NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(943) ? (itemId != NAVDRAWER_ITEM_SETTINGS) : (itemId == NAVDRAWER_ITEM_SETTINGS)))))) || (ListenerUtil.mutListener.listen(952) ? (itemId >= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(951) ? (itemId <= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(950) ? (itemId > NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(949) ? (itemId < NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(948) ? (itemId != NAVDRAWER_ITEM_HELP) : (itemId == NAVDRAWER_ITEM_HELP)))))))) && (ListenerUtil.mutListener.listen(958) ? (itemId >= NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(957) ? (itemId <= NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(956) ? (itemId > NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(955) ? (itemId < NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(954) ? (itemId != NAVDRAWER_ITEM_SEND_FEEDBACK) : (itemId == NAVDRAWER_ITEM_SEND_FEEDBACK))))))) : ((ListenerUtil.mutListener.listen(953) ? ((ListenerUtil.mutListener.listen(947) ? (itemId >= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(946) ? (itemId <= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(945) ? (itemId > NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(944) ? (itemId < NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(943) ? (itemId != NAVDRAWER_ITEM_SETTINGS) : (itemId == NAVDRAWER_ITEM_SETTINGS)))))) && (ListenerUtil.mutListener.listen(952) ? (itemId >= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(951) ? (itemId <= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(950) ? (itemId > NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(949) ? (itemId < NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(948) ? (itemId != NAVDRAWER_ITEM_HELP) : (itemId == NAVDRAWER_ITEM_HELP))))))) : ((ListenerUtil.mutListener.listen(947) ? (itemId >= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(946) ? (itemId <= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(945) ? (itemId > NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(944) ? (itemId < NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(943) ? (itemId != NAVDRAWER_ITEM_SETTINGS) : (itemId == NAVDRAWER_ITEM_SETTINGS)))))) || (ListenerUtil.mutListener.listen(952) ? (itemId >= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(951) ? (itemId <= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(950) ? (itemId > NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(949) ? (itemId < NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(948) ? (itemId != NAVDRAWER_ITEM_HELP) : (itemId == NAVDRAWER_ITEM_HELP)))))))) || (ListenerUtil.mutListener.listen(958) ? (itemId >= NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(957) ? (itemId <= NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(956) ? (itemId > NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(955) ? (itemId < NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(954) ? (itemId != NAVDRAWER_ITEM_SEND_FEEDBACK) : (itemId == NAVDRAWER_ITEM_SEND_FEEDBACK)))))))) && (ListenerUtil.mutListener.listen(964) ? (itemId >= NAVDRAWER_ITEM_PLAN_TRIP) : (ListenerUtil.mutListener.listen(963) ? (itemId <= NAVDRAWER_ITEM_PLAN_TRIP) : (ListenerUtil.mutListener.listen(962) ? (itemId > NAVDRAWER_ITEM_PLAN_TRIP) : (ListenerUtil.mutListener.listen(961) ? (itemId < NAVDRAWER_ITEM_PLAN_TRIP) : (ListenerUtil.mutListener.listen(960) ? (itemId != NAVDRAWER_ITEM_PLAN_TRIP) : (itemId == NAVDRAWER_ITEM_PLAN_TRIP))))))) : ((ListenerUtil.mutListener.listen(959) ? ((ListenerUtil.mutListener.listen(953) ? ((ListenerUtil.mutListener.listen(947) ? (itemId >= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(946) ? (itemId <= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(945) ? (itemId > NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(944) ? (itemId < NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(943) ? (itemId != NAVDRAWER_ITEM_SETTINGS) : (itemId == NAVDRAWER_ITEM_SETTINGS)))))) && (ListenerUtil.mutListener.listen(952) ? (itemId >= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(951) ? (itemId <= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(950) ? (itemId > NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(949) ? (itemId < NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(948) ? (itemId != NAVDRAWER_ITEM_HELP) : (itemId == NAVDRAWER_ITEM_HELP))))))) : ((ListenerUtil.mutListener.listen(947) ? (itemId >= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(946) ? (itemId <= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(945) ? (itemId > NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(944) ? (itemId < NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(943) ? (itemId != NAVDRAWER_ITEM_SETTINGS) : (itemId == NAVDRAWER_ITEM_SETTINGS)))))) || (ListenerUtil.mutListener.listen(952) ? (itemId >= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(951) ? (itemId <= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(950) ? (itemId > NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(949) ? (itemId < NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(948) ? (itemId != NAVDRAWER_ITEM_HELP) : (itemId == NAVDRAWER_ITEM_HELP)))))))) && (ListenerUtil.mutListener.listen(958) ? (itemId >= NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(957) ? (itemId <= NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(956) ? (itemId > NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(955) ? (itemId < NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(954) ? (itemId != NAVDRAWER_ITEM_SEND_FEEDBACK) : (itemId == NAVDRAWER_ITEM_SEND_FEEDBACK))))))) : ((ListenerUtil.mutListener.listen(953) ? ((ListenerUtil.mutListener.listen(947) ? (itemId >= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(946) ? (itemId <= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(945) ? (itemId > NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(944) ? (itemId < NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(943) ? (itemId != NAVDRAWER_ITEM_SETTINGS) : (itemId == NAVDRAWER_ITEM_SETTINGS)))))) && (ListenerUtil.mutListener.listen(952) ? (itemId >= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(951) ? (itemId <= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(950) ? (itemId > NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(949) ? (itemId < NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(948) ? (itemId != NAVDRAWER_ITEM_HELP) : (itemId == NAVDRAWER_ITEM_HELP))))))) : ((ListenerUtil.mutListener.listen(947) ? (itemId >= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(946) ? (itemId <= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(945) ? (itemId > NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(944) ? (itemId < NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(943) ? (itemId != NAVDRAWER_ITEM_SETTINGS) : (itemId == NAVDRAWER_ITEM_SETTINGS)))))) || (ListenerUtil.mutListener.listen(952) ? (itemId >= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(951) ? (itemId <= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(950) ? (itemId > NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(949) ? (itemId < NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(948) ? (itemId != NAVDRAWER_ITEM_HELP) : (itemId == NAVDRAWER_ITEM_HELP)))))))) || (ListenerUtil.mutListener.listen(958) ? (itemId >= NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(957) ? (itemId <= NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(956) ? (itemId > NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(955) ? (itemId < NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(954) ? (itemId != NAVDRAWER_ITEM_SEND_FEEDBACK) : (itemId == NAVDRAWER_ITEM_SEND_FEEDBACK)))))))) || (ListenerUtil.mutListener.listen(964) ? (itemId >= NAVDRAWER_ITEM_PLAN_TRIP) : (ListenerUtil.mutListener.listen(963) ? (itemId <= NAVDRAWER_ITEM_PLAN_TRIP) : (ListenerUtil.mutListener.listen(962) ? (itemId > NAVDRAWER_ITEM_PLAN_TRIP) : (ListenerUtil.mutListener.listen(961) ? (itemId < NAVDRAWER_ITEM_PLAN_TRIP) : (ListenerUtil.mutListener.listen(960) ? (itemId != NAVDRAWER_ITEM_PLAN_TRIP) : (itemId == NAVDRAWER_ITEM_PLAN_TRIP)))))))) && (ListenerUtil.mutListener.listen(970) ? (itemId >= NAVDRAWER_ITEM_PAY_FARE) : (ListenerUtil.mutListener.listen(969) ? (itemId <= NAVDRAWER_ITEM_PAY_FARE) : (ListenerUtil.mutListener.listen(968) ? (itemId > NAVDRAWER_ITEM_PAY_FARE) : (ListenerUtil.mutListener.listen(967) ? (itemId < NAVDRAWER_ITEM_PAY_FARE) : (ListenerUtil.mutListener.listen(966) ? (itemId != NAVDRAWER_ITEM_PAY_FARE) : (itemId == NAVDRAWER_ITEM_PAY_FARE))))))) : ((ListenerUtil.mutListener.listen(965) ? ((ListenerUtil.mutListener.listen(959) ? ((ListenerUtil.mutListener.listen(953) ? ((ListenerUtil.mutListener.listen(947) ? (itemId >= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(946) ? (itemId <= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(945) ? (itemId > NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(944) ? (itemId < NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(943) ? (itemId != NAVDRAWER_ITEM_SETTINGS) : (itemId == NAVDRAWER_ITEM_SETTINGS)))))) && (ListenerUtil.mutListener.listen(952) ? (itemId >= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(951) ? (itemId <= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(950) ? (itemId > NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(949) ? (itemId < NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(948) ? (itemId != NAVDRAWER_ITEM_HELP) : (itemId == NAVDRAWER_ITEM_HELP))))))) : ((ListenerUtil.mutListener.listen(947) ? (itemId >= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(946) ? (itemId <= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(945) ? (itemId > NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(944) ? (itemId < NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(943) ? (itemId != NAVDRAWER_ITEM_SETTINGS) : (itemId == NAVDRAWER_ITEM_SETTINGS)))))) || (ListenerUtil.mutListener.listen(952) ? (itemId >= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(951) ? (itemId <= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(950) ? (itemId > NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(949) ? (itemId < NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(948) ? (itemId != NAVDRAWER_ITEM_HELP) : (itemId == NAVDRAWER_ITEM_HELP)))))))) && (ListenerUtil.mutListener.listen(958) ? (itemId >= NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(957) ? (itemId <= NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(956) ? (itemId > NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(955) ? (itemId < NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(954) ? (itemId != NAVDRAWER_ITEM_SEND_FEEDBACK) : (itemId == NAVDRAWER_ITEM_SEND_FEEDBACK))))))) : ((ListenerUtil.mutListener.listen(953) ? ((ListenerUtil.mutListener.listen(947) ? (itemId >= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(946) ? (itemId <= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(945) ? (itemId > NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(944) ? (itemId < NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(943) ? (itemId != NAVDRAWER_ITEM_SETTINGS) : (itemId == NAVDRAWER_ITEM_SETTINGS)))))) && (ListenerUtil.mutListener.listen(952) ? (itemId >= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(951) ? (itemId <= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(950) ? (itemId > NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(949) ? (itemId < NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(948) ? (itemId != NAVDRAWER_ITEM_HELP) : (itemId == NAVDRAWER_ITEM_HELP))))))) : ((ListenerUtil.mutListener.listen(947) ? (itemId >= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(946) ? (itemId <= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(945) ? (itemId > NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(944) ? (itemId < NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(943) ? (itemId != NAVDRAWER_ITEM_SETTINGS) : (itemId == NAVDRAWER_ITEM_SETTINGS)))))) || (ListenerUtil.mutListener.listen(952) ? (itemId >= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(951) ? (itemId <= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(950) ? (itemId > NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(949) ? (itemId < NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(948) ? (itemId != NAVDRAWER_ITEM_HELP) : (itemId == NAVDRAWER_ITEM_HELP)))))))) || (ListenerUtil.mutListener.listen(958) ? (itemId >= NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(957) ? (itemId <= NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(956) ? (itemId > NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(955) ? (itemId < NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(954) ? (itemId != NAVDRAWER_ITEM_SEND_FEEDBACK) : (itemId == NAVDRAWER_ITEM_SEND_FEEDBACK)))))))) && (ListenerUtil.mutListener.listen(964) ? (itemId >= NAVDRAWER_ITEM_PLAN_TRIP) : (ListenerUtil.mutListener.listen(963) ? (itemId <= NAVDRAWER_ITEM_PLAN_TRIP) : (ListenerUtil.mutListener.listen(962) ? (itemId > NAVDRAWER_ITEM_PLAN_TRIP) : (ListenerUtil.mutListener.listen(961) ? (itemId < NAVDRAWER_ITEM_PLAN_TRIP) : (ListenerUtil.mutListener.listen(960) ? (itemId != NAVDRAWER_ITEM_PLAN_TRIP) : (itemId == NAVDRAWER_ITEM_PLAN_TRIP))))))) : ((ListenerUtil.mutListener.listen(959) ? ((ListenerUtil.mutListener.listen(953) ? ((ListenerUtil.mutListener.listen(947) ? (itemId >= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(946) ? (itemId <= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(945) ? (itemId > NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(944) ? (itemId < NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(943) ? (itemId != NAVDRAWER_ITEM_SETTINGS) : (itemId == NAVDRAWER_ITEM_SETTINGS)))))) && (ListenerUtil.mutListener.listen(952) ? (itemId >= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(951) ? (itemId <= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(950) ? (itemId > NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(949) ? (itemId < NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(948) ? (itemId != NAVDRAWER_ITEM_HELP) : (itemId == NAVDRAWER_ITEM_HELP))))))) : ((ListenerUtil.mutListener.listen(947) ? (itemId >= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(946) ? (itemId <= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(945) ? (itemId > NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(944) ? (itemId < NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(943) ? (itemId != NAVDRAWER_ITEM_SETTINGS) : (itemId == NAVDRAWER_ITEM_SETTINGS)))))) || (ListenerUtil.mutListener.listen(952) ? (itemId >= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(951) ? (itemId <= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(950) ? (itemId > NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(949) ? (itemId < NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(948) ? (itemId != NAVDRAWER_ITEM_HELP) : (itemId == NAVDRAWER_ITEM_HELP)))))))) && (ListenerUtil.mutListener.listen(958) ? (itemId >= NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(957) ? (itemId <= NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(956) ? (itemId > NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(955) ? (itemId < NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(954) ? (itemId != NAVDRAWER_ITEM_SEND_FEEDBACK) : (itemId == NAVDRAWER_ITEM_SEND_FEEDBACK))))))) : ((ListenerUtil.mutListener.listen(953) ? ((ListenerUtil.mutListener.listen(947) ? (itemId >= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(946) ? (itemId <= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(945) ? (itemId > NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(944) ? (itemId < NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(943) ? (itemId != NAVDRAWER_ITEM_SETTINGS) : (itemId == NAVDRAWER_ITEM_SETTINGS)))))) && (ListenerUtil.mutListener.listen(952) ? (itemId >= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(951) ? (itemId <= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(950) ? (itemId > NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(949) ? (itemId < NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(948) ? (itemId != NAVDRAWER_ITEM_HELP) : (itemId == NAVDRAWER_ITEM_HELP))))))) : ((ListenerUtil.mutListener.listen(947) ? (itemId >= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(946) ? (itemId <= NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(945) ? (itemId > NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(944) ? (itemId < NAVDRAWER_ITEM_SETTINGS) : (ListenerUtil.mutListener.listen(943) ? (itemId != NAVDRAWER_ITEM_SETTINGS) : (itemId == NAVDRAWER_ITEM_SETTINGS)))))) || (ListenerUtil.mutListener.listen(952) ? (itemId >= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(951) ? (itemId <= NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(950) ? (itemId > NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(949) ? (itemId < NAVDRAWER_ITEM_HELP) : (ListenerUtil.mutListener.listen(948) ? (itemId != NAVDRAWER_ITEM_HELP) : (itemId == NAVDRAWER_ITEM_HELP)))))))) || (ListenerUtil.mutListener.listen(958) ? (itemId >= NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(957) ? (itemId <= NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(956) ? (itemId > NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(955) ? (itemId < NAVDRAWER_ITEM_SEND_FEEDBACK) : (ListenerUtil.mutListener.listen(954) ? (itemId != NAVDRAWER_ITEM_SEND_FEEDBACK) : (itemId == NAVDRAWER_ITEM_SEND_FEEDBACK)))))))) || (ListenerUtil.mutListener.listen(964) ? (itemId >= NAVDRAWER_ITEM_PLAN_TRIP) : (ListenerUtil.mutListener.listen(963) ? (itemId <= NAVDRAWER_ITEM_PLAN_TRIP) : (ListenerUtil.mutListener.listen(962) ? (itemId > NAVDRAWER_ITEM_PLAN_TRIP) : (ListenerUtil.mutListener.listen(961) ? (itemId < NAVDRAWER_ITEM_PLAN_TRIP) : (ListenerUtil.mutListener.listen(960) ? (itemId != NAVDRAWER_ITEM_PLAN_TRIP) : (itemId == NAVDRAWER_ITEM_PLAN_TRIP)))))))) || (ListenerUtil.mutListener.listen(970) ? (itemId >= NAVDRAWER_ITEM_PAY_FARE) : (ListenerUtil.mutListener.listen(969) ? (itemId <= NAVDRAWER_ITEM_PAY_FARE) : (ListenerUtil.mutListener.listen(968) ? (itemId > NAVDRAWER_ITEM_PAY_FARE) : (ListenerUtil.mutListener.listen(967) ? (itemId < NAVDRAWER_ITEM_PAY_FARE) : (ListenerUtil.mutListener.listen(966) ? (itemId != NAVDRAWER_ITEM_PAY_FARE) : (itemId == NAVDRAWER_ITEM_PAY_FARE)))))))) || (ListenerUtil.mutListener.listen(976) ? (itemId >= NAVDRAWER_ITEM_OPEN_SOURCE) : (ListenerUtil.mutListener.listen(975) ? (itemId <= NAVDRAWER_ITEM_OPEN_SOURCE) : (ListenerUtil.mutListener.listen(974) ? (itemId > NAVDRAWER_ITEM_OPEN_SOURCE) : (ListenerUtil.mutListener.listen(973) ? (itemId < NAVDRAWER_ITEM_OPEN_SOURCE) : (ListenerUtil.mutListener.listen(972) ? (itemId != NAVDRAWER_ITEM_OPEN_SOURCE) : (itemId == NAVDRAWER_ITEM_OPEN_SOURCE))))))));
    }

    /**
     * Returns true if the item has been deprecated.
     *
     * @return true if the item has been deprecated, false if the item is a valid selection.
     */
    public boolean isItemDeprecated(int itemId) {
        return (ListenerUtil.mutListener.listen(988) ? ((ListenerUtil.mutListener.listen(982) ? (itemId <= 7) : (ListenerUtil.mutListener.listen(981) ? (itemId > 7) : (ListenerUtil.mutListener.listen(980) ? (itemId < 7) : (ListenerUtil.mutListener.listen(979) ? (itemId != 7) : (ListenerUtil.mutListener.listen(978) ? (itemId == 7) : (itemId >= 7)))))) || (ListenerUtil.mutListener.listen(987) ? (itemId >= 11) : (ListenerUtil.mutListener.listen(986) ? (itemId > 11) : (ListenerUtil.mutListener.listen(985) ? (itemId < 11) : (ListenerUtil.mutListener.listen(984) ? (itemId != 11) : (ListenerUtil.mutListener.listen(983) ? (itemId == 11) : (itemId <= 11))))))) : ((ListenerUtil.mutListener.listen(982) ? (itemId <= 7) : (ListenerUtil.mutListener.listen(981) ? (itemId > 7) : (ListenerUtil.mutListener.listen(980) ? (itemId < 7) : (ListenerUtil.mutListener.listen(979) ? (itemId != 7) : (ListenerUtil.mutListener.listen(978) ? (itemId == 7) : (itemId >= 7)))))) && (ListenerUtil.mutListener.listen(987) ? (itemId >= 11) : (ListenerUtil.mutListener.listen(986) ? (itemId > 11) : (ListenerUtil.mutListener.listen(985) ? (itemId < 11) : (ListenerUtil.mutListener.listen(984) ? (itemId != 11) : (ListenerUtil.mutListener.listen(983) ? (itemId == 11) : (itemId <= 11))))))));
    }
}
