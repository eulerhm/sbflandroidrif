package org.wordpress.android.ui.sitecreation.theme;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.DisplayUtilsWrapper;
import org.wordpress.android.util.config.SiteNameFeatureConfig;
import org.wordpress.android.util.image.ImageManager;

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
public final class HomePagePickerFragment_MembersInjector implements MembersInjector<HomePagePickerFragment> {
  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<DisplayUtilsWrapper> displayUtilsProvider;

  private final Provider<UiHelpers> uiHelperProvider;

  private final Provider<SiteNameFeatureConfig> siteNameFeatureConfigProvider;

  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<SiteDesignPickerDimensionProvider> thumbDimensionProvider;

  private final Provider<SiteDesignRecommendedDimensionProvider> recommendedDimensionProvider;

  public HomePagePickerFragment_MembersInjector(Provider<ImageManager> imageManagerProvider,
      Provider<DisplayUtilsWrapper> displayUtilsProvider, Provider<UiHelpers> uiHelperProvider,
      Provider<SiteNameFeatureConfig> siteNameFeatureConfigProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<SiteDesignPickerDimensionProvider> thumbDimensionProvider,
      Provider<SiteDesignRecommendedDimensionProvider> recommendedDimensionProvider) {
    this.imageManagerProvider = imageManagerProvider;
    this.displayUtilsProvider = displayUtilsProvider;
    this.uiHelperProvider = uiHelperProvider;
    this.siteNameFeatureConfigProvider = siteNameFeatureConfigProvider;
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.thumbDimensionProvider = thumbDimensionProvider;
    this.recommendedDimensionProvider = recommendedDimensionProvider;
  }

  public static MembersInjector<HomePagePickerFragment> create(
      Provider<ImageManager> imageManagerProvider,
      Provider<DisplayUtilsWrapper> displayUtilsProvider, Provider<UiHelpers> uiHelperProvider,
      Provider<SiteNameFeatureConfig> siteNameFeatureConfigProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<SiteDesignPickerDimensionProvider> thumbDimensionProvider,
      Provider<SiteDesignRecommendedDimensionProvider> recommendedDimensionProvider) {
    return new HomePagePickerFragment_MembersInjector(imageManagerProvider, displayUtilsProvider, uiHelperProvider, siteNameFeatureConfigProvider, viewModelFactoryProvider, thumbDimensionProvider, recommendedDimensionProvider);
  }

  @Override
  public void injectMembers(HomePagePickerFragment instance) {
    injectImageManager(instance, imageManagerProvider.get());
    injectDisplayUtils(instance, displayUtilsProvider.get());
    injectUiHelper(instance, uiHelperProvider.get());
    injectSiteNameFeatureConfig(instance, siteNameFeatureConfigProvider.get());
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectThumbDimensionProvider(instance, thumbDimensionProvider.get());
    injectRecommendedDimensionProvider(instance, recommendedDimensionProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.sitecreation.theme.HomePagePickerFragment.imageManager")
  public static void injectImageManager(HomePagePickerFragment instance,
      ImageManager imageManager) {
    instance.imageManager = imageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.sitecreation.theme.HomePagePickerFragment.displayUtils")
  public static void injectDisplayUtils(HomePagePickerFragment instance,
      DisplayUtilsWrapper displayUtils) {
    instance.displayUtils = displayUtils;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.sitecreation.theme.HomePagePickerFragment.uiHelper")
  public static void injectUiHelper(HomePagePickerFragment instance, UiHelpers uiHelper) {
    instance.uiHelper = uiHelper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.sitecreation.theme.HomePagePickerFragment.siteNameFeatureConfig")
  public static void injectSiteNameFeatureConfig(HomePagePickerFragment instance,
      SiteNameFeatureConfig siteNameFeatureConfig) {
    instance.siteNameFeatureConfig = siteNameFeatureConfig;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.sitecreation.theme.HomePagePickerFragment.viewModelFactory")
  public static void injectViewModelFactory(HomePagePickerFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.sitecreation.theme.HomePagePickerFragment.thumbDimensionProvider")
  public static void injectThumbDimensionProvider(HomePagePickerFragment instance,
      SiteDesignPickerDimensionProvider thumbDimensionProvider) {
    instance.thumbDimensionProvider = thumbDimensionProvider;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.sitecreation.theme.HomePagePickerFragment.recommendedDimensionProvider")
  public static void injectRecommendedDimensionProvider(HomePagePickerFragment instance,
      SiteDesignRecommendedDimensionProvider recommendedDimensionProvider) {
    instance.recommendedDimensionProvider = recommendedDimensionProvider;
  }
}
