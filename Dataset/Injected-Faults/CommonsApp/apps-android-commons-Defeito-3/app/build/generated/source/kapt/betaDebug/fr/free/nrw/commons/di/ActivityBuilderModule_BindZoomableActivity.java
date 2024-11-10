package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.media.ZoomableActivity;

@Module(
  subcomponents = ActivityBuilderModule_BindZoomableActivity.ZoomableActivitySubcomponent.class
)
public abstract class ActivityBuilderModule_BindZoomableActivity {
  private ActivityBuilderModule_BindZoomableActivity() {}

  @Binds
  @IntoMap
  @ClassKey(ZoomableActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      ZoomableActivitySubcomponent.Factory builder);

  @Subcomponent
  public interface ZoomableActivitySubcomponent extends AndroidInjector<ZoomableActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<ZoomableActivity> {}
  }
}
