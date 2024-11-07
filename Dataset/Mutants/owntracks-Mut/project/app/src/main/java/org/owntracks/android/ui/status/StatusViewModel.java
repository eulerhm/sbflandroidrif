package org.owntracks.android.ui.status;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import org.greenrobot.eventbus.Subscribe;
import org.owntracks.android.BR;
import org.owntracks.android.injection.qualifier.AppContext;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.services.MessageProcessor;
import org.owntracks.android.support.Events;
import org.owntracks.android.ui.base.viewmodel.BaseViewModel;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@PerActivity
public class StatusViewModel extends BaseViewModel<StatusMvvm.View> implements StatusMvvm.ViewModel<StatusMvvm.View> {

    private final Context context;

    private MessageProcessor.EndpointState endpointState;

    private String endpointMessage;

    private Date serviceStarted;

    private long locationUpdated;

    private int queueLength;

    @Inject
    public StatusViewModel(@AppContext Context context) {
        this.context = context;
    }

    public void attachView(@Nullable Bundle savedInstanceState, @NonNull StatusMvvm.View view) {
        if (!ListenerUtil.mutListener.listen(2277)) {
            super.attachView(savedInstanceState, view);
        }
    }

    @Override
    @Bindable
    public MessageProcessor.EndpointState getEndpointState() {
        return endpointState;
    }

    @Override
    @Bindable
    public String getEndpointMessage() {
        return endpointMessage;
    }

    @Override
    @Bindable
    public int getEndpointQueue() {
        return queueLength;
    }

    @Override
    @Bindable
    public Date getServiceStarted() {
        return serviceStarted;
    }

    @Override
    public boolean getDozeWhitelisted() {
        return (ListenerUtil.mutListener.listen(2283) ? ((ListenerUtil.mutListener.listen(2282) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(2281) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(2280) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(2279) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(2278) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)))))) && ((PowerManager) context.getApplicationContext().getSystemService(Context.POWER_SERVICE)).isIgnoringBatteryOptimizations(context.getApplicationContext().getPackageName())) : ((ListenerUtil.mutListener.listen(2282) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(2281) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(2280) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(2279) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(2278) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)))))) || ((PowerManager) context.getApplicationContext().getSystemService(Context.POWER_SERVICE)).isIgnoringBatteryOptimizations(context.getApplicationContext().getPackageName())));
    }

    @Override
    @Bindable
    public long getLocationUpdated() {
        return locationUpdated;
    }

    @Subscribe(sticky = true)
    public void onEvent(MessageProcessor.EndpointState e) {
        if (!ListenerUtil.mutListener.listen(2284)) {
            this.endpointState = e;
        }
        if (!ListenerUtil.mutListener.listen(2285)) {
            this.endpointMessage = e.getMessage();
        }
        if (!ListenerUtil.mutListener.listen(2286)) {
            notifyPropertyChanged(BR.endpointState);
        }
        if (!ListenerUtil.mutListener.listen(2287)) {
            notifyPropertyChanged(BR.endpointMessage);
        }
    }

    @Subscribe(sticky = true)
    public void onEvent(Events.ServiceStarted e) {
        if (!ListenerUtil.mutListener.listen(2288)) {
            this.serviceStarted = e.getDate();
        }
        if (!ListenerUtil.mutListener.listen(2289)) {
            notifyPropertyChanged(BR.serviceStarted);
        }
    }

    @Subscribe(sticky = true)
    public void onEvent(Location l) {
        if (!ListenerUtil.mutListener.listen(2290)) {
            this.locationUpdated = TimeUnit.MILLISECONDS.toSeconds(l.getTime());
        }
        if (!ListenerUtil.mutListener.listen(2291)) {
            notifyPropertyChanged(BR.locationUpdated);
        }
    }

    @Subscribe(sticky = true)
    public void onEvent(Events.QueueChanged e) {
        if (!ListenerUtil.mutListener.listen(2292)) {
            Timber.v("queue changed %s", e.getNewLength());
        }
        if (!ListenerUtil.mutListener.listen(2293)) {
            this.queueLength = e.getNewLength();
        }
        if (!ListenerUtil.mutListener.listen(2294)) {
            notifyPropertyChanged(BR.endpointQueue);
        }
    }

    public void viewLogs() {
        Intent intent = new Intent(context, LogViewerActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (!ListenerUtil.mutListener.listen(2295)) {
            context.startActivity(intent);
        }
    }
}
