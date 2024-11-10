package org.wordpress.android.ui.uploads;

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
public final class UploadServiceFacade_Factory implements Factory<UploadServiceFacade> {
  private final Provider<Context> appContextProvider;

  public UploadServiceFacade_Factory(Provider<Context> appContextProvider) {
    this.appContextProvider = appContextProvider;
  }

  @Override
  public UploadServiceFacade get() {
    return newInstance(appContextProvider.get());
  }

  public static UploadServiceFacade_Factory create(Provider<Context> appContextProvider) {
    return new UploadServiceFacade_Factory(appContextProvider);
  }

  public static UploadServiceFacade newInstance(Context appContext) {
    return new UploadServiceFacade(appContext);
  }
}
