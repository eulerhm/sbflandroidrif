package com.ichi2.anki;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ichi2.anim.ActivityTransitionAnimation;
import com.ichi2.anki.exception.ConfirmModSchemaException;
import com.ichi2.anki.receiver.SdCardReceiver;
import com.ichi2.anki.services.ReminderService;
import com.ichi2.async.CollectionTask;
import com.ichi2.async.TaskListenerWithContext;
import com.ichi2.async.TaskManager;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.Consts;
import com.ichi2.libanki.DeckConfig;
import com.ichi2.libanki.Deck;
import com.ichi2.libanki.utils.Time;
import com.ichi2.preferences.NumberRangePreference;
import com.ichi2.preferences.StepsPreference;
import com.ichi2.preferences.TimePreference;
import com.ichi2.themes.StyledProgressDialog;
import com.ichi2.themes.Themes;
import com.ichi2.ui.AppCompatPreferenceActivity;
import com.ichi2.utils.JSONArray;
import com.ichi2.utils.JSONException;
import com.ichi2.utils.JSONObject;
import com.ichi2.utils.NamedJSONComparator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import androidx.annotation.NonNull;
import timber.log.Timber;
import static com.ichi2.anim.ActivityTransitionAnimation.Direction.FADE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Preferences for the current deck.
 */
public class DeckOptions extends AppCompatPreferenceActivity implements OnSharedPreferenceChangeListener {

    private DeckConfig mOptions;

    private Deck mDeck;

    private Collection mCol;

    private boolean mPreferenceChanged = false;

    private BroadcastReceiver mUnmountReceiver = null;

    private DeckPreferenceHack mPref;

    public class DeckPreferenceHack implements SharedPreferences {

        // At most as many as in cacheValues
        private final Map<String, String> mValues = new HashMap<>(30);

        private final Map<String, String> mSummaries = new HashMap<>();

        private MaterialDialog mProgressDialog;

        private final List<OnSharedPreferenceChangeListener> listeners = new LinkedList<>();

        private DeckPreferenceHack() {
            if (!ListenerUtil.mutListener.listen(6813)) {
                this.cacheValues();
            }
        }

        public DeckOptions getDeckOptions() {
            return DeckOptions.this;
        }

