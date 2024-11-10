package org.wordpress.android.modules;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;
import org.wordpress.android.ui.CommentFullScreenDialogFragment;

@Module(
  subcomponents =
      ApplicationModule_ContributecommentFullScreenDialogFragment
          .CommentFullScreenDialogFragmentSubcomponent.class
)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ApplicationModule_ContributecommentFullScreenDialogFragment {
  private ApplicationModule_ContributecommentFullScreenDialogFragment() {}

  @Binds
  @IntoMap
  @ClassKey(CommentFullScreenDialogFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      CommentFullScreenDialogFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface CommentFullScreenDialogFragmentSubcomponent
      extends AndroidInjector<CommentFullScreenDialogFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<CommentFullScreenDialogFragment> {}
  }
}
