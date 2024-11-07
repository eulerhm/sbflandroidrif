package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.gist.GistFileFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        GistFilesViewFragmentProvider_GistFileFragment.GistFileFragmentSubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class GistFilesViewFragmentProvider_GistFileFragment {
  private GistFilesViewFragmentProvider_GistFileFragment() {}

  @Binds
  @IntoMap
  @ClassKey(GistFileFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      GistFileFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface GistFileFragmentSubcomponent extends AndroidInjector<GistFileFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<GistFileFragment> {}
  }
}
