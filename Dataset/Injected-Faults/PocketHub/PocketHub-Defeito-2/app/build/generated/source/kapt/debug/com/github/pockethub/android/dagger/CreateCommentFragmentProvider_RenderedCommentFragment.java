package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.comment.RenderedCommentFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        CreateCommentFragmentProvider_RenderedCommentFragment.RenderedCommentFragmentSubcomponent
            .class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class CreateCommentFragmentProvider_RenderedCommentFragment {
  private CreateCommentFragmentProvider_RenderedCommentFragment() {}

  @Binds
  @IntoMap
  @ClassKey(RenderedCommentFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      RenderedCommentFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface RenderedCommentFragmentSubcomponent
      extends AndroidInjector<RenderedCommentFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<RenderedCommentFragment> {}
  }
}
