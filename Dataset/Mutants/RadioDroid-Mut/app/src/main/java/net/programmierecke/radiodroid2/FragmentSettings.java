package net.programmierecke.radiodroid2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.audiofx.AudioEffect;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.Preference.OnPreferenceClickListener;
import androidx.preference.PreferenceScreen;
import android.util.Log;
import android.widget.Toast;
import com.mikepenz.iconics.typeface.library.community.material.CommunityMaterial;
import com.mikepenz.iconics.typeface.library.googlematerial.GoogleMaterial;
import com.bytehamster.lib.preferencesearch.SearchConfiguration;
import com.bytehamster.lib.preferencesearch.SearchPreference;
import net.programmierecke.radiodroid2.interfaces.IApplicationSelected;
import net.programmierecke.radiodroid2.proxy.ProxySettingsDialog;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Vector;
import static net.programmierecke.radiodroid2.ActivityMain.FRAGMENT_FROM_BACKSTACK;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FragmentSettings extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener, IApplicationSelected, PreferenceFragmentCompat.OnPreferenceStartScreenCallback {

    @Override
    public Fragment getCallbackFragment() {
        return this;
    }

    public static FragmentSettings openNewSettingsSubFragment(ActivityMain activity, String key) {
        FragmentSettings f = new FragmentSettings();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(4713)) {
            args.putString(PreferenceFragmentCompat.ARG_PREFERENCE_ROOT, key);
        }
        if (!ListenerUtil.mutListener.listen(4714)) {
            f.setArguments(args);
        }
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        if (!ListenerUtil.mutListener.listen(4715)) {
            fragmentTransaction.replace(R.id.containerView, f).addToBackStack(String.valueOf(FRAGMENT_FROM_BACKSTACK)).commit();
        }
        return f;
    }

    @Override
    public boolean onPreferenceStartScreen(PreferenceFragmentCompat preferenceFragmentCompat, PreferenceScreen preferenceScreen) {
        if (!ListenerUtil.mutListener.listen(4716)) {
            openNewSettingsSubFragment((ActivityMain) getActivity(), preferenceScreen.getKey());
        }
        return true;
    }

    private boolean isToplevel() {
        return (ListenerUtil.mutListener.listen(4717) ? (getPreferenceScreen() == null && getPreferenceScreen().getKey().equals("pref_toplevel")) : (getPreferenceScreen() == null || getPreferenceScreen().getKey().equals("pref_toplevel")));
    }

    private void refreshToplevelIcons() {
        if (!ListenerUtil.mutListener.listen(4718)) {
            findPreference("shareapp_package").setSummary(getPreferenceManager().getSharedPreferences().getString("shareapp_package", ""));
        }
        if (!ListenerUtil.mutListener.listen(4719)) {
            findPreference("pref_category_ui").setIcon(Utils.IconicsIcon(getContext(), CommunityMaterial.Icon2.cmd_monitor));
        }
        if (!ListenerUtil.mutListener.listen(4720)) {
            findPreference("pref_category_startup").setIcon(Utils.IconicsIcon(getContext(), GoogleMaterial.Icon.gmd_flight_takeoff));
        }
        if (!ListenerUtil.mutListener.listen(4721)) {
            findPreference("pref_category_interaction").setIcon(Utils.IconicsIcon(getContext(), CommunityMaterial.Icon.cmd_gesture_tap));
        }
        if (!ListenerUtil.mutListener.listen(4722)) {
            findPreference("pref_category_player").setIcon(Utils.IconicsIcon(getContext(), CommunityMaterial.Icon2.cmd_play));
        }
        if (!ListenerUtil.mutListener.listen(4723)) {
            findPreference("pref_category_alarm").setIcon(Utils.IconicsIcon(getContext(), CommunityMaterial.Icon.cmd_clock_outline));
        }
        if (!ListenerUtil.mutListener.listen(4724)) {
            findPreference("pref_category_connectivity").setIcon(Utils.IconicsIcon(getContext(), GoogleMaterial.Icon.gmd_import_export));
        }
        if (!ListenerUtil.mutListener.listen(4725)) {
            findPreference("pref_category_recordings").setIcon(Utils.IconicsIcon(getContext(), CommunityMaterial.Icon2.cmd_record_rec));
        }
        if (!ListenerUtil.mutListener.listen(4726)) {
            findPreference("pref_category_mpd").setIcon(Utils.IconicsIcon(getContext(), CommunityMaterial.Icon2.cmd_speaker_wireless));
        }
        if (!ListenerUtil.mutListener.listen(4727)) {
            findPreference("pref_category_other").setIcon(Utils.IconicsIcon(getContext(), CommunityMaterial.Icon2.cmd_information_outline));
        }
    }

    private void refreshToolbar() {
        ActivityMain activity = (ActivityMain) getActivity();
        // findViewById(R.id.my_awesome_toolbar);
        final Toolbar myToolbar = activity.getToolbar();
        if (!ListenerUtil.mutListener.listen(4729)) {
            if ((ListenerUtil.mutListener.listen(4728) ? (myToolbar == null && getPreferenceScreen() == null) : (myToolbar == null || getPreferenceScreen() == null)))
                return;
        }
        if (!ListenerUtil.mutListener.listen(4730)) {
            myToolbar.setTitle(getPreferenceScreen().getTitle());
        }
        if (!ListenerUtil.mutListener.listen(4738)) {
            if (Utils.bottomNavigationEnabled(activity)) {
                if (!ListenerUtil.mutListener.listen(4737)) {
                    if (isToplevel()) {
                        if (!ListenerUtil.mutListener.listen(4734)) {
                            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                        }
                        if (!ListenerUtil.mutListener.listen(4735)) {
                            activity.getSupportActionBar().setDisplayShowHomeEnabled(false);
                        }
                        if (!ListenerUtil.mutListener.listen(4736)) {
                            myToolbar.setNavigationOnClickListener(v -> activity.onBackPressed());
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(4731)) {
                            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                        }
                        if (!ListenerUtil.mutListener.listen(4732)) {
                            activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
                        }
                        if (!ListenerUtil.mutListener.listen(4733)) {
                            myToolbar.setNavigationOnClickListener(v -> activity.onBackPressed());
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        if (!ListenerUtil.mutListener.listen(4739)) {
            setPreferencesFromResource(R.xml.preferences, s);
        }
        if (!ListenerUtil.mutListener.listen(4740)) {
            refreshToolbar();
        }
        if (!ListenerUtil.mutListener.listen(4768)) {
            if (s == null) {
                if (!ListenerUtil.mutListener.listen(4765)) {
                    refreshToplevelIcons();
                }
                SearchPreference searchPreference = (SearchPreference) findPreference("searchPreference");
                SearchConfiguration config = searchPreference.getSearchConfiguration();
                if (!ListenerUtil.mutListener.listen(4766)) {
                    config.setActivity((AppCompatActivity) getActivity());
                }
                if (!ListenerUtil.mutListener.listen(4767)) {
                    config.index(R.xml.preferences);
                }
            } else if (s.equals("pref_category_player")) {
                if (!ListenerUtil.mutListener.listen(4764)) {
                    findPreference("equalizer").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                        @Override
                        public boolean onPreferenceClick(Preference preference) {
                            Intent intent = new Intent(AudioEffect.ACTION_DISPLAY_AUDIO_EFFECT_CONTROL_PANEL);
                            if (!ListenerUtil.mutListener.listen(4763)) {
                                if (getContext().getPackageManager().resolveActivity(intent, 0) == null) {
                                    if (!ListenerUtil.mutListener.listen(4762)) {
                                        Toast.makeText(getContext(), R.string.error_no_equalizer_found, Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(4760)) {
                                        intent.putExtra(AudioEffect.EXTRA_CONTENT_TYPE, AudioEffect.CONTENT_TYPE_MUSIC);
                                    }
                                    if (!ListenerUtil.mutListener.listen(4761)) {
                                        startActivityForResult(intent, ActivityMain.LAUNCH_EQUALIZER_REQUEST);
                                    }
                                }
                            }
                            return false;
                        }
                    });
                }
            } else if (s.equals("pref_category_connectivity")) {
                if (!ListenerUtil.mutListener.listen(4751)) {
                    findPreference("settings_proxy").setOnPreferenceClickListener(new OnPreferenceClickListener() {

                        @Override
                        public boolean onPreferenceClick(Preference preference) {
                            ProxySettingsDialog proxySettingsDialog = new ProxySettingsDialog();
                            if (!ListenerUtil.mutListener.listen(4749)) {
                                proxySettingsDialog.setCancelable(true);
                            }
                            if (!ListenerUtil.mutListener.listen(4750)) {
                                proxySettingsDialog.show(getFragmentManager(), "");
                            }
                            return false;
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(4759)) {
                    if ((ListenerUtil.mutListener.listen(4756) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) : (ListenerUtil.mutListener.listen(4755) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) : (ListenerUtil.mutListener.listen(4754) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) : (ListenerUtil.mutListener.listen(4753) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.JELLY_BEAN) : (ListenerUtil.mutListener.listen(4752) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN))))))) {
                        if (!ListenerUtil.mutListener.listen(4757)) {
                            findPreference("settings_retry_timeout").setVisible(false);
                        }
                        if (!ListenerUtil.mutListener.listen(4758)) {
                            findPreference("settings_retry_delay").setVisible(false);
                        }
                    }
                }
            } else if (s.equals("pref_category_mpd")) {
                if (!ListenerUtil.mutListener.listen(4748)) {
                    findPreference("mpd_servers_viewer").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                        @Override
                        public boolean onPreferenceClick(Preference preference) {
                            RadioDroidApp radioDroidApp = (RadioDroidApp) requireActivity().getApplication();
                            if (!ListenerUtil.mutListener.listen(4747)) {
                                Utils.showMpdServersDialog(radioDroidApp, requireActivity().getSupportFragmentManager(), null);
                            }
                            return false;
                        }
                    });
                }
            } else if (s.equals("pref_category_other")) {
                if (!ListenerUtil.mutListener.listen(4743)) {
                    findPreference("show_statistics").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                        @Override
                        public boolean onPreferenceClick(Preference preference) {
                            if (!ListenerUtil.mutListener.listen(4741)) {
                                ((ActivityMain) getActivity()).getToolbar().setTitle(R.string.settings_statistics);
                            }
                            FragmentServerInfo f = new FragmentServerInfo();
                            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                            if (!ListenerUtil.mutListener.listen(4742)) {
                                fragmentTransaction.replace(R.id.containerView, f).addToBackStack(String.valueOf(FRAGMENT_FROM_BACKSTACK)).commit();
                            }
                            return false;
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(4746)) {
                    findPreference("show_about").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                        @Override
                        public boolean onPreferenceClick(Preference preference) {
                            if (!ListenerUtil.mutListener.listen(4744)) {
                                ((ActivityMain) getActivity()).getToolbar().setTitle(R.string.settings_about);
                            }
                            FragmentAbout f = new FragmentAbout();
                            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                            if (!ListenerUtil.mutListener.listen(4745)) {
                                fragmentTransaction.replace(R.id.containerView, f).addToBackStack(String.valueOf(FRAGMENT_FROM_BACKSTACK)).commit();
                            }
                            return false;
                        }
                    });
                }
            }
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(4769)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(4770)) {
            getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(4771)) {
            refreshToolbar();
        }
        if (!ListenerUtil.mutListener.listen(4773)) {
            if (isToplevel())
                if (!ListenerUtil.mutListener.listen(4772)) {
                    refreshToplevelIcons();
                }
        }
        if (!ListenerUtil.mutListener.listen(4775)) {
            if (findPreference("shareapp_package") != null)
                if (!ListenerUtil.mutListener.listen(4774)) {
                    findPreference("shareapp_package").setSummary(getPreferenceManager().getSharedPreferences().getString("shareapp_package", ""));
                }
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(4776)) {
            getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(4777)) {
            super.onPause();
        }
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (!ListenerUtil.mutListener.listen(4779)) {
            if (BuildConfig.DEBUG) {
                if (!ListenerUtil.mutListener.listen(4778)) {
                    Log.d("AAA", "changed key:" + key);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4783)) {
            if (key.equals("alarm_external")) {
                boolean active = sharedPreferences.getBoolean(key, false);
                if (!ListenerUtil.mutListener.listen(4782)) {
                    if (active) {
                        ApplicationSelectorDialog newFragment = new ApplicationSelectorDialog();
                        if (!ListenerUtil.mutListener.listen(4780)) {
                            newFragment.setCallback(this);
                        }
                        if (!ListenerUtil.mutListener.listen(4781)) {
                            newFragment.show(getActivity().getSupportFragmentManager(), "appPicker");
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4789)) {
            if ((ListenerUtil.mutListener.listen(4785) ? ((ListenerUtil.mutListener.listen(4784) ? (key.equals("theme_name") && key.equals("circular_icons")) : (key.equals("theme_name") || key.equals("circular_icons"))) && key.equals("bottom_navigation")) : ((ListenerUtil.mutListener.listen(4784) ? (key.equals("theme_name") && key.equals("circular_icons")) : (key.equals("theme_name") || key.equals("circular_icons"))) || key.equals("bottom_navigation")))) {
                if (!ListenerUtil.mutListener.listen(4787)) {
                    if (key.equals("circular_icons"))
                        if (!ListenerUtil.mutListener.listen(4786)) {
                            ((RadioDroidApp) getActivity().getApplication()).getFavouriteManager().updateShortcuts();
                        }
                }
                if (!ListenerUtil.mutListener.listen(4788)) {
                    getActivity().recreate();
                }
            }
        }
    }

    @Override
    public void onAppSelected(String packageName, String activityName) {
        if (!ListenerUtil.mutListener.listen(4791)) {
            if (BuildConfig.DEBUG) {
                if (!ListenerUtil.mutListener.listen(4790)) {
                    Log.d("SEL", "selected:" + packageName + "/" + activityName);
                }
            }
        }
        SharedPreferences.Editor ed = getPreferenceManager().getSharedPreferences().edit();
        if (!ListenerUtil.mutListener.listen(4792)) {
            ed.putString("shareapp_package", packageName);
        }
        if (!ListenerUtil.mutListener.listen(4793)) {
            ed.putString("shareapp_activity", activityName);
        }
        if (!ListenerUtil.mutListener.listen(4794)) {
            ed.commit();
        }
        if (!ListenerUtil.mutListener.listen(4795)) {
            findPreference("shareapp_package").setSummary(packageName);
        }
    }
}
