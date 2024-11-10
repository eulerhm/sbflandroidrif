package org.wordpress.android.ui.deeplinks.handlers;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
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
public final class PagesLinkHandler_Factory implements Factory<PagesLinkHandler> {
  private final Provider<DeepLinkUriUtils> deepLinkUriUtilsProvider;

  public PagesLinkHandler_Factory(Provider<DeepLinkUriUtils> deepLinkUriUtilsProvider) {
    this.deepLinkUriUtilsProvider = deepLinkUriUtilsProvider;
  }

  @Override
  public PagesLinkHandler get() {
    return newInstance(deepLinkUriUtilsProvider.get());
  }

  public static PagesLinkHandler_Factory create(
      Provider<DeepLinkUriUtils> deepLinkUriUtilsProvider) {
    return new PagesLinkHandler_Factory(deepLinkUriUtilsProvider);
  }

  public static PagesLinkHandler newInstance(DeepLinkUriUtils deepLinkUriUtils) {
    return new PagesLinkHandler(deepLinkUriUtils);
  }
}
