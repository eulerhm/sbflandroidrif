package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.explore.SearchActivity;

@Module(subcomponents = ActivityBuilderModule_BindSearchActivity.SearchActivitySubcomponent.class)
public abstract class ActivityBuilderModule_BindSearchActivity {
  private ActivityBuilderModule_BindSearchActivity() {}

  @Binds
  @IntoMap
  @ClassKey(SearchActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      SearchActivitySubcomponent.Factory builder);

  @Subcomponent
  public interface SearchActivitySubcomponent extends AndroidInjector<SearchActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<SearchActivity> {}
  }
}
