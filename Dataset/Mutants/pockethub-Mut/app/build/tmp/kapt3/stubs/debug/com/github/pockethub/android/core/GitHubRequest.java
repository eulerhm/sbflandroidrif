package com.github.pockethub.android.core;

import io.reactivex.Single;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&\u00a8\u0006\u0007"}, d2 = {"Lcom/github/pockethub/android/core/GitHubRequest;", "V", "", "execute", "Lio/reactivex/Single;", "page", "", "app_debug"})
public abstract interface GitHubRequest<V extends java.lang.Object> {
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<V> execute(int page);
}