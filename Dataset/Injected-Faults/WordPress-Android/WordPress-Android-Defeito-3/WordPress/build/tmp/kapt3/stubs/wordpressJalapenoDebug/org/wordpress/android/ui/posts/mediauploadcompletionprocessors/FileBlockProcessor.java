package org.wordpress.android.ui.posts.mediauploadcompletionprocessors;

import java.lang.System;

/**
 * When a File Block's upload is complete, this processor replaces the href pointing to a local file url with a
 * remote url for all a tags present within the wp:file block.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0014J\u0012\u0010\u000b\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/posts/mediauploadcompletionprocessors/FileBlockProcessor;", "Lorg/wordpress/android/ui/posts/mediauploadcompletionprocessors/BlockProcessor;", "localId", "", "mediaFile", "Lorg/wordpress/android/util/helpers/MediaFile;", "(Ljava/lang/String;Lorg/wordpress/android/util/helpers/MediaFile;)V", "processBlockContentDocument", "", "document", "Lorg/jsoup/nodes/Document;", "processBlockJsonAttributes", "jsonAttributes", "Lcom/google/gson/JsonObject;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class FileBlockProcessor extends org.wordpress.android.ui.posts.mediauploadcompletionprocessors.BlockProcessor {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.posts.mediauploadcompletionprocessors.FileBlockProcessor.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String HYPERLINK_TAG = "a";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String HREF_ATTRIBUTE = "href";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ID_ATTRIBUTE = "id";
    
    public FileBlockProcessor(@org.jetbrains.annotations.Nullable()
    java.lang.String localId, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.util.helpers.MediaFile mediaFile) {
        super(null, null);
    }
    
    @java.lang.Override()
    protected boolean processBlockContentDocument(@org.jetbrains.annotations.Nullable()
    org.jsoup.nodes.Document document) {
        return false;
    }
    
    @java.lang.Override()
    protected boolean processBlockJsonAttributes(@org.jetbrains.annotations.Nullable()
    com.google.gson.JsonObject jsonAttributes) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/posts/mediauploadcompletionprocessors/FileBlockProcessor$Companion;", "", "()V", "HREF_ATTRIBUTE", "", "HYPERLINK_TAG", "ID_ATTRIBUTE", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}