package org.wordpress.android.ui.themes;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.ThemeStore;

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
public final class ThemeBrowserActivity_MembersInjector implements MembersInjector<ThemeBrowserActivity> {
  private final Provider<ThemeStore> mThemeStoreProvider;

  private final Provider<Dispatcher> mDispatcherProvider;

  public ThemeBrowserActivity_MembersInjector(Provider<ThemeStore> mThemeStoreProvider,
      Provider<Dispatcher> mDispatcherProvider) {
    this.mThemeStoreProvider = mThemeStoreProvider;
    this.mDispatcherProvider = mDispatcherProvider;
  }

  public static MembersInjector<ThemeBrowserActivity> create(
      Provider<ThemeStore> mThemeStoreProvider, Provider<Dispatcher> mDispatcherProvider) {
    return new ThemeBrowserActivity_MembersInjector(mThemeStoreProvider, mDispatcherProvider);
  }

  @Override
  public void injectMembers(ThemeBrowserActivity instance) {
    injectMThemeStore(instance, mThemeStoreProvider.get());
    injectMDispatcher(instance, mDispatcherProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.themes.ThemeBrowserActivity.mThemeStore")
  public static void injectMThemeStore(ThemeBrowserActivity instance, ThemeStore mThemeStore) {
    instance.mThemeStore = mThemeStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.themes.ThemeBrowserActivity.mDispatcher")
  public static void injectMDispatcher(ThemeBrowserActivity instance, Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }
}
