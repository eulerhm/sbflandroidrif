package org.wordpress.android.ui.posts.editor;

import java.lang.System;

/**
 * Injectable wrapper around AztecEditorFragment.
 *
 * AppPrefs interface contains some static methods, which make the client code difficult to test/mock. Main purpose of
 * this wrapper is to make testing of these static methods easier.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/AztecEditorFragmentStaticWrapper;", "", "appContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "isMediaInPostBody", "", "postContent", "", "localMediaId", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class AztecEditorFragmentStaticWrapper {
    private final android.content.Context appContext = null;
    
    @javax.inject.Inject()
    public AztecEditorFragmentStaticWrapper(@org.jetbrains.annotations.NotNull()
    android.content.Context appContext) {
        super();
    }
    
    public final boolean isMediaInPostBody(@org.jetbrains.annotations.NotNull()
    java.lang.String postContent, @org.jetbrains.annotations.NotNull()
    java.lang.String localMediaId) {
        return false;
    }
}