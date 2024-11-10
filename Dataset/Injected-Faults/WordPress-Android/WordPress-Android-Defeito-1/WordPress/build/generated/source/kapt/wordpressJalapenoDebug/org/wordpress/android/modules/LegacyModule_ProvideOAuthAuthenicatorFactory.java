package org.wordpress.android.modules;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.network.rest.wpcom.auth.AccessToken;
import org.wordpress.android.networking.OAuthAuthenticator;

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
public final class LegacyModule_ProvideOAuthAuthenicatorFactory implements Factory<OAuthAuthenticator> {
  private final LegacyModule module;

  private final Provider<AccessToken> accessTokenProvider;

  public LegacyModule_ProvideOAuthAuthenicatorFactory(LegacyModule module,
      Provider<AccessToken> accessTokenProvider) {
    this.module = module;
    this.accessTokenProvider = accessTokenProvider;
  }

  @Override
  public OAuthAuthenticator get() {
    return provideOAuthAuthenicator(module, accessTokenProvider.get());
  }

  public static LegacyModule_ProvideOAuthAuthenicatorFactory create(LegacyModule module,
      Provider<AccessToken> accessTokenProvider) {
    return new LegacyModule_ProvideOAuthAuthenicatorFactory(module, accessTokenProvider);
  }

  public static OAuthAuthenticator provideOAuthAuthenicator(LegacyModule instance,
      AccessToken accessToken) {
    return Preconditions.checkNotNullFromProvides(instance.provideOAuthAuthenicator(accessToken));
  }
}
