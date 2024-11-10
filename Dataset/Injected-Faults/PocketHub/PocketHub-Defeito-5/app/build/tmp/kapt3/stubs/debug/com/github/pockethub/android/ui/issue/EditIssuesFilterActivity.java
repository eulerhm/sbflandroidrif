package com.github.pockethub.android.ui.issue;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import com.github.pockethub.android.Intents.Builder;
import com.github.pockethub.android.R;
import com.github.pockethub.android.core.issue.IssueFilter;
import com.github.pockethub.android.ui.DialogResultListener;
import com.github.pockethub.android.ui.base.BaseActivity;
import com.github.pockethub.android.util.AvatarLoader;
import com.github.pockethub.android.util.InfoUtils;
import kotlinx.android.synthetic.main.activity_issues_filter_edit.*;
import javax.inject.Inject;

/**
 * Activity to create or edit an issues filter for a repository
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 02\u00020\u00012\u00020\u0002:\u00010B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0006\u0010\u0012\u001a\u00020\u0013J\u0012\u0010\u0014\u001a\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J \u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u0016H\u0016J\u0006\u0010 \u001a\u00020\u0013J\u0006\u0010!\u001a\u00020\u0013J\u0010\u0010\"\u001a\u00020\u00182\u0006\u0010#\u001a\u00020$H\u0016J\u0010\u0010%\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\u0016H\u0014J\u0018\u0010\'\u001a\u00020\u00132\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u001dH\u0002J\u0018\u0010+\u001a\u00020\u00132\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u001dH\u0002J\u0018\u0010,\u001a\u00020\u00132\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u001dH\u0002J\b\u0010-\u001a\u00020\u0013H\u0002J\b\u0010.\u001a\u00020\u0013H\u0002J\b\u0010/\u001a\u00020\u0013H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00061"}, d2 = {"Lcom/github/pockethub/android/ui/issue/EditIssuesFilterActivity;", "Lcom/github/pockethub/android/ui/base/BaseActivity;", "Lcom/github/pockethub/android/ui/DialogResultListener;", "()V", "assigneeDialog", "Lcom/github/pockethub/android/ui/issue/AssigneeDialog;", "avatars", "Lcom/github/pockethub/android/util/AvatarLoader;", "getAvatars", "()Lcom/github/pockethub/android/util/AvatarLoader;", "setAvatars", "(Lcom/github/pockethub/android/util/AvatarLoader;)V", "filter", "Lcom/github/pockethub/android/core/issue/IssueFilter;", "labelsDialog", "Lcom/github/pockethub/android/ui/issue/LabelsDialog;", "milestoneDialog", "Lcom/github/pockethub/android/ui/issue/MilestoneDialog;", "onAssigneeClicked", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "", "options", "Landroid/view/Menu;", "onDialogResult", "requestCode", "", "resultCode", "arguments", "onLabelsClicked", "onMilestoneClicked", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onSaveInstanceState", "outState", "onSortOrderChanged", "radioGroup", "Landroid/widget/RadioGroup;", "checkedId", "onSortTypeChanged", "onStatusChanged", "updateAssignee", "updateLabels", "updateMilestone", "Companion", "app_debug"})
public final class EditIssuesFilterActivity extends com.github.pockethub.android.ui.base.BaseActivity implements com.github.pockethub.android.ui.DialogResultListener {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.github.pockethub.android.util.AvatarLoader avatars;
    private com.github.pockethub.android.ui.issue.LabelsDialog labelsDialog;
    private com.github.pockethub.android.ui.issue.MilestoneDialog milestoneDialog;
    private com.github.pockethub.android.ui.issue.AssigneeDialog assigneeDialog;
    private com.github.pockethub.android.core.issue.IssueFilter filter;
    private static final int REQUEST_LABELS = 1;
    private static final int REQUEST_MILESTONE = 2;
    private static final int REQUEST_ASSIGNEE = 3;
    public static final com.github.pockethub.android.ui.issue.EditIssuesFilterActivity.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.pockethub.android.util.AvatarLoader getAvatars() {
        return null;
    }
    
    public final void setAvatars(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.util.AvatarLoader p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public boolean onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu options) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @java.lang.Override()
    protected void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    public final void onAssigneeClicked() {
    }
    
    public final void onMilestoneClicked() {
    }
    
    public final void onLabelsClicked() {
    }
    
    private final void onStatusChanged(android.widget.RadioGroup radioGroup, int checkedId) {
    }
    
    private final void onSortOrderChanged(android.widget.RadioGroup radioGroup, int checkedId) {
    }
    
    private final void onSortTypeChanged(android.widget.RadioGroup radioGroup, int checkedId) {
    }
    
    private final void updateLabels() {
    }
    
    private final void updateMilestone() {
    }
    
    private final void updateAssignee() {
    }
    
    @java.lang.Override()
    public void onDialogResult(int requestCode, int resultCode, @org.jetbrains.annotations.NotNull()
    android.os.Bundle arguments) {
    }
    
    public EditIssuesFilterActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/github/pockethub/android/ui/issue/EditIssuesFilterActivity$Companion;", "", "()V", "REQUEST_ASSIGNEE", "", "REQUEST_LABELS", "REQUEST_MILESTONE", "createIntent", "Landroid/content/Intent;", "filter", "Lcom/github/pockethub/android/core/issue/IssueFilter;", "app_debug"})
    public static final class Companion {
        
        /**
         * Create intent for creating an issue filter for the given repository
         *
         * @param filter
         * @return intent
         */
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent createIntent(@org.jetbrains.annotations.NotNull()
        com.github.pockethub.android.core.issue.IssueFilter filter) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}