        private void cacheValues() {
            if (!ListenerUtil.mutListener.listen(6814)) {
                Timber.i("DeckOptions - CacheValues");
            }
            try {
                if (!ListenerUtil.mutListener.listen(6819)) {
                    mOptions = mCol.getDecks().confForDid(mDeck.getLong("id"));
                }
                if (!ListenerUtil.mutListener.listen(6820)) {
                    mValues.put("name", mDeck.getString("name"));
                }
                if (!ListenerUtil.mutListener.listen(6821)) {
                    mValues.put("desc", mDeck.getString("desc"));
                }
                if (!ListenerUtil.mutListener.listen(6822)) {
                    mValues.put("deckConf", mDeck.getString("conf"));
                }
                if (!ListenerUtil.mutListener.listen(6823)) {
                    // general
                    mValues.put("maxAnswerTime", mOptions.getString("maxTaken"));
                }
                if (!ListenerUtil.mutListener.listen(6824)) {
                    mValues.put("showAnswerTimer", Boolean.toString(parseTimerValue(mOptions)));
                }
                if (!ListenerUtil.mutListener.listen(6825)) {
                    mValues.put("autoPlayAudio", Boolean.toString(mOptions.getBoolean("autoplay")));
                }
                if (!ListenerUtil.mutListener.listen(6826)) {
                    mValues.put("replayQuestion", Boolean.toString(mOptions.optBoolean("replayq", true)));
                }
                // new
                JSONObject newOptions = mOptions.getJSONObject("new");
                if (!ListenerUtil.mutListener.listen(6827)) {
                    mValues.put("newSteps", StepsPreference.convertFromJSON(newOptions.getJSONArray("delays")));
                }
                if (!ListenerUtil.mutListener.listen(6828)) {
                    mValues.put("newGradIvl", newOptions.getJSONArray("ints").getString(0));
                }
                if (!ListenerUtil.mutListener.listen(6829)) {
                    mValues.put("newEasy", newOptions.getJSONArray("ints").getString(1));
                }
                if (!ListenerUtil.mutListener.listen(6834)) {
                    mValues.put("newFactor", Integer.toString((ListenerUtil.mutListener.listen(6833) ? (newOptions.getInt("initialFactor") % 10) : (ListenerUtil.mutListener.listen(6832) ? (newOptions.getInt("initialFactor") * 10) : (ListenerUtil.mutListener.listen(6831) ? (newOptions.getInt("initialFactor") - 10) : (ListenerUtil.mutListener.listen(6830) ? (newOptions.getInt("initialFactor") + 10) : (newOptions.getInt("initialFactor") / 10)))))));
                }
                if (!ListenerUtil.mutListener.listen(6835)) {
                    mValues.put("newOrder", newOptions.getString("order"));
                }
                if (!ListenerUtil.mutListener.listen(6836)) {
                    mValues.put("newPerDay", newOptions.getString("perDay"));
                }
                if (!ListenerUtil.mutListener.listen(6837)) {
                    mValues.put("newBury", Boolean.toString(newOptions.optBoolean("bury", true)));
                }
                // rev
                JSONObject revOptions = mOptions.getJSONObject("rev");
                if (!ListenerUtil.mutListener.listen(6838)) {
                    mValues.put("revPerDay", revOptions.getString("perDay"));
                }
                if (!ListenerUtil.mutListener.listen(6843)) {
                    mValues.put("easyBonus", String.format(Locale.ROOT, "%.0f", (ListenerUtil.mutListener.listen(6842) ? (revOptions.getDouble("ease4") % 100) : (ListenerUtil.mutListener.listen(6841) ? (revOptions.getDouble("ease4") / 100) : (ListenerUtil.mutListener.listen(6840) ? (revOptions.getDouble("ease4") - 100) : (ListenerUtil.mutListener.listen(6839) ? (revOptions.getDouble("ease4") + 100) : (revOptions.getDouble("ease4") * 100)))))));
                }
                if (!ListenerUtil.mutListener.listen(6848)) {
                    mValues.put("hardFactor", String.format(Locale.ROOT, "%.0f", (ListenerUtil.mutListener.listen(6847) ? (revOptions.optDouble("hardFactor", 1.2) % 100) : (ListenerUtil.mutListener.listen(6846) ? (revOptions.optDouble("hardFactor", 1.2) / 100) : (ListenerUtil.mutListener.listen(6845) ? (revOptions.optDouble("hardFactor", 1.2) - 100) : (ListenerUtil.mutListener.listen(6844) ? (revOptions.optDouble("hardFactor", 1.2) + 100) : (revOptions.optDouble("hardFactor", 1.2) * 100)))))));
                }
                if (!ListenerUtil.mutListener.listen(6853)) {
                    mValues.put("revIvlFct", String.format(Locale.ROOT, "%.0f", (ListenerUtil.mutListener.listen(6852) ? (revOptions.getDouble("ivlFct") % 100) : (ListenerUtil.mutListener.listen(6851) ? (revOptions.getDouble("ivlFct") / 100) : (ListenerUtil.mutListener.listen(6850) ? (revOptions.getDouble("ivlFct") - 100) : (ListenerUtil.mutListener.listen(6849) ? (revOptions.getDouble("ivlFct") + 100) : (revOptions.getDouble("ivlFct") * 100)))))));
                }
                if (!ListenerUtil.mutListener.listen(6854)) {
                    mValues.put("revMaxIvl", revOptions.getString("maxIvl"));
                }
                if (!ListenerUtil.mutListener.listen(6855)) {
                    mValues.put("revBury", Boolean.toString(revOptions.optBoolean("bury", true)));
                }
                if (!ListenerUtil.mutListener.listen(6856)) {
                    mValues.put("revUseGeneralTimeoutSettings", Boolean.toString(revOptions.optBoolean("useGeneralTimeoutSettings", true)));
                }
                if (!ListenerUtil.mutListener.listen(6857)) {
                    mValues.put("revTimeoutAnswer", Boolean.toString(revOptions.optBoolean("timeoutAnswer", false)));
                }
                if (!ListenerUtil.mutListener.listen(6858)) {
                    mValues.put("revTimeoutAnswerSeconds", Integer.toString(revOptions.optInt("timeoutAnswerSeconds", 6)));
                }
                if (!ListenerUtil.mutListener.listen(6859)) {
                    mValues.put("revTimeoutQuestionSeconds", Integer.toString(revOptions.optInt("timeoutQuestionSeconds", 60)));
                }
                // lapse
                JSONObject lapOptions = mOptions.getJSONObject("lapse");
                if (!ListenerUtil.mutListener.listen(6860)) {
                    mValues.put("lapSteps", StepsPreference.convertFromJSON(lapOptions.getJSONArray("delays")));
                }
                if (!ListenerUtil.mutListener.listen(6865)) {
                    mValues.put("lapNewIvl", String.format(Locale.ROOT, "%.0f", (ListenerUtil.mutListener.listen(6864) ? (lapOptions.getDouble("mult") % 100) : (ListenerUtil.mutListener.listen(6863) ? (lapOptions.getDouble("mult") / 100) : (ListenerUtil.mutListener.listen(6862) ? (lapOptions.getDouble("mult") - 100) : (ListenerUtil.mutListener.listen(6861) ? (lapOptions.getDouble("mult") + 100) : (lapOptions.getDouble("mult") * 100)))))));
                }
                if (!ListenerUtil.mutListener.listen(6866)) {
                    mValues.put("lapMinIvl", lapOptions.getString("minInt"));
                }
                if (!ListenerUtil.mutListener.listen(6867)) {
                    mValues.put("lapLeechThres", lapOptions.getString("leechFails"));
                }
                if (!ListenerUtil.mutListener.listen(6868)) {
                    mValues.put("lapLeechAct", lapOptions.getString("leechAction"));
                }
                if (!ListenerUtil.mutListener.listen(6869)) {
                    // options group management
                    mValues.put("currentConf", mCol.getDecks().getConf(mDeck.getLong("conf")).getString("name"));
                }
                if (!ListenerUtil.mutListener.listen(6874)) {
                    // reminders
                    if (mOptions.has("reminder")) {
                        final JSONObject reminder = mOptions.getJSONObject("reminder");
                        final JSONArray reminderTime = reminder.getJSONArray("time");
                        if (!ListenerUtil.mutListener.listen(6872)) {
                            mValues.put("reminderEnabled", Boolean.toString(reminder.getBoolean("enabled")));
                        }
                        if (!ListenerUtil.mutListener.listen(6873)) {
                            mValues.put("reminderTime", String.format("%1$02d:%2$02d", reminderTime.getLong(0), reminderTime.getLong(1)));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(6870)) {
                            mValues.put("reminderEnabled", "false");
                        }
                        if (!ListenerUtil.mutListener.listen(6871)) {
                            mValues.put("reminderTime", TimePreference.DEFAULT_VALUE);
                        }
                    }
                }
            } catch (JSONException e) {
                if (!ListenerUtil.mutListener.listen(6815)) {
                    Timber.e(e, "DeckOptions - cacheValues");
                }
                if (!ListenerUtil.mutListener.listen(6816)) {
                    AnkiDroidApp.sendExceptionReport(e, "DeckOptions: cacheValues");
                }
                Resources r = DeckOptions.this.getResources();
                if (!ListenerUtil.mutListener.listen(6817)) {
                    UIUtils.showThemedToast(DeckOptions.this, r.getString(R.string.deck_options_corrupt, e.getLocalizedMessage()), false);
                }
                if (!ListenerUtil.mutListener.listen(6818)) {
                    finish();
                }
            }
        }

        private boolean parseTimerValue(DeckConfig options) {
            return DeckConfig.parseTimerOpt(options, true);
        }

        public class Editor implements SharedPreferences.Editor {

            private ContentValues mUpdate = new ContentValues();

            @Override
            public SharedPreferences.Editor clear() {
                if (!ListenerUtil.mutListener.listen(6875)) {
                    Timber.d("clear()");
                }
                if (!ListenerUtil.mutListener.listen(6876)) {
                    mUpdate = new ContentValues();
                }
                return this;
            }

