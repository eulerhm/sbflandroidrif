package org.wordpress.android.ui.activitylog.detail;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B{\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0010J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010%\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003\u00a2\u0006\u0002\u0010\u001dJ\t\u0010&\u001a\u00020\u0007H\u00c6\u0003J\u000b\u0010\'\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\fH\u00c6\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u008a\u0001\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010,J\u0013\u0010-\u001a\u00020\u00072\b\u0010.\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010/\u001a\u000200H\u00d6\u0001J\t\u00101\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u0011\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u001aR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0012\u00a8\u00062"}, d2 = {"Lorg/wordpress/android/ui/activitylog/detail/ActivityLogDetailModel;", "", "activityID", "", "rewindId", "actorIconUrl", "showJetpackIcon", "", "isRewindButtonVisible", "actorName", "actorRole", "content", "Lorg/wordpress/android/fluxc/tools/FormattableContent;", "summary", "createdDate", "createdTime", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;ZLjava/lang/String;Ljava/lang/String;Lorg/wordpress/android/fluxc/tools/FormattableContent;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getActivityID", "()Ljava/lang/String;", "getActorIconUrl", "getActorName", "getActorRole", "getContent", "()Lorg/wordpress/android/fluxc/tools/FormattableContent;", "getCreatedDate", "getCreatedTime", "()Z", "getRewindId", "getShowJetpackIcon", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getSummary", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;ZLjava/lang/String;Ljava/lang/String;Lorg/wordpress/android/fluxc/tools/FormattableContent;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/wordpress/android/ui/activitylog/detail/ActivityLogDetailModel;", "equals", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ActivityLogDetailModel {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String activityID = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String rewindId = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String actorIconUrl = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Boolean showJetpackIcon = null;
    private final boolean isRewindButtonVisible = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String actorName = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String actorRole = null;
    @org.jetbrains.annotations.Nullable()
    private final org.wordpress.android.fluxc.tools.FormattableContent content = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String summary = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String createdDate = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String createdTime = null;
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.activitylog.detail.ActivityLogDetailModel copy(@org.jetbrains.annotations.NotNull()
    java.lang.String activityID, @org.jetbrains.annotations.Nullable()
    java.lang.String rewindId, @org.jetbrains.annotations.Nullable()
    java.lang.String actorIconUrl, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean showJetpackIcon, boolean isRewindButtonVisible, @org.jetbrains.annotations.Nullable()
    java.lang.String actorName, @org.jetbrains.annotations.Nullable()
    java.lang.String actorRole, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.tools.FormattableContent content, @org.jetbrains.annotations.Nullable()
    java.lang.String summary, @org.jetbrains.annotations.NotNull()
    java.lang.String createdDate, @org.jetbrains.annotations.NotNull()
    java.lang.String createdTime) {
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
    
    public ActivityLogDetailModel(@org.jetbrains.annotations.NotNull()
    java.lang.String activityID, @org.jetbrains.annotations.Nullable()
    java.lang.String rewindId, @org.jetbrains.annotations.Nullable()
    java.lang.String actorIconUrl, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean showJetpackIcon, boolean isRewindButtonVisible, @org.jetbrains.annotations.Nullable()
    java.lang.String actorName, @org.jetbrains.annotations.Nullable()
    java.lang.String actorRole, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.tools.FormattableContent content, @org.jetbrains.annotations.Nullable()
    java.lang.String summary, @org.jetbrains.annotations.NotNull()
    java.lang.String createdDate, @org.jetbrains.annotations.NotNull()
    java.lang.String createdTime) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getActivityID() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getRewindId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getActorIconUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean getShowJetpackIcon() {
        return null;
    }
    
    public final boolean component5() {
        return false;
    }
    
    public final boolean isRewindButtonVisible() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getActorName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getActorRole() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.fluxc.tools.FormattableContent component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.fluxc.tools.FormattableContent getContent() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getSummary() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCreatedDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCreatedTime() {
        return null;
    }
}