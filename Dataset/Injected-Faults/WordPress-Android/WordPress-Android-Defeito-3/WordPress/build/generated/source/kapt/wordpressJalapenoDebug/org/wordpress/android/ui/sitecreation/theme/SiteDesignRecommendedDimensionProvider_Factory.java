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
public final class SiteDesignRecommendedDimensionProvider_Factory implements Factory<SiteDesignRecommendedDimensionProvider> {
  private final Provider<ContextProvider> contextProvider;

  public SiteDesignRecommendedDimensionProvider_Factory(Provider<ContextProvider> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public SiteDesignRecommendedDimensionProvider get() {
    return newInstance(contextProvider.get());
  }

  public static SiteDesignRecommendedDimensionProvider_Factory create(
      Provider<ContextProvider> contextProvider) {
    return new SiteDesignRecommendedDimensionProvider_Factory(contextProvider);
  }

  public static SiteDesignRecommendedDimensionProvider newInstance(
      ContextProvider contextProvider) {
    return new SiteDesignRecommendedDimensionProvider(contextProvider);
  }
}
