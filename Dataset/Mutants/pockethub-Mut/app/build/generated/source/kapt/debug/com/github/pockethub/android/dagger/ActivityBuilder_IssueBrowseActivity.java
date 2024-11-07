package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.issue.IssueBrowseActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(subcomponents = ActivityBuilder_IssueBrowseActivity.IssueBrowseActivitySubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ActivityBuilder_IssueBrowseActivity {
  private ActivityBuilder_IssueBrowseActivity() {}

  @Binds
  @IntoMap
  @ClassKey(IssueBrowseActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      IssueBrowseActivitySubcomponent.Factory builder);

  @Subcomponent(modules = IssueBrowseFragmentProvider.class)
  public interface IssueBrowseActivitySubcomponent extends AndroidInjector<IssueBrowseActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<IssueBrowseActivity> {}
  }
}
