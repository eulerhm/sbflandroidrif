package com.github.pockethub.android.ui.commit;

import com.github.pockethub.android.util.AvatarLoader;
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
public final class CommitCompareListFragment_MembersInjector implements MembersInjector<CommitCompareListFragment> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<AvatarLoader> avatarsProvider;

  public CommitCompareListFragment_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<AvatarLoader> avatarsProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.avatarsProvider = avatarsProvider;
  }

  public static MembersInjector<CommitCompareListFragment> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<AvatarLoader> avatarsProvider) {
    return new CommitCompareListFragment_MembersInjector(androidInjectorProvider, avatarsProvider);}

  @Override
  public void injectMembers(CommitCompareListFragment instance) {
    DaggerFragment_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectAvatars(instance, avatarsProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.commit.CommitCompareListFragment.avatars")
  public static void injectAvatars(CommitCompareListFragment instance, AvatarLoader avatars) {
    instance.avatars = avatars;
  }
}
