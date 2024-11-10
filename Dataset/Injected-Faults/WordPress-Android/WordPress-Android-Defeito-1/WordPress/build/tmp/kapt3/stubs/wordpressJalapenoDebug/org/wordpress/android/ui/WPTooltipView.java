package org.wordpress.android.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u001cB%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\u0017R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0010@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/ui/WPTooltipView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "animationDuration", "arrowHorizontalOffsetFromEnd", "arrowHorizontalOffsetFromEndResId", "arrowHorizontalOffsetFromStart", "arrowHorizontalOffsetFromStartResId", "messageId", "<set-?>", "Lorg/wordpress/android/ui/WPTooltipView$TooltipPosition;", "position", "getPosition", "()Lorg/wordpress/android/ui/WPTooltipView$TooltipPosition;", "tvMessage", "Landroid/widget/TextView;", "hide", "", "setMessage", "message", "", "show", "TooltipPosition", "org.wordpress.android_wordpressJalapenoDebug"})
public final class WPTooltipView extends android.widget.LinearLayout {
    private int messageId = 0;
    private int arrowHorizontalOffsetFromEndResId = 0;
    private int arrowHorizontalOffsetFromStartResId = 0;
    private int arrowHorizontalOffsetFromEnd = -1;
    private int arrowHorizontalOffsetFromStart = -1;
    private int animationDuration;
    private android.widget.TextView tvMessage;
    @org.jetbrains.annotations.NotNull()
    private org.wordpress.android.ui.WPTooltipView.TooltipPosition position = org.wordpress.android.ui.WPTooltipView.TooltipPosition.LEFT;
    
    @kotlin.jvm.JvmOverloads()
    public WPTooltipView(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads()
    public WPTooltipView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads()
    public WPTooltipView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs, int defStyleAttr) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.WPTooltipView.TooltipPosition getPosition() {
        return null;
    }
    
    public final void show() {
    }
    
    public final void hide() {
    }
    
    public final void setMessage(@org.jetbrains.annotations.NotNull()
    java.lang.CharSequence message) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\b\u0086\u0001\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\rB\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/ui/WPTooltipView$TooltipPosition;", "", "value", "", "layout", "(Ljava/lang/String;III)V", "getLayout", "()I", "getValue", "LEFT", "RIGHT", "ABOVE", "BELOW", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum TooltipPosition {
        /*public static final*/ LEFT /* = new LEFT(0, 0) */,
        /*public static final*/ RIGHT /* = new RIGHT(0, 0) */,
        /*public static final*/ ABOVE /* = new ABOVE(0, 0) */,
        /*public static final*/ BELOW /* = new BELOW(0, 0) */;
        private final int value = 0;
        private final int layout = 0;
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.WPTooltipView.TooltipPosition.Companion Companion = null;
        
        TooltipPosition(int value, @androidx.annotation.LayoutRes()
        int layout) {
        }
        
        public final int getValue() {
            return 0;
        }
        
        public final int getLayout() {
            return 0;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/WPTooltipView$TooltipPosition$Companion;", "", "()V", "fromInt", "Lorg/wordpress/android/ui/WPTooltipView$TooltipPosition;", "value", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Companion {
            
            private Companion() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.WPTooltipView.TooltipPosition fromInt(int value) {
                return null;
            }
        }
    }
}