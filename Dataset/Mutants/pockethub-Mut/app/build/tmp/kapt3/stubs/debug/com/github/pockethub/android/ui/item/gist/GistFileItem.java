package com.github.pockethub.android.ui.item.gist;

import com.github.pockethub.android.R;
import com.meisolsson.githubsdk.model.GistFile;
import com.xwray.groupie.kotlinandroidextensions.Item;
import com.xwray.groupie.kotlinandroidextensions.ViewHolder;
import kotlinx.android.synthetic.main.gist_file_item.*;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000e"}, d2 = {"Lcom/github/pockethub/android/ui/item/gist/GistFileItem;", "Lcom/xwray/groupie/kotlinandroidextensions/Item;", "gistFile", "Lcom/meisolsson/githubsdk/model/GistFile;", "(Lcom/meisolsson/githubsdk/model/GistFile;)V", "getGistFile", "()Lcom/meisolsson/githubsdk/model/GistFile;", "bind", "", "holer", "Lcom/xwray/groupie/kotlinandroidextensions/ViewHolder;", "position", "", "getLayout", "app_debug"})
public final class GistFileItem extends com.xwray.groupie.kotlinandroidextensions.Item {
    @org.jetbrains.annotations.NotNull()
    private final com.meisolsson.githubsdk.model.GistFile gistFile = null;
    
    @java.lang.Override()
    public int getLayout() {
        return 0;
    }
    
    @java.lang.Override()
    public void bind(@org.jetbrains.annotations.NotNull()
    com.xwray.groupie.kotlinandroidextensions.ViewHolder holer, int position) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.meisolsson.githubsdk.model.GistFile getGistFile() {
        return null;
    }
    
    public GistFileItem(@org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.model.GistFile gistFile) {
        super();
    }
}