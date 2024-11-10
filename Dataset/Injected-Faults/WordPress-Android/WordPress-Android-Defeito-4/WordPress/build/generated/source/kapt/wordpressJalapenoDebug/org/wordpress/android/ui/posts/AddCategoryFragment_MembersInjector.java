package org.wordpress.android.ui.posts;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
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
public final class AddCategoryFragment_MembersInjector implements MembersInjector<AddCategoryFragment> {
  private final Provider<TaxonomyStore> mTaxonomyStoreProvider;

  public AddCategoryFragment_MembersInjector(Provider<TaxonomyStore> mTaxonomyStoreProvider) {
    this.mTaxonomyStoreProvider = mTaxonomyStoreProvider;
  }

  public static MembersInjector<AddCategoryFragment> create(
      Provider<TaxonomyStore> mTaxonomyStoreProvider) {
    return new AddCategoryFragment_MembersInjector(mTaxonomyStoreProvider);
  }

  @Override
  public void injectMembers(AddCategoryFragment instance) {
    injectMTaxonomyStore(instance, mTaxonomyStoreProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.AddCategoryFragment.mTaxonomyStore")
  public static void injectMTaxonomyStore(AddCategoryFragment instance,
      TaxonomyStore mTaxonomyStore) {
    instance.mTaxonomyStore = mTaxonomyStore;
  }
}
