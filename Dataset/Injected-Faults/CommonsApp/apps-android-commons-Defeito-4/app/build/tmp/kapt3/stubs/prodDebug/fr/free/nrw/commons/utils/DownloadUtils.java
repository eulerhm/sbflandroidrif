package fr.free.nrw.commons.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0002\u00a8\u0006\f"}, d2 = {"Lfr/free/nrw/commons/utils/DownloadUtils;", "", "()V", "downloadMedia", "", "activity", "Landroid/app/Activity;", "m", "Lfr/free/nrw/commons/Media;", "enqueueRequest", "req", "Landroid/app/DownloadManager$Request;", "app-commons-v4.2.1-master_prodDebug"})
public final class DownloadUtils {
    @org.jetbrains.annotations.NotNull
    public static final fr.free.nrw.commons.utils.DownloadUtils INSTANCE = null;
    
    private DownloadUtils() {
        super();
    }
    
    /**
     * Start the media file downloading to the local SD card/storage. The file can then be opened in
     * Gallery or other apps.
     *
     * @param m Media file to download
     */
    @kotlin.jvm.JvmStatic
    public static final void downloadMedia(@org.jetbrains.annotations.Nullable
    android.app.Activity activity, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.Media m) {
    }
    
    private final void enqueueRequest(android.app.Activity activity, android.app.DownloadManager.Request req) {
    }
}