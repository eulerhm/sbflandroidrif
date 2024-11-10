package org.wordpress.android.ui.stats.refresh.lists.widget.configuration;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
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
public final class StatsWidgetColorSelectionDialogFragment_MembersInjector implements MembersInjector<StatsWidgetColorSelectionDialogFragment> {
  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public StatsWidgetColorSelectionDialogFragment_MembersInjector(
      Provider<ImageManager> imageManagerProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.imageManagerProvider = imageManagerProvider;
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<StatsWidgetColorSelectionDialogFragment> create(
      Provider<ImageManager> imageManagerProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new StatsWidgetColorSelectionDialogFragment_MembersInjector(imageManagerProvider, viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(StatsWidgetColorSelectionDialogFragment instance) {
    injectImageManager(instance, imageManagerProvider.get());
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsWidgetColorSelectionDialogFragment.imageManager")
  public static void injectImageManager(StatsWidgetColorSelectionDialogFragment instance,
      ImageManager imageManager) {
    instance.imageManager = imageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsWidgetColorSelectionDialogFragment.viewModelFactory")
  public static void injectViewModelFactory(StatsWidgetColorSelectionDialogFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
