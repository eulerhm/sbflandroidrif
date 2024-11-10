package org.owntracks.android.ui.base.navigator;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.appcompat.app.AppCompatActivity;
import org.owntracks.android.injection.scopes.PerActivity;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bJ\u0018\u0010\t\u001a\u00020\n2\u0010\u0010\u000b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\r0\fJ*\u0010\t\u001a\u00020\n2\u0010\u0010\u000b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\r0\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000f\u001a\u00020\u0010J\"\u0010\t\u001a\u00020\n2\u0010\u0010\u000b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\r0\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0011J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0013J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0018\u0010\u0016\u001a\u00020\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0017\u001a\u00020\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lorg/owntracks/android/ui/base/navigator/Navigator;", "", "activity", "Landroidx/appcompat/app/AppCompatActivity;", "(Landroidx/appcompat/app/AppCompatActivity;)V", "getExtrasBundle", "Landroid/os/Bundle;", "intent", "Landroid/content/Intent;", "startActivity", "", "activityClass", "Ljava/lang/Class;", "Landroid/app/Activity;", "args", "flags", "", "Landroid/os/Parcelable;", "action", "", "uri", "Landroid/net/Uri;", "startActivityForResult", "requestCode", "Companion", "app_debug"})
@org.owntracks.android.injection.scopes.PerActivity()
public final class Navigator {
    private final androidx.appcompat.app.AppCompatActivity activity = null;
    private static final java.lang.String EXTRA_ARGS = "_args";
    @org.jetbrains.annotations.NotNull()
    public static final org.owntracks.android.ui.base.navigator.Navigator.Companion Companion = null;
    
    public final void startActivity(@org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
    }
    
    public final void startActivity(@org.jetbrains.annotations.NotNull()
    java.lang.String action) {
    }
    
    public final void startActivity(@org.jetbrains.annotations.NotNull()
    java.lang.String action, @org.jetbrains.annotations.NotNull()
    android.net.Uri uri) {
    }
    
    public final void startActivity(@org.jetbrains.annotations.NotNull()
    java.lang.Class<? extends android.app.Activity> activityClass) {
    }
    
    public final void startActivity(@org.jetbrains.annotations.NotNull()
    java.lang.Class<? extends android.app.Activity> activityClass, @org.jetbrains.annotations.Nullable()
    android.os.Bundle args, int flags) {
    }
    
    public final void startActivity(@org.jetbrains.annotations.NotNull()
    java.lang.Class<? extends android.app.Activity> activityClass, @org.jetbrains.annotations.Nullable()
    android.os.Parcelable args) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.os.Bundle getExtrasBundle(@org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
        return null;
    }
    
    public final void startActivityForResult(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent, int requestCode) {
    }
    
    @javax.inject.Inject()
    public Navigator(@org.jetbrains.annotations.NotNull()
    androidx.appcompat.app.AppCompatActivity activity) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/owntracks/android/ui/base/navigator/Navigator$Companion;", "", "()V", "EXTRA_ARGS", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}