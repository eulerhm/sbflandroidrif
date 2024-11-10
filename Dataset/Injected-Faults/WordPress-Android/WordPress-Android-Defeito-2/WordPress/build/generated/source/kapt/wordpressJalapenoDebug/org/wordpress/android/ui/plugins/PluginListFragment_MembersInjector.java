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
public final class PluginListFragment_MembersInjector implements MembersInjector<PluginListFragment> {
  private final Provider<ViewModelProvider.Factory> mViewModelFactoryProvider;

  private final Provider<ImageManager> mImageManagerProvider;

  public PluginListFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> mViewModelFactoryProvider,
      Provider<ImageManager> mImageManagerProvider) {
    this.mViewModelFactoryProvider = mViewModelFactoryProvider;
    this.mImageManagerProvider = mImageManagerProvider;
  }

  public static MembersInjector<PluginListFragment> create(
      Provider<ViewModelProvider.Factory> mViewModelFactoryProvider,
      Provider<ImageManager> mImageManagerProvider) {
    return new PluginListFragment_MembersInjector(mViewModelFactoryProvider, mImageManagerProvider);
  }

  @Override
  public void injectMembers(PluginListFragment instance) {
    injectMViewModelFactory(instance, mViewModelFactoryProvider.get());
    injectMImageManager(instance, mImageManagerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.plugins.PluginListFragment.mViewModelFactory")
  public static void injectMViewModelFactory(PluginListFragment instance,
      ViewModelProvider.Factory mViewModelFactory) {
    instance.mViewModelFactory = mViewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.plugins.PluginListFragment.mImageManager")
  public static void injectMImageManager(PluginListFragment instance, ImageManager mImageManager) {
    instance.mImageManager = mImageManager;
  }
}
