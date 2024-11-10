package org.wordpress.android.ui.activitylog.list;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000b0\t\u00a2\u0006\u0002\u0010\fJ\u000e\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0018J\u0018\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u0010H\u0002J\u0010\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u001eH\u0016R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/EventItemViewHolder;", "Lorg/wordpress/android/ui/activitylog/list/ActivityLogViewHolder;", "parent", "Landroid/view/ViewGroup;", "itemClickListener", "Lkotlin/Function1;", "Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem;", "", "secondaryActionClickListener", "Lkotlin/Function2;", "Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$SecondaryAction;", "", "(Landroid/view/ViewGroup;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)V", "actionButton", "Landroid/widget/ImageButton;", "container", "Landroid/view/View;", "summary", "Landroid/widget/TextView;", "text", "thumbnail", "Landroid/widget/ImageView;", "bind", "activity", "Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$Event;", "renderMoreMenu", "event", "v", "updateChanges", "bundle", "Landroid/os/Bundle;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class EventItemViewHolder extends org.wordpress.android.ui.activitylog.list.ActivityLogViewHolder {
    private final kotlin.jvm.functions.Function1<org.wordpress.android.ui.activitylog.list.ActivityLogListItem, kotlin.Unit> itemClickListener = null;
    private final kotlin.jvm.functions.Function2<org.wordpress.android.ui.activitylog.list.ActivityLogListItem.SecondaryAction, org.wordpress.android.ui.activitylog.list.ActivityLogListItem, java.lang.Boolean> secondaryActionClickListener = null;
    private final android.widget.TextView summary = null;
    private final android.widget.TextView text = null;
    private final android.widget.ImageView thumbnail = null;
    private final android.view.View container = null;
    private final android.widget.ImageButton actionButton = null;
    
    public EventItemViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.activitylog.list.ActivityLogListItem, kotlin.Unit> itemClickListener, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super org.wordpress.android.ui.activitylog.list.ActivityLogListItem.SecondaryAction, ? super org.wordpress.android.ui.activitylog.list.ActivityLogListItem, java.lang.Boolean> secondaryActionClickListener) {
        super(null, 0);
    }
    
    @java.lang.Override()
    public void updateChanges(@org.jetbrains.annotations.NotNull()
    android.os.Bundle bundle) {
    }
    
    public final void bind(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.activitylog.list.ActivityLogListItem.Event activity) {
    }
    
    private final void renderMoreMenu(org.wordpress.android.ui.activitylog.list.ActivityLogListItem.Event event, android.view.View v) {
    }
}