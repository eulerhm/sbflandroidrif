package org.wordpress.android.ui.main.utils;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.viewmodel.ResourceProvider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class MeGravatarLoader_Factory implements Factory<MeGravatarLoader> {
  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<ResourceProvider> resourseProvider;

  public MeGravatarLoader_Factory(Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<ImageManager> imageManagerProvider, Provider<ResourceProvider> resourseProvider) {
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
    this.imageManagerProvider = imageManagerProvider;
    this.resourseProvider = resourseProvider;
  }

  @Override
  public MeGravatarLoader get() {
    return newInstance(appPrefsWrapperProvider.get(), imageManagerProvider.get(), resourseProvider.get());
  }

  public static MeGravatarLoader_Factory create(Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<ImageManager> imageManagerProvider, Provider<ResourceProvider> resourseProvider) {
    return new MeGravatarLoader_Factory(appPrefsWrapperProvider, imageManagerProvider, resourseProvider);
  }

  public static MeGravatarLoader newInstance(AppPrefsWrapper appPrefsWrapper,
      ImageManager imageManager, ResourceProvider resourseProvider) {
    return new MeGravatarLoader(appPrefsWrapper, imageManager, resourseProvider);
  }
}
