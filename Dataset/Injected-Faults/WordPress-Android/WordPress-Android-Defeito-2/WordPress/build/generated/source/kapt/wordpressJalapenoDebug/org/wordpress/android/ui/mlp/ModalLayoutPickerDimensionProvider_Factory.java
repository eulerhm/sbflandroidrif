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
public final class ModalLayoutPickerDimensionProvider_Factory implements Factory<ModalLayoutPickerDimensionProvider> {
  private final Provider<ContextProvider> contextProvider;

  public ModalLayoutPickerDimensionProvider_Factory(Provider<ContextProvider> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ModalLayoutPickerDimensionProvider get() {
    return newInstance(contextProvider.get());
  }

  public static ModalLayoutPickerDimensionProvider_Factory create(
      Provider<ContextProvider> contextProvider) {
    return new ModalLayoutPickerDimensionProvider_Factory(contextProvider);
  }

  public static ModalLayoutPickerDimensionProvider newInstance(ContextProvider contextProvider) {
    return new ModalLayoutPickerDimensionProvider(contextProvider);
  }
}
