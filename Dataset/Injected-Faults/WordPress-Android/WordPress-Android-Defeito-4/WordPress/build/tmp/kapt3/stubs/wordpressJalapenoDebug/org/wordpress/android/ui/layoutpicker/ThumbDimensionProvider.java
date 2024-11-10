package org.wordpress.android.ui.layoutpicker;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0012\u0010\b\u001a\u00020\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\u0005R\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\r\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/ui/layoutpicker/ThumbDimensionProvider;", "", "previewHeight", "", "getPreviewHeight", "()I", "previewWidth", "getPreviewWidth", "rowHeight", "getRowHeight", "scale", "", "getScale", "()D", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract interface ThumbDimensionProvider {
    
    public abstract int getPreviewWidth();
    
    public abstract int getPreviewHeight();
    
    public abstract int getRowHeight();
    
    public abstract double getScale();
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
    public final class DefaultImpls {
        
        public static double getScale(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.layoutpicker.ThumbDimensionProvider $this) {
            return 0.0;
        }
    }
}