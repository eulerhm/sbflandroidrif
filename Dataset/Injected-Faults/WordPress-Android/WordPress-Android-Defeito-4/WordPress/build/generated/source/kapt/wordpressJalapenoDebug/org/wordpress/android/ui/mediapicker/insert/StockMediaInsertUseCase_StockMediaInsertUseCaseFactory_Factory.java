package org.wordpress.android.ui.mediapicker.insert;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.StockMediaStore;

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
public final class StockMediaInsertUseCase_StockMediaInsertUseCaseFactory_Factory implements Factory<StockMediaInsertUseCase.StockMediaInsertUseCaseFactory> {
  private final Provider<StockMediaStore> stockMediaStoreProvider;

  public StockMediaInsertUseCase_StockMediaInsertUseCaseFactory_Factory(
      Provider<StockMediaStore> stockMediaStoreProvider) {
    this.stockMediaStoreProvider = stockMediaStoreProvider;
  }

  @Override
  public StockMediaInsertUseCase.StockMediaInsertUseCaseFactory get() {
    return newInstance(stockMediaStoreProvider.get());
  }

  public static StockMediaInsertUseCase_StockMediaInsertUseCaseFactory_Factory create(
      Provider<StockMediaStore> stockMediaStoreProvider) {
    return new StockMediaInsertUseCase_StockMediaInsertUseCaseFactory_Factory(stockMediaStoreProvider);
  }

  public static StockMediaInsertUseCase.StockMediaInsertUseCaseFactory newInstance(
      StockMediaStore stockMediaStore) {
    return new StockMediaInsertUseCase.StockMediaInsertUseCaseFactory(stockMediaStore);
  }
}
