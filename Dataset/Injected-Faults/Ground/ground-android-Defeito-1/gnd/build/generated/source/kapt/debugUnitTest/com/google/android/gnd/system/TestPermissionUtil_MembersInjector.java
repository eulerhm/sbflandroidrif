package com.google.android.gnd.system;

import android.app.Application;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
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
public final class TestPermissionUtil_MembersInjector implements MembersInjector<TestPermissionUtil> {
  private final Provider<Application> applicationProvider;

  private final Provider<ActivityStreams> activityStreamsProvider;

  public TestPermissionUtil_MembersInjector(Provider<Application> applicationProvider,
      Provider<ActivityStreams> activityStreamsProvider) {
    this.applicationProvider = applicationProvider;
    this.activityStreamsProvider = activityStreamsProvider;
  }

  public static MembersInjector<TestPermissionUtil> create(
      Provider<Application> applicationProvider,
      Provider<ActivityStreams> activityStreamsProvider) {
    return new TestPermissionUtil_MembersInjector(applicationProvider, activityStreamsProvider);
  }

  @Override
  public void injectMembers(TestPermissionUtil instance) {
    injectApplication(instance, applicationProvider.get());
    injectActivityStreams(instance, activityStreamsProvider.get());
  }

  @InjectedFieldSignature("com.google.android.gnd.system.TestPermissionUtil.application")
  public static void injectApplication(TestPermissionUtil instance, Application application) {
    instance.application = application;
  }

  @InjectedFieldSignature("com.google.android.gnd.system.TestPermissionUtil.activityStreams")
  public static void injectActivityStreams(TestPermissionUtil instance,
      ActivityStreams activityStreams) {
    instance.activityStreams = activityStreams;
  }
}
