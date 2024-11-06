package fr.free.nrw.commons.di;

import android.app.IntentService;
import android.app.Service;
import dagger.android.AndroidInjector;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class CommonsDaggerIntentService extends IntentService {

    public CommonsDaggerIntentService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(237)) {
            inject();
        }
        if (!ListenerUtil.mutListener.listen(238)) {
            super.onCreate();
        }
    }

    private void inject() {
        ApplicationlessInjection injection = ApplicationlessInjection.getInstance(getApplicationContext());
        AndroidInjector<Service> serviceInjector = injection.serviceInjector();
        if (!ListenerUtil.mutListener.listen(239)) {
            if (serviceInjector == null) {
                throw new NullPointerException("ApplicationlessInjection.serviceInjector() returned null");
            }
        }
        if (!ListenerUtil.mutListener.listen(240)) {
            serviceInjector.inject(this);
        }
    }
}
