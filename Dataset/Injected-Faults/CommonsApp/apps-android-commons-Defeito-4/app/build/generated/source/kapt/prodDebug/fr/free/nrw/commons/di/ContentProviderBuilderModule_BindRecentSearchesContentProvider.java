package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.explore.recentsearches.RecentSearchesContentProvider;

@Module(
  subcomponents =
      ContentProviderBuilderModule_BindRecentSearchesContentProvider
          .RecentSearchesContentProviderSubcomponent.class
)
public abstract class ContentProviderBuilderModule_BindRecentSearchesContentProvider {
  private ContentProviderBuilderModule_BindRecentSearchesContentProvider() {}

  @Binds
  @IntoMap
  @ClassKey(RecentSearchesContentProvider.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      RecentSearchesContentProviderSubcomponent.Factory builder);

  @Subcomponent
  public interface RecentSearchesContentProviderSubcomponent
      extends AndroidInjector<RecentSearchesContentProvider> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<RecentSearchesContentProvider> {}
  }
}
