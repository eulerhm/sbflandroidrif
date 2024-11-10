package com.google.android.gnd;

import androidx.work.WorkManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class TestWorkManagerModule_ProvideWorkManagerFactory implements Factory<WorkManager> {
  @Override
  public WorkManager get() {
    return provideWorkManager();
  }

  public static TestWorkManagerModule_ProvideWorkManagerFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static WorkManager provideWorkManager() {
    return Preconditions.checkNotNullFromProvides(TestWorkManagerModule.provideWorkManager());
  }

  private static final class InstanceHolder {
    private static final TestWorkManagerModule_ProvideWorkManagerFactory INSTANCE = new TestWorkManagerModule_ProvideWorkManagerFactory();
  }
}
