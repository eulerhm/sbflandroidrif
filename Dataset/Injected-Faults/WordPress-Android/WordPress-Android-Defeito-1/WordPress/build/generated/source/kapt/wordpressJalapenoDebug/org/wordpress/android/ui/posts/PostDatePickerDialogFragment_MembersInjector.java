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
public final class PostDatePickerDialogFragment_MembersInjector implements MembersInjector<PostDatePickerDialogFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public PostDatePickerDialogFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<PostDatePickerDialogFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new PostDatePickerDialogFragment_MembersInjector(viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(PostDatePickerDialogFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PostDatePickerDialogFragment.viewModelFactory")
  public static void injectViewModelFactory(PostDatePickerDialogFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
