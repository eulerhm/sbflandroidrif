package org.wordpress.android.ui.mediapicker.loader;

import android.content.Context;
import com.tenor.android.core.network.IApiClient;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;

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
public final class TenorGifClient_Factory implements Factory<TenorGifClient> {
  private final Provider<Context> contextProvider;

  private final Provider<IApiClient> tenorApiClientProvider;

  public TenorGifClient_Factory(Provider<Context> contextProvider,
      Provider<IApiClient> tenorApiClientProvider) {
    this.contextProvider = contextProvider;
    this.tenorApiClientProvider = tenorApiClientProvider;
  }

  @Override
  public TenorGifClient get() {
    return newInstance(contextProvider.get(), tenorApiClientProvider.get());
  }

  public static TenorGifClient_Factory create(Provider<Context> contextProvider,
      Provider<IApiClient> tenorApiClientProvider) {
    return new TenorGifClient_Factory(contextProvider, tenorApiClientProvider);
  }

  public static TenorGifClient newInstance(Context context, IApiClient tenorApiClient) {
    return new TenorGifClient(context, tenorApiClient);
  }
}
