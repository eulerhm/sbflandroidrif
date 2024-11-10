package com.github.pockethub.android.persistence;

import com.github.pockethub.android.Database;
import dagger.MembersInjector;
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
public final class DatabaseCache_MembersInjector implements MembersInjector<DatabaseCache> {
  private final Provider<Database> databaseProvider;

  public DatabaseCache_MembersInjector(Provider<Database> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  public static MembersInjector<DatabaseCache> create(Provider<Database> databaseProvider) {
    return new DatabaseCache_MembersInjector(databaseProvider);}

  @Override
  public void injectMembers(DatabaseCache instance) {
    injectDatabase(instance, databaseProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.persistence.DatabaseCache.database")
  public static void injectDatabase(DatabaseCache instance, Database database) {
    instance.database = database;
  }
}
