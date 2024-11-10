package org.wordpress.android.ui.reader;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;

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
public final class ReaderCommentListViewModel_Factory implements Factory<ReaderCommentListViewModel> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  public ReaderCommentListViewModel_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
  }

  @Override
  public ReaderCommentListViewModel get() {
    return newInstance(mainDispatcherProvider.get());
  }

  public static ReaderCommentListViewModel_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    return new ReaderCommentListViewModel_Factory(mainDispatcherProvider);
  }

  public static ReaderCommentListViewModel newInstance(CoroutineDispatcher mainDispatcher) {
    return new ReaderCommentListViewModel(mainDispatcher);
  }
}
