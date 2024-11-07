package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.repo.RepositoryReadmeFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        RepositoryViewFragmentProvider_RepositoryReadmeFragment.RepositoryReadmeFragmentSubcomponent
            .class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class RepositoryViewFragmentProvider_RepositoryReadmeFragment {
  private RepositoryViewFragmentProvider_RepositoryReadmeFragment() {}

  @Binds
  @IntoMap
  @ClassKey(RepositoryReadmeFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      RepositoryReadmeFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface RepositoryReadmeFragmentSubcomponent
      extends AndroidInjector<RepositoryReadmeFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<RepositoryReadmeFragment> {}
  }
}
