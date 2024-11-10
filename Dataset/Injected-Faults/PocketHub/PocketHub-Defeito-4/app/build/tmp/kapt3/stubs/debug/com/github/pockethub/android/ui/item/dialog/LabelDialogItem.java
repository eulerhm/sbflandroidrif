package com.github.pockethub.android.ui.item.dialog;

import com.github.pockethub.android.R;
import com.github.pockethub.android.ui.issue.LabelDrawableSpan;
import com.meisolsson.githubsdk.model.Label;
import com.xwray.groupie.kotlinandroidextensions.Item;
import com.xwray.groupie.kotlinandroidextensions.ViewHolder;
import kotlinx.android.synthetic.main.label_item.*;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0011H\u0016J\u0006\u0010\u0013\u001a\u00020\rR\u001e\u0010\b\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0014"}, d2 = {"Lcom/github/pockethub/android/ui/item/dialog/LabelDialogItem;", "Lcom/xwray/groupie/kotlinandroidextensions/Item;", "label", "Lcom/meisolsson/githubsdk/model/Label;", "selected", "", "(Lcom/meisolsson/githubsdk/model/Label;Z)V", "<set-?>", "isSelected", "()Z", "getLabel", "()Lcom/meisolsson/githubsdk/model/Label;", "bind", "", "holder", "Lcom/xwray/groupie/kotlinandroidextensions/ViewHolder;", "position", "", "getLayout", "toggleSelected", "app_debug"})
public final class LabelDialogItem extends com.xwray.groupie.kotlinandroidextensions.Item {
    private boolean isSelected;
    @org.jetbrains.annotations.NotNull()
    private final com.meisolsson.githubsdk.model.Label label = null;
    
    public final boolean isSelected() {
        return false;
    }
    
    @java.lang.Override()
    public int getLayout() {
        return 0;
    }
    
    @java.lang.Override()
    public void bind(@org.jetbrains.annotations.NotNull()
    com.xwray.groupie.kotlinandroidextensions.ViewHolder holder, int position) {
    }
    
    public final void toggleSelected() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.meisolsson.githubsdk.model.Label getLabel() {
        return null;
    }
    
    public LabelDialogItem(@org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.model.Label label, boolean selected) {
        super();
    }
}