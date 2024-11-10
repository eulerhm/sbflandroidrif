package org.wordpress.android.ui.stats.refresh;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.util.List;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.FollowerTypesUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.FollowersUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TotalFollowersUseCase;

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
public final class StatsModule_ProvideFollowersDetailUseCasesFactory implements Factory<List<BaseStatsUseCase<?, ?>>> {
  private final StatsModule module;

  private final Provider<TotalFollowersUseCase.TotalFollowersUseCaseFactory> totalFollowersUseCaseFactoryProvider;

  private final Provider<FollowerTypesUseCase> followerTypesUseCaseProvider;

  private final Provider<FollowersUseCase.FollowersUseCaseFactory> followersUseCaseFactoryProvider;

  public StatsModule_ProvideFollowersDetailUseCasesFactory(StatsModule module,
      Provider<TotalFollowersUseCase.TotalFollowersUseCaseFactory> totalFollowersUseCaseFactoryProvider,
      Provider<FollowerTypesUseCase> followerTypesUseCaseProvider,
      Provider<FollowersUseCase.FollowersUseCaseFactory> followersUseCaseFactoryProvider) {
    this.module = module;
    this.totalFollowersUseCaseFactoryProvider = totalFollowersUseCaseFactoryProvider;
    this.followerTypesUseCaseProvider = followerTypesUseCaseProvider;
    this.followersUseCaseFactoryProvider = followersUseCaseFactoryProvider;
  }

  @Override
  public List<BaseStatsUseCase<?, ?>> get() {
    return provideFollowersDetailUseCases(module, totalFollowersUseCaseFactoryProvider.get(), followerTypesUseCaseProvider.get(), followersUseCaseFactoryProvider.get());
  }

  public static StatsModule_ProvideFollowersDetailUseCasesFactory create(StatsModule module,
      Provider<TotalFollowersUseCase.TotalFollowersUseCaseFactory> totalFollowersUseCaseFactoryProvider,
      Provider<FollowerTypesUseCase> followerTypesUseCaseProvider,
      Provider<FollowersUseCase.FollowersUseCaseFactory> followersUseCaseFactoryProvider) {
    return new StatsModule_ProvideFollowersDetailUseCasesFactory(module, totalFollowersUseCaseFactoryProvider, followerTypesUseCaseProvider, followersUseCaseFactoryProvider);
  }

  public static List<BaseStatsUseCase<?, ?>> provideFollowersDetailUseCases(StatsModule instance,
      TotalFollowersUseCase.TotalFollowersUseCaseFactory totalFollowersUseCaseFactory,
      FollowerTypesUseCase followerTypesUseCase,
      FollowersUseCase.FollowersUseCaseFactory followersUseCaseFactory) {
    return Preconditions.checkNotNullFromProvides(instance.provideFollowersDetailUseCases(totalFollowersUseCaseFactory, followerTypesUseCase, followersUseCaseFactory));
  }
}
