package com.google.android.gnd;

import java.lang.System;

/**
 * Top-level view model representing state of the [MainActivity] shared by all fragments.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001BG\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\u0002\u0010\u0012J\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u001e\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u000e\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010#0\"H\u0002J\u000e\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0002J\u000e\u0010%\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0002J%\u0010&\u001a\u00110\'\u00a2\u0006\f\b(\u0012\b\b)\u0012\u0004\b\u0007\u0010\u00002\f\u0010*\u001a\b\u0012\u0004\u0012\u00020+0\"H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017\u00a8\u0006,"}, d2 = {"Lcom/google/android/gnd/MainViewModel;", "Lcom/google/android/gnd/ui/common/AbstractViewModel;", "surveyRepository", "Lcom/google/android/gnd/repository/SurveyRepository;", "featureRepository", "Lcom/google/android/gnd/repository/FeatureRepository;", "userRepository", "Lcom/google/android/gnd/repository/UserRepository;", "termsOfServiceRepository", "Lcom/google/android/gnd/repository/TermsOfServiceRepository;", "popups", "Lcom/google/android/gnd/ui/common/EphemeralPopups;", "navigator", "Lcom/google/android/gnd/ui/common/Navigator;", "authenticationManager", "Lcom/google/android/gnd/system/auth/AuthenticationManager;", "schedulers", "Lcom/google/android/gnd/rx/Schedulers;", "(Lcom/google/android/gnd/repository/SurveyRepository;Lcom/google/android/gnd/repository/FeatureRepository;Lcom/google/android/gnd/repository/UserRepository;Lcom/google/android/gnd/repository/TermsOfServiceRepository;Lcom/google/android/gnd/ui/common/EphemeralPopups;Lcom/google/android/gnd/ui/common/Navigator;Lcom/google/android/gnd/system/auth/AuthenticationManager;Lcom/google/android/gnd/rx/Schedulers;)V", "signInProgressDialogVisibility", "Landroidx/lifecycle/MutableLiveData;", "", "getSignInProgressDialogVisibility", "()Landroidx/lifecycle/MutableLiveData;", "windowInsets", "Landroidx/core/view/WindowInsetsCompat;", "getWindowInsets", "onSignInStateChange", "Lio/reactivex/Observable;", "Landroidx/navigation/NavDirections;", "signInState", "Lcom/google/android/gnd/system/auth/SignInState;", "onUserSignInError", "error", "Ljava8/util/Optional;", "", "onUserSignedIn", "onUserSignedOut", "syncFeatures", "Lio/reactivex/Completable;", "Lcom/google/android/gnd/rx/annotations/Cold;", "terminates", "survey", "Lcom/google/android/gnd/model/Survey;", "gnd_debug"})
@com.google.android.gnd.ui.common.SharedViewModel()
public final class MainViewModel extends com.google.android.gnd.ui.common.AbstractViewModel {
    private final com.google.android.gnd.repository.SurveyRepository surveyRepository = null;
    private final com.google.android.gnd.repository.FeatureRepository featureRepository = null;
    private final com.google.android.gnd.repository.UserRepository userRepository = null;
    private final com.google.android.gnd.repository.TermsOfServiceRepository termsOfServiceRepository = null;
    private final com.google.android.gnd.ui.common.EphemeralPopups popups = null;
    
    /**
     * The window insets determined by the activity.
     */
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<androidx.core.view.WindowInsetsCompat> windowInsets = null;
    
    /**
     * The state of sign in progress dialog visibility.
     */
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> signInProgressDialogVisibility = null;
    
    @javax.inject.Inject()
    public MainViewModel(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.repository.SurveyRepository surveyRepository, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.repository.FeatureRepository featureRepository, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.repository.UserRepository userRepository, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.repository.TermsOfServiceRepository termsOfServiceRepository, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.ui.common.EphemeralPopups popups, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.ui.common.Navigator navigator, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.system.auth.AuthenticationManager authenticationManager, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.rx.Schedulers schedulers) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<androidx.core.view.WindowInsetsCompat> getWindowInsets() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> getSignInProgressDialogVisibility() {
        return null;
    }
    
    /**
     * Keeps local features in sync with remote when a survey is active, does nothing when no survey
     * is active. The stream never completes; syncing stops when subscriptions are disposed of.
     *
     * @param survey the currently active survey.
     */
    private final io.reactivex.Completable syncFeatures(java8.util.Optional<com.google.android.gnd.model.Survey> survey) {
        return null;
    }
    
    private final io.reactivex.Observable<androidx.navigation.NavDirections> onSignInStateChange(com.google.android.gnd.system.auth.SignInState signInState) {
        return null;
    }
    
    private final io.reactivex.Observable<androidx.navigation.NavDirections> onUserSignInError(java8.util.Optional<java.lang.Throwable> error) {
        return null;
    }
    
    private final io.reactivex.Observable<androidx.navigation.NavDirections> onUserSignedOut() {
        return null;
    }
    
    private final io.reactivex.Observable<androidx.navigation.NavDirections> onUserSignedIn() {
        return null;
    }
}