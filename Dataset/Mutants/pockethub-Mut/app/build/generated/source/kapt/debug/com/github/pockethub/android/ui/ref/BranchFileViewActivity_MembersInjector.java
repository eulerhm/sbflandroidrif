package com.github.pockethub.android.ui.ref;

import com.github.pockethub.android.util.HttpImageGetter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class BranchFileViewActivity_MembersInjector implements MembersInjector<BranchFileViewActivity> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<HttpImageGetter> imageGetterProvider;

  public BranchFileViewActivity_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<HttpImageGetter> imageGetterProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.imageGetterProvider = imageGetterProvider;
  }

  public static MembersInjector<BranchFileViewActivity> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<HttpImageGetter> imageGetterProvider) {
    return new BranchFileViewActivity_MembersInjector(androidInjectorProvider, imageGetterProvider);}

  @Override
  public void injectMembers(BranchFileViewActivity instance) {
    DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectImageGetter(instance, imageGetterProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.ref.BranchFileViewActivity.imageGetter")
  public static void injectImageGetter(BranchFileViewActivity instance,
      HttpImageGetter imageGetter) {
    instance.imageGetter = imageGetter;
  }
}
