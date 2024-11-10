package org.wordpress.android.util.wizard;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\r\u001a\u00020\nH\u0002J\u0006\u0010\u0016\u001a\u00020\u0015J\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\nJ\u0006\u0010\u001b\u001a\u00020\u0018J\u000e\u0010\u001c\u001a\u00020\n2\u0006\u0010\u0001\u001a\u00020\u0002R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\n8F\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0012\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\f\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/util/wizard/WizardManager;", "T", "Lorg/wordpress/android/util/wizard/WizardStep;", "", "steps", "", "(Ljava/util/List;)V", "_navigatorLiveData", "Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "currentStep", "", "getCurrentStep", "()I", "currentStepIndex", "navigatorLiveData", "Landroidx/lifecycle/LiveData;", "getNavigatorLiveData", "()Landroidx/lifecycle/LiveData;", "stepsCount", "getStepsCount", "isIndexValid", "", "isLastStep", "onBackPressed", "", "setCurrentStepIndex", "stepIndex", "showNextStep", "stepPosition", "org.wordpress.android_wordpressJalapenoDebug"})
public final class WizardManager<T extends org.wordpress.android.util.wizard.WizardStep> {
    private final java.util.List<T> steps = null;
    private int currentStepIndex = -1;
    private final int stepsCount = 0;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<T> _navigatorLiveData = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<T> navigatorLiveData = null;
    
    public WizardManager(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends T> steps) {
        super();
    }
    
    public final int getStepsCount() {
        return 0;
    }
    
    public final int getCurrentStep() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<T> getNavigatorLiveData() {
        return null;
    }
    
    public final void showNextStep() {
    }
    
    public final void onBackPressed() {
    }
    
    private final boolean isIndexValid(int currentStepIndex) {
        return false;
    }
    
    public final boolean isLastStep() {
        return false;
    }
    
    public final int stepPosition(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.wizard.WizardStep T) {
        return 0;
    }
    
    public final void setCurrentStepIndex(int stepIndex) {
    }
}