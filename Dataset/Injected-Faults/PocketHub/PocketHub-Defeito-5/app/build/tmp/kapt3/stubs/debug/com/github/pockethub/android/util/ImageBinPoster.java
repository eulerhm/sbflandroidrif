package com.github.pockethub.android.util;

import android.content.Context;
import android.net.Uri;
import io.reactivex.Single;
import okio.Okio;
import java.io.IOException;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0007J\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/github/pockethub/android/util/ImageBinPoster;", "", "()V", "client", "Lokhttp3/OkHttpClient;", "getUrl", "", "body", "post", "Lio/reactivex/Single;", "Lokhttp3/Response;", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "bytes", "", "app_debug"})
public final class ImageBinPoster {
    private static final okhttp3.OkHttpClient client = null;
    public static final com.github.pockethub.android.util.ImageBinPoster INSTANCE = null;
    
    /**
     * Post the image to ImageBin
     *
     * @param context A context
     * @param uri The content URI
     * @return Single containing the network Response
     */
    @org.jetbrains.annotations.NotNull()
    public static final io.reactivex.Single<okhttp3.Response> post(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.net.Uri uri) {
        return null;
    }
    
    /**
     * Post the image to ImageBin
     *
     * @param bytes Bytes of the image to post
     * @return Single containing the network Response
     */
    @org.jetbrains.annotations.NotNull()
    public static final io.reactivex.Single<okhttp3.Response> post(@org.jetbrains.annotations.Nullable()
    byte[] bytes) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public static final java.lang.String getUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String body) {
        return null;
    }
    
    private ImageBinPoster() {
        super();
    }
}