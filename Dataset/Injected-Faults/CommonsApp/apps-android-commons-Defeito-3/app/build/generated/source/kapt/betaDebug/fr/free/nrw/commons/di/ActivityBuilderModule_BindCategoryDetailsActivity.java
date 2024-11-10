package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.category.CategoryDetailsActivity;

@Module(
  subcomponents =
      ActivityBuilderModule_BindCategoryDetailsActivity.CategoryDetailsActivitySubcomponent.class
)
public abstract class ActivityBuilderModule_BindCategoryDetailsActivity {
  private ActivityBuilderModule_BindCategoryDetailsActivity() {}

  @Binds
  @IntoMap
  @ClassKey(CategoryDetailsActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      CategoryDetailsActivitySubcomponent.Factory builder);

  @Subcomponent
  public interface CategoryDetailsActivitySubcomponent
      extends AndroidInjector<CategoryDetailsActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<CategoryDetailsActivity> {}
  }
}
