package org.owntracks.android.ui.preferences;

import androidx.appcompat.app.AppCompatActivity;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.injection.scopes.PerFragment;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\'J\n\u0010\u0007\u001a\u0004\u0018\u00010\bH\'J\n\u0010\t\u001a\u0004\u0018\u00010\nH\'J\n\u0010\u000b\u001a\u0004\u0018\u00010\fH\'J\n\u0010\r\u001a\u0004\u0018\u00010\u000eH\'\u00a8\u0006\u000f"}, d2 = {"Lorg/owntracks/android/ui/preferences/PreferencesActivityModule;", "", "()V", "bindActivity", "Landroidx/appcompat/app/AppCompatActivity;", "a", "Lorg/owntracks/android/ui/preferences/PreferencesActivity;", "bindAdvancedPreferencesFragment", "Lorg/owntracks/android/ui/preferences/AdvancedFragment;", "bindNotificationPreferencesFragment", "Lorg/owntracks/android/ui/preferences/NotificationFragment;", "bindPreferencesFragment", "Lorg/owntracks/android/ui/preferences/PreferencesFragment;", "bindReportingPreferencesFragment", "Lorg/owntracks/android/ui/preferences/ReportingFragment;", "app_debug"})
@dagger.Module()
public abstract class PreferencesActivityModule {
    
    @org.jetbrains.annotations.Nullable()
    @org.owntracks.android.injection.scopes.PerActivity()
    @dagger.Binds()
    public abstract androidx.appcompat.app.AppCompatActivity bindActivity(@org.jetbrains.annotations.Nullable()
    org.owntracks.android.ui.preferences.PreferencesActivity a);
    
    @org.jetbrains.annotations.Nullable()
    @org.owntracks.android.injection.scopes.PerFragment()
    @dagger.android.ContributesAndroidInjector()
    public abstract org.owntracks.android.ui.preferences.PreferencesFragment bindPreferencesFragment();
    
    @org.jetbrains.annotations.Nullable()
    @org.owntracks.android.injection.scopes.PerFragment()
    @dagger.android.ContributesAndroidInjector()
    public abstract org.owntracks.android.ui.preferences.ReportingFragment bindReportingPreferencesFragment();
    
    @org.jetbrains.annotations.Nullable()
    @org.owntracks.android.injection.scopes.PerFragment()
    @dagger.android.ContributesAndroidInjector()
    public abstract org.owntracks.android.ui.preferences.NotificationFragment bindNotificationPreferencesFragment();
    
    @org.jetbrains.annotations.Nullable()
    @org.owntracks.android.injection.scopes.PerFragment()
    @dagger.android.ContributesAndroidInjector()
    public abstract org.owntracks.android.ui.preferences.AdvancedFragment bindAdvancedPreferencesFragment();
    
    public PreferencesActivityModule() {
        super();
    }
}