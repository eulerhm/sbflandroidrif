package org.wordpress.android.support;

import java.lang.System;

/**
 * ScrollToAction that supports scrolling of all kinds of scrollable containers, including nested ones.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016J\u0011\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tH\u0096\u0001J)\u0010\u000b\u001a\u00020\f2\u000e\u0010\r\u001a\n \n*\u0004\u0018\u00010\u000e0\u000e2\u000e\u0010\u000f\u001a\n \n*\u0004\u0018\u00010\u00070\u0007H\u0096\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/support/BetterScrollToAction;", "Landroidx/test/espresso/ViewAction;", "original", "Landroidx/test/espresso/action/ScrollToAction;", "(Landroidx/test/espresso/action/ScrollToAction;)V", "getConstraints", "Lorg/hamcrest/Matcher;", "Landroid/view/View;", "getDescription", "", "kotlin.jvm.PlatformType", "perform", "", "p0", "Landroidx/test/espresso/UiController;", "p1", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class BetterScrollToAction implements androidx.test.espresso.ViewAction {
    private final androidx.test.espresso.action.ScrollToAction original = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.support.BetterScrollToAction.Companion Companion = null;
    
    public BetterScrollToAction() {
        super();
    }
    
    public BetterScrollToAction(@org.jetbrains.annotations.NotNull()
    androidx.test.espresso.action.ScrollToAction original) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.hamcrest.Matcher<android.view.View> getConstraints() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final androidx.test.espresso.ViewAction scrollTo() {
        return null;
    }
    
    @java.lang.Override()
    public java.lang.String getDescription() {
        return null;
    }
    
    @java.lang.Override()
    public void perform(androidx.test.espresso.UiController p0, android.view.View p1) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/support/BetterScrollToAction$Companion;", "", "()V", "scrollTo", "Landroidx/test/espresso/ViewAction;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final androidx.test.espresso.ViewAction scrollTo() {
            return null;
        }
    }
}