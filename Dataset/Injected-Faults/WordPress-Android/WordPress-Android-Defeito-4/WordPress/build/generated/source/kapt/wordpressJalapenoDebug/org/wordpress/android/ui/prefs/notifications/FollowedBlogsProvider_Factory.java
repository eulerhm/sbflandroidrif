package org.wordpress.android.ui.prefs.notifications;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.datasets.ReaderBlogTableWrapper;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.ui.utils.UrlUtilsWrapper;

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
public final class FollowedBlogsProvider_Factory implements Factory<FollowedBlogsProvider> {
  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<ReaderBlogTableWrapper> readerBlogTableProvider;

  private final Provider<UrlUtilsWrapper> urlUtilsProvider;

  public FollowedBlogsProvider_Factory(Provider<AccountStore> accountStoreProvider,
      Provider<ReaderBlogTableWrapper> readerBlogTableProvider,
      Provider<UrlUtilsWrapper> urlUtilsProvider) {
    this.accountStoreProvider = accountStoreProvider;
    this.readerBlogTableProvider = readerBlogTableProvider;
    this.urlUtilsProvider = urlUtilsProvider;
  }

  @Override
  public FollowedBlogsProvider get() {
    return newInstance(accountStoreProvider.get(), readerBlogTableProvider.get(), urlUtilsProvider.get());
  }

  public static FollowedBlogsProvider_Factory create(Provider<AccountStore> accountStoreProvider,
      Provider<ReaderBlogTableWrapper> readerBlogTableProvider,
      Provider<UrlUtilsWrapper> urlUtilsProvider) {
    return new FollowedBlogsProvider_Factory(accountStoreProvider, readerBlogTableProvider, urlUtilsProvider);
  }

  public static FollowedBlogsProvider newInstance(AccountStore accountStore,
      ReaderBlogTableWrapper readerBlogTable, UrlUtilsWrapper urlUtils) {
    return new FollowedBlogsProvider(accountStore, readerBlogTable, urlUtils);
  }
}
