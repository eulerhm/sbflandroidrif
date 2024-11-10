package org.wordpress.android.ui.reader;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.utils.AuthenticationUtils;
import org.wordpress.android.ui.utils.DownloadManagerWrapper;

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
public final class ReaderFileDownloadManager_Factory implements Factory<ReaderFileDownloadManager> {
  private final Provider<AuthenticationUtils> authenticationUtilsProvider;

  private final Provider<DownloadManagerWrapper> downloadManagerProvider;

  public ReaderFileDownloadManager_Factory(
      Provider<AuthenticationUtils> authenticationUtilsProvider,
      Provider<DownloadManagerWrapper> downloadManagerProvider) {
    this.authenticationUtilsProvider = authenticationUtilsProvider;
    this.downloadManagerProvider = downloadManagerProvider;
  }

  @Override
  public ReaderFileDownloadManager get() {
    return newInstance(authenticationUtilsProvider.get(), downloadManagerProvider.get());
  }

  public static ReaderFileDownloadManager_Factory create(
      Provider<AuthenticationUtils> authenticationUtilsProvider,
      Provider<DownloadManagerWrapper> downloadManagerProvider) {
    return new ReaderFileDownloadManager_Factory(authenticationUtilsProvider, downloadManagerProvider);
  }

  public static ReaderFileDownloadManager newInstance(AuthenticationUtils authenticationUtils,
      DownloadManagerWrapper downloadManager) {
    return new ReaderFileDownloadManager(authenticationUtils, downloadManager);
  }
}
