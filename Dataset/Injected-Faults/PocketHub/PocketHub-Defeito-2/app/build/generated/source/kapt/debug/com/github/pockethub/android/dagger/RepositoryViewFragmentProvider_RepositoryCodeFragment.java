package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.code.RepositoryCodeFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        RepositoryViewFragmentProvider_RepositoryCodeFragment.RepositoryCodeFragmentSubcomponent
            .class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class RepositoryViewFragmentProvider_RepositoryCodeFragment {
  private RepositoryViewFragmentProvider_RepositoryCodeFragment() {}

  @Binds
  @IntoMap
  @ClassKey(RepositoryCodeFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      RepositoryCodeFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface RepositoryCodeFragmentSubcomponent
      extends AndroidInjector<RepositoryCodeFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<RepositoryCodeFragment> {}
  }
}
