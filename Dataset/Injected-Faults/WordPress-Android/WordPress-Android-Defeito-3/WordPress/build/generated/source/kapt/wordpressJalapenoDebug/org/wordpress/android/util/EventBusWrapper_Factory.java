package org.wordpress.android.util;

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
public final class EventBusWrapper_Factory implements Factory<EventBusWrapper> {
  @Override
  public EventBusWrapper get() {
    return newInstance();
  }

  public static EventBusWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static EventBusWrapper newInstance() {
    return new EventBusWrapper();
  }

  private static final class InstanceHolder {
    private static final EventBusWrapper_Factory INSTANCE = new EventBusWrapper_Factory();
  }
}
