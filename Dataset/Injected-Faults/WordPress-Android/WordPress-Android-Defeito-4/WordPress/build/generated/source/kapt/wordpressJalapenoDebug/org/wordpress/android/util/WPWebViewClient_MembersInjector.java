package org.wordpress.android.util;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.network.MemorizingTrustManager;

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
public final class WPWebViewClient_MembersInjector implements MembersInjector<WPWebViewClient> {
  private final Provider<MemorizingTrustManager> mMemorizingTrustManagerProvider;

  public WPWebViewClient_MembersInjector(
      Provider<MemorizingTrustManager> mMemorizingTrustManagerProvider) {
    this.mMemorizingTrustManagerProvider = mMemorizingTrustManagerProvider;
  }

  public static MembersInjector<WPWebViewClient> create(
      Provider<MemorizingTrustManager> mMemorizingTrustManagerProvider) {
    return new WPWebViewClient_MembersInjector(mMemorizingTrustManagerProvider);
  }

  @Override
  public void injectMembers(WPWebViewClient instance) {
    injectMMemorizingTrustManager(instance, mMemorizingTrustManagerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.util.WPWebViewClient.mMemorizingTrustManager")
  public static void injectMMemorizingTrustManager(WPWebViewClient instance,
      MemorizingTrustManager mMemorizingTrustManager) {
    instance.mMemorizingTrustManager = mMemorizingTrustManager;
  }
}
