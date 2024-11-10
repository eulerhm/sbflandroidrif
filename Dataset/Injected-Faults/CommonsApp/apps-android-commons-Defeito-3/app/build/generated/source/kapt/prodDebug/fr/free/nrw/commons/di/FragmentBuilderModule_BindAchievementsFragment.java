package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.profile.achievements.AchievementsFragment;

@Module(
  subcomponents =
      FragmentBuilderModule_BindAchievementsFragment.AchievementsFragmentSubcomponent.class
)
public abstract class FragmentBuilderModule_BindAchievementsFragment {
  private FragmentBuilderModule_BindAchievementsFragment() {}

  @Binds
  @IntoMap
  @ClassKey(AchievementsFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      AchievementsFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface AchievementsFragmentSubcomponent extends AndroidInjector<AchievementsFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<AchievementsFragment> {}
  }
}
