package org.wordpress.android.ui.stories.usecase;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;

@ScopeMetadata("dagger.Reusable")
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
public final class UpdateStoryPostTitleUseCase_Factory implements Factory<UpdateStoryPostTitleUseCase> {
  @Override
  public UpdateStoryPostTitleUseCase get() {
    return newInstance();
  }

  public static UpdateStoryPostTitleUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static UpdateStoryPostTitleUseCase newInstance() {
    return new UpdateStoryPostTitleUseCase();
  }

  private static final class InstanceHolder {
    private static final UpdateStoryPostTitleUseCase_Factory INSTANCE = new UpdateStoryPostTitleUseCase_Factory();
  }
}
