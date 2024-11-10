package org.wordpress.android.ui.jetpack.backup.download;

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
public final class BackupDownloadFragment_MembersInjector implements MembersInjector<BackupDownloadFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  private final Provider<ImageManager> imageManagerProvider;

  public BackupDownloadFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider, Provider<ImageManager> imageManagerProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.uiHelpersProvider = uiHelpersProvider;
    this.imageManagerProvider = imageManagerProvider;
  }

  public static MembersInjector<BackupDownloadFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider, Provider<ImageManager> imageManagerProvider) {
    return new BackupDownloadFragment_MembersInjector(viewModelFactoryProvider, uiHelpersProvider, imageManagerProvider);
  }

  @Override
  public void injectMembers(BackupDownloadFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
    injectImageManager(instance, imageManagerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.jetpack.backup.download.BackupDownloadFragment.viewModelFactory")
  public static void injectViewModelFactory(BackupDownloadFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.jetpack.backup.download.BackupDownloadFragment.uiHelpers")
  public static void injectUiHelpers(BackupDownloadFragment instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.jetpack.backup.download.BackupDownloadFragment.imageManager")
  public static void injectImageManager(BackupDownloadFragment instance,
      ImageManager imageManager) {
    instance.imageManager = imageManager;
  }
}
