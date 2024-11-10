package com.google.android.gnd.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0007J\b\u0010\u0014\u001a\u00020\u0013H\u0007J\b\u0010\u0015\u001a\u00020\u0013H\u0017J\u0018\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/google/android/gnd/ui/MarkerIconFactoryTest;", "Lcom/google/android/gnd/BaseHiltTest;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "markerIconFactory", "Lcom/google/android/gnd/ui/MarkerIconFactory;", "getMarkerIconFactory", "()Lcom/google/android/gnd/ui/MarkerIconFactory;", "setMarkerIconFactory", "(Lcom/google/android/gnd/ui/MarkerIconFactory;)V", "markerUnscaledHeight", "", "markerUnscaledWidth", "markerBitmap_zoomedIn_scaleIsSetCorrectly", "", "markerBitmap_zoomedOut_scaleIsSetCorrectly", "setUp", "verifyBitmapScale", "bitmap", "Landroid/graphics/Bitmap;", "scale", "", "gnd_debug"})
@org.junit.runner.RunWith(value = org.robolectric.RobolectricTestRunner.class)
@dagger.hilt.android.testing.HiltAndroidTest()
public final class MarkerIconFactoryTest extends com.google.android.gnd.BaseHiltTest {
    @dagger.hilt.android.qualifiers.ApplicationContext()
    @javax.inject.Inject()
    public android.content.Context context;
    @javax.inject.Inject()
    public com.google.android.gnd.ui.MarkerIconFactory markerIconFactory;
    private int markerUnscaledWidth = 0;
    private int markerUnscaledHeight = 0;
    
    public MarkerIconFactoryTest() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    public final void setContext(@org.jetbrains.annotations.NotNull()
    android.content.Context p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gnd.ui.MarkerIconFactory getMarkerIconFactory() {
        return null;
    }
    
    public final void setMarkerIconFactory(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.ui.MarkerIconFactory p0) {
    }
    
    @java.lang.Override()
    @org.junit.Before()
    public void setUp() {
    }
    
    @org.junit.Test()
    public final void markerBitmap_zoomedOut_scaleIsSetCorrectly() {
    }
    
    @org.junit.Test()
    public final void markerBitmap_zoomedIn_scaleIsSetCorrectly() {
    }
    
    private final void verifyBitmapScale(android.graphics.Bitmap bitmap, float scale) {
    }
}