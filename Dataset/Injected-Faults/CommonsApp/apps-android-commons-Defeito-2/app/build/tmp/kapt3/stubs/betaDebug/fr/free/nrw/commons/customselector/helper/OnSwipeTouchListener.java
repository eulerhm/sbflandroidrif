package fr.free.nrw.commons.customselector.helper;

import java.lang.System;

/**
 * Class for detecting swipe gestures
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001:\u0001\u0018B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\f2\u0006\u0010\u0002\u001a\u00020\u0003J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\b\u0010\u0010\u001a\u00020\u000eH\u0016J\b\u0010\u0011\u001a\u00020\u000eH\u0016J\u001a\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lfr/free/nrw/commons/customselector/helper/OnSwipeTouchListener;", "Landroid/view/View$OnTouchListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "SWIPE_THRESHOLD_HEIGHT", "", "SWIPE_THRESHOLD_WIDTH", "SWIPE_VELOCITY_THRESHOLD", "gestureDetector", "Landroid/view/GestureDetector;", "getScreenResolution", "Lkotlin/Pair;", "onSwipeDown", "", "onSwipeLeft", "onSwipeRight", "onSwipeUp", "onTouch", "", "view", "Landroid/view/View;", "motionEvent", "Landroid/view/MotionEvent;", "GestureListener", "app-commons-v4.2.1-main_betaDebug"})
public class OnSwipeTouchListener implements android.view.View.OnTouchListener {
    private final android.view.GestureDetector gestureDetector = null;
    private final int SWIPE_THRESHOLD_HEIGHT = 0;
    private final int SWIPE_THRESHOLD_WIDTH = 0;
    private final int SWIPE_VELOCITY_THRESHOLD = 1000;
    
    public OnSwipeTouchListener(@org.jetbrains.annotations.Nullable
    android.content.Context context) {
        super();
    }
    
    @java.lang.Override
    public boolean onTouch(@org.jetbrains.annotations.Nullable
    android.view.View view, @org.jetbrains.annotations.NotNull
    android.view.MotionEvent motionEvent) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlin.Pair<java.lang.Integer, java.lang.Integer> getScreenResolution(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        return null;
    }
    
    /**
     * Swipe right to view previous image
     */
    public void onSwipeRight() {
    }
    
    /**
     * Swipe left to view next image
     */
    public void onSwipeLeft() {
    }
    
    /**
     * Swipe up to select the picture (the equivalent of tapping it in non-fullscreen mode)
     * and show the next picture skipping pictures that have either already been uploaded or
     * marked as not for upload
     */
    public void onSwipeUp() {
    }
    
    /**
     * Swipe down to mark that picture as "Not for upload" (the equivalent of selecting it then
     * tapping "Mark as not for upload" in non-fullscreen mode), and show the next picture.
     */
    public void onSwipeDown() {
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J(\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016\u00a8\u0006\r"}, d2 = {"Lfr/free/nrw/commons/customselector/helper/OnSwipeTouchListener$GestureListener;", "Landroid/view/GestureDetector$SimpleOnGestureListener;", "(Lfr/free/nrw/commons/customselector/helper/OnSwipeTouchListener;)V", "onDown", "", "e", "Landroid/view/MotionEvent;", "onFling", "event1", "event2", "velocityX", "", "velocityY", "app-commons-v4.2.1-main_betaDebug"})
    public final class GestureListener extends android.view.GestureDetector.SimpleOnGestureListener {
        
        public GestureListener() {
            super();
        }
        
        @java.lang.Override
        public boolean onDown(@org.jetbrains.annotations.NotNull
        android.view.MotionEvent e) {
            return false;
        }
        
        /**
         * Detects the gestures
         */
        @java.lang.Override
        public boolean onFling(@org.jetbrains.annotations.NotNull
        android.view.MotionEvent event1, @org.jetbrains.annotations.NotNull
        android.view.MotionEvent event2, float velocityX, float velocityY) {
            return false;
        }
    }
}