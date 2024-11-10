package org.wordpress.android.ui.reader.views;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.utils.UiHelpers;
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
public final class ReaderPostDetailHeaderView_MembersInjector implements MembersInjector<ReaderPostDetailHeaderView> {
  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  public ReaderPostDetailHeaderView_MembersInjector(Provider<ImageManager> imageManagerProvider,
      Provider<UiHelpers> uiHelpersProvider) {
    this.imageManagerProvider = imageManagerProvider;
    this.uiHelpersProvider = uiHelpersProvider;
  }

  public static MembersInjector<ReaderPostDetailHeaderView> create(
      Provider<ImageManager> imageManagerProvider, Provider<UiHelpers> uiHelpersProvider) {
    return new ReaderPostDetailHeaderView_MembersInjector(imageManagerProvider, uiHelpersProvider);
  }

  @Override
  public void injectMembers(ReaderPostDetailHeaderView instance) {
    injectImageManager(instance, imageManagerProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.views.ReaderPostDetailHeaderView.imageManager")
  public static void injectImageManager(ReaderPostDetailHeaderView instance,
      ImageManager imageManager) {
    instance.imageManager = imageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.views.ReaderPostDetailHeaderView.uiHelpers")
  public static void injectUiHelpers(ReaderPostDetailHeaderView instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }
}
