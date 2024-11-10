package org.wordpress.android.ui.mediapicker.insert;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J%\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fR\u0014\u0010\u0002\u001a\u00020\u00038VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/insert/MediaInsertUseCase;", "", "actionTitle", "", "getActionTitle", "()I", "insert", "Lkotlinx/coroutines/flow/Flow;", "Lorg/wordpress/android/ui/mediapicker/insert/MediaInsertHandler$InsertModel;", "identifiers", "", "Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract interface MediaInsertUseCase {
    
    public abstract int getActionTitle();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.ui.mediapicker.MediaItem.Identifier> identifiers, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends org.wordpress.android.ui.mediapicker.insert.MediaInsertHandler.InsertModel>> continuation);
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
    public final class DefaultImpls {
        
        public static int getActionTitle(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.insert.MediaInsertUseCase $this) {
            return 0;
        }
        
        @org.jetbrains.annotations.Nullable()
        public static java.lang.Object insert(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mediapicker.insert.MediaInsertUseCase $this, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.mediapicker.MediaItem.Identifier> identifiers, @org.jetbrains.annotations.NotNull()
        kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends org.wordpress.android.ui.mediapicker.insert.MediaInsertHandler.InsertModel>> p2) {
            return null;
        }
    }
}