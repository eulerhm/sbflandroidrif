package org.wordpress.android.ui.prefs;

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
public final class SiteSettingsInterfaceWrapper_Factory_Factory implements Factory<SiteSettingsInterfaceWrapper.Factory> {
  private final Provider<ContextProvider> contextProvider;

  public SiteSettingsInterfaceWrapper_Factory_Factory(Provider<ContextProvider> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public SiteSettingsInterfaceWrapper.Factory get() {
    return newInstance(contextProvider.get());
  }

  public static SiteSettingsInterfaceWrapper_Factory_Factory create(
      Provider<ContextProvider> contextProvider) {
    return new SiteSettingsInterfaceWrapper_Factory_Factory(contextProvider);
  }

  public static SiteSettingsInterfaceWrapper.Factory newInstance(ContextProvider contextProvider) {
    return new SiteSettingsInterfaceWrapper.Factory(contextProvider);
  }
}
