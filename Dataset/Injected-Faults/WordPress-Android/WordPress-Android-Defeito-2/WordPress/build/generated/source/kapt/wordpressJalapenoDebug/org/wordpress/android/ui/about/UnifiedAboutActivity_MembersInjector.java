package org.wordpress.android.ui.about;

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
public final class UnifiedAboutActivity_MembersInjector implements MembersInjector<UnifiedAboutActivity> {
  private final Provider<UnifiedAboutViewModel> viewModelProvider;

  public UnifiedAboutActivity_MembersInjector(Provider<UnifiedAboutViewModel> viewModelProvider) {
    this.viewModelProvider = viewModelProvider;
  }

  public static MembersInjector<UnifiedAboutActivity> create(
      Provider<UnifiedAboutViewModel> viewModelProvider) {
    return new UnifiedAboutActivity_MembersInjector(viewModelProvider);
  }

  @Override
  public void injectMembers(UnifiedAboutActivity instance) {
    injectViewModel(instance, viewModelProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.about.UnifiedAboutActivity.viewModel")
  public static void injectViewModel(UnifiedAboutActivity instance,
      UnifiedAboutViewModel viewModel) {
    instance.viewModel = viewModel;
  }
}
