package org.wordpress.android.modules;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import okhttp3.Interceptor;

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
public final class InterceptorModule_ProvideStethoInterceptorFactory implements Factory<Interceptor> {
  private final InterceptorModule module;

  public InterceptorModule_ProvideStethoInterceptorFactory(InterceptorModule module) {
    this.module = module;
  }

  @Override
  public Interceptor get() {
    return provideStethoInterceptor(module);
  }

  public static InterceptorModule_ProvideStethoInterceptorFactory create(InterceptorModule module) {
    return new InterceptorModule_ProvideStethoInterceptorFactory(module);
  }

  public static Interceptor provideStethoInterceptor(InterceptorModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideStethoInterceptor());
  }
}
