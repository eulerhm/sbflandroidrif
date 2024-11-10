package org.wordpress.android.ui.jetpack.restore;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.jetpack.common.providers.JetpackAvailableItemsProvider;
import org.wordpress.android.ui.jetpack.restore.builders.RestoreStateListItemBuilder;
import org.wordpress.android.ui.jetpack.restore.usecases.GetRestoreStatusUseCase;
import org.wordpress.android.ui.jetpack.restore.usecases.PostRestoreUseCase;
import org.wordpress.android.ui.jetpack.usecases.GetActivityLogItemUseCase;
import org.wordpress.android.util.wizard.WizardManager;

@ScopeMetadata
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
public final class RestoreViewModel_Factory implements Factory<RestoreViewModel> {
  private final Provider<WizardManager<RestoreStep>> wizardManagerProvider;

  private final Provider<JetpackAvailableItemsProvider> availableItemsProvider;

  private final Provider<GetActivityLogItemUseCase> getActivityLogItemUseCaseProvider;

  private final Provider<RestoreStateListItemBuilder> stateListItemBuilderProvider;

  private final Provider<PostRestoreUseCase> postRestoreUseCaseProvider;

  private final Provider<GetRestoreStatusUseCase> getRestoreStatusUseCaseProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  public RestoreViewModel_Factory(Provider<WizardManager<RestoreStep>> wizardManagerProvider,
      Provider<JetpackAvailableItemsProvider> availableItemsProvider,
      Provider<GetActivityLogItemUseCase> getActivityLogItemUseCaseProvider,
      Provider<RestoreStateListItemBuilder> stateListItemBuilderProvider,
      Provider<PostRestoreUseCase> postRestoreUseCaseProvider,
      Provider<GetRestoreStatusUseCase> getRestoreStatusUseCaseProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    this.wizardManagerProvider = wizardManagerProvider;
    this.availableItemsProvider = availableItemsProvider;
    this.getActivityLogItemUseCaseProvider = getActivityLogItemUseCaseProvider;
    this.stateListItemBuilderProvider = stateListItemBuilderProvider;
    this.postRestoreUseCaseProvider = postRestoreUseCaseProvider;
    this.getRestoreStatusUseCaseProvider = getRestoreStatusUseCaseProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
  }

  @Override
  public RestoreViewModel get() {
    return newInstance(wizardManagerProvider.get(), availableItemsProvider.get(), getActivityLogItemUseCaseProvider.get(), stateListItemBuilderProvider.get(), postRestoreUseCaseProvider.get(), getRestoreStatusUseCaseProvider.get(), mainDispatcherProvider.get());
  }

  public static RestoreViewModel_Factory create(
      Provider<WizardManager<RestoreStep>> wizardManagerProvider,
      Provider<JetpackAvailableItemsProvider> availableItemsProvider,
      Provider<GetActivityLogItemUseCase> getActivityLogItemUseCaseProvider,
      Provider<RestoreStateListItemBuilder> stateListItemBuilderProvider,
      Provider<PostRestoreUseCase> postRestoreUseCaseProvider,
      Provider<GetRestoreStatusUseCase> getRestoreStatusUseCaseProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    return new RestoreViewModel_Factory(wizardManagerProvider, availableItemsProvider, getActivityLogItemUseCaseProvider, stateListItemBuilderProvider, postRestoreUseCaseProvider, getRestoreStatusUseCaseProvider, mainDispatcherProvider);
  }

  public static RestoreViewModel newInstance(WizardManager<RestoreStep> wizardManager,
      JetpackAvailableItemsProvider availableItemsProvider,
      GetActivityLogItemUseCase getActivityLogItemUseCase,
      RestoreStateListItemBuilder stateListItemBuilder, PostRestoreUseCase postRestoreUseCase,
      GetRestoreStatusUseCase getRestoreStatusUseCase, CoroutineDispatcher mainDispatcher) {
    return new RestoreViewModel(wizardManager, availableItemsProvider, getActivityLogItemUseCase, stateListItemBuilder, postRestoreUseCase, getRestoreStatusUseCase, mainDispatcher);
  }
}