            @Override
            public boolean commit() {
                if (!ListenerUtil.mutListener.listen(6877)) {
                    Timber.d("DeckOptions - commit() changes back to database");
                }
                try {
                    if (!ListenerUtil.mutListener.listen(6969)) {
                        {
                            long _loopCounter124 = 0;
                            for (Entry<String, Object> entry : mUpdate.valueSet()) {
                                ListenerUtil.loopListener.listen("_loopCounter124", ++_loopCounter124);
                                String key = entry.getKey();
                                Object value = entry.getValue();
                                if (!ListenerUtil.mutListener.listen(6878)) {
                                    Timber.i("Change value for key '%s': %s", key, value);
                                }
                                if (!ListenerUtil.mutListener.listen(6968)) {
                                    switch(key) {
                                        case "maxAnswerTime":
                                            if (!ListenerUtil.mutListener.listen(6879)) {
                                                mOptions.put("maxTaken", value);
                                            }
                                            break;
                                        case "newFactor":
                                            if (!ListenerUtil.mutListener.listen(6884)) {
                                                mOptions.getJSONObject("new").put("initialFactor", (ListenerUtil.mutListener.listen(6883) ? ((Integer) value % 10) : (ListenerUtil.mutListener.listen(6882) ? ((Integer) value / 10) : (ListenerUtil.mutListener.listen(6881) ? ((Integer) value - 10) : (ListenerUtil.mutListener.listen(6880) ? ((Integer) value + 10) : ((Integer) value * 10))))));
                                            }
                                            break;
                                        case "newOrder":
                                            {
                                                int newValue = Integer.parseInt((String) value);
                                                // Sorting is slow, so only do it if we change order
                                                int oldValue = mOptions.getJSONObject("new").getInt("order");
                                                if (!ListenerUtil.mutListener.listen(6892)) {
                                                    if ((ListenerUtil.mutListener.listen(6889) ? (oldValue >= newValue) : (ListenerUtil.mutListener.listen(6888) ? (oldValue <= newValue) : (ListenerUtil.mutListener.listen(6887) ? (oldValue > newValue) : (ListenerUtil.mutListener.listen(6886) ? (oldValue < newValue) : (ListenerUtil.mutListener.listen(6885) ? (oldValue == newValue) : (oldValue != newValue))))))) {
                                                        if (!ListenerUtil.mutListener.listen(6890)) {
                                                            mOptions.getJSONObject("new").put("order", newValue);
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(6891)) {
                                                            TaskManager.launchCollectionTask(new CollectionTask.Reorder(mOptions), confChangeHandler());
                                                        }
                                                    }
                                                }
                                                if (!ListenerUtil.mutListener.listen(6893)) {
                                                    mOptions.getJSONObject("new").put("order", Integer.parseInt((String) value));
                                                }
                                                break;
                                            }
                                        case "newPerDay":
                                            if (!ListenerUtil.mutListener.listen(6894)) {
                                                mOptions.getJSONObject("new").put("perDay", value);
                                            }
                                            break;
                                        case "newGradIvl":
                                            {
                                                // [graduating, easy]
                                                JSONArray newInts = new JSONArray();
                                                if (!ListenerUtil.mutListener.listen(6895)) {
                                                    newInts.put(value);
                                                }
                                                if (!ListenerUtil.mutListener.listen(6896)) {
                                                    newInts.put(mOptions.getJSONObject("new").getJSONArray("ints").getInt(1));
                                                }
                                                if (!ListenerUtil.mutListener.listen(6897)) {
                                                    mOptions.getJSONObject("new").put("ints", newInts);
                                                }
                                                break;
                                            }
                                        case "newEasy":
                                            {
                                                // [graduating, easy]
                                                JSONArray newInts = new JSONArray();
                                                if (!ListenerUtil.mutListener.listen(6898)) {
                                                    newInts.put(mOptions.getJSONObject("new").getJSONArray("ints").getInt(0));
                                                }
                                                if (!ListenerUtil.mutListener.listen(6899)) {
                                                    newInts.put(value);
                                                }
                                                if (!ListenerUtil.mutListener.listen(6900)) {
                                                    mOptions.getJSONObject("new").put("ints", newInts);
                                                }
                                                break;
                                            }
                                        case "newBury":
                                            if (!ListenerUtil.mutListener.listen(6901)) {
                                                mOptions.getJSONObject("new").put("bury", value);
                                            }
                                            break;
                                        case "revPerDay":
                                            if (!ListenerUtil.mutListener.listen(6902)) {
                                                mOptions.getJSONObject("rev").put("perDay", value);
                                            }
                                            break;
                                        case "easyBonus":
                                            if (!ListenerUtil.mutListener.listen(6907)) {
                                                mOptions.getJSONObject("rev").put("ease4", (ListenerUtil.mutListener.listen(6906) ? ((Integer) value % 100.0f) : (ListenerUtil.mutListener.listen(6905) ? ((Integer) value * 100.0f) : (ListenerUtil.mutListener.listen(6904) ? ((Integer) value - 100.0f) : (ListenerUtil.mutListener.listen(6903) ? ((Integer) value + 100.0f) : ((Integer) value / 100.0f))))));
                                            }
                                            break;
                                        case "hardFactor":
                                            if (!ListenerUtil.mutListener.listen(6912)) {
                                                mOptions.getJSONObject("rev").put("hardFactor", (ListenerUtil.mutListener.listen(6911) ? ((Integer) value % 100.0f) : (ListenerUtil.mutListener.listen(6910) ? ((Integer) value * 100.0f) : (ListenerUtil.mutListener.listen(6909) ? ((Integer) value - 100.0f) : (ListenerUtil.mutListener.listen(6908) ? ((Integer) value + 100.0f) : ((Integer) value / 100.0f))))));
                                            }
                                            break;
                                        case "revIvlFct":
                                            if (!ListenerUtil.mutListener.listen(6917)) {
                                                mOptions.getJSONObject("rev").put("ivlFct", (ListenerUtil.mutListener.listen(6916) ? ((Integer) value % 100.0f) : (ListenerUtil.mutListener.listen(6915) ? ((Integer) value * 100.0f) : (ListenerUtil.mutListener.listen(6914) ? ((Integer) value - 100.0f) : (ListenerUtil.mutListener.listen(6913) ? ((Integer) value + 100.0f) : ((Integer) value / 100.0f))))));
                                            }
                                            break;
                                        case "revMaxIvl":
                                            if (!ListenerUtil.mutListener.listen(6918)) {
                                                mOptions.getJSONObject("rev").put("maxIvl", value);
                                            }
                                            break;
                                        case "revBury":
                                            if (!ListenerUtil.mutListener.listen(6919)) {
                                                mOptions.getJSONObject("rev").put("bury", value);
                                            }
                                            break;
                                        case "revUseGeneralTimeoutSettings":
                                            if (!ListenerUtil.mutListener.listen(6920)) {
                                                mOptions.getJSONObject("rev").put("useGeneralTimeoutSettings", value);
                                            }
                                            break;
                                        case "revTimeoutAnswer":
                                            if (!ListenerUtil.mutListener.listen(6921)) {
                                                mOptions.getJSONObject("rev").put("timeoutAnswer", value);
                                            }
                                            break;
                                        case "revTimeoutAnswerSeconds":
                                            if (!ListenerUtil.mutListener.listen(6922)) {
                                                mOptions.getJSONObject("rev").put("timeoutAnswerSeconds", value);
                                            }
                                            break;
                                        case "revTimeoutQuestionSeconds":
                                            if (!ListenerUtil.mutListener.listen(6923)) {
                                                mOptions.getJSONObject("rev").put("timeoutQuestionSeconds", value);
                                            }
                                            break;
                                        case "lapMinIvl":
                                            if (!ListenerUtil.mutListener.listen(6924)) {
                                                mOptions.getJSONObject("lapse").put("minInt", value);
                                            }
                                            break;
                                        case "lapLeechThres":
                                            if (!ListenerUtil.mutListener.listen(6925)) {
                                                mOptions.getJSONObject("lapse").put("leechFails", value);
                                            }
                                            break;
                                        case "lapLeechAct":
                                            if (!ListenerUtil.mutListener.listen(6926)) {
                                                mOptions.getJSONObject("lapse").put("leechAction", Integer.parseInt((String) value));
                                            }
                                            break;
                                        case "lapNewIvl":
                                            if (!ListenerUtil.mutListener.listen(6931)) {
                                                mOptions.getJSONObject("lapse").put("mult", (ListenerUtil.mutListener.listen(6930) ? ((Integer) value % 100.0f) : (ListenerUtil.mutListener.listen(6929) ? ((Integer) value * 100.0f) : (ListenerUtil.mutListener.listen(6928) ? ((Integer) value - 100.0f) : (ListenerUtil.mutListener.listen(6927) ? ((Integer) value + 100.0f) : ((Integer) value / 100.0f))))));
                                            }
                                            break;
                                        case "showAnswerTimer":
                                            if (!ListenerUtil.mutListener.listen(6932)) {
                                                mOptions.put("timer", (Boolean) value ? 1 : 0);
                                            }
                                            break;
                                        case "autoPlayAudio":
                                            if (!ListenerUtil.mutListener.listen(6933)) {
                                                mOptions.put("autoplay", value);
                                            }
                                            break;
                                        case "replayQuestion":
                                            if (!ListenerUtil.mutListener.listen(6934)) {
                                                mOptions.put("replayq", value);
                                            }
                                            break;
                                        case "desc":
                                            if (!ListenerUtil.mutListener.listen(6935)) {
                                                mDeck.put("desc", value);
                                            }
                                            if (!ListenerUtil.mutListener.listen(6936)) {
                                                mCol.getDecks().save(mDeck);
                                            }
                                            break;
                                        case "newSteps":
                                            if (!ListenerUtil.mutListener.listen(6937)) {
                                                mOptions.getJSONObject("new").put("delays", StepsPreference.convertToJSON((String) value));
                                            }
                                            break;
                                        case "lapSteps":
                                            if (!ListenerUtil.mutListener.listen(6938)) {
                                                mOptions.getJSONObject("lapse").put("delays", StepsPreference.convertToJSON((String) value));
                                            }
                                            break;
                                        case "deckConf":
                                            {
                                                long newConfId = Long.parseLong((String) value);
                                                if (!ListenerUtil.mutListener.listen(6939)) {
                                                    mOptions = mCol.getDecks().getConf(newConfId);
                                                }
                                                if (!ListenerUtil.mutListener.listen(6940)) {
                                                    TaskManager.launchCollectionTask(new CollectionTask.ConfChange(mDeck, mOptions), confChangeHandler());
                                                }
                                                break;
                                            }
                                        case "confRename":
                                            {
                                                String newName = (String) value;
                                                if (!ListenerUtil.mutListener.listen(6942)) {
                                                    if (!TextUtils.isEmpty(newName)) {
                                                        if (!ListenerUtil.mutListener.listen(6941)) {
                                                            mOptions.put("name", newName);
                                                        }
                                                    }
                                                }
                                                break;
                                            }
                                        case "confReset":
                                            if (!ListenerUtil.mutListener.listen(6944)) {
                                                if ((Boolean) value) {
                                                    if (!ListenerUtil.mutListener.listen(6943)) {
                                                        TaskManager.launchCollectionTask(new CollectionTask.ConfReset(mOptions), confChangeHandler());
                                                    }
                                                }
                                            }
                                            break;
                                        case "confAdd":
                                            {
                                                String newName = (String) value;
                                                if (!ListenerUtil.mutListener.listen(6947)) {
                                                    if (!TextUtils.isEmpty(newName)) {
                                                        // New config clones current config
                                                        long id = mCol.getDecks().confId(newName, mOptions.toString());
                                                        if (!ListenerUtil.mutListener.listen(6945)) {
                                                            mDeck.put("conf", id);
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(6946)) {
                                                            mCol.getDecks().save(mDeck);
                                                        }
                                                    }
                                                }
                                                break;
                                            }
                                        case "confRemove":
                                            if (!ListenerUtil.mutListener.listen(6951)) {
                                                if (mOptions.getLong("id") == 1) {
                                                    if (!ListenerUtil.mutListener.listen(6950)) {
                                                        // Don't remove the options group if it's the default group
                                                        UIUtils.showThemedToast(DeckOptions.this, getResources().getString(R.string.default_conf_delete_error), false);
                                                    }
                                                } else {
                                                    // Remove options group, handling the case where the user needs to confirm full sync
                                                    try {
                                                        if (!ListenerUtil.mutListener.listen(6949)) {
                                                            remConf();
                                                        }
                                                    } catch (ConfirmModSchemaException e) {
                                                        if (!ListenerUtil.mutListener.listen(6948)) {
                                                            // TODO : Use ConfirmationDialog DialogFragment -- not compatible with PreferenceActivity
                                                            new MaterialDialog.Builder(DeckOptions.this).content(R.string.full_sync_confirmation).positiveText(R.string.dialog_ok).negativeText(R.string.dialog_cancel).onPositive((dialog, which) -> {
                                                                mCol.modSchemaNoCheck();
                                                                try {
                                                                    remConf();
                                                                } catch (ConfirmModSchemaException cmse) {
                                                                    // This should never be reached as we just forced modSchema
                                                                    throw new RuntimeException(cmse);
                                                                }
                                                            }).build().show();
                                                        }
                                                    }
                                                }
                                            }
                                            break;
                                        case "confSetSubdecks":
                                            if (!ListenerUtil.mutListener.listen(6953)) {
                                                if ((Boolean) value) {
                                                    if (!ListenerUtil.mutListener.listen(6952)) {
                                                        TaskManager.launchCollectionTask(new CollectionTask.ConfSetSubdecks(mDeck, mOptions), confChangeHandler());
                                                    }
                                                }
                                            }
                                            break;
                                        case "reminderEnabled":
                                            {
                                                final JSONObject reminder = new JSONObject();
                                                if (!ListenerUtil.mutListener.listen(6954)) {
                                                    reminder.put("enabled", value);
                                                }
                                                if (!ListenerUtil.mutListener.listen(6957)) {
                                                    if (mOptions.has("reminder")) {
                                                        if (!ListenerUtil.mutListener.listen(6956)) {
                                                            reminder.put("time", mOptions.getJSONObject("reminder").getJSONArray("time"));
                                                        }
                                                    } else {
                                                        if (!ListenerUtil.mutListener.listen(6955)) {
                                                            reminder.put("time", new JSONArray().put(TimePreference.parseHours(TimePreference.DEFAULT_VALUE)).put(TimePreference.parseMinutes(TimePreference.DEFAULT_VALUE)));
                                                        }
                                                    }
                                                }
                                                if (!ListenerUtil.mutListener.listen(6958)) {
                                                    mOptions.put("reminder", reminder);
                                                }
                                                final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                final PendingIntent reminderIntent = PendingIntent.getBroadcast(getApplicationContext(), (int) mOptions.getLong("id"), new Intent(getApplicationContext(), ReminderService.class).putExtra(ReminderService.EXTRA_DECK_OPTION_ID, mOptions.getLong("id")), 0);
                                                if (!ListenerUtil.mutListener.listen(6959)) {
                                                    alarmManager.cancel(reminderIntent);
                                                }
                                                if (!ListenerUtil.mutListener.listen(6961)) {
                                                    if ((Boolean) value) {
                                                        final Calendar calendar = reminderToCalendar(mCol.getTime(), reminder);
                                                        if (!ListenerUtil.mutListener.listen(6960)) {
                                                            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, reminderIntent);
                                                        }
                                                    }
                                                }
                                                break;
                                            }
                                        case "reminderTime":
                                            {
                                                final JSONObject reminder = new JSONObject();
                                                if (!ListenerUtil.mutListener.listen(6962)) {
                                                    reminder.put("enabled", true);
                                                }
                                                if (!ListenerUtil.mutListener.listen(6963)) {
                                                    reminder.put("time", new JSONArray().put(TimePreference.parseHours((String) value)).put(TimePreference.parseMinutes((String) value)));
                                                }
                                                if (!ListenerUtil.mutListener.listen(6964)) {
                                                    mOptions.put("reminder", reminder);
                                                }
                                                final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                final PendingIntent reminderIntent = PendingIntent.getBroadcast(getApplicationContext(), (int) mOptions.getLong("id"), new Intent(getApplicationContext(), ReminderService.class).putExtra(ReminderService.EXTRA_DECK_OPTION_ID, mOptions.getLong("id")), 0);
                                                if (!ListenerUtil.mutListener.listen(6965)) {
                                                    alarmManager.cancel(reminderIntent);
                                                }
                                                final Calendar calendar = reminderToCalendar(mCol.getTime(), reminder);
                                                if (!ListenerUtil.mutListener.listen(6966)) {
                                                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, reminderIntent);
                                                }
                                                break;
                                            }
                                        default:
                                            if (!ListenerUtil.mutListener.listen(6967)) {
                                                Timber.w("Unknown key type: %s", key);
                                            }
                                            break;
                                    }
                                }
                            }
                        }
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                // save conf
                try {
                    if (!ListenerUtil.mutListener.listen(6974)) {
                        mCol.getDecks().save(mOptions);
                    }
                } catch (RuntimeException e) {
                    if (!ListenerUtil.mutListener.listen(6970)) {
                        Timber.e(e, "DeckOptions - RuntimeException on saving conf");
                    }
                    if (!ListenerUtil.mutListener.listen(6971)) {
                        AnkiDroidApp.sendExceptionReport(e, "DeckOptionsSaveConf");
                    }
                    if (!ListenerUtil.mutListener.listen(6972)) {
                        setResult(DeckPicker.RESULT_DB_ERROR);
                    }
                    if (!ListenerUtil.mutListener.listen(6973)) {
                        finish();
                    }
                }
                if (!ListenerUtil.mutListener.listen(6975)) {
                    // make sure we refresh the parent cached values
                    cacheValues();
                }
                if (!ListenerUtil.mutListener.listen(6976)) {
                    buildLists();
                }
                if (!ListenerUtil.mutListener.listen(6977)) {
                    updateSummaries();
                }
                if (!ListenerUtil.mutListener.listen(6979)) {
                    {
                        long _loopCounter125 = 0;
                        // and update any listeners
                        for (OnSharedPreferenceChangeListener listener : listeners) {
                            ListenerUtil.loopListener.listen("_loopCounter125", ++_loopCounter125);
                            if (!ListenerUtil.mutListener.listen(6978)) {
                                listener.onSharedPreferenceChanged(DeckPreferenceHack.this, null);
                            }
                        }
                    }
                }
                return true;
            }

