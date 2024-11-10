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
public final class StatsLinkHandler_Factory implements Factory<StatsLinkHandler> {
  private final Provider<DeepLinkUriUtils> deepLinkUriUtilsProvider;

  public StatsLinkHandler_Factory(Provider<DeepLinkUriUtils> deepLinkUriUtilsProvider) {
    this.deepLinkUriUtilsProvider = deepLinkUriUtilsProvider;
  }

  @Override
  public StatsLinkHandler get() {
    return newInstance(deepLinkUriUtilsProvider.get());
  }

  public static StatsLinkHandler_Factory create(
      Provider<DeepLinkUriUtils> deepLinkUriUtilsProvider) {
    return new StatsLinkHandler_Factory(deepLinkUriUtilsProvider);
  }

  public static StatsLinkHandler newInstance(DeepLinkUriUtils deepLinkUriUtils) {
    return new StatsLinkHandler(deepLinkUriUtils);
  }
}
