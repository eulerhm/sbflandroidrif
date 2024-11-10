package org.wordpress.android.ui.whatsnew;

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
public final class FeatureAnnouncementDialogFragment_MembersInjector implements MembersInjector<FeatureAnnouncementDialogFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public FeatureAnnouncementDialogFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<FeatureAnnouncementDialogFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new FeatureAnnouncementDialogFragment_MembersInjector(viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(FeatureAnnouncementDialogFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.whatsnew.FeatureAnnouncementDialogFragment.viewModelFactory")
  public static void injectViewModelFactory(FeatureAnnouncementDialogFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
