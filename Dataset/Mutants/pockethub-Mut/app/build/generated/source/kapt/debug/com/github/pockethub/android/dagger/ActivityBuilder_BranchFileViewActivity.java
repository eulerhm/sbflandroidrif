package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.ref.BranchFileViewActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents = ActivityBuilder_BranchFileViewActivity.BranchFileViewActivitySubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ActivityBuilder_BranchFileViewActivity {
  private ActivityBuilder_BranchFileViewActivity() {}

  @Binds
  @IntoMap
  @ClassKey(BranchFileViewActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      BranchFileViewActivitySubcomponent.Factory builder);

  @Subcomponent
  public interface BranchFileViewActivitySubcomponent
      extends AndroidInjector<BranchFileViewActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<BranchFileViewActivity> {}
  }
}
