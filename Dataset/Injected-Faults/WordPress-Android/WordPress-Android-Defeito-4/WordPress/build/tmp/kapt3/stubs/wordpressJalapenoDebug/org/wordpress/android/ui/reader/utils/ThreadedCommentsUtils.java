package org.wordpress.android.ui.reader.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\u0018\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\t\u001a\u00020\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/reader/utils/ThreadedCommentsUtils;", "", "contextProvider", "Lorg/wordpress/android/viewmodel/ContextProvider;", "(Lorg/wordpress/android/viewmodel/ContextProvider;)V", "getContextProvider", "()Lorg/wordpress/android/viewmodel/ContextProvider;", "getMaxWidthForContent", "", "isPrivatePost", "", "post", "Lorg/wordpress/android/models/ReaderPost;", "setLinksClickable", "", "textView", "Landroid/widget/TextView;", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class ThreadedCommentsUtils {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.viewmodel.ContextProvider contextProvider = null;
    
    @javax.inject.Inject()
    public ThreadedCommentsUtils(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ContextProvider contextProvider) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.viewmodel.ContextProvider getContextProvider() {
        return null;
    }
    
    public final int getMaxWidthForContent() {
        return 0;
    }
    
    public final boolean isPrivatePost(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.models.ReaderPost post) {
        return false;
    }
    
    public final void setLinksClickable(@org.jetbrains.annotations.Nullable()
    android.widget.TextView textView, boolean isPrivatePost) {
    }
}