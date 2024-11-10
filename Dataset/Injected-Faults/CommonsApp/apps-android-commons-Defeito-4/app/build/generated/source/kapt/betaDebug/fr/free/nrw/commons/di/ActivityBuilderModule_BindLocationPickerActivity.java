package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.LocationPicker.LocationPickerActivity;

@Module(
  subcomponents =
      ActivityBuilderModule_BindLocationPickerActivity.LocationPickerActivitySubcomponent.class
)
public abstract class ActivityBuilderModule_BindLocationPickerActivity {
  private ActivityBuilderModule_BindLocationPickerActivity() {}

  @Binds
  @IntoMap
  @ClassKey(LocationPickerActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      LocationPickerActivitySubcomponent.Factory builder);

  @Subcomponent
  public interface LocationPickerActivitySubcomponent
      extends AndroidInjector<LocationPickerActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<LocationPickerActivity> {}
  }
}
