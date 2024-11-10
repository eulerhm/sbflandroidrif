package org.wordpress.android.ui.reader.views;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010,\u001a\u00020-H\u0002J\u0016\u0010.\u001a\u00020-2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020%0!H\u0002J\u0010\u0010/\u001a\u00020-2\u0006\u00100\u001a\u00020\rH\u0002J\b\u00101\u001a\u00020-H\u0002J\u0010\u00102\u001a\u00020\r2\u0006\u00103\u001a\u00020\u0017H\u0002J\b\u00104\u001a\u00020-H\u0002J\b\u00105\u001a\u00020-H\u0002J\b\u00106\u001a\u00020-H\u0002J\u0014\u00107\u001a\u00020-2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020%0!J\u001a\u00108\u001a\u00020-*\u00020\u00102\f\u00109\u001a\b\u0012\u0004\u0012\u00020-0:H\u0002R\u0014\u0010\t\u001a\u00020\u00078BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\u000eR\u001c\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00108BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00078BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u000bR\u0014\u0010\u0016\u001a\u00020\u00178BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u001e\u0010\u001a\u001a\u00020\u001b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00170!8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\"\u0010#R\u0016\u0010$\u001a\n\u0012\u0004\u0012\u00020%\u0018\u00010!X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010&\u001a\u00020\'8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+\u00a8\u0006;"}, d2 = {"Lorg/wordpress/android/ui/reader/views/ReaderExpandableTagsView;", "Lcom/google/android/material/chip/ChipGroup;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "hiddenTagChipsCount", "getHiddenTagChipsCount", "()I", "isOverflowIndicatorChipOutsideBounds", "", "()Z", "lastVisibleTagChip", "Landroid/view/View;", "kotlin.jvm.PlatformType", "getLastVisibleTagChip", "()Landroid/view/View;", "lastVisibleTagChipIndex", "getLastVisibleTagChipIndex", "overflowIndicatorChip", "Lcom/google/android/material/chip/Chip;", "getOverflowIndicatorChip", "()Lcom/google/android/material/chip/Chip;", "readerTracker", "Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;", "getReaderTracker", "()Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;", "setReaderTracker", "(Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;)V", "tagChips", "", "getTagChips", "()Ljava/util/List;", "tagsUiState", "Lorg/wordpress/android/ui/reader/discover/interests/TagUiState;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "addOverflowIndicatorChip", "", "addTagChips", "expandLayout", "isChecked", "hideTagChipsOutsideBounds", "isChipWithinBounds", "chip", "showAllTagChips", "updateLastVisibleTagChip", "updateOverflowIndicatorChip", "updateUi", "preLayout", "what", "Lkotlin/Function0;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderExpandableTagsView extends com.google.android.material.chip.ChipGroup {
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    @javax.inject.Inject()
    public org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker;
    private java.util.List<org.wordpress.android.ui.reader.discover.interests.TagUiState> tagsUiState;
    
    @kotlin.jvm.JvmOverloads()
    public ReaderExpandableTagsView(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads()
    public ReaderExpandableTagsView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads()
    public ReaderExpandableTagsView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs, int defStyleAttr) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiHelpers getUiHelpers() {
        return null;
    }
    
    public final void setUiHelpers(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.reader.tracker.ReaderTracker getReaderTracker() {
        return null;
    }
    
    public final void setReaderTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.tracker.ReaderTracker p0) {
    }
    
    private final java.util.List<com.google.android.material.chip.Chip> getTagChips() {
        return null;
    }
    
    private final com.google.android.material.chip.Chip getOverflowIndicatorChip() {
        return null;
    }
    
    private final int getLastVisibleTagChipIndex() {
        return 0;
    }
    
    private final android.view.View getLastVisibleTagChip() {
        return null;
    }
    
    private final int getHiddenTagChipsCount() {
        return 0;
    }
    
    private final boolean isOverflowIndicatorChipOutsideBounds() {
        return false;
    }
    
    public final void updateUi(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.ui.reader.discover.interests.TagUiState> tagsUiState) {
    }
    
    private final void addOverflowIndicatorChip() {
    }
    
    private final void addTagChips(java.util.List<org.wordpress.android.ui.reader.discover.interests.TagUiState> tagsUiState) {
    }
    
    private final void expandLayout(boolean isChecked) {
    }
    
    private final void showAllTagChips() {
    }
    
    private final void hideTagChipsOutsideBounds() {
    }
    
    private final boolean isChipWithinBounds(com.google.android.material.chip.Chip chip) {
        return false;
    }
    
    private final void updateLastVisibleTagChip() {
    }
    
    private final void updateOverflowIndicatorChip() {
    }
    
    private final void preLayout(android.view.View $this$preLayout, kotlin.jvm.functions.Function0<kotlin.Unit> what) {
    }
}