package org.wordpress.android.ui.reader.viewmodels;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\r\u001a\u00020\n\u0012\u0006\u0010\u000e\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000fJ\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\nH\u00c6\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\fH\u00c6\u0003J\t\u0010!\u001a\u00020\nH\u00c6\u0003J\t\u0010\"\u001a\u00020\nH\u00c6\u0003J]\u0010#\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\n2\b\b\u0002\u0010\u000e\u001a\u00020\nH\u00c6\u0001J\u0013\u0010$\u001a\u00020\n2\b\u0010%\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010&\u001a\u00020\'H\u00d6\u0001J\t\u0010(\u001a\u00020)H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u000e\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0013R\u0011\u0010\r\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0013R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a\u00a8\u0006*"}, d2 = {"Lorg/wordpress/android/ui/reader/viewmodels/ReaderModeInfo;", "", "tag", "Lorg/wordpress/android/models/ReaderTag;", "listType", "Lorg/wordpress/android/ui/reader/ReaderTypes$ReaderPostListType;", "blogId", "", "feedId", "requestNewerPosts", "", "label", "Lorg/wordpress/android/ui/utils/UiString;", "isFirstLoad", "isFiltered", "(Lorg/wordpress/android/models/ReaderTag;Lorg/wordpress/android/ui/reader/ReaderTypes$ReaderPostListType;JJZLorg/wordpress/android/ui/utils/UiString;ZZ)V", "getBlogId", "()J", "getFeedId", "()Z", "getLabel", "()Lorg/wordpress/android/ui/utils/UiString;", "getListType", "()Lorg/wordpress/android/ui/reader/ReaderTypes$ReaderPostListType;", "getRequestNewerPosts", "getTag", "()Lorg/wordpress/android/models/ReaderTag;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderModeInfo {
    @org.jetbrains.annotations.Nullable()
    private final org.wordpress.android.models.ReaderTag tag = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.reader.ReaderTypes.ReaderPostListType listType = null;
    private final long blogId = 0L;
    private final long feedId = 0L;
    private final boolean requestNewerPosts = false;
    @org.jetbrains.annotations.Nullable()
    private final org.wordpress.android.ui.utils.UiString label = null;
    private final boolean isFirstLoad = false;
    private final boolean isFiltered = false;
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.reader.viewmodels.ReaderModeInfo copy(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.models.ReaderTag tag, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.ReaderTypes.ReaderPostListType listType, long blogId, long feedId, boolean requestNewerPosts, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.utils.UiString label, boolean isFirstLoad, boolean isFiltered) {
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
    
    public ReaderModeInfo(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.models.ReaderTag tag, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.ReaderTypes.ReaderPostListType listType, long blogId, long feedId, boolean requestNewerPosts, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.utils.UiString label, boolean isFirstLoad, boolean isFiltered) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.models.ReaderTag component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.models.ReaderTag getTag() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.reader.ReaderTypes.ReaderPostListType component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.reader.ReaderTypes.ReaderPostListType getListType() {
        return null;
    }
    
    public final long component3() {
        return 0L;
    }
    
    public final long getBlogId() {
        return 0L;
    }
    
    public final long component4() {
        return 0L;
    }
    
    public final long getFeedId() {
        return 0L;
    }
    
    public final boolean component5() {
        return false;
    }
    
    public final boolean getRequestNewerPosts() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.utils.UiString component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.utils.UiString getLabel() {
        return null;
    }
    
    public final boolean component7() {
        return false;
    }
    
    public final boolean isFirstLoad() {
        return false;
    }
    
    public final boolean component8() {
        return false;
    }
    
    public final boolean isFiltered() {
        return false;
    }
}