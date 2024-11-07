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
public final class AvatarLoader_Factory implements Factory<AvatarLoader> {
  private final Provider<Context> contextProvider;

  public AvatarLoader_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public AvatarLoader get() {
    return newInstance(contextProvider.get());
  }

  public static AvatarLoader_Factory create(Provider<Context> contextProvider) {
    return new AvatarLoader_Factory(contextProvider);
  }

  public static AvatarLoader newInstance(Context context) {
    return new AvatarLoader(context);
  }
}
