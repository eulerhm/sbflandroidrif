package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.gist.StarredGistsFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        GistsPagerFragmentProvider_StarredGistsFragment.StarredGistsFragmentSubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class GistsPagerFragmentProvider_StarredGistsFragment {
  private GistsPagerFragmentProvider_StarredGistsFragment() {}

  @Binds
  @IntoMap
  @ClassKey(StarredGistsFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      StarredGistsFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface StarredGistsFragmentSubcomponent extends AndroidInjector<StarredGistsFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<StarredGistsFragment> {}
  }
}
