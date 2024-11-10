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
import org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.PostsAndPagesUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.TotalLikesDetailUseCase;

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
public final class StatsModule_ProvideLikesDetailUseCasesFactory implements Factory<List<GranularUseCaseFactory>> {
  private final StatsModule module;

  private final Provider<TotalLikesDetailUseCase.TotalLikesGranularUseCaseFactory> totalLikesUseCaseFactoryProvider;

  private final Provider<PostsAndPagesUseCase.PostsAndPagesUseCaseFactory> postsAndPagesUseCaseFactoryProvider;

  public StatsModule_ProvideLikesDetailUseCasesFactory(StatsModule module,
      Provider<TotalLikesDetailUseCase.TotalLikesGranularUseCaseFactory> totalLikesUseCaseFactoryProvider,
      Provider<PostsAndPagesUseCase.PostsAndPagesUseCaseFactory> postsAndPagesUseCaseFactoryProvider) {
    this.module = module;
    this.totalLikesUseCaseFactoryProvider = totalLikesUseCaseFactoryProvider;
    this.postsAndPagesUseCaseFactoryProvider = postsAndPagesUseCaseFactoryProvider;
  }

  @Override
  public List<GranularUseCaseFactory> get() {
    return provideLikesDetailUseCases(module, totalLikesUseCaseFactoryProvider.get(), postsAndPagesUseCaseFactoryProvider.get());
  }

  public static StatsModule_ProvideLikesDetailUseCasesFactory create(StatsModule module,
      Provider<TotalLikesDetailUseCase.TotalLikesGranularUseCaseFactory> totalLikesUseCaseFactoryProvider,
      Provider<PostsAndPagesUseCase.PostsAndPagesUseCaseFactory> postsAndPagesUseCaseFactoryProvider) {
    return new StatsModule_ProvideLikesDetailUseCasesFactory(module, totalLikesUseCaseFactoryProvider, postsAndPagesUseCaseFactoryProvider);
  }

  public static List<GranularUseCaseFactory> provideLikesDetailUseCases(StatsModule instance,
      TotalLikesDetailUseCase.TotalLikesGranularUseCaseFactory totalLikesUseCaseFactory,
      PostsAndPagesUseCase.PostsAndPagesUseCaseFactory postsAndPagesUseCaseFactory) {
    return Preconditions.checkNotNullFromProvides(instance.provideLikesDetailUseCases(totalLikesUseCaseFactory, postsAndPagesUseCaseFactory));
  }
}
