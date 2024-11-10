package org.wordpress.android.ui.accounts;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.BuildConfigWrapper;

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
public final class LoginViewModel_Factory implements Factory<LoginViewModel> {
  private final Provider<BuildConfigWrapper> buildConfigWrapperProvider;

  public LoginViewModel_Factory(Provider<BuildConfigWrapper> buildConfigWrapperProvider) {
    this.buildConfigWrapperProvider = buildConfigWrapperProvider;
  }

  @Override
  public LoginViewModel get() {
    return newInstance(buildConfigWrapperProvider.get());
  }

  public static LoginViewModel_Factory create(
      Provider<BuildConfigWrapper> buildConfigWrapperProvider) {
    return new LoginViewModel_Factory(buildConfigWrapperProvider);
  }

  public static LoginViewModel newInstance(BuildConfigWrapper buildConfigWrapper) {
    return new LoginViewModel(buildConfigWrapper);
  }
}
