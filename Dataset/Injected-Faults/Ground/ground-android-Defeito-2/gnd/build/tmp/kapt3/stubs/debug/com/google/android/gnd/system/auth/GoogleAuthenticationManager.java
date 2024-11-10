package com.google.android.gnd.system.auth;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0002J\b\u0010#\u001a\u00020$H\u0016J\u0010\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020\'H\u0002J\u0010\u0010(\u001a\u00020$2\u0006\u0010)\u001a\u00020*H\u0002J\u001e\u0010+\u001a\u0010\u0012\f\u0012\n -*\u0004\u0018\u00010*0*0,2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010.\u001a\u00020$H\u0016J\b\u0010/\u001a\u00020$H\u0016J\f\u00100\u001a\u00020\n*\u000201H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R)\u0010\u0011\u001a\u0017\u0012\u0004\u0012\u00020\u00130\u0012\u00a2\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\u0007\u0010\u0002X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00138BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a\u00a8\u00062"}, d2 = {"Lcom/google/android/gnd/system/auth/GoogleAuthenticationManager;", "Lcom/google/android/gnd/system/auth/AuthenticationManager;", "resources", "Landroid/content/res/Resources;", "activityStreams", "Lcom/google/android/gnd/system/ActivityStreams;", "(Landroid/content/res/Resources;Lcom/google/android/gnd/system/ActivityStreams;)V", "activityResultsSubscription", "Lio/reactivex/disposables/Disposable;", "currentUser", "Lcom/google/android/gnd/model/User;", "getCurrentUser", "()Lcom/google/android/gnd/model/User;", "firebaseAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "googleSignInOptions", "Lcom/google/android/gms/auth/api/signin/GoogleSignInOptions;", "signInState", "Lio/reactivex/subjects/Subject;", "Lcom/google/android/gnd/system/auth/SignInState;", "Lcom/google/android/gnd/rx/annotations/Hot;", "replays", "getSignInState", "()Lio/reactivex/subjects/Subject;", "status", "getStatus", "()Lcom/google/android/gnd/system/auth/SignInState;", "getFirebaseAuthCredential", "Lcom/google/firebase/auth/AuthCredential;", "googleAccount", "Lcom/google/android/gms/auth/api/signin/GoogleSignInAccount;", "getGoogleSignInClient", "Lcom/google/android/gms/auth/api/signin/GoogleSignInClient;", "activity", "Landroid/app/Activity;", "init", "", "onActivityResult", "activityResult", "Lcom/google/android/gnd/system/ActivityResult;", "onFirebaseAuthSuccess", "authResult", "Lcom/google/firebase/auth/AuthResult;", "onGoogleSignIn", "Lcom/google/android/gms/tasks/Task;", "kotlin.jvm.PlatformType", "signIn", "signOut", "toUser", "Lcom/google/firebase/auth/FirebaseUser;", "gnd_debug"})
public final class GoogleAuthenticationManager implements com.google.android.gnd.system.auth.AuthenticationManager {
    private final com.google.android.gnd.system.ActivityStreams activityStreams = null;
    private final io.reactivex.disposables.Disposable activityResultsSubscription = null;
    private final com.google.firebase.auth.FirebaseAuth firebaseAuth = null;
    private final com.google.android.gms.auth.api.signin.GoogleSignInOptions googleSignInOptions = null;
    @org.jetbrains.annotations.NotNull()
    private final io.reactivex.subjects.Subject<com.google.android.gnd.system.auth.SignInState> signInState = null;
    
    @javax.inject.Inject()
    public GoogleAuthenticationManager(@org.jetbrains.annotations.NotNull()
    android.content.res.Resources resources, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.system.ActivityStreams activityStreams) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.subjects.Subject<com.google.android.gnd.system.auth.SignInState> getSignInState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.google.android.gnd.model.User getCurrentUser() {
        return null;
    }
    
    @java.lang.Override()
    public void init() {
    }
    
    private final com.google.android.gnd.system.auth.SignInState getStatus() {
        return null;
    }
    
    @java.lang.Override()
    public void signIn() {
    }
    
    @java.lang.Override()
    public void signOut() {
    }
    
    private final com.google.android.gms.auth.api.signin.GoogleSignInClient getGoogleSignInClient(android.app.Activity activity) {
        return null;
    }
    
    private final void onActivityResult(com.google.android.gnd.system.ActivityResult activityResult) {
    }
    
    private final com.google.android.gms.tasks.Task<com.google.firebase.auth.AuthResult> onGoogleSignIn(com.google.android.gms.auth.api.signin.GoogleSignInAccount googleAccount) {
        return null;
    }
    
    private final void onFirebaseAuthSuccess(com.google.firebase.auth.AuthResult authResult) {
    }
    
    private final com.google.firebase.auth.AuthCredential getFirebaseAuthCredential(com.google.android.gms.auth.api.signin.GoogleSignInAccount googleAccount) {
        return null;
    }
    
    private final com.google.android.gnd.model.User toUser(com.google.firebase.auth.FirebaseUser $this$toUser) {
        return null;
    }
}