package org.wordpress.android.modules;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import org.wordpress.android.fluxc.model.encryptedlogging.EncryptedLoggingKey;

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
public final class AppConfigModule_ProvideEncryptedLoggingKeyFactory implements Factory<EncryptedLoggingKey> {
  private final AppConfigModule module;

  public AppConfigModule_ProvideEncryptedLoggingKeyFactory(AppConfigModule module) {
    this.module = module;
  }

  @Override
  public EncryptedLoggingKey get() {
    return provideEncryptedLoggingKey(module);
  }

  public static AppConfigModule_ProvideEncryptedLoggingKeyFactory create(AppConfigModule module) {
    return new AppConfigModule_ProvideEncryptedLoggingKeyFactory(module);
  }

  public static EncryptedLoggingKey provideEncryptedLoggingKey(AppConfigModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideEncryptedLoggingKey());
  }
}
