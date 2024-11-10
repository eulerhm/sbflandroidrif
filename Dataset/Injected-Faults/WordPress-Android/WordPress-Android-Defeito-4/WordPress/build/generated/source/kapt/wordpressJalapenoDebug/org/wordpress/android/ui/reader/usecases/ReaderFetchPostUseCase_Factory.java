package org.wordpress.android.ui.reader.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.reader.actions.ReaderPostActionsWrapper;
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
public final class ReaderFetchPostUseCase_Factory implements Factory<ReaderFetchPostUseCase> {
  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<ReaderPostActionsWrapper> readerPostActionsWrapperProvider;

  public ReaderFetchPostUseCase_Factory(Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ReaderPostActionsWrapper> readerPostActionsWrapperProvider) {
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.readerPostActionsWrapperProvider = readerPostActionsWrapperProvider;
  }

  @Override
  public ReaderFetchPostUseCase get() {
    return newInstance(networkUtilsWrapperProvider.get(), readerPostActionsWrapperProvider.get());
  }

  public static ReaderFetchPostUseCase_Factory create(
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ReaderPostActionsWrapper> readerPostActionsWrapperProvider) {
    return new ReaderFetchPostUseCase_Factory(networkUtilsWrapperProvider, readerPostActionsWrapperProvider);
  }

  public static ReaderFetchPostUseCase newInstance(NetworkUtilsWrapper networkUtilsWrapper,
      ReaderPostActionsWrapper readerPostActionsWrapper) {
    return new ReaderFetchPostUseCase(networkUtilsWrapper, readerPostActionsWrapper);
  }
}
