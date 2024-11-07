package com.github.pockethub.android.ui.issue;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import com.github.pockethub.android.R;
import com.github.pockethub.android.ui.base.BaseActivity;
import com.github.pockethub.android.ui.SingleChoiceDialogFragment;
import com.github.pockethub.android.ui.item.dialog.MilestoneDialogItem;
import com.meisolsson.githubsdk.model.Milestone;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

/**
 * Dialog fragment to select an issue milestone
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u001c\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0013"}, d2 = {"Lcom/github/pockethub/android/ui/issue/MilestoneDialogFragment;", "Lcom/github/pockethub/android/ui/SingleChoiceDialogFragment;", "()V", "choices", "", "Lcom/meisolsson/githubsdk/model/Milestone;", "getChoices", "()Ljava/util/List;", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onItemClick", "", "item", "Lcom/xwray/groupie/Item;", "view", "Landroid/view/View;", "Companion", "app_debug"})
public final class MilestoneDialogFragment extends com.github.pockethub.android.ui.SingleChoiceDialogFragment {
    public static final com.github.pockethub.android.ui.issue.MilestoneDialogFragment.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    private final java.util.List<com.meisolsson.githubsdk.model.Milestone> getChoices() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.app.Dialog onCreateDialog(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onItemClick(@org.jetbrains.annotations.NotNull()
    com.xwray.groupie.Item<?> item, @org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public MilestoneDialogFragment() {
        super();
    }
    
    /**
     * Get selected milestone from results bundle
     */
    @org.jetbrains.annotations.Nullable()
    public static final com.meisolsson.githubsdk.model.Milestone getSelected(@org.jetbrains.annotations.NotNull()
    android.os.Bundle arguments) {
        return null;
    }
    
    /**
     * Confirm message and deliver callback to given activity
     */
    public static final void show(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.ui.base.BaseActivity activity, int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.Nullable()
    java.lang.String message, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.meisolsson.githubsdk.model.Milestone> choices, int selectedChoice) {
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007JJ\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0016\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0011j\b\u0012\u0004\u0012\u00020\u0004`\u00122\u0006\u0010\u0013\u001a\u00020\fH\u0007\u00a8\u0006\u0014"}, d2 = {"Lcom/github/pockethub/android/ui/issue/MilestoneDialogFragment$Companion;", "", "()V", "getSelected", "Lcom/meisolsson/githubsdk/model/Milestone;", "arguments", "Landroid/os/Bundle;", "show", "", "activity", "Lcom/github/pockethub/android/ui/base/BaseActivity;", "requestCode", "", "title", "", "message", "choices", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "selectedChoice", "app_debug"})
    public static final class Companion {
        
        /**
         * Get selected milestone from results bundle
         */
        @org.jetbrains.annotations.Nullable()
        public final com.meisolsson.githubsdk.model.Milestone getSelected(@org.jetbrains.annotations.NotNull()
        android.os.Bundle arguments) {
            return null;
        }
        
        /**
         * Confirm message and deliver callback to given activity
         */
        public final void show(@org.jetbrains.annotations.NotNull()
        com.github.pockethub.android.ui.base.BaseActivity activity, int requestCode, @org.jetbrains.annotations.NotNull()
        java.lang.String title, @org.jetbrains.annotations.Nullable()
        java.lang.String message, @org.jetbrains.annotations.NotNull()
        java.util.ArrayList<com.meisolsson.githubsdk.model.Milestone> choices, int selectedChoice) {
        }
        
        private Companion() {
            super();
        }
    }
}