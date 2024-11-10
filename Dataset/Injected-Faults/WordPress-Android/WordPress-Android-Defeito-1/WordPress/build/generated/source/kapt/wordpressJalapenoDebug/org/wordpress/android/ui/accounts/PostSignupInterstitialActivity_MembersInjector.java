package org.wordpress.android.ui.accounts;

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
public final class PostSignupInterstitialActivity_MembersInjector implements MembersInjector<PostSignupInterstitialActivity> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public PostSignupInterstitialActivity_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<PostSignupInterstitialActivity> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new PostSignupInterstitialActivity_MembersInjector(viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(PostSignupInterstitialActivity instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.accounts.PostSignupInterstitialActivity.viewModelFactory")
  public static void injectViewModelFactory(PostSignupInterstitialActivity instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
