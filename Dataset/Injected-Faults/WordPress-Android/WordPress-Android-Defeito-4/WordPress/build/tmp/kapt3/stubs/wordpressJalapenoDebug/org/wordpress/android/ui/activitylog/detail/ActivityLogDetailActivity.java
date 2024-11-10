package org.wordpress.android.ui.activitylog.detail;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005\u00a2\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014J\u0012\u0010\n\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0002J\u0012\u0010\u000b\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0002J\u0012\u0010\f\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/activitylog/detail/ActivityLogDetailActivity;", "Lorg/wordpress/android/ui/LocaleAwareActivity;", "()V", "onActivityResult", "", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onActivityResultForBackupDownload", "onActivityResultForRestore", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ActivityLogDetailActivity extends org.wordpress.android.ui.LocaleAwareActivity {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.activitylog.detail.ActivityLogDetailActivity.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_INNER_FLOW = "extra_inner_flow";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_RESTORE_FLOW = "extra_restore_inner_flow";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_BACKUP_DOWNLOAD_FLOW = "extra_backup_download_inner_flow";
    
    public ActivityLogDetailActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
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
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/activitylog/detail/ActivityLogDetailActivity$Companion;", "", "()V", "EXTRA_BACKUP_DOWNLOAD_FLOW", "", "EXTRA_INNER_FLOW", "EXTRA_RESTORE_FLOW", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}