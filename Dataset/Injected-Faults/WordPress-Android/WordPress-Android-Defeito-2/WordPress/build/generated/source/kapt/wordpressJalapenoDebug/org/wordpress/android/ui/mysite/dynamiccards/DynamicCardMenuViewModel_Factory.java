package org.wordpress.android.ui.mysite.dynamiccards;

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
public final class DynamicCardMenuViewModel_Factory implements Factory<DynamicCardMenuViewModel> {
  @Override
  public DynamicCardMenuViewModel get() {
    return newInstance();
  }

  public static DynamicCardMenuViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static DynamicCardMenuViewModel newInstance() {
    return new DynamicCardMenuViewModel();
  }

  private static final class InstanceHolder {
    private static final DynamicCardMenuViewModel_Factory INSTANCE = new DynamicCardMenuViewModel_Factory();
  }
}
