package com.google.android.gnd;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0007J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\b\u0010\u0012\u001a\u00020\u0010H\u0007R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u00a8\u0006\u0013"}, d2 = {"Lcom/google/android/gnd/MapTypeDialogTest;", "Lcom/google/android/gnd/BaseMainActivityTest;", "()V", "fakeAuthenticationManager", "Lcom/google/android/gnd/system/auth/FakeAuthenticationManager;", "getFakeAuthenticationManager", "()Lcom/google/android/gnd/system/auth/FakeAuthenticationManager;", "setFakeAuthenticationManager", "(Lcom/google/android/gnd/system/auth/FakeAuthenticationManager;)V", "mapsRepository", "Lcom/google/android/gnd/repository/MapsRepository;", "getMapsRepository", "()Lcom/google/android/gnd/repository/MapsRepository;", "setMapsRepository", "(Lcom/google/android/gnd/repository/MapsRepository;)V", "selectingMapTypeItem_shouldUpdateBasemapType", "", "setUp", "tappingMapTypeButton_shouldOpenDialog", "gnd_debug"})
@dagger.hilt.android.testing.HiltAndroidTest()
public final class MapTypeDialogTest extends com.google.android.gnd.BaseMainActivityTest {
    @javax.inject.Inject()
    public com.google.android.gnd.system.auth.FakeAuthenticationManager fakeAuthenticationManager;
    @javax.inject.Inject()
    public com.google.android.gnd.repository.MapsRepository mapsRepository;
    
    public MapTypeDialogTest() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gnd.system.auth.FakeAuthenticationManager getFakeAuthenticationManager() {
        return null;
    }
    
    public final void setFakeAuthenticationManager(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.system.auth.FakeAuthenticationManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gnd.repository.MapsRepository getMapsRepository() {
        return null;
    }
    
    public final void setMapsRepository(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.repository.MapsRepository p0) {
    }
    
    @java.lang.Override()
    public void setUp() {
    }
    
    @org.junit.Test()
    public final void tappingMapTypeButton_shouldOpenDialog() {
    }
    
    @org.junit.Test()
    public final void selectingMapTypeItem_shouldUpdateBasemapType() {
    }
}