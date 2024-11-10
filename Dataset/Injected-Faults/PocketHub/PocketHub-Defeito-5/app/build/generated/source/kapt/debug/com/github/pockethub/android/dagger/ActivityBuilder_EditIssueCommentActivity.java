package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.issue.EditCommentActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents = ActivityBuilder_EditIssueCommentActivity.EditCommentActivitySubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ActivityBuilder_EditIssueCommentActivity {
  private ActivityBuilder_EditIssueCommentActivity() {}

  @Binds
  @IntoMap
  @ClassKey(EditCommentActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      EditCommentActivitySubcomponent.Factory builder);

  @Subcomponent(modules = CreateCommentFragmentProvider.class)
  public interface EditCommentActivitySubcomponent extends AndroidInjector<EditCommentActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<EditCommentActivity> {}
  }
}
