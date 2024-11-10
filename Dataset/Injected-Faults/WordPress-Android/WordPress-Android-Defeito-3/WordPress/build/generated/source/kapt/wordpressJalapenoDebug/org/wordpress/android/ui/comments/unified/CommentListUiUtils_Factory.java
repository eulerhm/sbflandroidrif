package org.wordpress.android.ui.comments.unified;

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
public final class CommentListUiUtils_Factory implements Factory<CommentListUiUtils> {
  @Override
  public CommentListUiUtils get() {
    return newInstance();
  }

  public static CommentListUiUtils_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CommentListUiUtils newInstance() {
    return new CommentListUiUtils();
  }

  private static final class InstanceHolder {
    private static final CommentListUiUtils_Factory INSTANCE = new CommentListUiUtils_Factory();
  }
}
