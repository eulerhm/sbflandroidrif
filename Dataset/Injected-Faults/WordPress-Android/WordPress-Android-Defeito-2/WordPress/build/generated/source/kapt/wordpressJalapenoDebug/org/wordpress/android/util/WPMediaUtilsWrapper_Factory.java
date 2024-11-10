package org.wordpress.android.util;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;

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
public final class WPMediaUtilsWrapper_Factory implements Factory<WPMediaUtilsWrapper> {
  private final Provider<Context> contextProvider;

  public WPMediaUtilsWrapper_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public WPMediaUtilsWrapper get() {
    return newInstance(contextProvider.get());
  }

  public static WPMediaUtilsWrapper_Factory create(Provider<Context> contextProvider) {
    return new WPMediaUtilsWrapper_Factory(contextProvider);
  }

  public static WPMediaUtilsWrapper newInstance(Context context) {
    return new WPMediaUtilsWrapper(context);
  }
}
