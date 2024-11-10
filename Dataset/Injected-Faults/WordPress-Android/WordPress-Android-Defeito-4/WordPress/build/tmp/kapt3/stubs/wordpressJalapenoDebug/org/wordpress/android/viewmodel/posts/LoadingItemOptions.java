package org.wordpress.android.viewmodel.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B/\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\bR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n\u0082\u0001\u0002\u000f\u0010\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/viewmodel/posts/LoadingItemOptions;", "", "showEditButton", "", "showViewButton", "showMoreButton", "showMoveToDraftButton", "showDeletePermanentlyButton", "(ZZZZZ)V", "getShowDeletePermanentlyButton", "()Z", "getShowEditButton", "getShowMoreButton", "getShowMoveToDraftButton", "getShowViewButton", "Lorg/wordpress/android/viewmodel/posts/LoadingItemDefaultPost;", "Lorg/wordpress/android/viewmodel/posts/LoadingItemTrashedPost;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class LoadingItemOptions {
    private final boolean showEditButton = false;
    private final boolean showViewButton = false;
    private final boolean showMoreButton = false;
    private final boolean showMoveToDraftButton = false;
    private final boolean showDeletePermanentlyButton = false;
    
    private LoadingItemOptions(boolean showEditButton, boolean showViewButton, boolean showMoreButton, boolean showMoveToDraftButton, boolean showDeletePermanentlyButton) {
        super();
    }
    
    public final boolean getShowEditButton() {
        return false;
    }
    
    public final boolean getShowViewButton() {
        return false;
    }
    
    public final boolean getShowMoreButton() {
        return false;
    }
    
    public final boolean getShowMoveToDraftButton() {
        return false;
    }
    
    public final boolean getShowDeletePermanentlyButton() {
        return false;
    }
}