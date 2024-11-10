package org.wordpress.android.ui.engagement;

import java.lang.System;

@android.annotation.SuppressLint(value = {"ParcelCreator"})
@kotlinx.parcelize.Parcelize()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001d\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\fH\u00c6\u0003JO\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\fH\u00c6\u0001J\t\u0010!\u001a\u00020\fH\u00d6\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%H\u00d6\u0003J\t\u0010&\u001a\u00020\fH\u00d6\u0001J\t\u0010\'\u001a\u00020\u0005H\u00d6\u0001J\u0019\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\fH\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\t\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\n\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000f\u00a8\u0006-"}, d2 = {"Lorg/wordpress/android/ui/engagement/HeaderData;", "Landroid/os/Parcelable;", "authorName", "Lorg/wordpress/android/ui/engagement/AuthorName;", "snippetText", "", "authorAvatarUrl", "authorUserId", "", "authorPreferredSiteId", "authorPreferredSiteUrl", "numLikes", "", "(Lorg/wordpress/android/ui/engagement/AuthorName;Ljava/lang/String;Ljava/lang/String;JJLjava/lang/String;I)V", "getAuthorAvatarUrl", "()Ljava/lang/String;", "getAuthorName", "()Lorg/wordpress/android/ui/engagement/AuthorName;", "getAuthorPreferredSiteId", "()J", "getAuthorPreferredSiteUrl", "getAuthorUserId", "getNumLikes", "()I", "getSnippetText", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "org.wordpress.android_wordpressJalapenoDebug"})
public final class HeaderData implements android.os.Parcelable {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.engagement.AuthorName authorName = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String snippetText = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String authorAvatarUrl = null;
    private final long authorUserId = 0L;
    private final long authorPreferredSiteId = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String authorPreferredSiteUrl = null;
    private final int numLikes = 0;
    public static final android.os.Parcelable.Creator<org.wordpress.android.ui.engagement.HeaderData> CREATOR = null;
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.engagement.HeaderData copy(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.engagement.AuthorName authorName, @org.jetbrains.annotations.NotNull()
    java.lang.String snippetText, @org.jetbrains.annotations.NotNull()
    java.lang.String authorAvatarUrl, long authorUserId, long authorPreferredSiteId, @org.jetbrains.annotations.NotNull()
    java.lang.String authorPreferredSiteUrl, int numLikes) {
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
    
    public HeaderData(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.engagement.AuthorName authorName, @org.jetbrains.annotations.NotNull()
    java.lang.String snippetText, @org.jetbrains.annotations.NotNull()
    java.lang.String authorAvatarUrl, long authorUserId, long authorPreferredSiteId, @org.jetbrains.annotations.NotNull()
    java.lang.String authorPreferredSiteUrl, int numLikes) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.engagement.AuthorName component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.engagement.AuthorName getAuthorName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSnippetText() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAuthorAvatarUrl() {
        return null;
    }
    
    public final long component4() {
        return 0L;
    }
    
    public final long getAuthorUserId() {
        return 0L;
    }
    
    public final long component5() {
        return 0L;
    }
    
    public final long getAuthorPreferredSiteId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAuthorPreferredSiteUrl() {
        return null;
    }
    
    public final int component7() {
        return 0;
    }
    
    public final int getNumLikes() {
        return 0;
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
    public static final class Creator implements android.os.Parcelable.Creator<org.wordpress.android.ui.engagement.HeaderData> {
        
        public Creator() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final org.wordpress.android.ui.engagement.HeaderData createFromParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel in) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final org.wordpress.android.ui.engagement.HeaderData[] newArray(int size) {
            return null;
        }
    }
}