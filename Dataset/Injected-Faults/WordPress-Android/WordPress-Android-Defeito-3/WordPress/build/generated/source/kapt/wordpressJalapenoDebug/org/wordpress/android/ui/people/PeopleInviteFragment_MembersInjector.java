package org.wordpress.android.ui.people;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.viewmodel.ContextProvider;

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
public final class PeopleInviteFragment_MembersInjector implements MembersInjector<PeopleInviteFragment> {
  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<ViewModelProvider.Factory> mViewModelFactoryProvider;

  private final Provider<UiHelpers> mUiHelpersProvider;

  private final Provider<ContextProvider> mContextProvider;

  public PeopleInviteFragment_MembersInjector(Provider<SiteStore> mSiteStoreProvider,
      Provider<ViewModelProvider.Factory> mViewModelFactoryProvider,
      Provider<UiHelpers> mUiHelpersProvider, Provider<ContextProvider> mContextProvider) {
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mViewModelFactoryProvider = mViewModelFactoryProvider;
    this.mUiHelpersProvider = mUiHelpersProvider;
    this.mContextProvider = mContextProvider;
  }

  public static MembersInjector<PeopleInviteFragment> create(Provider<SiteStore> mSiteStoreProvider,
      Provider<ViewModelProvider.Factory> mViewModelFactoryProvider,
      Provider<UiHelpers> mUiHelpersProvider, Provider<ContextProvider> mContextProvider) {
    return new PeopleInviteFragment_MembersInjector(mSiteStoreProvider, mViewModelFactoryProvider, mUiHelpersProvider, mContextProvider);
  }

  @Override
  public void injectMembers(PeopleInviteFragment instance) {
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMViewModelFactory(instance, mViewModelFactoryProvider.get());
    injectMUiHelpers(instance, mUiHelpersProvider.get());
    injectMContextProvider(instance, mContextProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.people.PeopleInviteFragment.mSiteStore")
  public static void injectMSiteStore(PeopleInviteFragment instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.people.PeopleInviteFragment.mViewModelFactory")
  public static void injectMViewModelFactory(PeopleInviteFragment instance,
      ViewModelProvider.Factory mViewModelFactory) {
    instance.mViewModelFactory = mViewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.people.PeopleInviteFragment.mUiHelpers")
  public static void injectMUiHelpers(PeopleInviteFragment instance, UiHelpers mUiHelpers) {
    instance.mUiHelpers = mUiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.people.PeopleInviteFragment.mContextProvider")
  public static void injectMContextProvider(PeopleInviteFragment instance,
      ContextProvider mContextProvider) {
    instance.mContextProvider = mContextProvider;
  }
}
