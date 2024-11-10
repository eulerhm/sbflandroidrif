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
public final class PostEditorAnalyticsSessionWrapper_Factory implements Factory<PostEditorAnalyticsSessionWrapper> {
  @Override
  public PostEditorAnalyticsSessionWrapper get() {
    return newInstance();
  }

  public static PostEditorAnalyticsSessionWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static PostEditorAnalyticsSessionWrapper newInstance() {
    return new PostEditorAnalyticsSessionWrapper();
  }

  private static final class InstanceHolder {
    private static final PostEditorAnalyticsSessionWrapper_Factory INSTANCE = new PostEditorAnalyticsSessionWrapper_Factory();
  }
}
