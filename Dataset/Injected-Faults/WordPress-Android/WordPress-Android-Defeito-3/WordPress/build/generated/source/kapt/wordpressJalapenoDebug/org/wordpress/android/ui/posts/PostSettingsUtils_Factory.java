package org.wordpress.android.ui.posts;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.stats.refresh.utils.DateUtils;
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
public final class PostSettingsUtils_Factory implements Factory<PostSettingsUtils> {
  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<DateUtils> dateUtilsProvider;

  private final Provider<PostUtilsWrapper> postUtilsWrapperProvider;

  public PostSettingsUtils_Factory(Provider<ResourceProvider> resourceProvider,
      Provider<DateUtils> dateUtilsProvider, Provider<PostUtilsWrapper> postUtilsWrapperProvider) {
    this.resourceProvider = resourceProvider;
    this.dateUtilsProvider = dateUtilsProvider;
    this.postUtilsWrapperProvider = postUtilsWrapperProvider;
  }

  @Override
  public PostSettingsUtils get() {
    return newInstance(resourceProvider.get(), dateUtilsProvider.get(), postUtilsWrapperProvider.get());
  }

  public static PostSettingsUtils_Factory create(Provider<ResourceProvider> resourceProvider,
      Provider<DateUtils> dateUtilsProvider, Provider<PostUtilsWrapper> postUtilsWrapperProvider) {
    return new PostSettingsUtils_Factory(resourceProvider, dateUtilsProvider, postUtilsWrapperProvider);
  }

  public static PostSettingsUtils newInstance(ResourceProvider resourceProvider,
      DateUtils dateUtils, PostUtilsWrapper postUtilsWrapper) {
    return new PostSettingsUtils(resourceProvider, dateUtils, postUtilsWrapper);
  }
}
