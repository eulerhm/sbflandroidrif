package org.wordpress.android.ui.stats.refresh.utils;

import android.content.SharedPreferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;

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
public final class SelectedSectionManager_Factory implements Factory<SelectedSectionManager> {
  private final Provider<SharedPreferences> sharedPrefsProvider;

  public SelectedSectionManager_Factory(Provider<SharedPreferences> sharedPrefsProvider) {
    this.sharedPrefsProvider = sharedPrefsProvider;
  }

  @Override
  public SelectedSectionManager get() {
    return newInstance(sharedPrefsProvider.get());
  }

  public static SelectedSectionManager_Factory create(
      Provider<SharedPreferences> sharedPrefsProvider) {
    return new SelectedSectionManager_Factory(sharedPrefsProvider);
  }

  public static SelectedSectionManager newInstance(SharedPreferences sharedPrefs) {
    return new SelectedSectionManager(sharedPrefs);
  }
}
