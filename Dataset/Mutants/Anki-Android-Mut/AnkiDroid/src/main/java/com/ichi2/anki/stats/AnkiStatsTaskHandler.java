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
package com.ichi2.anki.stats;

import android.content.res.Resources;
import android.database.Cursor;
import android.os.AsyncTask;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.ichi2.anki.R;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.stats.Stats;
import com.ichi2.themes.Themes;
import com.wildplot.android.rendering.PlotSheet;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import timber.log.Timber;
import static com.ichi2.libanki.stats.Stats.SECONDS_PER_DAY;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AnkiStatsTaskHandler {

    private static AnkiStatsTaskHandler sInstance;

    private final Collection mCollectionData;

    private float mStandardTextSize = 10f;

    private Stats.AxisType mStatType = Stats.AxisType.TYPE_MONTH;

    private long mDeckId;

    private static final Lock sLock = new ReentrantLock();

    public AnkiStatsTaskHandler(Collection collection) {
        if (!ListenerUtil.mutListener.listen(3238)) {
            sInstance = this;
        }
        mCollectionData = collection;
    }

    public void setDeckId(long deckId) {
        if (!ListenerUtil.mutListener.listen(3239)) {
            mDeckId = deckId;
        }
    }

    public static AnkiStatsTaskHandler getInstance() {
        return sInstance;
    }

    public CreateChartTask createChart(Stats.ChartType chartType, View... views) {
        CreateChartTask createChartTask = new CreateChartTask(chartType);
        if (!ListenerUtil.mutListener.listen(3240)) {
            createChartTask.execute(views);
        }
        return createChartTask;
    }

    public CreateStatisticsOverview createStatisticsOverview(View... views) {
        CreateStatisticsOverview createChartTask = new CreateStatisticsOverview();
        if (!ListenerUtil.mutListener.listen(3241)) {
            createChartTask.execute(views);
        }
        return createChartTask;
    }

    public static DeckPreviewStatistics createReviewSummaryStatistics(Collection col, TextView view) {
        DeckPreviewStatistics deckPreviewStatistics = new DeckPreviewStatistics();
        if (!ListenerUtil.mutListener.listen(3242)) {
            deckPreviewStatistics.execute(col, view);
        }
        return deckPreviewStatistics;
    }

    private class CreateChartTask extends AsyncTask<View, Void, PlotSheet> {

        private ChartView mImageView;

        private ProgressBar mProgressBar;

        private boolean mIsRunning = false;

        private final Stats.ChartType mChartType;

        public CreateChartTask(Stats.ChartType chartType) {
            super();
            if (!ListenerUtil.mutListener.listen(3243)) {
                mIsRunning = true;
            }
            mChartType = chartType;
        }

        @Override
        protected PlotSheet doInBackground(View... params) {
            if (!ListenerUtil.mutListener.listen(3244)) {
                // only necessary on lower APIs because after honeycomb only one thread is used for all asynctasks
                sLock.lock();
            }
            try {
                if (!mIsRunning) {
                    if (!ListenerUtil.mutListener.listen(3247)) {
                        Timber.d("Quitting CreateChartTask (%s) before execution", mChartType.name());
                    }
                    return null;
                } else {
                    if (!ListenerUtil.mutListener.listen(3246)) {
                        Timber.d("Starting CreateChartTask, type: %s", mChartType.name());
                    }
                }
                if (!ListenerUtil.mutListener.listen(3248)) {
                    mImageView = (ChartView) params[0];
                }
                if (!ListenerUtil.mutListener.listen(3249)) {
                    mProgressBar = (ProgressBar) params[1];
                }
                ChartBuilder chartBuilder = new ChartBuilder(mImageView, mCollectionData, mDeckId, mChartType);
                return chartBuilder.renderChart(mStatType);
            } finally {
                if (!ListenerUtil.mutListener.listen(3245)) {
                    sLock.unlock();
                }
            }
        }

        @Override
        protected void onCancelled() {
            if (!ListenerUtil.mutListener.listen(3250)) {
                mIsRunning = false;
            }
        }

        @Override
        protected void onPostExecute(PlotSheet plotSheet) {
            if (!ListenerUtil.mutListener.listen(3256)) {
                if ((ListenerUtil.mutListener.listen(3251) ? (plotSheet != null || mIsRunning) : (plotSheet != null && mIsRunning))) {
                    if (!ListenerUtil.mutListener.listen(3252)) {
                        mImageView.setData(plotSheet);
                    }
                    if (!ListenerUtil.mutListener.listen(3253)) {
                        mProgressBar.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(3254)) {
                        mImageView.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(3255)) {
                        mImageView.invalidate();
                    }
                }
            }
        }
    }

    private class CreateStatisticsOverview extends AsyncTask<View, Void, String> {

        private WebView mWebView;

        private ProgressBar mProgressBar;

        private boolean mIsRunning = false;

        public CreateStatisticsOverview() {
            super();
            if (!ListenerUtil.mutListener.listen(3257)) {
                mIsRunning = true;
            }
        }

        @Override
        protected String doInBackground(View... params) {
            if (!ListenerUtil.mutListener.listen(3258)) {
                // only necessary on lower APIs because after honeycomb only one thread is used for all asynctasks
                sLock.lock();
            }
            try {
                if (!mIsRunning) {
                    if (!ListenerUtil.mutListener.listen(3261)) {
                        Timber.d("Quitting CreateStatisticsOverview before execution");
                    }
                    return null;
                } else {
                    if (!ListenerUtil.mutListener.listen(3260)) {
                        Timber.d("Starting CreateStatisticsOverview");
                    }
                }
                if (!ListenerUtil.mutListener.listen(3262)) {
                    mWebView = (WebView) params[0];
                }
                if (!ListenerUtil.mutListener.listen(3263)) {
                    mProgressBar = (ProgressBar) params[1];
                }
                OverviewStatsBuilder overviewStatsBuilder = new OverviewStatsBuilder(mWebView, mCollectionData, mDeckId, mStatType);
                return overviewStatsBuilder.createInfoHtmlString();
            } finally {
                if (!ListenerUtil.mutListener.listen(3259)) {
                    sLock.unlock();
                }
            }
        }

        @Override
        protected void onCancelled() {
            if (!ListenerUtil.mutListener.listen(3264)) {
                mIsRunning = false;
            }
        }

        @Override
        protected void onPostExecute(String html) {
            if (!ListenerUtil.mutListener.listen(3272)) {
                if ((ListenerUtil.mutListener.listen(3265) ? (html != null || mIsRunning) : (html != null && mIsRunning))) {
                    try {
                        if (!ListenerUtil.mutListener.listen(3267)) {
                            mWebView.loadData(URLEncoder.encode(html, "UTF-8").replaceAll("\\+", " "), "text/html; charset=utf-8", "utf-8");
                        }
                    } catch (UnsupportedEncodingException e) {
                        if (!ListenerUtil.mutListener.listen(3266)) {
                            e.printStackTrace();
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3268)) {
                        mProgressBar.setVisibility(View.GONE);
                    }
                    int backgroundColor = Themes.getColorFromAttr(mWebView.getContext(), android.R.attr.colorBackground);
                    if (!ListenerUtil.mutListener.listen(3269)) {
                        mWebView.setBackgroundColor(backgroundColor);
                    }
                    if (!ListenerUtil.mutListener.listen(3270)) {
                        mWebView.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(3271)) {
                        mWebView.invalidate();
                    }
                }
            }
        }
    }

    private static class DeckPreviewStatistics extends AsyncTask<Object, Void, String> {

        private TextView mTextView;

        private boolean mIsRunning = false;

        public DeckPreviewStatistics() {
            super();
            if (!ListenerUtil.mutListener.listen(3273)) {
                mIsRunning = true;
            }
        }

        @Override
        protected String doInBackground(Object... params) {
            if (!ListenerUtil.mutListener.listen(3274)) {
                // only necessary on lower APIs because after honeycomb only one thread is used for all asynctasks
                sLock.lock();
            }
            try {
                Collection collection = (Collection) params[0];
                if ((ListenerUtil.mutListener.listen(3277) ? ((ListenerUtil.mutListener.listen(3276) ? (!mIsRunning && collection == null) : (!mIsRunning || collection == null)) && collection.getDb() == null) : ((ListenerUtil.mutListener.listen(3276) ? (!mIsRunning && collection == null) : (!mIsRunning || collection == null)) || collection.getDb() == null))) {
                    if (!ListenerUtil.mutListener.listen(3279)) {
                        Timber.d("Quitting DeckPreviewStatistics before execution");
                    }
                    return null;
                } else {
                    if (!ListenerUtil.mutListener.listen(3278)) {
                        Timber.d("Starting DeckPreviewStatistics");
                    }
                }
                if (!ListenerUtil.mutListener.listen(3280)) {
                    mTextView = (TextView) params[1];
                }
                // eventually put this in Stats (in desktop it is not though)
                int cards;
                int minutes;
                String query = "select count(), sum(time)/1000 from revlog where id > " + ((ListenerUtil.mutListener.listen(3288) ? (((ListenerUtil.mutListener.listen(3284) ? (collection.getSched().getDayCutoff() % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(3283) ? (collection.getSched().getDayCutoff() / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(3282) ? (collection.getSched().getDayCutoff() * SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(3281) ? (collection.getSched().getDayCutoff() + SECONDS_PER_DAY) : (collection.getSched().getDayCutoff() - SECONDS_PER_DAY)))))) % 1000) : (ListenerUtil.mutListener.listen(3287) ? (((ListenerUtil.mutListener.listen(3284) ? (collection.getSched().getDayCutoff() % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(3283) ? (collection.getSched().getDayCutoff() / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(3282) ? (collection.getSched().getDayCutoff() * SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(3281) ? (collection.getSched().getDayCutoff() + SECONDS_PER_DAY) : (collection.getSched().getDayCutoff() - SECONDS_PER_DAY)))))) / 1000) : (ListenerUtil.mutListener.listen(3286) ? (((ListenerUtil.mutListener.listen(3284) ? (collection.getSched().getDayCutoff() % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(3283) ? (collection.getSched().getDayCutoff() / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(3282) ? (collection.getSched().getDayCutoff() * SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(3281) ? (collection.getSched().getDayCutoff() + SECONDS_PER_DAY) : (collection.getSched().getDayCutoff() - SECONDS_PER_DAY)))))) - 1000) : (ListenerUtil.mutListener.listen(3285) ? (((ListenerUtil.mutListener.listen(3284) ? (collection.getSched().getDayCutoff() % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(3283) ? (collection.getSched().getDayCutoff() / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(3282) ? (collection.getSched().getDayCutoff() * SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(3281) ? (collection.getSched().getDayCutoff() + SECONDS_PER_DAY) : (collection.getSched().getDayCutoff() - SECONDS_PER_DAY)))))) + 1000) : (((ListenerUtil.mutListener.listen(3284) ? (collection.getSched().getDayCutoff() % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(3283) ? (collection.getSched().getDayCutoff() / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(3282) ? (collection.getSched().getDayCutoff() * SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(3281) ? (collection.getSched().getDayCutoff() + SECONDS_PER_DAY) : (collection.getSched().getDayCutoff() - SECONDS_PER_DAY)))))) * 1000))))));
                if (!ListenerUtil.mutListener.listen(3289)) {
                    Timber.d("DeckPreviewStatistics query: %s", query);
                }
                try (Cursor cur = collection.getDb().query(query)) {
                    if (!ListenerUtil.mutListener.listen(3290)) {
                        cur.moveToFirst();
                    }
                    cards = cur.getInt(0);
                    minutes = (int) Math.round((ListenerUtil.mutListener.listen(3294) ? (cur.getInt(1) % 60.0) : (ListenerUtil.mutListener.listen(3293) ? (cur.getInt(1) * 60.0) : (ListenerUtil.mutListener.listen(3292) ? (cur.getInt(1) - 60.0) : (ListenerUtil.mutListener.listen(3291) ? (cur.getInt(1) + 60.0) : (cur.getInt(1) / 60.0))))));
                }
                Resources res = mTextView.getResources();
                final String span = res.getQuantityString(R.plurals.in_minutes, minutes, minutes);
                return res.getQuantityString(R.plurals.studied_cards_today, cards, cards, span);
            } finally {
                if (!ListenerUtil.mutListener.listen(3275)) {
                    sLock.unlock();
                }
            }
        }

        @Override
        protected void onCancelled() {
            if (!ListenerUtil.mutListener.listen(3295)) {
                mIsRunning = false;
            }
        }

        @Override
        protected void onPostExecute(String todayStatString) {
            if (!ListenerUtil.mutListener.listen(3300)) {
                if ((ListenerUtil.mutListener.listen(3296) ? (todayStatString != null || mIsRunning) : (todayStatString != null && mIsRunning))) {
                    if (!ListenerUtil.mutListener.listen(3297)) {
                        mTextView.setText(todayStatString);
                    }
                    if (!ListenerUtil.mutListener.listen(3298)) {
                        mTextView.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(3299)) {
                        mTextView.invalidate();
                    }
                }
            }
        }
    }

    public float getmStandardTextSize() {
        return mStandardTextSize;
    }

    public void setmStandardTextSize(float mStandardTextSize) {
        if (!ListenerUtil.mutListener.listen(3301)) {
            this.mStandardTextSize = mStandardTextSize;
        }
    }

    public Stats.AxisType getStatType() {
        return mStatType;
    }

    public void setStatType(Stats.AxisType mStatType) {
        if (!ListenerUtil.mutListener.listen(3302)) {
            this.mStatType = mStatType;
        }
    }
}
