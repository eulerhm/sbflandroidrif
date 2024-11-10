package org.wordpress.android.ui.qrcodeauth;

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
public final class QRCodeAuthUiStateMapper_Factory implements Factory<QRCodeAuthUiStateMapper> {
  @Override
  public QRCodeAuthUiStateMapper get() {
    return newInstance();
  }

  public static QRCodeAuthUiStateMapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static QRCodeAuthUiStateMapper newInstance() {
    return new QRCodeAuthUiStateMapper();
  }

  private static final class InstanceHolder {
    private static final QRCodeAuthUiStateMapper_Factory INSTANCE = new QRCodeAuthUiStateMapper_Factory();
  }
}
