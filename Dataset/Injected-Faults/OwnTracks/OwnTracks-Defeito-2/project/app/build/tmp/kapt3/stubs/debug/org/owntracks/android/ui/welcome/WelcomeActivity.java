package org.owntracks.android.ui.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import org.owntracks.android.R;
import org.owntracks.android.databinding.UiWelcomeBinding;
import org.owntracks.android.support.RequirementsChecker;
import org.owntracks.android.ui.base.BaseActivity;
import org.owntracks.android.ui.base.navigator.Navigator;
import org.owntracks.android.ui.map.MapActivity;
import org.owntracks.android.ui.welcome.finish.FinishFragment;
import org.owntracks.android.ui.welcome.intro.IntroFragment;
import org.owntracks.android.ui.welcome.permission.PermissionFragment;
import org.owntracks.android.ui.welcome.play.PlayFragment;
import org.owntracks.android.ui.welcome.version.VersionFragment;
import timber.log.Timber;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00012\u00020\u0004B\u0005\u00a2\u0006\u0002\u0010\u0005J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\"\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u0017H\u0016J\u0012\u0010\u001f\u001a\u00020\u00172\b\u0010 \u001a\u0004\u0018\u00010!H\u0014J\b\u0010\"\u001a\u00020\u0017H\u0016J\u0010\u0010#\u001a\u00020\u00172\u0006\u0010$\u001a\u00020%H\u0002J\u0010\u0010&\u001a\u00020\u00172\u0006\u0010$\u001a\u00020%H\u0002J\u0010\u0010\'\u001a\u00020\u00172\u0006\u0010(\u001a\u00020\u001aH\u0016J\u0010\u0010)\u001a\u00020\u00172\u0006\u0010*\u001a\u00020\u001aH\u0002J\b\u0010+\u001a\u00020\u0017H\u0016R\u001e\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u00020\u000f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006,"}, d2 = {"Lorg/owntracks/android/ui/welcome/WelcomeActivity;", "Lorg/owntracks/android/ui/base/BaseActivity;", "Lorg/owntracks/android/databinding/UiWelcomeBinding;", "Lorg/owntracks/android/ui/welcome/WelcomeViewModel;", "Lorg/owntracks/android/ui/welcome/WelcomeMvvm$View;", "()V", "navigator", "Lorg/owntracks/android/ui/base/navigator/Navigator;", "getNavigator", "()Lorg/owntracks/android/ui/base/navigator/Navigator;", "setNavigator", "(Lorg/owntracks/android/ui/base/navigator/Navigator;)V", "playFragment", "Lorg/owntracks/android/ui/welcome/play/PlayFragment;", "requirementsChecker", "Lorg/owntracks/android/support/RequirementsChecker;", "getRequirementsChecker", "()Lorg/owntracks/android/support/RequirementsChecker;", "setRequirementsChecker", "(Lorg/owntracks/android/support/RequirementsChecker;)V", "welcomeAdapter", "Lorg/owntracks/android/ui/welcome/WelcomeAdapter;", "buildPagerIndicator", "", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "refreshNextDoneButtons", "setDoneEnabled", "enabled", "", "setNextEnabled", "setPagerIndicator", "index", "showFragment", "position", "showNextFragment", "app_debug"})
public final class WelcomeActivity extends org.owntracks.android.ui.base.BaseActivity<org.owntracks.android.databinding.UiWelcomeBinding, org.owntracks.android.ui.welcome.WelcomeViewModel> implements org.owntracks.android.ui.welcome.WelcomeMvvm.View {
    @javax.inject.Inject()
    public org.owntracks.android.ui.base.navigator.Navigator navigator;
    @javax.inject.Inject()
    public org.owntracks.android.support.RequirementsChecker requirementsChecker;
    private org.owntracks.android.ui.welcome.WelcomeAdapter welcomeAdapter;
    private org.owntracks.android.ui.welcome.play.PlayFragment playFragment;
    
    @org.jetbrains.annotations.NotNull()
    public final org.owntracks.android.ui.base.navigator.Navigator getNavigator() {
        return null;
    }
    
    public final void setNavigator(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.ui.base.navigator.Navigator p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.owntracks.android.support.RequirementsChecker getRequirementsChecker() {
        return null;
    }
    
    public final void setRequirementsChecker(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.support.RequirementsChecker p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void showNextFragment() {
    }
    
    @java.lang.Override()
    public void setPagerIndicator(int index) {
    }
    
    private final void setNextEnabled(boolean enabled) {
    }
    
    private final void setDoneEnabled(boolean enabled) {
    }
    
    private final void showFragment(int position) {
    }
    
    @java.lang.Override()
    public void refreshNextDoneButtons() {
    }
    
    private final void buildPagerIndicator() {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    @java.lang.Override()
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    public WelcomeActivity() {
        super();
    }
}