package org.wordpress.android.util.crashlogging;

import android.content.SharedPreferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.util.BuildConfigWrapper;
import org.wordpress.android.util.EncryptedLogging;
import org.wordpress.android.util.LocaleManagerWrapper;
import org.wordpress.android.util.LogFileProviderWrapper;
import org.wordpress.android.viewmodel.ResourceProvider;

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
public final class WPCrashLoggingDataProvider_Factory implements Factory<WPCrashLoggingDataProvider> {
  private final Provider<SharedPreferences> sharedPreferencesProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<LocaleManagerWrapper> localeManagerProvider;

  private final Provider<EncryptedLogging> encryptedLoggingProvider;

  private final Provider<LogFileProviderWrapper> logFileProvider;

  private final Provider<BuildConfigWrapper> buildConfigProvider;

  public WPCrashLoggingDataProvider_Factory(Provider<SharedPreferences> sharedPreferencesProvider,
      Provider<ResourceProvider> resourceProvider, Provider<AccountStore> accountStoreProvider,
      Provider<LocaleManagerWrapper> localeManagerProvider,
      Provider<EncryptedLogging> encryptedLoggingProvider,
      Provider<LogFileProviderWrapper> logFileProvider,
      Provider<BuildConfigWrapper> buildConfigProvider) {
    this.sharedPreferencesProvider = sharedPreferencesProvider;
    this.resourceProvider = resourceProvider;
    this.accountStoreProvider = accountStoreProvider;
    this.localeManagerProvider = localeManagerProvider;
    this.encryptedLoggingProvider = encryptedLoggingProvider;
    this.logFileProvider = logFileProvider;
    this.buildConfigProvider = buildConfigProvider;
  }

  @Override
  public WPCrashLoggingDataProvider get() {
    return newInstance(sharedPreferencesProvider.get(), resourceProvider.get(), accountStoreProvider.get(), localeManagerProvider.get(), encryptedLoggingProvider.get(), logFileProvider.get(), buildConfigProvider.get());
  }

  public static WPCrashLoggingDataProvider_Factory create(
      Provider<SharedPreferences> sharedPreferencesProvider,
      Provider<ResourceProvider> resourceProvider, Provider<AccountStore> accountStoreProvider,
      Provider<LocaleManagerWrapper> localeManagerProvider,
      Provider<EncryptedLogging> encryptedLoggingProvider,
      Provider<LogFileProviderWrapper> logFileProvider,
      Provider<BuildConfigWrapper> buildConfigProvider) {
    return new WPCrashLoggingDataProvider_Factory(sharedPreferencesProvider, resourceProvider, accountStoreProvider, localeManagerProvider, encryptedLoggingProvider, logFileProvider, buildConfigProvider);
  }

  public static WPCrashLoggingDataProvider newInstance(SharedPreferences sharedPreferences,
      ResourceProvider resourceProvider, AccountStore accountStore,
      LocaleManagerWrapper localeManager, EncryptedLogging encryptedLogging,
      LogFileProviderWrapper logFileProvider, BuildConfigWrapper buildConfig) {
    return new WPCrashLoggingDataProvider(sharedPreferences, resourceProvider, accountStore, localeManager, encryptedLogging, logFileProvider, buildConfig);
  }
}