            @Override
            public SharedPreferences.Editor putBoolean(String key, boolean value) {
                if (!ListenerUtil.mutListener.listen(6980)) {
                    mUpdate.put(key, value);
                }
                return this;
            }

            @Override
            public SharedPreferences.Editor putFloat(String key, float value) {
                if (!ListenerUtil.mutListener.listen(6981)) {
                    mUpdate.put(key, value);
                }
                return this;
            }

            @Override
            public SharedPreferences.Editor putInt(String key, int value) {
                if (!ListenerUtil.mutListener.listen(6982)) {
                    mUpdate.put(key, value);
                }
                return this;
            }

            @Override
            public SharedPreferences.Editor putLong(String key, long value) {
                if (!ListenerUtil.mutListener.listen(6983)) {
                    mUpdate.put(key, value);
                }
                return this;
            }

            @Override
            public SharedPreferences.Editor putString(String key, String value) {
                if (!ListenerUtil.mutListener.listen(6984)) {
                    mUpdate.put(key, value);
                }
                return this;
            }

            @Override
            public SharedPreferences.Editor remove(String key) {
                if (!ListenerUtil.mutListener.listen(6985)) {
                    Timber.d("Editor.remove(key=%s)", key);
                }
                if (!ListenerUtil.mutListener.listen(6986)) {
                    mUpdate.remove(key);
                }
                return this;
            }

