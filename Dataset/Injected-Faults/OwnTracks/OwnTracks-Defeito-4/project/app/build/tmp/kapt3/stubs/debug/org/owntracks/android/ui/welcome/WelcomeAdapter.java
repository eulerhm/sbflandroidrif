package org.owntracks.android.ui.welcome;

import android.os.Build;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.support.RequirementsChecker;
import timber.log.Timber;
import java.util.*;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000f\u001a\u00020\u000bJ\u0010\u0010\u0012\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u000bH\u0002J\b\u0010\u0013\u001a\u00020\u000bH\u0016J.\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\tR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b8F\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lorg/owntracks/android/ui/welcome/WelcomeAdapter;", "Landroidx/viewpager2/adapter/FragmentStateAdapter;", "welcomeActivity", "Lorg/owntracks/android/ui/welcome/WelcomeActivity;", "requirementsChecker", "Lorg/owntracks/android/support/RequirementsChecker;", "(Lorg/owntracks/android/ui/welcome/WelcomeActivity;Lorg/owntracks/android/support/RequirementsChecker;)V", "fragments", "Ljava/util/ArrayList;", "Landroidx/fragment/app/Fragment;", "lastItemPosition", "", "getLastItemPosition", "()I", "createFragment", "position", "getFragment", "Lorg/owntracks/android/ui/welcome/WelcomeFragmentMvvm$View;", "getItem", "getItemCount", "setupFragments", "", "introFragment", "versionFragment", "playFragment", "permissionFragment", "finishFragment", "app_debug"})
@org.owntracks.android.injection.scopes.PerActivity()
public final class WelcomeAdapter extends androidx.viewpager2.adapter.FragmentStateAdapter {
    private final java.util.ArrayList<androidx.fragment.app.Fragment> fragments = null;
    private final org.owntracks.android.support.RequirementsChecker requirementsChecker = null;
    
    public final void setupFragments(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment introFragment, @org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment versionFragment, @org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment playFragment, @org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment permissionFragment, @org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment finishFragment) {
    }
    
    public final int getLastItemPosition() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.owntracks.android.ui.welcome.WelcomeFragmentMvvm.View getFragment(int position) {
        return null;
    }
    
    private final androidx.fragment.app.Fragment getItem(int position) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.fragment.app.Fragment createFragment(int position) {
        return null;
    }
    
    public WelcomeAdapter(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.ui.welcome.WelcomeActivity welcomeActivity, @org.jetbrains.annotations.NotNull()
    org.owntracks.android.support.RequirementsChecker requirementsChecker) {
        super(null);
    }
}