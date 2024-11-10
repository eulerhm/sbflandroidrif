package org.wordpress.android.ui.reader.discover;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.viewmodel.ContextProvider;
import org.wordpress.android.viewmodel.ResourceProvider;

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
public final class ReaderPostTagsUiStateBuilder_Factory implements Factory<ReaderPostTagsUiStateBuilder> {
  private final Provider<ContextProvider> contextProvider;

  private final Provider<ResourceProvider> resourceProvider;

  public ReaderPostTagsUiStateBuilder_Factory(Provider<ContextProvider> contextProvider,
      Provider<ResourceProvider> resourceProvider) {
    this.contextProvider = contextProvider;
    this.resourceProvider = resourceProvider;
  }

  @Override
  public ReaderPostTagsUiStateBuilder get() {
    return newInstance(contextProvider.get(), resourceProvider.get());
  }

  public static ReaderPostTagsUiStateBuilder_Factory create(
      Provider<ContextProvider> contextProvider, Provider<ResourceProvider> resourceProvider) {
    return new ReaderPostTagsUiStateBuilder_Factory(contextProvider, resourceProvider);
  }

  public static ReaderPostTagsUiStateBuilder newInstance(ContextProvider contextProvider,
      ResourceProvider resourceProvider) {
    return new ReaderPostTagsUiStateBuilder(contextProvider, resourceProvider);
  }
}
