package org.wordpress.android.viewmodel.helpers;

import android.content.Context;
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
public final class ConnectionStatusLiveData_Factory_Factory implements Factory<ConnectionStatusLiveData.Factory> {
  private final Provider<Context> contextProvider;

  public ConnectionStatusLiveData_Factory_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ConnectionStatusLiveData.Factory get() {
    return newInstance(contextProvider.get());
  }

  public static ConnectionStatusLiveData_Factory_Factory create(Provider<Context> contextProvider) {
    return new ConnectionStatusLiveData_Factory_Factory(contextProvider);
  }

  public static ConnectionStatusLiveData.Factory newInstance(Context context) {
    return new ConnectionStatusLiveData.Factory(context);
  }
}
