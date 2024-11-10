package org.wordpress.android.ui.jetpack.common.providers;

import java.lang.System;

/**
 * This class provides the available item choices for Jetpack Backup Download & Restore
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0002\u0006\u0007B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/ui/jetpack/common/providers/JetpackAvailableItemsProvider;", "", "()V", "getAvailableItems", "", "Lorg/wordpress/android/ui/jetpack/common/providers/JetpackAvailableItemsProvider$JetpackAvailableItem;", "JetpackAvailableItem", "JetpackAvailableItemType", "org.wordpress.android_wordpressJalapenoDebug"})
@javax.inject.Singleton()
public final class JetpackAvailableItemsProvider {
    
    @javax.inject.Inject()
    public JetpackAvailableItemsProvider() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.jetpack.common.providers.JetpackAvailableItemsProvider.JetpackAvailableItem> getAvailableItems() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000bJ.\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/ui/jetpack/common/providers/JetpackAvailableItemsProvider$JetpackAvailableItem;", "", "availableItemType", "Lorg/wordpress/android/ui/jetpack/common/providers/JetpackAvailableItemsProvider$JetpackAvailableItemType;", "labelResId", "", "labelHintResId", "(Lorg/wordpress/android/ui/jetpack/common/providers/JetpackAvailableItemsProvider$JetpackAvailableItemType;ILjava/lang/Integer;)V", "getAvailableItemType", "()Lorg/wordpress/android/ui/jetpack/common/providers/JetpackAvailableItemsProvider$JetpackAvailableItemType;", "getLabelHintResId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getLabelResId", "()I", "component1", "component2", "component3", "copy", "(Lorg/wordpress/android/ui/jetpack/common/providers/JetpackAvailableItemsProvider$JetpackAvailableItemType;ILjava/lang/Integer;)Lorg/wordpress/android/ui/jetpack/common/providers/JetpackAvailableItemsProvider$JetpackAvailableItem;", "equals", "", "other", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class JetpackAvailableItem {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.jetpack.common.providers.JetpackAvailableItemsProvider.JetpackAvailableItemType availableItemType = null;
        private final int labelResId = 0;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer labelHintResId = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.jetpack.common.providers.JetpackAvailableItemsProvider.JetpackAvailableItem copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.jetpack.common.providers.JetpackAvailableItemsProvider.JetpackAvailableItemType availableItemType, @androidx.annotation.StringRes()
        int labelResId, @org.jetbrains.annotations.Nullable()
        @androidx.annotation.StringRes()
        java.lang.Integer labelHintResId) {
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
        
        public JetpackAvailableItem(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.jetpack.common.providers.JetpackAvailableItemsProvider.JetpackAvailableItemType availableItemType, @androidx.annotation.StringRes()
        int labelResId, @org.jetbrains.annotations.Nullable()
        @androidx.annotation.StringRes()
        java.lang.Integer labelHintResId) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.jetpack.common.providers.JetpackAvailableItemsProvider.JetpackAvailableItemType component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.jetpack.common.providers.JetpackAvailableItemsProvider.JetpackAvailableItemType getAvailableItemType() {
            return null;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int getLabelResId() {
            return 0;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getLabelHintResId() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/jetpack/common/providers/JetpackAvailableItemsProvider$JetpackAvailableItemType;", "", "id", "", "(Ljava/lang/String;II)V", "getId", "()I", "THEMES", "PLUGINS", "MEDIA_UPLOADS", "SQLS", "ROOTS", "CONTENTS", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum JetpackAvailableItemType {
        /*public static final*/ THEMES /* = new THEMES(0) */,
        /*public static final*/ PLUGINS /* = new PLUGINS(0) */,
        /*public static final*/ MEDIA_UPLOADS /* = new MEDIA_UPLOADS(0) */,
        /*public static final*/ SQLS /* = new SQLS(0) */,
        /*public static final*/ ROOTS /* = new ROOTS(0) */,
        /*public static final*/ CONTENTS /* = new CONTENTS(0) */;
        private final int id = 0;
        
        JetpackAvailableItemType(int id) {
        }
        
        public final int getId() {
            return 0;
        }
    }
}