package org.wordpress.android.ui.history;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\t\n\u000b\fB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0003\r\u000e\u000f\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/history/HistoryListItem;", "", "type", "Lorg/wordpress/android/ui/history/HistoryListItem$ViewType;", "(Lorg/wordpress/android/ui/history/HistoryListItem$ViewType;)V", "getType", "()Lorg/wordpress/android/ui/history/HistoryListItem$ViewType;", "longId", "", "Footer", "Header", "Revision", "ViewType", "Lorg/wordpress/android/ui/history/HistoryListItem$Footer;", "Lorg/wordpress/android/ui/history/HistoryListItem$Header;", "Lorg/wordpress/android/ui/history/HistoryListItem$Revision;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class HistoryListItem {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.history.HistoryListItem.ViewType type = null;
    
    private HistoryListItem(org.wordpress.android.ui.history.HistoryListItem.ViewType type) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.history.HistoryListItem.ViewType getType() {
        return null;
    }
    
    public long longId() {
        return 0L;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/history/HistoryListItem$Footer;", "Lorg/wordpress/android/ui/history/HistoryListItem;", "text", "", "(Ljava/lang/String;)V", "getText", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Footer extends org.wordpress.android.ui.history.HistoryListItem {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String text = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.history.HistoryListItem.Footer copy(@org.jetbrains.annotations.NotNull()
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
        
        public Footer(@org.jetbrains.annotations.NotNull()
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
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/history/HistoryListItem$Header;", "Lorg/wordpress/android/ui/history/HistoryListItem;", "text", "", "(Ljava/lang/String;)V", "getText", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Header extends org.wordpress.android.ui.history.HistoryListItem {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String text = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.history.HistoryListItem.Header copy(@org.jetbrains.annotations.NotNull()
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
    
    @android.annotation.SuppressLint(value = {"ParcelCreator"})
    @kotlinx.parcelize.Parcelize()
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005B\u009f\u0001\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\r\u0012\u0011\u0010\u0013\u001a\r\u0012\t\u0012\u00070\u0015\u00a2\u0006\u0002\b\u00160\u0014\u0012\u0011\u0010\u0017\u001a\r\u0012\t\u0012\u00070\u0015\u00a2\u0006\u0002\b\u00160\u0014\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\r\u00a2\u0006\u0002\u0010\u001aJ\t\u0010=\u001a\u00020\u0007H\u00c6\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\rH\u00c6\u0003J\u0014\u0010?\u001a\r\u0012\t\u0012\u00070\u0015\u00a2\u0006\u0002\b\u00160\u0014H\u00c6\u0003J\u0014\u0010@\u001a\r\u0012\t\u0012\u00070\u0015\u00a2\u0006\u0002\b\u00160\u0014H\u00c6\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\rH\u00c6\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\rH\u00c6\u0003J\t\u0010C\u001a\u00020\u0007H\u00c6\u0003J\t\u0010D\u001a\u00020\nH\u00c6\u0003J\t\u0010E\u001a\u00020\nH\u00c6\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\rH\u00c6\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\rH\u00c6\u0003J\u000b\u0010H\u001a\u0004\u0018\u00010\rH\u00c6\u0003J\u000b\u0010I\u001a\u0004\u0018\u00010\rH\u00c6\u0003J\u000b\u0010J\u001a\u0004\u0018\u00010\rH\u00c6\u0003J\u00bb\u0001\u0010K\u001a\u00020\u00002\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\r2\u0013\b\u0002\u0010\u0013\u001a\r\u0012\t\u0012\u00070\u0015\u00a2\u0006\u0002\b\u00160\u00142\u0013\b\u0002\u0010\u0017\u001a\r\u0012\t\u0012\u00070\u0015\u00a2\u0006\u0002\b\u00160\u00142\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\rH\u00c6\u0001J\t\u0010L\u001a\u00020\nH\u00d6\u0001J\u0013\u0010M\u001a\u00020N2\b\u0010O\u001a\u0004\u0018\u00010PH\u00d6\u0003J\t\u0010Q\u001a\u00020\nH\u00d6\u0001J\b\u0010R\u001a\u00020\u0007H\u0016J\t\u0010S\u001a\u00020\rH\u00d6\u0001J\u0019\u0010T\u001a\u00020U2\u0006\u0010V\u001a\u00020W2\u0006\u0010X\u001a\u00020\nH\u00d6\u0001R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001c\u0010\u0018\u001a\u0004\u0018\u00010\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001c\"\u0004\b \u0010\u001eR\u001c\u0010\u0017\u001a\r\u0012\t\u0012\u00070\u0015\u00a2\u0006\u0002\b\u00160\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0017\u0010%\u001a\u00020\r\u00a2\u0006\u000e\n\u0000\u0012\u0004\b&\u0010\'\u001a\u0004\b(\u0010\u001cR\u0017\u0010)\u001a\u00020\r\u00a2\u0006\u000e\n\u0000\u0012\u0004\b*\u0010\'\u001a\u0004\b+\u0010\u001cR\u0013\u0010\u0012\u001a\u0004\u0018\u00010\r\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001cR\u0013\u0010\f\u001a\u0004\u0018\u00010\r\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001cR\u0014\u0010.\u001a\u00020/X\u0082\u0004\u00a2\u0006\b\n\u0000\u0012\u0004\b0\u0010\'R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\r\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u0010\u001cR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\r\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010\u001cR\u0013\u0010\u0011\u001a\u0004\u0018\u00010\r\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010\u001cR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\r\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010\u001cR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010$R\u0017\u00106\u001a\u00020\r\u00a2\u0006\u000e\n\u0000\u0012\u0004\b7\u0010\'\u001a\u0004\b8\u0010\u001cR\u001c\u0010\u0013\u001a\r\u0012\t\u0012\u00070\u0015\u00a2\u0006\u0002\b\u00160\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u0010\"R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u0011\u0010\u000b\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b<\u0010;\u00a8\u0006Y"}, d2 = {"Lorg/wordpress/android/ui/history/HistoryListItem$Revision;", "Lorg/wordpress/android/ui/history/HistoryListItem;", "Landroid/os/Parcelable;", "model", "Lorg/wordpress/android/fluxc/model/revisions/RevisionModel;", "(Lorg/wordpress/android/fluxc/model/revisions/RevisionModel;)V", "revisionId", "", "diffFromVersion", "totalAdditions", "", "totalDeletions", "postContent", "", "postExcerpt", "postTitle", "postDateGmt", "postModifiedGmt", "postAuthorId", "titleDiffs", "Ljava/util/ArrayList;", "Lorg/wordpress/android/fluxc/model/revisions/Diff;", "Lkotlinx/parcelize/RawValue;", "contentDiffs", "authorDisplayName", "authorAvatarURL", "(JJIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/lang/String;Ljava/lang/String;)V", "getAuthorAvatarURL", "()Ljava/lang/String;", "setAuthorAvatarURL", "(Ljava/lang/String;)V", "getAuthorDisplayName", "setAuthorDisplayName", "getContentDiffs", "()Ljava/util/ArrayList;", "getDiffFromVersion", "()J", "formattedDate", "getFormattedDate$annotations", "()V", "getFormattedDate", "formattedTime", "getFormattedTime$annotations", "getFormattedTime", "getPostAuthorId", "getPostContent", "postDate", "Ljava/util/Date;", "getPostDate$annotations", "getPostDateGmt", "getPostExcerpt", "getPostModifiedGmt", "getPostTitle", "getRevisionId", "timeSpan", "getTimeSpan$annotations", "getTimeSpan", "getTitleDiffs", "getTotalAdditions", "()I", "getTotalDeletions", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "describeContents", "equals", "", "other", "", "hashCode", "longId", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Revision extends org.wordpress.android.ui.history.HistoryListItem implements android.os.Parcelable {
        private final long revisionId = 0L;
        private final long diffFromVersion = 0L;
        private final int totalAdditions = 0;
        private final int totalDeletions = 0;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String postContent = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String postExcerpt = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String postTitle = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String postDateGmt = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String postModifiedGmt = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String postAuthorId = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.ArrayList<org.wordpress.android.fluxc.model.revisions.Diff> titleDiffs = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.ArrayList<org.wordpress.android.fluxc.model.revisions.Diff> contentDiffs = null;
        @org.jetbrains.annotations.Nullable()
        private java.lang.String authorDisplayName;
        @org.jetbrains.annotations.Nullable()
        private java.lang.String authorAvatarURL;
        private final java.util.Date postDate = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String timeSpan = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String formattedDate = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String formattedTime = null;
        public static final android.os.Parcelable.Creator<org.wordpress.android.ui.history.HistoryListItem.Revision> CREATOR = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.history.HistoryListItem.Revision copy(long revisionId, long diffFromVersion, int totalAdditions, int totalDeletions, @org.jetbrains.annotations.Nullable()
        java.lang.String postContent, @org.jetbrains.annotations.Nullable()
        java.lang.String postExcerpt, @org.jetbrains.annotations.Nullable()
        java.lang.String postTitle, @org.jetbrains.annotations.Nullable()
        java.lang.String postDateGmt, @org.jetbrains.annotations.Nullable()
        java.lang.String postModifiedGmt, @org.jetbrains.annotations.Nullable()
        java.lang.String postAuthorId, @org.jetbrains.annotations.NotNull()
        java.util.ArrayList<org.wordpress.android.fluxc.model.revisions.Diff> titleDiffs, @org.jetbrains.annotations.NotNull()
        java.util.ArrayList<org.wordpress.android.fluxc.model.revisions.Diff> contentDiffs, @org.jetbrains.annotations.Nullable()
        java.lang.String authorDisplayName, @org.jetbrains.annotations.Nullable()
        java.lang.String authorAvatarURL) {
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
        
        public Revision(long revisionId, long diffFromVersion, int totalAdditions, int totalDeletions, @org.jetbrains.annotations.Nullable()
        java.lang.String postContent, @org.jetbrains.annotations.Nullable()
        java.lang.String postExcerpt, @org.jetbrains.annotations.Nullable()
        java.lang.String postTitle, @org.jetbrains.annotations.Nullable()
        java.lang.String postDateGmt, @org.jetbrains.annotations.Nullable()
        java.lang.String postModifiedGmt, @org.jetbrains.annotations.Nullable()
        java.lang.String postAuthorId, @org.jetbrains.annotations.NotNull()
        java.util.ArrayList<org.wordpress.android.fluxc.model.revisions.Diff> titleDiffs, @org.jetbrains.annotations.NotNull()
        java.util.ArrayList<org.wordpress.android.fluxc.model.revisions.Diff> contentDiffs, @org.jetbrains.annotations.Nullable()
        java.lang.String authorDisplayName, @org.jetbrains.annotations.Nullable()
        java.lang.String authorAvatarURL) {
            super(null);
        }
        
        public final long component1() {
            return 0L;
        }
        
        public final long getRevisionId() {
            return 0L;
        }
        
        public final long component2() {
            return 0L;
        }
        
        public final long getDiffFromVersion() {
            return 0L;
        }
        
        public final int component3() {
            return 0;
        }
        
        public final int getTotalAdditions() {
            return 0;
        }
        
        public final int component4() {
            return 0;
        }
        
        public final int getTotalDeletions() {
            return 0;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component5() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getPostContent() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component6() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getPostExcerpt() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component7() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getPostTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component8() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getPostDateGmt() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component9() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getPostModifiedGmt() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component10() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getPostAuthorId() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.ArrayList<org.wordpress.android.fluxc.model.revisions.Diff> component11() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.ArrayList<org.wordpress.android.fluxc.model.revisions.Diff> getTitleDiffs() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.ArrayList<org.wordpress.android.fluxc.model.revisions.Diff> component12() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.ArrayList<org.wordpress.android.fluxc.model.revisions.Diff> getContentDiffs() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component13() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getAuthorDisplayName() {
            return null;
        }
        
        public final void setAuthorDisplayName(@org.jetbrains.annotations.Nullable()
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component14() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getAuthorAvatarURL() {
            return null;
        }
        
        public final void setAuthorAvatarURL(@org.jetbrains.annotations.Nullable()
        java.lang.String p0) {
        }
        
        @kotlinx.parcelize.IgnoredOnParcel()
        @java.lang.Deprecated()
        private static void getPostDate$annotations() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTimeSpan() {
            return null;
        }
        
        @kotlinx.parcelize.IgnoredOnParcel()
        @java.lang.Deprecated()
        public static void getTimeSpan$annotations() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getFormattedDate() {
            return null;
        }
        
        @kotlinx.parcelize.IgnoredOnParcel()
        @java.lang.Deprecated()
        public static void getFormattedDate$annotations() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getFormattedTime() {
            return null;
        }
        
        @kotlinx.parcelize.IgnoredOnParcel()
        @java.lang.Deprecated()
        public static void getFormattedTime$annotations() {
        }
        
        public Revision(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.revisions.RevisionModel model) {
            super(null);
        }
        
        @java.lang.Override()
        public long longId() {
            return 0L;
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
        public static final class Creator implements android.os.Parcelable.Creator<org.wordpress.android.ui.history.HistoryListItem.Revision> {
            
            public Creator() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public final org.wordpress.android.ui.history.HistoryListItem.Revision createFromParcel(@org.jetbrains.annotations.NotNull()
            android.os.Parcel in) {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public final org.wordpress.android.ui.history.HistoryListItem.Revision[] newArray(int size) {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/history/HistoryListItem$ViewType;", "", "id", "", "(Ljava/lang/String;II)V", "getId", "()I", "FOOTER", "HEADER", "REVISION", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum ViewType {
        /*public static final*/ FOOTER /* = new FOOTER(0) */,
        /*public static final*/ HEADER /* = new HEADER(0) */,
        /*public static final*/ REVISION /* = new REVISION(0) */;
        private final int id = 0;
        
        ViewType(int id) {
        }
        
        public final int getId() {
            return 0;
        }
    }
}