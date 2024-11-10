package org.wordpress.android.ui.bloggingreminders;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J<\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eH\u0007J<\u0010\u0010\u001a\u00020\u00042\u0012\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eH\u0007\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingReminderUtils;", "", "()V", "observeBottomSheet", "", "isBottomSheetShowing", "Landroidx/lifecycle/LiveData;", "Lorg/wordpress/android/viewmodel/Event;", "", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "tag", "", "getSupportFragmentManager", "Lkotlin/Function0;", "Landroidx/fragment/app/FragmentManager;", "observeTimePicker", "isTimePickerShowing", "org.wordpress.android_wordpressJalapenoDebug"})
public final class BloggingReminderUtils {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.bloggingreminders.BloggingReminderUtils INSTANCE = null;
    
    private BloggingReminderUtils() {
        super();
    }
    
    @kotlin.jvm.JvmStatic()
    public static final void observeBottomSheet(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Boolean>> isBottomSheetShowing, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LifecycleOwner lifecycleOwner, @org.jetbrains.annotations.NotNull()
    java.lang.String tag, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<? extends androidx.fragment.app.FragmentManager> getSupportFragmentManager) {
    }
    
    @kotlin.jvm.JvmStatic()
    public static final void observeTimePicker(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Boolean>> isTimePickerShowing, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LifecycleOwner lifecycleOwner, @org.jetbrains.annotations.NotNull()
    java.lang.String tag, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<? extends androidx.fragment.app.FragmentManager> getSupportFragmentManager) {
    }
}