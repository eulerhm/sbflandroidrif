package fr.free.nrw.commons.di;

import android.app.Activity;
import android.content.Context;
import androidx.fragment.app.Fragment;
import javax.inject.Inject;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import io.reactivex.disposables.CompositeDisposable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class CommonsDaggerSupportFragment extends Fragment implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> childFragmentInjector;

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onAttach(Context context) {
        if (!ListenerUtil.mutListener.listen(243)) {
            inject();
        }
        if (!ListenerUtil.mutListener.listen(244)) {
            super.onAttach(context);
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(245)) {
            super.onDestroy();
        }
        if (!ListenerUtil.mutListener.listen(246)) {
            compositeDisposable.clear();
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return childFragmentInjector;
    }

    public void inject() {
        HasSupportFragmentInjector hasSupportFragmentInjector = findHasFragmentInjector();
        AndroidInjector<Fragment> fragmentInjector = hasSupportFragmentInjector.supportFragmentInjector();
        if (!ListenerUtil.mutListener.listen(247)) {
            if (fragmentInjector == null) {
                throw new NullPointerException(String.format("%s.supportFragmentInjector() returned null", hasSupportFragmentInjector.getClass().getCanonicalName()));
            }
        }
        if (!ListenerUtil.mutListener.listen(248)) {
            fragmentInjector.inject(this);
        }
    }

    private HasSupportFragmentInjector findHasFragmentInjector() {
        Fragment parentFragment = this;
        {
            long _loopCounter5 = 0;
            while ((parentFragment = parentFragment.getParentFragment()) != null) {
                ListenerUtil.loopListener.listen("_loopCounter5", ++_loopCounter5);
                if (parentFragment instanceof HasSupportFragmentInjector) {
                    return (HasSupportFragmentInjector) parentFragment;
                }
            }
        }
        Activity activity = getActivity();
        if (activity instanceof HasSupportFragmentInjector) {
            return (HasSupportFragmentInjector) activity;
        }
        ApplicationlessInjection injection = ApplicationlessInjection.getInstance(activity.getApplicationContext());
        if (injection != null) {
            return injection;
        }
        throw new IllegalArgumentException(String.format("No injector was found for %s", getClass().getCanonicalName()));
    }
}
