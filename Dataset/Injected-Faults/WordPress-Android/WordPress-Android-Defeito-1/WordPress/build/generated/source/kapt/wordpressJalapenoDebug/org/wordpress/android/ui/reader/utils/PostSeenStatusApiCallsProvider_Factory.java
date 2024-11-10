package org.wordpress.android.ui.reader.utils;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.viewmodel.ContextProvider;

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
public final class PostSeenStatusApiCallsProvider_Factory implements Factory<PostSeenStatusApiCallsProvider> {
  private final Provider<ContextProvider> contextProvider;

  public PostSeenStatusApiCallsProvider_Factory(Provider<ContextProvider> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public PostSeenStatusApiCallsProvider get() {
    return newInstance(contextProvider.get());
  }

  public static PostSeenStatusApiCallsProvider_Factory create(
      Provider<ContextProvider> contextProvider) {
    return new PostSeenStatusApiCallsProvider_Factory(contextProvider);
  }

  public static PostSeenStatusApiCallsProvider newInstance(ContextProvider contextProvider) {
    return new PostSeenStatusApiCallsProvider(contextProvider);
  }
}
