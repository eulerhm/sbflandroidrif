package fr.free.nrw.commons.di;

import android.content.ContentProvider;
import dagger.android.AndroidInjector;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class CommonsDaggerContentProvider extends ContentProvider {

    public CommonsDaggerContentProvider() {
        super();
    }

    @Override
    public boolean onCreate() {
        if (!ListenerUtil.mutListener.listen(249)) {
            inject();
        }
        return true;
    }

    private void inject() {
        ApplicationlessInjection injection = ApplicationlessInjection.getInstance(getContext());
        AndroidInjector<ContentProvider> serviceInjector = injection.contentProviderInjector();
        if (!ListenerUtil.mutListener.listen(250)) {
            if (serviceInjector == null) {
                throw new NullPointerException("ApplicationlessInjection.contentProviderInjector() returned null");
            }
        }
        if (!ListenerUtil.mutListener.listen(251)) {
            serviceInjector.inject(this);
        }
    }
}
