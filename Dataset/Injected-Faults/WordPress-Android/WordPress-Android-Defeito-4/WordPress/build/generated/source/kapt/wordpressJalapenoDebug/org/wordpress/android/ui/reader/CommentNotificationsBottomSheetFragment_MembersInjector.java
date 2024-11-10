package org.wordpress.android.ui.reader;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.viewmodel.ContextProvider;

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
public final class CommentNotificationsBottomSheetFragment_MembersInjector implements MembersInjector<CommentNotificationsBottomSheetFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<ContextProvider> contextProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  public CommentNotificationsBottomSheetFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<ContextProvider> contextProvider, Provider<UiHelpers> uiHelpersProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.contextProvider = contextProvider;
    this.uiHelpersProvider = uiHelpersProvider;
  }

  public static MembersInjector<CommentNotificationsBottomSheetFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<ContextProvider> contextProvider, Provider<UiHelpers> uiHelpersProvider) {
    return new CommentNotificationsBottomSheetFragment_MembersInjector(viewModelFactoryProvider, contextProvider, uiHelpersProvider);
  }

  @Override
  public void injectMembers(CommentNotificationsBottomSheetFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectContextProvider(instance, contextProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.CommentNotificationsBottomSheetFragment.viewModelFactory")
  public static void injectViewModelFactory(CommentNotificationsBottomSheetFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.CommentNotificationsBottomSheetFragment.contextProvider")
  public static void injectContextProvider(CommentNotificationsBottomSheetFragment instance,
      ContextProvider contextProvider) {
    instance.contextProvider = contextProvider;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.CommentNotificationsBottomSheetFragment.uiHelpers")
  public static void injectUiHelpers(CommentNotificationsBottomSheetFragment instance,
      UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }
}