            public void apply() {
                if (!ListenerUtil.mutListener.listen(6987)) {
                    commit();
                }
            }

            // @Override On Android 1.5 this is not Override
            public android.content.SharedPreferences.Editor putStringSet(String arg0, Set<String> arg1) {
                // TODO Auto-generated method stub
                return null;
            }

            public DeckPreferenceHack getDeckPreferenceHack() {
                return DeckPreferenceHack.this;
            }

            public ConfChangeHandler confChangeHandler() {
                return new ConfChangeHandler(DeckPreferenceHack.this);
            }

            /**
             * Remove the currently selected options group
             */
            private void remConf() throws ConfirmModSchemaException {
                if (!ListenerUtil.mutListener.listen(6988)) {
                    // Remove options group, asking user to confirm full sync if necessary
                    mCol.getDecks().remConf(mOptions.getLong("id"));
                }
                if (!ListenerUtil.mutListener.listen(6989)) {
                    // Run the CPU intensive re-sort operation in a background thread
                    TaskManager.launchCollectionTask(new CollectionTask.ConfRemove(mOptions), confChangeHandler());
                }
                if (!ListenerUtil.mutListener.listen(6990)) {
                    mDeck.put("conf", 1);
                }
            }
        }

        @Override
        public boolean contains(String key) {
            return mValues.containsKey(key);
        }

