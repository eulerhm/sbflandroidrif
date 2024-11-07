package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.user.UriLauncherActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(subcomponents = ActivityBuilder_UriLauncherActivity.UriLauncherActivitySubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ActivityBuilder_UriLauncherActivity {
  private ActivityBuilder_UriLauncherActivity() {}

  @Binds
  @IntoMap
  @ClassKey(UriLauncherActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      UriLauncherActivitySubcomponent.Factory builder);

  @Subcomponent
  public interface UriLauncherActivitySubcomponent extends AndroidInjector<UriLauncherActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<UriLauncherActivity> {}
  }
}
