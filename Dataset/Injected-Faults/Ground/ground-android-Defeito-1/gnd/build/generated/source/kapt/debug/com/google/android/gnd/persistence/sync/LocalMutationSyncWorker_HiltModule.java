package com.google.android.gnd.persistence.sync;

import androidx.hilt.work.WorkerAssistedFactory;
import androidx.work.ListenableWorker;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

@Module
@InstallIn(SingletonComponent.class)
@OriginatingElement(
    topLevelClass = LocalMutationSyncWorker.class
)
public interface LocalMutationSyncWorker_HiltModule {
  @Binds
  @IntoMap
  @StringKey("com.google.android.gnd.persistence.sync.LocalMutationSyncWorker")
  WorkerAssistedFactory<? extends ListenableWorker> bind(
      LocalMutationSyncWorker_AssistedFactory factory);
}
