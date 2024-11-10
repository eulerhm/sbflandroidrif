package org.wordpress.android.ui.stats.refresh;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class StatsModule_ProvideSharedPrefsFactory implements Factory<SharedPreferences> {
  private final StatsModule module;

  private final Provider<Context> contextProvider;

  public StatsModule_ProvideSharedPrefsFactory(StatsModule module,
      Provider<Context> contextProvider) {
    this.module = module;
    this.contextProvider = contextProvider;
  }

  @Override
  public SharedPreferences get() {
    return provideSharedPrefs(module, contextProvider.get());
  }

  public static StatsModule_ProvideSharedPrefsFactory create(StatsModule module,
      Provider<Context> contextProvider) {
    return new StatsModule_ProvideSharedPrefsFactory(module, contextProvider);
  }

  public static SharedPreferences provideSharedPrefs(StatsModule instance, Context context) {
    return Preconditions.checkNotNullFromProvides(instance.provideSharedPrefs(context));
  }
}
