package org.wordpress.android.ui.suggestion;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.util.EventBusWrapper;

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
public final class UserSuggestionSource_Factory implements Factory<UserSuggestionSource> {
  private final Provider<Context> contextProvider;

  private final Provider<SiteModel> siteProvider;

  private final Provider<EventBusWrapper> eventBusWrapperProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public UserSuggestionSource_Factory(Provider<Context> contextProvider,
      Provider<SiteModel> siteProvider, Provider<EventBusWrapper> eventBusWrapperProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.contextProvider = contextProvider;
    this.siteProvider = siteProvider;
    this.eventBusWrapperProvider = eventBusWrapperProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public UserSuggestionSource get() {
    return newInstance(contextProvider.get(), siteProvider.get(), eventBusWrapperProvider.get(), bgDispatcherProvider.get());
  }

  public static UserSuggestionSource_Factory create(Provider<Context> contextProvider,
      Provider<SiteModel> siteProvider, Provider<EventBusWrapper> eventBusWrapperProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new UserSuggestionSource_Factory(contextProvider, siteProvider, eventBusWrapperProvider, bgDispatcherProvider);
  }

  public static UserSuggestionSource newInstance(Context context, SiteModel site,
      EventBusWrapper eventBusWrapper, CoroutineDispatcher bgDispatcher) {
    return new UserSuggestionSource(context, site, eventBusWrapper, bgDispatcher);
  }
}
