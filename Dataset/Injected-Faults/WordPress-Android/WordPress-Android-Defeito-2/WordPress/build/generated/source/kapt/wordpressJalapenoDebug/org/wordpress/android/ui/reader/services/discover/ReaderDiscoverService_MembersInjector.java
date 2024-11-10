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
public final class ReaderDiscoverService_MembersInjector implements MembersInjector<ReaderDiscoverService> {
  private final Provider<CoroutineDispatcher> ioDispatcherProvider;

  public ReaderDiscoverService_MembersInjector(Provider<CoroutineDispatcher> ioDispatcherProvider) {
    this.ioDispatcherProvider = ioDispatcherProvider;
  }

  public static MembersInjector<ReaderDiscoverService> create(
      Provider<CoroutineDispatcher> ioDispatcherProvider) {
    return new ReaderDiscoverService_MembersInjector(ioDispatcherProvider);
  }

  @Override
  public void injectMembers(ReaderDiscoverService instance) {
    injectIoDispatcher(instance, ioDispatcherProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.services.discover.ReaderDiscoverService.ioDispatcher")
  @Named("IO_THREAD")
  public static void injectIoDispatcher(ReaderDiscoverService instance,
      CoroutineDispatcher ioDispatcher) {
    instance.ioDispatcher = ioDispatcher;
  }
}
