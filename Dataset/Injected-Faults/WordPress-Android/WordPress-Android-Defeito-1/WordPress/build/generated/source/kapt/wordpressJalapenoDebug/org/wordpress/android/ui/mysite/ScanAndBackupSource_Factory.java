package org.wordpress.android.ui.mysite;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.jetpack.JetpackCapabilitiesUseCase;

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
public final class ScanAndBackupSource_Factory implements Factory<ScanAndBackupSource> {
  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<SelectedSiteRepository> selectedSiteRepositoryProvider;

  private final Provider<JetpackCapabilitiesUseCase> jetpackCapabilitiesUseCaseProvider;

  public ScanAndBackupSource_Factory(Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<JetpackCapabilitiesUseCase> jetpackCapabilitiesUseCaseProvider) {
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.selectedSiteRepositoryProvider = selectedSiteRepositoryProvider;
    this.jetpackCapabilitiesUseCaseProvider = jetpackCapabilitiesUseCaseProvider;
  }

  @Override
  public ScanAndBackupSource get() {
    return newInstance(bgDispatcherProvider.get(), selectedSiteRepositoryProvider.get(), jetpackCapabilitiesUseCaseProvider.get());
  }

  public static ScanAndBackupSource_Factory create(
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<JetpackCapabilitiesUseCase> jetpackCapabilitiesUseCaseProvider) {
    return new ScanAndBackupSource_Factory(bgDispatcherProvider, selectedSiteRepositoryProvider, jetpackCapabilitiesUseCaseProvider);
  }

  public static ScanAndBackupSource newInstance(CoroutineDispatcher bgDispatcher,
      SelectedSiteRepository selectedSiteRepository,
      JetpackCapabilitiesUseCase jetpackCapabilitiesUseCase) {
    return new ScanAndBackupSource(bgDispatcher, selectedSiteRepository, jetpackCapabilitiesUseCase);
  }
}
