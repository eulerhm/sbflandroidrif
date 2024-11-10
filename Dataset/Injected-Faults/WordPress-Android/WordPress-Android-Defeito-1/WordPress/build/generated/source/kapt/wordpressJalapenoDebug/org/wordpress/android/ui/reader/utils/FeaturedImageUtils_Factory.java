package org.wordpress.android.ui.reader.utils;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.PhotonUtilsWrapper;

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
public final class FeaturedImageUtils_Factory implements Factory<FeaturedImageUtils> {
  private final Provider<PhotonUtilsWrapper> photonUtilsWrapperProvider;

  public FeaturedImageUtils_Factory(Provider<PhotonUtilsWrapper> photonUtilsWrapperProvider) {
    this.photonUtilsWrapperProvider = photonUtilsWrapperProvider;
  }

  @Override
  public FeaturedImageUtils get() {
    return newInstance(photonUtilsWrapperProvider.get());
  }

  public static FeaturedImageUtils_Factory create(
      Provider<PhotonUtilsWrapper> photonUtilsWrapperProvider) {
    return new FeaturedImageUtils_Factory(photonUtilsWrapperProvider);
  }

  public static FeaturedImageUtils newInstance(PhotonUtilsWrapper photonUtilsWrapper) {
    return new FeaturedImageUtils(photonUtilsWrapper);
  }
}
