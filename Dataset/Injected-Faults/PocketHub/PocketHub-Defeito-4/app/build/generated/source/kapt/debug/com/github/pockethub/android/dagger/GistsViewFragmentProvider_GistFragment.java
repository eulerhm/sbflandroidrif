package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.gist.GistFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(subcomponents = GistsViewFragmentProvider_GistFragment.GistFragmentSubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class GistsViewFragmentProvider_GistFragment {
  private GistsViewFragmentProvider_GistFragment() {}

  @Binds
  @IntoMap
  @ClassKey(GistFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      GistFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface GistFragmentSubcomponent extends AndroidInjector<GistFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<GistFragment> {}
  }
}
