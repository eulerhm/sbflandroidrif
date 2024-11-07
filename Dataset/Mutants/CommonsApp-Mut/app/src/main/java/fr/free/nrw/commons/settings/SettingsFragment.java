package fr.free.nrw.commons.settings;

import static android.content.Context.MODE_PRIVATE;
import android.Manifest.permission;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.preference.ListPreference;
import androidx.preference.MultiSelectListPreference;
import androidx.preference.Preference;
import androidx.preference.Preference.OnPreferenceClickListener;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceGroupAdapter;
import androidx.preference.PreferenceScreen;
import androidx.preference.PreferenceViewHolder;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.Utils;
import fr.free.nrw.commons.campaigns.CampaignView;
import fr.free.nrw.commons.contributions.ContributionController;
import fr.free.nrw.commons.contributions.MainActivity;
import fr.free.nrw.commons.di.ApplicationlessInjection;
import fr.free.nrw.commons.kvstore.JsonKvStore;
import fr.free.nrw.commons.location.LocationServiceManager;
import fr.free.nrw.commons.logging.CommonsLogSender;
import fr.free.nrw.commons.recentlanguages.Language;
import fr.free.nrw.commons.recentlanguages.RecentLanguagesAdapter;
import fr.free.nrw.commons.recentlanguages.RecentLanguagesDao;
import fr.free.nrw.commons.upload.LanguagesAdapter;
import fr.free.nrw.commons.utils.DialogUtil;
import fr.free.nrw.commons.utils.PermissionUtils;
import fr.free.nrw.commons.utils.ViewUtil;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Inject
    @Named("default_preferences")
    JsonKvStore defaultKvStore;

    @Inject
    CommonsLogSender commonsLogSender;

    @Inject
    RecentLanguagesDao recentLanguagesDao;

    @Inject
    ContributionController contributionController;

    @Inject
    LocationServiceManager locationManager;

    private ListPreference themeListPreference;

    private Preference descriptionLanguageListPreference;

    private Preference appUiLanguageListPreference;

    private String keyLanguageListPreference;

    private TextView recentLanguagesTextView;

    private View separator;

    private ListView languageHistoryListView;

    private static final String GET_CONTENT_PICKER_HELP_URL = "https://commons-app.github.io/docs.html#get-content";

    private ActivityResultLauncher<String[]> inAppCameraLocationPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {

        @Override
        public void onActivityResult(Map<String, Boolean> result) {
            boolean areAllGranted = true;
            if (!ListenerUtil.mutListener.listen(30)) {
                {
                    long _loopCounter0 = 0;
                    for (final boolean b : result.values()) {
                        ListenerUtil.loopListener.listen("_loopCounter0", ++_loopCounter0);
                        if (!ListenerUtil.mutListener.listen(29)) {
                            areAllGranted = (ListenerUtil.mutListener.listen(28) ? (areAllGranted || b) : (areAllGranted && b));
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(33)) {
                if ((ListenerUtil.mutListener.listen(31) ? (!areAllGranted || shouldShowRequestPermissionRationale(permission.ACCESS_FINE_LOCATION)) : (!areAllGranted && shouldShowRequestPermissionRationale(permission.ACCESS_FINE_LOCATION)))) {
                    if (!ListenerUtil.mutListener.listen(32)) {
                        contributionController.handleShowRationaleFlowCameraLocation(getActivity());
                    }
                }
            }
        }
    });

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        if (!ListenerUtil.mutListener.listen(34)) {
            ApplicationlessInjection.getInstance(getActivity().getApplicationContext()).getCommonsApplicationComponent().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(35)) {
            // Set the preferences from an XML resource
            setPreferencesFromResource(R.xml.preferences, rootKey);
        }
        if (!ListenerUtil.mutListener.listen(36)) {
            themeListPreference = findPreference(Prefs.KEY_THEME_VALUE);
        }
        if (!ListenerUtil.mutListener.listen(37)) {
            prepareTheme();
        }
        MultiSelectListPreference multiSelectListPref = findPreference(Prefs.MANAGED_EXIF_TAGS);
        if (!ListenerUtil.mutListener.listen(39)) {
            if (multiSelectListPref != null) {
                if (!ListenerUtil.mutListener.listen(38)) {
                    multiSelectListPref.setOnPreferenceChangeListener((preference, newValue) -> {
                        if (newValue instanceof HashSet && !((HashSet) newValue).contains(getString(R.string.exif_tag_location))) {
                            defaultKvStore.putBoolean("has_user_manually_removed_location", true);
                        }
                        return true;
                    });
                }
            }
        }
        Preference inAppCameraLocationPref = findPreference("inAppCameraLocationPref");
        if (!ListenerUtil.mutListener.listen(40)) {
            inAppCameraLocationPref.setOnPreferenceChangeListener((preference, newValue) -> {
                boolean isInAppCameraLocationTurnedOn = (boolean) newValue;
                if (isInAppCameraLocationTurnedOn) {
                    createDialogsAndHandleLocationPermissions(getActivity());
                }
                return true;
            });
        }
        // Gets current language code from shared preferences
        String languageCode;
        if (!ListenerUtil.mutListener.listen(41)) {
            appUiLanguageListPreference = findPreference("appUiDefaultLanguagePref");
        }
        assert appUiLanguageListPreference != null;
        if (!ListenerUtil.mutListener.listen(42)) {
            keyLanguageListPreference = appUiLanguageListPreference.getKey();
        }
        languageCode = getCurrentLanguageCode(keyLanguageListPreference);
        assert languageCode != null;
        if (!ListenerUtil.mutListener.listen(45)) {
            if (languageCode.equals("")) {
                if (!ListenerUtil.mutListener.listen(44)) {
                    // If current language code is empty, means none selected by user yet so use phone local
                    appUiLanguageListPreference.setSummary(Locale.getDefault().getDisplayLanguage());
                }
            } else {
                // If any language is selected by user previously, use it
                Locale defLocale = new Locale(languageCode);
                if (!ListenerUtil.mutListener.listen(43)) {
                    appUiLanguageListPreference.setSummary((defLocale).getDisplayLanguage(defLocale));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(47)) {
            appUiLanguageListPreference.setOnPreferenceClickListener(new OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (!ListenerUtil.mutListener.listen(46)) {
                        prepareAppLanguages(appUiLanguageListPreference.getKey());
                    }
                    return true;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(48)) {
            descriptionLanguageListPreference = findPreference("descriptionDefaultLanguagePref");
        }
        assert descriptionLanguageListPreference != null;
        if (!ListenerUtil.mutListener.listen(49)) {
            keyLanguageListPreference = descriptionLanguageListPreference.getKey();
        }
        languageCode = getCurrentLanguageCode(keyLanguageListPreference);
        assert languageCode != null;
        if (!ListenerUtil.mutListener.listen(52)) {
            if (languageCode.equals("")) {
                if (!ListenerUtil.mutListener.listen(51)) {
                    // If current language code is empty, means none selected by user yet so use phone local
                    descriptionLanguageListPreference.setSummary(Locale.getDefault().getDisplayLanguage());
                }
            } else {
                // If any language is selected by user previously, use it
                Locale defLocale = new Locale(languageCode);
                if (!ListenerUtil.mutListener.listen(50)) {
                    descriptionLanguageListPreference.setSummary(defLocale.getDisplayLanguage(defLocale));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(54)) {
            descriptionLanguageListPreference.setOnPreferenceClickListener(new OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (!ListenerUtil.mutListener.listen(53)) {
                        prepareAppLanguages(descriptionLanguageListPreference.getKey());
                    }
                    return true;
                }
            });
        }
        Preference betaTesterPreference = findPreference("becomeBetaTester");
        if (!ListenerUtil.mutListener.listen(55)) {
            betaTesterPreference.setOnPreferenceClickListener(preference -> {
                Utils.handleWebUrl(getActivity(), Uri.parse(getResources().getString(R.string.beta_opt_in_link)));
                return true;
            });
        }
        Preference sendLogsPreference = findPreference("sendLogFile");
        if (!ListenerUtil.mutListener.listen(56)) {
            sendLogsPreference.setOnPreferenceClickListener(preference -> {
                checkPermissionsAndSendLogs();
                return true;
            });
        }
        Preference documentBasedPickerPreference = findPreference("openDocumentPhotoPickerPref");
        if (!ListenerUtil.mutListener.listen(57)) {
            documentBasedPickerPreference.setOnPreferenceChangeListener((preference, newValue) -> {
                boolean isGetContentPickerTurnedOn = !(boolean) newValue;
                if (isGetContentPickerTurnedOn) {
                    showLocationLossWarning();
                }
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(67)) {
            // Disable some settings when not logged in.
            if (defaultKvStore.getBoolean("login_skipped", false)) {
                if (!ListenerUtil.mutListener.listen(58)) {
                    findPreference("useExternalStorage").setEnabled(false);
                }
                if (!ListenerUtil.mutListener.listen(59)) {
                    findPreference("useAuthorName").setEnabled(false);
                }
                if (!ListenerUtil.mutListener.listen(60)) {
                    findPreference("displayNearbyCardView").setEnabled(false);
                }
                if (!ListenerUtil.mutListener.listen(61)) {
                    findPreference("descriptionDefaultLanguagePref").setEnabled(false);
                }
                if (!ListenerUtil.mutListener.listen(62)) {
                    findPreference("displayLocationPermissionForCardView").setEnabled(false);
                }
                if (!ListenerUtil.mutListener.listen(63)) {
                    findPreference(CampaignView.CAMPAIGNS_DEFAULT_PREFERENCE).setEnabled(false);
                }
                if (!ListenerUtil.mutListener.listen(64)) {
                    findPreference("managed_exif_tags").setEnabled(false);
                }
                if (!ListenerUtil.mutListener.listen(65)) {
                    findPreference("openDocumentPhotoPickerPref").setEnabled(false);
                }
                if (!ListenerUtil.mutListener.listen(66)) {
                    findPreference("inAppCameraLocationPref").setEnabled(false);
                }
            }
        }
    }

    /**
     * Asks users to provide location access
     *
     * @param activity
     */
    private void createDialogsAndHandleLocationPermissions(Activity activity) {
        if (!ListenerUtil.mutListener.listen(68)) {
            inAppCameraLocationPermissionLauncher.launch(new String[] { permission.ACCESS_FINE_LOCATION });
        }
    }

    /**
     * On some devices, the new Photo Picker with GET_CONTENT takeover
     * redacts location tags from EXIF metadata
     *
     * Show warning to the user when ACTION_GET_CONTENT intent is enabled
     */
    private void showLocationLossWarning() {
        if (!ListenerUtil.mutListener.listen(69)) {
            DialogUtil.showAlertDialog(getActivity(), null, getString(R.string.location_loss_warning), getString(R.string.ok), getString(R.string.read_help_link), () -> {
            }, () -> Utils.handleWebUrl(requireContext(), Uri.parse(GET_CONTENT_PICKER_HELP_URL)), null, true);
        }
    }

    @Override
    protected Adapter onCreateAdapter(final PreferenceScreen preferenceScreen) {
        return new PreferenceGroupAdapter(preferenceScreen) {

            @Override
            public void onBindViewHolder(PreferenceViewHolder holder, int position) {
                if (!ListenerUtil.mutListener.listen(70)) {
                    super.onBindViewHolder(holder, position);
                }
                Preference preference = getItem(position);
                View iconFrame = holder.itemView.findViewById(R.id.icon_frame);
                if (!ListenerUtil.mutListener.listen(72)) {
                    if (iconFrame != null) {
                        if (!ListenerUtil.mutListener.listen(71)) {
                            iconFrame.setVisibility(View.GONE);
                        }
                    }
                }
            }
        };
    }

    /**
     * Sets the theme pref
     */
    private void prepareTheme() {
        if (!ListenerUtil.mutListener.listen(73)) {
            themeListPreference.setOnPreferenceChangeListener((preference, newValue) -> {
                getActivity().recreate();
                return true;
            });
        }
    }

    /**
     * Prepare and Show language selection dialog box
     * Uses previously saved language if there is any, if not uses phone locale as initial language.
     * Disable default/already selected language from dialog box
     * Get ListPreference key and act accordingly for each ListPreference.
     * saves value chosen by user to shared preferences
     * to remember later and recall MainActivity to reflect language changes
     * @param keyListPreference
     */
    private void prepareAppLanguages(final String keyListPreference) {
        // Gets current language code from shared preferences
        final String languageCode = getCurrentLanguageCode(keyListPreference);
        final List<Language> recentLanguages = recentLanguagesDao.getRecentLanguages();
        HashMap<Integer, String> selectedLanguages = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(80)) {
            if (keyListPreference.equals("appUiDefaultLanguagePref")) {
                assert languageCode != null;
                if (!ListenerUtil.mutListener.listen(79)) {
                    if (languageCode.equals("")) {
                        if (!ListenerUtil.mutListener.listen(78)) {
                            selectedLanguages.put(0, Locale.getDefault().getLanguage());
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(77)) {
                            selectedLanguages.put(0, languageCode);
                        }
                    }
                }
            } else if (keyListPreference.equals("descriptionDefaultLanguagePref")) {
                assert languageCode != null;
                if (!ListenerUtil.mutListener.listen(76)) {
                    if (languageCode.equals("")) {
                        if (!ListenerUtil.mutListener.listen(75)) {
                            selectedLanguages.put(0, Locale.getDefault().getLanguage());
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(74)) {
                            selectedLanguages.put(0, languageCode);
                        }
                    }
                }
            }
        }
        LanguagesAdapter languagesAdapter = new LanguagesAdapter(getActivity(), selectedLanguages);
        Dialog dialog = new Dialog(getActivity());
        if (!ListenerUtil.mutListener.listen(81)) {
            dialog.setContentView(R.layout.dialog_select_language);
        }
        if (!ListenerUtil.mutListener.listen(82)) {
            dialog.setCanceledOnTouchOutside(true);
        }
        if (!ListenerUtil.mutListener.listen(91)) {
            dialog.getWindow().setLayout((int) ((ListenerUtil.mutListener.listen(86) ? (getActivity().getResources().getDisplayMetrics().widthPixels % 0.90) : (ListenerUtil.mutListener.listen(85) ? (getActivity().getResources().getDisplayMetrics().widthPixels / 0.90) : (ListenerUtil.mutListener.listen(84) ? (getActivity().getResources().getDisplayMetrics().widthPixels - 0.90) : (ListenerUtil.mutListener.listen(83) ? (getActivity().getResources().getDisplayMetrics().widthPixels + 0.90) : (getActivity().getResources().getDisplayMetrics().widthPixels * 0.90)))))), (int) ((ListenerUtil.mutListener.listen(90) ? (getActivity().getResources().getDisplayMetrics().heightPixels % 0.90) : (ListenerUtil.mutListener.listen(89) ? (getActivity().getResources().getDisplayMetrics().heightPixels / 0.90) : (ListenerUtil.mutListener.listen(88) ? (getActivity().getResources().getDisplayMetrics().heightPixels - 0.90) : (ListenerUtil.mutListener.listen(87) ? (getActivity().getResources().getDisplayMetrics().heightPixels + 0.90) : (getActivity().getResources().getDisplayMetrics().heightPixels * 0.90)))))));
        }
        if (!ListenerUtil.mutListener.listen(92)) {
            dialog.show();
        }
        EditText editText = dialog.findViewById(R.id.search_language);
        ListView listView = dialog.findViewById(R.id.language_list);
        if (!ListenerUtil.mutListener.listen(93)) {
            languageHistoryListView = dialog.findViewById(R.id.language_history_list);
        }
        if (!ListenerUtil.mutListener.listen(94)) {
            recentLanguagesTextView = dialog.findViewById(R.id.recent_searches);
        }
        if (!ListenerUtil.mutListener.listen(95)) {
            separator = dialog.findViewById(R.id.separator);
        }
        if (!ListenerUtil.mutListener.listen(96)) {
            setUpRecentLanguagesSection(recentLanguages, selectedLanguages);
        }
        if (!ListenerUtil.mutListener.listen(97)) {
            listView.setAdapter(languagesAdapter);
        }
        if (!ListenerUtil.mutListener.listen(100)) {
            editText.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (!ListenerUtil.mutListener.listen(98)) {
                        hideRecentLanguagesSection();
                    }
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (!ListenerUtil.mutListener.listen(99)) {
                        languagesAdapter.getFilter().filter(charSequence);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(101)) {
            languageHistoryListView.setOnItemClickListener((adapterView, view, position, id) -> {
                onRecentLanguageClicked(keyListPreference, dialog, adapterView, position);
            });
        }
        if (!ListenerUtil.mutListener.listen(113)) {
            listView.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String languageCode = ((LanguagesAdapter) adapterView.getAdapter()).getLanguageCode(i);
                    final String languageName = ((LanguagesAdapter) adapterView.getAdapter()).getLanguageName(i);
                    final boolean isExists = recentLanguagesDao.findRecentLanguage(languageCode);
                    if (!ListenerUtil.mutListener.listen(103)) {
                        if (isExists) {
                            if (!ListenerUtil.mutListener.listen(102)) {
                                recentLanguagesDao.deleteRecentLanguage(languageCode);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(104)) {
                        recentLanguagesDao.addRecentLanguage(new Language(languageName, languageCode));
                    }
                    if (!ListenerUtil.mutListener.listen(105)) {
                        saveLanguageValue(languageCode, keyListPreference);
                    }
                    Locale defLocale = new Locale(languageCode);
                    if (!ListenerUtil.mutListener.listen(111)) {
                        if (keyListPreference.equals("appUiDefaultLanguagePref")) {
                            if (!ListenerUtil.mutListener.listen(107)) {
                                appUiLanguageListPreference.setSummary(defLocale.getDisplayLanguage(defLocale));
                            }
                            if (!ListenerUtil.mutListener.listen(108)) {
                                setLocale(requireActivity(), languageCode);
                            }
                            if (!ListenerUtil.mutListener.listen(109)) {
                                getActivity().recreate();
                            }
                            final Intent intent = new Intent(getActivity(), MainActivity.class);
                            if (!ListenerUtil.mutListener.listen(110)) {
                                startActivity(intent);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(106)) {
                                descriptionLanguageListPreference.setSummary(defLocale.getDisplayLanguage(defLocale));
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(112)) {
                        dialog.dismiss();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(114)) {
            dialog.setOnDismissListener(dialogInterface -> languagesAdapter.getFilter().filter(""));
        }
    }

    /**
     * Set up recent languages section
     *
     * @param recentLanguages recently used languages
     * @param selectedLanguages selected languages
     */
    private void setUpRecentLanguagesSection(List<Language> recentLanguages, HashMap<Integer, String> selectedLanguages) {
        if (!ListenerUtil.mutListener.listen(139)) {
            if (recentLanguages.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(136)) {
                    languageHistoryListView.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(137)) {
                    recentLanguagesTextView.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(138)) {
                    separator.setVisibility(View.GONE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(131)) {
                    if ((ListenerUtil.mutListener.listen(119) ? (recentLanguages.size() >= 5) : (ListenerUtil.mutListener.listen(118) ? (recentLanguages.size() <= 5) : (ListenerUtil.mutListener.listen(117) ? (recentLanguages.size() < 5) : (ListenerUtil.mutListener.listen(116) ? (recentLanguages.size() != 5) : (ListenerUtil.mutListener.listen(115) ? (recentLanguages.size() == 5) : (recentLanguages.size() > 5))))))) {
                        if (!ListenerUtil.mutListener.listen(130)) {
                            {
                                long _loopCounter1 = 0;
                                for (int i = (ListenerUtil.mutListener.listen(129) ? (recentLanguages.size() % 1) : (ListenerUtil.mutListener.listen(128) ? (recentLanguages.size() / 1) : (ListenerUtil.mutListener.listen(127) ? (recentLanguages.size() * 1) : (ListenerUtil.mutListener.listen(126) ? (recentLanguages.size() + 1) : (recentLanguages.size() - 1))))); (ListenerUtil.mutListener.listen(125) ? (i <= 5) : (ListenerUtil.mutListener.listen(124) ? (i > 5) : (ListenerUtil.mutListener.listen(123) ? (i < 5) : (ListenerUtil.mutListener.listen(122) ? (i != 5) : (ListenerUtil.mutListener.listen(121) ? (i == 5) : (i >= 5)))))); i--) {
                                    ListenerUtil.loopListener.listen("_loopCounter1", ++_loopCounter1);
                                    if (!ListenerUtil.mutListener.listen(120)) {
                                        recentLanguagesDao.deleteRecentLanguage(recentLanguages.get(i).getLanguageCode());
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(132)) {
                    languageHistoryListView.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(133)) {
                    recentLanguagesTextView.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(134)) {
                    separator.setVisibility(View.VISIBLE);
                }
                final RecentLanguagesAdapter recentLanguagesAdapter = new RecentLanguagesAdapter(getActivity(), recentLanguagesDao.getRecentLanguages(), selectedLanguages);
                if (!ListenerUtil.mutListener.listen(135)) {
                    languageHistoryListView.setAdapter(recentLanguagesAdapter);
                }
            }
        }
    }

    /**
     * Handles click event for recent language section
     */
    private void onRecentLanguageClicked(String keyListPreference, Dialog dialog, AdapterView<?> adapterView, int position) {
        final String recentLanguageCode = ((RecentLanguagesAdapter) adapterView.getAdapter()).getLanguageCode(position);
        final String recentLanguageName = ((RecentLanguagesAdapter) adapterView.getAdapter()).getLanguageName(position);
        final boolean isExists = recentLanguagesDao.findRecentLanguage(recentLanguageCode);
        if (!ListenerUtil.mutListener.listen(141)) {
            if (isExists) {
                if (!ListenerUtil.mutListener.listen(140)) {
                    recentLanguagesDao.deleteRecentLanguage(recentLanguageCode);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(142)) {
            recentLanguagesDao.addRecentLanguage(new Language(recentLanguageName, recentLanguageCode));
        }
        if (!ListenerUtil.mutListener.listen(143)) {
            saveLanguageValue(recentLanguageCode, keyListPreference);
        }
        final Locale defLocale = new Locale(recentLanguageCode);
        if (!ListenerUtil.mutListener.listen(149)) {
            if (keyListPreference.equals("appUiDefaultLanguagePref")) {
                if (!ListenerUtil.mutListener.listen(145)) {
                    appUiLanguageListPreference.setSummary(defLocale.getDisplayLanguage(defLocale));
                }
                if (!ListenerUtil.mutListener.listen(146)) {
                    setLocale(requireActivity(), recentLanguageCode);
                }
                if (!ListenerUtil.mutListener.listen(147)) {
                    getActivity().recreate();
                }
                final Intent intent = new Intent(getActivity(), MainActivity.class);
                if (!ListenerUtil.mutListener.listen(148)) {
                    startActivity(intent);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(144)) {
                    descriptionLanguageListPreference.setSummary(defLocale.getDisplayLanguage(defLocale));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(150)) {
            dialog.dismiss();
        }
    }

    /**
     * Remove the section of recent languages
     */
    private void hideRecentLanguagesSection() {
        if (!ListenerUtil.mutListener.listen(151)) {
            languageHistoryListView.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(152)) {
            recentLanguagesTextView.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(153)) {
            separator.setVisibility(View.GONE);
        }
    }

    /**
     * Changing the default app language with selected one and save it to SharedPreferences
     */
    public void setLocale(final Activity activity, String userSelectedValue) {
        if (!ListenerUtil.mutListener.listen(155)) {
            if (userSelectedValue.equals("")) {
                if (!ListenerUtil.mutListener.listen(154)) {
                    userSelectedValue = Locale.getDefault().getLanguage();
                }
            }
        }
        final Locale locale = new Locale(userSelectedValue);
        if (!ListenerUtil.mutListener.listen(156)) {
            Locale.setDefault(locale);
        }
        final Configuration configuration = new Configuration();
        if (!ListenerUtil.mutListener.listen(157)) {
            configuration.locale = locale;
        }
        if (!ListenerUtil.mutListener.listen(158)) {
            activity.getBaseContext().getResources().updateConfiguration(configuration, activity.getBaseContext().getResources().getDisplayMetrics());
        }
        final SharedPreferences.Editor editor = activity.getSharedPreferences("Settings", MODE_PRIVATE).edit();
        if (!ListenerUtil.mutListener.listen(159)) {
            editor.putString("language", userSelectedValue);
        }
        if (!ListenerUtil.mutListener.listen(160)) {
            editor.apply();
        }
    }

    /**
     * Save userselected language in List Preference
     * @param userSelectedValue
     * @param preferenceKey
     */
    private void saveLanguageValue(final String userSelectedValue, final String preferenceKey) {
        if (!ListenerUtil.mutListener.listen(163)) {
            if (preferenceKey.equals("appUiDefaultLanguagePref")) {
                if (!ListenerUtil.mutListener.listen(162)) {
                    defaultKvStore.putString(Prefs.APP_UI_LANGUAGE, userSelectedValue);
                }
            } else if (preferenceKey.equals("descriptionDefaultLanguagePref")) {
                if (!ListenerUtil.mutListener.listen(161)) {
                    defaultKvStore.putString(Prefs.DESCRIPTION_LANGUAGE, userSelectedValue);
                }
            }
        }
    }

    /**
     * Gets current language code from shared preferences
     * @param preferenceKey
     * @return
     */
    private String getCurrentLanguageCode(final String preferenceKey) {
        if (!ListenerUtil.mutListener.listen(164)) {
            if (preferenceKey.equals("appUiDefaultLanguagePref")) {
                return defaultKvStore.getString(Prefs.APP_UI_LANGUAGE, "");
            }
        }
        if (!ListenerUtil.mutListener.listen(165)) {
            if (preferenceKey.equals("descriptionDefaultLanguagePref")) {
                return defaultKvStore.getString(Prefs.DESCRIPTION_LANGUAGE, "");
            }
        }
        return null;
    }

    /**
     * First checks for external storage permissions and then sends logs via email
     */
    private void checkPermissionsAndSendLogs() {
        if (!ListenerUtil.mutListener.listen(168)) {
            if (PermissionUtils.hasPermission(getActivity(), PermissionUtils.PERMISSIONS_STORAGE)) {
                if (!ListenerUtil.mutListener.listen(167)) {
                    commonsLogSender.send(getActivity(), null);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(166)) {
                    requestExternalStoragePermissions();
                }
            }
        }
    }

    /**
     * Requests external storage permissions and shows a toast stating that log collection has
     * started
     */
    private void requestExternalStoragePermissions() {
        if (!ListenerUtil.mutListener.listen(170)) {
            Dexter.withActivity(getActivity()).withPermissions(PermissionUtils.PERMISSIONS_STORAGE).withListener(new MultiplePermissionsListener() {

                @Override
                public void onPermissionsChecked(MultiplePermissionsReport report) {
                    if (!ListenerUtil.mutListener.listen(169)) {
                        ViewUtil.showLongToast(getActivity(), getResources().getString(R.string.log_collection_started));
                    }
                }

                @Override
                public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                }
            }).onSameThread().check();
        }
    }
}
