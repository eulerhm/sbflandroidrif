package com.github.pockethub.android.ui.item.issue;

import android.content.Context;
import android.text.TextUtils;
import android.widget.LinearLayout;
import com.github.pockethub.android.R;
import com.github.pockethub.android.core.issue.IssueUtils;
import com.github.pockethub.android.ui.issue.LabelDrawableSpan;
import com.github.pockethub.android.util.AvatarLoader;
import com.github.pockethub.android.util.HttpImageGetter;
import com.meisolsson.githubsdk.model.Issue;
import com.meisolsson.githubsdk.model.IssueState;
import com.xwray.groupie.kotlinandroidextensions.Item;
import com.xwray.groupie.kotlinandroidextensions.ViewHolder;
import kotlinx.android.synthetic.main.issue_header.*;
import kotlinx.android.synthetic.main.milestone.*;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0016B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0014H\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0017"}, d2 = {"Lcom/github/pockethub/android/ui/item/issue/IssueHeaderItem;", "Lcom/xwray/groupie/kotlinandroidextensions/Item;", "avatarLoader", "Lcom/github/pockethub/android/util/AvatarLoader;", "imageGetter", "Lcom/github/pockethub/android/util/HttpImageGetter;", "context", "Landroid/content/Context;", "actionListener", "Lcom/github/pockethub/android/ui/item/issue/IssueHeaderItem$OnIssueHeaderActionListener;", "issue", "Lcom/meisolsson/githubsdk/model/Issue;", "(Lcom/github/pockethub/android/util/AvatarLoader;Lcom/github/pockethub/android/util/HttpImageGetter;Landroid/content/Context;Lcom/github/pockethub/android/ui/item/issue/IssueHeaderItem$OnIssueHeaderActionListener;Lcom/meisolsson/githubsdk/model/Issue;)V", "getIssue", "()Lcom/meisolsson/githubsdk/model/Issue;", "bind", "", "holder", "Lcom/xwray/groupie/kotlinandroidextensions/ViewHolder;", "position", "", "getLayout", "OnIssueHeaderActionListener", "app_debug"})
public final class IssueHeaderItem extends com.xwray.groupie.kotlinandroidextensions.Item {
    private final com.github.pockethub.android.util.AvatarLoader avatarLoader = null;
    private final com.github.pockethub.android.util.HttpImageGetter imageGetter = null;
    private final android.content.Context context = null;
    private final com.github.pockethub.android.ui.item.issue.IssueHeaderItem.OnIssueHeaderActionListener actionListener = null;
    @org.jetbrains.annotations.NotNull()
    private final com.meisolsson.githubsdk.model.Issue issue = null;
    
    @java.lang.Override()
    public int getLayout() {
        return 0;
    }
    
    @java.lang.Override()
    public void bind(@org.jetbrains.annotations.NotNull()
    com.xwray.groupie.kotlinandroidextensions.ViewHolder holder, int position) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.meisolsson.githubsdk.model.Issue getIssue() {
        return null;
    }
    
    public IssueHeaderItem(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.util.AvatarLoader avatarLoader, @org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.util.HttpImageGetter imageGetter, @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.ui.item.issue.IssueHeaderItem.OnIssueHeaderActionListener actionListener, @org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.model.Issue issue) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&\u00a8\u0006\b"}, d2 = {"Lcom/github/pockethub/android/ui/item/issue/IssueHeaderItem$OnIssueHeaderActionListener;", "", "onAssigneesClicked", "", "onCommitsClicked", "onLabelsClicked", "onMilestonesClicked", "onStateClicked", "app_debug"})
    public static abstract interface OnIssueHeaderActionListener {
        
        public abstract void onCommitsClicked();
        
        public abstract void onStateClicked();
        
        public abstract void onMilestonesClicked();
        
        public abstract void onAssigneesClicked();
        
        public abstract void onLabelsClicked();
    }
}