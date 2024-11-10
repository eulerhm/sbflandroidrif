package org.wordpress.android.ui.whatsnew;

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
public final class FeatureAnnouncementListAdapter_MembersInjector implements MembersInjector<FeatureAnnouncementListAdapter> {
  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public FeatureAnnouncementListAdapter_MembersInjector(Provider<ImageManager> imageManagerProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.imageManagerProvider = imageManagerProvider;
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<FeatureAnnouncementListAdapter> create(
      Provider<ImageManager> imageManagerProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new FeatureAnnouncementListAdapter_MembersInjector(imageManagerProvider, viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(FeatureAnnouncementListAdapter instance) {
    injectImageManager(instance, imageManagerProvider.get());
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.whatsnew.FeatureAnnouncementListAdapter.imageManager")
  public static void injectImageManager(FeatureAnnouncementListAdapter instance,
      ImageManager imageManager) {
    instance.imageManager = imageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.whatsnew.FeatureAnnouncementListAdapter.viewModelFactory")
  public static void injectViewModelFactory(FeatureAnnouncementListAdapter instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
