package org.wordpress.android.ui.reader.adapters;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.reader.utils.ThreadedCommentsUtils;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.image.ImageManager;

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
public final class CommentSnippetAdapter_MembersInjector implements MembersInjector<CommentSnippetAdapter> {
  private final Provider<ThreadedCommentsUtils> threadedCommentsUtilsProvider;

  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<ReaderTracker> readerTrackerProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  public CommentSnippetAdapter_MembersInjector(
      Provider<ThreadedCommentsUtils> threadedCommentsUtilsProvider,
      Provider<ImageManager> imageManagerProvider, Provider<AccountStore> accountStoreProvider,
      Provider<ReaderTracker> readerTrackerProvider, Provider<UiHelpers> uiHelpersProvider) {
    this.threadedCommentsUtilsProvider = threadedCommentsUtilsProvider;
    this.imageManagerProvider = imageManagerProvider;
    this.accountStoreProvider = accountStoreProvider;
    this.readerTrackerProvider = readerTrackerProvider;
    this.uiHelpersProvider = uiHelpersProvider;
  }

  public static MembersInjector<CommentSnippetAdapter> create(
      Provider<ThreadedCommentsUtils> threadedCommentsUtilsProvider,
      Provider<ImageManager> imageManagerProvider, Provider<AccountStore> accountStoreProvider,
      Provider<ReaderTracker> readerTrackerProvider, Provider<UiHelpers> uiHelpersProvider) {
    return new CommentSnippetAdapter_MembersInjector(threadedCommentsUtilsProvider, imageManagerProvider, accountStoreProvider, readerTrackerProvider, uiHelpersProvider);
  }

  @Override
  public void injectMembers(CommentSnippetAdapter instance) {
    injectThreadedCommentsUtils(instance, threadedCommentsUtilsProvider.get());
    injectImageManager(instance, imageManagerProvider.get());
    injectAccountStore(instance, accountStoreProvider.get());
    injectReaderTracker(instance, readerTrackerProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.adapters.CommentSnippetAdapter.threadedCommentsUtils")
  public static void injectThreadedCommentsUtils(CommentSnippetAdapter instance,
      ThreadedCommentsUtils threadedCommentsUtils) {
    instance.threadedCommentsUtils = threadedCommentsUtils;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.adapters.CommentSnippetAdapter.imageManager")
  public static void injectImageManager(CommentSnippetAdapter instance, ImageManager imageManager) {
    instance.imageManager = imageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.adapters.CommentSnippetAdapter.accountStore")
  public static void injectAccountStore(CommentSnippetAdapter instance, AccountStore accountStore) {
    instance.accountStore = accountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.adapters.CommentSnippetAdapter.readerTracker")
  public static void injectReaderTracker(CommentSnippetAdapter instance,
      ReaderTracker readerTracker) {
    instance.readerTracker = readerTracker;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.adapters.CommentSnippetAdapter.uiHelpers")
  public static void injectUiHelpers(CommentSnippetAdapter instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }
}
