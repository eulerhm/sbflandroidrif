package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.commit.CommitFileViewActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents = ActivityBuilder_CommitFileViewActivity.CommitFileViewActivitySubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ActivityBuilder_CommitFileViewActivity {
  private ActivityBuilder_CommitFileViewActivity() {}

  @Binds
  @IntoMap
  @ClassKey(CommitFileViewActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      CommitFileViewActivitySubcomponent.Factory builder);

  @Subcomponent
  public interface CommitFileViewActivitySubcomponent
      extends AndroidInjector<CommitFileViewActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<CommitFileViewActivity> {}
  }
}
