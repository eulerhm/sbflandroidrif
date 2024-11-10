package org.wordpress.android.ui.deeplinks.handlers;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;

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
public final class DeepLinkHandlers_Factory implements Factory<DeepLinkHandlers> {
  private final Provider<EditorLinkHandler> editorLinkHandlerProvider;

  private final Provider<StatsLinkHandler> statsLinkHandlerProvider;

  private final Provider<StartLinkHandler> startLinkHandlerProvider;

  private final Provider<ReaderLinkHandler> readerLinkHandlerProvider;

  private final Provider<PagesLinkHandler> pagesLinkHandlerProvider;

  private final Provider<NotificationsLinkHandler> notificationsLinkHandlerProvider;

  private final Provider<QRCodeAuthLinkHandler> qrCodeAuthLinkHandlerProvider;

  public DeepLinkHandlers_Factory(Provider<EditorLinkHandler> editorLinkHandlerProvider,
      Provider<StatsLinkHandler> statsLinkHandlerProvider,
      Provider<StartLinkHandler> startLinkHandlerProvider,
      Provider<ReaderLinkHandler> readerLinkHandlerProvider,
      Provider<PagesLinkHandler> pagesLinkHandlerProvider,
      Provider<NotificationsLinkHandler> notificationsLinkHandlerProvider,
      Provider<QRCodeAuthLinkHandler> qrCodeAuthLinkHandlerProvider) {
    this.editorLinkHandlerProvider = editorLinkHandlerProvider;
    this.statsLinkHandlerProvider = statsLinkHandlerProvider;
    this.startLinkHandlerProvider = startLinkHandlerProvider;
    this.readerLinkHandlerProvider = readerLinkHandlerProvider;
    this.pagesLinkHandlerProvider = pagesLinkHandlerProvider;
    this.notificationsLinkHandlerProvider = notificationsLinkHandlerProvider;
    this.qrCodeAuthLinkHandlerProvider = qrCodeAuthLinkHandlerProvider;
  }

  @Override
  public DeepLinkHandlers get() {
    return newInstance(editorLinkHandlerProvider.get(), statsLinkHandlerProvider.get(), startLinkHandlerProvider.get(), readerLinkHandlerProvider.get(), pagesLinkHandlerProvider.get(), notificationsLinkHandlerProvider.get(), qrCodeAuthLinkHandlerProvider.get());
  }

  public static DeepLinkHandlers_Factory create(
      Provider<EditorLinkHandler> editorLinkHandlerProvider,
      Provider<StatsLinkHandler> statsLinkHandlerProvider,
      Provider<StartLinkHandler> startLinkHandlerProvider,
      Provider<ReaderLinkHandler> readerLinkHandlerProvider,
      Provider<PagesLinkHandler> pagesLinkHandlerProvider,
      Provider<NotificationsLinkHandler> notificationsLinkHandlerProvider,
      Provider<QRCodeAuthLinkHandler> qrCodeAuthLinkHandlerProvider) {
    return new DeepLinkHandlers_Factory(editorLinkHandlerProvider, statsLinkHandlerProvider, startLinkHandlerProvider, readerLinkHandlerProvider, pagesLinkHandlerProvider, notificationsLinkHandlerProvider, qrCodeAuthLinkHandlerProvider);
  }

  public static DeepLinkHandlers newInstance(EditorLinkHandler editorLinkHandler,
      StatsLinkHandler statsLinkHandler, StartLinkHandler startLinkHandler,
      ReaderLinkHandler readerLinkHandler, PagesLinkHandler pagesLinkHandler,
      NotificationsLinkHandler notificationsLinkHandler,
      QRCodeAuthLinkHandler qrCodeAuthLinkHandler) {
    return new DeepLinkHandlers(editorLinkHandler, statsLinkHandler, startLinkHandler, readerLinkHandler, pagesLinkHandler, notificationsLinkHandler, qrCodeAuthLinkHandler);
  }
}
