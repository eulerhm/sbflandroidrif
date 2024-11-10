package org.wordpress.android.ui.reader.subfilter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0006\u0007B\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0005\u0082\u0001\u0002\b\t\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/reader/subfilter/BottomSheetUiState;", "", "isVisible", "", "(Z)V", "()Z", "BottomSheetHidden", "BottomSheetVisible", "Lorg/wordpress/android/ui/reader/subfilter/BottomSheetUiState$BottomSheetVisible;", "Lorg/wordpress/android/ui/reader/subfilter/BottomSheetUiState$BottomSheetHidden;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class BottomSheetUiState {
    private final boolean isVisible = false;
    
    private BottomSheetUiState(boolean isVisible) {
        super();
    }
    
    public final boolean isVisible() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/reader/subfilter/BottomSheetUiState$BottomSheetVisible;", "Lorg/wordpress/android/ui/reader/subfilter/BottomSheetUiState;", "title", "Lorg/wordpress/android/ui/utils/UiString;", "categories", "", "Lorg/wordpress/android/ui/reader/subfilter/SubfilterCategory;", "(Lorg/wordpress/android/ui/utils/UiString;Ljava/util/List;)V", "getCategories", "()Ljava/util/List;", "getTitle", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class BottomSheetVisible extends org.wordpress.android.ui.reader.subfilter.BottomSheetUiState {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString title = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.reader.subfilter.SubfilterCategory> categories = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.subfilter.BottomSheetUiState.BottomSheetVisible copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.reader.subfilter.SubfilterCategory> categories) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public BottomSheetVisible(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.reader.subfilter.SubfilterCategory> categories) {
            super(false);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString getTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.reader.subfilter.SubfilterCategory> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.reader.subfilter.SubfilterCategory> getCategories() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/subfilter/BottomSheetUiState$BottomSheetHidden;", "Lorg/wordpress/android/ui/reader/subfilter/BottomSheetUiState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class BottomSheetHidden extends org.wordpress.android.ui.reader.subfilter.BottomSheetUiState {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.reader.subfilter.BottomSheetUiState.BottomSheetHidden INSTANCE = null;
        
        private BottomSheetHidden() {
            super(false);
        }
    }
}