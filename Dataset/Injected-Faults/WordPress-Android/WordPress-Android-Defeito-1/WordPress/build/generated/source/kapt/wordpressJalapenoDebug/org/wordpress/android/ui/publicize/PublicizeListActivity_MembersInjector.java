package org.wordpress.android.ui.publicize;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.SiteStore;

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
public final class PublicizeListActivity_MembersInjector implements MembersInjector<PublicizeListActivity> {
  private final Provider<SiteStore> mSiteStoreProvider;

  public PublicizeListActivity_MembersInjector(Provider<SiteStore> mSiteStoreProvider) {
    this.mSiteStoreProvider = mSiteStoreProvider;
  }

  public static MembersInjector<PublicizeListActivity> create(
      Provider<SiteStore> mSiteStoreProvider) {
    return new PublicizeListActivity_MembersInjector(mSiteStoreProvider);
  }

  @Override
  public void injectMembers(PublicizeListActivity instance) {
    injectMSiteStore(instance, mSiteStoreProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.publicize.PublicizeListActivity.mSiteStore")
  public static void injectMSiteStore(PublicizeListActivity instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }
}
