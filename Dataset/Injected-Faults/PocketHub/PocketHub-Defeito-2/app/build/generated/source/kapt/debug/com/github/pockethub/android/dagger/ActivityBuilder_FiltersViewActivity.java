package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.issue.FiltersViewActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(subcomponents = ActivityBuilder_FiltersViewActivity.FiltersViewActivitySubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ActivityBuilder_FiltersViewActivity {
  private ActivityBuilder_FiltersViewActivity() {}

  @Binds
  @IntoMap
  @ClassKey(FiltersViewActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      FiltersViewActivitySubcomponent.Factory builder);

  @Subcomponent(modules = FiltersViewFragmentProvider.class)
  public interface FiltersViewActivitySubcomponent extends AndroidInjector<FiltersViewActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<FiltersViewActivity> {}
  }
}
