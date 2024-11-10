package org.wordpress.android.util.experiments;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001:\u0001,B=\b\u0007\u0012\u0012\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\b\u0001\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\u0006\u0010\u001b\u001a\u00020\u001cJ\u0011\u0010\u001d\u001a\u00020\u001eH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001fJ\u0006\u0010 \u001a\u00020\u001cJ\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0002J\u001d\u0010%\u001a\u00020\u00122\u0006\u0010&\u001a\u00020\u00052\u0006\u0010\'\u001a\u00020(H\u0000\u00a2\u0006\u0002\b)J\u0010\u0010*\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020$H\u0002J\u0006\u0010+\u001a\u00020\u001cR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R!\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\u00148BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006-"}, d2 = {"Lorg/wordpress/android/util/experiments/ExPlat;", "", "experiments", "Ldagger/Lazy;", "", "Lorg/wordpress/android/util/experiments/Experiment;", "experimentStore", "Lorg/wordpress/android/fluxc/store/ExperimentStore;", "appLog", "Lorg/wordpress/android/fluxc/utils/AppLogWrapper;", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "(Ldagger/Lazy;Lorg/wordpress/android/fluxc/store/ExperimentStore;Lorg/wordpress/android/fluxc/utils/AppLogWrapper;Lorg/wordpress/android/fluxc/store/AccountStore;Lkotlinx/coroutines/CoroutineScope;)V", "activeVariations", "", "", "Lorg/wordpress/android/fluxc/model/experiments/Variation;", "experimentNames", "", "getExperimentNames", "()Ljava/util/List;", "experimentNames$delegate", "Lkotlin/Lazy;", "platform", "Lorg/wordpress/android/fluxc/store/ExperimentStore$Platform;", "clear", "", "fetchAssignments", "Lorg/wordpress/android/fluxc/store/ExperimentStore$OnAssignmentsFetched;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "forceRefresh", "getAssignments", "Lorg/wordpress/android/fluxc/model/experiments/Assignments;", "refreshStrategy", "Lorg/wordpress/android/util/experiments/ExPlat$RefreshStrategy;", "getVariation", "experiment", "shouldRefreshIfStale", "", "getVariation$org_wordpress_android_wordpressJalapenoDebug", "refresh", "refreshIfNeeded", "RefreshStrategy", "org.wordpress.android_wordpressJalapenoDebug"})
@javax.inject.Singleton()
public final class ExPlat {
    private final dagger.Lazy<java.util.Set<org.wordpress.android.util.experiments.Experiment>> experiments = null;
    private final org.wordpress.android.fluxc.store.ExperimentStore experimentStore = null;
    private final org.wordpress.android.fluxc.utils.AppLogWrapper appLog = null;
    private final org.wordpress.android.fluxc.store.AccountStore accountStore = null;
    private final kotlinx.coroutines.CoroutineScope coroutineScope = null;
    private final org.wordpress.android.fluxc.store.ExperimentStore.Platform platform = org.wordpress.android.fluxc.store.ExperimentStore.Platform.WORDPRESS_ANDROID;
    private final java.util.Map<java.lang.String, org.wordpress.android.fluxc.model.experiments.Variation> activeVariations = null;
    private final kotlin.Lazy experimentNames$delegate = null;
    
    @javax.inject.Inject()
    public ExPlat(@org.jetbrains.annotations.NotNull()
    dagger.Lazy<java.util.Set<org.wordpress.android.util.experiments.Experiment>> experiments, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.ExperimentStore experimentStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.utils.AppLogWrapper appLog, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore accountStore, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "APPLICATION_SCOPE")
    kotlinx.coroutines.CoroutineScope coroutineScope) {
        super();
    }
    
    private final java.util.List<java.lang.String> getExperimentNames() {
        return null;
    }
    
    public final void refreshIfNeeded() {
    }
    
    public final void forceRefresh() {
    }
    
    public final void clear() {
    }
    
    /**
     * This returns the current active [Variation] for the provided [Experiment].
     *
     * If no active [Variation] is found, we can assume this is the first time this method is being
     * called for the provided [Experiment] during the current session. In this case, the [Variation]
     * is returned from the cached [Assignments] and then set as active. If the cached [Assignments]
     * is stale and [shouldRefreshIfStale] is `true`, then new [Assignments] are fetched and their
     * variations are going to be returned by this method on the next session.
     *
     * If the provided [Experiment] was not included in [ExPlat.start], then [Control] is returned.
     * If [BuildConfig.DEBUG] is `true`, an [IllegalArgumentException] is thrown instead.
     */
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.model.experiments.Variation getVariation$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.experiments.Experiment experiment, boolean shouldRefreshIfStale) {
        return null;
    }
    
    private final void refresh(org.wordpress.android.util.experiments.ExPlat.RefreshStrategy refreshStrategy) {
    }
    
    private final org.wordpress.android.fluxc.model.experiments.Assignments getAssignments(org.wordpress.android.util.experiments.ExPlat.RefreshStrategy refreshStrategy) {
        return null;
    }
    
    private final java.lang.Object fetchAssignments(kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.store.ExperimentStore.OnAssignmentsFetched> continuation) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/util/experiments/ExPlat$RefreshStrategy;", "", "(Ljava/lang/String;I)V", "ALWAYS", "IF_STALE", "NEVER", "org.wordpress.android_wordpressJalapenoDebug"})
    static enum RefreshStrategy {
        /*public static final*/ ALWAYS /* = new ALWAYS() */,
        /*public static final*/ IF_STALE /* = new IF_STALE() */,
        /*public static final*/ NEVER /* = new NEVER() */;
        
        RefreshStrategy() {
        }
    }
}