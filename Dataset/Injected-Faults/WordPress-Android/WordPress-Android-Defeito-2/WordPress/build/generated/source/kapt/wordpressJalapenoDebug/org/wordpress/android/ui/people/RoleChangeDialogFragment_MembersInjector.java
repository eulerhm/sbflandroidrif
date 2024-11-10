package org.wordpress.android.ui.people;

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
public final class RoleChangeDialogFragment_MembersInjector implements MembersInjector<RoleChangeDialogFragment> {
  private final Provider<SiteStore> mSiteStoreProvider;

  public RoleChangeDialogFragment_MembersInjector(Provider<SiteStore> mSiteStoreProvider) {
    this.mSiteStoreProvider = mSiteStoreProvider;
  }

  public static MembersInjector<RoleChangeDialogFragment> create(
      Provider<SiteStore> mSiteStoreProvider) {
    return new RoleChangeDialogFragment_MembersInjector(mSiteStoreProvider);
  }

  @Override
  public void injectMembers(RoleChangeDialogFragment instance) {
    injectMSiteStore(instance, mSiteStoreProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.people.RoleChangeDialogFragment.mSiteStore")
  public static void injectMSiteStore(RoleChangeDialogFragment instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }
}
