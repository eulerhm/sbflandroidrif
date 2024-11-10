package org.wordpress.android.modules;

import com.android.volley.toolbox.ImageLoader;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;

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
public final class LegacyModule_ProvideImageCacheFactory implements Factory<ImageLoader.ImageCache> {
  private final LegacyModule module;

  public LegacyModule_ProvideImageCacheFactory(LegacyModule module) {
    this.module = module;
  }

  @Override
  public ImageLoader.ImageCache get() {
    return provideImageCache(module);
  }

  public static LegacyModule_ProvideImageCacheFactory create(LegacyModule module) {
    return new LegacyModule_ProvideImageCacheFactory(module);
  }

  public static ImageLoader.ImageCache provideImageCache(LegacyModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideImageCache());
  }
}
