package org.wordpress.android.models.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;

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
public final class LocalCommentCacheUpdateUseCase_Factory implements Factory<LocalCommentCacheUpdateUseCase> {
  @Override
  public LocalCommentCacheUpdateUseCase get() {
    return newInstance();
  }

  public static LocalCommentCacheUpdateUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static LocalCommentCacheUpdateUseCase newInstance() {
    return new LocalCommentCacheUpdateUseCase();
  }

  private static final class InstanceHolder {
    private static final LocalCommentCacheUpdateUseCase_Factory INSTANCE = new LocalCommentCacheUpdateUseCase_Factory();
  }
}
