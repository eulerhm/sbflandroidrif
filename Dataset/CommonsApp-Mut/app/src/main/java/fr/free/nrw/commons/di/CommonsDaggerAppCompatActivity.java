package fr.free.nrw.commons.di;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import javax.inject.Inject;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class CommonsDaggerAppCompatActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> supportFragmentInjector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(233)) {
            inject();
        }
        if (!ListenerUtil.mutListener.listen(234)) {
            super.onCreate(savedInstanceState);
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return supportFragmentInjector;
    }

    /**
     * when this Activity is created it injects an instance of this class inside
     * activityInjector method of ApplicationlessInjection
     */
    private void inject() {
        ApplicationlessInjection injection = ApplicationlessInjection.getInstance(getApplicationContext());
        AndroidInjector<Activity> activityInjector = injection.activityInjector();
        if (!ListenerUtil.mutListener.listen(235)) {
            if (activityInjector == null) {
                throw new NullPointerException("ApplicationlessInjection.activityInjector() returned null");
            }
        }
        if (!ListenerUtil.mutListener.listen(236)) {
            activityInjector.inject(this);
        }
    }
}
