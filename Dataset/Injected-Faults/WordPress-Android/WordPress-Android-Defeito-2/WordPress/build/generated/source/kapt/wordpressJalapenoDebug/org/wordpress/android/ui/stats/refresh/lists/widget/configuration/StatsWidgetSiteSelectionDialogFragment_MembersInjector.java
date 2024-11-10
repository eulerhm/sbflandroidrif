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
public final class StatsWidgetSiteSelectionDialogFragment_MembersInjector implements MembersInjector<StatsWidgetSiteSelectionDialogFragment> {
  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public StatsWidgetSiteSelectionDialogFragment_MembersInjector(
      Provider<ImageManager> imageManagerProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.imageManagerProvider = imageManagerProvider;
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<StatsWidgetSiteSelectionDialogFragment> create(
      Provider<ImageManager> imageManagerProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new StatsWidgetSiteSelectionDialogFragment_MembersInjector(imageManagerProvider, viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(StatsWidgetSiteSelectionDialogFragment instance) {
    injectImageManager(instance, imageManagerProvider.get());
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsWidgetSiteSelectionDialogFragment.imageManager")
  public static void injectImageManager(StatsWidgetSiteSelectionDialogFragment instance,
      ImageManager imageManager) {
    instance.imageManager = imageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsWidgetSiteSelectionDialogFragment.viewModelFactory")
  public static void injectViewModelFactory(StatsWidgetSiteSelectionDialogFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
