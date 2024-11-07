package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.gist.CreateGistActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(subcomponents = ActivityBuilder_CreateGistActivity.CreateGistActivitySubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ActivityBuilder_CreateGistActivity {
  private ActivityBuilder_CreateGistActivity() {}

  @Binds
  @IntoMap
  @ClassKey(CreateGistActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      CreateGistActivitySubcomponent.Factory builder);

  @Subcomponent
  public interface CreateGistActivitySubcomponent extends AndroidInjector<CreateGistActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<CreateGistActivity> {}
  }
}
