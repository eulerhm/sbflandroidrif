package org.wordpress.android.ui.mediapicker.loader;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.NetworkUtilsWrapper;
import org.wordpress.android.util.UriUtilsWrapper;

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
public final class GifMediaDataSource_Factory implements Factory<GifMediaDataSource> {
  private final Provider<Context> contextProvider;

  private final Provider<TenorGifClient> tenorClientProvider;

  private final Provider<UriUtilsWrapper> uriUtilsWrapperProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  public GifMediaDataSource_Factory(Provider<Context> contextProvider,
      Provider<TenorGifClient> tenorClientProvider,
      Provider<UriUtilsWrapper> uriUtilsWrapperProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider) {
    this.contextProvider = contextProvider;
    this.tenorClientProvider = tenorClientProvider;
    this.uriUtilsWrapperProvider = uriUtilsWrapperProvider;
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
  }

  @Override
  public GifMediaDataSource get() {
    return newInstance(contextProvider.get(), tenorClientProvider.get(), uriUtilsWrapperProvider.get(), networkUtilsWrapperProvider.get());
  }

  public static GifMediaDataSource_Factory create(Provider<Context> contextProvider,
      Provider<TenorGifClient> tenorClientProvider,
      Provider<UriUtilsWrapper> uriUtilsWrapperProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider) {
    return new GifMediaDataSource_Factory(contextProvider, tenorClientProvider, uriUtilsWrapperProvider, networkUtilsWrapperProvider);
  }

  public static GifMediaDataSource newInstance(Context context, TenorGifClient tenorClient,
      UriUtilsWrapper uriUtilsWrapper, NetworkUtilsWrapper networkUtilsWrapper) {
    return new GifMediaDataSource(context, tenorClient, uriUtilsWrapper, networkUtilsWrapper);
  }
}
