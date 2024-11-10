package org.wordpress.android.ui.utils;

import java.lang.System;

/**
 * Creates BackgroundColorSpan with padding
 * Credits: https://medium.com/@tokudu/android-adding-padding-to-backgroundcolorspan-179ab4fae187
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\r\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J`\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lorg/wordpress/android/ui/utils/PaddingBackgroundColorSpan;", "Landroid/text/style/LineBackgroundSpan;", "backgroundColor", "", "padding", "(II)V", "bgRect", "Landroid/graphics/Rect;", "drawBackground", "", "canvas", "Landroid/graphics/Canvas;", "paint", "Landroid/graphics/Paint;", "left", "right", "top", "baseline", "bottom", "text", "", "start", "end", "lnum", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PaddingBackgroundColorSpan implements android.text.style.LineBackgroundSpan {
    private final int backgroundColor = 0;
    private final int padding = 0;
    private final android.graphics.Rect bgRect = null;
    
    public PaddingBackgroundColorSpan(int backgroundColor, int padding) {
        super();
    }
    
    @java.lang.Override()
    public void drawBackground(@org.jetbrains.annotations.NotNull()
    android.graphics.Canvas canvas, @org.jetbrains.annotations.NotNull()
    android.graphics.Paint paint, int left, int right, int top, int baseline, int bottom, @org.jetbrains.annotations.NotNull()
    java.lang.CharSequence text, int start, int end, int lnum) {
    }
}