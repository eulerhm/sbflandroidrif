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
public final class PostTimePickerDialogFragment_MembersInjector implements MembersInjector<PostTimePickerDialogFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public PostTimePickerDialogFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<PostTimePickerDialogFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new PostTimePickerDialogFragment_MembersInjector(viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(PostTimePickerDialogFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PostTimePickerDialogFragment.viewModelFactory")
  public static void injectViewModelFactory(PostTimePickerDialogFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
