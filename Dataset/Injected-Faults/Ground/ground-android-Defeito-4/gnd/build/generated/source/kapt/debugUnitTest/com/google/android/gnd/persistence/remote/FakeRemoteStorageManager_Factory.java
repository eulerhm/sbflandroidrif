package com.google.android.gnd.persistence.remote;

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
public final class FakeRemoteStorageManager_Factory implements Factory<FakeRemoteStorageManager> {
  @Override
  public FakeRemoteStorageManager get() {
    return newInstance();
  }

  public static FakeRemoteStorageManager_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FakeRemoteStorageManager newInstance() {
    return new FakeRemoteStorageManager();
  }

  private static final class InstanceHolder {
    private static final FakeRemoteStorageManager_Factory INSTANCE = new FakeRemoteStorageManager_Factory();
  }
}
