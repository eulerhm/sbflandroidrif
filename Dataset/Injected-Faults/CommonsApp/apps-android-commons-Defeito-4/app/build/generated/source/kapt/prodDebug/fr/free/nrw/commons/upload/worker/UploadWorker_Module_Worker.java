package fr.free.nrw.commons.upload.worker;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = UploadWorker_Module_Worker.UploadWorkerSubcomponent.class)
public abstract class UploadWorker_Module_Worker {
  private UploadWorker_Module_Worker() {}

  @Binds
  @IntoMap
  @ClassKey(UploadWorker.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      UploadWorkerSubcomponent.Factory builder);

  @Subcomponent
  public interface UploadWorkerSubcomponent extends AndroidInjector<UploadWorker> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<UploadWorker> {}
  }
}
