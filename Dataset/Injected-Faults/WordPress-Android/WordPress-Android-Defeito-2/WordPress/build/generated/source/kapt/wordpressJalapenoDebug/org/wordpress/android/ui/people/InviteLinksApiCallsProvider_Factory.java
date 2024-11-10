package org.wordpress.android.ui.people;

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
public final class InviteLinksApiCallsProvider_Factory implements Factory<InviteLinksApiCallsProvider> {
  private final Provider<ContextProvider> contextProvider;

  public InviteLinksApiCallsProvider_Factory(Provider<ContextProvider> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public InviteLinksApiCallsProvider get() {
    return newInstance(contextProvider.get());
  }

  public static InviteLinksApiCallsProvider_Factory create(
      Provider<ContextProvider> contextProvider) {
    return new InviteLinksApiCallsProvider_Factory(contextProvider);
  }

  public static InviteLinksApiCallsProvider newInstance(ContextProvider contextProvider) {
    return new InviteLinksApiCallsProvider(contextProvider);
  }
}
