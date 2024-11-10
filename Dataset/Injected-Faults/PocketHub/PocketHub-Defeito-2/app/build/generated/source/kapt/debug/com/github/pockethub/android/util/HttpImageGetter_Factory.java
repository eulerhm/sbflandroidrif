package com.github.pockethub.android.util;

import android.content.Context;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class HttpImageGetter_Factory implements Factory<HttpImageGetter> {
  private final Provider<Context> contextProvider;

  public HttpImageGetter_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public HttpImageGetter get() {
    return newInstance(contextProvider.get());
  }

  public static HttpImageGetter_Factory create(Provider<Context> contextProvider) {
    return new HttpImageGetter_Factory(contextProvider);
  }

  public static HttpImageGetter newInstance(Context context) {
    return new HttpImageGetter(context);
  }
}
