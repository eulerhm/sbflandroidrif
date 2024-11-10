package org.wordpress.android.ui.plugins;

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
public final class PluginBrowserActivity_MembersInjector implements MembersInjector<PluginBrowserActivity> {
  private final Provider<ViewModelProvider.Factory> mViewModelFactoryProvider;

  private final Provider<ImageManager> mImageManagerProvider;

  public PluginBrowserActivity_MembersInjector(
      Provider<ViewModelProvider.Factory> mViewModelFactoryProvider,
      Provider<ImageManager> mImageManagerProvider) {
    this.mViewModelFactoryProvider = mViewModelFactoryProvider;
    this.mImageManagerProvider = mImageManagerProvider;
  }

  public static MembersInjector<PluginBrowserActivity> create(
      Provider<ViewModelProvider.Factory> mViewModelFactoryProvider,
      Provider<ImageManager> mImageManagerProvider) {
    return new PluginBrowserActivity_MembersInjector(mViewModelFactoryProvider, mImageManagerProvider);
  }

  @Override
  public void injectMembers(PluginBrowserActivity instance) {
    injectMViewModelFactory(instance, mViewModelFactoryProvider.get());
    injectMImageManager(instance, mImageManagerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.plugins.PluginBrowserActivity.mViewModelFactory")
  public static void injectMViewModelFactory(PluginBrowserActivity instance,
      ViewModelProvider.Factory mViewModelFactory) {
    instance.mViewModelFactory = mViewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.plugins.PluginBrowserActivity.mImageManager")
  public static void injectMImageManager(PluginBrowserActivity instance,
      ImageManager mImageManager) {
    instance.mImageManager = mImageManager;
  }
}
