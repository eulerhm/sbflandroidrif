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
public final class PreviewStateHelper_Factory implements Factory<PreviewStateHelper> {
  @Override
  public PreviewStateHelper get() {
    return newInstance();
  }

  public static PreviewStateHelper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static PreviewStateHelper newInstance() {
    return new PreviewStateHelper();
  }

  private static final class InstanceHolder {
    private static final PreviewStateHelper_Factory INSTANCE = new PreviewStateHelper_Factory();
  }
}
