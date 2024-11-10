package org.wordpress.android.ui.comments.unified.usecase;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.CommentsStore;

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
public final class GetCommentUseCase_Factory implements Factory<GetCommentUseCase> {
  private final Provider<CommentsStore> commentsStoreProvider;

  public GetCommentUseCase_Factory(Provider<CommentsStore> commentsStoreProvider) {
    this.commentsStoreProvider = commentsStoreProvider;
  }

  @Override
  public GetCommentUseCase get() {
    return newInstance(commentsStoreProvider.get());
  }

  public static GetCommentUseCase_Factory create(Provider<CommentsStore> commentsStoreProvider) {
    return new GetCommentUseCase_Factory(commentsStoreProvider);
  }

  public static GetCommentUseCase newInstance(CommentsStore commentsStore) {
    return new GetCommentUseCase(commentsStore);
  }
}
