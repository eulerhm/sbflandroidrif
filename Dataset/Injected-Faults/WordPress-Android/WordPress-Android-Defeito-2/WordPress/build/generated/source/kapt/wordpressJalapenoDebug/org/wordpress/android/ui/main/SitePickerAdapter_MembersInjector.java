package org.wordpress.android.ui.main;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.util.BuildConfigWrapper;
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
public final class SitePickerAdapter_MembersInjector implements MembersInjector<SitePickerAdapter> {
  private final Provider<AccountStore> mAccountStoreProvider;

  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<ImageManager> mImageManagerProvider;

  private final Provider<BuildConfigWrapper> mBuildConfigWrapperProvider;

  private final Provider<SelectedSiteRepository> mSelectedSiteRepositoryProvider;

  public SitePickerAdapter_MembersInjector(Provider<AccountStore> mAccountStoreProvider,
      Provider<SiteStore> mSiteStoreProvider, Provider<ImageManager> mImageManagerProvider,
      Provider<BuildConfigWrapper> mBuildConfigWrapperProvider,
      Provider<SelectedSiteRepository> mSelectedSiteRepositoryProvider) {
    this.mAccountStoreProvider = mAccountStoreProvider;
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mImageManagerProvider = mImageManagerProvider;
    this.mBuildConfigWrapperProvider = mBuildConfigWrapperProvider;
    this.mSelectedSiteRepositoryProvider = mSelectedSiteRepositoryProvider;
  }

  public static MembersInjector<SitePickerAdapter> create(
      Provider<AccountStore> mAccountStoreProvider, Provider<SiteStore> mSiteStoreProvider,
      Provider<ImageManager> mImageManagerProvider,
      Provider<BuildConfigWrapper> mBuildConfigWrapperProvider,
      Provider<SelectedSiteRepository> mSelectedSiteRepositoryProvider) {
    return new SitePickerAdapter_MembersInjector(mAccountStoreProvider, mSiteStoreProvider, mImageManagerProvider, mBuildConfigWrapperProvider, mSelectedSiteRepositoryProvider);
  }

  @Override
  public void injectMembers(SitePickerAdapter instance) {
    injectMAccountStore(instance, mAccountStoreProvider.get());
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMImageManager(instance, mImageManagerProvider.get());
    injectMBuildConfigWrapper(instance, mBuildConfigWrapperProvider.get());
    injectMSelectedSiteRepository(instance, mSelectedSiteRepositoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.SitePickerAdapter.mAccountStore")
  public static void injectMAccountStore(SitePickerAdapter instance, AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.SitePickerAdapter.mSiteStore")
  public static void injectMSiteStore(SitePickerAdapter instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.SitePickerAdapter.mImageManager")
  public static void injectMImageManager(SitePickerAdapter instance, ImageManager mImageManager) {
    instance.mImageManager = mImageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.SitePickerAdapter.mBuildConfigWrapper")
  public static void injectMBuildConfigWrapper(SitePickerAdapter instance,
      BuildConfigWrapper mBuildConfigWrapper) {
    instance.mBuildConfigWrapper = mBuildConfigWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.SitePickerAdapter.mSelectedSiteRepository")
  public static void injectMSelectedSiteRepository(SitePickerAdapter instance,
      SelectedSiteRepository mSelectedSiteRepository) {
    instance.mSelectedSiteRepository = mSelectedSiteRepository;
  }
}
