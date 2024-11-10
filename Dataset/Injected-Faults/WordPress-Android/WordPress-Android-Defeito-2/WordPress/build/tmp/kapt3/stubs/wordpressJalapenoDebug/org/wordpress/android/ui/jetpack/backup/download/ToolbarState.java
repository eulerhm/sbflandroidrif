package org.wordpress.android.ui.jetpack.backup.download;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\t\n\u000b\fB\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0087D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\u0006\u0082\u0001\u0004\r\u000e\u000f\u0010\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/jetpack/backup/download/ToolbarState;", "", "()V", "icon", "", "getIcon", "()I", "title", "getTitle", "CompleteToolbarState", "DetailsToolbarState", "ErrorToolbarState", "ProgressToolbarState", "Lorg/wordpress/android/ui/jetpack/backup/download/ToolbarState$DetailsToolbarState;", "Lorg/wordpress/android/ui/jetpack/backup/download/ToolbarState$ProgressToolbarState;", "Lorg/wordpress/android/ui/jetpack/backup/download/ToolbarState$CompleteToolbarState;", "Lorg/wordpress/android/ui/jetpack/backup/download/ToolbarState$ErrorToolbarState;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class ToolbarState {
    @androidx.annotation.DrawableRes()
    private final int icon = org.wordpress.android.R.drawable.ic_close_24px;
    
    private ToolbarState() {
        super();
    }
    
    public abstract int getTitle();
    
    public final int getIcon() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0016X\u0097D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/jetpack/backup/download/ToolbarState$DetailsToolbarState;", "Lorg/wordpress/android/ui/jetpack/backup/download/ToolbarState;", "()V", "title", "", "getTitle", "()I", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class DetailsToolbarState extends org.wordpress.android.ui.jetpack.backup.download.ToolbarState {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.jetpack.backup.download.ToolbarState.DetailsToolbarState INSTANCE = null;
        @androidx.annotation.StringRes()
        private static final int title = org.wordpress.android.R.string.backup_download_details_page_title;
        
        private DetailsToolbarState() {
            super();
        }
        
        @java.lang.Override()
        public int getTitle() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0016X\u0097D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/jetpack/backup/download/ToolbarState$ProgressToolbarState;", "Lorg/wordpress/android/ui/jetpack/backup/download/ToolbarState;", "()V", "title", "", "getTitle", "()I", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ProgressToolbarState extends org.wordpress.android.ui.jetpack.backup.download.ToolbarState {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.jetpack.backup.download.ToolbarState.ProgressToolbarState INSTANCE = null;
        @androidx.annotation.StringRes()
        private static final int title = org.wordpress.android.R.string.backup_download_progress_page_title;
        
        private ProgressToolbarState() {
            super();
        }
        
        @java.lang.Override()
        public int getTitle() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0016X\u0097D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/jetpack/backup/download/ToolbarState$CompleteToolbarState;", "Lorg/wordpress/android/ui/jetpack/backup/download/ToolbarState;", "()V", "title", "", "getTitle", "()I", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class CompleteToolbarState extends org.wordpress.android.ui.jetpack.backup.download.ToolbarState {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.jetpack.backup.download.ToolbarState.CompleteToolbarState INSTANCE = null;
        @androidx.annotation.StringRes()
        private static final int title = org.wordpress.android.R.string.backup_download_complete_page_title;
        
        private CompleteToolbarState() {
            super();
        }
        
        @java.lang.Override()
        public int getTitle() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0016X\u0097D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/jetpack/backup/download/ToolbarState$ErrorToolbarState;", "Lorg/wordpress/android/ui/jetpack/backup/download/ToolbarState;", "()V", "title", "", "getTitle", "()I", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ErrorToolbarState extends org.wordpress.android.ui.jetpack.backup.download.ToolbarState {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.jetpack.backup.download.ToolbarState.ErrorToolbarState INSTANCE = null;
        @androidx.annotation.StringRes()
        private static final int title = org.wordpress.android.R.string.backup_download_complete_failed_title;
        
        private ErrorToolbarState() {
            super();
        }
        
        @java.lang.Override()
        public int getTitle() {
            return 0;
        }
    }
}