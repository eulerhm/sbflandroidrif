package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.upload.UploadActivity;

@Module(subcomponents = ActivityBuilderModule_BindUploadActivity.UploadActivitySubcomponent.class)
public abstract class ActivityBuilderModule_BindUploadActivity {
  private ActivityBuilderModule_BindUploadActivity() {}

  @Binds
  @IntoMap
  @ClassKey(UploadActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      UploadActivitySubcomponent.Factory builder);

  @Subcomponent
  public interface UploadActivitySubcomponent extends AndroidInjector<UploadActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<UploadActivity> {}
  }
}