        @Override
        public Editor edit() {
            return new Editor();
        }

        @Override
        public Map<String, ?> getAll() {
            return mValues;
        }

        @Override
        public boolean getBoolean(String key, boolean defValue) {
            return Boolean.parseBoolean(this.getString(key, Boolean.toString(defValue)));
        }

        @Override
        public float getFloat(String key, float defValue) {
            return Float.parseFloat(this.getString(key, Float.toString(defValue)));
        }

        @Override
        public int getInt(String key, int defValue) {
            return Integer.parseInt(this.getString(key, Integer.toString(defValue)));
        }

        @Override
        public long getLong(String key, long defValue) {
            return Long.parseLong(this.getString(key, Long.toString(defValue)));
        }

        @Override
        public String getString(String key, String defValue) {
            if (!ListenerUtil.mutListener.listen(6991)) {
                Timber.d("getString(key=%s, defValue=%s)", key, defValue);
            }
            if (!ListenerUtil.mutListener.listen(6992)) {
                if (!mValues.containsKey(key)) {
                    return defValue;
                }
            }
            return mValues.get(key);
        }

        @Override
        public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
            if (!ListenerUtil.mutListener.listen(6993)) {
                listeners.add(listener);
            }
        }

        @Override
        public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
            if (!ListenerUtil.mutListener.listen(6994)) {
                listeners.remove(listener);
            }
        }

        // @Override On Android 1.5 this is not Override
        public Set<String> getStringSet(String arg0, Set<String> arg1) {
            // TODO Auto-generated method stub
            return null;
        }
    }

    private static class ConfChangeHandler extends TaskListenerWithContext<DeckPreferenceHack, Void, Boolean> {

        public ConfChangeHandler(DeckPreferenceHack deckPreferenceHack) {
            super(deckPreferenceHack);
        }

        @Override
        public void actualOnPreExecute(@NonNull DeckPreferenceHack deckPreferenceHack) {
            Resources res = deckPreferenceHack.getDeckOptions().getResources();
            if (!ListenerUtil.mutListener.listen(6995)) {
                deckPreferenceHack.mProgressDialog = StyledProgressDialog.show(deckPreferenceHack.getDeckOptions(), "", res.getString(R.string.reordering_cards), false);
            }
        }

        @Override
        public void actualOnPostExecute(@NonNull DeckPreferenceHack deckPreferenceHack, Boolean result) {
            if (!ListenerUtil.mutListener.listen(6996)) {
                deckPreferenceHack.cacheValues();
            }
            if (!ListenerUtil.mutListener.listen(6997)) {
                deckPreferenceHack.getDeckOptions().buildLists();
            }
            if (!ListenerUtil.mutListener.listen(6998)) {
                deckPreferenceHack.getDeckOptions().updateSummaries();
            }
            if (!ListenerUtil.mutListener.listen(6999)) {
                deckPreferenceHack.mProgressDialog.dismiss();
            }
            if (!ListenerUtil.mutListener.listen(7000)) {
                // Restart to reflect the new preference values
                deckPreferenceHack.getDeckOptions().restartActivity();
            }
        }
    }

    @Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
        if (!ListenerUtil.mutListener.listen(7001)) {
            Timber.d("getSharedPreferences(name=%s)", name);
        }
        return mPref;
    }

    @Override
    // conversion to fragments tracked as #5019 in github
    @SuppressWarnings("deprecation")
    protected void onCreate(Bundle icicle) {
        if (!ListenerUtil.mutListener.listen(7002)) {
            Themes.setThemeLegacy(this);
        }
        if (!ListenerUtil.mutListener.listen(7003)) {
            super.onCreate(icicle);
        }
        if (!ListenerUtil.mutListener.listen(7004)) {
            mCol = CollectionHelper.getInstance().getCol(this);
        }
        if (!ListenerUtil.mutListener.listen(7006)) {
            if (mCol == null) {
                if (!ListenerUtil.mutListener.listen(7005)) {
                    finish();
                }
                return;
            }
        }
        Bundle extras = getIntent().getExtras();
        if (!ListenerUtil.mutListener.listen(7010)) {
            if ((ListenerUtil.mutListener.listen(7007) ? (extras != null || extras.containsKey("did")) : (extras != null && extras.containsKey("did")))) {
                if (!ListenerUtil.mutListener.listen(7009)) {
                    mDeck = mCol.getDecks().get(extras.getLong("did"));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7008)) {
                    mDeck = mCol.getDecks().current();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7011)) {
            registerExternalStorageListener();
        }
        if (!ListenerUtil.mutListener.listen(7026)) {
            if (mCol == null) {
                if (!ListenerUtil.mutListener.listen(7024)) {
                    Timber.w("DeckOptions - No Collection loaded");
                }
                if (!ListenerUtil.mutListener.listen(7025)) {
                    finish();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7012)) {
                    mPref = new DeckPreferenceHack();
                }
                if (!ListenerUtil.mutListener.listen(7013)) {
                    // #6068 - constructor can call finish()
                    if (this.isFinishing()) {
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(7014)) {
                    mPref.registerOnSharedPreferenceChangeListener(this);
                }
                if (!ListenerUtil.mutListener.listen(7015)) {
                    this.addPreferencesFromResource(R.xml.deck_options);
                }
                if (!ListenerUtil.mutListener.listen(7017)) {
                    if (this.isSchedV2()) {
                        if (!ListenerUtil.mutListener.listen(7016)) {
                            this.enableSchedV2Preferences();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7018)) {
                    this.buildLists();
                }
                if (!ListenerUtil.mutListener.listen(7019)) {
                    this.updateSummaries();
                }
                // Set the activity title to include the name of the deck
                String title = getResources().getString(R.string.deckpreferences_title);
                if (!ListenerUtil.mutListener.listen(7022)) {
                    if (title.contains("XXX")) {
                        try {
                            if (!ListenerUtil.mutListener.listen(7021)) {
                                title = title.replace("XXX", mDeck.getString("name"));
                            }
                        } catch (JSONException e) {
                            if (!ListenerUtil.mutListener.listen(7020)) {
                                title = title.replace("XXX", "???");
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7023)) {
                    this.setTitle(title);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7027)) {
            // Add a home button to the actionbar
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(7028)) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    // TODO Tracked in https://github.com/ankidroid/Anki-Android/issues/5019
    @SuppressWarnings("deprecation")
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(7030)) {
            if (item.getItemId() == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(7029)) {
                    closeWithResult();
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (!ListenerUtil.mutListener.listen(7031)) {
            // update values on changed preference
            mPreferenceChanged = true;
        }
        if (!ListenerUtil.mutListener.listen(7032)) {
            this.updateSummaries();
        }
    }

    // Workaround for bug 4611: http://code.google.com/p/android/issues/detail?id=4611
    // TODO Tracked in https://github.com/ankidroid/Anki-Android/issues/5019
    @SuppressWarnings("deprecation")
    @Override
    public boolean onPreferenceTreeClick(android.preference.PreferenceScreen preferenceScreen, android.preference.Preference preference) {
        if (!ListenerUtil.mutListener.listen(7033)) {
            super.onPreferenceTreeClick(preferenceScreen, preference);
        }
        if (!ListenerUtil.mutListener.listen(7036)) {
            if ((ListenerUtil.mutListener.listen(7034) ? (preference instanceof android.preference.PreferenceScreen || ((android.preference.PreferenceScreen) preference).getDialog() != null) : (preference instanceof android.preference.PreferenceScreen && ((android.preference.PreferenceScreen) preference).getDialog() != null))) {
                if (!ListenerUtil.mutListener.listen(7035)) {
                    ((android.preference.PreferenceScreen) preference).getDialog().getWindow().getDecorView().setBackgroundDrawable(this.getWindow().getDecorView().getBackground().getConstantState().newDrawable());
                }
            }
        }
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!ListenerUtil.mutListener.listen(7040)) {
            if ((ListenerUtil.mutListener.listen(7037) ? (keyCode == KeyEvent.KEYCODE_BACK || event.getRepeatCount() == 0) : (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0))) {
                if (!ListenerUtil.mutListener.listen(7038)) {
                    Timber.i("DeckOptions - onBackPressed()");
                }
                if (!ListenerUtil.mutListener.listen(7039)) {
                    closeWithResult();
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void closeWithResult() {
        if (!ListenerUtil.mutListener.listen(7043)) {
            if (mPreferenceChanged) {
                if (!ListenerUtil.mutListener.listen(7042)) {
                    setResult(RESULT_OK);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7041)) {
                    setResult(RESULT_CANCELED);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7044)) {
            finish();
        }
        if (!ListenerUtil.mutListener.listen(7045)) {
            ActivityTransitionAnimation.slide(this, FADE);
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(7046)) {
            super.onDestroy();
        }
        if (!ListenerUtil.mutListener.listen(7048)) {
            if (mUnmountReceiver != null) {
                if (!ListenerUtil.mutListener.listen(7047)) {
                    unregisterReceiver(mUnmountReceiver);
                }
            }
        }
    }

    // TODO Tracked in https://github.com/ankidroid/Anki-Android/issues/5019
    @SuppressWarnings("deprecation")
    protected void updateSummaries() {
        Resources res = getResources();
        if (!ListenerUtil.mutListener.listen(7061)) {
            {
                long _loopCounter126 = 0;
                // for all text preferences, set summary as current database value
                for (String key : mPref.mValues.keySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter126", ++_loopCounter126);
                    android.preference.Preference pref = this.findPreference(key);
                    if (!ListenerUtil.mutListener.listen(7051)) {
                        if ("deckConf".equals(key)) {
                            String groupName = getOptionsGroupName();
                            int count = getOptionsGroupCount();
                            if (!ListenerUtil.mutListener.listen(7049)) {
                                // Escape "%" in groupName as it's treated as a token
                                groupName = groupName.replaceAll("%", "%%");
                            }
                            if (!ListenerUtil.mutListener.listen(7050)) {
                                pref.setSummary(res.getQuantityString(R.plurals.deck_conf_group_summ, count, groupName, count));
                            }
                            continue;
                        }
                    }
                    String value = null;
                    if (!ListenerUtil.mutListener.listen(7054)) {
                        if (pref == null) {
                            continue;
                        } else if (pref instanceof android.preference.CheckBoxPreference) {
                            continue;
                        } else if (pref instanceof android.preference.ListPreference) {
                            android.preference.ListPreference lp = (android.preference.ListPreference) pref;
                            if (!ListenerUtil.mutListener.listen(7053)) {
                                value = lp.getEntry() != null ? lp.getEntry().toString() : "";
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(7052)) {
                                value = this.mPref.getString(key, "");
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(7056)) {
                        // update summary
                        if (!mPref.mSummaries.containsKey(key)) {
                            CharSequence s = pref.getSummary();
                            if (!ListenerUtil.mutListener.listen(7055)) {
                                mPref.mSummaries.put(key, s != null ? pref.getSummary().toString() : null);
                            }
                        }
                    }
                    String summ = mPref.mSummaries.get(key);
                    if (!ListenerUtil.mutListener.listen(7060)) {
                        if ((ListenerUtil.mutListener.listen(7057) ? (summ != null || summ.contains("XXX")) : (summ != null && summ.contains("XXX")))) {
                            if (!ListenerUtil.mutListener.listen(7059)) {
                                pref.setSummary(summ.replace("XXX", value));
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(7058)) {
                                pref.setSummary(value);
                            }
                        }
                    }
                }
            }
        }
        // Update summaries of preference items that don't have values (aren't in mValues)
        int subDeckCount = getSubdeckCount();
        if (!ListenerUtil.mutListener.listen(7062)) {
            this.findPreference("confSetSubdecks").setSummary(res.getQuantityString(R.plurals.deck_conf_set_subdecks_summ, subDeckCount, subDeckCount));
        }
    }

    // TODO Tracked in https://github.com/ankidroid/Anki-Android/issues/5019
    @SuppressWarnings("deprecation")
    protected void buildLists() {
        android.preference.ListPreference deckConfPref = (android.preference.ListPreference) findPreference("deckConf");
        ArrayList<DeckConfig> confs = mCol.getDecks().allConf();
        if (!ListenerUtil.mutListener.listen(7063)) {
            Collections.sort(confs, NamedJSONComparator.instance);
        }
        String[] confValues = new String[confs.size()];
        String[] confLabels = new String[confs.size()];
        if (!ListenerUtil.mutListener.listen(7071)) {
            {
                long _loopCounter127 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(7070) ? (i >= confs.size()) : (ListenerUtil.mutListener.listen(7069) ? (i <= confs.size()) : (ListenerUtil.mutListener.listen(7068) ? (i > confs.size()) : (ListenerUtil.mutListener.listen(7067) ? (i != confs.size()) : (ListenerUtil.mutListener.listen(7066) ? (i == confs.size()) : (i < confs.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter127", ++_loopCounter127);
                    DeckConfig o = confs.get(i);
                    if (!ListenerUtil.mutListener.listen(7064)) {
                        confValues[i] = o.getString("id");
                    }
                    if (!ListenerUtil.mutListener.listen(7065)) {
                        confLabels[i] = o.getString("name");
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7072)) {
            deckConfPref.setEntries(confLabels);
        }
        if (!ListenerUtil.mutListener.listen(7073)) {
            deckConfPref.setEntryValues(confValues);
        }
        if (!ListenerUtil.mutListener.listen(7074)) {
            deckConfPref.setValue(mPref.getString("deckConf", "0"));
        }
        android.preference.ListPreference newOrderPref = (android.preference.ListPreference) findPreference("newOrder");
        if (!ListenerUtil.mutListener.listen(7075)) {
            newOrderPref.setEntries(R.array.new_order_labels);
        }
        if (!ListenerUtil.mutListener.listen(7076)) {
            newOrderPref.setEntryValues(R.array.new_order_values);
        }
        if (!ListenerUtil.mutListener.listen(7077)) {
            newOrderPref.setValue(mPref.getString("newOrder", "0"));
        }
        android.preference.ListPreference leechActPref = (android.preference.ListPreference) findPreference("lapLeechAct");
        if (!ListenerUtil.mutListener.listen(7078)) {
            leechActPref.setEntries(R.array.leech_action_labels);
        }
        if (!ListenerUtil.mutListener.listen(7079)) {
            leechActPref.setEntryValues(R.array.leech_action_values);
        }
        if (!ListenerUtil.mutListener.listen(7080)) {
            leechActPref.setValue(mPref.getString("lapLeechAct", Integer.toString(Consts.LEECH_SUSPEND)));
        }
    }

    private boolean isSchedV2() {
        return (ListenerUtil.mutListener.listen(7085) ? (mCol.schedVer() >= 2) : (ListenerUtil.mutListener.listen(7084) ? (mCol.schedVer() <= 2) : (ListenerUtil.mutListener.listen(7083) ? (mCol.schedVer() > 2) : (ListenerUtil.mutListener.listen(7082) ? (mCol.schedVer() < 2) : (ListenerUtil.mutListener.listen(7081) ? (mCol.schedVer() != 2) : (mCol.schedVer() == 2))))));
    }

    /**
     * Enable deck preferences that are only available with Scheduler V2.
     */
    // TODO Tracked in https://github.com/ankidroid/Anki-Android/issues/5019
    @SuppressWarnings("deprecation")
    protected void enableSchedV2Preferences() {
        NumberRangePreference hardFactorPreference = (NumberRangePreference) findPreference("hardFactor");
        if (!ListenerUtil.mutListener.listen(7086)) {
            hardFactorPreference.setEnabled(true);
        }
    }

    /**
     * Returns the number of decks using the options group of the current deck.
     */
    private int getOptionsGroupCount() {
        int count = 0;
        long conf = mDeck.getLong("conf");
        if (!ListenerUtil.mutListener.listen(7090)) {
            {
                long _loopCounter128 = 0;
                for (Deck deck : mCol.getDecks().all()) {
                    ListenerUtil.loopListener.listen("_loopCounter128", ++_loopCounter128);
                    if (!ListenerUtil.mutListener.listen(7087)) {
                        if (deck.isDyn()) {
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(7089)) {
                        if (deck.getLong("conf") == conf) {
                            if (!ListenerUtil.mutListener.listen(7088)) {
                                count++;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    /**
     * Get the name of the currently set options group
     */
    private String getOptionsGroupName() {
        long confId = mPref.getLong("deckConf", 0);
        return mCol.getDecks().getConf(confId).getString("name");
    }

    /**
     * Get the number of (non-dynamic) subdecks for the current deck
     */
    private int getSubdeckCount() {
        int count = 0;
        long did = mDeck.getLong("id");
        TreeMap<String, Long> children = mCol.getDecks().children(did);
        if (!ListenerUtil.mutListener.listen(7093)) {
            {
                long _loopCounter129 = 0;
                for (long childDid : children.values()) {
                    ListenerUtil.loopListener.listen("_loopCounter129", ++_loopCounter129);
                    Deck child = mCol.getDecks().get(childDid);
                    if (!ListenerUtil.mutListener.listen(7091)) {
                        if (child.isDyn()) {
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(7092)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * finish when sd card is ejected
     */
    private void registerExternalStorageListener() {
        if (!ListenerUtil.mutListener.listen(7099)) {
            if (mUnmountReceiver == null) {
                if (!ListenerUtil.mutListener.listen(7096)) {
                    mUnmountReceiver = new BroadcastReceiver() {

                        @Override
                        public void onReceive(Context context, Intent intent) {
                            if (!ListenerUtil.mutListener.listen(7095)) {
                                if (SdCardReceiver.MEDIA_EJECT.equals(intent.getAction())) {
                                    if (!ListenerUtil.mutListener.listen(7094)) {
                                        finish();
                                    }
                                }
                            }
                        }
                    };
                }
                IntentFilter iFilter = new IntentFilter();
                if (!ListenerUtil.mutListener.listen(7097)) {
                    iFilter.addAction(SdCardReceiver.MEDIA_EJECT);
                }
                if (!ListenerUtil.mutListener.listen(7098)) {
                    registerReceiver(mUnmountReceiver, iFilter);
                }
            }
        }
    }

    private void restartActivity() {
        if (!ListenerUtil.mutListener.listen(7100)) {
            recreate();
        }
    }

    public static Calendar reminderToCalendar(Time time, JSONObject reminder) {
        final Calendar calendar = time.calendar();
        if (!ListenerUtil.mutListener.listen(7101)) {
            calendar.set(Calendar.HOUR_OF_DAY, reminder.getJSONArray("time").getInt(0));
        }
        if (!ListenerUtil.mutListener.listen(7102)) {
            calendar.set(Calendar.MINUTE, reminder.getJSONArray("time").getInt(1));
        }
        if (!ListenerUtil.mutListener.listen(7103)) {
            calendar.set(Calendar.SECOND, 0);
        }
        return calendar;
    }
}
