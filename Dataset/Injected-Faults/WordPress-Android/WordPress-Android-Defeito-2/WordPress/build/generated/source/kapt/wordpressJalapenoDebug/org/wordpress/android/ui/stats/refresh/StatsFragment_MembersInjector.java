package org.wordpress.android.ui.stats.refresh;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.utils.UiHelpers;

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
public final class StatsFragment_MembersInjector implements MembersInjector<StatsFragment> {
  private final Provider<UiHelpers> uiHelpersProvider;

  public StatsFragment_MembersInjector(Provider<UiHelpers> uiHelpersProvider) {
    this.uiHelpersProvider = uiHelpersProvider;
  }

  public static MembersInjector<StatsFragment> create(Provider<UiHelpers> uiHelpersProvider) {
    return new StatsFragment_MembersInjector(uiHelpersProvider);
  }

  @Override
  public void injectMembers(StatsFragment instance) {
    injectUiHelpers(instance, uiHelpersProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.StatsFragment.uiHelpers")
  public static void injectUiHelpers(StatsFragment instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }
}
