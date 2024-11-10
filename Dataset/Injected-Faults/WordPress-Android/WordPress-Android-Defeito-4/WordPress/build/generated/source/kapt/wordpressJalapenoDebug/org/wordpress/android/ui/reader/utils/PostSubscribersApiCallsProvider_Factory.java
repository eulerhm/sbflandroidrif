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
public final class PostSubscribersApiCallsProvider_Factory implements Factory<PostSubscribersApiCallsProvider> {
  private final Provider<ContextProvider> contextProvider;

  public PostSubscribersApiCallsProvider_Factory(Provider<ContextProvider> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public PostSubscribersApiCallsProvider get() {
    return newInstance(contextProvider.get());
  }

  public static PostSubscribersApiCallsProvider_Factory create(
      Provider<ContextProvider> contextProvider) {
    return new PostSubscribersApiCallsProvider_Factory(contextProvider);
  }

  public static PostSubscribersApiCallsProvider newInstance(ContextProvider contextProvider) {
    return new PostSubscribersApiCallsProvider(contextProvider);
  }
}
