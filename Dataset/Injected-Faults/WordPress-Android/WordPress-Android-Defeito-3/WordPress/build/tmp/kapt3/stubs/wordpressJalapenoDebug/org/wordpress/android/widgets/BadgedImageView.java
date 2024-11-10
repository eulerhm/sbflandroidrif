package org.wordpress.android.widgets;

import java.lang.System;

/**
 * A ImageView that can draw a badge at the corner of its view.
 * The main difference between this implementation and others commonly found online, is that this one uses
 * Porter/Duff Compositing to create a transparent space between the badge background and the view.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0012\u0018\u0000 @2\u00020\u0001:\u0001@B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0010\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020+H\u0002J\u0010\u00101\u001a\u00020/2\u0006\u00100\u001a\u00020+H\u0002J \u00102\u001a\u00020/2\u0006\u00100\u001a\u00020+2\u0006\u00103\u001a\u00020\u00102\u0006\u00104\u001a\u00020\u0010H\u0002J \u00105\u001a\u00020/2\u0006\u00100\u001a\u00020+2\u0006\u00103\u001a\u00020\u00102\u0006\u00104\u001a\u00020\u0010H\u0002J \u00106\u001a\u00020/2\u0006\u00100\u001a\u00020+2\u0006\u00103\u001a\u00020\u00102\u0006\u00104\u001a\u00020\u0010H\u0002J\b\u00107\u001a\u00020/H\u0016J\u0010\u00108\u001a\u00020/2\u0006\u00100\u001a\u00020+H\u0014J(\u00109\u001a\u00020/2\u0006\u0010:\u001a\u00020\u00072\u0006\u0010;\u001a\u00020\u00072\u0006\u0010<\u001a\u00020\u00072\u0006\u0010=\u001a\u00020\u0007H\u0014J\u0010\u0010\u000e\u001a\u00020/2\b\b\u0001\u0010>\u001a\u00020\u0007J\u0010\u0010\u001e\u001a\u00020/2\b\b\u0001\u0010?\u001a\u00020\u0007R(\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0011\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R$\u0010\u0016\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0013\"\u0004\b\u0018\u0010\u0015R$\u0010\u0019\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0013\"\u0004\b\u001b\u0010\u0015R(\u0010\u001c\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\r\"\u0004\b\u001e\u0010\u000fR$\u0010\u001f\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0013\"\u0004\b!\u0010\u0015R$\u0010\"\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0013\"\u0004\b$\u0010\u0015R\u000e\u0010%\u001a\u00020&X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\'\u001a\u00020(X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020&X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u0004\u0018\u00010-X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006A"}, d2 = {"Lorg/wordpress/android/widgets/BadgedImageView;", "Landroidx/appcompat/widget/AppCompatImageView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "value", "Landroid/graphics/drawable/Drawable;", "badgeBackground", "getBadgeBackground", "()Landroid/graphics/drawable/Drawable;", "setBadgeBackground", "(Landroid/graphics/drawable/Drawable;)V", "", "badgeBackgroundBorderWidth", "getBadgeBackgroundBorderWidth", "()F", "setBadgeBackgroundBorderWidth", "(F)V", "badgeBackgroundSize", "getBadgeBackgroundSize", "setBadgeBackgroundSize", "badgeHorizontalOffset", "getBadgeHorizontalOffset", "setBadgeHorizontalOffset", "badgeIcon", "getBadgeIcon", "setBadgeIcon", "badgeIconSize", "getBadgeIconSize", "setBadgeIconSize", "badgeVerticalOffset", "getBadgeVerticalOffset", "setBadgeVerticalOffset", "eraserPaint", "Landroid/graphics/Paint;", "invalidated", "", "paint", "tempCanvas", "Landroid/graphics/Canvas;", "tempCanvasBitmap", "Landroid/graphics/Bitmap;", "clearCanvas", "", "canvas", "drawBadge", "drawBadgeBackground", "x", "y", "drawBadgeIcon", "drawBadgeSpace", "invalidate", "onDraw", "onSizeChanged", "width", "height", "oldWidht", "oldHeight", "badgeBackgroundResId", "badgeIconResId", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class BadgedImageView extends androidx.appcompat.widget.AppCompatImageView {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.widgets.BadgedImageView.Companion Companion = null;
    public static final float DEFAULT_BADGE_BACKGROUND_SIZE = 16.0F;
    public static final float DEFAULT_BADGE_BACKGROUND_BORDER_WIDTH = 0.0F;
    public static final float DEFAULT_BADGE_ICON_SIZE = 16.0F;
    public static final float DEFAULT_BADGE_HORIZONTAL_OFFSET = 0.0F;
    public static final float DEFAULT_BADGE_VERTICAL_OFFSET = 0.0F;
    @org.jetbrains.annotations.Nullable()
    private android.graphics.drawable.Drawable badgeBackground;
    private float badgeBackgroundSize = 0.0F;
    private float badgeBackgroundBorderWidth = 0.0F;
    @org.jetbrains.annotations.Nullable()
    private android.graphics.drawable.Drawable badgeIcon;
    private float badgeIconSize = 0.0F;
    private float badgeHorizontalOffset = 0.0F;
    private float badgeVerticalOffset = 0.0F;
    private final android.graphics.Paint paint = null;
    private final android.graphics.Paint eraserPaint = null;
    private android.graphics.Bitmap tempCanvasBitmap;
    private android.graphics.Canvas tempCanvas;
    private boolean invalidated = true;
    
    @kotlin.jvm.JvmOverloads()
    public BadgedImageView(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads()
    public BadgedImageView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads()
    public BadgedImageView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs, int defStyleAttr) {
        super(null);
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.graphics.drawable.Drawable getBadgeBackground() {
        return null;
    }
    
    public final void setBadgeBackground(@org.jetbrains.annotations.Nullable()
    android.graphics.drawable.Drawable value) {
    }
    
    public final float getBadgeBackgroundSize() {
        return 0.0F;
    }
    
    public final void setBadgeBackgroundSize(float value) {
    }
    
    public final float getBadgeBackgroundBorderWidth() {
        return 0.0F;
    }
    
    public final void setBadgeBackgroundBorderWidth(float value) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.graphics.drawable.Drawable getBadgeIcon() {
        return null;
    }
    
    public final void setBadgeIcon(@org.jetbrains.annotations.Nullable()
    android.graphics.drawable.Drawable value) {
    }
    
    public final float getBadgeIconSize() {
        return 0.0F;
    }
    
    public final void setBadgeIconSize(float value) {
    }
    
    public final float getBadgeHorizontalOffset() {
        return 0.0F;
    }
    
    public final void setBadgeHorizontalOffset(float value) {
    }
    
    public final float getBadgeVerticalOffset() {
        return 0.0F;
    }
    
    public final void setBadgeVerticalOffset(float value) {
    }
    
    public final void setBadgeBackground(@androidx.annotation.DrawableRes()
    int badgeBackgroundResId) {
    }
    
    public final void setBadgeIcon(@androidx.annotation.DrawableRes()
    int badgeIconResId) {
    }
    
    @java.lang.Override()
    public void invalidate() {
    }
    
    @java.lang.Override()
    protected void onSizeChanged(int width, int height, int oldWidht, int oldHeight) {
    }
    
    @java.lang.Override()
    protected void onDraw(@org.jetbrains.annotations.NotNull()
    android.graphics.Canvas canvas) {
    }
    
    private final void clearCanvas(android.graphics.Canvas canvas) {
    }
    
    private final void drawBadge(android.graphics.Canvas canvas) {
    }
    
    private final void drawBadgeSpace(android.graphics.Canvas canvas, float x, float y) {
    }
    
    private final void drawBadgeBackground(android.graphics.Canvas canvas, float x, float y) {
    }
    
    private final void drawBadgeIcon(android.graphics.Canvas canvas, float x, float y) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/widgets/BadgedImageView$Companion;", "", "()V", "DEFAULT_BADGE_BACKGROUND_BORDER_WIDTH", "", "DEFAULT_BADGE_BACKGROUND_SIZE", "DEFAULT_BADGE_HORIZONTAL_OFFSET", "DEFAULT_BADGE_ICON_SIZE", "DEFAULT_BADGE_VERTICAL_OFFSET", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}