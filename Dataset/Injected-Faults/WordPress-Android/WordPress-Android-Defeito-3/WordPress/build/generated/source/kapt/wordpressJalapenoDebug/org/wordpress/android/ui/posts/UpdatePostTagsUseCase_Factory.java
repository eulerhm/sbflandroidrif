package org.wordpress.android.ui.posts;

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
public final class UpdatePostTagsUseCase_Factory implements Factory<UpdatePostTagsUseCase> {
  @Override
  public UpdatePostTagsUseCase get() {
    return newInstance();
  }

  public static UpdatePostTagsUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static UpdatePostTagsUseCase newInstance() {
    return new UpdatePostTagsUseCase();
  }

  private static final class InstanceHolder {
    private static final UpdatePostTagsUseCase_Factory INSTANCE = new UpdatePostTagsUseCase_Factory();
  }
}
