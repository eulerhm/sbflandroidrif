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
public final class ThreadModule_ProvideBackgroundDispatcherFactory implements Factory<CoroutineDispatcher> {
  private final ThreadModule module;

  public ThreadModule_ProvideBackgroundDispatcherFactory(ThreadModule module) {
    this.module = module;
  }

  @Override
  public CoroutineDispatcher get() {
    return provideBackgroundDispatcher(module);
  }

  public static ThreadModule_ProvideBackgroundDispatcherFactory create(ThreadModule module) {
    return new ThreadModule_ProvideBackgroundDispatcherFactory(module);
  }

  public static CoroutineDispatcher provideBackgroundDispatcher(ThreadModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideBackgroundDispatcher());
  }
}
