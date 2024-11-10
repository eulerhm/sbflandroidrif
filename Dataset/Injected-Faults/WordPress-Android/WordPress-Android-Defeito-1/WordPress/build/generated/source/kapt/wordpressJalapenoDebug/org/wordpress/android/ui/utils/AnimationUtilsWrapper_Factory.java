package org.wordpress.android.ui.utils;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;

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
public final class AnimationUtilsWrapper_Factory implements Factory<AnimationUtilsWrapper> {
  @Override
  public AnimationUtilsWrapper get() {
    return newInstance();
  }

  public static AnimationUtilsWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AnimationUtilsWrapper newInstance() {
    return new AnimationUtilsWrapper();
  }

  private static final class InstanceHolder {
    private static final AnimationUtilsWrapper_Factory INSTANCE = new AnimationUtilsWrapper_Factory();
  }
}
