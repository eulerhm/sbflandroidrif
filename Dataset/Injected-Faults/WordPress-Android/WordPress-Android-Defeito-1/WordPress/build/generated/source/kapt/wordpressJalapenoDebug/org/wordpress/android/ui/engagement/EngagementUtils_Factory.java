package org.wordpress.android.ui.engagement;

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
public final class EngagementUtils_Factory implements Factory<EngagementUtils> {
  @Override
  public EngagementUtils get() {
    return newInstance();
  }

  public static EngagementUtils_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static EngagementUtils newInstance() {
    return new EngagementUtils();
  }

  private static final class InstanceHolder {
    private static final EngagementUtils_Factory INSTANCE = new EngagementUtils_Factory();
  }
}
