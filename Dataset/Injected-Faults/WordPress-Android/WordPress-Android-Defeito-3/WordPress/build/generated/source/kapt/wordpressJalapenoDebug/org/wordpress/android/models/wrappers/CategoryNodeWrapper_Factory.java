package org.wordpress.android.models.wrappers;

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
public final class CategoryNodeWrapper_Factory implements Factory<CategoryNodeWrapper> {
  @Override
  public CategoryNodeWrapper get() {
    return newInstance();
  }

  public static CategoryNodeWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CategoryNodeWrapper newInstance() {
    return new CategoryNodeWrapper();
  }

  private static final class InstanceHolder {
    private static final CategoryNodeWrapper_Factory INSTANCE = new CategoryNodeWrapper_Factory();
  }
}
