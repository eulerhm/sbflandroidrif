package org.wordpress.android.ui.posts.services;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.utils.AuthenticationUtils;

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
public final class AztecVideoLoader_MembersInjector implements MembersInjector<AztecVideoLoader> {
  private final Provider<AuthenticationUtils> mAuthenticationUtilsProvider;

  public AztecVideoLoader_MembersInjector(
      Provider<AuthenticationUtils> mAuthenticationUtilsProvider) {
    this.mAuthenticationUtilsProvider = mAuthenticationUtilsProvider;
  }

  public static MembersInjector<AztecVideoLoader> create(
      Provider<AuthenticationUtils> mAuthenticationUtilsProvider) {
    return new AztecVideoLoader_MembersInjector(mAuthenticationUtilsProvider);
  }

  @Override
  public void injectMembers(AztecVideoLoader instance) {
    injectMAuthenticationUtils(instance, mAuthenticationUtilsProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.services.AztecVideoLoader.mAuthenticationUtils")
  public static void injectMAuthenticationUtils(AztecVideoLoader instance,
      AuthenticationUtils mAuthenticationUtils) {
    instance.mAuthenticationUtils = mAuthenticationUtils;
  }
}
