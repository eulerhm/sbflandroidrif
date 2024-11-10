package org.wordpress.android.workers.weeklyroundup;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;

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
public final class WeeklyRoundupScheduler_Factory implements Factory<WeeklyRoundupScheduler> {
  private final Provider<Context> contextProvider;

  public WeeklyRoundupScheduler_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public WeeklyRoundupScheduler get() {
    return newInstance(contextProvider.get());
  }

  public static WeeklyRoundupScheduler_Factory create(Provider<Context> contextProvider) {
    return new WeeklyRoundupScheduler_Factory(contextProvider);
  }

  public static WeeklyRoundupScheduler newInstance(Context context) {
    return new WeeklyRoundupScheduler(context);
  }
}
