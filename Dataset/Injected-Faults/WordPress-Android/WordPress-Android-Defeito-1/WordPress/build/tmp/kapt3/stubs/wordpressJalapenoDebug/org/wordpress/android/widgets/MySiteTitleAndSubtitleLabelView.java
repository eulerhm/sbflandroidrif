package org.wordpress.android.widgets;

import java.lang.System;

/**
 * Text View used for a site name and url label on My Site fragment.
 * This view works in tandem with autosizing behavior of title textiview so
 * when the title is long, and wraps to the next line text size is reduced and title and subtitle stop being centered.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/widgets/MySiteTitleAndSubtitleLabelView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "subtitle", "Lcom/google/android/material/button/MaterialButton;", "getSubtitle", "()Lcom/google/android/material/button/MaterialButton;", "setSubtitle", "(Lcom/google/android/material/button/MaterialButton;)V", "title", "Lcom/google/android/material/textview/MaterialTextView;", "getTitle", "()Lcom/google/android/material/textview/MaterialTextView;", "setTitle", "(Lcom/google/android/material/textview/MaterialTextView;)V", "org.wordpress.android_wordpressJalapenoDebug"})
public final class MySiteTitleAndSubtitleLabelView extends androidx.constraintlayout.widget.ConstraintLayout {
    @org.jetbrains.annotations.NotNull()
    private com.google.android.material.textview.MaterialTextView title;
    @org.jetbrains.annotations.NotNull()
    private com.google.android.material.button.MaterialButton subtitle;
    
    @kotlin.jvm.JvmOverloads()
    public MySiteTitleAndSubtitleLabelView(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads()
    public MySiteTitleAndSubtitleLabelView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads()
    public MySiteTitleAndSubtitleLabelView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs, int defStyleAttr) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.material.textview.MaterialTextView getTitle() {
        return null;
    }
    
    public final void setTitle(@org.jetbrains.annotations.NotNull()
    com.google.android.material.textview.MaterialTextView p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.material.button.MaterialButton getSubtitle() {
        return null;
    }
    
    public final void setSubtitle(@org.jetbrains.annotations.NotNull()
    com.google.android.material.button.MaterialButton p0) {
    }
}