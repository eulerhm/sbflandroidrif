package org.wordpress.android.ui.mysite;

import java.lang.System;

@kotlin.Suppress(names = {"ComplexMethod"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0002H\u0016\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteAdapterDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "updatedItem", "org.wordpress.android_wordpressJalapenoDebug"})
public final class MySiteAdapterDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<org.wordpress.android.ui.mysite.MySiteCardAndItem> {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.mysite.MySiteAdapterDiffCallback INSTANCE = null;
    
    private MySiteAdapterDiffCallback() {
        super();
    }
    
    @java.lang.Override()
    public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteCardAndItem oldItem, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteCardAndItem updatedItem) {
        return false;
    }
    
    @java.lang.Override()
    public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteCardAndItem oldItem, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteCardAndItem newItem) {
        return false;
    }
}