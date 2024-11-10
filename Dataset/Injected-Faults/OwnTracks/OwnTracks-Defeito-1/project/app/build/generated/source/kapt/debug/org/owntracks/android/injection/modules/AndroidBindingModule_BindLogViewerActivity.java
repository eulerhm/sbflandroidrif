package org.owntracks.android.injection.modules;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.ui.status.LogViewerActivity;
import org.owntracks.android.ui.status.LogViewerActivityModule;

@Module(
  subcomponents = AndroidBindingModule_BindLogViewerActivity.LogViewerActivitySubcomponent.class
)
public abstract class AndroidBindingModule_BindLogViewerActivity {
  private AndroidBindingModule_BindLogViewerActivity() {}

  @Binds
  @IntoMap
  @ClassKey(LogViewerActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      LogViewerActivitySubcomponent.Factory builder);

  @Subcomponent(modules = LogViewerActivityModule.class)
  @PerActivity
  public interface LogViewerActivitySubcomponent extends AndroidInjector<LogViewerActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<LogViewerActivity> {}
  }
}
