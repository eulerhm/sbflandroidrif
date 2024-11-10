package org.wordpress.android.ui.reader;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.reader.discover.ReaderPostUiStateBuilder;
import org.wordpress.android.ui.reader.utils.FeaturedImageUtils;
import org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper;
import org.wordpress.android.ui.reader.utils.ThreadedCommentsUtils;
import org.wordpress.android.ui.reader.views.ReaderPostDetailsHeaderViewUiStateBuilder;
import org.wordpress.android.ui.utils.HtmlMessageUtils;
import org.wordpress.android.ui.utils.HtmlUtilsWrapper;
import org.wordpress.android.util.DateTimeUtilsWrapper;
import org.wordpress.android.util.DisplayUtilsWrapper;
import org.wordpress.android.util.GravatarUtilsWrapper;
import org.wordpress.android.viewmodel.ContextProvider;
import org.wordpress.android.viewmodel.ResourceProvider;

@ScopeMetadata("dagger.Reusable")
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
public final class ReaderPostDetailUiStateBuilder_Factory implements Factory<ReaderPostDetailUiStateBuilder> {
  private final Provider<ReaderPostDetailsHeaderViewUiStateBuilder> postDetailsHeaderViewUiStateBuilderProvider;

  private final Provider<ReaderPostUiStateBuilder> postUiStateBuilderProvider;

  private final Provider<FeaturedImageUtils> featuredImageUtilsProvider;

  private final Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider;

  private final Provider<DisplayUtilsWrapper> displayUtilsWrapperProvider;

  private final Provider<ContextProvider> contextProvider;

  private final Provider<HtmlUtilsWrapper> htmlUtilsWrapperProvider;

  private final Provider<HtmlMessageUtils> htmlMessageUtilsProvider;

  private final Provider<DateTimeUtilsWrapper> dateTimeUtilsWrapperProvider;

  private final Provider<GravatarUtilsWrapper> gravatarUtilsWrapperProvider;

  private final Provider<ThreadedCommentsUtils> threadedCommentsUtilsProvider;

  private final Provider<ResourceProvider> resourceProvider;

  public ReaderPostDetailUiStateBuilder_Factory(
      Provider<ReaderPostDetailsHeaderViewUiStateBuilder> postDetailsHeaderViewUiStateBuilderProvider,
      Provider<ReaderPostUiStateBuilder> postUiStateBuilderProvider,
      Provider<FeaturedImageUtils> featuredImageUtilsProvider,
      Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider,
      Provider<DisplayUtilsWrapper> displayUtilsWrapperProvider,
      Provider<ContextProvider> contextProvider,
      Provider<HtmlUtilsWrapper> htmlUtilsWrapperProvider,
      Provider<HtmlMessageUtils> htmlMessageUtilsProvider,
      Provider<DateTimeUtilsWrapper> dateTimeUtilsWrapperProvider,
      Provider<GravatarUtilsWrapper> gravatarUtilsWrapperProvider,
      Provider<ThreadedCommentsUtils> threadedCommentsUtilsProvider,
      Provider<ResourceProvider> resourceProvider) {
    this.postDetailsHeaderViewUiStateBuilderProvider = postDetailsHeaderViewUiStateBuilderProvider;
    this.postUiStateBuilderProvider = postUiStateBuilderProvider;
    this.featuredImageUtilsProvider = featuredImageUtilsProvider;
    this.readerUtilsWrapperProvider = readerUtilsWrapperProvider;
    this.displayUtilsWrapperProvider = displayUtilsWrapperProvider;
    this.contextProvider = contextProvider;
    this.htmlUtilsWrapperProvider = htmlUtilsWrapperProvider;
    this.htmlMessageUtilsProvider = htmlMessageUtilsProvider;
    this.dateTimeUtilsWrapperProvider = dateTimeUtilsWrapperProvider;
    this.gravatarUtilsWrapperProvider = gravatarUtilsWrapperProvider;
    this.threadedCommentsUtilsProvider = threadedCommentsUtilsProvider;
    this.resourceProvider = resourceProvider;
  }

  @Override
  public ReaderPostDetailUiStateBuilder get() {
    return newInstance(postDetailsHeaderViewUiStateBuilderProvider.get(), postUiStateBuilderProvider.get(), featuredImageUtilsProvider.get(), readerUtilsWrapperProvider.get(), displayUtilsWrapperProvider.get(), contextProvider.get(), htmlUtilsWrapperProvider.get(), htmlMessageUtilsProvider.get(), dateTimeUtilsWrapperProvider.get(), gravatarUtilsWrapperProvider.get(), threadedCommentsUtilsProvider.get(), resourceProvider.get());
  }

  public static ReaderPostDetailUiStateBuilder_Factory create(
      Provider<ReaderPostDetailsHeaderViewUiStateBuilder> postDetailsHeaderViewUiStateBuilderProvider,
      Provider<ReaderPostUiStateBuilder> postUiStateBuilderProvider,
      Provider<FeaturedImageUtils> featuredImageUtilsProvider,
      Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider,
      Provider<DisplayUtilsWrapper> displayUtilsWrapperProvider,
      Provider<ContextProvider> contextProvider,
      Provider<HtmlUtilsWrapper> htmlUtilsWrapperProvider,
      Provider<HtmlMessageUtils> htmlMessageUtilsProvider,
      Provider<DateTimeUtilsWrapper> dateTimeUtilsWrapperProvider,
      Provider<GravatarUtilsWrapper> gravatarUtilsWrapperProvider,
      Provider<ThreadedCommentsUtils> threadedCommentsUtilsProvider,
      Provider<ResourceProvider> resourceProvider) {
    return new ReaderPostDetailUiStateBuilder_Factory(postDetailsHeaderViewUiStateBuilderProvider, postUiStateBuilderProvider, featuredImageUtilsProvider, readerUtilsWrapperProvider, displayUtilsWrapperProvider, contextProvider, htmlUtilsWrapperProvider, htmlMessageUtilsProvider, dateTimeUtilsWrapperProvider, gravatarUtilsWrapperProvider, threadedCommentsUtilsProvider, resourceProvider);
  }

  public static ReaderPostDetailUiStateBuilder newInstance(
      ReaderPostDetailsHeaderViewUiStateBuilder postDetailsHeaderViewUiStateBuilder,
      ReaderPostUiStateBuilder postUiStateBuilder, FeaturedImageUtils featuredImageUtils,
      ReaderUtilsWrapper readerUtilsWrapper, DisplayUtilsWrapper displayUtilsWrapper,
      ContextProvider contextProvider, HtmlUtilsWrapper htmlUtilsWrapper,
      HtmlMessageUtils htmlMessageUtils, DateTimeUtilsWrapper dateTimeUtilsWrapper,
      GravatarUtilsWrapper gravatarUtilsWrapper, ThreadedCommentsUtils threadedCommentsUtils,
      ResourceProvider resourceProvider) {
    return new ReaderPostDetailUiStateBuilder(postDetailsHeaderViewUiStateBuilder, postUiStateBuilder, featuredImageUtils, readerUtilsWrapper, displayUtilsWrapper, contextProvider, htmlUtilsWrapper, htmlMessageUtils, dateTimeUtilsWrapper, gravatarUtilsWrapper, threadedCommentsUtils, resourceProvider);
  }
}
