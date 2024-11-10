package org.wordpress.android.util;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.widgets.WPSnackbarWrapper;

@ScopeMetadata("javax.inject.Singleton")
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
public final class SnackbarSequencer_Factory implements Factory<SnackbarSequencer> {
  private final Provider<UiHelpers> uiHelperProvider;

  private final Provider<WPSnackbarWrapper> wpSnackbarWrapperProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  public SnackbarSequencer_Factory(Provider<UiHelpers> uiHelperProvider,
      Provider<WPSnackbarWrapper> wpSnackbarWrapperProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    this.uiHelperProvider = uiHelperProvider;
    this.wpSnackbarWrapperProvider = wpSnackbarWrapperProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
  }

  @Override
  public SnackbarSequencer get() {
    return newInstance(uiHelperProvider.get(), wpSnackbarWrapperProvider.get(), mainDispatcherProvider.get());
  }

  public static SnackbarSequencer_Factory create(Provider<UiHelpers> uiHelperProvider,
      Provider<WPSnackbarWrapper> wpSnackbarWrapperProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    return new SnackbarSequencer_Factory(uiHelperProvider, wpSnackbarWrapperProvider, mainDispatcherProvider);
  }

  public static SnackbarSequencer newInstance(UiHelpers uiHelper,
      WPSnackbarWrapper wpSnackbarWrapper, CoroutineDispatcher mainDispatcher) {
    return new SnackbarSequencer(uiHelper, wpSnackbarWrapper, mainDispatcher);
  }
}
