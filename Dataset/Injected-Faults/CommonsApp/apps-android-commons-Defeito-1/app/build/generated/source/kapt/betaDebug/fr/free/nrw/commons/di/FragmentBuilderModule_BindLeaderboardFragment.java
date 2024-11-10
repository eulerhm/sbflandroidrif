package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.profile.leaderboard.LeaderboardFragment;

@Module(
  subcomponents =
      FragmentBuilderModule_BindLeaderboardFragment.LeaderboardFragmentSubcomponent.class
)
public abstract class FragmentBuilderModule_BindLeaderboardFragment {
  private FragmentBuilderModule_BindLeaderboardFragment() {}

  @Binds
  @IntoMap
  @ClassKey(LeaderboardFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      LeaderboardFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface LeaderboardFragmentSubcomponent extends AndroidInjector<LeaderboardFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<LeaderboardFragment> {}
  }
}
