/**
 * ************************************************************************************
 *  Copyright (c) 2009 Nicolas Raoul <nicolas.raoul@gmail.com>                           *
 *  Copyright (c) 2009 Edu Zamora <edu.zasu@gmail.com>                                   *
 *  Copyright (c) 2010 Norbert Nagold <norbert.nagold@gmail.com>                         *
 *  Copyright (c) 2012 Kostas Spyropoulos <inigo.aldana@gmail.com>                       *
 *  Copyright (c) 2015 Timothy Rae <perceptualchaos2@gmail.com>                          *
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

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.WindowManager.BadTokenException;
import android.webkit.URLUtil;
import android.widget.Toast;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ichi2.anim.ActivityTransitionAnimation;
import com.ichi2.anki.contextmenu.AnkiCardContextMenu;
import com.ichi2.anki.contextmenu.CardBrowserContextMenu;
import com.ichi2.anki.debug.DatabaseLock;
import com.ichi2.anki.exception.ConfirmModSchemaException;
import com.ichi2.anki.exception.StorageAccessException;
import com.ichi2.anki.services.BootService;
import com.ichi2.anki.services.NotificationService;
import com.ichi2.anki.web.CustomSyncServer;
import com.ichi2.compat.CompatHelper;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.Utils;
import com.ichi2.preferences.NumberRangePreference;
import com.ichi2.themes.Themes;
import com.ichi2.ui.AppCompatPreferenceActivity;
import com.ichi2.ui.ConfirmationPreference;
import com.ichi2.ui.SeekBarPreference;
import com.ichi2.utils.AdaptionUtil;
import com.ichi2.utils.LanguageUtil;
import com.ichi2.anki.analytics.UsageAnalytics;
import com.ichi2.utils.VersionUtils;
import com.ichi2.utils.JSONObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.annotation.VisibleForTesting;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import timber.log.Timber;
import static com.ichi2.anim.ActivityTransitionAnimation.Direction.FADE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

// TODO Tracked in https://github.com/ankidroid/Anki-Android/issues/5019
@SuppressWarnings("deprecation")
interface PreferenceContext {

    void addPreferencesFromResource(int preferencesResId);

    android.preference.PreferenceScreen getPreferenceScreen();
}

/**
 * Preferences dialog.
 */
// TODO Tracked in https://github.com/ankidroid/Anki-Android/issues/5019
@SuppressWarnings("deprecation")
public class Preferences extends AppCompatPreferenceActivity implements PreferenceContext, OnSharedPreferenceChangeListener {

    /**
     * Key of the language preference
     */
    public static final String LANGUAGE = "language";

    /* Only enable AnkiDroid notifications unrelated to due reminders */
    public static final int PENDING_NOTIFICATIONS_ONLY = 1000000;

    // Other variables
    private final HashMap<String, String> mOriginalSumarries = new HashMap<>();

    private static final String[] sCollectionPreferences = { "showEstimates", "showProgress", "learnCutoff", "timeLimit", "useCurrent", "newSpread", "dayOffset", "schedVer" };

    private static final int RESULT_LOAD_IMG = 111;

    private android.preference.CheckBoxPreference backgroundImage;

    private static long fileLength;

