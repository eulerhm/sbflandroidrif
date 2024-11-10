package com.github.pockethub.android.persistence;

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
public final class Organizations_Factory implements Factory<Organizations> {
  private final Provider<Context> contextProvider;

  public Organizations_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public Organizations get() {
    return newInstance(contextProvider.get());
  }

  public static Organizations_Factory create(Provider<Context> contextProvider) {
    return new Organizations_Factory(contextProvider);
  }

  public static Organizations newInstance(Context context) {
    return new Organizations(context);
  }
}
