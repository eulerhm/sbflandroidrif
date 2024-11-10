package org.wordpress.android.workers.weeklyroundup;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.stats.time.VisitsAndViewsStore;

@ScopeMetadata
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
public final class WeeklyRoundupRepository_Factory implements Factory<WeeklyRoundupRepository> {
  private final Provider<VisitsAndViewsStore> visitsAndViewsStoreProvider;

  public WeeklyRoundupRepository_Factory(
      Provider<VisitsAndViewsStore> visitsAndViewsStoreProvider) {
    this.visitsAndViewsStoreProvider = visitsAndViewsStoreProvider;
  }

  @Override
  public WeeklyRoundupRepository get() {
    return newInstance(visitsAndViewsStoreProvider.get());
  }

  public static WeeklyRoundupRepository_Factory create(
      Provider<VisitsAndViewsStore> visitsAndViewsStoreProvider) {
    return new WeeklyRoundupRepository_Factory(visitsAndViewsStoreProvider);
  }

  public static WeeklyRoundupRepository newInstance(VisitsAndViewsStore visitsAndViewsStore) {
    return new WeeklyRoundupRepository(visitsAndViewsStore);
  }
}
