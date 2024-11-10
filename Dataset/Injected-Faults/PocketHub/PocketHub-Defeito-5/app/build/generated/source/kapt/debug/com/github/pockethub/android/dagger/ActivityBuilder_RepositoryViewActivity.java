package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.repo.RepositoryViewActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents = ActivityBuilder_RepositoryViewActivity.RepositoryViewActivitySubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ActivityBuilder_RepositoryViewActivity {
  private ActivityBuilder_RepositoryViewActivity() {}

  @Binds
  @IntoMap
  @ClassKey(RepositoryViewActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      RepositoryViewActivitySubcomponent.Factory builder);

  @Subcomponent(modules = RepositoryViewFragmentProvider.class)
  public interface RepositoryViewActivitySubcomponent
      extends AndroidInjector<RepositoryViewActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<RepositoryViewActivity> {}
  }
}
