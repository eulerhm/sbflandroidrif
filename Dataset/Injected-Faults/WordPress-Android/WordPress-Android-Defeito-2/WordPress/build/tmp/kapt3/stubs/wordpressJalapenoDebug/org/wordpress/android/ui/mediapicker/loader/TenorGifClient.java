package org.wordpress.android.ui.mediapicker.loader;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006JO\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\b2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\f0\u00122\u0014\u0010\u0014\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0012\u0004\u0012\u00020\f0\u0012\u00a2\u0006\u0002\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\b*\u0004\u0018\u00010\b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0018"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/loader/TenorGifClient;", "", "context", "Landroid/content/Context;", "tenorApiClient", "Lcom/tenor/android/core/network/IApiClient;", "(Landroid/content/Context;Lcom/tenor/android/core/network/IApiClient;)V", "fittedToMaximumAllowed", "", "getFittedToMaximumAllowed", "(Ljava/lang/Integer;)I", "search", "", "query", "", "position", "loadSize", "onSuccess", "Lkotlin/Function1;", "Lcom/tenor/android/core/response/impl/GifsResponse;", "onFailure", "", "(Ljava/lang/String;ILjava/lang/Integer;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class TenorGifClient {
    private final android.content.Context context = null;
    private final com.tenor.android.core.network.IApiClient tenorApiClient = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.mediapicker.loader.TenorGifClient.Companion Companion = null;
    private static final int MAXIMUM_ALLOWED_LOAD_SIZE = 50;
    
    @javax.inject.Inject()
    public TenorGifClient(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.tenor.android.core.network.IApiClient tenorApiClient) {
        super();
    }
    
    public final void search(@org.jetbrains.annotations.NotNull()
    java.lang.String query, int position, @org.jetbrains.annotations.Nullable()
    java.lang.Integer loadSize, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.tenor.android.core.response.impl.GifsResponse, kotlin.Unit> onSuccess, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Throwable, kotlin.Unit> onFailure) {
    }
    
    private final int getFittedToMaximumAllowed(java.lang.Integer $this$fittedToMaximumAllowed) {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/loader/TenorGifClient$Companion;", "", "()V", "MAXIMUM_ALLOWED_LOAD_SIZE", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}