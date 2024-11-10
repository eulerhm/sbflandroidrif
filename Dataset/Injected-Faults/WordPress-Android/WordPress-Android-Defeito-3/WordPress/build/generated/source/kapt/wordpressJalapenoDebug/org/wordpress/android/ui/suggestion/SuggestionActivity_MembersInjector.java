package org.wordpress.android.ui.suggestion;

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
public final class SuggestionActivity_MembersInjector implements MembersInjector<SuggestionActivity> {
  private final Provider<SuggestionViewModel> viewModelProvider;

  public SuggestionActivity_MembersInjector(Provider<SuggestionViewModel> viewModelProvider) {
    this.viewModelProvider = viewModelProvider;
  }

  public static MembersInjector<SuggestionActivity> create(
      Provider<SuggestionViewModel> viewModelProvider) {
    return new SuggestionActivity_MembersInjector(viewModelProvider);
  }

  @Override
  public void injectMembers(SuggestionActivity instance) {
    injectViewModel(instance, viewModelProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.suggestion.SuggestionActivity.viewModel")
  public static void injectViewModel(SuggestionActivity instance, SuggestionViewModel viewModel) {
    instance.viewModel = viewModel;
  }
}
