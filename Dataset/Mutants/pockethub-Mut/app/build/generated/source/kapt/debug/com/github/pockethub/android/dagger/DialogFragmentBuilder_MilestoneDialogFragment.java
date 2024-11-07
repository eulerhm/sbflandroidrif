package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.issue.MilestoneDialogFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        DialogFragmentBuilder_MilestoneDialogFragment.MilestoneDialogFragmentSubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class DialogFragmentBuilder_MilestoneDialogFragment {
  private DialogFragmentBuilder_MilestoneDialogFragment() {}

  @Binds
  @IntoMap
  @ClassKey(MilestoneDialogFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      MilestoneDialogFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface MilestoneDialogFragmentSubcomponent
      extends AndroidInjector<MilestoneDialogFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<MilestoneDialogFragment> {}
  }
}
