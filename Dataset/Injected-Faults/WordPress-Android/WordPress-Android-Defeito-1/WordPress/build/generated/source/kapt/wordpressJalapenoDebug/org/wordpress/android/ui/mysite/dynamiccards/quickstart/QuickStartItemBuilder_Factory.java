package org.wordpress.android.ui.mysite.dynamiccards.quickstart;

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
public final class QuickStartItemBuilder_Factory implements Factory<QuickStartItemBuilder> {
  private final Provider<QuickStartRepository> quickStartRepositoryProvider;

  public QuickStartItemBuilder_Factory(
      Provider<QuickStartRepository> quickStartRepositoryProvider) {
    this.quickStartRepositoryProvider = quickStartRepositoryProvider;
  }

  @Override
  public QuickStartItemBuilder get() {
    return newInstance(quickStartRepositoryProvider.get());
  }

  public static QuickStartItemBuilder_Factory create(
      Provider<QuickStartRepository> quickStartRepositoryProvider) {
    return new QuickStartItemBuilder_Factory(quickStartRepositoryProvider);
  }

  public static QuickStartItemBuilder newInstance(QuickStartRepository quickStartRepository) {
    return new QuickStartItemBuilder(quickStartRepository);
  }
}
