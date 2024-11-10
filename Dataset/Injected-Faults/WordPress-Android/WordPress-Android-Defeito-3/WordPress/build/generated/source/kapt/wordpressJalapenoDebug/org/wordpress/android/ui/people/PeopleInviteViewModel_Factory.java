package org.wordpress.android.ui.people;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.models.InvitePeopleUtils;
import org.wordpress.android.util.DateTimeUtilsWrapper;
import org.wordpress.android.util.analytics.AnalyticsUtilsWrapper;
import org.wordpress.android.viewmodel.ContextProvider;

@ScopeMetadata
@QualifierMetadata("javax.inject.Named")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class PeopleInviteViewModel_Factory implements Factory<PeopleInviteViewModel> {
  private final Provider<InviteLinksHandler> inviteLinksHandlerProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<DateTimeUtilsWrapper> dateTimeUtilsWrapperProvider;

  private final Provider<ContextProvider> contextProvider;

  private final Provider<InvitePeopleUtils> invitePeopleUtilsProvider;

  private final Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider;

  public PeopleInviteViewModel_Factory(Provider<InviteLinksHandler> inviteLinksHandlerProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<DateTimeUtilsWrapper> dateTimeUtilsWrapperProvider,
      Provider<ContextProvider> contextProvider,
      Provider<InvitePeopleUtils> invitePeopleUtilsProvider,
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider) {
    this.inviteLinksHandlerProvider = inviteLinksHandlerProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.dateTimeUtilsWrapperProvider = dateTimeUtilsWrapperProvider;
    this.contextProvider = contextProvider;
    this.invitePeopleUtilsProvider = invitePeopleUtilsProvider;
    this.analyticsUtilsWrapperProvider = analyticsUtilsWrapperProvider;
  }

  @Override
  public PeopleInviteViewModel get() {
    return newInstance(inviteLinksHandlerProvider.get(), bgDispatcherProvider.get(), dateTimeUtilsWrapperProvider.get(), contextProvider.get(), invitePeopleUtilsProvider.get(), analyticsUtilsWrapperProvider.get());
  }

  public static PeopleInviteViewModel_Factory create(
      Provider<InviteLinksHandler> inviteLinksHandlerProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<DateTimeUtilsWrapper> dateTimeUtilsWrapperProvider,
      Provider<ContextProvider> contextProvider,
      Provider<InvitePeopleUtils> invitePeopleUtilsProvider,
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider) {
    return new PeopleInviteViewModel_Factory(inviteLinksHandlerProvider, bgDispatcherProvider, dateTimeUtilsWrapperProvider, contextProvider, invitePeopleUtilsProvider, analyticsUtilsWrapperProvider);
  }

  public static PeopleInviteViewModel newInstance(InviteLinksHandler inviteLinksHandler,
      CoroutineDispatcher bgDispatcher, DateTimeUtilsWrapper dateTimeUtilsWrapper,
      ContextProvider contextProvider, InvitePeopleUtils invitePeopleUtils,
      AnalyticsUtilsWrapper analyticsUtilsWrapper) {
    return new PeopleInviteViewModel(inviteLinksHandler, bgDispatcher, dateTimeUtilsWrapper, contextProvider, invitePeopleUtils, analyticsUtilsWrapper);
  }
}
