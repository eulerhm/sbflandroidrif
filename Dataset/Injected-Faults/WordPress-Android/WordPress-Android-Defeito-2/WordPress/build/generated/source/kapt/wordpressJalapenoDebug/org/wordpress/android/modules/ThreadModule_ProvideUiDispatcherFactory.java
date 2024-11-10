package org.wordpress.android.modules;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import kotlinx.coroutines.CoroutineDispatcher;

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
public final class ThreadModule_ProvideUiDispatcherFactory implements Factory<CoroutineDispatcher> {
  private final ThreadModule module;

  public ThreadModule_ProvideUiDispatcherFactory(ThreadModule module) {
    this.module = module;
  }

  @Override
  public CoroutineDispatcher get() {
    return provideUiDispatcher(module);
  }

  public static ThreadModule_ProvideUiDispatcherFactory create(ThreadModule module) {
    return new ThreadModule_ProvideUiDispatcherFactory(module);
  }

  public static CoroutineDispatcher provideUiDispatcher(ThreadModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideUiDispatcher());
  }
}
