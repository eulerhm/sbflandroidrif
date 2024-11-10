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
public final class StatsWidgetDataTypeSelectionDialogFragment_MembersInjector implements MembersInjector<StatsWidgetDataTypeSelectionDialogFragment> {
  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public StatsWidgetDataTypeSelectionDialogFragment_MembersInjector(
      Provider<ImageManager> imageManagerProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.imageManagerProvider = imageManagerProvider;
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<StatsWidgetDataTypeSelectionDialogFragment> create(
      Provider<ImageManager> imageManagerProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new StatsWidgetDataTypeSelectionDialogFragment_MembersInjector(imageManagerProvider, viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(StatsWidgetDataTypeSelectionDialogFragment instance) {
    injectImageManager(instance, imageManagerProvider.get());
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsWidgetDataTypeSelectionDialogFragment.imageManager")
  public static void injectImageManager(StatsWidgetDataTypeSelectionDialogFragment instance,
      ImageManager imageManager) {
    instance.imageManager = imageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsWidgetDataTypeSelectionDialogFragment.viewModelFactory")
  public static void injectViewModelFactory(StatsWidgetDataTypeSelectionDialogFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
