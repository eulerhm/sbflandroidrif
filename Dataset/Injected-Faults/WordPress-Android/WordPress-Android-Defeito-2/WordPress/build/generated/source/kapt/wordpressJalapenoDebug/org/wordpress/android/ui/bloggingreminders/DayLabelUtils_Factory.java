package org.wordpress.android.ui.bloggingreminders;

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
public final class DayLabelUtils_Factory implements Factory<DayLabelUtils> {
  private final Provider<ResourceProvider> resourceProvider;

  public DayLabelUtils_Factory(Provider<ResourceProvider> resourceProvider) {
    this.resourceProvider = resourceProvider;
  }

  @Override
  public DayLabelUtils get() {
    return newInstance(resourceProvider.get());
  }

  public static DayLabelUtils_Factory create(Provider<ResourceProvider> resourceProvider) {
    return new DayLabelUtils_Factory(resourceProvider);
  }

  public static DayLabelUtils newInstance(ResourceProvider resourceProvider) {
    return new DayLabelUtils(resourceProvider);
  }
}
