package com.github.pockethub.android.ui.item.commit;

import com.github.pockethub.android.R;
import com.github.pockethub.android.ui.commit.DiffStyler;
import com.xwray.groupie.kotlinandroidextensions.Item;
import com.xwray.groupie.kotlinandroidextensions.ViewHolder;
import kotlinx.android.synthetic.main.commit_diff_line.*;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u0010"}, d2 = {"Lcom/github/pockethub/android/ui/item/commit/CommitFileLineItem;", "Lcom/xwray/groupie/kotlinandroidextensions/Item;", "diffStyler", "Lcom/github/pockethub/android/ui/commit/DiffStyler;", "line", "", "(Lcom/github/pockethub/android/ui/commit/DiffStyler;Ljava/lang/CharSequence;)V", "getLine", "()Ljava/lang/CharSequence;", "bind", "", "holder", "Lcom/xwray/groupie/kotlinandroidextensions/ViewHolder;", "position", "", "getLayout", "app_debug"})
public final class CommitFileLineItem extends com.xwray.groupie.kotlinandroidextensions.Item {
    private final com.github.pockethub.android.ui.commit.DiffStyler diffStyler = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.CharSequence line = null;
    
    @java.lang.Override()
    public int getLayout() {
        return 0;
    }
    
    @java.lang.Override()
    public void bind(@org.jetbrains.annotations.NotNull()
    com.xwray.groupie.kotlinandroidextensions.ViewHolder holder, int position) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.CharSequence getLine() {
        return null;
    }
    
    public CommitFileLineItem(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.ui.commit.DiffStyler diffStyler, @org.jetbrains.annotations.NotNull()
    java.lang.CharSequence line) {
        super();
    }
}