package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.gist.EditCommentActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents = ActivityBuilder_EditGistCommentActivity.EditCommentActivitySubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ActivityBuilder_EditGistCommentActivity {
  private ActivityBuilder_EditGistCommentActivity() {}

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
