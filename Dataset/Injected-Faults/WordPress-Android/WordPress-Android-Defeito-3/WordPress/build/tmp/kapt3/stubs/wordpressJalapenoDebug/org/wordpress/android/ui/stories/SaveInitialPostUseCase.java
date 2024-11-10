package org.wordpress.android.ui.stories;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/stories/SaveInitialPostUseCase;", "", "postStore", "Lorg/wordpress/android/fluxc/store/PostStore;", "savePostToDbUseCase", "Lorg/wordpress/android/ui/posts/SavePostToDbUseCase;", "(Lorg/wordpress/android/fluxc/store/PostStore;Lorg/wordpress/android/ui/posts/SavePostToDbUseCase;)V", "getPostStore", "()Lorg/wordpress/android/fluxc/store/PostStore;", "getSavePostToDbUseCase", "()Lorg/wordpress/android/ui/posts/SavePostToDbUseCase;", "saveInitialPost", "", "editPostRepository", "Lorg/wordpress/android/ui/posts/EditPostRepository;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SaveInitialPostUseCase {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.fluxc.store.PostStore postStore = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.posts.SavePostToDbUseCase savePostToDbUseCase = null;
    
    @javax.inject.Inject()
    public SaveInitialPostUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PostStore postStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.SavePostToDbUseCase savePostToDbUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.PostStore getPostStore() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.posts.SavePostToDbUseCase getSavePostToDbUseCase() {
        return null;
    }
    
    public final void saveInitialPost(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.EditPostRepository editPostRepository, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel site) {
    }
}