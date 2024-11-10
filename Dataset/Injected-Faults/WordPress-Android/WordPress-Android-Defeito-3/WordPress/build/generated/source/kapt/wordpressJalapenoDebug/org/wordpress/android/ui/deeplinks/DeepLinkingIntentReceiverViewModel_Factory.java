package org.wordpress.android.ui.deeplinks;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.ui.deeplinks.handlers.DeepLinkHandlers;
import org.wordpress.android.ui.deeplinks.handlers.ServerTrackingHandler;
import org.wordpress.android.util.analytics.AnalyticsUtilsWrapper;

@ScopeMetadata
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
public final class DeepLinkingIntentReceiverViewModel_Factory implements Factory<DeepLinkingIntentReceiverViewModel> {
  private final Provider<CoroutineDispatcher> uiDispatcherProvider;

  private final Provider<DeepLinkHandlers> deepLinkHandlersProvider;

  private final Provider<DeepLinkUriUtils> deepLinkUriUtilsProvider;

  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<ServerTrackingHandler> serverTrackingHandlerProvider;

  private final Provider<DeepLinkTrackingUtils> deepLinkTrackingUtilsProvider;

  private final Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider;

  public DeepLinkingIntentReceiverViewModel_Factory(
      Provider<CoroutineDispatcher> uiDispatcherProvider,
      Provider<DeepLinkHandlers> deepLinkHandlersProvider,
      Provider<DeepLinkUriUtils> deepLinkUriUtilsProvider,
      Provider<AccountStore> accountStoreProvider,
      Provider<ServerTrackingHandler> serverTrackingHandlerProvider,
      Provider<DeepLinkTrackingUtils> deepLinkTrackingUtilsProvider,
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider) {
    this.uiDispatcherProvider = uiDispatcherProvider;
    this.deepLinkHandlersProvider = deepLinkHandlersProvider;
    this.deepLinkUriUtilsProvider = deepLinkUriUtilsProvider;
    this.accountStoreProvider = accountStoreProvider;
    this.serverTrackingHandlerProvider = serverTrackingHandlerProvider;
    this.deepLinkTrackingUtilsProvider = deepLinkTrackingUtilsProvider;
    this.analyticsUtilsWrapperProvider = analyticsUtilsWrapperProvider;
  }

  @Override
  public DeepLinkingIntentReceiverViewModel get() {
    return newInstance(uiDispatcherProvider.get(), deepLinkHandlersProvider.get(), deepLinkUriUtilsProvider.get(), accountStoreProvider.get(), serverTrackingHandlerProvider.get(), deepLinkTrackingUtilsProvider.get(), analyticsUtilsWrapperProvider.get());
  }

  public static DeepLinkingIntentReceiverViewModel_Factory create(
      Provider<CoroutineDispatcher> uiDispatcherProvider,
      Provider<DeepLinkHandlers> deepLinkHandlersProvider,
      Provider<DeepLinkUriUtils> deepLinkUriUtilsProvider,
      Provider<AccountStore> accountStoreProvider,
      Provider<ServerTrackingHandler> serverTrackingHandlerProvider,
      Provider<DeepLinkTrackingUtils> deepLinkTrackingUtilsProvider,
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider) {
    return new DeepLinkingIntentReceiverViewModel_Factory(uiDispatcherProvider, deepLinkHandlersProvider, deepLinkUriUtilsProvider, accountStoreProvider, serverTrackingHandlerProvider, deepLinkTrackingUtilsProvider, analyticsUtilsWrapperProvider);
  }

  public static DeepLinkingIntentReceiverViewModel newInstance(CoroutineDispatcher uiDispatcher,
      DeepLinkHandlers deepLinkHandlers, DeepLinkUriUtils deepLinkUriUtils,
      AccountStore accountStore, ServerTrackingHandler serverTrackingHandler,
      DeepLinkTrackingUtils deepLinkTrackingUtils, AnalyticsUtilsWrapper analyticsUtilsWrapper) {
    return new DeepLinkingIntentReceiverViewModel(uiDispatcher, deepLinkHandlers, deepLinkUriUtils, accountStore, serverTrackingHandler, deepLinkTrackingUtils, analyticsUtilsWrapper);
  }
}
