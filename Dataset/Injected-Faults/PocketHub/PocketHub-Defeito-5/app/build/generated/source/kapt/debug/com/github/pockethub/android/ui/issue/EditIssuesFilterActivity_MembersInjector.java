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
public final class EditIssuesFilterActivity_MembersInjector implements MembersInjector<EditIssuesFilterActivity> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<AvatarLoader> avatarsProvider;

  public EditIssuesFilterActivity_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<AvatarLoader> avatarsProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.avatarsProvider = avatarsProvider;
  }

  public static MembersInjector<EditIssuesFilterActivity> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<AvatarLoader> avatarsProvider) {
    return new EditIssuesFilterActivity_MembersInjector(androidInjectorProvider, avatarsProvider);}

  @Override
  public void injectMembers(EditIssuesFilterActivity instance) {
    DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectAvatars(instance, avatarsProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.issue.EditIssuesFilterActivity.avatars")
  public static void injectAvatars(EditIssuesFilterActivity instance, AvatarLoader avatars) {
    instance.avatars = avatars;
  }
}
