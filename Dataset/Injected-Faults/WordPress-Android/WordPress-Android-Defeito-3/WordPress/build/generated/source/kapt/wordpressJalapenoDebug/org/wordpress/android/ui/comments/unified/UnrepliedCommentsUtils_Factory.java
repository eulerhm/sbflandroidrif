package org.wordpress.android.ui.comments.unified;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;

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
public final class UnrepliedCommentsUtils_Factory implements Factory<UnrepliedCommentsUtils> {
  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<SelectedSiteRepository> selectedSiteRepositoryProvider;

  public UnrepliedCommentsUtils_Factory(Provider<AccountStore> accountStoreProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider) {
    this.accountStoreProvider = accountStoreProvider;
    this.selectedSiteRepositoryProvider = selectedSiteRepositoryProvider;
  }

  @Override
  public UnrepliedCommentsUtils get() {
    return newInstance(accountStoreProvider.get(), selectedSiteRepositoryProvider.get());
  }

  public static UnrepliedCommentsUtils_Factory create(Provider<AccountStore> accountStoreProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider) {
    return new UnrepliedCommentsUtils_Factory(accountStoreProvider, selectedSiteRepositoryProvider);
  }

  public static UnrepliedCommentsUtils newInstance(AccountStore accountStore,
      SelectedSiteRepository selectedSiteRepository) {
    return new UnrepliedCommentsUtils(accountStore, selectedSiteRepository);
  }
}
