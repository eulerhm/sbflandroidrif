package fr.free.nrw.commons.nearby.fragments;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B!\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ$\u0010\f\u001a \u0012\u0004\u0012\u00020\u000e\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000f\u0012\u0004\u0012\u00020\u00120\rJ\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00120\u0015J\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00120\u0015J\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u0018\u0010\u0019\u001a\u0014\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00120\rJ\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u0010\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u0012H\u0002J\b\u0010 \u001a\u00020\u0012H\u0002J\u0010\u0010!\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020\u000eH\u0002J\u001c\u0010#\u001a\u00020\u0012*\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020\u000bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lfr/free/nrw/commons/nearby/fragments/CommonPlaceClickActions;", "", "applicationKvStore", "Lfr/free/nrw/commons/kvstore/JsonKvStore;", "activity", "Landroid/app/Activity;", "contributionController", "Lfr/free/nrw/commons/contributions/ContributionController;", "(Lfr/free/nrw/commons/kvstore/JsonKvStore;Landroid/app/Activity;Lfr/free/nrw/commons/contributions/ContributionController;)V", "onBookmarkLongPressed", "Lkotlin/Function0;", "", "onCameraClicked", "Lkotlin/Function2;", "Lfr/free/nrw/commons/nearby/Place;", "Landroidx/activity/result/ActivityResultLauncher;", "", "", "", "onCameraLongPressed", "onDirectionsClicked", "Lkotlin/Function1;", "onDirectionsLongPressed", "onGalleryClicked", "onGalleryLongPressed", "onOverflowClicked", "Landroid/view/View;", "onOverflowLongPressed", "openWebView", "link", "Landroid/net/Uri;", "setPositiveButton", "showLoginDialog", "storeSharedPrefs", "selectedPlace", "enableBy", "Landroidx/appcompat/widget/PopupMenu;", "menuId", "", "hasLink", "app-commons-v4.2.1-main_betaDebug"})
public final class CommonPlaceClickActions {
    private final fr.free.nrw.commons.kvstore.JsonKvStore applicationKvStore = null;
    private final android.app.Activity activity = null;
    private final fr.free.nrw.commons.contributions.ContributionController contributionController = null;
    
    @javax.inject.Inject
    public CommonPlaceClickActions(@org.jetbrains.annotations.NotNull
    @javax.inject.Named(value = "default_preferences")
    fr.free.nrw.commons.kvstore.JsonKvStore applicationKvStore, @org.jetbrains.annotations.NotNull
    android.app.Activity activity, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.contributions.ContributionController contributionController) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlin.jvm.functions.Function2<fr.free.nrw.commons.nearby.Place, androidx.activity.result.ActivityResultLauncher<java.lang.String[]>, kotlin.Unit> onCameraClicked() {
        return null;
    }
    
    /**
     * Shows the Label for the Icon when it's long pressed
     */
    @org.jetbrains.annotations.NotNull
    public final kotlin.jvm.functions.Function0<java.lang.Boolean> onCameraLongPressed() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlin.jvm.functions.Function0<java.lang.Boolean> onGalleryLongPressed() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlin.jvm.functions.Function0<java.lang.Boolean> onBookmarkLongPressed() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlin.jvm.functions.Function0<java.lang.Boolean> onDirectionsLongPressed() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlin.jvm.functions.Function0<java.lang.Boolean> onOverflowLongPressed() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlin.jvm.functions.Function1<fr.free.nrw.commons.nearby.Place, kotlin.Unit> onGalleryClicked() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlin.jvm.functions.Function2<fr.free.nrw.commons.nearby.Place, android.view.View, kotlin.Unit> onOverflowClicked() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlin.jvm.functions.Function1<fr.free.nrw.commons.nearby.Place, kotlin.Unit> onDirectionsClicked() {
        return null;
    }
    
    private final void storeSharedPrefs(fr.free.nrw.commons.nearby.Place selectedPlace) {
    }
    
    private final boolean openWebView(android.net.Uri link) {
        return false;
    }
    
    private final void enableBy(androidx.appcompat.widget.PopupMenu $this$enableBy, int menuId, boolean hasLink) {
    }
    
    private final void showLoginDialog() {
    }
    
    private final void setPositiveButton() {
    }
}