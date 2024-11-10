package org.wordpress.android.ui.reader.views;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.ui.reader.discover.ReaderPostTagsUiStateBuilder;
import org.wordpress.android.ui.reader.discover.ReaderPostUiStateBuilder;
import org.wordpress.android.util.DateTimeUtilsWrapper;

@ScopeMetadata("dagger.Reusable")
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
public final class ReaderPostDetailsHeaderViewUiStateBuilder_Factory implements Factory<ReaderPostDetailsHeaderViewUiStateBuilder> {
  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<ReaderPostUiStateBuilder> postUiStateBuilderProvider;

  private final Provider<ReaderPostTagsUiStateBuilder> readerPostTagsUiStateBuilderProvider;

  private final Provider<DateTimeUtilsWrapper> dateTimeUtilsWrapperProvider;

  public ReaderPostDetailsHeaderViewUiStateBuilder_Factory(
      Provider<AccountStore> accountStoreProvider,
      Provider<ReaderPostUiStateBuilder> postUiStateBuilderProvider,
      Provider<ReaderPostTagsUiStateBuilder> readerPostTagsUiStateBuilderProvider,
      Provider<DateTimeUtilsWrapper> dateTimeUtilsWrapperProvider) {
    this.accountStoreProvider = accountStoreProvider;
    this.postUiStateBuilderProvider = postUiStateBuilderProvider;
    this.readerPostTagsUiStateBuilderProvider = readerPostTagsUiStateBuilderProvider;
    this.dateTimeUtilsWrapperProvider = dateTimeUtilsWrapperProvider;
  }

  @Override
  public ReaderPostDetailsHeaderViewUiStateBuilder get() {
    return newInstance(accountStoreProvider.get(), postUiStateBuilderProvider.get(), readerPostTagsUiStateBuilderProvider.get(), dateTimeUtilsWrapperProvider.get());
  }

  public static ReaderPostDetailsHeaderViewUiStateBuilder_Factory create(
      Provider<AccountStore> accountStoreProvider,
      Provider<ReaderPostUiStateBuilder> postUiStateBuilderProvider,
      Provider<ReaderPostTagsUiStateBuilder> readerPostTagsUiStateBuilderProvider,
      Provider<DateTimeUtilsWrapper> dateTimeUtilsWrapperProvider) {
    return new ReaderPostDetailsHeaderViewUiStateBuilder_Factory(accountStoreProvider, postUiStateBuilderProvider, readerPostTagsUiStateBuilderProvider, dateTimeUtilsWrapperProvider);
  }

  public static ReaderPostDetailsHeaderViewUiStateBuilder newInstance(AccountStore accountStore,
      ReaderPostUiStateBuilder postUiStateBuilder,
      ReaderPostTagsUiStateBuilder readerPostTagsUiStateBuilder,
      DateTimeUtilsWrapper dateTimeUtilsWrapper) {
    return new ReaderPostDetailsHeaderViewUiStateBuilder(accountStore, postUiStateBuilder, readerPostTagsUiStateBuilder, dateTimeUtilsWrapper);
  }
}
