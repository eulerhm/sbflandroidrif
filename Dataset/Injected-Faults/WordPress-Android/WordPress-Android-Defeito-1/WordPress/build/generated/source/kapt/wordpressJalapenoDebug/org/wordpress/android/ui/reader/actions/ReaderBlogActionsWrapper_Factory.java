package org.wordpress.android.ui.reader.actions;

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
public final class ReaderBlogActionsWrapper_Factory implements Factory<ReaderBlogActionsWrapper> {
  private final Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider;

  public ReaderBlogActionsWrapper_Factory(Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider) {
    this.readerUtilsWrapperProvider = readerUtilsWrapperProvider;
  }

  @Override
  public ReaderBlogActionsWrapper get() {
    return newInstance(readerUtilsWrapperProvider.get());
  }

  public static ReaderBlogActionsWrapper_Factory create(
      Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider) {
    return new ReaderBlogActionsWrapper_Factory(readerUtilsWrapperProvider);
  }

  public static ReaderBlogActionsWrapper newInstance(ReaderUtilsWrapper readerUtilsWrapper) {
    return new ReaderBlogActionsWrapper(readerUtilsWrapper);
  }
}
