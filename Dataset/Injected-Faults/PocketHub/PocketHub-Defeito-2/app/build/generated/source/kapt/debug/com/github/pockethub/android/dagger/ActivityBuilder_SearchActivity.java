package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.search.SearchActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(subcomponents = ActivityBuilder_SearchActivity.SearchActivitySubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ActivityBuilder_SearchActivity {
  private ActivityBuilder_SearchActivity() {}

  @Binds
  @IntoMap
  @ClassKey(SearchActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      SearchActivitySubcomponent.Factory builder);

  @Subcomponent(modules = SearchActivityFragmentProvider.class)
  public interface SearchActivitySubcomponent extends AndroidInjector<SearchActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<SearchActivity> {}
  }
}
