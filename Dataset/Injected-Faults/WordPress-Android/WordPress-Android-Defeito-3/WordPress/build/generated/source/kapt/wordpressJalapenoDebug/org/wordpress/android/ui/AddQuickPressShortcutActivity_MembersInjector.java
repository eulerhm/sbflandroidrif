package org.wordpress.android.ui;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.tools.FluxCImageLoader;

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
public final class AddQuickPressShortcutActivity_MembersInjector implements MembersInjector<AddQuickPressShortcutActivity> {
  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<FluxCImageLoader> mImageLoaderProvider;

  public AddQuickPressShortcutActivity_MembersInjector(Provider<SiteStore> mSiteStoreProvider,
      Provider<FluxCImageLoader> mImageLoaderProvider) {
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mImageLoaderProvider = mImageLoaderProvider;
  }

  public static MembersInjector<AddQuickPressShortcutActivity> create(
      Provider<SiteStore> mSiteStoreProvider, Provider<FluxCImageLoader> mImageLoaderProvider) {
    return new AddQuickPressShortcutActivity_MembersInjector(mSiteStoreProvider, mImageLoaderProvider);
  }

  @Override
  public void injectMembers(AddQuickPressShortcutActivity instance) {
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMImageLoader(instance, mImageLoaderProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.AddQuickPressShortcutActivity.mSiteStore")
  public static void injectMSiteStore(AddQuickPressShortcutActivity instance,
      SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.AddQuickPressShortcutActivity.mImageLoader")
  public static void injectMImageLoader(AddQuickPressShortcutActivity instance,
      FluxCImageLoader mImageLoader) {
    instance.mImageLoader = mImageLoader;
  }
}
