package org.wordpress.android.ui.mlp;

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
public final class SupportedBlocksProvider_Factory implements Factory<SupportedBlocksProvider> {
  private final Provider<ContextProvider> contextProvider;

  public SupportedBlocksProvider_Factory(Provider<ContextProvider> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public SupportedBlocksProvider get() {
    return newInstance(contextProvider.get());
  }

  public static SupportedBlocksProvider_Factory create(Provider<ContextProvider> contextProvider) {
    return new SupportedBlocksProvider_Factory(contextProvider);
  }

  public static SupportedBlocksProvider newInstance(ContextProvider contextProvider) {
    return new SupportedBlocksProvider(contextProvider);
  }
}
