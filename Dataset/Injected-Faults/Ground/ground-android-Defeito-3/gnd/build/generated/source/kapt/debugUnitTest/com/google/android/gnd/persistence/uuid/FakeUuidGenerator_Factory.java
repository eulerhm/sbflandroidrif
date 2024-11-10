package com.google.android.gnd.persistence.uuid;

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
public final class FakeUuidGenerator_Factory implements Factory<FakeUuidGenerator> {
  @Override
  public FakeUuidGenerator get() {
    return newInstance();
  }

  public static FakeUuidGenerator_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FakeUuidGenerator newInstance() {
    return new FakeUuidGenerator();
  }

  private static final class InstanceHolder {
    private static final FakeUuidGenerator_Factory INSTANCE = new FakeUuidGenerator_Factory();
  }
}
