package org.wordpress.android.ui.reader.discover.viewholders;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\rB\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\tH\u0002\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/viewholders/ReaderInterestsCardViewHolder;", "Lorg/wordpress/android/ui/reader/discover/viewholders/ReaderViewHolder;", "Lorg/wordpress/android/databinding/ReaderInterestCardBinding;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "parentView", "Landroid/view/ViewGroup;", "(Lorg/wordpress/android/ui/utils/UiHelpers;Landroid/view/ViewGroup;)V", "onBind", "", "uiState", "Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState;", "setOnTouchItemListener", "GestureListener", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderInterestsCardViewHolder extends org.wordpress.android.ui.reader.discover.viewholders.ReaderViewHolder<org.wordpress.android.databinding.ReaderInterestCardBinding> {
    
    public ReaderInterestsCardViewHolder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers uiHelpers, @org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parentView) {
        super(null);
    }
    
    @java.lang.Override()
    public void onBind(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.discover.ReaderCardUiState uiState) {
    }
    
    private final void setOnTouchItemListener() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J,\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/viewholders/ReaderInterestsCardViewHolder$GestureListener;", "Landroid/view/GestureDetector$SimpleOnGestureListener;", "(Lorg/wordpress/android/ui/reader/discover/viewholders/ReaderInterestsCardViewHolder;)V", "onDown", "", "e", "Landroid/view/MotionEvent;", "onScroll", "e1", "e2", "distanceX", "", "distanceY", "org.wordpress.android_wordpressJalapenoDebug"})
    final class GestureListener extends android.view.GestureDetector.SimpleOnGestureListener {
        
        public GestureListener() {
            super();
        }
        
        /**
         * Capture the DOWN as soon as it's detected to prevent the viewPager from intercepting touch events
         * We need to do this immediately, because if we don't, then the next move event could potentially
         * trigger the viewPager to switch tabs
         */
        @java.lang.Override()
        public boolean onDown(@org.jetbrains.annotations.Nullable()
        android.view.MotionEvent e) {
            return false;
        }
        
        @java.lang.Override()
        public boolean onScroll(@org.jetbrains.annotations.Nullable()
        android.view.MotionEvent e1, @org.jetbrains.annotations.Nullable()
        android.view.MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }
    }
}