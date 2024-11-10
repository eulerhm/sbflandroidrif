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
public final class RoleUtilsWrapper_Factory implements Factory<RoleUtilsWrapper> {
  @Override
  public RoleUtilsWrapper get() {
    return newInstance();
  }

  public static RoleUtilsWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static RoleUtilsWrapper newInstance() {
    return new RoleUtilsWrapper();
  }

  private static final class InstanceHolder {
    private static final RoleUtilsWrapper_Factory INSTANCE = new RoleUtilsWrapper_Factory();
  }
}
