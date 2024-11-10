package org.wordpress.android.ui.reader.discover;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.datasets.ReaderBlogTableWrapper;
import org.wordpress.android.datasets.wrappers.ReaderPostTableWrapper;
import org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper;
import org.wordpress.android.util.config.SeenUnseenWithCounterFeatureConfig;

@ScopeMetadata("dagger.Reusable")
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
public final class ReaderPostMoreButtonUiStateBuilder_Factory implements Factory<ReaderPostMoreButtonUiStateBuilder> {
  private final Provider<ReaderPostTableWrapper> readerPostTableWrapperProvider;

  private final Provider<ReaderBlogTableWrapper> readerBlogTableWrapperProvider;

  private final Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider;

  private final Provider<SeenUnseenWithCounterFeatureConfig> seenUnseenWithCounterFeatureConfigProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public ReaderPostMoreButtonUiStateBuilder_Factory(
      Provider<ReaderPostTableWrapper> readerPostTableWrapperProvider,
      Provider<ReaderBlogTableWrapper> readerBlogTableWrapperProvider,
      Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider,
      Provider<SeenUnseenWithCounterFeatureConfig> seenUnseenWithCounterFeatureConfigProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.readerPostTableWrapperProvider = readerPostTableWrapperProvider;
    this.readerBlogTableWrapperProvider = readerBlogTableWrapperProvider;
    this.readerUtilsWrapperProvider = readerUtilsWrapperProvider;
    this.seenUnseenWithCounterFeatureConfigProvider = seenUnseenWithCounterFeatureConfigProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public ReaderPostMoreButtonUiStateBuilder get() {
    return newInstance(readerPostTableWrapperProvider.get(), readerBlogTableWrapperProvider.get(), readerUtilsWrapperProvider.get(), seenUnseenWithCounterFeatureConfigProvider.get(), bgDispatcherProvider.get());
  }

  public static ReaderPostMoreButtonUiStateBuilder_Factory create(
      Provider<ReaderPostTableWrapper> readerPostTableWrapperProvider,
      Provider<ReaderBlogTableWrapper> readerBlogTableWrapperProvider,
      Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider,
      Provider<SeenUnseenWithCounterFeatureConfig> seenUnseenWithCounterFeatureConfigProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new ReaderPostMoreButtonUiStateBuilder_Factory(readerPostTableWrapperProvider, readerBlogTableWrapperProvider, readerUtilsWrapperProvider, seenUnseenWithCounterFeatureConfigProvider, bgDispatcherProvider);
  }

  public static ReaderPostMoreButtonUiStateBuilder newInstance(
      ReaderPostTableWrapper readerPostTableWrapper, ReaderBlogTableWrapper readerBlogTableWrapper,
      ReaderUtilsWrapper readerUtilsWrapper,
      SeenUnseenWithCounterFeatureConfig seenUnseenWithCounterFeatureConfig,
      CoroutineDispatcher bgDispatcher) {
    return new ReaderPostMoreButtonUiStateBuilder(readerPostTableWrapper, readerBlogTableWrapper, readerUtilsWrapper, seenUnseenWithCounterFeatureConfig, bgDispatcher);
  }
}
