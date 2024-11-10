package org.owntracks.android.ui.welcome.permission;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import org.greenrobot.eventbus.EventBus;
import org.owntracks.android.R;
import org.owntracks.android.databinding.UiWelcomePermissionsBinding;
import org.owntracks.android.support.Events.PermissionGranted;
import org.owntracks.android.ui.base.BaseSupportFragment;
import org.owntracks.android.ui.welcome.WelcomeMvvm;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\u0018\u0000 $2\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004:\u0001$B\u0005\u00a2\u0006\u0002\u0010\u0005J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u0007H\u0016J&\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J+\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010\u001f\u001a\u00020 H\u0016\u00a2\u0006\u0002\u0010!J\b\u0010\"\u001a\u00020\u000fH\u0016J\b\u0010#\u001a\u00020\u000fH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u00020\t8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r\u00a8\u0006%"}, d2 = {"Lorg/owntracks/android/ui/welcome/permission/PermissionFragment;", "Lorg/owntracks/android/ui/base/BaseSupportFragment;", "Lorg/owntracks/android/databinding/UiWelcomePermissionsBinding;", "Lorg/owntracks/android/ui/welcome/permission/PermissionFragmentViewModel;", "Lorg/owntracks/android/ui/welcome/permission/PermissionFragmentMvvm$View;", "()V", "askedForPermission", "", "eventBus", "Lorg/greenrobot/eventbus/EventBus;", "getEventBus", "()Lorg/greenrobot/eventbus/EventBus;", "setEventBus", "(Lorg/greenrobot/eventbus/EventBus;)V", "checkPermission", "", "isNextEnabled", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onRequestPermissionsResult", "requestCode", "", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onShowFragment", "requestFix", "Companion", "app_debug"})
public final class PermissionFragment extends org.owntracks.android.ui.base.BaseSupportFragment<org.owntracks.android.databinding.UiWelcomePermissionsBinding, org.owntracks.android.ui.welcome.permission.PermissionFragmentViewModel> implements org.owntracks.android.ui.welcome.permission.PermissionFragmentMvvm.View {
    private boolean askedForPermission = false;
    @javax.inject.Inject()
    public org.greenrobot.eventbus.EventBus eventBus;
    private static final int PERMISSIONS_REQUEST_CODE = 1;
    @org.jetbrains.annotations.NotNull()
    public static final org.owntracks.android.ui.welcome.permission.PermissionFragment.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public final org.greenrobot.eventbus.EventBus getEventBus() {
        return null;
    }
    
    public final void setEventBus(@org.jetbrains.annotations.NotNull()
    org.greenrobot.eventbus.EventBus p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void requestFix() {
    }
    
    @java.lang.Override()
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
    }
    
    private final void checkPermission() {
    }
    
    @java.lang.Override()
    public boolean isNextEnabled() {
        return false;
    }
    
    @java.lang.Override()
    public void onShowFragment() {
    }
    
    public PermissionFragment() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/owntracks/android/ui/welcome/permission/PermissionFragment$Companion;", "", "()V", "PERMISSIONS_REQUEST_CODE", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}