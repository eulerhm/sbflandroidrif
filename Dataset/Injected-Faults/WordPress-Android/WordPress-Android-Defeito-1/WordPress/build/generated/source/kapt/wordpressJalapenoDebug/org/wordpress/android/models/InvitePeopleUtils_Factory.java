package org.wordpress.android.models;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.models.wrappers.RoleUtilsWrapper;
import org.wordpress.android.models.wrappers.SimpleDateFormatWrapper;
import org.wordpress.android.util.DateTimeUtilsWrapper;
import org.wordpress.android.viewmodel.ContextProvider;

@ScopeMetadata("dagger.Reusable")
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
public final class InvitePeopleUtils_Factory implements Factory<InvitePeopleUtils> {
  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<ContextProvider> contextProvider;

  private final Provider<DateTimeUtilsWrapper> dateTimeUtilsWrapperProvider;

  private final Provider<RoleUtilsWrapper> roleUtilsWrapperProvider;

  private final Provider<SimpleDateFormatWrapper> simpleDateFormatWrapperProvider;

  public InvitePeopleUtils_Factory(Provider<SiteStore> siteStoreProvider,
      Provider<ContextProvider> contextProvider,
      Provider<DateTimeUtilsWrapper> dateTimeUtilsWrapperProvider,
      Provider<RoleUtilsWrapper> roleUtilsWrapperProvider,
      Provider<SimpleDateFormatWrapper> simpleDateFormatWrapperProvider) {
    this.siteStoreProvider = siteStoreProvider;
    this.contextProvider = contextProvider;
    this.dateTimeUtilsWrapperProvider = dateTimeUtilsWrapperProvider;
    this.roleUtilsWrapperProvider = roleUtilsWrapperProvider;
    this.simpleDateFormatWrapperProvider = simpleDateFormatWrapperProvider;
  }

  @Override
  public InvitePeopleUtils get() {
    return newInstance(siteStoreProvider.get(), contextProvider.get(), dateTimeUtilsWrapperProvider.get(), roleUtilsWrapperProvider.get(), simpleDateFormatWrapperProvider.get());
  }

  public static InvitePeopleUtils_Factory create(Provider<SiteStore> siteStoreProvider,
      Provider<ContextProvider> contextProvider,
      Provider<DateTimeUtilsWrapper> dateTimeUtilsWrapperProvider,
      Provider<RoleUtilsWrapper> roleUtilsWrapperProvider,
      Provider<SimpleDateFormatWrapper> simpleDateFormatWrapperProvider) {
    return new InvitePeopleUtils_Factory(siteStoreProvider, contextProvider, dateTimeUtilsWrapperProvider, roleUtilsWrapperProvider, simpleDateFormatWrapperProvider);
  }

  public static InvitePeopleUtils newInstance(SiteStore siteStore, ContextProvider contextProvider,
      DateTimeUtilsWrapper dateTimeUtilsWrapper, RoleUtilsWrapper roleUtilsWrapper,
      SimpleDateFormatWrapper simpleDateFormatWrapper) {
    return new InvitePeopleUtils(siteStore, contextProvider, dateTimeUtilsWrapper, roleUtilsWrapper, simpleDateFormatWrapper);
  }
}
