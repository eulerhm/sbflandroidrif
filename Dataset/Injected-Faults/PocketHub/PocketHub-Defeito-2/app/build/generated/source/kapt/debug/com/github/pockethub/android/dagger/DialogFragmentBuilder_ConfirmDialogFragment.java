package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.ConfirmDialogFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        DialogFragmentBuilder_ConfirmDialogFragment.ConfirmDialogFragmentSubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class DialogFragmentBuilder_ConfirmDialogFragment {
  private DialogFragmentBuilder_ConfirmDialogFragment() {}

  @Binds
  @IntoMap
  @ClassKey(ConfirmDialogFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      ConfirmDialogFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface ConfirmDialogFragmentSubcomponent
      extends AndroidInjector<ConfirmDialogFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<ConfirmDialogFragment> {}
  }
}
