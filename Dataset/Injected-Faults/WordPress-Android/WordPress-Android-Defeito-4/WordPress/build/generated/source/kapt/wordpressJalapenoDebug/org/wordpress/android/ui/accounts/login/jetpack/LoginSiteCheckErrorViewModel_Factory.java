package org.wordpress.android.ui.accounts.login.jetpack;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;

@ScopeMetadata
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
public final class LoginSiteCheckErrorViewModel_Factory implements Factory<LoginSiteCheckErrorViewModel> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  public LoginSiteCheckErrorViewModel_Factory(
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
  }

  @Override
  public LoginSiteCheckErrorViewModel get() {
    return newInstance(mainDispatcherProvider.get());
  }

  public static LoginSiteCheckErrorViewModel_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    return new LoginSiteCheckErrorViewModel_Factory(mainDispatcherProvider);
  }

  public static LoginSiteCheckErrorViewModel newInstance(CoroutineDispatcher mainDispatcher) {
    return new LoginSiteCheckErrorViewModel(mainDispatcher);
  }
}
