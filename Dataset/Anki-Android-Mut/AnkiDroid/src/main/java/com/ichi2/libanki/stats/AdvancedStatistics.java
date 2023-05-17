/**
 * *************************************************************************************
 * /****************************************************************************************
 *  Copyright (c) 2016 Jeffrey van Prehn <jvanprehn@gmail.com>                           *
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
package com.ichi2.libanki.stats;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import com.ichi2.anki.AnkiDroidApp;
import com.ichi2.anki.CollectionHelper;
import com.ichi2.anki.R;
import com.ichi2.anki.stats.StatsMetaInfo;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.Consts;
import com.ichi2.libanki.DB;
import com.ichi2.libanki.Decks;
import com.ichi2.libanki.DeckConfig;
import com.ichi2.libanki.utils.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;
import androidx.annotation.NonNull;
import androidx.sqlite.db.SupportSQLiteDatabase;
import timber.log.Timber;
import static com.ichi2.libanki.stats.Stats.SECONDS_PER_DAY;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Display forecast statistics based on a simulation of future reviews.
 *
 * Sequence diagram (https://www.websequencediagrams.com/):
 * Stats->+AdvancedStatistics: runFilter
 * AdvancedStatistics->+ReviewSimulator: simNreviews
 * loop dids
 *   loop nIterations
 *       loop cards
 *           ReviewSimulator->+Review: newCard
 *           Review->+NewCardSimulator: simulateNewCard
 *           NewCardSimulator->-Review: tElapsed:int
 *           Review-->-ReviewSimulator: SimulationResult, Review
 *
 *           loop reviews
 *               ReviewSimulator->+Review: simulateReview
 *               Review->+EaseClassifier: simSingleReview
 *               EaseClassifier->+Card:getType
 *               Card-->-EaseClassifier:cardType:int
 *               EaseClassifier-->-Review: ReviewOutcome
 *               Review-->-ReviewSimulator: SimulationResult, Review[]
 *           end
 *        end
 *   end
 * end
 * ReviewSimulator-->-AdvancedStatistics: SimulationResult
 * AdvancedStatistics-->-Stats: StatsMetaInfo
 *
 * %2F%2F Class diagram (http://yuml.me/diagram/scruffy/class/draw; http://yuml.me/edit/e0ad47bf):
 * [AdvancedStatistics]
 * [ReviewSimulator]
 * [StatsMetaInfo|mTitle:int;mType:int;mAxisTitles:int［］;mValueLabels:int［］;mColors:int［］;]
 * [Settings|computeNDays:int;computeMaxError:double;simulateNIterations:int]
 * [Deck|-did:long;newPerDay:int;revPerDay:int]
 * [Card|-id:long;ivl:int;factor:double;lastReview:int;due:int;correct:int|setAll();getType()]
 * [Review|prob:double;tElapsed:int]
 * [SimulationResult|nReviews［CARD_TYPE］［t］;nInState［CARD_TYPE］［t］]
 * [ReviewOutcome|prob:double]
 * [ReviewSimulator]uses -.->[CardIterator]
 * [ReviewSimulator]uses -.->[DeckFactory]
 * [ReviewSimulator]creates -.->[SimulationResult]
 * [ReviewSimulator]creates -.->[Review]
 * [Card]belongs to-.->[Deck]
 * [Review]updates -.->[SimulationResult]
 * [Review]]++-1>[Card]
 * [Review]creates -.->[Review]
 * [AdvancedStatistics]uses -.->[ReviewSimulator]
 * [Review]uses -.->[NewCardSimulator|nAddedToday:int;tAdd:int]
 * [Review]uses -.->[EaseClassifier|probabilities:double［CARD_TYPE］［REVIEW_OUTCOME］]
 * [EaseClassifier]creates -.->[ReviewOutcome]
 * [ReviewOutcome]++-1>[Card]
 * [AdvancedStatistics]creates -.-> [StatsMetaInfo]
 */
@SuppressWarnings({ "PMD.AvoidThrowingRawExceptionTypes", "PMD.ExcessiveClassLength", "PMD.ExcessiveMethodLength", "PMD.AvoidReassigningParameters", "PMD.FieldDeclarationsShouldBeAtStartOfClass", "PMD.SwitchStatementsShouldHaveDefault", "PMD.NPathComplexity", "PMD.OneDeclarationPerLine", "PMD.SwitchStmtsShouldHaveDefault" })
public class AdvancedStatistics {

    private static final int TIME = 0;

    // the time dimension at index 0.
    private static final int CARD_TYPE_COUNT = 3;

    private static final int CARD_TYPE_NEW = 0;

    private static final int CARD_TYPE_YOUNG = 1;

    private static final int CARD_TYPE_MATURE = 2;

    private static final int CARD_TYPE_NEW_PLUS_1 = 1;

    private static final int CARD_TYPE_YOUNG_PLUS_1 = 2;

    private static final int CARD_TYPE_MATURE_PLUS_1 = 3;

    private static final int REVIEW_TYPE_COUNT = 4;

    private static final int REVIEW_TYPE_LEARN = 0;

    private static final int REVIEW_TYPE_YOUNG = 1;

    private static final int REVIEW_TYPE_MATURE = 2;

    private static final int REVIEW_TYPE_RELEARN = 3;

    private static final int REVIEW_TYPE_COUNT_PLUS_1 = 5;

    private static final int REVIEW_TYPE_LEARN_PLUS_1 = 1;

    private static final int REVIEW_TYPE_YOUNG_PLUS_1 = 2;

    private static final int REVIEW_TYPE_MATURE_PLUS_1 = 3;

    private static final int REVIEW_TYPE_RELEARN_PLUS_1 = 4;

    private static final int REVIEW_OUTCOME_REPEAT = 0;

    private static final int REVIEW_OUTCOME_HARD = 1;

    private static final int REVIEW_OUTCOME_GOOD = 2;

    private static final int REVIEW_OUTCOME_EASY = 3;

    private static final int REVIEW_OUTCOME_REPEAT_PLUS_1 = 1;

    private static final int REVIEW_OUTCOME_HARD_PLUS_1 = 2;

    private static final int REVIEW_OUTCOME_GOOD_PLUS_1 = 3;

    private static final int REVIEW_OUTCOME_EASY_PLUS_1 = 4;

    private final ArrayUtils ArrayUtils = new ArrayUtils();

    private final DeckFactory Decks = new DeckFactory();

    private Settings Settings;

