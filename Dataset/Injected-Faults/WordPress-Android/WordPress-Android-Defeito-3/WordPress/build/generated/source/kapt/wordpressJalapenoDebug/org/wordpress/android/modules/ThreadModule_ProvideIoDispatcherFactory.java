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
public final class ThreadModule_ProvideIoDispatcherFactory implements Factory<CoroutineDispatcher> {
  private final ThreadModule module;

  public ThreadModule_ProvideIoDispatcherFactory(ThreadModule module) {
    this.module = module;
  }

  @Override
  public CoroutineDispatcher get() {
    return provideIoDispatcher(module);
  }

  public static ThreadModule_ProvideIoDispatcherFactory create(ThreadModule module) {
    return new ThreadModule_ProvideIoDispatcherFactory(module);
  }

  public static CoroutineDispatcher provideIoDispatcher(ThreadModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideIoDispatcher());
  }
}
