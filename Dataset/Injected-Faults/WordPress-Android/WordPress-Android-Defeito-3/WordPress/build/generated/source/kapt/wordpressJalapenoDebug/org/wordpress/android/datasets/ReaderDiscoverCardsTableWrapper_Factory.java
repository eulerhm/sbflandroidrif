package org.wordpress.android.datasets;

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
public final class ReaderDiscoverCardsTableWrapper_Factory implements Factory<ReaderDiscoverCardsTableWrapper> {
  @Override
  public ReaderDiscoverCardsTableWrapper get() {
    return newInstance();
  }

  public static ReaderDiscoverCardsTableWrapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ReaderDiscoverCardsTableWrapper newInstance() {
    return new ReaderDiscoverCardsTableWrapper();
  }

  private static final class InstanceHolder {
    private static final ReaderDiscoverCardsTableWrapper_Factory INSTANCE = new ReaderDiscoverCardsTableWrapper_Factory();
  }
}
