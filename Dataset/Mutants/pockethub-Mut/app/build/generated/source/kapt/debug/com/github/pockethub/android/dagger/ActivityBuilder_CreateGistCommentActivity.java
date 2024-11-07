package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.gist.CreateCommentActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        ActivityBuilder_CreateGistCommentActivity.CreateCommentActivitySubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ActivityBuilder_CreateGistCommentActivity {
  private ActivityBuilder_CreateGistCommentActivity() {}

  @Binds
  @IntoMap
  @ClassKey(CreateCommentActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      CreateCommentActivitySubcomponent.Factory builder);

  @Subcomponent(modules = CreateCommentFragmentProvider.class)
  public interface CreateCommentActivitySubcomponent
      extends AndroidInjector<CreateCommentActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<CreateCommentActivity> {}
  }
}