    // ----------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(10366)) {
            Themes.setThemeLegacy(this);
        }
        if (!ListenerUtil.mutListener.listen(10367)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(10368)) {
            // Add a home button to the actionbar
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(10369)) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(10370)) {
            setTitle(getResources().getText(R.string.preferences_title));
        }
    }

    private Collection getCol() {
        return CollectionHelper.getInstance().getCol(this);
    }

    @Override
    public void onBuildHeaders(List<Header> target) {
        if (!ListenerUtil.mutListener.listen(10371)) {
            loadHeadersFromResource(R.xml.preference_headers, target);
        }
        Iterator<Header> iterator = target.iterator();
        if (!ListenerUtil.mutListener.listen(10375)) {
            {
                long _loopCounter171 = 0;
                while (iterator.hasNext()) {
                    ListenerUtil.loopListener.listen("_loopCounter171", ++_loopCounter171);
                    Header header = iterator.next();
                    if (!ListenerUtil.mutListener.listen(10374)) {
                        if ((ListenerUtil.mutListener.listen(10372) ? ((header.titleRes == R.string.pref_cat_advanced) || AdaptionUtil.isRestrictedLearningDevice()) : ((header.titleRes == R.string.pref_cat_advanced) && AdaptionUtil.isRestrictedLearningDevice()))) {
                            if (!ListenerUtil.mutListener.listen(10373)) {
                                iterator.remove();
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        return SettingsFragment.class.getName().equals(fragmentName);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(10377)) {
            if (item.getItemId() == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(10376)) {
                    onBackPressed();
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (!ListenerUtil.mutListener.listen(10378)) {
            updatePreference(sharedPreferences, key, this);
        }
    }

    public static Intent getPreferenceSubscreenIntent(Context context, String subscreen) {
        Intent i = new Intent(context, Preferences.class);
        if (!ListenerUtil.mutListener.listen(10379)) {
            i.putExtra(android.preference.PreferenceActivity.EXTRA_SHOW_FRAGMENT, "com.ichi2.anki.Preferences$SettingsFragment");
        }
        Bundle extras = new Bundle();
        if (!ListenerUtil.mutListener.listen(10380)) {
            extras.putString("subscreen", subscreen);
        }
        if (!ListenerUtil.mutListener.listen(10381)) {
            i.putExtra(android.preference.PreferenceActivity.EXTRA_SHOW_FRAGMENT_ARGUMENTS, extras);
        }
        if (!ListenerUtil.mutListener.listen(10382)) {
            i.putExtra(android.preference.PreferenceActivity.EXTRA_NO_HEADERS, true);
        }
        return i;
    }

    private void initSubscreen(String action, PreferenceContext listener) {
        android.preference.PreferenceScreen screen;
        switch(action) {
            case "com.ichi2.anki.prefs.general":
                if (!ListenerUtil.mutListener.listen(10383)) {
                    listener.addPreferencesFromResource(R.xml.preferences_general);
                }
                screen = listener.getPreferenceScreen();
                if (!ListenerUtil.mutListener.listen(10386)) {
                    if (AdaptionUtil.isRestrictedLearningDevice()) {
                        android.preference.CheckBoxPreference mCheckBoxPref_Vibrate = (android.preference.CheckBoxPreference) screen.findPreference("widgetVibrate");
                        android.preference.CheckBoxPreference mCheckBoxPref_Blink = (android.preference.CheckBoxPreference) screen.findPreference("widgetBlink");
                        android.preference.PreferenceCategory mCategory = (android.preference.PreferenceCategory) screen.findPreference("category_general_notification_pref");
                        if (!ListenerUtil.mutListener.listen(10384)) {
                            mCategory.removePreference(mCheckBoxPref_Vibrate);
                        }
                        if (!ListenerUtil.mutListener.listen(10385)) {
                            mCategory.removePreference(mCheckBoxPref_Blink);
                        }
                    }
                }
                try {
                    // See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.
                    Drawable languageIcon = VectorDrawableCompat.create(getResources(), R.drawable.ic_language_black_24dp, getTheme());
                    if (!ListenerUtil.mutListener.listen(10388)) {
                        screen.findPreference("language").setIcon(languageIcon);
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(10387)) {
                        Timber.w(e, "Failed to set language icon");
                    }
                }
                if (!ListenerUtil.mutListener.listen(10389)) {
                    // Build languages
                    initializeLanguageDialog(screen);
                }
                break;
            case "com.ichi2.anki.prefs.reviewing":
                if (!ListenerUtil.mutListener.listen(10390)) {
                    listener.addPreferencesFromResource(R.xml.preferences_reviewing);
                }
                screen = listener.getPreferenceScreen();
                // Show error toast if the user tries to disable answer button without gestures on
                android.preference.ListPreference fullscreenPreference = (android.preference.ListPreference) screen.findPreference("fullscreenMode");
                if (!ListenerUtil.mutListener.listen(10391)) {
                    fullscreenPreference.setOnPreferenceChangeListener((preference, newValue) -> {
                        SharedPreferences prefs = AnkiDroidApp.getSharedPrefs(Preferences.this);
                        if (prefs.getBoolean("gestures", false) || !"2".equals(newValue)) {
                            return true;
                        } else {
                            Toast.makeText(getApplicationContext(), R.string.full_screen_error_gestures, Toast.LENGTH_LONG).show();
                            return false;
                        }
                    });
                }
                // Custom buttons options
                android.preference.Preference customButtonsPreference = screen.findPreference("custom_buttons_link");
                if (!ListenerUtil.mutListener.listen(10392)) {
                    customButtonsPreference.setOnPreferenceClickListener(preference -> {
                        Intent i = getPreferenceSubscreenIntent(Preferences.this, "com.ichi2.anki.prefs.custom_buttons");
                        startActivity(i);
                        return true;
                    });
                }
                break;
            case "com.ichi2.anki.prefs.appearance":
                if (!ListenerUtil.mutListener.listen(10393)) {
                    listener.addPreferencesFromResource(R.xml.preferences_appearance);
                }
                screen = listener.getPreferenceScreen();
                if (!ListenerUtil.mutListener.listen(10394)) {
                    backgroundImage = (android.preference.CheckBoxPreference) screen.findPreference("deckPickerBackground");
                }
                if (!ListenerUtil.mutListener.listen(10395)) {
                    backgroundImage.setOnPreferenceClickListener(preference -> {
                        if (backgroundImage.isChecked()) {
                            try {
                                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
                                backgroundImage.setChecked(true);
                            } catch (Exception ex) {
                                Timber.e("%s", ex.getLocalizedMessage());
                            }
                        } else {
                            backgroundImage.setChecked(false);
                            String currentAnkiDroidDirectory = CollectionHelper.getCurrentAnkiDroidDirectory(this);
                            File imgFile = new File(currentAnkiDroidDirectory, "DeckPickerBackground.png");
                            if (imgFile.exists()) {
                                if (imgFile.delete()) {
                                    UIUtils.showThemedToast(this, getString(R.string.background_image_removed), false);
                                } else {
                                    UIUtils.showThemedToast(this, getString(R.string.error_deleting_image), false);
                                }
                            } else {
                                UIUtils.showThemedToast(this, getString(R.string.background_image_removed), false);
                            }
                        }
                        return true;
                    });
                }
                if (!ListenerUtil.mutListener.listen(10396)) {
                    initializeCustomFontsDialog(screen);
                }
                break;
            case "com.ichi2.anki.prefs.gestures":
                if (!ListenerUtil.mutListener.listen(10397)) {
                    listener.addPreferencesFromResource(R.xml.preferences_gestures);
                }
                screen = listener.getPreferenceScreen();
                if (!ListenerUtil.mutListener.listen(10398)) {
                    updateGestureCornerTouch(screen);
                }
                break;
            case "com.ichi2.anki.prefs.custom_buttons":
                if (!ListenerUtil.mutListener.listen(10399)) {
                    getSupportActionBar().setTitle(R.string.custom_buttons);
                }
                if (!ListenerUtil.mutListener.listen(10400)) {
                    listener.addPreferencesFromResource(R.xml.preferences_custom_buttons);
                }
                screen = listener.getPreferenceScreen();
                // Reset toolbar button customizations
                android.preference.Preference reset_custom_buttons = screen.findPreference("reset_custom_buttons");
                if (!ListenerUtil.mutListener.listen(10401)) {
                    reset_custom_buttons.setOnPreferenceClickListener(preference -> {
                        SharedPreferences.Editor edit = AnkiDroidApp.getSharedPrefs(getBaseContext()).edit();
                        edit.remove("customButtonUndo");
                        edit.remove("customButtonScheduleCard");
                        edit.remove("customButtonEditCard");
                        edit.remove("customButtonTags");
                        edit.remove("customButtonAddCard");
                        edit.remove("customButtonReplay");
                        edit.remove("customButtonCardInfo");
                        edit.remove("customButtonSelectTts");
                        edit.remove("customButtonDeckOptions");
                        edit.remove("customButtonMarkCard");
                        edit.remove("customButtonToggleMicToolBar");
                        edit.remove("customButtonBury");
                        edit.remove("customButtonSuspend");
                        edit.remove("customButtonFlag");
                        edit.remove("customButtonDelete");
                        edit.remove("customButtonEnableWhiteboard");
                        edit.remove("customButtonSaveWhiteboard");
                        edit.remove("customButtonWhiteboardPenColor");
                        edit.remove("customButtonClearWhiteboard");
                        edit.remove("customButtonShowHideWhiteboard");
                        edit.apply();
                        // TODO: Should reload the preferences screen on completion
                        return true;
                    });
                }
                break;
            case "com.ichi2.anki.prefs.advanced":
                if (!ListenerUtil.mutListener.listen(10402)) {
                    listener.addPreferencesFromResource(R.xml.preferences_advanced);
                }
                screen = listener.getPreferenceScreen();
                // Check that input is valid before committing change in the collection path
                android.preference.EditTextPreference collectionPathPreference = (android.preference.EditTextPreference) screen.findPreference("deckPath");
                if (!ListenerUtil.mutListener.listen(10403)) {
                    collectionPathPreference.setOnPreferenceChangeListener((preference, newValue) -> {
                        final String newPath = (String) newValue;
                        try {
                            CollectionHelper.initializeAnkiDroidDirectory(newPath);
                            return true;
                        } catch (StorageAccessException e) {
                            Timber.e(e, "Could not initialize directory: %s", newPath);
                            Toast.makeText(getApplicationContext(), R.string.dialog_collection_path_not_dir, Toast.LENGTH_LONG).show();
                            return false;
                        }
                    });
                }
                // Custom sync server option
                android.preference.Preference customSyncServerPreference = screen.findPreference("custom_sync_server_link");
                if (!ListenerUtil.mutListener.listen(10404)) {
                    customSyncServerPreference.setOnPreferenceClickListener(preference -> {
                        Intent i = getPreferenceSubscreenIntent(Preferences.this, "com.ichi2.anki.prefs.custom_sync_server");
                        startActivity(i);
                        return true;
                    });
                }
                // Advanced statistics option
                android.preference.Preference advancedStatisticsPreference = screen.findPreference("advanced_statistics_link");
                if (!ListenerUtil.mutListener.listen(10405)) {
                    advancedStatisticsPreference.setOnPreferenceClickListener(preference -> {
                        Intent i = getPreferenceSubscreenIntent(Preferences.this, "com.ichi2.anki.prefs.advanced_statistics");
                        startActivity(i);
                        return true;
                    });
                }
                if (!ListenerUtil.mutListener.listen(10406)) {
                    setupContextMenuPreference(screen, CardBrowserContextMenu.CARD_BROWSER_CONTEXT_MENU_PREF_KEY, R.string.card_browser_context_menu);
                }
                if (!ListenerUtil.mutListener.listen(10407)) {
                    setupContextMenuPreference(screen, AnkiCardContextMenu.ANKI_CARD_CONTEXT_MENU_PREF_KEY, R.string.context_menu_anki_card_label);
                }
                if (!ListenerUtil.mutListener.listen(10415)) {
                    // Make it possible to test crash reporting, but only for DEBUG builds
                    if ((ListenerUtil.mutListener.listen(10408) ? (BuildConfig.DEBUG || !AdaptionUtil.isUserATestClient()) : (BuildConfig.DEBUG && !AdaptionUtil.isUserATestClient()))) {
                        if (!ListenerUtil.mutListener.listen(10409)) {
                            Timber.i("Debug mode, allowing for test crashes");
                        }
                        android.preference.Preference triggerTestCrashPreference = new android.preference.Preference(this);
                        if (!ListenerUtil.mutListener.listen(10410)) {
                            triggerTestCrashPreference.setKey("trigger_crash_preference");
                        }
                        if (!ListenerUtil.mutListener.listen(10411)) {
                            triggerTestCrashPreference.setTitle("Trigger test crash");
                        }
                        if (!ListenerUtil.mutListener.listen(10412)) {
                            triggerTestCrashPreference.setSummary("Touch here for an immediate test crash");
                        }
                        if (!ListenerUtil.mutListener.listen(10413)) {
                            triggerTestCrashPreference.setOnPreferenceClickListener(preference -> {
                                Timber.w("Crash triggered on purpose from advanced preferences in debug mode");
                                throw new RuntimeException("This is a test crash");
                            });
                        }
                        if (!ListenerUtil.mutListener.listen(10414)) {
                            screen.addPreference(triggerTestCrashPreference);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(10422)) {
                    // Make it possible to test analytics, but only for DEBUG builds
                    if (BuildConfig.DEBUG) {
                        if (!ListenerUtil.mutListener.listen(10416)) {
                            Timber.i("Debug mode, allowing for dynamic analytics config");
                        }
                        android.preference.Preference analyticsDebugMode = new android.preference.Preference(this);
                        if (!ListenerUtil.mutListener.listen(10417)) {
                            analyticsDebugMode.setKey("analytics_debug_preference");
                        }
                        if (!ListenerUtil.mutListener.listen(10418)) {
                            analyticsDebugMode.setTitle("Switch Analytics to dev mode");
                        }
                        if (!ListenerUtil.mutListener.listen(10419)) {
                            analyticsDebugMode.setSummary("Touch here to use Analytics dev tag and 100% sample rate");
                        }
                        if (!ListenerUtil.mutListener.listen(10420)) {
                            analyticsDebugMode.setOnPreferenceClickListener(preference -> {
                                if (UsageAnalytics.isEnabled()) {
                                    UIUtils.showThemedToast(this, "Analytics set to dev mode", true);
                                } else {
                                    UIUtils.showThemedToast(this, "Done! Enable Analytics in 'General' settings to use.", true);
                                }
                                UsageAnalytics.setDevMode();
                                return true;
                            });
                        }
                        if (!ListenerUtil.mutListener.listen(10421)) {
                            screen.addPreference(analyticsDebugMode);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(10429)) {
                    if (BuildConfig.DEBUG) {
                        if (!ListenerUtil.mutListener.listen(10423)) {
                            Timber.i("Debug mode, allowing database lock preference");
                        }
                        android.preference.Preference lockDbPreference = new android.preference.Preference(this);
                        if (!ListenerUtil.mutListener.listen(10424)) {
                            lockDbPreference.setKey("debug_lock_database");
                        }
                        if (!ListenerUtil.mutListener.listen(10425)) {
                            lockDbPreference.setTitle("Lock Database");
                        }
                        if (!ListenerUtil.mutListener.listen(10426)) {
                            lockDbPreference.setSummary("Touch here to lock the database (all threads block in-process, exception if using second process)");
                        }
                        if (!ListenerUtil.mutListener.listen(10427)) {
                            lockDbPreference.setOnPreferenceClickListener(preference -> {
                                DatabaseLock.engage(this);
                                return true;
                            });
                        }
                        if (!ListenerUtil.mutListener.listen(10428)) {
                            screen.addPreference(lockDbPreference);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(10435)) {
                    if (BuildConfig.DEBUG) {
                        if (!ListenerUtil.mutListener.listen(10430)) {
                            Timber.i("Debug mode, adding show changelog");
                        }
                        android.preference.Preference changelogPreference = new android.preference.Preference(this);
                        if (!ListenerUtil.mutListener.listen(10431)) {
                            changelogPreference.setTitle("Open Changelog");
                        }
                        Intent infoIntent = new Intent(this, Info.class);
                        if (!ListenerUtil.mutListener.listen(10432)) {
                            infoIntent.putExtra(Info.TYPE_EXTRA, Info.TYPE_NEW_VERSION);
                        }
                        if (!ListenerUtil.mutListener.listen(10433)) {
                            changelogPreference.setIntent(infoIntent);
                        }
                        if (!ListenerUtil.mutListener.listen(10434)) {
                            screen.addPreference(changelogPreference);
                        }
                    }
                }
                // Force full sync option
                ConfirmationPreference fullSyncPreference = (ConfirmationPreference) screen.findPreference("force_full_sync");
                if (!ListenerUtil.mutListener.listen(10436)) {
                    fullSyncPreference.setDialogMessage(R.string.force_full_sync_summary);
                }
                if (!ListenerUtil.mutListener.listen(10437)) {
                    fullSyncPreference.setDialogTitle(R.string.force_full_sync_title);
                }
                if (!ListenerUtil.mutListener.listen(10438)) {
                    fullSyncPreference.setOkHandler(() -> {
                        if (getCol() == null) {
                            Toast.makeText(getApplicationContext(), R.string.directory_inaccessible, Toast.LENGTH_LONG).show();
                            return;
                        }
                        getCol().modSchemaNoCheck();
                        getCol().setMod();
                        Toast.makeText(getApplicationContext(), android.R.string.ok, Toast.LENGTH_SHORT).show();
                    });
                }
                if (!ListenerUtil.mutListener.listen(10439)) {
                    // Workaround preferences
                    removeUnnecessaryAdvancedPrefs(screen);
                }
                if (!ListenerUtil.mutListener.listen(10440)) {
                    addThirdPartyAppsListener(screen);
                }
                break;
            case "com.ichi2.anki.prefs.custom_sync_server":
                if (!ListenerUtil.mutListener.listen(10441)) {
                    getSupportActionBar().setTitle(R.string.custom_sync_server_title);
                }
                if (!ListenerUtil.mutListener.listen(10442)) {
                    listener.addPreferencesFromResource(R.xml.preferences_custom_sync_server);
                }
                screen = listener.getPreferenceScreen();
                android.preference.Preference syncUrlPreference = screen.findPreference("syncBaseUrl");
                android.preference.Preference mSyncUrlPreference = screen.findPreference("syncMediaUrl");
                if (!ListenerUtil.mutListener.listen(10443)) {
                    syncUrlPreference.setOnPreferenceChangeListener((preference, newValue) -> {
                        String newUrl = newValue.toString();
                        if (!URLUtil.isValidUrl(newUrl)) {
                            new AlertDialog.Builder(this).setTitle(R.string.custom_sync_server_base_url_invalid).setPositiveButton(R.string.dialog_ok, null).show();
                            return false;
                        }
                        return true;
                    });
                }
                if (!ListenerUtil.mutListener.listen(10444)) {
                    mSyncUrlPreference.setOnPreferenceChangeListener((preference, newValue) -> {
                        String newUrl = newValue.toString();
                        if (!URLUtil.isValidUrl(newUrl)) {
                            new AlertDialog.Builder(this).setTitle(R.string.custom_sync_server_media_url_invalid).setPositiveButton(R.string.dialog_ok, null).show();
                            return false;
                        }
                        return true;
                    });
                }
                break;
            case "com.ichi2.anki.prefs.advanced_statistics":
                if (!ListenerUtil.mutListener.listen(10445)) {
                    getSupportActionBar().setTitle(R.string.advanced_statistics_title);
                }
                if (!ListenerUtil.mutListener.listen(10446)) {
                    listener.addPreferencesFromResource(R.xml.preferences_advanced_statistics);
                }
                break;
        }
    }

    private void setupContextMenuPreference(android.preference.PreferenceScreen screen, String key, @StringRes int contextMenuName) {
        // different than the app language
        android.preference.CheckBoxPreference cardBrowserContextMenuPreference = (android.preference.CheckBoxPreference) screen.findPreference(key);
        String menuName = getString(contextMenuName);
        if (!ListenerUtil.mutListener.listen(10447)) {
            // Note: The below format strings are generic, not card browser specific despite the name
            cardBrowserContextMenuPreference.setTitle(getString(R.string.card_browser_enable_external_context_menu, menuName));
        }
        if (!ListenerUtil.mutListener.listen(10448)) {
            cardBrowserContextMenuPreference.setSummary(getString(R.string.card_browser_enable_external_context_menu_summary, menuName));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(10449)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        // DEFECT #5973: Does not handle Google Drive downloads
        try {
            if (!ListenerUtil.mutListener.listen(10479)) {
                if ((ListenerUtil.mutListener.listen(10457) ? ((ListenerUtil.mutListener.listen(10456) ? ((ListenerUtil.mutListener.listen(10455) ? (requestCode >= RESULT_LOAD_IMG) : (ListenerUtil.mutListener.listen(10454) ? (requestCode <= RESULT_LOAD_IMG) : (ListenerUtil.mutListener.listen(10453) ? (requestCode > RESULT_LOAD_IMG) : (ListenerUtil.mutListener.listen(10452) ? (requestCode < RESULT_LOAD_IMG) : (ListenerUtil.mutListener.listen(10451) ? (requestCode != RESULT_LOAD_IMG) : (requestCode == RESULT_LOAD_IMG)))))) || resultCode == RESULT_OK) : ((ListenerUtil.mutListener.listen(10455) ? (requestCode >= RESULT_LOAD_IMG) : (ListenerUtil.mutListener.listen(10454) ? (requestCode <= RESULT_LOAD_IMG) : (ListenerUtil.mutListener.listen(10453) ? (requestCode > RESULT_LOAD_IMG) : (ListenerUtil.mutListener.listen(10452) ? (requestCode < RESULT_LOAD_IMG) : (ListenerUtil.mutListener.listen(10451) ? (requestCode != RESULT_LOAD_IMG) : (requestCode == RESULT_LOAD_IMG)))))) && resultCode == RESULT_OK)) || null != data) : ((ListenerUtil.mutListener.listen(10456) ? ((ListenerUtil.mutListener.listen(10455) ? (requestCode >= RESULT_LOAD_IMG) : (ListenerUtil.mutListener.listen(10454) ? (requestCode <= RESULT_LOAD_IMG) : (ListenerUtil.mutListener.listen(10453) ? (requestCode > RESULT_LOAD_IMG) : (ListenerUtil.mutListener.listen(10452) ? (requestCode < RESULT_LOAD_IMG) : (ListenerUtil.mutListener.listen(10451) ? (requestCode != RESULT_LOAD_IMG) : (requestCode == RESULT_LOAD_IMG)))))) || resultCode == RESULT_OK) : ((ListenerUtil.mutListener.listen(10455) ? (requestCode >= RESULT_LOAD_IMG) : (ListenerUtil.mutListener.listen(10454) ? (requestCode <= RESULT_LOAD_IMG) : (ListenerUtil.mutListener.listen(10453) ? (requestCode > RESULT_LOAD_IMG) : (ListenerUtil.mutListener.listen(10452) ? (requestCode < RESULT_LOAD_IMG) : (ListenerUtil.mutListener.listen(10451) ? (requestCode != RESULT_LOAD_IMG) : (requestCode == RESULT_LOAD_IMG)))))) && resultCode == RESULT_OK)) && null != data))) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = { MediaStore.Images.Media.DATA };
                    try (Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null)) {
                        if (!ListenerUtil.mutListener.listen(10460)) {
                            cursor.moveToFirst();
                        }
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String imgPathString = cursor.getString(columnIndex);
                        File sourceFile = new File(imgPathString);
                        // file size in MB
                        long fileLength = (ListenerUtil.mutListener.listen(10468) ? (sourceFile.length() % ((ListenerUtil.mutListener.listen(10464) ? (1024 % 1024) : (ListenerUtil.mutListener.listen(10463) ? (1024 / 1024) : (ListenerUtil.mutListener.listen(10462) ? (1024 - 1024) : (ListenerUtil.mutListener.listen(10461) ? (1024 + 1024) : (1024 * 1024))))))) : (ListenerUtil.mutListener.listen(10467) ? (sourceFile.length() * ((ListenerUtil.mutListener.listen(10464) ? (1024 % 1024) : (ListenerUtil.mutListener.listen(10463) ? (1024 / 1024) : (ListenerUtil.mutListener.listen(10462) ? (1024 - 1024) : (ListenerUtil.mutListener.listen(10461) ? (1024 + 1024) : (1024 * 1024))))))) : (ListenerUtil.mutListener.listen(10466) ? (sourceFile.length() - ((ListenerUtil.mutListener.listen(10464) ? (1024 % 1024) : (ListenerUtil.mutListener.listen(10463) ? (1024 / 1024) : (ListenerUtil.mutListener.listen(10462) ? (1024 - 1024) : (ListenerUtil.mutListener.listen(10461) ? (1024 + 1024) : (1024 * 1024))))))) : (ListenerUtil.mutListener.listen(10465) ? (sourceFile.length() + ((ListenerUtil.mutListener.listen(10464) ? (1024 % 1024) : (ListenerUtil.mutListener.listen(10463) ? (1024 / 1024) : (ListenerUtil.mutListener.listen(10462) ? (1024 - 1024) : (ListenerUtil.mutListener.listen(10461) ? (1024 + 1024) : (1024 * 1024))))))) : (sourceFile.length() / ((ListenerUtil.mutListener.listen(10464) ? (1024 % 1024) : (ListenerUtil.mutListener.listen(10463) ? (1024 / 1024) : (ListenerUtil.mutListener.listen(10462) ? (1024 - 1024) : (ListenerUtil.mutListener.listen(10461) ? (1024 + 1024) : (1024 * 1024)))))))))));
                        String currentAnkiDroidDirectory = CollectionHelper.getCurrentAnkiDroidDirectory(this);
                        String imageName = "DeckPickerBackground.png";
                        File destFile = new File(currentAnkiDroidDirectory, imageName);
                        if (!ListenerUtil.mutListener.listen(10478)) {
                            // Image size less than 10 MB copied to AnkiDroid folder
                            if ((ListenerUtil.mutListener.listen(10473) ? (fileLength >= 10) : (ListenerUtil.mutListener.listen(10472) ? (fileLength <= 10) : (ListenerUtil.mutListener.listen(10471) ? (fileLength > 10) : (ListenerUtil.mutListener.listen(10470) ? (fileLength != 10) : (ListenerUtil.mutListener.listen(10469) ? (fileLength == 10) : (fileLength < 10))))))) {
                                try (FileChannel sourceChannel = new FileInputStream(sourceFile).getChannel();
                                    FileChannel destChannel = new FileOutputStream(destFile).getChannel()) {
                                    if (!ListenerUtil.mutListener.listen(10476)) {
                                        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
                                    }
                                    if (!ListenerUtil.mutListener.listen(10477)) {
                                        UIUtils.showThemedToast(this, getString(R.string.background_image_applied), false);
                                    }
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(10474)) {
                                    backgroundImage.setChecked(false);
                                }
                                if (!ListenerUtil.mutListener.listen(10475)) {
                                    UIUtils.showThemedToast(this, getString(R.string.image_max_size_allowed, 10), false);
                                }
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(10458)) {
                        backgroundImage.setChecked(false);
                    }
                    if (!ListenerUtil.mutListener.listen(10459)) {
                        UIUtils.showThemedToast(this, getString(R.string.no_image_selected), false);
                    }
                }
            }
        } catch (OutOfMemoryError | Exception e) {
            if (!ListenerUtil.mutListener.listen(10450)) {
                UIUtils.showThemedToast(this, getString(R.string.error_selecting_image, e.getLocalizedMessage()), false);
            }
        }
    }

    private void addThirdPartyAppsListener(android.preference.PreferenceScreen screen) {
        // and need to handle the keypress ourself.
        android.preference.Preference showThirdParty = screen.findPreference("thirdpartyapps_link");
        final String githubThirdPartyAppsUrl = "https://github.com/ankidroid/Anki-Android/wiki/Third-Party-Apps";
        if (!ListenerUtil.mutListener.listen(10480)) {
            showThirdParty.setOnPreferenceClickListener((preference) -> {
                try {
                    Intent openThirdPartyAppsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(githubThirdPartyAppsUrl));
                    super.startActivity(openThirdPartyAppsIntent);
                } catch (ActivityNotFoundException e) {
                    // We use a different message here. We have limited space in the snackbar
                    String error = getString(R.string.activity_start_failed_load_url, githubThirdPartyAppsUrl);
                    UIUtils.showSimpleSnackbar(this, error, false);
                }
                return true;
            });
        }
    }

    /**
     * Loop over every preference in the list and set the summary text
     */
    private void initAllPreferences(android.preference.PreferenceScreen screen) {
        if (!ListenerUtil.mutListener.listen(10503)) {
            {
                long _loopCounter174 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(10502) ? (i >= screen.getPreferenceCount()) : (ListenerUtil.mutListener.listen(10501) ? (i <= screen.getPreferenceCount()) : (ListenerUtil.mutListener.listen(10500) ? (i > screen.getPreferenceCount()) : (ListenerUtil.mutListener.listen(10499) ? (i != screen.getPreferenceCount()) : (ListenerUtil.mutListener.listen(10498) ? (i == screen.getPreferenceCount()) : (i < screen.getPreferenceCount())))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter174", ++_loopCounter174);
                    android.preference.Preference preference = screen.getPreference(i);
                    if (!ListenerUtil.mutListener.listen(10497)) {
                        if (preference instanceof android.preference.PreferenceGroup) {
                            android.preference.PreferenceGroup preferenceGroup = (android.preference.PreferenceGroup) preference;
                            if (!ListenerUtil.mutListener.listen(10496)) {
                                {
                                    long _loopCounter173 = 0;
                                    for (int j = 0; (ListenerUtil.mutListener.listen(10495) ? (j >= preferenceGroup.getPreferenceCount()) : (ListenerUtil.mutListener.listen(10494) ? (j <= preferenceGroup.getPreferenceCount()) : (ListenerUtil.mutListener.listen(10493) ? (j > preferenceGroup.getPreferenceCount()) : (ListenerUtil.mutListener.listen(10492) ? (j != preferenceGroup.getPreferenceCount()) : (ListenerUtil.mutListener.listen(10491) ? (j == preferenceGroup.getPreferenceCount()) : (j < preferenceGroup.getPreferenceCount())))))); ++j) {
                                        ListenerUtil.loopListener.listen("_loopCounter173", ++_loopCounter173);
                                        android.preference.Preference nestedPreference = preferenceGroup.getPreference(j);
                                        if (!ListenerUtil.mutListener.listen(10490)) {
                                            if (nestedPreference instanceof android.preference.PreferenceGroup) {
                                                android.preference.PreferenceGroup nestedPreferenceGroup = (android.preference.PreferenceGroup) nestedPreference;
                                                if (!ListenerUtil.mutListener.listen(10489)) {
                                                    {
                                                        long _loopCounter172 = 0;
                                                        for (int k = 0; (ListenerUtil.mutListener.listen(10488) ? (k >= nestedPreferenceGroup.getPreferenceCount()) : (ListenerUtil.mutListener.listen(10487) ? (k <= nestedPreferenceGroup.getPreferenceCount()) : (ListenerUtil.mutListener.listen(10486) ? (k > nestedPreferenceGroup.getPreferenceCount()) : (ListenerUtil.mutListener.listen(10485) ? (k != nestedPreferenceGroup.getPreferenceCount()) : (ListenerUtil.mutListener.listen(10484) ? (k == nestedPreferenceGroup.getPreferenceCount()) : (k < nestedPreferenceGroup.getPreferenceCount())))))); ++k) {
                                                            ListenerUtil.loopListener.listen("_loopCounter172", ++_loopCounter172);
                                                            if (!ListenerUtil.mutListener.listen(10483)) {
                                                                initPreference(nestedPreferenceGroup.getPreference(k));
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(10482)) {
                                                    initPreference(preferenceGroup.getPreference(j));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(10481)) {
                                initPreference(preference);
                            }
                        }
                    }
                }
            }
        }
    }

    private void initPreference(android.preference.Preference pref) {
        if (!ListenerUtil.mutListener.listen(10524)) {
            // Load stored values from Preferences which are stored in the Collection
            if (Arrays.asList(sCollectionPreferences).contains(pref.getKey())) {
                Collection col = getCol();
                if (!ListenerUtil.mutListener.listen(10523)) {
                    if (col != null) {
                        try {
                            JSONObject conf = col.getConf();
                            if (!ListenerUtil.mutListener.listen(10522)) {
                                switch(pref.getKey()) {
                                    case "showEstimates":
                                        if (!ListenerUtil.mutListener.listen(10506)) {
                                            ((android.preference.CheckBoxPreference) pref).setChecked(conf.getBoolean("estTimes"));
                                        }
                                        break;
                                    case "showProgress":
                                        if (!ListenerUtil.mutListener.listen(10507)) {
                                            ((android.preference.CheckBoxPreference) pref).setChecked(conf.getBoolean("dueCounts"));
                                        }
                                        break;
                                    case "learnCutoff":
                                        if (!ListenerUtil.mutListener.listen(10512)) {
                                            ((NumberRangePreference) pref).setValue((ListenerUtil.mutListener.listen(10511) ? (conf.getInt("collapseTime") % 60) : (ListenerUtil.mutListener.listen(10510) ? (conf.getInt("collapseTime") * 60) : (ListenerUtil.mutListener.listen(10509) ? (conf.getInt("collapseTime") - 60) : (ListenerUtil.mutListener.listen(10508) ? (conf.getInt("collapseTime") + 60) : (conf.getInt("collapseTime") / 60))))));
                                        }
                                        break;
                                    case "timeLimit":
                                        if (!ListenerUtil.mutListener.listen(10517)) {
                                            ((NumberRangePreference) pref).setValue((ListenerUtil.mutListener.listen(10516) ? (conf.getInt("timeLim") % 60) : (ListenerUtil.mutListener.listen(10515) ? (conf.getInt("timeLim") * 60) : (ListenerUtil.mutListener.listen(10514) ? (conf.getInt("timeLim") - 60) : (ListenerUtil.mutListener.listen(10513) ? (conf.getInt("timeLim") + 60) : (conf.getInt("timeLim") / 60))))));
                                        }
                                        break;
                                    case "useCurrent":
                                        if (!ListenerUtil.mutListener.listen(10518)) {
                                            ((android.preference.ListPreference) pref).setValueIndex(conf.optBoolean("addToCur", true) ? 0 : 1);
                                        }
                                        break;
                                    case "newSpread":
                                        if (!ListenerUtil.mutListener.listen(10519)) {
                                            ((android.preference.ListPreference) pref).setValueIndex(conf.getInt("newSpread"));
                                        }
                                        break;
                                    case "dayOffset":
                                        Calendar calendar = col.crtGregorianCalendar();
                                        if (!ListenerUtil.mutListener.listen(10520)) {
                                            ((SeekBarPreference) pref).setValue(calendar.get(Calendar.HOUR_OF_DAY));
                                        }
                                        break;
                                    case "schedVer":
                                        if (!ListenerUtil.mutListener.listen(10521)) {
                                            ((android.preference.CheckBoxPreference) pref).setChecked(conf.optInt("schedVer", 1) == 2);
                                        }
                                }
                            }
                        } catch (NumberFormatException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(10505)) {
                            // Disable Col preferences if Collection closed
                            pref.setEnabled(false);
                        }
                    }
                }
            } else if ("minimumCardsDueForNotification".equals(pref.getKey())) {
                if (!ListenerUtil.mutListener.listen(10504)) {
                    updateNotificationPreference((android.preference.ListPreference) pref);
                }
            }
        }
        // Set the value from the summary cache
        CharSequence s = pref.getSummary();
        if (!ListenerUtil.mutListener.listen(10525)) {
            mOriginalSumarries.put(pref.getKey(), (s != null) ? s.toString() : "");
        }
        if (!ListenerUtil.mutListener.listen(10526)) {
            // Update summary
            updateSummary(pref);
        }
    }

    /**
     * Code which is run when a SharedPreference change has been detected
     * @param prefs instance of SharedPreferences
     * @param key key in prefs which is being updated
     * @param listener PreferenceActivity of PreferenceFragment which is hosting the preference
     */
    // Tracked as #5019 on github - convert to fragments
    @SuppressWarnings("deprecation")
    private void updatePreference(SharedPreferences prefs, String key, PreferenceContext listener) {
        try {
            android.preference.PreferenceScreen screen = listener.getPreferenceScreen();
            android.preference.Preference pref = screen.findPreference(key);
            if (!ListenerUtil.mutListener.listen(10529)) {
                if (pref == null) {
                    if (!ListenerUtil.mutListener.listen(10528)) {
                        Timber.e("Preferences: no preference found for the key: %s", key);
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(10601)) {
                // Handle special cases
                switch(key) {
                    case CustomSyncServer.PREFERENCE_CUSTOM_MEDIA_SYNC_URL:
                    case CustomSyncServer.PREFERENCE_CUSTOM_SYNC_BASE:
                    case CustomSyncServer.PREFERENCE_ENABLE_CUSTOM_SYNC_SERVER:
                        if (!ListenerUtil.mutListener.listen(10530)) {
                            // This may be a tad hasty - performed before "back" is pressed.
                            CustomSyncServer.handleSyncServerPreferenceChange(getBaseContext());
                        }
                        break;
                    case "timeoutAnswer":
                        {
                            android.preference.CheckBoxPreference keepScreenOn = (android.preference.CheckBoxPreference) screen.findPreference("keepScreenOn");
                            if (!ListenerUtil.mutListener.listen(10531)) {
                                keepScreenOn.setChecked(((android.preference.CheckBoxPreference) pref).isChecked());
                            }
                            break;
                        }
                    case LANGUAGE:
                        if (!ListenerUtil.mutListener.listen(10532)) {
                            closePreferences();
                        }
                        break;
                    case "showProgress":
                        if (!ListenerUtil.mutListener.listen(10533)) {
                            getCol().getConf().put("dueCounts", ((android.preference.CheckBoxPreference) pref).isChecked());
                        }
                        if (!ListenerUtil.mutListener.listen(10534)) {
                            getCol().setMod();
                        }
                        break;
                    case "showEstimates":
                        if (!ListenerUtil.mutListener.listen(10535)) {
                            getCol().getConf().put("estTimes", ((android.preference.CheckBoxPreference) pref).isChecked());
                        }
                        if (!ListenerUtil.mutListener.listen(10536)) {
                            getCol().setMod();
                        }
                        break;
                    case "newSpread":
                        if (!ListenerUtil.mutListener.listen(10537)) {
                            getCol().getConf().put("newSpread", Integer.parseInt(((android.preference.ListPreference) pref).getValue()));
                        }
                        if (!ListenerUtil.mutListener.listen(10538)) {
                            getCol().setMod();
                        }
                        break;
                    case "timeLimit":
                        if (!ListenerUtil.mutListener.listen(10543)) {
                            getCol().getConf().put("timeLim", (ListenerUtil.mutListener.listen(10542) ? (((NumberRangePreference) pref).getValue() % 60) : (ListenerUtil.mutListener.listen(10541) ? (((NumberRangePreference) pref).getValue() / 60) : (ListenerUtil.mutListener.listen(10540) ? (((NumberRangePreference) pref).getValue() - 60) : (ListenerUtil.mutListener.listen(10539) ? (((NumberRangePreference) pref).getValue() + 60) : (((NumberRangePreference) pref).getValue() * 60))))));
                        }
                        if (!ListenerUtil.mutListener.listen(10544)) {
                            getCol().setMod();
                        }
                        break;
                    case "learnCutoff":
                        if (!ListenerUtil.mutListener.listen(10549)) {
                            getCol().getConf().put("collapseTime", (ListenerUtil.mutListener.listen(10548) ? (((NumberRangePreference) pref).getValue() % 60) : (ListenerUtil.mutListener.listen(10547) ? (((NumberRangePreference) pref).getValue() / 60) : (ListenerUtil.mutListener.listen(10546) ? (((NumberRangePreference) pref).getValue() - 60) : (ListenerUtil.mutListener.listen(10545) ? (((NumberRangePreference) pref).getValue() + 60) : (((NumberRangePreference) pref).getValue() * 60))))));
                        }
                        if (!ListenerUtil.mutListener.listen(10550)) {
                            getCol().setMod();
                        }
                        break;
                    case "useCurrent":
                        if (!ListenerUtil.mutListener.listen(10551)) {
                            getCol().getConf().put("addToCur", "0".equals(((android.preference.ListPreference) pref).getValue()));
                        }
                        if (!ListenerUtil.mutListener.listen(10552)) {
                            getCol().setMod();
                        }
                        break;
                    case "dayOffset":
                        {
                            int hours = ((SeekBarPreference) pref).getValue();
                            Calendar date = getCol().crtGregorianCalendar();
                            if (!ListenerUtil.mutListener.listen(10553)) {
                                date.set(Calendar.HOUR_OF_DAY, hours);
                            }
                            if (!ListenerUtil.mutListener.listen(10558)) {
                                getCol().setCrt((ListenerUtil.mutListener.listen(10557) ? (date.getTimeInMillis() % 1000) : (ListenerUtil.mutListener.listen(10556) ? (date.getTimeInMillis() * 1000) : (ListenerUtil.mutListener.listen(10555) ? (date.getTimeInMillis() - 1000) : (ListenerUtil.mutListener.listen(10554) ? (date.getTimeInMillis() + 1000) : (date.getTimeInMillis() / 1000))))));
                            }
                            if (!ListenerUtil.mutListener.listen(10559)) {
                                getCol().setMod();
                            }
                            if (!ListenerUtil.mutListener.listen(10560)) {
                                BootService.scheduleNotification(getCol().getTime(), this);
                            }
                            break;
                        }
                    case "minimumCardsDueForNotification":
                        {
                            android.preference.ListPreference listpref = (android.preference.ListPreference) screen.findPreference("minimumCardsDueForNotification");
                            if (!ListenerUtil.mutListener.listen(10570)) {
                                if (listpref != null) {
                                    if (!ListenerUtil.mutListener.listen(10561)) {
                                        updateNotificationPreference(listpref);
                                    }
                                    if (!ListenerUtil.mutListener.listen(10569)) {
                                        if ((ListenerUtil.mutListener.listen(10566) ? (Integer.parseInt(listpref.getValue()) >= PENDING_NOTIFICATIONS_ONLY) : (ListenerUtil.mutListener.listen(10565) ? (Integer.parseInt(listpref.getValue()) <= PENDING_NOTIFICATIONS_ONLY) : (ListenerUtil.mutListener.listen(10564) ? (Integer.parseInt(listpref.getValue()) > PENDING_NOTIFICATIONS_ONLY) : (ListenerUtil.mutListener.listen(10563) ? (Integer.parseInt(listpref.getValue()) != PENDING_NOTIFICATIONS_ONLY) : (ListenerUtil.mutListener.listen(10562) ? (Integer.parseInt(listpref.getValue()) == PENDING_NOTIFICATIONS_ONLY) : (Integer.parseInt(listpref.getValue()) < PENDING_NOTIFICATIONS_ONLY))))))) {
                                            if (!ListenerUtil.mutListener.listen(10568)) {
                                                BootService.scheduleNotification(getCol().getTime(), this);
                                            }
                                        } else {
                                            PendingIntent intent = PendingIntent.getBroadcast(this, 0, new Intent(this, NotificationService.class), 0);
                                            final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                            if (!ListenerUtil.mutListener.listen(10567)) {
                                                alarmManager.cancel(intent);
                                            }
                                        }
                                    }
                                }
                            }
                            break;
                        }
                    case AnkiDroidApp.FEEDBACK_REPORT_KEY:
                        {
                            String value = prefs.getString(AnkiDroidApp.FEEDBACK_REPORT_KEY, "");
                            if (!ListenerUtil.mutListener.listen(10571)) {
                                AnkiDroidApp.getInstance().setAcraReportingMode(value);
                            }
                            if (!ListenerUtil.mutListener.listen(10572)) {
                                // If the user changed error reporting, make sure future reports have a chance to post
                                AnkiDroidApp.deleteACRALimiterData(this);
                            }
                            if (!ListenerUtil.mutListener.listen(10573)) {
                                // We also need to re-chain our UncaughtExceptionHandlers
                                UsageAnalytics.reInitialize();
                            }
                            break;
                        }
                    case "syncAccount":
                        {
                            SharedPreferences preferences = AnkiDroidApp.getSharedPrefs(getBaseContext());
                            String username = preferences.getString("username", "");
                            android.preference.Preference syncAccount = screen.findPreference("syncAccount");
                            if (!ListenerUtil.mutListener.listen(10577)) {
                                if (syncAccount != null) {
                                    if (!ListenerUtil.mutListener.listen(10576)) {
                                        if (TextUtils.isEmpty(username)) {
                                            if (!ListenerUtil.mutListener.listen(10575)) {
                                                syncAccount.setSummary(R.string.sync_account_summ_logged_out);
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(10574)) {
                                                syncAccount.setSummary(getString(R.string.sync_account_summ_logged_in, username));
                                            }
                                        }
                                    }
                                }
                            }
                            break;
                        }
                    case "providerEnabled":
                        {
                            ComponentName providerName = new ComponentName(this, "com.ichi2.anki.provider.CardContentProvider");
                            PackageManager pm = getPackageManager();
                            int state;
                            if (((android.preference.CheckBoxPreference) pref).isChecked()) {
                                state = PackageManager.COMPONENT_ENABLED_STATE_ENABLED;
                                if (!ListenerUtil.mutListener.listen(10579)) {
                                    Timber.i("AnkiDroid ContentProvider enabled by user");
                                }
                            } else {
                                state = PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
                                if (!ListenerUtil.mutListener.listen(10578)) {
                                    Timber.i("AnkiDroid ContentProvider disabled by user");
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(10580)) {
                                pm.setComponentEnabledSetting(providerName, state, PackageManager.DONT_KILL_APP);
                            }
                            break;
                        }
                    case "schedVer":
                        {
                            boolean wantNew = ((android.preference.CheckBoxPreference) pref).isChecked();
                            boolean haveNew = getCol().schedVer() == 2;
                            if (!ListenerUtil.mutListener.listen(10581)) {
                                // northing to do?
                                if (haveNew == wantNew) {
                                    break;
                                }
                            }
                            MaterialDialog.Builder builder = new MaterialDialog.Builder(this);
                            if (!ListenerUtil.mutListener.listen(10590)) {
                                if ((ListenerUtil.mutListener.listen(10582) ? (haveNew || !wantNew) : (haveNew && !wantNew))) {
                                    if (!ListenerUtil.mutListener.listen(10583)) {
                                        // Going back to V1
                                        builder.title(R.string.sched_ver_toggle_title);
                                    }
                                    if (!ListenerUtil.mutListener.listen(10584)) {
                                        builder.content(R.string.sched_ver_2to1);
                                    }
                                    if (!ListenerUtil.mutListener.listen(10585)) {
                                        builder.onPositive((dialog, which) -> {
                                            getCol().modSchemaNoCheck();
                                            try {
                                                getCol().changeSchedulerVer(1);
                                                ((android.preference.CheckBoxPreference) pref).setChecked(false);
                                            } catch (ConfirmModSchemaException e2) {
                                                // This should never be reached as we explicitly called modSchemaNoCheck()
                                                throw new RuntimeException(e2);
                                            }
                                        });
                                    }
                                    if (!ListenerUtil.mutListener.listen(10586)) {
                                        builder.onNegative((dialog, which) -> ((android.preference.CheckBoxPreference) pref).setChecked(true));
                                    }
                                    if (!ListenerUtil.mutListener.listen(10587)) {
                                        builder.positiveText(R.string.dialog_ok);
                                    }
                                    if (!ListenerUtil.mutListener.listen(10588)) {
                                        builder.negativeText(R.string.dialog_cancel);
                                    }
                                    if (!ListenerUtil.mutListener.listen(10589)) {
                                        builder.show();
                                    }
                                    break;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(10591)) {
                                // Going to V2
                                builder.title(R.string.sched_ver_toggle_title);
                            }
                            if (!ListenerUtil.mutListener.listen(10592)) {
                                builder.content(R.string.sched_ver_1to2);
                            }
                            if (!ListenerUtil.mutListener.listen(10593)) {
                                builder.onPositive((dialog, which) -> {
                                    getCol().modSchemaNoCheck();
                                    try {
                                        getCol().changeSchedulerVer(2);
                                        ((android.preference.CheckBoxPreference) pref).setChecked(true);
                                    } catch (ConfirmModSchemaException e2) {
                                        // This should never be reached as we explicitly called modSchemaNoCheck()
                                        throw new RuntimeException(e2);
                                    }
                                });
                            }
                            if (!ListenerUtil.mutListener.listen(10594)) {
                                builder.onNegative((dialog, which) -> ((android.preference.CheckBoxPreference) pref).setChecked(false));
                            }
                            if (!ListenerUtil.mutListener.listen(10595)) {
                                builder.positiveText(R.string.dialog_ok);
                            }
                            if (!ListenerUtil.mutListener.listen(10596)) {
                                builder.negativeText(R.string.dialog_cancel);
                            }
                            if (!ListenerUtil.mutListener.listen(10597)) {
                                builder.show();
                            }
                            break;
                        }
                    case CardBrowserContextMenu.CARD_BROWSER_CONTEXT_MENU_PREF_KEY:
                        if (!ListenerUtil.mutListener.listen(10598)) {
                            CardBrowserContextMenu.ensureConsistentStateWithSharedPreferences(this);
                        }
                        break;
                    case AnkiCardContextMenu.ANKI_CARD_CONTEXT_MENU_PREF_KEY:
                        if (!ListenerUtil.mutListener.listen(10599)) {
                            AnkiCardContextMenu.ensureConsistentStateWithSharedPreferences(this);
                        }
                        break;
                    case "gestureCornerTouch":
                        {
                            if (!ListenerUtil.mutListener.listen(10600)) {
                                updateGestureCornerTouch(screen);
                            }
                        }
                }
            }
            if (!ListenerUtil.mutListener.listen(10602)) {
                // Update the summary text to reflect new value
                updateSummary(pref);
            }
        } catch (BadTokenException e) {
            if (!ListenerUtil.mutListener.listen(10527)) {
                Timber.e(e, "Preferences: BadTokenException on showDialog");
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateGestureCornerTouch(android.preference.PreferenceScreen screen) {
        boolean gestureCornerTouch = AnkiDroidApp.getSharedPrefs(this).getBoolean("gestureCornerTouch", false);
        if (!ListenerUtil.mutListener.listen(10611)) {
            if (gestureCornerTouch) {
                if (!ListenerUtil.mutListener.listen(10607)) {
                    screen.findPreference("gestureTapTop").setTitle(R.string.gestures_corner_tap_top_center);
                }
                if (!ListenerUtil.mutListener.listen(10608)) {
                    screen.findPreference("gestureTapLeft").setTitle(R.string.gestures_corner_tap_middle_left);
                }
                if (!ListenerUtil.mutListener.listen(10609)) {
                    screen.findPreference("gestureTapRight").setTitle(R.string.gestures_corner_tap_middle_right);
                }
                if (!ListenerUtil.mutListener.listen(10610)) {
                    screen.findPreference("gestureTapBottom").setTitle(R.string.gestures_corner_tap_bottom_center);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10603)) {
                    screen.findPreference("gestureTapTop").setTitle(R.string.gestures_tap_top);
                }
                if (!ListenerUtil.mutListener.listen(10604)) {
                    screen.findPreference("gestureTapLeft").setTitle(R.string.gestures_tap_left);
                }
                if (!ListenerUtil.mutListener.listen(10605)) {
                    screen.findPreference("gestureTapRight").setTitle(R.string.gestures_tap_right);
                }
                if (!ListenerUtil.mutListener.listen(10606)) {
                    screen.findPreference("gestureTapBottom").setTitle(R.string.gestures_tap_bottom);
                }
            }
        }
    }

    public void updateNotificationPreference(android.preference.ListPreference listpref) {
        CharSequence[] entries = listpref.getEntries();
        CharSequence[] values = listpref.getEntryValues();
        if (!ListenerUtil.mutListener.listen(10619)) {
            {
                long _loopCounter175 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(10618) ? (i >= entries.length) : (ListenerUtil.mutListener.listen(10617) ? (i <= entries.length) : (ListenerUtil.mutListener.listen(10616) ? (i > entries.length) : (ListenerUtil.mutListener.listen(10615) ? (i != entries.length) : (ListenerUtil.mutListener.listen(10614) ? (i == entries.length) : (i < entries.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter175", ++_loopCounter175);
                    int value = Integer.parseInt(values[i].toString());
                    if (!ListenerUtil.mutListener.listen(10613)) {
                        if (entries[i].toString().contains("%d")) {
                            if (!ListenerUtil.mutListener.listen(10612)) {
                                entries[i] = String.format(entries[i].toString(), value);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10620)) {
            listpref.setEntries(entries);
        }
        if (!ListenerUtil.mutListener.listen(10621)) {
            listpref.setSummary(listpref.getEntry().toString());
        }
    }

    private void updateSummary(android.preference.Preference pref) {
        if (!ListenerUtil.mutListener.listen(10623)) {
            if ((ListenerUtil.mutListener.listen(10622) ? (pref == null && pref.getKey() == null) : (pref == null || pref.getKey() == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10631)) {
            // Handle special cases
            switch(pref.getKey()) {
                case "about_dialog_preference":
                    if (!ListenerUtil.mutListener.listen(10624)) {
                        pref.setSummary(getResources().getString(R.string.about_version) + " " + VersionUtils.getPkgVersionName());
                    }
                    break;
                case "custom_sync_server_link":
                    SharedPreferences preferences = AnkiDroidApp.getSharedPrefs(this);
                    if (!ListenerUtil.mutListener.listen(10627)) {
                        if (!CustomSyncServer.isEnabled(preferences)) {
                            if (!ListenerUtil.mutListener.listen(10626)) {
                                pref.setSummary(R.string.disabled);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(10625)) {
                                pref.setSummary(CustomSyncServer.getSyncBaseUrlOrDefault(preferences, ""));
                            }
                        }
                    }
                    break;
                case "advanced_statistics_link":
                    if (!ListenerUtil.mutListener.listen(10630)) {
                        if (!AnkiDroidApp.getSharedPrefs(this).getBoolean("advanced_statistics_enabled", false)) {
                            if (!ListenerUtil.mutListener.listen(10629)) {
                                pref.setSummary(R.string.disabled);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(10628)) {
                                pref.setSummary(R.string.enabled);
                            }
                        }
                    }
                    break;
            }
        }
        // Get value text
        String value;
        try {
            if (pref instanceof NumberRangePreference) {
                value = Integer.toString(((NumberRangePreference) pref).getValue());
            } else if (pref instanceof SeekBarPreference) {
                value = Integer.toString(((SeekBarPreference) pref).getValue());
            } else if (pref instanceof android.preference.ListPreference) {
                value = ((android.preference.ListPreference) pref).getEntry().toString();
            } else if (pref instanceof android.preference.EditTextPreference) {
                value = ((android.preference.EditTextPreference) pref).getText();
            } else {
                return;
            }
        } catch (NullPointerException e) {
            value = "";
        }
        // Get summary text
        String oldSummary = mOriginalSumarries.get(pref.getKey());
        if (!ListenerUtil.mutListener.listen(10636)) {
            // Replace summary text with value according to some rules
            if ("".equals(oldSummary)) {
                if (!ListenerUtil.mutListener.listen(10635)) {
                    pref.setSummary(value);
                }
            } else if ("".equals(value)) {
                if (!ListenerUtil.mutListener.listen(10634)) {
                    pref.setSummary(oldSummary);
                }
            } else if ("minimumCardsDueForNotification".equals(pref.getKey())) {
                if (!ListenerUtil.mutListener.listen(10633)) {
                    pref.setSummary(replaceStringIfNumeric(oldSummary, value));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10632)) {
                    pref.setSummary(replaceString(oldSummary, value));
                }
            }
        }
    }

    private String replaceString(String str, String value) {
        if (str.contains("XXX")) {
            return str.replace("XXX", value);
        } else {
            return str;
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private String replaceStringIfNumeric(String str, String value) {
        try {
            if (!ListenerUtil.mutListener.listen(10637)) {
                Double.parseDouble(value);
            }
            return replaceString(str, value);
        } catch (NumberFormatException e) {
            return value;
        }
    }

    private void initializeLanguageDialog(android.preference.PreferenceScreen screen) {
        android.preference.ListPreference languageSelection = (android.preference.ListPreference) screen.findPreference(LANGUAGE);
        if (!ListenerUtil.mutListener.listen(10656)) {
            if (languageSelection != null) {
                Map<String, String> items = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
                if (!ListenerUtil.mutListener.listen(10639)) {
                    {
                        long _loopCounter176 = 0;
                        for (String localeCode : LanguageUtil.APP_LANGUAGES) {
                            ListenerUtil.loopListener.listen("_loopCounter176", ++_loopCounter176);
                            Locale loc = LanguageUtil.getLocale(localeCode);
                            if (!ListenerUtil.mutListener.listen(10638)) {
                                items.put(loc.getDisplayName(loc), loc.toString());
                            }
                        }
                    }
                }
                CharSequence[] languageDialogLabels = new CharSequence[(ListenerUtil.mutListener.listen(10643) ? (items.size() % 1) : (ListenerUtil.mutListener.listen(10642) ? (items.size() / 1) : (ListenerUtil.mutListener.listen(10641) ? (items.size() * 1) : (ListenerUtil.mutListener.listen(10640) ? (items.size() - 1) : (items.size() + 1)))))];
                CharSequence[] languageDialogValues = new CharSequence[(ListenerUtil.mutListener.listen(10647) ? (items.size() % 1) : (ListenerUtil.mutListener.listen(10646) ? (items.size() / 1) : (ListenerUtil.mutListener.listen(10645) ? (items.size() * 1) : (ListenerUtil.mutListener.listen(10644) ? (items.size() - 1) : (items.size() + 1)))))];
                if (!ListenerUtil.mutListener.listen(10648)) {
                    languageDialogLabels[0] = getResources().getString(R.string.language_system);
                }
                if (!ListenerUtil.mutListener.listen(10649)) {
                    languageDialogValues[0] = "";
                }
                int i = 1;
                if (!ListenerUtil.mutListener.listen(10653)) {
                    {
                        long _loopCounter177 = 0;
                        for (Map.Entry<String, String> e : items.entrySet()) {
                            ListenerUtil.loopListener.listen("_loopCounter177", ++_loopCounter177);
                            if (!ListenerUtil.mutListener.listen(10650)) {
                                languageDialogLabels[i] = e.getKey();
                            }
                            if (!ListenerUtil.mutListener.listen(10651)) {
                                languageDialogValues[i] = e.getValue();
                            }
                            if (!ListenerUtil.mutListener.listen(10652)) {
                                i++;
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(10654)) {
                    languageSelection.setEntries(languageDialogLabels);
                }
                if (!ListenerUtil.mutListener.listen(10655)) {
                    languageSelection.setEntryValues(languageDialogValues);
                }
            }
        }
    }

    private void removeUnnecessaryAdvancedPrefs(android.preference.PreferenceScreen screen) {
        android.preference.PreferenceCategory plugins = (android.preference.PreferenceCategory) screen.findPreference("category_plugins");
        if (!ListenerUtil.mutListener.listen(10660)) {
            // Disable the emoji/kana buttons to scroll preference if those keys don't exist
            if (!CompatHelper.hasKanaAndEmojiKeys()) {
                android.preference.CheckBoxPreference emojiScrolling = (android.preference.CheckBoxPreference) screen.findPreference("scrolling_buttons");
                if (!ListenerUtil.mutListener.listen(10659)) {
                    if ((ListenerUtil.mutListener.listen(10657) ? (emojiScrolling != null || plugins != null) : (emojiScrolling != null && plugins != null))) {
                        if (!ListenerUtil.mutListener.listen(10658)) {
                            plugins.removePreference(emojiScrolling);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10665)) {
            // Disable the double scroll preference if no scrolling keys
            if ((ListenerUtil.mutListener.listen(10661) ? (!CompatHelper.hasScrollKeys() || !CompatHelper.hasKanaAndEmojiKeys()) : (!CompatHelper.hasScrollKeys() && !CompatHelper.hasKanaAndEmojiKeys()))) {
                android.preference.CheckBoxPreference doubleScrolling = (android.preference.CheckBoxPreference) screen.findPreference("double_scrolling");
                if (!ListenerUtil.mutListener.listen(10664)) {
                    if ((ListenerUtil.mutListener.listen(10662) ? (doubleScrolling != null || plugins != null) : (doubleScrolling != null && plugins != null))) {
                        if (!ListenerUtil.mutListener.listen(10663)) {
                            plugins.removePreference(doubleScrolling);
                        }
                    }
                }
            }
        }
    }

    /**
     * Initializes the list of custom fonts shown in the preferences.
     */
    private void initializeCustomFontsDialog(android.preference.PreferenceScreen screen) {
        android.preference.ListPreference defaultFontPreference = (android.preference.ListPreference) screen.findPreference("defaultFont");
        if (!ListenerUtil.mutListener.listen(10668)) {
            if (defaultFontPreference != null) {
                if (!ListenerUtil.mutListener.listen(10666)) {
                    defaultFontPreference.setEntries(getCustomFonts("System default"));
                }
                if (!ListenerUtil.mutListener.listen(10667)) {
                    defaultFontPreference.setEntryValues(getCustomFonts(""));
                }
            }
        }
        android.preference.ListPreference browserEditorCustomFontsPreference = (android.preference.ListPreference) screen.findPreference("browserEditorFont");
        if (!ListenerUtil.mutListener.listen(10669)) {
            browserEditorCustomFontsPreference.setEntries(getCustomFonts("System default"));
        }
        if (!ListenerUtil.mutListener.listen(10670)) {
            browserEditorCustomFontsPreference.setEntryValues(getCustomFonts("", true));
        }
    }

    /**
     * Returns a list of the names of the installed custom fonts.
     */
    private String[] getCustomFonts(String defaultValue) {
        return getCustomFonts(defaultValue, false);
    }

    private String[] getCustomFonts(String defaultValue, boolean useFullPath) {
        List<AnkiFont> mFonts = Utils.getCustomFonts(this);
        int count = mFonts.size();
        if (!ListenerUtil.mutListener.listen(10671)) {
            Timber.d("There are %d custom fonts", count);
        }
        String[] names = new String[(ListenerUtil.mutListener.listen(10675) ? (count % 1) : (ListenerUtil.mutListener.listen(10674) ? (count / 1) : (ListenerUtil.mutListener.listen(10673) ? (count * 1) : (ListenerUtil.mutListener.listen(10672) ? (count - 1) : (count + 1)))))];
        if (!ListenerUtil.mutListener.listen(10676)) {
            names[0] = defaultValue;
        }
        if (!ListenerUtil.mutListener.listen(10709)) {
            if (useFullPath) {
                if (!ListenerUtil.mutListener.listen(10708)) {
                    {
                        long _loopCounter179 = 0;
                        for (int index = 1; (ListenerUtil.mutListener.listen(10707) ? (index >= (ListenerUtil.mutListener.listen(10702) ? (count % 1) : (ListenerUtil.mutListener.listen(10701) ? (count / 1) : (ListenerUtil.mutListener.listen(10700) ? (count * 1) : (ListenerUtil.mutListener.listen(10699) ? (count - 1) : (count + 1)))))) : (ListenerUtil.mutListener.listen(10706) ? (index <= (ListenerUtil.mutListener.listen(10702) ? (count % 1) : (ListenerUtil.mutListener.listen(10701) ? (count / 1) : (ListenerUtil.mutListener.listen(10700) ? (count * 1) : (ListenerUtil.mutListener.listen(10699) ? (count - 1) : (count + 1)))))) : (ListenerUtil.mutListener.listen(10705) ? (index > (ListenerUtil.mutListener.listen(10702) ? (count % 1) : (ListenerUtil.mutListener.listen(10701) ? (count / 1) : (ListenerUtil.mutListener.listen(10700) ? (count * 1) : (ListenerUtil.mutListener.listen(10699) ? (count - 1) : (count + 1)))))) : (ListenerUtil.mutListener.listen(10704) ? (index != (ListenerUtil.mutListener.listen(10702) ? (count % 1) : (ListenerUtil.mutListener.listen(10701) ? (count / 1) : (ListenerUtil.mutListener.listen(10700) ? (count * 1) : (ListenerUtil.mutListener.listen(10699) ? (count - 1) : (count + 1)))))) : (ListenerUtil.mutListener.listen(10703) ? (index == (ListenerUtil.mutListener.listen(10702) ? (count % 1) : (ListenerUtil.mutListener.listen(10701) ? (count / 1) : (ListenerUtil.mutListener.listen(10700) ? (count * 1) : (ListenerUtil.mutListener.listen(10699) ? (count - 1) : (count + 1)))))) : (index < (ListenerUtil.mutListener.listen(10702) ? (count % 1) : (ListenerUtil.mutListener.listen(10701) ? (count / 1) : (ListenerUtil.mutListener.listen(10700) ? (count * 1) : (ListenerUtil.mutListener.listen(10699) ? (count - 1) : (count + 1))))))))))); ++index) {
                            ListenerUtil.loopListener.listen("_loopCounter179", ++_loopCounter179);
                            if (!ListenerUtil.mutListener.listen(10697)) {
                                names[index] = mFonts.get((ListenerUtil.mutListener.listen(10696) ? (index % 1) : (ListenerUtil.mutListener.listen(10695) ? (index / 1) : (ListenerUtil.mutListener.listen(10694) ? (index * 1) : (ListenerUtil.mutListener.listen(10693) ? (index + 1) : (index - 1)))))).getPath();
                            }
                            if (!ListenerUtil.mutListener.listen(10698)) {
                                Timber.d("Adding custom font: %s", names[index]);
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10692)) {
                    {
                        long _loopCounter178 = 0;
                        for (int index = 1; (ListenerUtil.mutListener.listen(10691) ? (index >= (ListenerUtil.mutListener.listen(10686) ? (count % 1) : (ListenerUtil.mutListener.listen(10685) ? (count / 1) : (ListenerUtil.mutListener.listen(10684) ? (count * 1) : (ListenerUtil.mutListener.listen(10683) ? (count - 1) : (count + 1)))))) : (ListenerUtil.mutListener.listen(10690) ? (index <= (ListenerUtil.mutListener.listen(10686) ? (count % 1) : (ListenerUtil.mutListener.listen(10685) ? (count / 1) : (ListenerUtil.mutListener.listen(10684) ? (count * 1) : (ListenerUtil.mutListener.listen(10683) ? (count - 1) : (count + 1)))))) : (ListenerUtil.mutListener.listen(10689) ? (index > (ListenerUtil.mutListener.listen(10686) ? (count % 1) : (ListenerUtil.mutListener.listen(10685) ? (count / 1) : (ListenerUtil.mutListener.listen(10684) ? (count * 1) : (ListenerUtil.mutListener.listen(10683) ? (count - 1) : (count + 1)))))) : (ListenerUtil.mutListener.listen(10688) ? (index != (ListenerUtil.mutListener.listen(10686) ? (count % 1) : (ListenerUtil.mutListener.listen(10685) ? (count / 1) : (ListenerUtil.mutListener.listen(10684) ? (count * 1) : (ListenerUtil.mutListener.listen(10683) ? (count - 1) : (count + 1)))))) : (ListenerUtil.mutListener.listen(10687) ? (index == (ListenerUtil.mutListener.listen(10686) ? (count % 1) : (ListenerUtil.mutListener.listen(10685) ? (count / 1) : (ListenerUtil.mutListener.listen(10684) ? (count * 1) : (ListenerUtil.mutListener.listen(10683) ? (count - 1) : (count + 1)))))) : (index < (ListenerUtil.mutListener.listen(10686) ? (count % 1) : (ListenerUtil.mutListener.listen(10685) ? (count / 1) : (ListenerUtil.mutListener.listen(10684) ? (count * 1) : (ListenerUtil.mutListener.listen(10683) ? (count - 1) : (count + 1))))))))))); ++index) {
                            ListenerUtil.loopListener.listen("_loopCounter178", ++_loopCounter178);
                            if (!ListenerUtil.mutListener.listen(10681)) {
                                names[index] = mFonts.get((ListenerUtil.mutListener.listen(10680) ? (index % 1) : (ListenerUtil.mutListener.listen(10679) ? (index / 1) : (ListenerUtil.mutListener.listen(10678) ? (index * 1) : (ListenerUtil.mutListener.listen(10677) ? (index + 1) : (index - 1)))))).getName();
                            }
                            if (!ListenerUtil.mutListener.listen(10682)) {
                                Timber.d("Adding custom font: %s", names[index]);
                            }
                        }
                    }
                }
            }
        }
        return names;
    }

    private void closePreferences() {
        if (!ListenerUtil.mutListener.listen(10710)) {
            finish();
        }
        if (!ListenerUtil.mutListener.listen(10711)) {
            ActivityTransitionAnimation.slide(this, FADE);
        }
        if (!ListenerUtil.mutListener.listen(10714)) {
            if ((ListenerUtil.mutListener.listen(10712) ? (getCol() != null || getCol().getDb() != null) : (getCol() != null && getCol().getDb() != null))) {
                if (!ListenerUtil.mutListener.listen(10713)) {
                    getCol().save();
                }
            }
        }
    }

    /**
     * This is not fit for purpose (other than testing a single screen)
     */
    @NonNull
    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public Set<String> getLoadedPreferenceKeys() {
        return mOriginalSumarries.keySet();
    }

    // Tracked as #5019 on github
    @SuppressWarnings("deprecation")
    public static class SettingsFragment extends android.preference.PreferenceFragment implements PreferenceContext, OnSharedPreferenceChangeListener {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            if (!ListenerUtil.mutListener.listen(10715)) {
                super.onCreate(savedInstanceState);
            }
            String subscreen = getArguments().getString("subscreen");
            if (!ListenerUtil.mutListener.listen(10716)) {
                UsageAnalytics.sendAnalyticsScreenView(subscreen.replaceFirst("^com.ichi2.anki.", ""));
            }
            if (!ListenerUtil.mutListener.listen(10717)) {
                ((Preferences) getActivity()).initSubscreen(subscreen, this);
            }
            if (!ListenerUtil.mutListener.listen(10718)) {
                ((Preferences) getActivity()).initAllPreferences(getPreferenceScreen());
            }
        }

        @Override
        public void onResume() {
            if (!ListenerUtil.mutListener.listen(10719)) {
                super.onResume();
            }
            SharedPreferences prefs = getPreferenceManager().getSharedPreferences();
            if (!ListenerUtil.mutListener.listen(10720)) {
                prefs.registerOnSharedPreferenceChangeListener(this);
            }
            if (!ListenerUtil.mutListener.listen(10721)) {
                // in from preferences screen), so we need to update it here.
                ((Preferences) getActivity()).updatePreference(prefs, "syncAccount", this);
            }
            if (!ListenerUtil.mutListener.listen(10722)) {
                ((Preferences) getActivity()).updatePreference(prefs, "custom_sync_server_link", this);
            }
            if (!ListenerUtil.mutListener.listen(10723)) {
                ((Preferences) getActivity()).updatePreference(prefs, "advanced_statistics_link", this);
            }
        }

        @Override
        public void onPause() {
            if (!ListenerUtil.mutListener.listen(10724)) {
                getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
            }
            if (!ListenerUtil.mutListener.listen(10725)) {
                super.onPause();
            }
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (!ListenerUtil.mutListener.listen(10726)) {
                ((Preferences) getActivity()).updatePreference(sharedPreferences, key, this);
            }
        }
    }
}
