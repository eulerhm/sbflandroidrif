package org.wordpress.android;

import androidx.annotation.NonNull;
import javax.inject.Inject;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WordPressApp extends WordPress implements HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> mDispatchingAndroidInjector;

    @Inject
    AppInitializer mAppInitializer;

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(29551)) {
            super.onCreate();
        }
        if (!ListenerUtil.mutListener.listen(29552)) {
            mAppInitializer.init();
        }
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return mDispatchingAndroidInjector;
    }

    @NonNull
    @Override
    public AppInitializer initializer() {
        return mAppInitializer;
    }
}
