// Generated by Dagger (https://google.github.io/dagger).
package fr.free.nrw.commons.explore.depictions.media;

import dagger.internal.Factory;
import io.reactivex.Scheduler;
import javax.inject.Provider;

public final class DepictedImagesPresenterImpl_Factory implements Factory<DepictedImagesPresenterImpl> {
  private final Provider<Scheduler> mainThreadSchedulerProvider;

  private final Provider<PageableDepictedMediaDataSource> dataSourceFactoryProvider;

  public DepictedImagesPresenterImpl_Factory(Provider<Scheduler> mainThreadSchedulerProvider,
      Provider<PageableDepictedMediaDataSource> dataSourceFactoryProvider) {
    this.mainThreadSchedulerProvider = mainThreadSchedulerProvider;
    this.dataSourceFactoryProvider = dataSourceFactoryProvider;
  }

  @Override
  public DepictedImagesPresenterImpl get() {
    return new DepictedImagesPresenterImpl(mainThreadSchedulerProvider.get(), dataSourceFactoryProvider.get());
  }

  public static DepictedImagesPresenterImpl_Factory create(
      Provider<Scheduler> mainThreadSchedulerProvider,
      Provider<PageableDepictedMediaDataSource> dataSourceFactoryProvider) {
    return new DepictedImagesPresenterImpl_Factory(mainThreadSchedulerProvider, dataSourceFactoryProvider);
  }

  public static DepictedImagesPresenterImpl newInstance(Scheduler mainThreadScheduler,
      PageableDepictedMediaDataSource dataSourceFactory) {
    return new DepictedImagesPresenterImpl(mainThreadScheduler, dataSourceFactory);
  }
}
