package org.wordpress.android.ui.engagement;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.viewmodel.ResourceProvider;

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
public final class UserProfileBottomSheetFragment_MembersInjector implements MembersInjector<UserProfileBottomSheetFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  private final Provider<ResourceProvider> resourceProvider;

  public UserProfileBottomSheetFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<ImageManager> imageManagerProvider, Provider<UiHelpers> uiHelpersProvider,
      Provider<ResourceProvider> resourceProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.imageManagerProvider = imageManagerProvider;
    this.uiHelpersProvider = uiHelpersProvider;
    this.resourceProvider = resourceProvider;
  }

  public static MembersInjector<UserProfileBottomSheetFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<ImageManager> imageManagerProvider, Provider<UiHelpers> uiHelpersProvider,
      Provider<ResourceProvider> resourceProvider) {
    return new UserProfileBottomSheetFragment_MembersInjector(viewModelFactoryProvider, imageManagerProvider, uiHelpersProvider, resourceProvider);
  }

  @Override
  public void injectMembers(UserProfileBottomSheetFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectImageManager(instance, imageManagerProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
    injectResourceProvider(instance, resourceProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.engagement.UserProfileBottomSheetFragment.viewModelFactory")
  public static void injectViewModelFactory(UserProfileBottomSheetFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.engagement.UserProfileBottomSheetFragment.imageManager")
  public static void injectImageManager(UserProfileBottomSheetFragment instance,
      ImageManager imageManager) {
    instance.imageManager = imageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.engagement.UserProfileBottomSheetFragment.uiHelpers")
  public static void injectUiHelpers(UserProfileBottomSheetFragment instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.engagement.UserProfileBottomSheetFragment.resourceProvider")
  public static void injectResourceProvider(UserProfileBottomSheetFragment instance,
      ResourceProvider resourceProvider) {
    instance.resourceProvider = resourceProvider;
  }
}
