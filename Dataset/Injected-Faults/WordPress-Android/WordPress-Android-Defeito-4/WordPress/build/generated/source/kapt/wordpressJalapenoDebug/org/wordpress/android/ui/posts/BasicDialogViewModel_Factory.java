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
public final class BasicDialogViewModel_Factory implements Factory<BasicDialogViewModel> {
  @Override
  public BasicDialogViewModel get() {
    return newInstance();
  }

  public static BasicDialogViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static BasicDialogViewModel newInstance() {
    return new BasicDialogViewModel();
  }

  private static final class InstanceHolder {
    private static final BasicDialogViewModel_Factory INSTANCE = new BasicDialogViewModel_Factory();
  }
}
