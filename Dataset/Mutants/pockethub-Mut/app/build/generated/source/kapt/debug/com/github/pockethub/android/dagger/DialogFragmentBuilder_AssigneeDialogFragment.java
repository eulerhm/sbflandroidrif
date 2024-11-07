package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.issue.AssigneeDialogFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        DialogFragmentBuilder_AssigneeDialogFragment.AssigneeDialogFragmentSubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class DialogFragmentBuilder_AssigneeDialogFragment {
  private DialogFragmentBuilder_AssigneeDialogFragment() {}

  @Binds
  @IntoMap
  @ClassKey(AssigneeDialogFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      AssigneeDialogFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface AssigneeDialogFragmentSubcomponent
      extends AndroidInjector<AssigneeDialogFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<AssigneeDialogFragment> {}
  }
}
