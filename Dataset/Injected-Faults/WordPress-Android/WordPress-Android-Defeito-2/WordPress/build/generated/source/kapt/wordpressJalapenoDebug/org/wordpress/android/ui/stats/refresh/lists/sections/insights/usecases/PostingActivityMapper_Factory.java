package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
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
public final class PostingActivityMapper_Factory implements Factory<PostingActivityMapper> {
  private final Provider<LocaleManagerWrapper> localeManagerWrapperProvider;

  private final Provider<ResourceProvider> resourceProvider;

  public PostingActivityMapper_Factory(Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<ResourceProvider> resourceProvider) {
    this.localeManagerWrapperProvider = localeManagerWrapperProvider;
    this.resourceProvider = resourceProvider;
  }

  @Override
  public PostingActivityMapper get() {
    return newInstance(localeManagerWrapperProvider.get(), resourceProvider.get());
  }

  public static PostingActivityMapper_Factory create(
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<ResourceProvider> resourceProvider) {
    return new PostingActivityMapper_Factory(localeManagerWrapperProvider, resourceProvider);
  }

  public static PostingActivityMapper newInstance(LocaleManagerWrapper localeManagerWrapper,
      ResourceProvider resourceProvider) {
    return new PostingActivityMapper(localeManagerWrapper, resourceProvider);
  }
}
