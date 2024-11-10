package org.owntracks.android.ui.welcome;

import android.content.Intent;
import androidx.lifecycle.MutableLiveData;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.support.Preferences;
import org.owntracks.android.ui.base.viewmodel.BaseViewModel;
import org.owntracks.android.ui.map.MapActivity;
import timber.log.Timber;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u0018J\u0006\u0010\u001a\u001a\u00020\u0018R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR$\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R$\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lorg/owntracks/android/ui/welcome/WelcomeViewModel;", "Lorg/owntracks/android/ui/base/viewmodel/BaseViewModel;", "Lorg/owntracks/android/ui/welcome/WelcomeMvvm$View;", "preferences", "Lorg/owntracks/android/support/Preferences;", "(Lorg/owntracks/android/support/Preferences;)V", "currentFragmentPosition", "Landroidx/lifecycle/MutableLiveData;", "", "getCurrentFragmentPosition", "()Landroidx/lifecycle/MutableLiveData;", "setCurrentFragmentPosition", "(Landroidx/lifecycle/MutableLiveData;)V", "value", "", "doneEnabled", "getDoneEnabled", "()Z", "setDoneEnabled", "(Z)V", "nextEnabled", "getNextEnabled", "setNextEnabled", "moveBack", "", "moveNext", "onDoneClicked", "app_debug"})
@org.owntracks.android.injection.scopes.PerActivity()
public final class WelcomeViewModel extends org.owntracks.android.ui.base.viewmodel.BaseViewModel<org.owntracks.android.ui.welcome.WelcomeMvvm.View> {
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.MutableLiveData<java.lang.Integer> currentFragmentPosition;
    private boolean doneEnabled = false;
    private boolean nextEnabled = false;
    private final org.owntracks.android.support.Preferences preferences = null;
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Integer> getCurrentFragmentPosition() {
        return null;
    }
    
    public final void setCurrentFragmentPosition(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<java.lang.Integer> p0) {
    }
    
    public final boolean getDoneEnabled() {
        return false;
    }
    
    public final void setDoneEnabled(boolean value) {
    }
    
    public final boolean getNextEnabled() {
        return false;
    }
    
    public final void setNextEnabled(boolean value) {
    }
    
    public final void moveNext() {
    }
    
    public final void moveBack() {
    }
    
    public final void onDoneClicked() {
    }
    
    @javax.inject.Inject()
    public WelcomeViewModel(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.support.Preferences preferences) {
        super();
    }
}