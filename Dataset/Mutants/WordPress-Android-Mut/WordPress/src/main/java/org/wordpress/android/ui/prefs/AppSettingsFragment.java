package org.wordpress.android.ui.prefs;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.BuildConfig;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.action.AccountAction;
import org.wordpress.android.fluxc.generated.AccountActionBuilder;
import org.wordpress.android.fluxc.generated.WhatsNewActionBuilder;
import org.wordpress.android.fluxc.model.whatsnew.WhatsNewAnnouncementModel;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.AccountStore.OnAccountChanged;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.store.WhatsNewStore.OnWhatsNewFetched;
import org.wordpress.android.fluxc.store.WhatsNewStore.WhatsNewAppId;
import org.wordpress.android.fluxc.store.WhatsNewStore.WhatsNewFetchPayload;
import org.wordpress.android.ui.prefs.language.LocalePickerBottomSheet;
import org.wordpress.android.ui.prefs.language.LocalePickerBottomSheet.LocalePickerCallback;
import org.wordpress.android.ui.about.UnifiedAboutActivity;
import org.wordpress.android.ui.debug.DebugSettingsActivity;
import org.wordpress.android.ui.mysite.tabs.MySiteDefaultTabExperiment;
import org.wordpress.android.ui.mysite.tabs.MySiteTabType;
import org.wordpress.android.ui.reader.services.update.ReaderUpdateLogic;
import org.wordpress.android.ui.reader.services.update.ReaderUpdateServiceStarter;
import org.wordpress.android.ui.whatsnew.FeatureAnnouncementDialogFragment;
import org.wordpress.android.ui.whatsnew.FeatureAnnouncementProvider;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppThemeUtils;
import org.wordpress.android.util.BuildConfigWrapper;
import org.wordpress.android.util.LocaleManager;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.WPActivityUtils;
import org.wordpress.android.util.WPPrefUtils;
import org.wordpress.android.util.analytics.AnalyticsUtils;
import org.wordpress.android.util.config.MySiteDashboardTabsFeatureConfig;
import org.wordpress.android.util.config.UnifiedAboutFeatureConfig;
import org.wordpress.android.viewmodel.ContextProvider;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AppSettingsFragment extends PreferenceFragment implements OnPreferenceClickListener, Preference.OnPreferenceChangeListener, LocalePickerCallback {

    public static final int LANGUAGE_CHANGED = 1000;

    private WPPreference mLanguagePreference;

    private ListPreference mAppThemePreference;

    private ListPreference mInitialScreenPreference;

    // This Device settings
    private WPSwitchPreference mOptimizedImage;

    private DetailListPreference mImageMaxSizePref;

    private DetailListPreference mImageQualityPref;

    private WPSwitchPreference mOptimizedVideo;

    private DetailListPreference mVideoWidthPref;

    private DetailListPreference mVideoEncorderBitratePref;

    private PreferenceScreen mPrivacySettings;

    private WPSwitchPreference mStripImageLocation;

    private WPSwitchPreference mReportCrashPref;

    private Preference mWhatsNew;

    @Inject
    SiteStore mSiteStore;

    @Inject
    AccountStore mAccountStore;

    @Inject
    Dispatcher mDispatcher;

    @Inject
    ContextProvider mContextProvider;

    @Inject
    FeatureAnnouncementProvider mFeatureAnnouncementProvider;

    @Inject
    BuildConfigWrapper mBuildConfigWrapper;

    @Inject
    UnifiedAboutFeatureConfig mUnifiedAboutFeatureConfig;

    @Inject
    MySiteDashboardTabsFeatureConfig mMySiteDashboardTabsFeatureConfig;

    @Inject
    MySiteDefaultTabExperiment mMySiteDefaultTabExperiment;

    private static final String TRACK_STYLE = "style";

    private static final String TRACK_ENABLED = "enabled";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(14334)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(14335)) {
            ((WordPress) getActivity().getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(14336)) {
            mDispatcher.register(this);
        }
        if (!ListenerUtil.mutListener.listen(14337)) {
            setRetainInstance(true);
        }
        if (!ListenerUtil.mutListener.listen(14338)) {
            addPreferencesFromResource(R.xml.app_settings);
        }
        if (!ListenerUtil.mutListener.listen(14341)) {
            findPreference(getString(R.string.pref_key_send_usage)).setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if (!ListenerUtil.mutListener.listen(14339)) {
                        if (newValue == null) {
                            return false;
                        }
                    }
                    boolean hasUserOptedOut = !(boolean) newValue;
                    if (!ListenerUtil.mutListener.listen(14340)) {
                        AnalyticsUtils.updateAnalyticsPreference(getActivity(), mDispatcher, mAccountStore, hasUserOptedOut);
                    }
                    return true;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(14342)) {
            updateAnalyticsSyncUI();
        }
        if (!ListenerUtil.mutListener.listen(14343)) {
            mLanguagePreference = (WPPreference) findPreference(getString(R.string.pref_key_language));
        }
        if (!ListenerUtil.mutListener.listen(14344)) {
            mLanguagePreference.setOnPreferenceChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(14345)) {
            mLanguagePreference.setOnPreferenceClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(14346)) {
            mAppThemePreference = (ListPreference) findPreference(getString(R.string.pref_key_app_theme));
        }
        if (!ListenerUtil.mutListener.listen(14347)) {
            mAppThemePreference.setOnPreferenceChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(14348)) {
            mInitialScreenPreference = (ListPreference) findPreference(getString(R.string.pref_key_initial_screen));
        }
        if (!ListenerUtil.mutListener.listen(14349)) {
            mInitialScreenPreference.setOnPreferenceChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(14350)) {
            findPreference(getString(R.string.pref_key_language)).setOnPreferenceClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(14351)) {
            findPreference(getString(R.string.pref_key_device_settings)).setOnPreferenceClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(14352)) {
            findPreference(getString(R.string.pref_key_debug_settings)).setOnPreferenceClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(14353)) {
            findPreference(getString(R.string.pref_key_app_about)).setOnPreferenceClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(14354)) {
            findPreference(getString(R.string.pref_key_oss_licenses)).setOnPreferenceClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(14355)) {
            mOptimizedImage = (WPSwitchPreference) WPPrefUtils.getPrefAndSetChangeListener(this, R.string.pref_key_optimize_image, this);
        }
        if (!ListenerUtil.mutListener.listen(14356)) {
            mImageMaxSizePref = (DetailListPreference) WPPrefUtils.getPrefAndSetChangeListener(this, R.string.pref_key_site_image_width, this);
        }
        if (!ListenerUtil.mutListener.listen(14357)) {
            mImageQualityPref = (DetailListPreference) WPPrefUtils.getPrefAndSetChangeListener(this, R.string.pref_key_site_image_quality, this);
        }
        if (!ListenerUtil.mutListener.listen(14358)) {
            mOptimizedVideo = (WPSwitchPreference) WPPrefUtils.getPrefAndSetChangeListener(this, R.string.pref_key_optimize_video, this);
        }
        if (!ListenerUtil.mutListener.listen(14359)) {
            mVideoWidthPref = (DetailListPreference) WPPrefUtils.getPrefAndSetChangeListener(this, R.string.pref_key_site_video_width, this);
        }
        if (!ListenerUtil.mutListener.listen(14360)) {
            mVideoEncorderBitratePref = (DetailListPreference) WPPrefUtils.getPrefAndSetChangeListener(this, R.string.pref_key_site_video_encoder_bitrate, this);
        }
        if (!ListenerUtil.mutListener.listen(14361)) {
            mPrivacySettings = (PreferenceScreen) WPPrefUtils.getPrefAndSetClickListener(this, R.string.pref_key_privacy_settings, this);
        }
        if (!ListenerUtil.mutListener.listen(14362)) {
            mStripImageLocation = (WPSwitchPreference) WPPrefUtils.getPrefAndSetChangeListener(this, R.string.pref_key_strip_image_location, this);
        }
        if (!ListenerUtil.mutListener.listen(14363)) {
            mReportCrashPref = (WPSwitchPreference) WPPrefUtils.getPrefAndSetChangeListener(this, R.string.pref_key_send_crash, this);
        }
        if (!ListenerUtil.mutListener.listen(14364)) {
            // Set Local settings
            mOptimizedImage.setChecked(AppPrefs.isImageOptimize());
        }
        if (!ListenerUtil.mutListener.listen(14365)) {
            setDetailListPreferenceValue(mImageMaxSizePref, String.valueOf(AppPrefs.getImageOptimizeMaxSize()), getLabelForImageMaxSizeValue(AppPrefs.getImageOptimizeMaxSize()));
        }
        if (!ListenerUtil.mutListener.listen(14366)) {
            setDetailListPreferenceValue(mImageQualityPref, String.valueOf(AppPrefs.getImageOptimizeQuality()), getLabelForImageQualityValue(AppPrefs.getImageOptimizeQuality()));
        }
        if (!ListenerUtil.mutListener.listen(14367)) {
            mOptimizedVideo.setChecked(AppPrefs.isVideoOptimize());
        }
        if (!ListenerUtil.mutListener.listen(14368)) {
            setDetailListPreferenceValue(mVideoWidthPref, String.valueOf(AppPrefs.getVideoOptimizeWidth()), getLabelForVideoMaxWidthValue(AppPrefs.getVideoOptimizeWidth()));
        }
        if (!ListenerUtil.mutListener.listen(14369)) {
            setDetailListPreferenceValue(mVideoEncorderBitratePref, String.valueOf(AppPrefs.getVideoOptimizeQuality()), getLabelForVideoEncoderBitrateValue(AppPrefs.getVideoOptimizeQuality()));
        }
        if (!ListenerUtil.mutListener.listen(14370)) {
            mStripImageLocation.setChecked(AppPrefs.isStripImageLocation());
        }
        if (!ListenerUtil.mutListener.listen(14371)) {
            mWhatsNew = findPreference(getString(R.string.pref_key_whats_new));
        }
        if (!ListenerUtil.mutListener.listen(14372)) {
            removeWhatsNewPreference();
        }
        if (!ListenerUtil.mutListener.listen(14373)) {
            mDispatcher.dispatch(WhatsNewActionBuilder.newFetchCachedAnnouncementAction());
        }
        if (!ListenerUtil.mutListener.listen(14375)) {
            if (mUnifiedAboutFeatureConfig.isEnabled()) {
                if (!ListenerUtil.mutListener.listen(14374)) {
                    removeAboutCategory();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14377)) {
            if (!BuildConfig.OFFER_GUTENBERG) {
                if (!ListenerUtil.mutListener.listen(14376)) {
                    removeExperimentalCategory();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14379)) {
            if (!BuildConfig.ENABLE_DEBUG_SETTINGS) {
                if (!ListenerUtil.mutListener.listen(14378)) {
                    removeDebugSettingsCategory();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14381)) {
            if (!mMySiteDashboardTabsFeatureConfig.isEnabled()) {
                if (!ListenerUtil.mutListener.listen(14380)) {
                    removeInitialScreen();
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        final ListView listOfPreferences = view.findViewById(android.R.id.list);
        if (!ListenerUtil.mutListener.listen(14383)) {
            if (listOfPreferences != null) {
                if (!ListenerUtil.mutListener.listen(14382)) {
                    ViewCompat.setNestedScrollingEnabled(listOfPreferences, true);
                }
            }
        }
        return view;
    }

    private void removeExperimentalCategory() {
        PreferenceCategory experimentalPreferenceCategory = (PreferenceCategory) findPreference(getString(R.string.pref_key_experimental_section));
        PreferenceScreen preferenceScreen = (PreferenceScreen) findPreference(getString(R.string.pref_key_app_settings_root));
        if (!ListenerUtil.mutListener.listen(14384)) {
            preferenceScreen.removePreference(experimentalPreferenceCategory);
        }
    }

    private void removeDebugSettingsCategory() {
        Preference experimentalPreference = findPreference(getString(R.string.pref_key_debug_settings));
        PreferenceScreen preferenceScreen = (PreferenceScreen) findPreference(getString(R.string.pref_key_app_settings_root));
        if (!ListenerUtil.mutListener.listen(14385)) {
            preferenceScreen.removePreference(experimentalPreference);
        }
    }

    private void removeAboutCategory() {
        PreferenceCategory aboutPreferenceCategory = (PreferenceCategory) findPreference(getString(R.string.pref_key_about_section));
        PreferenceScreen preferenceScreen = (PreferenceScreen) findPreference(getString(R.string.pref_key_app_settings_root));
        if (!ListenerUtil.mutListener.listen(14386)) {
            preferenceScreen.removePreference(aboutPreferenceCategory);
        }
    }

    private void removeWhatsNewPreference() {
        PreferenceScreen preferenceScreen = (PreferenceScreen) findPreference(getString(R.string.pref_key_app_settings_root));
        if (!ListenerUtil.mutListener.listen(14387)) {
            preferenceScreen.removePreference(mWhatsNew);
        }
    }

    private void addWhatsNewPreference() {
        PreferenceScreen preferenceScreen = (PreferenceScreen) findPreference(getString(R.string.pref_key_app_settings_root));
        if (!ListenerUtil.mutListener.listen(14388)) {
            preferenceScreen.addPreference(mWhatsNew);
        }
    }

    private void removeInitialScreen() {
        Preference initialScreenPreference = findPreference(getString(R.string.pref_key_initial_screen));
        PreferenceScreen preferenceScreen = (PreferenceScreen) findPreference(getString(R.string.pref_key_app_settings_root));
        if (!ListenerUtil.mutListener.listen(14389)) {
            preferenceScreen.removePreference(initialScreenPreference);
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(14390)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(14393)) {
            if ((ListenerUtil.mutListener.listen(14391) ? (mAccountStore.hasAccessToken() || NetworkUtils.isNetworkAvailable(getActivity())) : (mAccountStore.hasAccessToken() && NetworkUtils.isNetworkAvailable(getActivity())))) {
                if (!ListenerUtil.mutListener.listen(14392)) {
                    mDispatcher.dispatch(AccountActionBuilder.newFetchSettingsAction());
                }
            }
        }
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(14394)) {
            super.onStart();
        }
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(14395)) {
            mDispatcher.unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(14396)) {
            super.onStop();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(14397)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(14398)) {
            reattachLocalePickerCallback();
        }
        if (!ListenerUtil.mutListener.listen(14399)) {
            // flush gathered events (if any)
            AnalyticsTracker.flush();
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWhatsNewFetched(OnWhatsNewFetched event) {
        if (!ListenerUtil.mutListener.listen(14401)) {
            if (event.isFromCache()) {
                if (!ListenerUtil.mutListener.listen(14400)) {
                    mDispatcher.dispatch(WhatsNewActionBuilder.newFetchRemoteAnnouncementAction(new WhatsNewFetchPayload(mBuildConfigWrapper.getAppVersionName(), WhatsNewAppId.WP_ANDROID)));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14404)) {
            if ((ListenerUtil.mutListener.listen(14403) ? ((ListenerUtil.mutListener.listen(14402) ? (event.error != null && event.getWhatsNewItems() == null) : (event.error != null || event.getWhatsNewItems() == null)) && event.getWhatsNewItems().isEmpty()) : ((ListenerUtil.mutListener.listen(14402) ? (event.error != null && event.getWhatsNewItems() == null) : (event.error != null || event.getWhatsNewItems() == null)) || event.getWhatsNewItems().isEmpty()))) {
                return;
            }
        }
        WhatsNewAnnouncementModel latestAnnouncement = event.getWhatsNewItems().get(0);
        if (!ListenerUtil.mutListener.listen(14405)) {
            mWhatsNew.setSummary(getString(R.string.version_with_name_param, latestAnnouncement.getAppVersionName()));
        }
        if (!ListenerUtil.mutListener.listen(14406)) {
            mWhatsNew.setOnPreferenceClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(14408)) {
            if (mBuildConfigWrapper.isWhatsNewFeatureEnabled()) {
                if (!ListenerUtil.mutListener.listen(14407)) {
                    addWhatsNewPreference();
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAccountChanged(OnAccountChanged event) {
        if (!ListenerUtil.mutListener.listen(14409)) {
            if (!isAdded()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(14415)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(14414)) {
                    switch(event.error.type) {
                        case SETTINGS_FETCH_GENERIC_ERROR:
                            if (!ListenerUtil.mutListener.listen(14411)) {
                                ToastUtils.showToast(getActivity(), R.string.error_fetch_account_settings, ToastUtils.Duration.LONG);
                            }
                            break;
                        case SETTINGS_FETCH_REAUTHORIZATION_REQUIRED_ERROR:
                            if (!ListenerUtil.mutListener.listen(14412)) {
                                ToastUtils.showToast(getActivity(), R.string.error_disabled_apis, ToastUtils.Duration.LONG);
                            }
                            break;
                        case SETTINGS_POST_ERROR:
                            if (!ListenerUtil.mutListener.listen(14413)) {
                                ToastUtils.showToast(getActivity(), R.string.error_post_account_settings, ToastUtils.Duration.LONG);
                            }
                            break;
                    }
                }
            } else if (event.causeOfChange == AccountAction.FETCH_SETTINGS) {
                if (!ListenerUtil.mutListener.listen(14410)) {
                    // no need to sync with remote here, or do anything else here, since the logic is already in WordPress.java
                    updateAnalyticsSyncUI();
                }
            }
        }
    }

    /* Make sure the UI is synced with the backend value */
    private void updateAnalyticsSyncUI() {
        if (!ListenerUtil.mutListener.listen(14416)) {
            if (!isAdded()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(14418)) {
            if (mAccountStore.hasAccessToken()) {
                SwitchPreference tracksOptOutPreference = (SwitchPreference) findPreference(getString(R.string.pref_key_send_usage));
                if (!ListenerUtil.mutListener.listen(14417)) {
                    tracksOptOutPreference.setChecked(!mAccountStore.getAccount().getTracksOptOut());
                }
            }
        }
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        String preferenceKey = preference != null ? preference.getKey() : "";
        if (!ListenerUtil.mutListener.listen(14419)) {
            if (preferenceKey.equals(getString(R.string.pref_key_device_settings))) {
                return handleDevicePreferenceClick();
            } else if (preferenceKey.equals(getString(R.string.pref_key_debug_settings))) {
                return handleDebugSettingsPreferenceClick();
            } else if (preferenceKey.equals(getString(R.string.pref_key_app_about))) {
                return handleAboutPreferenceClick();
            } else if (preferenceKey.equals(getString(R.string.pref_key_oss_licenses))) {
                return handleOssPreferenceClick();
            } else if (preference == mPrivacySettings) {
                return handlePrivacyClick();
            } else if (preference == mWhatsNew) {
                return handleFeatureAnnouncementClick();
            } else if (preference == mLanguagePreference) {
                return handleAppLocalePickerClick();
            }
        }
        return false;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (!ListenerUtil.mutListener.listen(14420)) {
            if (newValue == null) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(14450)) {
            if (preference == mLanguagePreference) {
                if (!ListenerUtil.mutListener.listen(14449)) {
                    changeLanguage(newValue.toString());
                }
                return false;
            } else if (preference == mOptimizedImage) {
                if (!ListenerUtil.mutListener.listen(14445)) {
                    AppPrefs.setImageOptimize((Boolean) newValue);
                }
                if (!ListenerUtil.mutListener.listen(14446)) {
                    mImageMaxSizePref.setEnabled((Boolean) newValue);
                }
                Map<String, Object> properties = new HashMap<>();
                if (!ListenerUtil.mutListener.listen(14447)) {
                    properties.put("enabled", newValue);
                }
                if (!ListenerUtil.mutListener.listen(14448)) {
                    AnalyticsTracker.track(AnalyticsTracker.Stat.SITE_SETTINGS_OPTIMIZE_IMAGES_CHANGED, properties);
                }
            } else if (preference == mImageMaxSizePref) {
                int newWidth = Integer.parseInt(newValue.toString());
                if (!ListenerUtil.mutListener.listen(14442)) {
                    AppPrefs.setImageOptimizeMaxSize(newWidth);
                }
                if (!ListenerUtil.mutListener.listen(14443)) {
                    setDetailListPreferenceValue(mImageMaxSizePref, newValue.toString(), getLabelForImageMaxSizeValue(AppPrefs.getImageOptimizeMaxSize()));
                }
                if (!ListenerUtil.mutListener.listen(14444)) {
                    AnalyticsTracker.track(AnalyticsTracker.Stat.APP_SETTINGS_MAX_IMAGE_SIZE_CHANGED);
                }
            } else if (preference == mImageQualityPref) {
                if (!ListenerUtil.mutListener.listen(14439)) {
                    AppPrefs.setImageOptimizeQuality(Integer.parseInt(newValue.toString()));
                }
                if (!ListenerUtil.mutListener.listen(14440)) {
                    setDetailListPreferenceValue(mImageQualityPref, newValue.toString(), getLabelForImageQualityValue(AppPrefs.getImageOptimizeQuality()));
                }
                if (!ListenerUtil.mutListener.listen(14441)) {
                    AnalyticsTracker.track(AnalyticsTracker.Stat.APP_SETTINGS_IMAGE_QUALITY_CHANGED);
                }
            } else if (preference == mOptimizedVideo) {
                if (!ListenerUtil.mutListener.listen(14436)) {
                    AppPrefs.setVideoOptimize((Boolean) newValue);
                }
                if (!ListenerUtil.mutListener.listen(14437)) {
                    mVideoEncorderBitratePref.setEnabled((Boolean) newValue);
                }
                if (!ListenerUtil.mutListener.listen(14438)) {
                    AnalyticsTracker.track(AnalyticsTracker.Stat.APP_SETTINGS_VIDEO_OPTIMIZATION_CHANGED, Collections.singletonMap(TRACK_ENABLED, newValue));
                }
            } else if (preference == mVideoWidthPref) {
                int newWidth = Integer.parseInt(newValue.toString());
                if (!ListenerUtil.mutListener.listen(14433)) {
                    AppPrefs.setVideoOptimizeWidth(newWidth);
                }
                if (!ListenerUtil.mutListener.listen(14434)) {
                    setDetailListPreferenceValue(mVideoWidthPref, newValue.toString(), getLabelForVideoMaxWidthValue(AppPrefs.getVideoOptimizeWidth()));
                }
                if (!ListenerUtil.mutListener.listen(14435)) {
                    AnalyticsTracker.track(AnalyticsTracker.Stat.APP_SETTINGS_MAX_VIDEO_SIZE_CHANGED);
                }
            } else if (preference == mVideoEncorderBitratePref) {
                if (!ListenerUtil.mutListener.listen(14430)) {
                    AppPrefs.setVideoOptimizeQuality(Integer.parseInt(newValue.toString()));
                }
                if (!ListenerUtil.mutListener.listen(14431)) {
                    setDetailListPreferenceValue(mVideoEncorderBitratePref, newValue.toString(), getLabelForVideoEncoderBitrateValue(AppPrefs.getVideoOptimizeQuality()));
                }
                if (!ListenerUtil.mutListener.listen(14432)) {
                    AnalyticsTracker.track(AnalyticsTracker.Stat.APP_SETTINGS_VIDEO_QUALITY_CHANGED);
                }
            } else if (preference == mStripImageLocation) {
                if (!ListenerUtil.mutListener.listen(14428)) {
                    AppPrefs.setStripImageLocation((Boolean) newValue);
                }
                if (!ListenerUtil.mutListener.listen(14429)) {
                    AnalyticsTracker.track(AnalyticsTracker.Stat.APP_SETTINGS_REMOVE_LOCATION_FROM_MEDIA_CHANGED, Collections.singletonMap(TRACK_ENABLED, newValue));
                }
            } else if (preference == mAppThemePreference) {
                if (!ListenerUtil.mutListener.listen(14425)) {
                    AppThemeUtils.Companion.setAppTheme(getActivity(), (String) newValue);
                }
                if (!ListenerUtil.mutListener.listen(14426)) {
                    AnalyticsTracker.track(AnalyticsTracker.Stat.APP_SETTINGS_APPEARANCE_CHANGED, Collections.singletonMap(TRACK_STYLE, (String) newValue));
                }
                if (!ListenerUtil.mutListener.listen(14427)) {
                    // restart activity to make sure changes are applied to PreferenceScreen
                    getActivity().recreate();
                }
            } else if (preference == mInitialScreenPreference) {
                String trackValue = newValue.equals(MySiteTabType.SITE_MENU.getLabel()) ? MySiteTabType.SITE_MENU.getTrackingLabel() : MySiteTabType.DASHBOARD.getTrackingLabel();
                Map<String, Object> properties = new HashMap<>();
                if (!ListenerUtil.mutListener.listen(14422)) {
                    properties.put("selected", trackValue);
                }
                if (!ListenerUtil.mutListener.listen(14423)) {
                    AnalyticsTracker.track(Stat.APP_SETTINGS_INITIAL_SCREEN_CHANGED, properties);
                }
                if (!ListenerUtil.mutListener.listen(14424)) {
                    mMySiteDefaultTabExperiment.changeExperimentVariantAssignmentIfNeeded(trackValue);
                }
            } else if (preference == mReportCrashPref) {
                if (!ListenerUtil.mutListener.listen(14421)) {
                    AnalyticsTracker.track(Stat.PRIVACY_SETTINGS_REPORT_CRASHES_TOGGLED, Collections.singletonMap(TRACK_ENABLED, newValue));
                }
            }
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(14452)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(14451)) {
                        getActivity().finish();
                    }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void changeLanguage(String languageCode) {
        if (!ListenerUtil.mutListener.listen(14454)) {
            if ((ListenerUtil.mutListener.listen(14453) ? (mLanguagePreference == null && TextUtils.isEmpty(languageCode)) : (mLanguagePreference == null || TextUtils.isEmpty(languageCode)))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(14455)) {
            if (LocaleManager.isSameLanguage(languageCode)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(14456)) {
            LocaleManager.setNewLocale(WordPress.getContext(), languageCode);
        }
        if (!ListenerUtil.mutListener.listen(14457)) {
            WordPress.updateContextLocale();
        }
        if (!ListenerUtil.mutListener.listen(14458)) {
            mContextProvider.refreshContext();
        }
        // data in Tracks metadata.
        Map<String, Object> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(14459)) {
            properties.put("app_locale", Locale.getDefault());
        }
        if (!ListenerUtil.mutListener.listen(14460)) {
            AnalyticsTracker.track(Stat.ACCOUNT_SETTINGS_LANGUAGE_CHANGED, properties);
        }
        if (!ListenerUtil.mutListener.listen(14461)) {
            // Language is now part of metadata, so we need to refresh them
            AnalyticsUtils.refreshMetadata(mAccountStore, mSiteStore);
        }
        // Refresh the app
        Intent refresh = new Intent(getActivity(), getActivity().getClass());
        if (!ListenerUtil.mutListener.listen(14462)) {
            startActivity(refresh);
        }
        if (!ListenerUtil.mutListener.listen(14463)) {
            getActivity().setResult(LANGUAGE_CHANGED);
        }
        if (!ListenerUtil.mutListener.listen(14464)) {
            getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
        if (!ListenerUtil.mutListener.listen(14465)) {
            getActivity().finish();
        }
        if (!ListenerUtil.mutListener.listen(14466)) {
            // update Reader tags as they need be localized
            ReaderUpdateServiceStarter.startService(WordPress.getContext(), EnumSet.of(ReaderUpdateLogic.UpdateTask.TAGS));
        }
    }

    private boolean handleAboutPreferenceClick() {
        if (!ListenerUtil.mutListener.listen(14470)) {
            // Temporarily limiting this feature to the WordPress app
            if ((ListenerUtil.mutListener.listen(14467) ? (mUnifiedAboutFeatureConfig.isEnabled() || !BuildConfig.IS_JETPACK_APP) : (mUnifiedAboutFeatureConfig.isEnabled() && !BuildConfig.IS_JETPACK_APP))) {
                if (!ListenerUtil.mutListener.listen(14469)) {
                    startActivity(new Intent(getActivity(), UnifiedAboutActivity.class));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(14468)) {
                    startActivity(new Intent(getActivity(), AboutActivity.class));
                }
            }
        }
        return true;
    }

    private boolean handleDebugSettingsPreferenceClick() {
        if (!ListenerUtil.mutListener.listen(14471)) {
            startActivity(new Intent(getActivity(), DebugSettingsActivity.class));
        }
        return true;
    }

    private boolean handleDevicePreferenceClick() {
        try {
            // open specific app info screen
            Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            if (!ListenerUtil.mutListener.listen(14474)) {
                intent.setData(Uri.parse("package:" + getActivity().getPackageName()));
            }
            if (!ListenerUtil.mutListener.listen(14475)) {
                startActivity(intent);
            }
        } catch (ActivityNotFoundException exception) {
            if (!ListenerUtil.mutListener.listen(14472)) {
                AppLog.w(AppLog.T.SETTINGS, exception.getMessage());
            }
            // open generic apps screen
            Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
            if (!ListenerUtil.mutListener.listen(14473)) {
                startActivity(intent);
            }
        }
        if (!ListenerUtil.mutListener.listen(14476)) {
            AnalyticsTracker.track(Stat.APP_SETTINGS_OPEN_DEVICE_SETTINGS_TAPPED);
        }
        return true;
    }

    private boolean handleOssPreferenceClick() {
        if (!ListenerUtil.mutListener.listen(14477)) {
            startActivity(new Intent(getActivity(), LicensesActivity.class));
        }
        return true;
    }

    private String getLabelForImageMaxSizeValue(int newValue) {
        String[] values = getActivity().getResources().getStringArray(R.array.site_settings_image_max_size_values);
        String[] entries = getActivity().getResources().getStringArray(R.array.site_settings_image_max_size_entries);
        if (!ListenerUtil.mutListener.listen(14484)) {
            {
                long _loopCounter243 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(14483) ? (i >= values.length) : (ListenerUtil.mutListener.listen(14482) ? (i <= values.length) : (ListenerUtil.mutListener.listen(14481) ? (i > values.length) : (ListenerUtil.mutListener.listen(14480) ? (i != values.length) : (ListenerUtil.mutListener.listen(14479) ? (i == values.length) : (i < values.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter243", ++_loopCounter243);
                    if (!ListenerUtil.mutListener.listen(14478)) {
                        if (values[i].equals(String.valueOf(newValue))) {
                            return entries[i];
                        }
                    }
                }
            }
        }
        return entries[0];
    }

    private String getLabelForImageQualityValue(int newValue) {
        String[] values = getActivity().getResources().getStringArray(R.array.site_settings_image_quality_values);
        String[] entries = getActivity().getResources().getStringArray(R.array.site_settings_image_quality_entries);
        if (!ListenerUtil.mutListener.listen(14491)) {
            {
                long _loopCounter244 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(14490) ? (i >= values.length) : (ListenerUtil.mutListener.listen(14489) ? (i <= values.length) : (ListenerUtil.mutListener.listen(14488) ? (i > values.length) : (ListenerUtil.mutListener.listen(14487) ? (i != values.length) : (ListenerUtil.mutListener.listen(14486) ? (i == values.length) : (i < values.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter244", ++_loopCounter244);
                    if (!ListenerUtil.mutListener.listen(14485)) {
                        if (values[i].equals(String.valueOf(newValue))) {
                            return entries[i];
                        }
                    }
                }
            }
        }
        return entries[0];
    }

    private String getLabelForVideoMaxWidthValue(int newValue) {
        String[] values = getActivity().getResources().getStringArray(R.array.site_settings_video_width_values);
        String[] entries = getActivity().getResources().getStringArray(R.array.site_settings_video_width_entries);
        if (!ListenerUtil.mutListener.listen(14498)) {
            {
                long _loopCounter245 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(14497) ? (i >= values.length) : (ListenerUtil.mutListener.listen(14496) ? (i <= values.length) : (ListenerUtil.mutListener.listen(14495) ? (i > values.length) : (ListenerUtil.mutListener.listen(14494) ? (i != values.length) : (ListenerUtil.mutListener.listen(14493) ? (i == values.length) : (i < values.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter245", ++_loopCounter245);
                    if (!ListenerUtil.mutListener.listen(14492)) {
                        if (values[i].equals(String.valueOf(newValue))) {
                            return entries[i];
                        }
                    }
                }
            }
        }
        return entries[0];
    }

    private String getLabelForVideoEncoderBitrateValue(int newValue) {
        String[] values = getActivity().getResources().getStringArray(R.array.site_settings_video_bitrate_values);
        String[] entries = getActivity().getResources().getStringArray(R.array.site_settings_video_bitrate_entries);
        if (!ListenerUtil.mutListener.listen(14505)) {
            {
                long _loopCounter246 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(14504) ? (i >= values.length) : (ListenerUtil.mutListener.listen(14503) ? (i <= values.length) : (ListenerUtil.mutListener.listen(14502) ? (i > values.length) : (ListenerUtil.mutListener.listen(14501) ? (i != values.length) : (ListenerUtil.mutListener.listen(14500) ? (i == values.length) : (i < values.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter246", ++_loopCounter246);
                    if (!ListenerUtil.mutListener.listen(14499)) {
                        if (values[i].equals(String.valueOf(newValue))) {
                            return entries[i];
                        }
                    }
                }
            }
        }
        return entries[0];
    }

    private void setDetailListPreferenceValue(DetailListPreference pref, String value, String summary) {
        if (!ListenerUtil.mutListener.listen(14506)) {
            pref.setValue(value);
        }
        if (!ListenerUtil.mutListener.listen(14507)) {
            pref.setSummary(summary);
        }
        if (!ListenerUtil.mutListener.listen(14508)) {
            pref.refreshAdapter();
        }
    }

    private boolean handlePrivacyClick() {
        if (!ListenerUtil.mutListener.listen(14509)) {
            AnalyticsTracker.track(Stat.APP_SETTINGS_PRIVACY_SETTINGS_TAPPED);
        }
        if (!ListenerUtil.mutListener.listen(14511)) {
            if ((ListenerUtil.mutListener.listen(14510) ? (mPrivacySettings == null && !isAdded()) : (mPrivacySettings == null || !isAdded()))) {
                return false;
            }
        }
        String title = getString(R.string.preference_privacy_settings);
        Dialog dialog = mPrivacySettings.getDialog();
        if (!ListenerUtil.mutListener.listen(14513)) {
            if (dialog != null) {
                if (!ListenerUtil.mutListener.listen(14512)) {
                    WPActivityUtils.addToolbarToDialog(this, dialog, title);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14514)) {
            AnalyticsTracker.track(Stat.PRIVACY_SETTINGS_OPENED);
        }
        return true;
    }

    private boolean handleFeatureAnnouncementClick() {
        if (getActivity() instanceof AppCompatActivity) {
            if (!ListenerUtil.mutListener.listen(14515)) {
                AnalyticsTracker.track(Stat.FEATURE_ANNOUNCEMENT_SHOWN_FROM_APP_SETTINGS);
            }
            if (!ListenerUtil.mutListener.listen(14516)) {
                new FeatureAnnouncementDialogFragment().show(((AppCompatActivity) getActivity()).getSupportFragmentManager(), FeatureAnnouncementDialogFragment.TAG);
            }
            return true;
        } else {
            throw new IllegalArgumentException("Parent activity is not AppCompatActivity. FeatureAnnouncementDialogFragment must be called " + "using support fragment manager from AppCompatActivity.");
        }
    }

    private boolean handleAppLocalePickerClick() {
        if (getActivity() instanceof AppCompatActivity) {
            LocalePickerBottomSheet bottomSheet = LocalePickerBottomSheet.newInstance();
            if (!ListenerUtil.mutListener.listen(14517)) {
                bottomSheet.setLocalePickerCallback(this);
            }
            if (!ListenerUtil.mutListener.listen(14518)) {
                bottomSheet.show(((AppCompatActivity) getActivity()).getSupportFragmentManager(), LocalePickerBottomSheet.TAG);
            }
            return true;
        } else {
            throw new IllegalArgumentException("Parent activity is not AppCompatActivity. LocalePickerBottomSheet must be called " + "using support fragment manager from AppCompatActivity.");
        }
    }

    private void reattachLocalePickerCallback() {
        if (!ListenerUtil.mutListener.listen(14521)) {
            if (getActivity() instanceof AppCompatActivity) {
                LocalePickerBottomSheet bottomSheet = (LocalePickerBottomSheet) (((AppCompatActivity) getActivity())).getSupportFragmentManager().findFragmentByTag(LocalePickerBottomSheet.TAG);
                if (!ListenerUtil.mutListener.listen(14520)) {
                    if (bottomSheet != null) {
                        if (!ListenerUtil.mutListener.listen(14519)) {
                            bottomSheet.setLocalePickerCallback(this);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onLocaleSelected(@NotNull String languageCode) {
        if (!ListenerUtil.mutListener.listen(14522)) {
            onPreferenceChange(mLanguagePreference, languageCode);
        }
    }
}
