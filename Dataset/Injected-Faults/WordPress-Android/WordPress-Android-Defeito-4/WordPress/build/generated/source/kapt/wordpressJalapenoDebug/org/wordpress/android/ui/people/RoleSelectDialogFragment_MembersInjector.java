package org.wordpress.android.ui.people;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.viewmodel.ContextProvider;

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
public final class RoleSelectDialogFragment_MembersInjector implements MembersInjector<RoleSelectDialogFragment> {
  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<ContextProvider> mContextProvider;

  public RoleSelectDialogFragment_MembersInjector(Provider<SiteStore> mSiteStoreProvider,
      Provider<ContextProvider> mContextProvider) {
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mContextProvider = mContextProvider;
  }

  public static MembersInjector<RoleSelectDialogFragment> create(
      Provider<SiteStore> mSiteStoreProvider, Provider<ContextProvider> mContextProvider) {
    return new RoleSelectDialogFragment_MembersInjector(mSiteStoreProvider, mContextProvider);
  }

  @Override
  public void injectMembers(RoleSelectDialogFragment instance) {
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMContextProvider(instance, mContextProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.people.RoleSelectDialogFragment.mSiteStore")
  public static void injectMSiteStore(RoleSelectDialogFragment instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.people.RoleSelectDialogFragment.mContextProvider")
  public static void injectMContextProvider(RoleSelectDialogFragment instance,
      ContextProvider mContextProvider) {
    instance.mContextProvider = mContextProvider;
  }
}
