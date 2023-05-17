package org.owntracks.android.ui.preferences.connection;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import org.greenrobot.eventbus.Subscribe;
import org.owntracks.android.injection.qualifier.AppContext;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.support.Events;
import org.owntracks.android.support.Preferences;
import org.owntracks.android.ui.base.viewmodel.BaseViewModel;
import org.owntracks.android.ui.preferences.connection.dialog.ConnectionHostHttpDialogViewModel;
import org.owntracks.android.ui.preferences.connection.dialog.ConnectionHostMqttDialogViewModel;
import org.owntracks.android.ui.preferences.connection.dialog.ConnectionIdentificationViewModel;
import org.owntracks.android.ui.preferences.connection.dialog.ConnectionModeDialogViewModel;
import org.owntracks.android.ui.preferences.connection.dialog.ConnectionParametersViewModel;
import org.owntracks.android.ui.preferences.connection.dialog.ConnectionSecurityViewModel;
import javax.inject.Inject;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@PerActivity
public class ConnectionViewModel extends BaseViewModel<ConnectionMvvm.View> implements ConnectionMvvm.ViewModel<ConnectionMvvm.View> {

    private final Preferences preferences;

    private final Context context;

    private int modeId;

    @Inject
    ConnectionViewModel(Preferences preferences, @AppContext Context context) {
        this.preferences = preferences;
        this.context = context;
    }

    public void attachView(@Nullable Bundle savedInstanceState, @NonNull ConnectionMvvm.View view) {
        if (!ListenerUtil.mutListener.listen(2032)) {
            super.attachView(savedInstanceState, view);
        }
        if (!ListenerUtil.mutListener.listen(2033)) {
            setModeId(preferences.getMode());
        }
    }

    @Override
    public void setModeId(int newModeId) {
        if (!ListenerUtil.mutListener.listen(2034)) {
            this.modeId = newModeId;
        }
    }

    @Bindable
    @Override
    public int getModeId() {
        return modeId;
    }

    @Subscribe
    public void onEvent(Events.ModeChanged e) {
        if (!ListenerUtil.mutListener.listen(2035)) {
            Timber.v("mode changed %s", e.getNewModeId());
        }
        if (!ListenerUtil.mutListener.listen(2036)) {
            setModeId(e.getNewModeId());
        }
        if (!ListenerUtil.mutListener.listen(2037)) {
            getView().recreateOptionsMenu();
        }
        if (!ListenerUtil.mutListener.listen(2038)) {
            notifyChange();
        }
    }

    @Override
    public void onModeClick() {
        if (!ListenerUtil.mutListener.listen(2039)) {
            getView().showModeDialog();
        }
    }

    @Override
    public void onHostClick() {
        if (!ListenerUtil.mutListener.listen(2040)) {
            getView().showHostDialog();
        }
    }

    @Override
    public void onIdentificationClick() {
        if (!ListenerUtil.mutListener.listen(2041)) {
            getView().showIdentificationDialog();
        }
    }

    @Override
    public void onSecurityClick() {
        if (!ListenerUtil.mutListener.listen(2042)) {
            getView().showSecurityDialog();
        }
    }

    @Override
    public void onParametersClick() {
        if (!ListenerUtil.mutListener.listen(2043)) {
            getView().showParametersDialog();
        }
    }

    @Override
    public ConnectionModeDialogViewModel getModeDialogViewModel() {
        return new ConnectionModeDialogViewModel(preferences);
    }

    @Override
    public ConnectionHostMqttDialogViewModel getHostDialogViewModelMqtt() {
        return new ConnectionHostMqttDialogViewModel(preferences);
    }

    @Override
    public ConnectionHostHttpDialogViewModel getHostDialogViewModelHttp() {
        return new ConnectionHostHttpDialogViewModel(preferences);
    }

    @Override
    public ConnectionIdentificationViewModel getIdentificationDialogViewModel() {
        return new ConnectionIdentificationViewModel(preferences);
    }

    @Override
    public ConnectionSecurityViewModel getConnectionSecurityViewModel() {
        return new ConnectionSecurityViewModel(preferences, navigator, context);
    }

    @Override
    public ConnectionParametersViewModel getConnectionParametersViewModel() {
        return new ConnectionParametersViewModel(preferences);
    }
}
