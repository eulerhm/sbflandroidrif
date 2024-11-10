package com.google.android.gnd;

import android.content.SharedPreferences;
import com.google.android.gnd.persistence.remote.FakeRemoteDataStore;
import com.google.android.gnd.repository.TermsOfServiceRepository;
import com.google.android.gnd.repository.UserRepository;
import com.google.android.gnd.system.auth.FakeAuthenticationManager;
import com.google.android.gnd.ui.common.Navigator;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import javax.annotation.Generated;
import javax.inject.Provider;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class MainViewModelTest_MembersInjector implements MembersInjector<MainViewModelTest> {
  private final Provider<FakeAuthenticationManager> fakeAuthenticationManagerProvider;

  private final Provider<FakeRemoteDataStore> fakeRemoteDataStoreProvider;

  private final Provider<MainViewModel> viewModelProvider;

  private final Provider<Navigator> navigatorProvider;

  private final Provider<SharedPreferences> sharedPreferencesProvider;

  private final Provider<TermsOfServiceRepository> tosRepositoryProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  public MainViewModelTest_MembersInjector(
      Provider<FakeAuthenticationManager> fakeAuthenticationManagerProvider,
      Provider<FakeRemoteDataStore> fakeRemoteDataStoreProvider,
      Provider<MainViewModel> viewModelProvider, Provider<Navigator> navigatorProvider,
      Provider<SharedPreferences> sharedPreferencesProvider,
      Provider<TermsOfServiceRepository> tosRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    this.fakeAuthenticationManagerProvider = fakeAuthenticationManagerProvider;
    this.fakeRemoteDataStoreProvider = fakeRemoteDataStoreProvider;
    this.viewModelProvider = viewModelProvider;
    this.navigatorProvider = navigatorProvider;
    this.sharedPreferencesProvider = sharedPreferencesProvider;
    this.tosRepositoryProvider = tosRepositoryProvider;
    this.userRepositoryProvider = userRepositoryProvider;
  }

  public static MembersInjector<MainViewModelTest> create(
      Provider<FakeAuthenticationManager> fakeAuthenticationManagerProvider,
      Provider<FakeRemoteDataStore> fakeRemoteDataStoreProvider,
      Provider<MainViewModel> viewModelProvider, Provider<Navigator> navigatorProvider,
      Provider<SharedPreferences> sharedPreferencesProvider,
      Provider<TermsOfServiceRepository> tosRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    return new MainViewModelTest_MembersInjector(fakeAuthenticationManagerProvider, fakeRemoteDataStoreProvider, viewModelProvider, navigatorProvider, sharedPreferencesProvider, tosRepositoryProvider, userRepositoryProvider);
  }

  @Override
  public void injectMembers(MainViewModelTest instance) {
    injectFakeAuthenticationManager(instance, fakeAuthenticationManagerProvider.get());
    injectFakeRemoteDataStore(instance, fakeRemoteDataStoreProvider.get());
    injectViewModel(instance, viewModelProvider.get());
    injectNavigator(instance, navigatorProvider.get());
    injectSharedPreferences(instance, sharedPreferencesProvider.get());
    injectTosRepository(instance, tosRepositoryProvider.get());
    injectUserRepository(instance, userRepositoryProvider.get());
  }

  @InjectedFieldSignature("com.google.android.gnd.MainViewModelTest.fakeAuthenticationManager")
  public static void injectFakeAuthenticationManager(MainViewModelTest instance,
      FakeAuthenticationManager fakeAuthenticationManager) {
    instance.fakeAuthenticationManager = fakeAuthenticationManager;
  }

  @InjectedFieldSignature("com.google.android.gnd.MainViewModelTest.fakeRemoteDataStore")
  public static void injectFakeRemoteDataStore(MainViewModelTest instance,
      FakeRemoteDataStore fakeRemoteDataStore) {
    instance.fakeRemoteDataStore = fakeRemoteDataStore;
  }

  @InjectedFieldSignature("com.google.android.gnd.MainViewModelTest.viewModel")
  public static void injectViewModel(MainViewModelTest instance, MainViewModel viewModel) {
    instance.viewModel = viewModel;
  }

  @InjectedFieldSignature("com.google.android.gnd.MainViewModelTest.navigator")
  public static void injectNavigator(MainViewModelTest instance, Navigator navigator) {
    instance.navigator = navigator;
  }

  @InjectedFieldSignature("com.google.android.gnd.MainViewModelTest.sharedPreferences")
  public static void injectSharedPreferences(MainViewModelTest instance,
      SharedPreferences sharedPreferences) {
    instance.sharedPreferences = sharedPreferences;
  }

  @InjectedFieldSignature("com.google.android.gnd.MainViewModelTest.tosRepository")
  public static void injectTosRepository(MainViewModelTest instance,
      TermsOfServiceRepository tosRepository) {
    instance.tosRepository = tosRepository;
  }

  @InjectedFieldSignature("com.google.android.gnd.MainViewModelTest.userRepository")
  public static void injectUserRepository(MainViewModelTest instance,
      UserRepository userRepository) {
    instance.userRepository = userRepository;
  }
}
