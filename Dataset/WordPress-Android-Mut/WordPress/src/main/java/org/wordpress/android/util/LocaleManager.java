package org.wordpress.android.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import kotlin.Triple;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Helper class for working with localized strings. Ensures updates to the users
 * selected language is properly saved and resources appropriately updated for the
 * android version.
 */
public class LocaleManager {

    /**
     * Key used for saving the language selection to shared preferences.
     */
    private static final String LANGUAGE_KEY = "language-pref";

    /**
     * Pattern to split a language string (to parse the language and region values).
     */
    private static Pattern languageSplitter = Pattern.compile("_");

    /**
     * Activate the locale associated with the provided context.
     *
     * @param context The current context.
     */
    public static Context setLocale(Context context) {
        return updateResources(context, getLanguage(context));
    }

    /**
     * Apply locale to the provided configuration.
     *
     * @param context current context used to access Shared Preferences.
     * @param configuration configuration that the locale should be applied to.
     */
    public static Configuration updatedConfigLocale(Context context, Configuration configuration) {
        Locale locale = languageLocale(getLanguage(context));
        if (!ListenerUtil.mutListener.listen(27658)) {
            Locale.setDefault(locale);
        }
        if (!ListenerUtil.mutListener.listen(27659)) {
            // RTL may not be implemented properly.
            configuration.setLocale(locale);
        }
        if (!ListenerUtil.mutListener.listen(27666)) {
            if ((ListenerUtil.mutListener.listen(27664) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) : (ListenerUtil.mutListener.listen(27663) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N_MR1) : (ListenerUtil.mutListener.listen(27662) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) : (ListenerUtil.mutListener.listen(27661) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.N_MR1) : (ListenerUtil.mutListener.listen(27660) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.N_MR1) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.N_MR1))))))) {
                if (!ListenerUtil.mutListener.listen(27665)) {
                    configuration.locale = locale;
                }
            }
        }
        return configuration;
    }

    /**
     * Change the active locale to the language provided. Save the updated language
     * settings to sharedPreferences.
     *
     * @param context  The current context
     * @param language The 2-letter language code (example "en") to switch to
     */
    public static void setNewLocale(Context context, String language) {
        if (!ListenerUtil.mutListener.listen(27667)) {
            if (isSameLanguage(language)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(27668)) {
            saveLanguageToPref(context, language);
        }
        if (!ListenerUtil.mutListener.listen(27669)) {
            updateResources(context, language);
        }
    }

    /**
     * Compare the language for the current context with another language.
     *
     * @param language The language to compare
     * @return True if the languages are the same, else false
     */
    public static boolean isSameLanguage(@NonNull String language) {
        Locale newLocale = languageLocale(language);
        return Locale.getDefault().toString().equals(newLocale.toString());
    }

    /**
     * If the user has selected a language other than the device default, return that
     * language code, else just return the device default language code.
     *
     * @return The 2-letter language code (example "en")
     */
    public static String getLanguage(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(LANGUAGE_KEY, LanguageUtils.getCurrentDeviceLanguageCode());
    }

    /**
     * Convert the device language code (codes defined by ISO 639-1) to a Language ID.
     * Language IDs, used only by WordPress, are integer values that map to a language code.
     * http://bit.ly/2H7gksN
     */
    @NonNull
    public static String getLanguageWordPressId(Context context) {
        final String deviceLanguageCode = LanguageUtils.getPatchedCurrentDeviceLanguage(context);
        Map<String, String> languageCodeToID = LocaleManager.generateLanguageMap(context);
        String langID = null;
        if (!ListenerUtil.mutListener.listen(27679)) {
            if (languageCodeToID.containsKey(deviceLanguageCode)) {
                if (!ListenerUtil.mutListener.listen(27678)) {
                    langID = languageCodeToID.get(deviceLanguageCode);
                }
            } else {
                int pos = deviceLanguageCode.indexOf("_");
                if (!ListenerUtil.mutListener.listen(27677)) {
                    if ((ListenerUtil.mutListener.listen(27674) ? (pos >= -1) : (ListenerUtil.mutListener.listen(27673) ? (pos <= -1) : (ListenerUtil.mutListener.listen(27672) ? (pos < -1) : (ListenerUtil.mutListener.listen(27671) ? (pos != -1) : (ListenerUtil.mutListener.listen(27670) ? (pos == -1) : (pos > -1))))))) {
                        String newLang = deviceLanguageCode.substring(0, pos);
                        if (!ListenerUtil.mutListener.listen(27676)) {
                            if (languageCodeToID.containsKey(newLang)) {
                                if (!ListenerUtil.mutListener.listen(27675)) {
                                    langID = languageCodeToID.get(newLang);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27681)) {
            if (langID == null) {
                if (!ListenerUtil.mutListener.listen(27680)) {
                    // fallback to device language code if there is no match
                    langID = deviceLanguageCode;
                }
            }
        }
        return langID;
    }

    /**
     * Save the updated language to SharedPreferences.
     * Use commit() instead of apply() to ensure the language preference is saved instantly
     * as the app may be restarted immediately.
     *
     * @param context  The current context
     * @param language The 2-letter language code (example "en")
     */
    @SuppressLint("ApplySharedPref")
    private static void saveLanguageToPref(Context context, String language) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (!ListenerUtil.mutListener.listen(27682)) {
            prefs.edit().putString(LANGUAGE_KEY, language).commit();
        }
    }

    /**
     * Update resources for the current session.
     *
     * @param context  The current active context
     * @param language The 2-letter language code (example "en")
     * @return The modified context containing the updated localized resources
     */
    private static Context updateResources(Context context, String language) {
        Locale locale = languageLocale(language);
        if (!ListenerUtil.mutListener.listen(27683)) {
            Locale.setDefault(locale);
        }
        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        if (!ListenerUtil.mutListener.listen(27684)) {
            // RTL may not be implemented properly.
            config.setLocale(locale);
        }
        if (!ListenerUtil.mutListener.listen(27685)) {
            context = context.createConfigurationContext(config);
        }
        if (!ListenerUtil.mutListener.listen(27693)) {
            if ((ListenerUtil.mutListener.listen(27690) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) : (ListenerUtil.mutListener.listen(27689) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N_MR1) : (ListenerUtil.mutListener.listen(27688) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) : (ListenerUtil.mutListener.listen(27687) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.N_MR1) : (ListenerUtil.mutListener.listen(27686) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.N_MR1) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.N_MR1))))))) {
                if (!ListenerUtil.mutListener.listen(27691)) {
                    config.locale = locale;
                }
                if (!ListenerUtil.mutListener.listen(27692)) {
                    res.updateConfiguration(config, res.getDisplayMetrics());
                }
            }
        }
        return context;
    }

    /**
     * Method gets around a bug in the java.util.Formatter for API 7.x as detailed here
     * [https://bugs.openjdk.java.net/browse/JDK-8167567]. Any strings that contain
     * locale-specific grouping separators should use:
     * <code>
     * String.format(LocaleManager.getSafeLocale(context), baseString, val)
     * </code>
     * <p>
     * An example of a string that contains locale-specific grouping separators:
     * <code>
     * <string name="test">%,d likes</string>
     * </code>
     */
    public static Locale getSafeLocale(@Nullable Context context) {
        Locale baseLocale;
        if (context == null) {
            baseLocale = Locale.getDefault();
        } else {
            Configuration config = context.getResources().getConfiguration();
            baseLocale = config.getLocales().get(0);
        }
        return languageLocale(baseLocale.getLanguage());
    }

    /**
     * Gets a locale for the given language code.
     *
     * @param languageCode The language code (example "en" or "es-US"). If null or empty will return
     *                     the current default locale.
     */
    public static Locale languageLocale(@Nullable String languageCode) {
        if (TextUtils.isEmpty(languageCode)) {
            return Locale.getDefault();
        }
        // Attempt to parse language and region codes.
        String[] opts = languageSplitter.split(languageCode, 0);
        if ((ListenerUtil.mutListener.listen(27698) ? (opts.length >= 1) : (ListenerUtil.mutListener.listen(27697) ? (opts.length <= 1) : (ListenerUtil.mutListener.listen(27696) ? (opts.length < 1) : (ListenerUtil.mutListener.listen(27695) ? (opts.length != 1) : (ListenerUtil.mutListener.listen(27694) ? (opts.length == 1) : (opts.length > 1))))))) {
            return new Locale(opts[0], opts[1]);
        } else {
            return new Locale(opts[0]);
        }
    }

    /**
     * Creates a map from language codes to WordPress language IDs.
     */
    public static Map<String, String> generateLanguageMap(Context context) {
        String[] languageIds = context.getResources().getStringArray(org.wordpress.android.R.array.lang_ids);
        String[] languageCodes = context.getResources().getStringArray(org.wordpress.android.R.array.language_codes);
        Map<String, String> languageMap = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(27711)) {
            {
                long _loopCounter408 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(27710) ? ((ListenerUtil.mutListener.listen(27704) ? (i >= languageIds.length) : (ListenerUtil.mutListener.listen(27703) ? (i <= languageIds.length) : (ListenerUtil.mutListener.listen(27702) ? (i > languageIds.length) : (ListenerUtil.mutListener.listen(27701) ? (i != languageIds.length) : (ListenerUtil.mutListener.listen(27700) ? (i == languageIds.length) : (i < languageIds.length)))))) || (ListenerUtil.mutListener.listen(27709) ? (i >= languageCodes.length) : (ListenerUtil.mutListener.listen(27708) ? (i <= languageCodes.length) : (ListenerUtil.mutListener.listen(27707) ? (i > languageCodes.length) : (ListenerUtil.mutListener.listen(27706) ? (i != languageCodes.length) : (ListenerUtil.mutListener.listen(27705) ? (i == languageCodes.length) : (i < languageCodes.length))))))) : ((ListenerUtil.mutListener.listen(27704) ? (i >= languageIds.length) : (ListenerUtil.mutListener.listen(27703) ? (i <= languageIds.length) : (ListenerUtil.mutListener.listen(27702) ? (i > languageIds.length) : (ListenerUtil.mutListener.listen(27701) ? (i != languageIds.length) : (ListenerUtil.mutListener.listen(27700) ? (i == languageIds.length) : (i < languageIds.length)))))) && (ListenerUtil.mutListener.listen(27709) ? (i >= languageCodes.length) : (ListenerUtil.mutListener.listen(27708) ? (i <= languageCodes.length) : (ListenerUtil.mutListener.listen(27707) ? (i > languageCodes.length) : (ListenerUtil.mutListener.listen(27706) ? (i != languageCodes.length) : (ListenerUtil.mutListener.listen(27705) ? (i == languageCodes.length) : (i < languageCodes.length)))))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter408", ++_loopCounter408);
                    if (!ListenerUtil.mutListener.listen(27699)) {
                        languageMap.put(languageCodes[i], languageIds[i]);
                    }
                }
            }
        }
        return languageMap;
    }

    /**
     * Generates display strings for given language codes. Used as entries in language preference.
     */
    @Nullable
    public static Triple<String[], String[], String[]> createSortedLanguageDisplayStrings(CharSequence[] languageCodes, Locale locale) {
        if (!ListenerUtil.mutListener.listen(27718)) {
            if ((ListenerUtil.mutListener.listen(27717) ? (languageCodes == null && (ListenerUtil.mutListener.listen(27716) ? (languageCodes.length >= 1) : (ListenerUtil.mutListener.listen(27715) ? (languageCodes.length <= 1) : (ListenerUtil.mutListener.listen(27714) ? (languageCodes.length > 1) : (ListenerUtil.mutListener.listen(27713) ? (languageCodes.length != 1) : (ListenerUtil.mutListener.listen(27712) ? (languageCodes.length == 1) : (languageCodes.length < 1))))))) : (languageCodes == null || (ListenerUtil.mutListener.listen(27716) ? (languageCodes.length >= 1) : (ListenerUtil.mutListener.listen(27715) ? (languageCodes.length <= 1) : (ListenerUtil.mutListener.listen(27714) ? (languageCodes.length > 1) : (ListenerUtil.mutListener.listen(27713) ? (languageCodes.length != 1) : (ListenerUtil.mutListener.listen(27712) ? (languageCodes.length == 1) : (languageCodes.length < 1))))))))) {
                return null;
            }
        }
        ArrayList<String> entryStrings = new ArrayList<>(languageCodes.length);
        if (!ListenerUtil.mutListener.listen(27725)) {
            {
                long _loopCounter409 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(27724) ? (i >= languageCodes.length) : (ListenerUtil.mutListener.listen(27723) ? (i <= languageCodes.length) : (ListenerUtil.mutListener.listen(27722) ? (i > languageCodes.length) : (ListenerUtil.mutListener.listen(27721) ? (i != languageCodes.length) : (ListenerUtil.mutListener.listen(27720) ? (i == languageCodes.length) : (i < languageCodes.length)))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter409", ++_loopCounter409);
                    if (!ListenerUtil.mutListener.listen(27719)) {
                        // "__" is used to sort the language code with the display string so both arrays are sorted at the same time
                        entryStrings.add(i, StringUtils.capitalize(getLanguageString(languageCodes[i].toString(), locale)) + "__" + languageCodes[i]);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27726)) {
            Collections.sort(entryStrings, Collator.getInstance(locale));
        }
        String[] sortedEntries = new String[languageCodes.length];
        String[] sortedValues = new String[languageCodes.length];
        String[] detailStrings = new String[languageCodes.length];
        if (!ListenerUtil.mutListener.listen(27735)) {
            {
                long _loopCounter410 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(27734) ? (i >= entryStrings.size()) : (ListenerUtil.mutListener.listen(27733) ? (i <= entryStrings.size()) : (ListenerUtil.mutListener.listen(27732) ? (i > entryStrings.size()) : (ListenerUtil.mutListener.listen(27731) ? (i != entryStrings.size()) : (ListenerUtil.mutListener.listen(27730) ? (i == entryStrings.size()) : (i < entryStrings.size())))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter410", ++_loopCounter410);
                    // now, we can split the sorted array to extract the display string and the language code
                    String[] split = entryStrings.get(i).split("__");
                    if (!ListenerUtil.mutListener.listen(27727)) {
                        sortedEntries[i] = split[0];
                    }
                    if (!ListenerUtil.mutListener.listen(27728)) {
                        sortedValues[i] = split[1];
                    }
                    if (!ListenerUtil.mutListener.listen(27729)) {
                        detailStrings[i] = StringUtils.capitalize(getLanguageString(sortedValues[i], languageLocale(sortedValues[i])));
                    }
                }
            }
        }
        return new Triple<>(sortedEntries, sortedValues, detailStrings);
    }

    /**
     * Generates detail display strings in the currently selected locale. Used as detail text
     * in language preference dialog.
     */
    @Nullable
    public static String[] createLanguageDetailDisplayStrings(CharSequence[] languageCodes) {
        if (!ListenerUtil.mutListener.listen(27742)) {
            if ((ListenerUtil.mutListener.listen(27741) ? (languageCodes == null && (ListenerUtil.mutListener.listen(27740) ? (languageCodes.length >= 1) : (ListenerUtil.mutListener.listen(27739) ? (languageCodes.length <= 1) : (ListenerUtil.mutListener.listen(27738) ? (languageCodes.length > 1) : (ListenerUtil.mutListener.listen(27737) ? (languageCodes.length != 1) : (ListenerUtil.mutListener.listen(27736) ? (languageCodes.length == 1) : (languageCodes.length < 1))))))) : (languageCodes == null || (ListenerUtil.mutListener.listen(27740) ? (languageCodes.length >= 1) : (ListenerUtil.mutListener.listen(27739) ? (languageCodes.length <= 1) : (ListenerUtil.mutListener.listen(27738) ? (languageCodes.length > 1) : (ListenerUtil.mutListener.listen(27737) ? (languageCodes.length != 1) : (ListenerUtil.mutListener.listen(27736) ? (languageCodes.length == 1) : (languageCodes.length < 1))))))))) {
                return null;
            }
        }
        String[] detailStrings = new String[languageCodes.length];
        if (!ListenerUtil.mutListener.listen(27749)) {
            {
                long _loopCounter411 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(27748) ? (i >= languageCodes.length) : (ListenerUtil.mutListener.listen(27747) ? (i <= languageCodes.length) : (ListenerUtil.mutListener.listen(27746) ? (i > languageCodes.length) : (ListenerUtil.mutListener.listen(27745) ? (i != languageCodes.length) : (ListenerUtil.mutListener.listen(27744) ? (i == languageCodes.length) : (i < languageCodes.length)))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter411", ++_loopCounter411);
                    if (!ListenerUtil.mutListener.listen(27743)) {
                        detailStrings[i] = StringUtils.capitalize(getLanguageString(languageCodes[i].toString(), languageLocale(languageCodes[i].toString())));
                    }
                }
            }
        }
        return detailStrings;
    }

    /**
     * Return a non-null display string for a given language code.
     */
    public static String getLanguageString(String languageCode, Locale displayLocale) {
        if (!ListenerUtil.mutListener.listen(27762)) {
            if ((ListenerUtil.mutListener.listen(27761) ? ((ListenerUtil.mutListener.listen(27755) ? (languageCode == null && (ListenerUtil.mutListener.listen(27754) ? (languageCode.length() >= 2) : (ListenerUtil.mutListener.listen(27753) ? (languageCode.length() <= 2) : (ListenerUtil.mutListener.listen(27752) ? (languageCode.length() > 2) : (ListenerUtil.mutListener.listen(27751) ? (languageCode.length() != 2) : (ListenerUtil.mutListener.listen(27750) ? (languageCode.length() == 2) : (languageCode.length() < 2))))))) : (languageCode == null || (ListenerUtil.mutListener.listen(27754) ? (languageCode.length() >= 2) : (ListenerUtil.mutListener.listen(27753) ? (languageCode.length() <= 2) : (ListenerUtil.mutListener.listen(27752) ? (languageCode.length() > 2) : (ListenerUtil.mutListener.listen(27751) ? (languageCode.length() != 2) : (ListenerUtil.mutListener.listen(27750) ? (languageCode.length() == 2) : (languageCode.length() < 2)))))))) && (ListenerUtil.mutListener.listen(27760) ? (languageCode.length() >= 6) : (ListenerUtil.mutListener.listen(27759) ? (languageCode.length() <= 6) : (ListenerUtil.mutListener.listen(27758) ? (languageCode.length() < 6) : (ListenerUtil.mutListener.listen(27757) ? (languageCode.length() != 6) : (ListenerUtil.mutListener.listen(27756) ? (languageCode.length() == 6) : (languageCode.length() > 6))))))) : ((ListenerUtil.mutListener.listen(27755) ? (languageCode == null && (ListenerUtil.mutListener.listen(27754) ? (languageCode.length() >= 2) : (ListenerUtil.mutListener.listen(27753) ? (languageCode.length() <= 2) : (ListenerUtil.mutListener.listen(27752) ? (languageCode.length() > 2) : (ListenerUtil.mutListener.listen(27751) ? (languageCode.length() != 2) : (ListenerUtil.mutListener.listen(27750) ? (languageCode.length() == 2) : (languageCode.length() < 2))))))) : (languageCode == null || (ListenerUtil.mutListener.listen(27754) ? (languageCode.length() >= 2) : (ListenerUtil.mutListener.listen(27753) ? (languageCode.length() <= 2) : (ListenerUtil.mutListener.listen(27752) ? (languageCode.length() > 2) : (ListenerUtil.mutListener.listen(27751) ? (languageCode.length() != 2) : (ListenerUtil.mutListener.listen(27750) ? (languageCode.length() == 2) : (languageCode.length() < 2)))))))) || (ListenerUtil.mutListener.listen(27760) ? (languageCode.length() >= 6) : (ListenerUtil.mutListener.listen(27759) ? (languageCode.length() <= 6) : (ListenerUtil.mutListener.listen(27758) ? (languageCode.length() < 6) : (ListenerUtil.mutListener.listen(27757) ? (languageCode.length() != 6) : (ListenerUtil.mutListener.listen(27756) ? (languageCode.length() == 6) : (languageCode.length() > 6))))))))) {
                return "";
            }
        }
        Locale languageLocale = languageLocale(languageCode);
        String displayLanguage = StringUtils.capitalize(languageLocale.getDisplayLanguage(displayLocale));
        String displayCountry = languageLocale.getDisplayCountry(displayLocale);
        if (!ListenerUtil.mutListener.listen(27763)) {
            if (!TextUtils.isEmpty(displayCountry)) {
                return displayLanguage + " (" + displayCountry + ")";
            }
        }
        return displayLanguage;
    }
}
