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
public final class PaginateCommentsUseCase_Factory implements Factory<PaginateCommentsUseCase> {
  private final Provider<PaginateCommentsResourceProvider> paginateCommentsResourceProvider;

  public PaginateCommentsUseCase_Factory(
      Provider<PaginateCommentsResourceProvider> paginateCommentsResourceProvider) {
    this.paginateCommentsResourceProvider = paginateCommentsResourceProvider;
  }

  @Override
  public PaginateCommentsUseCase get() {
    return newInstance(paginateCommentsResourceProvider.get());
  }

  public static PaginateCommentsUseCase_Factory create(
      Provider<PaginateCommentsResourceProvider> paginateCommentsResourceProvider) {
    return new PaginateCommentsUseCase_Factory(paginateCommentsResourceProvider);
  }

  public static PaginateCommentsUseCase newInstance(
      PaginateCommentsResourceProvider paginateCommentsResourceProvider) {
    return new PaginateCommentsUseCase(paginateCommentsResourceProvider);
  }
}
