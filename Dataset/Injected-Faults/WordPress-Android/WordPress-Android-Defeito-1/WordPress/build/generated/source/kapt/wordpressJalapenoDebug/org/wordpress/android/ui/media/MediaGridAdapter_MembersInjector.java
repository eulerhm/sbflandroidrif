package org.wordpress.android.ui.media;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Named;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineScope;
import org.wordpress.android.ui.utils.AuthenticationUtils;
import org.wordpress.android.util.image.ImageManager;

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
public final class MediaGridAdapter_MembersInjector implements MembersInjector<MediaGridAdapter> {
  private final Provider<ImageManager> mImageManagerProvider;

  private final Provider<AuthenticationUtils> mAuthenticationUtilsProvider;

  private final Provider<CoroutineScope> mAppScopeProvider;

  public MediaGridAdapter_MembersInjector(Provider<ImageManager> mImageManagerProvider,
      Provider<AuthenticationUtils> mAuthenticationUtilsProvider,
      Provider<CoroutineScope> mAppScopeProvider) {
    this.mImageManagerProvider = mImageManagerProvider;
    this.mAuthenticationUtilsProvider = mAuthenticationUtilsProvider;
    this.mAppScopeProvider = mAppScopeProvider;
  }

  public static MembersInjector<MediaGridAdapter> create(
      Provider<ImageManager> mImageManagerProvider,
      Provider<AuthenticationUtils> mAuthenticationUtilsProvider,
      Provider<CoroutineScope> mAppScopeProvider) {
    return new MediaGridAdapter_MembersInjector(mImageManagerProvider, mAuthenticationUtilsProvider, mAppScopeProvider);
  }

  @Override
  public void injectMembers(MediaGridAdapter instance) {
    injectMImageManager(instance, mImageManagerProvider.get());
    injectMAuthenticationUtils(instance, mAuthenticationUtilsProvider.get());
    injectMAppScope(instance, mAppScopeProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.media.MediaGridAdapter.mImageManager")
  public static void injectMImageManager(MediaGridAdapter instance, ImageManager mImageManager) {
    instance.mImageManager = mImageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.media.MediaGridAdapter.mAuthenticationUtils")
  public static void injectMAuthenticationUtils(MediaGridAdapter instance,
      AuthenticationUtils mAuthenticationUtils) {
    instance.mAuthenticationUtils = mAuthenticationUtils;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.media.MediaGridAdapter.mAppScope")
  @Named("APPLICATION_SCOPE")
  public static void injectMAppScope(MediaGridAdapter instance, CoroutineScope mAppScope) {
    instance.mAppScope = mAppScope;
  }
}
