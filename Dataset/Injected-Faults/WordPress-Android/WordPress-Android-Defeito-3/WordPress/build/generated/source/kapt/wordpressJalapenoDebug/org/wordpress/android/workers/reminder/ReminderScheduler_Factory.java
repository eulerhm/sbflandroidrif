package org.wordpress.android.workers.reminder;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.viewmodel.ContextProvider;

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
public final class ReminderScheduler_Factory implements Factory<ReminderScheduler> {
  private final Provider<ContextProvider> contextProvider;

  public ReminderScheduler_Factory(Provider<ContextProvider> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ReminderScheduler get() {
    return newInstance(contextProvider.get());
  }

  public static ReminderScheduler_Factory create(Provider<ContextProvider> contextProvider) {
    return new ReminderScheduler_Factory(contextProvider);
  }

  public static ReminderScheduler newInstance(ContextProvider contextProvider) {
    return new ReminderScheduler(contextProvider);
  }
}
