package org.wordpress.android.ui.posts.prepublishing.home.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.DateTimeUtilsWrapper;

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
public final class PublishPostImmediatelyUseCase_Factory implements Factory<PublishPostImmediatelyUseCase> {
  private final Provider<DateTimeUtilsWrapper> dateTimeUtilsWrapperProvider;

  public PublishPostImmediatelyUseCase_Factory(
      Provider<DateTimeUtilsWrapper> dateTimeUtilsWrapperProvider) {
    this.dateTimeUtilsWrapperProvider = dateTimeUtilsWrapperProvider;
  }

  @Override
  public PublishPostImmediatelyUseCase get() {
    return newInstance(dateTimeUtilsWrapperProvider.get());
  }

  public static PublishPostImmediatelyUseCase_Factory create(
      Provider<DateTimeUtilsWrapper> dateTimeUtilsWrapperProvider) {
    return new PublishPostImmediatelyUseCase_Factory(dateTimeUtilsWrapperProvider);
  }

  public static PublishPostImmediatelyUseCase newInstance(
      DateTimeUtilsWrapper dateTimeUtilsWrapper) {
    return new PublishPostImmediatelyUseCase(dateTimeUtilsWrapper);
  }
}
