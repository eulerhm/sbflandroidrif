package org.wordpress.android.viewmodel.pages;

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
public final class AutoSaveConflictResolver_Factory implements Factory<AutoSaveConflictResolver> {
  @Override
  public AutoSaveConflictResolver get() {
    return newInstance();
  }

  public static AutoSaveConflictResolver_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AutoSaveConflictResolver newInstance() {
    return new AutoSaveConflictResolver();
  }

  private static final class InstanceHolder {
    private static final AutoSaveConflictResolver_Factory INSTANCE = new AutoSaveConflictResolver_Factory();
  }
}
