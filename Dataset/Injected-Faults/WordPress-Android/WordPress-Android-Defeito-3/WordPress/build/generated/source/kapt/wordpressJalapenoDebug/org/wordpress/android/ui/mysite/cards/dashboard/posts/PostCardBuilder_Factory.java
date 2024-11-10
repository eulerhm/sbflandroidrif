package org.wordpress.android.ui.mysite.cards.dashboard.posts;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.utils.AppLogWrapper;
import org.wordpress.android.util.LocaleManagerWrapper;

@ScopeMetadata
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
public final class PostCardBuilder_Factory implements Factory<PostCardBuilder> {
  private final Provider<LocaleManagerWrapper> localeManagerWrapperProvider;

  private final Provider<AppLogWrapper> appLogWrapperProvider;

  public PostCardBuilder_Factory(Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<AppLogWrapper> appLogWrapperProvider) {
    this.localeManagerWrapperProvider = localeManagerWrapperProvider;
    this.appLogWrapperProvider = appLogWrapperProvider;
  }

  @Override
  public PostCardBuilder get() {
    return newInstance(localeManagerWrapperProvider.get(), appLogWrapperProvider.get());
  }

  public static PostCardBuilder_Factory create(
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<AppLogWrapper> appLogWrapperProvider) {
    return new PostCardBuilder_Factory(localeManagerWrapperProvider, appLogWrapperProvider);
  }

  public static PostCardBuilder newInstance(LocaleManagerWrapper localeManagerWrapper,
      AppLogWrapper appLogWrapper) {
    return new PostCardBuilder(localeManagerWrapper, appLogWrapper);
  }
}
