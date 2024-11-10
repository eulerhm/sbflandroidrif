package org.wordpress.android.ui.plans;

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
public final class PlansListAdapter_MembersInjector implements MembersInjector<PlansListAdapter> {
  private final Provider<ImageManager> imageManagerProvider;

  public PlansListAdapter_MembersInjector(Provider<ImageManager> imageManagerProvider) {
    this.imageManagerProvider = imageManagerProvider;
  }

  public static MembersInjector<PlansListAdapter> create(
      Provider<ImageManager> imageManagerProvider) {
    return new PlansListAdapter_MembersInjector(imageManagerProvider);
  }

  @Override
  public void injectMembers(PlansListAdapter instance) {
    injectImageManager(instance, imageManagerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.plans.PlansListAdapter.imageManager")
  public static void injectImageManager(PlansListAdapter instance, ImageManager imageManager) {
    instance.imageManager = imageManager;
  }
}
