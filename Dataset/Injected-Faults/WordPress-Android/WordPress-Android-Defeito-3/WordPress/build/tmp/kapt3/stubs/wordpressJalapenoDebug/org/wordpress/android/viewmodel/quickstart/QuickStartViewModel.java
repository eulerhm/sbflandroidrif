package org.wordpress.android.viewmodel.quickstart;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\nJ\b\u0010\u001a\u001a\u00020\u0016H\u0002J\u0006\u0010\u001b\u001a\u00020\u0016J\u000e\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u0010R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/viewmodel/quickstart/QuickStartViewModel;", "Landroidx/lifecycle/ViewModel;", "quickStartStore", "Lorg/wordpress/android/fluxc/store/QuickStartStore;", "(Lorg/wordpress/android/fluxc/store/QuickStartStore;)V", "_quickStartTaskStateStates", "Landroidx/lifecycle/MutableLiveData;", "", "Lorg/wordpress/android/ui/quickstart/QuickStartTaskState;", "isStarted", "", "quickStartTaskStateStates", "Landroidx/lifecycle/LiveData;", "getQuickStartTaskStateStates", "()Landroidx/lifecycle/LiveData;", "siteId", "", "getSiteId", "()J", "setSiteId", "(J)V", "completeTask", "", "task", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;", "isCompleted", "refreshTaskStatus", "skipAllTasks", "start", "org.wordpress.android_wordpressJalapenoDebug"})
public final class QuickStartViewModel extends androidx.lifecycle.ViewModel {
    private final org.wordpress.android.fluxc.store.QuickStartStore quickStartStore = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<org.wordpress.android.ui.quickstart.QuickStartTaskState>> _quickStartTaskStateStates = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.ui.quickstart.QuickStartTaskState>> quickStartTaskStateStates = null;
    private boolean isStarted = false;
    private long siteId = 0L;
    
    @javax.inject.Inject()
    public QuickStartViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore quickStartStore) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.ui.quickstart.QuickStartTaskState>> getQuickStartTaskStateStates() {
        return null;
    }
    
    public final long getSiteId() {
        return 0L;
    }
    
    public final void setSiteId(long p0) {
    }
    
    public final void start(long siteId) {
    }
    
    private final void refreshTaskStatus() {
    }
    
    public final void completeTask(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task, boolean isCompleted) {
    }
    
    public final void skipAllTasks() {
    }
}