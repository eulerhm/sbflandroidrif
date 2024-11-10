package org.wordpress.android.ui.reader.utils;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.reader.services.update.TagUpdateClientUtilsProvider;

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
public final class ReaderUtilsWrapper_Factory implements Factory<ReaderUtilsWrapper> {
  private final Provider<Context> appContextProvider;

  private final Provider<TagUpdateClientUtilsProvider> tagUpdateClientUtilsProvider;

  public ReaderUtilsWrapper_Factory(Provider<Context> appContextProvider,
      Provider<TagUpdateClientUtilsProvider> tagUpdateClientUtilsProvider) {
    this.appContextProvider = appContextProvider;
    this.tagUpdateClientUtilsProvider = tagUpdateClientUtilsProvider;
  }

  @Override
  public ReaderUtilsWrapper get() {
    return newInstance(appContextProvider.get(), tagUpdateClientUtilsProvider.get());
  }

  public static ReaderUtilsWrapper_Factory create(Provider<Context> appContextProvider,
      Provider<TagUpdateClientUtilsProvider> tagUpdateClientUtilsProvider) {
    return new ReaderUtilsWrapper_Factory(appContextProvider, tagUpdateClientUtilsProvider);
  }

  public static ReaderUtilsWrapper newInstance(Context appContext,
      TagUpdateClientUtilsProvider tagUpdateClientUtilsProvider) {
    return new ReaderUtilsWrapper(appContext, tagUpdateClientUtilsProvider);
  }
}
