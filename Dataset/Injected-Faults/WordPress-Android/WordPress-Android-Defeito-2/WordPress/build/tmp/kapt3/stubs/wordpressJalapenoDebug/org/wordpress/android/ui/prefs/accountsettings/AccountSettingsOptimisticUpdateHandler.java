package org.wordpress.android.ui.prefs.accountsettings;

import java.lang.System;

/**
 * In AccountSettings, when the preference is changed by the user, the changed preference will be reflected immediately
 * in the screen even before the preference is actually updated in the server. We are optimistic that preferences will
 * be updated in the server successfully.
 * In case of any failure, we are reverting back the preference change and notifying user with error message.
 * Whenever a request is made for the preference change, optimisticallyChangedPreferenceMap is updated with the
 * preference key and value that has to be optimistically updated to ui state.
 *
 * on calling applyOptimisticallyChangedPreferences function, the preferences that has to be updated
 * optimistically will be updated with the latest value. (ie. last())
 *
 * Once the server returns the response, the preference key and value is removed from the
 * optimisticallyChangedPreferenceMap.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bJ\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u0005J\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005R \u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/prefs/accountsettings/AccountSettingsOptimisticUpdateHandler;", "", "()V", "optimisticallyChangedPreferenceMap", "", "", "", "applyOptimisticallyChangedPreferences", "Lorg/wordpress/android/ui/prefs/accountsettings/AccountSettingsViewModel$AccountSettingsUiState;", "state", "removeFirstChange", "Lkotlin/Function0;", "", "preferenceKey", "update", "value", "org.wordpress.android_wordpressJalapenoDebug"})
public final class AccountSettingsOptimisticUpdateHandler {
    private final java.util.Map<java.lang.String, java.util.List<java.lang.String>> optimisticallyChangedPreferenceMap = null;
    
    @javax.inject.Inject()
    public AccountSettingsOptimisticUpdateHandler() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.AccountSettingsUiState applyOptimisticallyChangedPreferences(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.AccountSettingsUiState state) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function0<kotlin.Unit> update(@org.jetbrains.annotations.NotNull()
    java.lang.String preferenceKey, @org.jetbrains.annotations.NotNull()
    java.lang.String value) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function0<kotlin.Unit> removeFirstChange(@org.jetbrains.annotations.NotNull()
    java.lang.String preferenceKey) {
        return null;
    }
}