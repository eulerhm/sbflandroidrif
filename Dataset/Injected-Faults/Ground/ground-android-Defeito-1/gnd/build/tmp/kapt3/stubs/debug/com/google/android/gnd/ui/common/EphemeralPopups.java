package com.google.android.gnd.ui.common;

import java.lang.System;

/**
 * Displays short-lived messages such as toasts that are shown over other UI elements.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bJ\u0012\u0010\f\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\f\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\r\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/google/android/gnd/ui/common/EphemeralPopups;", "", "context", "Landroid/app/Application;", "(Landroid/app/Application;)V", "showError", "", "messageId", "", "message", "", "showFyi", "showLong", "showSuccess", "gnd_debug"})
@javax.inject.Singleton()
public final class EphemeralPopups {
    private final android.app.Application context = null;
    
    @javax.inject.Inject()
    public EphemeralPopups(@org.jetbrains.annotations.NotNull()
    android.app.Application context) {
        super();
    }
    
    public final void showSuccess(@androidx.annotation.StringRes()
    int messageId) {
    }
    
    public final void showError(@androidx.annotation.StringRes()
    int messageId) {
    }
    
    public final void showFyi(@androidx.annotation.StringRes()
    int messageId) {
    }
    
    public final void showError(@org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    public final void showError() {
    }
    
    private final void showLong(@androidx.annotation.StringRes()
    int messageId) {
    }
    
    private final void showLong(java.lang.String message) {
    }
}