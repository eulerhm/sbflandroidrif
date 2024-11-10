package org.wordpress.android.ui.debug;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.notifications.NotificationManagerWrapper;
import org.wordpress.android.util.DebugUtils;
import org.wordpress.android.util.config.ManualFeatureConfig;
import org.wordpress.android.util.config.RemoteConfig;
import org.wordpress.android.viewmodel.ContextProvider;
import org.wordpress.android.workers.weeklyroundup.WeeklyRoundupNotifier;

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
public final class DebugSettingsViewModel_Factory implements Factory<DebugSettingsViewModel> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<ManualFeatureConfig> manualFeatureConfigProvider;

  private final Provider<RemoteConfig> remoteConfigProvider;

  private final Provider<DebugUtils> debugUtilsProvider;

  private final Provider<WeeklyRoundupNotifier> weeklyRoundupNotifierProvider;

  private final Provider<NotificationManagerWrapper> notificationManagerProvider;

  private final Provider<ContextProvider> contextProvider;

  public DebugSettingsViewModel_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<ManualFeatureConfig> manualFeatureConfigProvider,
      Provider<RemoteConfig> remoteConfigProvider, Provider<DebugUtils> debugUtilsProvider,
      Provider<WeeklyRoundupNotifier> weeklyRoundupNotifierProvider,
      Provider<NotificationManagerWrapper> notificationManagerProvider,
      Provider<ContextProvider> contextProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.manualFeatureConfigProvider = manualFeatureConfigProvider;
    this.remoteConfigProvider = remoteConfigProvider;
    this.debugUtilsProvider = debugUtilsProvider;
    this.weeklyRoundupNotifierProvider = weeklyRoundupNotifierProvider;
    this.notificationManagerProvider = notificationManagerProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public DebugSettingsViewModel get() {
    return newInstance(mainDispatcherProvider.get(), bgDispatcherProvider.get(), manualFeatureConfigProvider.get(), remoteConfigProvider.get(), debugUtilsProvider.get(), weeklyRoundupNotifierProvider.get(), notificationManagerProvider.get(), contextProvider.get());
  }

  public static DebugSettingsViewModel_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<ManualFeatureConfig> manualFeatureConfigProvider,
      Provider<RemoteConfig> remoteConfigProvider, Provider<DebugUtils> debugUtilsProvider,
      Provider<WeeklyRoundupNotifier> weeklyRoundupNotifierProvider,
      Provider<NotificationManagerWrapper> notificationManagerProvider,
      Provider<ContextProvider> contextProvider) {
    return new DebugSettingsViewModel_Factory(mainDispatcherProvider, bgDispatcherProvider, manualFeatureConfigProvider, remoteConfigProvider, debugUtilsProvider, weeklyRoundupNotifierProvider, notificationManagerProvider, contextProvider);
  }

  public static DebugSettingsViewModel newInstance(CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher bgDispatcher, ManualFeatureConfig manualFeatureConfig,
      RemoteConfig remoteConfig, DebugUtils debugUtils, WeeklyRoundupNotifier weeklyRoundupNotifier,
      NotificationManagerWrapper notificationManager, ContextProvider contextProvider) {
    return new DebugSettingsViewModel(mainDispatcher, bgDispatcher, manualFeatureConfig, remoteConfig, debugUtils, weeklyRoundupNotifier, notificationManager, contextProvider);
  }
}
