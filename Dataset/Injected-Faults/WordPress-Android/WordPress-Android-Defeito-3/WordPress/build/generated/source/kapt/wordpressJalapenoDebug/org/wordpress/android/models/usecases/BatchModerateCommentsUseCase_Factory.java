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
public final class BatchModerateCommentsUseCase_Factory implements Factory<BatchModerateCommentsUseCase> {
  private final Provider<ModerateCommentsResourceProvider> moderateCommentsResourceProvider;

  public BatchModerateCommentsUseCase_Factory(
      Provider<ModerateCommentsResourceProvider> moderateCommentsResourceProvider) {
    this.moderateCommentsResourceProvider = moderateCommentsResourceProvider;
  }

  @Override
  public BatchModerateCommentsUseCase get() {
    return newInstance(moderateCommentsResourceProvider.get());
  }

  public static BatchModerateCommentsUseCase_Factory create(
      Provider<ModerateCommentsResourceProvider> moderateCommentsResourceProvider) {
    return new BatchModerateCommentsUseCase_Factory(moderateCommentsResourceProvider);
  }

  public static BatchModerateCommentsUseCase newInstance(
      ModerateCommentsResourceProvider moderateCommentsResourceProvider) {
    return new BatchModerateCommentsUseCase(moderateCommentsResourceProvider);
  }
}
