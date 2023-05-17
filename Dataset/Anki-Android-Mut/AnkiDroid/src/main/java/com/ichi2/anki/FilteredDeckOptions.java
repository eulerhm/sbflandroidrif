package com.ichi2.anki;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import com.ichi2.anim.ActivityTransitionAnimation;
import com.ichi2.anki.receiver.SdCardReceiver;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.Deck;
import com.ichi2.preferences.StepsPreference;
import com.ichi2.themes.Themes;
import com.ichi2.ui.AppCompatPreferenceActivity;
import com.ichi2.anki.analytics.UsageAnalytics;
import com.ichi2.utils.JSONArray;
import com.ichi2.utils.JSONException;
import com.ichi2.utils.JSONObject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import timber.log.Timber;
import static com.ichi2.anim.ActivityTransitionAnimation.Direction.FADE;
import static com.ichi2.libanki.Consts.DECK_STD;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Preferences for the current deck.
 */
public class FilteredDeckOptions extends AppCompatPreferenceActivity implements OnSharedPreferenceChangeListener {

    private Deck mDeck;

    private Collection mCol;

    private boolean mAllowCommit = true;

    private boolean mPrefChanged = false;

    private BroadcastReceiver mUnmountReceiver = null;

    // TODO: not anymore used in libanki?
    private final String[] dynExamples = new String[] { null, "{'search'=\"is:new\", 'resched'=False, 'steps'=\"1\", 'order'=5}", "{'search'=\"added:1\", 'resched'=False, 'steps'=\"1\", 'order'=5}", "{'search'=\"rated:1:1\", 'order'=4}", "{'search'=\"prop:due<=2\", 'order'=6}", "{'search'=\"is:due tag:TAG\", 'order'=6}", "{'search'=\"is:due\", 'order'=3}", "{'search'=\"\", 'steps'=\"1 10 20\", 'order'=0}" };

    public class DeckPreferenceHack implements SharedPreferences {

        private final Map<String, String> mValues = new HashMap<>();

        private final Map<String, String> mSummaries = new HashMap<>();

        public DeckPreferenceHack() {
            if (!ListenerUtil.mutListener.listen(8397)) {
                this.cacheValues();
            }
        }

