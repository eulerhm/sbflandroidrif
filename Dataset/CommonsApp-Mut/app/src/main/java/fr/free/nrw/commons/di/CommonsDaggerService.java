package fr.free.nrw.commons.di;

import android.app.Service;
import dagger.android.AndroidInjector;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class CommonsDaggerService extends Service {

    public CommonsDaggerService() {
        super();
    }

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(252)) {
            inject();
        }
        if (!ListenerUtil.mutListener.listen(253)) {
            super.onCreate();
        }
    }

    private void inject() {
        ApplicationlessInjection injection = ApplicationlessInjection.getInstance(getApplicationContext());
        AndroidInjector<Service> serviceInjector = injection.serviceInjector();
        if (!ListenerUtil.mutListener.listen(254)) {
            if (serviceInjector == null) {
                throw new NullPointerException("ApplicationlessInjection.serviceInjector() returned null");
            }
        }
        if (!ListenerUtil.mutListener.listen(255)) {
            serviceInjector.inject(this);
        }
    }
}
