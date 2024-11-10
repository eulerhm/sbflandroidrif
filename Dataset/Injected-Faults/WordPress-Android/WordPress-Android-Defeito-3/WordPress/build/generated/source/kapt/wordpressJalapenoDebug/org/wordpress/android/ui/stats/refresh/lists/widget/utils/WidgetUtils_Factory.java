package org.wordpress.android.ui.stats.refresh.lists.widget.utils;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.util.image.ImageManager;

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
public final class WidgetUtils_Factory implements Factory<WidgetUtils> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<ImageManager> imageManagerProvider;

  public WidgetUtils_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<ImageManager> imageManagerProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.imageManagerProvider = imageManagerProvider;
  }

  @Override
  public WidgetUtils get() {
    return newInstance(mainDispatcherProvider.get(), imageManagerProvider.get());
  }

  public static WidgetUtils_Factory create(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<ImageManager> imageManagerProvider) {
    return new WidgetUtils_Factory(mainDispatcherProvider, imageManagerProvider);
  }

  public static WidgetUtils newInstance(CoroutineDispatcher mainDispatcher,
      ImageManager imageManager) {
    return new WidgetUtils(mainDispatcher, imageManager);
  }
}
