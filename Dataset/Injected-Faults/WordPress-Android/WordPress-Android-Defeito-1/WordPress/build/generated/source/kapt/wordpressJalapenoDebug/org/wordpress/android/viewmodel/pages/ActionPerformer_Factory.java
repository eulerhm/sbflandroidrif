package org.wordpress.android.viewmodel.pages;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.Dispatcher;

@ScopeMetadata
@QualifierMetadata("javax.inject.Named")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ActionPerformer_Factory implements Factory<ActionPerformer> {
  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<CoroutineDispatcher> defaultDispatcherProvider;

  public ActionPerformer_Factory(Provider<Dispatcher> dispatcherProvider,
      Provider<CoroutineDispatcher> defaultDispatcherProvider) {
    this.dispatcherProvider = dispatcherProvider;
    this.defaultDispatcherProvider = defaultDispatcherProvider;
  }

  @Override
  public ActionPerformer get() {
    return newInstance(dispatcherProvider.get(), defaultDispatcherProvider.get());
  }

  public static ActionPerformer_Factory create(Provider<Dispatcher> dispatcherProvider,
      Provider<CoroutineDispatcher> defaultDispatcherProvider) {
    return new ActionPerformer_Factory(dispatcherProvider, defaultDispatcherProvider);
  }

  public static ActionPerformer newInstance(Dispatcher dispatcher,
      CoroutineDispatcher defaultDispatcher) {
    return new ActionPerformer(dispatcher, defaultDispatcher);
  }
}
