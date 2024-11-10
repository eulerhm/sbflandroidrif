package fr.free.nrw.commons.nearby;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 2, d1 = {"\u0000P\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u008e\u0002\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072$\u0010\t\u001a \u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b\u0012\u0004\u0012\u00020\b0\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0018\u0010\u0013\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\b0\n2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0018\u0010\u0015\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\b0\n2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0012\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b\u001a\u0018\u0010\u001b\u001a\u00020\b*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u001d0\u001cH\u0002\u00a8\u0006\u001e"}, d2 = {"placeAdapterDelegate", "Lcom/hannesdorfmann/adapterdelegates4/AdapterDelegate;", "", "Lfr/free/nrw/commons/nearby/Place;", "bookmarkLocationDao", "Lfr/free/nrw/commons/bookmarks/locations/BookmarkLocationsDao;", "onItemClick", "Lkotlin/Function1;", "", "onCameraClicked", "Lkotlin/Function2;", "Landroidx/activity/result/ActivityResultLauncher;", "", "", "onCameraLongPressed", "Lkotlin/Function0;", "", "onGalleryClicked", "onGalleryLongPressed", "onBookmarkClicked", "onBookmarkLongPressed", "onOverflowIconClicked", "Landroid/view/View;", "onOverFlowLongPressed", "onDirectionsClicked", "onDirectionsLongPressed", "inAppCameraLocationPermissionLauncher", "showOrHideAndScrollToIfLast", "Lcom/hannesdorfmann/adapterdelegates4/dsl/AdapterDelegateViewBindingViewHolder;", "Lfr/free/nrw/commons/databinding/ItemPlaceBinding;", "app-commons-v4.2.1-master_prodDebug"})
public final class PlaceAdapterDelegateKt {
    
    @org.jetbrains.annotations.NotNull
    public static final com.hannesdorfmann.adapterdelegates4.AdapterDelegate<java.util.List<fr.free.nrw.commons.nearby.Place>> placeAdapterDelegate(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.bookmarks.locations.BookmarkLocationsDao bookmarkLocationDao, @org.jetbrains.annotations.Nullable
    kotlin.jvm.functions.Function1<? super fr.free.nrw.commons.nearby.Place, kotlin.Unit> onItemClick, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function2<? super fr.free.nrw.commons.nearby.Place, ? super androidx.activity.result.ActivityResultLauncher<java.lang.String[]>, kotlin.Unit> onCameraClicked, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<java.lang.Boolean> onCameraLongPressed, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super fr.free.nrw.commons.nearby.Place, kotlin.Unit> onGalleryClicked, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<java.lang.Boolean> onGalleryLongPressed, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function2<? super fr.free.nrw.commons.nearby.Place, ? super java.lang.Boolean, kotlin.Unit> onBookmarkClicked, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<java.lang.Boolean> onBookmarkLongPressed, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function2<? super fr.free.nrw.commons.nearby.Place, ? super android.view.View, kotlin.Unit> onOverflowIconClicked, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<java.lang.Boolean> onOverFlowLongPressed, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super fr.free.nrw.commons.nearby.Place, kotlin.Unit> onDirectionsClicked, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<java.lang.Boolean> onDirectionsLongPressed, @org.jetbrains.annotations.NotNull
    androidx.activity.result.ActivityResultLauncher<java.lang.String[]> inAppCameraLocationPermissionLauncher) {
        return null;
    }
    
    private static final void showOrHideAndScrollToIfLast(com.hannesdorfmann.adapterdelegates4.dsl.AdapterDelegateViewBindingViewHolder<fr.free.nrw.commons.nearby.Place, fr.free.nrw.commons.databinding.ItemPlaceBinding> $this$showOrHideAndScrollToIfLast) {
    }
}