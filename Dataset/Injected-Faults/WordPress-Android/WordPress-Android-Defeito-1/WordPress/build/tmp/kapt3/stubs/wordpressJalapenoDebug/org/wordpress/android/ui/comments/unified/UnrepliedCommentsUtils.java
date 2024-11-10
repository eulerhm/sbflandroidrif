package org.wordpress.android.ui.comments.unified;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\"\u0010\u0007\u001a\f\u0012\u0004\u0012\u00020\t0\bj\u0002`\n2\u0010\u0010\u000b\u001a\f\u0012\u0004\u0012\u00020\t0\bj\u0002`\nJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/UnrepliedCommentsUtils;", "", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "selectedSiteRepository", "Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;", "(Lorg/wordpress/android/fluxc/store/AccountStore;Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;)V", "getUnrepliedComments", "", "Lorg/wordpress/android/fluxc/persistence/comments/CommentsDao$CommentEntity;", "Lorg/wordpress/android/fluxc/persistence/comments/CommentEntityList;", "comments", "isMyComment", "", "comment", "org.wordpress.android_wordpressJalapenoDebug"})
public final class UnrepliedCommentsUtils {
    private final org.wordpress.android.fluxc.store.AccountStore accountStore = null;
    private final org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository = null;
    
    @javax.inject.Inject()
    public UnrepliedCommentsUtils(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore accountStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.Suppress(names = {"NestedBlockDepth"})
    public final java.util.List<org.wordpress.android.fluxc.persistence.comments.CommentsDao.CommentEntity> getUnrepliedComments(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.fluxc.persistence.comments.CommentsDao.CommentEntity> comments) {
        return null;
    }
    
    private final boolean isMyComment(org.wordpress.android.fluxc.persistence.comments.CommentsDao.CommentEntity comment) {
        return false;
    }
}