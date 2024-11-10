package org.owntracks.android.support.preferences;

import java.lang.System;

/**
 * *
 * Allows a preferences class to read and write values from some sort of store
 */
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H&J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\bH&J\u0018\u0010\t\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\bH&J\b\u0010\n\u001a\u00020\u0005H&J\u001a\u0010\u000b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\r2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0003H&J\u0018\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\bH&J\u0018\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0005H&J\u001e\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\rH&J\u0010\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0018H&J\u0010\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\bH&J\u0010\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0018H&\u00a8\u0006\u001d"}, d2 = {"Lorg/owntracks/android/support/preferences/PreferencesStore;", "", "getBoolean", "", "key", "", "default", "getInitMode", "", "getInt", "getSharedPreferencesName", "getString", "getStringSet", "", "hasKey", "putBoolean", "", "value", "putInt", "putString", "putStringSet", "values", "registerOnSharedPreferenceChangeListener", "listenerModeChanged", "Lorg/owntracks/android/support/preferences/OnModeChangedPreferenceChangedListener;", "remove", "setMode", "mode", "unregisterOnSharedPreferenceChangeListener", "app_debug"})
public abstract interface PreferencesStore {
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String getSharedPreferencesName();
    
    public abstract void setMode(@org.jetbrains.annotations.NotNull()
    java.lang.String key, int mode);
    
    public abstract int getInitMode(@org.jetbrains.annotations.NotNull()
    java.lang.String key, int p1_772401952);
    
    public abstract void putBoolean(@org.jetbrains.annotations.NotNull()
    java.lang.String key, boolean value);
    
    public abstract boolean getBoolean(@org.jetbrains.annotations.NotNull()
    java.lang.String key, boolean p1_772401952);
    
    public abstract void putInt(@org.jetbrains.annotations.NotNull()
    java.lang.String key, int value);
    
    public abstract int getInt(@org.jetbrains.annotations.NotNull()
    java.lang.String key, int p1_772401952);
    
    public abstract void putString(@org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    java.lang.String value);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.String getString(@org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    java.lang.String p1_772401952);
    
    public abstract void putStringSet(@org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    java.util.Set<java.lang.String> values);
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.Set<java.lang.String> getStringSet(@org.jetbrains.annotations.NotNull()
    java.lang.String key);
    
    public abstract boolean hasKey(@org.jetbrains.annotations.NotNull()
    java.lang.String key);
    
    public abstract void remove(@org.jetbrains.annotations.NotNull()
    java.lang.String key);
    
    public abstract void registerOnSharedPreferenceChangeListener(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.support.preferences.OnModeChangedPreferenceChangedListener listenerModeChanged);
    
    public abstract void unregisterOnSharedPreferenceChangeListener(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.support.preferences.OnModeChangedPreferenceChangedListener listenerModeChanged);
}