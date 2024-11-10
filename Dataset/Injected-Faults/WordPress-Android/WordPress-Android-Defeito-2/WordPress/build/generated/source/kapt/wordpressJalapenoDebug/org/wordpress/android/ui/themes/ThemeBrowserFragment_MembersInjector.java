package org.wordpress.android.ui.themes;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.QuickStartStore;
import org.wordpress.android.fluxc.store.ThemeStore;
import org.wordpress.android.util.QuickStartUtilsWrapper;
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
public final class ThemeBrowserFragment_MembersInjector implements MembersInjector<ThemeBrowserFragment> {
  private final Provider<ThemeStore> mThemeStoreProvider;

  private final Provider<QuickStartStore> mQuickStartStoreProvider;

  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<ImageManager> mImageManagerProvider;

  private final Provider<QuickStartUtilsWrapper> mQuickStartUtilsWrapperProvider;

  public ThemeBrowserFragment_MembersInjector(Provider<ThemeStore> mThemeStoreProvider,
      Provider<QuickStartStore> mQuickStartStoreProvider, Provider<Dispatcher> mDispatcherProvider,
      Provider<ImageManager> mImageManagerProvider,
      Provider<QuickStartUtilsWrapper> mQuickStartUtilsWrapperProvider) {
    this.mThemeStoreProvider = mThemeStoreProvider;
    this.mQuickStartStoreProvider = mQuickStartStoreProvider;
    this.mDispatcherProvider = mDispatcherProvider;
    this.mImageManagerProvider = mImageManagerProvider;
    this.mQuickStartUtilsWrapperProvider = mQuickStartUtilsWrapperProvider;
  }

  public static MembersInjector<ThemeBrowserFragment> create(
      Provider<ThemeStore> mThemeStoreProvider, Provider<QuickStartStore> mQuickStartStoreProvider,
      Provider<Dispatcher> mDispatcherProvider, Provider<ImageManager> mImageManagerProvider,
      Provider<QuickStartUtilsWrapper> mQuickStartUtilsWrapperProvider) {
    return new ThemeBrowserFragment_MembersInjector(mThemeStoreProvider, mQuickStartStoreProvider, mDispatcherProvider, mImageManagerProvider, mQuickStartUtilsWrapperProvider);
  }

  @Override
  public void injectMembers(ThemeBrowserFragment instance) {
    injectMThemeStore(instance, mThemeStoreProvider.get());
    injectMQuickStartStore(instance, mQuickStartStoreProvider.get());
    injectMDispatcher(instance, mDispatcherProvider.get());
    injectMImageManager(instance, mImageManagerProvider.get());
    injectMQuickStartUtilsWrapper(instance, mQuickStartUtilsWrapperProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.themes.ThemeBrowserFragment.mThemeStore")
  public static void injectMThemeStore(ThemeBrowserFragment instance, ThemeStore mThemeStore) {
    instance.mThemeStore = mThemeStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.themes.ThemeBrowserFragment.mQuickStartStore")
  public static void injectMQuickStartStore(ThemeBrowserFragment instance,
      QuickStartStore mQuickStartStore) {
    instance.mQuickStartStore = mQuickStartStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.themes.ThemeBrowserFragment.mDispatcher")
  public static void injectMDispatcher(ThemeBrowserFragment instance, Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.themes.ThemeBrowserFragment.mImageManager")
  public static void injectMImageManager(ThemeBrowserFragment instance,
      ImageManager mImageManager) {
    instance.mImageManager = mImageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.themes.ThemeBrowserFragment.mQuickStartUtilsWrapper")
  public static void injectMQuickStartUtilsWrapper(ThemeBrowserFragment instance,
      QuickStartUtilsWrapper mQuickStartUtilsWrapper) {
    instance.mQuickStartUtilsWrapper = mQuickStartUtilsWrapper;
  }
}
