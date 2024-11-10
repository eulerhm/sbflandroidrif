package org.wordpress.android.modules;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import kotlinx.coroutines.CoroutineScope;

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
public final class ThreadModule_ProvideBackgroundScopeFactory implements Factory<CoroutineScope> {
  private final ThreadModule module;

  public ThreadModule_ProvideBackgroundScopeFactory(ThreadModule module) {
    this.module = module;
  }

  @Override
  public CoroutineScope get() {
    return provideBackgroundScope(module);
  }

  public static ThreadModule_ProvideBackgroundScopeFactory create(ThreadModule module) {
    return new ThreadModule_ProvideBackgroundScopeFactory(module);
  }

  public static CoroutineScope provideBackgroundScope(ThreadModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideBackgroundScope());
  }
}
