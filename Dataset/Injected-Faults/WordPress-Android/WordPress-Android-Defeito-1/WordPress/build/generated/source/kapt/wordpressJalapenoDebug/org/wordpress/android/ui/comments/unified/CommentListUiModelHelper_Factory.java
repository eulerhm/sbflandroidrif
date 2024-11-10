package org.wordpress.android.ui.comments.unified;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.DateTimeUtilsWrapper;
import org.wordpress.android.util.NetworkUtilsWrapper;
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
public final class CommentListUiModelHelper_Factory implements Factory<CommentListUiModelHelper> {
  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<DateTimeUtilsWrapper> dateTimeUtilsWrapperProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  public CommentListUiModelHelper_Factory(Provider<ResourceProvider> resourceProvider,
      Provider<DateTimeUtilsWrapper> dateTimeUtilsWrapperProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider) {
    this.resourceProvider = resourceProvider;
    this.dateTimeUtilsWrapperProvider = dateTimeUtilsWrapperProvider;
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
  }

  @Override
  public CommentListUiModelHelper get() {
    return newInstance(resourceProvider.get(), dateTimeUtilsWrapperProvider.get(), networkUtilsWrapperProvider.get());
  }

  public static CommentListUiModelHelper_Factory create(Provider<ResourceProvider> resourceProvider,
      Provider<DateTimeUtilsWrapper> dateTimeUtilsWrapperProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider) {
    return new CommentListUiModelHelper_Factory(resourceProvider, dateTimeUtilsWrapperProvider, networkUtilsWrapperProvider);
  }

  public static CommentListUiModelHelper newInstance(ResourceProvider resourceProvider,
      DateTimeUtilsWrapper dateTimeUtilsWrapper, NetworkUtilsWrapper networkUtilsWrapper) {
    return new CommentListUiModelHelper(resourceProvider, dateTimeUtilsWrapper, networkUtilsWrapper);
  }
}
