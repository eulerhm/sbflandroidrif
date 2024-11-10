package org.wordpress.android.ui.posts;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.PostSchedulingNotificationStore;
import org.wordpress.android.fluxc.store.PostStore;
import org.wordpress.android.viewmodel.ResourceProvider;

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
public final class PublishNotificationReceiverViewModel_Factory implements Factory<PublishNotificationReceiverViewModel> {
  private final Provider<PostSchedulingNotificationStore> postSchedulingNotificationStoreProvider;

  private final Provider<PostStore> postStoreProvider;

  private final Provider<ResourceProvider> resourceProvider;

  public PublishNotificationReceiverViewModel_Factory(
      Provider<PostSchedulingNotificationStore> postSchedulingNotificationStoreProvider,
      Provider<PostStore> postStoreProvider, Provider<ResourceProvider> resourceProvider) {
    this.postSchedulingNotificationStoreProvider = postSchedulingNotificationStoreProvider;
    this.postStoreProvider = postStoreProvider;
    this.resourceProvider = resourceProvider;
  }

  @Override
  public PublishNotificationReceiverViewModel get() {
    return newInstance(postSchedulingNotificationStoreProvider.get(), postStoreProvider.get(), resourceProvider.get());
  }

  public static PublishNotificationReceiverViewModel_Factory create(
      Provider<PostSchedulingNotificationStore> postSchedulingNotificationStoreProvider,
      Provider<PostStore> postStoreProvider, Provider<ResourceProvider> resourceProvider) {
    return new PublishNotificationReceiverViewModel_Factory(postSchedulingNotificationStoreProvider, postStoreProvider, resourceProvider);
  }

  public static PublishNotificationReceiverViewModel newInstance(
      PostSchedulingNotificationStore postSchedulingNotificationStore, PostStore postStore,
      ResourceProvider resourceProvider) {
    return new PublishNotificationReceiverViewModel(postSchedulingNotificationStore, postStore, resourceProvider);
  }
}
