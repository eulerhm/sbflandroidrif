package org.wordpress.android.util;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;

@ScopeMetadata("dagger.Reusable")
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
public final class PhotonUtilsWrapper_Factory implements Factory<PhotonUtilsWrapper> {
  @Override
  public PhotonUtilsWrapper get() {
    return newInstance();
  }

  public static PhotonUtilsWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static PhotonUtilsWrapper newInstance() {
    return new PhotonUtilsWrapper();
  }

  private static final class InstanceHolder {
    private static final PhotonUtilsWrapper_Factory INSTANCE = new PhotonUtilsWrapper_Factory();
  }
}
