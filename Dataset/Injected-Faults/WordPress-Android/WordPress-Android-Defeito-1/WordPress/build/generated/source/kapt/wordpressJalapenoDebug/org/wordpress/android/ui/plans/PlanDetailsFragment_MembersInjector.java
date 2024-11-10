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
public final class PlanDetailsFragment_MembersInjector implements MembersInjector<PlanDetailsFragment> {
  private final Provider<ImageManager> imageManagerProvider;

  public PlanDetailsFragment_MembersInjector(Provider<ImageManager> imageManagerProvider) {
    this.imageManagerProvider = imageManagerProvider;
  }

  public static MembersInjector<PlanDetailsFragment> create(
      Provider<ImageManager> imageManagerProvider) {
    return new PlanDetailsFragment_MembersInjector(imageManagerProvider);
  }

  @Override
  public void injectMembers(PlanDetailsFragment instance) {
    injectImageManager(instance, imageManagerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.plans.PlanDetailsFragment.imageManager")
  public static void injectImageManager(PlanDetailsFragment instance, ImageManager imageManager) {
    instance.imageManager = imageManager;
  }
}
