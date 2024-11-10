package org.wordpress.android.ui.reader.services.discover;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Named;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;

@QualifierMetadata("javax.inject.Named")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ReaderDiscoverJobService_MembersInjector implements MembersInjector<ReaderDiscoverJobService> {
  private final Provider<CoroutineDispatcher> ioDispatcherProvider;

  public ReaderDiscoverJobService_MembersInjector(
      Provider<CoroutineDispatcher> ioDispatcherProvider) {
    this.ioDispatcherProvider = ioDispatcherProvider;
  }

  public static MembersInjector<ReaderDiscoverJobService> create(
      Provider<CoroutineDispatcher> ioDispatcherProvider) {
    return new ReaderDiscoverJobService_MembersInjector(ioDispatcherProvider);
  }

  @Override
  public void injectMembers(ReaderDiscoverJobService instance) {
    injectIoDispatcher(instance, ioDispatcherProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.services.discover.ReaderDiscoverJobService.ioDispatcher")
  @Named("IO_THREAD")
  public static void injectIoDispatcher(ReaderDiscoverJobService instance,
      CoroutineDispatcher ioDispatcher) {
    instance.ioDispatcher = ioDispatcher;
  }
}
