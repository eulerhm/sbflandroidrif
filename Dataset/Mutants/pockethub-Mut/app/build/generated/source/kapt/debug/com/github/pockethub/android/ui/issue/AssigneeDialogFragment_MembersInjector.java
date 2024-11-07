package com.github.pockethub.android.ui.issue;

import com.github.pockethub.android.util.AvatarLoader;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatDialogFragment_MembersInjector;
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
public final class AssigneeDialogFragment_MembersInjector implements MembersInjector<AssigneeDialogFragment> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<AvatarLoader> avatarsProvider;

  public AssigneeDialogFragment_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<AvatarLoader> avatarsProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.avatarsProvider = avatarsProvider;
  }

  public static MembersInjector<AssigneeDialogFragment> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<AvatarLoader> avatarsProvider) {
    return new AssigneeDialogFragment_MembersInjector(androidInjectorProvider, avatarsProvider);}

  @Override
  public void injectMembers(AssigneeDialogFragment instance) {
    DaggerAppCompatDialogFragment_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectAvatars(instance, avatarsProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.issue.AssigneeDialogFragment.avatars")
  public static void injectAvatars(AssigneeDialogFragment instance, AvatarLoader avatars) {
    instance.avatars = avatars;
  }
}
