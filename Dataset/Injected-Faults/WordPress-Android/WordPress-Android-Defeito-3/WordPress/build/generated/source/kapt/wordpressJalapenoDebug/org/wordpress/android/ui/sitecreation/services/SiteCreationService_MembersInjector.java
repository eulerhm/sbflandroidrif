package org.wordpress.android.ui.sitecreation.services;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.ui.sitecreation.misc.SiteCreationTracker;
import org.wordpress.android.util.LocaleManagerWrapper;

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
public final class SiteCreationService_MembersInjector implements MembersInjector<SiteCreationService> {
  private final Provider<SiteCreationServiceManager> managerProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<SiteCreationTracker> trackerProvider;

  private final Provider<LocaleManagerWrapper> localeManagerWrapperProvider;

  public SiteCreationService_MembersInjector(Provider<SiteCreationServiceManager> managerProvider,
      Provider<Dispatcher> dispatcherProvider, Provider<SiteCreationTracker> trackerProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider) {
    this.managerProvider = managerProvider;
    this.dispatcherProvider = dispatcherProvider;
    this.trackerProvider = trackerProvider;
    this.localeManagerWrapperProvider = localeManagerWrapperProvider;
  }

  public static MembersInjector<SiteCreationService> create(
      Provider<SiteCreationServiceManager> managerProvider, Provider<Dispatcher> dispatcherProvider,
      Provider<SiteCreationTracker> trackerProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider) {
    return new SiteCreationService_MembersInjector(managerProvider, dispatcherProvider, trackerProvider, localeManagerWrapperProvider);
  }

  @Override
  public void injectMembers(SiteCreationService instance) {
    injectManager(instance, managerProvider.get());
    injectDispatcher(instance, dispatcherProvider.get());
    injectTracker(instance, trackerProvider.get());
    injectLocaleManagerWrapper(instance, localeManagerWrapperProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.sitecreation.services.SiteCreationService.manager")
  public static void injectManager(SiteCreationService instance,
      SiteCreationServiceManager manager) {
    instance.manager = manager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.sitecreation.services.SiteCreationService.dispatcher")
  public static void injectDispatcher(SiteCreationService instance, Dispatcher dispatcher) {
    instance.dispatcher = dispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.sitecreation.services.SiteCreationService.tracker")
  public static void injectTracker(SiteCreationService instance, SiteCreationTracker tracker) {
    instance.tracker = tracker;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.sitecreation.services.SiteCreationService.localeManagerWrapper")
  public static void injectLocaleManagerWrapper(SiteCreationService instance,
      LocaleManagerWrapper localeManagerWrapper) {
    instance.localeManagerWrapper = localeManagerWrapper;
  }
}
