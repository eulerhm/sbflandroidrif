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
import android.webkit.WebView;
import com.ichi2.anki.R;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.Consts;
import com.ichi2.libanki.stats.Stats;
import com.ichi2.libanki.Utils;
import com.ichi2.themes.Themes;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class OverviewStatsBuilder {

    private static final int CARDS_INDEX = 0;

    private static final int THETIME_INDEX = 1;

    private static final int FAILED_INDEX = 2;

    private static final int LRN_INDEX = 3;

    private static final int REV_INDEX = 4;

    private static final int RELRN_INDEX = 5;

    private static final int FILT_INDEX = 6;

    private static final int MCNT_INDEX = 7;

    private static final int MSUM_INDEX = 8;

    // for resources access
    private final WebView mWebView;

    private final Collection mCol;

    private final long mDeckId;

    private final Stats.AxisType mType;

    public static class OverviewStats {

        public int forecastTotalReviews;

        public double forecastAverageReviews;

        public int forecastDueTomorrow;

        public double reviewsPerDayOnAll;

        public double reviewsPerDayOnStudyDays;

        public int allDays;

        public int daysStudied;

        public double timePerDayOnAll;

        public double timePerDayOnStudyDays;

        public double totalTime;

        public int totalReviews;

        public double newCardsPerDay;

        public int totalNewCards;

        public double averageInterval;

        public double longestInterval;

        public AnswerButtonsOverview newCardsOverview;

        public AnswerButtonsOverview youngCardsOverview;

        public AnswerButtonsOverview matureCardsOverview;

        public long totalCards;

        public long totalNotes;

        public double lowestEase;

        public double averageEase;

        public double highestEase;

        public static class AnswerButtonsOverview {

            public int total;

            public int correct;

            public double getPercentage() {
                if (!ListenerUtil.mutListener.listen(3678)) {
                    if ((ListenerUtil.mutListener.listen(3677) ? (correct >= 0) : (ListenerUtil.mutListener.listen(3676) ? (correct <= 0) : (ListenerUtil.mutListener.listen(3675) ? (correct > 0) : (ListenerUtil.mutListener.listen(3674) ? (correct < 0) : (ListenerUtil.mutListener.listen(3673) ? (correct != 0) : (correct == 0))))))) {
                        return 0;
                    }
                }
                return (ListenerUtil.mutListener.listen(3686) ? ((ListenerUtil.mutListener.listen(3682) ? ((double) correct % (double) total) : (ListenerUtil.mutListener.listen(3681) ? ((double) correct * (double) total) : (ListenerUtil.mutListener.listen(3680) ? ((double) correct - (double) total) : (ListenerUtil.mutListener.listen(3679) ? ((double) correct + (double) total) : ((double) correct / (double) total))))) % 100.0) : (ListenerUtil.mutListener.listen(3685) ? ((ListenerUtil.mutListener.listen(3682) ? ((double) correct % (double) total) : (ListenerUtil.mutListener.listen(3681) ? ((double) correct * (double) total) : (ListenerUtil.mutListener.listen(3680) ? ((double) correct - (double) total) : (ListenerUtil.mutListener.listen(3679) ? ((double) correct + (double) total) : ((double) correct / (double) total))))) / 100.0) : (ListenerUtil.mutListener.listen(3684) ? ((ListenerUtil.mutListener.listen(3682) ? ((double) correct % (double) total) : (ListenerUtil.mutListener.listen(3681) ? ((double) correct * (double) total) : (ListenerUtil.mutListener.listen(3680) ? ((double) correct - (double) total) : (ListenerUtil.mutListener.listen(3679) ? ((double) correct + (double) total) : ((double) correct / (double) total))))) - 100.0) : (ListenerUtil.mutListener.listen(3683) ? ((ListenerUtil.mutListener.listen(3682) ? ((double) correct % (double) total) : (ListenerUtil.mutListener.listen(3681) ? ((double) correct * (double) total) : (ListenerUtil.mutListener.listen(3680) ? ((double) correct - (double) total) : (ListenerUtil.mutListener.listen(3679) ? ((double) correct + (double) total) : ((double) correct / (double) total))))) + 100.0) : ((ListenerUtil.mutListener.listen(3682) ? ((double) correct % (double) total) : (ListenerUtil.mutListener.listen(3681) ? ((double) correct * (double) total) : (ListenerUtil.mutListener.listen(3680) ? ((double) correct - (double) total) : (ListenerUtil.mutListener.listen(3679) ? ((double) correct + (double) total) : ((double) correct / (double) total))))) * 100.0)))));
            }
        }
    }

    public OverviewStatsBuilder(WebView chartView, Collection collectionData, long deckId, Stats.AxisType mStatType) {
        mWebView = chartView;
        mCol = collectionData;
        mDeckId = deckId;
        mType = mStatType;
    }

    public String createInfoHtmlString() {
        int textColorInt = Themes.getColorFromAttr(mWebView.getContext(), android.R.attr.textColor);
        // Color to hex string
        String textColor = String.format("#%06X", (0xFFFFFF & textColorInt));
        String css = "<style>\n" + "h1, h3 { margin-bottom: 0; margin-top: 1em; text-transform: capitalize; }\n" + ".pielabel { text-align:center; padding:0px; color:white; }\n" + "body {color:" + textColor + ";}\n" + "</style>";
        StringBuilder stringBuilder = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(3687)) {
            stringBuilder.append("<center>");
        }
        if (!ListenerUtil.mutListener.listen(3688)) {
            stringBuilder.append(css);
        }
        if (!ListenerUtil.mutListener.listen(3689)) {
            appendTodaysStats(stringBuilder);
        }
        if (!ListenerUtil.mutListener.listen(3690)) {
            appendOverViewStats(stringBuilder);
        }
        if (!ListenerUtil.mutListener.listen(3691)) {
            stringBuilder.append("</center>");
        }
        return stringBuilder.toString();
    }

    private void appendOverViewStats(StringBuilder stringBuilder) {
        Stats stats = new Stats(mCol, mDeckId);
        OverviewStats oStats = new OverviewStats();
        if (!ListenerUtil.mutListener.listen(3692)) {
            stats.calculateOverviewStatistics(mType, oStats);
        }
        Resources res = mWebView.getResources();
        if (!ListenerUtil.mutListener.listen(3693)) {
            stringBuilder.append(_title(res.getString(mType.descriptionId)));
        }
        boolean allDaysStudied = (ListenerUtil.mutListener.listen(3698) ? (oStats.daysStudied >= oStats.allDays) : (ListenerUtil.mutListener.listen(3697) ? (oStats.daysStudied <= oStats.allDays) : (ListenerUtil.mutListener.listen(3696) ? (oStats.daysStudied > oStats.allDays) : (ListenerUtil.mutListener.listen(3695) ? (oStats.daysStudied < oStats.allDays) : (ListenerUtil.mutListener.listen(3694) ? (oStats.daysStudied != oStats.allDays) : (oStats.daysStudied == oStats.allDays))))));
        String daysStudied = res.getString(R.string.stats_overview_days_studied, (int) ((ListenerUtil.mutListener.listen(3706) ? ((ListenerUtil.mutListener.listen(3702) ? ((float) oStats.daysStudied % (float) oStats.allDays) : (ListenerUtil.mutListener.listen(3701) ? ((float) oStats.daysStudied * (float) oStats.allDays) : (ListenerUtil.mutListener.listen(3700) ? ((float) oStats.daysStudied - (float) oStats.allDays) : (ListenerUtil.mutListener.listen(3699) ? ((float) oStats.daysStudied + (float) oStats.allDays) : ((float) oStats.daysStudied / (float) oStats.allDays))))) % 100) : (ListenerUtil.mutListener.listen(3705) ? ((ListenerUtil.mutListener.listen(3702) ? ((float) oStats.daysStudied % (float) oStats.allDays) : (ListenerUtil.mutListener.listen(3701) ? ((float) oStats.daysStudied * (float) oStats.allDays) : (ListenerUtil.mutListener.listen(3700) ? ((float) oStats.daysStudied - (float) oStats.allDays) : (ListenerUtil.mutListener.listen(3699) ? ((float) oStats.daysStudied + (float) oStats.allDays) : ((float) oStats.daysStudied / (float) oStats.allDays))))) / 100) : (ListenerUtil.mutListener.listen(3704) ? ((ListenerUtil.mutListener.listen(3702) ? ((float) oStats.daysStudied % (float) oStats.allDays) : (ListenerUtil.mutListener.listen(3701) ? ((float) oStats.daysStudied * (float) oStats.allDays) : (ListenerUtil.mutListener.listen(3700) ? ((float) oStats.daysStudied - (float) oStats.allDays) : (ListenerUtil.mutListener.listen(3699) ? ((float) oStats.daysStudied + (float) oStats.allDays) : ((float) oStats.daysStudied / (float) oStats.allDays))))) - 100) : (ListenerUtil.mutListener.listen(3703) ? ((ListenerUtil.mutListener.listen(3702) ? ((float) oStats.daysStudied % (float) oStats.allDays) : (ListenerUtil.mutListener.listen(3701) ? ((float) oStats.daysStudied * (float) oStats.allDays) : (ListenerUtil.mutListener.listen(3700) ? ((float) oStats.daysStudied - (float) oStats.allDays) : (ListenerUtil.mutListener.listen(3699) ? ((float) oStats.daysStudied + (float) oStats.allDays) : ((float) oStats.daysStudied / (float) oStats.allDays))))) + 100) : ((ListenerUtil.mutListener.listen(3702) ? ((float) oStats.daysStudied % (float) oStats.allDays) : (ListenerUtil.mutListener.listen(3701) ? ((float) oStats.daysStudied * (float) oStats.allDays) : (ListenerUtil.mutListener.listen(3700) ? ((float) oStats.daysStudied - (float) oStats.allDays) : (ListenerUtil.mutListener.listen(3699) ? ((float) oStats.daysStudied + (float) oStats.allDays) : ((float) oStats.daysStudied / (float) oStats.allDays))))) * 100)))))), oStats.daysStudied, oStats.allDays);
        if (!ListenerUtil.mutListener.listen(3707)) {
            // Fill in the forecast summaries first
            calculateForecastOverview(mType, oStats);
        }
        Locale l = Locale.getDefault();
        if (!ListenerUtil.mutListener.listen(3708)) {
            stringBuilder.append(_subtitle(res.getString(R.string.stats_forecast).toUpperCase(l)));
        }
        if (!ListenerUtil.mutListener.listen(3709)) {
            stringBuilder.append(res.getString(R.string.stats_overview_forecast_total, oStats.forecastTotalReviews));
        }
        if (!ListenerUtil.mutListener.listen(3710)) {
            stringBuilder.append("<br>");
        }
        if (!ListenerUtil.mutListener.listen(3711)) {
            stringBuilder.append(res.getString(R.string.stats_overview_forecast_average, oStats.forecastAverageReviews));
        }
        if (!ListenerUtil.mutListener.listen(3712)) {
            stringBuilder.append("<br>");
        }
        if (!ListenerUtil.mutListener.listen(3713)) {
            stringBuilder.append(res.getString(R.string.stats_overview_forecast_due_tomorrow, oStats.forecastDueTomorrow));
        }
        if (!ListenerUtil.mutListener.listen(3714)) {
            stringBuilder.append("<br>");
        }
        if (!ListenerUtil.mutListener.listen(3715)) {
            // REVIEW COUNT
            stringBuilder.append(_subtitle(res.getString(R.string.stats_review_count).toUpperCase(l)));
        }
        if (!ListenerUtil.mutListener.listen(3716)) {
            stringBuilder.append(daysStudied);
        }
        if (!ListenerUtil.mutListener.listen(3717)) {
            stringBuilder.append("<br>");
        }
        if (!ListenerUtil.mutListener.listen(3718)) {
            stringBuilder.append(res.getString(R.string.stats_overview_forecast_total, oStats.totalReviews));
        }
        if (!ListenerUtil.mutListener.listen(3719)) {
            stringBuilder.append("<br>");
        }
        if (!ListenerUtil.mutListener.listen(3720)) {
            stringBuilder.append(res.getString(R.string.stats_overview_reviews_per_day_studydays, oStats.reviewsPerDayOnStudyDays));
        }
        if (!ListenerUtil.mutListener.listen(3723)) {
            if (!allDaysStudied) {
                if (!ListenerUtil.mutListener.listen(3721)) {
                    stringBuilder.append("<br>");
                }
                if (!ListenerUtil.mutListener.listen(3722)) {
                    stringBuilder.append(res.getString(R.string.stats_overview_reviews_per_day_all, oStats.reviewsPerDayOnAll));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3724)) {
            stringBuilder.append("<br>");
        }
        if (!ListenerUtil.mutListener.listen(3725)) {
            // REVIEW TIME
            stringBuilder.append(_subtitle(res.getString(R.string.stats_review_time).toUpperCase(l)));
        }
        if (!ListenerUtil.mutListener.listen(3726)) {
            stringBuilder.append(daysStudied);
        }
        if (!ListenerUtil.mutListener.listen(3727)) {
            stringBuilder.append("<br>");
        }
        if (!ListenerUtil.mutListener.listen(3728)) {
            // TODO: Anki Desktop allows changing to hours / days here.
            stringBuilder.append(res.getString(R.string.stats_overview_total_time_in_period, Math.round(oStats.totalTime)));
        }
        if (!ListenerUtil.mutListener.listen(3729)) {
            stringBuilder.append("<br>");
        }
        if (!ListenerUtil.mutListener.listen(3730)) {
            stringBuilder.append(res.getString(R.string.stats_overview_time_per_day_studydays, oStats.timePerDayOnStudyDays));
        }
        if (!ListenerUtil.mutListener.listen(3733)) {
            if (!allDaysStudied) {
                if (!ListenerUtil.mutListener.listen(3731)) {
                    stringBuilder.append("<br>");
                }
                if (!ListenerUtil.mutListener.listen(3732)) {
                    stringBuilder.append(res.getString(R.string.stats_overview_time_per_day_all, oStats.timePerDayOnAll));
                }
            }
        }
        double cardsPerMinute = (ListenerUtil.mutListener.listen(3738) ? (oStats.totalTime >= 0) : (ListenerUtil.mutListener.listen(3737) ? (oStats.totalTime <= 0) : (ListenerUtil.mutListener.listen(3736) ? (oStats.totalTime > 0) : (ListenerUtil.mutListener.listen(3735) ? (oStats.totalTime < 0) : (ListenerUtil.mutListener.listen(3734) ? (oStats.totalTime != 0) : (oStats.totalTime == 0)))))) ? 0 : (ListenerUtil.mutListener.listen(3742) ? (((double) oStats.totalReviews) % oStats.totalTime) : (ListenerUtil.mutListener.listen(3741) ? (((double) oStats.totalReviews) * oStats.totalTime) : (ListenerUtil.mutListener.listen(3740) ? (((double) oStats.totalReviews) - oStats.totalTime) : (ListenerUtil.mutListener.listen(3739) ? (((double) oStats.totalReviews) + oStats.totalTime) : (((double) oStats.totalReviews) / oStats.totalTime)))));
        double averageAnswerTime = (ListenerUtil.mutListener.listen(3747) ? (oStats.totalReviews >= 0) : (ListenerUtil.mutListener.listen(3746) ? (oStats.totalReviews <= 0) : (ListenerUtil.mutListener.listen(3745) ? (oStats.totalReviews > 0) : (ListenerUtil.mutListener.listen(3744) ? (oStats.totalReviews < 0) : (ListenerUtil.mutListener.listen(3743) ? (oStats.totalReviews != 0) : (oStats.totalReviews == 0)))))) ? 0 : (ListenerUtil.mutListener.listen(3755) ? (((ListenerUtil.mutListener.listen(3751) ? (oStats.totalTime % 60) : (ListenerUtil.mutListener.listen(3750) ? (oStats.totalTime / 60) : (ListenerUtil.mutListener.listen(3749) ? (oStats.totalTime - 60) : (ListenerUtil.mutListener.listen(3748) ? (oStats.totalTime + 60) : (oStats.totalTime * 60)))))) % ((double) oStats.totalReviews)) : (ListenerUtil.mutListener.listen(3754) ? (((ListenerUtil.mutListener.listen(3751) ? (oStats.totalTime % 60) : (ListenerUtil.mutListener.listen(3750) ? (oStats.totalTime / 60) : (ListenerUtil.mutListener.listen(3749) ? (oStats.totalTime - 60) : (ListenerUtil.mutListener.listen(3748) ? (oStats.totalTime + 60) : (oStats.totalTime * 60)))))) * ((double) oStats.totalReviews)) : (ListenerUtil.mutListener.listen(3753) ? (((ListenerUtil.mutListener.listen(3751) ? (oStats.totalTime % 60) : (ListenerUtil.mutListener.listen(3750) ? (oStats.totalTime / 60) : (ListenerUtil.mutListener.listen(3749) ? (oStats.totalTime - 60) : (ListenerUtil.mutListener.listen(3748) ? (oStats.totalTime + 60) : (oStats.totalTime * 60)))))) - ((double) oStats.totalReviews)) : (ListenerUtil.mutListener.listen(3752) ? (((ListenerUtil.mutListener.listen(3751) ? (oStats.totalTime % 60) : (ListenerUtil.mutListener.listen(3750) ? (oStats.totalTime / 60) : (ListenerUtil.mutListener.listen(3749) ? (oStats.totalTime - 60) : (ListenerUtil.mutListener.listen(3748) ? (oStats.totalTime + 60) : (oStats.totalTime * 60)))))) + ((double) oStats.totalReviews)) : (((ListenerUtil.mutListener.listen(3751) ? (oStats.totalTime % 60) : (ListenerUtil.mutListener.listen(3750) ? (oStats.totalTime / 60) : (ListenerUtil.mutListener.listen(3749) ? (oStats.totalTime - 60) : (ListenerUtil.mutListener.listen(3748) ? (oStats.totalTime + 60) : (oStats.totalTime * 60)))))) / ((double) oStats.totalReviews))))));
        if (!ListenerUtil.mutListener.listen(3756)) {
            stringBuilder.append("<br>");
        }
        if (!ListenerUtil.mutListener.listen(3757)) {
            stringBuilder.append(res.getString(R.string.stats_overview_average_answer_time, averageAnswerTime, cardsPerMinute));
        }
        if (!ListenerUtil.mutListener.listen(3758)) {
            stringBuilder.append("<br>");
        }
        if (!ListenerUtil.mutListener.listen(3759)) {
            // ADDED
            stringBuilder.append(_subtitle(res.getString(R.string.stats_added).toUpperCase(l)));
        }
        if (!ListenerUtil.mutListener.listen(3760)) {
            stringBuilder.append(res.getString(R.string.stats_overview_total_new_cards, oStats.totalNewCards));
        }
        if (!ListenerUtil.mutListener.listen(3761)) {
            stringBuilder.append("<br>");
        }
        if (!ListenerUtil.mutListener.listen(3762)) {
            stringBuilder.append(res.getString(R.string.stats_overview_new_cards_per_day, oStats.newCardsPerDay));
        }
        if (!ListenerUtil.mutListener.listen(3763)) {
            stringBuilder.append("<br>");
        }
        if (!ListenerUtil.mutListener.listen(3764)) {
            // INTERVALS
            stringBuilder.append(_subtitle(res.getString(R.string.stats_review_intervals).toUpperCase(l)));
        }
        if (!ListenerUtil.mutListener.listen(3765)) {
            stringBuilder.append(res.getString(R.string.stats_overview_average_interval));
        }
        if (!ListenerUtil.mutListener.listen(3770)) {
            stringBuilder.append(Utils.roundedTimeSpan(mWebView.getContext(), (int) Math.round((ListenerUtil.mutListener.listen(3769) ? (oStats.averageInterval % Stats.SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(3768) ? (oStats.averageInterval / Stats.SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(3767) ? (oStats.averageInterval - Stats.SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(3766) ? (oStats.averageInterval + Stats.SECONDS_PER_DAY) : (oStats.averageInterval * Stats.SECONDS_PER_DAY))))))));
        }
        if (!ListenerUtil.mutListener.listen(3771)) {
            stringBuilder.append("<br>");
        }
        if (!ListenerUtil.mutListener.listen(3772)) {
            stringBuilder.append(res.getString(R.string.stats_overview_longest_interval));
        }
        if (!ListenerUtil.mutListener.listen(3777)) {
            stringBuilder.append(Utils.roundedTimeSpan(mWebView.getContext(), (int) Math.round((ListenerUtil.mutListener.listen(3776) ? (oStats.longestInterval % Stats.SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(3775) ? (oStats.longestInterval / Stats.SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(3774) ? (oStats.longestInterval - Stats.SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(3773) ? (oStats.longestInterval + Stats.SECONDS_PER_DAY) : (oStats.longestInterval * Stats.SECONDS_PER_DAY))))))));
        }
        if (!ListenerUtil.mutListener.listen(3778)) {
            // ANSWER BUTTONS
            stringBuilder.append(_subtitle(res.getString(R.string.stats_answer_buttons).toUpperCase(l)));
        }
        if (!ListenerUtil.mutListener.listen(3779)) {
            stringBuilder.append(res.getString(R.string.stats_overview_answer_buttons_learn, oStats.newCardsOverview.getPercentage(), oStats.newCardsOverview.correct, oStats.newCardsOverview.total));
        }
        if (!ListenerUtil.mutListener.listen(3780)) {
            stringBuilder.append("<br>");
        }
        if (!ListenerUtil.mutListener.listen(3781)) {
            stringBuilder.append(res.getString(R.string.stats_overview_answer_buttons_young, oStats.youngCardsOverview.getPercentage(), oStats.youngCardsOverview.correct, oStats.youngCardsOverview.total));
        }
        if (!ListenerUtil.mutListener.listen(3782)) {
            stringBuilder.append("<br>");
        }
        if (!ListenerUtil.mutListener.listen(3783)) {
            stringBuilder.append(res.getString(R.string.stats_overview_answer_buttons_mature, oStats.matureCardsOverview.getPercentage(), oStats.matureCardsOverview.correct, oStats.matureCardsOverview.total));
        }
        if (!ListenerUtil.mutListener.listen(3784)) {
            // CARD TYPES
            stringBuilder.append(_subtitle(res.getString(R.string.title_activity_template_editor).toUpperCase(l)));
        }
        if (!ListenerUtil.mutListener.listen(3785)) {
            stringBuilder.append(res.getString(R.string.stats_overview_card_types_total_cards, oStats.totalCards));
        }
        if (!ListenerUtil.mutListener.listen(3786)) {
            stringBuilder.append("<br>");
        }
        if (!ListenerUtil.mutListener.listen(3787)) {
            stringBuilder.append(res.getString(R.string.stats_overview_card_types_total_notes, oStats.totalNotes));
        }
        if (!ListenerUtil.mutListener.listen(3788)) {
            stringBuilder.append("<br>");
        }
        if (!ListenerUtil.mutListener.listen(3789)) {
            stringBuilder.append(res.getString(R.string.stats_overview_card_types_lowest_ease, oStats.lowestEase));
        }
        if (!ListenerUtil.mutListener.listen(3790)) {
            stringBuilder.append("<br>");
        }
        if (!ListenerUtil.mutListener.listen(3791)) {
            stringBuilder.append(res.getString(R.string.stats_overview_card_types_average_ease, oStats.averageEase));
        }
        if (!ListenerUtil.mutListener.listen(3792)) {
            stringBuilder.append("<br>");
        }
        if (!ListenerUtil.mutListener.listen(3793)) {
            stringBuilder.append(res.getString(R.string.stats_overview_card_types_highest_ease, oStats.highestEase));
        }
    }

    private void appendTodaysStats(StringBuilder stringBuilder) {
        Stats stats = new Stats(mCol, mDeckId);
        int[] todayStats = stats.calculateTodayStats();
        if (!ListenerUtil.mutListener.listen(3794)) {
            stringBuilder.append(_title(mWebView.getResources().getString(R.string.stats_today)));
        }
        Resources res = mWebView.getResources();
        final int minutes = (int) Math.round((ListenerUtil.mutListener.listen(3798) ? (todayStats[THETIME_INDEX] % 60.0) : (ListenerUtil.mutListener.listen(3797) ? (todayStats[THETIME_INDEX] * 60.0) : (ListenerUtil.mutListener.listen(3796) ? (todayStats[THETIME_INDEX] - 60.0) : (ListenerUtil.mutListener.listen(3795) ? (todayStats[THETIME_INDEX] + 60.0) : (todayStats[THETIME_INDEX] / 60.0))))));
        final String span = res.getQuantityString(R.plurals.time_span_minutes, minutes, minutes);
        if (!ListenerUtil.mutListener.listen(3799)) {
            stringBuilder.append(res.getQuantityString(R.plurals.stats_today_cards, todayStats[CARDS_INDEX], todayStats[CARDS_INDEX], span));
        }
        if (!ListenerUtil.mutListener.listen(3800)) {
            stringBuilder.append("<br>");
        }
        if (!ListenerUtil.mutListener.listen(3801)) {
            stringBuilder.append(res.getString(R.string.stats_today_again_count, todayStats[FAILED_INDEX]));
        }
        if (!ListenerUtil.mutListener.listen(3821)) {
            if ((ListenerUtil.mutListener.listen(3806) ? (todayStats[CARDS_INDEX] >= 0) : (ListenerUtil.mutListener.listen(3805) ? (todayStats[CARDS_INDEX] <= 0) : (ListenerUtil.mutListener.listen(3804) ? (todayStats[CARDS_INDEX] < 0) : (ListenerUtil.mutListener.listen(3803) ? (todayStats[CARDS_INDEX] != 0) : (ListenerUtil.mutListener.listen(3802) ? (todayStats[CARDS_INDEX] == 0) : (todayStats[CARDS_INDEX] > 0))))))) {
                if (!ListenerUtil.mutListener.listen(3807)) {
                    stringBuilder.append(" ");
                }
                if (!ListenerUtil.mutListener.listen(3820)) {
                    stringBuilder.append(res.getString(R.string.stats_today_correct_count, (((ListenerUtil.mutListener.listen(3819) ? (((ListenerUtil.mutListener.listen(3815) ? (1 % (ListenerUtil.mutListener.listen(3811) ? (todayStats[FAILED_INDEX] % (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3810) ? (todayStats[FAILED_INDEX] * (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3809) ? (todayStats[FAILED_INDEX] - (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3808) ? (todayStats[FAILED_INDEX] + (float) (todayStats[CARDS_INDEX])) : (todayStats[FAILED_INDEX] / (float) (todayStats[CARDS_INDEX]))))))) : (ListenerUtil.mutListener.listen(3814) ? (1 / (ListenerUtil.mutListener.listen(3811) ? (todayStats[FAILED_INDEX] % (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3810) ? (todayStats[FAILED_INDEX] * (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3809) ? (todayStats[FAILED_INDEX] - (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3808) ? (todayStats[FAILED_INDEX] + (float) (todayStats[CARDS_INDEX])) : (todayStats[FAILED_INDEX] / (float) (todayStats[CARDS_INDEX]))))))) : (ListenerUtil.mutListener.listen(3813) ? (1 * (ListenerUtil.mutListener.listen(3811) ? (todayStats[FAILED_INDEX] % (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3810) ? (todayStats[FAILED_INDEX] * (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3809) ? (todayStats[FAILED_INDEX] - (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3808) ? (todayStats[FAILED_INDEX] + (float) (todayStats[CARDS_INDEX])) : (todayStats[FAILED_INDEX] / (float) (todayStats[CARDS_INDEX]))))))) : (ListenerUtil.mutListener.listen(3812) ? (1 + (ListenerUtil.mutListener.listen(3811) ? (todayStats[FAILED_INDEX] % (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3810) ? (todayStats[FAILED_INDEX] * (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3809) ? (todayStats[FAILED_INDEX] - (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3808) ? (todayStats[FAILED_INDEX] + (float) (todayStats[CARDS_INDEX])) : (todayStats[FAILED_INDEX] / (float) (todayStats[CARDS_INDEX]))))))) : (1 - (ListenerUtil.mutListener.listen(3811) ? (todayStats[FAILED_INDEX] % (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3810) ? (todayStats[FAILED_INDEX] * (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3809) ? (todayStats[FAILED_INDEX] - (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3808) ? (todayStats[FAILED_INDEX] + (float) (todayStats[CARDS_INDEX])) : (todayStats[FAILED_INDEX] / (float) (todayStats[CARDS_INDEX])))))))))))) % 100.0) : (ListenerUtil.mutListener.listen(3818) ? (((ListenerUtil.mutListener.listen(3815) ? (1 % (ListenerUtil.mutListener.listen(3811) ? (todayStats[FAILED_INDEX] % (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3810) ? (todayStats[FAILED_INDEX] * (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3809) ? (todayStats[FAILED_INDEX] - (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3808) ? (todayStats[FAILED_INDEX] + (float) (todayStats[CARDS_INDEX])) : (todayStats[FAILED_INDEX] / (float) (todayStats[CARDS_INDEX]))))))) : (ListenerUtil.mutListener.listen(3814) ? (1 / (ListenerUtil.mutListener.listen(3811) ? (todayStats[FAILED_INDEX] % (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3810) ? (todayStats[FAILED_INDEX] * (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3809) ? (todayStats[FAILED_INDEX] - (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3808) ? (todayStats[FAILED_INDEX] + (float) (todayStats[CARDS_INDEX])) : (todayStats[FAILED_INDEX] / (float) (todayStats[CARDS_INDEX]))))))) : (ListenerUtil.mutListener.listen(3813) ? (1 * (ListenerUtil.mutListener.listen(3811) ? (todayStats[FAILED_INDEX] % (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3810) ? (todayStats[FAILED_INDEX] * (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3809) ? (todayStats[FAILED_INDEX] - (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3808) ? (todayStats[FAILED_INDEX] + (float) (todayStats[CARDS_INDEX])) : (todayStats[FAILED_INDEX] / (float) (todayStats[CARDS_INDEX]))))))) : (ListenerUtil.mutListener.listen(3812) ? (1 + (ListenerUtil.mutListener.listen(3811) ? (todayStats[FAILED_INDEX] % (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3810) ? (todayStats[FAILED_INDEX] * (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3809) ? (todayStats[FAILED_INDEX] - (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3808) ? (todayStats[FAILED_INDEX] + (float) (todayStats[CARDS_INDEX])) : (todayStats[FAILED_INDEX] / (float) (todayStats[CARDS_INDEX]))))))) : (1 - (ListenerUtil.mutListener.listen(3811) ? (todayStats[FAILED_INDEX] % (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3810) ? (todayStats[FAILED_INDEX] * (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3809) ? (todayStats[FAILED_INDEX] - (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3808) ? (todayStats[FAILED_INDEX] + (float) (todayStats[CARDS_INDEX])) : (todayStats[FAILED_INDEX] / (float) (todayStats[CARDS_INDEX])))))))))))) / 100.0) : (ListenerUtil.mutListener.listen(3817) ? (((ListenerUtil.mutListener.listen(3815) ? (1 % (ListenerUtil.mutListener.listen(3811) ? (todayStats[FAILED_INDEX] % (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3810) ? (todayStats[FAILED_INDEX] * (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3809) ? (todayStats[FAILED_INDEX] - (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3808) ? (todayStats[FAILED_INDEX] + (float) (todayStats[CARDS_INDEX])) : (todayStats[FAILED_INDEX] / (float) (todayStats[CARDS_INDEX]))))))) : (ListenerUtil.mutListener.listen(3814) ? (1 / (ListenerUtil.mutListener.listen(3811) ? (todayStats[FAILED_INDEX] % (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3810) ? (todayStats[FAILED_INDEX] * (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3809) ? (todayStats[FAILED_INDEX] - (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3808) ? (todayStats[FAILED_INDEX] + (float) (todayStats[CARDS_INDEX])) : (todayStats[FAILED_INDEX] / (float) (todayStats[CARDS_INDEX]))))))) : (ListenerUtil.mutListener.listen(3813) ? (1 * (ListenerUtil.mutListener.listen(3811) ? (todayStats[FAILED_INDEX] % (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3810) ? (todayStats[FAILED_INDEX] * (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3809) ? (todayStats[FAILED_INDEX] - (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3808) ? (todayStats[FAILED_INDEX] + (float) (todayStats[CARDS_INDEX])) : (todayStats[FAILED_INDEX] / (float) (todayStats[CARDS_INDEX]))))))) : (ListenerUtil.mutListener.listen(3812) ? (1 + (ListenerUtil.mutListener.listen(3811) ? (todayStats[FAILED_INDEX] % (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3810) ? (todayStats[FAILED_INDEX] * (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3809) ? (todayStats[FAILED_INDEX] - (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3808) ? (todayStats[FAILED_INDEX] + (float) (todayStats[CARDS_INDEX])) : (todayStats[FAILED_INDEX] / (float) (todayStats[CARDS_INDEX]))))))) : (1 - (ListenerUtil.mutListener.listen(3811) ? (todayStats[FAILED_INDEX] % (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3810) ? (todayStats[FAILED_INDEX] * (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3809) ? (todayStats[FAILED_INDEX] - (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3808) ? (todayStats[FAILED_INDEX] + (float) (todayStats[CARDS_INDEX])) : (todayStats[FAILED_INDEX] / (float) (todayStats[CARDS_INDEX])))))))))))) - 100.0) : (ListenerUtil.mutListener.listen(3816) ? (((ListenerUtil.mutListener.listen(3815) ? (1 % (ListenerUtil.mutListener.listen(3811) ? (todayStats[FAILED_INDEX] % (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3810) ? (todayStats[FAILED_INDEX] * (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3809) ? (todayStats[FAILED_INDEX] - (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3808) ? (todayStats[FAILED_INDEX] + (float) (todayStats[CARDS_INDEX])) : (todayStats[FAILED_INDEX] / (float) (todayStats[CARDS_INDEX]))))))) : (ListenerUtil.mutListener.listen(3814) ? (1 / (ListenerUtil.mutListener.listen(3811) ? (todayStats[FAILED_INDEX] % (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3810) ? (todayStats[FAILED_INDEX] * (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3809) ? (todayStats[FAILED_INDEX] - (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3808) ? (todayStats[FAILED_INDEX] + (float) (todayStats[CARDS_INDEX])) : (todayStats[FAILED_INDEX] / (float) (todayStats[CARDS_INDEX]))))))) : (ListenerUtil.mutListener.listen(3813) ? (1 * (ListenerUtil.mutListener.listen(3811) ? (todayStats[FAILED_INDEX] % (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3810) ? (todayStats[FAILED_INDEX] * (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3809) ? (todayStats[FAILED_INDEX] - (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3808) ? (todayStats[FAILED_INDEX] + (float) (todayStats[CARDS_INDEX])) : (todayStats[FAILED_INDEX] / (float) (todayStats[CARDS_INDEX]))))))) : (ListenerUtil.mutListener.listen(3812) ? (1 + (ListenerUtil.mutListener.listen(3811) ? (todayStats[FAILED_INDEX] % (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3810) ? (todayStats[FAILED_INDEX] * (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3809) ? (todayStats[FAILED_INDEX] - (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3808) ? (todayStats[FAILED_INDEX] + (float) (todayStats[CARDS_INDEX])) : (todayStats[FAILED_INDEX] / (float) (todayStats[CARDS_INDEX]))))))) : (1 - (ListenerUtil.mutListener.listen(3811) ? (todayStats[FAILED_INDEX] % (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3810) ? (todayStats[FAILED_INDEX] * (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3809) ? (todayStats[FAILED_INDEX] - (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3808) ? (todayStats[FAILED_INDEX] + (float) (todayStats[CARDS_INDEX])) : (todayStats[FAILED_INDEX] / (float) (todayStats[CARDS_INDEX])))))))))))) + 100.0) : (((ListenerUtil.mutListener.listen(3815) ? (1 % (ListenerUtil.mutListener.listen(3811) ? (todayStats[FAILED_INDEX] % (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3810) ? (todayStats[FAILED_INDEX] * (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3809) ? (todayStats[FAILED_INDEX] - (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3808) ? (todayStats[FAILED_INDEX] + (float) (todayStats[CARDS_INDEX])) : (todayStats[FAILED_INDEX] / (float) (todayStats[CARDS_INDEX]))))))) : (ListenerUtil.mutListener.listen(3814) ? (1 / (ListenerUtil.mutListener.listen(3811) ? (todayStats[FAILED_INDEX] % (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3810) ? (todayStats[FAILED_INDEX] * (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3809) ? (todayStats[FAILED_INDEX] - (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3808) ? (todayStats[FAILED_INDEX] + (float) (todayStats[CARDS_INDEX])) : (todayStats[FAILED_INDEX] / (float) (todayStats[CARDS_INDEX]))))))) : (ListenerUtil.mutListener.listen(3813) ? (1 * (ListenerUtil.mutListener.listen(3811) ? (todayStats[FAILED_INDEX] % (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3810) ? (todayStats[FAILED_INDEX] * (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3809) ? (todayStats[FAILED_INDEX] - (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3808) ? (todayStats[FAILED_INDEX] + (float) (todayStats[CARDS_INDEX])) : (todayStats[FAILED_INDEX] / (float) (todayStats[CARDS_INDEX]))))))) : (ListenerUtil.mutListener.listen(3812) ? (1 + (ListenerUtil.mutListener.listen(3811) ? (todayStats[FAILED_INDEX] % (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3810) ? (todayStats[FAILED_INDEX] * (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3809) ? (todayStats[FAILED_INDEX] - (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3808) ? (todayStats[FAILED_INDEX] + (float) (todayStats[CARDS_INDEX])) : (todayStats[FAILED_INDEX] / (float) (todayStats[CARDS_INDEX]))))))) : (1 - (ListenerUtil.mutListener.listen(3811) ? (todayStats[FAILED_INDEX] % (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3810) ? (todayStats[FAILED_INDEX] * (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3809) ? (todayStats[FAILED_INDEX] - (float) (todayStats[CARDS_INDEX])) : (ListenerUtil.mutListener.listen(3808) ? (todayStats[FAILED_INDEX] + (float) (todayStats[CARDS_INDEX])) : (todayStats[FAILED_INDEX] / (float) (todayStats[CARDS_INDEX])))))))))))) * 100.0)))))))));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3822)) {
            stringBuilder.append("<br>");
        }
        if (!ListenerUtil.mutListener.listen(3823)) {
            stringBuilder.append(res.getString(R.string.stats_today_type_breakdown, todayStats[LRN_INDEX], todayStats[REV_INDEX], todayStats[RELRN_INDEX], todayStats[FILT_INDEX]));
        }
        if (!ListenerUtil.mutListener.listen(3824)) {
            stringBuilder.append("<br>");
        }
        if (!ListenerUtil.mutListener.listen(3840)) {
            if ((ListenerUtil.mutListener.listen(3829) ? (todayStats[MCNT_INDEX] >= 0) : (ListenerUtil.mutListener.listen(3828) ? (todayStats[MCNT_INDEX] <= 0) : (ListenerUtil.mutListener.listen(3827) ? (todayStats[MCNT_INDEX] > 0) : (ListenerUtil.mutListener.listen(3826) ? (todayStats[MCNT_INDEX] < 0) : (ListenerUtil.mutListener.listen(3825) ? (todayStats[MCNT_INDEX] == 0) : (todayStats[MCNT_INDEX] != 0))))))) {
                if (!ListenerUtil.mutListener.listen(3839)) {
                    stringBuilder.append(res.getString(R.string.stats_today_mature_cards, todayStats[MSUM_INDEX], todayStats[MCNT_INDEX], ((ListenerUtil.mutListener.listen(3838) ? ((ListenerUtil.mutListener.listen(3834) ? (todayStats[MSUM_INDEX] % (float) (todayStats[MCNT_INDEX])) : (ListenerUtil.mutListener.listen(3833) ? (todayStats[MSUM_INDEX] * (float) (todayStats[MCNT_INDEX])) : (ListenerUtil.mutListener.listen(3832) ? (todayStats[MSUM_INDEX] - (float) (todayStats[MCNT_INDEX])) : (ListenerUtil.mutListener.listen(3831) ? (todayStats[MSUM_INDEX] + (float) (todayStats[MCNT_INDEX])) : (todayStats[MSUM_INDEX] / (float) (todayStats[MCNT_INDEX])))))) % 100.0) : (ListenerUtil.mutListener.listen(3837) ? ((ListenerUtil.mutListener.listen(3834) ? (todayStats[MSUM_INDEX] % (float) (todayStats[MCNT_INDEX])) : (ListenerUtil.mutListener.listen(3833) ? (todayStats[MSUM_INDEX] * (float) (todayStats[MCNT_INDEX])) : (ListenerUtil.mutListener.listen(3832) ? (todayStats[MSUM_INDEX] - (float) (todayStats[MCNT_INDEX])) : (ListenerUtil.mutListener.listen(3831) ? (todayStats[MSUM_INDEX] + (float) (todayStats[MCNT_INDEX])) : (todayStats[MSUM_INDEX] / (float) (todayStats[MCNT_INDEX])))))) / 100.0) : (ListenerUtil.mutListener.listen(3836) ? ((ListenerUtil.mutListener.listen(3834) ? (todayStats[MSUM_INDEX] % (float) (todayStats[MCNT_INDEX])) : (ListenerUtil.mutListener.listen(3833) ? (todayStats[MSUM_INDEX] * (float) (todayStats[MCNT_INDEX])) : (ListenerUtil.mutListener.listen(3832) ? (todayStats[MSUM_INDEX] - (float) (todayStats[MCNT_INDEX])) : (ListenerUtil.mutListener.listen(3831) ? (todayStats[MSUM_INDEX] + (float) (todayStats[MCNT_INDEX])) : (todayStats[MSUM_INDEX] / (float) (todayStats[MCNT_INDEX])))))) - 100.0) : (ListenerUtil.mutListener.listen(3835) ? ((ListenerUtil.mutListener.listen(3834) ? (todayStats[MSUM_INDEX] % (float) (todayStats[MCNT_INDEX])) : (ListenerUtil.mutListener.listen(3833) ? (todayStats[MSUM_INDEX] * (float) (todayStats[MCNT_INDEX])) : (ListenerUtil.mutListener.listen(3832) ? (todayStats[MSUM_INDEX] - (float) (todayStats[MCNT_INDEX])) : (ListenerUtil.mutListener.listen(3831) ? (todayStats[MSUM_INDEX] + (float) (todayStats[MCNT_INDEX])) : (todayStats[MSUM_INDEX] / (float) (todayStats[MCNT_INDEX])))))) + 100.0) : ((ListenerUtil.mutListener.listen(3834) ? (todayStats[MSUM_INDEX] % (float) (todayStats[MCNT_INDEX])) : (ListenerUtil.mutListener.listen(3833) ? (todayStats[MSUM_INDEX] * (float) (todayStats[MCNT_INDEX])) : (ListenerUtil.mutListener.listen(3832) ? (todayStats[MSUM_INDEX] - (float) (todayStats[MCNT_INDEX])) : (ListenerUtil.mutListener.listen(3831) ? (todayStats[MSUM_INDEX] + (float) (todayStats[MCNT_INDEX])) : (todayStats[MSUM_INDEX] / (float) (todayStats[MCNT_INDEX])))))) * 100.0))))))));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3830)) {
                    stringBuilder.append(res.getString(R.string.stats_today_no_mature_cards));
                }
            }
        }
    }

    private String _title(String title) {
        return "<h1>" + title + "</h1>";
    }

    private String _subtitle(String title) {
        return "<h3>" + title + "</h3>";
    }

    // should replace the one in Stats.java.
    private void calculateForecastOverview(Stats.AxisType type, OverviewStats oStats) {
        Integer start = null;
        Integer end = null;
        int chunk = 0;
        if (!ListenerUtil.mutListener.listen(3850)) {
            switch(type) {
                case TYPE_MONTH:
                    if (!ListenerUtil.mutListener.listen(3841)) {
                        start = 0;
                    }
                    if (!ListenerUtil.mutListener.listen(3842)) {
                        end = 31;
                    }
                    if (!ListenerUtil.mutListener.listen(3843)) {
                        chunk = 1;
                    }
                    break;
                case TYPE_YEAR:
                    if (!ListenerUtil.mutListener.listen(3844)) {
                        start = 0;
                    }
                    if (!ListenerUtil.mutListener.listen(3845)) {
                        end = 52;
                    }
                    if (!ListenerUtil.mutListener.listen(3846)) {
                        chunk = 7;
                    }
                    break;
                case TYPE_LIFE:
                    if (!ListenerUtil.mutListener.listen(3847)) {
                        start = 0;
                    }
                    if (!ListenerUtil.mutListener.listen(3848)) {
                        end = null;
                    }
                    if (!ListenerUtil.mutListener.listen(3849)) {
                        chunk = 30;
                    }
                    break;
            }
        }
        List<int[]> d = _due(start, end, chunk);
        int tot = 0;
        List<int[]> totd = new ArrayList<>(d.size());
        if (!ListenerUtil.mutListener.listen(3857)) {
            {
                long _loopCounter89 = 0;
                for (int[] day : d) {
                    ListenerUtil.loopListener.listen("_loopCounter89", ++_loopCounter89);
                    if (!ListenerUtil.mutListener.listen(3855)) {
                        tot += (ListenerUtil.mutListener.listen(3854) ? (day[1] % day[2]) : (ListenerUtil.mutListener.listen(3853) ? (day[1] / day[2]) : (ListenerUtil.mutListener.listen(3852) ? (day[1] * day[2]) : (ListenerUtil.mutListener.listen(3851) ? (day[1] - day[2]) : (day[1] + day[2])))));
                    }
                    if (!ListenerUtil.mutListener.listen(3856)) {
                        totd.add(new int[] { day[0], tot });
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3858)) {
            // Fill in the overview stats
            oStats.forecastTotalReviews = tot;
        }
        if (!ListenerUtil.mutListener.listen(3872)) {
            oStats.forecastAverageReviews = (ListenerUtil.mutListener.listen(3863) ? (totd.size() >= 0) : (ListenerUtil.mutListener.listen(3862) ? (totd.size() <= 0) : (ListenerUtil.mutListener.listen(3861) ? (totd.size() > 0) : (ListenerUtil.mutListener.listen(3860) ? (totd.size() < 0) : (ListenerUtil.mutListener.listen(3859) ? (totd.size() != 0) : (totd.size() == 0)))))) ? 0 : (ListenerUtil.mutListener.listen(3871) ? ((double) tot % ((ListenerUtil.mutListener.listen(3867) ? (totd.size() % chunk) : (ListenerUtil.mutListener.listen(3866) ? (totd.size() / chunk) : (ListenerUtil.mutListener.listen(3865) ? (totd.size() - chunk) : (ListenerUtil.mutListener.listen(3864) ? (totd.size() + chunk) : (totd.size() * chunk))))))) : (ListenerUtil.mutListener.listen(3870) ? ((double) tot * ((ListenerUtil.mutListener.listen(3867) ? (totd.size() % chunk) : (ListenerUtil.mutListener.listen(3866) ? (totd.size() / chunk) : (ListenerUtil.mutListener.listen(3865) ? (totd.size() - chunk) : (ListenerUtil.mutListener.listen(3864) ? (totd.size() + chunk) : (totd.size() * chunk))))))) : (ListenerUtil.mutListener.listen(3869) ? ((double) tot - ((ListenerUtil.mutListener.listen(3867) ? (totd.size() % chunk) : (ListenerUtil.mutListener.listen(3866) ? (totd.size() / chunk) : (ListenerUtil.mutListener.listen(3865) ? (totd.size() - chunk) : (ListenerUtil.mutListener.listen(3864) ? (totd.size() + chunk) : (totd.size() * chunk))))))) : (ListenerUtil.mutListener.listen(3868) ? ((double) tot + ((ListenerUtil.mutListener.listen(3867) ? (totd.size() % chunk) : (ListenerUtil.mutListener.listen(3866) ? (totd.size() / chunk) : (ListenerUtil.mutListener.listen(3865) ? (totd.size() - chunk) : (ListenerUtil.mutListener.listen(3864) ? (totd.size() + chunk) : (totd.size() * chunk))))))) : ((double) tot / ((ListenerUtil.mutListener.listen(3867) ? (totd.size() % chunk) : (ListenerUtil.mutListener.listen(3866) ? (totd.size() / chunk) : (ListenerUtil.mutListener.listen(3865) ? (totd.size() - chunk) : (ListenerUtil.mutListener.listen(3864) ? (totd.size() + chunk) : (totd.size() * chunk)))))))))));
        }
        if (!ListenerUtil.mutListener.listen(3877)) {
            oStats.forecastDueTomorrow = mCol.getDb().queryScalar("select count() from cards where did in " + _limit() + " and queue in (" + Consts.QUEUE_TYPE_REV + "," + Consts.QUEUE_TYPE_DAY_LEARN_RELEARN + ") " + "and due = ?", (ListenerUtil.mutListener.listen(3876) ? (mCol.getSched().getToday() % 1) : (ListenerUtil.mutListener.listen(3875) ? (mCol.getSched().getToday() / 1) : (ListenerUtil.mutListener.listen(3874) ? (mCol.getSched().getToday() * 1) : (ListenerUtil.mutListener.listen(3873) ? (mCol.getSched().getToday() - 1) : (mCol.getSched().getToday() + 1))))));
        }
    }

    private List<int[]> _due(Integer start, Integer end, int chunk) {
        String lim = "";
        if (!ListenerUtil.mutListener.listen(3879)) {
            if (start != null) {
                if (!ListenerUtil.mutListener.listen(3878)) {
                    lim += String.format(Locale.US, " and due-%d >= %d", mCol.getSched().getToday(), start);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3881)) {
            if (end != null) {
                if (!ListenerUtil.mutListener.listen(3880)) {
                    lim += String.format(Locale.US, " and day < %d", end);
                }
            }
        }
        List<int[]> d = new ArrayList<>();
        String query = "select (due-" + mCol.getSched().getToday() + ")/" + chunk + " as day,\n" + "sum(case when ivl < 21 then 1 else 0 end), -- yng\n" + "sum(case when ivl >= 21 then 1 else 0 end) -- mtr\n" + "from cards\n" + "where did in " + _limit() + " and queue in (" + Consts.QUEUE_TYPE_REV + "," + Consts.QUEUE_TYPE_DAY_LEARN_RELEARN + ")\n" + lim + "\n" + "group by day order by day";
        try (Cursor cur = mCol.getDb().query(query)) {
            if (!ListenerUtil.mutListener.listen(3883)) {
                {
                    long _loopCounter90 = 0;
                    while (cur.moveToNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter90", ++_loopCounter90);
                        if (!ListenerUtil.mutListener.listen(3882)) {
                            d.add(new int[] { cur.getInt(0), cur.getInt(1), cur.getInt(2) });
                        }
                    }
                }
            }
        }
        return d;
    }

    private String _limit() {
        return Stats.deckLimit(mDeckId, mCol);
    }
}
