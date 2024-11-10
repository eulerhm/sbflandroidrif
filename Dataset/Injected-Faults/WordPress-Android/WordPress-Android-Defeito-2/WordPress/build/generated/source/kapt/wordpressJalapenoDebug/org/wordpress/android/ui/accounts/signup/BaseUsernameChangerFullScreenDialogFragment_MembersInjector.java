package org.wordpress.android.ui.accounts.signup;

import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerFragment_MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;

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
public final class BaseUsernameChangerFullScreenDialogFragment_MembersInjector implements MembersInjector<BaseUsernameChangerFullScreenDialogFragment> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<Dispatcher> mDispatcherProvider;

  public BaseUsernameChangerFullScreenDialogFragment_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<Dispatcher> mDispatcherProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.mDispatcherProvider = mDispatcherProvider;
  }

  public static MembersInjector<BaseUsernameChangerFullScreenDialogFragment> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<Dispatcher> mDispatcherProvider) {
    return new BaseUsernameChangerFullScreenDialogFragment_MembersInjector(androidInjectorProvider, mDispatcherProvider);
  }

  @Override
  public void injectMembers(BaseUsernameChangerFullScreenDialogFragment instance) {
    DaggerFragment_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectMDispatcher(instance, mDispatcherProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.signup.BaseUsernameChangerFullScreenDialogFragment.mDispatcher")
  public static void injectMDispatcher(BaseUsernameChangerFullScreenDialogFragment instance,
      Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }
}
