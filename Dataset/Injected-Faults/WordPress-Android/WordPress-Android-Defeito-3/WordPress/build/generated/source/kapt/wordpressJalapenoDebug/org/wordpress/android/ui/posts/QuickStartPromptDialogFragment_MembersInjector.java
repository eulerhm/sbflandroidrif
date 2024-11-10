package org.wordpress.android.ui.posts;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
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
public final class QuickStartPromptDialogFragment_MembersInjector implements MembersInjector<QuickStartPromptDialogFragment> {
  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<SelectedSiteRepository> selectedSiteRepositoryProvider;

  public QuickStartPromptDialogFragment_MembersInjector(Provider<ImageManager> imageManagerProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider) {
    this.imageManagerProvider = imageManagerProvider;
    this.selectedSiteRepositoryProvider = selectedSiteRepositoryProvider;
  }

  public static MembersInjector<QuickStartPromptDialogFragment> create(
      Provider<ImageManager> imageManagerProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider) {
    return new QuickStartPromptDialogFragment_MembersInjector(imageManagerProvider, selectedSiteRepositoryProvider);
  }

  @Override
  public void injectMembers(QuickStartPromptDialogFragment instance) {
    injectImageManager(instance, imageManagerProvider.get());
    injectSelectedSiteRepository(instance, selectedSiteRepositoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.QuickStartPromptDialogFragment.imageManager")
  public static void injectImageManager(QuickStartPromptDialogFragment instance,
      ImageManager imageManager) {
    instance.imageManager = imageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.QuickStartPromptDialogFragment.selectedSiteRepository")
  public static void injectSelectedSiteRepository(QuickStartPromptDialogFragment instance,
      SelectedSiteRepository selectedSiteRepository) {
    instance.selectedSiteRepository = selectedSiteRepository;
  }
}
