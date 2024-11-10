package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.issue.EditIssueActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(subcomponents = ActivityBuilder_EditIssueActivity.EditIssueActivitySubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ActivityBuilder_EditIssueActivity {
  private ActivityBuilder_EditIssueActivity() {}

  @Binds
  @IntoMap
  @ClassKey(EditIssueActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      EditIssueActivitySubcomponent.Factory builder);

  @Subcomponent
  public interface EditIssueActivitySubcomponent extends AndroidInjector<EditIssueActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<EditIssueActivity> {}
  }
}
