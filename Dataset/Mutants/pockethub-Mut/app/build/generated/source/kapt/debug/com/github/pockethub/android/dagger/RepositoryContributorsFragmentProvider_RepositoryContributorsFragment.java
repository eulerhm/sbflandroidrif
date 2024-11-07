package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.repo.RepositoryContributorsFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        RepositoryContributorsFragmentProvider_RepositoryContributorsFragment
            .RepositoryContributorsFragmentSubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class RepositoryContributorsFragmentProvider_RepositoryContributorsFragment {
  private RepositoryContributorsFragmentProvider_RepositoryContributorsFragment() {}

  @Binds
  @IntoMap
  @ClassKey(RepositoryContributorsFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      RepositoryContributorsFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface RepositoryContributorsFragmentSubcomponent
      extends AndroidInjector<RepositoryContributorsFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<RepositoryContributorsFragment> {}
  }
}
