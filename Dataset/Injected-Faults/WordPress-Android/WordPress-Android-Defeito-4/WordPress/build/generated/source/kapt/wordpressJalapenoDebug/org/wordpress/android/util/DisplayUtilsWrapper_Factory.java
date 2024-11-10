package org.wordpress.android.util;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.viewmodel.ContextProvider;

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
public final class DisplayUtilsWrapper_Factory implements Factory<DisplayUtilsWrapper> {
  private final Provider<ContextProvider> contextProvider;

  public DisplayUtilsWrapper_Factory(Provider<ContextProvider> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public DisplayUtilsWrapper get() {
    return newInstance(contextProvider.get());
  }

  public static DisplayUtilsWrapper_Factory create(Provider<ContextProvider> contextProvider) {
    return new DisplayUtilsWrapper_Factory(contextProvider);
  }

  public static DisplayUtilsWrapper newInstance(ContextProvider contextProvider) {
    return new DisplayUtilsWrapper(contextProvider);
  }
}
