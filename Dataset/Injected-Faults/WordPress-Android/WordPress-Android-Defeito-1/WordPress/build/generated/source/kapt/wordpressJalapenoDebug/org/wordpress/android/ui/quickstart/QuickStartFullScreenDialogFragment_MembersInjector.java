package org.wordpress.android.ui.quickstart;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.QuickStartStore;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.ui.mysite.cards.quickstart.QuickStartCardBuilder;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.DisplayUtilsWrapper;

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
public final class QuickStartFullScreenDialogFragment_MembersInjector implements MembersInjector<QuickStartFullScreenDialogFragment> {
  private final Provider<QuickStartTracker> quickStartTrackerProvider;

  private final Provider<QuickStartStore> quickStartStoreProvider;

  private final Provider<SelectedSiteRepository> selectedSiteRepositoryProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  private final Provider<QuickStartCardBuilder> quickStartCardBuilderProvider;

  private final Provider<DisplayUtilsWrapper> displayUtilsWrapperProvider;

  public QuickStartFullScreenDialogFragment_MembersInjector(
      Provider<QuickStartTracker> quickStartTrackerProvider,
      Provider<QuickStartStore> quickStartStoreProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<UiHelpers> uiHelpersProvider,
      Provider<QuickStartCardBuilder> quickStartCardBuilderProvider,
      Provider<DisplayUtilsWrapper> displayUtilsWrapperProvider) {
    this.quickStartTrackerProvider = quickStartTrackerProvider;
    this.quickStartStoreProvider = quickStartStoreProvider;
    this.selectedSiteRepositoryProvider = selectedSiteRepositoryProvider;
    this.uiHelpersProvider = uiHelpersProvider;
    this.quickStartCardBuilderProvider = quickStartCardBuilderProvider;
    this.displayUtilsWrapperProvider = displayUtilsWrapperProvider;
  }

  public static MembersInjector<QuickStartFullScreenDialogFragment> create(
      Provider<QuickStartTracker> quickStartTrackerProvider,
      Provider<QuickStartStore> quickStartStoreProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<UiHelpers> uiHelpersProvider,
      Provider<QuickStartCardBuilder> quickStartCardBuilderProvider,
      Provider<DisplayUtilsWrapper> displayUtilsWrapperProvider) {
    return new QuickStartFullScreenDialogFragment_MembersInjector(quickStartTrackerProvider, quickStartStoreProvider, selectedSiteRepositoryProvider, uiHelpersProvider, quickStartCardBuilderProvider, displayUtilsWrapperProvider);
  }

  @Override
  public void injectMembers(QuickStartFullScreenDialogFragment instance) {
    injectQuickStartTracker(instance, quickStartTrackerProvider.get());
    injectQuickStartStore(instance, quickStartStoreProvider.get());
    injectSelectedSiteRepository(instance, selectedSiteRepositoryProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
    injectQuickStartCardBuilder(instance, quickStartCardBuilderProvider.get());
    injectDisplayUtilsWrapper(instance, displayUtilsWrapperProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.quickstart.QuickStartFullScreenDialogFragment.quickStartTracker")
  public static void injectQuickStartTracker(QuickStartFullScreenDialogFragment instance,
      QuickStartTracker quickStartTracker) {
    instance.quickStartTracker = quickStartTracker;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.quickstart.QuickStartFullScreenDialogFragment.quickStartStore")
  public static void injectQuickStartStore(QuickStartFullScreenDialogFragment instance,
      QuickStartStore quickStartStore) {
    instance.quickStartStore = quickStartStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.quickstart.QuickStartFullScreenDialogFragment.selectedSiteRepository")
  public static void injectSelectedSiteRepository(QuickStartFullScreenDialogFragment instance,
      SelectedSiteRepository selectedSiteRepository) {
    instance.selectedSiteRepository = selectedSiteRepository;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.quickstart.QuickStartFullScreenDialogFragment.uiHelpers")
  public static void injectUiHelpers(QuickStartFullScreenDialogFragment instance,
      UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.quickstart.QuickStartFullScreenDialogFragment.quickStartCardBuilder")
  public static void injectQuickStartCardBuilder(QuickStartFullScreenDialogFragment instance,
      QuickStartCardBuilder quickStartCardBuilder) {
    instance.quickStartCardBuilder = quickStartCardBuilder;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.quickstart.QuickStartFullScreenDialogFragment.displayUtilsWrapper")
  public static void injectDisplayUtilsWrapper(QuickStartFullScreenDialogFragment instance,
      DisplayUtilsWrapper displayUtilsWrapper) {
    instance.displayUtilsWrapper = displayUtilsWrapper;
  }
}
