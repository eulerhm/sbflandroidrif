package org.wordpress.android.ui.quickstart;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u001d2\u00020\u0001:\u0003\u001d\u001e\u001fB%\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\u0011\u001a\u00020\u00072\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u0016\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u001e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u0014R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\n\u0082\u0001\u0002 !\u00a8\u0006\""}, d2 = {"Lorg/wordpress/android/ui/quickstart/QuickStartType;", "", "label", "", "trackingLabel", "tasks", "", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getLabel", "()Ljava/lang/String;", "taskTypes", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTaskType;", "getTaskTypes", "()Ljava/util/List;", "getTasks", "getTrackingLabel", "getTaskFromString", "string", "isEveryQuickStartTaskDone", "", "quickStartStore", "Lorg/wordpress/android/fluxc/store/QuickStartStore;", "siteLocalId", "", "isQuickStartInProgress", "startQuickStart", "", "isSiteTitleTaskCompleted", "Companion", "ExistingSiteQuickStartType", "NewSiteQuickStartType", "Lorg/wordpress/android/ui/quickstart/QuickStartType$NewSiteQuickStartType;", "Lorg/wordpress/android/ui/quickstart/QuickStartType$ExistingSiteQuickStartType;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class QuickStartType {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String label = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String trackingLabel = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask> tasks = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType> taskTypes = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.quickstart.QuickStartType.Companion Companion = null;
    private static final java.lang.String NEW_SITE = "new-site";
    private static final java.lang.String EXISTING_SITE = "existing-site";
    private static final java.lang.String NEW_SITE_TRACKING_LABEL = "new_site";
    private static final java.lang.String EXISTING_SITE_TRACKING_LABEL = "existing_site";
    
    private QuickStartType(java.lang.String label, java.lang.String trackingLabel, java.util.List<? extends org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask> tasks) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLabel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTrackingLabel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask> getTasks() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType> getTaskTypes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask getTaskFromString(@org.jetbrains.annotations.Nullable()
    java.lang.String string) {
        return null;
    }
    
    public final void startQuickStart(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore quickStartStore, long siteLocalId, boolean isSiteTitleTaskCompleted) {
    }
    
    public final boolean isQuickStartInProgress(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore quickStartStore, long siteLocalId) {
        return false;
    }
    
    public final boolean isEveryQuickStartTaskDone(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore quickStartStore, long siteLocalId) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/quickstart/QuickStartType$NewSiteQuickStartType;", "Lorg/wordpress/android/ui/quickstart/QuickStartType;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class NewSiteQuickStartType extends org.wordpress.android.ui.quickstart.QuickStartType {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.quickstart.QuickStartType.NewSiteQuickStartType INSTANCE = null;
        
        private NewSiteQuickStartType() {
            super(null, null, null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/quickstart/QuickStartType$ExistingSiteQuickStartType;", "Lorg/wordpress/android/ui/quickstart/QuickStartType;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ExistingSiteQuickStartType extends org.wordpress.android.ui.quickstart.QuickStartType {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.quickstart.QuickStartType.ExistingSiteQuickStartType INSTANCE = null;
        
        private ExistingSiteQuickStartType() {
            super(null, null, null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/quickstart/QuickStartType$Companion;", "", "()V", "EXISTING_SITE", "", "EXISTING_SITE_TRACKING_LABEL", "NEW_SITE", "NEW_SITE_TRACKING_LABEL", "fromLabel", "Lorg/wordpress/android/ui/quickstart/QuickStartType;", "label", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.quickstart.QuickStartType fromLabel(@org.jetbrains.annotations.NotNull()
        java.lang.String label) {
            return null;
        }
    }
}