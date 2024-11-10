package com.github.pockethub.android.ui.issue;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import androidx.core.content.ContextCompat;
import com.github.pockethub.android.Intents.Builder;
import com.github.pockethub.android.R;
import com.github.pockethub.android.accounts.AccountUtils;
import com.github.pockethub.android.core.issue.IssueUtils;
import com.github.pockethub.android.rx.AutoDisposeUtils;
import com.github.pockethub.android.rx.RxProgress;
import com.github.pockethub.android.ui.DialogResultListener;
import com.github.pockethub.android.ui.base.BaseActivity;
import com.github.pockethub.android.ui.TextWatcherAdapter;
import com.github.pockethub.android.util.AvatarLoader;
import com.github.pockethub.android.util.ImageBinPoster;
import com.github.pockethub.android.util.InfoUtils;
import com.github.pockethub.android.util.PermissionsUtils;
import com.github.pockethub.android.util.ToastUtils;
import com.meisolsson.githubsdk.core.ServiceGenerator;
import com.meisolsson.githubsdk.model.Issue;
import com.meisolsson.githubsdk.model.Repository;
import com.meisolsson.githubsdk.model.User;
import com.meisolsson.githubsdk.model.request.issue.IssueRequest;
import com.meisolsson.githubsdk.service.issues.IssueService;
import com.meisolsson.githubsdk.service.repositories.RepositoryCollaboratorService;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kotlinx.android.synthetic.main.activity_issue_edit.*;
import kotlinx.android.synthetic.main.milestone.*;
import retrofit2.Response;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 * Activity to edit or create an issue
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\f\n\u0002\u0010\r\n\u0002\b\u0002\u0018\u0000 B2\u00020\u00012\u00020\u0002:\u0001BB\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\u0012\u0010\u001a\u001a\u00020\u00192\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0002J\"\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0014J\u0006\u0010#\u001a\u00020\u0019J\u0012\u0010$\u001a\u00020\u00192\b\u0010%\u001a\u0004\u0018\u00010&H\u0014J\u0010\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H\u0016J\b\u0010+\u001a\u00020\u0019H\u0014J \u0010,\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010-\u001a\u00020&H\u0016J\u0010\u0010.\u001a\u00020(2\u0006\u0010/\u001a\u00020\u0015H\u0016J+\u00100\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001f2\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u001c022\u0006\u00103\u001a\u000204H\u0016\u00a2\u0006\u0002\u00105J\u0010\u00106\u001a\u00020\u00192\u0006\u00107\u001a\u00020&H\u0014J\b\u00108\u001a\u00020\u0019H\u0002J\b\u00109\u001a\u00020\u0019H\u0002J\b\u0010:\u001a\u00020\u0019H\u0002J\b\u0010;\u001a\u00020\u0019H\u0002J\b\u0010<\u001a\u00020\u0019H\u0002J\b\u0010=\u001a\u00020\u0019H\u0002J\b\u0010>\u001a\u00020\u0019H\u0002J\u0012\u0010?\u001a\u00020\u00192\b\b\u0002\u0010@\u001a\u00020AH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006C"}, d2 = {"Lcom/github/pockethub/android/ui/issue/EditIssueActivity;", "Lcom/github/pockethub/android/ui/base/BaseActivity;", "Lcom/github/pockethub/android/ui/DialogResultListener;", "()V", "assigneeDialog", "Lcom/github/pockethub/android/ui/issue/AssigneeDialog;", "avatars", "Lcom/github/pockethub/android/util/AvatarLoader;", "getAvatars", "()Lcom/github/pockethub/android/util/AvatarLoader;", "setAvatars", "(Lcom/github/pockethub/android/util/AvatarLoader;)V", "issue", "Lcom/meisolsson/githubsdk/model/Issue;", "labelsDialog", "Lcom/github/pockethub/android/ui/issue/LabelsDialog;", "milestoneDialog", "Lcom/github/pockethub/android/ui/issue/MilestoneDialog;", "repository", "Lcom/meisolsson/githubsdk/model/Repository;", "saveItem", "Landroid/view/MenuItem;", "titleTextWatcher", "Lcom/github/pockethub/android/ui/TextWatcherAdapter;", "checkCollaboratorStatus", "", "insertImage", "url", "", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onAddImageClicked", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onDestroy", "onDialogResult", "arguments", "onOptionsItemSelected", "item", "onRequestPermissionsResult", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onSaveInstanceState", "outState", "showCollaboratorOptions", "showImageError", "showMainContent", "startImagePicker", "updateAssignee", "updateLabels", "updateMilestone", "updateSaveMenu", "text", "", "Companion", "app_debug"})
public final class EditIssueActivity extends com.github.pockethub.android.ui.base.BaseActivity implements com.github.pockethub.android.ui.DialogResultListener {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.github.pockethub.android.util.AvatarLoader avatars;
    private com.meisolsson.githubsdk.model.Issue issue;
    private com.meisolsson.githubsdk.model.Repository repository;
    private android.view.MenuItem saveItem;
    private com.github.pockethub.android.ui.issue.MilestoneDialog milestoneDialog;
    private com.github.pockethub.android.ui.issue.AssigneeDialog assigneeDialog;
    private com.github.pockethub.android.ui.issue.LabelsDialog labelsDialog;
    private final com.github.pockethub.android.ui.TextWatcherAdapter titleTextWatcher = null;
    private static final java.lang.String TAG = "EditIssueActivity";
    private static final int REQUEST_CODE_SELECT_PHOTO = 0;
    private static final int READ_PERMISSION_REQUEST = 1;
    public static final com.github.pockethub.android.ui.issue.EditIssueActivity.Companion Companion = null;
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
    protected void onDestroy() {
    }
    
    @java.lang.Override()
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
    }
    
    private final void startImagePicker() {
    }
    
    @java.lang.Override()
    public void onDialogResult(int requestCode, int resultCode, @org.jetbrains.annotations.NotNull()
    android.os.Bundle arguments) {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    public final void onAddImageClicked() {
    }
    
    private final void showImageError() {
    }
    
    private final void insertImage(java.lang.String url) {
    }
    
    private final void showMainContent() {
    }
    
    private final void showCollaboratorOptions() {
    }
    
    private final void updateMilestone() {
    }
    
    private final void updateAssignee() {
    }
    
    private final void updateLabels() {
    }
    
    @java.lang.Override()
    protected void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    private final void updateSaveMenu(java.lang.CharSequence text) {
    }
    
    @java.lang.Override()
    public boolean onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    private final void checkCollaboratorStatus() {
    }
    
    public EditIssueActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J.\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00072\b\u0010\r\u001a\u0004\u0018\u00010\u00072\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\u0010\u0010\b\u001a\u00020\t2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/github/pockethub/android/ui/issue/EditIssueActivity$Companion;", "", "()V", "READ_PERMISSION_REQUEST", "", "REQUEST_CODE_SELECT_PHOTO", "TAG", "", "createIntent", "Landroid/content/Intent;", "issue", "Lcom/meisolsson/githubsdk/model/Issue;", "repositoryOwner", "repositoryName", "user", "Lcom/meisolsson/githubsdk/model/User;", "repository", "Lcom/meisolsson/githubsdk/model/Repository;", "app_debug"})
    public static final class Companion {
        
        /**
         * Create intent to create an issue
         *
         * @param repository
         * @return intent
         */
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent createIntent(@org.jetbrains.annotations.Nullable()
        com.meisolsson.githubsdk.model.Repository repository) {
            return null;
        }
        
        /**
         * Create intent to edit an issue
         *
         * @param issue
         * @param repositoryOwner
         * @param repositoryName
         * @param user
         * @return intent
         */
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent createIntent(@org.jetbrains.annotations.Nullable()
        com.meisolsson.githubsdk.model.Issue issue, @org.jetbrains.annotations.Nullable()
        java.lang.String repositoryOwner, @org.jetbrains.annotations.Nullable()
        java.lang.String repositoryName, @org.jetbrains.annotations.Nullable()
        com.meisolsson.githubsdk.model.User user) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}