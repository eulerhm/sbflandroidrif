package org.wordpress.android.ui.accounts;

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
public final class SignupEpilogueActivity_MembersInjector implements MembersInjector<SignupEpilogueActivity> {
  private final Provider<SiteStore> mSiteStoreProvider;

  public SignupEpilogueActivity_MembersInjector(Provider<SiteStore> mSiteStoreProvider) {
    this.mSiteStoreProvider = mSiteStoreProvider;
  }

  public static MembersInjector<SignupEpilogueActivity> create(
      Provider<SiteStore> mSiteStoreProvider) {
    return new SignupEpilogueActivity_MembersInjector(mSiteStoreProvider);
  }

  @Override
  public void injectMembers(SignupEpilogueActivity instance) {
    injectMSiteStore(instance, mSiteStoreProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.SignupEpilogueActivity.mSiteStore")
  public static void injectMSiteStore(SignupEpilogueActivity instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }
}
