package org.wordpress.android.ui.posts.editor;

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
public final class EditorActionsProvider_Factory implements Factory<EditorActionsProvider> {
  @Override
  public EditorActionsProvider get() {
    return newInstance();
  }

  public static EditorActionsProvider_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static EditorActionsProvider newInstance() {
    return new EditorActionsProvider();
  }

  private static final class InstanceHolder {
    private static final EditorActionsProvider_Factory INSTANCE = new EditorActionsProvider_Factory();
  }
}
