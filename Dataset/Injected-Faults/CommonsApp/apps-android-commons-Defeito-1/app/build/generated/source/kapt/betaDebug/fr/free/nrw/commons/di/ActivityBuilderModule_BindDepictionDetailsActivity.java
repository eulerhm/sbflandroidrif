package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.explore.depictions.WikidataItemDetailsActivity;

@Module(
  subcomponents =
      ActivityBuilderModule_BindDepictionDetailsActivity.WikidataItemDetailsActivitySubcomponent
          .class
)
public abstract class ActivityBuilderModule_BindDepictionDetailsActivity {
  private ActivityBuilderModule_BindDepictionDetailsActivity() {}

  @Binds
  @IntoMap
  @ClassKey(WikidataItemDetailsActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      WikidataItemDetailsActivitySubcomponent.Factory builder);

  @Subcomponent
  public interface WikidataItemDetailsActivitySubcomponent
      extends AndroidInjector<WikidataItemDetailsActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<WikidataItemDetailsActivity> {}
  }
}
