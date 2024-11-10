package org.wordpress.android.ui.stats.refresh.lists;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.NetworkUtilsWrapper;

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
public final class UiModelMapper_Factory implements Factory<UiModelMapper> {
  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  public UiModelMapper_Factory(Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider) {
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
  }

  @Override
  public UiModelMapper get() {
    return newInstance(networkUtilsWrapperProvider.get());
  }

  public static UiModelMapper_Factory create(
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider) {
    return new UiModelMapper_Factory(networkUtilsWrapperProvider);
  }

  public static UiModelMapper newInstance(NetworkUtilsWrapper networkUtilsWrapper) {
    return new UiModelMapper(networkUtilsWrapper);
  }
}
