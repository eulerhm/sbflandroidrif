package org.wordpress.android.viewmodel.main;

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
public final class SitePickerViewModel_Factory implements Factory<SitePickerViewModel> {
  @Override
  public SitePickerViewModel get() {
    return newInstance();
  }

  public static SitePickerViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SitePickerViewModel newInstance() {
    return new SitePickerViewModel();
  }

  private static final class InstanceHolder {
    private static final SitePickerViewModel_Factory INSTANCE = new SitePickerViewModel_Factory();
  }
}
