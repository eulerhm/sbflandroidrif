// Generated by Dagger (https://google.github.io/dagger).
package fr.free.nrw.commons.explore.categories.sub;

import dagger.internal.Factory;
import io.reactivex.Scheduler;
import javax.inject.Provider;

public final class SubCategoriesPresenterImpl_Factory implements Factory<SubCategoriesPresenterImpl> {
  private final Provider<Scheduler> mainThreadSchedulerProvider;

  private final Provider<PageableSubCategoriesDataSource> dataSourceFactoryProvider;

  public SubCategoriesPresenterImpl_Factory(Provider<Scheduler> mainThreadSchedulerProvider,
      Provider<PageableSubCategoriesDataSource> dataSourceFactoryProvider) {
    this.mainThreadSchedulerProvider = mainThreadSchedulerProvider;
    this.dataSourceFactoryProvider = dataSourceFactoryProvider;
  }

  @Override
  public SubCategoriesPresenterImpl get() {
    return new SubCategoriesPresenterImpl(mainThreadSchedulerProvider.get(), dataSourceFactoryProvider.get());
  }

  public static SubCategoriesPresenterImpl_Factory create(
      Provider<Scheduler> mainThreadSchedulerProvider,
      Provider<PageableSubCategoriesDataSource> dataSourceFactoryProvider) {
    return new SubCategoriesPresenterImpl_Factory(mainThreadSchedulerProvider, dataSourceFactoryProvider);
  }

  public static SubCategoriesPresenterImpl newInstance(Scheduler mainThreadScheduler,
      PageableSubCategoriesDataSource dataSourceFactory) {
    return new SubCategoriesPresenterImpl(mainThreadScheduler, dataSourceFactory);
  }
}
