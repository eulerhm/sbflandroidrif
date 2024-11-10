package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.recentlanguages.RecentLanguagesContentProvider;

@Module(
  subcomponents =
      ContentProviderBuilderModule_BindRecentLanguagesContentProvider
          .RecentLanguagesContentProviderSubcomponent.class
)
public abstract class ContentProviderBuilderModule_BindRecentLanguagesContentProvider {
  private ContentProviderBuilderModule_BindRecentLanguagesContentProvider() {}

  @Binds
  @IntoMap
  @ClassKey(RecentLanguagesContentProvider.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      RecentLanguagesContentProviderSubcomponent.Factory builder);

  @Subcomponent
  public interface RecentLanguagesContentProviderSubcomponent
      extends AndroidInjector<RecentLanguagesContentProvider> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<RecentLanguagesContentProvider> {}
  }
}
