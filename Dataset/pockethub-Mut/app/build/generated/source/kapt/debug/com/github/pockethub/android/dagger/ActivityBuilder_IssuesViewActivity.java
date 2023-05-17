package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.issue.IssuesViewActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(subcomponents = ActivityBuilder_IssuesViewActivity.IssuesViewActivitySubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ActivityBuilder_IssuesViewActivity {
  private ActivityBuilder_IssuesViewActivity() {}

  @Binds
  @IntoMap
  @ClassKey(IssuesViewActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      IssuesViewActivitySubcomponent.Factory builder);

  @Subcomponent(modules = IssuesViewFragmentProvider.class)
  public interface IssuesViewActivitySubcomponent extends AndroidInjector<IssuesViewActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<IssuesViewActivity> {}
  }
}
