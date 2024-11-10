package org.wordpress.android.viewmodel.pages;

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
public final class PostModelUploadUiStateUseCase_Factory implements Factory<PostModelUploadUiStateUseCase> {
  @Override
  public PostModelUploadUiStateUseCase get() {
    return newInstance();
  }

  public static PostModelUploadUiStateUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static PostModelUploadUiStateUseCase newInstance() {
    return new PostModelUploadUiStateUseCase();
  }

  private static final class InstanceHolder {
    private static final PostModelUploadUiStateUseCase_Factory INSTANCE = new PostModelUploadUiStateUseCase_Factory();
  }
}
