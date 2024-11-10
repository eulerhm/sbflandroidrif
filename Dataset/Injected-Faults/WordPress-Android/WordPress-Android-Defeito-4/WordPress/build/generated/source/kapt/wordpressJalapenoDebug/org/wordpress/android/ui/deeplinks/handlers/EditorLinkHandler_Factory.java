package org.wordpress.android.ui.deeplinks.handlers;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.PostStore;
import org.wordpress.android.ui.deeplinks.DeepLinkUriUtils;

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
public final class EditorLinkHandler_Factory implements Factory<EditorLinkHandler> {
  private final Provider<DeepLinkUriUtils> deepLinkUriUtilsProvider;

  private final Provider<PostStore> postStoreProvider;

  public EditorLinkHandler_Factory(Provider<DeepLinkUriUtils> deepLinkUriUtilsProvider,
      Provider<PostStore> postStoreProvider) {
    this.deepLinkUriUtilsProvider = deepLinkUriUtilsProvider;
    this.postStoreProvider = postStoreProvider;
  }

  @Override
  public EditorLinkHandler get() {
    return newInstance(deepLinkUriUtilsProvider.get(), postStoreProvider.get());
  }

  public static EditorLinkHandler_Factory create(
      Provider<DeepLinkUriUtils> deepLinkUriUtilsProvider, Provider<PostStore> postStoreProvider) {
    return new EditorLinkHandler_Factory(deepLinkUriUtilsProvider, postStoreProvider);
  }

  public static EditorLinkHandler newInstance(DeepLinkUriUtils deepLinkUriUtils,
      PostStore postStore) {
    return new EditorLinkHandler(deepLinkUriUtils, postStore);
  }
}
