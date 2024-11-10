package org.wordpress.android.ui.accounts.signup;

import java.lang.System;

/**
 * Implements functionality specific to the Username Changer functionality in the sign-up flow.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0014J\u001c\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0014J\b\u0010\n\u001a\u00020\u000bH\u0014J\b\u0010\f\u001a\u00020\bH\u0014J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\bH\u0016\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/accounts/signup/UsernameChangerFullScreenDialogFragment;", "Lorg/wordpress/android/ui/accounts/signup/BaseUsernameChangerFullScreenDialogFragment;", "()V", "canHeaderTextLiveUpdate", "", "getHeaderText", "Landroid/text/Spanned;", "username", "", "display", "getSuggestionsFailedStat", "Lorg/wordpress/android/analytics/AnalyticsTracker$Stat;", "getTrackEventSource", "onUsernameConfirmed", "", "controller", "Lorg/wordpress/android/ui/FullScreenDialogFragment$FullScreenDialogController;", "usernameSelected", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class UsernameChangerFullScreenDialogFragment extends org.wordpress.android.ui.accounts.signup.BaseUsernameChangerFullScreenDialogFragment {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.accounts.signup.UsernameChangerFullScreenDialogFragment.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SOURCE = "signup_epilogue";
    
    public UsernameChangerFullScreenDialogFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected org.wordpress.android.analytics.AnalyticsTracker.Stat getSuggestionsFailedStat() {
        return null;
    }
    
    @java.lang.Override()
    protected boolean canHeaderTextLiveUpdate() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected android.text.Spanned getHeaderText(@org.jetbrains.annotations.Nullable()
    java.lang.String username, @org.jetbrains.annotations.Nullable()
    java.lang.String display) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected java.lang.String getTrackEventSource() {
        return null;
    }
    
    @java.lang.Override()
    public void onUsernameConfirmed(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.FullScreenDialogFragment.FullScreenDialogController controller, @org.jetbrains.annotations.NotNull()
    java.lang.String usernameSelected) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/accounts/signup/UsernameChangerFullScreenDialogFragment$Companion;", "", "()V", "SOURCE", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}