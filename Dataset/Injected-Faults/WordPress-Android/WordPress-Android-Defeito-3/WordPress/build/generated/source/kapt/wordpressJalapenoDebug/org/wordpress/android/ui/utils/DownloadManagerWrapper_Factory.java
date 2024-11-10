package org.wordpress.android.ui.utils;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;

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
public final class DownloadManagerWrapper_Factory implements Factory<DownloadManagerWrapper> {
  private final Provider<Context> contextProvider;

  public DownloadManagerWrapper_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public DownloadManagerWrapper get() {
    return newInstance(contextProvider.get());
  }

  public static DownloadManagerWrapper_Factory create(Provider<Context> contextProvider) {
    return new DownloadManagerWrapper_Factory(contextProvider);
  }

  public static DownloadManagerWrapper newInstance(Context context) {
    return new DownloadManagerWrapper(context);
  }
}
