package org.owntracks.android.ui.welcome;

import androidx.appcompat.app.AppCompatActivity;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.injection.scopes.PerFragment;
import org.owntracks.android.ui.base.viewmodel.BaseViewModel;
import org.owntracks.android.ui.welcome.finish.FinishFragment;
import org.owntracks.android.ui.welcome.finish.FinishFragmentModule;
import org.owntracks.android.ui.welcome.intro.IntroFragment;
import org.owntracks.android.ui.welcome.intro.IntroFragmentModule;
import org.owntracks.android.ui.welcome.permission.PermissionFragment;
import org.owntracks.android.ui.welcome.permission.PermissionFragmentModule;
import org.owntracks.android.ui.welcome.play.PlayFragment;
import org.owntracks.android.ui.welcome.play.PlayFragmentModule;
import org.owntracks.android.ui.welcome.version.VersionFragment;
import org.owntracks.android.ui.welcome.version.VersionFragmentModule;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\'J\n\u0010\u0007\u001a\u0004\u0018\u00010\bH\'J\n\u0010\t\u001a\u0004\u0018\u00010\nH\'J\n\u0010\u000b\u001a\u0004\u0018\u00010\fH\'J\n\u0010\r\u001a\u0004\u0018\u00010\u000eH\'J\n\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\'J\u001c\u0010\u0011\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0013\u0018\u00010\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\'\u00a8\u0006\u0016"}, d2 = {"Lorg/owntracks/android/ui/welcome/WelcomeActivityModule;", "", "()V", "bindActivity", "Landroidx/appcompat/app/AppCompatActivity;", "a", "Lorg/owntracks/android/ui/welcome/WelcomeActivity;", "bindFinishFragment", "Lorg/owntracks/android/ui/welcome/finish/FinishFragment;", "bindIntroFragment", "Lorg/owntracks/android/ui/welcome/intro/IntroFragment;", "bindPermissionFragment", "Lorg/owntracks/android/ui/welcome/permission/PermissionFragment;", "bindPlayFragment", "Lorg/owntracks/android/ui/welcome/play/PlayFragment;", "bindVersionFragment", "Lorg/owntracks/android/ui/welcome/version/VersionFragment;", "bindViewModel", "Lorg/owntracks/android/ui/base/viewmodel/BaseViewModel;", "Lorg/owntracks/android/ui/welcome/WelcomeMvvm$View;", "viewModel", "Lorg/owntracks/android/ui/welcome/WelcomeViewModel;", "app_debug"})
@dagger.Module()
public abstract class WelcomeActivityModule {
    
    @org.jetbrains.annotations.Nullable()
    @org.owntracks.android.injection.scopes.PerActivity()
    @dagger.Binds()
    public abstract androidx.appcompat.app.AppCompatActivity bindActivity(@org.jetbrains.annotations.Nullable()
    org.owntracks.android.ui.welcome.WelcomeActivity a);
    
    @org.jetbrains.annotations.Nullable()
    @org.owntracks.android.injection.scopes.PerActivity()
    @dagger.Binds()
    public abstract org.owntracks.android.ui.base.viewmodel.BaseViewModel<org.owntracks.android.ui.welcome.WelcomeMvvm.View> bindViewModel(@org.jetbrains.annotations.Nullable()
    org.owntracks.android.ui.welcome.WelcomeViewModel viewModel);
    
    @org.jetbrains.annotations.Nullable()
    @org.owntracks.android.injection.scopes.PerFragment()
    @dagger.android.ContributesAndroidInjector(modules = {org.owntracks.android.ui.welcome.play.PlayFragmentModule.class})
    public abstract org.owntracks.android.ui.welcome.play.PlayFragment bindPlayFragment();
    
    @org.jetbrains.annotations.Nullable()
    @org.owntracks.android.injection.scopes.PerFragment()
    @dagger.android.ContributesAndroidInjector(modules = {org.owntracks.android.ui.welcome.intro.IntroFragmentModule.class})
    public abstract org.owntracks.android.ui.welcome.intro.IntroFragment bindIntroFragment();
    
    @org.jetbrains.annotations.Nullable()
    @org.owntracks.android.injection.scopes.PerFragment()
    @dagger.android.ContributesAndroidInjector(modules = {org.owntracks.android.ui.welcome.version.VersionFragmentModule.class})
    public abstract org.owntracks.android.ui.welcome.version.VersionFragment bindVersionFragment();
    
    @org.jetbrains.annotations.Nullable()
    @org.owntracks.android.injection.scopes.PerFragment()
    @dagger.android.ContributesAndroidInjector(modules = {org.owntracks.android.ui.welcome.permission.PermissionFragmentModule.class})
    public abstract org.owntracks.android.ui.welcome.permission.PermissionFragment bindPermissionFragment();
    
    @org.jetbrains.annotations.Nullable()
    @org.owntracks.android.injection.scopes.PerFragment()
    @dagger.android.ContributesAndroidInjector(modules = {org.owntracks.android.ui.welcome.finish.FinishFragmentModule.class})
    public abstract org.owntracks.android.ui.welcome.finish.FinishFragment bindFinishFragment();
    
    public WelcomeActivityModule() {
        super();
    }
}