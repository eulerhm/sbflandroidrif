package com.github.pockethub.android.ui.issue;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.PaintDrawable;
import android.text.style.DynamicDrawableSpan;
import android.widget.TextView;
import com.github.pockethub.android.R;
import com.github.pockethub.android.util.ServiceUtils;
import com.meisolsson.githubsdk.model.Label;
import java.util.*;

/**
 * Span that draws a [Label]
 *
 * @constructor Create background span for label
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00112\u00020\u0001:\u0002\u0011\u0012B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\rJ\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/github/pockethub/android/ui/issue/LabelDrawableSpan;", "Landroid/text/style/DynamicDrawableSpan;", "resources", "Landroid/content/res/Resources;", "textSize", "", "color", "", "paddingLeft", "textHeight", "bounds", "Landroid/graphics/Rect;", "name", "(Landroid/content/res/Resources;FLjava/lang/String;FFLandroid/graphics/Rect;Ljava/lang/String;)V", "", "getDrawable", "Landroid/graphics/drawable/Drawable;", "Companion", "LabelDrawable", "app_debug"})
public final class LabelDrawableSpan extends android.text.style.DynamicDrawableSpan {
    private final int color = 0;
    private final android.content.res.Resources resources = null;
    private final float textSize = 0.0F;
    private final float paddingLeft = 0.0F;
    private final float textHeight = 0.0F;
    private final android.graphics.Rect bounds = null;
    private final java.lang.String name = null;
    public static final com.github.pockethub.android.ui.issue.LabelDrawableSpan.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.graphics.drawable.Drawable getDrawable() {
        return null;
    }
    
    public LabelDrawableSpan(@org.jetbrains.annotations.NotNull()
    android.content.res.Resources resources, float textSize, @org.jetbrains.annotations.NotNull()
    java.lang.String color, float paddingLeft, float textHeight, @org.jetbrains.annotations.NotNull()
    android.graphics.Rect bounds, @org.jetbrains.annotations.NotNull()
    java.lang.String name) {
        super();
    }
    
    /**
     * Set text on view to be given labels
     *
     * @param view
     * @param labels
     */
    public static final void setText(@org.jetbrains.annotations.NotNull()
    android.widget.TextView view, @org.jetbrains.annotations.NotNull()
    java.util.Collection<? extends com.meisolsson.githubsdk.model.Label> labels) {
    }
    
    /**
     * Set text on view to be given label
     *
     * @param view
     * @param label
     */
    public static final void setText(@org.jetbrains.annotations.NotNull()
    android.widget.TextView view, @org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.model.Label label) {
    }
    
    /**
     * @constructor Create drawable for labels
     */
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\u000e\u0010\u000f\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/github/pockethub/android/ui/issue/LabelDrawableSpan$LabelDrawable;", "Landroid/graphics/drawable/PaintDrawable;", "paddingLeft", "", "textHeight", "bounds", "Landroid/graphics/Rect;", "resources", "Landroid/content/res/Resources;", "textSize", "name", "", "bg", "", "(FFLandroid/graphics/Rect;Landroid/content/res/Resources;FLjava/lang/String;I)V", "height", "layers", "Landroid/graphics/drawable/LayerDrawable;", "textColor", "draw", "", "canvas", "Landroid/graphics/Canvas;", "app_debug"})
    static final class LabelDrawable extends android.graphics.drawable.PaintDrawable {
        private final float height = 0.0F;
        private final int textColor = 0;
        private final android.graphics.drawable.LayerDrawable layers = null;
        private final float paddingLeft = 0.0F;
        private final float textHeight = 0.0F;
        private final java.lang.String name = null;
        
        @java.lang.Override()
        public void draw(@org.jetbrains.annotations.NotNull()
        android.graphics.Canvas canvas) {
        }
        
        public LabelDrawable(float paddingLeft, float textHeight, @org.jetbrains.annotations.NotNull()
        android.graphics.Rect bounds, @org.jetbrains.annotations.NotNull()
        android.content.res.Resources resources, float textSize, @org.jetbrains.annotations.NotNull()
        java.lang.String name, int bg) {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u001e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J#\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nH\u0002\u00a2\u0006\u0002\u0010\u000bJ\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\fH\u0007\u00a8\u0006\r"}, d2 = {"Lcom/github/pockethub/android/ui/issue/LabelDrawableSpan$Companion;", "", "()V", "setText", "", "view", "Landroid/widget/TextView;", "label", "Lcom/meisolsson/githubsdk/model/Label;", "labels", "", "(Landroid/widget/TextView;[Lcom/meisolsson/githubsdk/model/Label;)V", "", "app_debug"})
    public static final class Companion {
        
        /**
         * Set text on view to be given labels
         *
         * @param view
         * @param labels
         */
        public final void setText(@org.jetbrains.annotations.NotNull()
        android.widget.TextView view, @org.jetbrains.annotations.NotNull()
        java.util.Collection<? extends com.meisolsson.githubsdk.model.Label> labels) {
        }
        
        /**
         * Set text on view to be given label
         *
         * @param view
         * @param label
         */
        public final void setText(@org.jetbrains.annotations.NotNull()
        android.widget.TextView view, @org.jetbrains.annotations.NotNull()
        com.meisolsson.githubsdk.model.Label label) {
        }
        
        private final void setText(android.widget.TextView view, com.meisolsson.githubsdk.model.Label[] labels) {
        }
        
        private Companion() {
            super();
        }
    }
}