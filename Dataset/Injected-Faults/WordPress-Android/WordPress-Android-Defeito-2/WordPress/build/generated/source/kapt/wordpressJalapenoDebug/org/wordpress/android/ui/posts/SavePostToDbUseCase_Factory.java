package org.wordpress.android.ui.posts;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.ui.notifications.utils.PendingDraftsNotificationsUtilsWrapper;
import org.wordpress.android.ui.uploads.UploadUtilsWrapper;
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
public final class SavePostToDbUseCase_Factory implements Factory<SavePostToDbUseCase> {
  private final Provider<UploadUtilsWrapper> uploadUtilsProvider;

  private final Provider<DateTimeUtilsWrapper> dateTimeUtilsProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<PendingDraftsNotificationsUtilsWrapper> pendingDraftsNotificationsUtilsProvider;

  private final Provider<Context> contextProvider;

  public SavePostToDbUseCase_Factory(Provider<UploadUtilsWrapper> uploadUtilsProvider,
      Provider<DateTimeUtilsWrapper> dateTimeUtilsProvider, Provider<Dispatcher> dispatcherProvider,
      Provider<PendingDraftsNotificationsUtilsWrapper> pendingDraftsNotificationsUtilsProvider,
      Provider<Context> contextProvider) {
    this.uploadUtilsProvider = uploadUtilsProvider;
    this.dateTimeUtilsProvider = dateTimeUtilsProvider;
    this.dispatcherProvider = dispatcherProvider;
    this.pendingDraftsNotificationsUtilsProvider = pendingDraftsNotificationsUtilsProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public SavePostToDbUseCase get() {
    return newInstance(uploadUtilsProvider.get(), dateTimeUtilsProvider.get(), dispatcherProvider.get(), pendingDraftsNotificationsUtilsProvider.get(), contextProvider.get());
  }

  public static SavePostToDbUseCase_Factory create(Provider<UploadUtilsWrapper> uploadUtilsProvider,
      Provider<DateTimeUtilsWrapper> dateTimeUtilsProvider, Provider<Dispatcher> dispatcherProvider,
      Provider<PendingDraftsNotificationsUtilsWrapper> pendingDraftsNotificationsUtilsProvider,
      Provider<Context> contextProvider) {
    return new SavePostToDbUseCase_Factory(uploadUtilsProvider, dateTimeUtilsProvider, dispatcherProvider, pendingDraftsNotificationsUtilsProvider, contextProvider);
  }

  public static SavePostToDbUseCase newInstance(UploadUtilsWrapper uploadUtils,
      DateTimeUtilsWrapper dateTimeUtils, Dispatcher dispatcher,
      PendingDraftsNotificationsUtilsWrapper pendingDraftsNotificationsUtils, Context context) {
    return new SavePostToDbUseCase(uploadUtils, dateTimeUtils, dispatcher, pendingDraftsNotificationsUtils, context);
  }
}
