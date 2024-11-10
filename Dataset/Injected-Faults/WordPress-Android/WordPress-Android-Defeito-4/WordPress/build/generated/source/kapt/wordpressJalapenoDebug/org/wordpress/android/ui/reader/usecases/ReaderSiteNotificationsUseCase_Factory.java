package org.wordpress.android.ui.reader.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.datasets.ReaderBlogTableWrapper;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.util.NetworkUtilsWrapper;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ReaderSiteNotificationsUseCase_Factory implements Factory<ReaderSiteNotificationsUseCase> {
  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<ReaderTracker> readerTrackerProvider;

  private final Provider<ReaderBlogTableWrapper> readerBlogTableWrapperProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  public ReaderSiteNotificationsUseCase_Factory(Provider<Dispatcher> dispatcherProvider,
      Provider<ReaderTracker> readerTrackerProvider,
      Provider<ReaderBlogTableWrapper> readerBlogTableWrapperProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider) {
    this.dispatcherProvider = dispatcherProvider;
    this.readerTrackerProvider = readerTrackerProvider;
    this.readerBlogTableWrapperProvider = readerBlogTableWrapperProvider;
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
  }

  @Override
  public ReaderSiteNotificationsUseCase get() {
    return newInstance(dispatcherProvider.get(), readerTrackerProvider.get(), readerBlogTableWrapperProvider.get(), networkUtilsWrapperProvider.get());
  }

  public static ReaderSiteNotificationsUseCase_Factory create(
      Provider<Dispatcher> dispatcherProvider, Provider<ReaderTracker> readerTrackerProvider,
      Provider<ReaderBlogTableWrapper> readerBlogTableWrapperProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider) {
    return new ReaderSiteNotificationsUseCase_Factory(dispatcherProvider, readerTrackerProvider, readerBlogTableWrapperProvider, networkUtilsWrapperProvider);
  }

  public static ReaderSiteNotificationsUseCase newInstance(Dispatcher dispatcher,
      ReaderTracker readerTracker, ReaderBlogTableWrapper readerBlogTableWrapper,
      NetworkUtilsWrapper networkUtilsWrapper) {
    return new ReaderSiteNotificationsUseCase(dispatcher, readerTracker, readerBlogTableWrapper, networkUtilsWrapper);
  }
}
