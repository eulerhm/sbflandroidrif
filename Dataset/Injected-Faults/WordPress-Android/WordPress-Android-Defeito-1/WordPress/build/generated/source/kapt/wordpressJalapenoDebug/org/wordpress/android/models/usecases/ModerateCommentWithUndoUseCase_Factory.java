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
public final class ModerateCommentWithUndoUseCase_Factory implements Factory<ModerateCommentWithUndoUseCase> {
  private final Provider<ModerateCommentsResourceProvider> moderateCommentsResourceProvider;

  public ModerateCommentWithUndoUseCase_Factory(
      Provider<ModerateCommentsResourceProvider> moderateCommentsResourceProvider) {
    this.moderateCommentsResourceProvider = moderateCommentsResourceProvider;
  }

  @Override
  public ModerateCommentWithUndoUseCase get() {
    return newInstance(moderateCommentsResourceProvider.get());
  }

  public static ModerateCommentWithUndoUseCase_Factory create(
      Provider<ModerateCommentsResourceProvider> moderateCommentsResourceProvider) {
    return new ModerateCommentWithUndoUseCase_Factory(moderateCommentsResourceProvider);
  }

  public static ModerateCommentWithUndoUseCase newInstance(
      ModerateCommentsResourceProvider moderateCommentsResourceProvider) {
    return new ModerateCommentWithUndoUseCase(moderateCommentsResourceProvider);
  }
}
