package org.wordpress.android.models.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.CommentsStore;
import org.wordpress.android.ui.comments.unified.UnrepliedCommentsUtils;
import org.wordpress.android.util.NetworkUtilsWrapper;
import org.wordpress.android.viewmodel.ResourceProvider;

@ScopeMetadata("dagger.Reusable")
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
public final class PaginateCommentsResourceProvider_Factory implements Factory<PaginateCommentsResourceProvider> {
  private final Provider<CommentsStore> commentsStoreProvider;

  private final Provider<UnrepliedCommentsUtils> unrepliedCommentsUtilsProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<ResourceProvider> resourceProvider;

  public PaginateCommentsResourceProvider_Factory(Provider<CommentsStore> commentsStoreProvider,
      Provider<UnrepliedCommentsUtils> unrepliedCommentsUtilsProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ResourceProvider> resourceProvider) {
    this.commentsStoreProvider = commentsStoreProvider;
    this.unrepliedCommentsUtilsProvider = unrepliedCommentsUtilsProvider;
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.resourceProvider = resourceProvider;
  }

  @Override
  public PaginateCommentsResourceProvider get() {
    return newInstance(commentsStoreProvider.get(), unrepliedCommentsUtilsProvider.get(), networkUtilsWrapperProvider.get(), resourceProvider.get());
  }

  public static PaginateCommentsResourceProvider_Factory create(
      Provider<CommentsStore> commentsStoreProvider,
      Provider<UnrepliedCommentsUtils> unrepliedCommentsUtilsProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ResourceProvider> resourceProvider) {
    return new PaginateCommentsResourceProvider_Factory(commentsStoreProvider, unrepliedCommentsUtilsProvider, networkUtilsWrapperProvider, resourceProvider);
  }

  public static PaginateCommentsResourceProvider newInstance(CommentsStore commentsStore,
      UnrepliedCommentsUtils unrepliedCommentsUtils, NetworkUtilsWrapper networkUtilsWrapper,
      ResourceProvider resourceProvider) {
    return new PaginateCommentsResourceProvider(commentsStore, unrepliedCommentsUtils, networkUtilsWrapper, resourceProvider);
  }
}
