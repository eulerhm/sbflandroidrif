package org.wordpress.android.ui.reader.viewmodels;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.datasets.wrappers.ReaderPostTableWrapper;
import org.wordpress.android.ui.reader.ReaderFollowCommentsHandler;

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
public final class ConversationNotificationsViewModel_Factory implements Factory<ConversationNotificationsViewModel> {
  private final Provider<ReaderFollowCommentsHandler> followCommentsHandlerProvider;

  private final Provider<ReaderPostTableWrapper> readerPostTableWrapperProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public ConversationNotificationsViewModel_Factory(
      Provider<ReaderFollowCommentsHandler> followCommentsHandlerProvider,
      Provider<ReaderPostTableWrapper> readerPostTableWrapperProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.followCommentsHandlerProvider = followCommentsHandlerProvider;
    this.readerPostTableWrapperProvider = readerPostTableWrapperProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public ConversationNotificationsViewModel get() {
    return newInstance(followCommentsHandlerProvider.get(), readerPostTableWrapperProvider.get(), bgDispatcherProvider.get());
  }

  public static ConversationNotificationsViewModel_Factory create(
      Provider<ReaderFollowCommentsHandler> followCommentsHandlerProvider,
      Provider<ReaderPostTableWrapper> readerPostTableWrapperProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new ConversationNotificationsViewModel_Factory(followCommentsHandlerProvider, readerPostTableWrapperProvider, bgDispatcherProvider);
  }

  public static ConversationNotificationsViewModel newInstance(
      ReaderFollowCommentsHandler followCommentsHandler,
      ReaderPostTableWrapper readerPostTableWrapper, CoroutineDispatcher bgDispatcher) {
    return new ConversationNotificationsViewModel(followCommentsHandler, readerPostTableWrapper, bgDispatcher);
  }
}
