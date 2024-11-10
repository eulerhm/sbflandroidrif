package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.profile.ProfileActivity;

@Module(
  subcomponents = ActivityBuilderModule_BindAchievementsActivity.ProfileActivitySubcomponent.class
)
public abstract class ActivityBuilderModule_BindAchievementsActivity {
  private ActivityBuilderModule_BindAchievementsActivity() {}

  @Binds
  @IntoMap
  @ClassKey(ProfileActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      ProfileActivitySubcomponent.Factory builder);

  @Subcomponent
  public interface ProfileActivitySubcomponent extends AndroidInjector<ProfileActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<ProfileActivity> {}
  }
}
