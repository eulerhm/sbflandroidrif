package org.wordpress.android.ui.stats.refresh.lists.widget.minified;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;

@ScopeMetadata
@QualifierMetadata("javax.inject.Named")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class StatsMinifiedWidgetConfigureViewModel_Factory implements Factory<StatsMinifiedWidgetConfigureViewModel> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  public StatsMinifiedWidgetConfigureViewModel_Factory(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
  }

  @Override
  public StatsMinifiedWidgetConfigureViewModel get() {
    return newInstance(mainDispatcherProvider.get(), appPrefsWrapperProvider.get());
  }

  public static StatsMinifiedWidgetConfigureViewModel_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider) {
    return new StatsMinifiedWidgetConfigureViewModel_Factory(mainDispatcherProvider, appPrefsWrapperProvider);
  }

  public static StatsMinifiedWidgetConfigureViewModel newInstance(
      CoroutineDispatcher mainDispatcher, AppPrefsWrapper appPrefsWrapper) {
    return new StatsMinifiedWidgetConfigureViewModel(mainDispatcher, appPrefsWrapper);
  }
}