    /**
     * Determine forecast statistics based on a computation or simulation of future reviews.
     * Returns all information required by stats.java to plot the 'forecast' chart based on these statistics.
     * The chart will display:
     * - The forecasted number of reviews per review type (relearn, mature, young, learn) as bars
     * - The forecasted number of cards in each state (new, young, mature) as lines
     * @param metaInfo Object which will be filled with all information required by stats.java to plot the 'forecast' chart and returned by this method.
     * @param type Type of 'forecast' chart for which to determine forecast statistics. Accepted values:
     *             Stats.TYPE_MONTH: Determine forecast statistics for next 30 days with 1-day chunks
     *             Stats.TYPE_YEAR:  Determine forecast statistics for next year with 7-day chunks
     *             Stats.TYPE_LIFE:  Determine forecast statistics for next 2 years with 30-day chunks
     * @param context Contains The collection which contains the decks to be simulated.
     *             Also used for access to the database and access to the creation time of the collection.
     *             The creation time of the collection is needed since due times of cards are relative to the creation time of the collection.
     *             So we could pass mCol here.
     * @param dids Deck id's
     * @return @see #metaInfo
     */
    public StatsMetaInfo calculateDueAsMetaInfo(StatsMetaInfo metaInfo, Stats.AxisType type, Context context, String dids) {
        if (!ListenerUtil.mutListener.listen(17353)) {
            if (!AnkiDroidApp.getSharedPrefs(context).getBoolean("advanced_statistics_enabled", false)) {
                return metaInfo;
            }
        }
        if (!ListenerUtil.mutListener.listen(17354)) {
            // To indicate that we calculated the statistics so that Stats.java knows that it shouldn't display the standard Forecast chart.
            Settings = new Settings(context);
        }
        if (!ListenerUtil.mutListener.listen(17355)) {
            metaInfo.setStatsCalculated(true);
        }
        Collection mCol = CollectionHelper.getInstance().getCol(context);
        int mMaxCards = 0;
        double mLastElement = 0;
        int mZeroIndex = 0;
        int[] mValueLabels = { R.string.statistics_relearn, R.string.statistics_mature, R.string.statistics_young, R.string.statistics_learn };
        int[] mColors = { R.attr.stats_relearn, R.attr.stats_mature, R.attr.stats_young, R.attr.stats_learn };
        int[] mAxisTitles = { type.ordinal(), R.string.stats_cards, R.string.stats_cumulative_cards };
        PlottableSimulationResult simuationResult = calculateDueAsPlottableSimulationResult(type, mCol, dids);
        ArrayList<int[]> dues = simuationResult.getNReviews();
        double[][] mSeriesList = new double[REVIEW_TYPE_COUNT_PLUS_1][dues.size()];
        if (!ListenerUtil.mutListener.listen(17423)) {
            {
                long _loopCounter332 = 0;
                for (int t = 0; (ListenerUtil.mutListener.listen(17422) ? (t >= dues.size()) : (ListenerUtil.mutListener.listen(17421) ? (t <= dues.size()) : (ListenerUtil.mutListener.listen(17420) ? (t > dues.size()) : (ListenerUtil.mutListener.listen(17419) ? (t != dues.size()) : (ListenerUtil.mutListener.listen(17418) ? (t == dues.size()) : (t < dues.size())))))); t++) {
                    ListenerUtil.loopListener.listen("_loopCounter332", ++_loopCounter332);
                    int[] data = dues.get(t);
                    int nReviews = (ListenerUtil.mutListener.listen(17367) ? ((ListenerUtil.mutListener.listen(17363) ? ((ListenerUtil.mutListener.listen(17359) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17358) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17357) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17356) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) % data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17362) ? ((ListenerUtil.mutListener.listen(17359) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17358) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17357) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17356) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) / data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17361) ? ((ListenerUtil.mutListener.listen(17359) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17358) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17357) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17356) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) * data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17360) ? ((ListenerUtil.mutListener.listen(17359) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17358) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17357) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17356) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) - data[REVIEW_TYPE_MATURE_PLUS_1]) : ((ListenerUtil.mutListener.listen(17359) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17358) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17357) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17356) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) + data[REVIEW_TYPE_MATURE_PLUS_1]))))) % data[REVIEW_TYPE_RELEARN_PLUS_1]) : (ListenerUtil.mutListener.listen(17366) ? ((ListenerUtil.mutListener.listen(17363) ? ((ListenerUtil.mutListener.listen(17359) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17358) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17357) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17356) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) % data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17362) ? ((ListenerUtil.mutListener.listen(17359) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17358) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17357) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17356) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) / data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17361) ? ((ListenerUtil.mutListener.listen(17359) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17358) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17357) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17356) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) * data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17360) ? ((ListenerUtil.mutListener.listen(17359) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17358) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17357) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17356) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) - data[REVIEW_TYPE_MATURE_PLUS_1]) : ((ListenerUtil.mutListener.listen(17359) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17358) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17357) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17356) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) + data[REVIEW_TYPE_MATURE_PLUS_1]))))) / data[REVIEW_TYPE_RELEARN_PLUS_1]) : (ListenerUtil.mutListener.listen(17365) ? ((ListenerUtil.mutListener.listen(17363) ? ((ListenerUtil.mutListener.listen(17359) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17358) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17357) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17356) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) % data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17362) ? ((ListenerUtil.mutListener.listen(17359) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17358) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17357) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17356) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) / data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17361) ? ((ListenerUtil.mutListener.listen(17359) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17358) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17357) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17356) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) * data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17360) ? ((ListenerUtil.mutListener.listen(17359) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17358) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17357) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17356) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) - data[REVIEW_TYPE_MATURE_PLUS_1]) : ((ListenerUtil.mutListener.listen(17359) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17358) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17357) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17356) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) + data[REVIEW_TYPE_MATURE_PLUS_1]))))) * data[REVIEW_TYPE_RELEARN_PLUS_1]) : (ListenerUtil.mutListener.listen(17364) ? ((ListenerUtil.mutListener.listen(17363) ? ((ListenerUtil.mutListener.listen(17359) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17358) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17357) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17356) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) % data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17362) ? ((ListenerUtil.mutListener.listen(17359) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17358) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17357) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17356) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) / data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17361) ? ((ListenerUtil.mutListener.listen(17359) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17358) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17357) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17356) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) * data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17360) ? ((ListenerUtil.mutListener.listen(17359) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17358) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17357) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17356) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) - data[REVIEW_TYPE_MATURE_PLUS_1]) : ((ListenerUtil.mutListener.listen(17359) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17358) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17357) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17356) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) + data[REVIEW_TYPE_MATURE_PLUS_1]))))) - data[REVIEW_TYPE_RELEARN_PLUS_1]) : ((ListenerUtil.mutListener.listen(17363) ? ((ListenerUtil.mutListener.listen(17359) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17358) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17357) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17356) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) % data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17362) ? ((ListenerUtil.mutListener.listen(17359) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17358) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17357) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17356) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) / data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17361) ? ((ListenerUtil.mutListener.listen(17359) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17358) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17357) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17356) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) * data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17360) ? ((ListenerUtil.mutListener.listen(17359) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17358) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17357) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17356) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) - data[REVIEW_TYPE_MATURE_PLUS_1]) : ((ListenerUtil.mutListener.listen(17359) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17358) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17357) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17356) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) + data[REVIEW_TYPE_MATURE_PLUS_1]))))) + data[REVIEW_TYPE_RELEARN_PLUS_1])))));
                    if (!ListenerUtil.mutListener.listen(17374)) {
                        if ((ListenerUtil.mutListener.listen(17372) ? (nReviews >= mMaxCards) : (ListenerUtil.mutListener.listen(17371) ? (nReviews <= mMaxCards) : (ListenerUtil.mutListener.listen(17370) ? (nReviews < mMaxCards) : (ListenerUtil.mutListener.listen(17369) ? (nReviews != mMaxCards) : (ListenerUtil.mutListener.listen(17368) ? (nReviews == mMaxCards) : (nReviews > mMaxCards)))))))
                            if (!ListenerUtil.mutListener.listen(17373)) {
                                // Y-Axis: Max. value
                                mMaxCards = nReviews;
                            }
                    }
                    if (!ListenerUtil.mutListener.listen(17375)) {
                        // X-Axis: Day / Week / Month
                        mSeriesList[TIME][t] = data[TIME];
                    }
                    if (!ListenerUtil.mutListener.listen(17388)) {
                        mSeriesList[REVIEW_TYPE_LEARN_PLUS_1][t] = (ListenerUtil.mutListener.listen(17387) ? ((ListenerUtil.mutListener.listen(17383) ? ((ListenerUtil.mutListener.listen(17379) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17378) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17377) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17376) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) % data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17382) ? ((ListenerUtil.mutListener.listen(17379) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17378) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17377) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17376) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) / data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17381) ? ((ListenerUtil.mutListener.listen(17379) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17378) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17377) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17376) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) * data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17380) ? ((ListenerUtil.mutListener.listen(17379) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17378) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17377) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17376) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) - data[REVIEW_TYPE_MATURE_PLUS_1]) : ((ListenerUtil.mutListener.listen(17379) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17378) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17377) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17376) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) + data[REVIEW_TYPE_MATURE_PLUS_1]))))) % // Y-Axis: # Cards
                        data[REVIEW_TYPE_RELEARN_PLUS_1]) : (ListenerUtil.mutListener.listen(17386) ? ((ListenerUtil.mutListener.listen(17383) ? ((ListenerUtil.mutListener.listen(17379) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17378) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17377) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17376) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) % data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17382) ? ((ListenerUtil.mutListener.listen(17379) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17378) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17377) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17376) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) / data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17381) ? ((ListenerUtil.mutListener.listen(17379) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17378) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17377) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17376) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) * data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17380) ? ((ListenerUtil.mutListener.listen(17379) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17378) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17377) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17376) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) - data[REVIEW_TYPE_MATURE_PLUS_1]) : ((ListenerUtil.mutListener.listen(17379) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17378) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17377) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17376) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) + data[REVIEW_TYPE_MATURE_PLUS_1]))))) / // Y-Axis: # Cards
                        data[REVIEW_TYPE_RELEARN_PLUS_1]) : (ListenerUtil.mutListener.listen(17385) ? ((ListenerUtil.mutListener.listen(17383) ? ((ListenerUtil.mutListener.listen(17379) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17378) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17377) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17376) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) % data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17382) ? ((ListenerUtil.mutListener.listen(17379) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17378) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17377) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17376) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) / data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17381) ? ((ListenerUtil.mutListener.listen(17379) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17378) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17377) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17376) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) * data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17380) ? ((ListenerUtil.mutListener.listen(17379) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17378) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17377) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17376) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) - data[REVIEW_TYPE_MATURE_PLUS_1]) : ((ListenerUtil.mutListener.listen(17379) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17378) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17377) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17376) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) + data[REVIEW_TYPE_MATURE_PLUS_1]))))) * // Y-Axis: # Cards
                        data[REVIEW_TYPE_RELEARN_PLUS_1]) : (ListenerUtil.mutListener.listen(17384) ? ((ListenerUtil.mutListener.listen(17383) ? ((ListenerUtil.mutListener.listen(17379) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17378) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17377) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17376) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) % data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17382) ? ((ListenerUtil.mutListener.listen(17379) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17378) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17377) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17376) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) / data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17381) ? ((ListenerUtil.mutListener.listen(17379) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17378) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17377) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17376) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) * data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17380) ? ((ListenerUtil.mutListener.listen(17379) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17378) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17377) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17376) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) - data[REVIEW_TYPE_MATURE_PLUS_1]) : ((ListenerUtil.mutListener.listen(17379) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17378) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17377) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17376) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) + data[REVIEW_TYPE_MATURE_PLUS_1]))))) - // Y-Axis: # Cards
                        data[REVIEW_TYPE_RELEARN_PLUS_1]) : ((ListenerUtil.mutListener.listen(17383) ? ((ListenerUtil.mutListener.listen(17379) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17378) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17377) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17376) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) % data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17382) ? ((ListenerUtil.mutListener.listen(17379) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17378) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17377) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17376) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) / data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17381) ? ((ListenerUtil.mutListener.listen(17379) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17378) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17377) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17376) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) * data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17380) ? ((ListenerUtil.mutListener.listen(17379) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17378) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17377) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17376) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) - data[REVIEW_TYPE_MATURE_PLUS_1]) : ((ListenerUtil.mutListener.listen(17379) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17378) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17377) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17376) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) + data[REVIEW_TYPE_MATURE_PLUS_1]))))) + // Y-Axis: # Cards
                        data[REVIEW_TYPE_RELEARN_PLUS_1])))));
                    }
                    if (!ListenerUtil.mutListener.listen(17397)) {
                        mSeriesList[REVIEW_TYPE_YOUNG_PLUS_1][t] = (ListenerUtil.mutListener.listen(17396) ? ((ListenerUtil.mutListener.listen(17392) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17391) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17390) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17389) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) % // Y-Axis: # Mature cards
                        data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17395) ? ((ListenerUtil.mutListener.listen(17392) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17391) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17390) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17389) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) / // Y-Axis: # Mature cards
                        data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17394) ? ((ListenerUtil.mutListener.listen(17392) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17391) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17390) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17389) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) * // Y-Axis: # Mature cards
                        data[REVIEW_TYPE_MATURE_PLUS_1]) : (ListenerUtil.mutListener.listen(17393) ? ((ListenerUtil.mutListener.listen(17392) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17391) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17390) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17389) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) - // Y-Axis: # Mature cards
                        data[REVIEW_TYPE_MATURE_PLUS_1]) : ((ListenerUtil.mutListener.listen(17392) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17391) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17390) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17389) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + data[REVIEW_TYPE_YOUNG_PLUS_1]))))) + // Y-Axis: # Mature cards
                        data[REVIEW_TYPE_MATURE_PLUS_1])))));
                    }
                    if (!ListenerUtil.mutListener.listen(17402)) {
                        mSeriesList[REVIEW_TYPE_MATURE_PLUS_1][t] = (ListenerUtil.mutListener.listen(17401) ? (data[REVIEW_TYPE_LEARN_PLUS_1] % // Y-Axis: # Young
                        data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17400) ? (data[REVIEW_TYPE_LEARN_PLUS_1] / // Y-Axis: # Young
                        data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17399) ? (data[REVIEW_TYPE_LEARN_PLUS_1] * // Y-Axis: # Young
                        data[REVIEW_TYPE_YOUNG_PLUS_1]) : (ListenerUtil.mutListener.listen(17398) ? (data[REVIEW_TYPE_LEARN_PLUS_1] - // Y-Axis: # Young
                        data[REVIEW_TYPE_YOUNG_PLUS_1]) : (data[REVIEW_TYPE_LEARN_PLUS_1] + // Y-Axis: # Young
                        data[REVIEW_TYPE_YOUNG_PLUS_1])))));
                    }
                    if (!ListenerUtil.mutListener.listen(17403)) {
                        // Y-Axis: # Learn
                        mSeriesList[REVIEW_TYPE_RELEARN_PLUS_1][t] = data[REVIEW_TYPE_LEARN_PLUS_1];
                    }
                    if (!ListenerUtil.mutListener.listen(17410)) {
                        if ((ListenerUtil.mutListener.listen(17408) ? (data[TIME] >= mLastElement) : (ListenerUtil.mutListener.listen(17407) ? (data[TIME] <= mLastElement) : (ListenerUtil.mutListener.listen(17406) ? (data[TIME] < mLastElement) : (ListenerUtil.mutListener.listen(17405) ? (data[TIME] != mLastElement) : (ListenerUtil.mutListener.listen(17404) ? (data[TIME] == mLastElement) : (data[TIME] > mLastElement)))))))
                            if (!ListenerUtil.mutListener.listen(17409)) {
                                // X-Axis: Max. value (only for TYPE_LIFE)
                                mLastElement = data[TIME];
                            }
                    }
                    if (!ListenerUtil.mutListener.listen(17417)) {
                        if ((ListenerUtil.mutListener.listen(17415) ? (data[TIME] >= 0) : (ListenerUtil.mutListener.listen(17414) ? (data[TIME] <= 0) : (ListenerUtil.mutListener.listen(17413) ? (data[TIME] > 0) : (ListenerUtil.mutListener.listen(17412) ? (data[TIME] < 0) : (ListenerUtil.mutListener.listen(17411) ? (data[TIME] != 0) : (data[TIME] == 0))))))) {
                            if (!ListenerUtil.mutListener.listen(17416)) {
                                // Because we retrieve dues in the past and we should not cumulate them
                                mZeroIndex = t;
                            }
                        }
                    }
                }
            }
        }
        // # X values
        int mMaxElements = (ListenerUtil.mutListener.listen(17427) ? (dues.size() % 1) : (ListenerUtil.mutListener.listen(17426) ? (dues.size() / 1) : (ListenerUtil.mutListener.listen(17425) ? (dues.size() * 1) : (ListenerUtil.mutListener.listen(17424) ? (dues.size() + 1) : (dues.size() - 1)))));
        if (!ListenerUtil.mutListener.listen(17430)) {
            switch(type) {
                case TYPE_MONTH:
                    if (!ListenerUtil.mutListener.listen(17428)) {
                        // X-Axis: Max. value
                        mLastElement = 31;
                    }
                    break;
                case TYPE_YEAR:
                    if (!ListenerUtil.mutListener.listen(17429)) {
                        // X-Axis: Max. value
                        mLastElement = 52;
                    }
                    break;
                default:
            }
        }
        // X-Axis: Min. value
        double mFirstElement = 0;
        // Day starting at mZeroIndex, Cumulative # cards
        double[][] mCumulative = simuationResult.getNInState();
        double mMcount = (ListenerUtil.mutListener.listen(17450) ? ((ListenerUtil.mutListener.listen(17442) ? (// Y-Axis: Max. cumulative value
        mCumulative[CARD_TYPE_NEW_PLUS_1][(ListenerUtil.mutListener.listen(17434) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17433) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17432) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17431) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_NEW_PLUS_1].length - 1)))))] % mCumulative[CARD_TYPE_YOUNG_PLUS_1][(ListenerUtil.mutListener.listen(17438) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17437) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17436) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17435) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length - 1)))))]) : (ListenerUtil.mutListener.listen(17441) ? (// Y-Axis: Max. cumulative value
        mCumulative[CARD_TYPE_NEW_PLUS_1][(ListenerUtil.mutListener.listen(17434) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17433) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17432) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17431) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_NEW_PLUS_1].length - 1)))))] / mCumulative[CARD_TYPE_YOUNG_PLUS_1][(ListenerUtil.mutListener.listen(17438) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17437) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17436) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17435) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length - 1)))))]) : (ListenerUtil.mutListener.listen(17440) ? (// Y-Axis: Max. cumulative value
        mCumulative[CARD_TYPE_NEW_PLUS_1][(ListenerUtil.mutListener.listen(17434) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17433) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17432) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17431) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_NEW_PLUS_1].length - 1)))))] * mCumulative[CARD_TYPE_YOUNG_PLUS_1][(ListenerUtil.mutListener.listen(17438) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17437) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17436) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17435) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length - 1)))))]) : (ListenerUtil.mutListener.listen(17439) ? (// Y-Axis: Max. cumulative value
        mCumulative[CARD_TYPE_NEW_PLUS_1][(ListenerUtil.mutListener.listen(17434) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17433) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17432) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17431) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_NEW_PLUS_1].length - 1)))))] - mCumulative[CARD_TYPE_YOUNG_PLUS_1][(ListenerUtil.mutListener.listen(17438) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17437) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17436) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17435) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length - 1)))))]) : (// Y-Axis: Max. cumulative value
        mCumulative[CARD_TYPE_NEW_PLUS_1][(ListenerUtil.mutListener.listen(17434) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17433) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17432) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17431) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_NEW_PLUS_1].length - 1)))))] + mCumulative[CARD_TYPE_YOUNG_PLUS_1][(ListenerUtil.mutListener.listen(17438) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17437) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17436) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17435) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length - 1)))))]))))) % mCumulative[CARD_TYPE_MATURE_PLUS_1][(ListenerUtil.mutListener.listen(17446) ? (mCumulative[CARD_TYPE_MATURE_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17445) ? (mCumulative[CARD_TYPE_MATURE_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17444) ? (mCumulative[CARD_TYPE_MATURE_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17443) ? (mCumulative[CARD_TYPE_MATURE_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_MATURE_PLUS_1].length - 1)))))]) : (ListenerUtil.mutListener.listen(17449) ? ((ListenerUtil.mutListener.listen(17442) ? (// Y-Axis: Max. cumulative value
        mCumulative[CARD_TYPE_NEW_PLUS_1][(ListenerUtil.mutListener.listen(17434) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17433) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17432) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17431) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_NEW_PLUS_1].length - 1)))))] % mCumulative[CARD_TYPE_YOUNG_PLUS_1][(ListenerUtil.mutListener.listen(17438) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17437) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17436) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17435) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length - 1)))))]) : (ListenerUtil.mutListener.listen(17441) ? (// Y-Axis: Max. cumulative value
        mCumulative[CARD_TYPE_NEW_PLUS_1][(ListenerUtil.mutListener.listen(17434) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17433) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17432) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17431) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_NEW_PLUS_1].length - 1)))))] / mCumulative[CARD_TYPE_YOUNG_PLUS_1][(ListenerUtil.mutListener.listen(17438) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17437) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17436) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17435) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length - 1)))))]) : (ListenerUtil.mutListener.listen(17440) ? (// Y-Axis: Max. cumulative value
        mCumulative[CARD_TYPE_NEW_PLUS_1][(ListenerUtil.mutListener.listen(17434) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17433) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17432) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17431) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_NEW_PLUS_1].length - 1)))))] * mCumulative[CARD_TYPE_YOUNG_PLUS_1][(ListenerUtil.mutListener.listen(17438) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17437) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17436) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17435) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length - 1)))))]) : (ListenerUtil.mutListener.listen(17439) ? (// Y-Axis: Max. cumulative value
        mCumulative[CARD_TYPE_NEW_PLUS_1][(ListenerUtil.mutListener.listen(17434) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17433) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17432) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17431) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_NEW_PLUS_1].length - 1)))))] - mCumulative[CARD_TYPE_YOUNG_PLUS_1][(ListenerUtil.mutListener.listen(17438) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17437) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17436) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17435) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length - 1)))))]) : (// Y-Axis: Max. cumulative value
        mCumulative[CARD_TYPE_NEW_PLUS_1][(ListenerUtil.mutListener.listen(17434) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17433) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17432) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17431) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_NEW_PLUS_1].length - 1)))))] + mCumulative[CARD_TYPE_YOUNG_PLUS_1][(ListenerUtil.mutListener.listen(17438) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17437) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17436) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17435) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length - 1)))))]))))) / mCumulative[CARD_TYPE_MATURE_PLUS_1][(ListenerUtil.mutListener.listen(17446) ? (mCumulative[CARD_TYPE_MATURE_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17445) ? (mCumulative[CARD_TYPE_MATURE_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17444) ? (mCumulative[CARD_TYPE_MATURE_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17443) ? (mCumulative[CARD_TYPE_MATURE_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_MATURE_PLUS_1].length - 1)))))]) : (ListenerUtil.mutListener.listen(17448) ? ((ListenerUtil.mutListener.listen(17442) ? (// Y-Axis: Max. cumulative value
        mCumulative[CARD_TYPE_NEW_PLUS_1][(ListenerUtil.mutListener.listen(17434) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17433) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17432) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17431) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_NEW_PLUS_1].length - 1)))))] % mCumulative[CARD_TYPE_YOUNG_PLUS_1][(ListenerUtil.mutListener.listen(17438) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17437) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17436) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17435) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length - 1)))))]) : (ListenerUtil.mutListener.listen(17441) ? (// Y-Axis: Max. cumulative value
        mCumulative[CARD_TYPE_NEW_PLUS_1][(ListenerUtil.mutListener.listen(17434) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17433) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17432) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17431) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_NEW_PLUS_1].length - 1)))))] / mCumulative[CARD_TYPE_YOUNG_PLUS_1][(ListenerUtil.mutListener.listen(17438) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17437) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17436) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17435) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length - 1)))))]) : (ListenerUtil.mutListener.listen(17440) ? (// Y-Axis: Max. cumulative value
        mCumulative[CARD_TYPE_NEW_PLUS_1][(ListenerUtil.mutListener.listen(17434) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17433) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17432) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17431) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_NEW_PLUS_1].length - 1)))))] * mCumulative[CARD_TYPE_YOUNG_PLUS_1][(ListenerUtil.mutListener.listen(17438) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17437) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17436) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17435) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length - 1)))))]) : (ListenerUtil.mutListener.listen(17439) ? (// Y-Axis: Max. cumulative value
        mCumulative[CARD_TYPE_NEW_PLUS_1][(ListenerUtil.mutListener.listen(17434) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17433) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17432) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17431) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_NEW_PLUS_1].length - 1)))))] - mCumulative[CARD_TYPE_YOUNG_PLUS_1][(ListenerUtil.mutListener.listen(17438) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17437) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17436) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17435) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length - 1)))))]) : (// Y-Axis: Max. cumulative value
        mCumulative[CARD_TYPE_NEW_PLUS_1][(ListenerUtil.mutListener.listen(17434) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17433) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17432) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17431) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_NEW_PLUS_1].length - 1)))))] + mCumulative[CARD_TYPE_YOUNG_PLUS_1][(ListenerUtil.mutListener.listen(17438) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17437) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17436) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17435) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length - 1)))))]))))) * mCumulative[CARD_TYPE_MATURE_PLUS_1][(ListenerUtil.mutListener.listen(17446) ? (mCumulative[CARD_TYPE_MATURE_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17445) ? (mCumulative[CARD_TYPE_MATURE_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17444) ? (mCumulative[CARD_TYPE_MATURE_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17443) ? (mCumulative[CARD_TYPE_MATURE_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_MATURE_PLUS_1].length - 1)))))]) : (ListenerUtil.mutListener.listen(17447) ? ((ListenerUtil.mutListener.listen(17442) ? (// Y-Axis: Max. cumulative value
        mCumulative[CARD_TYPE_NEW_PLUS_1][(ListenerUtil.mutListener.listen(17434) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17433) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17432) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17431) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_NEW_PLUS_1].length - 1)))))] % mCumulative[CARD_TYPE_YOUNG_PLUS_1][(ListenerUtil.mutListener.listen(17438) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17437) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17436) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17435) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length - 1)))))]) : (ListenerUtil.mutListener.listen(17441) ? (// Y-Axis: Max. cumulative value
        mCumulative[CARD_TYPE_NEW_PLUS_1][(ListenerUtil.mutListener.listen(17434) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17433) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17432) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17431) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_NEW_PLUS_1].length - 1)))))] / mCumulative[CARD_TYPE_YOUNG_PLUS_1][(ListenerUtil.mutListener.listen(17438) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17437) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17436) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17435) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length - 1)))))]) : (ListenerUtil.mutListener.listen(17440) ? (// Y-Axis: Max. cumulative value
        mCumulative[CARD_TYPE_NEW_PLUS_1][(ListenerUtil.mutListener.listen(17434) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17433) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17432) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17431) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_NEW_PLUS_1].length - 1)))))] * mCumulative[CARD_TYPE_YOUNG_PLUS_1][(ListenerUtil.mutListener.listen(17438) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17437) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17436) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17435) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length - 1)))))]) : (ListenerUtil.mutListener.listen(17439) ? (// Y-Axis: Max. cumulative value
        mCumulative[CARD_TYPE_NEW_PLUS_1][(ListenerUtil.mutListener.listen(17434) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17433) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17432) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17431) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_NEW_PLUS_1].length - 1)))))] - mCumulative[CARD_TYPE_YOUNG_PLUS_1][(ListenerUtil.mutListener.listen(17438) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17437) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17436) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17435) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length - 1)))))]) : (// Y-Axis: Max. cumulative value
        mCumulative[CARD_TYPE_NEW_PLUS_1][(ListenerUtil.mutListener.listen(17434) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17433) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17432) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17431) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_NEW_PLUS_1].length - 1)))))] + mCumulative[CARD_TYPE_YOUNG_PLUS_1][(ListenerUtil.mutListener.listen(17438) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17437) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17436) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17435) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length - 1)))))]))))) - mCumulative[CARD_TYPE_MATURE_PLUS_1][(ListenerUtil.mutListener.listen(17446) ? (mCumulative[CARD_TYPE_MATURE_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17445) ? (mCumulative[CARD_TYPE_MATURE_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17444) ? (mCumulative[CARD_TYPE_MATURE_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17443) ? (mCumulative[CARD_TYPE_MATURE_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_MATURE_PLUS_1].length - 1)))))]) : ((ListenerUtil.mutListener.listen(17442) ? (// Y-Axis: Max. cumulative value
        mCumulative[CARD_TYPE_NEW_PLUS_1][(ListenerUtil.mutListener.listen(17434) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17433) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17432) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17431) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_NEW_PLUS_1].length - 1)))))] % mCumulative[CARD_TYPE_YOUNG_PLUS_1][(ListenerUtil.mutListener.listen(17438) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17437) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17436) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17435) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length - 1)))))]) : (ListenerUtil.mutListener.listen(17441) ? (// Y-Axis: Max. cumulative value
        mCumulative[CARD_TYPE_NEW_PLUS_1][(ListenerUtil.mutListener.listen(17434) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17433) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17432) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17431) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_NEW_PLUS_1].length - 1)))))] / mCumulative[CARD_TYPE_YOUNG_PLUS_1][(ListenerUtil.mutListener.listen(17438) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17437) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17436) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17435) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length - 1)))))]) : (ListenerUtil.mutListener.listen(17440) ? (// Y-Axis: Max. cumulative value
        mCumulative[CARD_TYPE_NEW_PLUS_1][(ListenerUtil.mutListener.listen(17434) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17433) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17432) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17431) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_NEW_PLUS_1].length - 1)))))] * mCumulative[CARD_TYPE_YOUNG_PLUS_1][(ListenerUtil.mutListener.listen(17438) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17437) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17436) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17435) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length - 1)))))]) : (ListenerUtil.mutListener.listen(17439) ? (// Y-Axis: Max. cumulative value
        mCumulative[CARD_TYPE_NEW_PLUS_1][(ListenerUtil.mutListener.listen(17434) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17433) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17432) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17431) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_NEW_PLUS_1].length - 1)))))] - mCumulative[CARD_TYPE_YOUNG_PLUS_1][(ListenerUtil.mutListener.listen(17438) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17437) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17436) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17435) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length - 1)))))]) : (// Y-Axis: Max. cumulative value
        mCumulative[CARD_TYPE_NEW_PLUS_1][(ListenerUtil.mutListener.listen(17434) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17433) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17432) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17431) ? (mCumulative[CARD_TYPE_NEW_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_NEW_PLUS_1].length - 1)))))] + mCumulative[CARD_TYPE_YOUNG_PLUS_1][(ListenerUtil.mutListener.listen(17438) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17437) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17436) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17435) ? (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_YOUNG_PLUS_1].length - 1)))))]))))) + mCumulative[CARD_TYPE_MATURE_PLUS_1][(ListenerUtil.mutListener.listen(17446) ? (mCumulative[CARD_TYPE_MATURE_PLUS_1].length % 1) : (ListenerUtil.mutListener.listen(17445) ? (mCumulative[CARD_TYPE_MATURE_PLUS_1].length / 1) : (ListenerUtil.mutListener.listen(17444) ? (mCumulative[CARD_TYPE_MATURE_PLUS_1].length * 1) : (ListenerUtil.mutListener.listen(17443) ? (mCumulative[CARD_TYPE_MATURE_PLUS_1].length + 1) : (mCumulative[CARD_TYPE_MATURE_PLUS_1].length - 1)))))])))));
        if (!ListenerUtil.mutListener.listen(17457)) {
            // some adjustments to not crash the chartbuilding with empty data
            if ((ListenerUtil.mutListener.listen(17455) ? (mMaxElements >= 0) : (ListenerUtil.mutListener.listen(17454) ? (mMaxElements <= 0) : (ListenerUtil.mutListener.listen(17453) ? (mMaxElements > 0) : (ListenerUtil.mutListener.listen(17452) ? (mMaxElements < 0) : (ListenerUtil.mutListener.listen(17451) ? (mMaxElements != 0) : (mMaxElements == 0))))))) {
                if (!ListenerUtil.mutListener.listen(17456)) {
                    mMaxElements = 10;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17464)) {
            if ((ListenerUtil.mutListener.listen(17462) ? (mMcount >= 0) : (ListenerUtil.mutListener.listen(17461) ? (mMcount <= 0) : (ListenerUtil.mutListener.listen(17460) ? (mMcount > 0) : (ListenerUtil.mutListener.listen(17459) ? (mMcount < 0) : (ListenerUtil.mutListener.listen(17458) ? (mMcount != 0) : (mMcount == 0))))))) {
                if (!ListenerUtil.mutListener.listen(17463)) {
                    mMcount = 10;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17472)) {
            if ((ListenerUtil.mutListener.listen(17469) ? (mFirstElement >= mLastElement) : (ListenerUtil.mutListener.listen(17468) ? (mFirstElement <= mLastElement) : (ListenerUtil.mutListener.listen(17467) ? (mFirstElement > mLastElement) : (ListenerUtil.mutListener.listen(17466) ? (mFirstElement < mLastElement) : (ListenerUtil.mutListener.listen(17465) ? (mFirstElement != mLastElement) : (mFirstElement == mLastElement))))))) {
                if (!ListenerUtil.mutListener.listen(17470)) {
                    mFirstElement = 0;
                }
                if (!ListenerUtil.mutListener.listen(17471)) {
                    mLastElement = 6;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17479)) {
            if ((ListenerUtil.mutListener.listen(17477) ? (mMaxCards >= 0) : (ListenerUtil.mutListener.listen(17476) ? (mMaxCards <= 0) : (ListenerUtil.mutListener.listen(17475) ? (mMaxCards > 0) : (ListenerUtil.mutListener.listen(17474) ? (mMaxCards < 0) : (ListenerUtil.mutListener.listen(17473) ? (mMaxCards != 0) : (mMaxCards == 0)))))))
                if (!ListenerUtil.mutListener.listen(17478)) {
                    mMaxCards = 10;
                }
        }
        if (!ListenerUtil.mutListener.listen(17480)) {
            metaInfo.setmDynamicAxis(true);
        }
        if (!ListenerUtil.mutListener.listen(17481)) {
            metaInfo.setmHasColoredCumulative(true);
        }
        if (!ListenerUtil.mutListener.listen(17482)) {
            metaInfo.setmType(type);
        }
        if (!ListenerUtil.mutListener.listen(17483)) {
            metaInfo.setmTitle(R.string.stats_forecast);
        }
        if (!ListenerUtil.mutListener.listen(17484)) {
            metaInfo.setmBackwards(true);
        }
        if (!ListenerUtil.mutListener.listen(17485)) {
            metaInfo.setmValueLabels(mValueLabels);
        }
        if (!ListenerUtil.mutListener.listen(17486)) {
            metaInfo.setmColors(mColors);
        }
        if (!ListenerUtil.mutListener.listen(17487)) {
            metaInfo.setmAxisTitles(mAxisTitles);
        }
        if (!ListenerUtil.mutListener.listen(17488)) {
            metaInfo.setmMaxCards(mMaxCards);
        }
        if (!ListenerUtil.mutListener.listen(17489)) {
            metaInfo.setmMaxElements(mMaxElements);
        }
        if (!ListenerUtil.mutListener.listen(17490)) {
            metaInfo.setmFirstElement(mFirstElement);
        }
        if (!ListenerUtil.mutListener.listen(17491)) {
            metaInfo.setmLastElement(mLastElement);
        }
        if (!ListenerUtil.mutListener.listen(17492)) {
            metaInfo.setmZeroIndex(mZeroIndex);
        }
        if (!ListenerUtil.mutListener.listen(17493)) {
            metaInfo.setmCumulative(mCumulative);
        }
        if (!ListenerUtil.mutListener.listen(17494)) {
            metaInfo.setmMcount(mMcount);
        }
        if (!ListenerUtil.mutListener.listen(17495)) {
            metaInfo.setmSeriesList(mSeriesList);
        }
        if (!ListenerUtil.mutListener.listen(17501)) {
            metaInfo.setDataAvailable((ListenerUtil.mutListener.listen(17500) ? (dues.size() >= 0) : (ListenerUtil.mutListener.listen(17499) ? (dues.size() <= 0) : (ListenerUtil.mutListener.listen(17498) ? (dues.size() < 0) : (ListenerUtil.mutListener.listen(17497) ? (dues.size() != 0) : (ListenerUtil.mutListener.listen(17496) ? (dues.size() == 0) : (dues.size() > 0)))))));
        }
        return metaInfo;
    }

    /**
     * Determine forecast statistics based on a computation or simulation of future reviews and returns the results of the simulation.
     * @param type @see #calculateDueOriginal(StatsMetaInfo, int, Context, String)
     * @param mCol @see #calculateDueOriginal(StatsMetaInfo, int, Context, String)
     * @param dids @see #calculateDueOriginal(StatsMetaInfo, int, Context, String)
     * @return An object containing the results of the simulation:
     *        - The forecasted number of reviews per review type (relearn, mature, young, learn)
     *        - The forecasted number of cards in each state (new, young, mature)
     */
    private PlottableSimulationResult calculateDueAsPlottableSimulationResult(Stats.AxisType type, Collection mCol, String dids) {
        int end = 0;
        int chunk = 0;
        if (!ListenerUtil.mutListener.listen(17508)) {
            switch(type) {
                case TYPE_MONTH:
                    if (!ListenerUtil.mutListener.listen(17502)) {
                        end = 31;
                    }
                    if (!ListenerUtil.mutListener.listen(17503)) {
                        chunk = 1;
                    }
                    break;
                case TYPE_YEAR:
                    if (!ListenerUtil.mutListener.listen(17504)) {
                        end = 52;
                    }
                    if (!ListenerUtil.mutListener.listen(17505)) {
                        chunk = 7;
                    }
                    break;
                case TYPE_LIFE:
                    if (!ListenerUtil.mutListener.listen(17506)) {
                        end = 24;
                    }
                    if (!ListenerUtil.mutListener.listen(17507)) {
                        chunk = 30;
                    }
                    break;
            }
        }
        EaseClassifier classifier = new EaseClassifier(mCol.getTime(), mCol.getDb());
        ReviewSimulator reviewSimulator = new ReviewSimulator(mCol.getDb(), classifier, end, chunk);
        TodayStats todayStats = new TodayStats(mCol, Settings.getDayStartCutoff(mCol.getCrt()));
        long t0 = mCol.getTime().intTimeMS();
        SimulationResult simulationResult = reviewSimulator.simNreviews(Settings.getToday((int) mCol.getCrt()), mCol.getDecks(), dids, todayStats);
        long t1 = mCol.getTime().intTimeMS();
        if (!ListenerUtil.mutListener.listen(17513)) {
            Timber.d("Simulation of all decks took: %d ms", (ListenerUtil.mutListener.listen(17512) ? (t1 % t0) : (ListenerUtil.mutListener.listen(17511) ? (t1 / t0) : (ListenerUtil.mutListener.listen(17510) ? (t1 * t0) : (ListenerUtil.mutListener.listen(17509) ? (t1 + t0) : (t1 - t0))))));
        }
        int[][] nReviews = ArrayUtils.transposeMatrix(simulationResult.getNReviews());
        int[][] nInState = ArrayUtils.transposeMatrix(simulationResult.getNInState());
        ArrayList<int[]> dues = new ArrayList<>((ListenerUtil.mutListener.listen(17517) ? (nReviews.length % 2) : (ListenerUtil.mutListener.listen(17516) ? (nReviews.length / 2) : (ListenerUtil.mutListener.listen(17515) ? (nReviews.length * 2) : (ListenerUtil.mutListener.listen(17514) ? (nReviews.length - 2) : (nReviews.length + 2))))));
        if (!ListenerUtil.mutListener.listen(17524)) {
            {
                long _loopCounter333 = 0;
                // Forecasted number of reviews
                for (int i = 0; (ListenerUtil.mutListener.listen(17523) ? (i >= nReviews.length) : (ListenerUtil.mutListener.listen(17522) ? (i <= nReviews.length) : (ListenerUtil.mutListener.listen(17521) ? (i > nReviews.length) : (ListenerUtil.mutListener.listen(17520) ? (i != nReviews.length) : (ListenerUtil.mutListener.listen(17519) ? (i == nReviews.length) : (i < nReviews.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter333", ++_loopCounter333);
                    if (!ListenerUtil.mutListener.listen(17518)) {
                        dues.add(new int[] { // Time
                        i, nReviews[i][REVIEW_TYPE_LEARN], nReviews[i][REVIEW_TYPE_YOUNG], nReviews[i][REVIEW_TYPE_MATURE], nReviews[i][REVIEW_TYPE_RELEARN] });
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17537)) {
            // small adjustment for a proper chartbuilding
            if ((ListenerUtil.mutListener.listen(17535) ? ((ListenerUtil.mutListener.listen(17529) ? (dues.size() >= 0) : (ListenerUtil.mutListener.listen(17528) ? (dues.size() <= 0) : (ListenerUtil.mutListener.listen(17527) ? (dues.size() > 0) : (ListenerUtil.mutListener.listen(17526) ? (dues.size() < 0) : (ListenerUtil.mutListener.listen(17525) ? (dues.size() != 0) : (dues.size() == 0)))))) && (ListenerUtil.mutListener.listen(17534) ? (dues.get(0)[0] >= 0) : (ListenerUtil.mutListener.listen(17533) ? (dues.get(0)[0] <= 0) : (ListenerUtil.mutListener.listen(17532) ? (dues.get(0)[0] < 0) : (ListenerUtil.mutListener.listen(17531) ? (dues.get(0)[0] != 0) : (ListenerUtil.mutListener.listen(17530) ? (dues.get(0)[0] == 0) : (dues.get(0)[0] > 0))))))) : ((ListenerUtil.mutListener.listen(17529) ? (dues.size() >= 0) : (ListenerUtil.mutListener.listen(17528) ? (dues.size() <= 0) : (ListenerUtil.mutListener.listen(17527) ? (dues.size() > 0) : (ListenerUtil.mutListener.listen(17526) ? (dues.size() < 0) : (ListenerUtil.mutListener.listen(17525) ? (dues.size() != 0) : (dues.size() == 0)))))) || (ListenerUtil.mutListener.listen(17534) ? (dues.get(0)[0] >= 0) : (ListenerUtil.mutListener.listen(17533) ? (dues.get(0)[0] <= 0) : (ListenerUtil.mutListener.listen(17532) ? (dues.get(0)[0] < 0) : (ListenerUtil.mutListener.listen(17531) ? (dues.get(0)[0] != 0) : (ListenerUtil.mutListener.listen(17530) ? (dues.get(0)[0] == 0) : (dues.get(0)[0] > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(17536)) {
                    dues.add(0, new int[] { 0, 0, 0, 0, 0 });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17545)) {
            if ((ListenerUtil.mutListener.listen(17543) ? (type == Stats.AxisType.TYPE_LIFE || (ListenerUtil.mutListener.listen(17542) ? (dues.size() >= 2) : (ListenerUtil.mutListener.listen(17541) ? (dues.size() <= 2) : (ListenerUtil.mutListener.listen(17540) ? (dues.size() > 2) : (ListenerUtil.mutListener.listen(17539) ? (dues.size() != 2) : (ListenerUtil.mutListener.listen(17538) ? (dues.size() == 2) : (dues.size() < 2))))))) : (type == Stats.AxisType.TYPE_LIFE && (ListenerUtil.mutListener.listen(17542) ? (dues.size() >= 2) : (ListenerUtil.mutListener.listen(17541) ? (dues.size() <= 2) : (ListenerUtil.mutListener.listen(17540) ? (dues.size() > 2) : (ListenerUtil.mutListener.listen(17539) ? (dues.size() != 2) : (ListenerUtil.mutListener.listen(17538) ? (dues.size() == 2) : (dues.size() < 2))))))))) {
                if (!ListenerUtil.mutListener.listen(17544)) {
                    end = 31;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17572)) {
            if ((ListenerUtil.mutListener.listen(17555) ? (type != Stats.AxisType.TYPE_LIFE || (ListenerUtil.mutListener.listen(17554) ? (dues.get((ListenerUtil.mutListener.listen(17549) ? (dues.size() % 1) : (ListenerUtil.mutListener.listen(17548) ? (dues.size() / 1) : (ListenerUtil.mutListener.listen(17547) ? (dues.size() * 1) : (ListenerUtil.mutListener.listen(17546) ? (dues.size() + 1) : (dues.size() - 1))))))[0] >= end) : (ListenerUtil.mutListener.listen(17553) ? (dues.get((ListenerUtil.mutListener.listen(17549) ? (dues.size() % 1) : (ListenerUtil.mutListener.listen(17548) ? (dues.size() / 1) : (ListenerUtil.mutListener.listen(17547) ? (dues.size() * 1) : (ListenerUtil.mutListener.listen(17546) ? (dues.size() + 1) : (dues.size() - 1))))))[0] <= end) : (ListenerUtil.mutListener.listen(17552) ? (dues.get((ListenerUtil.mutListener.listen(17549) ? (dues.size() % 1) : (ListenerUtil.mutListener.listen(17548) ? (dues.size() / 1) : (ListenerUtil.mutListener.listen(17547) ? (dues.size() * 1) : (ListenerUtil.mutListener.listen(17546) ? (dues.size() + 1) : (dues.size() - 1))))))[0] > end) : (ListenerUtil.mutListener.listen(17551) ? (dues.get((ListenerUtil.mutListener.listen(17549) ? (dues.size() % 1) : (ListenerUtil.mutListener.listen(17548) ? (dues.size() / 1) : (ListenerUtil.mutListener.listen(17547) ? (dues.size() * 1) : (ListenerUtil.mutListener.listen(17546) ? (dues.size() + 1) : (dues.size() - 1))))))[0] != end) : (ListenerUtil.mutListener.listen(17550) ? (dues.get((ListenerUtil.mutListener.listen(17549) ? (dues.size() % 1) : (ListenerUtil.mutListener.listen(17548) ? (dues.size() / 1) : (ListenerUtil.mutListener.listen(17547) ? (dues.size() * 1) : (ListenerUtil.mutListener.listen(17546) ? (dues.size() + 1) : (dues.size() - 1))))))[0] == end) : (dues.get((ListenerUtil.mutListener.listen(17549) ? (dues.size() % 1) : (ListenerUtil.mutListener.listen(17548) ? (dues.size() / 1) : (ListenerUtil.mutListener.listen(17547) ? (dues.size() * 1) : (ListenerUtil.mutListener.listen(17546) ? (dues.size() + 1) : (dues.size() - 1))))))[0] < end))))))) : (type != Stats.AxisType.TYPE_LIFE && (ListenerUtil.mutListener.listen(17554) ? (dues.get((ListenerUtil.mutListener.listen(17549) ? (dues.size() % 1) : (ListenerUtil.mutListener.listen(17548) ? (dues.size() / 1) : (ListenerUtil.mutListener.listen(17547) ? (dues.size() * 1) : (ListenerUtil.mutListener.listen(17546) ? (dues.size() + 1) : (dues.size() - 1))))))[0] >= end) : (ListenerUtil.mutListener.listen(17553) ? (dues.get((ListenerUtil.mutListener.listen(17549) ? (dues.size() % 1) : (ListenerUtil.mutListener.listen(17548) ? (dues.size() / 1) : (ListenerUtil.mutListener.listen(17547) ? (dues.size() * 1) : (ListenerUtil.mutListener.listen(17546) ? (dues.size() + 1) : (dues.size() - 1))))))[0] <= end) : (ListenerUtil.mutListener.listen(17552) ? (dues.get((ListenerUtil.mutListener.listen(17549) ? (dues.size() % 1) : (ListenerUtil.mutListener.listen(17548) ? (dues.size() / 1) : (ListenerUtil.mutListener.listen(17547) ? (dues.size() * 1) : (ListenerUtil.mutListener.listen(17546) ? (dues.size() + 1) : (dues.size() - 1))))))[0] > end) : (ListenerUtil.mutListener.listen(17551) ? (dues.get((ListenerUtil.mutListener.listen(17549) ? (dues.size() % 1) : (ListenerUtil.mutListener.listen(17548) ? (dues.size() / 1) : (ListenerUtil.mutListener.listen(17547) ? (dues.size() * 1) : (ListenerUtil.mutListener.listen(17546) ? (dues.size() + 1) : (dues.size() - 1))))))[0] != end) : (ListenerUtil.mutListener.listen(17550) ? (dues.get((ListenerUtil.mutListener.listen(17549) ? (dues.size() % 1) : (ListenerUtil.mutListener.listen(17548) ? (dues.size() / 1) : (ListenerUtil.mutListener.listen(17547) ? (dues.size() * 1) : (ListenerUtil.mutListener.listen(17546) ? (dues.size() + 1) : (dues.size() - 1))))))[0] == end) : (dues.get((ListenerUtil.mutListener.listen(17549) ? (dues.size() % 1) : (ListenerUtil.mutListener.listen(17548) ? (dues.size() / 1) : (ListenerUtil.mutListener.listen(17547) ? (dues.size() * 1) : (ListenerUtil.mutListener.listen(17546) ? (dues.size() + 1) : (dues.size() - 1))))))[0] < end))))))))) {
                if (!ListenerUtil.mutListener.listen(17571)) {
                    dues.add(new int[] { end, 0, 0, 0, 0 });
                }
            } else if ((ListenerUtil.mutListener.listen(17561) ? (type == Stats.AxisType.TYPE_LIFE || (ListenerUtil.mutListener.listen(17560) ? (dues.size() >= 2) : (ListenerUtil.mutListener.listen(17559) ? (dues.size() <= 2) : (ListenerUtil.mutListener.listen(17558) ? (dues.size() > 2) : (ListenerUtil.mutListener.listen(17557) ? (dues.size() != 2) : (ListenerUtil.mutListener.listen(17556) ? (dues.size() == 2) : (dues.size() < 2))))))) : (type == Stats.AxisType.TYPE_LIFE && (ListenerUtil.mutListener.listen(17560) ? (dues.size() >= 2) : (ListenerUtil.mutListener.listen(17559) ? (dues.size() <= 2) : (ListenerUtil.mutListener.listen(17558) ? (dues.size() > 2) : (ListenerUtil.mutListener.listen(17557) ? (dues.size() != 2) : (ListenerUtil.mutListener.listen(17556) ? (dues.size() == 2) : (dues.size() < 2))))))))) {
                if (!ListenerUtil.mutListener.listen(17570)) {
                    dues.add(new int[] { Math.max(12, (ListenerUtil.mutListener.listen(17569) ? (dues.get((ListenerUtil.mutListener.listen(17565) ? (dues.size() % 1) : (ListenerUtil.mutListener.listen(17564) ? (dues.size() / 1) : (ListenerUtil.mutListener.listen(17563) ? (dues.size() * 1) : (ListenerUtil.mutListener.listen(17562) ? (dues.size() + 1) : (dues.size() - 1))))))[0] % 1) : (ListenerUtil.mutListener.listen(17568) ? (dues.get((ListenerUtil.mutListener.listen(17565) ? (dues.size() % 1) : (ListenerUtil.mutListener.listen(17564) ? (dues.size() / 1) : (ListenerUtil.mutListener.listen(17563) ? (dues.size() * 1) : (ListenerUtil.mutListener.listen(17562) ? (dues.size() + 1) : (dues.size() - 1))))))[0] / 1) : (ListenerUtil.mutListener.listen(17567) ? (dues.get((ListenerUtil.mutListener.listen(17565) ? (dues.size() % 1) : (ListenerUtil.mutListener.listen(17564) ? (dues.size() / 1) : (ListenerUtil.mutListener.listen(17563) ? (dues.size() * 1) : (ListenerUtil.mutListener.listen(17562) ? (dues.size() + 1) : (dues.size() - 1))))))[0] * 1) : (ListenerUtil.mutListener.listen(17566) ? (dues.get((ListenerUtil.mutListener.listen(17565) ? (dues.size() % 1) : (ListenerUtil.mutListener.listen(17564) ? (dues.size() / 1) : (ListenerUtil.mutListener.listen(17563) ? (dues.size() * 1) : (ListenerUtil.mutListener.listen(17562) ? (dues.size() + 1) : (dues.size() - 1))))))[0] - 1) : (dues.get((ListenerUtil.mutListener.listen(17565) ? (dues.size() % 1) : (ListenerUtil.mutListener.listen(17564) ? (dues.size() / 1) : (ListenerUtil.mutListener.listen(17563) ? (dues.size() * 1) : (ListenerUtil.mutListener.listen(17562) ? (dues.size() + 1) : (dues.size() - 1))))))[0] + 1)))))), 0, 0, 0, 0 });
                }
            }
        }
        double[][] nInStateCum = new double[dues.size()][];
        if (!ListenerUtil.mutListener.listen(17597)) {
            {
                long _loopCounter334 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(17596) ? (i >= dues.size()) : (ListenerUtil.mutListener.listen(17595) ? (i <= dues.size()) : (ListenerUtil.mutListener.listen(17594) ? (i > dues.size()) : (ListenerUtil.mutListener.listen(17593) ? (i != dues.size()) : (ListenerUtil.mutListener.listen(17592) ? (i == dues.size()) : (i < dues.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter334", ++_loopCounter334);
                    if (!ListenerUtil.mutListener.listen(17591)) {
                        if ((ListenerUtil.mutListener.listen(17577) ? (i >= nInState.length) : (ListenerUtil.mutListener.listen(17576) ? (i <= nInState.length) : (ListenerUtil.mutListener.listen(17575) ? (i > nInState.length) : (ListenerUtil.mutListener.listen(17574) ? (i != nInState.length) : (ListenerUtil.mutListener.listen(17573) ? (i == nInState.length) : (i < nInState.length))))))) {
                            if (!ListenerUtil.mutListener.listen(17590)) {
                                nInStateCum[i] = new double[] { i, // Y-Axis: Relearn = 0 (we can't say 'we know x relearn cards on day d')
                                0, // Y-Axis: Mature
                                nInState[i][CARD_TYPE_MATURE], // Y-Axis: Young
                                nInState[i][CARD_TYPE_YOUNG], // Y-Axis: New
                                nInState[i][CARD_TYPE_NEW] };
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(17589)) {
                                if ((ListenerUtil.mutListener.listen(17582) ? (i >= 0) : (ListenerUtil.mutListener.listen(17581) ? (i <= 0) : (ListenerUtil.mutListener.listen(17580) ? (i > 0) : (ListenerUtil.mutListener.listen(17579) ? (i < 0) : (ListenerUtil.mutListener.listen(17578) ? (i != 0) : (i == 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(17588)) {
                                        nInStateCum[i] = new double[] { i, 0, 0, 0, 0 };
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(17587)) {
                                        nInStateCum[i] = nInStateCum[(ListenerUtil.mutListener.listen(17586) ? (i % 1) : (ListenerUtil.mutListener.listen(17585) ? (i / 1) : (ListenerUtil.mutListener.listen(17584) ? (i * 1) : (ListenerUtil.mutListener.listen(17583) ? (i + 1) : (i - 1)))))];
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return new PlottableSimulationResult(dues, ArrayUtils.transposeMatrix(nInStateCum));
    }

    private static class Card {

        private int ivl;

        private double factor;

        private int lastReview;

        private int due;

        private int correct;

        private long id;

        @Override
        @NonNull
        public String toString() {
            return "Card [ivl=" + ivl + ", factor=" + factor + ", due=" + due + ", correct=" + correct + ", id=" + id + "]";
        }

        public Card(long id, int ivl, int factor, int due, int correct, int lastReview) {
            super();
            if (!ListenerUtil.mutListener.listen(17598)) {
                this.id = id;
            }
            if (!ListenerUtil.mutListener.listen(17599)) {
                this.ivl = ivl;
            }
            if (!ListenerUtil.mutListener.listen(17604)) {
                this.factor = (ListenerUtil.mutListener.listen(17603) ? (factor % 1000.0) : (ListenerUtil.mutListener.listen(17602) ? (factor * 1000.0) : (ListenerUtil.mutListener.listen(17601) ? (factor - 1000.0) : (ListenerUtil.mutListener.listen(17600) ? (factor + 1000.0) : (factor / 1000.0)))));
            }
            if (!ListenerUtil.mutListener.listen(17605)) {
                this.due = due;
            }
            if (!ListenerUtil.mutListener.listen(17606)) {
                this.correct = correct;
            }
            if (!ListenerUtil.mutListener.listen(17607)) {
                this.lastReview = lastReview;
            }
        }

        public void setAll(long id, int ivl, int factor, int due, int correct, int lastReview) {
            if (!ListenerUtil.mutListener.listen(17608)) {
                this.id = id;
            }
            if (!ListenerUtil.mutListener.listen(17609)) {
                this.ivl = ivl;
            }
            if (!ListenerUtil.mutListener.listen(17614)) {
                this.factor = (ListenerUtil.mutListener.listen(17613) ? (factor % 1000.0) : (ListenerUtil.mutListener.listen(17612) ? (factor * 1000.0) : (ListenerUtil.mutListener.listen(17611) ? (factor - 1000.0) : (ListenerUtil.mutListener.listen(17610) ? (factor + 1000.0) : (factor / 1000.0)))));
            }
            if (!ListenerUtil.mutListener.listen(17615)) {
                this.due = due;
            }
            if (!ListenerUtil.mutListener.listen(17616)) {
                this.correct = correct;
            }
            if (!ListenerUtil.mutListener.listen(17617)) {
                this.lastReview = lastReview;
            }
        }

        public void setAll(Card card) {
            if (!ListenerUtil.mutListener.listen(17618)) {
                this.id = card.id;
            }
            if (!ListenerUtil.mutListener.listen(17619)) {
                this.ivl = card.ivl;
            }
            if (!ListenerUtil.mutListener.listen(17620)) {
                this.factor = card.factor;
            }
            if (!ListenerUtil.mutListener.listen(17621)) {
                this.due = card.due;
            }
            if (!ListenerUtil.mutListener.listen(17622)) {
                this.correct = card.correct;
            }
            if (!ListenerUtil.mutListener.listen(17623)) {
                this.lastReview = card.lastReview;
            }
        }

        public long getId() {
            return id;
        }

        public int getIvl() {
            return ivl;
        }

        public void setIvl(int ivl) {
            if (!ListenerUtil.mutListener.listen(17624)) {
                this.ivl = ivl;
            }
        }

        public double getFactor() {
            return factor;
        }

        public void setFactor(double factor) {
            if (!ListenerUtil.mutListener.listen(17625)) {
                this.factor = factor;
            }
        }

        public int getDue() {
            return due;
        }

        public void setDue(int due) {
            if (!ListenerUtil.mutListener.listen(17626)) {
                this.due = due;
            }
        }

        /**
         * Type of the card, based on the interval.
         * @return CARD_TYPE_NEW if interval = 0, CARD_TYPE_YOUNG if interval 1-20, CARD_TYPE_MATURE if interval >= 20
         */
        public int getType() {
            if ((ListenerUtil.mutListener.listen(17631) ? (ivl >= 0) : (ListenerUtil.mutListener.listen(17630) ? (ivl <= 0) : (ListenerUtil.mutListener.listen(17629) ? (ivl > 0) : (ListenerUtil.mutListener.listen(17628) ? (ivl < 0) : (ListenerUtil.mutListener.listen(17627) ? (ivl != 0) : (ivl == 0))))))) {
                return CARD_TYPE_NEW;
            } else if ((ListenerUtil.mutListener.listen(17636) ? (ivl <= 21) : (ListenerUtil.mutListener.listen(17635) ? (ivl > 21) : (ListenerUtil.mutListener.listen(17634) ? (ivl < 21) : (ListenerUtil.mutListener.listen(17633) ? (ivl != 21) : (ListenerUtil.mutListener.listen(17632) ? (ivl == 21) : (ivl >= 21))))))) {
                return CARD_TYPE_MATURE;
            } else {
                return CARD_TYPE_YOUNG;
            }
        }

        public int getCorrect() {
            return correct;
        }

        public void setCorrect(int correct) {
            if (!ListenerUtil.mutListener.listen(17637)) {
                this.correct = correct;
            }
        }

        public int getLastReview() {
            return lastReview;
        }

        public void setLastReview(int lastReview) {
            if (!ListenerUtil.mutListener.listen(17638)) {
                this.lastReview = lastReview;
            }
        }
    }

    private class DeckFactory {

        public Deck createDeck(long did, Decks decks) {
            if (!ListenerUtil.mutListener.listen(17639)) {
                Timber.d("Trying to get deck settings for deck with id=" + did);
            }
            DeckConfig conf = decks.confForDid(did);
            int newPerDay = Settings.getMaxNewPerDay();
            int revPerDay = Settings.getMaxReviewsPerDay();
            int initialFactor = Settings.getInitialFactor();
            if (!ListenerUtil.mutListener.listen(17647)) {
                if (conf.getInt("dyn") == Consts.DECK_STD) {
                    if (!ListenerUtil.mutListener.listen(17641)) {
                        revPerDay = conf.getJSONObject("rev").getInt("perDay");
                    }
                    if (!ListenerUtil.mutListener.listen(17642)) {
                        newPerDay = conf.getJSONObject("new").getInt("perDay");
                    }
                    if (!ListenerUtil.mutListener.listen(17643)) {
                        initialFactor = conf.getJSONObject("new").getInt("initialFactor");
                    }
                    if (!ListenerUtil.mutListener.listen(17644)) {
                        Timber.d("rev.perDay=%d", revPerDay);
                    }
                    if (!ListenerUtil.mutListener.listen(17645)) {
                        Timber.d("new.perDay=%d", newPerDay);
                    }
                    if (!ListenerUtil.mutListener.listen(17646)) {
                        Timber.d("new.initialFactor=%d", initialFactor);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(17640)) {
                        Timber.d("dyn=%d", conf.getInt("dyn"));
                    }
                }
            }
            return new Deck(did, newPerDay, revPerDay, initialFactor);
        }
    }

    /**
     * Stores settings that are deck-specific.
     */
    private static class Deck {

        private final long did;

        private final int newPerDay;

        private final int revPerDay;

        private final int initialFactor;

        public Deck(long did, int newPerDay, int revPerDay, int initialFactor) {
            this.did = did;
            this.newPerDay = newPerDay;
            this.revPerDay = revPerDay;
            this.initialFactor = initialFactor;
        }

        public long getDid() {
            return did;
        }

        public int getNewPerDay() {
            return newPerDay;
        }

        public int getRevPerDay() {
            return revPerDay;
        }

        public int getInitialFactor() {
            return initialFactor;
        }
    }

    private static class CardIterator {

        private final Cursor cur;

        private final int today;

        private final Deck deck;

        public CardIterator(DB db, int today, Deck deck) {
            this.today = today;
            this.deck = deck;
            long did = deck.getDid();
            String query = "SELECT id, due, ivl, factor, type, reps " + "FROM cards " + "WHERE did IN (" + did + ") " + "AND queue != " + Consts.QUEUE_TYPE_SUSPENDED + // ignore suspended cards
            " " + "order by id;";
            if (!ListenerUtil.mutListener.listen(17648)) {
                Timber.d("Forecast query: %s", query);
            }
            cur = db.query(query);
        }

        public boolean moveToNext() {
            return cur.moveToNext();
        }

        public void current(Card card) {
            if (!ListenerUtil.mutListener.listen(17658)) {
                // Id
                card.setAll(// Id
                cur.getLong(0), // reps = 0 ? 0 : card interval
                cur.getInt(5) == 0 ? 0 : cur.getInt(2), // factor
                (ListenerUtil.mutListener.listen(17653) ? (cur.getInt(3) >= 0) : (ListenerUtil.mutListener.listen(17652) ? (cur.getInt(3) <= 0) : (ListenerUtil.mutListener.listen(17651) ? (cur.getInt(3) < 0) : (ListenerUtil.mutListener.listen(17650) ? (cur.getInt(3) != 0) : (ListenerUtil.mutListener.listen(17649) ? (cur.getInt(3) == 0) : (cur.getInt(3) > 0)))))) ? cur.getInt(3) : deck.getInitialFactor(), // due
                Math.max((ListenerUtil.mutListener.listen(17657) ? (cur.getInt(1) % today) : (ListenerUtil.mutListener.listen(17656) ? (cur.getInt(1) / today) : (ListenerUtil.mutListener.listen(17655) ? (cur.getInt(1) * today) : (ListenerUtil.mutListener.listen(17654) ? (cur.getInt(1) + today) : (cur.getInt(1) - today))))), 0), // correct
                1, // lastreview
                -1);
            }
        }

        public void close() {
            if (!ListenerUtil.mutListener.listen(17661)) {
                if ((ListenerUtil.mutListener.listen(17659) ? (cur != null || !cur.isClosed()) : (cur != null && !cur.isClosed())))
                    if (!ListenerUtil.mutListener.listen(17660)) {
                        cur.close();
                    }
            }
        }
    }

    /**
     * Based on the current type of the card (@see Card#getType()), determines the interval of the card after review and the probability of the card having that interval after review.
     * This is done using a discrete probability distribution, which is built on construction.
     * For each possible current type of the card, it gives the probability of each possible review outcome (repeat, hard, good, easy).
     * The review outcome determines the next interval of the card.
     *
     * If the review outcome is specified by the caller, the next interval of the card will be determined based on the review outcome
     * and the probability will be fetched from the probability distribution.
     * If the review outcome is not specified by the caller, the review outcome will be sampled randomly from the probability distribution
     * and the probability will be 1.
     */
    private static class EaseClassifier {

        private final Random random;

        private final DB db;

        private double[][] probabilities;

        private double[][] probabilitiesCumulative;

        // half of new cards are answered correctly
        private final int[] priorNew = { 5, 0, 5, 0 };

        // 90% of young cards get "good" response
        private final int[] priorYoung = { 1, 0, 9, 0 };

        // 90% of mature cards get "good" response
        private final int[] priorMature = { 1, 0, 9, 0 };

        // Over decks means more data, but not tuned to deck.
        private static final String queryBaseNew = "select " + "count() as N, " + "sum(case when ease=1 then 1 else 0 end) as repeat, " + // Doesn't occur in query_new
        "0 as hard, " + "sum(case when ease=2 then 1 else 0 end) as good, " + "sum(case when ease=3 then 1 else 0 end) as easy " + "from revlog ";

        private static final String queryBaseYoungMature = "select " + "count() as N, " + "sum(case when ease=1 then 1 else 0 end) as repeat, " + // Doesn't occur in query_new
        "sum(case when ease=2 then 1 else 0 end) as hard, " + "sum(case when ease=3 then 1 else 0 end) as good, " + "sum(case when ease=4 then 1 else 0 end) as easy " + "from revlog ";

        private static final String queryNew = queryBaseNew + "where type=" + CARD_TYPE_NEW + ";";

        private static final String queryYoung = queryBaseYoungMature + "where type=" + Consts.CARD_TYPE_LRN + " and lastIvl < 21;";

        private static final String queryMature = queryBaseYoungMature + "where type=" + Consts.CARD_TYPE_LRN + " and lastIvl >= 21;";

        public EaseClassifier(Time time, DB db) {
            this.db = db;
            singleReviewOutcome = new ReviewOutcome(null, 0);
            long t0 = time.intTimeMS();
            if (!ListenerUtil.mutListener.listen(17662)) {
                calculateCumProbabilitiesForNewEasePerCurrentEase();
            }
            long t1 = time.intTimeMS();
            if (!ListenerUtil.mutListener.listen(17667)) {
                Timber.d("Calculating probability distributions took: %d ms", (ListenerUtil.mutListener.listen(17666) ? (t1 % t0) : (ListenerUtil.mutListener.listen(17665) ? (t1 / t0) : (ListenerUtil.mutListener.listen(17664) ? (t1 * t0) : (ListenerUtil.mutListener.listen(17663) ? (t1 + t0) : (t1 - t0))))));
            }
            if (!ListenerUtil.mutListener.listen(17668)) {
                Timber.d("new\t\t%s", Arrays.toString(this.probabilities[0]));
            }
            if (!ListenerUtil.mutListener.listen(17669)) {
                Timber.d("young\t\t%s", Arrays.toString(this.probabilities[1]));
            }
            if (!ListenerUtil.mutListener.listen(17670)) {
                Timber.d("mature\t%s", Arrays.toString(this.probabilities[2]));
            }
            if (!ListenerUtil.mutListener.listen(17671)) {
                Timber.d("Cumulative new\t\t%s", Arrays.toString(this.probabilitiesCumulative[0]));
            }
            if (!ListenerUtil.mutListener.listen(17672)) {
                Timber.d("Cumulative young\t\t%s", Arrays.toString(this.probabilitiesCumulative[1]));
            }
            if (!ListenerUtil.mutListener.listen(17673)) {
                Timber.d("Cumulative mature\t%s", Arrays.toString(this.probabilitiesCumulative[2]));
            }
            random = new Random();
        }

        private double[] cumsum(double[] p) {
            double[] q = new double[4];
            if (!ListenerUtil.mutListener.listen(17674)) {
                q[0] = p[0];
            }
            if (!ListenerUtil.mutListener.listen(17679)) {
                q[1] = (ListenerUtil.mutListener.listen(17678) ? (q[0] % p[1]) : (ListenerUtil.mutListener.listen(17677) ? (q[0] / p[1]) : (ListenerUtil.mutListener.listen(17676) ? (q[0] * p[1]) : (ListenerUtil.mutListener.listen(17675) ? (q[0] - p[1]) : (q[0] + p[1])))));
            }
            if (!ListenerUtil.mutListener.listen(17684)) {
                q[2] = (ListenerUtil.mutListener.listen(17683) ? (q[1] % p[2]) : (ListenerUtil.mutListener.listen(17682) ? (q[1] / p[2]) : (ListenerUtil.mutListener.listen(17681) ? (q[1] * p[2]) : (ListenerUtil.mutListener.listen(17680) ? (q[1] - p[2]) : (q[1] + p[2])))));
            }
            if (!ListenerUtil.mutListener.listen(17689)) {
                q[3] = (ListenerUtil.mutListener.listen(17688) ? (q[2] % p[3]) : (ListenerUtil.mutListener.listen(17687) ? (q[2] / p[3]) : (ListenerUtil.mutListener.listen(17686) ? (q[2] * p[3]) : (ListenerUtil.mutListener.listen(17685) ? (q[2] - p[3]) : (q[2] + p[3])))));
            }
            return q;
        }

        private void calculateCumProbabilitiesForNewEasePerCurrentEase() {
            if (!ListenerUtil.mutListener.listen(17690)) {
                this.probabilities = new double[3][];
            }
            if (!ListenerUtil.mutListener.listen(17691)) {
                this.probabilitiesCumulative = new double[3][];
            }
            if (!ListenerUtil.mutListener.listen(17692)) {
                this.probabilities[CARD_TYPE_NEW] = calculateProbabilitiesForNewEaseForCurrentEase(queryNew, priorNew);
            }
            if (!ListenerUtil.mutListener.listen(17693)) {
                this.probabilities[CARD_TYPE_YOUNG] = calculateProbabilitiesForNewEaseForCurrentEase(queryYoung, priorYoung);
            }
            if (!ListenerUtil.mutListener.listen(17694)) {
                this.probabilities[CARD_TYPE_MATURE] = calculateProbabilitiesForNewEaseForCurrentEase(queryMature, priorMature);
            }
            if (!ListenerUtil.mutListener.listen(17695)) {
                this.probabilitiesCumulative[CARD_TYPE_NEW] = cumsum(this.probabilities[CARD_TYPE_NEW]);
            }
            if (!ListenerUtil.mutListener.listen(17696)) {
                this.probabilitiesCumulative[CARD_TYPE_YOUNG] = cumsum(this.probabilities[CARD_TYPE_YOUNG]);
            }
            if (!ListenerUtil.mutListener.listen(17697)) {
                this.probabilitiesCumulative[CARD_TYPE_MATURE] = cumsum(this.probabilities[CARD_TYPE_MATURE]);
            }
        }

        /**
         * Given a query which selects the frequency of each review outcome for the current type of the card,
         * and an array containing the prior frequency of each review outcome for the current type of the card,
         * it gives the probability of each possible review outcome (repeat, hard, good, easy).
         * @param queryNewEaseCountForCurrentEase Query which selects the frequency of each review outcome for the current type of the card.
         * @param prior Array containing the prior frequency of each review outcome for the current type of the card.
         * @return The probability of each possible review outcome (repeat, hard, good, easy).
         */
        private double[] calculateProbabilitiesForNewEaseForCurrentEase(String queryNewEaseCountForCurrentEase, int[] prior) {
            int[] freqs = new int[] { prior[REVIEW_OUTCOME_REPEAT], prior[REVIEW_OUTCOME_HARD], prior[REVIEW_OUTCOME_GOOD], prior[REVIEW_OUTCOME_EASY] };
            int n = (ListenerUtil.mutListener.listen(17709) ? ((ListenerUtil.mutListener.listen(17705) ? ((ListenerUtil.mutListener.listen(17701) ? (prior[REVIEW_OUTCOME_REPEAT] % prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17700) ? (prior[REVIEW_OUTCOME_REPEAT] / prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17699) ? (prior[REVIEW_OUTCOME_REPEAT] * prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17698) ? (prior[REVIEW_OUTCOME_REPEAT] - prior[REVIEW_OUTCOME_HARD]) : (prior[REVIEW_OUTCOME_REPEAT] + prior[REVIEW_OUTCOME_HARD]))))) % prior[REVIEW_OUTCOME_GOOD]) : (ListenerUtil.mutListener.listen(17704) ? ((ListenerUtil.mutListener.listen(17701) ? (prior[REVIEW_OUTCOME_REPEAT] % prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17700) ? (prior[REVIEW_OUTCOME_REPEAT] / prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17699) ? (prior[REVIEW_OUTCOME_REPEAT] * prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17698) ? (prior[REVIEW_OUTCOME_REPEAT] - prior[REVIEW_OUTCOME_HARD]) : (prior[REVIEW_OUTCOME_REPEAT] + prior[REVIEW_OUTCOME_HARD]))))) / prior[REVIEW_OUTCOME_GOOD]) : (ListenerUtil.mutListener.listen(17703) ? ((ListenerUtil.mutListener.listen(17701) ? (prior[REVIEW_OUTCOME_REPEAT] % prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17700) ? (prior[REVIEW_OUTCOME_REPEAT] / prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17699) ? (prior[REVIEW_OUTCOME_REPEAT] * prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17698) ? (prior[REVIEW_OUTCOME_REPEAT] - prior[REVIEW_OUTCOME_HARD]) : (prior[REVIEW_OUTCOME_REPEAT] + prior[REVIEW_OUTCOME_HARD]))))) * prior[REVIEW_OUTCOME_GOOD]) : (ListenerUtil.mutListener.listen(17702) ? ((ListenerUtil.mutListener.listen(17701) ? (prior[REVIEW_OUTCOME_REPEAT] % prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17700) ? (prior[REVIEW_OUTCOME_REPEAT] / prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17699) ? (prior[REVIEW_OUTCOME_REPEAT] * prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17698) ? (prior[REVIEW_OUTCOME_REPEAT] - prior[REVIEW_OUTCOME_HARD]) : (prior[REVIEW_OUTCOME_REPEAT] + prior[REVIEW_OUTCOME_HARD]))))) - prior[REVIEW_OUTCOME_GOOD]) : ((ListenerUtil.mutListener.listen(17701) ? (prior[REVIEW_OUTCOME_REPEAT] % prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17700) ? (prior[REVIEW_OUTCOME_REPEAT] / prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17699) ? (prior[REVIEW_OUTCOME_REPEAT] * prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17698) ? (prior[REVIEW_OUTCOME_REPEAT] - prior[REVIEW_OUTCOME_HARD]) : (prior[REVIEW_OUTCOME_REPEAT] + prior[REVIEW_OUTCOME_HARD]))))) + prior[REVIEW_OUTCOME_GOOD]))))) % prior[REVIEW_OUTCOME_EASY]) : (ListenerUtil.mutListener.listen(17708) ? ((ListenerUtil.mutListener.listen(17705) ? ((ListenerUtil.mutListener.listen(17701) ? (prior[REVIEW_OUTCOME_REPEAT] % prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17700) ? (prior[REVIEW_OUTCOME_REPEAT] / prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17699) ? (prior[REVIEW_OUTCOME_REPEAT] * prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17698) ? (prior[REVIEW_OUTCOME_REPEAT] - prior[REVIEW_OUTCOME_HARD]) : (prior[REVIEW_OUTCOME_REPEAT] + prior[REVIEW_OUTCOME_HARD]))))) % prior[REVIEW_OUTCOME_GOOD]) : (ListenerUtil.mutListener.listen(17704) ? ((ListenerUtil.mutListener.listen(17701) ? (prior[REVIEW_OUTCOME_REPEAT] % prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17700) ? (prior[REVIEW_OUTCOME_REPEAT] / prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17699) ? (prior[REVIEW_OUTCOME_REPEAT] * prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17698) ? (prior[REVIEW_OUTCOME_REPEAT] - prior[REVIEW_OUTCOME_HARD]) : (prior[REVIEW_OUTCOME_REPEAT] + prior[REVIEW_OUTCOME_HARD]))))) / prior[REVIEW_OUTCOME_GOOD]) : (ListenerUtil.mutListener.listen(17703) ? ((ListenerUtil.mutListener.listen(17701) ? (prior[REVIEW_OUTCOME_REPEAT] % prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17700) ? (prior[REVIEW_OUTCOME_REPEAT] / prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17699) ? (prior[REVIEW_OUTCOME_REPEAT] * prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17698) ? (prior[REVIEW_OUTCOME_REPEAT] - prior[REVIEW_OUTCOME_HARD]) : (prior[REVIEW_OUTCOME_REPEAT] + prior[REVIEW_OUTCOME_HARD]))))) * prior[REVIEW_OUTCOME_GOOD]) : (ListenerUtil.mutListener.listen(17702) ? ((ListenerUtil.mutListener.listen(17701) ? (prior[REVIEW_OUTCOME_REPEAT] % prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17700) ? (prior[REVIEW_OUTCOME_REPEAT] / prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17699) ? (prior[REVIEW_OUTCOME_REPEAT] * prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17698) ? (prior[REVIEW_OUTCOME_REPEAT] - prior[REVIEW_OUTCOME_HARD]) : (prior[REVIEW_OUTCOME_REPEAT] + prior[REVIEW_OUTCOME_HARD]))))) - prior[REVIEW_OUTCOME_GOOD]) : ((ListenerUtil.mutListener.listen(17701) ? (prior[REVIEW_OUTCOME_REPEAT] % prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17700) ? (prior[REVIEW_OUTCOME_REPEAT] / prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17699) ? (prior[REVIEW_OUTCOME_REPEAT] * prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17698) ? (prior[REVIEW_OUTCOME_REPEAT] - prior[REVIEW_OUTCOME_HARD]) : (prior[REVIEW_OUTCOME_REPEAT] + prior[REVIEW_OUTCOME_HARD]))))) + prior[REVIEW_OUTCOME_GOOD]))))) / prior[REVIEW_OUTCOME_EASY]) : (ListenerUtil.mutListener.listen(17707) ? ((ListenerUtil.mutListener.listen(17705) ? ((ListenerUtil.mutListener.listen(17701) ? (prior[REVIEW_OUTCOME_REPEAT] % prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17700) ? (prior[REVIEW_OUTCOME_REPEAT] / prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17699) ? (prior[REVIEW_OUTCOME_REPEAT] * prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17698) ? (prior[REVIEW_OUTCOME_REPEAT] - prior[REVIEW_OUTCOME_HARD]) : (prior[REVIEW_OUTCOME_REPEAT] + prior[REVIEW_OUTCOME_HARD]))))) % prior[REVIEW_OUTCOME_GOOD]) : (ListenerUtil.mutListener.listen(17704) ? ((ListenerUtil.mutListener.listen(17701) ? (prior[REVIEW_OUTCOME_REPEAT] % prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17700) ? (prior[REVIEW_OUTCOME_REPEAT] / prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17699) ? (prior[REVIEW_OUTCOME_REPEAT] * prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17698) ? (prior[REVIEW_OUTCOME_REPEAT] - prior[REVIEW_OUTCOME_HARD]) : (prior[REVIEW_OUTCOME_REPEAT] + prior[REVIEW_OUTCOME_HARD]))))) / prior[REVIEW_OUTCOME_GOOD]) : (ListenerUtil.mutListener.listen(17703) ? ((ListenerUtil.mutListener.listen(17701) ? (prior[REVIEW_OUTCOME_REPEAT] % prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17700) ? (prior[REVIEW_OUTCOME_REPEAT] / prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17699) ? (prior[REVIEW_OUTCOME_REPEAT] * prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17698) ? (prior[REVIEW_OUTCOME_REPEAT] - prior[REVIEW_OUTCOME_HARD]) : (prior[REVIEW_OUTCOME_REPEAT] + prior[REVIEW_OUTCOME_HARD]))))) * prior[REVIEW_OUTCOME_GOOD]) : (ListenerUtil.mutListener.listen(17702) ? ((ListenerUtil.mutListener.listen(17701) ? (prior[REVIEW_OUTCOME_REPEAT] % prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17700) ? (prior[REVIEW_OUTCOME_REPEAT] / prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17699) ? (prior[REVIEW_OUTCOME_REPEAT] * prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17698) ? (prior[REVIEW_OUTCOME_REPEAT] - prior[REVIEW_OUTCOME_HARD]) : (prior[REVIEW_OUTCOME_REPEAT] + prior[REVIEW_OUTCOME_HARD]))))) - prior[REVIEW_OUTCOME_GOOD]) : ((ListenerUtil.mutListener.listen(17701) ? (prior[REVIEW_OUTCOME_REPEAT] % prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17700) ? (prior[REVIEW_OUTCOME_REPEAT] / prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17699) ? (prior[REVIEW_OUTCOME_REPEAT] * prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17698) ? (prior[REVIEW_OUTCOME_REPEAT] - prior[REVIEW_OUTCOME_HARD]) : (prior[REVIEW_OUTCOME_REPEAT] + prior[REVIEW_OUTCOME_HARD]))))) + prior[REVIEW_OUTCOME_GOOD]))))) * prior[REVIEW_OUTCOME_EASY]) : (ListenerUtil.mutListener.listen(17706) ? ((ListenerUtil.mutListener.listen(17705) ? ((ListenerUtil.mutListener.listen(17701) ? (prior[REVIEW_OUTCOME_REPEAT] % prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17700) ? (prior[REVIEW_OUTCOME_REPEAT] / prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17699) ? (prior[REVIEW_OUTCOME_REPEAT] * prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17698) ? (prior[REVIEW_OUTCOME_REPEAT] - prior[REVIEW_OUTCOME_HARD]) : (prior[REVIEW_OUTCOME_REPEAT] + prior[REVIEW_OUTCOME_HARD]))))) % prior[REVIEW_OUTCOME_GOOD]) : (ListenerUtil.mutListener.listen(17704) ? ((ListenerUtil.mutListener.listen(17701) ? (prior[REVIEW_OUTCOME_REPEAT] % prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17700) ? (prior[REVIEW_OUTCOME_REPEAT] / prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17699) ? (prior[REVIEW_OUTCOME_REPEAT] * prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17698) ? (prior[REVIEW_OUTCOME_REPEAT] - prior[REVIEW_OUTCOME_HARD]) : (prior[REVIEW_OUTCOME_REPEAT] + prior[REVIEW_OUTCOME_HARD]))))) / prior[REVIEW_OUTCOME_GOOD]) : (ListenerUtil.mutListener.listen(17703) ? ((ListenerUtil.mutListener.listen(17701) ? (prior[REVIEW_OUTCOME_REPEAT] % prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17700) ? (prior[REVIEW_OUTCOME_REPEAT] / prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17699) ? (prior[REVIEW_OUTCOME_REPEAT] * prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17698) ? (prior[REVIEW_OUTCOME_REPEAT] - prior[REVIEW_OUTCOME_HARD]) : (prior[REVIEW_OUTCOME_REPEAT] + prior[REVIEW_OUTCOME_HARD]))))) * prior[REVIEW_OUTCOME_GOOD]) : (ListenerUtil.mutListener.listen(17702) ? ((ListenerUtil.mutListener.listen(17701) ? (prior[REVIEW_OUTCOME_REPEAT] % prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17700) ? (prior[REVIEW_OUTCOME_REPEAT] / prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17699) ? (prior[REVIEW_OUTCOME_REPEAT] * prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17698) ? (prior[REVIEW_OUTCOME_REPEAT] - prior[REVIEW_OUTCOME_HARD]) : (prior[REVIEW_OUTCOME_REPEAT] + prior[REVIEW_OUTCOME_HARD]))))) - prior[REVIEW_OUTCOME_GOOD]) : ((ListenerUtil.mutListener.listen(17701) ? (prior[REVIEW_OUTCOME_REPEAT] % prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17700) ? (prior[REVIEW_OUTCOME_REPEAT] / prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17699) ? (prior[REVIEW_OUTCOME_REPEAT] * prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17698) ? (prior[REVIEW_OUTCOME_REPEAT] - prior[REVIEW_OUTCOME_HARD]) : (prior[REVIEW_OUTCOME_REPEAT] + prior[REVIEW_OUTCOME_HARD]))))) + prior[REVIEW_OUTCOME_GOOD]))))) - prior[REVIEW_OUTCOME_EASY]) : ((ListenerUtil.mutListener.listen(17705) ? ((ListenerUtil.mutListener.listen(17701) ? (prior[REVIEW_OUTCOME_REPEAT] % prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17700) ? (prior[REVIEW_OUTCOME_REPEAT] / prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17699) ? (prior[REVIEW_OUTCOME_REPEAT] * prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17698) ? (prior[REVIEW_OUTCOME_REPEAT] - prior[REVIEW_OUTCOME_HARD]) : (prior[REVIEW_OUTCOME_REPEAT] + prior[REVIEW_OUTCOME_HARD]))))) % prior[REVIEW_OUTCOME_GOOD]) : (ListenerUtil.mutListener.listen(17704) ? ((ListenerUtil.mutListener.listen(17701) ? (prior[REVIEW_OUTCOME_REPEAT] % prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17700) ? (prior[REVIEW_OUTCOME_REPEAT] / prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17699) ? (prior[REVIEW_OUTCOME_REPEAT] * prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17698) ? (prior[REVIEW_OUTCOME_REPEAT] - prior[REVIEW_OUTCOME_HARD]) : (prior[REVIEW_OUTCOME_REPEAT] + prior[REVIEW_OUTCOME_HARD]))))) / prior[REVIEW_OUTCOME_GOOD]) : (ListenerUtil.mutListener.listen(17703) ? ((ListenerUtil.mutListener.listen(17701) ? (prior[REVIEW_OUTCOME_REPEAT] % prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17700) ? (prior[REVIEW_OUTCOME_REPEAT] / prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17699) ? (prior[REVIEW_OUTCOME_REPEAT] * prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17698) ? (prior[REVIEW_OUTCOME_REPEAT] - prior[REVIEW_OUTCOME_HARD]) : (prior[REVIEW_OUTCOME_REPEAT] + prior[REVIEW_OUTCOME_HARD]))))) * prior[REVIEW_OUTCOME_GOOD]) : (ListenerUtil.mutListener.listen(17702) ? ((ListenerUtil.mutListener.listen(17701) ? (prior[REVIEW_OUTCOME_REPEAT] % prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17700) ? (prior[REVIEW_OUTCOME_REPEAT] / prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17699) ? (prior[REVIEW_OUTCOME_REPEAT] * prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17698) ? (prior[REVIEW_OUTCOME_REPEAT] - prior[REVIEW_OUTCOME_HARD]) : (prior[REVIEW_OUTCOME_REPEAT] + prior[REVIEW_OUTCOME_HARD]))))) - prior[REVIEW_OUTCOME_GOOD]) : ((ListenerUtil.mutListener.listen(17701) ? (prior[REVIEW_OUTCOME_REPEAT] % prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17700) ? (prior[REVIEW_OUTCOME_REPEAT] / prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17699) ? (prior[REVIEW_OUTCOME_REPEAT] * prior[REVIEW_OUTCOME_HARD]) : (ListenerUtil.mutListener.listen(17698) ? (prior[REVIEW_OUTCOME_REPEAT] - prior[REVIEW_OUTCOME_HARD]) : (prior[REVIEW_OUTCOME_REPEAT] + prior[REVIEW_OUTCOME_HARD]))))) + prior[REVIEW_OUTCOME_GOOD]))))) + prior[REVIEW_OUTCOME_EASY])))));
            try (Cursor cur = db.query(queryNewEaseCountForCurrentEase)) {
                if (!ListenerUtil.mutListener.listen(17710)) {
                    cur.moveToNext();
                }
                if (!ListenerUtil.mutListener.listen(17711)) {
                    // Repeat
                    freqs[REVIEW_OUTCOME_REPEAT] += cur.getInt(REVIEW_OUTCOME_REPEAT_PLUS_1);
                }
                if (!ListenerUtil.mutListener.listen(17712)) {
                    // Hard
                    freqs[REVIEW_OUTCOME_HARD] += cur.getInt(REVIEW_OUTCOME_HARD_PLUS_1);
                }
                if (!ListenerUtil.mutListener.listen(17713)) {
                    // Good
                    freqs[REVIEW_OUTCOME_GOOD] += cur.getInt(REVIEW_OUTCOME_GOOD_PLUS_1);
                }
                if (!ListenerUtil.mutListener.listen(17714)) {
                    // Easy
                    freqs[REVIEW_OUTCOME_EASY] += cur.getInt(REVIEW_OUTCOME_EASY_PLUS_1);
                }
                // N
                int nQuery = cur.getInt(0);
                if (!ListenerUtil.mutListener.listen(17715)) {
                    n += nQuery;
                }
            }
            return new double[] { (ListenerUtil.mutListener.listen(17719) ? (freqs[REVIEW_OUTCOME_REPEAT] % (double) n) : (ListenerUtil.mutListener.listen(17718) ? (freqs[REVIEW_OUTCOME_REPEAT] * (double) n) : (ListenerUtil.mutListener.listen(17717) ? (freqs[REVIEW_OUTCOME_REPEAT] - (double) n) : (ListenerUtil.mutListener.listen(17716) ? (freqs[REVIEW_OUTCOME_REPEAT] + (double) n) : (freqs[REVIEW_OUTCOME_REPEAT] / (double) n))))), (ListenerUtil.mutListener.listen(17723) ? (freqs[REVIEW_OUTCOME_HARD] % (double) n) : (ListenerUtil.mutListener.listen(17722) ? (freqs[REVIEW_OUTCOME_HARD] * (double) n) : (ListenerUtil.mutListener.listen(17721) ? (freqs[REVIEW_OUTCOME_HARD] - (double) n) : (ListenerUtil.mutListener.listen(17720) ? (freqs[REVIEW_OUTCOME_HARD] + (double) n) : (freqs[REVIEW_OUTCOME_HARD] / (double) n))))), (ListenerUtil.mutListener.listen(17727) ? (freqs[REVIEW_OUTCOME_GOOD] % (double) n) : (ListenerUtil.mutListener.listen(17726) ? (freqs[REVIEW_OUTCOME_GOOD] * (double) n) : (ListenerUtil.mutListener.listen(17725) ? (freqs[REVIEW_OUTCOME_GOOD] - (double) n) : (ListenerUtil.mutListener.listen(17724) ? (freqs[REVIEW_OUTCOME_GOOD] + (double) n) : (freqs[REVIEW_OUTCOME_GOOD] / (double) n))))), (ListenerUtil.mutListener.listen(17731) ? (freqs[REVIEW_OUTCOME_EASY] % (double) n) : (ListenerUtil.mutListener.listen(17730) ? (freqs[REVIEW_OUTCOME_EASY] * (double) n) : (ListenerUtil.mutListener.listen(17729) ? (freqs[REVIEW_OUTCOME_EASY] - (double) n) : (ListenerUtil.mutListener.listen(17728) ? (freqs[REVIEW_OUTCOME_EASY] + (double) n) : (freqs[REVIEW_OUTCOME_EASY] / (double) n))))) };
        }

        private int draw(double[] p) {
            return searchsorted(p, random.nextDouble());
        }

        private int searchsorted(double[] p, double random) {
            if (!ListenerUtil.mutListener.listen(17737)) {
                if ((ListenerUtil.mutListener.listen(17736) ? (random >= p[0]) : (ListenerUtil.mutListener.listen(17735) ? (random > p[0]) : (ListenerUtil.mutListener.listen(17734) ? (random < p[0]) : (ListenerUtil.mutListener.listen(17733) ? (random != p[0]) : (ListenerUtil.mutListener.listen(17732) ? (random == p[0]) : (random <= p[0])))))))
                    return 0;
            }
            if (!ListenerUtil.mutListener.listen(17743)) {
                if ((ListenerUtil.mutListener.listen(17742) ? (random >= p[1]) : (ListenerUtil.mutListener.listen(17741) ? (random > p[1]) : (ListenerUtil.mutListener.listen(17740) ? (random < p[1]) : (ListenerUtil.mutListener.listen(17739) ? (random != p[1]) : (ListenerUtil.mutListener.listen(17738) ? (random == p[1]) : (random <= p[1])))))))
                    return 1;
            }
            if (!ListenerUtil.mutListener.listen(17749)) {
                if ((ListenerUtil.mutListener.listen(17748) ? (random >= p[2]) : (ListenerUtil.mutListener.listen(17747) ? (random > p[2]) : (ListenerUtil.mutListener.listen(17746) ? (random < p[2]) : (ListenerUtil.mutListener.listen(17745) ? (random != p[2]) : (ListenerUtil.mutListener.listen(17744) ? (random == p[2]) : (random <= p[2])))))))
                    return 2;
            }
            return 3;
        }

        private final ReviewOutcome singleReviewOutcome;

        public ReviewOutcome simSingleReview(Card c) {
            @Consts.CARD_TYPE
            int type = c.getType();
            int outcome = draw(probabilitiesCumulative[type]);
            if (!ListenerUtil.mutListener.listen(17750)) {
                applyOutcomeToCard(c, outcome);
            }
            if (!ListenerUtil.mutListener.listen(17751)) {
                singleReviewOutcome.setAll(c, 1);
            }
            return singleReviewOutcome;
        }

        public ReviewOutcome simSingleReview(Card c, int outcome) {
            int c_type = c.getType();
            if (!ListenerUtil.mutListener.listen(17752)) {
                // For first review, re-use current card to prevent creating too many objects
                applyOutcomeToCard(c, outcome);
            }
            if (!ListenerUtil.mutListener.listen(17753)) {
                singleReviewOutcome.setAll(c, probabilities[c_type][outcome]);
            }
            return singleReviewOutcome;
        }

        private void applyOutcomeToCard(Card c, int outcome) {
            @Consts.CARD_TYPE
            int type = c.getType();
            int ivl = c.getIvl();
            double factor = c.getFactor();
            if (!ListenerUtil.mutListener.listen(17784)) {
                if ((ListenerUtil.mutListener.listen(17758) ? (type >= CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(17757) ? (type <= CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(17756) ? (type > CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(17755) ? (type < CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(17754) ? (type != CARD_TYPE_NEW) : (type == CARD_TYPE_NEW))))))) {
                    if (!ListenerUtil.mutListener.listen(17783)) {
                        if ((ListenerUtil.mutListener.listen(17780) ? (outcome >= 2) : (ListenerUtil.mutListener.listen(17779) ? (outcome > 2) : (ListenerUtil.mutListener.listen(17778) ? (outcome < 2) : (ListenerUtil.mutListener.listen(17777) ? (outcome != 2) : (ListenerUtil.mutListener.listen(17776) ? (outcome == 2) : (outcome <= 2))))))) {
                            if (!ListenerUtil.mutListener.listen(17782)) {
                                ivl = 1;
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(17781)) {
                                ivl = 4;
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(17775)) {
                        switch(outcome) {
                            case REVIEW_OUTCOME_REPEAT:
                                if (!ListenerUtil.mutListener.listen(17759)) {
                                    ivl = 1;
                                }
                                // factor = Math.max(1300, factor - 200);
                                break;
                            case REVIEW_OUTCOME_HARD:
                                if (!ListenerUtil.mutListener.listen(17760)) {
                                    ivl *= 1.2;
                                }
                                break;
                            case REVIEW_OUTCOME_GOOD:
                                if (!ListenerUtil.mutListener.listen(17765)) {
                                    ivl *= (ListenerUtil.mutListener.listen(17764) ? (1.2 % factor) : (ListenerUtil.mutListener.listen(17763) ? (1.2 / factor) : (ListenerUtil.mutListener.listen(17762) ? (1.2 - factor) : (ListenerUtil.mutListener.listen(17761) ? (1.2 + factor) : (1.2 * factor)))));
                                }
                                break;
                            case REVIEW_OUTCOME_EASY:
                            default:
                                if (!ListenerUtil.mutListener.listen(17774)) {
                                    ivl *= (ListenerUtil.mutListener.listen(17773) ? ((ListenerUtil.mutListener.listen(17769) ? (1.2 % 2.) : (ListenerUtil.mutListener.listen(17768) ? (1.2 / 2.) : (ListenerUtil.mutListener.listen(17767) ? (1.2 - 2.) : (ListenerUtil.mutListener.listen(17766) ? (1.2 + 2.) : (1.2 * 2.))))) % factor) : (ListenerUtil.mutListener.listen(17772) ? ((ListenerUtil.mutListener.listen(17769) ? (1.2 % 2.) : (ListenerUtil.mutListener.listen(17768) ? (1.2 / 2.) : (ListenerUtil.mutListener.listen(17767) ? (1.2 - 2.) : (ListenerUtil.mutListener.listen(17766) ? (1.2 + 2.) : (1.2 * 2.))))) / factor) : (ListenerUtil.mutListener.listen(17771) ? ((ListenerUtil.mutListener.listen(17769) ? (1.2 % 2.) : (ListenerUtil.mutListener.listen(17768) ? (1.2 / 2.) : (ListenerUtil.mutListener.listen(17767) ? (1.2 - 2.) : (ListenerUtil.mutListener.listen(17766) ? (1.2 + 2.) : (1.2 * 2.))))) - factor) : (ListenerUtil.mutListener.listen(17770) ? ((ListenerUtil.mutListener.listen(17769) ? (1.2 % 2.) : (ListenerUtil.mutListener.listen(17768) ? (1.2 / 2.) : (ListenerUtil.mutListener.listen(17767) ? (1.2 - 2.) : (ListenerUtil.mutListener.listen(17766) ? (1.2 + 2.) : (1.2 * 2.))))) + factor) : ((ListenerUtil.mutListener.listen(17769) ? (1.2 % 2.) : (ListenerUtil.mutListener.listen(17768) ? (1.2 / 2.) : (ListenerUtil.mutListener.listen(17767) ? (1.2 - 2.) : (ListenerUtil.mutListener.listen(17766) ? (1.2 + 2.) : (1.2 * 2.))))) * factor)))));
                                }
                                break;
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(17785)) {
                c.setIvl(ivl);
            }
            if (!ListenerUtil.mutListener.listen(17791)) {
                c.setCorrect(((ListenerUtil.mutListener.listen(17790) ? (outcome >= 0) : (ListenerUtil.mutListener.listen(17789) ? (outcome <= 0) : (ListenerUtil.mutListener.listen(17788) ? (outcome < 0) : (ListenerUtil.mutListener.listen(17787) ? (outcome != 0) : (ListenerUtil.mutListener.listen(17786) ? (outcome == 0) : (outcome > 0))))))) ? 1 : 0);
            }
        }
    }

    public static class TodayStats {

        private final Map<Long, Integer> nLearnedPerDeckId;

        public TodayStats(Collection col, long dayStartCutoff) {
            nLearnedPerDeckId = new HashMap<>(col.getDecks().count());
            SupportSQLiteDatabase db = col.getDb().getDatabase();
            String query = "select cards.did, " + "sum(case when revlog.type = " + CARD_TYPE_NEW + " then 1 else 0 end)" + /* learning */
            " from revlog, cards where revlog.cid = cards.id and revlog.id > " + dayStartCutoff + " group by cards.did";
            if (!ListenerUtil.mutListener.listen(17792)) {
                Timber.d("AdvancedStatistics.TodayStats query: %s", query);
            }
            try (Cursor cur = db.query(query)) {
                if (!ListenerUtil.mutListener.listen(17794)) {
                    {
                        long _loopCounter335 = 0;
                        while (cur.moveToNext()) {
                            ListenerUtil.loopListener.listen("_loopCounter335", ++_loopCounter335);
                            if (!ListenerUtil.mutListener.listen(17793)) {
                                nLearnedPerDeckId.put(cur.getLong(0), cur.getInt(1));
                            }
                        }
                    }
                }
            }
        }

        public int getNLearned(long did) {
            if (nLearnedPerDeckId.containsKey(did)) {
                return nLearnedPerDeckId.get(did);
            } else {
                return 0;
            }
        }
    }

    public static class NewCardSimulator {

        private int nAddedToday;

        private int tAdd;

        public NewCardSimulator() {
            if (!ListenerUtil.mutListener.listen(17795)) {
                reset(0);
            }
        }

        public int simulateNewCard(Deck deck) {
            if (!ListenerUtil.mutListener.listen(17796)) {
                nAddedToday++;
            }
            // differs from online
            int tElapsed = tAdd;
            if (!ListenerUtil.mutListener.listen(17804)) {
                if ((ListenerUtil.mutListener.listen(17801) ? (nAddedToday <= deck.getNewPerDay()) : (ListenerUtil.mutListener.listen(17800) ? (nAddedToday > deck.getNewPerDay()) : (ListenerUtil.mutListener.listen(17799) ? (nAddedToday < deck.getNewPerDay()) : (ListenerUtil.mutListener.listen(17798) ? (nAddedToday != deck.getNewPerDay()) : (ListenerUtil.mutListener.listen(17797) ? (nAddedToday == deck.getNewPerDay()) : (nAddedToday >= deck.getNewPerDay()))))))) {
                    if (!ListenerUtil.mutListener.listen(17802)) {
                        tAdd++;
                    }
                    if (!ListenerUtil.mutListener.listen(17803)) {
                        nAddedToday = 0;
                    }
                }
            }
            return tElapsed;
        }

        public void reset(int nAddedToday) {
            if (!ListenerUtil.mutListener.listen(17805)) {
                this.nAddedToday = nAddedToday;
            }
            if (!ListenerUtil.mutListener.listen(17806)) {
                this.tAdd = 0;
            }
        }
    }

    /**
     * Simulates future card reviews, keeping track of statistics and returns those as SimulationResult.
     *
     * A simulation is run for each of the specified decks using the settings (max # cards per day, max # reviews per day, initial factor for new cards) for that deck.
     * Within each deck the simulation consists of one or more simulations of each card within that deck.
     * A simulation of a single card means simulating future card reviews starting from now until the end of the simulation window as specified by nTimeBins and timeBinLength.
     *
     * A review of a single card is run by the specified classifier.
     */
    private class ReviewSimulator {

        private final DB db;

        private final EaseClassifier classifier;

        // TODO: also exists in Review
        private final int nTimeBins;

        private final int timeBinLength;

        private final int tMax;

        private final NewCardSimulator newCardSimulator = new NewCardSimulator();

        public ReviewSimulator(DB db, EaseClassifier classifier, int nTimeBins, int timeBinLength) {
            this.db = db;
            this.classifier = classifier;
            this.nTimeBins = nTimeBins;
            this.timeBinLength = timeBinLength;
            this.tMax = (ListenerUtil.mutListener.listen(17810) ? (this.nTimeBins % this.timeBinLength) : (ListenerUtil.mutListener.listen(17809) ? (this.nTimeBins / this.timeBinLength) : (ListenerUtil.mutListener.listen(17808) ? (this.nTimeBins - this.timeBinLength) : (ListenerUtil.mutListener.listen(17807) ? (this.nTimeBins + this.timeBinLength) : (this.nTimeBins * this.timeBinLength)))));
        }

        public SimulationResult simNreviews(int today, Decks decks, String didsStr, TodayStats todayStats) {
            SimulationResult simulationResultAggregated = new SimulationResult(nTimeBins, timeBinLength, SimulationResult.DOUBLE_TO_INT_MODE_ROUND);
            long[] dids = ArrayUtils.stringToLongArray(didsStr);
            int nIterations = Settings.getSimulateNIterations();
            double nIterationsInv = (ListenerUtil.mutListener.listen(17814) ? (1.0 % nIterations) : (ListenerUtil.mutListener.listen(17813) ? (1.0 * nIterations) : (ListenerUtil.mutListener.listen(17812) ? (1.0 - nIterations) : (ListenerUtil.mutListener.listen(17811) ? (1.0 + nIterations) : (1.0 / nIterations)))));
            if (!ListenerUtil.mutListener.listen(17823)) {
                {
                    long _loopCounter337 = 0;
                    for (long did : dids) {
                        ListenerUtil.loopListener.listen("_loopCounter337", ++_loopCounter337);
                        if (!ListenerUtil.mutListener.listen(17822)) {
                            {
                                long _loopCounter336 = 0;
                                for (int iteration = 0; (ListenerUtil.mutListener.listen(17821) ? (iteration >= nIterations) : (ListenerUtil.mutListener.listen(17820) ? (iteration <= nIterations) : (ListenerUtil.mutListener.listen(17819) ? (iteration > nIterations) : (ListenerUtil.mutListener.listen(17818) ? (iteration != nIterations) : (ListenerUtil.mutListener.listen(17817) ? (iteration == nIterations) : (iteration < nIterations)))))); iteration++) {
                                    ListenerUtil.loopListener.listen("_loopCounter336", ++_loopCounter336);
                                    if (!ListenerUtil.mutListener.listen(17815)) {
                                        newCardSimulator.reset(todayStats.getNLearned(did));
                                    }
                                    if (!ListenerUtil.mutListener.listen(17816)) {
                                        simulationResultAggregated.add(simNreviews(today, Decks.createDeck(did, decks)), nIterationsInv);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return simulationResultAggregated;
        }

        private SimulationResult simNreviews(int today, Deck deck) {
            SimulationResult simulationResult;
            // So if we take the floor when displaying it, we will display the maximum # reviews
            if ((ListenerUtil.mutListener.listen(17828) ? (Settings.getComputeNDays() >= 0) : (ListenerUtil.mutListener.listen(17827) ? (Settings.getComputeNDays() <= 0) : (ListenerUtil.mutListener.listen(17826) ? (Settings.getComputeNDays() < 0) : (ListenerUtil.mutListener.listen(17825) ? (Settings.getComputeNDays() != 0) : (ListenerUtil.mutListener.listen(17824) ? (Settings.getComputeNDays() == 0) : (Settings.getComputeNDays() > 0)))))))
                simulationResult = new SimulationResult(nTimeBins, timeBinLength, SimulationResult.DOUBLE_TO_INT_MODE_FLOOR);
            else
                simulationResult = new SimulationResult(nTimeBins, timeBinLength, SimulationResult.DOUBLE_TO_INT_MODE_ROUND);
            if (!ListenerUtil.mutListener.listen(17829)) {
                Timber.d("today: %d", today);
            }
            Stack<Review> reviews = new Stack<>();
            ArrayList<Review> reviewList = new ArrayList<>();
            Card card = new Card(0, 0, 0, 0, 0, 0);
            CardIterator cardIterator = null;
            Review review = new Review(deck, simulationResult, classifier, reviews, reviewList);
            try {
                if (!ListenerUtil.mutListener.listen(17832)) {
                    cardIterator = new CardIterator(db, today, deck);
                }
                if (!ListenerUtil.mutListener.listen(17844)) {
                    {
                        long _loopCounter339 = 0;
                        while (cardIterator.moveToNext()) {
                            ListenerUtil.loopListener.listen("_loopCounter339", ++_loopCounter339);
                            if (!ListenerUtil.mutListener.listen(17833)) {
                                cardIterator.current(card);
                            }
                            if (!ListenerUtil.mutListener.listen(17834)) {
                                review.newCard(card, newCardSimulator);
                            }
                            if (!ListenerUtil.mutListener.listen(17841)) {
                                if ((ListenerUtil.mutListener.listen(17839) ? (review.getT() >= tMax) : (ListenerUtil.mutListener.listen(17838) ? (review.getT() <= tMax) : (ListenerUtil.mutListener.listen(17837) ? (review.getT() > tMax) : (ListenerUtil.mutListener.listen(17836) ? (review.getT() != tMax) : (ListenerUtil.mutListener.listen(17835) ? (review.getT() == tMax) : (review.getT() < tMax)))))))
                                    if (!ListenerUtil.mutListener.listen(17840)) {
                                        reviews.push(review);
                                    }
                            }
                            if (!ListenerUtil.mutListener.listen(17843)) {
                                {
                                    long _loopCounter338 = 0;
                                    while (!reviews.isEmpty()) {
                                        ListenerUtil.loopListener.listen("_loopCounter338", ++_loopCounter338);
                                        if (!ListenerUtil.mutListener.listen(17842)) {
                                            reviews.pop().simulateReview();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } finally {
                if (!ListenerUtil.mutListener.listen(17831)) {
                    if (cardIterator != null)
                        if (!ListenerUtil.mutListener.listen(17830)) {
                            cardIterator.close();
                        }
                }
            }
            if (!ListenerUtil.mutListener.listen(17845)) {
                ArrayUtils.formatMatrix("nReviews", simulationResult.getNReviews(), "%04d ");
            }
            if (!ListenerUtil.mutListener.listen(17846)) {
                ArrayUtils.formatMatrix("nInState", simulationResult.getNInState(), "%04d ");
            }
            return simulationResult;
        }
    }

    /**
     * Stores global settings.
     */
    private static class Settings {

        private final int computeNDays;

        private final double computeMaxError;

        private final int simulateNIterations;

        private final Collection mCol;

        public Settings(Context context) {
            SharedPreferences prefs = AnkiDroidApp.getSharedPrefs(context);
            mCol = CollectionHelper.getInstance().getCol(context);
            computeNDays = prefs.getInt("advanced_forecast_stats_compute_n_days", 0);
            int computePrecision = prefs.getInt("advanced_forecast_stats_compute_precision", 90);
            computeMaxError = (ListenerUtil.mutListener.listen(17854) ? (((ListenerUtil.mutListener.listen(17850) ? (100 % computePrecision) : (ListenerUtil.mutListener.listen(17849) ? (100 / computePrecision) : (ListenerUtil.mutListener.listen(17848) ? (100 * computePrecision) : (ListenerUtil.mutListener.listen(17847) ? (100 + computePrecision) : (100 - computePrecision)))))) % 100.0) : (ListenerUtil.mutListener.listen(17853) ? (((ListenerUtil.mutListener.listen(17850) ? (100 % computePrecision) : (ListenerUtil.mutListener.listen(17849) ? (100 / computePrecision) : (ListenerUtil.mutListener.listen(17848) ? (100 * computePrecision) : (ListenerUtil.mutListener.listen(17847) ? (100 + computePrecision) : (100 - computePrecision)))))) * 100.0) : (ListenerUtil.mutListener.listen(17852) ? (((ListenerUtil.mutListener.listen(17850) ? (100 % computePrecision) : (ListenerUtil.mutListener.listen(17849) ? (100 / computePrecision) : (ListenerUtil.mutListener.listen(17848) ? (100 * computePrecision) : (ListenerUtil.mutListener.listen(17847) ? (100 + computePrecision) : (100 - computePrecision)))))) - 100.0) : (ListenerUtil.mutListener.listen(17851) ? (((ListenerUtil.mutListener.listen(17850) ? (100 % computePrecision) : (ListenerUtil.mutListener.listen(17849) ? (100 / computePrecision) : (ListenerUtil.mutListener.listen(17848) ? (100 * computePrecision) : (ListenerUtil.mutListener.listen(17847) ? (100 + computePrecision) : (100 - computePrecision)))))) + 100.0) : (((ListenerUtil.mutListener.listen(17850) ? (100 % computePrecision) : (ListenerUtil.mutListener.listen(17849) ? (100 / computePrecision) : (ListenerUtil.mutListener.listen(17848) ? (100 * computePrecision) : (ListenerUtil.mutListener.listen(17847) ? (100 + computePrecision) : (100 - computePrecision)))))) / 100.0)))));
            simulateNIterations = prefs.getInt("advanced_forecast_stats_mc_n_iterations", 1);
            if (!ListenerUtil.mutListener.listen(17855)) {
                Timber.d("computeNDays: %s", computeNDays);
            }
            if (!ListenerUtil.mutListener.listen(17856)) {
                Timber.d("computeMaxError: %s", computeMaxError);
            }
            if (!ListenerUtil.mutListener.listen(17857)) {
                Timber.d("simulateNIterations: %s", simulateNIterations);
            }
        }

        public int getComputeNDays() {
            return computeNDays;
        }

        public double getComputeMaxError() {
            return computeMaxError;
        }

        public int getSimulateNIterations() {
            return simulateNIterations;
        }

        /**
         * @return Maximum number of new cards per day which will be used if it cannot be read from Deck settings.
         */
        public int getMaxNewPerDay() {
            return 20;
        }

        /**
         * @return Maximum number of reviews per day which will be used if it cannot be read from Deck settings.
         */
        public int getMaxReviewsPerDay() {
            return 10000;
        }

        /**
         * @return Factor which will be used if it cannot be read from Deck settings.
         */
        public int getInitialFactor() {
            return Consts.STARTING_FACTOR;
        }

        /**
         * Today.
         * @param collectionCreatedTime The difference, measured in seconds, between midnight, January 1, 1970 UTC and the time at which the collection was created.
         * @return Today in days counted from the time at which the collection was created
         */
        public int getToday(long collectionCreatedTime) {
            if (!ListenerUtil.mutListener.listen(17858)) {
                Timber.d("Collection creation timestamp: %d", collectionCreatedTime);
            }
            long currentTime = mCol.getTime().intTime();
            if (!ListenerUtil.mutListener.listen(17859)) {
                Timber.d("Now: %d", currentTime);
            }
            return (int) ((ListenerUtil.mutListener.listen(17867) ? (((ListenerUtil.mutListener.listen(17863) ? (currentTime % collectionCreatedTime) : (ListenerUtil.mutListener.listen(17862) ? (currentTime / collectionCreatedTime) : (ListenerUtil.mutListener.listen(17861) ? (currentTime * collectionCreatedTime) : (ListenerUtil.mutListener.listen(17860) ? (currentTime + collectionCreatedTime) : (currentTime - collectionCreatedTime)))))) % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17866) ? (((ListenerUtil.mutListener.listen(17863) ? (currentTime % collectionCreatedTime) : (ListenerUtil.mutListener.listen(17862) ? (currentTime / collectionCreatedTime) : (ListenerUtil.mutListener.listen(17861) ? (currentTime * collectionCreatedTime) : (ListenerUtil.mutListener.listen(17860) ? (currentTime + collectionCreatedTime) : (currentTime - collectionCreatedTime)))))) * SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17865) ? (((ListenerUtil.mutListener.listen(17863) ? (currentTime % collectionCreatedTime) : (ListenerUtil.mutListener.listen(17862) ? (currentTime / collectionCreatedTime) : (ListenerUtil.mutListener.listen(17861) ? (currentTime * collectionCreatedTime) : (ListenerUtil.mutListener.listen(17860) ? (currentTime + collectionCreatedTime) : (currentTime - collectionCreatedTime)))))) - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17864) ? (((ListenerUtil.mutListener.listen(17863) ? (currentTime % collectionCreatedTime) : (ListenerUtil.mutListener.listen(17862) ? (currentTime / collectionCreatedTime) : (ListenerUtil.mutListener.listen(17861) ? (currentTime * collectionCreatedTime) : (ListenerUtil.mutListener.listen(17860) ? (currentTime + collectionCreatedTime) : (currentTime - collectionCreatedTime)))))) + SECONDS_PER_DAY) : (((ListenerUtil.mutListener.listen(17863) ? (currentTime % collectionCreatedTime) : (ListenerUtil.mutListener.listen(17862) ? (currentTime / collectionCreatedTime) : (ListenerUtil.mutListener.listen(17861) ? (currentTime * collectionCreatedTime) : (ListenerUtil.mutListener.listen(17860) ? (currentTime + collectionCreatedTime) : (currentTime - collectionCreatedTime)))))) / SECONDS_PER_DAY))))));
        }

        /**
         * Beginning of today.
         * @param collectionCreatedTime The difference, measured in seconds, between midnight, January 1, 1970 UTC and the time at which the collection was created.
         * @return The beginning of today in milliseconds counted from the time at which the collection was created
         */
        public long getDayStartCutoff(long collectionCreatedTime) {
            long today = getToday(collectionCreatedTime);
            return (ListenerUtil.mutListener.listen(17879) ? (((ListenerUtil.mutListener.listen(17875) ? (collectionCreatedTime % ((ListenerUtil.mutListener.listen(17871) ? (today % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17870) ? (today / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17869) ? (today - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17868) ? (today + SECONDS_PER_DAY) : (today * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(17874) ? (collectionCreatedTime / ((ListenerUtil.mutListener.listen(17871) ? (today % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17870) ? (today / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17869) ? (today - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17868) ? (today + SECONDS_PER_DAY) : (today * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(17873) ? (collectionCreatedTime * ((ListenerUtil.mutListener.listen(17871) ? (today % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17870) ? (today / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17869) ? (today - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17868) ? (today + SECONDS_PER_DAY) : (today * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(17872) ? (collectionCreatedTime - ((ListenerUtil.mutListener.listen(17871) ? (today % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17870) ? (today / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17869) ? (today - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17868) ? (today + SECONDS_PER_DAY) : (today * SECONDS_PER_DAY))))))) : (collectionCreatedTime + ((ListenerUtil.mutListener.listen(17871) ? (today % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17870) ? (today / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17869) ? (today - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17868) ? (today + SECONDS_PER_DAY) : (today * SECONDS_PER_DAY)))))))))))) % 1000) : (ListenerUtil.mutListener.listen(17878) ? (((ListenerUtil.mutListener.listen(17875) ? (collectionCreatedTime % ((ListenerUtil.mutListener.listen(17871) ? (today % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17870) ? (today / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17869) ? (today - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17868) ? (today + SECONDS_PER_DAY) : (today * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(17874) ? (collectionCreatedTime / ((ListenerUtil.mutListener.listen(17871) ? (today % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17870) ? (today / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17869) ? (today - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17868) ? (today + SECONDS_PER_DAY) : (today * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(17873) ? (collectionCreatedTime * ((ListenerUtil.mutListener.listen(17871) ? (today % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17870) ? (today / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17869) ? (today - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17868) ? (today + SECONDS_PER_DAY) : (today * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(17872) ? (collectionCreatedTime - ((ListenerUtil.mutListener.listen(17871) ? (today % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17870) ? (today / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17869) ? (today - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17868) ? (today + SECONDS_PER_DAY) : (today * SECONDS_PER_DAY))))))) : (collectionCreatedTime + ((ListenerUtil.mutListener.listen(17871) ? (today % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17870) ? (today / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17869) ? (today - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17868) ? (today + SECONDS_PER_DAY) : (today * SECONDS_PER_DAY)))))))))))) / 1000) : (ListenerUtil.mutListener.listen(17877) ? (((ListenerUtil.mutListener.listen(17875) ? (collectionCreatedTime % ((ListenerUtil.mutListener.listen(17871) ? (today % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17870) ? (today / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17869) ? (today - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17868) ? (today + SECONDS_PER_DAY) : (today * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(17874) ? (collectionCreatedTime / ((ListenerUtil.mutListener.listen(17871) ? (today % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17870) ? (today / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17869) ? (today - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17868) ? (today + SECONDS_PER_DAY) : (today * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(17873) ? (collectionCreatedTime * ((ListenerUtil.mutListener.listen(17871) ? (today % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17870) ? (today / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17869) ? (today - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17868) ? (today + SECONDS_PER_DAY) : (today * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(17872) ? (collectionCreatedTime - ((ListenerUtil.mutListener.listen(17871) ? (today % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17870) ? (today / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17869) ? (today - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17868) ? (today + SECONDS_PER_DAY) : (today * SECONDS_PER_DAY))))))) : (collectionCreatedTime + ((ListenerUtil.mutListener.listen(17871) ? (today % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17870) ? (today / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17869) ? (today - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17868) ? (today + SECONDS_PER_DAY) : (today * SECONDS_PER_DAY)))))))))))) - 1000) : (ListenerUtil.mutListener.listen(17876) ? (((ListenerUtil.mutListener.listen(17875) ? (collectionCreatedTime % ((ListenerUtil.mutListener.listen(17871) ? (today % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17870) ? (today / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17869) ? (today - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17868) ? (today + SECONDS_PER_DAY) : (today * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(17874) ? (collectionCreatedTime / ((ListenerUtil.mutListener.listen(17871) ? (today % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17870) ? (today / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17869) ? (today - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17868) ? (today + SECONDS_PER_DAY) : (today * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(17873) ? (collectionCreatedTime * ((ListenerUtil.mutListener.listen(17871) ? (today % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17870) ? (today / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17869) ? (today - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17868) ? (today + SECONDS_PER_DAY) : (today * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(17872) ? (collectionCreatedTime - ((ListenerUtil.mutListener.listen(17871) ? (today % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17870) ? (today / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17869) ? (today - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17868) ? (today + SECONDS_PER_DAY) : (today * SECONDS_PER_DAY))))))) : (collectionCreatedTime + ((ListenerUtil.mutListener.listen(17871) ? (today % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17870) ? (today / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17869) ? (today - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17868) ? (today + SECONDS_PER_DAY) : (today * SECONDS_PER_DAY)))))))))))) + 1000) : (((ListenerUtil.mutListener.listen(17875) ? (collectionCreatedTime % ((ListenerUtil.mutListener.listen(17871) ? (today % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17870) ? (today / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17869) ? (today - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17868) ? (today + SECONDS_PER_DAY) : (today * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(17874) ? (collectionCreatedTime / ((ListenerUtil.mutListener.listen(17871) ? (today % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17870) ? (today / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17869) ? (today - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17868) ? (today + SECONDS_PER_DAY) : (today * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(17873) ? (collectionCreatedTime * ((ListenerUtil.mutListener.listen(17871) ? (today % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17870) ? (today / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17869) ? (today - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17868) ? (today + SECONDS_PER_DAY) : (today * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(17872) ? (collectionCreatedTime - ((ListenerUtil.mutListener.listen(17871) ? (today % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17870) ? (today / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17869) ? (today - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17868) ? (today + SECONDS_PER_DAY) : (today * SECONDS_PER_DAY))))))) : (collectionCreatedTime + ((ListenerUtil.mutListener.listen(17871) ? (today % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17870) ? (today / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17869) ? (today - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17868) ? (today + SECONDS_PER_DAY) : (today * SECONDS_PER_DAY)))))))))))) * 1000)))));
        }
    }

    private class ArrayUtils {

        public int[][] createIntMatrix(int m, int n) {
            int[][] matrix = new int[m][];
            if (!ListenerUtil.mutListener.listen(17893)) {
                {
                    long _loopCounter341 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(17892) ? (i >= m) : (ListenerUtil.mutListener.listen(17891) ? (i <= m) : (ListenerUtil.mutListener.listen(17890) ? (i > m) : (ListenerUtil.mutListener.listen(17889) ? (i != m) : (ListenerUtil.mutListener.listen(17888) ? (i == m) : (i < m)))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter341", ++_loopCounter341);
                        if (!ListenerUtil.mutListener.listen(17880)) {
                            matrix[i] = new int[n];
                        }
                        if (!ListenerUtil.mutListener.listen(17887)) {
                            {
                                long _loopCounter340 = 0;
                                for (int j = 0; (ListenerUtil.mutListener.listen(17886) ? (j >= n) : (ListenerUtil.mutListener.listen(17885) ? (j <= n) : (ListenerUtil.mutListener.listen(17884) ? (j > n) : (ListenerUtil.mutListener.listen(17883) ? (j != n) : (ListenerUtil.mutListener.listen(17882) ? (j == n) : (j < n)))))); j++) {
                                    ListenerUtil.loopListener.listen("_loopCounter340", ++_loopCounter340);
                                    if (!ListenerUtil.mutListener.listen(17881)) {
                                        matrix[i][j] = 0;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return matrix;
        }

        public int[][] toIntMatrix(double[][] doubleMatrix, int doubleToIntMode) {
            int m = doubleMatrix.length;
            if (!ListenerUtil.mutListener.listen(17899)) {
                if ((ListenerUtil.mutListener.listen(17898) ? (m >= 0) : (ListenerUtil.mutListener.listen(17897) ? (m <= 0) : (ListenerUtil.mutListener.listen(17896) ? (m > 0) : (ListenerUtil.mutListener.listen(17895) ? (m < 0) : (ListenerUtil.mutListener.listen(17894) ? (m != 0) : (m == 0)))))))
                    return new int[0][];
            }
            int n = doubleMatrix[1].length;
            int[][] intMatrix = new int[m][];
            if (!ListenerUtil.mutListener.listen(17920)) {
                {
                    long _loopCounter343 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(17919) ? (i >= m) : (ListenerUtil.mutListener.listen(17918) ? (i <= m) : (ListenerUtil.mutListener.listen(17917) ? (i > m) : (ListenerUtil.mutListener.listen(17916) ? (i != m) : (ListenerUtil.mutListener.listen(17915) ? (i == m) : (i < m)))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter343", ++_loopCounter343);
                        if (!ListenerUtil.mutListener.listen(17900)) {
                            intMatrix[i] = new int[n];
                        }
                        if (!ListenerUtil.mutListener.listen(17914)) {
                            {
                                long _loopCounter342 = 0;
                                for (int j = 0; (ListenerUtil.mutListener.listen(17913) ? (j >= n) : (ListenerUtil.mutListener.listen(17912) ? (j <= n) : (ListenerUtil.mutListener.listen(17911) ? (j > n) : (ListenerUtil.mutListener.listen(17910) ? (j != n) : (ListenerUtil.mutListener.listen(17909) ? (j == n) : (j < n)))))); j++) {
                                    ListenerUtil.loopListener.listen("_loopCounter342", ++_loopCounter342);
                                    if (!ListenerUtil.mutListener.listen(17908)) {
                                        if ((ListenerUtil.mutListener.listen(17905) ? (doubleToIntMode >= SimulationResult.DOUBLE_TO_INT_MODE_ROUND) : (ListenerUtil.mutListener.listen(17904) ? (doubleToIntMode <= SimulationResult.DOUBLE_TO_INT_MODE_ROUND) : (ListenerUtil.mutListener.listen(17903) ? (doubleToIntMode > SimulationResult.DOUBLE_TO_INT_MODE_ROUND) : (ListenerUtil.mutListener.listen(17902) ? (doubleToIntMode < SimulationResult.DOUBLE_TO_INT_MODE_ROUND) : (ListenerUtil.mutListener.listen(17901) ? (doubleToIntMode != SimulationResult.DOUBLE_TO_INT_MODE_ROUND) : (doubleToIntMode == SimulationResult.DOUBLE_TO_INT_MODE_ROUND))))))) {
                                            if (!ListenerUtil.mutListener.listen(17907)) {
                                                intMatrix[i][j] = (int) Math.round(doubleMatrix[i][j]);
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(17906)) {
                                                intMatrix[i][j] = (int) doubleMatrix[i][j];
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return intMatrix;
        }

        public double[][] createDoubleMatrix(int m, int n) {
            double[][] matrix = new double[m][];
            if (!ListenerUtil.mutListener.listen(17934)) {
                {
                    long _loopCounter345 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(17933) ? (i >= m) : (ListenerUtil.mutListener.listen(17932) ? (i <= m) : (ListenerUtil.mutListener.listen(17931) ? (i > m) : (ListenerUtil.mutListener.listen(17930) ? (i != m) : (ListenerUtil.mutListener.listen(17929) ? (i == m) : (i < m)))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter345", ++_loopCounter345);
                        if (!ListenerUtil.mutListener.listen(17921)) {
                            matrix[i] = new double[n];
                        }
                        if (!ListenerUtil.mutListener.listen(17928)) {
                            {
                                long _loopCounter344 = 0;
                                for (int j = 0; (ListenerUtil.mutListener.listen(17927) ? (j >= n) : (ListenerUtil.mutListener.listen(17926) ? (j <= n) : (ListenerUtil.mutListener.listen(17925) ? (j > n) : (ListenerUtil.mutListener.listen(17924) ? (j != n) : (ListenerUtil.mutListener.listen(17923) ? (j == n) : (j < n)))))); j++) {
                                    ListenerUtil.loopListener.listen("_loopCounter344", ++_loopCounter344);
                                    if (!ListenerUtil.mutListener.listen(17922)) {
                                        matrix[i][j] = 0;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return matrix;
        }

        public <T> T[] append(T[] arr, T element, int n) {
            final int N0 = arr.length;
            final int N1 = (ListenerUtil.mutListener.listen(17938) ? (N0 % n) : (ListenerUtil.mutListener.listen(17937) ? (N0 / n) : (ListenerUtil.mutListener.listen(17936) ? (N0 * n) : (ListenerUtil.mutListener.listen(17935) ? (N0 - n) : (N0 + n)))));
            if (!ListenerUtil.mutListener.listen(17939)) {
                arr = Arrays.copyOf(arr, N1);
            }
            if (!ListenerUtil.mutListener.listen(17946)) {
                {
                    long _loopCounter346 = 0;
                    for (int N = N0; (ListenerUtil.mutListener.listen(17945) ? (N >= N1) : (ListenerUtil.mutListener.listen(17944) ? (N <= N1) : (ListenerUtil.mutListener.listen(17943) ? (N > N1) : (ListenerUtil.mutListener.listen(17942) ? (N != N1) : (ListenerUtil.mutListener.listen(17941) ? (N == N1) : (N < N1)))))); N++) {
                        ListenerUtil.loopListener.listen("_loopCounter346", ++_loopCounter346);
                        if (!ListenerUtil.mutListener.listen(17940)) {
                            arr[N] = element;
                        }
                    }
                }
            }
            return arr;
        }

        public int nRows(int[][] matrix) {
            return matrix.length;
        }

        public int nCols(int[][] matrix) {
            if (!ListenerUtil.mutListener.listen(17952)) {
                if ((ListenerUtil.mutListener.listen(17951) ? (matrix.length >= 0) : (ListenerUtil.mutListener.listen(17950) ? (matrix.length <= 0) : (ListenerUtil.mutListener.listen(17949) ? (matrix.length > 0) : (ListenerUtil.mutListener.listen(17948) ? (matrix.length < 0) : (ListenerUtil.mutListener.listen(17947) ? (matrix.length != 0) : (matrix.length == 0)))))))
                    return 0;
            }
            return matrix[0].length;
        }

        public long[] stringToLongArray(String s) {
            String[] split = s.substring(1, (ListenerUtil.mutListener.listen(17956) ? (s.length() % 1) : (ListenerUtil.mutListener.listen(17955) ? (s.length() / 1) : (ListenerUtil.mutListener.listen(17954) ? (s.length() * 1) : (ListenerUtil.mutListener.listen(17953) ? (s.length() + 1) : (s.length() - 1)))))).split(", ");
            long[] arr = new long[split.length];
            if (!ListenerUtil.mutListener.listen(17963)) {
                {
                    long _loopCounter347 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(17962) ? (i >= split.length) : (ListenerUtil.mutListener.listen(17961) ? (i <= split.length) : (ListenerUtil.mutListener.listen(17960) ? (i > split.length) : (ListenerUtil.mutListener.listen(17959) ? (i != split.length) : (ListenerUtil.mutListener.listen(17958) ? (i == split.length) : (i < split.length)))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter347", ++_loopCounter347);
                        if (!ListenerUtil.mutListener.listen(17957)) {
                            arr[i] = Long.parseLong(split[i]);
                        }
                    }
                }
            }
            return arr;
        }

        public int[][] transposeMatrix(int[][] matrix) {
            if (!ListenerUtil.mutListener.listen(17969)) {
                if ((ListenerUtil.mutListener.listen(17968) ? (matrix.length >= 0) : (ListenerUtil.mutListener.listen(17967) ? (matrix.length <= 0) : (ListenerUtil.mutListener.listen(17966) ? (matrix.length > 0) : (ListenerUtil.mutListener.listen(17965) ? (matrix.length < 0) : (ListenerUtil.mutListener.listen(17964) ? (matrix.length != 0) : (matrix.length == 0)))))))
                    return matrix;
            }
            int m = matrix.length;
            int n = matrix[0].length;
            int[][] transpose = new int[n][m];
            int c, d;
            if (!ListenerUtil.mutListener.listen(17982)) {
                {
                    long _loopCounter349 = 0;
                    for (c = 0; (ListenerUtil.mutListener.listen(17981) ? (c >= m) : (ListenerUtil.mutListener.listen(17980) ? (c <= m) : (ListenerUtil.mutListener.listen(17979) ? (c > m) : (ListenerUtil.mutListener.listen(17978) ? (c != m) : (ListenerUtil.mutListener.listen(17977) ? (c == m) : (c < m)))))); c++) {
                        ListenerUtil.loopListener.listen("_loopCounter349", ++_loopCounter349);
                        if (!ListenerUtil.mutListener.listen(17976)) {
                            {
                                long _loopCounter348 = 0;
                                for (d = 0; (ListenerUtil.mutListener.listen(17975) ? (d >= n) : (ListenerUtil.mutListener.listen(17974) ? (d <= n) : (ListenerUtil.mutListener.listen(17973) ? (d > n) : (ListenerUtil.mutListener.listen(17972) ? (d != n) : (ListenerUtil.mutListener.listen(17971) ? (d == n) : (d < n)))))); d++) {
                                    ListenerUtil.loopListener.listen("_loopCounter348", ++_loopCounter348);
                                    if (!ListenerUtil.mutListener.listen(17970)) {
                                        transpose[d][c] = matrix[c][d];
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return transpose;
        }

        public double[][] transposeMatrix(double[][] matrix) {
            if (!ListenerUtil.mutListener.listen(17988)) {
                if ((ListenerUtil.mutListener.listen(17987) ? (matrix.length >= 0) : (ListenerUtil.mutListener.listen(17986) ? (matrix.length <= 0) : (ListenerUtil.mutListener.listen(17985) ? (matrix.length > 0) : (ListenerUtil.mutListener.listen(17984) ? (matrix.length < 0) : (ListenerUtil.mutListener.listen(17983) ? (matrix.length != 0) : (matrix.length == 0)))))))
                    return matrix;
            }
            int m = matrix.length;
            int n = matrix[0].length;
            double[][] transpose = new double[n][m];
            int c, d;
            if (!ListenerUtil.mutListener.listen(18001)) {
                {
                    long _loopCounter351 = 0;
                    for (c = 0; (ListenerUtil.mutListener.listen(18000) ? (c >= m) : (ListenerUtil.mutListener.listen(17999) ? (c <= m) : (ListenerUtil.mutListener.listen(17998) ? (c > m) : (ListenerUtil.mutListener.listen(17997) ? (c != m) : (ListenerUtil.mutListener.listen(17996) ? (c == m) : (c < m)))))); c++) {
                        ListenerUtil.loopListener.listen("_loopCounter351", ++_loopCounter351);
                        if (!ListenerUtil.mutListener.listen(17995)) {
                            {
                                long _loopCounter350 = 0;
                                for (d = 0; (ListenerUtil.mutListener.listen(17994) ? (d >= n) : (ListenerUtil.mutListener.listen(17993) ? (d <= n) : (ListenerUtil.mutListener.listen(17992) ? (d > n) : (ListenerUtil.mutListener.listen(17991) ? (d != n) : (ListenerUtil.mutListener.listen(17990) ? (d == n) : (d < n)))))); d++) {
                                    ListenerUtil.loopListener.listen("_loopCounter350", ++_loopCounter350);
                                    if (!ListenerUtil.mutListener.listen(17989)) {
                                        transpose[d][c] = matrix[c][d];
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return transpose;
        }

        public void formatMatrix(String matrixName, int[][] matrix, String format) {
            StringBuilder s = new StringBuilder();
            if (!ListenerUtil.mutListener.listen(18002)) {
                s.append(matrixName);
            }
            if (!ListenerUtil.mutListener.listen(18003)) {
                s.append(":");
            }
            if (!ListenerUtil.mutListener.listen(18004)) {
                s.append(System.getProperty("line.separator"));
            }
            if (!ListenerUtil.mutListener.listen(18008)) {
                {
                    long _loopCounter353 = 0;
                    for (int[] aMatrix : matrix) {
                        ListenerUtil.loopListener.listen("_loopCounter353", ++_loopCounter353);
                        if (!ListenerUtil.mutListener.listen(18006)) {
                            {
                                long _loopCounter352 = 0;
                                for (int i : aMatrix) {
                                    ListenerUtil.loopListener.listen("_loopCounter352", ++_loopCounter352);
                                    if (!ListenerUtil.mutListener.listen(18005)) {
                                        s.append(String.format(format, i));
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(18007)) {
                            s.append(System.getProperty("line.separator"));
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(18009)) {
                Timber.d(s.toString());
            }
        }
    }

    /**
     * Statistics generated by simulations of Reviews.
     */
    private class SimulationResult {

        public static final int DOUBLE_TO_INT_MODE_FLOOR = 0;

        public static final int DOUBLE_TO_INT_MODE_ROUND = 1;

        private final int doubleToIntMode;

        private final int nTimeBins;

        private final int timeBinLength;

        private final int nDays;

        /**
         * Forecasted number of reviews per time bin (a time bin contains statistics for 1 or a multiple of days)
         * First dimension:
         * 0 = Learn
         * 1 = Young
         * 2 = Mature
         * 3 = Relearn
         * Second dimension: time
         */
        private final double[][] nReviews;

        /**
         * Forecasted number of reviews per day.
         * @see #nReviews
         */
        private final double[][] nReviewsPerDay;

        /**
         * Forecasted number of cards per state
         * First dimension:
         * 0 = New
         * 1 = Young
         * 2 = Mature
         * Second dimension: time
         */
        private final double[][] nInState;

        /**
         * Create an empty SimulationResult.
         * @param nTimeBins Number of time bins.
         * @param timeBinLength Length of 1 time bin in days.
         */
        public SimulationResult(int nTimeBins, int timeBinLength, int doubleToIntMode) {
            nReviews = ArrayUtils.createDoubleMatrix(REVIEW_TYPE_COUNT, nTimeBins);
            nReviewsPerDay = ArrayUtils.createDoubleMatrix(REVIEW_TYPE_COUNT, (ListenerUtil.mutListener.listen(18013) ? (nTimeBins % timeBinLength) : (ListenerUtil.mutListener.listen(18012) ? (nTimeBins / timeBinLength) : (ListenerUtil.mutListener.listen(18011) ? (nTimeBins - timeBinLength) : (ListenerUtil.mutListener.listen(18010) ? (nTimeBins + timeBinLength) : (nTimeBins * timeBinLength))))));
            nInState = ArrayUtils.createDoubleMatrix(CARD_TYPE_COUNT, nTimeBins);
            this.nTimeBins = nTimeBins;
            this.timeBinLength = timeBinLength;
            this.nDays = (ListenerUtil.mutListener.listen(18017) ? (nTimeBins % timeBinLength) : (ListenerUtil.mutListener.listen(18016) ? (nTimeBins / timeBinLength) : (ListenerUtil.mutListener.listen(18015) ? (nTimeBins - timeBinLength) : (ListenerUtil.mutListener.listen(18014) ? (nTimeBins + timeBinLength) : (nTimeBins * timeBinLength)))));
            this.doubleToIntMode = doubleToIntMode;
        }

        public int getnDays() {
            return nDays;
        }

        /**
         * Adds the statistics generated by another simulation to the current statistics.
         * Use to gather statistics over decks.
         * @param res2Add Statistics to be added to the current statistics.
         */
        public void add(SimulationResult res2Add, double prob) {
            int[][] nReviews = res2Add.getNReviews();
            int[][] nInState = res2Add.getNInState();
            if (!ListenerUtil.mutListener.listen(18034)) {
                {
                    long _loopCounter355 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(18033) ? (i >= nReviews.length) : (ListenerUtil.mutListener.listen(18032) ? (i <= nReviews.length) : (ListenerUtil.mutListener.listen(18031) ? (i > nReviews.length) : (ListenerUtil.mutListener.listen(18030) ? (i != nReviews.length) : (ListenerUtil.mutListener.listen(18029) ? (i == nReviews.length) : (i < nReviews.length)))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter355", ++_loopCounter355);
                        if (!ListenerUtil.mutListener.listen(18028)) {
                            {
                                long _loopCounter354 = 0;
                                for (int j = 0; (ListenerUtil.mutListener.listen(18027) ? (j >= nReviews[i].length) : (ListenerUtil.mutListener.listen(18026) ? (j <= nReviews[i].length) : (ListenerUtil.mutListener.listen(18025) ? (j > nReviews[i].length) : (ListenerUtil.mutListener.listen(18024) ? (j != nReviews[i].length) : (ListenerUtil.mutListener.listen(18023) ? (j == nReviews[i].length) : (j < nReviews[i].length)))))); j++) {
                                    ListenerUtil.loopListener.listen("_loopCounter354", ++_loopCounter354);
                                    if (!ListenerUtil.mutListener.listen(18022)) {
                                        this.nReviews[i][j] += (ListenerUtil.mutListener.listen(18021) ? (nReviews[i][j] % prob) : (ListenerUtil.mutListener.listen(18020) ? (nReviews[i][j] / prob) : (ListenerUtil.mutListener.listen(18019) ? (nReviews[i][j] - prob) : (ListenerUtil.mutListener.listen(18018) ? (nReviews[i][j] + prob) : (nReviews[i][j] * prob)))));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(18051)) {
                {
                    long _loopCounter357 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(18050) ? (i >= nInState.length) : (ListenerUtil.mutListener.listen(18049) ? (i <= nInState.length) : (ListenerUtil.mutListener.listen(18048) ? (i > nInState.length) : (ListenerUtil.mutListener.listen(18047) ? (i != nInState.length) : (ListenerUtil.mutListener.listen(18046) ? (i == nInState.length) : (i < nInState.length)))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter357", ++_loopCounter357);
                        if (!ListenerUtil.mutListener.listen(18045)) {
                            {
                                long _loopCounter356 = 0;
                                for (int j = 0; (ListenerUtil.mutListener.listen(18044) ? (j >= nInState[i].length) : (ListenerUtil.mutListener.listen(18043) ? (j <= nInState[i].length) : (ListenerUtil.mutListener.listen(18042) ? (j > nInState[i].length) : (ListenerUtil.mutListener.listen(18041) ? (j != nInState[i].length) : (ListenerUtil.mutListener.listen(18040) ? (j == nInState[i].length) : (j < nInState[i].length)))))); j++) {
                                    ListenerUtil.loopListener.listen("_loopCounter356", ++_loopCounter356);
                                    if (!ListenerUtil.mutListener.listen(18039)) {
                                        this.nInState[i][j] += (ListenerUtil.mutListener.listen(18038) ? (nInState[i][j] % prob) : (ListenerUtil.mutListener.listen(18037) ? (nInState[i][j] / prob) : (ListenerUtil.mutListener.listen(18036) ? (nInState[i][j] - prob) : (ListenerUtil.mutListener.listen(18035) ? (nInState[i][j] + prob) : (nInState[i][j] * prob)))));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        public int[][] getNReviews() {
            return ArrayUtils.toIntMatrix(nReviews, doubleToIntMode);
        }

        public int[][] getNInState() {
            return ArrayUtils.toIntMatrix(nInState, doubleToIntMode);
        }

        /**
         * Request the number of reviews which have been simulated so far at a particular day
         * (to check if the 'maximum number of reviews per day' limit has been reached).
         * If we are doing more than one simulation this means the average number of reviews
         * simulated so far at the requested day (over simulations).
         * More correct would be simulating all (or several) possible futures and returning here the number of
         * reviews done in the future currently being simulated.
         *
         * But that would change the entire structure of the simulation (which is now in a for each card loop).
         * @param tElapsed Day for which the number of reviews is requested.
         * @return Number of reviews of young and mature cards simulated at time tElapsed.
         * This excludes new cards and relearns as they don't count towards the limit.
         */
        public int nReviewsDoneToday(int tElapsed) {
            return (int) ((ListenerUtil.mutListener.listen(18055) ? (nReviewsPerDay[REVIEW_TYPE_YOUNG][tElapsed] % nReviewsPerDay[REVIEW_TYPE_MATURE][tElapsed]) : (ListenerUtil.mutListener.listen(18054) ? (nReviewsPerDay[REVIEW_TYPE_YOUNG][tElapsed] / nReviewsPerDay[REVIEW_TYPE_MATURE][tElapsed]) : (ListenerUtil.mutListener.listen(18053) ? (nReviewsPerDay[REVIEW_TYPE_YOUNG][tElapsed] * nReviewsPerDay[REVIEW_TYPE_MATURE][tElapsed]) : (ListenerUtil.mutListener.listen(18052) ? (nReviewsPerDay[REVIEW_TYPE_YOUNG][tElapsed] - nReviewsPerDay[REVIEW_TYPE_MATURE][tElapsed]) : (nReviewsPerDay[REVIEW_TYPE_YOUNG][tElapsed] + nReviewsPerDay[REVIEW_TYPE_MATURE][tElapsed]))))));
        }

        /**
         * Increment the count 'number of reviews of card with type cardType' with one at day t.
         * @param cardType  Card type
         * @param t Day for which to increment
         */
        public void incrementNReviews(int cardType, int t, double prob) {
            if (!ListenerUtil.mutListener.listen(18060)) {
                nReviews[cardType][(ListenerUtil.mutListener.listen(18059) ? (t % timeBinLength) : (ListenerUtil.mutListener.listen(18058) ? (t * timeBinLength) : (ListenerUtil.mutListener.listen(18057) ? (t - timeBinLength) : (ListenerUtil.mutListener.listen(18056) ? (t + timeBinLength) : (t / timeBinLength)))))] += prob;
            }
            if (!ListenerUtil.mutListener.listen(18061)) {
                nReviewsPerDay[cardType][t] += prob;
            }
        }

        /**
         * Increment the count 'number of cards in the state of the given card' with one between tFrom and tTo.
         * @param card Card from which to read the state.
         * @param tFrom The first day for which to update the state.
         * @param tTo The day after the last day for which to update the state.
         */
        public void updateNInState(Card card, int tFrom, int tTo, double prob) {
            int cardType = card.getType();
            int t0 = (ListenerUtil.mutListener.listen(18065) ? (tFrom % timeBinLength) : (ListenerUtil.mutListener.listen(18064) ? (tFrom * timeBinLength) : (ListenerUtil.mutListener.listen(18063) ? (tFrom - timeBinLength) : (ListenerUtil.mutListener.listen(18062) ? (tFrom + timeBinLength) : (tFrom / timeBinLength)))));
            int t1 = (ListenerUtil.mutListener.listen(18069) ? (tTo % timeBinLength) : (ListenerUtil.mutListener.listen(18068) ? (tTo * timeBinLength) : (ListenerUtil.mutListener.listen(18067) ? (tTo - timeBinLength) : (ListenerUtil.mutListener.listen(18066) ? (tTo + timeBinLength) : (tTo / timeBinLength)))));
            if (!ListenerUtil.mutListener.listen(18082)) {
                {
                    long _loopCounter358 = 0;
                    for (int t = t0; (ListenerUtil.mutListener.listen(18081) ? (t >= t1) : (ListenerUtil.mutListener.listen(18080) ? (t <= t1) : (ListenerUtil.mutListener.listen(18079) ? (t > t1) : (ListenerUtil.mutListener.listen(18078) ? (t != t1) : (ListenerUtil.mutListener.listen(18077) ? (t == t1) : (t < t1)))))); t++) {
                        ListenerUtil.loopListener.listen("_loopCounter358", ++_loopCounter358);
                        if (!ListenerUtil.mutListener.listen(18076)) {
                            if ((ListenerUtil.mutListener.listen(18074) ? (t >= nTimeBins) : (ListenerUtil.mutListener.listen(18073) ? (t <= nTimeBins) : (ListenerUtil.mutListener.listen(18072) ? (t > nTimeBins) : (ListenerUtil.mutListener.listen(18071) ? (t != nTimeBins) : (ListenerUtil.mutListener.listen(18070) ? (t == nTimeBins) : (t < nTimeBins))))))) {
                                if (!ListenerUtil.mutListener.listen(18075)) {
                                    nInState[cardType][t] += prob;
                                }
                            } else {
                                return;
                            }
                        }
                    }
                }
            }
        }

        /**
         * Increment the count 'number of cards in the state of the given card' with one between tFrom and tTo and
         * replace state set during last review (contained in prevCard) with state set during new review (contained in card).
         *
         * This is necessary because we want to display the state at the end of each time bin.
         * So if two reviews occurred in one time bin, that time bin should display the
         * last review which occurred in it.
         *
         * @see #updateNInState(Card, int, int, double)
         */
        public void updateNInState(Card prevCard, Card card, int tFrom, int tTo, double prob) {
            int lastReview = prevCard.getLastReview();
            int prevCardType = prevCard.getType();
            int cardType = card.getType();
            int t0 = (ListenerUtil.mutListener.listen(18086) ? (tFrom % timeBinLength) : (ListenerUtil.mutListener.listen(18085) ? (tFrom * timeBinLength) : (ListenerUtil.mutListener.listen(18084) ? (tFrom - timeBinLength) : (ListenerUtil.mutListener.listen(18083) ? (tFrom + timeBinLength) : (tFrom / timeBinLength)))));
            int t1 = (ListenerUtil.mutListener.listen(18090) ? (Math.min(lastReview, tTo) % timeBinLength) : (ListenerUtil.mutListener.listen(18089) ? (Math.min(lastReview, tTo) * timeBinLength) : (ListenerUtil.mutListener.listen(18088) ? (Math.min(lastReview, tTo) - timeBinLength) : (ListenerUtil.mutListener.listen(18087) ? (Math.min(lastReview, tTo) + timeBinLength) : (Math.min(lastReview, tTo) / timeBinLength)))));
            if (!ListenerUtil.mutListener.listen(18103)) {
                {
                    long _loopCounter359 = 0;
                    // Replace state set during last review
                    for (int t = t0; (ListenerUtil.mutListener.listen(18102) ? (t >= t1) : (ListenerUtil.mutListener.listen(18101) ? (t <= t1) : (ListenerUtil.mutListener.listen(18100) ? (t > t1) : (ListenerUtil.mutListener.listen(18099) ? (t != t1) : (ListenerUtil.mutListener.listen(18098) ? (t == t1) : (t < t1)))))); t++) {
                        ListenerUtil.loopListener.listen("_loopCounter359", ++_loopCounter359);
                        if (!ListenerUtil.mutListener.listen(18097)) {
                            if ((ListenerUtil.mutListener.listen(18095) ? (t >= nTimeBins) : (ListenerUtil.mutListener.listen(18094) ? (t <= nTimeBins) : (ListenerUtil.mutListener.listen(18093) ? (t > nTimeBins) : (ListenerUtil.mutListener.listen(18092) ? (t != nTimeBins) : (ListenerUtil.mutListener.listen(18091) ? (t == nTimeBins) : (t < nTimeBins))))))) {
                                if (!ListenerUtil.mutListener.listen(18096)) {
                                    nInState[prevCardType][t] -= prob;
                                }
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(18108)) {
                t1 = (ListenerUtil.mutListener.listen(18107) ? (tTo % timeBinLength) : (ListenerUtil.mutListener.listen(18106) ? (tTo * timeBinLength) : (ListenerUtil.mutListener.listen(18105) ? (tTo - timeBinLength) : (ListenerUtil.mutListener.listen(18104) ? (tTo + timeBinLength) : (tTo / timeBinLength)))));
            }
            if (!ListenerUtil.mutListener.listen(18121)) {
                {
                    long _loopCounter360 = 0;
                    // With state set during new review
                    for (int t = t0; (ListenerUtil.mutListener.listen(18120) ? (t >= t1) : (ListenerUtil.mutListener.listen(18119) ? (t <= t1) : (ListenerUtil.mutListener.listen(18118) ? (t > t1) : (ListenerUtil.mutListener.listen(18117) ? (t != t1) : (ListenerUtil.mutListener.listen(18116) ? (t == t1) : (t < t1)))))); t++) {
                        ListenerUtil.loopListener.listen("_loopCounter360", ++_loopCounter360);
                        if (!ListenerUtil.mutListener.listen(18115)) {
                            if ((ListenerUtil.mutListener.listen(18113) ? (t >= nTimeBins) : (ListenerUtil.mutListener.listen(18112) ? (t <= nTimeBins) : (ListenerUtil.mutListener.listen(18111) ? (t > nTimeBins) : (ListenerUtil.mutListener.listen(18110) ? (t != nTimeBins) : (ListenerUtil.mutListener.listen(18109) ? (t == nTimeBins) : (t < nTimeBins))))))) {
                                if (!ListenerUtil.mutListener.listen(18114)) {
                                    nInState[cardType][t] += prob;
                                }
                            } else {
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    private static class PlottableSimulationResult {

        // 4 = Relearn
        private final ArrayList<int[]> nReviews;

        // Second dimension: time
        private final double[][] nInState;

        public PlottableSimulationResult(ArrayList<int[]> nReviews, double[][] nInState) {
            this.nReviews = nReviews;
            this.nInState = nInState;
        }

        public ArrayList<int[]> getNReviews() {
            return nReviews;
        }

        public double[][] getNInState() {
            return nInState;
        }
    }

    /**
     * A review has a particular outcome with a particular probability.
     * A review results in the state of the card (card interval) being changed.
     * A ReviewOutcome bundles the probability of the outcome and the card with changed state.
     */
    private static class ReviewOutcome {

        private Card card;

        private double prob;

        public ReviewOutcome(Card card, double prob) {
            if (!ListenerUtil.mutListener.listen(18122)) {
                this.card = card;
            }
            if (!ListenerUtil.mutListener.listen(18123)) {
                this.prob = prob;
            }
        }

        public void setAll(Card card, double prob) {
            if (!ListenerUtil.mutListener.listen(18124)) {
                this.card = card;
            }
            if (!ListenerUtil.mutListener.listen(18125)) {
                this.prob = prob;
            }
        }

        public Card getCard() {
            return card;
        }

        public double getProb() {
            return prob;
        }

        @Override
        @NonNull
        public String toString() {
            return "ReviewOutcome{" + "card=" + card + ", prob=" + prob + '}';
        }
    }

    /**
     * Bundles the information needed to simulate a review and the objects affected by the review.
     */
    private class Review {

        /**
         * Deck-specific setting stored separately to save a method call on the deck object)
         */
        private final int maxReviewsPerDay;

        /**
         * Number of reviews simulated for this card at time < tElapsed
         */
        private int nPrevRevs;

        /**
         * The probability that the outcomes of the reviews simulated for this card at time < tElapsed are such that
         * this review [with this state of the card] will occur [at this time (tElapsed)].
         */
        private double prob;

        /**
         * The time instant at which the review takes place.
         */
        private int tElapsed;

        /**
         * The outcome of the review.
         * We still have to do the review if the outcome has already been specified
         * (to update statistics, deterime probability of specified outcome, and to schedule subsequent reviews)
         * Only relevant if we are computing (all possible review outcomes), not if simulating (only one possible outcome)
         */
        private int outcome;

        /**
         * Deck-specific settings
         */
        private final Deck deck;

        /**
         * State of the card before current review.
         * Needed to schedule current review but with different outcome and to update statistics.
         */
        private Card card = new Card(0, 0, 0, 0, 0, 0);

        private final Card prevCard = new Card(0, 0, 0, 0, 0, 0);

        /**
         * State of the card after current review.
         * Needed to schedule future review.
         */
        private Card newCard = new Card(0, 0, 0, 0, 0, 0);

        /**
         * Statistics
         */
        private final SimulationResult simulationResult;

        /**
         * Classifier which uses probability distribution from review log to predict outcome of review.
         */
        private final EaseClassifier classifier;

        /**
         * Reviews which are scheduled to be simulated.
         * For adding current review with other outcome and future review.
         */
        private final Stack<Review> reviews;

        /**
         * Review objects to be re-used so that we don't have to create new Review objects all the time.
         * Be careful: it also contains Review objects which are still in use.
         * So the algorithm using this list has to make sure that it only re-uses Review objects which are not in use anymore.
         */
        private final List<Review> reviewList;

        /**
         * For creating future reviews which are to be scheduled as a result of the current review.
         * @see Review(Deck, SimulationResult, EaseClassifier, Stack<Review>)
         */
        private Review(Review prevReview, Card card, int nPrevRevs, int tElapsed, double prob) {
            this.deck = prevReview.deck;
            if (!ListenerUtil.mutListener.listen(18126)) {
                this.card.setAll(card);
            }
            this.simulationResult = prevReview.simulationResult;
            this.classifier = prevReview.classifier;
            this.reviews = prevReview.reviews;
            this.reviewList = prevReview.reviewList;
            if (!ListenerUtil.mutListener.listen(18127)) {
                this.nPrevRevs = nPrevRevs;
            }
            if (!ListenerUtil.mutListener.listen(18128)) {
                this.tElapsed = tElapsed;
            }
            if (!ListenerUtil.mutListener.listen(18129)) {
                this.prob = prob;
            }
            this.maxReviewsPerDay = deck.getRevPerDay();
        }

        /**
         * For creating a review which is to be scheduled.
         * After this constructor, either @see newCard(Card, NewCardSimulator) or existingCard(Card, int, int, double) has to be called.
         * @param deck Information needed to simulate a review: deck settings.
         *             Will be affected by the review. After the review it will contain the card type etc. after the review.
         * @param simulationResult Will be affected by the review. After the review it will contain updated statistics.
         * @param classifier Information needed to simulate a review: transition probabilities to new card state for each possible current card state.
         * @param reviews Will be affected by the review. Scheduled future reviews of this card will be added.
         */
        public Review(Deck deck, SimulationResult simulationResult, EaseClassifier classifier, Stack<Review> reviews, List<Review> reviewList) {
            this.deck = deck;
            this.simulationResult = simulationResult;
            this.classifier = classifier;
            this.reviews = reviews;
            this.reviewList = reviewList;
            this.maxReviewsPerDay = deck.getRevPerDay();
        }

        /**
         * Re-use the current review object to schedule a new card. A new card here means that it has not been reviewed yet.
         * @param card Information needed to simulate a review: card due date, type and factor.
         * @param newCardSimulator Information needed to simulate a review: The next day new cards will be added and the number of cards already added on that day.
         *                         Will be affected by the review. After the review of a new card, the number of cards added on that day will be updated.
         *                         Next day new cards will be added might be updated if new card limit has been reached.
         */
        public void newCard(Card card, NewCardSimulator newCardSimulator) {
            if (!ListenerUtil.mutListener.listen(18130)) {
                this.card = card;
            }
            if (!ListenerUtil.mutListener.listen(18131)) {
                this.nPrevRevs = 0;
            }
            if (!ListenerUtil.mutListener.listen(18132)) {
                this.prob = 1;
            }
            if (!ListenerUtil.mutListener.listen(18133)) {
                this.outcome = 0;
            }
            if (!ListenerUtil.mutListener.listen(18141)) {
                // # Rate-limit new cards by shifting starting time
                if ((ListenerUtil.mutListener.listen(18138) ? (card.getType() >= CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(18137) ? (card.getType() <= CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(18136) ? (card.getType() > CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(18135) ? (card.getType() < CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(18134) ? (card.getType() != CARD_TYPE_NEW) : (card.getType() == CARD_TYPE_NEW))))))) {
                    if (!ListenerUtil.mutListener.listen(18140)) {
                        tElapsed = newCardSimulator.simulateNewCard(deck);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(18139)) {
                        tElapsed = card.getDue();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(18142)) {
                // New reviews happen with probability 1
                this.simulationResult.updateNInState(card, 0, tElapsed, 1);
            }
        }

        /**
         * Re-use the current review object to schedule an existing card. An existing card here means that it has been reviewed before (either by the user or by the simulation)
         * and hence the due date is known.
         */
        private void existingCard(Card card, int nPrevRevs, int tElapsed, double prob) {
            if (!ListenerUtil.mutListener.listen(18143)) {
                this.card.setAll(card);
            }
            if (!ListenerUtil.mutListener.listen(18144)) {
                this.nPrevRevs = nPrevRevs;
            }
            if (!ListenerUtil.mutListener.listen(18145)) {
                this.tElapsed = tElapsed;
            }
            if (!ListenerUtil.mutListener.listen(18146)) {
                this.prob = prob;
            }
            if (!ListenerUtil.mutListener.listen(18147)) {
                this.outcome = 0;
            }
        }

        /**
         * Simulates one review of the card. The review results in:
         * - The card (prevCard and newCard) being updated
         * - New card simulator (when to schedule next new card) being updated if the card was new
         * - The simulationResult being updated.
         * - New review(s) being scheduled.
         */
        public void simulateReview() {
            if (!ListenerUtil.mutListener.listen(18239)) {
                if ((ListenerUtil.mutListener.listen(18164) ? ((ListenerUtil.mutListener.listen(18158) ? ((ListenerUtil.mutListener.listen(18152) ? (card.getType() >= CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(18151) ? (card.getType() <= CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(18150) ? (card.getType() > CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(18149) ? (card.getType() < CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(18148) ? (card.getType() != CARD_TYPE_NEW) : (card.getType() == CARD_TYPE_NEW)))))) && (ListenerUtil.mutListener.listen(18157) ? (simulationResult.nReviewsDoneToday(tElapsed) >= maxReviewsPerDay) : (ListenerUtil.mutListener.listen(18156) ? (simulationResult.nReviewsDoneToday(tElapsed) <= maxReviewsPerDay) : (ListenerUtil.mutListener.listen(18155) ? (simulationResult.nReviewsDoneToday(tElapsed) > maxReviewsPerDay) : (ListenerUtil.mutListener.listen(18154) ? (simulationResult.nReviewsDoneToday(tElapsed) != maxReviewsPerDay) : (ListenerUtil.mutListener.listen(18153) ? (simulationResult.nReviewsDoneToday(tElapsed) == maxReviewsPerDay) : (simulationResult.nReviewsDoneToday(tElapsed) < maxReviewsPerDay))))))) : ((ListenerUtil.mutListener.listen(18152) ? (card.getType() >= CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(18151) ? (card.getType() <= CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(18150) ? (card.getType() > CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(18149) ? (card.getType() < CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(18148) ? (card.getType() != CARD_TYPE_NEW) : (card.getType() == CARD_TYPE_NEW)))))) || (ListenerUtil.mutListener.listen(18157) ? (simulationResult.nReviewsDoneToday(tElapsed) >= maxReviewsPerDay) : (ListenerUtil.mutListener.listen(18156) ? (simulationResult.nReviewsDoneToday(tElapsed) <= maxReviewsPerDay) : (ListenerUtil.mutListener.listen(18155) ? (simulationResult.nReviewsDoneToday(tElapsed) > maxReviewsPerDay) : (ListenerUtil.mutListener.listen(18154) ? (simulationResult.nReviewsDoneToday(tElapsed) != maxReviewsPerDay) : (ListenerUtil.mutListener.listen(18153) ? (simulationResult.nReviewsDoneToday(tElapsed) == maxReviewsPerDay) : (simulationResult.nReviewsDoneToday(tElapsed) < maxReviewsPerDay)))))))) && (ListenerUtil.mutListener.listen(18163) ? (outcome >= 0) : (ListenerUtil.mutListener.listen(18162) ? (outcome <= 0) : (ListenerUtil.mutListener.listen(18161) ? (outcome < 0) : (ListenerUtil.mutListener.listen(18160) ? (outcome != 0) : (ListenerUtil.mutListener.listen(18159) ? (outcome == 0) : (outcome > 0))))))) : ((ListenerUtil.mutListener.listen(18158) ? ((ListenerUtil.mutListener.listen(18152) ? (card.getType() >= CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(18151) ? (card.getType() <= CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(18150) ? (card.getType() > CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(18149) ? (card.getType() < CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(18148) ? (card.getType() != CARD_TYPE_NEW) : (card.getType() == CARD_TYPE_NEW)))))) && (ListenerUtil.mutListener.listen(18157) ? (simulationResult.nReviewsDoneToday(tElapsed) >= maxReviewsPerDay) : (ListenerUtil.mutListener.listen(18156) ? (simulationResult.nReviewsDoneToday(tElapsed) <= maxReviewsPerDay) : (ListenerUtil.mutListener.listen(18155) ? (simulationResult.nReviewsDoneToday(tElapsed) > maxReviewsPerDay) : (ListenerUtil.mutListener.listen(18154) ? (simulationResult.nReviewsDoneToday(tElapsed) != maxReviewsPerDay) : (ListenerUtil.mutListener.listen(18153) ? (simulationResult.nReviewsDoneToday(tElapsed) == maxReviewsPerDay) : (simulationResult.nReviewsDoneToday(tElapsed) < maxReviewsPerDay))))))) : ((ListenerUtil.mutListener.listen(18152) ? (card.getType() >= CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(18151) ? (card.getType() <= CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(18150) ? (card.getType() > CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(18149) ? (card.getType() < CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(18148) ? (card.getType() != CARD_TYPE_NEW) : (card.getType() == CARD_TYPE_NEW)))))) || (ListenerUtil.mutListener.listen(18157) ? (simulationResult.nReviewsDoneToday(tElapsed) >= maxReviewsPerDay) : (ListenerUtil.mutListener.listen(18156) ? (simulationResult.nReviewsDoneToday(tElapsed) <= maxReviewsPerDay) : (ListenerUtil.mutListener.listen(18155) ? (simulationResult.nReviewsDoneToday(tElapsed) > maxReviewsPerDay) : (ListenerUtil.mutListener.listen(18154) ? (simulationResult.nReviewsDoneToday(tElapsed) != maxReviewsPerDay) : (ListenerUtil.mutListener.listen(18153) ? (simulationResult.nReviewsDoneToday(tElapsed) == maxReviewsPerDay) : (simulationResult.nReviewsDoneToday(tElapsed) < maxReviewsPerDay)))))))) || (ListenerUtil.mutListener.listen(18163) ? (outcome >= 0) : (ListenerUtil.mutListener.listen(18162) ? (outcome <= 0) : (ListenerUtil.mutListener.listen(18161) ? (outcome < 0) : (ListenerUtil.mutListener.listen(18160) ? (outcome != 0) : (ListenerUtil.mutListener.listen(18159) ? (outcome == 0) : (outcome > 0))))))))) {
                    if (!ListenerUtil.mutListener.listen(18181)) {
                        // Update the forecasted number of reviews
                        if ((ListenerUtil.mutListener.listen(18179) ? (outcome >= 0) : (ListenerUtil.mutListener.listen(18178) ? (outcome <= 0) : (ListenerUtil.mutListener.listen(18177) ? (outcome > 0) : (ListenerUtil.mutListener.listen(18176) ? (outcome < 0) : (ListenerUtil.mutListener.listen(18175) ? (outcome != 0) : (outcome == 0)))))))
                            if (!ListenerUtil.mutListener.listen(18180)) {
                                simulationResult.incrementNReviews(card.getType(), tElapsed, prob);
                            }
                    }
                    if (!ListenerUtil.mutListener.listen(18182)) {
                        // Simulate response
                        prevCard.setAll(card);
                    }
                    if (!ListenerUtil.mutListener.listen(18183)) {
                        newCard.setAll(card);
                    }
                    ReviewOutcome reviewOutcome;
                    if ((ListenerUtil.mutListener.listen(18194) ? ((ListenerUtil.mutListener.listen(18188) ? (tElapsed <= Settings.getComputeNDays()) : (ListenerUtil.mutListener.listen(18187) ? (tElapsed > Settings.getComputeNDays()) : (ListenerUtil.mutListener.listen(18186) ? (tElapsed < Settings.getComputeNDays()) : (ListenerUtil.mutListener.listen(18185) ? (tElapsed != Settings.getComputeNDays()) : (ListenerUtil.mutListener.listen(18184) ? (tElapsed == Settings.getComputeNDays()) : (tElapsed >= Settings.getComputeNDays())))))) && (ListenerUtil.mutListener.listen(18193) ? (prob >= Settings.getComputeMaxError()) : (ListenerUtil.mutListener.listen(18192) ? (prob <= Settings.getComputeMaxError()) : (ListenerUtil.mutListener.listen(18191) ? (prob > Settings.getComputeMaxError()) : (ListenerUtil.mutListener.listen(18190) ? (prob != Settings.getComputeMaxError()) : (ListenerUtil.mutListener.listen(18189) ? (prob == Settings.getComputeMaxError()) : (prob < Settings.getComputeMaxError()))))))) : ((ListenerUtil.mutListener.listen(18188) ? (tElapsed <= Settings.getComputeNDays()) : (ListenerUtil.mutListener.listen(18187) ? (tElapsed > Settings.getComputeNDays()) : (ListenerUtil.mutListener.listen(18186) ? (tElapsed < Settings.getComputeNDays()) : (ListenerUtil.mutListener.listen(18185) ? (tElapsed != Settings.getComputeNDays()) : (ListenerUtil.mutListener.listen(18184) ? (tElapsed == Settings.getComputeNDays()) : (tElapsed >= Settings.getComputeNDays())))))) || (ListenerUtil.mutListener.listen(18193) ? (prob >= Settings.getComputeMaxError()) : (ListenerUtil.mutListener.listen(18192) ? (prob <= Settings.getComputeMaxError()) : (ListenerUtil.mutListener.listen(18191) ? (prob > Settings.getComputeMaxError()) : (ListenerUtil.mutListener.listen(18190) ? (prob != Settings.getComputeMaxError()) : (ListenerUtil.mutListener.listen(18189) ? (prob == Settings.getComputeMaxError()) : (prob < Settings.getComputeMaxError())))))))))
                        reviewOutcome = classifier.simSingleReview(newCard);
                    else
                        reviewOutcome = classifier.simSingleReview(newCard, outcome);
                    if (!ListenerUtil.mutListener.listen(18195)) {
                        newCard = reviewOutcome.getCard();
                    }
                    double outcomeProb = reviewOutcome.getProb();
                    if (!ListenerUtil.mutListener.listen(18196)) {
                        newCard.setLastReview(tElapsed);
                    }
                    if (!ListenerUtil.mutListener.listen(18207)) {
                        // If card failed, update "relearn" count
                        if ((ListenerUtil.mutListener.listen(18201) ? (newCard.getCorrect() >= 0) : (ListenerUtil.mutListener.listen(18200) ? (newCard.getCorrect() <= 0) : (ListenerUtil.mutListener.listen(18199) ? (newCard.getCorrect() > 0) : (ListenerUtil.mutListener.listen(18198) ? (newCard.getCorrect() < 0) : (ListenerUtil.mutListener.listen(18197) ? (newCard.getCorrect() != 0) : (newCard.getCorrect() == 0)))))))
                            if (!ListenerUtil.mutListener.listen(18206)) {
                                simulationResult.incrementNReviews(3, tElapsed, (ListenerUtil.mutListener.listen(18205) ? (prob % outcomeProb) : (ListenerUtil.mutListener.listen(18204) ? (prob / outcomeProb) : (ListenerUtil.mutListener.listen(18203) ? (prob - outcomeProb) : (ListenerUtil.mutListener.listen(18202) ? (prob + outcomeProb) : (prob * outcomeProb))))));
                            }
                    }
                    if (!ListenerUtil.mutListener.listen(18216)) {
                        // Set state of card between current and next review
                        simulationResult.updateNInState(prevCard, newCard, tElapsed, (ListenerUtil.mutListener.listen(18211) ? (tElapsed % newCard.getIvl()) : (ListenerUtil.mutListener.listen(18210) ? (tElapsed / newCard.getIvl()) : (ListenerUtil.mutListener.listen(18209) ? (tElapsed * newCard.getIvl()) : (ListenerUtil.mutListener.listen(18208) ? (tElapsed - newCard.getIvl()) : (tElapsed + newCard.getIvl()))))), (ListenerUtil.mutListener.listen(18215) ? (prob % outcomeProb) : (ListenerUtil.mutListener.listen(18214) ? (prob / outcomeProb) : (ListenerUtil.mutListener.listen(18213) ? (prob - outcomeProb) : (ListenerUtil.mutListener.listen(18212) ? (prob + outcomeProb) : (prob * outcomeProb))))));
                    }
                    if (!ListenerUtil.mutListener.listen(18229)) {
                        // Schedule current review, but with other outcome
                        if ((ListenerUtil.mutListener.listen(18227) ? ((ListenerUtil.mutListener.listen(18221) ? (outcomeProb >= 1.0) : (ListenerUtil.mutListener.listen(18220) ? (outcomeProb <= 1.0) : (ListenerUtil.mutListener.listen(18219) ? (outcomeProb > 1.0) : (ListenerUtil.mutListener.listen(18218) ? (outcomeProb != 1.0) : (ListenerUtil.mutListener.listen(18217) ? (outcomeProb == 1.0) : (outcomeProb < 1.0)))))) || (ListenerUtil.mutListener.listen(18226) ? (outcome >= 3) : (ListenerUtil.mutListener.listen(18225) ? (outcome <= 3) : (ListenerUtil.mutListener.listen(18224) ? (outcome > 3) : (ListenerUtil.mutListener.listen(18223) ? (outcome != 3) : (ListenerUtil.mutListener.listen(18222) ? (outcome == 3) : (outcome < 3))))))) : ((ListenerUtil.mutListener.listen(18221) ? (outcomeProb >= 1.0) : (ListenerUtil.mutListener.listen(18220) ? (outcomeProb <= 1.0) : (ListenerUtil.mutListener.listen(18219) ? (outcomeProb > 1.0) : (ListenerUtil.mutListener.listen(18218) ? (outcomeProb != 1.0) : (ListenerUtil.mutListener.listen(18217) ? (outcomeProb == 1.0) : (outcomeProb < 1.0)))))) && (ListenerUtil.mutListener.listen(18226) ? (outcome >= 3) : (ListenerUtil.mutListener.listen(18225) ? (outcome <= 3) : (ListenerUtil.mutListener.listen(18224) ? (outcome > 3) : (ListenerUtil.mutListener.listen(18223) ? (outcome != 3) : (ListenerUtil.mutListener.listen(18222) ? (outcome == 3) : (outcome < 3)))))))))
                            if (!ListenerUtil.mutListener.listen(18228)) {
                                scheduleCurrentReview(prevCard);
                            }
                    }
                    if (!ListenerUtil.mutListener.listen(18238)) {
                        // Advance time to next review
                        scheduleNextReview(newCard, (ListenerUtil.mutListener.listen(18233) ? (tElapsed % newCard.getIvl()) : (ListenerUtil.mutListener.listen(18232) ? (tElapsed / newCard.getIvl()) : (ListenerUtil.mutListener.listen(18231) ? (tElapsed * newCard.getIvl()) : (ListenerUtil.mutListener.listen(18230) ? (tElapsed - newCard.getIvl()) : (tElapsed + newCard.getIvl()))))), (ListenerUtil.mutListener.listen(18237) ? (prob % outcomeProb) : (ListenerUtil.mutListener.listen(18236) ? (prob / outcomeProb) : (ListenerUtil.mutListener.listen(18235) ? (prob - outcomeProb) : (ListenerUtil.mutListener.listen(18234) ? (prob + outcomeProb) : (prob * outcomeProb))))));
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(18169)) {
                        // Advance time to next review (max. #reviews reached for this day)
                        simulationResult.updateNInState(card, card, tElapsed, (ListenerUtil.mutListener.listen(18168) ? (tElapsed % 1) : (ListenerUtil.mutListener.listen(18167) ? (tElapsed / 1) : (ListenerUtil.mutListener.listen(18166) ? (tElapsed * 1) : (ListenerUtil.mutListener.listen(18165) ? (tElapsed - 1) : (tElapsed + 1))))), prob);
                    }
                    if (!ListenerUtil.mutListener.listen(18174)) {
                        rescheduleCurrentReview((ListenerUtil.mutListener.listen(18173) ? (tElapsed % 1) : (ListenerUtil.mutListener.listen(18172) ? (tElapsed / 1) : (ListenerUtil.mutListener.listen(18171) ? (tElapsed * 1) : (ListenerUtil.mutListener.listen(18170) ? (tElapsed - 1) : (tElapsed + 1))))));
                    }
                }
            }
        }

        private void writeLog(Card newCard, double outcomeProb) {
            String tabs = "";
            if (!ListenerUtil.mutListener.listen(18246)) {
                {
                    long _loopCounter361 = 0;
                    for (int d = 0; (ListenerUtil.mutListener.listen(18245) ? (d >= nPrevRevs) : (ListenerUtil.mutListener.listen(18244) ? (d <= nPrevRevs) : (ListenerUtil.mutListener.listen(18243) ? (d > nPrevRevs) : (ListenerUtil.mutListener.listen(18242) ? (d != nPrevRevs) : (ListenerUtil.mutListener.listen(18241) ? (d == nPrevRevs) : (d < nPrevRevs)))))); d++) {
                        ListenerUtil.loopListener.listen("_loopCounter361", ++_loopCounter361);
                        if (!ListenerUtil.mutListener.listen(18240)) {
                            tabs += "\t";
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(18247)) {
                Timber.d("%st=%d p=%f * %s", tabs, tElapsed, prob, outcomeProb);
            }
            if (!ListenerUtil.mutListener.listen(18248)) {
                Timber.d("%s%s", tabs, prevCard);
            }
            if (!ListenerUtil.mutListener.listen(18249)) {
                Timber.d("%s%s", tabs, newCard);
            }
        }

        /**
         * Schedule the current review at another time (will re-use current Review).
         */
        private void rescheduleCurrentReview(int newTElapsed) {
            if (!ListenerUtil.mutListener.listen(18257)) {
                if ((ListenerUtil.mutListener.listen(18254) ? (newTElapsed >= simulationResult.getnDays()) : (ListenerUtil.mutListener.listen(18253) ? (newTElapsed <= simulationResult.getnDays()) : (ListenerUtil.mutListener.listen(18252) ? (newTElapsed > simulationResult.getnDays()) : (ListenerUtil.mutListener.listen(18251) ? (newTElapsed != simulationResult.getnDays()) : (ListenerUtil.mutListener.listen(18250) ? (newTElapsed == simulationResult.getnDays()) : (newTElapsed < simulationResult.getnDays()))))))) {
                    if (!ListenerUtil.mutListener.listen(18255)) {
                        this.tElapsed = newTElapsed;
                    }
                    if (!ListenerUtil.mutListener.listen(18256)) {
                        this.reviews.push(this);
                    }
                }
            }
        }

        /**
         * Schedule the current review at the current time, but with another outcome (will re-use current Review).
         * @param newCard
         */
        private void scheduleCurrentReview(Card newCard) {
            if (!ListenerUtil.mutListener.listen(18258)) {
                this.card.setAll(newCard);
            }
            if (!ListenerUtil.mutListener.listen(18259)) {
                this.outcome++;
            }
            if (!ListenerUtil.mutListener.listen(18260)) {
                this.reviews.push(this);
            }
        }

        /**
         * Schedule next review (will not re-use current Review).
         */
        private void scheduleNextReview(Card newCard, int newTElapsed, double newProb) {
            if (!ListenerUtil.mutListener.listen(18287)) {
                // Schedule next review(s) if they are within the time window of the simulation
                if ((ListenerUtil.mutListener.listen(18265) ? (newTElapsed >= simulationResult.getnDays()) : (ListenerUtil.mutListener.listen(18264) ? (newTElapsed <= simulationResult.getnDays()) : (ListenerUtil.mutListener.listen(18263) ? (newTElapsed > simulationResult.getnDays()) : (ListenerUtil.mutListener.listen(18262) ? (newTElapsed != simulationResult.getnDays()) : (ListenerUtil.mutListener.listen(18261) ? (newTElapsed == simulationResult.getnDays()) : (newTElapsed < simulationResult.getnDays()))))))) {
                    Review review;
                    // This is possible since reviews with nPrevRevs > nPrevRevs of the current review which were already scheduled have all already been processed before we do the current review.
                    if ((ListenerUtil.mutListener.listen(18270) ? (reviewList.size() >= nPrevRevs) : (ListenerUtil.mutListener.listen(18269) ? (reviewList.size() <= nPrevRevs) : (ListenerUtil.mutListener.listen(18268) ? (reviewList.size() < nPrevRevs) : (ListenerUtil.mutListener.listen(18267) ? (reviewList.size() != nPrevRevs) : (ListenerUtil.mutListener.listen(18266) ? (reviewList.size() == nPrevRevs) : (reviewList.size() > nPrevRevs))))))) {
                        review = reviewList.get(nPrevRevs);
                        if (!ListenerUtil.mutListener.listen(18285)) {
                            review.existingCard(newCard, (ListenerUtil.mutListener.listen(18284) ? (nPrevRevs % 1) : (ListenerUtil.mutListener.listen(18283) ? (nPrevRevs / 1) : (ListenerUtil.mutListener.listen(18282) ? (nPrevRevs * 1) : (ListenerUtil.mutListener.listen(18281) ? (nPrevRevs - 1) : (nPrevRevs + 1))))), newTElapsed, newProb);
                        }
                    } else {
                        if ((ListenerUtil.mutListener.listen(18275) ? (reviewList.size() >= nPrevRevs) : (ListenerUtil.mutListener.listen(18274) ? (reviewList.size() <= nPrevRevs) : (ListenerUtil.mutListener.listen(18273) ? (reviewList.size() > nPrevRevs) : (ListenerUtil.mutListener.listen(18272) ? (reviewList.size() < nPrevRevs) : (ListenerUtil.mutListener.listen(18271) ? (reviewList.size() != nPrevRevs) : (reviewList.size() == nPrevRevs))))))) {
                            review = new Review(this, newCard, (ListenerUtil.mutListener.listen(18279) ? (nPrevRevs % 1) : (ListenerUtil.mutListener.listen(18278) ? (nPrevRevs / 1) : (ListenerUtil.mutListener.listen(18277) ? (nPrevRevs * 1) : (ListenerUtil.mutListener.listen(18276) ? (nPrevRevs - 1) : (nPrevRevs + 1))))), newTElapsed, newProb);
                            if (!ListenerUtil.mutListener.listen(18280)) {
                                reviewList.add(review);
                            }
                        } else {
                            throw new IllegalStateException("State of previous reviews of this card should have been saved for determining possible future reviews other than the current one.");
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(18286)) {
                        this.reviews.push(review);
                    }
                }
            }
        }

        public int getT() {
            return tElapsed;
        }
    }
}
