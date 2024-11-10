package org.wordpress.android.ui.posts;

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
public final class UpdateFeaturedImageUseCase_Factory implements Factory<UpdateFeaturedImageUseCase> {
  @Override
  public UpdateFeaturedImageUseCase get() {
    return newInstance();
  }

  public static UpdateFeaturedImageUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static UpdateFeaturedImageUseCase newInstance() {
    return new UpdateFeaturedImageUseCase();
  }

  private static final class InstanceHolder {
    private static final UpdateFeaturedImageUseCase_Factory INSTANCE = new UpdateFeaturedImageUseCase_Factory();
  }
}
