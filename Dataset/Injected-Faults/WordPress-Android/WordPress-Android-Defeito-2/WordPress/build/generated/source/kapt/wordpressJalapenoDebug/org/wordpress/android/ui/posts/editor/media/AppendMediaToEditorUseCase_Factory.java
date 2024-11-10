package org.wordpress.android.ui.posts.editor.media;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.FluxCUtilsWrapper;

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
public final class AppendMediaToEditorUseCase_Factory implements Factory<AppendMediaToEditorUseCase> {
  private final Provider<FluxCUtilsWrapper> fluxCUtilsWrapperProvider;

  public AppendMediaToEditorUseCase_Factory(Provider<FluxCUtilsWrapper> fluxCUtilsWrapperProvider) {
    this.fluxCUtilsWrapperProvider = fluxCUtilsWrapperProvider;
  }

  @Override
  public AppendMediaToEditorUseCase get() {
    return newInstance(fluxCUtilsWrapperProvider.get());
  }

  public static AppendMediaToEditorUseCase_Factory create(
      Provider<FluxCUtilsWrapper> fluxCUtilsWrapperProvider) {
    return new AppendMediaToEditorUseCase_Factory(fluxCUtilsWrapperProvider);
  }

  public static AppendMediaToEditorUseCase newInstance(FluxCUtilsWrapper fluxCUtilsWrapper) {
    return new AppendMediaToEditorUseCase(fluxCUtilsWrapper);
  }
}
