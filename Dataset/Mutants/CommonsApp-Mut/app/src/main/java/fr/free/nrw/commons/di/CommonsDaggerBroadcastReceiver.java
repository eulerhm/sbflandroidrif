package fr.free.nrw.commons.di;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import dagger.android.AndroidInjector;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Receives broadcast then injects it's instance to the broadcastReceiverInjector method of
 * ApplicationlessInjection class
 */
public abstract class CommonsDaggerBroadcastReceiver extends BroadcastReceiver {

    public CommonsDaggerBroadcastReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!ListenerUtil.mutListener.listen(230)) {
            inject(context);
        }
    }

    private void inject(Context context) {
        ApplicationlessInjection injection = ApplicationlessInjection.getInstance(context.getApplicationContext());
        AndroidInjector<BroadcastReceiver> serviceInjector = injection.broadcastReceiverInjector();
        if (!ListenerUtil.mutListener.listen(231)) {
            if (serviceInjector == null) {
                throw new NullPointerException("ApplicationlessInjection.broadcastReceiverInjector() returned null");
            }
        }
        if (!ListenerUtil.mutListener.listen(232)) {
            serviceInjector.inject(this);
        }
    }
}
