package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.review.ReviewActivity;

@Module(subcomponents = ActivityBuilderModule_BindReviewActivity.ReviewActivitySubcomponent.class)
public abstract class ActivityBuilderModule_BindReviewActivity {
  private ActivityBuilderModule_BindReviewActivity() {}

  @Binds
  @IntoMap
  @ClassKey(ReviewActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      ReviewActivitySubcomponent.Factory builder);

  @Subcomponent
  public interface ReviewActivitySubcomponent extends AndroidInjector<ReviewActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<ReviewActivity> {}
  }
}
