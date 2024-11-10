package org.wordpress.android.modules;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;
import org.wordpress.android.ui.posts.BasicDialog;

@Module(subcomponents = ApplicationModule_ContributeBasicDialog.BasicDialogSubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ApplicationModule_ContributeBasicDialog {
  private ApplicationModule_ContributeBasicDialog() {}

  @Binds
  @IntoMap
  @ClassKey(BasicDialog.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      BasicDialogSubcomponent.Factory builder);

  @Subcomponent
  public interface BasicDialogSubcomponent extends AndroidInjector<BasicDialog> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<BasicDialog> {}
  }
}
