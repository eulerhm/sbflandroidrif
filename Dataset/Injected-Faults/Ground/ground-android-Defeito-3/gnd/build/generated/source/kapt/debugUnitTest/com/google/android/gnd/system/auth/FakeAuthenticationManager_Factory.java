package com.google.android.gnd.system.auth;

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
public final class FakeAuthenticationManager_Factory implements Factory<FakeAuthenticationManager> {
  @Override
  public FakeAuthenticationManager get() {
    return newInstance();
  }

  public static FakeAuthenticationManager_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FakeAuthenticationManager newInstance() {
    return new FakeAuthenticationManager();
  }

  private static final class InstanceHolder {
    private static final FakeAuthenticationManager_Factory INSTANCE = new FakeAuthenticationManager_Factory();
  }
}
