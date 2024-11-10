package org.wordpress.android.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 2, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001\u001a\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u0006\u0010\u0005\u001a\u00020\u0006\u001a\b\u0010\u0007\u001a\u00020\bH\u0002\u00a8\u0006\t"}, d2 = {"enqueuePeriodicUploadWorkRequestForAllSites", "Lkotlin/Pair;", "Landroidx/work/WorkRequest;", "Landroidx/work/Operation;", "enqueueUploadWorkRequestForSite", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "getUploadConstraints", "Landroidx/work/Constraints;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class UploadWorkerKt {
    
    private static final androidx.work.Constraints getUploadConstraints() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final kotlin.Pair<androidx.work.WorkRequest, androidx.work.Operation> enqueueUploadWorkRequestForSite(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final kotlin.Pair<androidx.work.WorkRequest, androidx.work.Operation> enqueuePeriodicUploadWorkRequestForAllSites() {
        return null;
    }
}