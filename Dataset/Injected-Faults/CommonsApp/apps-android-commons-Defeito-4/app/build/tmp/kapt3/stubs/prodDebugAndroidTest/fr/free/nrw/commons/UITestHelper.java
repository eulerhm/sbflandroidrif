package fr.free.nrw.commons;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2 = {"Lfr/free/nrw/commons/UITestHelper;", "", "()V", "Companion", "app-commons-v4.2.1-master_prodDebug"})
public final class UITestHelper {
    @org.jetbrains.annotations.NotNull
    public static final fr.free.nrw.commons.UITestHelper.Companion Companion = null;
    
    public UITestHelper() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u0004\"\b\b\u0000\u0010\u0005*\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00050\bJ\"\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\r\u001a\u00020\u000eJ\"\u0010\u000f\u001a\n\u0012\u0004\u0012\u0002H\u0005\u0018\u00010\n\"\u0004\b\u0000\u0010\u00052\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00050\nJ\b\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0012H\u0002J\u0006\u0010\u0014\u001a\u00020\u0004J\u0006\u0010\u0015\u001a\u00020\u0004J\u0006\u0010\u0016\u001a\u00020\u0004J\u0006\u0010\u0017\u001a\u00020\u0004J\u000e\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001a\u00a8\u0006\u001b"}, d2 = {"Lfr/free/nrw/commons/UITestHelper$Companion;", "", "()V", "changeOrientation", "", "T", "Landroid/app/Activity;", "activityRule", "Landroidx/test/rule/ActivityTestRule;", "childAtPosition", "Lorg/hamcrest/Matcher;", "Landroid/view/View;", "parentMatcher", "position", "", "first", "matcher", "getTestUserPassword", "", "getTestUsername", "loginUser", "logoutUser", "skipLogin", "skipWelcome", "sleep", "timeInMillis", "", "app-commons-v4.2.1-master_prodDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final void skipWelcome() {
        }
        
        public final void skipLogin() {
        }
        
        public final void loginUser() {
        }
        
        public final void logoutUser() {
        }
        
        @org.jetbrains.annotations.NotNull
        public final org.hamcrest.Matcher<android.view.View> childAtPosition(@org.jetbrains.annotations.NotNull
        org.hamcrest.Matcher<android.view.View> parentMatcher, int position) {
            return null;
        }
        
        public final void sleep(long timeInMillis) {
        }
        
        private final java.lang.String getTestUsername() {
            return null;
        }
        
        private final java.lang.String getTestUserPassword() {
            return null;
        }
        
        public final <T extends android.app.Activity>void changeOrientation(@org.jetbrains.annotations.NotNull
        androidx.test.rule.ActivityTestRule<T> activityRule) {
        }
        
        @org.jetbrains.annotations.Nullable
        public final <T extends java.lang.Object>org.hamcrest.Matcher<T> first(@org.jetbrains.annotations.NotNull
        org.hamcrest.Matcher<T> matcher) {
            return null;
        }
    }
}