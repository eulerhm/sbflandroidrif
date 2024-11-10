package org.wordpress.android.ui.jetpack.scan.history;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.image.ImageManager;

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
public final class ScanHistoryListFragment_MembersInjector implements MembersInjector<ScanHistoryListFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  public ScanHistoryListFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<ImageManager> imageManagerProvider, Provider<UiHelpers> uiHelpersProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.imageManagerProvider = imageManagerProvider;
    this.uiHelpersProvider = uiHelpersProvider;
  }

  public static MembersInjector<ScanHistoryListFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<ImageManager> imageManagerProvider, Provider<UiHelpers> uiHelpersProvider) {
    return new ScanHistoryListFragment_MembersInjector(viewModelFactoryProvider, imageManagerProvider, uiHelpersProvider);
  }

  @Override
  public void injectMembers(ScanHistoryListFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectImageManager(instance, imageManagerProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.jetpack.scan.history.ScanHistoryListFragment.viewModelFactory")
  public static void injectViewModelFactory(ScanHistoryListFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.jetpack.scan.history.ScanHistoryListFragment.imageManager")
  public static void injectImageManager(ScanHistoryListFragment instance,
      ImageManager imageManager) {
    instance.imageManager = imageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.jetpack.scan.history.ScanHistoryListFragment.uiHelpers")
  public static void injectUiHelpers(ScanHistoryListFragment instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }
}
