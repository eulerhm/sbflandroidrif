package fr.free.nrw.commons.upload.worker;

import java.lang.System;

/**
 * Helper class for all the one time work requests
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2 = {"Lfr/free/nrw/commons/upload/worker/WorkRequestHelper;", "", "()V", "Companion", "app-commons-v4.2.1-master_prodDebug"})
public final class WorkRequestHelper {
    @org.jetbrains.annotations.NotNull
    public static final fr.free.nrw.commons.upload.worker.WorkRequestHelper.Companion Companion = null;
    
    public WorkRequestHelper() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u00a8\u0006\t"}, d2 = {"Lfr/free/nrw/commons/upload/worker/WorkRequestHelper$Companion;", "", "()V", "makeOneTimeWorkRequest", "", "context", "Landroid/content/Context;", "existingWorkPolicy", "Landroidx/work/ExistingWorkPolicy;", "app-commons-v4.2.1-master_prodDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final void makeOneTimeWorkRequest(@org.jetbrains.annotations.NotNull
        android.content.Context context, @org.jetbrains.annotations.NotNull
        androidx.work.ExistingWorkPolicy existingWorkPolicy) {
        }
    }
}