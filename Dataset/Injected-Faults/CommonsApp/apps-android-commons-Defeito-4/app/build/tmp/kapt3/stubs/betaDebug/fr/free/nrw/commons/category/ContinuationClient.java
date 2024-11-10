package fr.free.nrw.commons.category;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004JH\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\r0\f2\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00072$\u0010\u0010\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\n\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\f0\u0011J&\u0010\u0012\u001a\u00020\u00132\u0014\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0007J\u0010\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0007H\u0002J\u0018\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0007H\u0004J\u0018\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0007H\u0004J.\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\r0\f2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\f2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0007H&R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R(\u0010\t\u001a\u001c\u0012\u0004\u0012\u00020\u0007\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\n0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lfr/free/nrw/commons/category/ContinuationClient;", "Network", "Domain", "", "()V", "continuationExists", "", "", "", "continuationStore", "", "continuationRequest", "Lio/reactivex/Single;", "", "prefix", "name", "requestFunction", "Lkotlin/Function1;", "handleContinuationResponse", "", "continuation", "key", "hasMorePagesFor", "resetContinuation", "category", "resetUserContinuation", "userName", "responseMapper", "networkResult", "app-commons-v4.2.1-main_betaDebug"})
public abstract class ContinuationClient<Network extends java.lang.Object, Domain extends java.lang.Object> {
    private final java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.String>> continuationStore = null;
    private final java.util.Map<java.lang.String, java.lang.Boolean> continuationExists = null;
    
    public ContinuationClient() {
        super();
    }
    
    private final boolean hasMorePagesFor(java.lang.String key) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Single<java.util.List<Domain>> continuationRequest(@org.jetbrains.annotations.NotNull
    java.lang.String prefix, @org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.util.Map<java.lang.String, java.lang.String>, ? extends io.reactivex.Single<Network>> requestFunction) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<java.util.List<Domain>> responseMapper(@org.jetbrains.annotations.NotNull
    io.reactivex.Single<Network> networkResult, @org.jetbrains.annotations.Nullable
    java.lang.String key);
    
    public final void handleContinuationResponse(@org.jetbrains.annotations.Nullable
    java.util.Map<java.lang.String, java.lang.String> continuation, @org.jetbrains.annotations.Nullable
    java.lang.String key) {
    }
    
    protected final void resetContinuation(@org.jetbrains.annotations.NotNull
    java.lang.String prefix, @org.jetbrains.annotations.NotNull
    java.lang.String category) {
    }
    
    /**
     * Remove the existing the key from continuationExists and continuationStore
     *
     * @param prefix
     * @param userName the username
     */
    protected final void resetUserContinuation(@org.jetbrains.annotations.NotNull
    java.lang.String prefix, @org.jetbrains.annotations.NotNull
    java.lang.String userName) {
    }
}