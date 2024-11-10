package org.wordpress.android.ui.whatsnew;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.WhatsNewStore;
import org.wordpress.android.util.BuildConfigWrapper;

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
public final class FeatureAnnouncementProvider_Factory implements Factory<FeatureAnnouncementProvider> {
  private final Provider<WhatsNewStore> whatsNewStoreProvider;

  private final Provider<BuildConfigWrapper> buildConfigWrapperProvider;

  public FeatureAnnouncementProvider_Factory(Provider<WhatsNewStore> whatsNewStoreProvider,
      Provider<BuildConfigWrapper> buildConfigWrapperProvider) {
    this.whatsNewStoreProvider = whatsNewStoreProvider;
    this.buildConfigWrapperProvider = buildConfigWrapperProvider;
  }

  @Override
  public FeatureAnnouncementProvider get() {
    return newInstance(whatsNewStoreProvider.get(), buildConfigWrapperProvider.get());
  }

  public static FeatureAnnouncementProvider_Factory create(
      Provider<WhatsNewStore> whatsNewStoreProvider,
      Provider<BuildConfigWrapper> buildConfigWrapperProvider) {
    return new FeatureAnnouncementProvider_Factory(whatsNewStoreProvider, buildConfigWrapperProvider);
  }

  public static FeatureAnnouncementProvider newInstance(WhatsNewStore whatsNewStore,
      BuildConfigWrapper buildConfigWrapper) {
    return new FeatureAnnouncementProvider(whatsNewStore, buildConfigWrapper);
  }
}
