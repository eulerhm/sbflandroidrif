package org.wordpress.android.ui.stats.refresh.lists.detail;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityRetainedComponent;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.internal.lifecycle.HiltViewModelMap;
import dagger.hilt.codegen.OriginatingElement;
import dagger.multibindings.IntoMap;
import dagger.multibindings.IntoSet;
import dagger.multibindings.StringKey;
import java.lang.String;
import javax.annotation.Generated;

@Generated("dagger.hilt.android.processor.internal.viewmodel.ViewModelProcessor")
@OriginatingElement(
    topLevelClass = InsightsDetailViewModel.class
)
public final class InsightsDetailViewModel_HiltModules {
  private InsightsDetailViewModel_HiltModules() {
  }

  @Module
  @InstallIn(ViewModelComponent.class)
  public abstract static class BindsModule {
    private BindsModule() {
    }

    @Binds
    @IntoMap
    @StringKey("org.wordpress.android.ui.stats.refresh.lists.detail.InsightsDetailViewModel")
    @HiltViewModelMap
    public abstract ViewModel binds(InsightsDetailViewModel vm);
  }

  @Module
  @InstallIn(ActivityRetainedComponent.class)
  public static final class KeyModule {
    private KeyModule() {
    }

    @Provides
    @IntoSet
    @HiltViewModelMap.KeySet
    public static String provide() {
      return "org.wordpress.android.ui.stats.refresh.lists.detail.InsightsDetailViewModel";
    }
  }
}
