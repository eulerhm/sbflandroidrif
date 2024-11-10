package org.wordpress.android.util.wizard;

import java.lang.System;

/**
 * Navigation target containing all the data needed for navigating the user to a next screen of the wizard.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\n\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u00020\u0005B\u0015\u0012\u0006\u0010\u0006\u001a\u00028\u0000\u0012\u0006\u0010\u0007\u001a\u00028\u0001\u00a2\u0006\u0002\u0010\bR\u0013\u0010\u0007\u001a\u00028\u0001\u00a2\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0006\u001a\u00028\u0000\u00a2\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\r\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/util/wizard/WizardNavigationTarget;", "S", "Lorg/wordpress/android/util/wizard/WizardStep;", "T", "Lorg/wordpress/android/util/wizard/WizardState;", "", "wizardStep", "wizardState", "(Lorg/wordpress/android/util/wizard/WizardStep;Lorg/wordpress/android/util/wizard/WizardState;)V", "getWizardState", "()Lorg/wordpress/android/util/wizard/WizardState;", "Lorg/wordpress/android/util/wizard/WizardState;", "getWizardStep", "()Lorg/wordpress/android/util/wizard/WizardStep;", "Lorg/wordpress/android/util/wizard/WizardStep;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class WizardNavigationTarget<S extends org.wordpress.android.util.wizard.WizardStep, T extends org.wordpress.android.util.wizard.WizardState> {
    @org.jetbrains.annotations.NotNull()
    private final S wizardStep = null;
    @org.jetbrains.annotations.NotNull()
    private final T wizardState = null;
    
    public WizardNavigationTarget(@org.jetbrains.annotations.NotNull()
    S wizardStep, @org.jetbrains.annotations.NotNull()
    T wizardState) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final S getWizardStep() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final T getWizardState() {
        return null;
    }
}