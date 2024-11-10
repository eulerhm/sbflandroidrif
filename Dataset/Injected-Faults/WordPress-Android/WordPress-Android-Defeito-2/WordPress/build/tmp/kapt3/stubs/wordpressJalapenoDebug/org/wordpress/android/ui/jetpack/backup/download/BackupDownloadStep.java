package org.wordpress.android.ui.jetpack.backup.download;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0001\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\fB\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadStep;", "", "Lorg/wordpress/android/util/wizard/WizardStep;", "id", "", "(Ljava/lang/String;II)V", "getId", "()I", "DETAILS", "PROGRESS", "COMPLETE", "ERROR", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public enum BackupDownloadStep implements org.wordpress.android.util.wizard.WizardStep {
    /*public static final*/ DETAILS /* = new DETAILS(0) */,
    /*public static final*/ PROGRESS /* = new PROGRESS(0) */,
    /*public static final*/ COMPLETE /* = new COMPLETE(0) */,
    /*public static final*/ ERROR /* = new ERROR(0) */;
    private final int id = 0;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.jetpack.backup.download.BackupDownloadStep.Companion Companion = null;
    
    BackupDownloadStep(int id) {
    }
    
    public final int getId() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadStep$Companion;", "", "()V", "fromString", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadStep;", "input", "", "indexForErrorTransition", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.jetpack.backup.download.BackupDownloadStep fromString(@org.jetbrains.annotations.NotNull()
        java.lang.String input) {
            return null;
        }
        
        public final int indexForErrorTransition() {
            return 0;
        }
    }
}