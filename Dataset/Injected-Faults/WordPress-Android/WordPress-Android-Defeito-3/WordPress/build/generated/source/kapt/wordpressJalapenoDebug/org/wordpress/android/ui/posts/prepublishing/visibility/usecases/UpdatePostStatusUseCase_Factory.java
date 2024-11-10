package org.wordpress.android.ui.posts.prepublishing.visibility.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.posts.PostUtilsWrapper;
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
public final class UpdatePostStatusUseCase_Factory implements Factory<UpdatePostStatusUseCase> {
  private final Provider<DateTimeUtilsWrapper> dateTimeUtilsWrapperProvider;

  private final Provider<PostUtilsWrapper> postUtilsWrapperProvider;

  public UpdatePostStatusUseCase_Factory(
      Provider<DateTimeUtilsWrapper> dateTimeUtilsWrapperProvider,
      Provider<PostUtilsWrapper> postUtilsWrapperProvider) {
    this.dateTimeUtilsWrapperProvider = dateTimeUtilsWrapperProvider;
    this.postUtilsWrapperProvider = postUtilsWrapperProvider;
  }

  @Override
  public UpdatePostStatusUseCase get() {
    return newInstance(dateTimeUtilsWrapperProvider.get(), postUtilsWrapperProvider.get());
  }

  public static UpdatePostStatusUseCase_Factory create(
      Provider<DateTimeUtilsWrapper> dateTimeUtilsWrapperProvider,
      Provider<PostUtilsWrapper> postUtilsWrapperProvider) {
    return new UpdatePostStatusUseCase_Factory(dateTimeUtilsWrapperProvider, postUtilsWrapperProvider);
  }

  public static UpdatePostStatusUseCase newInstance(DateTimeUtilsWrapper dateTimeUtilsWrapper,
      PostUtilsWrapper postUtilsWrapper) {
    return new UpdatePostStatusUseCase(dateTimeUtilsWrapper, postUtilsWrapper);
  }
}
