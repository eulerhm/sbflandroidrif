package org.wordpress.android.ui.sitecreation.sitename;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.internal.lifecycle.HiltViewModelMap.KeySet")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class SiteCreationSiteNameViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<String> {
  @Override
  public String get() {
    return provide();
  }

  public static SiteCreationSiteNameViewModel_HiltModules_KeyModule_ProvideFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static String provide() {
    return Preconditions.checkNotNullFromProvides(SiteCreationSiteNameViewModel_HiltModules.KeyModule.provide());
  }

  private static final class InstanceHolder {
    private static final SiteCreationSiteNameViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new SiteCreationSiteNameViewModel_HiltModules_KeyModule_ProvideFactory();
  }
}
