package org.owntracks.android.ui.preferences;

import android.content.Context;
import android.os.Bundle;
import com.takisoft.preferencex.PreferenceFragmentCompat;
import dagger.android.support.AndroidSupportInjection;
import org.owntracks.android.support.Preferences;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001c\u0010\r\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u0012"}, d2 = {"Lorg/owntracks/android/ui/preferences/AbstractPreferenceFragment;", "Lcom/takisoft/preferencex/PreferenceFragmentCompat;", "()V", "preferences", "Lorg/owntracks/android/support/Preferences;", "getPreferences", "()Lorg/owntracks/android/support/Preferences;", "setPreferences", "(Lorg/owntracks/android/support/Preferences;)V", "onAttach", "", "context", "Landroid/content/Context;", "onCreatePreferencesFix", "savedInstanceState", "Landroid/os/Bundle;", "rootKey", "", "app_debug"})
public abstract class AbstractPreferenceFragment extends com.takisoft.preferencex.PreferenceFragmentCompat {
    @javax.inject.Inject()
    public org.owntracks.android.support.Preferences preferences;
    
    @org.jetbrains.annotations.NotNull()
    public final org.owntracks.android.support.Preferences getPreferences() {
        return null;
    }
    
    public final void setPreferences(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.support.Preferences p0) {
    }
    
    @java.lang.Override()
    public void onCreatePreferencesFix(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState, @org.jetbrains.annotations.Nullable()
    java.lang.String rootKey) {
    }
    
    @java.lang.Override()
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public AbstractPreferenceFragment() {
        super();
    }
}