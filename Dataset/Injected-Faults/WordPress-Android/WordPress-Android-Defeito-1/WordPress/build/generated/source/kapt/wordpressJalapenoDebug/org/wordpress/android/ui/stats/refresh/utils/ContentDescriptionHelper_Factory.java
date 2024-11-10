package org.wordpress.android.ui.stats.refresh.utils;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.RtlUtils;
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
public final class ContentDescriptionHelper_Factory implements Factory<ContentDescriptionHelper> {
  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<RtlUtils> rtlUtilsProvider;

  public ContentDescriptionHelper_Factory(Provider<ResourceProvider> resourceProvider,
      Provider<RtlUtils> rtlUtilsProvider) {
    this.resourceProvider = resourceProvider;
    this.rtlUtilsProvider = rtlUtilsProvider;
  }

  @Override
  public ContentDescriptionHelper get() {
    return newInstance(resourceProvider.get(), rtlUtilsProvider.get());
  }

  public static ContentDescriptionHelper_Factory create(Provider<ResourceProvider> resourceProvider,
      Provider<RtlUtils> rtlUtilsProvider) {
    return new ContentDescriptionHelper_Factory(resourceProvider, rtlUtilsProvider);
  }

  public static ContentDescriptionHelper newInstance(ResourceProvider resourceProvider,
      RtlUtils rtlUtils) {
    return new ContentDescriptionHelper(resourceProvider, rtlUtils);
  }
}
