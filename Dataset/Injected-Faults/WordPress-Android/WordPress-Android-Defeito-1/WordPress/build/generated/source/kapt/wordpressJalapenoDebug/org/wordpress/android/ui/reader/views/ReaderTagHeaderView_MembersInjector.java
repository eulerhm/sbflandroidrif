package org.wordpress.android.ui.reader.views;

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
public final class ReaderTagHeaderView_MembersInjector implements MembersInjector<ReaderTagHeaderView> {
  private final Provider<UiHelpers> uiHelpersProvider;

  public ReaderTagHeaderView_MembersInjector(Provider<UiHelpers> uiHelpersProvider) {
    this.uiHelpersProvider = uiHelpersProvider;
  }

  public static MembersInjector<ReaderTagHeaderView> create(Provider<UiHelpers> uiHelpersProvider) {
    return new ReaderTagHeaderView_MembersInjector(uiHelpersProvider);
  }

  @Override
  public void injectMembers(ReaderTagHeaderView instance) {
    injectUiHelpers(instance, uiHelpersProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.views.ReaderTagHeaderView.uiHelpers")
  public static void injectUiHelpers(ReaderTagHeaderView instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }
}
