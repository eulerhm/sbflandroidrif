package org.wordpress.android.ui.sitecreation.misc;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0002\u0010\tJ\u000e\u0010\u001e\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\u0014J\u0010\u0010 \u001a\u00020\b2\u0006\u0010!\u001a\u00020\"H\u0002J\u0018\u0010#\u001a\u00020\b2\u0006\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\'J\u0016\u0010(\u001a\u00020\b2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020)R\u0016\u0010\n\u001a\n \u000b*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n \u000b*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n \u000b*\u0004\u0018\u00010\u000e0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n \u000b*\u0004\u0018\u00010\u00100\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n \u000b*\u0004\u0018\u00010\u00100\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R(\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\b\u0018\u00010\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0019\u001a\n \u000b*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u001a\u001a\n \u000b*\u0004\u0018\u00010\u001b0\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/misc/SearchInputWithHeader;", "", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "rootView", "Landroid/view/View;", "onClear", "Lkotlin/Function0;", "", "(Lorg/wordpress/android/ui/utils/UiHelpers;Landroid/view/View;Lkotlin/jvm/functions/Function0;)V", "clearAllLayout", "kotlin.jvm.PlatformType", "divider", "headerLayout", "Landroid/view/ViewGroup;", "headerSubtitle", "Landroid/widget/TextView;", "headerTitle", "onTextChanged", "Lkotlin/Function1;", "", "getOnTextChanged", "()Lkotlin/jvm/functions/Function1;", "setOnTextChanged", "(Lkotlin/jvm/functions/Function1;)V", "progressBar", "searchInput", "Landroid/widget/EditText;", "showKeyboardHandler", "Landroid/os/Handler;", "setInputText", "text", "showKeyboard", "shouldShow", "", "updateHeader", "context", "Landroid/content/Context;", "uiState", "Lorg/wordpress/android/ui/sitecreation/misc/SiteCreationHeaderUiState;", "updateSearchInput", "Lorg/wordpress/android/ui/sitecreation/misc/SiteCreationSearchInputUiState;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SearchInputWithHeader {
    private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
    private final android.view.ViewGroup headerLayout = null;
    private final android.widget.TextView headerTitle = null;
    private final android.widget.TextView headerSubtitle = null;
    private final android.widget.EditText searchInput = null;
    private final android.view.View progressBar = null;
    private final android.view.View clearAllLayout = null;
    private final android.view.View divider = null;
    private final android.os.Handler showKeyboardHandler = null;
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onTextChanged;
    
    public SearchInputWithHeader(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers uiHelpers, @org.jetbrains.annotations.NotNull()
    android.view.View rootView, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClear) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> getOnTextChanged() {
        return null;
    }
    
    public final void setOnTextChanged(@org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> p0) {
    }
    
    public final void setInputText(@org.jetbrains.annotations.NotNull()
    java.lang.String text) {
    }
    
    public final void updateHeader(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.sitecreation.misc.SiteCreationHeaderUiState uiState) {
    }
    
    public final void updateSearchInput(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.misc.SiteCreationSearchInputUiState uiState) {
    }
    
    private final void showKeyboard(boolean shouldShow) {
    }
}