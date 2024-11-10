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
    topLevelClass = TileSetDownloadWorker.class
)
public interface TileSetDownloadWorker_HiltModule {
  @Binds
  @IntoMap
  @StringKey("com.google.android.gnd.persistence.sync.TileSetDownloadWorker")
  WorkerAssistedFactory<? extends ListenableWorker> bind(
      TileSetDownloadWorker_AssistedFactory factory);
}
