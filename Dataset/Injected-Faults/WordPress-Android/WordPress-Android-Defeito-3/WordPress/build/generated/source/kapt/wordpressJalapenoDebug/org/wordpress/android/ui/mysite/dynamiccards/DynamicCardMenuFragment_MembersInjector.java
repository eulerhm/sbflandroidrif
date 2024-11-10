package org.wordpress.android.ui.mysite.dynamiccards;

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
public final class DynamicCardMenuFragment_MembersInjector implements MembersInjector<DynamicCardMenuFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<ImageManager> imageManagerProvider;

  public DynamicCardMenuFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<ImageManager> imageManagerProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.imageManagerProvider = imageManagerProvider;
  }

  public static MembersInjector<DynamicCardMenuFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<ImageManager> imageManagerProvider) {
    return new DynamicCardMenuFragment_MembersInjector(viewModelFactoryProvider, imageManagerProvider);
  }

  @Override
  public void injectMembers(DynamicCardMenuFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectImageManager(instance, imageManagerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.mysite.dynamiccards.DynamicCardMenuFragment.viewModelFactory")
  public static void injectViewModelFactory(DynamicCardMenuFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.mysite.dynamiccards.DynamicCardMenuFragment.imageManager")
  public static void injectImageManager(DynamicCardMenuFragment instance,
      ImageManager imageManager) {
    instance.imageManager = imageManager;
  }
}
