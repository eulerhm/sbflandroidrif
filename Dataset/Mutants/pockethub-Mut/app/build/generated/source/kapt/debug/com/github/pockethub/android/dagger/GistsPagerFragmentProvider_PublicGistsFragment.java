package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.gist.PublicGistsFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        GistsPagerFragmentProvider_PublicGistsFragment.PublicGistsFragmentSubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class GistsPagerFragmentProvider_PublicGistsFragment {
  private GistsPagerFragmentProvider_PublicGistsFragment() {}

  @Binds
  @IntoMap
  @ClassKey(PublicGistsFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      PublicGistsFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface PublicGistsFragmentSubcomponent extends AndroidInjector<PublicGistsFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<PublicGistsFragment> {}
  }
}
