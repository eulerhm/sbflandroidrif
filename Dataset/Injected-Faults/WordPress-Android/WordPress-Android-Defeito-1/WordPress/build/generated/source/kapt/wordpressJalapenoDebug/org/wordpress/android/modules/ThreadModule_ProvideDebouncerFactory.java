package org.wordpress.android.modules;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import org.wordpress.android.util.helpers.Debouncer;

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
public final class ThreadModule_ProvideDebouncerFactory implements Factory<Debouncer> {
  private final ThreadModule module;

  public ThreadModule_ProvideDebouncerFactory(ThreadModule module) {
    this.module = module;
  }

  @Override
  public Debouncer get() {
    return provideDebouncer(module);
  }

  public static ThreadModule_ProvideDebouncerFactory create(ThreadModule module) {
    return new ThreadModule_ProvideDebouncerFactory(module);
  }

  public static Debouncer provideDebouncer(ThreadModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideDebouncer());
  }
}
