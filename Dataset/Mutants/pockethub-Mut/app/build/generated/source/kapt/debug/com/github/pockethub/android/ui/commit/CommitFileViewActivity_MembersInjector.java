package com.github.pockethub.android.ui.commit;

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
public final class CommitFileViewActivity_MembersInjector implements MembersInjector<CommitFileViewActivity> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<HttpImageGetter> imageGetterProvider;

  public CommitFileViewActivity_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<HttpImageGetter> imageGetterProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.imageGetterProvider = imageGetterProvider;
  }

  public static MembersInjector<CommitFileViewActivity> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<HttpImageGetter> imageGetterProvider) {
    return new CommitFileViewActivity_MembersInjector(androidInjectorProvider, imageGetterProvider);}

  @Override
  public void injectMembers(CommitFileViewActivity instance) {
    DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectImageGetter(instance, imageGetterProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.commit.CommitFileViewActivity.imageGetter")
  public static void injectImageGetter(CommitFileViewActivity instance,
      HttpImageGetter imageGetter) {
    instance.imageGetter = imageGetter;
  }
}
