package com.github.pockethub.android.ui.issue;

import com.github.pockethub.android.util.AvatarLoader;
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
public final class EditIssueActivity_MembersInjector implements MembersInjector<EditIssueActivity> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<AvatarLoader> avatarsProvider;

  public EditIssueActivity_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<AvatarLoader> avatarsProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.avatarsProvider = avatarsProvider;
  }

  public static MembersInjector<EditIssueActivity> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<AvatarLoader> avatarsProvider) {
    return new EditIssueActivity_MembersInjector(androidInjectorProvider, avatarsProvider);}

  @Override
  public void injectMembers(EditIssueActivity instance) {
    DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectAvatars(instance, avatarsProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.issue.EditIssueActivity.avatars")
  public static void injectAvatars(EditIssueActivity instance, AvatarLoader avatars) {
    instance.avatars = avatars;
  }
}
