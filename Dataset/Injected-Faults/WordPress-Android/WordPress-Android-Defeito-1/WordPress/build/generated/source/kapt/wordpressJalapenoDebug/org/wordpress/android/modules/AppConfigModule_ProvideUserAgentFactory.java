package org.wordpress.android.modules;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.network.UserAgent;

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
public final class AppConfigModule_ProvideUserAgentFactory implements Factory<UserAgent> {
  private final AppConfigModule module;

  private final Provider<Context> appContextProvider;

  public AppConfigModule_ProvideUserAgentFactory(AppConfigModule module,
      Provider<Context> appContextProvider) {
    this.module = module;
    this.appContextProvider = appContextProvider;
  }

  @Override
  public UserAgent get() {
    return provideUserAgent(module, appContextProvider.get());
  }

  public static AppConfigModule_ProvideUserAgentFactory create(AppConfigModule module,
      Provider<Context> appContextProvider) {
    return new AppConfigModule_ProvideUserAgentFactory(module, appContextProvider);
  }

  public static UserAgent provideUserAgent(AppConfigModule instance, Context appContext) {
    return Preconditions.checkNotNullFromProvides(instance.provideUserAgent(appContext));
  }
}
