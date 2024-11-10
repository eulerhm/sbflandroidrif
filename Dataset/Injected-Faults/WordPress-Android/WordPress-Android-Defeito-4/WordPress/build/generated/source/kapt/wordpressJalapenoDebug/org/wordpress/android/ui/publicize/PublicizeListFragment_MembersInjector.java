package org.wordpress.android.ui.publicize;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository;
import org.wordpress.android.util.QuickStartUtilsWrapper;
import org.wordpress.android.util.SnackbarSequencer;

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
public final class PublicizeListFragment_MembersInjector implements MembersInjector<PublicizeListFragment> {
  private final Provider<AccountStore> mAccountStoreProvider;

  private final Provider<QuickStartUtilsWrapper> mQuickStartUtilsWrapperProvider;

  private final Provider<QuickStartRepository> mQuickStartRepositoryProvider;

  private final Provider<SnackbarSequencer> mSnackbarSequencerProvider;

  public PublicizeListFragment_MembersInjector(Provider<AccountStore> mAccountStoreProvider,
      Provider<QuickStartUtilsWrapper> mQuickStartUtilsWrapperProvider,
      Provider<QuickStartRepository> mQuickStartRepositoryProvider,
      Provider<SnackbarSequencer> mSnackbarSequencerProvider) {
    this.mAccountStoreProvider = mAccountStoreProvider;
    this.mQuickStartUtilsWrapperProvider = mQuickStartUtilsWrapperProvider;
    this.mQuickStartRepositoryProvider = mQuickStartRepositoryProvider;
    this.mSnackbarSequencerProvider = mSnackbarSequencerProvider;
  }

  public static MembersInjector<PublicizeListFragment> create(
      Provider<AccountStore> mAccountStoreProvider,
      Provider<QuickStartUtilsWrapper> mQuickStartUtilsWrapperProvider,
      Provider<QuickStartRepository> mQuickStartRepositoryProvider,
      Provider<SnackbarSequencer> mSnackbarSequencerProvider) {
    return new PublicizeListFragment_MembersInjector(mAccountStoreProvider, mQuickStartUtilsWrapperProvider, mQuickStartRepositoryProvider, mSnackbarSequencerProvider);
  }

  @Override
  public void injectMembers(PublicizeListFragment instance) {
    injectMAccountStore(instance, mAccountStoreProvider.get());
    injectMQuickStartUtilsWrapper(instance, mQuickStartUtilsWrapperProvider.get());
    injectMQuickStartRepository(instance, mQuickStartRepositoryProvider.get());
    injectMSnackbarSequencer(instance, mSnackbarSequencerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.publicize.PublicizeListFragment.mAccountStore")
  public static void injectMAccountStore(PublicizeListFragment instance,
      AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.publicize.PublicizeListFragment.mQuickStartUtilsWrapper")
  public static void injectMQuickStartUtilsWrapper(PublicizeListFragment instance,
      QuickStartUtilsWrapper mQuickStartUtilsWrapper) {
    instance.mQuickStartUtilsWrapper = mQuickStartUtilsWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.publicize.PublicizeListFragment.mQuickStartRepository")
  public static void injectMQuickStartRepository(PublicizeListFragment instance,
      QuickStartRepository mQuickStartRepository) {
    instance.mQuickStartRepository = mQuickStartRepository;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.publicize.PublicizeListFragment.mSnackbarSequencer")
  public static void injectMSnackbarSequencer(PublicizeListFragment instance,
      SnackbarSequencer mSnackbarSequencer) {
    instance.mSnackbarSequencer = mSnackbarSequencer;
  }
}
