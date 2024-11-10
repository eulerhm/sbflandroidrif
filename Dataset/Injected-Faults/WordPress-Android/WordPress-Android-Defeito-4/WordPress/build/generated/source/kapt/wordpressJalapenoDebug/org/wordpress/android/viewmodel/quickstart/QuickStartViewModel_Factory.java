package org.wordpress.android.viewmodel.quickstart;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.QuickStartStore;

@ScopeMetadata
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
public final class QuickStartViewModel_Factory implements Factory<QuickStartViewModel> {
  private final Provider<QuickStartStore> quickStartStoreProvider;

  public QuickStartViewModel_Factory(Provider<QuickStartStore> quickStartStoreProvider) {
    this.quickStartStoreProvider = quickStartStoreProvider;
  }

  @Override
  public QuickStartViewModel get() {
    return newInstance(quickStartStoreProvider.get());
  }

  public static QuickStartViewModel_Factory create(
      Provider<QuickStartStore> quickStartStoreProvider) {
    return new QuickStartViewModel_Factory(quickStartStoreProvider);
  }

  public static QuickStartViewModel newInstance(QuickStartStore quickStartStore) {
    return new QuickStartViewModel(quickStartStore);
  }
}
