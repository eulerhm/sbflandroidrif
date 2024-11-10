package org.wordpress.android.modules;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.mediapicker.loader.TenorGifClient;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ApplicationModule_ProvideTenorGifClientFactory implements Factory<TenorGifClient> {
  private final Provider<Context> contextProvider;

  public ApplicationModule_ProvideTenorGifClientFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public TenorGifClient get() {
    return provideTenorGifClient(contextProvider.get());
  }

  public static ApplicationModule_ProvideTenorGifClientFactory create(
      Provider<Context> contextProvider) {
    return new ApplicationModule_ProvideTenorGifClientFactory(contextProvider);
  }

  public static TenorGifClient provideTenorGifClient(Context context) {
    return Preconditions.checkNotNullFromProvides(ApplicationModule.provideTenorGifClient(context));
  }
}
