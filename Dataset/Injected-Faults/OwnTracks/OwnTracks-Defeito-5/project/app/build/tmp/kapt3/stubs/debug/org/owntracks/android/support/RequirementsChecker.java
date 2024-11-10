package org.owntracks.android.support;

import android.Manifest;
import android.content.pm.PackageManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import org.owntracks.android.injection.scopes.PerActivity;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\r\u001a\u00020\nR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n8F\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\u000bR\u0011\u0010\f\u001a\u00020\n8F\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lorg/owntracks/android/support/RequirementsChecker;", "", "preferences", "Lorg/owntracks/android/support/Preferences;", "context", "Landroidx/appcompat/app/AppCompatActivity;", "(Lorg/owntracks/android/support/Preferences;Landroidx/appcompat/app/AppCompatActivity;)V", "getContext", "()Landroidx/appcompat/app/AppCompatActivity;", "isPermissionCheckPassed", "", "()Z", "isPlayCheckPassed", "areRequirementsMet", "app_debug"})
@org.owntracks.android.injection.scopes.PerActivity()
public final class RequirementsChecker {
    private final org.owntracks.android.support.Preferences preferences = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.appcompat.app.AppCompatActivity context = null;
    
    public final boolean areRequirementsMet() {
        return false;
    }
    
    public final boolean isPlayCheckPassed() {
        return false;
    }
    
    public final boolean isPermissionCheckPassed() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.appcompat.app.AppCompatActivity getContext() {
        return null;
    }
    
    @javax.inject.Inject()
    public RequirementsChecker(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.support.Preferences preferences, @org.jetbrains.annotations.NotNull()
    androidx.appcompat.app.AppCompatActivity context) {
        super();
    }
}