package org.wordpress.android.ui.comments.unified;

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
public final class UnifiedCommentActivityViewModel_Factory implements Factory<UnifiedCommentActivityViewModel> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  public UnifiedCommentActivityViewModel_Factory(
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
  }

  @Override
  public UnifiedCommentActivityViewModel get() {
    return newInstance(mainDispatcherProvider.get());
  }

  public static UnifiedCommentActivityViewModel_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    return new UnifiedCommentActivityViewModel_Factory(mainDispatcherProvider);
  }

  public static UnifiedCommentActivityViewModel newInstance(CoroutineDispatcher mainDispatcher) {
    return new UnifiedCommentActivityViewModel(mainDispatcher);
  }
}
