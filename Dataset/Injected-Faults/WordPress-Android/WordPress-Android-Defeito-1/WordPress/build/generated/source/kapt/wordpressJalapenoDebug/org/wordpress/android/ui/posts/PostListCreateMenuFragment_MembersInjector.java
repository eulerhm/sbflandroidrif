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
public final class PostListCreateMenuFragment_MembersInjector implements MembersInjector<PostListCreateMenuFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public PostListCreateMenuFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<PostListCreateMenuFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new PostListCreateMenuFragment_MembersInjector(viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(PostListCreateMenuFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PostListCreateMenuFragment.viewModelFactory")
  public static void injectViewModelFactory(PostListCreateMenuFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
