package org.owntracks.android.ui.preferences;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.owntracks.android.support.Preferences;
import org.owntracks.android.ui.base.viewmodel.BaseViewModel;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PreferencesFragmentViewModel extends BaseViewModel<PreferencesFragmentMvvm.View> implements PreferencesFragmentMvvm.ViewModel<PreferencesFragmentMvvm.View> {

    private final Preferences preferences;

    @Inject
    public PreferencesFragmentViewModel(Preferences preferences) {
        this.preferences = preferences;
    }

    public void attachView(@Nullable Bundle savedInstanceState, @NonNull PreferencesFragmentMvvm.View view) {
        if (!ListenerUtil.mutListener.listen(2205)) {
            super.attachView(savedInstanceState, view);
        }
        if (!ListenerUtil.mutListener.listen(2206)) {
            view.loadRoot();
        }
        if (!ListenerUtil.mutListener.listen(2207)) {
            view.setModeSummary(preferences.getMode());
        }
    }

    @Override
    public Preferences getPreferences() {
        return preferences;
    }
}
