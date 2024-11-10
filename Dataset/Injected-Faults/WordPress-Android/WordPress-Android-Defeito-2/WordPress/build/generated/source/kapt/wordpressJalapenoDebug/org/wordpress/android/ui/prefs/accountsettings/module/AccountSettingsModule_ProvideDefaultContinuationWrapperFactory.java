package org.wordpress.android.ui.prefs.accountsettings.module;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.ui.utils.ContinuationWrapper;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("javax.inject.Named")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class AccountSettingsModule_ProvideDefaultContinuationWrapperFactory implements Factory<ContinuationWrapper<AccountStore.OnAccountChanged>> {
  private final AccountSettingsModule module;

  public AccountSettingsModule_ProvideDefaultContinuationWrapperFactory(
      AccountSettingsModule module) {
    this.module = module;
  }

  @Override
  public ContinuationWrapper<AccountStore.OnAccountChanged> get() {
    return provideDefaultContinuationWrapper(module);
  }

  public static AccountSettingsModule_ProvideDefaultContinuationWrapperFactory create(
      AccountSettingsModule module) {
    return new AccountSettingsModule_ProvideDefaultContinuationWrapperFactory(module);
  }

  public static ContinuationWrapper<AccountStore.OnAccountChanged> provideDefaultContinuationWrapper(
      AccountSettingsModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideDefaultContinuationWrapper());
  }
}
