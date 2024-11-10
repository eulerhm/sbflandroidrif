package org.wordpress.android.ui.qrcodeauth;

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
public final class QRCodeAuthValidator_Factory implements Factory<QRCodeAuthValidator> {
  @Override
  public QRCodeAuthValidator get() {
    return newInstance();
  }

  public static QRCodeAuthValidator_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static QRCodeAuthValidator newInstance() {
    return new QRCodeAuthValidator();
  }

  private static final class InstanceHolder {
    private static final QRCodeAuthValidator_Factory INSTANCE = new QRCodeAuthValidator_Factory();
  }
}
