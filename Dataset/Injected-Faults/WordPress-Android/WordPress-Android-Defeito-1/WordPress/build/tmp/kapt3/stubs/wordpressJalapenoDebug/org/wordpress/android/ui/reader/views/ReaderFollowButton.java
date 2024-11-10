package org.wordpress.android.ui.reader.views;

import java.lang.System;

/**
 * Follow button used in reader detail
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u001a\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0002J\u000e\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\fJ\u0018\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\fH\u0002J\u000e\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\fJ\b\u0010\u0014\u001a\u00020\u000fH\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/reader/views/ReaderFollowButton;", "Lcom/google/android/material/button/MaterialButton;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "followButtonType", "Lorg/wordpress/android/ui/reader/views/ReaderFollowButtonType;", "isFollowed", "", "showCaption", "hideCaptionAndEnlargeIcon", "", "initView", "setIsFollowed", "animateChanges", "setIsFollowedAnimated", "updateFollowTextAndIcon", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderFollowButton extends com.google.android.material.button.MaterialButton {
    private boolean isFollowed = false;
    private boolean showCaption = false;
    private org.wordpress.android.ui.reader.views.ReaderFollowButtonType followButtonType = org.wordpress.android.ui.reader.views.ReaderFollowButtonType.FOLLOW_SITE;
    
    @kotlin.jvm.JvmOverloads()
    public ReaderFollowButton(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads()
    public ReaderFollowButton(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads()
    public ReaderFollowButton(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs, int defStyleAttr) {
        super(null);
    }
    
    private final void initView(android.content.Context context, android.util.AttributeSet attrs) {
    }
    
    private final void hideCaptionAndEnlargeIcon(android.content.Context context) {
    }
    
    private final void updateFollowTextAndIcon() {
    }
    
    public final void setIsFollowed(boolean isFollowed) {
    }
    
    public final void setIsFollowedAnimated(boolean isFollowed) {
    }
    
    private final void setIsFollowed(boolean isFollowed, boolean animateChanges) {
    }
}