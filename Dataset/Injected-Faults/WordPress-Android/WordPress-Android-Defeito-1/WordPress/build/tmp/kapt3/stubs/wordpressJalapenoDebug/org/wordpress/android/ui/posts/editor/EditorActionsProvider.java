package org.wordpress.android.ui.posts.editor;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bH\u0007J\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/EditorActionsProvider;", "", "()V", "getPrimaryAction", "Lorg/wordpress/android/ui/posts/editor/PrimaryEditorAction;", "postStatus", "Lorg/wordpress/android/fluxc/model/post/PostStatus;", "userCanPublish", "", "isLandingEditor", "getSecondaryAction", "Lorg/wordpress/android/ui/posts/editor/SecondaryEditorAction;", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class EditorActionsProvider {
    
    @javax.inject.Inject()
    public EditorActionsProvider() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmOverloads()
    public final org.wordpress.android.ui.posts.editor.PrimaryEditorAction getPrimaryAction(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.post.PostStatus postStatus, boolean userCanPublish) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmOverloads()
    public final org.wordpress.android.ui.posts.editor.PrimaryEditorAction getPrimaryAction(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.post.PostStatus postStatus, boolean userCanPublish, boolean isLandingEditor) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.posts.editor.SecondaryEditorAction getSecondaryAction(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.post.PostStatus postStatus, boolean userCanPublish) {
        return null;
    }
}