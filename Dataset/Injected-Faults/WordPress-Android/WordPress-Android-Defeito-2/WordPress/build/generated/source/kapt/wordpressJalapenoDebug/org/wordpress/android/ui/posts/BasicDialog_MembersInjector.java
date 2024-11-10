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
public final class BasicDialog_MembersInjector implements MembersInjector<BasicDialog> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public BasicDialog_MembersInjector(Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<BasicDialog> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new BasicDialog_MembersInjector(viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(BasicDialog instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.BasicDialog.viewModelFactory")
  public static void injectViewModelFactory(BasicDialog instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
