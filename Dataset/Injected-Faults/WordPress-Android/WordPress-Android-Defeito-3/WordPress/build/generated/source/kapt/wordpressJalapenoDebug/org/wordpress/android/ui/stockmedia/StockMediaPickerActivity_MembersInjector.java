package org.wordpress.android.ui.stockmedia;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.StockMediaStore;
import org.wordpress.android.util.image.ImageManager;

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
public final class StockMediaPickerActivity_MembersInjector implements MembersInjector<StockMediaPickerActivity> {
  private final Provider<StockMediaStore> mStockMediaStoreProvider;

  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<ImageManager> mImageManagerProvider;

  public StockMediaPickerActivity_MembersInjector(
      Provider<StockMediaStore> mStockMediaStoreProvider, Provider<Dispatcher> mDispatcherProvider,
      Provider<ImageManager> mImageManagerProvider) {
    this.mStockMediaStoreProvider = mStockMediaStoreProvider;
    this.mDispatcherProvider = mDispatcherProvider;
    this.mImageManagerProvider = mImageManagerProvider;
  }

  public static MembersInjector<StockMediaPickerActivity> create(
      Provider<StockMediaStore> mStockMediaStoreProvider, Provider<Dispatcher> mDispatcherProvider,
      Provider<ImageManager> mImageManagerProvider) {
    return new StockMediaPickerActivity_MembersInjector(mStockMediaStoreProvider, mDispatcherProvider, mImageManagerProvider);
  }

  @Override
  public void injectMembers(StockMediaPickerActivity instance) {
    injectMStockMediaStore(instance, mStockMediaStoreProvider.get());
    injectMDispatcher(instance, mDispatcherProvider.get());
    injectMImageManager(instance, mImageManagerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stockmedia.StockMediaPickerActivity.mStockMediaStore")
  public static void injectMStockMediaStore(StockMediaPickerActivity instance,
      StockMediaStore mStockMediaStore) {
    instance.mStockMediaStore = mStockMediaStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stockmedia.StockMediaPickerActivity.mDispatcher")
  public static void injectMDispatcher(StockMediaPickerActivity instance, Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stockmedia.StockMediaPickerActivity.mImageManager")
  public static void injectMImageManager(StockMediaPickerActivity instance,
      ImageManager mImageManager) {
    instance.mImageManager = mImageManager;
  }
}
