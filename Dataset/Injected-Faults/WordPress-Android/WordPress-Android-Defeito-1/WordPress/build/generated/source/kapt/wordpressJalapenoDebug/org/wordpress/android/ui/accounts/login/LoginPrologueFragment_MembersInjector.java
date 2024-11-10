package org.wordpress.android.ui.accounts.login;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.accounts.UnifiedLoginTracker;

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
public final class LoginPrologueFragment_MembersInjector implements MembersInjector<LoginPrologueFragment> {
  private final Provider<UnifiedLoginTracker> unifiedLoginTrackerProvider;

  public LoginPrologueFragment_MembersInjector(
      Provider<UnifiedLoginTracker> unifiedLoginTrackerProvider) {
    this.unifiedLoginTrackerProvider = unifiedLoginTrackerProvider;
  }

  public static MembersInjector<LoginPrologueFragment> create(
      Provider<UnifiedLoginTracker> unifiedLoginTrackerProvider) {
    return new LoginPrologueFragment_MembersInjector(unifiedLoginTrackerProvider);
  }

  @Override
  public void injectMembers(LoginPrologueFragment instance) {
    injectUnifiedLoginTracker(instance, unifiedLoginTrackerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.login.LoginPrologueFragment.unifiedLoginTracker")
  public static void injectUnifiedLoginTracker(LoginPrologueFragment instance,
      UnifiedLoginTracker unifiedLoginTracker) {
    instance.unifiedLoginTracker = unifiedLoginTracker;
  }
}
