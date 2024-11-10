package org.wordpress.android.support;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J*\u0010\u0003\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tJH\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u0018\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000b0\u0013\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/support/SupportHelper;", "", "()V", "getSupportEmailAndNameSuggestion", "Lkotlin/Pair;", "", "account", "Lorg/wordpress/android/fluxc/model/AccountModel;", "selectedSite", "Lorg/wordpress/android/fluxc/model/SiteModel;", "showSupportIdentityInputDialog", "", "context", "Landroid/content/Context;", "email", "name", "isNameInputHidden", "", "emailAndNameSelected", "Lkotlin/Function2;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SupportHelper {
    
    public SupportHelper() {
        super();
    }
    
    /**
     * This is a helper function that shows the support identity input dialog and runs the provided function with
     * the input from it.
     *
     * @param context Context the dialog will be showed from
     * @param email Initial value for the email field
     * @param name Initial value for the name field
     * @param isNameInputHidden Whether the name input field should be shown or not
     * @param emailAndNameSelected Function to run with the email and name inputs from the dialog. Even if the
     * [isNameInputHidden] parameter is true, the input in the name field will be provided and it's up to the caller to
     * ignore the name parameter.
     */
    public final void showSupportIdentityInputDialog(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.lang.String email, @org.jetbrains.annotations.Nullable()
    java.lang.String name, boolean isNameInputHidden, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.lang.String, ? super java.lang.String, kotlin.Unit> emailAndNameSelected) {
    }
    
    /**
     * This is a helper function to returns suggested email and name values to be used in the support identity dialog.
     *
     * @param account WordPress.com account
     * @param selectedSite Selected site of the user which will be used if the [account] is null
     *
     * @return a Pair with email and name suggestion
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlin.Pair<java.lang.String, java.lang.String> getSupportEmailAndNameSuggestion(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.AccountModel account, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel selectedSite) {
        return null;
    }
}