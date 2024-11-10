package org.wordpress.android.ui.mysite.cards.quicklinksribbon;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository;

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
public final class QuickLinkRibbonBuilder_Factory implements Factory<QuickLinkRibbonBuilder> {
  private final Provider<QuickStartRepository> quickStartRepositoryProvider;

  public QuickLinkRibbonBuilder_Factory(
      Provider<QuickStartRepository> quickStartRepositoryProvider) {
    this.quickStartRepositoryProvider = quickStartRepositoryProvider;
  }

  @Override
  public QuickLinkRibbonBuilder get() {
    return newInstance(quickStartRepositoryProvider.get());
  }

  public static QuickLinkRibbonBuilder_Factory create(
      Provider<QuickStartRepository> quickStartRepositoryProvider) {
    return new QuickLinkRibbonBuilder_Factory(quickStartRepositoryProvider);
  }

  public static QuickLinkRibbonBuilder newInstance(QuickStartRepository quickStartRepository) {
    return new QuickLinkRibbonBuilder(quickStartRepository);
  }
}
