package org.wordpress.android.modules;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import org.wordpress.android.fluxc.network.rest.wpcom.auth.AppSecrets;

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
public final class AppConfigModule_ProvideAppSecretsFactory implements Factory<AppSecrets> {
  private final AppConfigModule module;

  public AppConfigModule_ProvideAppSecretsFactory(AppConfigModule module) {
    this.module = module;
  }

  @Override
  public AppSecrets get() {
    return provideAppSecrets(module);
  }

  public static AppConfigModule_ProvideAppSecretsFactory create(AppConfigModule module) {
    return new AppConfigModule_ProvideAppSecretsFactory(module);
  }

  public static AppSecrets provideAppSecrets(AppConfigModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideAppSecrets());
  }
}
