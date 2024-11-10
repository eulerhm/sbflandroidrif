package org.wordpress.android.util;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;

@ScopeMetadata
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
public final class ListFormatterUtils_Factory implements Factory<ListFormatterUtils> {
  @Override
  public ListFormatterUtils get() {
    return newInstance();
  }

  public static ListFormatterUtils_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ListFormatterUtils newInstance() {
    return new ListFormatterUtils();
  }

  private static final class InstanceHolder {
    private static final ListFormatterUtils_Factory INSTANCE = new ListFormatterUtils_Factory();
  }
}
