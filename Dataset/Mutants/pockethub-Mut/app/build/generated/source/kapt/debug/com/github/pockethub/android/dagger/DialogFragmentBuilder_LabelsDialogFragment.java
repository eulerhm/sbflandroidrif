package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.issue.LabelsDialogFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        DialogFragmentBuilder_LabelsDialogFragment.LabelsDialogFragmentSubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class DialogFragmentBuilder_LabelsDialogFragment {
  private DialogFragmentBuilder_LabelsDialogFragment() {}

  @Binds
  @IntoMap
  @ClassKey(LabelsDialogFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      LabelsDialogFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface LabelsDialogFragmentSubcomponent extends AndroidInjector<LabelsDialogFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<LabelsDialogFragment> {}
  }
}
