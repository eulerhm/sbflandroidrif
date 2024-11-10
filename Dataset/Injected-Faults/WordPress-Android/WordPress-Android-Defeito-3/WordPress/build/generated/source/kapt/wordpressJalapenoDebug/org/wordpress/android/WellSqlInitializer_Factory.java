package org.wordpress.android;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class WellSqlInitializer_Factory implements Factory<WellSqlInitializer> {
  private final Provider<Context> contextProvider;

  public WellSqlInitializer_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public WellSqlInitializer get() {
    return newInstance(contextProvider.get());
  }

  public static WellSqlInitializer_Factory create(Provider<Context> contextProvider) {
    return new WellSqlInitializer_Factory(contextProvider);
  }

  public static WellSqlInitializer newInstance(Context context) {
    return new WellSqlInitializer(context);
  }
}
