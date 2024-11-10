package org.wordpress.android.ui.publicize;

import dagger.MembersInjector;
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
public final class PublicizeButtonPrefsFragment_MembersInjector implements MembersInjector<PublicizeButtonPrefsFragment> {
  private final Provider<Dispatcher> mDispatcherProvider;

  public PublicizeButtonPrefsFragment_MembersInjector(Provider<Dispatcher> mDispatcherProvider) {
    this.mDispatcherProvider = mDispatcherProvider;
  }

  public static MembersInjector<PublicizeButtonPrefsFragment> create(
      Provider<Dispatcher> mDispatcherProvider) {
    return new PublicizeButtonPrefsFragment_MembersInjector(mDispatcherProvider);
  }

  @Override
  public void injectMembers(PublicizeButtonPrefsFragment instance) {
    injectMDispatcher(instance, mDispatcherProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.publicize.PublicizeButtonPrefsFragment.mDispatcher")
  public static void injectMDispatcher(PublicizeButtonPrefsFragment instance,
      Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }
}
