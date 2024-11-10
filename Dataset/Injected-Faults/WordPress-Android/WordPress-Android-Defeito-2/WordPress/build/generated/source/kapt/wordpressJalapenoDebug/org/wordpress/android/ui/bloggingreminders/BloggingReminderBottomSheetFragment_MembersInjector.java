package org.wordpress.android.ui.bloggingreminders;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.utils.UiHelpers;

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
public final class BloggingReminderBottomSheetFragment_MembersInjector implements MembersInjector<BloggingReminderBottomSheetFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<BloggingRemindersAdapter> adapterProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  public BloggingReminderBottomSheetFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<BloggingRemindersAdapter> adapterProvider, Provider<UiHelpers> uiHelpersProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.adapterProvider = adapterProvider;
    this.uiHelpersProvider = uiHelpersProvider;
  }

  public static MembersInjector<BloggingReminderBottomSheetFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<BloggingRemindersAdapter> adapterProvider, Provider<UiHelpers> uiHelpersProvider) {
    return new BloggingReminderBottomSheetFragment_MembersInjector(viewModelFactoryProvider, adapterProvider, uiHelpersProvider);
  }

  @Override
  public void injectMembers(BloggingReminderBottomSheetFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectAdapter(instance, adapterProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.bloggingreminders.BloggingReminderBottomSheetFragment.viewModelFactory")
  public static void injectViewModelFactory(BloggingReminderBottomSheetFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.bloggingreminders.BloggingReminderBottomSheetFragment.adapter")
  public static void injectAdapter(BloggingReminderBottomSheetFragment instance,
      BloggingRemindersAdapter adapter) {
    instance.adapter = adapter;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.bloggingreminders.BloggingReminderBottomSheetFragment.uiHelpers")
  public static void injectUiHelpers(BloggingReminderBottomSheetFragment instance,
      UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }
}
