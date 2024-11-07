package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.issue.IssueDashboardPagerFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        MainFragmentProvider_IssueDashboardPagerFragment.IssueDashboardPagerFragmentSubcomponent
            .class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class MainFragmentProvider_IssueDashboardPagerFragment {
  private MainFragmentProvider_IssueDashboardPagerFragment() {}

  @Binds
  @IntoMap
  @ClassKey(IssueDashboardPagerFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      IssueDashboardPagerFragmentSubcomponent.Factory builder);

  @Subcomponent(modules = IssueDashboardPagerFragmentProvider.class)
  public interface IssueDashboardPagerFragmentSubcomponent
      extends AndroidInjector<IssueDashboardPagerFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<IssueDashboardPagerFragment> {}
  }
}
