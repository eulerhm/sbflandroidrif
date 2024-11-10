package org.wordpress.android.ui.posts;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.PostSchedulingNotificationStore;
import org.wordpress.android.fluxc.store.SiteStore;
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
public final class EditPostPublishSettingsViewModel_Factory implements Factory<EditPostPublishSettingsViewModel> {
  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<PostSettingsUtils> postSettingsUtilsProvider;

  private final Provider<LocaleManagerWrapper> localeManagerWrapperProvider;

  private final Provider<PostSchedulingNotificationStore> postSchedulingNotificationStoreProvider;

  private final Provider<SiteStore> siteStoreProvider;

  public EditPostPublishSettingsViewModel_Factory(Provider<ResourceProvider> resourceProvider,
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
  public EditPostPublishSettingsViewModel get() {
    return newInstance(resourceProvider.get(), postSettingsUtilsProvider.get(), localeManagerWrapperProvider.get(), postSchedulingNotificationStoreProvider.get(), siteStoreProvider.get());
  }

  public static EditPostPublishSettingsViewModel_Factory create(
      Provider<ResourceProvider> resourceProvider,
      Provider<PostSettingsUtils> postSettingsUtilsProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<PostSchedulingNotificationStore> postSchedulingNotificationStoreProvider,
      Provider<SiteStore> siteStoreProvider) {
    return new EditPostPublishSettingsViewModel_Factory(resourceProvider, postSettingsUtilsProvider, localeManagerWrapperProvider, postSchedulingNotificationStoreProvider, siteStoreProvider);
  }

  public static EditPostPublishSettingsViewModel newInstance(ResourceProvider resourceProvider,
      PostSettingsUtils postSettingsUtils, LocaleManagerWrapper localeManagerWrapper,
      PostSchedulingNotificationStore postSchedulingNotificationStore, SiteStore siteStore) {
    return new EditPostPublishSettingsViewModel(resourceProvider, postSettingsUtils, localeManagerWrapper, postSchedulingNotificationStore, siteStore);
  }
}
