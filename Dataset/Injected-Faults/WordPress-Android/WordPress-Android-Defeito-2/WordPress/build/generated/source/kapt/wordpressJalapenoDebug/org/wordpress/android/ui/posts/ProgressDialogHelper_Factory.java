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
public final class ProgressDialogHelper_Factory implements Factory<ProgressDialogHelper> {
  @Override
  public ProgressDialogHelper get() {
    return newInstance();
  }

  public static ProgressDialogHelper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ProgressDialogHelper newInstance() {
    return new ProgressDialogHelper();
  }

  private static final class InstanceHolder {
    private static final ProgressDialogHelper_Factory INSTANCE = new ProgressDialogHelper_Factory();
  }
}
