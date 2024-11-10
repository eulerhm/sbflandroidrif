package com.github.pockethub.android.ui.comment;

import com.github.pockethub.android.util.HttpImageGetter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerFragment_MembersInjector;
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
public final class RenderedCommentFragment_MembersInjector implements MembersInjector<RenderedCommentFragment> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<HttpImageGetter> imageGetterProvider;

  public RenderedCommentFragment_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<HttpImageGetter> imageGetterProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.imageGetterProvider = imageGetterProvider;
  }

  public static MembersInjector<RenderedCommentFragment> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<HttpImageGetter> imageGetterProvider) {
    return new RenderedCommentFragment_MembersInjector(androidInjectorProvider, imageGetterProvider);}

  @Override
  public void injectMembers(RenderedCommentFragment instance) {
    DaggerFragment_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectImageGetter(instance, imageGetterProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.comment.RenderedCommentFragment.imageGetter")
  public static void injectImageGetter(RenderedCommentFragment instance,
      HttpImageGetter imageGetter) {
    instance.imageGetter = imageGetter;
  }
}
