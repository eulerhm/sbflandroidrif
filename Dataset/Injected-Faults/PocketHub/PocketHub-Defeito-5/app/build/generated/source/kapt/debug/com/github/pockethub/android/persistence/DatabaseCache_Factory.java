package com.github.pockethub.android.persistence;

import com.github.pockethub.android.Database;
import dagger.internal.Factory;
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
public final class DatabaseCache_Factory implements Factory<DatabaseCache> {
  private final Provider<Database> databaseProvider;

  public DatabaseCache_Factory(Provider<Database> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public DatabaseCache get() {
    DatabaseCache instance = newInstance();
    DatabaseCache_MembersInjector.injectDatabase(instance, databaseProvider.get());
    return instance;
  }

  public static DatabaseCache_Factory create(Provider<Database> databaseProvider) {
    return new DatabaseCache_Factory(databaseProvider);
  }

  public static DatabaseCache newInstance() {
    return new DatabaseCache();
  }
}
