package org.wordpress.android.ui.reader.utils;

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
public final class ReaderImageScannerProvider_Factory implements Factory<ReaderImageScannerProvider> {
  @Override
  public ReaderImageScannerProvider get() {
    return newInstance();
  }

  public static ReaderImageScannerProvider_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ReaderImageScannerProvider newInstance() {
    return new ReaderImageScannerProvider();
  }

  private static final class InstanceHolder {
    private static final ReaderImageScannerProvider_Factory INSTANCE = new ReaderImageScannerProvider_Factory();
  }
}
