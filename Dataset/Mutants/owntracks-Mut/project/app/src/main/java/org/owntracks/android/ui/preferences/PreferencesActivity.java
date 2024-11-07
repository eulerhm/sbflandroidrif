package org.owntracks.android.ui.preferences;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import org.owntracks.android.R;
import org.owntracks.android.databinding.UiPreferencesBinding;
import org.owntracks.android.ui.base.BaseActivity;
import org.owntracks.android.ui.base.view.MvvmView;
import org.owntracks.android.ui.base.viewmodel.NoOpViewModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PreferencesActivity extends BaseActivity<UiPreferencesBinding, NoOpViewModel> implements MvvmView, PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(2192)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(2193)) {
            setContentView(R.layout.ui_preferences);
        }
        if (!ListenerUtil.mutListener.listen(2194)) {
            bindAndAttachContentView(R.layout.ui_preferences, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(2195)) {
            setHasEventBus(false);
        }
        if (!ListenerUtil.mutListener.listen(2196)) {
            setSupportToolbar(this.binding.toolbar, true, true);
        }
        if (!ListenerUtil.mutListener.listen(2197)) {
            setDrawer(binding.toolbar);
        }
        if (!ListenerUtil.mutListener.listen(2198)) {
            getSupportFragmentManager().addOnBackStackChangedListener(() -> {
                if (getSupportFragmentManager().getFragments().isEmpty()) {
                    setToolbarTitle(getTitle());
                } else {
                    setToolbarTitle(((PreferenceFragmentCompat) getSupportFragmentManager().getFragments().get(0)).getPreferenceScreen().getTitle());
                }
            });
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new PreferencesFragment(), null);
        if (!ListenerUtil.mutListener.listen(2199)) {
            fragmentTransaction.commit();
        }
        if (!ListenerUtil.mutListener.listen(2200)) {
            getSupportFragmentManager().executePendingTransactions();
        }
    }

    private void setToolbarTitle(CharSequence text) {
        if (!ListenerUtil.mutListener.listen(2201)) {
            binding.toolbar.setTitle(text);
        }
    }

    @Override
    public boolean onPreferenceStartFragment(PreferenceFragmentCompat caller, Preference pref) {
        final Bundle args = pref.getExtras();
        final Fragment fragment = getSupportFragmentManager().getFragmentFactory().instantiate(getClassLoader(), pref.getFragment());
        if (!ListenerUtil.mutListener.listen(2202)) {
            fragment.setArguments(args);
        }
        if (!ListenerUtil.mutListener.listen(2203)) {
            fragment.setTargetFragment(caller, 0);
        }
        if (!ListenerUtil.mutListener.listen(2204)) {
            // Replace the existing Fragment with the new Fragment
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(pref.getKey()).commit();
        }
        return true;
    }
}
