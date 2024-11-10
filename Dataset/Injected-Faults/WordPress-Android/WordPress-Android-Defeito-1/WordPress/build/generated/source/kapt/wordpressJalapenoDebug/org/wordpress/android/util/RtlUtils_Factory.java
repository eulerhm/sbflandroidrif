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
public final class RtlUtils_Factory implements Factory<RtlUtils> {
  private final Provider<Context> contextProvider;

  public RtlUtils_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public RtlUtils get() {
    return newInstance(contextProvider.get());
  }

  public static RtlUtils_Factory create(Provider<Context> contextProvider) {
    return new RtlUtils_Factory(contextProvider);
  }

  public static RtlUtils newInstance(Context context) {
    return new RtlUtils(context);
  }
}
