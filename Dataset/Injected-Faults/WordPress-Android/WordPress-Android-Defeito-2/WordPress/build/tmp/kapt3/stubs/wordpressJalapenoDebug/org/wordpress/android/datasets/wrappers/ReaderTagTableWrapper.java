package org.wordpress.android.datasets.wrappers;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0006J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/datasets/wrappers/ReaderTagTableWrapper;", "", "()V", "clearTagLastUpdated", "", "readerTag", "Lorg/wordpress/android/models/ReaderTag;", "getFollowedTags", "Lorg/wordpress/android/models/ReaderTagList;", "setTagLastUpdated", "tag", "shouldAutoUpdateTag", "", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class ReaderTagTableWrapper {
    
    @javax.inject.Inject()
    public ReaderTagTableWrapper() {
        super();
    }
    
    public final boolean shouldAutoUpdateTag(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderTag readerTag) {
        return false;
    }
    
    public final void setTagLastUpdated(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderTag tag) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.models.ReaderTagList getFollowedTags() {
        return null;
    }
    
    public final void clearTagLastUpdated(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderTag readerTag) {
    }
}