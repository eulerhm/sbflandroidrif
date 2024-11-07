package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.repo.RepositoryNewsFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        RepositoryViewFragmentProvider_RepositoryNewsFragment.RepositoryNewsFragmentSubcomponent
            .class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class RepositoryViewFragmentProvider_RepositoryNewsFragment {
  private RepositoryViewFragmentProvider_RepositoryNewsFragment() {}

  @Binds
  @IntoMap
  @ClassKey(RepositoryNewsFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      RepositoryNewsFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface RepositoryNewsFragmentSubcomponent
      extends AndroidInjector<RepositoryNewsFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<RepositoryNewsFragment> {}
  }
}
