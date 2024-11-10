package org.owntracks.android.ui.welcome.play;

import androidx.databinding.Bindable;
import org.owntracks.android.injection.scopes.PerFragment;
import org.owntracks.android.ui.base.viewmodel.BaseViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0003J\u0006\u0010\u0011\u001a\u00020\u0012R&\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058G@GX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR*\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0004\u001a\u0004\u0018\u00010\u000b8G@GX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0013"}, d2 = {"Lorg/owntracks/android/ui/welcome/play/PlayFragmentViewModel;", "Lorg/owntracks/android/ui/base/viewmodel/BaseViewModel;", "Lorg/owntracks/android/ui/welcome/play/PlayFragmentMvvm$View;", "()V", "<set-?>", "", "fixAvailable", "getFixAvailable", "()Z", "setFixAvailable", "(Z)V", "", "message", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "onFixClicked", "", "app_debug"})
@org.owntracks.android.injection.scopes.PerFragment()
public final class PlayFragmentViewModel extends org.owntracks.android.ui.base.viewmodel.BaseViewModel<org.owntracks.android.ui.welcome.play.PlayFragmentMvvm.View> {
    private boolean fixAvailable = false;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String message;
    
    @androidx.databinding.Bindable()
    public final boolean getFixAvailable() {
        return false;
    }
    
    @androidx.databinding.Bindable()
    public final void setFixAvailable(boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @androidx.databinding.Bindable()
    public final java.lang.String getMessage() {
        return null;
    }
    
    @androidx.databinding.Bindable()
    public final void setMessage(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    public final void onFixClicked() {
    }
    
    @javax.inject.Inject()
    public PlayFragmentViewModel() {
        super();
    }
}