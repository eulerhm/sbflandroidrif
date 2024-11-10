package org.wordpress.android.ui.domains;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.utils.UiHelpers;

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
public final class DomainsDashboardAdapter_Factory implements Factory<DomainsDashboardAdapter> {
  private final Provider<UiHelpers> uiHelpersProvider;

  public DomainsDashboardAdapter_Factory(Provider<UiHelpers> uiHelpersProvider) {
    this.uiHelpersProvider = uiHelpersProvider;
  }

  @Override
  public DomainsDashboardAdapter get() {
    return newInstance(uiHelpersProvider.get());
  }

  public static DomainsDashboardAdapter_Factory create(Provider<UiHelpers> uiHelpersProvider) {
    return new DomainsDashboardAdapter_Factory(uiHelpersProvider);
  }

  public static DomainsDashboardAdapter newInstance(UiHelpers uiHelpers) {
    return new DomainsDashboardAdapter(uiHelpers);
  }
}
