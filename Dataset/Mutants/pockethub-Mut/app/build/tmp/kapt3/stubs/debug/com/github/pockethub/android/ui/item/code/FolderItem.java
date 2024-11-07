package com.github.pockethub.android.ui.item.code;

import com.github.pockethub.android.R;
import com.github.pockethub.android.core.code.FullTree;
import com.github.pockethub.android.core.commit.CommitUtils;
import com.xwray.groupie.kotlinandroidextensions.Item;
import com.xwray.groupie.kotlinandroidextensions.ViewHolder;
import kotlinx.android.synthetic.main.folder_item.*;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000e"}, d2 = {"Lcom/github/pockethub/android/ui/item/code/FolderItem;", "Lcom/xwray/groupie/kotlinandroidextensions/Item;", "folder", "Lcom/github/pockethub/android/core/code/FullTree$Folder;", "(Lcom/github/pockethub/android/core/code/FullTree$Folder;)V", "getFolder", "()Lcom/github/pockethub/android/core/code/FullTree$Folder;", "bind", "", "holder", "Lcom/xwray/groupie/kotlinandroidextensions/ViewHolder;", "position", "", "getLayout", "app_debug"})
public final class FolderItem extends com.xwray.groupie.kotlinandroidextensions.Item {
    @org.jetbrains.annotations.NotNull()
    private final com.github.pockethub.android.core.code.FullTree.Folder folder = null;
    
    @java.lang.Override()
    public int getLayout() {
        return 0;
    }
    
    @java.lang.Override()
    public void bind(@org.jetbrains.annotations.NotNull()
    com.xwray.groupie.kotlinandroidextensions.ViewHolder holder, int position) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.pockethub.android.core.code.FullTree.Folder getFolder() {
        return null;
    }
    
    public FolderItem(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.core.code.FullTree.Folder folder) {
        super();
    }
}