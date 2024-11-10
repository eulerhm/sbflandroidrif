package org.wordpress.android.ui.history;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.image.ImageManager;

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
public final class HistoryAdapter_MembersInjector implements MembersInjector<HistoryAdapter> {
  private final Provider<ImageManager> imageManagerProvider;

  public HistoryAdapter_MembersInjector(Provider<ImageManager> imageManagerProvider) {
    this.imageManagerProvider = imageManagerProvider;
  }

  public static MembersInjector<HistoryAdapter> create(
      Provider<ImageManager> imageManagerProvider) {
    return new HistoryAdapter_MembersInjector(imageManagerProvider);
  }

  @Override
  public void injectMembers(HistoryAdapter instance) {
    injectImageManager(instance, imageManagerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.history.HistoryAdapter.imageManager")
  public static void injectImageManager(HistoryAdapter instance, ImageManager imageManager) {
    instance.imageManager = imageManager;
  }
}
