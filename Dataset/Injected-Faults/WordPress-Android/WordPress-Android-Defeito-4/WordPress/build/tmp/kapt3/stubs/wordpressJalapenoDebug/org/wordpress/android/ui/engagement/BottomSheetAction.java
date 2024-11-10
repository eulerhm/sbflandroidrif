package org.wordpress.android.ui.engagement;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/engagement/BottomSheetAction;", "", "()V", "HideBottomSheet", "ShowBottomSheet", "Lorg/wordpress/android/ui/engagement/BottomSheetAction$ShowBottomSheet;", "Lorg/wordpress/android/ui/engagement/BottomSheetAction$HideBottomSheet;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class BottomSheetAction {
    
    private BottomSheetAction() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/engagement/BottomSheetAction$ShowBottomSheet;", "Lorg/wordpress/android/ui/engagement/BottomSheetAction;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ShowBottomSheet extends org.wordpress.android.ui.engagement.BottomSheetAction {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.engagement.BottomSheetAction.ShowBottomSheet INSTANCE = null;
        
        private ShowBottomSheet() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/engagement/BottomSheetAction$HideBottomSheet;", "Lorg/wordpress/android/ui/engagement/BottomSheetAction;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class HideBottomSheet extends org.wordpress.android.ui.engagement.BottomSheetAction {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.engagement.BottomSheetAction.HideBottomSheet INSTANCE = null;
        
        private HideBottomSheet() {
            super();
        }
    }
}