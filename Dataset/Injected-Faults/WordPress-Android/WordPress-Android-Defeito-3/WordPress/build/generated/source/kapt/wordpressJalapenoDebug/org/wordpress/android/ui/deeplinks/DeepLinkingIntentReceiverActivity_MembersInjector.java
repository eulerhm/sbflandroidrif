package org.wordpress.android.ui.deeplinks;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;

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
public final class DeepLinkingIntentReceiverActivity_MembersInjector implements MembersInjector<DeepLinkingIntentReceiverActivity> {
  private final Provider<DeepLinkNavigator> mDeeplinkNavigatorProvider;

  private final Provider<DeepLinkUriUtils> mDeepLinkUriUtilsProvider;

  private final Provider<ViewModelProvider.Factory> mViewModelFactoryProvider;

  public DeepLinkingIntentReceiverActivity_MembersInjector(
      Provider<DeepLinkNavigator> mDeeplinkNavigatorProvider,
      Provider<DeepLinkUriUtils> mDeepLinkUriUtilsProvider,
      Provider<ViewModelProvider.Factory> mViewModelFactoryProvider) {
    this.mDeeplinkNavigatorProvider = mDeeplinkNavigatorProvider;
    this.mDeepLinkUriUtilsProvider = mDeepLinkUriUtilsProvider;
    this.mViewModelFactoryProvider = mViewModelFactoryProvider;
  }

  public static MembersInjector<DeepLinkingIntentReceiverActivity> create(
      Provider<DeepLinkNavigator> mDeeplinkNavigatorProvider,
      Provider<DeepLinkUriUtils> mDeepLinkUriUtilsProvider,
      Provider<ViewModelProvider.Factory> mViewModelFactoryProvider) {
    return new DeepLinkingIntentReceiverActivity_MembersInjector(mDeeplinkNavigatorProvider, mDeepLinkUriUtilsProvider, mViewModelFactoryProvider);
  }

  @Override
  public void injectMembers(DeepLinkingIntentReceiverActivity instance) {
    injectMDeeplinkNavigator(instance, mDeeplinkNavigatorProvider.get());
    injectMDeepLinkUriUtils(instance, mDeepLinkUriUtilsProvider.get());
    injectMViewModelFactory(instance, mViewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.deeplinks.DeepLinkingIntentReceiverActivity.mDeeplinkNavigator")
  public static void injectMDeeplinkNavigator(DeepLinkingIntentReceiverActivity instance,
      DeepLinkNavigator mDeeplinkNavigator) {
    instance.mDeeplinkNavigator = mDeeplinkNavigator;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.deeplinks.DeepLinkingIntentReceiverActivity.mDeepLinkUriUtils")
  public static void injectMDeepLinkUriUtils(DeepLinkingIntentReceiverActivity instance,
      DeepLinkUriUtils mDeepLinkUriUtils) {
    instance.mDeepLinkUriUtils = mDeepLinkUriUtils;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.deeplinks.DeepLinkingIntentReceiverActivity.mViewModelFactory")
  public static void injectMViewModelFactory(DeepLinkingIntentReceiverActivity instance,
      ViewModelProvider.Factory mViewModelFactory) {
    instance.mViewModelFactory = mViewModelFactory;
  }
}
