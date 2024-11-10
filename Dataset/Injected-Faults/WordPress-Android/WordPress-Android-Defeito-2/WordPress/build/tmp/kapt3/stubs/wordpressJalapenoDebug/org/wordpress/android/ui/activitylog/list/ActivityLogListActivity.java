package org.wordpress.android.ui.activitylog.list;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014J\u0012\u0010\n\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0002J\u0012\u0010\u000b\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0002J\u0012\u0010\f\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J \u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0006H\u0002J\u0018\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u0017H\u0002J\f\u0010\u001b\u001a\u00020\u0004*\u00020\u001cH\u0002\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/ActivityLogListActivity;", "Lorg/wordpress/android/ui/LocaleAwareActivity;", "()V", "onActivityResult", "", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onActivityResultForBackupDownload", "onActivityResultForRestore", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "passQueryBackupDownloadStatus", "rewindId", "", "downloadId", "", "actionState", "passQueryRestoreStatus", "restoreId", "checkAndUpdateUiToBackupScreen", "Lorg/wordpress/android/databinding/ActivityLogListActivityBinding;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ActivityLogListActivity extends org.wordpress.android.ui.LocaleAwareActivity {
    
    public ActivityLogListActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
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
    private final void checkAndUpdateUiToBackupScreen(org.wordpress.android.databinding.ActivityLogListActivityBinding $this$checkAndUpdateUiToBackupScreen) {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    private final void onActivityResultForRestore(android.content.Intent data) {
    }
    
    private final void onActivityResultForBackupDownload(android.content.Intent data) {
    }
    
    private final void passQueryRestoreStatus(java.lang.String rewindId, long restoreId) {
    }
    
    private final void passQueryBackupDownloadStatus(java.lang.String rewindId, long downloadId, int actionState) {
    }
}