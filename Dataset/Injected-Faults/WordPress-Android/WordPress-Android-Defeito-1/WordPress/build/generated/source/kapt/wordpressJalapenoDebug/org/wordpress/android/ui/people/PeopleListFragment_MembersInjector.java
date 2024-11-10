package org.wordpress.android.ui.people;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.SiteStore;
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
public final class PeopleListFragment_MembersInjector implements MembersInjector<PeopleListFragment> {
  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<ImageManager> mImageManagerProvider;

  public PeopleListFragment_MembersInjector(Provider<SiteStore> mSiteStoreProvider,
      Provider<ImageManager> mImageManagerProvider) {
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mImageManagerProvider = mImageManagerProvider;
  }

  public static MembersInjector<PeopleListFragment> create(Provider<SiteStore> mSiteStoreProvider,
      Provider<ImageManager> mImageManagerProvider) {
    return new PeopleListFragment_MembersInjector(mSiteStoreProvider, mImageManagerProvider);
  }

  @Override
  public void injectMembers(PeopleListFragment instance) {
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMImageManager(instance, mImageManagerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.people.PeopleListFragment.mSiteStore")
  public static void injectMSiteStore(PeopleListFragment instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.people.PeopleListFragment.mImageManager")
  public static void injectMImageManager(PeopleListFragment instance, ImageManager mImageManager) {
    instance.mImageManager = mImageManager;
  }
}
