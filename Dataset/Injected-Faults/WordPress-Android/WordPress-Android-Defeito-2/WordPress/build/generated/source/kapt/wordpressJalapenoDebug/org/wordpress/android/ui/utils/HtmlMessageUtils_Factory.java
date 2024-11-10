package org.wordpress.android.ui.utils;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
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
public final class HtmlMessageUtils_Factory implements Factory<HtmlMessageUtils> {
  private final Provider<ResourceProvider> resourceProvider;

  public HtmlMessageUtils_Factory(Provider<ResourceProvider> resourceProvider) {
    this.resourceProvider = resourceProvider;
  }

  @Override
  public HtmlMessageUtils get() {
    return newInstance(resourceProvider.get());
  }

  public static HtmlMessageUtils_Factory create(Provider<ResourceProvider> resourceProvider) {
    return new HtmlMessageUtils_Factory(resourceProvider);
  }

  public static HtmlMessageUtils newInstance(ResourceProvider resourceProvider) {
    return new HtmlMessageUtils(resourceProvider);
  }
}
