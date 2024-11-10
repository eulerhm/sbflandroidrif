package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.issue.FilterListFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        FiltersViewFragmentProvider_FilterListFragment.FilterListFragmentSubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class FiltersViewFragmentProvider_FilterListFragment {
  private FiltersViewFragmentProvider_FilterListFragment() {}

  @Binds
  @IntoMap
  @ClassKey(FilterListFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      FilterListFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface FilterListFragmentSubcomponent extends AndroidInjector<FilterListFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<FilterListFragment> {}
  }
}
