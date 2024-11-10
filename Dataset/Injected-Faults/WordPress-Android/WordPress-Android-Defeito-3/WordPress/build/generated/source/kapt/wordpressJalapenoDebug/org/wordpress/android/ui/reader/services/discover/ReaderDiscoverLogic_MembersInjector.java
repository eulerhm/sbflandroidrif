package org.wordpress.android.ui.reader.services.discover;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.datasets.ReaderBlogTableWrapper;
import org.wordpress.android.datasets.wrappers.ReaderTagTableWrapper;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.ui.reader.repository.usecases.GetDiscoverCardsUseCase;
import org.wordpress.android.ui.reader.repository.usecases.ParseDiscoverCardsJsonUseCase;
import org.wordpress.android.ui.reader.repository.usecases.tags.GetFollowedTagsUseCase;

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
public final class ReaderDiscoverLogic_MembersInjector implements MembersInjector<ReaderDiscoverLogic> {
  private final Provider<ParseDiscoverCardsJsonUseCase> parseDiscoverCardsJsonUseCaseProvider;

  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  private final Provider<ReaderTagTableWrapper> readerTagTableWrapperProvider;

  private final Provider<GetFollowedTagsUseCase> getFollowedTagsUseCaseProvider;

  private final Provider<ReaderBlogTableWrapper> readerBlogTableWrapperProvider;

  private final Provider<GetDiscoverCardsUseCase> getDiscoverCardsUseCaseProvider;

  public ReaderDiscoverLogic_MembersInjector(
      Provider<ParseDiscoverCardsJsonUseCase> parseDiscoverCardsJsonUseCaseProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<ReaderTagTableWrapper> readerTagTableWrapperProvider,
      Provider<GetFollowedTagsUseCase> getFollowedTagsUseCaseProvider,
      Provider<ReaderBlogTableWrapper> readerBlogTableWrapperProvider,
      Provider<GetDiscoverCardsUseCase> getDiscoverCardsUseCaseProvider) {
    this.parseDiscoverCardsJsonUseCaseProvider = parseDiscoverCardsJsonUseCaseProvider;
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
    this.readerTagTableWrapperProvider = readerTagTableWrapperProvider;
    this.getFollowedTagsUseCaseProvider = getFollowedTagsUseCaseProvider;
    this.readerBlogTableWrapperProvider = readerBlogTableWrapperProvider;
    this.getDiscoverCardsUseCaseProvider = getDiscoverCardsUseCaseProvider;
  }

  public static MembersInjector<ReaderDiscoverLogic> create(
      Provider<ParseDiscoverCardsJsonUseCase> parseDiscoverCardsJsonUseCaseProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<ReaderTagTableWrapper> readerTagTableWrapperProvider,
      Provider<GetFollowedTagsUseCase> getFollowedTagsUseCaseProvider,
      Provider<ReaderBlogTableWrapper> readerBlogTableWrapperProvider,
      Provider<GetDiscoverCardsUseCase> getDiscoverCardsUseCaseProvider) {
    return new ReaderDiscoverLogic_MembersInjector(parseDiscoverCardsJsonUseCaseProvider, appPrefsWrapperProvider, readerTagTableWrapperProvider, getFollowedTagsUseCaseProvider, readerBlogTableWrapperProvider, getDiscoverCardsUseCaseProvider);
  }

  @Override
  public void injectMembers(ReaderDiscoverLogic instance) {
    injectParseDiscoverCardsJsonUseCase(instance, parseDiscoverCardsJsonUseCaseProvider.get());
    injectAppPrefsWrapper(instance, appPrefsWrapperProvider.get());
    injectReaderTagTableWrapper(instance, readerTagTableWrapperProvider.get());
    injectGetFollowedTagsUseCase(instance, getFollowedTagsUseCaseProvider.get());
    injectReaderBlogTableWrapper(instance, readerBlogTableWrapperProvider.get());
    injectGetDiscoverCardsUseCase(instance, getDiscoverCardsUseCaseProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.services.discover.ReaderDiscoverLogic.parseDiscoverCardsJsonUseCase")
  public static void injectParseDiscoverCardsJsonUseCase(ReaderDiscoverLogic instance,
      ParseDiscoverCardsJsonUseCase parseDiscoverCardsJsonUseCase) {
    instance.parseDiscoverCardsJsonUseCase = parseDiscoverCardsJsonUseCase;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.services.discover.ReaderDiscoverLogic.appPrefsWrapper")
  public static void injectAppPrefsWrapper(ReaderDiscoverLogic instance,
      AppPrefsWrapper appPrefsWrapper) {
    instance.appPrefsWrapper = appPrefsWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.services.discover.ReaderDiscoverLogic.readerTagTableWrapper")
  public static void injectReaderTagTableWrapper(ReaderDiscoverLogic instance,
      ReaderTagTableWrapper readerTagTableWrapper) {
    instance.readerTagTableWrapper = readerTagTableWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.services.discover.ReaderDiscoverLogic.getFollowedTagsUseCase")
  public static void injectGetFollowedTagsUseCase(ReaderDiscoverLogic instance,
      GetFollowedTagsUseCase getFollowedTagsUseCase) {
    instance.getFollowedTagsUseCase = getFollowedTagsUseCase;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.services.discover.ReaderDiscoverLogic.readerBlogTableWrapper")
  public static void injectReaderBlogTableWrapper(ReaderDiscoverLogic instance,
      ReaderBlogTableWrapper readerBlogTableWrapper) {
    instance.readerBlogTableWrapper = readerBlogTableWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.services.discover.ReaderDiscoverLogic.getDiscoverCardsUseCase")
  public static void injectGetDiscoverCardsUseCase(ReaderDiscoverLogic instance,
      GetDiscoverCardsUseCase getDiscoverCardsUseCase) {
    instance.getDiscoverCardsUseCase = getDiscoverCardsUseCase;
  }
}
