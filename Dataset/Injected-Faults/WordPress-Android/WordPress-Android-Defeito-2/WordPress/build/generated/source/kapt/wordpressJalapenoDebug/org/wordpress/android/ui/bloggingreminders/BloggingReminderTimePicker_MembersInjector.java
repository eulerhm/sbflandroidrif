package org.wordpress.android.ui.bloggingreminders;

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
public final class BloggingReminderTimePicker_MembersInjector implements MembersInjector<BloggingReminderTimePicker> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public BloggingReminderTimePicker_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<BloggingReminderTimePicker> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new BloggingReminderTimePicker_MembersInjector(viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(BloggingReminderTimePicker instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.bloggingreminders.BloggingReminderTimePicker.viewModelFactory")
  public static void injectViewModelFactory(BloggingReminderTimePicker instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
