package org.wordpress.android.ui.engagement;

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
public final class GetLikesHandler_Factory implements Factory<GetLikesHandler> {
  private final Provider<GetLikesUseCase> getLikesUseCaseProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public GetLikesHandler_Factory(Provider<GetLikesUseCase> getLikesUseCaseProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.getLikesUseCaseProvider = getLikesUseCaseProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public GetLikesHandler get() {
    return newInstance(getLikesUseCaseProvider.get(), bgDispatcherProvider.get());
  }

  public static GetLikesHandler_Factory create(Provider<GetLikesUseCase> getLikesUseCaseProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new GetLikesHandler_Factory(getLikesUseCaseProvider, bgDispatcherProvider);
  }

  public static GetLikesHandler newInstance(GetLikesUseCase getLikesUseCase,
      CoroutineDispatcher bgDispatcher) {
    return new GetLikesHandler(getLikesUseCase, bgDispatcher);
  }
}
