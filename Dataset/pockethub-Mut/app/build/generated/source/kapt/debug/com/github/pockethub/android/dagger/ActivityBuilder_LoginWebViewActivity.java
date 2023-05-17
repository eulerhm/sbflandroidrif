package com.github.pockethub.android.dagger;

import com.github.pockethub.android.accounts.LoginWebViewActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(subcomponents = ActivityBuilder_LoginWebViewActivity.LoginWebViewActivitySubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ActivityBuilder_LoginWebViewActivity {
  private ActivityBuilder_LoginWebViewActivity() {}

  @Binds
  @IntoMap
  @ClassKey(LoginWebViewActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      LoginWebViewActivitySubcomponent.Factory builder);

  @Subcomponent
  public interface LoginWebViewActivitySubcomponent extends AndroidInjector<LoginWebViewActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<LoginWebViewActivity> {}
  }
}
