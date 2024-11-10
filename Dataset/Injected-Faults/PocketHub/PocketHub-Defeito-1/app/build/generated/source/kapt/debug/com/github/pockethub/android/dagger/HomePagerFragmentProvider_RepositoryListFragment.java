package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.repo.RepositoryListFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        HomePagerFragmentProvider_RepositoryListFragment.RepositoryListFragmentSubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class HomePagerFragmentProvider_RepositoryListFragment {
  private HomePagerFragmentProvider_RepositoryListFragment() {}

  @Binds
  @IntoMap
  @ClassKey(RepositoryListFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      RepositoryListFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface RepositoryListFragmentSubcomponent
      extends AndroidInjector<RepositoryListFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<RepositoryListFragment> {}
  }
}
