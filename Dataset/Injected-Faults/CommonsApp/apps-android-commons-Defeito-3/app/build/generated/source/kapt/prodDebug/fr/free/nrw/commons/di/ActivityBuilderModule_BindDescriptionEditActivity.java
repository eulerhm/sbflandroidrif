package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.description.DescriptionEditActivity;

@Module(
  subcomponents =
      ActivityBuilderModule_BindDescriptionEditActivity.DescriptionEditActivitySubcomponent.class
)
public abstract class ActivityBuilderModule_BindDescriptionEditActivity {
  private ActivityBuilderModule_BindDescriptionEditActivity() {}

  @Binds
  @IntoMap
  @ClassKey(DescriptionEditActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      DescriptionEditActivitySubcomponent.Factory builder);

  @Subcomponent
  public interface DescriptionEditActivitySubcomponent
      extends AndroidInjector<DescriptionEditActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<DescriptionEditActivity> {}
  }
}
