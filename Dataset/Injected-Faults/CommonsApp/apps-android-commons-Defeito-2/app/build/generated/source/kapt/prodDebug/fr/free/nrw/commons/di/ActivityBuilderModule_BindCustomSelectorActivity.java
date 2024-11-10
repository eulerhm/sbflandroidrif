package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.customselector.ui.selector.CustomSelectorActivity;

@Module(
  subcomponents =
      ActivityBuilderModule_BindCustomSelectorActivity.CustomSelectorActivitySubcomponent.class
)
public abstract class ActivityBuilderModule_BindCustomSelectorActivity {
  private ActivityBuilderModule_BindCustomSelectorActivity() {}

  @Binds
  @IntoMap
  @ClassKey(CustomSelectorActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      CustomSelectorActivitySubcomponent.Factory builder);

  @Subcomponent
  public interface CustomSelectorActivitySubcomponent
      extends AndroidInjector<CustomSelectorActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<CustomSelectorActivity> {}
  }
}
