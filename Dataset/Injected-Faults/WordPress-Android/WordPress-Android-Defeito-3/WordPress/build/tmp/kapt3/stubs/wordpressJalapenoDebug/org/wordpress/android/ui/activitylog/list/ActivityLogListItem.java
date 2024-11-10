package org.wordpress.android.ui.activitylog.list;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u000b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013B\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0006\u0014\u0015\u0016\u0017\u0018\u0019\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem;", "", "type", "Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$ViewType;", "(Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$ViewType;)V", "getType", "()Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$ViewType;", "longId", "", "Event", "Footer", "Header", "IActionableItem", "Icon", "Loading", "Notice", "Progress", "SecondaryAction", "Status", "ViewType", "Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$Event;", "Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$Progress;", "Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$Header;", "Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$Footer;", "Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$Loading;", "Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$Notice;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class ActivityLogListItem {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.activitylog.list.ActivityLogListItem.ViewType type = null;
    
    private ActivityLogListItem(org.wordpress.android.ui.activitylog.list.ActivityLogListItem.ViewType type) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.activitylog.list.ActivityLogListItem.ViewType getType() {
        return null;
    }
    
    public long longId() {
        return 0L;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$IActionableItem;", "", "isButtonVisible", "", "()Z", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract interface IActionableItem {
        
        public abstract boolean isButtonVisible();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\bBc\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\b\u0010\r\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000f\u001a\u00020\u0006\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0006\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0016J\t\u0010)\u001a\u00020\nH\u00c6\u0003J\t\u0010*\u001a\u00020\u0015H\u00c6\u0003J\t\u0010+\u001a\u00020\u0006H\u00c6\u0003J\t\u0010,\u001a\u00020\nH\u00c6\u0003J\t\u0010-\u001a\u00020\nH\u00c6\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\nH\u00c2\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\nH\u00c2\u0003J\t\u00100\u001a\u00020\u0006H\u00c6\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\nH\u00c6\u0003J\t\u00102\u001a\u00020\u0012H\u00c6\u0003J\t\u00103\u001a\u00020\u0006H\u00c6\u0003J}\u00104\u001a\u00020\u00002\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000f\u001a\u00020\u00062\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00062\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u00c6\u0001J\u0013\u00105\u001a\u00020\u00062\b\u00106\u001a\u0004\u0018\u000107H\u00d6\u0003J\t\u00108\u001a\u000209H\u00d6\u0001J\b\u0010:\u001a\u00020;H\u0016J\t\u0010<\u001a\u00020\nH\u00d6\u0001R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0014\u001a\u00020\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\f\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u001e\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018R\u0010\u0010\r\u001a\u0004\u0018\u00010\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010 \u001a\u00020\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001aR\u0014\u0010\u0013\u001a\u00020\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\"R\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\"R\u0011\u0010\u000f\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\"R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\n\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0018R\u0011\u0010$\u001a\u00020%\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\'R\u0011\u0010\u000b\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0018\u00a8\u0006="}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$Event;", "Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem;", "Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$IActionableItem;", "model", "Lorg/wordpress/android/fluxc/model/activity/ActivityLogModel;", "rewindDisabled", "", "isRestoreHidden", "(Lorg/wordpress/android/fluxc/model/activity/ActivityLogModel;ZZ)V", "activityId", "", "title", "description", "gridIcon", "eventStatus", "isRewindable", "rewindId", "date", "Ljava/util/Date;", "isButtonVisible", "buttonIcon", "Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$Icon;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/util/Date;ZLorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$Icon;Z)V", "getActivityId", "()Ljava/lang/String;", "getButtonIcon", "()Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$Icon;", "getDate", "()Ljava/util/Date;", "getDescription", "formattedDate", "getFormattedDate", "icon", "getIcon", "()Z", "getRewindId", "status", "Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$Status;", "getStatus", "()Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$Status;", "getTitle", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "", "longId", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Event extends org.wordpress.android.ui.activitylog.list.ActivityLogListItem implements org.wordpress.android.ui.activitylog.list.ActivityLogListItem.IActionableItem {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String activityId = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String title = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String description = null;
        private final java.lang.String gridIcon = null;
        private final java.lang.String eventStatus = null;
        private final boolean isRewindable = false;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String rewindId = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.Date date = null;
        private final boolean isButtonVisible = false;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.activitylog.list.ActivityLogListItem.Icon buttonIcon = null;
        private final boolean isRestoreHidden = false;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String formattedDate = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.activitylog.list.ActivityLogListItem.Icon icon = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.activitylog.list.ActivityLogListItem.Status status = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.activitylog.list.ActivityLogListItem.Event copy(@org.jetbrains.annotations.NotNull()
        java.lang.String activityId, @org.jetbrains.annotations.NotNull()
        java.lang.String title, @org.jetbrains.annotations.NotNull()
        java.lang.String description, @org.jetbrains.annotations.Nullable()
        java.lang.String gridIcon, @org.jetbrains.annotations.Nullable()
        java.lang.String eventStatus, boolean isRewindable, @org.jetbrains.annotations.Nullable()
        java.lang.String rewindId, @org.jetbrains.annotations.NotNull()
        java.util.Date date, boolean isButtonVisible, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.activitylog.list.ActivityLogListItem.Icon buttonIcon, boolean isRestoreHidden) {
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
        
        public Event(@org.jetbrains.annotations.NotNull()
        java.lang.String activityId, @org.jetbrains.annotations.NotNull()
        java.lang.String title, @org.jetbrains.annotations.NotNull()
        java.lang.String description, @org.jetbrains.annotations.Nullable()
        java.lang.String gridIcon, @org.jetbrains.annotations.Nullable()
        java.lang.String eventStatus, boolean isRewindable, @org.jetbrains.annotations.Nullable()
        java.lang.String rewindId, @org.jetbrains.annotations.NotNull()
        java.util.Date date, boolean isButtonVisible, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.activitylog.list.ActivityLogListItem.Icon buttonIcon, boolean isRestoreHidden) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getActivityId() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getDescription() {
            return null;
        }
        
        private final java.lang.String component4() {
            return null;
        }
        
        private final java.lang.String component5() {
            return null;
        }
        
        public final boolean component6() {
            return false;
        }
        
        public final boolean isRewindable() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component7() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getRewindId() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Date component8() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Date getDate() {
            return null;
        }
        
        public final boolean component9() {
            return false;
        }
        
        @java.lang.Override()
        public boolean isButtonVisible() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.activitylog.list.ActivityLogListItem.Icon component10() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.activitylog.list.ActivityLogListItem.Icon getButtonIcon() {
            return null;
        }
        
        public final boolean component11() {
            return false;
        }
        
        public final boolean isRestoreHidden() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getFormattedDate() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.activitylog.list.ActivityLogListItem.Icon getIcon() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.activitylog.list.ActivityLogListItem.Status getStatus() {
            return null;
        }
        
        public Event(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.activity.ActivityLogModel model, boolean rewindDisabled, boolean isRestoreHidden) {
            super(null);
        }
        
        @java.lang.Override()
        public long longId() {
            return 0L;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u0018B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0006H\u00c6\u0003J\'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$Progress;", "Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem;", "title", "", "description", "progressType", "Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$Progress$Type;", "(Ljava/lang/String;Ljava/lang/String;Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$Progress$Type;)V", "getDescription", "()Ljava/lang/String;", "getProgressType", "()Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$Progress$Type;", "getTitle", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Type", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Progress extends org.wordpress.android.ui.activitylog.list.ActivityLogListItem {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String title = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String description = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.activitylog.list.ActivityLogListItem.Progress.Type progressType = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.activitylog.list.ActivityLogListItem.Progress copy(@org.jetbrains.annotations.NotNull()
        java.lang.String title, @org.jetbrains.annotations.NotNull()
        java.lang.String description, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.activitylog.list.ActivityLogListItem.Progress.Type progressType) {
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
        
        public Progress(@org.jetbrains.annotations.NotNull()
        java.lang.String title, @org.jetbrains.annotations.NotNull()
        java.lang.String description, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.activitylog.list.ActivityLogListItem.Progress.Type progressType) {
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
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getDescription() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.activitylog.list.ActivityLogListItem.Progress.Type component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.activitylog.list.ActivityLogListItem.Progress.Type getProgressType() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$Progress$Type;", "", "(Ljava/lang/String;I)V", "RESTORE", "BACKUP_DOWNLOAD", "org.wordpress.android_wordpressJalapenoDebug"})
        public static enum Type {
            /*public static final*/ RESTORE /* = new RESTORE() */,
            /*public static final*/ BACKUP_DOWNLOAD /* = new BACKUP_DOWNLOAD() */;
            
            Type() {
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$Header;", "Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem;", "text", "", "(Ljava/lang/String;)V", "getText", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Header extends org.wordpress.android.ui.activitylog.list.ActivityLogListItem {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String text = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.activitylog.list.ActivityLogListItem.Header copy(@org.jetbrains.annotations.NotNull()
        java.lang.String text) {
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
        
        public Header(@org.jetbrains.annotations.NotNull()
        java.lang.String text) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getText() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$Footer;", "Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Footer extends org.wordpress.android.ui.activitylog.list.ActivityLogListItem {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.activitylog.list.ActivityLogListItem.Footer INSTANCE = null;
        
        private Footer() {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$Loading;", "Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Loading extends org.wordpress.android.ui.activitylog.list.ActivityLogListItem {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.activitylog.list.ActivityLogListItem.Loading INSTANCE = null;
        
        private Loading() {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J3\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$Notice;", "Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem;", "label", "", "primaryAction", "Lkotlin/Function0;", "", "secondaryAction", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "getLabel", "()Ljava/lang/String;", "getPrimaryAction", "()Lkotlin/jvm/functions/Function0;", "getSecondaryAction", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Notice extends org.wordpress.android.ui.activitylog.list.ActivityLogListItem {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String label = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> primaryAction = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> secondaryAction = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.activitylog.list.ActivityLogListItem.Notice copy(@org.jetbrains.annotations.NotNull()
        java.lang.String label, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> primaryAction, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> secondaryAction) {
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
        
        public Notice(@org.jetbrains.annotations.NotNull()
        java.lang.String label, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> primaryAction, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> secondaryAction) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getLabel() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getPrimaryAction() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getSecondaryAction() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$ViewType;", "", "id", "", "(Ljava/lang/String;II)V", "getId", "()I", "EVENT", "PROGRESS", "HEADER", "FOOTER", "LOADING", "NOTICE", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum ViewType {
        /*public static final*/ EVENT /* = new EVENT(0) */,
        /*public static final*/ PROGRESS /* = new PROGRESS(0) */,
        /*public static final*/ HEADER /* = new HEADER(0) */,
        /*public static final*/ FOOTER /* = new FOOTER(0) */,
        /*public static final*/ LOADING /* = new LOADING(0) */,
        /*public static final*/ NOTICE /* = new NOTICE(0) */;
        private final int id = 0;
        
        ViewType(int id) {
        }
        
        public final int getId() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\b\u0086\u0001\u0018\u0000 \u000f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000fB\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$Status;", "", "value", "", "color", "", "(Ljava/lang/String;ILjava/lang/String;I)V", "getColor", "()I", "getValue", "()Ljava/lang/String;", "NEGATIVE", "INFO", "POSITIVE", "NEUTRAL", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum Status {
        /*public static final*/ NEGATIVE /* = new NEGATIVE(null, 0) */,
        /*public static final*/ INFO /* = new INFO(null, 0) */,
        /*public static final*/ POSITIVE /* = new POSITIVE(null, 0) */,
        /*public static final*/ NEUTRAL /* = new NEUTRAL(null, 0) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String value = null;
        private final int color = 0;
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.activitylog.list.ActivityLogListItem.Status.Companion Companion = null;
        private static final java.util.Map<java.lang.String, org.wordpress.android.ui.activitylog.list.ActivityLogListItem.Status> map = null;
        
        Status(java.lang.String value, @androidx.annotation.DrawableRes()
        int color) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getValue() {
            return null;
        }
        
        public final int getColor() {
            return 0;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u0005R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$Status$Companion;", "", "()V", "map", "", "", "Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$Status;", "fromValue", "value", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Companion {
            
            private Companion() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.activitylog.list.ActivityLogListItem.Status fromValue(@org.jetbrains.annotations.Nullable()
            java.lang.String value) {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b#\b\u0086\u0001\u0018\u0000 \'2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\'B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&\u00a8\u0006("}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$Icon;", "", "value", "", "drawable", "", "(Ljava/lang/String;ILjava/lang/String;I)V", "getDrawable", "()I", "getValue", "()Ljava/lang/String;", "CHECKMARK", "CLOUD", "COG", "COMMENT", "CROSS", "DOMAINS", "HISTORY", "IMAGE", "LAYOUT", "LOCK", "LOGOUT", "MAIL", "MENU", "MY_SITES", "NOTICE", "NOTICE_OUTLINE", "PAGES", "PLANS", "PLUGINS", "POSTS", "SHARE", "SHIPPING", "SPAM", "THEMES", "TRASH", "USER", "MORE", "DEFAULT", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum Icon {
        /*public static final*/ CHECKMARK /* = new CHECKMARK(null, 0) */,
        /*public static final*/ CLOUD /* = new CLOUD(null, 0) */,
        /*public static final*/ COG /* = new COG(null, 0) */,
        /*public static final*/ COMMENT /* = new COMMENT(null, 0) */,
        /*public static final*/ CROSS /* = new CROSS(null, 0) */,
        /*public static final*/ DOMAINS /* = new DOMAINS(null, 0) */,
        /*public static final*/ HISTORY /* = new HISTORY(null, 0) */,
        /*public static final*/ IMAGE /* = new IMAGE(null, 0) */,
        /*public static final*/ LAYOUT /* = new LAYOUT(null, 0) */,
        /*public static final*/ LOCK /* = new LOCK(null, 0) */,
        /*public static final*/ LOGOUT /* = new LOGOUT(null, 0) */,
        /*public static final*/ MAIL /* = new MAIL(null, 0) */,
        /*public static final*/ MENU /* = new MENU(null, 0) */,
        /*public static final*/ MY_SITES /* = new MY_SITES(null, 0) */,
        /*public static final*/ NOTICE /* = new NOTICE(null, 0) */,
        /*public static final*/ NOTICE_OUTLINE /* = new NOTICE_OUTLINE(null, 0) */,
        /*public static final*/ PAGES /* = new PAGES(null, 0) */,
        /*public static final*/ PLANS /* = new PLANS(null, 0) */,
        /*public static final*/ PLUGINS /* = new PLUGINS(null, 0) */,
        /*public static final*/ POSTS /* = new POSTS(null, 0) */,
        /*public static final*/ SHARE /* = new SHARE(null, 0) */,
        /*public static final*/ SHIPPING /* = new SHIPPING(null, 0) */,
        /*public static final*/ SPAM /* = new SPAM(null, 0) */,
        /*public static final*/ THEMES /* = new THEMES(null, 0) */,
        /*public static final*/ TRASH /* = new TRASH(null, 0) */,
        /*public static final*/ USER /* = new USER(null, 0) */,
        /*public static final*/ MORE /* = new MORE(null, 0) */,
        /*public static final*/ DEFAULT /* = new DEFAULT(null, 0) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String value = null;
        private final int drawable = 0;
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.activitylog.list.ActivityLogListItem.Icon.Companion Companion = null;
        private static final java.util.Map<java.lang.String, org.wordpress.android.ui.activitylog.list.ActivityLogListItem.Icon> map = null;
        
        Icon(java.lang.String value, @androidx.annotation.DrawableRes()
        int drawable) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getValue() {
            return null;
        }
        
        public final int getDrawable() {
            return 0;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u0005R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$Icon$Companion;", "", "()V", "map", "", "", "Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$Icon;", "fromValue", "value", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Companion {
            
            private Companion() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.activitylog.list.ActivityLogListItem.Icon fromValue(@org.jetbrains.annotations.Nullable()
            java.lang.String value) {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$SecondaryAction;", "", "itemId", "", "(Ljava/lang/String;IJ)V", "getItemId", "()J", "RESTORE", "DOWNLOAD_BACKUP", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum SecondaryAction {
        /*public static final*/ RESTORE /* = new RESTORE(0L) */,
        /*public static final*/ DOWNLOAD_BACKUP /* = new DOWNLOAD_BACKUP(0L) */;
        private final long itemId = 0L;
        
        SecondaryAction(long itemId) {
        }
        
        public final long getItemId() {
            return 0L;
        }
    }
}