/**
 * *************************************************************************************
 *  Copyright (c) 2014 Michael Goldbach <michael@m-goldbach.net>                         *
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
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import com.ichi2.anim.ActivityTransitionAnimation;
import com.ichi2.anki.stats.AnkiStatsTaskHandler;
import com.ichi2.anki.stats.ChartView;
import com.ichi2.anki.widgets.DeckDropDownAdapter;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.Decks;
import com.ichi2.libanki.stats.Stats;
import com.ichi2.libanki.Deck;
import com.ichi2.ui.FixedTextView;
import com.ichi2.ui.SlidingTabLayout;
import com.ichi2.utils.JSONException;
import java.util.ArrayList;
import java.util.Locale;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class Statistics extends NavigationDrawerActivity implements DeckDropDownAdapter.SubtitleListener {

    public static final int TODAYS_STATS_TAB_POSITION = 0;

    public static final int FORECAST_TAB_POSITION = 1;

    public static final int REVIEW_COUNT_TAB_POSITION = 2;

    public static final int REVIEW_TIME_TAB_POSITION = 3;

    public static final int INTERVALS_TAB_POSITION = 4;

    public static final int HOURLY_BREAKDOWN_TAB_POSITION = 5;

    public static final int WEEKLY_BREAKDOWN_TAB_POSITION = 6;

    public static final int ANSWER_BUTTONS_TAB_POSITION = 7;

    public static final int CARDS_TYPES_TAB_POSITION = 8;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    private AnkiStatsTaskHandler mTaskHandler = null;

    private long mDeckId;

    private ArrayList<Deck> mDropDownDecks;

    private Spinner mActionBarSpinner;

    private static boolean sIsSubtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(11487)) {
            if (showedActivityFailedScreen(savedInstanceState)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(11488)) {
            Timber.d("onCreate()");
        }
        if (!ListenerUtil.mutListener.listen(11489)) {
            sIsSubtitle = true;
        }
        if (!ListenerUtil.mutListener.listen(11490)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(11491)) {
            setContentView(R.layout.activity_anki_stats);
        }
        if (!ListenerUtil.mutListener.listen(11492)) {
            initNavigationDrawer(findViewById(android.R.id.content));
        }
        if (!ListenerUtil.mutListener.listen(11493)) {
            startLoadingCollection();
        }
    }

    @Override
    protected void onCollectionLoaded(Collection col) {
        if (!ListenerUtil.mutListener.listen(11494)) {
            Timber.d("onCollectionLoaded()");
        }
        if (!ListenerUtil.mutListener.listen(11495)) {
            super.onCollectionLoaded(col);
        }
        if (!ListenerUtil.mutListener.listen(11496)) {
            // Add drop-down menu to select deck to action bar.
            mDropDownDecks = getCol().getDecks().allSorted();
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(11497)) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(11498)) {
            mActionBarSpinner = findViewById(R.id.toolbar_spinner);
        }
        if (!ListenerUtil.mutListener.listen(11499)) {
            mActionBarSpinner.setAdapter(new DeckDropDownAdapter(this, mDropDownDecks));
        }
        if (!ListenerUtil.mutListener.listen(11501)) {
            mActionBarSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (!ListenerUtil.mutListener.listen(11500)) {
                        selectDropDownItem(position);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(11502)) {
            mActionBarSpinner.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(11503)) {
            // Setup Task Handler
            mTaskHandler = new AnkiStatsTaskHandler(col);
        }
        if (!ListenerUtil.mutListener.listen(11504)) {
            // primary sections of the activity.
            mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        }
        if (!ListenerUtil.mutListener.listen(11505)) {
            // Set up the ViewPager with the sections adapter.
            mViewPager = findViewById(R.id.pager);
        }
        if (!ListenerUtil.mutListener.listen(11506)) {
            mViewPager.setAdapter(mSectionsPagerAdapter);
        }
        if (!ListenerUtil.mutListener.listen(11507)) {
            mViewPager.setOffscreenPageLimit(8);
        }
        SlidingTabLayout slidingTabLayout = findViewById(R.id.sliding_tabs);
        if (!ListenerUtil.mutListener.listen(11508)) {
            slidingTabLayout.setViewPager(mViewPager);
        }
        // Dirty way to get text size from a TextView with current style, change if possible
        float size = new FixedTextView(this).getTextSize();
        if (!ListenerUtil.mutListener.listen(11509)) {
            mTaskHandler.setmStandardTextSize(size);
        }
        if (!ListenerUtil.mutListener.listen(11510)) {
            // Prepare options menu only after loading everything
            supportInvalidateOptionsMenu();
        }
        if (!ListenerUtil.mutListener.listen(11511)) {
            mSectionsPagerAdapter.notifyDataSetChanged();
        }
        if (!ListenerUtil.mutListener.listen(11512)) {
            // Default to libanki's selected deck
            selectDeckById(getCol().getDecks().selected());
        }
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(11513)) {
            Timber.d("onResume()");
        }
        if (!ListenerUtil.mutListener.listen(11514)) {
            selectNavigationItem(R.id.nav_stats);
        }
        if (!ListenerUtil.mutListener.listen(11515)) {
            super.onResume();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(11516)) {
            super.onCreateOptionsMenu(menu);
        }
        // System.err.println("in onCreateOptionsMenu");
        MenuInflater inflater = getMenuInflater();
        if (!ListenerUtil.mutListener.listen(11517)) {
            inflater.inflate(R.menu.anki_stats, menu);
        }
        if (!ListenerUtil.mutListener.listen(11522)) {
            // exit if mTaskHandler not initialized yet
            if (mTaskHandler != null) {
                if (!ListenerUtil.mutListener.listen(11521)) {
                    switch(mTaskHandler.getStatType()) {
                        case TYPE_MONTH:
                            MenuItem monthItem = menu.findItem(R.id.item_time_month);
                            if (!ListenerUtil.mutListener.listen(11518)) {
                                monthItem.setChecked(true);
                            }
                            break;
                        case TYPE_YEAR:
                            MenuItem yearItem = menu.findItem(R.id.item_time_year);
                            if (!ListenerUtil.mutListener.listen(11519)) {
                                yearItem.setChecked(true);
                            }
                            break;
                        case TYPE_LIFE:
                            MenuItem lifeItem = menu.findItem(R.id.item_time_all);
                            if (!ListenerUtil.mutListener.listen(11520)) {
                                lifeItem.setChecked(true);
                            }
                            break;
                    }
                }
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(11523)) {
            if (getDrawerToggle().onOptionsItemSelected(item)) {
                return true;
            }
        }
        int itemId = item.getItemId();
        if (!ListenerUtil.mutListener.listen(11536)) {
            if (itemId == R.id.item_time_month) {
                if (!ListenerUtil.mutListener.listen(11532)) {
                    item.setChecked(!item.isChecked());
                }
                if (!ListenerUtil.mutListener.listen(11535)) {
                    if (mTaskHandler.getStatType() != Stats.AxisType.TYPE_MONTH) {
                        if (!ListenerUtil.mutListener.listen(11533)) {
                            mTaskHandler.setStatType(Stats.AxisType.TYPE_MONTH);
                        }
                        if (!ListenerUtil.mutListener.listen(11534)) {
                            mSectionsPagerAdapter.notifyDataSetChanged();
                        }
                    }
                }
                return true;
            } else if (itemId == R.id.item_time_year) {
                if (!ListenerUtil.mutListener.listen(11528)) {
                    item.setChecked(!item.isChecked());
                }
                if (!ListenerUtil.mutListener.listen(11531)) {
                    if (mTaskHandler.getStatType() != Stats.AxisType.TYPE_YEAR) {
                        if (!ListenerUtil.mutListener.listen(11529)) {
                            mTaskHandler.setStatType(Stats.AxisType.TYPE_YEAR);
                        }
                        if (!ListenerUtil.mutListener.listen(11530)) {
                            mSectionsPagerAdapter.notifyDataSetChanged();
                        }
                    }
                }
                return true;
            } else if (itemId == R.id.item_time_all) {
                if (!ListenerUtil.mutListener.listen(11524)) {
                    item.setChecked(!item.isChecked());
                }
                if (!ListenerUtil.mutListener.listen(11527)) {
                    if (mTaskHandler.getStatType() != Stats.AxisType.TYPE_LIFE) {
                        if (!ListenerUtil.mutListener.listen(11525)) {
                            mTaskHandler.setStatType(Stats.AxisType.TYPE_LIFE);
                        }
                        if (!ListenerUtil.mutListener.listen(11526)) {
                            mSectionsPagerAdapter.notifyDataSetChanged();
                        }
                    }
                }
                return true;
            } else if (itemId == R.id.action_time_chooser) {
                // showTimeDialog();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void selectDropDownItem(int position) {
        if (!ListenerUtil.mutListener.listen(11537)) {
            mActionBarSpinner.setSelection(position);
        }
        if (!ListenerUtil.mutListener.listen(11550)) {
            if ((ListenerUtil.mutListener.listen(11542) ? (position >= 0) : (ListenerUtil.mutListener.listen(11541) ? (position <= 0) : (ListenerUtil.mutListener.listen(11540) ? (position > 0) : (ListenerUtil.mutListener.listen(11539) ? (position < 0) : (ListenerUtil.mutListener.listen(11538) ? (position != 0) : (position == 0))))))) {
                if (!ListenerUtil.mutListener.listen(11549)) {
                    mDeckId = Stats.ALL_DECKS_ID;
                }
            } else {
                Deck deck = mDropDownDecks.get((ListenerUtil.mutListener.listen(11546) ? (position % 1) : (ListenerUtil.mutListener.listen(11545) ? (position / 1) : (ListenerUtil.mutListener.listen(11544) ? (position * 1) : (ListenerUtil.mutListener.listen(11543) ? (position + 1) : (position - 1))))));
                try {
                    if (!ListenerUtil.mutListener.listen(11548)) {
                        mDeckId = deck.getLong("id");
                    }
                } catch (JSONException e) {
                    if (!ListenerUtil.mutListener.listen(11547)) {
                        Timber.e(e, "Could not get ID from deck");
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11551)) {
            mTaskHandler.setDeckId(mDeckId);
        }
        if (!ListenerUtil.mutListener.listen(11552)) {
            mSectionsPagerAdapter.notifyDataSetChanged();
        }
    }

    // Iterates the drop down decks, and selects the one matching the given id
    private boolean selectDeckById(long deckId) {
        if (!ListenerUtil.mutListener.listen(11564)) {
            {
                long _loopCounter190 = 0;
                for (int dropDownDeckIdx = 0; (ListenerUtil.mutListener.listen(11563) ? (dropDownDeckIdx >= mDropDownDecks.size()) : (ListenerUtil.mutListener.listen(11562) ? (dropDownDeckIdx <= mDropDownDecks.size()) : (ListenerUtil.mutListener.listen(11561) ? (dropDownDeckIdx > mDropDownDecks.size()) : (ListenerUtil.mutListener.listen(11560) ? (dropDownDeckIdx != mDropDownDecks.size()) : (ListenerUtil.mutListener.listen(11559) ? (dropDownDeckIdx == mDropDownDecks.size()) : (dropDownDeckIdx < mDropDownDecks.size())))))); dropDownDeckIdx++) {
                    ListenerUtil.loopListener.listen("_loopCounter190", ++_loopCounter190);
                    if (!ListenerUtil.mutListener.listen(11558)) {
                        if (mDropDownDecks.get(dropDownDeckIdx).getLong("id") == deckId) {
                            if (!ListenerUtil.mutListener.listen(11557)) {
                                selectDropDownItem((ListenerUtil.mutListener.listen(11556) ? (dropDownDeckIdx % 1) : (ListenerUtil.mutListener.listen(11555) ? (dropDownDeckIdx / 1) : (ListenerUtil.mutListener.listen(11554) ? (dropDownDeckIdx * 1) : (ListenerUtil.mutListener.listen(11553) ? (dropDownDeckIdx - 1) : (dropDownDeckIdx + 1))))));
                            }
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * @return text to be used in the subtitle of the drop-down deck selector
     */
    public String getSubtitleText() {
        return getResources().getString(R.string.statistics);
    }

    public AnkiStatsTaskHandler getTaskHandler() {
        return mTaskHandler;
    }

    public ViewPager getViewPager() {
        return mViewPager;
    }

    public SectionsPagerAdapter getSectionsPagerAdapter() {
        return mSectionsPagerAdapter;
    }

    private long getDeckId() {
        return mDeckId;
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        // works best for updating all tabs
        @Override
        public int getItemPosition(@NonNull Object object) {
            if (!ListenerUtil.mutListener.listen(11566)) {
                if (object instanceof StatisticFragment) {
                    if (!ListenerUtil.mutListener.listen(11565)) {
                        ((StatisticFragment) object).checkAndUpdate();
                    }
                }
            }
            // don't return POSITION_NONE, avoid fragment recreation.
            return super.getItemPosition(object);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            StatisticFragment item = StatisticFragment.newInstance(position);
            if (!ListenerUtil.mutListener.listen(11567)) {
                item.checkAndUpdate();
            }
            return item;
        }

        @Override
        public int getCount() {
            return 9;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            if (!ListenerUtil.mutListener.listen(11568)) {
                switch(position) {
                    case TODAYS_STATS_TAB_POSITION:
                        return getString(R.string.stats_overview).toUpperCase(l);
                    case FORECAST_TAB_POSITION:
                        return getString(R.string.stats_forecast).toUpperCase(l);
                    case REVIEW_COUNT_TAB_POSITION:
                        return getString(R.string.stats_review_count).toUpperCase(l);
                    case REVIEW_TIME_TAB_POSITION:
                        return getString(R.string.stats_review_time).toUpperCase(l);
                    case INTERVALS_TAB_POSITION:
                        return getString(R.string.stats_review_intervals).toUpperCase(l);
                    case HOURLY_BREAKDOWN_TAB_POSITION:
                        return getString(R.string.stats_breakdown).toUpperCase(l);
                    case WEEKLY_BREAKDOWN_TAB_POSITION:
                        return getString(R.string.stats_weekly_breakdown).toUpperCase(l);
                    case ANSWER_BUTTONS_TAB_POSITION:
                        return getString(R.string.stats_answer_buttons).toUpperCase(l);
                    case CARDS_TYPES_TAB_POSITION:
                        return getString(R.string.title_activity_template_editor).toUpperCase(l);
                }
            }
            return null;
        }
    }

    public abstract static class StatisticFragment extends Fragment {

        // track current settings for each individual fragment
        protected long mDeckId;

        protected ViewPager mActivityPager;

        protected SectionsPagerAdapter mActivitySectionPagerAdapter;

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        protected static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static StatisticFragment newInstance(int sectionNumber) {
            Fragment fragment;
            Bundle args;
            switch(sectionNumber) {
                case FORECAST_TAB_POSITION:
                case REVIEW_COUNT_TAB_POSITION:
                case REVIEW_TIME_TAB_POSITION:
                case INTERVALS_TAB_POSITION:
                case HOURLY_BREAKDOWN_TAB_POSITION:
                case WEEKLY_BREAKDOWN_TAB_POSITION:
                case ANSWER_BUTTONS_TAB_POSITION:
                case CARDS_TYPES_TAB_POSITION:
                    fragment = new ChartFragment();
                    args = new Bundle();
                    if (!ListenerUtil.mutListener.listen(11569)) {
                        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
                    }
                    if (!ListenerUtil.mutListener.listen(11570)) {
                        fragment.setArguments(args);
                    }
                    return (ChartFragment) fragment;
                case TODAYS_STATS_TAB_POSITION:
                    fragment = new OverviewStatisticsFragment();
                    args = new Bundle();
                    if (!ListenerUtil.mutListener.listen(11571)) {
                        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
                    }
                    if (!ListenerUtil.mutListener.listen(11572)) {
                        fragment.setArguments(args);
                    }
                    return (OverviewStatisticsFragment) fragment;
                default:
                    return null;
            }
        }

        @Override
        public void onResume() {
            if (!ListenerUtil.mutListener.listen(11573)) {
                super.onResume();
            }
            if (!ListenerUtil.mutListener.listen(11574)) {
                checkAndUpdate();
            }
        }

        public abstract void invalidateView();

        public abstract void checkAndUpdate();
    }

    /**
     * A chart fragment containing a ChartView.
     */
    public static class ChartFragment extends StatisticFragment {

        private ChartView mChart;

        private ProgressBar mProgressBar;

        private int mHeight = 0;

        private int mWidth = 0;

        private int mSectionNumber;

        private Stats.AxisType mType = Stats.AxisType.TYPE_MONTH;

        private boolean mIsCreated = false;

        private AsyncTask mCreateChartTask;

        public ChartFragment() {
            super();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            if (!ListenerUtil.mutListener.listen(11575)) {
                setHasOptionsMenu(true);
            }
            Bundle bundle = getArguments();
            if (!ListenerUtil.mutListener.listen(11576)) {
                mSectionNumber = bundle.getInt(ARG_SECTION_NUMBER);
            }
            // System.err.println("sectionNumber: " + mSectionNumber);
            View rootView = inflater.inflate(R.layout.fragment_anki_stats, container, false);
            if (!ListenerUtil.mutListener.listen(11577)) {
                mChart = rootView.findViewById(R.id.image_view_chart);
            }
            if (!ListenerUtil.mutListener.listen(11580)) {
                if (mChart == null) {
                    if (!ListenerUtil.mutListener.listen(11579)) {
                        Timber.d("mChart null!");
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(11578)) {
                        Timber.d("mChart is not null!");
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(11581)) {
                mProgressBar = rootView.findViewById(R.id.progress_bar_stats);
            }
            if (!ListenerUtil.mutListener.listen(11582)) {
                mProgressBar.setVisibility(View.VISIBLE);
            }
            if (!ListenerUtil.mutListener.listen(11585)) {
                // TODO: Implementing loader for Collection in Fragment itself would be a better solution.
                if ((((Statistics) getActivity()).getTaskHandler()) == null) {
                    if (!ListenerUtil.mutListener.listen(11583)) {
                        // Close statistics if the TaskHandler hasn't been loaded yet
                        Timber.e("Statistics.ChartFragment.onCreateView() TaskHandler not found");
                    }
                    if (!ListenerUtil.mutListener.listen(11584)) {
                        getActivity().finish();
                    }
                    return rootView;
                }
            }
            if (!ListenerUtil.mutListener.listen(11586)) {
                createChart();
            }
            if (!ListenerUtil.mutListener.listen(11587)) {
                mHeight = mChart.getMeasuredHeight();
            }
            if (!ListenerUtil.mutListener.listen(11588)) {
                mWidth = mChart.getMeasuredWidth();
            }
            if (!ListenerUtil.mutListener.listen(11589)) {
                mChart.addFragment(this);
            }
            if (!ListenerUtil.mutListener.listen(11590)) {
                mType = (((Statistics) getActivity()).getTaskHandler()).getStatType();
            }
            if (!ListenerUtil.mutListener.listen(11591)) {
                mIsCreated = true;
            }
            if (!ListenerUtil.mutListener.listen(11592)) {
                mActivityPager = ((Statistics) getActivity()).getViewPager();
            }
            if (!ListenerUtil.mutListener.listen(11593)) {
                mActivitySectionPagerAdapter = ((Statistics) getActivity()).getSectionsPagerAdapter();
            }
            if (!ListenerUtil.mutListener.listen(11594)) {
                mDeckId = ((Statistics) getActivity()).getDeckId();
            }
            if (!ListenerUtil.mutListener.listen(11606)) {
                if ((ListenerUtil.mutListener.listen(11599) ? (mDeckId >= Stats.ALL_DECKS_ID) : (ListenerUtil.mutListener.listen(11598) ? (mDeckId <= Stats.ALL_DECKS_ID) : (ListenerUtil.mutListener.listen(11597) ? (mDeckId > Stats.ALL_DECKS_ID) : (ListenerUtil.mutListener.listen(11596) ? (mDeckId < Stats.ALL_DECKS_ID) : (ListenerUtil.mutListener.listen(11595) ? (mDeckId == Stats.ALL_DECKS_ID) : (mDeckId != Stats.ALL_DECKS_ID))))))) {
                    Collection col = CollectionHelper.getInstance().getCol(getActivity());
                    String baseName = Decks.basename(col.getDecks().current().getString("name"));
                    if (!ListenerUtil.mutListener.listen(11605)) {
                        if (sIsSubtitle) {
                            if (!ListenerUtil.mutListener.listen(11604)) {
                                ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle(baseName);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(11603)) {
                                getActivity().setTitle(baseName);
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(11602)) {
                        if (sIsSubtitle) {
                            if (!ListenerUtil.mutListener.listen(11601)) {
                                ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle(R.string.stats_deck_collection);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(11600)) {
                                getActivity().setTitle(getResources().getString(R.string.stats_deck_collection));
                            }
                        }
                    }
                }
            }
            return rootView;
        }

        private void createChart() {
            if (!ListenerUtil.mutListener.listen(11615)) {
                switch(mSectionNumber) {
                    case FORECAST_TAB_POSITION:
                        if (!ListenerUtil.mutListener.listen(11607)) {
                            mCreateChartTask = (((Statistics) getActivity()).getTaskHandler()).createChart(Stats.ChartType.FORECAST, mChart, mProgressBar);
                        }
                        break;
                    case REVIEW_COUNT_TAB_POSITION:
                        if (!ListenerUtil.mutListener.listen(11608)) {
                            mCreateChartTask = (((Statistics) getActivity()).getTaskHandler()).createChart(Stats.ChartType.REVIEW_COUNT, mChart, mProgressBar);
                        }
                        break;
                    case REVIEW_TIME_TAB_POSITION:
                        if (!ListenerUtil.mutListener.listen(11609)) {
                            mCreateChartTask = (((Statistics) getActivity()).getTaskHandler()).createChart(Stats.ChartType.REVIEW_TIME, mChart, mProgressBar);
                        }
                        break;
                    case INTERVALS_TAB_POSITION:
                        if (!ListenerUtil.mutListener.listen(11610)) {
                            mCreateChartTask = (((Statistics) getActivity()).getTaskHandler()).createChart(Stats.ChartType.INTERVALS, mChart, mProgressBar);
                        }
                        break;
                    case HOURLY_BREAKDOWN_TAB_POSITION:
                        if (!ListenerUtil.mutListener.listen(11611)) {
                            mCreateChartTask = (((Statistics) getActivity()).getTaskHandler()).createChart(Stats.ChartType.HOURLY_BREAKDOWN, mChart, mProgressBar);
                        }
                        break;
                    case WEEKLY_BREAKDOWN_TAB_POSITION:
                        if (!ListenerUtil.mutListener.listen(11612)) {
                            mCreateChartTask = (((Statistics) getActivity()).getTaskHandler()).createChart(Stats.ChartType.WEEKLY_BREAKDOWN, mChart, mProgressBar);
                        }
                        break;
                    case ANSWER_BUTTONS_TAB_POSITION:
                        if (!ListenerUtil.mutListener.listen(11613)) {
                            mCreateChartTask = (((Statistics) getActivity()).getTaskHandler()).createChart(Stats.ChartType.ANSWER_BUTTONS, mChart, mProgressBar);
                        }
                        break;
                    case CARDS_TYPES_TAB_POSITION:
                        if (!ListenerUtil.mutListener.listen(11614)) {
                            mCreateChartTask = (((Statistics) getActivity()).getTaskHandler()).createChart(Stats.ChartType.CARDS_TYPES, mChart, mProgressBar);
                        }
                        break;
                }
            }
        }

        @Override
        public void checkAndUpdate() {
            if (!ListenerUtil.mutListener.listen(11616)) {
                // System.err.println("<<<<<<<checkAndUpdate" + mSectionNumber);
                if (!mIsCreated) {
                    return;
                }
            }
            int height = mChart.getMeasuredHeight();
            int width = mChart.getMeasuredWidth();
            if (!ListenerUtil.mutListener.listen(11652)) {
                // are height and width checks still necessary without bitmaps?
                if ((ListenerUtil.mutListener.listen(11627) ? ((ListenerUtil.mutListener.listen(11621) ? (height >= 0) : (ListenerUtil.mutListener.listen(11620) ? (height <= 0) : (ListenerUtil.mutListener.listen(11619) ? (height > 0) : (ListenerUtil.mutListener.listen(11618) ? (height < 0) : (ListenerUtil.mutListener.listen(11617) ? (height == 0) : (height != 0)))))) || (ListenerUtil.mutListener.listen(11626) ? (width >= 0) : (ListenerUtil.mutListener.listen(11625) ? (width <= 0) : (ListenerUtil.mutListener.listen(11624) ? (width > 0) : (ListenerUtil.mutListener.listen(11623) ? (width < 0) : (ListenerUtil.mutListener.listen(11622) ? (width == 0) : (width != 0))))))) : ((ListenerUtil.mutListener.listen(11621) ? (height >= 0) : (ListenerUtil.mutListener.listen(11620) ? (height <= 0) : (ListenerUtil.mutListener.listen(11619) ? (height > 0) : (ListenerUtil.mutListener.listen(11618) ? (height < 0) : (ListenerUtil.mutListener.listen(11617) ? (height == 0) : (height != 0)))))) && (ListenerUtil.mutListener.listen(11626) ? (width >= 0) : (ListenerUtil.mutListener.listen(11625) ? (width <= 0) : (ListenerUtil.mutListener.listen(11624) ? (width > 0) : (ListenerUtil.mutListener.listen(11623) ? (width < 0) : (ListenerUtil.mutListener.listen(11622) ? (width == 0) : (width != 0))))))))) {
                    Collection col = CollectionHelper.getInstance().getCol(getActivity());
                    if (!ListenerUtil.mutListener.listen(11651)) {
                        if ((ListenerUtil.mutListener.listen(11640) ? ((ListenerUtil.mutListener.listen(11639) ? ((ListenerUtil.mutListener.listen(11638) ? ((ListenerUtil.mutListener.listen(11632) ? (mHeight >= height) : (ListenerUtil.mutListener.listen(11631) ? (mHeight <= height) : (ListenerUtil.mutListener.listen(11630) ? (mHeight > height) : (ListenerUtil.mutListener.listen(11629) ? (mHeight < height) : (ListenerUtil.mutListener.listen(11628) ? (mHeight == height) : (mHeight != height)))))) && (ListenerUtil.mutListener.listen(11637) ? (mWidth >= width) : (ListenerUtil.mutListener.listen(11636) ? (mWidth <= width) : (ListenerUtil.mutListener.listen(11635) ? (mWidth > width) : (ListenerUtil.mutListener.listen(11634) ? (mWidth < width) : (ListenerUtil.mutListener.listen(11633) ? (mWidth == width) : (mWidth != width))))))) : ((ListenerUtil.mutListener.listen(11632) ? (mHeight >= height) : (ListenerUtil.mutListener.listen(11631) ? (mHeight <= height) : (ListenerUtil.mutListener.listen(11630) ? (mHeight > height) : (ListenerUtil.mutListener.listen(11629) ? (mHeight < height) : (ListenerUtil.mutListener.listen(11628) ? (mHeight == height) : (mHeight != height)))))) || (ListenerUtil.mutListener.listen(11637) ? (mWidth >= width) : (ListenerUtil.mutListener.listen(11636) ? (mWidth <= width) : (ListenerUtil.mutListener.listen(11635) ? (mWidth > width) : (ListenerUtil.mutListener.listen(11634) ? (mWidth < width) : (ListenerUtil.mutListener.listen(11633) ? (mWidth == width) : (mWidth != width)))))))) && mType != (((Statistics) getActivity()).getTaskHandler()).getStatType()) : ((ListenerUtil.mutListener.listen(11638) ? ((ListenerUtil.mutListener.listen(11632) ? (mHeight >= height) : (ListenerUtil.mutListener.listen(11631) ? (mHeight <= height) : (ListenerUtil.mutListener.listen(11630) ? (mHeight > height) : (ListenerUtil.mutListener.listen(11629) ? (mHeight < height) : (ListenerUtil.mutListener.listen(11628) ? (mHeight == height) : (mHeight != height)))))) && (ListenerUtil.mutListener.listen(11637) ? (mWidth >= width) : (ListenerUtil.mutListener.listen(11636) ? (mWidth <= width) : (ListenerUtil.mutListener.listen(11635) ? (mWidth > width) : (ListenerUtil.mutListener.listen(11634) ? (mWidth < width) : (ListenerUtil.mutListener.listen(11633) ? (mWidth == width) : (mWidth != width))))))) : ((ListenerUtil.mutListener.listen(11632) ? (mHeight >= height) : (ListenerUtil.mutListener.listen(11631) ? (mHeight <= height) : (ListenerUtil.mutListener.listen(11630) ? (mHeight > height) : (ListenerUtil.mutListener.listen(11629) ? (mHeight < height) : (ListenerUtil.mutListener.listen(11628) ? (mHeight == height) : (mHeight != height)))))) || (ListenerUtil.mutListener.listen(11637) ? (mWidth >= width) : (ListenerUtil.mutListener.listen(11636) ? (mWidth <= width) : (ListenerUtil.mutListener.listen(11635) ? (mWidth > width) : (ListenerUtil.mutListener.listen(11634) ? (mWidth < width) : (ListenerUtil.mutListener.listen(11633) ? (mWidth == width) : (mWidth != width)))))))) || mType != (((Statistics) getActivity()).getTaskHandler()).getStatType())) && mDeckId != ((Statistics) getActivity()).getDeckId()) : ((ListenerUtil.mutListener.listen(11639) ? ((ListenerUtil.mutListener.listen(11638) ? ((ListenerUtil.mutListener.listen(11632) ? (mHeight >= height) : (ListenerUtil.mutListener.listen(11631) ? (mHeight <= height) : (ListenerUtil.mutListener.listen(11630) ? (mHeight > height) : (ListenerUtil.mutListener.listen(11629) ? (mHeight < height) : (ListenerUtil.mutListener.listen(11628) ? (mHeight == height) : (mHeight != height)))))) && (ListenerUtil.mutListener.listen(11637) ? (mWidth >= width) : (ListenerUtil.mutListener.listen(11636) ? (mWidth <= width) : (ListenerUtil.mutListener.listen(11635) ? (mWidth > width) : (ListenerUtil.mutListener.listen(11634) ? (mWidth < width) : (ListenerUtil.mutListener.listen(11633) ? (mWidth == width) : (mWidth != width))))))) : ((ListenerUtil.mutListener.listen(11632) ? (mHeight >= height) : (ListenerUtil.mutListener.listen(11631) ? (mHeight <= height) : (ListenerUtil.mutListener.listen(11630) ? (mHeight > height) : (ListenerUtil.mutListener.listen(11629) ? (mHeight < height) : (ListenerUtil.mutListener.listen(11628) ? (mHeight == height) : (mHeight != height)))))) || (ListenerUtil.mutListener.listen(11637) ? (mWidth >= width) : (ListenerUtil.mutListener.listen(11636) ? (mWidth <= width) : (ListenerUtil.mutListener.listen(11635) ? (mWidth > width) : (ListenerUtil.mutListener.listen(11634) ? (mWidth < width) : (ListenerUtil.mutListener.listen(11633) ? (mWidth == width) : (mWidth != width)))))))) && mType != (((Statistics) getActivity()).getTaskHandler()).getStatType()) : ((ListenerUtil.mutListener.listen(11638) ? ((ListenerUtil.mutListener.listen(11632) ? (mHeight >= height) : (ListenerUtil.mutListener.listen(11631) ? (mHeight <= height) : (ListenerUtil.mutListener.listen(11630) ? (mHeight > height) : (ListenerUtil.mutListener.listen(11629) ? (mHeight < height) : (ListenerUtil.mutListener.listen(11628) ? (mHeight == height) : (mHeight != height)))))) && (ListenerUtil.mutListener.listen(11637) ? (mWidth >= width) : (ListenerUtil.mutListener.listen(11636) ? (mWidth <= width) : (ListenerUtil.mutListener.listen(11635) ? (mWidth > width) : (ListenerUtil.mutListener.listen(11634) ? (mWidth < width) : (ListenerUtil.mutListener.listen(11633) ? (mWidth == width) : (mWidth != width))))))) : ((ListenerUtil.mutListener.listen(11632) ? (mHeight >= height) : (ListenerUtil.mutListener.listen(11631) ? (mHeight <= height) : (ListenerUtil.mutListener.listen(11630) ? (mHeight > height) : (ListenerUtil.mutListener.listen(11629) ? (mHeight < height) : (ListenerUtil.mutListener.listen(11628) ? (mHeight == height) : (mHeight != height)))))) || (ListenerUtil.mutListener.listen(11637) ? (mWidth >= width) : (ListenerUtil.mutListener.listen(11636) ? (mWidth <= width) : (ListenerUtil.mutListener.listen(11635) ? (mWidth > width) : (ListenerUtil.mutListener.listen(11634) ? (mWidth < width) : (ListenerUtil.mutListener.listen(11633) ? (mWidth == width) : (mWidth != width)))))))) || mType != (((Statistics) getActivity()).getTaskHandler()).getStatType())) || mDeckId != ((Statistics) getActivity()).getDeckId()))) {
                            if (!ListenerUtil.mutListener.listen(11641)) {
                                mHeight = height;
                            }
                            if (!ListenerUtil.mutListener.listen(11642)) {
                                mWidth = width;
                            }
                            if (!ListenerUtil.mutListener.listen(11643)) {
                                mType = (((Statistics) getActivity()).getTaskHandler()).getStatType();
                            }
                            if (!ListenerUtil.mutListener.listen(11644)) {
                                mProgressBar.setVisibility(View.VISIBLE);
                            }
                            if (!ListenerUtil.mutListener.listen(11645)) {
                                mChart.setVisibility(View.GONE);
                            }
                            if (!ListenerUtil.mutListener.listen(11646)) {
                                mDeckId = ((Statistics) getActivity()).getDeckId();
                            }
                            if (!ListenerUtil.mutListener.listen(11649)) {
                                if ((ListenerUtil.mutListener.listen(11647) ? (mCreateChartTask != null || !mCreateChartTask.isCancelled()) : (mCreateChartTask != null && !mCreateChartTask.isCancelled()))) {
                                    if (!ListenerUtil.mutListener.listen(11648)) {
                                        mCreateChartTask.cancel(true);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(11650)) {
                                createChart();
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            if (!ListenerUtil.mutListener.listen(11653)) {
                super.onCreate(savedInstanceState);
            }
        }

        @Override
        public void invalidateView() {
            if (!ListenerUtil.mutListener.listen(11655)) {
                if (mChart != null) {
                    if (!ListenerUtil.mutListener.listen(11654)) {
                        mChart.invalidate();
                    }
                }
            }
        }

        @Override
        public void onDestroy() {
            if (!ListenerUtil.mutListener.listen(11656)) {
                super.onDestroy();
            }
            if (!ListenerUtil.mutListener.listen(11659)) {
                if ((ListenerUtil.mutListener.listen(11657) ? (mCreateChartTask != null || !mCreateChartTask.isCancelled()) : (mCreateChartTask != null && !mCreateChartTask.isCancelled()))) {
                    if (!ListenerUtil.mutListener.listen(11658)) {
                        mCreateChartTask.cancel(true);
                    }
                }
            }
        }
    }

    public static class OverviewStatisticsFragment extends StatisticFragment {

        private WebView mWebView;

        private ProgressBar mProgressBar;

        private Stats.AxisType mType = Stats.AxisType.TYPE_MONTH;

        private boolean mIsCreated = false;

        private AsyncTask mCreateStatisticsOverviewTask;

        public OverviewStatisticsFragment() {
            super();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            if (!ListenerUtil.mutListener.listen(11660)) {
                setHasOptionsMenu(true);
            }
            View rootView = inflater.inflate(R.layout.fragment_anki_stats_overview, container, false);
            AnkiStatsTaskHandler handler = (((Statistics) getActivity()).getTaskHandler());
            if (!ListenerUtil.mutListener.listen(11663)) {
                // TODO: Implementing loader for Collection in Fragment itself would be a better solution.
                if (handler == null) {
                    if (!ListenerUtil.mutListener.listen(11661)) {
                        Timber.e("Statistics.OverviewStatisticsFragment.onCreateView() TaskHandler not found");
                    }
                    if (!ListenerUtil.mutListener.listen(11662)) {
                        getActivity().finish();
                    }
                    return rootView;
                }
            }
            if (!ListenerUtil.mutListener.listen(11664)) {
                mWebView = rootView.findViewById(R.id.web_view_stats);
            }
            if (!ListenerUtil.mutListener.listen(11668)) {
                if (mWebView == null) {
                    if (!ListenerUtil.mutListener.listen(11667)) {
                        Timber.d("mChart null!");
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(11665)) {
                        Timber.d("mChart is not null!");
                    }
                    if (!ListenerUtil.mutListener.listen(11666)) {
                        // Set transparent color to prevent flashing white when night mode enabled
                        mWebView.setBackgroundColor(Color.argb(1, 0, 0, 0));
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(11669)) {
                mProgressBar = rootView.findViewById(R.id.progress_bar_stats_overview);
            }
            if (!ListenerUtil.mutListener.listen(11670)) {
                mProgressBar.setVisibility(View.VISIBLE);
            }
            if (!ListenerUtil.mutListener.listen(11671)) {
                // mChart.setVisibility(View.GONE);
                createStatisticOverview();
            }
            if (!ListenerUtil.mutListener.listen(11672)) {
                mType = handler.getStatType();
            }
            if (!ListenerUtil.mutListener.listen(11673)) {
                mIsCreated = true;
            }
            if (!ListenerUtil.mutListener.listen(11674)) {
                mActivityPager = ((Statistics) getActivity()).getViewPager();
            }
            if (!ListenerUtil.mutListener.listen(11675)) {
                mActivitySectionPagerAdapter = ((Statistics) getActivity()).getSectionsPagerAdapter();
            }
            Collection col = CollectionHelper.getInstance().getCol(getActivity());
            if (!ListenerUtil.mutListener.listen(11676)) {
                mDeckId = ((Statistics) getActivity()).getDeckId();
            }
            if (!ListenerUtil.mutListener.listen(11688)) {
                if ((ListenerUtil.mutListener.listen(11681) ? (mDeckId >= Stats.ALL_DECKS_ID) : (ListenerUtil.mutListener.listen(11680) ? (mDeckId <= Stats.ALL_DECKS_ID) : (ListenerUtil.mutListener.listen(11679) ? (mDeckId > Stats.ALL_DECKS_ID) : (ListenerUtil.mutListener.listen(11678) ? (mDeckId < Stats.ALL_DECKS_ID) : (ListenerUtil.mutListener.listen(11677) ? (mDeckId == Stats.ALL_DECKS_ID) : (mDeckId != Stats.ALL_DECKS_ID))))))) {
                    String basename = Decks.basename(col.getDecks().current().getString("name"));
                    if (!ListenerUtil.mutListener.listen(11687)) {
                        if (sIsSubtitle) {
                            if (!ListenerUtil.mutListener.listen(11686)) {
                                ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle(basename);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(11685)) {
                                getActivity().setTitle(basename);
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(11684)) {
                        if (sIsSubtitle) {
                            if (!ListenerUtil.mutListener.listen(11683)) {
                                ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle(R.string.stats_deck_collection);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(11682)) {
                                getActivity().setTitle(R.string.stats_deck_collection);
                            }
                        }
                    }
                }
            }
            return rootView;
        }

        private void createStatisticOverview() {
            AnkiStatsTaskHandler handler = (((Statistics) getActivity()).getTaskHandler());
            if (!ListenerUtil.mutListener.listen(11689)) {
                mCreateStatisticsOverviewTask = handler.createStatisticsOverview(mWebView, mProgressBar);
            }
        }

        @Override
        public void invalidateView() {
            if (!ListenerUtil.mutListener.listen(11691)) {
                if (mWebView != null) {
                    if (!ListenerUtil.mutListener.listen(11690)) {
                        mWebView.invalidate();
                    }
                }
            }
        }

        @Override
        public void checkAndUpdate() {
            if (!ListenerUtil.mutListener.listen(11692)) {
                if (!mIsCreated) {
                    return;
                }
            }
            Collection col = CollectionHelper.getInstance().getCol(getActivity());
            if (!ListenerUtil.mutListener.listen(11702)) {
                if ((ListenerUtil.mutListener.listen(11693) ? (mType != (((Statistics) getActivity()).getTaskHandler()).getStatType() && mDeckId != ((Statistics) getActivity()).getDeckId()) : (mType != (((Statistics) getActivity()).getTaskHandler()).getStatType() || mDeckId != ((Statistics) getActivity()).getDeckId()))) {
                    if (!ListenerUtil.mutListener.listen(11694)) {
                        mType = (((Statistics) getActivity()).getTaskHandler()).getStatType();
                    }
                    if (!ListenerUtil.mutListener.listen(11695)) {
                        mProgressBar.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(11696)) {
                        mWebView.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(11697)) {
                        mDeckId = ((Statistics) getActivity()).getDeckId();
                    }
                    if (!ListenerUtil.mutListener.listen(11700)) {
                        if ((ListenerUtil.mutListener.listen(11698) ? (mCreateStatisticsOverviewTask != null || !mCreateStatisticsOverviewTask.isCancelled()) : (mCreateStatisticsOverviewTask != null && !mCreateStatisticsOverviewTask.isCancelled()))) {
                            if (!ListenerUtil.mutListener.listen(11699)) {
                                mCreateStatisticsOverviewTask.cancel(true);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(11701)) {
                        createStatisticOverview();
                    }
                }
            }
        }

        @Override
        public void onDestroy() {
            if (!ListenerUtil.mutListener.listen(11703)) {
                super.onDestroy();
            }
            if (!ListenerUtil.mutListener.listen(11706)) {
                if ((ListenerUtil.mutListener.listen(11704) ? (mCreateStatisticsOverviewTask != null || !mCreateStatisticsOverviewTask.isCancelled()) : (mCreateStatisticsOverviewTask != null && !mCreateStatisticsOverviewTask.isCancelled()))) {
                    if (!ListenerUtil.mutListener.listen(11705)) {
                        mCreateStatisticsOverviewTask.cancel(true);
                    }
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(11713)) {
            if (isDrawerOpen()) {
                if (!ListenerUtil.mutListener.listen(11712)) {
                    super.onBackPressed();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11707)) {
                    Timber.i("Back key pressed");
                }
                Intent data = new Intent();
                if (!ListenerUtil.mutListener.listen(11709)) {
                    if (getIntent().hasExtra("selectedDeck")) {
                        if (!ListenerUtil.mutListener.listen(11708)) {
                            data.putExtra("originalDeck", getIntent().getLongExtra("selectedDeck", 0L));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(11710)) {
                    setResult(RESULT_CANCELED, data);
                }
                if (!ListenerUtil.mutListener.listen(11711)) {
                    finishWithAnimation(ActivityTransitionAnimation.Direction.RIGHT);
                }
            }
        }
    }
}
