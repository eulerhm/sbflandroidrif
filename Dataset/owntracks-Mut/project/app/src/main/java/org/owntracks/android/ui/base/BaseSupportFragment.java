package org.owntracks.android.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.CallSuper;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.owntracks.android.BR;
import org.owntracks.android.ui.base.navigator.Navigator;
import org.owntracks.android.ui.base.view.MvvmView;
import org.owntracks.android.ui.base.viewmodel.MvvmViewModel;
import javax.inject.Inject;
import dagger.android.support.DaggerFragment;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class BaseSupportFragment<B extends ViewDataBinding, V extends MvvmViewModel> extends DaggerFragment {

    protected B binding;

    @Inject
    protected V viewModel;

    @Inject
    protected Navigator navigator;

    /* Use this method to inflate the content view for your Fragment.  */
    protected final View setAndBindContentView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @LayoutRes int layoutResId, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1525)) {
            if (viewModel == null) {
                throw new IllegalStateException("viewModel must not be null and should be injected");
            }
        }
        if (!ListenerUtil.mutListener.listen(1526)) {
            binding = DataBindingUtil.inflate(inflater, layoutResId, container, false);
        }
        if (!ListenerUtil.mutListener.listen(1527)) {
            binding.setVariable(BR.vm, viewModel);
        }
        if (!ListenerUtil.mutListener.listen(1528)) {
            // noinspection unchecked
            viewModel.attachView(savedInstanceState, (MvvmView) this);
        }
        return binding.getRoot();
    }

    @Override
    @CallSuper
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(1529)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(1531)) {
            if (viewModel != null) {
                if (!ListenerUtil.mutListener.listen(1530)) {
                    viewModel.saveInstanceState(outState);
                }
            }
        }
    }

    @Override
    @CallSuper
    public void onDestroyView() {
        if (!ListenerUtil.mutListener.listen(1532)) {
            super.onDestroyView();
        }
        if (!ListenerUtil.mutListener.listen(1534)) {
            if (viewModel != null) {
                if (!ListenerUtil.mutListener.listen(1533)) {
                    viewModel.detachView();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1535)) {
            binding = null;
        }
        if (!ListenerUtil.mutListener.listen(1536)) {
            viewModel = null;
        }
    }
}
