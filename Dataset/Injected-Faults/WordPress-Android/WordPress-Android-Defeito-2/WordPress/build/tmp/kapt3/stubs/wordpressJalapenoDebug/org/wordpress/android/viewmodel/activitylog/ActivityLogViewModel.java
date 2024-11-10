package org.wordpress.android.viewmodel.activitylog;

import java.lang.System;

/**
 * It was decided to reuse the 'Activity Log' screen instead of creating a new 'Backup' screen. This was due to the
 * fact that there will be lots of code that would need to be duplicated for the new 'Backup' screen. On the other
 * hand, not much more complexity would be introduced if the 'Activity Log' screen is reused (mainly some 'if/else'
 * code branches here and there).
 *
 * However, should more 'Backup' related additions are added to the 'Activity Log' screen, then it should become a
 * necessity to split those features in separate screens in order not to increase further the complexity of this
 * screen's architecture.
 */
@kotlin.Suppress(names = {"LargeClass", "LongParameterList"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00a6\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0019\u0018\u00002\u00020\u0001:\u000e\u00a9\u0001\u00aa\u0001\u00ab\u0001\u00ac\u0001\u00ad\u0001\u00ae\u0001\u00af\u0001BG\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\u0002\u0010\u0012J\u0014\u0010_\u001a\u000e\u0012\u0004\u0012\u00020a\u0012\u0004\u0012\u00020a0`H\u0002J\u0014\u0010b\u001a\u000e\u0012\u0004\u0012\u00020a\u0012\u0004\u0012\u00020a0`H\u0002J\u0006\u0010c\u001a\u00020\u001fJ\u0010\u0010d\u001a\u00020\u001f2\u0006\u0010e\u001a\u000202H\u0002J\u0012\u0010f\u001a\u0004\u0018\u00010g2\u0006\u0010h\u001a\u00020/H\u0002J\u001c\u0010i\u001a\u00020j2\b\u0010k\u001a\u0004\u0018\u00010)2\b\u0010l\u001a\u0004\u0018\u00010mH\u0002J\u001c\u0010n\u001a\u00020j2\b\u0010k\u001a\u0004\u0018\u00010)2\b\u0010l\u001a\u0004\u0018\u00010mH\u0002J\u0010\u0010o\u001a\u00020\u001f2\u0006\u0010p\u001a\u00020qH\u0002J\u0010\u0010r\u001a\u00020\u001f2\u0006\u0010p\u001a\u00020sH\u0002J\u0010\u0010t\u001a\u00020\u001f2\u0006\u0010p\u001a\u00020uH\u0002J\u0010\u0010v\u001a\u00020\u001f2\u0006\u0010p\u001a\u00020wH\u0002J\u0010\u0010x\u001a\u00020\u001f2\u0006\u0010p\u001a\u00020yH\u0002J\b\u0010z\u001a\u00020\u001fH\u0002J\u0010\u0010{\u001a\u00020\u001f2\u0006\u0010p\u001a\u00020|H\u0002J*\u0010}\u001a\u00020\u001f2\u0006\u0010~\u001a\u00020\u007f2\u0007\u0010\u0080\u0001\u001a\u00020B2\u0007\u0010\u0081\u0001\u001a\u0002052\u0006\u0010h\u001a\u00020/H\u0002J\u0007\u0010\u0082\u0001\u001a\u00020\u001fJ\u0016\u0010\u0083\u0001\u001a\u00020\u001f2\r\u0010\u0084\u0001\u001a\b\u0012\u0004\u0012\u00020-0\u0019J\u0007\u0010\u0085\u0001\u001a\u00020\u001fJ\u0007\u0010\u0086\u0001\u001a\u00020\u001fJ\t\u0010\u0087\u0001\u001a\u00020\u001fH\u0014J$\u0010\u0088\u0001\u001a\u00020\u001f2\u001b\u0010\u0089\u0001\u001a\u0016\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u000202\u0018\u000101j\u0004\u0018\u0001`3J\u0012\u0010\u008a\u0001\u001a\u00020\u001f2\u0007\u0010\u008b\u0001\u001a\u00020)H\u0002J\u0010\u0010\u008c\u0001\u001a\u00020\u001f2\u0007\u0010\u008d\u0001\u001a\u00020\u001aJ\u0007\u0010\u008e\u0001\u001a\u00020\u001fJ!\u0010\u008f\u0001\u001a\u00020\u001f2\u0006\u0010k\u001a\u00020)2\u0006\u0010e\u001a\u0002022\b\u0010\u0090\u0001\u001a\u00030\u0091\u0001J\u0018\u0010\u0092\u0001\u001a\u00020\u001f2\u0006\u0010k\u001a\u00020)2\u0007\u0010\u0093\u0001\u001a\u000202J\u0007\u0010\u0094\u0001\u001a\u00020\u001fJ\u001a\u0010\u0095\u0001\u001a\u00020B2\b\u0010\u0096\u0001\u001a\u00030\u0097\u00012\u0007\u0010\u008d\u0001\u001a\u00020\u001aJ\u001b\u0010\u0098\u0001\u001a\u00020\u001f2\n\b\u0002\u0010e\u001a\u0004\u0018\u000102H\u0002\u00a2\u0006\u0003\u0010\u0099\u0001J\u001c\u0010\u009a\u0001\u001a\u00020\u001f2\u000b\b\u0002\u0010\u0093\u0001\u001a\u0004\u0018\u000102H\u0002\u00a2\u0006\u0003\u0010\u0099\u0001J\t\u0010\u009b\u0001\u001a\u00020\u001fH\u0002J\t\u0010\u009c\u0001\u001a\u00020\u001fH\u0002J\t\u0010\u009d\u0001\u001a\u00020\u001fH\u0002J\t\u0010\u009e\u0001\u001a\u00020\u001fH\u0002J)\u0010\u009f\u0001\u001a\u00020\u001f2\t\b\u0002\u0010\u00a0\u0001\u001a\u00020B2\t\b\u0002\u0010\u0081\u0001\u001a\u0002052\b\b\u0002\u0010h\u001a\u00020/H\u0007J\'\u0010\u00a1\u0001\u001a\u00020\u001f2\u0007\u0010\u00a2\u0001\u001a\u00020B2\t\b\u0002\u0010\u0081\u0001\u001a\u0002052\b\b\u0002\u0010h\u001a\u00020/H\u0002J\u0013\u0010\u00a3\u0001\u001a\u00020\u001f2\b\u0010k\u001a\u0004\u0018\u00010)H\u0002J\u0011\u0010\u00a4\u0001\u001a\u00020\u001f2\u0006\u0010k\u001a\u00020)H\u0002J\t\u0010\u00a5\u0001\u001a\u00020\u001fH\u0002J\u001d\u0010\u00a6\u0001\u001a\u00020\u001f2\b\u0010k\u001a\u0004\u0018\u00010)2\b\u0010l\u001a\u0004\u0018\u00010mH\u0002J\u0011\u0010\u00a7\u0001\u001a\u00020\u001f2\u0006\u0010k\u001a\u00020)H\u0002J\u0017\u0010\u00a8\u0001\u001a\u00020\u001f2\u0006\u0010Y\u001a\u00020Z2\u0006\u0010M\u001a\u00020BR\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00190\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0!0\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u001eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u001eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u001a0\u001eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\u001eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020-0\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020/X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\"\u00100\u001a\u0016\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u000202\u0018\u000101j\u0004\u0018\u0001`3X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00104\u001a\u000205X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u00106\u001a\b\u0012\u0004\u0012\u00020\u001507\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u00109R\u0017\u0010:\u001a\b\u0012\u0004\u0012\u00020\u0017078F\u00a2\u0006\u0006\u001a\u0004\b;\u00109R\u001d\u0010<\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u0019078F\u00a2\u0006\u0006\u001a\u0004\b=\u00109R\u0010\u0010>\u001a\u0004\u0018\u00010+X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010?\u001a\b\u0012\u0004\u0012\u00020\u001c078F\u00a2\u0006\u0006\u001a\u0004\b@\u00109R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010A\u001a\u00020B8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bA\u0010CR\u0014\u0010D\u001a\u00020B8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bD\u0010CR\u0014\u0010E\u001a\u00020B8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bE\u0010CR\u000e\u0010F\u001a\u00020BX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010G\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e8F\u00a2\u0006\u0006\u001a\u0004\bH\u0010IR\u001d\u0010J\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0!078F\u00a2\u0006\u0006\u001a\u0004\bK\u00109R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010L\u001a\u0004\u0018\u00010+X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010M\u001a\u00020BX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bN\u0010C\"\u0004\bO\u0010PR\u0017\u0010Q\u001a\b\u0012\u0004\u0012\u00020$078F\u00a2\u0006\u0006\u001a\u0004\bR\u00109R\u0017\u0010S\u001a\b\u0012\u0004\u0012\u00020&078F\u00a2\u0006\u0006\u001a\u0004\bT\u00109R\u0017\u0010U\u001a\b\u0012\u0004\u0012\u00020\u001a078F\u00a2\u0006\u0006\u001a\u0004\bV\u00109R\u0017\u0010W\u001a\b\u0012\u0004\u0012\u00020)078F\u00a2\u0006\u0006\u001a\u0004\bX\u00109R\u001a\u0010Y\u001a\u00020ZX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b[\u0010\\\"\u0004\b]\u0010^\u00a8\u0006\u00b0\u0001"}, d2 = {"Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel;", "Landroidx/lifecycle/ViewModel;", "activityLogStore", "Lorg/wordpress/android/fluxc/store/ActivityLogStore;", "getRestoreStatusUseCase", "Lorg/wordpress/android/ui/jetpack/restore/usecases/GetRestoreStatusUseCase;", "getBackupDownloadStatusUseCase", "Lorg/wordpress/android/ui/jetpack/backup/download/usecases/GetBackupDownloadStatusUseCase;", "postDismissBackupDownloadUseCase", "Lorg/wordpress/android/ui/jetpack/backup/download/usecases/PostDismissBackupDownloadUseCase;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "dateUtils", "Lorg/wordpress/android/ui/stats/refresh/utils/DateUtils;", "activityLogTracker", "Lorg/wordpress/android/util/analytics/ActivityLogTracker;", "jetpackCapabilitiesUseCase", "Lorg/wordpress/android/ui/jetpack/JetpackCapabilitiesUseCase;", "(Lorg/wordpress/android/fluxc/store/ActivityLogStore;Lorg/wordpress/android/ui/jetpack/restore/usecases/GetRestoreStatusUseCase;Lorg/wordpress/android/ui/jetpack/backup/download/usecases/GetBackupDownloadStatusUseCase;Lorg/wordpress/android/ui/jetpack/backup/download/usecases/PostDismissBackupDownloadUseCase;Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/ui/stats/refresh/utils/DateUtils;Lorg/wordpress/android/util/analytics/ActivityLogTracker;Lorg/wordpress/android/ui/jetpack/JetpackCapabilitiesUseCase;)V", "_emptyUiState", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$EmptyUiState;", "_eventListStatus", "Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$ActivityLogListStatus;", "_events", "", "Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem;", "_filtersUiState", "Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$FiltersUiState;", "_moveToTop", "Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "", "_navigationEvents", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/activitylog/ActivityLogNavigationEvents;", "_showActivityTypeFilterDialog", "Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$ShowActivityTypePicker;", "_showDateRangePicker", "Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$ShowDateRangePicker;", "_showItemDetail", "_showSnackbarMessage", "", "backupDownloadStatusJob", "Lkotlinx/coroutines/Job;", "currentActivityTypeFilter", "Lorg/wordpress/android/fluxc/model/activity/ActivityTypeModel;", "currentBackupDownloadEvent", "Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$BackupDownloadEvent;", "currentDateRangeFilter", "Landroidx/core/util/Pair;", "", "Lorg/wordpress/android/viewmodel/activitylog/DateRange;", "currentRestoreEvent", "Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$RestoreEvent;", "emptyUiState", "Landroidx/lifecycle/LiveData;", "getEmptyUiState", "()Landroidx/lifecycle/LiveData;", "eventListStatus", "getEventListStatus", "events", "getEvents", "fetchActivitiesJob", "filtersUiState", "getFiltersUiState", "isBackupDownloadProgressItemShown", "", "()Z", "isDone", "isRestoreProgressItemShown", "isStarted", "moveToTop", "getMoveToTop", "()Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "navigationEvents", "getNavigationEvents", "restoreStatusJob", "rewindableOnly", "getRewindableOnly", "setRewindableOnly", "(Z)V", "showActivityTypeFilterDialog", "getShowActivityTypeFilterDialog", "showDateRangePicker", "getShowDateRangePicker", "showItemDetail", "getShowItemDetail", "showSnackbarMessage", "getShowSnackbarMessage", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "setSite", "(Lorg/wordpress/android/fluxc/model/SiteModel;)V", "createActivityTypeFilterLabel", "Lkotlin/Pair;", "Lorg/wordpress/android/ui/utils/UiString;", "createDateRangeFilterLabel", "dateRangePickerClicked", "dismissNoticeClicked", "downloadId", "getBackupDownloadNoticeItem", "Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$Notice;", "backupDownloadEvent", "getBackupDownloadProgressItem", "Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$Progress;", "rewindId", "published", "Ljava/util/Date;", "getRestoreProgressItem", "handleBackupDownloadStatus", "state", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState;", "handleBackupDownloadStatusForComplete", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState$Complete;", "handleBackupDownloadStatusForProgress", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState$Progress;", "handleRestoreStatus", "Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState;", "handleRestoreStatusForComplete", "Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState$Complete;", "handleRestoreStatusForMultisite", "handleRestoreStatusForProgress", "Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState$Progress;", "onActivityLogFetched", "event", "Lorg/wordpress/android/fluxc/store/ActivityLogStore$OnActivityLogFetched;", "loadingMore", "restoreEvent", "onActivityTypeFilterClicked", "onActivityTypesSelected", "selectedTypes", "onClearActivityTypeFilterClicked", "onClearDateRangeFilterClicked", "onCleared", "onDateRangeSelected", "dateRange", "onDownloadBackupFileClicked", "url", "onItemClicked", "item", "onPullToRefresh", "onQueryBackupDownloadStatus", "actionState", "", "onQueryRestoreStatus", "restoreId", "onScrolledToBottom", "onSecondaryActionClicked", "secondaryAction", "Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$SecondaryAction;", "queryBackupDownloadStatus", "(Ljava/lang/Long;)V", "queryRestoreStatus", "refreshActivityLogEmptyUiState", "refreshBackupEmptyUiState", "refreshEmptyUiState", "refreshFiltersUiState", "reloadEvents", "done", "requestEventsUpdate", "loadMore", "showBackupDownloadFinishedMessage", "showBackupDownloadStartedMessage", "showFiltersIfSupported", "showRestoreFinishedMessage", "showRestoreStartedMessage", "start", "ActivityLogListStatus", "BackupDownloadEvent", "EmptyUiState", "FiltersUiState", "RestoreEvent", "ShowActivityTypePicker", "ShowDateRangePicker", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ActivityLogViewModel extends androidx.lifecycle.ViewModel {
    private final org.wordpress.android.fluxc.store.ActivityLogStore activityLogStore = null;
    private final org.wordpress.android.ui.jetpack.restore.usecases.GetRestoreStatusUseCase getRestoreStatusUseCase = null;
    private final org.wordpress.android.ui.jetpack.backup.download.usecases.GetBackupDownloadStatusUseCase getBackupDownloadStatusUseCase = null;
    private final org.wordpress.android.ui.jetpack.backup.download.usecases.PostDismissBackupDownloadUseCase postDismissBackupDownloadUseCase = null;
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final org.wordpress.android.ui.stats.refresh.utils.DateUtils dateUtils = null;
    private final org.wordpress.android.util.analytics.ActivityLogTracker activityLogTracker = null;
    private final org.wordpress.android.ui.jetpack.JetpackCapabilitiesUseCase jetpackCapabilitiesUseCase = null;
    private boolean isStarted = false;
    private final androidx.lifecycle.MutableLiveData<java.util.List<org.wordpress.android.ui.activitylog.list.ActivityLogListItem>> _events = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.ActivityLogListStatus> _eventListStatus = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.FiltersUiState> _filtersUiState = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.EmptyUiState> _emptyUiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.EmptyUiState> emptyUiState = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.ShowActivityTypePicker> _showActivityTypeFilterDialog = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.ShowDateRangePicker> _showDateRangePicker = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<kotlin.Unit> _moveToTop = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.ui.activitylog.list.ActivityLogListItem> _showItemDetail = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<java.lang.String> _showSnackbarMessage = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.activitylog.ActivityLogNavigationEvents>> _navigationEvents = null;
    private kotlinx.coroutines.Job fetchActivitiesJob;
    private kotlinx.coroutines.Job restoreStatusJob;
    private kotlinx.coroutines.Job backupDownloadStatusJob;
    private androidx.core.util.Pair<java.lang.Long, java.lang.Long> currentDateRangeFilter;
    private java.util.List<org.wordpress.android.fluxc.model.activity.ActivityTypeModel> currentActivityTypeFilter;
    public org.wordpress.android.fluxc.model.SiteModel site;
    private boolean rewindableOnly = false;
    private org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.RestoreEvent currentRestoreEvent;
    private org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.BackupDownloadEvent currentBackupDownloadEvent;
    
    @javax.inject.Inject()
    public ActivityLogViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.ActivityLogStore activityLogStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.restore.usecases.GetRestoreStatusUseCase getRestoreStatusUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.backup.download.usecases.GetBackupDownloadStatusUseCase getBackupDownloadStatusUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.backup.download.usecases.PostDismissBackupDownloadUseCase postDismissBackupDownloadUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.DateUtils dateUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.ActivityLogTracker activityLogTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.JetpackCapabilitiesUseCase jetpackCapabilitiesUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.ui.activitylog.list.ActivityLogListItem>> getEvents() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.ActivityLogListStatus> getEventListStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.FiltersUiState> getFiltersUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.EmptyUiState> getEmptyUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.ShowActivityTypePicker> getShowActivityTypeFilterDialog() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.ShowDateRangePicker> getShowDateRangePicker() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.viewmodel.SingleLiveEvent<kotlin.Unit> getMoveToTop() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.activitylog.list.ActivityLogListItem> getShowItemDetail() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getShowSnackbarMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.activitylog.ActivityLogNavigationEvents>> getNavigationEvents() {
        return null;
    }
    
    private final boolean isRestoreProgressItemShown() {
        return false;
    }
    
    private final boolean isBackupDownloadProgressItemShown() {
        return false;
    }
    
    private final boolean isDone() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.model.SiteModel getSite() {
        return null;
    }
    
    public final void setSite(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel p0) {
    }
    
    public final boolean getRewindableOnly() {
        return false;
    }
    
    public final void setRewindableOnly(boolean p0) {
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, boolean rewindableOnly) {
    }
    
    @androidx.annotation.VisibleForTesting()
    @kotlin.Suppress(names = {"LongMethod", "ComplexMethod"})
    public final void reloadEvents(boolean done, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.RestoreEvent restoreEvent, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.BackupDownloadEvent backupDownloadEvent) {
    }
    
    private final org.wordpress.android.ui.activitylog.list.ActivityLogListItem.Progress getRestoreProgressItem(java.lang.String rewindId, java.util.Date published) {
        return null;
    }
    
    private final org.wordpress.android.ui.activitylog.list.ActivityLogListItem.Progress getBackupDownloadProgressItem(java.lang.String rewindId, java.util.Date published) {
        return null;
    }
    
    private final org.wordpress.android.ui.activitylog.list.ActivityLogListItem.Notice getBackupDownloadNoticeItem(org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.BackupDownloadEvent backupDownloadEvent) {
        return null;
    }
    
    private final void showRestoreFinishedMessage(java.lang.String rewindId, java.util.Date published) {
    }
    
    private final void showBackupDownloadFinishedMessage(java.lang.String rewindId) {
    }
    
    private final void requestEventsUpdate(boolean loadMore, org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.RestoreEvent restoreEvent, org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.BackupDownloadEvent backupDownloadEvent) {
    }
    
    private final void onActivityLogFetched(org.wordpress.android.fluxc.store.ActivityLogStore.OnActivityLogFetched event, boolean loadingMore, org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.RestoreEvent restoreEvent, org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.BackupDownloadEvent backupDownloadEvent) {
    }
    
    private final void showFiltersIfSupported() {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    private final void refreshFiltersUiState() {
    }
    
    private final void refreshEmptyUiState() {
    }
    
    private final void refreshBackupEmptyUiState() {
    }
    
    private final void refreshActivityLogEmptyUiState() {
    }
    
    private final kotlin.Pair<org.wordpress.android.ui.utils.UiString, org.wordpress.android.ui.utils.UiString> createDateRangeFilterLabel() {
        return null;
    }
    
    private final kotlin.Pair<org.wordpress.android.ui.utils.UiString, org.wordpress.android.ui.utils.UiString> createActivityTypeFilterLabel() {
        return null;
    }
    
    public final void onScrolledToBottom() {
    }
    
    public final void onPullToRefresh() {
    }
    
    public final void onItemClicked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.activitylog.list.ActivityLogListItem item) {
    }
    
    public final boolean onSecondaryActionClicked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.activitylog.list.ActivityLogListItem.SecondaryAction secondaryAction, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.activitylog.list.ActivityLogListItem item) {
        return false;
    }
    
    private final void onDownloadBackupFileClicked(java.lang.String url) {
    }
    
    /**
     * Reload events first to remove the notice item, as it shows progress to the user. Then
     * trigger the dismiss (this is an optimistic call). If the dismiss fails it will show
     * again on the next reload.
     */
    private final void dismissNoticeClicked(long downloadId) {
    }
    
    public final void dateRangePickerClicked() {
    }
    
    public final void onDateRangeSelected(@org.jetbrains.annotations.Nullable()
    androidx.core.util.Pair<java.lang.Long, java.lang.Long> dateRange) {
    }
    
    public final void onClearDateRangeFilterClicked() {
    }
    
    public final void onActivityTypeFilterClicked() {
    }
    
    public final void onActivityTypesSelected(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.fluxc.model.activity.ActivityTypeModel> selectedTypes) {
    }
    
    public final void onClearActivityTypeFilterClicked() {
    }
    
    public final void onQueryRestoreStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String rewindId, long restoreId) {
    }
    
    private final void queryRestoreStatus(java.lang.Long restoreId) {
    }
    
    private final void handleRestoreStatus(org.wordpress.android.ui.jetpack.restore.RestoreRequestState state) {
    }
    
    private final void handleRestoreStatusForMultisite() {
    }
    
    private final void handleRestoreStatusForProgress(org.wordpress.android.ui.jetpack.restore.RestoreRequestState.Progress state) {
    }
    
    private final void handleRestoreStatusForComplete(org.wordpress.android.ui.jetpack.restore.RestoreRequestState.Complete state) {
    }
    
    private final void showRestoreStartedMessage(java.lang.String rewindId) {
    }
    
    public final void onQueryBackupDownloadStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String rewindId, long downloadId, int actionState) {
    }
    
    private final void queryBackupDownloadStatus(java.lang.Long downloadId) {
    }
    
    private final void handleBackupDownloadStatus(org.wordpress.android.ui.jetpack.backup.download.BackupDownloadRequestState state) {
    }
    
    private final void handleBackupDownloadStatusForProgress(org.wordpress.android.ui.jetpack.backup.download.BackupDownloadRequestState.Progress state) {
    }
    
    private final void handleBackupDownloadStatusForComplete(org.wordpress.android.ui.jetpack.backup.download.BackupDownloadRequestState.Complete state) {
    }
    
    private final void showBackupDownloadStartedMessage(java.lang.String rewindId) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$ActivityLogListStatus;", "", "(Ljava/lang/String;I)V", "CAN_LOAD_MORE", "DONE", "ERROR", "FETCHING", "LOADING_MORE", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum ActivityLogListStatus {
        /*public static final*/ CAN_LOAD_MORE /* = new CAN_LOAD_MORE() */,
        /*public static final*/ DONE /* = new DONE() */,
        /*public static final*/ ERROR /* = new ERROR() */,
        /*public static final*/ FETCHING /* = new FETCHING() */,
        /*public static final*/ LOADING_MORE /* = new LOADING_MORE() */;
        
        ActivityLogListStatus() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0004\u0018\u0001`\u0005\u00a2\u0006\u0002\u0010\u0006J\u001d\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0004\u0018\u0001`\u0005H\u00c6\u0003J\'\u0010\n\u001a\u00020\u00002\u001c\b\u0002\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0004\u0018\u0001`\u0005H\u00c6\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R%\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0004\u0018\u0001`\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$ShowDateRangePicker;", "", "initialSelection", "Landroidx/core/util/Pair;", "", "Lorg/wordpress/android/viewmodel/activitylog/DateRange;", "(Landroidx/core/util/Pair;)V", "getInitialSelection", "()Landroidx/core/util/Pair;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ShowDateRangePicker {
        @org.jetbrains.annotations.Nullable()
        private final androidx.core.util.Pair<java.lang.Long, java.lang.Long> initialSelection = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.ShowDateRangePicker copy(@org.jetbrains.annotations.Nullable()
        androidx.core.util.Pair<java.lang.Long, java.lang.Long> initialSelection) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public ShowDateRangePicker(@org.jetbrains.annotations.Nullable()
        androidx.core.util.Pair<java.lang.Long, java.lang.Long> initialSelection) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final androidx.core.util.Pair<java.lang.Long, java.lang.Long> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final androidx.core.util.Pair<java.lang.Long, java.lang.Long> getInitialSelection() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u001a\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010\bj\u0004\u0018\u0001`\n\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\u001d\u0010\u0014\u001a\u0016\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010\bj\u0004\u0018\u0001`\nH\u00c6\u0003JA\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u001c\b\u0002\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010\bj\u0004\u0018\u0001`\nH\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u0006H\u00d6\u0001R%\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010\bj\u0004\u0018\u0001`\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u001c"}, d2 = {"Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$ShowActivityTypePicker;", "", "siteId", "Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$RemoteId;", "initialSelection", "", "", "dateRange", "Landroidx/core/util/Pair;", "", "Lorg/wordpress/android/viewmodel/activitylog/DateRange;", "(Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$RemoteId;Ljava/util/List;Landroidx/core/util/Pair;)V", "getDateRange", "()Landroidx/core/util/Pair;", "getInitialSelection", "()Ljava/util/List;", "getSiteId", "()Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$RemoteId;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ShowActivityTypePicker {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId siteId = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<java.lang.String> initialSelection = null;
        @org.jetbrains.annotations.Nullable()
        private final androidx.core.util.Pair<java.lang.Long, java.lang.Long> dateRange = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.ShowActivityTypePicker copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId siteId, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> initialSelection, @org.jetbrains.annotations.Nullable()
        androidx.core.util.Pair<java.lang.Long, java.lang.Long> dateRange) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public ShowActivityTypePicker(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId siteId, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> initialSelection, @org.jetbrains.annotations.Nullable()
        androidx.core.util.Pair<java.lang.Long, java.lang.Long> dateRange) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId getSiteId() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> getInitialSelection() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final androidx.core.util.Pair<java.lang.Long, java.lang.Long> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final androidx.core.util.Pair<java.lang.Long, java.lang.Long> getDateRange() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\tH\u00c6\u0003J?\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u00c6\u0001J\u0013\u0010\u0017\u001a\u00020\u00032\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u0007H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\fR\u0013\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001c"}, d2 = {"Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$RestoreEvent;", "", "displayProgress", "", "isRestoreHidden", "isCompleted", "rewindId", "", "published", "Ljava/util/Date;", "(ZZZLjava/lang/String;Ljava/util/Date;)V", "getDisplayProgress", "()Z", "getPublished", "()Ljava/util/Date;", "getRewindId", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class RestoreEvent {
        private final boolean displayProgress = false;
        private final boolean isRestoreHidden = false;
        private final boolean isCompleted = false;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String rewindId = null;
        @org.jetbrains.annotations.Nullable()
        private final java.util.Date published = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.RestoreEvent copy(boolean displayProgress, boolean isRestoreHidden, boolean isCompleted, @org.jetbrains.annotations.Nullable()
        java.lang.String rewindId, @org.jetbrains.annotations.Nullable()
        java.util.Date published) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public RestoreEvent(boolean displayProgress, boolean isRestoreHidden, boolean isCompleted, @org.jetbrains.annotations.Nullable()
        java.lang.String rewindId, @org.jetbrains.annotations.Nullable()
        java.util.Date published) {
            super();
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean getDisplayProgress() {
            return false;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean isRestoreHidden() {
            return false;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean isCompleted() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getRewindId() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.Date component5() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.Date getPublished() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u001c\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B[\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\t\u00a2\u0006\u0002\u0010\u000eJ\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\tH\u00c6\u0003J\u0010\u0010 \u001a\u0004\u0018\u00010\u000bH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0013J\u000b\u0010!\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\tH\u00c6\u0003Jh\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\tH\u00c6\u0001\u00a2\u0006\u0002\u0010$J\u0013\u0010%\u001a\u00020\u00032\b\u0010&\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\'\u001a\u00020(H\u00d6\u0001J\t\u0010)\u001a\u00020\u0007H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0015\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0013\u0010\r\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016\u00a8\u0006*"}, d2 = {"Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$BackupDownloadEvent;", "", "displayProgress", "", "displayNotice", "isCompleted", "rewindId", "", "published", "Ljava/util/Date;", "downloadId", "", "url", "validUntil", "(ZZZLjava/lang/String;Ljava/util/Date;Ljava/lang/Long;Ljava/lang/String;Ljava/util/Date;)V", "getDisplayNotice", "()Z", "getDisplayProgress", "getDownloadId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getPublished", "()Ljava/util/Date;", "getRewindId", "()Ljava/lang/String;", "getUrl", "getValidUntil", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(ZZZLjava/lang/String;Ljava/util/Date;Ljava/lang/Long;Ljava/lang/String;Ljava/util/Date;)Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$BackupDownloadEvent;", "equals", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class BackupDownloadEvent {
        private final boolean displayProgress = false;
        private final boolean displayNotice = false;
        private final boolean isCompleted = false;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String rewindId = null;
        @org.jetbrains.annotations.Nullable()
        private final java.util.Date published = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Long downloadId = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String url = null;
        @org.jetbrains.annotations.Nullable()
        private final java.util.Date validUntil = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.BackupDownloadEvent copy(boolean displayProgress, boolean displayNotice, boolean isCompleted, @org.jetbrains.annotations.Nullable()
        java.lang.String rewindId, @org.jetbrains.annotations.Nullable()
        java.util.Date published, @org.jetbrains.annotations.Nullable()
        java.lang.Long downloadId, @org.jetbrains.annotations.Nullable()
        java.lang.String url, @org.jetbrains.annotations.Nullable()
        java.util.Date validUntil) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public BackupDownloadEvent(boolean displayProgress, boolean displayNotice, boolean isCompleted, @org.jetbrains.annotations.Nullable()
        java.lang.String rewindId, @org.jetbrains.annotations.Nullable()
        java.util.Date published, @org.jetbrains.annotations.Nullable()
        java.lang.Long downloadId, @org.jetbrains.annotations.Nullable()
        java.lang.String url, @org.jetbrains.annotations.Nullable()
        java.util.Date validUntil) {
            super();
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean getDisplayProgress() {
            return false;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean getDisplayNotice() {
            return false;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean isCompleted() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getRewindId() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.Date component5() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.Date getPublished() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Long component6() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Long getDownloadId() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component7() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getUrl() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.Date component8() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.Date getValidUntil() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0007\bB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0002\t\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$FiltersUiState;", "", "visibility", "", "(Z)V", "getVisibility", "()Z", "FiltersHidden", "FiltersShown", "Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$FiltersUiState$FiltersHidden;", "Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$FiltersUiState$FiltersShown;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class FiltersUiState {
        private final boolean visibility = false;
        
        private FiltersUiState(boolean visibility) {
            super();
        }
        
        public final boolean getVisibility() {
            return false;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$FiltersUiState$FiltersHidden;", "Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$FiltersUiState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class FiltersHidden extends org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.FiltersUiState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.FiltersUiState.FiltersHidden INSTANCE = null;
            
            private FiltersHidden() {
                super(false);
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\u0011\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u00c6\u0003J\u0011\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u00c6\u0003JU\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u00c6\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u00d6\u0003J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001J\t\u0010!\u001a\u00020\"H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0019\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012\u00a8\u0006#"}, d2 = {"Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$FiltersUiState$FiltersShown;", "Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$FiltersUiState;", "dateRangeLabel", "Lorg/wordpress/android/ui/utils/UiString;", "dateRangeLabelContentDescription", "activityTypeLabel", "activityTypeLabelContentDescription", "onClearDateRangeFilterClicked", "Lkotlin/Function0;", "", "onClearActivityTypeFilterClicked", "(Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/UiString;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "getActivityTypeLabel", "()Lorg/wordpress/android/ui/utils/UiString;", "getActivityTypeLabelContentDescription", "getDateRangeLabel", "getDateRangeLabelContentDescription", "getOnClearActivityTypeFilterClicked", "()Lkotlin/jvm/functions/Function0;", "getOnClearDateRangeFilterClicked", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class FiltersShown extends org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.FiltersUiState {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString dateRangeLabel = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString dateRangeLabelContentDescription = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString activityTypeLabel = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString activityTypeLabelContentDescription = null;
            @org.jetbrains.annotations.Nullable()
            private final kotlin.jvm.functions.Function0<kotlin.Unit> onClearDateRangeFilterClicked = null;
            @org.jetbrains.annotations.Nullable()
            private final kotlin.jvm.functions.Function0<kotlin.Unit> onClearActivityTypeFilterClicked = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.FiltersUiState.FiltersShown copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString dateRangeLabel, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString dateRangeLabelContentDescription, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString activityTypeLabel, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString activityTypeLabelContentDescription, @org.jetbrains.annotations.Nullable()
            kotlin.jvm.functions.Function0<kotlin.Unit> onClearDateRangeFilterClicked, @org.jetbrains.annotations.Nullable()
            kotlin.jvm.functions.Function0<kotlin.Unit> onClearActivityTypeFilterClicked) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            public FiltersShown(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString dateRangeLabel, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString dateRangeLabelContentDescription, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString activityTypeLabel, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString activityTypeLabelContentDescription, @org.jetbrains.annotations.Nullable()
            kotlin.jvm.functions.Function0<kotlin.Unit> onClearDateRangeFilterClicked, @org.jetbrains.annotations.Nullable()
            kotlin.jvm.functions.Function0<kotlin.Unit> onClearActivityTypeFilterClicked) {
                super(false);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString getDateRangeLabel() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString getDateRangeLabelContentDescription() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString component3() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString getActivityTypeLabel() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString component4() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString getActivityTypeLabelContentDescription() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final kotlin.jvm.functions.Function0<kotlin.Unit> component5() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnClearDateRangeFilterClicked() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final kotlin.jvm.functions.Function0<kotlin.Unit> component6() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnClearActivityTypeFilterClicked() {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\t\nB\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\u0006\u0082\u0001\u0004\u000b\f\r\u000e\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$EmptyUiState;", "", "()V", "emptyScreenSubtitle", "Lorg/wordpress/android/ui/utils/UiString;", "getEmptyScreenSubtitle", "()Lorg/wordpress/android/ui/utils/UiString;", "emptyScreenTitle", "getEmptyScreenTitle", "ActivityLog", "Backup", "Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$EmptyUiState$ActivityLog$EmptyFilters;", "Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$EmptyUiState$ActivityLog$ActiveFilters;", "Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$EmptyUiState$Backup$EmptyFilters;", "Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$EmptyUiState$Backup$ActiveFilters;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class EmptyUiState {
        
        private EmptyUiState() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public abstract org.wordpress.android.ui.utils.UiString getEmptyScreenTitle();
        
        @org.jetbrains.annotations.NotNull()
        public abstract org.wordpress.android.ui.utils.UiString getEmptyScreenSubtitle();
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$EmptyUiState$ActivityLog;", "", "()V", "ActiveFilters", "EmptyFilters", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ActivityLog {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.EmptyUiState.ActivityLog INSTANCE = null;
            
            private ActivityLog() {
                super();
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$EmptyUiState$ActivityLog$EmptyFilters;", "Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$EmptyUiState;", "()V", "emptyScreenSubtitle", "Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "getEmptyScreenSubtitle", "()Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "emptyScreenTitle", "getEmptyScreenTitle", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class EmptyFilters extends org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.EmptyUiState {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.EmptyUiState.ActivityLog.EmptyFilters INSTANCE = null;
                @org.jetbrains.annotations.NotNull()
                private static final org.wordpress.android.ui.utils.UiString.UiStringRes emptyScreenTitle = null;
                @org.jetbrains.annotations.NotNull()
                private static final org.wordpress.android.ui.utils.UiString.UiStringRes emptyScreenSubtitle = null;
                
                private EmptyFilters() {
                    super();
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString.UiStringRes getEmptyScreenTitle() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString.UiStringRes getEmptyScreenSubtitle() {
                    return null;
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$EmptyUiState$ActivityLog$ActiveFilters;", "Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$EmptyUiState;", "()V", "emptyScreenSubtitle", "Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "getEmptyScreenSubtitle", "()Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "emptyScreenTitle", "getEmptyScreenTitle", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class ActiveFilters extends org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.EmptyUiState {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.EmptyUiState.ActivityLog.ActiveFilters INSTANCE = null;
                @org.jetbrains.annotations.NotNull()
                private static final org.wordpress.android.ui.utils.UiString.UiStringRes emptyScreenTitle = null;
                @org.jetbrains.annotations.NotNull()
                private static final org.wordpress.android.ui.utils.UiString.UiStringRes emptyScreenSubtitle = null;
                
                private ActiveFilters() {
                    super();
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString.UiStringRes getEmptyScreenTitle() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString.UiStringRes getEmptyScreenSubtitle() {
                    return null;
                }
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$EmptyUiState$Backup;", "", "()V", "ActiveFilters", "EmptyFilters", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Backup {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.EmptyUiState.Backup INSTANCE = null;
            
            private Backup() {
                super();
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$EmptyUiState$Backup$EmptyFilters;", "Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$EmptyUiState;", "()V", "emptyScreenSubtitle", "Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "getEmptyScreenSubtitle", "()Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "emptyScreenTitle", "getEmptyScreenTitle", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class EmptyFilters extends org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.EmptyUiState {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.EmptyUiState.Backup.EmptyFilters INSTANCE = null;
                @org.jetbrains.annotations.NotNull()
                private static final org.wordpress.android.ui.utils.UiString.UiStringRes emptyScreenTitle = null;
                @org.jetbrains.annotations.NotNull()
                private static final org.wordpress.android.ui.utils.UiString.UiStringRes emptyScreenSubtitle = null;
                
                private EmptyFilters() {
                    super();
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString.UiStringRes getEmptyScreenTitle() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString.UiStringRes getEmptyScreenSubtitle() {
                    return null;
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$EmptyUiState$Backup$ActiveFilters;", "Lorg/wordpress/android/viewmodel/activitylog/ActivityLogViewModel$EmptyUiState;", "()V", "emptyScreenSubtitle", "Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "getEmptyScreenSubtitle", "()Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "emptyScreenTitle", "getEmptyScreenTitle", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class ActiveFilters extends org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.EmptyUiState {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.viewmodel.activitylog.ActivityLogViewModel.EmptyUiState.Backup.ActiveFilters INSTANCE = null;
                @org.jetbrains.annotations.NotNull()
                private static final org.wordpress.android.ui.utils.UiString.UiStringRes emptyScreenTitle = null;
                @org.jetbrains.annotations.NotNull()
                private static final org.wordpress.android.ui.utils.UiString.UiStringRes emptyScreenSubtitle = null;
                
                private ActiveFilters() {
                    super();
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString.UiStringRes getEmptyScreenTitle() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public org.wordpress.android.ui.utils.UiString.UiStringRes getEmptyScreenSubtitle() {
                    return null;
                }
            }
        }
    }
}