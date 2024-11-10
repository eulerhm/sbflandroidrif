package org.wordpress.android.ui.posts;

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
public final class PostNotificationScheduleTimeDialogFragment_MembersInjector implements MembersInjector<PostNotificationScheduleTimeDialogFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public PostNotificationScheduleTimeDialogFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<PostNotificationScheduleTimeDialogFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new PostNotificationScheduleTimeDialogFragment_MembersInjector(viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(PostNotificationScheduleTimeDialogFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PostNotificationScheduleTimeDialogFragment.viewModelFactory")
  public static void injectViewModelFactory(PostNotificationScheduleTimeDialogFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
