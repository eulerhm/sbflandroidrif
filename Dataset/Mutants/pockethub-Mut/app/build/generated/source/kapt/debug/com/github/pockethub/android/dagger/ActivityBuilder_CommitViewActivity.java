package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.commit.CommitViewActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(subcomponents = ActivityBuilder_CommitViewActivity.CommitViewActivitySubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ActivityBuilder_CommitViewActivity {
  private ActivityBuilder_CommitViewActivity() {}

  @Binds
  @IntoMap
  @ClassKey(CommitViewActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      CommitViewActivitySubcomponent.Factory builder);

  @Subcomponent(modules = CommitViewFragmentProvider.class)
  public interface CommitViewActivitySubcomponent extends AndroidInjector<CommitViewActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<CommitViewActivity> {}
  }
}
