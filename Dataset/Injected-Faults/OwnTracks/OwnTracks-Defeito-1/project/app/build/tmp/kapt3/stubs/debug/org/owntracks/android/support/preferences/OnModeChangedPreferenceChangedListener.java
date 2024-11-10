package org.owntracks.android.support.preferences;

import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&\u00a8\u0006\u0004"}, d2 = {"Lorg/owntracks/android/support/preferences/OnModeChangedPreferenceChangedListener;", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "onAttachAfterModeChanged", "", "app_debug"})
public abstract interface OnModeChangedPreferenceChangedListener extends android.content.SharedPreferences.OnSharedPreferenceChangeListener {
    
    public abstract void onAttachAfterModeChanged();
}