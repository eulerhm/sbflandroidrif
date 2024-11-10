package org.wordpress.android.ui.comments.unified;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.NetworkUtilsWrapper;
import org.wordpress.android.util.SnackbarSequencer;
import org.wordpress.android.util.config.UnifiedCommentsDetailFeatureConfig;

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
public final class UnifiedCommentListFragment_MembersInjector implements MembersInjector<UnifiedCommentListFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  private final Provider<SnackbarSequencer> snackbarSequencerProvider;

  private final Provider<SelectedSiteRepository> selectedSiteRepositoryProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<UnifiedCommentsDetailFeatureConfig> unifiedCommentsDetailFeatureConfigProvider;

  public UnifiedCommentListFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider, Provider<SnackbarSequencer> snackbarSequencerProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<UnifiedCommentsDetailFeatureConfig> unifiedCommentsDetailFeatureConfigProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.uiHelpersProvider = uiHelpersProvider;
    this.snackbarSequencerProvider = snackbarSequencerProvider;
    this.selectedSiteRepositoryProvider = selectedSiteRepositoryProvider;
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.unifiedCommentsDetailFeatureConfigProvider = unifiedCommentsDetailFeatureConfigProvider;
  }

  public static MembersInjector<UnifiedCommentListFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider, Provider<SnackbarSequencer> snackbarSequencerProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<UnifiedCommentsDetailFeatureConfig> unifiedCommentsDetailFeatureConfigProvider) {
    return new UnifiedCommentListFragment_MembersInjector(viewModelFactoryProvider, uiHelpersProvider, snackbarSequencerProvider, selectedSiteRepositoryProvider, networkUtilsWrapperProvider, unifiedCommentsDetailFeatureConfigProvider);
  }

  @Override
  public void injectMembers(UnifiedCommentListFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
    injectSnackbarSequencer(instance, snackbarSequencerProvider.get());
    injectSelectedSiteRepository(instance, selectedSiteRepositoryProvider.get());
    injectNetworkUtilsWrapper(instance, networkUtilsWrapperProvider.get());
    injectUnifiedCommentsDetailFeatureConfig(instance, unifiedCommentsDetailFeatureConfigProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.comments.unified.UnifiedCommentListFragment.viewModelFactory")
  public static void injectViewModelFactory(UnifiedCommentListFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.comments.unified.UnifiedCommentListFragment.uiHelpers")
  public static void injectUiHelpers(UnifiedCommentListFragment instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.comments.unified.UnifiedCommentListFragment.snackbarSequencer")
  public static void injectSnackbarSequencer(UnifiedCommentListFragment instance,
      SnackbarSequencer snackbarSequencer) {
    instance.snackbarSequencer = snackbarSequencer;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.comments.unified.UnifiedCommentListFragment.selectedSiteRepository")
  public static void injectSelectedSiteRepository(UnifiedCommentListFragment instance,
      SelectedSiteRepository selectedSiteRepository) {
    instance.selectedSiteRepository = selectedSiteRepository;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.comments.unified.UnifiedCommentListFragment.networkUtilsWrapper")
  public static void injectNetworkUtilsWrapper(UnifiedCommentListFragment instance,
      NetworkUtilsWrapper networkUtilsWrapper) {
    instance.networkUtilsWrapper = networkUtilsWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.comments.unified.UnifiedCommentListFragment.unifiedCommentsDetailFeatureConfig")
  public static void injectUnifiedCommentsDetailFeatureConfig(UnifiedCommentListFragment instance,
      UnifiedCommentsDetailFeatureConfig unifiedCommentsDetailFeatureConfig) {
    instance.unifiedCommentsDetailFeatureConfig = unifiedCommentsDetailFeatureConfig;
  }
}
