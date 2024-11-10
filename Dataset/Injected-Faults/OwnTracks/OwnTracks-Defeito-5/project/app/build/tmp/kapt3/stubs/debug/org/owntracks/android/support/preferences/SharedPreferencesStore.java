package org.owntracks.android.support.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;
import dagger.Binds;
import dagger.Module;
import org.owntracks.android.R;
import org.owntracks.android.injection.qualifier.AppContext;
import javax.inject.Singleton;
import org.owntracks.android.services.MessageProcessorEndpointHttp;
import org.owntracks.android.services.MessageProcessorEndpointMqtt;
import java.util.*;
import javax.inject.Inject;

/**
 * *
 * Implements a PreferencesStore that uses a SharedPreferecnces as a backend.
 */
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u000e\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0012H\u0002J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0015H\u0016J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0019H\u0016J\u0018\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0019H\u0016J\b\u0010\u001b\u001a\u00020\fH\u0016J\u001a\u0010\u001c\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\fH\u0016J\u0016\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\f0\u001e2\u0006\u0010\u0016\u001a\u00020\fH\u0016J\u0010\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\fH\u0016J\u0018\u0010 \u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010!\u001a\u00020\u0015H\u0016J\u0018\u0010\"\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010!\u001a\u00020\u0019H\u0016J\u0018\u0010#\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010!\u001a\u00020\fH\u0016J\u001e\u0010$\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\f2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\f0\u001eH\u0016J\u0010\u0010&\u001a\u00020\u00122\u0006\u0010\'\u001a\u00020\tH\u0016J\u0010\u0010(\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\fH\u0016J\u0018\u0010)\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010*\u001a\u00020\u0019H\u0016J\u0010\u0010+\u001a\u00020\u00122\u0006\u0010\'\u001a\u00020\tH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006,"}, d2 = {"Lorg/owntracks/android/support/preferences/SharedPreferencesStore;", "Lorg/owntracks/android/support/preferences/PreferencesStore;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "activeSharedPreferences", "Landroid/content/SharedPreferences;", "activeSharedPreferencesChangeListener", "Ljava/util/LinkedList;", "Lorg/owntracks/android/support/preferences/OnModeChangedPreferenceChangedListener;", "commonPreferenceKeys", "", "", "commonSharedPreferences", "httpSharedPreferences", "privateSharedPreferences", "sharedPreferencesName", "attachAllActivePreferenceChangeListeners", "", "detachAllActivePreferenceChangeListeners", "getBoolean", "", "key", "default", "getInitMode", "", "getInt", "getSharedPreferencesName", "getString", "getStringSet", "", "hasKey", "putBoolean", "value", "putInt", "putString", "putStringSet", "values", "registerOnSharedPreferenceChangeListener", "listenerModeChanged", "remove", "setMode", "mode", "unregisterOnSharedPreferenceChangeListener", "app_debug"})
@javax.inject.Singleton()
public final class SharedPreferencesStore implements org.owntracks.android.support.preferences.PreferencesStore {
    private java.lang.String sharedPreferencesName;
    private final java.util.LinkedList<org.owntracks.android.support.preferences.OnModeChangedPreferenceChangedListener> activeSharedPreferencesChangeListener = null;
    private android.content.SharedPreferences activeSharedPreferences;
    private final android.content.SharedPreferences commonSharedPreferences = null;
    private final android.content.SharedPreferences privateSharedPreferences = null;
    private final android.content.SharedPreferences httpSharedPreferences = null;
    private final java.util.List<java.lang.String> commonPreferenceKeys = null;
    
    @java.lang.Override()
    public void putString(@org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.String getString(@org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    java.lang.String p1_772401952) {
        return null;
    }
    
    @java.lang.Override()
    public void remove(@org.jetbrains.annotations.NotNull()
    java.lang.String key) {
    }
    
    @java.lang.Override()
    public boolean getBoolean(@org.jetbrains.annotations.NotNull()
    java.lang.String key, boolean p1_772401952) {
        return false;
    }
    
    @java.lang.Override()
    public void putBoolean(@org.jetbrains.annotations.NotNull()
    java.lang.String key, boolean value) {
    }
    
    @java.lang.Override()
    public int getInt(@org.jetbrains.annotations.NotNull()
    java.lang.String key, int p1_772401952) {
        return 0;
    }
    
    @java.lang.Override()
    public void putInt(@org.jetbrains.annotations.NotNull()
    java.lang.String key, int value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String getSharedPreferencesName() {
        return null;
    }
    
    @java.lang.Override()
    public void putStringSet(@org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    java.util.Set<java.lang.String> values) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.util.Set<java.lang.String> getStringSet(@org.jetbrains.annotations.NotNull()
    java.lang.String key) {
        return null;
    }
    
    @java.lang.Override()
    public boolean hasKey(@org.jetbrains.annotations.NotNull()
    java.lang.String key) {
        return false;
    }
    
    @java.lang.Override()
    public int getInitMode(@org.jetbrains.annotations.NotNull()
    java.lang.String key, int p1_772401952) {
        return 0;
    }
    
    @java.lang.Override()
    public void setMode(@org.jetbrains.annotations.NotNull()
    java.lang.String key, int mode) {
    }
    
    @java.lang.Override()
    public void registerOnSharedPreferenceChangeListener(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.support.preferences.OnModeChangedPreferenceChangedListener listenerModeChanged) {
    }
    
    @java.lang.Override()
    public void unregisterOnSharedPreferenceChangeListener(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.support.preferences.OnModeChangedPreferenceChangedListener listenerModeChanged) {
    }
    
    private final void detachAllActivePreferenceChangeListeners() {
    }
    
    private final void attachAllActivePreferenceChangeListeners() {
    }
    
    @javax.inject.Inject()
    public SharedPreferencesStore(@org.jetbrains.annotations.NotNull()
    @org.owntracks.android.injection.qualifier.AppContext()
    android.content.Context context) {
        super();
    }
}