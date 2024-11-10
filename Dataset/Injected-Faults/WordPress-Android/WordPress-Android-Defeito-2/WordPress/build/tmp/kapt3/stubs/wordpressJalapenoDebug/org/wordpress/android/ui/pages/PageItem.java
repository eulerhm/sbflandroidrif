package org.wordpress.android.ui.pages;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\n\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010B\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0004\u0011\u0012\u0013\u0014\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/pages/PageItem;", "", "type", "Lorg/wordpress/android/ui/pages/PageItem$Type;", "(Lorg/wordpress/android/ui/pages/PageItem$Type;)V", "getType", "()Lorg/wordpress/android/ui/pages/PageItem$Type;", "Action", "Divider", "DraftPage", "Empty", "Page", "ParentPage", "PublishedPage", "ScheduledPage", "TrashedPage", "Type", "Lorg/wordpress/android/ui/pages/PageItem$Page;", "Lorg/wordpress/android/ui/pages/PageItem$ParentPage;", "Lorg/wordpress/android/ui/pages/PageItem$Divider;", "Lorg/wordpress/android/ui/pages/PageItem$Empty;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class PageItem {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.pages.PageItem.Type type = null;
    
    private PageItem(org.wordpress.android.ui.pages.PageItem.Type type) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public org.wordpress.android.ui.pages.PageItem.Type getType() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\'\b&\u0018\u00002\u00020\u0001B\u00a9\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0010\u001a\u00020\u0005\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0007\u0012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\u0006\u0010\u001a\u001a\u00020\u0016\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\u001c\u001a\u00020\u0016\u00a2\u0006\u0002\u0010\u001dR\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u001a\u0010\u0015\u001a\u00020\u0016X\u0096\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u0016\u0010\u001b\u001a\u0004\u0018\u00010\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\'R\u0018\u0010\t\u001a\u0004\u0018\u00010\u0005X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010*\u001a\u0004\b(\u0010)R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0007X\u0096\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010%\"\u0004\b,\u0010-R\u001a\u0010\u0010\u001a\u00020\u0005X\u0096\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u0005X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010*\u001a\u0004\b4\u0010)R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010/R\u0014\u0010\u0018\u001a\u00020\u0019X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u00109R\u0014\u0010\u001a\u001a\u00020\u0016X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b:\u0010!R\u001a\u0010\u001c\u001a\u00020\u0016X\u0096\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b;\u0010!\"\u0004\b<\u0010#R\u0018\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010*\u001a\u0004\b=\u0010)R\u0014\u0010\u0017\u001a\u00020\u0016X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b>\u0010!R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b?\u0010%\u00a8\u0006@"}, d2 = {"Lorg/wordpress/android/ui/pages/PageItem$Page;", "Lorg/wordpress/android/ui/pages/PageItem;", "remoteId", "", "localId", "", "title", "", "subtitle", "icon", "date", "Ljava/util/Date;", "labels", "", "Lorg/wordpress/android/ui/utils/UiString;", "labelsColor", "indent", "imageUrl", "actions", "", "Lorg/wordpress/android/ui/pages/PageItem$Action;", "actionsEnabled", "", "tapActionEnabled", "progressBarUiState", "Lorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;", "showOverlay", "author", "showQuickStartFocusPoint", "(JILjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/Date;Ljava/util/List;Ljava/lang/Integer;ILjava/lang/String;Ljava/util/Set;ZZLorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;ZLjava/lang/String;Z)V", "getActions", "()Ljava/util/Set;", "getActionsEnabled", "()Z", "setActionsEnabled", "(Z)V", "getAuthor", "()Ljava/lang/String;", "getDate", "()Ljava/util/Date;", "getIcon", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getImageUrl", "setImageUrl", "(Ljava/lang/String;)V", "getIndent", "()I", "setIndent", "(I)V", "getLabels", "()Ljava/util/List;", "getLabelsColor", "getLocalId", "getProgressBarUiState", "()Lorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;", "getRemoteId", "()J", "getShowOverlay", "getShowQuickStartFocusPoint", "setShowQuickStartFocusPoint", "getSubtitle", "getTapActionEnabled", "getTitle", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class Page extends org.wordpress.android.ui.pages.PageItem {
        private final long remoteId = 0L;
        private final int localId = 0;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String title = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer subtitle = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer icon = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.Date date = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.utils.UiString> labels = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer labelsColor = null;
        private int indent;
        @org.jetbrains.annotations.Nullable()
        private java.lang.String imageUrl;
        @org.jetbrains.annotations.NotNull()
        private final java.util.Set<org.wordpress.android.ui.pages.PageItem.Action> actions = null;
        private boolean actionsEnabled;
        private final boolean tapActionEnabled = false;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.viewmodel.uistate.ProgressBarUiState progressBarUiState = null;
        private final boolean showOverlay = false;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String author = null;
        private boolean showQuickStartFocusPoint;
        
        public Page(long remoteId, int localId, @org.jetbrains.annotations.NotNull()
        java.lang.String title, @org.jetbrains.annotations.Nullable()
        java.lang.Integer subtitle, @org.jetbrains.annotations.Nullable()
        java.lang.Integer icon, @org.jetbrains.annotations.NotNull()
        java.util.Date date, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.utils.UiString> labels, @org.jetbrains.annotations.Nullable()
        @androidx.annotation.ColorRes()
        java.lang.Integer labelsColor, int indent, @org.jetbrains.annotations.Nullable()
        java.lang.String imageUrl, @org.jetbrains.annotations.NotNull()
        java.util.Set<? extends org.wordpress.android.ui.pages.PageItem.Action> actions, boolean actionsEnabled, boolean tapActionEnabled, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.viewmodel.uistate.ProgressBarUiState progressBarUiState, boolean showOverlay, @org.jetbrains.annotations.Nullable()
        java.lang.String author, boolean showQuickStartFocusPoint) {
            super(null);
        }
        
        public long getRemoteId() {
            return 0L;
        }
        
        public int getLocalId() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public java.lang.String getTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public java.lang.Integer getSubtitle() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public java.lang.Integer getIcon() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public java.util.Date getDate() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public java.util.List<org.wordpress.android.ui.utils.UiString> getLabels() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public java.lang.Integer getLabelsColor() {
            return null;
        }
        
        public int getIndent() {
            return 0;
        }
        
        public void setIndent(int p0) {
        }
        
        @org.jetbrains.annotations.Nullable()
        public java.lang.String getImageUrl() {
            return null;
        }
        
        public void setImageUrl(@org.jetbrains.annotations.Nullable()
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public java.util.Set<org.wordpress.android.ui.pages.PageItem.Action> getActions() {
            return null;
        }
        
        public boolean getActionsEnabled() {
            return false;
        }
        
        public void setActionsEnabled(boolean p0) {
        }
        
        public boolean getTapActionEnabled() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public org.wordpress.android.viewmodel.uistate.ProgressBarUiState getProgressBarUiState() {
            return null;
        }
        
        public boolean getShowOverlay() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public java.lang.String getAuthor() {
            return null;
        }
        
        public boolean getShowQuickStartFocusPoint() {
            return false;
        }
        
        public void setShowQuickStartFocusPoint(boolean p0) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b9\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u00ad\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0007\u0012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\u0006\u0010\u0019\u001a\u00020\u0016\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0016\u00a2\u0006\u0002\u0010\u001cJ\t\u0010>\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u000f\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u00c6\u0003J\t\u0010A\u001a\u00020\u0016H\u00c6\u0003J\t\u0010B\u001a\u00020\u0018H\u00c6\u0003J\t\u0010C\u001a\u00020\u0016H\u00c6\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\t\u0010E\u001a\u00020\u0016H\u00c6\u0003J\t\u0010F\u001a\u00020\u0005H\u00c6\u0003J\t\u0010G\u001a\u00020\u0007H\u00c6\u0003J\u0010\u0010H\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010(J\u0010\u0010I\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010(J\t\u0010J\u001a\u00020\u000bH\u00c6\u0003J\u000f\u0010K\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u00c6\u0003J\u0010\u0010L\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010(J\t\u0010M\u001a\u00020\u0005H\u00c6\u0003J\u00c4\u0001\u0010N\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\n\u001a\u00020\u000b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00072\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u00162\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\u001b\u001a\u00020\u0016H\u00c6\u0001\u00a2\u0006\u0002\u0010OJ\u0013\u0010P\u001a\u00020\u00162\b\u0010Q\u001a\u0004\u0018\u00010RH\u00d6\u0003J\t\u0010S\u001a\u00020\u0005H\u00d6\u0001J\t\u0010T\u001a\u00020\u0007H\u00d6\u0001R\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u001a\u0010\u0015\u001a\u00020\u0016X\u0096\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0016\u0010\u001a\u001a\u0004\u0018\u00010\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0018\u0010\t\u001a\u0004\u0018\u00010\u0005X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010)\u001a\u0004\b\'\u0010(R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0007X\u0096\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b*\u0010$\"\u0004\b+\u0010,R\u001a\u0010\u0010\u001a\u00020\u0005X\u0096\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u0005X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010)\u001a\u0004\b3\u0010(R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010.R\u0014\u0010\u0017\u001a\u00020\u0018X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u00108R\u0014\u0010\u0019\u001a\u00020\u0016X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u0010 R\u001a\u0010\u001b\u001a\u00020\u0016X\u0096\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b:\u0010 \"\u0004\b;\u0010\"R\u0018\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010)\u001a\u0004\b<\u0010(R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u0010$\u00a8\u0006U"}, d2 = {"Lorg/wordpress/android/ui/pages/PageItem$PublishedPage;", "Lorg/wordpress/android/ui/pages/PageItem$Page;", "remoteId", "", "localId", "", "title", "", "subtitle", "icon", "date", "Ljava/util/Date;", "labels", "", "Lorg/wordpress/android/ui/utils/UiString;", "labelsColor", "indent", "imageUrl", "actions", "", "Lorg/wordpress/android/ui/pages/PageItem$Action;", "actionsEnabled", "", "progressBarUiState", "Lorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;", "showOverlay", "author", "showQuickStartFocusPoint", "(JILjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/Date;Ljava/util/List;Ljava/lang/Integer;ILjava/lang/String;Ljava/util/Set;ZLorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;ZLjava/lang/String;Z)V", "getActions", "()Ljava/util/Set;", "getActionsEnabled", "()Z", "setActionsEnabled", "(Z)V", "getAuthor", "()Ljava/lang/String;", "getDate", "()Ljava/util/Date;", "getIcon", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getImageUrl", "setImageUrl", "(Ljava/lang/String;)V", "getIndent", "()I", "setIndent", "(I)V", "getLabels", "()Ljava/util/List;", "getLabelsColor", "getLocalId", "getProgressBarUiState", "()Lorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;", "getRemoteId", "()J", "getShowOverlay", "getShowQuickStartFocusPoint", "setShowQuickStartFocusPoint", "getSubtitle", "getTitle", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JILjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/Date;Ljava/util/List;Ljava/lang/Integer;ILjava/lang/String;Ljava/util/Set;ZLorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;ZLjava/lang/String;Z)Lorg/wordpress/android/ui/pages/PageItem$PublishedPage;", "equals", "other", "", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PublishedPage extends org.wordpress.android.ui.pages.PageItem.Page {
        private final long remoteId = 0L;
        private final int localId = 0;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String title = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer subtitle = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer icon = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.Date date = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.utils.UiString> labels = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer labelsColor = null;
        private int indent;
        @org.jetbrains.annotations.Nullable()
        private java.lang.String imageUrl;
        @org.jetbrains.annotations.NotNull()
        private final java.util.Set<org.wordpress.android.ui.pages.PageItem.Action> actions = null;
        private boolean actionsEnabled;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.viewmodel.uistate.ProgressBarUiState progressBarUiState = null;
        private final boolean showOverlay = false;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String author = null;
        private boolean showQuickStartFocusPoint;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.pages.PageItem.PublishedPage copy(long remoteId, int localId, @org.jetbrains.annotations.NotNull()
        java.lang.String title, @org.jetbrains.annotations.Nullable()
        java.lang.Integer subtitle, @org.jetbrains.annotations.Nullable()
        java.lang.Integer icon, @org.jetbrains.annotations.NotNull()
        java.util.Date date, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.utils.UiString> labels, @org.jetbrains.annotations.Nullable()
        java.lang.Integer labelsColor, int indent, @org.jetbrains.annotations.Nullable()
        java.lang.String imageUrl, @org.jetbrains.annotations.NotNull()
        java.util.Set<? extends org.wordpress.android.ui.pages.PageItem.Action> actions, boolean actionsEnabled, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.viewmodel.uistate.ProgressBarUiState progressBarUiState, boolean showOverlay, @org.jetbrains.annotations.Nullable()
        java.lang.String author, boolean showQuickStartFocusPoint) {
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
        
        public PublishedPage(long remoteId, int localId, @org.jetbrains.annotations.NotNull()
        java.lang.String title, @org.jetbrains.annotations.Nullable()
        java.lang.Integer subtitle, @org.jetbrains.annotations.Nullable()
        java.lang.Integer icon, @org.jetbrains.annotations.NotNull()
        java.util.Date date, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.utils.UiString> labels, @org.jetbrains.annotations.Nullable()
        java.lang.Integer labelsColor, int indent, @org.jetbrains.annotations.Nullable()
        java.lang.String imageUrl, @org.jetbrains.annotations.NotNull()
        java.util.Set<? extends org.wordpress.android.ui.pages.PageItem.Action> actions, boolean actionsEnabled, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.viewmodel.uistate.ProgressBarUiState progressBarUiState, boolean showOverlay, @org.jetbrains.annotations.Nullable()
        java.lang.String author, boolean showQuickStartFocusPoint) {
            super(0L, 0, null, null, null, null, null, null, 0, null, null, false, false, null, false, null, false);
        }
        
        public final long component1() {
            return 0L;
        }
        
        @java.lang.Override()
        public long getRemoteId() {
            return 0L;
        }
        
        public final int component2() {
            return 0;
        }
        
        @java.lang.Override()
        public int getLocalId() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String getTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.Integer getSubtitle() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component5() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.Integer getIcon() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Date component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.util.Date getDate() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.utils.UiString> component7() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.util.List<org.wordpress.android.ui.utils.UiString> getLabels() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component8() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.Integer getLabelsColor() {
            return null;
        }
        
        public final int component9() {
            return 0;
        }
        
        @java.lang.Override()
        public int getIndent() {
            return 0;
        }
        
        @java.lang.Override()
        public void setIndent(int p0) {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component10() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.String getImageUrl() {
            return null;
        }
        
        @java.lang.Override()
        public void setImageUrl(@org.jetbrains.annotations.Nullable()
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Set<org.wordpress.android.ui.pages.PageItem.Action> component11() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.util.Set<org.wordpress.android.ui.pages.PageItem.Action> getActions() {
            return null;
        }
        
        public final boolean component12() {
            return false;
        }
        
        @java.lang.Override()
        public boolean getActionsEnabled() {
            return false;
        }
        
        @java.lang.Override()
        public void setActionsEnabled(boolean p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.viewmodel.uistate.ProgressBarUiState component13() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.viewmodel.uistate.ProgressBarUiState getProgressBarUiState() {
            return null;
        }
        
        public final boolean component14() {
            return false;
        }
        
        @java.lang.Override()
        public boolean getShowOverlay() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component15() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.String getAuthor() {
            return null;
        }
        
        public final boolean component16() {
            return false;
        }
        
        @java.lang.Override()
        public boolean getShowQuickStartFocusPoint() {
            return false;
        }
        
        @java.lang.Override()
        public void setShowQuickStartFocusPoint(boolean p0) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b3\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u0097\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0007\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0014\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0014\u00a2\u0006\u0002\u0010\u001aJ\t\u00108\u001a\u00020\u0003H\u00c6\u0003J\t\u00109\u001a\u00020\u0014H\u00c6\u0003J\t\u0010:\u001a\u00020\u0016H\u00c6\u0003J\t\u0010;\u001a\u00020\u0014H\u00c6\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\t\u0010=\u001a\u00020\u0014H\u00c6\u0003J\t\u0010>\u001a\u00020\u0005H\u00c6\u0003J\t\u0010?\u001a\u00020\u0007H\u00c6\u0003J\u0010\u0010@\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010+J\t\u0010A\u001a\u00020\nH\u00c6\u0003J\u000f\u0010B\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u00c6\u0003J\u0010\u0010C\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010+J\u000b\u0010D\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u000f\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u00c6\u0003J\u00ae\u0001\u0010F\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00142\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\u0019\u001a\u00020\u0014H\u00c6\u0001\u00a2\u0006\u0002\u0010GJ\u0013\u0010H\u001a\u00020\u00142\b\u0010I\u001a\u0004\u0018\u00010JH\u00d6\u0003J\t\u0010K\u001a\u00020\u0005H\u00d6\u0001J\t\u0010L\u001a\u00020\u0007H\u00d6\u0001R\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\u0013\u001a\u00020\u0014X\u0096\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0016\u0010\u0018\u001a\u0004\u0018\u00010\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0007X\u0096\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\"\"\u0004\b&\u0010\'R\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u0005X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010,\u001a\u0004\b*\u0010+R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0014\u0010\u0015\u001a\u00020\u0016X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0014\u0010\u0017\u001a\u00020\u0014X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010\u001eR\u001a\u0010\u0019\u001a\u00020\u0014X\u0096\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u001e\"\u0004\b5\u0010 R\u0018\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010,\u001a\u0004\b6\u0010+R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u0010\"\u00a8\u0006M"}, d2 = {"Lorg/wordpress/android/ui/pages/PageItem$DraftPage;", "Lorg/wordpress/android/ui/pages/PageItem$Page;", "remoteId", "", "localId", "", "title", "", "subtitle", "date", "Ljava/util/Date;", "labels", "", "Lorg/wordpress/android/ui/utils/UiString;", "labelsColor", "imageUrl", "actions", "", "Lorg/wordpress/android/ui/pages/PageItem$Action;", "actionsEnabled", "", "progressBarUiState", "Lorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;", "showOverlay", "author", "showQuickStartFocusPoint", "(JILjava/lang/String;Ljava/lang/Integer;Ljava/util/Date;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/Set;ZLorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;ZLjava/lang/String;Z)V", "getActions", "()Ljava/util/Set;", "getActionsEnabled", "()Z", "setActionsEnabled", "(Z)V", "getAuthor", "()Ljava/lang/String;", "getDate", "()Ljava/util/Date;", "getImageUrl", "setImageUrl", "(Ljava/lang/String;)V", "getLabels", "()Ljava/util/List;", "getLabelsColor", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getLocalId", "()I", "getProgressBarUiState", "()Lorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;", "getRemoteId", "()J", "getShowOverlay", "getShowQuickStartFocusPoint", "setShowQuickStartFocusPoint", "getSubtitle", "getTitle", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JILjava/lang/String;Ljava/lang/Integer;Ljava/util/Date;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/Set;ZLorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;ZLjava/lang/String;Z)Lorg/wordpress/android/ui/pages/PageItem$DraftPage;", "equals", "other", "", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class DraftPage extends org.wordpress.android.ui.pages.PageItem.Page {
        private final long remoteId = 0L;
        private final int localId = 0;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String title = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer subtitle = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.Date date = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.utils.UiString> labels = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer labelsColor = null;
        @org.jetbrains.annotations.Nullable()
        private java.lang.String imageUrl;
        @org.jetbrains.annotations.NotNull()
        private final java.util.Set<org.wordpress.android.ui.pages.PageItem.Action> actions = null;
        private boolean actionsEnabled;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.viewmodel.uistate.ProgressBarUiState progressBarUiState = null;
        private final boolean showOverlay = false;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String author = null;
        private boolean showQuickStartFocusPoint;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.pages.PageItem.DraftPage copy(long remoteId, int localId, @org.jetbrains.annotations.NotNull()
        java.lang.String title, @org.jetbrains.annotations.Nullable()
        java.lang.Integer subtitle, @org.jetbrains.annotations.NotNull()
        java.util.Date date, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.utils.UiString> labels, @org.jetbrains.annotations.Nullable()
        java.lang.Integer labelsColor, @org.jetbrains.annotations.Nullable()
        java.lang.String imageUrl, @org.jetbrains.annotations.NotNull()
        java.util.Set<? extends org.wordpress.android.ui.pages.PageItem.Action> actions, boolean actionsEnabled, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.viewmodel.uistate.ProgressBarUiState progressBarUiState, boolean showOverlay, @org.jetbrains.annotations.Nullable()
        java.lang.String author, boolean showQuickStartFocusPoint) {
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
        
        public DraftPage(long remoteId, int localId, @org.jetbrains.annotations.NotNull()
        java.lang.String title, @org.jetbrains.annotations.Nullable()
        java.lang.Integer subtitle, @org.jetbrains.annotations.NotNull()
        java.util.Date date, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.utils.UiString> labels, @org.jetbrains.annotations.Nullable()
        java.lang.Integer labelsColor, @org.jetbrains.annotations.Nullable()
        java.lang.String imageUrl, @org.jetbrains.annotations.NotNull()
        java.util.Set<? extends org.wordpress.android.ui.pages.PageItem.Action> actions, boolean actionsEnabled, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.viewmodel.uistate.ProgressBarUiState progressBarUiState, boolean showOverlay, @org.jetbrains.annotations.Nullable()
        java.lang.String author, boolean showQuickStartFocusPoint) {
            super(0L, 0, null, null, null, null, null, null, 0, null, null, false, false, null, false, null, false);
        }
        
        public final long component1() {
            return 0L;
        }
        
        @java.lang.Override()
        public long getRemoteId() {
            return 0L;
        }
        
        public final int component2() {
            return 0;
        }
        
        @java.lang.Override()
        public int getLocalId() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String getTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.Integer getSubtitle() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Date component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.util.Date getDate() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.utils.UiString> component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.util.List<org.wordpress.android.ui.utils.UiString> getLabels() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component7() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.Integer getLabelsColor() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component8() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.String getImageUrl() {
            return null;
        }
        
        @java.lang.Override()
        public void setImageUrl(@org.jetbrains.annotations.Nullable()
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Set<org.wordpress.android.ui.pages.PageItem.Action> component9() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.util.Set<org.wordpress.android.ui.pages.PageItem.Action> getActions() {
            return null;
        }
        
        public final boolean component10() {
            return false;
        }
        
        @java.lang.Override()
        public boolean getActionsEnabled() {
            return false;
        }
        
        @java.lang.Override()
        public void setActionsEnabled(boolean p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.viewmodel.uistate.ProgressBarUiState component11() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.viewmodel.uistate.ProgressBarUiState getProgressBarUiState() {
            return null;
        }
        
        public final boolean component12() {
            return false;
        }
        
        @java.lang.Override()
        public boolean getShowOverlay() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component13() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.String getAuthor() {
            return null;
        }
        
        public final boolean component14() {
            return false;
        }
        
        @java.lang.Override()
        public boolean getShowQuickStartFocusPoint() {
            return false;
        }
        
        @java.lang.Override()
        public void setShowQuickStartFocusPoint(boolean p0) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b3\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u0097\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0007\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0014\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0014\u00a2\u0006\u0002\u0010\u001aJ\t\u00108\u001a\u00020\u0003H\u00c6\u0003J\t\u00109\u001a\u00020\u0014H\u00c6\u0003J\t\u0010:\u001a\u00020\u0016H\u00c6\u0003J\t\u0010;\u001a\u00020\u0014H\u00c6\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\t\u0010=\u001a\u00020\u0014H\u00c6\u0003J\t\u0010>\u001a\u00020\u0005H\u00c6\u0003J\t\u0010?\u001a\u00020\u0007H\u00c6\u0003J\u0010\u0010@\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010+J\t\u0010A\u001a\u00020\nH\u00c6\u0003J\u000f\u0010B\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u00c6\u0003J\u0010\u0010C\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010+J\u000b\u0010D\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u000f\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u00c6\u0003J\u00ae\u0001\u0010F\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00142\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\u0019\u001a\u00020\u0014H\u00c6\u0001\u00a2\u0006\u0002\u0010GJ\u0013\u0010H\u001a\u00020\u00142\b\u0010I\u001a\u0004\u0018\u00010JH\u00d6\u0003J\t\u0010K\u001a\u00020\u0005H\u00d6\u0001J\t\u0010L\u001a\u00020\u0007H\u00d6\u0001R\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\u0013\u001a\u00020\u0014X\u0096\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0016\u0010\u0018\u001a\u0004\u0018\u00010\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0007X\u0096\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\"\"\u0004\b&\u0010\'R\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u0005X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010,\u001a\u0004\b*\u0010+R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0014\u0010\u0015\u001a\u00020\u0016X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0014\u0010\u0017\u001a\u00020\u0014X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010\u001eR\u001a\u0010\u0019\u001a\u00020\u0014X\u0096\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u001e\"\u0004\b5\u0010 R\u0018\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010,\u001a\u0004\b6\u0010+R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u0010\"\u00a8\u0006M"}, d2 = {"Lorg/wordpress/android/ui/pages/PageItem$ScheduledPage;", "Lorg/wordpress/android/ui/pages/PageItem$Page;", "remoteId", "", "localId", "", "title", "", "subtitle", "date", "Ljava/util/Date;", "labels", "", "Lorg/wordpress/android/ui/utils/UiString;", "labelsColor", "imageUrl", "actions", "", "Lorg/wordpress/android/ui/pages/PageItem$Action;", "actionsEnabled", "", "progressBarUiState", "Lorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;", "showOverlay", "author", "showQuickStartFocusPoint", "(JILjava/lang/String;Ljava/lang/Integer;Ljava/util/Date;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/Set;ZLorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;ZLjava/lang/String;Z)V", "getActions", "()Ljava/util/Set;", "getActionsEnabled", "()Z", "setActionsEnabled", "(Z)V", "getAuthor", "()Ljava/lang/String;", "getDate", "()Ljava/util/Date;", "getImageUrl", "setImageUrl", "(Ljava/lang/String;)V", "getLabels", "()Ljava/util/List;", "getLabelsColor", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getLocalId", "()I", "getProgressBarUiState", "()Lorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;", "getRemoteId", "()J", "getShowOverlay", "getShowQuickStartFocusPoint", "setShowQuickStartFocusPoint", "getSubtitle", "getTitle", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JILjava/lang/String;Ljava/lang/Integer;Ljava/util/Date;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/Set;ZLorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;ZLjava/lang/String;Z)Lorg/wordpress/android/ui/pages/PageItem$ScheduledPage;", "equals", "other", "", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ScheduledPage extends org.wordpress.android.ui.pages.PageItem.Page {
        private final long remoteId = 0L;
        private final int localId = 0;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String title = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer subtitle = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.Date date = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.utils.UiString> labels = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer labelsColor = null;
        @org.jetbrains.annotations.Nullable()
        private java.lang.String imageUrl;
        @org.jetbrains.annotations.NotNull()
        private final java.util.Set<org.wordpress.android.ui.pages.PageItem.Action> actions = null;
        private boolean actionsEnabled;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.viewmodel.uistate.ProgressBarUiState progressBarUiState = null;
        private final boolean showOverlay = false;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String author = null;
        private boolean showQuickStartFocusPoint;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.pages.PageItem.ScheduledPage copy(long remoteId, int localId, @org.jetbrains.annotations.NotNull()
        java.lang.String title, @org.jetbrains.annotations.Nullable()
        java.lang.Integer subtitle, @org.jetbrains.annotations.NotNull()
        java.util.Date date, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.utils.UiString> labels, @org.jetbrains.annotations.Nullable()
        java.lang.Integer labelsColor, @org.jetbrains.annotations.Nullable()
        java.lang.String imageUrl, @org.jetbrains.annotations.NotNull()
        java.util.Set<? extends org.wordpress.android.ui.pages.PageItem.Action> actions, boolean actionsEnabled, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.viewmodel.uistate.ProgressBarUiState progressBarUiState, boolean showOverlay, @org.jetbrains.annotations.Nullable()
        java.lang.String author, boolean showQuickStartFocusPoint) {
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
        
        public ScheduledPage(long remoteId, int localId, @org.jetbrains.annotations.NotNull()
        java.lang.String title, @org.jetbrains.annotations.Nullable()
        java.lang.Integer subtitle, @org.jetbrains.annotations.NotNull()
        java.util.Date date, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.utils.UiString> labels, @org.jetbrains.annotations.Nullable()
        java.lang.Integer labelsColor, @org.jetbrains.annotations.Nullable()
        java.lang.String imageUrl, @org.jetbrains.annotations.NotNull()
        java.util.Set<? extends org.wordpress.android.ui.pages.PageItem.Action> actions, boolean actionsEnabled, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.viewmodel.uistate.ProgressBarUiState progressBarUiState, boolean showOverlay, @org.jetbrains.annotations.Nullable()
        java.lang.String author, boolean showQuickStartFocusPoint) {
            super(0L, 0, null, null, null, null, null, null, 0, null, null, false, false, null, false, null, false);
        }
        
        public final long component1() {
            return 0L;
        }
        
        @java.lang.Override()
        public long getRemoteId() {
            return 0L;
        }
        
        public final int component2() {
            return 0;
        }
        
        @java.lang.Override()
        public int getLocalId() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String getTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.Integer getSubtitle() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Date component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.util.Date getDate() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.utils.UiString> component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.util.List<org.wordpress.android.ui.utils.UiString> getLabels() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component7() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.Integer getLabelsColor() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component8() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.String getImageUrl() {
            return null;
        }
        
        @java.lang.Override()
        public void setImageUrl(@org.jetbrains.annotations.Nullable()
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Set<org.wordpress.android.ui.pages.PageItem.Action> component9() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.util.Set<org.wordpress.android.ui.pages.PageItem.Action> getActions() {
            return null;
        }
        
        public final boolean component10() {
            return false;
        }
        
        @java.lang.Override()
        public boolean getActionsEnabled() {
            return false;
        }
        
        @java.lang.Override()
        public void setActionsEnabled(boolean p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.viewmodel.uistate.ProgressBarUiState component11() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.viewmodel.uistate.ProgressBarUiState getProgressBarUiState() {
            return null;
        }
        
        public final boolean component12() {
            return false;
        }
        
        @java.lang.Override()
        public boolean getShowOverlay() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component13() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.String getAuthor() {
            return null;
        }
        
        public final boolean component14() {
            return false;
        }
        
        @java.lang.Override()
        public boolean getShowQuickStartFocusPoint() {
            return false;
        }
        
        @java.lang.Override()
        public void setShowQuickStartFocusPoint(boolean p0) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b3\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u0097\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0007\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0014\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0014\u00a2\u0006\u0002\u0010\u001aJ\t\u00108\u001a\u00020\u0003H\u00c6\u0003J\t\u00109\u001a\u00020\u0014H\u00c6\u0003J\t\u0010:\u001a\u00020\u0016H\u00c6\u0003J\t\u0010;\u001a\u00020\u0014H\u00c6\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\t\u0010=\u001a\u00020\u0014H\u00c6\u0003J\t\u0010>\u001a\u00020\u0005H\u00c6\u0003J\t\u0010?\u001a\u00020\u0007H\u00c6\u0003J\u0010\u0010@\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010+J\t\u0010A\u001a\u00020\nH\u00c6\u0003J\u000f\u0010B\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u00c6\u0003J\u0010\u0010C\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010+J\u000b\u0010D\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u000f\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u00c6\u0003J\u00ae\u0001\u0010F\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00142\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\u0019\u001a\u00020\u0014H\u00c6\u0001\u00a2\u0006\u0002\u0010GJ\u0013\u0010H\u001a\u00020\u00142\b\u0010I\u001a\u0004\u0018\u00010JH\u00d6\u0003J\t\u0010K\u001a\u00020\u0005H\u00d6\u0001J\t\u0010L\u001a\u00020\u0007H\u00d6\u0001R\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\u0013\u001a\u00020\u0014X\u0096\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0016\u0010\u0018\u001a\u0004\u0018\u00010\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0007X\u0096\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\"\"\u0004\b&\u0010\'R\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u0005X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010,\u001a\u0004\b*\u0010+R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0014\u0010\u0015\u001a\u00020\u0016X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0014\u0010\u0017\u001a\u00020\u0014X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010\u001eR\u001a\u0010\u0019\u001a\u00020\u0014X\u0096\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u001e\"\u0004\b5\u0010 R\u0018\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010,\u001a\u0004\b6\u0010+R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u0010\"\u00a8\u0006M"}, d2 = {"Lorg/wordpress/android/ui/pages/PageItem$TrashedPage;", "Lorg/wordpress/android/ui/pages/PageItem$Page;", "remoteId", "", "localId", "", "title", "", "subtitle", "date", "Ljava/util/Date;", "labels", "", "Lorg/wordpress/android/ui/utils/UiString;", "labelsColor", "imageUrl", "actions", "", "Lorg/wordpress/android/ui/pages/PageItem$Action;", "actionsEnabled", "", "progressBarUiState", "Lorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;", "showOverlay", "author", "showQuickStartFocusPoint", "(JILjava/lang/String;Ljava/lang/Integer;Ljava/util/Date;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/Set;ZLorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;ZLjava/lang/String;Z)V", "getActions", "()Ljava/util/Set;", "getActionsEnabled", "()Z", "setActionsEnabled", "(Z)V", "getAuthor", "()Ljava/lang/String;", "getDate", "()Ljava/util/Date;", "getImageUrl", "setImageUrl", "(Ljava/lang/String;)V", "getLabels", "()Ljava/util/List;", "getLabelsColor", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getLocalId", "()I", "getProgressBarUiState", "()Lorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;", "getRemoteId", "()J", "getShowOverlay", "getShowQuickStartFocusPoint", "setShowQuickStartFocusPoint", "getSubtitle", "getTitle", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JILjava/lang/String;Ljava/lang/Integer;Ljava/util/Date;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/Set;ZLorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;ZLjava/lang/String;Z)Lorg/wordpress/android/ui/pages/PageItem$TrashedPage;", "equals", "other", "", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class TrashedPage extends org.wordpress.android.ui.pages.PageItem.Page {
        private final long remoteId = 0L;
        private final int localId = 0;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String title = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer subtitle = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.Date date = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.utils.UiString> labels = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer labelsColor = null;
        @org.jetbrains.annotations.Nullable()
        private java.lang.String imageUrl;
        @org.jetbrains.annotations.NotNull()
        private final java.util.Set<org.wordpress.android.ui.pages.PageItem.Action> actions = null;
        private boolean actionsEnabled;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.viewmodel.uistate.ProgressBarUiState progressBarUiState = null;
        private final boolean showOverlay = false;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String author = null;
        private boolean showQuickStartFocusPoint;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.pages.PageItem.TrashedPage copy(long remoteId, int localId, @org.jetbrains.annotations.NotNull()
        java.lang.String title, @org.jetbrains.annotations.Nullable()
        java.lang.Integer subtitle, @org.jetbrains.annotations.NotNull()
        java.util.Date date, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.utils.UiString> labels, @org.jetbrains.annotations.Nullable()
        java.lang.Integer labelsColor, @org.jetbrains.annotations.Nullable()
        java.lang.String imageUrl, @org.jetbrains.annotations.NotNull()
        java.util.Set<? extends org.wordpress.android.ui.pages.PageItem.Action> actions, boolean actionsEnabled, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.viewmodel.uistate.ProgressBarUiState progressBarUiState, boolean showOverlay, @org.jetbrains.annotations.Nullable()
        java.lang.String author, boolean showQuickStartFocusPoint) {
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
        
        public TrashedPage(long remoteId, int localId, @org.jetbrains.annotations.NotNull()
        java.lang.String title, @org.jetbrains.annotations.Nullable()
        java.lang.Integer subtitle, @org.jetbrains.annotations.NotNull()
        java.util.Date date, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.utils.UiString> labels, @org.jetbrains.annotations.Nullable()
        java.lang.Integer labelsColor, @org.jetbrains.annotations.Nullable()
        java.lang.String imageUrl, @org.jetbrains.annotations.NotNull()
        java.util.Set<? extends org.wordpress.android.ui.pages.PageItem.Action> actions, boolean actionsEnabled, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.viewmodel.uistate.ProgressBarUiState progressBarUiState, boolean showOverlay, @org.jetbrains.annotations.Nullable()
        java.lang.String author, boolean showQuickStartFocusPoint) {
            super(0L, 0, null, null, null, null, null, null, 0, null, null, false, false, null, false, null, false);
        }
        
        public final long component1() {
            return 0L;
        }
        
        @java.lang.Override()
        public long getRemoteId() {
            return 0L;
        }
        
        public final int component2() {
            return 0;
        }
        
        @java.lang.Override()
        public int getLocalId() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String getTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.Integer getSubtitle() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Date component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.util.Date getDate() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.utils.UiString> component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.util.List<org.wordpress.android.ui.utils.UiString> getLabels() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component7() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.Integer getLabelsColor() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component8() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.String getImageUrl() {
            return null;
        }
        
        @java.lang.Override()
        public void setImageUrl(@org.jetbrains.annotations.Nullable()
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Set<org.wordpress.android.ui.pages.PageItem.Action> component9() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.util.Set<org.wordpress.android.ui.pages.PageItem.Action> getActions() {
            return null;
        }
        
        public final boolean component10() {
            return false;
        }
        
        @java.lang.Override()
        public boolean getActionsEnabled() {
            return false;
        }
        
        @java.lang.Override()
        public void setActionsEnabled(boolean p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.viewmodel.uistate.ProgressBarUiState component11() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.viewmodel.uistate.ProgressBarUiState getProgressBarUiState() {
            return null;
        }
        
        public final boolean component12() {
            return false;
        }
        
        @java.lang.Override()
        public boolean getShowOverlay() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component13() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.String getAuthor() {
            return null;
        }
        
        public final boolean component14() {
            return false;
        }
        
        @java.lang.Override()
        public boolean getShowQuickStartFocusPoint() {
            return false;
        }
        
        @java.lang.Override()
        public void setShowQuickStartFocusPoint(boolean p0) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\tH\u00c6\u0003J1\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u00c6\u0001J\u0013\u0010\u0019\u001a\u00020\u00072\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001J\t\u0010\u001e\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\u001f"}, d2 = {"Lorg/wordpress/android/ui/pages/PageItem$ParentPage;", "Lorg/wordpress/android/ui/pages/PageItem;", "id", "", "title", "", "isSelected", "", "type", "Lorg/wordpress/android/ui/pages/PageItem$Type;", "(JLjava/lang/String;ZLorg/wordpress/android/ui/pages/PageItem$Type;)V", "getId", "()J", "()Z", "setSelected", "(Z)V", "getTitle", "()Ljava/lang/String;", "getType", "()Lorg/wordpress/android/ui/pages/PageItem$Type;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ParentPage extends org.wordpress.android.ui.pages.PageItem {
        private final long id = 0L;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String title = null;
        private boolean isSelected;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.pages.PageItem.Type type = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.pages.PageItem.ParentPage copy(long id, @org.jetbrains.annotations.NotNull()
        java.lang.String title, boolean isSelected, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.pages.PageItem.Type type) {
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
        
        public ParentPage(long id, @org.jetbrains.annotations.NotNull()
        java.lang.String title, boolean isSelected, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.pages.PageItem.Type type) {
            super(null);
        }
        
        public final long component1() {
            return 0L;
        }
        
        public final long getId() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTitle() {
            return null;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean isSelected() {
            return false;
        }
        
        public final void setSelected(boolean p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.pages.PageItem.Type component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.ui.pages.PageItem.Type getType() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/pages/PageItem$Divider;", "Lorg/wordpress/android/ui/pages/PageItem;", "title", "", "(Ljava/lang/String;)V", "getTitle", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Divider extends org.wordpress.android.ui.pages.PageItem {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String title = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.pages.PageItem.Divider copy(@org.jetbrains.annotations.NotNull()
        java.lang.String title) {
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
        
        public Divider() {
            super(null);
        }
        
        public Divider(@org.jetbrains.annotations.NotNull()
        java.lang.String title) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTitle() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\bJ\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0005H\u00c6\u0003J1\u0010\u0010\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\tR\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/pages/PageItem$Empty;", "Lorg/wordpress/android/ui/pages/PageItem;", "textResource", "", "isSearching", "", "isButtonVisible", "isImageVisible", "(IZZZ)V", "()Z", "getTextResource", "()I", "component1", "component2", "component3", "component4", "copy", "equals", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Empty extends org.wordpress.android.ui.pages.PageItem {
        private final int textResource = 0;
        private final boolean isSearching = false;
        private final boolean isButtonVisible = false;
        private final boolean isImageVisible = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.pages.PageItem.Empty copy(@androidx.annotation.StringRes()
        int textResource, boolean isSearching, boolean isButtonVisible, boolean isImageVisible) {
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
        
        public Empty() {
            super(null);
        }
        
        public Empty(@androidx.annotation.StringRes()
        int textResource, boolean isSearching, boolean isButtonVisible, boolean isImageVisible) {
            super(null);
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getTextResource() {
            return 0;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean isSearching() {
            return false;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean isButtonVisible() {
            return false;
        }
        
        public final boolean component4() {
            return false;
        }
        
        public final boolean isImageVisible() {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/pages/PageItem$Type;", "", "viewType", "", "(Ljava/lang/String;II)V", "getViewType", "()I", "PAGE", "DIVIDER", "EMPTY", "PARENT", "TOP_LEVEL_PARENT", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum Type {
        /*public static final*/ PAGE /* = new PAGE(0) */,
        /*public static final*/ DIVIDER /* = new DIVIDER(0) */,
        /*public static final*/ EMPTY /* = new EMPTY(0) */,
        /*public static final*/ PARENT /* = new PARENT(0) */,
        /*public static final*/ TOP_LEVEL_PARENT /* = new TOP_LEVEL_PARENT(0) */;
        private final int viewType = 0;
        
        Type(int viewType) {
        }
        
        public final int getViewType() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\b\u0086\u0001\u0018\u0000 \u00122\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0012B\u0011\b\u0002\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/pages/PageItem$Action;", "", "itemId", "", "(Ljava/lang/String;II)V", "getItemId", "()I", "VIEW_PAGE", "CANCEL_AUTO_UPLOAD", "SET_PARENT", "SET_AS_HOMEPAGE", "SET_AS_POSTS_PAGE", "COPY", "COPY_LINK", "PUBLISH_NOW", "MOVE_TO_DRAFT", "DELETE_PERMANENTLY", "MOVE_TO_TRASH", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum Action {
        /*public static final*/ VIEW_PAGE /* = new VIEW_PAGE(0) */,
        /*public static final*/ CANCEL_AUTO_UPLOAD /* = new CANCEL_AUTO_UPLOAD(0) */,
        /*public static final*/ SET_PARENT /* = new SET_PARENT(0) */,
        /*public static final*/ SET_AS_HOMEPAGE /* = new SET_AS_HOMEPAGE(0) */,
        /*public static final*/ SET_AS_POSTS_PAGE /* = new SET_AS_POSTS_PAGE(0) */,
        /*public static final*/ COPY /* = new COPY(0) */,
        /*public static final*/ COPY_LINK /* = new COPY_LINK(0) */,
        /*public static final*/ PUBLISH_NOW /* = new PUBLISH_NOW(0) */,
        /*public static final*/ MOVE_TO_DRAFT /* = new MOVE_TO_DRAFT(0) */,
        /*public static final*/ DELETE_PERMANENTLY /* = new DELETE_PERMANENTLY(0) */,
        /*public static final*/ MOVE_TO_TRASH /* = new MOVE_TO_TRASH(0) */;
        private final int itemId = 0;
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.pages.PageItem.Action.Companion Companion = null;
        
        Action(@androidx.annotation.IdRes()
        int itemId) {
        }
        
        public final int getItemId() {
            return 0;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/pages/PageItem$Action$Companion;", "", "()V", "fromItemId", "Lorg/wordpress/android/ui/pages/PageItem$Action;", "itemId", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Companion {
            
            private Companion() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.pages.PageItem.Action fromItemId(int itemId) {
                return null;
            }
        }
    }
}