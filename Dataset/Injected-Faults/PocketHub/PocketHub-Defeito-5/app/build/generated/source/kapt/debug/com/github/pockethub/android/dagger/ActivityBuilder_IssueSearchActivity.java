package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.issue.IssueSearchActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(subcomponents = ActivityBuilder_IssueSearchActivity.IssueSearchActivitySubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ActivityBuilder_IssueSearchActivity {
  private ActivityBuilder_IssueSearchActivity() {}

  @Binds
  @IntoMap
  @ClassKey(IssueSearchActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      IssueSearchActivitySubcomponent.Factory builder);

  @Subcomponent(modules = IssueSearchFragmentProvider.class)
  public interface IssueSearchActivitySubcomponent extends AndroidInjector<IssueSearchActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<IssueSearchActivity> {}
  }
}
