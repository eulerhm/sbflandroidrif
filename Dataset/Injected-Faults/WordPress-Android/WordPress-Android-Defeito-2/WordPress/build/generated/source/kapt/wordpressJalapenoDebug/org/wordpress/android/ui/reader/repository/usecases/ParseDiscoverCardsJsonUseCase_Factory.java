package org.wordpress.android.ui.reader.repository.usecases;

import com.automattic.android.tracks.crashlogging.CrashLogging;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;

@ScopeMetadata("dagger.Reusable")
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
public final class ParseDiscoverCardsJsonUseCase_Factory implements Factory<ParseDiscoverCardsJsonUseCase> {
  private final Provider<CrashLogging> crashLoggingProvider;

  public ParseDiscoverCardsJsonUseCase_Factory(Provider<CrashLogging> crashLoggingProvider) {
    this.crashLoggingProvider = crashLoggingProvider;
  }

  @Override
  public ParseDiscoverCardsJsonUseCase get() {
    return newInstance(crashLoggingProvider.get());
  }

  public static ParseDiscoverCardsJsonUseCase_Factory create(
      Provider<CrashLogging> crashLoggingProvider) {
    return new ParseDiscoverCardsJsonUseCase_Factory(crashLoggingProvider);
  }

  public static ParseDiscoverCardsJsonUseCase newInstance(CrashLogging crashLogging) {
    return new ParseDiscoverCardsJsonUseCase(crashLogging);
  }
}
