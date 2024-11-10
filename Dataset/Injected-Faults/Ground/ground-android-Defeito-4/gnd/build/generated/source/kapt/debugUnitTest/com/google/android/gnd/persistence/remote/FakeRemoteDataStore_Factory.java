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
public final class FakeRemoteDataStore_Factory implements Factory<FakeRemoteDataStore> {
  @Override
  public FakeRemoteDataStore get() {
    return newInstance();
  }

  public static FakeRemoteDataStore_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FakeRemoteDataStore newInstance() {
    return new FakeRemoteDataStore();
  }

  private static final class InstanceHolder {
    private static final FakeRemoteDataStore_Factory INSTANCE = new FakeRemoteDataStore_Factory();
  }
}
