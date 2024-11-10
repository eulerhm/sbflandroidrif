package org.wordpress.android.ui.posts.prepublishing;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.PostSchedulingNotificationStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.posts.PostSettingsUtils;
import org.wordpress.android.util.LocaleManagerWrapper;
import org.wordpress.android.viewmodel.ResourceProvider;

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
public final class PrepublishingPublishSettingsViewModel_Factory implements Factory<PrepublishingPublishSettingsViewModel> {
  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<PostSettingsUtils> postSettingsUtilsProvider;

  private final Provider<LocaleManagerWrapper> localeManagerWrapperProvider;

  private final Provider<PostSchedulingNotificationStore> postSchedulingNotificationStoreProvider;

  private final Provider<SiteStore> siteStoreProvider;

  public PrepublishingPublishSettingsViewModel_Factory(Provider<ResourceProvider> resourceProvider,
      Provider<PostSettingsUtils> postSettingsUtilsProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<PostSchedulingNotificationStore> postSchedulingNotificationStoreProvider,
      Provider<SiteStore> siteStoreProvider) {
    this.resourceProvider = resourceProvider;
    this.postSettingsUtilsProvider = postSettingsUtilsProvider;
    this.localeManagerWrapperProvider = localeManagerWrapperProvider;
    this.postSchedulingNotificationStoreProvider = postSchedulingNotificationStoreProvider;
    this.siteStoreProvider = siteStoreProvider;
  }

  @Override
  public PrepublishingPublishSettingsViewModel get() {
    return newInstance(resourceProvider.get(), postSettingsUtilsProvider.get(), localeManagerWrapperProvider.get(), postSchedulingNotificationStoreProvider.get(), siteStoreProvider.get());
  }

  public static PrepublishingPublishSettingsViewModel_Factory create(
      Provider<ResourceProvider> resourceProvider,
      Provider<PostSettingsUtils> postSettingsUtilsProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<PostSchedulingNotificationStore> postSchedulingNotificationStoreProvider,
      Provider<SiteStore> siteStoreProvider) {
    return new PrepublishingPublishSettingsViewModel_Factory(resourceProvider, postSettingsUtilsProvider, localeManagerWrapperProvider, postSchedulingNotificationStoreProvider, siteStoreProvider);
  }

  public static PrepublishingPublishSettingsViewModel newInstance(ResourceProvider resourceProvider,
      PostSettingsUtils postSettingsUtils, LocaleManagerWrapper localeManagerWrapper,
      PostSchedulingNotificationStore postSchedulingNotificationStore, SiteStore siteStore) {
    return new PrepublishingPublishSettingsViewModel(resourceProvider, postSettingsUtils, localeManagerWrapper, postSchedulingNotificationStore, siteStore);
  }
}
