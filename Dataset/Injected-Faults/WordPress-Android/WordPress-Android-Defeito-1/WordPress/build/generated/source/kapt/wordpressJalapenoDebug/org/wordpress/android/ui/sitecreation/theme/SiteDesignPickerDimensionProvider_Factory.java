package org.wordpress.android.ui.sitecreation.theme;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.viewmodel.ContextProvider;

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
public final class SiteDesignPickerDimensionProvider_Factory implements Factory<SiteDesignPickerDimensionProvider> {
  private final Provider<ContextProvider> contextProvider;

  public SiteDesignPickerDimensionProvider_Factory(Provider<ContextProvider> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public SiteDesignPickerDimensionProvider get() {
    return newInstance(contextProvider.get());
  }

  public static SiteDesignPickerDimensionProvider_Factory create(
      Provider<ContextProvider> contextProvider) {
    return new SiteDesignPickerDimensionProvider_Factory(contextProvider);
  }

  public static SiteDesignPickerDimensionProvider newInstance(ContextProvider contextProvider) {
    return new SiteDesignPickerDimensionProvider(contextProvider);
  }
}
