package com.github.pockethub.android.ui.item.dialog;

import com.github.pockethub.android.R;
import com.github.pockethub.android.util.AvatarLoader;
import com.meisolsson.githubsdk.model.User;
import com.xwray.groupie.kotlinandroidextensions.Item;
import com.xwray.groupie.kotlinandroidextensions.ViewHolder;
import kotlinx.android.synthetic.main.collaborator_item.*;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007H\u0016J\b\u0010\u0010\u001a\u00020\u0007H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0011"}, d2 = {"Lcom/github/pockethub/android/ui/item/dialog/AssigneeDialogItem;", "Lcom/xwray/groupie/kotlinandroidextensions/Item;", "avatarLoader", "Lcom/github/pockethub/android/util/AvatarLoader;", "user", "Lcom/meisolsson/githubsdk/model/User;", "selected", "", "(Lcom/github/pockethub/android/util/AvatarLoader;Lcom/meisolsson/githubsdk/model/User;I)V", "getUser", "()Lcom/meisolsson/githubsdk/model/User;", "bind", "", "holder", "Lcom/xwray/groupie/kotlinandroidextensions/ViewHolder;", "position", "getLayout", "app_debug"})
public final class AssigneeDialogItem extends com.xwray.groupie.kotlinandroidextensions.Item {
    private final com.github.pockethub.android.util.AvatarLoader avatarLoader = null;
    @org.jetbrains.annotations.NotNull()
    private final com.meisolsson.githubsdk.model.User user = null;
    private final int selected = 0;
    
    @java.lang.Override()
    public int getLayout() {
        return 0;
    }
    
    @java.lang.Override()
    public void bind(@org.jetbrains.annotations.NotNull()
    com.xwray.groupie.kotlinandroidextensions.ViewHolder holder, int position) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.meisolsson.githubsdk.model.User getUser() {
        return null;
    }
    
    public AssigneeDialogItem(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.util.AvatarLoader avatarLoader, @org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.model.User user, int selected) {
        super();
    }
}