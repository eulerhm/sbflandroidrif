package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.gist.GistsPagerFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents = MainFragmentProvider_GistsPagerFragment.GistsPagerFragmentSubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class MainFragmentProvider_GistsPagerFragment {
  private MainFragmentProvider_GistsPagerFragment() {}

  @Binds
  @IntoMap
  @ClassKey(GistsPagerFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      GistsPagerFragmentSubcomponent.Factory builder);

  @Subcomponent(modules = GistsPagerFragmentProvider.class)
  public interface GistsPagerFragmentSubcomponent extends AndroidInjector<GistsPagerFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<GistsPagerFragment> {}
  }
}
