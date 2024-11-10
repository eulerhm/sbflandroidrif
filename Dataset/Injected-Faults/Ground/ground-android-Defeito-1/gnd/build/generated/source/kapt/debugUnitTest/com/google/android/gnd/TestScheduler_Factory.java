package com.google.android.gnd;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import javax.annotation.Generated;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class TestScheduler_Factory implements Factory<TestScheduler> {
  @Override
  public TestScheduler get() {
    return newInstance();
  }

  public static TestScheduler_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static TestScheduler newInstance() {
    return new TestScheduler();
  }

  private static final class InstanceHolder {
    private static final TestScheduler_Factory INSTANCE = new TestScheduler_Factory();
  }
}
