package com.github.pockethub.android.accounts;

import com.meisolsson.githubsdk.service.users.UserService;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class LoginActivity_MembersInjector implements MembersInjector<LoginActivity> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<UserService> userServiceProvider;

  public LoginActivity_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<UserService> userServiceProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.userServiceProvider = userServiceProvider;
  }

  public static MembersInjector<LoginActivity> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<UserService> userServiceProvider) {
    return new LoginActivity_MembersInjector(androidInjectorProvider, userServiceProvider);}

  @Override
  public void injectMembers(LoginActivity instance) {
    DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectUserService(instance, userServiceProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.accounts.LoginActivity.userService")
  public static void injectUserService(LoginActivity instance, UserService userService) {
    instance.userService = userService;
  }
}
