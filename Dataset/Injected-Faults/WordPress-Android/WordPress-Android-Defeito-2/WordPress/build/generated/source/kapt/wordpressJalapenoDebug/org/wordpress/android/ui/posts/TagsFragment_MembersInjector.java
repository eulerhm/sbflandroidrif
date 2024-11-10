package org.wordpress.android.ui.posts;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
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
public final class TagsFragment_MembersInjector implements MembersInjector<TagsFragment> {
  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<TaxonomyStore> mTaxonomyStoreProvider;

  public TagsFragment_MembersInjector(Provider<Dispatcher> mDispatcherProvider,
      Provider<TaxonomyStore> mTaxonomyStoreProvider) {
    this.mDispatcherProvider = mDispatcherProvider;
    this.mTaxonomyStoreProvider = mTaxonomyStoreProvider;
  }

  public static MembersInjector<TagsFragment> create(Provider<Dispatcher> mDispatcherProvider,
      Provider<TaxonomyStore> mTaxonomyStoreProvider) {
    return new TagsFragment_MembersInjector(mDispatcherProvider, mTaxonomyStoreProvider);
  }

  @Override
  public void injectMembers(TagsFragment instance) {
    injectMDispatcher(instance, mDispatcherProvider.get());
    injectMTaxonomyStore(instance, mTaxonomyStoreProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.TagsFragment.mDispatcher")
  public static void injectMDispatcher(TagsFragment instance, Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.TagsFragment.mTaxonomyStore")
  public static void injectMTaxonomyStore(TagsFragment instance, TaxonomyStore mTaxonomyStore) {
    instance.mTaxonomyStore = mTaxonomyStore;
  }
}
