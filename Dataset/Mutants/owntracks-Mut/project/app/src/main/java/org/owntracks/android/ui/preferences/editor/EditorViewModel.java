package org.owntracks.android.ui.preferences.editor;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import org.owntracks.android.BR;
import org.owntracks.android.R;
import org.owntracks.android.data.repos.WaypointsRepo;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.model.messages.MessageConfiguration;
import org.owntracks.android.support.Parser;
import org.owntracks.android.support.Preferences;
import org.owntracks.android.ui.base.viewmodel.BaseViewModel;
import java.io.IOException;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@PerActivity
public class EditorViewModel extends BaseViewModel<EditorMvvm.View> implements EditorMvvm.ViewModel<EditorMvvm.View> {

    private final Parser parser;

    private final Preferences preferences;

    @Bindable
    private String effectiveConfiguration;

    @Inject
    WaypointsRepo waypointsRepo;

    @Inject
    public EditorViewModel(Preferences preferences, Parser parser) {
        this.preferences = preferences;
        this.parser = parser;
    }

    public void attachView(@Nullable Bundle savedInstanceState, @NonNull EditorMvvm.View view) {
        if (!ListenerUtil.mutListener.listen(2078)) {
            super.attachView(savedInstanceState, view);
        }
        if (!ListenerUtil.mutListener.listen(2079)) {
            updateEffectiveConfiguration();
        }
    }

    private void updateEffectiveConfiguration() {
        try {
            MessageConfiguration message = preferences.exportToMessage();
            if (!ListenerUtil.mutListener.listen(2081)) {
                message.setWaypoints(waypointsRepo.exportToMessage());
            }
            if (!ListenerUtil.mutListener.listen(2082)) {
                message.set(preferences.getPreferenceKey(R.string.preferenceKeyPassword), "********");
            }
            if (!ListenerUtil.mutListener.listen(2083)) {
                setEffectiveConfiguration(parser.toJsonPlainPretty(message));
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(2080)) {
                getView().displayLoadFailed();
            }
        }
    }

    @Bindable
    public String getEffectiveConfiguration() {
        return effectiveConfiguration;
    }

    @Bindable
    private void setEffectiveConfiguration(String effectiveConfiguration) {
        if (!ListenerUtil.mutListener.listen(2084)) {
            this.effectiveConfiguration = effectiveConfiguration;
        }
        if (!ListenerUtil.mutListener.listen(2085)) {
            notifyPropertyChanged(BR.effectiveConfiguration);
        }
    }

    @Override
    public void onPreferencesValueForKeySetSuccessful() {
        if (!ListenerUtil.mutListener.listen(2086)) {
            updateEffectiveConfiguration();
        }
        if (!ListenerUtil.mutListener.listen(2087)) {
            notifyPropertyChanged(BR.effectiveConfiguration);
        }
    }
}
