package org.wordpress.android.ui.reader;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.ui.reader.utils.DateProvider;
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
public final class ReaderCssProvider_Factory implements Factory<ReaderCssProvider> {
  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  private final Provider<DateProvider> dateProvider;

  public ReaderCssProvider_Factory(Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider, Provider<DateProvider> dateProvider) {
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
    this.dateProvider = dateProvider;
  }

  @Override
  public ReaderCssProvider get() {
    return newInstance(networkUtilsWrapperProvider.get(), appPrefsWrapperProvider.get(), dateProvider.get());
  }

  public static ReaderCssProvider_Factory create(
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider, Provider<DateProvider> dateProvider) {
    return new ReaderCssProvider_Factory(networkUtilsWrapperProvider, appPrefsWrapperProvider, dateProvider);
  }

  public static ReaderCssProvider newInstance(NetworkUtilsWrapper networkUtilsWrapper,
      AppPrefsWrapper appPrefsWrapper, DateProvider dateProvider) {
    return new ReaderCssProvider(networkUtilsWrapper, appPrefsWrapper, dateProvider);
  }
}
