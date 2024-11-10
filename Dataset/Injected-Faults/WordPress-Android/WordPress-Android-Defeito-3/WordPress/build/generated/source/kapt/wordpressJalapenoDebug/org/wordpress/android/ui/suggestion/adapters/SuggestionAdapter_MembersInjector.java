package org.wordpress.android.ui.suggestion.adapters;

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
public final class SuggestionAdapter_MembersInjector implements MembersInjector<SuggestionAdapter> {
  private final Provider<ImageManager> mImageManagerProvider;

  public SuggestionAdapter_MembersInjector(Provider<ImageManager> mImageManagerProvider) {
    this.mImageManagerProvider = mImageManagerProvider;
  }

  public static MembersInjector<SuggestionAdapter> create(
      Provider<ImageManager> mImageManagerProvider) {
    return new SuggestionAdapter_MembersInjector(mImageManagerProvider);
  }

  @Override
  public void injectMembers(SuggestionAdapter instance) {
    injectMImageManager(instance, mImageManagerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.suggestion.adapters.SuggestionAdapter.mImageManager")
  public static void injectMImageManager(SuggestionAdapter instance, ImageManager mImageManager) {
    instance.mImageManager = mImageManager;
  }
}
