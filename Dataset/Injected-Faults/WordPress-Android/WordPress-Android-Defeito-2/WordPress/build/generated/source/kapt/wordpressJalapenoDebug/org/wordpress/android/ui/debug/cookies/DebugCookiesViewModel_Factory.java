package org.wordpress.android.ui.debug.cookies;

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
public final class DebugCookiesViewModel_Factory implements Factory<DebugCookiesViewModel> {
  private final Provider<DebugCookieManager> debugCookieManagerProvider;

  public DebugCookiesViewModel_Factory(Provider<DebugCookieManager> debugCookieManagerProvider) {
    this.debugCookieManagerProvider = debugCookieManagerProvider;
  }

  @Override
  public DebugCookiesViewModel get() {
    return newInstance(debugCookieManagerProvider.get());
  }

  public static DebugCookiesViewModel_Factory create(
      Provider<DebugCookieManager> debugCookieManagerProvider) {
    return new DebugCookiesViewModel_Factory(debugCookieManagerProvider);
  }

  public static DebugCookiesViewModel newInstance(DebugCookieManager debugCookieManager) {
    return new DebugCookiesViewModel(debugCookieManager);
  }
}
