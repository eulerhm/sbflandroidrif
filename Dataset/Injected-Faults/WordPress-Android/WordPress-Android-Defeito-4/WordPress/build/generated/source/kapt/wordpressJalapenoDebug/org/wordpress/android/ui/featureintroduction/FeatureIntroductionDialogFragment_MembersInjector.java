package org.wordpress.android.ui.featureintroduction;

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
public final class FeatureIntroductionDialogFragment_MembersInjector implements MembersInjector<FeatureIntroductionDialogFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  public FeatureIntroductionDialogFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.uiHelpersProvider = uiHelpersProvider;
  }

  public static MembersInjector<FeatureIntroductionDialogFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider) {
    return new FeatureIntroductionDialogFragment_MembersInjector(viewModelFactoryProvider, uiHelpersProvider);
  }

  @Override
  public void injectMembers(FeatureIntroductionDialogFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.featureintroduction.FeatureIntroductionDialogFragment.viewModelFactory")
  public static void injectViewModelFactory(FeatureIntroductionDialogFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.featureintroduction.FeatureIntroductionDialogFragment.uiHelpers")
  public static void injectUiHelpers(FeatureIntroductionDialogFragment instance,
      UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }
}
