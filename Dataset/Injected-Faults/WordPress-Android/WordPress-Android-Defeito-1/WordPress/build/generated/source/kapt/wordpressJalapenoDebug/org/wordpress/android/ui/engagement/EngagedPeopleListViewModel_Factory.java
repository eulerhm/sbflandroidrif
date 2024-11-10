package org.wordpress.android.ui.engagement;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper;
import org.wordpress.android.util.analytics.AnalyticsUtilsWrapper;

@ScopeMetadata
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
public final class EngagedPeopleListViewModel_Factory implements Factory<EngagedPeopleListViewModel> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<GetLikesHandler> getLikesHandlerProvider;

  private final Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider;

  private final Provider<EngagementUtils> engagementUtilsProvider;

  private final Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider;

  public EngagedPeopleListViewModel_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<GetLikesHandler> getLikesHandlerProvider,
      Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider,
      Provider<EngagementUtils> engagementUtilsProvider,
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.getLikesHandlerProvider = getLikesHandlerProvider;
    this.readerUtilsWrapperProvider = readerUtilsWrapperProvider;
    this.engagementUtilsProvider = engagementUtilsProvider;
    this.analyticsUtilsWrapperProvider = analyticsUtilsWrapperProvider;
  }

  @Override
  public EngagedPeopleListViewModel get() {
    return newInstance(mainDispatcherProvider.get(), bgDispatcherProvider.get(), getLikesHandlerProvider.get(), readerUtilsWrapperProvider.get(), engagementUtilsProvider.get(), analyticsUtilsWrapperProvider.get());
  }

  public static EngagedPeopleListViewModel_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<GetLikesHandler> getLikesHandlerProvider,
      Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider,
      Provider<EngagementUtils> engagementUtilsProvider,
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider) {
    return new EngagedPeopleListViewModel_Factory(mainDispatcherProvider, bgDispatcherProvider, getLikesHandlerProvider, readerUtilsWrapperProvider, engagementUtilsProvider, analyticsUtilsWrapperProvider);
  }

  public static EngagedPeopleListViewModel newInstance(CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher bgDispatcher, GetLikesHandler getLikesHandler,
      ReaderUtilsWrapper readerUtilsWrapper, EngagementUtils engagementUtils,
      AnalyticsUtilsWrapper analyticsUtilsWrapper) {
    return new EngagedPeopleListViewModel(mainDispatcher, bgDispatcher, getLikesHandler, readerUtilsWrapper, engagementUtils, analyticsUtilsWrapper);
  }
}
