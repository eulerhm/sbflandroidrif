package org.wordpress.android.viewmodel.posts;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase;
import org.wordpress.android.viewmodel.pages.PostPageListLabelColorUseCase;

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
public final class PostListItemUiStateHelper_Factory implements Factory<PostListItemUiStateHelper> {
  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  private final Provider<PostModelUploadUiStateUseCase> uploadUiStateUseCaseProvider;

  private final Provider<PostPageListLabelColorUseCase> labelColorUseCaseProvider;

  public PostListItemUiStateHelper_Factory(Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<PostModelUploadUiStateUseCase> uploadUiStateUseCaseProvider,
      Provider<PostPageListLabelColorUseCase> labelColorUseCaseProvider) {
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
    this.uploadUiStateUseCaseProvider = uploadUiStateUseCaseProvider;
    this.labelColorUseCaseProvider = labelColorUseCaseProvider;
  }

  @Override
  public PostListItemUiStateHelper get() {
    return newInstance(appPrefsWrapperProvider.get(), uploadUiStateUseCaseProvider.get(), labelColorUseCaseProvider.get());
  }

  public static PostListItemUiStateHelper_Factory create(
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<PostModelUploadUiStateUseCase> uploadUiStateUseCaseProvider,
      Provider<PostPageListLabelColorUseCase> labelColorUseCaseProvider) {
    return new PostListItemUiStateHelper_Factory(appPrefsWrapperProvider, uploadUiStateUseCaseProvider, labelColorUseCaseProvider);
  }

  public static PostListItemUiStateHelper newInstance(AppPrefsWrapper appPrefsWrapper,
      PostModelUploadUiStateUseCase uploadUiStateUseCase,
      PostPageListLabelColorUseCase labelColorUseCase) {
    return new PostListItemUiStateHelper(appPrefsWrapper, uploadUiStateUseCase, labelColorUseCase);
  }
}
