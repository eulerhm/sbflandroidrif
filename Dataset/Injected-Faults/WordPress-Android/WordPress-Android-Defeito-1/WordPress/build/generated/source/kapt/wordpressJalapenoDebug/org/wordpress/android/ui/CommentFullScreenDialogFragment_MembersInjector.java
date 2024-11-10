package org.wordpress.android.ui;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.SiteStore;

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
public final class CommentFullScreenDialogFragment_MembersInjector implements MembersInjector<CommentFullScreenDialogFragment> {
  private final Provider<CommentFullScreenDialogViewModel> viewModelProvider;

  private final Provider<SiteStore> siteStoreProvider;

  public CommentFullScreenDialogFragment_MembersInjector(
      Provider<CommentFullScreenDialogViewModel> viewModelProvider,
      Provider<SiteStore> siteStoreProvider) {
    this.viewModelProvider = viewModelProvider;
    this.siteStoreProvider = siteStoreProvider;
  }

  public static MembersInjector<CommentFullScreenDialogFragment> create(
      Provider<CommentFullScreenDialogViewModel> viewModelProvider,
      Provider<SiteStore> siteStoreProvider) {
    return new CommentFullScreenDialogFragment_MembersInjector(viewModelProvider, siteStoreProvider);
  }

  @Override
  public void injectMembers(CommentFullScreenDialogFragment instance) {
    injectViewModel(instance, viewModelProvider.get());
    injectSiteStore(instance, siteStoreProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.CommentFullScreenDialogFragment.viewModel")
  public static void injectViewModel(CommentFullScreenDialogFragment instance,
      CommentFullScreenDialogViewModel viewModel) {
    instance.viewModel = viewModel;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.CommentFullScreenDialogFragment.siteStore")
  public static void injectSiteStore(CommentFullScreenDialogFragment instance,
      SiteStore siteStore) {
    instance.siteStore = siteStore;
  }
}
