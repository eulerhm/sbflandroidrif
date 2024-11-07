/*
 *  Copyright (c) 2020 David Allison <davidallisongithub@gmail.com>
 *
 *  This program is free software; you can redistribute it and/or modify it under
 *  the terms of the GNU General Public License as published by the Free Software
 *  Foundation; either version 3 of the License, or (at your option) any later
 *  version.
 *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along with
 *  this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ichi2.anki;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.ichi2.libanki.Card;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.Consts;
import com.ichi2.libanki.Model;
import com.ichi2.libanki.Models;
import com.ichi2.libanki.Utils;
import com.ichi2.ui.FixedTextView;
import com.ichi2.utils.FunctionalInterfaces;
import com.ichi2.utils.LanguageUtil;
import com.ichi2.utils.UiUtil;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import androidx.annotation.CheckResult;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import timber.log.Timber;
import static com.ichi2.libanki.stats.Stats.SECONDS_PER_DAY;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class CardInfo extends AnkiActivity {

    private static final long INVALID_CARD_ID = -1;

    private static final DateFormat sDateFormat = DateFormat.getDateInstance();

    private static final DateFormat sDateTimeFormat = DateFormat.getDateTimeInstance();

    private CardInfoModel mModel;

    private long mCardId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(6092)) {
            if (showedActivityFailedScreen(savedInstanceState)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6093)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(6094)) {
            setContentView(R.layout.card_info);
        }
        if (!ListenerUtil.mutListener.listen(6095)) {
            mCardId = getCardId(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(6098)) {
            if (!hasValidCardId()) {
                if (!ListenerUtil.mutListener.listen(6096)) {
                    UIUtils.showThemedToast(this, getString(R.string.multimedia_editor_something_wrong), false);
                }
                if (!ListenerUtil.mutListener.listen(6097)) {
                    finishWithoutAnimation();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6099)) {
            enableToolbar();
        }
        if (!ListenerUtil.mutListener.listen(6100)) {
            startLoadingCollection();
        }
    }

    @Override
    protected void onCollectionLoaded(Collection col) {
        if (!ListenerUtil.mutListener.listen(6101)) {
            super.onCollectionLoaded(col);
        }
        Card c = getCard(col);
        if (!ListenerUtil.mutListener.listen(6104)) {
            if (c == null) {
                if (!ListenerUtil.mutListener.listen(6102)) {
                    UIUtils.showThemedToast(this, getString(R.string.multimedia_editor_something_wrong), false);
                }
                if (!ListenerUtil.mutListener.listen(6103)) {
                    finishWithoutAnimation();
                }
                return;
            }
        }
        // Candidate to move to background thread - can get hundreds of rows for bad cards.
        CardInfoModel model = CardInfoModel.create(c, col);
        if (!ListenerUtil.mutListener.listen(6105)) {
            setText(R.id.card_info_added, formatDate(model.getAddedDate()));
        }
        if (!ListenerUtil.mutListener.listen(6106)) {
            setIfNotNull(model.getFirstReviewDate(), R.id.card_info_first_review, R.id.card_info_first_review_label, this::formatDate);
        }
        if (!ListenerUtil.mutListener.listen(6107)) {
            setIfNotNull(model.getLatestReviewDate(), R.id.card_info_latest_review, R.id.card_info_latest_review_label, this::formatDate);
        }
        if (!ListenerUtil.mutListener.listen(6108)) {
            setIfNotNull(model.getDue(), R.id.card_info_due, R.id.card_info_due_label, s -> s);
        }
        if (!ListenerUtil.mutListener.listen(6109)) {
            setIfNotNull(model.getInterval(), R.id.card_info_interval, R.id.card_info_interval_label, s -> getResources().getQuantityString(R.plurals.time_span_days, model.getInterval(), model.getInterval()));
        }
        if (!ListenerUtil.mutListener.listen(6110)) {
            setIfNotNull(model.getEaseInPercent(), R.id.card_info_ease, R.id.card_info_ease_label, easePercent -> formatDouble("%.0f%%", easePercent * 100));
        }
        if (!ListenerUtil.mutListener.listen(6111)) {
            setFormattedText(R.id.card_info_review_count, "%d", model.getReviews());
        }
        if (!ListenerUtil.mutListener.listen(6112)) {
            setFormattedText(R.id.card_info_lapse_count, "%d", model.getLapses());
        }
        if (!ListenerUtil.mutListener.listen(6113)) {
            setIfNotNull(model.getAverageTimeMs(), R.id.card_info_average_time, R.id.card_info_average_time_label, this::formatAsTimeSpan);
        }
        if (!ListenerUtil.mutListener.listen(6114)) {
            setIfNotNull(model.getTotalTimeMs(), R.id.card_info_total_time, R.id.card_info_total_time_label, this::formatAsTimeSpan);
        }
        if (!ListenerUtil.mutListener.listen(6115)) {
            setText(R.id.card_info_card_type, model.getCardType());
        }
        if (!ListenerUtil.mutListener.listen(6116)) {
            setText(R.id.card_info_note_type, model.getNoteType());
        }
        if (!ListenerUtil.mutListener.listen(6117)) {
            setText(R.id.card_info_deck_name, model.getDeckName());
        }
        if (!ListenerUtil.mutListener.listen(6118)) {
            setFormattedText(R.id.card_info_card_id, "%d", model.getCardId());
        }
        if (!ListenerUtil.mutListener.listen(6119)) {
            setFormattedText(R.id.card_info_note_id, "%d", model.getNoteId());
        }
        TableLayout tl = findViewById(R.id.card_info_revlog_entries);
        if (!ListenerUtil.mutListener.listen(6127)) {
            {
                long _loopCounter113 = 0;
                for (CardInfoModel.RevLogEntry entry : model.getEntries()) {
                    ListenerUtil.loopListener.listen("_loopCounter113", ++_loopCounter113);
                    TableRow row = new TableRow(this);
                    if (!ListenerUtil.mutListener.listen(6120)) {
                        addWithText(row, formatDateTime(entry.dateTime)).setGravity(Gravity.START);
                    }
                    if (!ListenerUtil.mutListener.listen(6121)) {
                        addWithText(row, entry.spannableType(this)).setGravity(Gravity.CENTER_HORIZONTAL);
                    }
                    if (!ListenerUtil.mutListener.listen(6122)) {
                        addWithText(row, entry.getRating(this)).setGravity(Gravity.CENTER_HORIZONTAL);
                    }
                    if (!ListenerUtil.mutListener.listen(6123)) {
                        addWithText(row, Utils.timeQuantityNextIvl(this, entry.intervalAsTimeSeconds())).setGravity(Gravity.START);
                    }
                    if (!ListenerUtil.mutListener.listen(6124)) {
                        addWithText(row, entry.getEase(this)).setGravity(Gravity.CENTER_HORIZONTAL);
                    }
                    if (!ListenerUtil.mutListener.listen(6125)) {
                        addWithText(row, entry.getTimeTaken()).setGravity(Gravity.END);
                    }
                    if (!ListenerUtil.mutListener.listen(6126)) {
                        tl.addView(row);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6128)) {
            this.mModel = model;
        }
    }

    private FixedTextView addWithText(TableRow row, String value) {
        return addWithText(row, new SpannableString(value));
    }

    private FixedTextView addWithText(TableRow row, Spannable value) {
        FixedTextView text = new FixedTextView(this);
        if (!ListenerUtil.mutListener.listen(6129)) {
            text.setText(value);
        }
        if (!ListenerUtil.mutListener.listen(6130)) {
            text.setTextSize(12f);
        }
        if (!ListenerUtil.mutListener.listen(6131)) {
            row.addView(text);
        }
        return text;
    }

    @NonNull
    private String formatAsTimeSpan(Long timeInMs) {
        // So, we use seconds
        return getString(R.string.time_span_decimal_seconds, String.format(getLocale(), "%.2f", (ListenerUtil.mutListener.listen(6135) ? (timeInMs % 1000d) : (ListenerUtil.mutListener.listen(6134) ? (timeInMs * 1000d) : (ListenerUtil.mutListener.listen(6133) ? (timeInMs - 1000d) : (ListenerUtil.mutListener.listen(6132) ? (timeInMs + 1000d) : (timeInMs / 1000d)))))));
    }

    private <T> void setIfNotNull(T nullableData, @IdRes int dataRes, @IdRes int labelRes, FunctionalInterfaces.Function<T, String> asString) {
        if (!ListenerUtil.mutListener.listen(6139)) {
            if (nullableData == null) {
                if (!ListenerUtil.mutListener.listen(6137)) {
                    findViewById(dataRes).setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(6138)) {
                    findViewById(labelRes).setVisibility(View.GONE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6136)) {
                    setText(dataRes, asString.apply(nullableData));
                }
            }
        }
    }

    private void setFormattedText(@IdRes int resource, String formatSpecifier, long number) {
        String text = formatLong(formatSpecifier, number);
        if (!ListenerUtil.mutListener.listen(6140)) {
            setText(resource, text);
        }
    }

    @NonNull
    private String formatLong(String formatSpecifier, long number) {
        return String.format(getLocale(), formatSpecifier, number);
    }

    @NonNull
    private String formatDouble(String formatSpecifier, double number) {
        return String.format(getLocale(), formatSpecifier, number);
    }

    private Locale getLocale() {
        return LanguageUtil.getLocaleCompat(getResources());
    }

    private void setText(@IdRes int id, String text) {
        TextView view = findViewById(id);
        if (!ListenerUtil.mutListener.listen(6141)) {
            view.setText(text);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(6142)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(6143)) {
            outState.putLong("cardId", mCardId);
        }
    }

    @SuppressLint("DirectDateInstantiation")
    private String formatDate(Long date) {
        return sDateFormat.format(new Date(date));
    }

    @SuppressLint("DirectDateInstantiation")
    private String formatDateTime(long dateTime) {
        return sDateTimeFormat.format(new Date(dateTime));
    }

    @Nullable
    private Card getCard(Collection col) {
        return col.getCard(mCardId);
    }

    private boolean hasValidCardId() {
        return (ListenerUtil.mutListener.listen(6148) ? (mCardId >= 0) : (ListenerUtil.mutListener.listen(6147) ? (mCardId <= 0) : (ListenerUtil.mutListener.listen(6146) ? (mCardId < 0) : (ListenerUtil.mutListener.listen(6145) ? (mCardId != 0) : (ListenerUtil.mutListener.listen(6144) ? (mCardId == 0) : (mCardId > 0))))));
    }

    private long getCardId(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            return savedInstanceState.getLong("cardId");
        }
        try {
            return getIntent().getLongExtra("cardId", INVALID_CARD_ID);
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(6149)) {
                Timber.w(e, "Failed to get Card Id");
            }
            return INVALID_CARD_ID;
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public CardInfoModel getModel() {
        return mModel;
    }

    public static class CardInfoModel {

        private final long mAddedDate;

        @Nullable
        private final Long mFirstReviewDate;

        @Nullable
        private final Long mLatestReviewDate;

        private final String mDue;

        @Nullable
        private final Integer mInterval;

        @Nullable
        private final Double mEaseInPercent;

        private final int mLapses;

        private final int mReviews;

        @Nullable
        private final Long mAverageTimeMs;

        @Nullable
        private final Long mTotalTimeMs;

        private final String mCardType;

        private final String mNoteType;

        private final String mDeckName;

        private final long mNoteId;

        private final List<RevLogEntry> mEntries;

        public CardInfoModel(long createdDate, @Nullable Long firstReview, @Nullable Long latestReview, String due, @Nullable Integer interval, @Nullable Double easeInPercent, int reviews, int lapses, @Nullable Long averageTime, @Nullable Long totalTime, String cardType, String noteType, String deckName, long noteId, List<RevLogEntry> entries) {
            this.mAddedDate = createdDate;
            this.mFirstReviewDate = firstReview;
            mLatestReviewDate = latestReview;
            mDue = due;
            mInterval = interval;
            mEaseInPercent = easeInPercent;
            mReviews = reviews;
            mLapses = lapses;
            mAverageTimeMs = averageTime;
            mTotalTimeMs = totalTime;
            mCardType = cardType;
            mNoteType = noteType;
            mDeckName = deckName;
            mNoteId = noteId;
            mEntries = entries;
        }

        @CheckResult
        public static CardInfoModel create(Card c, Collection collection) {
            long addedDate = c.getId();
            Long firstReview = collection.getDb().queryLongScalar("select min(id) from revlog where cid = ?", c.getId());
            if (!ListenerUtil.mutListener.listen(6156)) {
                if ((ListenerUtil.mutListener.listen(6154) ? (firstReview >= 0) : (ListenerUtil.mutListener.listen(6153) ? (firstReview <= 0) : (ListenerUtil.mutListener.listen(6152) ? (firstReview > 0) : (ListenerUtil.mutListener.listen(6151) ? (firstReview < 0) : (ListenerUtil.mutListener.listen(6150) ? (firstReview != 0) : (firstReview == 0))))))) {
                    if (!ListenerUtil.mutListener.listen(6155)) {
                        firstReview = null;
                    }
                }
            }
            Long latestReview = collection.getDb().queryLongScalar("select max(id) from revlog where cid = ?", c.getId());
            if (!ListenerUtil.mutListener.listen(6163)) {
                if ((ListenerUtil.mutListener.listen(6161) ? (latestReview >= 0) : (ListenerUtil.mutListener.listen(6160) ? (latestReview <= 0) : (ListenerUtil.mutListener.listen(6159) ? (latestReview > 0) : (ListenerUtil.mutListener.listen(6158) ? (latestReview < 0) : (ListenerUtil.mutListener.listen(6157) ? (latestReview != 0) : (latestReview == 0))))))) {
                    if (!ListenerUtil.mutListener.listen(6162)) {
                        latestReview = null;
                    }
                }
            }
            Long averageTime = collection.getDb().queryLongScalar("select avg(time) from revlog where cid = ?", c.getId());
            if (!ListenerUtil.mutListener.listen(6170)) {
                if ((ListenerUtil.mutListener.listen(6168) ? (averageTime >= 0) : (ListenerUtil.mutListener.listen(6167) ? (averageTime <= 0) : (ListenerUtil.mutListener.listen(6166) ? (averageTime > 0) : (ListenerUtil.mutListener.listen(6165) ? (averageTime < 0) : (ListenerUtil.mutListener.listen(6164) ? (averageTime != 0) : (averageTime == 0))))))) {
                    if (!ListenerUtil.mutListener.listen(6169)) {
                        averageTime = null;
                    }
                }
            }
            Long totalTime = collection.getDb().queryLongScalar("select sum(time) from revlog where cid = ?", c.getId());
            if (!ListenerUtil.mutListener.listen(6177)) {
                if ((ListenerUtil.mutListener.listen(6175) ? (totalTime >= 0) : (ListenerUtil.mutListener.listen(6174) ? (totalTime <= 0) : (ListenerUtil.mutListener.listen(6173) ? (totalTime > 0) : (ListenerUtil.mutListener.listen(6172) ? (totalTime < 0) : (ListenerUtil.mutListener.listen(6171) ? (totalTime != 0) : (totalTime == 0))))))) {
                    if (!ListenerUtil.mutListener.listen(6176)) {
                        totalTime = null;
                    }
                }
            }
            Double easeInPercent = (ListenerUtil.mutListener.listen(6181) ? (c.getFactor() % 1000.0d) : (ListenerUtil.mutListener.listen(6180) ? (c.getFactor() * 1000.0d) : (ListenerUtil.mutListener.listen(6179) ? (c.getFactor() - 1000.0d) : (ListenerUtil.mutListener.listen(6178) ? (c.getFactor() + 1000.0d) : (c.getFactor() / 1000.0d)))));
            int lapses = c.getLapses();
            int reviews = c.getReps();
            Model model = collection.getModels().get(c.note().getMid());
            String cardType = getCardType(c, model);
            String noteType = model.getString("name");
            String deckName = collection.getDecks().get(c.getDid()).getString("name");
            long noteId = c.getNid();
            Integer interval = c.getIvl();
            if (!ListenerUtil.mutListener.listen(6188)) {
                if ((ListenerUtil.mutListener.listen(6186) ? (interval >= 0) : (ListenerUtil.mutListener.listen(6185) ? (interval > 0) : (ListenerUtil.mutListener.listen(6184) ? (interval < 0) : (ListenerUtil.mutListener.listen(6183) ? (interval != 0) : (ListenerUtil.mutListener.listen(6182) ? (interval == 0) : (interval <= 0))))))) {
                    if (!ListenerUtil.mutListener.listen(6187)) {
                        interval = null;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(6195)) {
                if ((ListenerUtil.mutListener.listen(6193) ? (c.getType() >= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(6192) ? (c.getType() <= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(6191) ? (c.getType() > Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(6190) ? (c.getType() != Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(6189) ? (c.getType() == Consts.CARD_TYPE_REV) : (c.getType() < Consts.CARD_TYPE_REV))))))) {
                    if (!ListenerUtil.mutListener.listen(6194)) {
                        easeInPercent = null;
                    }
                }
            }
            String due = c.getDueString();
            List<RevLogEntry> entries = new ArrayList<>(collection.getDb().queryScalar("select count() from revlog where cid = ?", c.getId()));
            try (Cursor cur = collection.getDb().query("select " + "id as dateTime, " + "ease as rating, " + "ivl, " + "factor as ease, " + "time, " + "type " + "from revlog where cid = ?" + "order by id desc", c.getId())) {
                if (!ListenerUtil.mutListener.listen(6203)) {
                    {
                        long _loopCounter114 = 0;
                        while (cur.moveToNext()) {
                            ListenerUtil.loopListener.listen("_loopCounter114", ++_loopCounter114);
                            RevLogEntry e = new RevLogEntry();
                            if (!ListenerUtil.mutListener.listen(6196)) {
                                e.dateTime = cur.getLong(0);
                            }
                            if (!ListenerUtil.mutListener.listen(6197)) {
                                e.rating = cur.getInt(1);
                            }
                            if (!ListenerUtil.mutListener.listen(6198)) {
                                e.ivl = cur.getLong(2);
                            }
                            if (!ListenerUtil.mutListener.listen(6199)) {
                                e.factor = cur.getLong(3);
                            }
                            if (!ListenerUtil.mutListener.listen(6200)) {
                                e.timeTakenMs = cur.getLong(4);
                            }
                            if (!ListenerUtil.mutListener.listen(6201)) {
                                e.type = cur.getInt(5);
                            }
                            if (!ListenerUtil.mutListener.listen(6202)) {
                                entries.add(e);
                            }
                        }
                    }
                }
            }
            return new CardInfoModel(addedDate, firstReview, latestReview, due, interval, easeInPercent, reviews, lapses, averageTime, totalTime, cardType, noteType, deckName, noteId, entries);
        }

        @NonNull
        protected static String getCardType(Card c, Model model) {
            try {
                int ord = c.getOrd();
                if (!ListenerUtil.mutListener.listen(6206)) {
                    if (c.model().isCloze()) {
                        if (!ListenerUtil.mutListener.listen(6205)) {
                            ord = 0;
                        }
                    }
                }
                return model.getJSONArray("tmpls").getJSONObject(ord).getString("name");
            } catch (Exception e) {
                if (!ListenerUtil.mutListener.listen(6204)) {
                    Timber.w(e);
                }
                return null;
            }
        }

        public long getAddedDate() {
            return mAddedDate;
        }

        @Nullable
        public Long getFirstReviewDate() {
            return mFirstReviewDate;
        }

        @Nullable
        public Long getLatestReviewDate() {
            return mLatestReviewDate;
        }

        @Nullable
        public String getDue() {
            return mDue;
        }

        @Nullable
        public Integer getInterval() {
            return mInterval;
        }

        @Nullable
        public Double getEaseInPercent() {
            return mEaseInPercent;
        }

        public int getReviews() {
            return mReviews;
        }

        public int getLapses() {
            return mLapses;
        }

        @Nullable
        public Long getAverageTimeMs() {
            return mAverageTimeMs;
        }

        @Nullable
        public Long getTotalTimeMs() {
            return mTotalTimeMs;
        }

        public String getCardType() {
            return mCardType;
        }

        public String getNoteType() {
            return mNoteType;
        }

        public String getDeckName() {
            return mDeckName;
        }

        public long getCardId() {
            return mAddedDate;
        }

        public long getNoteId() {
            return mNoteId;
        }

        public List<RevLogEntry> getEntries() {
            return mEntries;
        }

        // date type rating interval ease time
        public static class RevLogEntry {

            public long dateTime;

            public int type;

            public int rating;

            public long ivl;

            public long factor;

            public long timeTakenMs;

            public Spannable spannableType(Context context) {
                int[] attrs = new int[] { R.attr.newCountColor, R.attr.learnCountColor, R.attr.reviewCountColor };
                TypedArray ta = context.obtainStyledAttributes(attrs);
                int newCountColor = ta.getColor(0, ContextCompat.getColor(context, R.color.black));
                int learnCountColor = ta.getColor(1, ContextCompat.getColor(context, R.color.black));
                int reviewCountColor = ta.getColor(2, ContextCompat.getColor(context, R.color.black));
                int filteredColor = ContextCompat.getColor(context, R.color.material_orange_A700);
                if (!ListenerUtil.mutListener.listen(6207)) {
                    ta.recycle();
                }
                switch(type) {
                    case Consts.REVLOG_LRN:
                        return UiUtil.makeColored(context.getString(R.string.card_info_revlog_learn), newCountColor);
                    case Consts.REVLOG_REV:
                        return UiUtil.makeColored(context.getString(R.string.card_info_revlog_review), reviewCountColor);
                    case Consts.REVLOG_RELRN:
                        return UiUtil.makeColored(context.getString(R.string.card_info_revlog_relearn), learnCountColor);
                    case Consts.REVLOG_CRAM:
                        return UiUtil.makeColored(context.getString(R.string.card_info_revlog_filtered), filteredColor);
                    default:
                        return new SpannableString(Integer.toString(type));
                }
            }

            public Spannable getEase(Context context) {
                if ((ListenerUtil.mutListener.listen(6212) ? (factor >= 0) : (ListenerUtil.mutListener.listen(6211) ? (factor <= 0) : (ListenerUtil.mutListener.listen(6210) ? (factor > 0) : (ListenerUtil.mutListener.listen(6209) ? (factor < 0) : (ListenerUtil.mutListener.listen(6208) ? (factor != 0) : (factor == 0))))))) {
                    return new SpannableString(context.getString(R.string.card_info_ease_not_applicable));
                } else {
                    return new SpannableString(Long.toString((ListenerUtil.mutListener.listen(6216) ? (factor % 10) : (ListenerUtil.mutListener.listen(6215) ? (factor * 10) : (ListenerUtil.mutListener.listen(6214) ? (factor - 10) : (ListenerUtil.mutListener.listen(6213) ? (factor + 10) : (factor / 10)))))));
                }
            }

            public long intervalAsTimeSeconds() {
                if (!ListenerUtil.mutListener.listen(6222)) {
                    if ((ListenerUtil.mutListener.listen(6221) ? (ivl >= 0) : (ListenerUtil.mutListener.listen(6220) ? (ivl <= 0) : (ListenerUtil.mutListener.listen(6219) ? (ivl > 0) : (ListenerUtil.mutListener.listen(6218) ? (ivl != 0) : (ListenerUtil.mutListener.listen(6217) ? (ivl == 0) : (ivl < 0))))))) {
                        return -ivl;
                    }
                }
                return (ListenerUtil.mutListener.listen(6226) ? (ivl % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(6225) ? (ivl / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(6224) ? (ivl - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(6223) ? (ivl + SECONDS_PER_DAY) : (ivl * SECONDS_PER_DAY)))));
            }

            public String getTimeTaken() {
                // return Utils.timeQuantityNextIvl(context, timeTakenMs / 1000);
                return Long.toString((ListenerUtil.mutListener.listen(6230) ? (timeTakenMs % 1000) : (ListenerUtil.mutListener.listen(6229) ? (timeTakenMs * 1000) : (ListenerUtil.mutListener.listen(6228) ? (timeTakenMs - 1000) : (ListenerUtil.mutListener.listen(6227) ? (timeTakenMs + 1000) : (timeTakenMs / 1000))))));
            }

            public Spannable getRating(Context context) {
                String source = Long.toString(rating);
                if ((ListenerUtil.mutListener.listen(6235) ? (rating >= 1) : (ListenerUtil.mutListener.listen(6234) ? (rating <= 1) : (ListenerUtil.mutListener.listen(6233) ? (rating > 1) : (ListenerUtil.mutListener.listen(6232) ? (rating < 1) : (ListenerUtil.mutListener.listen(6231) ? (rating != 1) : (rating == 1))))))) {
                    int[] attrs = new int[] { R.attr.learnCountColor };
                    TypedArray ta = context.obtainStyledAttributes(attrs);
                    int failColor = ta.getColor(0, ContextCompat.getColor(context, R.color.black));
                    if (!ListenerUtil.mutListener.listen(6236)) {
                        ta.recycle();
                    }
                    return UiUtil.makeColored(source, failColor);
                } else {
                    return new SpannableString(source);
                }
            }
        }
    }
}
