package org.wordpress.android.ui.people;

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
public final class InviteLinksHandler_Factory implements Factory<InviteLinksHandler> {
  private final Provider<InviteLinksUseCase> inviteLinksUseCaseProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public InviteLinksHandler_Factory(Provider<InviteLinksUseCase> inviteLinksUseCaseProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.inviteLinksUseCaseProvider = inviteLinksUseCaseProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public InviteLinksHandler get() {
    return newInstance(inviteLinksUseCaseProvider.get(), bgDispatcherProvider.get());
  }

  public static InviteLinksHandler_Factory create(
      Provider<InviteLinksUseCase> inviteLinksUseCaseProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new InviteLinksHandler_Factory(inviteLinksUseCaseProvider, bgDispatcherProvider);
  }

  public static InviteLinksHandler newInstance(InviteLinksUseCase inviteLinksUseCase,
      CoroutineDispatcher bgDispatcher) {
    return new InviteLinksHandler(inviteLinksUseCase, bgDispatcher);
  }
}
