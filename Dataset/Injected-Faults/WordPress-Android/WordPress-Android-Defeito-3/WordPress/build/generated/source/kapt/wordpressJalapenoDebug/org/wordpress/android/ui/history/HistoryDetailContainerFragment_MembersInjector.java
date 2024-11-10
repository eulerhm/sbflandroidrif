package org.wordpress.android.ui.history;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.PostStore;
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
public final class HistoryDetailContainerFragment_MembersInjector implements MembersInjector<HistoryDetailContainerFragment> {
  private final Provider<ImageManager> mImageManagerProvider;

  private final Provider<PostStore> mPostStoreProvider;

  public HistoryDetailContainerFragment_MembersInjector(
      Provider<ImageManager> mImageManagerProvider, Provider<PostStore> mPostStoreProvider) {
    this.mImageManagerProvider = mImageManagerProvider;
    this.mPostStoreProvider = mPostStoreProvider;
  }

  public static MembersInjector<HistoryDetailContainerFragment> create(
      Provider<ImageManager> mImageManagerProvider, Provider<PostStore> mPostStoreProvider) {
    return new HistoryDetailContainerFragment_MembersInjector(mImageManagerProvider, mPostStoreProvider);
  }

  @Override
  public void injectMembers(HistoryDetailContainerFragment instance) {
    injectMImageManager(instance, mImageManagerProvider.get());
    injectMPostStore(instance, mPostStoreProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.history.HistoryDetailContainerFragment.mImageManager")
  public static void injectMImageManager(HistoryDetailContainerFragment instance,
      ImageManager mImageManager) {
    instance.mImageManager = mImageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.history.HistoryDetailContainerFragment.mPostStore")
  public static void injectMPostStore(HistoryDetailContainerFragment instance,
      PostStore mPostStore) {
    instance.mPostStore = mPostStore;
  }
}
