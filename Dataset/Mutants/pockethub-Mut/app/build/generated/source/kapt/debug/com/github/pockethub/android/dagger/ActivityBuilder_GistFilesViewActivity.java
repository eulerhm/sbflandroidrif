package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.gist.GistFilesViewActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents = ActivityBuilder_GistFilesViewActivity.GistFilesViewActivitySubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ActivityBuilder_GistFilesViewActivity {
  private ActivityBuilder_GistFilesViewActivity() {}

  @Binds
  @IntoMap
  @ClassKey(GistFilesViewActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      GistFilesViewActivitySubcomponent.Factory builder);

  @Subcomponent(modules = GistFilesViewFragmentProvider.class)
  public interface GistFilesViewActivitySubcomponent
      extends AndroidInjector<GistFilesViewActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<GistFilesViewActivity> {}
  }
}
