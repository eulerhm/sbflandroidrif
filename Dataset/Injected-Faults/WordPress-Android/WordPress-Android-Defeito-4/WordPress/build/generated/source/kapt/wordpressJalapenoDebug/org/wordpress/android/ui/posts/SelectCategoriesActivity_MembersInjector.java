package org.wordpress.android.ui.posts;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.PostStore;
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
public final class SelectCategoriesActivity_MembersInjector implements MembersInjector<SelectCategoriesActivity> {
  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<PostStore> mPostStoreProvider;

  private final Provider<TaxonomyStore> mTaxonomyStoreProvider;

  private final Provider<Dispatcher> mDispatcherProvider;

  public SelectCategoriesActivity_MembersInjector(Provider<SiteStore> mSiteStoreProvider,
      Provider<PostStore> mPostStoreProvider, Provider<TaxonomyStore> mTaxonomyStoreProvider,
      Provider<Dispatcher> mDispatcherProvider) {
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mPostStoreProvider = mPostStoreProvider;
    this.mTaxonomyStoreProvider = mTaxonomyStoreProvider;
    this.mDispatcherProvider = mDispatcherProvider;
  }

  public static MembersInjector<SelectCategoriesActivity> create(
      Provider<SiteStore> mSiteStoreProvider, Provider<PostStore> mPostStoreProvider,
      Provider<TaxonomyStore> mTaxonomyStoreProvider, Provider<Dispatcher> mDispatcherProvider) {
    return new SelectCategoriesActivity_MembersInjector(mSiteStoreProvider, mPostStoreProvider, mTaxonomyStoreProvider, mDispatcherProvider);
  }

  @Override
  public void injectMembers(SelectCategoriesActivity instance) {
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMPostStore(instance, mPostStoreProvider.get());
    injectMTaxonomyStore(instance, mTaxonomyStoreProvider.get());
    injectMDispatcher(instance, mDispatcherProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.SelectCategoriesActivity.mSiteStore")
  public static void injectMSiteStore(SelectCategoriesActivity instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.SelectCategoriesActivity.mPostStore")
  public static void injectMPostStore(SelectCategoriesActivity instance, PostStore mPostStore) {
    instance.mPostStore = mPostStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.SelectCategoriesActivity.mTaxonomyStore")
  public static void injectMTaxonomyStore(SelectCategoriesActivity instance,
      TaxonomyStore mTaxonomyStore) {
    instance.mTaxonomyStore = mTaxonomyStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.SelectCategoriesActivity.mDispatcher")
  public static void injectMDispatcher(SelectCategoriesActivity instance, Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }
}
