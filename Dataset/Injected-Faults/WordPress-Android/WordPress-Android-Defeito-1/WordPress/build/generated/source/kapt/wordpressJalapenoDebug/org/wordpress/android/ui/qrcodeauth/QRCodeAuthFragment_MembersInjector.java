package org.wordpress.android.ui.qrcodeauth;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.utils.UiHelpers;

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
public final class QRCodeAuthFragment_MembersInjector implements MembersInjector<QRCodeAuthFragment> {
  private final Provider<UiHelpers> uiHelpersProvider;

  public QRCodeAuthFragment_MembersInjector(Provider<UiHelpers> uiHelpersProvider) {
    this.uiHelpersProvider = uiHelpersProvider;
  }

  public static MembersInjector<QRCodeAuthFragment> create(Provider<UiHelpers> uiHelpersProvider) {
    return new QRCodeAuthFragment_MembersInjector(uiHelpersProvider);
  }

  @Override
  public void injectMembers(QRCodeAuthFragment instance) {
    injectUiHelpers(instance, uiHelpersProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.qrcodeauth.QRCodeAuthFragment.uiHelpers")
  public static void injectUiHelpers(QRCodeAuthFragment instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }
}
