package org.wordpress.android.ui.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u0014\u0010\b\u001a\u00020\u00032\n\u0010\t\u001a\u00060\nj\u0002`\u000bH&\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingActionClickedListener;", "", "onActionClicked", "", "actionType", "Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState$ActionType;", "bundle", "Landroid/os/Bundle;", "onSubmitButtonClicked", "publishPost", "", "Lorg/wordpress/android/ui/posts/PublishPost;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract interface PrepublishingActionClickedListener {
    
    public abstract void onActionClicked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PrepublishingHomeItemUiState.ActionType actionType, @org.jetbrains.annotations.Nullable()
    android.os.Bundle bundle);
    
    public abstract void onSubmitButtonClicked(boolean publishPost);
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
    public final class DefaultImpls {
    }
}