package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.gist.GistsViewActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(subcomponents = ActivityBuilder_GistsViewActivity.GistsViewActivitySubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ActivityBuilder_GistsViewActivity {
  private ActivityBuilder_GistsViewActivity() {}

  @Binds
  @IntoMap
  @ClassKey(GistsViewActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      GistsViewActivitySubcomponent.Factory builder);

  @Subcomponent(modules = GistsViewFragmentProvider.class)
  public interface GistsViewActivitySubcomponent extends AndroidInjector<GistsViewActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<GistsViewActivity> {}
  }
}
