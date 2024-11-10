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
public final class HistoryListFragment_MembersInjector implements MembersInjector<HistoryListFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public HistoryListFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<HistoryListFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new HistoryListFragment_MembersInjector(viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(HistoryListFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.HistoryListFragment.viewModelFactory")
  public static void injectViewModelFactory(HistoryListFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
