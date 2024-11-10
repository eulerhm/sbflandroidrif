package org.wordpress.android.ui.accounts;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.login.LoginAnalyticsListener;

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
public final class LoginMagicLinkInterceptActivity_MembersInjector implements MembersInjector<LoginMagicLinkInterceptActivity> {
  private final Provider<LoginAnalyticsListener> mLoginAnalyticsListenerProvider;

  public LoginMagicLinkInterceptActivity_MembersInjector(
      Provider<LoginAnalyticsListener> mLoginAnalyticsListenerProvider) {
    this.mLoginAnalyticsListenerProvider = mLoginAnalyticsListenerProvider;
  }

  public static MembersInjector<LoginMagicLinkInterceptActivity> create(
      Provider<LoginAnalyticsListener> mLoginAnalyticsListenerProvider) {
    return new LoginMagicLinkInterceptActivity_MembersInjector(mLoginAnalyticsListenerProvider);
  }

  @Override
  public void injectMembers(LoginMagicLinkInterceptActivity instance) {
    injectMLoginAnalyticsListener(instance, mLoginAnalyticsListenerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.LoginMagicLinkInterceptActivity.mLoginAnalyticsListener")
  public static void injectMLoginAnalyticsListener(LoginMagicLinkInterceptActivity instance,
      LoginAnalyticsListener mLoginAnalyticsListener) {
    instance.mLoginAnalyticsListener = mLoginAnalyticsListener;
  }
}
