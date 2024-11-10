package org.wordpress.android.ui.stats.refresh;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider;

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
public final class StatsActivity_MembersInjector implements MembersInjector<StatsActivity> {
  private final Provider<StatsSiteProvider> statsSiteProvider;

  public StatsActivity_MembersInjector(Provider<StatsSiteProvider> statsSiteProvider) {
    this.statsSiteProvider = statsSiteProvider;
  }

  public static MembersInjector<StatsActivity> create(
      Provider<StatsSiteProvider> statsSiteProvider) {
    return new StatsActivity_MembersInjector(statsSiteProvider);
  }

  @Override
  public void injectMembers(StatsActivity instance) {
    injectStatsSiteProvider(instance, statsSiteProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.StatsActivity.statsSiteProvider")
  public static void injectStatsSiteProvider(StatsActivity instance,
      StatsSiteProvider statsSiteProvider) {
    instance.statsSiteProvider = statsSiteProvider;
  }
}
