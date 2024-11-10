package org.wordpress.android.ui.prefs.accountsettings;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.prefs.accountsettings.usecase.FetchAccountSettingsUseCase;
import org.wordpress.android.ui.prefs.accountsettings.usecase.GetAccountUseCase;
import org.wordpress.android.ui.prefs.accountsettings.usecase.GetSitesUseCase;
import org.wordpress.android.ui.prefs.accountsettings.usecase.PushAccountSettingsUseCase;
import org.wordpress.android.util.NetworkUtilsWrapper;
import org.wordpress.android.viewmodel.ResourceProvider;

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
public final class AccountSettingsViewModel_Factory implements Factory<AccountSettingsViewModel> {
  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<FetchAccountSettingsUseCase> fetchAccountSettingsUseCaseProvider;

  private final Provider<PushAccountSettingsUseCase> pushAccountSettingsUseCaseProvider;

  private final Provider<GetAccountUseCase> getAccountUseCaseProvider;

  private final Provider<GetSitesUseCase> getSitesUseCaseProvider;

  private final Provider<AccountSettingsOptimisticUpdateHandler> optimisticUpdateHandlerProvider;

  public AccountSettingsViewModel_Factory(Provider<ResourceProvider> resourceProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<FetchAccountSettingsUseCase> fetchAccountSettingsUseCaseProvider,
      Provider<PushAccountSettingsUseCase> pushAccountSettingsUseCaseProvider,
      Provider<GetAccountUseCase> getAccountUseCaseProvider,
      Provider<GetSitesUseCase> getSitesUseCaseProvider,
      Provider<AccountSettingsOptimisticUpdateHandler> optimisticUpdateHandlerProvider) {
    this.resourceProvider = resourceProvider;
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.fetchAccountSettingsUseCaseProvider = fetchAccountSettingsUseCaseProvider;
    this.pushAccountSettingsUseCaseProvider = pushAccountSettingsUseCaseProvider;
    this.getAccountUseCaseProvider = getAccountUseCaseProvider;
    this.getSitesUseCaseProvider = getSitesUseCaseProvider;
    this.optimisticUpdateHandlerProvider = optimisticUpdateHandlerProvider;
  }

  @Override
  public AccountSettingsViewModel get() {
    return newInstance(resourceProvider.get(), networkUtilsWrapperProvider.get(), mainDispatcherProvider.get(), fetchAccountSettingsUseCaseProvider.get(), pushAccountSettingsUseCaseProvider.get(), getAccountUseCaseProvider.get(), getSitesUseCaseProvider.get(), optimisticUpdateHandlerProvider.get());
  }

  public static AccountSettingsViewModel_Factory create(Provider<ResourceProvider> resourceProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<FetchAccountSettingsUseCase> fetchAccountSettingsUseCaseProvider,
      Provider<PushAccountSettingsUseCase> pushAccountSettingsUseCaseProvider,
      Provider<GetAccountUseCase> getAccountUseCaseProvider,
      Provider<GetSitesUseCase> getSitesUseCaseProvider,
      Provider<AccountSettingsOptimisticUpdateHandler> optimisticUpdateHandlerProvider) {
    return new AccountSettingsViewModel_Factory(resourceProvider, networkUtilsWrapperProvider, mainDispatcherProvider, fetchAccountSettingsUseCaseProvider, pushAccountSettingsUseCaseProvider, getAccountUseCaseProvider, getSitesUseCaseProvider, optimisticUpdateHandlerProvider);
  }

  public static AccountSettingsViewModel newInstance(ResourceProvider resourceProvider,
      NetworkUtilsWrapper networkUtilsWrapper, CoroutineDispatcher mainDispatcher,
      FetchAccountSettingsUseCase fetchAccountSettingsUseCase,
      PushAccountSettingsUseCase pushAccountSettingsUseCase, GetAccountUseCase getAccountUseCase,
      GetSitesUseCase getSitesUseCase,
      AccountSettingsOptimisticUpdateHandler optimisticUpdateHandler) {
    return new AccountSettingsViewModel(resourceProvider, networkUtilsWrapper, mainDispatcher, fetchAccountSettingsUseCase, pushAccountSettingsUseCase, getAccountUseCase, getSitesUseCase, optimisticUpdateHandler);
  }
}
