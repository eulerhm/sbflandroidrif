package org.wordpress.android.widgets;

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
public final class AppRatingDialogWrapper_Factory implements Factory<AppRatingDialogWrapper> {
  @Override
  public AppRatingDialogWrapper get() {
    return newInstance();
  }

  public static AppRatingDialogWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AppRatingDialogWrapper newInstance() {
    return new AppRatingDialogWrapper();
  }

  private static final class InstanceHolder {
    private static final AppRatingDialogWrapper_Factory INSTANCE = new AppRatingDialogWrapper_Factory();
  }
}
