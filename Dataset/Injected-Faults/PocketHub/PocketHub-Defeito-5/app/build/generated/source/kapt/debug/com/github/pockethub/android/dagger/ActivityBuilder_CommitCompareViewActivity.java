package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.commit.CommitCompareViewActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        ActivityBuilder_CommitCompareViewActivity.CommitCompareViewActivitySubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ActivityBuilder_CommitCompareViewActivity {
  private ActivityBuilder_CommitCompareViewActivity() {}

  @Binds
  @IntoMap
  @ClassKey(CommitCompareViewActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      CommitCompareViewActivitySubcomponent.Factory builder);

  @Subcomponent(modules = CommitCompareViewFragmentProvider.class)
  public interface CommitCompareViewActivitySubcomponent
      extends AndroidInjector<CommitCompareViewActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<CommitCompareViewActivity> {}
  }
}
