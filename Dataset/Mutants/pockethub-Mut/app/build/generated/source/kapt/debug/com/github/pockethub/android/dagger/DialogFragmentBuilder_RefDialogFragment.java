package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.ref.RefDialogFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(subcomponents = DialogFragmentBuilder_RefDialogFragment.RefDialogFragmentSubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class DialogFragmentBuilder_RefDialogFragment {
  private DialogFragmentBuilder_RefDialogFragment() {}

  @Binds
  @IntoMap
  @ClassKey(RefDialogFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      RefDialogFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface RefDialogFragmentSubcomponent extends AndroidInjector<RefDialogFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<RefDialogFragment> {}
  }
}
