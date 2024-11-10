package org.wordpress.android.modules;

import android.content.Context;
import com.automattic.android.tracks.crashlogging.CrashLogging;
import com.automattic.android.tracks.crashlogging.CrashLoggingDataProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class CrashLoggingModule_Companion_ProvideCrashLoggingFactory implements Factory<CrashLogging> {
  private final Provider<Context> contextProvider;

  private final Provider<CrashLoggingDataProvider> crashLoggingDataProvider;

  public CrashLoggingModule_Companion_ProvideCrashLoggingFactory(Provider<Context> contextProvider,
      Provider<CrashLoggingDataProvider> crashLoggingDataProvider) {
    this.contextProvider = contextProvider;
    this.crashLoggingDataProvider = crashLoggingDataProvider;
  }

  @Override
  public CrashLogging get() {
    return provideCrashLogging(contextProvider.get(), crashLoggingDataProvider.get());
  }

  public static CrashLoggingModule_Companion_ProvideCrashLoggingFactory create(
      Provider<Context> contextProvider,
      Provider<CrashLoggingDataProvider> crashLoggingDataProvider) {
    return new CrashLoggingModule_Companion_ProvideCrashLoggingFactory(contextProvider, crashLoggingDataProvider);
  }

  public static CrashLogging provideCrashLogging(Context context,
      CrashLoggingDataProvider crashLoggingDataProvider) {
    return Preconditions.checkNotNullFromProvides(CrashLoggingModule.Companion.provideCrashLogging(context, crashLoggingDataProvider));
  }
}
