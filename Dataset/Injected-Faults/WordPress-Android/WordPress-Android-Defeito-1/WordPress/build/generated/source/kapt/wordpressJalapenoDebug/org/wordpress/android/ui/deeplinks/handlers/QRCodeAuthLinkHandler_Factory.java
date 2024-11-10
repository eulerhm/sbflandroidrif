package org.wordpress.android.ui.deeplinks.handlers;

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
public final class QRCodeAuthLinkHandler_Factory implements Factory<QRCodeAuthLinkHandler> {
  @Override
  public QRCodeAuthLinkHandler get() {
    return newInstance();
  }

  public static QRCodeAuthLinkHandler_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static QRCodeAuthLinkHandler newInstance() {
    return new QRCodeAuthLinkHandler();
  }

  private static final class InstanceHolder {
    private static final QRCodeAuthLinkHandler_Factory INSTANCE = new QRCodeAuthLinkHandler_Factory();
  }
}
