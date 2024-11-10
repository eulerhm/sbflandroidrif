package org.wordpress.android.ui.reader.repository.usecases.tags;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.reader.services.update.wrapper.ReaderUpdateServiceStarterWrapper;
import org.wordpress.android.util.EventBusWrapper;
import org.wordpress.android.util.NetworkUtilsWrapper;
import org.wordpress.android.viewmodel.ContextProvider;

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
public final class FetchFollowedTagsUseCase_Factory implements Factory<FetchFollowedTagsUseCase> {
  private final Provider<ContextProvider> contextProvider;

  private final Provider<EventBusWrapper> eventBusWrapperProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<ReaderUpdateServiceStarterWrapper> readerUpdateServiceStarterWrapperProvider;

  public FetchFollowedTagsUseCase_Factory(Provider<ContextProvider> contextProvider,
      Provider<EventBusWrapper> eventBusWrapperProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ReaderUpdateServiceStarterWrapper> readerUpdateServiceStarterWrapperProvider) {
    this.contextProvider = contextProvider;
    this.eventBusWrapperProvider = eventBusWrapperProvider;
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.readerUpdateServiceStarterWrapperProvider = readerUpdateServiceStarterWrapperProvider;
  }

  @Override
  public FetchFollowedTagsUseCase get() {
    return newInstance(contextProvider.get(), eventBusWrapperProvider.get(), networkUtilsWrapperProvider.get(), readerUpdateServiceStarterWrapperProvider.get());
  }

  public static FetchFollowedTagsUseCase_Factory create(Provider<ContextProvider> contextProvider,
      Provider<EventBusWrapper> eventBusWrapperProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ReaderUpdateServiceStarterWrapper> readerUpdateServiceStarterWrapperProvider) {
    return new FetchFollowedTagsUseCase_Factory(contextProvider, eventBusWrapperProvider, networkUtilsWrapperProvider, readerUpdateServiceStarterWrapperProvider);
  }

  public static FetchFollowedTagsUseCase newInstance(ContextProvider contextProvider,
      EventBusWrapper eventBusWrapper, NetworkUtilsWrapper networkUtilsWrapper,
      ReaderUpdateServiceStarterWrapper readerUpdateServiceStarterWrapper) {
    return new FetchFollowedTagsUseCase(contextProvider, eventBusWrapper, networkUtilsWrapper, readerUpdateServiceStarterWrapper);
  }
}
