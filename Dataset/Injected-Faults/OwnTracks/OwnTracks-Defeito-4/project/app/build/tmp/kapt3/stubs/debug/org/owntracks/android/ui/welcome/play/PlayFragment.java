package org.owntracks.android.ui.welcome.play;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import org.owntracks.android.R;
import org.owntracks.android.databinding.UiWelcomePlayBinding;
import org.owntracks.android.ui.base.BaseSupportFragment;
import org.owntracks.android.ui.welcome.WelcomeMvvm;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00162\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004:\u0001\u0016B\u0005\u00a2\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0002J\b\u0010\n\u001a\u00020\u0007H\u0016J&\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0006\u0010\u0013\u001a\u00020\tJ\b\u0010\u0014\u001a\u00020\tH\u0016J\b\u0010\u0015\u001a\u00020\tH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lorg/owntracks/android/ui/welcome/play/PlayFragment;", "Lorg/owntracks/android/ui/base/BaseSupportFragment;", "Lorg/owntracks/android/databinding/UiWelcomePlayBinding;", "Lorg/owntracks/android/ui/welcome/play/PlayFragmentViewModel;", "Lorg/owntracks/android/ui/welcome/play/PlayFragmentMvvm$View;", "()V", "canProceed", "", "checkAvailability", "", "isNextEnabled", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onPlayServicesResolutionResult", "onShowFragment", "requestFix", "Companion", "app_debug"})
public final class PlayFragment extends org.owntracks.android.ui.base.BaseSupportFragment<org.owntracks.android.databinding.UiWelcomePlayBinding, org.owntracks.android.ui.welcome.play.PlayFragmentViewModel> implements org.owntracks.android.ui.welcome.play.PlayFragmentMvvm.View {
    private boolean canProceed = false;
    public static final int PLAY_SERVICES_RESOLUTION_REQUEST = 1;
    @org.jetbrains.annotations.NotNull()
    public static final org.owntracks.android.ui.welcome.play.PlayFragment.Companion Companion = null;
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    public final void onPlayServicesResolutionResult() {
    }
    
    @java.lang.Override()
    public void requestFix() {
    }
    
    private final void checkAvailability() {
    }
    
    @java.lang.Override()
    public boolean isNextEnabled() {
        return false;
    }
    
    @java.lang.Override()
    public void onShowFragment() {
    }
    
    public PlayFragment() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/owntracks/android/ui/welcome/play/PlayFragment$Companion;", "", "()V", "PLAY_SERVICES_RESOLUTION_REQUEST", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}