package org.wordpress.android.ui.prefs.accountsettings;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;

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
public final class AccountSettingsOptimisticUpdateHandler_Factory implements Factory<AccountSettingsOptimisticUpdateHandler> {
  @Override
  public AccountSettingsOptimisticUpdateHandler get() {
    return newInstance();
  }

  public static AccountSettingsOptimisticUpdateHandler_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AccountSettingsOptimisticUpdateHandler newInstance() {
    return new AccountSettingsOptimisticUpdateHandler();
  }

  private static final class InstanceHolder {
    private static final AccountSettingsOptimisticUpdateHandler_Factory INSTANCE = new AccountSettingsOptimisticUpdateHandler_Factory();
  }
}
