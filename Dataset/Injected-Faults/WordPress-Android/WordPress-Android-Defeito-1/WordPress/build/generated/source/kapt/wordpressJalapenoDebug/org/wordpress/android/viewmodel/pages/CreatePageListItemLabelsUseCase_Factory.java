package org.wordpress.android.viewmodel.pages;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.uploads.UploadUtilsWrapper;

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
public final class CreatePageListItemLabelsUseCase_Factory implements Factory<CreatePageListItemLabelsUseCase> {
  private final Provider<AutoSaveConflictResolver> autoSaveConflictResolverProvider;

  private final Provider<PostPageListLabelColorUseCase> labelColorUseCaseProvider;

  private final Provider<UploadUtilsWrapper> uploadUtilsWrapperProvider;

  public CreatePageListItemLabelsUseCase_Factory(
      Provider<AutoSaveConflictResolver> autoSaveConflictResolverProvider,
      Provider<PostPageListLabelColorUseCase> labelColorUseCaseProvider,
      Provider<UploadUtilsWrapper> uploadUtilsWrapperProvider) {
    this.autoSaveConflictResolverProvider = autoSaveConflictResolverProvider;
    this.labelColorUseCaseProvider = labelColorUseCaseProvider;
    this.uploadUtilsWrapperProvider = uploadUtilsWrapperProvider;
  }

  @Override
  public CreatePageListItemLabelsUseCase get() {
    return newInstance(autoSaveConflictResolverProvider.get(), labelColorUseCaseProvider.get(), uploadUtilsWrapperProvider.get());
  }

  public static CreatePageListItemLabelsUseCase_Factory create(
      Provider<AutoSaveConflictResolver> autoSaveConflictResolverProvider,
      Provider<PostPageListLabelColorUseCase> labelColorUseCaseProvider,
      Provider<UploadUtilsWrapper> uploadUtilsWrapperProvider) {
    return new CreatePageListItemLabelsUseCase_Factory(autoSaveConflictResolverProvider, labelColorUseCaseProvider, uploadUtilsWrapperProvider);
  }

  public static CreatePageListItemLabelsUseCase newInstance(
      AutoSaveConflictResolver autoSaveConflictResolver,
      PostPageListLabelColorUseCase labelColorUseCase, UploadUtilsWrapper uploadUtilsWrapper) {
    return new CreatePageListItemLabelsUseCase(autoSaveConflictResolver, labelColorUseCase, uploadUtilsWrapper);
  }
}
