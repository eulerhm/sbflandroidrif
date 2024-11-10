package org.wordpress.android.ui.engagement;

import java.lang.System;

@android.annotation.SuppressLint(value = {"ParcelCreator"})
@kotlinx.parcelize.Parcelize()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0007H\u00c6\u0003J\t\u0010 \u001a\u00020\u000bH\u00c6\u0003J\t\u0010!\u001a\u00020\rH\u00c6\u0003JO\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH\u00c6\u0001J\t\u0010#\u001a\u00020$H\u00d6\u0001J\u0013\u0010%\u001a\u00020&2\b\u0010\'\u001a\u0004\u0018\u00010(H\u00d6\u0003J\t\u0010)\u001a\u00020$H\u00d6\u0001J\t\u0010*\u001a\u00020\u000bH\u00d6\u0001J\u0019\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020$H\u00d6\u0001R\u0011\u0010\t\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a\u00a8\u00060"}, d2 = {"Lorg/wordpress/android/ui/engagement/ListScenario;", "Landroid/os/Parcelable;", "type", "Lorg/wordpress/android/ui/engagement/ListScenarioType;", "source", "Lorg/wordpress/android/ui/engagement/EngagementNavigationSource;", "siteId", "", "postOrCommentId", "commentPostId", "commentSiteUrl", "", "headerData", "Lorg/wordpress/android/ui/engagement/HeaderData;", "(Lorg/wordpress/android/ui/engagement/ListScenarioType;Lorg/wordpress/android/ui/engagement/EngagementNavigationSource;JJJLjava/lang/String;Lorg/wordpress/android/ui/engagement/HeaderData;)V", "getCommentPostId", "()J", "getCommentSiteUrl", "()Ljava/lang/String;", "getHeaderData", "()Lorg/wordpress/android/ui/engagement/HeaderData;", "getPostOrCommentId", "getSiteId", "getSource", "()Lorg/wordpress/android/ui/engagement/EngagementNavigationSource;", "getType", "()Lorg/wordpress/android/ui/engagement/ListScenarioType;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ListScenario implements android.os.Parcelable {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.engagement.ListScenarioType type = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.engagement.EngagementNavigationSource source = null;
    private final long siteId = 0L;
    private final long postOrCommentId = 0L;
    private final long commentPostId = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String commentSiteUrl = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.engagement.HeaderData headerData = null;
    public static final android.os.Parcelable.Creator<org.wordpress.android.ui.engagement.ListScenario> CREATOR = null;
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.engagement.ListScenario copy(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.engagement.ListScenarioType type, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.engagement.EngagementNavigationSource source, long siteId, long postOrCommentId, long commentPostId, @org.jetbrains.annotations.NotNull()
    java.lang.String commentSiteUrl, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.engagement.HeaderData headerData) {
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
    
    public ListScenario(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.engagement.ListScenarioType type, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.engagement.EngagementNavigationSource source, long siteId, long postOrCommentId, long commentPostId, @org.jetbrains.annotations.NotNull()
    java.lang.String commentSiteUrl, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.engagement.HeaderData headerData) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.engagement.ListScenarioType component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.engagement.ListScenarioType getType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.engagement.EngagementNavigationSource component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.engagement.EngagementNavigationSource getSource() {
        return null;
    }
    
    public final long component3() {
        return 0L;
    }
    
    public final long getSiteId() {
        return 0L;
    }
    
    public final long component4() {
        return 0L;
    }
    
    public final long getPostOrCommentId() {
        return 0L;
    }
    
    public final long component5() {
        return 0L;
    }
    
    public final long getCommentPostId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCommentSiteUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.engagement.HeaderData component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.engagement.HeaderData getHeaderData() {
        return null;
    }
    
    @java.lang.Override()
    public int describeContents() {
        return 0;
    }
    
    @java.lang.Override()
    public void writeToParcel(@org.jetbrains.annotations.NotNull()
    android.os.Parcel parcel, int flags) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
    public static final class Creator implements android.os.Parcelable.Creator<org.wordpress.android.ui.engagement.ListScenario> {
        
        public Creator() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final org.wordpress.android.ui.engagement.ListScenario createFromParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel in) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final org.wordpress.android.ui.engagement.ListScenario[] newArray(int size) {
            return null;
        }
    }
}