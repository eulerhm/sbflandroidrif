package org.wordpress.android.ui.posts;

import java.lang.System;

/**
 * This helper class allows to create, dismiss or ignore the state of a [dialog] ProgressDialog
 * based on the [state] parameter using its [updateProgressDialogState] method.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J*\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/posts/ProgressDialogHelper;", "", "()V", "updateProgressDialogState", "Landroid/app/ProgressDialog;", "context", "Landroid/content/Context;", "dialog", "state", "Lorg/wordpress/android/ui/posts/ProgressDialogUiState;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class ProgressDialogHelper {
    
    @javax.inject.Inject()
    public ProgressDialogHelper() {
        super();
    }
    
    /**
     * Gets a [dialog] and returns it (eventually) updated based on the [state] UI state.
     *
     * @param context The context to which the Progress Dialog belongs
     * @param dialog The current dialog with its state; can be {@code null}
     * @param state The desired new UI state of the Progress Dialog
     * @param uiHelpers Helper class used to manage the dialog message
     *
     * @return The resulting dialog with its (eventually) modified state. Can be {@code null}
     */
    @org.jetbrains.annotations.Nullable()
    public final android.app.ProgressDialog updateProgressDialogState(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.app.ProgressDialog dialog, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.ProgressDialogUiState state, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
        return null;
    }
}