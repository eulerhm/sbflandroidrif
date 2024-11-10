package org.wordpress.android.ui.prefs;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.store.TaxonomyStore;

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
public final class SiteSettingsTagListActivity_MembersInjector implements MembersInjector<SiteSettingsTagListActivity> {
  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<TaxonomyStore> mTaxonomyStoreProvider;

  public SiteSettingsTagListActivity_MembersInjector(Provider<Dispatcher> mDispatcherProvider,
      Provider<SiteStore> mSiteStoreProvider, Provider<TaxonomyStore> mTaxonomyStoreProvider) {
    this.mDispatcherProvider = mDispatcherProvider;
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mTaxonomyStoreProvider = mTaxonomyStoreProvider;
  }

  public static MembersInjector<SiteSettingsTagListActivity> create(
      Provider<Dispatcher> mDispatcherProvider, Provider<SiteStore> mSiteStoreProvider,
      Provider<TaxonomyStore> mTaxonomyStoreProvider) {
    return new SiteSettingsTagListActivity_MembersInjector(mDispatcherProvider, mSiteStoreProvider, mTaxonomyStoreProvider);
  }

  @Override
  public void injectMembers(SiteSettingsTagListActivity instance) {
    injectMDispatcher(instance, mDispatcherProvider.get());
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMTaxonomyStore(instance, mTaxonomyStoreProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.SiteSettingsTagListActivity.mDispatcher")
  public static void injectMDispatcher(SiteSettingsTagListActivity instance,
      Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.SiteSettingsTagListActivity.mSiteStore")
  public static void injectMSiteStore(SiteSettingsTagListActivity instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.SiteSettingsTagListActivity.mTaxonomyStore")
  public static void injectMTaxonomyStore(SiteSettingsTagListActivity instance,
      TaxonomyStore mTaxonomyStore) {
    instance.mTaxonomyStore = mTaxonomyStore;
  }
}
