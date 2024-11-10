package org.wordpress.android.ui.accounts.login;

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
public final class LoginPrologueViewModel_Factory implements Factory<LoginPrologueViewModel> {
  @Override
  public LoginPrologueViewModel get() {
    return newInstance();
  }

  public static LoginPrologueViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static LoginPrologueViewModel newInstance() {
    return new LoginPrologueViewModel();
  }

  private static final class InstanceHolder {
    private static final LoginPrologueViewModel_Factory INSTANCE = new LoginPrologueViewModel_Factory();
  }
}
