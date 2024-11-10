package org.wordpress.android.ui.prefs.notifications;

import java.lang.System;

/**
 * Custom view for main switch in toolbar for preferences.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0016\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0002<=B/\b\u0007\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0002\u0010\fJ\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0011J\u0010\u0010!\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u001a\u0010\"\u001a\u00020\u001f2\b\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020\u0011H\u0016J\u0012\u0010&\u001a\u00020\u001f2\b\u0010\'\u001a\u0004\u0018\u00010(H\u0016J\u0012\u0010)\u001a\u00020\u00112\b\u0010\'\u001a\u0004\u0018\u00010(H\u0016J\u0012\u0010*\u001a\u00020\u001f2\b\b\u0001\u0010+\u001a\u00020\nH\u0016J\u000e\u0010,\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020\u0011J\u000e\u0010-\u001a\u00020\u001f2\u0006\u0010.\u001a\u00020\nJ\u0010\u0010/\u001a\u00020\u001f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u0010\u00100\u001a\u00020\u001f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eJ\u000e\u00101\u001a\u00020\u001f2\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u00102\u001a\u00020\u001f2\u0006\u00103\u001a\u00020\nJ\u0010\u00104\u001a\u00020\u001f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u000eJ\u0010\u00105\u001a\u00020\u001f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u000eJ\u0010\u00106\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0011H\u0002J\u000e\u00107\u001a\u00020\u001f2\u0006\u00108\u001a\u00020\nJ\b\u00109\u001a\u00020\u001fH\u0002J\u0006\u0010:\u001a\u00020\u001fJ\u000e\u0010;\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020\u001dR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0010\u001a\u00020\u00118F\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006>"}, d2 = {"Lorg/wordpress/android/ui/prefs/notifications/PrefMainSwitchToolbarView;", "Landroid/widget/LinearLayout;", "Landroid/widget/CompoundButton$OnCheckedChangeListener;", "Landroid/view/View$OnLongClickListener;", "Landroid/view/View$OnClickListener;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyle", "", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "hintOff", "", "hintOn", "isMainChecked", "", "()Z", "mainSwitch", "Landroidx/appcompat/widget/SwitchCompat;", "mainSwitchToolbarListener", "Lorg/wordpress/android/ui/prefs/notifications/PrefMainSwitchToolbarView$MainSwitchToolbarListener;", "titleContentDescription", "titleOff", "titleOn", "toolbarSwitch", "Landroidx/appcompat/widget/Toolbar;", "viewStyle", "Lorg/wordpress/android/ui/prefs/notifications/PrefMainSwitchToolbarView$PrefMainSwitchToolbarViewStyle;", "loadInitialState", "", "checkMain", "loadResourcesForViewStyle", "onCheckedChanged", "buttonView", "Landroid/widget/CompoundButton;", "isChecked", "onClick", "v", "Landroid/view/View;", "onLongClick", "setBackgroundColor", "color", "setChecked", "setContentOffset", "offset", "setHintOff", "setHintOn", "setMainSwitchToolbarListener", "setOffsetEnd", "offsetEnd", "setTitleOff", "setTitleOn", "setToolbarTitle", "setViewStyle", "viewStyleInt", "setupFocusabilityForTalkBack", "updateToolbarSwitchForAccessibility", "updateViewStyle", "MainSwitchToolbarListener", "PrefMainSwitchToolbarViewStyle", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PrefMainSwitchToolbarView extends android.widget.LinearLayout implements android.widget.CompoundButton.OnCheckedChangeListener, android.view.View.OnLongClickListener, android.view.View.OnClickListener {
    private java.lang.String hintOn;
    private java.lang.String hintOff;
    private androidx.appcompat.widget.SwitchCompat mainSwitch;
    private org.wordpress.android.ui.prefs.notifications.PrefMainSwitchToolbarView.MainSwitchToolbarListener mainSwitchToolbarListener;
    private androidx.appcompat.widget.Toolbar toolbarSwitch;
    private java.lang.String titleOff;
    private java.lang.String titleOn;
    private org.wordpress.android.ui.prefs.notifications.PrefMainSwitchToolbarView.PrefMainSwitchToolbarViewStyle viewStyle;
    private java.lang.String titleContentDescription;
    
    @kotlin.jvm.JvmOverloads()
    public PrefMainSwitchToolbarView(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads()
    public PrefMainSwitchToolbarView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads()
    public PrefMainSwitchToolbarView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs, int defStyle) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads()
    public PrefMainSwitchToolbarView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs, int defStyle, int defStyleRes) {
        super(null);
    }
    
    public final boolean isMainChecked() {
        return false;
    }
    
    @kotlin.Suppress(names = {"MemberVisibilityCanBePrivate"})
    public final void updateToolbarSwitchForAccessibility() {
    }
    
    @kotlin.Suppress(names = {"MemberVisibilityCanBePrivate"})
    public final void setContentOffset(int offset) {
    }
    
    /**
     * Applies end padding to the switch menu
     */
    @kotlin.Suppress(names = {"MemberVisibilityCanBePrivate"})
    public final void setOffsetEnd(int offsetEnd) {
    }
    
    private final void setupFocusabilityForTalkBack() {
    }
    
    /**
     * Loads initial state of the main switch and toolbar
     */
    public final void loadInitialState(boolean checkMain) {
    }
    
    @kotlin.Suppress(names = {"MemberVisibilityCanBePrivate"})
    public final void setTitleOn(@org.jetbrains.annotations.Nullable()
    java.lang.String titleOn) {
    }
    
    @kotlin.Suppress(names = {"MemberVisibilityCanBePrivate"})
    public final void setTitleOff(@org.jetbrains.annotations.Nullable()
    java.lang.String titleOff) {
    }
    
    @kotlin.Suppress(names = {"MemberVisibilityCanBePrivate"})
    public final void setHintOn(@org.jetbrains.annotations.Nullable()
    java.lang.String hintOn) {
    }
    
    @kotlin.Suppress(names = {"MemberVisibilityCanBePrivate"})
    public final void setHintOff(@org.jetbrains.annotations.Nullable()
    java.lang.String hintOff) {
    }
    
    @kotlin.Suppress(names = {"MemberVisibilityCanBePrivate"})
    public final void setViewStyle(int viewStyleInt) {
    }
    
    @kotlin.Suppress(names = {"MemberVisibilityCanBePrivate"})
    public final void updateViewStyle(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.notifications.PrefMainSwitchToolbarView.PrefMainSwitchToolbarViewStyle viewStyle) {
    }
    
    private final void loadResourcesForViewStyle(org.wordpress.android.ui.prefs.notifications.PrefMainSwitchToolbarView.PrefMainSwitchToolbarViewStyle viewStyle) {
    }
    
    @java.lang.Override()
    public void setBackgroundColor(@androidx.annotation.ColorInt()
    int color) {
    }
    
    @java.lang.Override()
    public boolean onLongClick(@org.jetbrains.annotations.Nullable()
    android.view.View v) {
        return false;
    }
    
    @java.lang.Override()
    public void onClick(@org.jetbrains.annotations.Nullable()
    android.view.View v) {
    }
    
    public final void setChecked(boolean isChecked) {
    }
    
    private final void setToolbarTitle(boolean checkMain) {
    }
    
    @java.lang.Override()
    public void onCheckedChanged(@org.jetbrains.annotations.Nullable()
    android.widget.CompoundButton buttonView, boolean isChecked) {
    }
    
    public final void setMainSwitchToolbarListener(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.notifications.PrefMainSwitchToolbarView.MainSwitchToolbarListener mainSwitchToolbarListener) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\b\u0086\u0001\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\rB#\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0006R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bj\u0002\b\u000bj\u0002\b\f\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/ui/prefs/notifications/PrefMainSwitchToolbarView$PrefMainSwitchToolbarViewStyle;", "", "value", "", "titleColorResId", "backgroundColorResId", "(Ljava/lang/String;IIII)V", "getBackgroundColorResId", "()I", "getTitleColorResId", "getValue", "HIGHLIGHTED", "NORMAL", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum PrefMainSwitchToolbarViewStyle {
        /*public static final*/ HIGHLIGHTED /* = new HIGHLIGHTED(0, 0, 0) */,
        /*public static final*/ NORMAL /* = new NORMAL(0, 0, 0) */;
        private final int value = 0;
        private final int titleColorResId = 0;
        private final int backgroundColorResId = 0;
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.prefs.notifications.PrefMainSwitchToolbarView.PrefMainSwitchToolbarViewStyle.Companion Companion = null;
        
        PrefMainSwitchToolbarViewStyle(int value, @androidx.annotation.AttrRes()
        int titleColorResId, @androidx.annotation.AttrRes()
        int backgroundColorResId) {
        }
        
        public final int getValue() {
            return 0;
        }
        
        public final int getTitleColorResId() {
            return 0;
        }
        
        public final int getBackgroundColorResId() {
            return 0;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/prefs/notifications/PrefMainSwitchToolbarView$PrefMainSwitchToolbarViewStyle$Companion;", "", "()V", "fromInt", "Lorg/wordpress/android/ui/prefs/notifications/PrefMainSwitchToolbarView$PrefMainSwitchToolbarViewStyle;", "value", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Companion {
            
            private Companion() {
                super();
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.prefs.notifications.PrefMainSwitchToolbarView.PrefMainSwitchToolbarViewStyle fromInt(int value) {
                return null;
            }
        }
    }
    
    /**
     * Interface definition for callbacks to be invoked on interaction with main switch toolbar.
     */
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/ui/prefs/notifications/PrefMainSwitchToolbarView$MainSwitchToolbarListener;", "", "onMainSwitchCheckedChanged", "", "buttonView", "Landroid/widget/CompoundButton;", "isChecked", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract interface MainSwitchToolbarListener {
        
        /**
         * Called when the checked state of main switch is changed.
         *
         * @param buttonView The main switch whose state has changed.
         * @param isChecked The new checked state of main switch.
         */
        public abstract void onMainSwitchCheckedChanged(@org.jetbrains.annotations.Nullable()
        android.widget.CompoundButton buttonView, boolean isChecked);
    }
}