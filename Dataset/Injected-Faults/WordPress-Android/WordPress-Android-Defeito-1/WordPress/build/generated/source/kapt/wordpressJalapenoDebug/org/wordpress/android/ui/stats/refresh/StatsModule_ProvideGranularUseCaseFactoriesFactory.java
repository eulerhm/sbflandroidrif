package org.wordpress.android.ui.stats.refresh;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.util.List;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.stats.refresh.lists.sections.granular.GranularUseCaseFactory;
import org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.AuthorsUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.ClicksUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.CountryViewsUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.FileDownloadsUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.OverviewUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.PostsAndPagesUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.ReferrersUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.SearchTermsUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.VideoPlaysUseCase;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("javax.inject.Named")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class StatsModule_ProvideGranularUseCaseFactoriesFactory implements Factory<List<GranularUseCaseFactory>> {
  private final StatsModule module;

  private final Provider<PostsAndPagesUseCase.PostsAndPagesUseCaseFactory> postsAndPagesUseCaseFactoryProvider;

  private final Provider<ReferrersUseCase.ReferrersUseCaseFactory> referrersUseCaseFactoryProvider;

  private final Provider<ClicksUseCase.ClicksUseCaseFactory> clicksUseCaseFactoryProvider;

  private final Provider<CountryViewsUseCase.CountryViewsUseCaseFactory> countryViewsUseCaseFactoryProvider;

  private final Provider<VideoPlaysUseCase.VideoPlaysUseCaseFactory> videoPlaysUseCaseFactoryProvider;

  private final Provider<SearchTermsUseCase.SearchTermsUseCaseFactory> searchTermsUseCaseFactoryProvider;

  private final Provider<AuthorsUseCase.AuthorsUseCaseFactory> authorsUseCaseFactoryProvider;

  private final Provider<OverviewUseCase.OverviewUseCaseFactory> overviewUseCaseFactoryProvider;

  private final Provider<FileDownloadsUseCase.FileDownloadsUseCaseFactory> fileDownloadsUseCaseFactoryProvider;

  public StatsModule_ProvideGranularUseCaseFactoriesFactory(StatsModule module,
      Provider<PostsAndPagesUseCase.PostsAndPagesUseCaseFactory> postsAndPagesUseCaseFactoryProvider,
      Provider<ReferrersUseCase.ReferrersUseCaseFactory> referrersUseCaseFactoryProvider,
      Provider<ClicksUseCase.ClicksUseCaseFactory> clicksUseCaseFactoryProvider,
      Provider<CountryViewsUseCase.CountryViewsUseCaseFactory> countryViewsUseCaseFactoryProvider,
      Provider<VideoPlaysUseCase.VideoPlaysUseCaseFactory> videoPlaysUseCaseFactoryProvider,
      Provider<SearchTermsUseCase.SearchTermsUseCaseFactory> searchTermsUseCaseFactoryProvider,
      Provider<AuthorsUseCase.AuthorsUseCaseFactory> authorsUseCaseFactoryProvider,
      Provider<OverviewUseCase.OverviewUseCaseFactory> overviewUseCaseFactoryProvider,
      Provider<FileDownloadsUseCase.FileDownloadsUseCaseFactory> fileDownloadsUseCaseFactoryProvider) {
    this.module = module;
    this.postsAndPagesUseCaseFactoryProvider = postsAndPagesUseCaseFactoryProvider;
    this.referrersUseCaseFactoryProvider = referrersUseCaseFactoryProvider;
    this.clicksUseCaseFactoryProvider = clicksUseCaseFactoryProvider;
    this.countryViewsUseCaseFactoryProvider = countryViewsUseCaseFactoryProvider;
    this.videoPlaysUseCaseFactoryProvider = videoPlaysUseCaseFactoryProvider;
    this.searchTermsUseCaseFactoryProvider = searchTermsUseCaseFactoryProvider;
    this.authorsUseCaseFactoryProvider = authorsUseCaseFactoryProvider;
    this.overviewUseCaseFactoryProvider = overviewUseCaseFactoryProvider;
    this.fileDownloadsUseCaseFactoryProvider = fileDownloadsUseCaseFactoryProvider;
  }

  @Override
  public List<GranularUseCaseFactory> get() {
    return provideGranularUseCaseFactories(module, postsAndPagesUseCaseFactoryProvider.get(), referrersUseCaseFactoryProvider.get(), clicksUseCaseFactoryProvider.get(), countryViewsUseCaseFactoryProvider.get(), videoPlaysUseCaseFactoryProvider.get(), searchTermsUseCaseFactoryProvider.get(), authorsUseCaseFactoryProvider.get(), overviewUseCaseFactoryProvider.get(), fileDownloadsUseCaseFactoryProvider.get());
  }

  public static StatsModule_ProvideGranularUseCaseFactoriesFactory create(StatsModule module,
      Provider<PostsAndPagesUseCase.PostsAndPagesUseCaseFactory> postsAndPagesUseCaseFactoryProvider,
      Provider<ReferrersUseCase.ReferrersUseCaseFactory> referrersUseCaseFactoryProvider,
      Provider<ClicksUseCase.ClicksUseCaseFactory> clicksUseCaseFactoryProvider,
      Provider<CountryViewsUseCase.CountryViewsUseCaseFactory> countryViewsUseCaseFactoryProvider,
      Provider<VideoPlaysUseCase.VideoPlaysUseCaseFactory> videoPlaysUseCaseFactoryProvider,
      Provider<SearchTermsUseCase.SearchTermsUseCaseFactory> searchTermsUseCaseFactoryProvider,
      Provider<AuthorsUseCase.AuthorsUseCaseFactory> authorsUseCaseFactoryProvider,
      Provider<OverviewUseCase.OverviewUseCaseFactory> overviewUseCaseFactoryProvider,
      Provider<FileDownloadsUseCase.FileDownloadsUseCaseFactory> fileDownloadsUseCaseFactoryProvider) {
    return new StatsModule_ProvideGranularUseCaseFactoriesFactory(module, postsAndPagesUseCaseFactoryProvider, referrersUseCaseFactoryProvider, clicksUseCaseFactoryProvider, countryViewsUseCaseFactoryProvider, videoPlaysUseCaseFactoryProvider, searchTermsUseCaseFactoryProvider, authorsUseCaseFactoryProvider, overviewUseCaseFactoryProvider, fileDownloadsUseCaseFactoryProvider);
  }

  public static List<GranularUseCaseFactory> provideGranularUseCaseFactories(StatsModule instance,
      PostsAndPagesUseCase.PostsAndPagesUseCaseFactory postsAndPagesUseCaseFactory,
      ReferrersUseCase.ReferrersUseCaseFactory referrersUseCaseFactory,
      ClicksUseCase.ClicksUseCaseFactory clicksUseCaseFactory,
      CountryViewsUseCase.CountryViewsUseCaseFactory countryViewsUseCaseFactory,
      VideoPlaysUseCase.VideoPlaysUseCaseFactory videoPlaysUseCaseFactory,
      SearchTermsUseCase.SearchTermsUseCaseFactory searchTermsUseCaseFactory,
      AuthorsUseCase.AuthorsUseCaseFactory authorsUseCaseFactory,
      OverviewUseCase.OverviewUseCaseFactory overviewUseCaseFactory,
      FileDownloadsUseCase.FileDownloadsUseCaseFactory fileDownloadsUseCaseFactory) {
    return Preconditions.checkNotNullFromProvides(instance.provideGranularUseCaseFactories(postsAndPagesUseCaseFactory, referrersUseCaseFactory, clicksUseCaseFactory, countryViewsUseCaseFactory, videoPlaysUseCaseFactory, searchTermsUseCaseFactory, authorsUseCaseFactory, overviewUseCaseFactory, fileDownloadsUseCaseFactory));
  }
}
