package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.issue.EditIssuesFilterActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        ActivityBuilder_EditIssuesFilterActivity.EditIssuesFilterActivitySubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ActivityBuilder_EditIssuesFilterActivity {
  private ActivityBuilder_EditIssuesFilterActivity() {}

  @Binds
  @IntoMap
  @ClassKey(EditIssuesFilterActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      EditIssuesFilterActivitySubcomponent.Factory builder);

  @Subcomponent
  public interface EditIssuesFilterActivitySubcomponent
      extends AndroidInjector<EditIssuesFilterActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<EditIssuesFilterActivity> {}
  }
}
