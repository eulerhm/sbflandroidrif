package org.wordpress.android.modules;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import org.wordpress.android.support.SupportHelper;

@ScopeMetadata("javax.inject.Singleton")
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
public final class SupportModule_ProvideSupportHelperFactory implements Factory<SupportHelper> {
  private final SupportModule module;

  public SupportModule_ProvideSupportHelperFactory(SupportModule module) {
    this.module = module;
  }

  @Override
  public SupportHelper get() {
    return provideSupportHelper(module);
  }

  public static SupportModule_ProvideSupportHelperFactory create(SupportModule module) {
    return new SupportModule_ProvideSupportHelperFactory(module);
  }

  public static SupportHelper provideSupportHelper(SupportModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideSupportHelper());
  }
}
