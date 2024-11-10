package org.wordpress.android.ui.prefs.categories.list;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u000b\f\rB%\b\u0004\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b\u0082\u0001\u0003\u000e\u000f\u0010\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/prefs/categories/list/UiState;", "", "loadingVisible", "", "contentVisible", "errorVisible", "(ZZZ)V", "getContentVisible", "()Z", "getErrorVisible", "getLoadingVisible", "Content", "Error", "Loading", "Lorg/wordpress/android/ui/prefs/categories/list/UiState$Content;", "Lorg/wordpress/android/ui/prefs/categories/list/UiState$Loading;", "Lorg/wordpress/android/ui/prefs/categories/list/UiState$Error;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class UiState {
    private final boolean loadingVisible = false;
    private final boolean contentVisible = false;
    private final boolean errorVisible = false;
    
    private UiState(boolean loadingVisible, boolean contentVisible, boolean errorVisible) {
        super();
    }
    
    public final boolean getLoadingVisible() {
        return false;
    }
    
    public final boolean getContentVisible() {
        return false;
    }
    
    public final boolean getErrorVisible() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/prefs/categories/list/UiState$Content;", "Lorg/wordpress/android/ui/prefs/categories/list/UiState;", "list", "", "Lorg/wordpress/android/models/CategoryNode;", "(Ljava/util/List;)V", "getList", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Content extends org.wordpress.android.ui.prefs.categories.list.UiState {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.models.CategoryNode> list = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.prefs.categories.list.UiState.Content copy(@org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.models.CategoryNode> list) {
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
        
        public Content(@org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.models.CategoryNode> list) {
            super(false, false, false);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.models.CategoryNode> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.models.CategoryNode> getList() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/prefs/categories/list/UiState$Loading;", "Lorg/wordpress/android/ui/prefs/categories/list/UiState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Loading extends org.wordpress.android.ui.prefs.categories.list.UiState {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.prefs.categories.list.UiState.Loading INSTANCE = null;
        
        private Loading() {
            super(false, false, false);
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0014\u0015B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\u0004\u0018\u00010\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\rX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0012\u0010\u0010\u001a\u00020\tX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u000bR\u0012\u0010\u0012\u001a\u00020\tX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u000b\u0082\u0001\u0002\u0016\u0017\u00a8\u0006\u0018"}, d2 = {"Lorg/wordpress/android/ui/prefs/categories/list/UiState$Error;", "Lorg/wordpress/android/ui/prefs/categories/list/UiState;", "()V", "action", "Lkotlin/Function0;", "", "getAction", "()Lkotlin/jvm/functions/Function0;", "buttonText", "Lorg/wordpress/android/ui/utils/UiString;", "getButtonText", "()Lorg/wordpress/android/ui/utils/UiString;", "image", "", "getImage", "()I", "subtitle", "getSubtitle", "title", "getTitle", "GenericError", "NoConnection", "Lorg/wordpress/android/ui/prefs/categories/list/UiState$Error$NoConnection;", "Lorg/wordpress/android/ui/prefs/categories/list/UiState$Error$GenericError;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class Error extends org.wordpress.android.ui.prefs.categories.list.UiState {
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.utils.UiString buttonText = null;
        @org.jetbrains.annotations.Nullable()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> action = null;
        
        private Error() {
            super(false, false, false);
        }
        
        public abstract int getImage();
        
        @org.jetbrains.annotations.NotNull()
        public abstract org.wordpress.android.ui.utils.UiString getTitle();
        
        @org.jetbrains.annotations.NotNull()
        public abstract org.wordpress.android.ui.utils.UiString getSubtitle();
        
        @org.jetbrains.annotations.Nullable()
        public org.wordpress.android.ui.utils.UiString getButtonText() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public kotlin.jvm.functions.Function0<kotlin.Unit> getAction() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\u0015\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\rH\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\u00020\r8\u0016X\u0097D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000bR\u0014\u0010\u0012\u001a\u00020\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000b\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/ui/prefs/categories/list/UiState$Error$NoConnection;", "Lorg/wordpress/android/ui/prefs/categories/list/UiState$Error;", "action", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)V", "getAction", "()Lkotlin/jvm/functions/Function0;", "buttonText", "Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "getButtonText", "()Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "image", "", "getImage", "()I", "subtitle", "getSubtitle", "title", "getTitle", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class NoConnection extends org.wordpress.android.ui.prefs.categories.list.UiState.Error {
            @org.jetbrains.annotations.NotNull()
            private final kotlin.jvm.functions.Function0<kotlin.Unit> action = null;
            @androidx.annotation.DrawableRes()
            private final int image = org.wordpress.android.R.drawable.img_illustration_cloud_off_152dp;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString.UiStringRes title = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString.UiStringRes subtitle = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString.UiStringRes buttonText = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.prefs.categories.list.UiState.Error.NoConnection copy(@org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function0<kotlin.Unit> action) {
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
            
            public NoConnection(@org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function0<kotlin.Unit> action) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function0<kotlin.Unit> component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public kotlin.jvm.functions.Function0<kotlin.Unit> getAction() {
                return null;
            }
            
            @java.lang.Override()
            public int getImage() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.utils.UiString.UiStringRes getTitle() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.utils.UiString.UiStringRes getSubtitle() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.utils.UiString.UiStringRes getButtonText() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\u0015\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\rH\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\u00020\r8\u0016X\u0097D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000bR\u0014\u0010\u0012\u001a\u00020\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000b\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/ui/prefs/categories/list/UiState$Error$GenericError;", "Lorg/wordpress/android/ui/prefs/categories/list/UiState$Error;", "action", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)V", "getAction", "()Lkotlin/jvm/functions/Function0;", "buttonText", "Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "getButtonText", "()Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "image", "", "getImage", "()I", "subtitle", "getSubtitle", "title", "getTitle", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class GenericError extends org.wordpress.android.ui.prefs.categories.list.UiState.Error {
            @org.jetbrains.annotations.NotNull()
            private final kotlin.jvm.functions.Function0<kotlin.Unit> action = null;
            @androidx.annotation.DrawableRes()
            private final int image = org.wordpress.android.R.drawable.img_illustration_cloud_off_152dp;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString.UiStringRes title = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString.UiStringRes subtitle = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString.UiStringRes buttonText = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.prefs.categories.list.UiState.Error.GenericError copy(@org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function0<kotlin.Unit> action) {
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
            
            public GenericError(@org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function0<kotlin.Unit> action) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function0<kotlin.Unit> component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public kotlin.jvm.functions.Function0<kotlin.Unit> getAction() {
                return null;
            }
            
            @java.lang.Override()
            public int getImage() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.utils.UiString.UiStringRes getTitle() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.utils.UiString.UiStringRes getSubtitle() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.utils.UiString.UiStringRes getButtonText() {
                return null;
            }
        }
    }
}