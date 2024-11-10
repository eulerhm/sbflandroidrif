package org.wordpress.android.ui.jetpack.scan.builders;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.DateFormatWrapper;

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
public final class ThreatItemBuilder_Factory implements Factory<ThreatItemBuilder> {
  private final Provider<DateFormatWrapper> dateFormatWrapperProvider;

  public ThreatItemBuilder_Factory(Provider<DateFormatWrapper> dateFormatWrapperProvider) {
    this.dateFormatWrapperProvider = dateFormatWrapperProvider;
  }

  @Override
  public ThreatItemBuilder get() {
    return newInstance(dateFormatWrapperProvider.get());
  }

  public static ThreatItemBuilder_Factory create(
      Provider<DateFormatWrapper> dateFormatWrapperProvider) {
    return new ThreatItemBuilder_Factory(dateFormatWrapperProvider);
  }

  public static ThreatItemBuilder newInstance(DateFormatWrapper dateFormatWrapper) {
    return new ThreatItemBuilder(dateFormatWrapper);
  }
}
