package org.wordpress.android.ui.reader.repository.usecases.tags;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.util.EventBusWrapper;
import org.wordpress.android.util.NetworkUtilsWrapper;

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
public final class FollowInterestTagsUseCase_Factory implements Factory<FollowInterestTagsUseCase> {
  private final Provider<EventBusWrapper> eventBusWrapperProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<AccountStore> accountStoreProvider;

  public FollowInterestTagsUseCase_Factory(Provider<EventBusWrapper> eventBusWrapperProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<AccountStore> accountStoreProvider) {
    this.eventBusWrapperProvider = eventBusWrapperProvider;
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.accountStoreProvider = accountStoreProvider;
  }

  @Override
  public FollowInterestTagsUseCase get() {
    return newInstance(eventBusWrapperProvider.get(), networkUtilsWrapperProvider.get(), accountStoreProvider.get());
  }

  public static FollowInterestTagsUseCase_Factory create(
      Provider<EventBusWrapper> eventBusWrapperProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<AccountStore> accountStoreProvider) {
    return new FollowInterestTagsUseCase_Factory(eventBusWrapperProvider, networkUtilsWrapperProvider, accountStoreProvider);
  }

  public static FollowInterestTagsUseCase newInstance(EventBusWrapper eventBusWrapper,
      NetworkUtilsWrapper networkUtilsWrapper, AccountStore accountStore) {
    return new FollowInterestTagsUseCase(eventBusWrapper, networkUtilsWrapper, accountStore);
  }
}
