package com.github.pockethub.android.ui;

import com.github.pockethub.android.Database;
import com.github.pockethub.android.core.user.UserComparator;
import com.github.pockethub.android.persistence.AccountDataManager;
import com.github.pockethub.android.util.AvatarLoader;
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
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<AccountDataManager> accountDataManagerProvider;

  private final Provider<Database> databaseProvider;

  private final Provider<UserComparator> userComparatorProvider;

  private final Provider<AvatarLoader> avatarsProvider;

  public MainActivity_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<AccountDataManager> accountDataManagerProvider, Provider<Database> databaseProvider,
      Provider<UserComparator> userComparatorProvider, Provider<AvatarLoader> avatarsProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.accountDataManagerProvider = accountDataManagerProvider;
    this.databaseProvider = databaseProvider;
    this.userComparatorProvider = userComparatorProvider;
    this.avatarsProvider = avatarsProvider;
  }

  public static MembersInjector<MainActivity> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<AccountDataManager> accountDataManagerProvider, Provider<Database> databaseProvider,
      Provider<UserComparator> userComparatorProvider, Provider<AvatarLoader> avatarsProvider) {
    return new MainActivity_MembersInjector(androidInjectorProvider, accountDataManagerProvider, databaseProvider, userComparatorProvider, avatarsProvider);}

  @Override
  public void injectMembers(MainActivity instance) {
    DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectAccountDataManager(instance, accountDataManagerProvider.get());
    injectDatabase(instance, databaseProvider.get());
    injectUserComparatorProvider(instance, userComparatorProvider);
    injectAvatars(instance, avatarsProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.MainActivity.accountDataManager")
  public static void injectAccountDataManager(MainActivity instance,
      AccountDataManager accountDataManager) {
    instance.accountDataManager = accountDataManager;
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.MainActivity.database")
  public static void injectDatabase(MainActivity instance, Database database) {
    instance.database = database;
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.MainActivity.userComparatorProvider")
  public static void injectUserComparatorProvider(MainActivity instance,
      Provider<UserComparator> userComparatorProvider) {
    instance.userComparatorProvider = userComparatorProvider;
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.MainActivity.avatars")
  public static void injectAvatars(MainActivity instance, AvatarLoader avatars) {
    instance.avatars = avatars;
  }
}
