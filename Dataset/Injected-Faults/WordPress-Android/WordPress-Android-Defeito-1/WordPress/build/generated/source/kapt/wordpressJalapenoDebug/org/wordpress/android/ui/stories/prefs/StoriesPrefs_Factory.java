package org.wordpress.android.ui.stories.prefs;

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
public final class StoriesPrefs_Factory implements Factory<StoriesPrefs> {
  private final Provider<Context> contextProvider;

  public StoriesPrefs_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public StoriesPrefs get() {
    return newInstance(contextProvider.get());
  }

  public static StoriesPrefs_Factory create(Provider<Context> contextProvider) {
    return new StoriesPrefs_Factory(contextProvider);
  }

  public static StoriesPrefs newInstance(Context context) {
    return new StoriesPrefs(context);
  }
}
