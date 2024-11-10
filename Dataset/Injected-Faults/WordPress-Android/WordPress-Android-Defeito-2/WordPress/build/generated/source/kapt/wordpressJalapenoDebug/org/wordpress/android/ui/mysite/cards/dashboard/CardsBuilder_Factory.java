package org.wordpress.android.ui.mysite.cards.dashboard;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.mysite.cards.dashboard.bloggingprompts.BloggingPromptCardBuilder;
import org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardBuilder;
import org.wordpress.android.ui.mysite.cards.dashboard.todaysstats.TodaysStatsCardBuilder;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class CardsBuilder_Factory implements Factory<CardsBuilder> {
  private final Provider<TodaysStatsCardBuilder> todaysStatsCardBuilderProvider;

  private final Provider<PostCardBuilder> postCardBuilderProvider;

  private final Provider<BloggingPromptCardBuilder> bloggingPromptCardBuilderProvider;

  public CardsBuilder_Factory(Provider<TodaysStatsCardBuilder> todaysStatsCardBuilderProvider,
      Provider<PostCardBuilder> postCardBuilderProvider,
      Provider<BloggingPromptCardBuilder> bloggingPromptCardBuilderProvider) {
    this.todaysStatsCardBuilderProvider = todaysStatsCardBuilderProvider;
    this.postCardBuilderProvider = postCardBuilderProvider;
    this.bloggingPromptCardBuilderProvider = bloggingPromptCardBuilderProvider;
  }

  @Override
  public CardsBuilder get() {
    return newInstance(todaysStatsCardBuilderProvider.get(), postCardBuilderProvider.get(), bloggingPromptCardBuilderProvider.get());
  }

  public static CardsBuilder_Factory create(
      Provider<TodaysStatsCardBuilder> todaysStatsCardBuilderProvider,
      Provider<PostCardBuilder> postCardBuilderProvider,
      Provider<BloggingPromptCardBuilder> bloggingPromptCardBuilderProvider) {
    return new CardsBuilder_Factory(todaysStatsCardBuilderProvider, postCardBuilderProvider, bloggingPromptCardBuilderProvider);
  }

  public static CardsBuilder newInstance(TodaysStatsCardBuilder todaysStatsCardBuilder,
      PostCardBuilder postCardBuilder, BloggingPromptCardBuilder bloggingPromptCardBuilder) {
    return new CardsBuilder(todaysStatsCardBuilder, postCardBuilder, bloggingPromptCardBuilder);
  }
}
