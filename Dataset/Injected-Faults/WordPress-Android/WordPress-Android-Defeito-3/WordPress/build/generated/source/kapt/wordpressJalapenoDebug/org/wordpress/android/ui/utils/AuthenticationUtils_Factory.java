package org.wordpress.android.ui.utils;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.network.HTTPAuthManager;
import org.wordpress.android.fluxc.network.UserAgent;
import org.wordpress.android.fluxc.network.rest.wpcom.auth.AccessToken;
import org.wordpress.android.fluxc.network.rest.wpcom.site.PrivateAtomicCookie;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AuthenticationUtils_Factory implements Factory<AuthenticationUtils> {
  private final Provider<AccessToken> accessTokenProvider;

  private final Provider<HTTPAuthManager> httpAuthManagerProvider;

  private final Provider<UserAgent> userAgentProvider;

  private final Provider<PrivateAtomicCookie> privateAtomicCookieProvider;

  public AuthenticationUtils_Factory(Provider<AccessToken> accessTokenProvider,
      Provider<HTTPAuthManager> httpAuthManagerProvider, Provider<UserAgent> userAgentProvider,
      Provider<PrivateAtomicCookie> privateAtomicCookieProvider) {
    this.accessTokenProvider = accessTokenProvider;
    this.httpAuthManagerProvider = httpAuthManagerProvider;
    this.userAgentProvider = userAgentProvider;
    this.privateAtomicCookieProvider = privateAtomicCookieProvider;
  }

  @Override
  public AuthenticationUtils get() {
    return newInstance(accessTokenProvider.get(), httpAuthManagerProvider.get(), userAgentProvider.get(), privateAtomicCookieProvider.get());
  }

  public static AuthenticationUtils_Factory create(Provider<AccessToken> accessTokenProvider,
      Provider<HTTPAuthManager> httpAuthManagerProvider, Provider<UserAgent> userAgentProvider,
      Provider<PrivateAtomicCookie> privateAtomicCookieProvider) {
    return new AuthenticationUtils_Factory(accessTokenProvider, httpAuthManagerProvider, userAgentProvider, privateAtomicCookieProvider);
  }

  public static AuthenticationUtils newInstance(AccessToken accessToken,
      HTTPAuthManager httpAuthManager, UserAgent userAgent,
      PrivateAtomicCookie privateAtomicCookie) {
    return new AuthenticationUtils(accessToken, httpAuthManager, userAgent, privateAtomicCookie);
  }
}
