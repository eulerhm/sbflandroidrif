package org.wordpress.android.ui.reader.subfilter;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.datasets.ReaderBlogTableWrapper;
import org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper;

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
public final class SubfilterListItemMapper_Factory implements Factory<SubfilterListItemMapper> {
  private final Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider;

  private final Provider<ReaderBlogTableWrapper> readerBlogTableWrapperProvider;

  public SubfilterListItemMapper_Factory(Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider,
      Provider<ReaderBlogTableWrapper> readerBlogTableWrapperProvider) {
    this.readerUtilsWrapperProvider = readerUtilsWrapperProvider;
    this.readerBlogTableWrapperProvider = readerBlogTableWrapperProvider;
  }

  @Override
  public SubfilterListItemMapper get() {
    return newInstance(readerUtilsWrapperProvider.get(), readerBlogTableWrapperProvider.get());
  }

  public static SubfilterListItemMapper_Factory create(
      Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider,
      Provider<ReaderBlogTableWrapper> readerBlogTableWrapperProvider) {
    return new SubfilterListItemMapper_Factory(readerUtilsWrapperProvider, readerBlogTableWrapperProvider);
  }

  public static SubfilterListItemMapper newInstance(ReaderUtilsWrapper readerUtilsWrapper,
      ReaderBlogTableWrapper readerBlogTableWrapper) {
    return new SubfilterListItemMapper(readerUtilsWrapper, readerBlogTableWrapper);
  }
}
