package org.wordpress.android.models.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;

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
public final class UnifiedCommentsListHandler_Factory implements Factory<UnifiedCommentsListHandler> {
  private final Provider<PaginateCommentsUseCase> paginateCommentsUseCaseProvider;

  private final Provider<BatchModerateCommentsUseCase> batchModerationUseCaseProvider;

  private final Provider<ModerateCommentWithUndoUseCase> moderationWithUndoUseCaseProvider;

  public UnifiedCommentsListHandler_Factory(
      Provider<PaginateCommentsUseCase> paginateCommentsUseCaseProvider,
      Provider<BatchModerateCommentsUseCase> batchModerationUseCaseProvider,
      Provider<ModerateCommentWithUndoUseCase> moderationWithUndoUseCaseProvider) {
    this.paginateCommentsUseCaseProvider = paginateCommentsUseCaseProvider;
    this.batchModerationUseCaseProvider = batchModerationUseCaseProvider;
    this.moderationWithUndoUseCaseProvider = moderationWithUndoUseCaseProvider;
  }

  @Override
  public UnifiedCommentsListHandler get() {
    return newInstance(paginateCommentsUseCaseProvider.get(), batchModerationUseCaseProvider.get(), moderationWithUndoUseCaseProvider.get());
  }

  public static UnifiedCommentsListHandler_Factory create(
      Provider<PaginateCommentsUseCase> paginateCommentsUseCaseProvider,
      Provider<BatchModerateCommentsUseCase> batchModerationUseCaseProvider,
      Provider<ModerateCommentWithUndoUseCase> moderationWithUndoUseCaseProvider) {
    return new UnifiedCommentsListHandler_Factory(paginateCommentsUseCaseProvider, batchModerationUseCaseProvider, moderationWithUndoUseCaseProvider);
  }

  public static UnifiedCommentsListHandler newInstance(
      PaginateCommentsUseCase paginateCommentsUseCase,
      BatchModerateCommentsUseCase batchModerationUseCase,
      ModerateCommentWithUndoUseCase moderationWithUndoUseCase) {
    return new UnifiedCommentsListHandler(paginateCommentsUseCase, batchModerationUseCase, moderationWithUndoUseCase);
  }
}