        protected void cacheValues() {
            if (!ListenerUtil.mutListener.listen(8398)) {
                Timber.d("cacheValues()");
            }
            JSONArray ar = mDeck.getJSONArray("terms").getJSONArray(0);
            if (!ListenerUtil.mutListener.listen(8399)) {
                mValues.put("search", ar.getString(0));
            }
            if (!ListenerUtil.mutListener.listen(8400)) {
                mValues.put("limit", ar.getString(1));
            }
            if (!ListenerUtil.mutListener.listen(8401)) {
                mValues.put("order", ar.getString(2));
            }
            JSONArray delays = mDeck.optJSONArray("delays");
            if (!ListenerUtil.mutListener.listen(8406)) {
                if (delays != null) {
                    if (!ListenerUtil.mutListener.listen(8404)) {
                        mValues.put("steps", StepsPreference.convertFromJSON(delays));
                    }
                    if (!ListenerUtil.mutListener.listen(8405)) {
                        mValues.put("stepsOn", Boolean.toString(true));
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(8402)) {
                        mValues.put("steps", "1 10");
                    }
                    if (!ListenerUtil.mutListener.listen(8403)) {
                        mValues.put("stepsOn", Boolean.toString(false));
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8407)) {
                mValues.put("resched", Boolean.toString(mDeck.getBoolean("resched")));
            }
        }

        public class Editor implements SharedPreferences.Editor {

            private ContentValues mUpdate = new ContentValues();

            @Override
            public SharedPreferences.Editor clear() {
                if (!ListenerUtil.mutListener.listen(8408)) {
                    Timber.d("clear()");
                }
                if (!ListenerUtil.mutListener.listen(8409)) {
                    mUpdate = new ContentValues();
                }
                return this;
            }

            @Override
            public boolean commit() {
                if (!ListenerUtil.mutListener.listen(8410)) {
                    Timber.d("commit() changes back to database");
                }
                if (!ListenerUtil.mutListener.listen(8443)) {
                    {
                        long _loopCounter137 = 0;
                        for (Entry<String, Object> entry : mUpdate.valueSet()) {
                            ListenerUtil.loopListener.listen("_loopCounter137", ++_loopCounter137);
                            if (!ListenerUtil.mutListener.listen(8411)) {
                                Timber.i("Change value for key '%s': %s", entry.getKey(), entry.getValue());
                            }
                            if (!ListenerUtil.mutListener.listen(8442)) {
                                if ("search".equals(entry.getKey())) {
                                    JSONArray ar = mDeck.getJSONArray("terms");
                                    if (!ListenerUtil.mutListener.listen(8441)) {
                                        ar.getJSONArray(0).put(0, entry.getValue());
                                    }
                                } else if ("limit".equals(entry.getKey())) {
                                    JSONArray ar = mDeck.getJSONArray("terms");
                                    if (!ListenerUtil.mutListener.listen(8440)) {
                                        ar.getJSONArray(0).put(1, entry.getValue());
                                    }
                                } else if ("order".equals(entry.getKey())) {
                                    JSONArray ar = mDeck.getJSONArray("terms");
                                    if (!ListenerUtil.mutListener.listen(8439)) {
                                        ar.getJSONArray(0).put(2, Integer.parseInt((String) entry.getValue()));
                                    }
                                } else if ("resched".equals(entry.getKey())) {
                                    if (!ListenerUtil.mutListener.listen(8438)) {
                                        mDeck.put("resched", entry.getValue());
                                    }
                                } else if ("stepsOn".equals(entry.getKey())) {
                                    boolean on = (Boolean) entry.getValue();
                                    if (!ListenerUtil.mutListener.listen(8437)) {
                                        if (on) {
                                            JSONArray steps = StepsPreference.convertToJSON(mValues.get("steps"));
                                            if (!ListenerUtil.mutListener.listen(8436)) {
                                                if ((ListenerUtil.mutListener.listen(8434) ? (steps.length() >= 0) : (ListenerUtil.mutListener.listen(8433) ? (steps.length() <= 0) : (ListenerUtil.mutListener.listen(8432) ? (steps.length() < 0) : (ListenerUtil.mutListener.listen(8431) ? (steps.length() != 0) : (ListenerUtil.mutListener.listen(8430) ? (steps.length() == 0) : (steps.length() > 0))))))) {
                                                    if (!ListenerUtil.mutListener.listen(8435)) {
                                                        mDeck.put("delays", steps);
                                                    }
                                                }
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(8429)) {
                                                mDeck.put("delays", JSONObject.NULL);
                                            }
                                        }
                                    }
                                } else if ("steps".equals(entry.getKey())) {
                                    if (!ListenerUtil.mutListener.listen(8428)) {
                                        mDeck.put("delays", StepsPreference.convertToJSON((String) entry.getValue()));
                                    }
                                } else if ("preset".equals(entry.getKey())) {
                                    int i = Integer.parseInt((String) entry.getValue());
                                    if (!ListenerUtil.mutListener.listen(8427)) {
                                        if ((ListenerUtil.mutListener.listen(8416) ? (i >= 0) : (ListenerUtil.mutListener.listen(8415) ? (i <= 0) : (ListenerUtil.mutListener.listen(8414) ? (i < 0) : (ListenerUtil.mutListener.listen(8413) ? (i != 0) : (ListenerUtil.mutListener.listen(8412) ? (i == 0) : (i > 0))))))) {
                                            JSONObject presetValues = new JSONObject(dynExamples[i]);
                                            JSONArray ar = presetValues.names();
                                            if (!ListenerUtil.mutListener.listen(8424)) {
                                                {
                                                    long _loopCounter136 = 0;
                                                    for (String name : ar.stringIterable()) {
                                                        ListenerUtil.loopListener.listen("_loopCounter136", ++_loopCounter136);
                                                        if (!ListenerUtil.mutListener.listen(8418)) {
                                                            if ("steps".equals(name)) {
                                                                if (!ListenerUtil.mutListener.listen(8417)) {
                                                                    mUpdate.put("stepsOn", true);
                                                                }
                                                            }
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(8423)) {
                                                            if ("resched".equals(name)) {
                                                                if (!ListenerUtil.mutListener.listen(8421)) {
                                                                    mUpdate.put(name, presetValues.getBoolean(name));
                                                                }
                                                                if (!ListenerUtil.mutListener.listen(8422)) {
                                                                    mValues.put(name, Boolean.toString(presetValues.getBoolean(name)));
                                                                }
                                                            } else {
                                                                if (!ListenerUtil.mutListener.listen(8419)) {
                                                                    mUpdate.put(name, presetValues.getString(name));
                                                                }
                                                                if (!ListenerUtil.mutListener.listen(8420)) {
                                                                    mValues.put(name, presetValues.getString(name));
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(8425)) {
                                                mUpdate.put("preset", "0");
                                            }
                                            if (!ListenerUtil.mutListener.listen(8426)) {
                                                commit();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                // save deck
                try {
                    if (!ListenerUtil.mutListener.listen(8448)) {
                        mCol.getDecks().save(mDeck);
                    }
                } catch (RuntimeException e) {
                    if (!ListenerUtil.mutListener.listen(8444)) {
                        Timber.e(e, "RuntimeException on saving deck");
                    }
                    if (!ListenerUtil.mutListener.listen(8445)) {
                        AnkiDroidApp.sendExceptionReport(e, "FilteredDeckOptionsSaveDeck");
                    }
                    if (!ListenerUtil.mutListener.listen(8446)) {
                        setResult(DeckPicker.RESULT_DB_ERROR);
                    }
                    if (!ListenerUtil.mutListener.listen(8447)) {
                        finish();
                    }
                }
                if (!ListenerUtil.mutListener.listen(8449)) {
                    // make sure we refresh the parent cached values
                    cacheValues();
                }
                if (!ListenerUtil.mutListener.listen(8450)) {
                    updateSummaries();
                }
                if (!ListenerUtil.mutListener.listen(8452)) {
                    {
                        long _loopCounter138 = 0;
                        // and update any listeners
                        for (OnSharedPreferenceChangeListener listener : listeners) {
                            ListenerUtil.loopListener.listen("_loopCounter138", ++_loopCounter138);
                            if (!ListenerUtil.mutListener.listen(8451)) {
                                listener.onSharedPreferenceChanged(DeckPreferenceHack.this, null);
                            }
                        }
                    }
                }
                return true;
            }

            @Override
            public SharedPreferences.Editor putBoolean(String key, boolean value) {
                if (!ListenerUtil.mutListener.listen(8453)) {
                    mUpdate.put(key, value);
                }
                return this;
            }

            @Override
            public SharedPreferences.Editor putFloat(String key, float value) {
                if (!ListenerUtil.mutListener.listen(8454)) {
                    mUpdate.put(key, value);
                }
                return this;
            }

            @Override
            public SharedPreferences.Editor putInt(String key, int value) {
                if (!ListenerUtil.mutListener.listen(8455)) {
                    mUpdate.put(key, value);
                }
                return this;
            }

            @Override
            public SharedPreferences.Editor putLong(String key, long value) {
                if (!ListenerUtil.mutListener.listen(8456)) {
                    mUpdate.put(key, value);
                }
                return this;
            }

            @Override
            public SharedPreferences.Editor putString(String key, String value) {
                if (!ListenerUtil.mutListener.listen(8457)) {
                    mUpdate.put(key, value);
                }
                return this;
            }

            @Override
            public SharedPreferences.Editor remove(String key) {
                if (!ListenerUtil.mutListener.listen(8458)) {
                    Timber.d("Editor.remove(key=%s)", key);
                }
                if (!ListenerUtil.mutListener.listen(8459)) {
                    mUpdate.remove(key);
                }
                return this;
            }

            public void apply() {
                if (!ListenerUtil.mutListener.listen(8461)) {
                    if (mAllowCommit) {
                        if (!ListenerUtil.mutListener.listen(8460)) {
                            commit();
                        }
                    }
                }
            }

            // @Override On Android 1.5 this is not Override
            public android.content.SharedPreferences.Editor putStringSet(String arg0, Set<String> arg1) {
                // TODO Auto-generated method stub
                return null;
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
            if (!ListenerUtil.mutListener.listen(8462)) {
                Timber.d("getString(key=%s, defValue=%s)", key, defValue);
            }
            if (!ListenerUtil.mutListener.listen(8463)) {
                if (!mValues.containsKey(key)) {
                    return defValue;
                }
            }
            return mValues.get(key);
        }

        public final List<OnSharedPreferenceChangeListener> listeners = new LinkedList<>();

        @Override
        public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
            if (!ListenerUtil.mutListener.listen(8464)) {
                listeners.add(listener);
            }
        }

        @Override
        public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
            if (!ListenerUtil.mutListener.listen(8465)) {
                listeners.remove(listener);
            }
        }

        // @Override On Android 1.5 this is not Override
        public Set<String> getStringSet(String arg0, Set<String> arg1) {
            // TODO Auto-generated method stub
            return null;
        }
    }

    private DeckPreferenceHack mPref;

    @Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
        if (!ListenerUtil.mutListener.listen(8466)) {
            Timber.d("getSharedPreferences(name=%s)", name);
        }
        return mPref;
    }

    @Override
    // Tracked as #5019 on github
    @SuppressWarnings("deprecation")
    protected void onCreate(Bundle icicle) {
        if (!ListenerUtil.mutListener.listen(8467)) {
            Themes.setThemeLegacy(this);
        }
        if (!ListenerUtil.mutListener.listen(8468)) {
            super.onCreate(icicle);
        }
        if (!ListenerUtil.mutListener.listen(8469)) {
            UsageAnalytics.sendAnalyticsScreenView(this);
        }
        if (!ListenerUtil.mutListener.listen(8470)) {
            mCol = CollectionHelper.getInstance().getCol(this);
        }
        if (!ListenerUtil.mutListener.listen(8472)) {
            if (mCol == null) {
                if (!ListenerUtil.mutListener.listen(8471)) {
                    finish();
                }
                return;
            }
        }
        Bundle extras = getIntent().getExtras();
        if (!ListenerUtil.mutListener.listen(8476)) {
            if ((ListenerUtil.mutListener.listen(8473) ? (extras != null || extras.containsKey("did")) : (extras != null && extras.containsKey("did")))) {
                if (!ListenerUtil.mutListener.listen(8475)) {
                    mDeck = mCol.getDecks().get(extras.getLong("did"));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8474)) {
                    mDeck = mCol.getDecks().current();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8477)) {
            registerExternalStorageListener();
        }
        if (!ListenerUtil.mutListener.listen(8486)) {
            if ((ListenerUtil.mutListener.listen(8478) ? (mCol == null && mDeck.getInt("dyn") == DECK_STD) : (mCol == null || mDeck.getInt("dyn") == DECK_STD))) {
                if (!ListenerUtil.mutListener.listen(8484)) {
                    Timber.w("No Collection loaded or deck is not a dyn deck");
                }
                if (!ListenerUtil.mutListener.listen(8485)) {
                    finish();
                }
                return;
            } else {
                if (!ListenerUtil.mutListener.listen(8479)) {
                    mPref = new DeckPreferenceHack();
                }
                if (!ListenerUtil.mutListener.listen(8480)) {
                    mPref.registerOnSharedPreferenceChangeListener(this);
                }
                if (!ListenerUtil.mutListener.listen(8481)) {
                    this.addPreferencesFromResource(R.xml.cram_deck_options);
                }
                if (!ListenerUtil.mutListener.listen(8482)) {
                    this.buildLists();
                }
                if (!ListenerUtil.mutListener.listen(8483)) {
                    this.updateSummaries();
                }
            }
        }
        // Set the activity title to include the name of the deck
        String title = getResources().getString(R.string.deckpreferences_title);
        if (!ListenerUtil.mutListener.listen(8489)) {
            if (title.contains("XXX")) {
                try {
                    if (!ListenerUtil.mutListener.listen(8488)) {
                        title = title.replace("XXX", mDeck.getString("name"));
                    }
                } catch (JSONException e) {
                    if (!ListenerUtil.mutListener.listen(8487)) {
                        title = title.replace("XXX", "???");
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8490)) {
            this.setTitle(title);
        }
        if (!ListenerUtil.mutListener.listen(8491)) {
            // Add a home button to the actionbar
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(8492)) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    // TODO Tracked in https://github.com/ankidroid/Anki-Android/issues/5019
    @SuppressWarnings("deprecation")
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(8494)) {
            if (item.getItemId() == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(8493)) {
                    closeDeckOptions();
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (!ListenerUtil.mutListener.listen(8495)) {
            // update values on changed preference
            this.updateSummaries();
        }
        if (!ListenerUtil.mutListener.listen(8496)) {
            mPrefChanged = true;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!ListenerUtil.mutListener.listen(8500)) {
            if ((ListenerUtil.mutListener.listen(8497) ? (keyCode == KeyEvent.KEYCODE_BACK || event.getRepeatCount() == 0) : (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0))) {
                if (!ListenerUtil.mutListener.listen(8498)) {
                    Timber.i("DeckOptions - onBackPressed()");
                }
                if (!ListenerUtil.mutListener.listen(8499)) {
                    closeDeckOptions();
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void closeDeckOptions() {
        if (!ListenerUtil.mutListener.listen(8503)) {
            if (mPrefChanged) {
                // Rebuild the filtered deck if a setting has changed
                try {
                    if (!ListenerUtil.mutListener.listen(8502)) {
                        mCol.getSched().rebuildDyn(mDeck.getLong("id"));
                    }
                } catch (JSONException e) {
                    if (!ListenerUtil.mutListener.listen(8501)) {
                        Timber.e(e);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8504)) {
            finish();
        }
        if (!ListenerUtil.mutListener.listen(8505)) {
            ActivityTransitionAnimation.slide(this, FADE);
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(8506)) {
            super.onDestroy();
        }
        if (!ListenerUtil.mutListener.listen(8508)) {
            if (mUnmountReceiver != null) {
                if (!ListenerUtil.mutListener.listen(8507)) {
                    unregisterReceiver(mUnmountReceiver);
                }
            }
        }
    }

    // conversion to fragments tracked in github as #5019
    @SuppressWarnings("deprecation")
    protected void updateSummaries() {
        if (!ListenerUtil.mutListener.listen(8509)) {
            mAllowCommit = false;
        }
        Set<String> keys = mPref.mValues.keySet();
        if (!ListenerUtil.mutListener.listen(8518)) {
            {
                long _loopCounter139 = 0;
                for (String key : keys) {
                    ListenerUtil.loopListener.listen("_loopCounter139", ++_loopCounter139);
                    android.preference.Preference pref = this.findPreference(key);
                    String value;
                    if (pref == null) {
                        continue;
                    } else if (pref instanceof android.preference.CheckBoxPreference) {
                        continue;
                    } else if (pref instanceof android.preference.ListPreference) {
                        android.preference.ListPreference lp = (android.preference.ListPreference) pref;
                        CharSequence entry = lp.getEntry();
                        if (entry != null) {
                            value = entry.toString();
                        } else {
                            value = "";
                        }
                    } else {
                        value = this.mPref.getString(key, "");
                    }
                    if (!ListenerUtil.mutListener.listen(8511)) {
                        // update value for EditTexts
                        if (pref instanceof android.preference.EditTextPreference) {
                            if (!ListenerUtil.mutListener.listen(8510)) {
                                ((android.preference.EditTextPreference) pref).setText(value);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(8513)) {
                        // update summary
                        if (!mPref.mSummaries.containsKey(key)) {
                            CharSequence s = pref.getSummary();
                            if (!ListenerUtil.mutListener.listen(8512)) {
                                mPref.mSummaries.put(key, s != null ? pref.getSummary().toString() : null);
                            }
                        }
                    }
                    String summ = mPref.mSummaries.get(key);
                    if (!ListenerUtil.mutListener.listen(8517)) {
                        if ((ListenerUtil.mutListener.listen(8514) ? (summ != null || summ.contains("XXX")) : (summ != null && summ.contains("XXX")))) {
                            if (!ListenerUtil.mutListener.listen(8516)) {
                                pref.setSummary(summ.replace("XXX", value));
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(8515)) {
                                pref.setSummary(value);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8519)) {
            mAllowCommit = true;
        }
    }

    // Tracked as #5019 on github
    @SuppressWarnings("deprecation")
    protected void buildLists() {
        android.preference.ListPreference newOrderPref = (android.preference.ListPreference) findPreference("order");
        if (!ListenerUtil.mutListener.listen(8520)) {
            newOrderPref.setEntries(R.array.cram_deck_conf_order_labels);
        }
        if (!ListenerUtil.mutListener.listen(8521)) {
            newOrderPref.setEntryValues(R.array.cram_deck_conf_order_values);
        }
        if (!ListenerUtil.mutListener.listen(8522)) {
            newOrderPref.setValue(mPref.getString("order", "0"));
        }
    }

    /**
     * finish when sd card is ejected
     */
    private void registerExternalStorageListener() {
        if (!ListenerUtil.mutListener.listen(8528)) {
            if (mUnmountReceiver == null) {
                if (!ListenerUtil.mutListener.listen(8525)) {
                    mUnmountReceiver = new BroadcastReceiver() {

                        @Override
                        public void onReceive(Context context, Intent intent) {
                            if (!ListenerUtil.mutListener.listen(8524)) {
                                if (intent.getAction().equals(SdCardReceiver.MEDIA_EJECT)) {
                                    if (!ListenerUtil.mutListener.listen(8523)) {
                                        finish();
                                    }
                                }
                            }
                        }
                    };
                }
                IntentFilter iFilter = new IntentFilter();
                if (!ListenerUtil.mutListener.listen(8526)) {
                    iFilter.addAction(SdCardReceiver.MEDIA_EJECT);
                }
                if (!ListenerUtil.mutListener.listen(8527)) {
                    registerReceiver(mUnmountReceiver, iFilter);
                }
            }
        }
    }
}
