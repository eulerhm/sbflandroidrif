package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.user.UserViewActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(subcomponents = ActivityBuilder_UserViewActivity.UserViewActivitySubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ActivityBuilder_UserViewActivity {
  private ActivityBuilder_UserViewActivity() {}

  @Binds
  @IntoMap
  @ClassKey(UserViewActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      UserViewActivitySubcomponent.Factory builder);

  @Subcomponent(modules = UserViewFragmentProvider.class)
  public interface UserViewActivitySubcomponent extends AndroidInjector<UserViewActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<UserViewActivity> {}
  }
}
