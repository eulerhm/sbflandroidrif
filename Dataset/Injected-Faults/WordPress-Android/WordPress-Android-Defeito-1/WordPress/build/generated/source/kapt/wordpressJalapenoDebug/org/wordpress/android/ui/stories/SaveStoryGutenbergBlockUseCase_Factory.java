package org.wordpress.android.ui.stories;

import com.automattic.android.tracks.crashlogging.CrashLogging;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.stories.prefs.StoriesPrefs;

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
public final class SaveStoryGutenbergBlockUseCase_Factory implements Factory<SaveStoryGutenbergBlockUseCase> {
  private final Provider<StoriesPrefs> storiesPrefsProvider;

  private final Provider<CrashLogging> crashLoggingProvider;

  public SaveStoryGutenbergBlockUseCase_Factory(Provider<StoriesPrefs> storiesPrefsProvider,
      Provider<CrashLogging> crashLoggingProvider) {
    this.storiesPrefsProvider = storiesPrefsProvider;
    this.crashLoggingProvider = crashLoggingProvider;
  }

  @Override
  public SaveStoryGutenbergBlockUseCase get() {
    return newInstance(storiesPrefsProvider.get(), crashLoggingProvider.get());
  }

  public static SaveStoryGutenbergBlockUseCase_Factory create(
      Provider<StoriesPrefs> storiesPrefsProvider, Provider<CrashLogging> crashLoggingProvider) {
    return new SaveStoryGutenbergBlockUseCase_Factory(storiesPrefsProvider, crashLoggingProvider);
  }

  public static SaveStoryGutenbergBlockUseCase newInstance(StoriesPrefs storiesPrefs,
      CrashLogging crashLogging) {
    return new SaveStoryGutenbergBlockUseCase(storiesPrefs, crashLogging);
  }
}
