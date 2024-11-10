package org.wordpress.android.viewmodel.pages;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;

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
public final class PageItemProgressUiStateUseCase_Factory implements Factory<PageItemProgressUiStateUseCase> {
  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  public PageItemProgressUiStateUseCase_Factory(Provider<AppPrefsWrapper> appPrefsWrapperProvider) {
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
  }

  @Override
  public PageItemProgressUiStateUseCase get() {
    return newInstance(appPrefsWrapperProvider.get());
  }

  public static PageItemProgressUiStateUseCase_Factory create(
      Provider<AppPrefsWrapper> appPrefsWrapperProvider) {
    return new PageItemProgressUiStateUseCase_Factory(appPrefsWrapperProvider);
  }

  public static PageItemProgressUiStateUseCase newInstance(AppPrefsWrapper appPrefsWrapper) {
    return new PageItemProgressUiStateUseCase(appPrefsWrapper);
  }
}
