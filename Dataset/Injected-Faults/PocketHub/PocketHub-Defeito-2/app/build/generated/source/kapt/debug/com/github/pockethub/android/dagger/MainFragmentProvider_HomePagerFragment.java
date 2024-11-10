package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.user.HomePagerFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(subcomponents = MainFragmentProvider_HomePagerFragment.HomePagerFragmentSubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class MainFragmentProvider_HomePagerFragment {
  private MainFragmentProvider_HomePagerFragment() {}

  @Binds
  @IntoMap
  @ClassKey(HomePagerFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      HomePagerFragmentSubcomponent.Factory builder);

  @Subcomponent(modules = HomePagerFragmentProvider.class)
  public interface HomePagerFragmentSubcomponent extends AndroidInjector<HomePagerFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<HomePagerFragment> {}
  }
}
