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
public final class PostListEventListener_Factory_Factory implements Factory<PostListEventListener.Factory> {
  @Override
  public PostListEventListener.Factory get() {
    return newInstance();
  }

  public static PostListEventListener_Factory_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static PostListEventListener.Factory newInstance() {
    return new PostListEventListener.Factory();
  }

  private static final class InstanceHolder {
    private static final PostListEventListener_Factory_Factory INSTANCE = new PostListEventListener_Factory_Factory();
  }
}
