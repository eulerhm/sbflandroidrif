package org.owntracks.android.ui.preferences;

import android.content.Intent;
import android.os.Bundle;
import androidx.preference.Preference;
import org.owntracks.android.R;
import org.owntracks.android.services.MessageProcessorEndpointHttp;
import org.owntracks.android.services.MessageProcessorEndpointMqtt;
import org.owntracks.android.ui.preferences.connection.ConnectionActivity;
import org.owntracks.android.ui.preferences.editor.EditorActivity;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0005\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010\f\u001a\u00020\bH\u0016R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000e"}, d2 = {"Lorg/owntracks/android/ui/preferences/PreferencesFragment;", "Lorg/owntracks/android/ui/preferences/AbstractPreferenceFragment;", "()V", "connectionMode", "", "getConnectionMode", "()Ljava/lang/String;", "onCreatePreferencesFix", "", "savedInstanceState", "Landroid/os/Bundle;", "rootKey", "onResume", "Companion", "app_debug"})
public final class PreferencesFragment extends org.owntracks.android.ui.preferences.AbstractPreferenceFragment {
    private static final java.lang.String UI_PREFERENCE_SCREEN_CONNECTION = "connectionScreen";
    private static final java.lang.String UI_SCREEN_CONFIGURATION = "configuration";
    @org.jetbrains.annotations.NotNull()
    public static final org.owntracks.android.ui.preferences.PreferencesFragment.Companion Companion = null;
    
    @java.lang.Override()
    public void onCreatePreferencesFix(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState, @org.jetbrains.annotations.Nullable()
    java.lang.String rootKey) {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    private final java.lang.String getConnectionMode() {
        return null;
    }
    
    public PreferencesFragment() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lorg/owntracks/android/ui/preferences/PreferencesFragment$Companion;", "", "()V", "UI_PREFERENCE_SCREEN_CONNECTION", "", "UI_SCREEN_CONFIGURATION", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}