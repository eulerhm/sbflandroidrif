package com.github.pockethub.android.util;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.github.pockethub.android.R;
import com.meisolsson.githubsdk.model.User;
import okhttp3.HttpUrl;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Avatar utilities
 *
 * @constructor Create avatar helper
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\u001a\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002J\u0014\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002J\u0014\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u0012H\u0002J\u0010\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/github/pockethub/android/util/AvatarLoader;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "cornerRadius", "", "requestManager", "Lcom/bumptech/glide/RequestManager;", "transformation", "Lcom/bumptech/glide/load/resource/bitmap/RoundedCorners;", "bind", "", "view", "Landroid/widget/ImageView;", "user", "Lcom/meisolsson/githubsdk/model/User;", "url", "", "getAvatarUrl", "id", "getMaxAvatarSize", "Companion", "app_debug"})
@javax.inject.Singleton()
public final class AvatarLoader {
    private final com.bumptech.glide.RequestManager requestManager = null;
    private final int cornerRadius = 0;
    private final com.bumptech.glide.load.resource.bitmap.RoundedCorners transformation = null;
    private static final int DISK_CACHE_SIZE = 52428800;
    private static final java.lang.String TAG = "AvatarLoader";
    private static final float CORNER_RADIUS_IN_DIP = 3.0F;
    
    /**
     * The max size of avatar images, used to rescale images to save memory.
     */
    private static int avatarSize;
    public static final com.github.pockethub.android.util.AvatarLoader.Companion Companion = null;
    
    /**
     * Bind view to image at URL
     *
     * @param view The ImageView that is to display the user's avatar.
     * @param user A User object that points to the desired user.
     */
    public final void bind(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView view, @org.jetbrains.annotations.Nullable()
    com.meisolsson.githubsdk.model.User user) {
    }
    
    private final void bind(android.widget.ImageView view, java.lang.String url) {
    }
    
    private final java.lang.String getAvatarUrl(com.meisolsson.githubsdk.model.User user) {
        return null;
    }
    
    private final java.lang.String getAvatarUrl(java.lang.String id) {
        return null;
    }
    
    private final int getMaxAvatarSize(android.content.Context context) {
        return 0;
    }
    
    @javax.inject.Inject()
    public AvatarLoader(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/github/pockethub/android/util/AvatarLoader$Companion;", "", "()V", "CORNER_RADIUS_IN_DIP", "", "DISK_CACHE_SIZE", "", "TAG", "", "avatarSize", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}