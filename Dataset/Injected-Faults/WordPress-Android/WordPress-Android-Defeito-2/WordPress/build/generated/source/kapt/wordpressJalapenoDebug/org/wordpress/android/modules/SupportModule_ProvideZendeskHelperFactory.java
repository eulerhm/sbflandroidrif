package org.wordpress.android.modules;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.support.SupportHelper;
import org.wordpress.android.support.ZendeskHelper;
import org.wordpress.android.util.BuildConfigWrapper;

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
public final class SupportModule_ProvideZendeskHelperFactory implements Factory<ZendeskHelper> {
  private final SupportModule module;

  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<SupportHelper> supportHelperProvider;

  private final Provider<BuildConfigWrapper> buildConfigWrapperProvider;

  public SupportModule_ProvideZendeskHelperFactory(SupportModule module,
      Provider<AccountStore> accountStoreProvider, Provider<SiteStore> siteStoreProvider,
      Provider<SupportHelper> supportHelperProvider,
      Provider<BuildConfigWrapper> buildConfigWrapperProvider) {
    this.module = module;
    this.accountStoreProvider = accountStoreProvider;
    this.siteStoreProvider = siteStoreProvider;
    this.supportHelperProvider = supportHelperProvider;
    this.buildConfigWrapperProvider = buildConfigWrapperProvider;
  }

  @Override
  public ZendeskHelper get() {
    return provideZendeskHelper(module, accountStoreProvider.get(), siteStoreProvider.get(), supportHelperProvider.get(), buildConfigWrapperProvider.get());
  }

  public static SupportModule_ProvideZendeskHelperFactory create(SupportModule module,
      Provider<AccountStore> accountStoreProvider, Provider<SiteStore> siteStoreProvider,
      Provider<SupportHelper> supportHelperProvider,
      Provider<BuildConfigWrapper> buildConfigWrapperProvider) {
    return new SupportModule_ProvideZendeskHelperFactory(module, accountStoreProvider, siteStoreProvider, supportHelperProvider, buildConfigWrapperProvider);
  }

  public static ZendeskHelper provideZendeskHelper(SupportModule instance,
      AccountStore accountStore, SiteStore siteStore, SupportHelper supportHelper,
      BuildConfigWrapper buildConfigWrapper) {
    return Preconditions.checkNotNullFromProvides(instance.provideZendeskHelper(accountStore, siteStore, supportHelper, buildConfigWrapper));
  }
}
