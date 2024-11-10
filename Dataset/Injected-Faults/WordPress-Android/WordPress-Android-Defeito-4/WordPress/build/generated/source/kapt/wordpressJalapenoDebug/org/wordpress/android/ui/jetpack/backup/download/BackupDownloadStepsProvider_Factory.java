package org.wordpress.android.ui.jetpack.backup.download;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class BackupDownloadStepsProvider_Factory implements Factory<BackupDownloadStepsProvider> {
  @Override
  public BackupDownloadStepsProvider get() {
    return newInstance();
  }

  public static BackupDownloadStepsProvider_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static BackupDownloadStepsProvider newInstance() {
    return new BackupDownloadStepsProvider();
  }

  private static final class InstanceHolder {
    private static final BackupDownloadStepsProvider_Factory INSTANCE = new BackupDownloadStepsProvider_Factory();
  }
}
