package org.wordpress.android;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002:\u0001\rB\u0005\u00a2\u0006\u0002\u0010\u0003J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0016J\b\u0010\u0004\u001a\u00020\u0005H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/BaseWordPressTest;", "Lorg/wordpress/android/WordPress;", "Ldagger/android/HasAndroidInjector;", "()V", "initializer", "Lorg/wordpress/android/AppInitializer;", "getInitializer", "()Lorg/wordpress/android/AppInitializer;", "setInitializer", "(Lorg/wordpress/android/AppInitializer;)V", "androidInjector", "Ldagger/android/AndroidInjector;", "", "AndroidInjectorEntryPoint", "org.wordpress.android_wordpressJalapenoDebug"})
public class BaseWordPressTest extends org.wordpress.android.WordPress implements dagger.android.HasAndroidInjector {
    public org.wordpress.android.AppInitializer initializer;
    
    public BaseWordPressTest() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.AppInitializer getInitializer() {
        return null;
    }
    
    public final void setInitializer(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.AppInitializer p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public dagger.android.AndroidInjector<java.lang.Object> androidInjector() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.wordpress.android.AppInitializer initializer() {
        return null;
    }
    
    @dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
    @dagger.hilt.EntryPoint()
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H&\u00a8\u0006\u0004"}, d2 = {"Lorg/wordpress/android/BaseWordPressTest$AndroidInjectorEntryPoint;", "", "injector", "Ldagger/android/DispatchingAndroidInjector;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract interface AndroidInjectorEntryPoint {
        
        @org.jetbrains.annotations.NotNull()
        public abstract dagger.android.DispatchingAndroidInjector<java.lang.Object> injector();
    }
}