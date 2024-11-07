package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.gist.MyGistsFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents = GistsPagerFragmentProvider_MyGistsFragment.MyGistsFragmentSubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class GistsPagerFragmentProvider_MyGistsFragment {
  private GistsPagerFragmentProvider_MyGistsFragment() {}

  @Binds
  @IntoMap
  @ClassKey(MyGistsFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      MyGistsFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface MyGistsFragmentSubcomponent extends AndroidInjector<MyGistsFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<MyGistsFragment> {}
  }
}
