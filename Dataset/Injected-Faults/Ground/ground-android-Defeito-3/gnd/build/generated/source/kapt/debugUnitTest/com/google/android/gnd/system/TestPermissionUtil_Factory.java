package com.google.android.gnd.system;

import android.app.Application;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class TestPermissionUtil_Factory implements Factory<TestPermissionUtil> {
  private final Provider<Application> applicationProvider;

  private final Provider<ActivityStreams> activityStreamsProvider;

  public TestPermissionUtil_Factory(Provider<Application> applicationProvider,
      Provider<ActivityStreams> activityStreamsProvider) {
    this.applicationProvider = applicationProvider;
    this.activityStreamsProvider = activityStreamsProvider;
  }

  @Override
  public TestPermissionUtil get() {
    TestPermissionUtil instance = newInstance();
    TestPermissionUtil_MembersInjector.injectApplication(instance, applicationProvider.get());
    TestPermissionUtil_MembersInjector.injectActivityStreams(instance, activityStreamsProvider.get());
    return instance;
  }

  public static TestPermissionUtil_Factory create(Provider<Application> applicationProvider,
      Provider<ActivityStreams> activityStreamsProvider) {
    return new TestPermissionUtil_Factory(applicationProvider, activityStreamsProvider);
  }

  public static TestPermissionUtil newInstance() {
    return new TestPermissionUtil();
  }
}
