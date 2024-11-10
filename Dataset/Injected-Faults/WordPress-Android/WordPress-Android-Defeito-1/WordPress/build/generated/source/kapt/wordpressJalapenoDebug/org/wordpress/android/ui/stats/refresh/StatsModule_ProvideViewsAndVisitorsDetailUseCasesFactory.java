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
import org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.CountryViewsUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.ReferrersUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.ViewsAndVisitorsDetailUseCase;

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
public final class StatsModule_ProvideViewsAndVisitorsDetailUseCasesFactory implements Factory<List<GranularUseCaseFactory>> {
  private final StatsModule module;

  private final Provider<ViewsAndVisitorsDetailUseCase.ViewsAndVisitorsGranularUseCaseFactory> viewsAndVisitorsGranularUseCaseFactoryProvider;

  private final Provider<ReferrersUseCase.ReferrersUseCaseFactory> referrersUseCaseFactoryProvider;

  private final Provider<CountryViewsUseCase.CountryViewsUseCaseFactory> countryViewsUseCaseFactoryProvider;

  public StatsModule_ProvideViewsAndVisitorsDetailUseCasesFactory(StatsModule module,
      Provider<ViewsAndVisitorsDetailUseCase.ViewsAndVisitorsGranularUseCaseFactory> viewsAndVisitorsGranularUseCaseFactoryProvider,
      Provider<ReferrersUseCase.ReferrersUseCaseFactory> referrersUseCaseFactoryProvider,
      Provider<CountryViewsUseCase.CountryViewsUseCaseFactory> countryViewsUseCaseFactoryProvider) {
    this.module = module;
    this.viewsAndVisitorsGranularUseCaseFactoryProvider = viewsAndVisitorsGranularUseCaseFactoryProvider;
    this.referrersUseCaseFactoryProvider = referrersUseCaseFactoryProvider;
    this.countryViewsUseCaseFactoryProvider = countryViewsUseCaseFactoryProvider;
  }

  @Override
  public List<GranularUseCaseFactory> get() {
    return provideViewsAndVisitorsDetailUseCases(module, viewsAndVisitorsGranularUseCaseFactoryProvider.get(), referrersUseCaseFactoryProvider.get(), countryViewsUseCaseFactoryProvider.get());
  }

  public static StatsModule_ProvideViewsAndVisitorsDetailUseCasesFactory create(StatsModule module,
      Provider<ViewsAndVisitorsDetailUseCase.ViewsAndVisitorsGranularUseCaseFactory> viewsAndVisitorsGranularUseCaseFactoryProvider,
      Provider<ReferrersUseCase.ReferrersUseCaseFactory> referrersUseCaseFactoryProvider,
      Provider<CountryViewsUseCase.CountryViewsUseCaseFactory> countryViewsUseCaseFactoryProvider) {
    return new StatsModule_ProvideViewsAndVisitorsDetailUseCasesFactory(module, viewsAndVisitorsGranularUseCaseFactoryProvider, referrersUseCaseFactoryProvider, countryViewsUseCaseFactoryProvider);
  }

  public static List<GranularUseCaseFactory> provideViewsAndVisitorsDetailUseCases(
      StatsModule instance,
      ViewsAndVisitorsDetailUseCase.ViewsAndVisitorsGranularUseCaseFactory viewsAndVisitorsGranularUseCaseFactory,
      ReferrersUseCase.ReferrersUseCaseFactory referrersUseCaseFactory,
      CountryViewsUseCase.CountryViewsUseCaseFactory countryViewsUseCaseFactory) {
    return Preconditions.checkNotNullFromProvides(instance.provideViewsAndVisitorsDetailUseCases(viewsAndVisitorsGranularUseCaseFactory, referrersUseCaseFactory, countryViewsUseCaseFactory));
  }
}
