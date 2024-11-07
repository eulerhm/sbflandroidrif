package org.wordpress.android.ui.posts;

import android.os.Bundle;
import android.os.Handler;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import androidx.annotation.Nullable;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.ui.prefs.SiteSettingsInterface;
import org.wordpress.android.ui.prefs.WPSwitchPreference;
import org.wordpress.android.util.WPPrefUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@SuppressWarnings("deprecation")
public class JetpackSecuritySettingsFragment extends PreferenceFragment implements SiteSettingsInterface.SiteSettingsListener, Preference.OnPreferenceChangeListener {

    private static final long FETCH_DELAY = 1000;

    public SiteModel mSite;

    // Can interface with WP.com or WP.org
    public SiteSettingsInterface mSiteSettings;

    // Used to ensure that settings are only fetched once throughout the lifecycle of the fragment
    private boolean mShouldFetch;

    // Jetpack settings
    private WPSwitchPreference mJpMonitorActivePref;

    private WPSwitchPreference mJpMonitorEmailNotesPref;

    private WPSwitchPreference mJpMonitorWpNotesPref;

    private WPSwitchPreference mJpBruteForcePref;

    private WPSwitchPreference mJpSsoPref;

    private WPSwitchPreference mJpMatchEmailPref;

    private WPSwitchPreference mJpUseTwoFactorPref;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(12673)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(12674)) {
            addPreferencesFromResource(R.xml.jetpack_settings);
        }
        if (!ListenerUtil.mutListener.listen(12675)) {
            mSite = (SiteModel) getActivity().getIntent().getSerializableExtra(WordPress.SITE);
        }
        if (!ListenerUtil.mutListener.listen(12678)) {
            if (mSite != null) {
                if (!ListenerUtil.mutListener.listen(12676)) {
                    // setup state to fetch remote settings
                    mShouldFetch = true;
                }
                if (!ListenerUtil.mutListener.listen(12677)) {
                    // initialize the appropriate settings interface (WP.com or WP.org)
                    mSiteSettings = SiteSettingsInterface.getInterface(getActivity(), mSite, this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12679)) {
            // toggle which preferences are shown and set references
            initPreferences();
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(12680)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(12681)) {
            // always load cached settings
            mSiteSettings.init(false);
        }
        if (!ListenerUtil.mutListener.listen(12684)) {
            if (mShouldFetch) {
                if (!ListenerUtil.mutListener.listen(12682)) {
                    new Handler().postDelayed(() -> {
                        // initialize settings with locally cached values, fetch remote on first pass
                        mSiteSettings.init(true);
                    }, FETCH_DELAY);
                }
                if (!ListenerUtil.mutListener.listen(12683)) {
                    // stop future calls from fetching remote settings
                    mShouldFetch = false;
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(12686)) {
            if (mSiteSettings != null) {
                if (!ListenerUtil.mutListener.listen(12685)) {
                    mSiteSettings.clear();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12687)) {
            super.onDestroy();
        }
    }

    // SiteSettingsListener
    @Override
    public void onSaveError(Exception error) {
    }

    @Override
    public void onFetchError(Exception error) {
    }

    @Override
    public void onSettingsUpdated() {
        if (!ListenerUtil.mutListener.listen(12689)) {
            if (isAdded()) {
                if (!ListenerUtil.mutListener.listen(12688)) {
                    setPreferencesFromSiteSettings();
                }
            }
        }
    }

    @Override
    public void onSettingsSaved() {
    }

    @Override
    public void onCredentialsValidated(Exception error) {
    }

    /**
     * Helper method to retrieve {@link Preference} references and initialize any data.
     */
    public void initPreferences() {
        if (!ListenerUtil.mutListener.listen(12690)) {
            mJpMonitorActivePref = (WPSwitchPreference) getChangePref(R.string.pref_key_jetpack_monitor_uptime);
        }
        if (!ListenerUtil.mutListener.listen(12691)) {
            mJpMonitorEmailNotesPref = (WPSwitchPreference) getChangePref(R.string.pref_key_jetpack_send_email_notifications);
        }
        if (!ListenerUtil.mutListener.listen(12692)) {
            mJpMonitorWpNotesPref = (WPSwitchPreference) getChangePref(R.string.pref_key_jetpack_send_wp_notifications);
        }
        if (!ListenerUtil.mutListener.listen(12693)) {
            mJpSsoPref = (WPSwitchPreference) getChangePref(R.string.pref_key_jetpack_allow_wpcom_sign_in);
        }
        if (!ListenerUtil.mutListener.listen(12694)) {
            mJpBruteForcePref = (WPSwitchPreference) getChangePref(R.string.pref_key_jetpack_prevent_brute_force);
        }
        if (!ListenerUtil.mutListener.listen(12695)) {
            mJpMatchEmailPref = (WPSwitchPreference) getChangePref(R.string.pref_key_jetpack_match_via_email);
        }
        if (!ListenerUtil.mutListener.listen(12696)) {
            mJpUseTwoFactorPref = (WPSwitchPreference) getChangePref(R.string.pref_key_jetpack_require_two_factor);
        }
    }

    public void setPreferencesFromSiteSettings() {
        if (!ListenerUtil.mutListener.listen(12697)) {
            mJpMonitorActivePref.setChecked(mSiteSettings.isJetpackMonitorEnabled());
        }
        if (!ListenerUtil.mutListener.listen(12698)) {
            mJpMonitorEmailNotesPref.setChecked(mSiteSettings.shouldSendJetpackMonitorEmailNotifications());
        }
        if (!ListenerUtil.mutListener.listen(12699)) {
            mJpMonitorWpNotesPref.setChecked(mSiteSettings.shouldSendJetpackMonitorWpNotifications());
        }
        if (!ListenerUtil.mutListener.listen(12700)) {
            mJpBruteForcePref.setChecked(mSiteSettings.isJetpackProtectEnabled());
        }
        if (!ListenerUtil.mutListener.listen(12701)) {
            mJpSsoPref.setChecked(mSiteSettings.isJetpackSsoEnabled());
        }
        if (!ListenerUtil.mutListener.listen(12702)) {
            mJpMatchEmailPref.setChecked(mSiteSettings.isJetpackSsoMatchEmailEnabled());
        }
        if (!ListenerUtil.mutListener.listen(12703)) {
            mJpUseTwoFactorPref.setChecked(mSiteSettings.isJetpackSsoTwoFactorEnabled());
        }
    }

    private Preference getChangePref(int id) {
        return WPPrefUtils.getPrefAndSetChangeListener(this, id, this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (!ListenerUtil.mutListener.listen(12704)) {
            if (newValue == null) {
                return false;
            }
        }
        Boolean prefBool = (Boolean) newValue;
        if (!ListenerUtil.mutListener.listen(12719)) {
            if (preference == mJpMonitorActivePref) {
                if (!ListenerUtil.mutListener.listen(12717)) {
                    mJpMonitorActivePref.setChecked(prefBool);
                }
                if (!ListenerUtil.mutListener.listen(12718)) {
                    mSiteSettings.enableJetpackMonitor(prefBool);
                }
            } else if (preference == mJpMonitorEmailNotesPref) {
                if (!ListenerUtil.mutListener.listen(12715)) {
                    mJpMonitorEmailNotesPref.setChecked(prefBool);
                }
                if (!ListenerUtil.mutListener.listen(12716)) {
                    mSiteSettings.enableJetpackMonitorEmailNotifications(prefBool);
                }
            } else if (preference == mJpMonitorWpNotesPref) {
                if (!ListenerUtil.mutListener.listen(12713)) {
                    mJpMonitorWpNotesPref.setChecked(prefBool);
                }
                if (!ListenerUtil.mutListener.listen(12714)) {
                    mSiteSettings.enableJetpackMonitorWpNotifications(prefBool);
                }
            } else if (preference == mJpBruteForcePref) {
                if (!ListenerUtil.mutListener.listen(12711)) {
                    mJpBruteForcePref.setChecked(prefBool);
                }
                if (!ListenerUtil.mutListener.listen(12712)) {
                    mSiteSettings.enableJetpackProtect(prefBool);
                }
            } else if (preference == mJpSsoPref) {
                if (!ListenerUtil.mutListener.listen(12709)) {
                    mJpSsoPref.setChecked(prefBool);
                }
                if (!ListenerUtil.mutListener.listen(12710)) {
                    mSiteSettings.enableJetpackSso(prefBool);
                }
            } else if (preference == mJpMatchEmailPref) {
                if (!ListenerUtil.mutListener.listen(12707)) {
                    mJpMatchEmailPref.setChecked(prefBool);
                }
                if (!ListenerUtil.mutListener.listen(12708)) {
                    mSiteSettings.enableJetpackSsoMatchEmail(prefBool);
                }
            } else if (preference == mJpUseTwoFactorPref) {
                if (!ListenerUtil.mutListener.listen(12705)) {
                    mJpUseTwoFactorPref.setChecked(prefBool);
                }
                if (!ListenerUtil.mutListener.listen(12706)) {
                    mSiteSettings.enableJetpackSsoTwoFactor(prefBool);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12720)) {
            mSiteSettings.saveSettings();
        }
        return true;
    }
}
