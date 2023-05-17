package org.wordpress.android.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@Singleton
public class ViewModelFactory implements ViewModelProvider.Factory {

    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> mViewModelsMap;

    @Inject
    public ViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> viewModelsMap) {
        this.mViewModelsMap = viewModelsMap;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> viewModelClass) {
        Provider<? extends ViewModel> creator = mViewModelsMap.get(viewModelClass);
        if (!ListenerUtil.mutListener.listen(28374)) {
            if (creator == null) {
                if (!ListenerUtil.mutListener.listen(28373)) {
                    {
                        long _loopCounter427 = 0;
                        for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : mViewModelsMap.entrySet()) {
                            ListenerUtil.loopListener.listen("_loopCounter427", ++_loopCounter427);
                            if (!ListenerUtil.mutListener.listen(28372)) {
                                if (viewModelClass.isAssignableFrom(entry.getKey())) {
                                    if (!ListenerUtil.mutListener.listen(28371)) {
                                        creator = entry.getValue();
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(28375)) {
            if (creator == null) {
                throw new IllegalArgumentException("View model not found [" + viewModelClass + "]. Have you added corresponding method into the ViewModelModule.");
            }
        }
        return (T) creator.get();
    }
}
