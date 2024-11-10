package org.wordpress.android.ui.reader.utils;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.viewmodel.ContextProvider;

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
public final class ThreadedCommentsUtils_Factory implements Factory<ThreadedCommentsUtils> {
  private final Provider<ContextProvider> contextProvider;

  public ThreadedCommentsUtils_Factory(Provider<ContextProvider> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ThreadedCommentsUtils get() {
    return newInstance(contextProvider.get());
  }

  public static ThreadedCommentsUtils_Factory create(Provider<ContextProvider> contextProvider) {
    return new ThreadedCommentsUtils_Factory(contextProvider);
  }

  public static ThreadedCommentsUtils newInstance(ContextProvider contextProvider) {
    return new ThreadedCommentsUtils(contextProvider);
  }
}
