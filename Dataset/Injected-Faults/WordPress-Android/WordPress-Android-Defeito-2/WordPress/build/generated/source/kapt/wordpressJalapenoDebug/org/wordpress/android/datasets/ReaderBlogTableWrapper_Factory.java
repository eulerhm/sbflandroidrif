package org.wordpress.android.datasets;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
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
public final class ReaderBlogTableWrapper_Factory implements Factory<ReaderBlogTableWrapper> {
  private final Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider;

  public ReaderBlogTableWrapper_Factory(Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider) {
    this.readerUtilsWrapperProvider = readerUtilsWrapperProvider;
  }

  @Override
  public ReaderBlogTableWrapper get() {
    return newInstance(readerUtilsWrapperProvider.get());
  }

  public static ReaderBlogTableWrapper_Factory create(
      Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider) {
    return new ReaderBlogTableWrapper_Factory(readerUtilsWrapperProvider);
  }

  public static ReaderBlogTableWrapper newInstance(ReaderUtilsWrapper readerUtilsWrapper) {
    return new ReaderBlogTableWrapper(readerUtilsWrapper);
  }
}
