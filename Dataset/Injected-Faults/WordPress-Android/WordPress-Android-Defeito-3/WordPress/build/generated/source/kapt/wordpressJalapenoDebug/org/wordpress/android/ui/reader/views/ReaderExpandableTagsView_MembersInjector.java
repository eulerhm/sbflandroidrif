package org.wordpress.android.ui.reader.views;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
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
public final class ReaderExpandableTagsView_MembersInjector implements MembersInjector<ReaderExpandableTagsView> {
  private final Provider<UiHelpers> uiHelpersProvider;

  private final Provider<ReaderTracker> readerTrackerProvider;

  public ReaderExpandableTagsView_MembersInjector(Provider<UiHelpers> uiHelpersProvider,
      Provider<ReaderTracker> readerTrackerProvider) {
    this.uiHelpersProvider = uiHelpersProvider;
    this.readerTrackerProvider = readerTrackerProvider;
  }

  public static MembersInjector<ReaderExpandableTagsView> create(
      Provider<UiHelpers> uiHelpersProvider, Provider<ReaderTracker> readerTrackerProvider) {
    return new ReaderExpandableTagsView_MembersInjector(uiHelpersProvider, readerTrackerProvider);
  }

  @Override
  public void injectMembers(ReaderExpandableTagsView instance) {
    injectUiHelpers(instance, uiHelpersProvider.get());
    injectReaderTracker(instance, readerTrackerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.views.ReaderExpandableTagsView.uiHelpers")
  public static void injectUiHelpers(ReaderExpandableTagsView instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.views.ReaderExpandableTagsView.readerTracker")
  public static void injectReaderTracker(ReaderExpandableTagsView instance,
      ReaderTracker readerTracker) {
    instance.readerTracker = readerTracker;
  }
}
