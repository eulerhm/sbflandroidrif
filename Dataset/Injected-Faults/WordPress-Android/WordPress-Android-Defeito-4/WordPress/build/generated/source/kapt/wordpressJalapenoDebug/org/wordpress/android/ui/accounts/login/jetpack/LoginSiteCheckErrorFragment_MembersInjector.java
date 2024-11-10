package org.wordpress.android.ui.accounts.login.jetpack;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.accounts.UnifiedLoginTracker;
import org.wordpress.android.ui.utils.HtmlMessageUtils;

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
public final class LoginSiteCheckErrorFragment_MembersInjector implements MembersInjector<LoginSiteCheckErrorFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<UnifiedLoginTracker> unifiedLoginTrackerProvider;

  private final Provider<HtmlMessageUtils> htmlMessageUtilsProvider;

  public LoginSiteCheckErrorFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UnifiedLoginTracker> unifiedLoginTrackerProvider,
      Provider<HtmlMessageUtils> htmlMessageUtilsProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.unifiedLoginTrackerProvider = unifiedLoginTrackerProvider;
    this.htmlMessageUtilsProvider = htmlMessageUtilsProvider;
  }

  public static MembersInjector<LoginSiteCheckErrorFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UnifiedLoginTracker> unifiedLoginTrackerProvider,
      Provider<HtmlMessageUtils> htmlMessageUtilsProvider) {
    return new LoginSiteCheckErrorFragment_MembersInjector(viewModelFactoryProvider, unifiedLoginTrackerProvider, htmlMessageUtilsProvider);
  }

  @Override
  public void injectMembers(LoginSiteCheckErrorFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectUnifiedLoginTracker(instance, unifiedLoginTrackerProvider.get());
    injectHtmlMessageUtils(instance, htmlMessageUtilsProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.login.jetpack.LoginSiteCheckErrorFragment.viewModelFactory")
  public static void injectViewModelFactory(LoginSiteCheckErrorFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.login.jetpack.LoginSiteCheckErrorFragment.unifiedLoginTracker")
  public static void injectUnifiedLoginTracker(LoginSiteCheckErrorFragment instance,
      UnifiedLoginTracker unifiedLoginTracker) {
    instance.unifiedLoginTracker = unifiedLoginTracker;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.login.jetpack.LoginSiteCheckErrorFragment.htmlMessageUtils")
  public static void injectHtmlMessageUtils(LoginSiteCheckErrorFragment instance,
      HtmlMessageUtils htmlMessageUtils) {
    instance.htmlMessageUtils = htmlMessageUtils;
  }
}
