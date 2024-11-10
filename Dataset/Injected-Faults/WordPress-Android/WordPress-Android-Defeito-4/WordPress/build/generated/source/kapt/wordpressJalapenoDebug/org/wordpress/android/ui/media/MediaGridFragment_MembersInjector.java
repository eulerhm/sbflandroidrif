package org.wordpress.android.ui.media;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.MediaStore;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository;
import org.wordpress.android.util.QuickStartUtilsWrapper;
import org.wordpress.android.util.SnackbarSequencer;

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
public final class MediaGridFragment_MembersInjector implements MembersInjector<MediaGridFragment> {
  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<MediaStore> mMediaStoreProvider;

  private final Provider<QuickStartRepository> mQuickStartRepositoryProvider;

  private final Provider<QuickStartUtilsWrapper> mQuickStartUtilsWrapperProvider;

  private final Provider<SnackbarSequencer> mSnackbarSequencerProvider;

  private final Provider<SelectedSiteRepository> mSelectedSiteRepositoryProvider;

  public MediaGridFragment_MembersInjector(Provider<Dispatcher> mDispatcherProvider,
      Provider<MediaStore> mMediaStoreProvider,
      Provider<QuickStartRepository> mQuickStartRepositoryProvider,
      Provider<QuickStartUtilsWrapper> mQuickStartUtilsWrapperProvider,
      Provider<SnackbarSequencer> mSnackbarSequencerProvider,
      Provider<SelectedSiteRepository> mSelectedSiteRepositoryProvider) {
    this.mDispatcherProvider = mDispatcherProvider;
    this.mMediaStoreProvider = mMediaStoreProvider;
    this.mQuickStartRepositoryProvider = mQuickStartRepositoryProvider;
    this.mQuickStartUtilsWrapperProvider = mQuickStartUtilsWrapperProvider;
    this.mSnackbarSequencerProvider = mSnackbarSequencerProvider;
    this.mSelectedSiteRepositoryProvider = mSelectedSiteRepositoryProvider;
  }

  public static MembersInjector<MediaGridFragment> create(Provider<Dispatcher> mDispatcherProvider,
      Provider<MediaStore> mMediaStoreProvider,
      Provider<QuickStartRepository> mQuickStartRepositoryProvider,
      Provider<QuickStartUtilsWrapper> mQuickStartUtilsWrapperProvider,
      Provider<SnackbarSequencer> mSnackbarSequencerProvider,
      Provider<SelectedSiteRepository> mSelectedSiteRepositoryProvider) {
    return new MediaGridFragment_MembersInjector(mDispatcherProvider, mMediaStoreProvider, mQuickStartRepositoryProvider, mQuickStartUtilsWrapperProvider, mSnackbarSequencerProvider, mSelectedSiteRepositoryProvider);
  }

  @Override
  public void injectMembers(MediaGridFragment instance) {
    injectMDispatcher(instance, mDispatcherProvider.get());
    injectMMediaStore(instance, mMediaStoreProvider.get());
    injectMQuickStartRepository(instance, mQuickStartRepositoryProvider.get());
    injectMQuickStartUtilsWrapper(instance, mQuickStartUtilsWrapperProvider.get());
    injectMSnackbarSequencer(instance, mSnackbarSequencerProvider.get());
    injectMSelectedSiteRepository(instance, mSelectedSiteRepositoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.media.MediaGridFragment.mDispatcher")
  public static void injectMDispatcher(MediaGridFragment instance, Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.media.MediaGridFragment.mMediaStore")
  public static void injectMMediaStore(MediaGridFragment instance, MediaStore mMediaStore) {
    instance.mMediaStore = mMediaStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.media.MediaGridFragment.mQuickStartRepository")
  public static void injectMQuickStartRepository(MediaGridFragment instance,
      QuickStartRepository mQuickStartRepository) {
    instance.mQuickStartRepository = mQuickStartRepository;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.media.MediaGridFragment.mQuickStartUtilsWrapper")
  public static void injectMQuickStartUtilsWrapper(MediaGridFragment instance,
      QuickStartUtilsWrapper mQuickStartUtilsWrapper) {
    instance.mQuickStartUtilsWrapper = mQuickStartUtilsWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.media.MediaGridFragment.mSnackbarSequencer")
  public static void injectMSnackbarSequencer(MediaGridFragment instance,
      SnackbarSequencer mSnackbarSequencer) {
    instance.mSnackbarSequencer = mSnackbarSequencer;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.media.MediaGridFragment.mSelectedSiteRepository")
  public static void injectMSelectedSiteRepository(MediaGridFragment instance,
      SelectedSiteRepository mSelectedSiteRepository) {
    instance.mSelectedSiteRepository = mSelectedSiteRepository;
  }
}
