package org.wordpress.android.ui.mysite;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.mysite.cards.dashboard.CardsSource;
import org.wordpress.android.ui.mysite.cards.dashboard.bloggingprompts.BloggingPromptCardSource;
import org.wordpress.android.ui.mysite.cards.domainregistration.DomainRegistrationSource;
import org.wordpress.android.ui.mysite.cards.quickstart.QuickStartCardSource;
import org.wordpress.android.ui.mysite.dynamiccards.DynamicCardsSource;
import org.wordpress.android.ui.quickstart.QuickStartTracker;

@ScopeMetadata
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
public final class MySiteSourceManager_Factory implements Factory<MySiteSourceManager> {
  private final Provider<QuickStartTracker> quickStartTrackerProvider;

  private final Provider<CurrentAvatarSource> currentAvatarSourceProvider;

  private final Provider<DomainRegistrationSource> domainRegistrationSourceProvider;

  private final Provider<DynamicCardsSource> dynamicCardsSourceProvider;

  private final Provider<QuickStartCardSource> quickStartCardSourceProvider;

  private final Provider<ScanAndBackupSource> scanAndBackupSourceProvider;

  private final Provider<SelectedSiteSource> selectedSiteSourceProvider;

  private final Provider<CardsSource> cardsSourceProvider;

  private final Provider<SiteIconProgressSource> siteIconProgressSourceProvider;

  private final Provider<BloggingPromptCardSource> bloggingPromptCardSourceProvider;

  private final Provider<SelectedSiteRepository> selectedSiteRepositoryProvider;

  public MySiteSourceManager_Factory(Provider<QuickStartTracker> quickStartTrackerProvider,
      Provider<CurrentAvatarSource> currentAvatarSourceProvider,
      Provider<DomainRegistrationSource> domainRegistrationSourceProvider,
      Provider<DynamicCardsSource> dynamicCardsSourceProvider,
      Provider<QuickStartCardSource> quickStartCardSourceProvider,
      Provider<ScanAndBackupSource> scanAndBackupSourceProvider,
      Provider<SelectedSiteSource> selectedSiteSourceProvider,
      Provider<CardsSource> cardsSourceProvider,
      Provider<SiteIconProgressSource> siteIconProgressSourceProvider,
      Provider<BloggingPromptCardSource> bloggingPromptCardSourceProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider) {
    this.quickStartTrackerProvider = quickStartTrackerProvider;
    this.currentAvatarSourceProvider = currentAvatarSourceProvider;
    this.domainRegistrationSourceProvider = domainRegistrationSourceProvider;
    this.dynamicCardsSourceProvider = dynamicCardsSourceProvider;
    this.quickStartCardSourceProvider = quickStartCardSourceProvider;
    this.scanAndBackupSourceProvider = scanAndBackupSourceProvider;
    this.selectedSiteSourceProvider = selectedSiteSourceProvider;
    this.cardsSourceProvider = cardsSourceProvider;
    this.siteIconProgressSourceProvider = siteIconProgressSourceProvider;
    this.bloggingPromptCardSourceProvider = bloggingPromptCardSourceProvider;
    this.selectedSiteRepositoryProvider = selectedSiteRepositoryProvider;
  }

  @Override
  public MySiteSourceManager get() {
    return newInstance(quickStartTrackerProvider.get(), currentAvatarSourceProvider.get(), domainRegistrationSourceProvider.get(), dynamicCardsSourceProvider.get(), quickStartCardSourceProvider.get(), scanAndBackupSourceProvider.get(), selectedSiteSourceProvider.get(), cardsSourceProvider.get(), siteIconProgressSourceProvider.get(), bloggingPromptCardSourceProvider.get(), selectedSiteRepositoryProvider.get());
  }

  public static MySiteSourceManager_Factory create(
      Provider<QuickStartTracker> quickStartTrackerProvider,
      Provider<CurrentAvatarSource> currentAvatarSourceProvider,
      Provider<DomainRegistrationSource> domainRegistrationSourceProvider,
      Provider<DynamicCardsSource> dynamicCardsSourceProvider,
      Provider<QuickStartCardSource> quickStartCardSourceProvider,
      Provider<ScanAndBackupSource> scanAndBackupSourceProvider,
      Provider<SelectedSiteSource> selectedSiteSourceProvider,
      Provider<CardsSource> cardsSourceProvider,
      Provider<SiteIconProgressSource> siteIconProgressSourceProvider,
      Provider<BloggingPromptCardSource> bloggingPromptCardSourceProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider) {
    return new MySiteSourceManager_Factory(quickStartTrackerProvider, currentAvatarSourceProvider, domainRegistrationSourceProvider, dynamicCardsSourceProvider, quickStartCardSourceProvider, scanAndBackupSourceProvider, selectedSiteSourceProvider, cardsSourceProvider, siteIconProgressSourceProvider, bloggingPromptCardSourceProvider, selectedSiteRepositoryProvider);
  }

  public static MySiteSourceManager newInstance(QuickStartTracker quickStartTracker,
      CurrentAvatarSource currentAvatarSource, DomainRegistrationSource domainRegistrationSource,
      DynamicCardsSource dynamicCardsSource, QuickStartCardSource quickStartCardSource,
      ScanAndBackupSource scanAndBackupSource, SelectedSiteSource selectedSiteSource,
      CardsSource cardsSource, SiteIconProgressSource siteIconProgressSource,
      BloggingPromptCardSource bloggingPromptCardSource,
      SelectedSiteRepository selectedSiteRepository) {
    return new MySiteSourceManager(quickStartTracker, currentAvatarSource, domainRegistrationSource, dynamicCardsSource, quickStartCardSource, scanAndBackupSource, selectedSiteSource, cardsSource, siteIconProgressSource, bloggingPromptCardSource, selectedSiteRepository);
  }
}
