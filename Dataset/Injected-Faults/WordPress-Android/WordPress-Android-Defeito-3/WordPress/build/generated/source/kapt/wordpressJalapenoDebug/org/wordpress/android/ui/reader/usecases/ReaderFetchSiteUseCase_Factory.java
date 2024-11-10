package org.wordpress.android.ui.reader.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.reader.actions.ReaderBlogActionsWrapper;
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
public final class ReaderFetchSiteUseCase_Factory implements Factory<ReaderFetchSiteUseCase> {
  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<ReaderBlogActionsWrapper> readerBlogActionsWrapperProvider;

  public ReaderFetchSiteUseCase_Factory(Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ReaderBlogActionsWrapper> readerBlogActionsWrapperProvider) {
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.readerBlogActionsWrapperProvider = readerBlogActionsWrapperProvider;
  }

  @Override
  public ReaderFetchSiteUseCase get() {
    return newInstance(networkUtilsWrapperProvider.get(), readerBlogActionsWrapperProvider.get());
  }

  public static ReaderFetchSiteUseCase_Factory create(
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ReaderBlogActionsWrapper> readerBlogActionsWrapperProvider) {
    return new ReaderFetchSiteUseCase_Factory(networkUtilsWrapperProvider, readerBlogActionsWrapperProvider);
  }

  public static ReaderFetchSiteUseCase newInstance(NetworkUtilsWrapper networkUtilsWrapper,
      ReaderBlogActionsWrapper readerBlogActionsWrapper) {
    return new ReaderFetchSiteUseCase(networkUtilsWrapper, readerBlogActionsWrapper);
  }
}
