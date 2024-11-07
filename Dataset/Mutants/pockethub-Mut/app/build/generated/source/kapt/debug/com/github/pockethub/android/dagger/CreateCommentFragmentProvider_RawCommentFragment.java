package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.comment.RawCommentFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        CreateCommentFragmentProvider_RawCommentFragment.RawCommentFragmentSubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class CreateCommentFragmentProvider_RawCommentFragment {
  private CreateCommentFragmentProvider_RawCommentFragment() {}

  @Binds
  @IntoMap
  @ClassKey(RawCommentFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      RawCommentFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface RawCommentFragmentSubcomponent extends AndroidInjector<RawCommentFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<RawCommentFragment> {}
  }
}
