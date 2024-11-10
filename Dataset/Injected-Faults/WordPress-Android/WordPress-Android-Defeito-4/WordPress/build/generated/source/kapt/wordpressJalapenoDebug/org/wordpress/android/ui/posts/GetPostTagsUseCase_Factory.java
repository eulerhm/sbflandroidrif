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
public final class GetPostTagsUseCase_Factory implements Factory<GetPostTagsUseCase> {
  @Override
  public GetPostTagsUseCase get() {
    return newInstance();
  }

  public static GetPostTagsUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static GetPostTagsUseCase newInstance() {
    return new GetPostTagsUseCase();
  }

  private static final class InstanceHolder {
    private static final GetPostTagsUseCase_Factory INSTANCE = new GetPostTagsUseCase_Factory();
  }
}
