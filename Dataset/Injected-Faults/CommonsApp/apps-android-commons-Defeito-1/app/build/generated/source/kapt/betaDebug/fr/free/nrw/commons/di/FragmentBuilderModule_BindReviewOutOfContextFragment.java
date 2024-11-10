package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.review.ReviewImageFragment;

@Module(
  subcomponents =
      FragmentBuilderModule_BindReviewOutOfContextFragment.ReviewImageFragmentSubcomponent.class
)
public abstract class FragmentBuilderModule_BindReviewOutOfContextFragment {
  private FragmentBuilderModule_BindReviewOutOfContextFragment() {}

  @Binds
  @IntoMap
  @ClassKey(ReviewImageFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      ReviewImageFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface ReviewImageFragmentSubcomponent extends AndroidInjector<ReviewImageFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<ReviewImageFragment> {}
  }
}
