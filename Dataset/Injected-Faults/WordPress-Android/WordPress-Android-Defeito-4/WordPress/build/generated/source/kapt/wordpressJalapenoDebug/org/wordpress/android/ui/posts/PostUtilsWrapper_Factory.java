package org.wordpress.android.ui.posts;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.reader.utils.DateProvider;

@ScopeMetadata("dagger.Reusable")
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
public final class PostUtilsWrapper_Factory implements Factory<PostUtilsWrapper> {
  private final Provider<DateProvider> dateProvider;

  public PostUtilsWrapper_Factory(Provider<DateProvider> dateProvider) {
    this.dateProvider = dateProvider;
  }

  @Override
  public PostUtilsWrapper get() {
    return newInstance(dateProvider.get());
  }

  public static PostUtilsWrapper_Factory create(Provider<DateProvider> dateProvider) {
    return new PostUtilsWrapper_Factory(dateProvider);
  }

  public static PostUtilsWrapper newInstance(DateProvider dateProvider) {
    return new PostUtilsWrapper(dateProvider);
  }
}
