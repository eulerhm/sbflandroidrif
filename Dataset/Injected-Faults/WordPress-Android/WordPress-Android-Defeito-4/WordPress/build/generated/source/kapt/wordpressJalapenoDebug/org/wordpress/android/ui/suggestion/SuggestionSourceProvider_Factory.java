package org.wordpress.android.ui.suggestion;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;

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
public final class SuggestionSourceProvider_Factory implements Factory<SuggestionSourceProvider> {
  private final Provider<SuggestionSourceSubcomponent.Factory> suggestionSourceSubcomponentFactoryProvider;

  public SuggestionSourceProvider_Factory(
      Provider<SuggestionSourceSubcomponent.Factory> suggestionSourceSubcomponentFactoryProvider) {
    this.suggestionSourceSubcomponentFactoryProvider = suggestionSourceSubcomponentFactoryProvider;
  }

  @Override
  public SuggestionSourceProvider get() {
    return newInstance(suggestionSourceSubcomponentFactoryProvider.get());
  }

  public static SuggestionSourceProvider_Factory create(
      Provider<SuggestionSourceSubcomponent.Factory> suggestionSourceSubcomponentFactoryProvider) {
    return new SuggestionSourceProvider_Factory(suggestionSourceSubcomponentFactoryProvider);
  }

  public static SuggestionSourceProvider newInstance(
      SuggestionSourceSubcomponent.Factory suggestionSourceSubcomponentFactory) {
    return new SuggestionSourceProvider(suggestionSourceSubcomponentFactory);
  }
}
