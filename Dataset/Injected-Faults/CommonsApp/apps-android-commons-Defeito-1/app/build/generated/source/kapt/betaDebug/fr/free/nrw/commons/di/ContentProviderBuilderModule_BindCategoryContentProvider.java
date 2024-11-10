package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.category.CategoryContentProvider;

@Module(
  subcomponents =
      ContentProviderBuilderModule_BindCategoryContentProvider.CategoryContentProviderSubcomponent
          .class
)
public abstract class ContentProviderBuilderModule_BindCategoryContentProvider {
  private ContentProviderBuilderModule_BindCategoryContentProvider() {}

  @Binds
  @IntoMap
  @ClassKey(CategoryContentProvider.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      CategoryContentProviderSubcomponent.Factory builder);

  @Subcomponent
  public interface CategoryContentProviderSubcomponent
      extends AndroidInjector<CategoryContentProvider> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<CategoryContentProvider> {}
  }
}
