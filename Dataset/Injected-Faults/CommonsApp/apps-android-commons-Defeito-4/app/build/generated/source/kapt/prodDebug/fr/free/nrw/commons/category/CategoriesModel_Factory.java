// Generated by Dagger (https://google.github.io/dagger).
package fr.free.nrw.commons.category;

import dagger.internal.Factory;
import fr.free.nrw.commons.upload.GpsCategoryModel;
import javax.inject.Provider;

public final class CategoriesModel_Factory implements Factory<CategoriesModel> {
  private final Provider<CategoryClient> categoryClientProvider;

  private final Provider<CategoryDao> categoryDaoProvider;

  private final Provider<GpsCategoryModel> gpsCategoryModelProvider;

  public CategoriesModel_Factory(Provider<CategoryClient> categoryClientProvider,
      Provider<CategoryDao> categoryDaoProvider,
      Provider<GpsCategoryModel> gpsCategoryModelProvider) {
    this.categoryClientProvider = categoryClientProvider;
    this.categoryDaoProvider = categoryDaoProvider;
    this.gpsCategoryModelProvider = gpsCategoryModelProvider;
  }

  @Override
  public CategoriesModel get() {
    return new CategoriesModel(categoryClientProvider.get(), categoryDaoProvider.get(), gpsCategoryModelProvider.get());
  }

  public static CategoriesModel_Factory create(Provider<CategoryClient> categoryClientProvider,
      Provider<CategoryDao> categoryDaoProvider,
      Provider<GpsCategoryModel> gpsCategoryModelProvider) {
    return new CategoriesModel_Factory(categoryClientProvider, categoryDaoProvider, gpsCategoryModelProvider);
  }

  public static CategoriesModel newInstance(CategoryClient categoryClient, CategoryDao categoryDao,
      GpsCategoryModel gpsCategoryModel) {
    return new CategoriesModel(categoryClient, categoryDao, gpsCategoryModel);
  }
}
