package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.repo.RepositoryContributorsActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        ActivityBuilder_RepositoryContributorsActivity.RepositoryContributorsActivitySubcomponent
            .class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ActivityBuilder_RepositoryContributorsActivity {
  private ActivityBuilder_RepositoryContributorsActivity() {}

  @Binds
  @IntoMap
  @ClassKey(RepositoryContributorsActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      RepositoryContributorsActivitySubcomponent.Factory builder);

  @Subcomponent(modules = RepositoryContributorsFragmentProvider.class)
  public interface RepositoryContributorsActivitySubcomponent
      extends AndroidInjector<RepositoryContributorsActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<RepositoryContributorsActivity> {}
  }
}
