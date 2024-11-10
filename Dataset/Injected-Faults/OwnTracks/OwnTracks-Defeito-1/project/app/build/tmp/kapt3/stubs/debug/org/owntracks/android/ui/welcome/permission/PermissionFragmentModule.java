package org.owntracks.android.ui.welcome.permission;

import androidx.fragment.app.Fragment;
import dagger.Binds;
import dagger.Module;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\'\u00a8\u0006\u0007"}, d2 = {"Lorg/owntracks/android/ui/welcome/permission/PermissionFragmentModule;", "", "()V", "bindSupportFragment", "Landroidx/fragment/app/Fragment;", "f", "Lorg/owntracks/android/ui/welcome/permission/PermissionFragment;", "app_debug"})
@dagger.Module()
public abstract class PermissionFragmentModule {
    
    @org.jetbrains.annotations.Nullable()
    @dagger.Binds()
    public abstract androidx.fragment.app.Fragment bindSupportFragment(@org.jetbrains.annotations.Nullable()
    org.owntracks.android.ui.welcome.permission.PermissionFragment f);
    
    public PermissionFragmentModule() {
        super();
    }
}