package org.wordpress.android.modules;

import android.content.Context;
import androidx.lifecycle.LiveData;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.viewmodel.helpers.ConnectionStatus;

@ScopeMetadata
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
public final class ApplicationModule_ProvideConnectionStatusLiveDataFactory implements Factory<LiveData<ConnectionStatus>> {
  private final Provider<Context> contextProvider;

  public ApplicationModule_ProvideConnectionStatusLiveDataFactory(
      Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public LiveData<ConnectionStatus> get() {
    return provideConnectionStatusLiveData(contextProvider.get());
  }

  public static ApplicationModule_ProvideConnectionStatusLiveDataFactory create(
      Provider<Context> contextProvider) {
    return new ApplicationModule_ProvideConnectionStatusLiveDataFactory(contextProvider);
  }

  public static LiveData<ConnectionStatus> provideConnectionStatusLiveData(Context context) {
    return Preconditions.checkNotNullFromProvides(ApplicationModule.provideConnectionStatusLiveData(context));
  }
}
