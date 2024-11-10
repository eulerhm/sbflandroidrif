package org.wordpress.android.ui.sitecreation.theme;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.layoutpicker.LayoutPreviewFragment_MembersInjector;
import org.wordpress.android.util.DisplayUtilsWrapper;
import org.wordpress.android.util.config.SiteNameFeatureConfig;

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
public final class DesignPreviewFragment_MembersInjector implements MembersInjector<DesignPreviewFragment> {
  private final Provider<DisplayUtilsWrapper> displayUtilsWrapperProvider;

  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<SiteNameFeatureConfig> siteNameFeatureConfigProvider;

  public DesignPreviewFragment_MembersInjector(
      Provider<DisplayUtilsWrapper> displayUtilsWrapperProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<SiteNameFeatureConfig> siteNameFeatureConfigProvider) {
    this.displayUtilsWrapperProvider = displayUtilsWrapperProvider;
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.siteNameFeatureConfigProvider = siteNameFeatureConfigProvider;
  }

  public static MembersInjector<DesignPreviewFragment> create(
      Provider<DisplayUtilsWrapper> displayUtilsWrapperProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<SiteNameFeatureConfig> siteNameFeatureConfigProvider) {
    return new DesignPreviewFragment_MembersInjector(displayUtilsWrapperProvider, viewModelFactoryProvider, siteNameFeatureConfigProvider);
  }

  @Override
  public void injectMembers(DesignPreviewFragment instance) {
    LayoutPreviewFragment_MembersInjector.injectDisplayUtilsWrapper(instance, displayUtilsWrapperProvider.get());
    LayoutPreviewFragment_MembersInjector.injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectSiteNameFeatureConfig(instance, siteNameFeatureConfigProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.sitecreation.theme.DesignPreviewFragment.siteNameFeatureConfig")
  public static void injectSiteNameFeatureConfig(DesignPreviewFragment instance,
      SiteNameFeatureConfig siteNameFeatureConfig) {
    instance.siteNameFeatureConfig = siteNameFeatureConfig;
  }
}
