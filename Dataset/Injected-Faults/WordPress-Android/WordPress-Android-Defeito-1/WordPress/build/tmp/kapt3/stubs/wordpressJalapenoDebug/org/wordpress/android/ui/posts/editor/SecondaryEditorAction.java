package org.wordpress.android.ui.posts.editor;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001b\b\u0002\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0007R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/SecondaryEditorAction;", "", "titleResource", "", "isVisible", "", "(Ljava/lang/String;ILjava/lang/Integer;Z)V", "()Z", "getTitleResource", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "SAVE_AS_DRAFT", "SAVE", "PUBLISH_NOW", "NONE", "org.wordpress.android_wordpressJalapenoDebug"})
public enum SecondaryEditorAction {
    /*public static final*/ SAVE_AS_DRAFT /* = new SAVE_AS_DRAFT(null, false) */,
    /*public static final*/ SAVE /* = new SAVE(null, false) */,
    /*public static final*/ PUBLISH_NOW /* = new PUBLISH_NOW(null, false) */,
    /*public static final*/ NONE /* = new NONE(null, false) */;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer titleResource = null;
    private final boolean isVisible = false;
    
    SecondaryEditorAction(@androidx.annotation.StringRes()
    java.lang.Integer titleResource, boolean isVisible) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getTitleResource() {
        return null;
    }
    
    public final boolean isVisible() {
        return false;
    }
}