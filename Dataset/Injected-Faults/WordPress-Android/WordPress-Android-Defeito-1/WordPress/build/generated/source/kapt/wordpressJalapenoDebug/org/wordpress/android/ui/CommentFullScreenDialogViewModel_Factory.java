package org.wordpress.android.ui;

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
public final class CommentFullScreenDialogViewModel_Factory implements Factory<CommentFullScreenDialogViewModel> {
  @Override
  public CommentFullScreenDialogViewModel get() {
    return newInstance();
  }

  public static CommentFullScreenDialogViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CommentFullScreenDialogViewModel newInstance() {
    return new CommentFullScreenDialogViewModel();
  }

  private static final class InstanceHolder {
    private static final CommentFullScreenDialogViewModel_Factory INSTANCE = new CommentFullScreenDialogViewModel_Factory();
  }
}
