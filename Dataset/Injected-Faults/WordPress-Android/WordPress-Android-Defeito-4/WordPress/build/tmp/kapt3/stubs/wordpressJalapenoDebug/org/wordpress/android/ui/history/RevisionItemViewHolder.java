package org.wordpress.android.ui.history;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u000e\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u000eJ\u0010\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u001eH\u0016R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lorg/wordpress/android/ui/history/RevisionItemViewHolder;", "Lorg/wordpress/android/ui/history/HistoryViewHolder;", "parent", "Landroid/view/ViewGroup;", "itemClickListener", "Lkotlin/Function1;", "Lorg/wordpress/android/ui/history/HistoryListItem;", "", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "(Landroid/view/ViewGroup;Lkotlin/jvm/functions/Function1;Lorg/wordpress/android/util/image/ImageManager;)V", "avatar", "Landroid/widget/ImageView;", "boundRevision", "Lorg/wordpress/android/ui/history/HistoryListItem$Revision;", "container", "Landroid/view/View;", "diffAdditions", "Landroid/widget/TextView;", "diffDeletions", "diffLayout", "Landroid/widget/LinearLayout;", "getImageManager", "()Lorg/wordpress/android/util/image/ImageManager;", "subtitle", "title", "bind", "revision", "updateChanges", "bundle", "Landroid/os/Bundle;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class RevisionItemViewHolder extends org.wordpress.android.ui.history.HistoryViewHolder {
    private final kotlin.jvm.functions.Function1<org.wordpress.android.ui.history.HistoryListItem, kotlin.Unit> itemClickListener = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.util.image.ImageManager imageManager = null;
    private final android.view.View container = null;
    private final android.widget.ImageView avatar = null;
    private final android.widget.TextView title = null;
    private final android.widget.TextView subtitle = null;
    private final android.widget.LinearLayout diffLayout = null;
    private final android.widget.TextView diffAdditions = null;
    private final android.widget.TextView diffDeletions = null;
    private org.wordpress.android.ui.history.HistoryListItem.Revision boundRevision;
    
    public RevisionItemViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.history.HistoryListItem, kotlin.Unit> itemClickListener, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager imageManager) {
        super(null, 0);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.image.ImageManager getImageManager() {
        return null;
    }
    
    public final void bind(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.history.HistoryListItem.Revision revision) {
    }
    
    @java.lang.Override()
    public void updateChanges(@org.jetbrains.annotations.NotNull()
    android.os.Bundle bundle) {
    }
}