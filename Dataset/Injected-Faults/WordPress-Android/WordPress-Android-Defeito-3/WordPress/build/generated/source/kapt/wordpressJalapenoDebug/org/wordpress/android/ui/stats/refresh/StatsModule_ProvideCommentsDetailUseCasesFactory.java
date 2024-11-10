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
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.AuthorsCommentsUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.PostsCommentsUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TotalCommentsUseCase;

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
public final class StatsModule_ProvideCommentsDetailUseCasesFactory implements Factory<List<BaseStatsUseCase<?, ?>>> {
  private final StatsModule module;

  private final Provider<TotalCommentsUseCase.TotalCommentsUseCaseFactory> totalCommentsUseCaseFactoryProvider;

  private final Provider<AuthorsCommentsUseCase> authorsCommentsUseCaseProvider;

  private final Provider<PostsCommentsUseCase> postsCommentsUseCaseProvider;

  public StatsModule_ProvideCommentsDetailUseCasesFactory(StatsModule module,
      Provider<TotalCommentsUseCase.TotalCommentsUseCaseFactory> totalCommentsUseCaseFactoryProvider,
      Provider<AuthorsCommentsUseCase> authorsCommentsUseCaseProvider,
      Provider<PostsCommentsUseCase> postsCommentsUseCaseProvider) {
    this.module = module;
    this.totalCommentsUseCaseFactoryProvider = totalCommentsUseCaseFactoryProvider;
    this.authorsCommentsUseCaseProvider = authorsCommentsUseCaseProvider;
    this.postsCommentsUseCaseProvider = postsCommentsUseCaseProvider;
  }

  @Override
  public List<BaseStatsUseCase<?, ?>> get() {
    return provideCommentsDetailUseCases(module, totalCommentsUseCaseFactoryProvider.get(), authorsCommentsUseCaseProvider.get(), postsCommentsUseCaseProvider.get());
  }

  public static StatsModule_ProvideCommentsDetailUseCasesFactory create(StatsModule module,
      Provider<TotalCommentsUseCase.TotalCommentsUseCaseFactory> totalCommentsUseCaseFactoryProvider,
      Provider<AuthorsCommentsUseCase> authorsCommentsUseCaseProvider,
      Provider<PostsCommentsUseCase> postsCommentsUseCaseProvider) {
    return new StatsModule_ProvideCommentsDetailUseCasesFactory(module, totalCommentsUseCaseFactoryProvider, authorsCommentsUseCaseProvider, postsCommentsUseCaseProvider);
  }

  public static List<BaseStatsUseCase<?, ?>> provideCommentsDetailUseCases(StatsModule instance,
      TotalCommentsUseCase.TotalCommentsUseCaseFactory totalCommentsUseCaseFactory,
      AuthorsCommentsUseCase authorsCommentsUseCase, PostsCommentsUseCase postsCommentsUseCase) {
    return Preconditions.checkNotNullFromProvides(instance.provideCommentsDetailUseCases(totalCommentsUseCaseFactory, authorsCommentsUseCase, postsCommentsUseCase));
  }
}
