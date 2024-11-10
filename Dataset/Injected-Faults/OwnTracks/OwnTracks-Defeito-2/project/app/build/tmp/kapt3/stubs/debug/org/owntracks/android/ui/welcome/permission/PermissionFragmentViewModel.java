package org.owntracks.android.ui.welcome.permission;

import android.os.Bundle;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.ui.base.viewmodel.BaseViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0007\b\u0001\u00a2\u0006\u0002\u0010\u0003J\u001a\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016J\u0006\u0010\u000f\u001a\u00020\u000bR$\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t\u00a8\u0006\u0010"}, d2 = {"Lorg/owntracks/android/ui/welcome/permission/PermissionFragmentViewModel;", "Lorg/owntracks/android/ui/base/viewmodel/BaseViewModel;", "Lorg/owntracks/android/ui/welcome/permission/PermissionFragmentMvvm$View;", "()V", "permissionGranted", "", "isPermissionGranted", "()Z", "setPermissionGranted", "(Z)V", "attachView", "", "savedInstanceState", "Landroid/os/Bundle;", "view", "onFixClicked", "app_debug"})
@org.owntracks.android.injection.scopes.PerActivity()
public final class PermissionFragmentViewModel extends org.owntracks.android.ui.base.viewmodel.BaseViewModel<org.owntracks.android.ui.welcome.permission.PermissionFragmentMvvm.View> {
    private boolean isPermissionGranted = false;
    
    public final boolean isPermissionGranted() {
        return false;
    }
    
    public final void setPermissionGranted(boolean permissionGranted) {
    }
    
    @java.lang.Override()
    public void attachView(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState, @org.jetbrains.annotations.NotNull()
    org.owntracks.android.ui.welcome.permission.PermissionFragmentMvvm.View view) {
    }
    
    public final void onFixClicked() {
    }
    
    @javax.inject.Inject()
    public PermissionFragmentViewModel() {
        super();
    }
}