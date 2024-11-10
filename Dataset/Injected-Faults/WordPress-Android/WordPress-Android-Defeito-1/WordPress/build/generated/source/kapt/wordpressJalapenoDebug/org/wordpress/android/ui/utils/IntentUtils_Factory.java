package org.wordpress.android.ui.utils;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.viewmodel.ContextProvider;

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
public final class IntentUtils_Factory implements Factory<IntentUtils> {
  private final Provider<ContextProvider> contextProvider;

  public IntentUtils_Factory(Provider<ContextProvider> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public IntentUtils get() {
    return newInstance(contextProvider.get());
  }

  public static IntentUtils_Factory create(Provider<ContextProvider> contextProvider) {
    return new IntentUtils_Factory(contextProvider);
  }

  public static IntentUtils newInstance(ContextProvider contextProvider) {
    return new IntentUtils(contextProvider);
  }
}
