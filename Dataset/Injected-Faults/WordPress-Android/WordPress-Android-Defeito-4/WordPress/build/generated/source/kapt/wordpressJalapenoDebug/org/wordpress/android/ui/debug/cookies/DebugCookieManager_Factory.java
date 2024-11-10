package org.wordpress.android.ui.debug.cookies;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.net.CookieManager;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.BuildConfigWrapper;

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
public final class DebugCookieManager_Factory implements Factory<DebugCookieManager> {
  private final Provider<Context> contextProvider;

  private final Provider<CookieManager> cookieManagerProvider;

  private final Provider<BuildConfigWrapper> buildConfigProvider;

  public DebugCookieManager_Factory(Provider<Context> contextProvider,
      Provider<CookieManager> cookieManagerProvider,
      Provider<BuildConfigWrapper> buildConfigProvider) {
    this.contextProvider = contextProvider;
    this.cookieManagerProvider = cookieManagerProvider;
    this.buildConfigProvider = buildConfigProvider;
  }

  @Override
  public DebugCookieManager get() {
    return newInstance(contextProvider.get(), cookieManagerProvider.get(), buildConfigProvider.get());
  }

  public static DebugCookieManager_Factory create(Provider<Context> contextProvider,
      Provider<CookieManager> cookieManagerProvider,
      Provider<BuildConfigWrapper> buildConfigProvider) {
    return new DebugCookieManager_Factory(contextProvider, cookieManagerProvider, buildConfigProvider);
  }

  public static DebugCookieManager newInstance(Context context, CookieManager cookieManager,
      BuildConfigWrapper buildConfig) {
    return new DebugCookieManager(context, cookieManager, buildConfig);
  }
}
