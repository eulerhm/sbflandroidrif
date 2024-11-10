package org.wordpress.android.ui;

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
public final class ActivityLauncherWrapper_Factory implements Factory<ActivityLauncherWrapper> {
  @Override
  public ActivityLauncherWrapper get() {
    return newInstance();
  }

  public static ActivityLauncherWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ActivityLauncherWrapper newInstance() {
    return new ActivityLauncherWrapper();
  }

  private static final class InstanceHolder {
    private static final ActivityLauncherWrapper_Factory INSTANCE = new ActivityLauncherWrapper_Factory();
  }
}
