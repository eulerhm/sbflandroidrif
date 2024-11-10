package org.wordpress.android.ui.jetpack.common;

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
public final class CheckboxSpannableLabel_Factory implements Factory<CheckboxSpannableLabel> {
  private final Provider<ResourceProvider> resourceProvider;

  public CheckboxSpannableLabel_Factory(Provider<ResourceProvider> resourceProvider) {
    this.resourceProvider = resourceProvider;
  }

  @Override
  public CheckboxSpannableLabel get() {
    return newInstance(resourceProvider.get());
  }

  public static CheckboxSpannableLabel_Factory create(Provider<ResourceProvider> resourceProvider) {
    return new CheckboxSpannableLabel_Factory(resourceProvider);
  }

  public static CheckboxSpannableLabel newInstance(ResourceProvider resourceProvider) {
    return new CheckboxSpannableLabel(resourceProvider);
  }
}
