package org.wordpress.android.ui.notifications.utils;

import java.lang.System;

/**
 * Injectable wrapper around NotificationsUtils.
 *
 * NotificationsUtils interface is consisted of static methods, which make the client code difficult to test/mock.
 * Main purpose of this wrapper is to make testing easier.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ*\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0010J4\u0010\u0005\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\n\u001a\u00020\u000b2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u00132\u0006\u0010\u000e\u001a\u00020\u000fJ,\u0010\u0005\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0016\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/notifications/utils/NotificationsUtilsWrapper;", "", "formattableContentMapper", "Lorg/wordpress/android/fluxc/tools/FormattableContentMapper;", "(Lorg/wordpress/android/fluxc/tools/FormattableContentMapper;)V", "getSpannableContentForRanges", "Landroid/text/SpannableStringBuilder;", "subject", "Lorg/json/JSONObject;", "blockObject", "textView", "Landroid/widget/TextView;", "onNoteBlockTextClickListener", "Lorg/wordpress/android/ui/notifications/blocks/NoteBlock$OnNoteBlockTextClickListener;", "isFooter", "", "Lorg/wordpress/android/fluxc/tools/FormattableContent;", "formattableContent", "clickHandler", "Lkotlin/Function1;", "Lorg/wordpress/android/fluxc/tools/FormattableRange;", "", "mapJsonToFormattableContent", "org.wordpress.android_wordpressJalapenoDebug"})
@javax.inject.Singleton()
public final class NotificationsUtilsWrapper {
    private final org.wordpress.android.fluxc.tools.FormattableContentMapper formattableContentMapper = null;
    
    @javax.inject.Inject()
    public NotificationsUtilsWrapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.tools.FormattableContentMapper formattableContentMapper) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.text.SpannableStringBuilder getSpannableContentForRanges(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.tools.FormattableContent subject) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.text.SpannableStringBuilder getSpannableContentForRanges(@org.jetbrains.annotations.NotNull()
    org.json.JSONObject subject) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.text.SpannableStringBuilder getSpannableContentForRanges(@org.jetbrains.annotations.NotNull()
    org.json.JSONObject blockObject, @org.jetbrains.annotations.Nullable()
    android.widget.TextView textView, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.notifications.blocks.NoteBlock.OnNoteBlockTextClickListener onNoteBlockTextClickListener, boolean isFooter) {
        return null;
    }
    
    /**
     * Returns a spannable with formatted content based on WP.com note content 'range' data
     *
     * @param formattableContent the data
     * @param textView the TextView that will display the spannnable
     * @param onNoteBlockTextClickListener - click listener for ClickableSpans in the spannable
     * @param isFooter - Set if spannable should apply special formatting
     * @return Spannable string with formatted content
     */
    @org.jetbrains.annotations.NotNull()
    public final android.text.SpannableStringBuilder getSpannableContentForRanges(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.tools.FormattableContent formattableContent, @org.jetbrains.annotations.Nullable()
    android.widget.TextView textView, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.notifications.blocks.NoteBlock.OnNoteBlockTextClickListener onNoteBlockTextClickListener, boolean isFooter) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.text.SpannableStringBuilder getSpannableContentForRanges(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.tools.FormattableContent formattableContent, @org.jetbrains.annotations.NotNull()
    android.widget.TextView textView, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.tools.FormattableRange, kotlin.Unit> clickHandler, boolean isFooter) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.tools.FormattableContent mapJsonToFormattableContent(@org.jetbrains.annotations.NotNull()
    org.json.JSONObject blockObject) {
        return null;
    }
}