package com.github.pockethub.android;

import android.accounts.Account;
import android.app.Application;
import android.content.Context;
import com.github.pockethub.android.accounts.LoginActivity;
import com.github.pockethub.android.accounts.LoginActivity_MembersInjector;
import com.github.pockethub.android.accounts.LoginWebViewActivity;
import com.github.pockethub.android.core.commit.CommitStore;
import com.github.pockethub.android.core.commit.CommitStore_Factory;
import com.github.pockethub.android.core.commit.RefreshCommitTaskFactory;
import com.github.pockethub.android.core.gist.GistStore;
import com.github.pockethub.android.core.gist.GistStore_Factory;
import com.github.pockethub.android.core.gist.RefreshGistTaskFactory;
import com.github.pockethub.android.core.issue.IssueStore;
import com.github.pockethub.android.core.issue.IssueStore_Factory;
import com.github.pockethub.android.core.issue.RefreshIssueTaskFactory;
import com.github.pockethub.android.core.user.UserComparator;
import com.github.pockethub.android.core.user.UserComparator_Factory;
import com.github.pockethub.android.dagger.ActivityBuilder_BranchFileViewActivity;
import com.github.pockethub.android.dagger.ActivityBuilder_CommitCompareViewActivity;
import com.github.pockethub.android.dagger.ActivityBuilder_CommitFileViewActivity;
import com.github.pockethub.android.dagger.ActivityBuilder_CommitViewActivity;
import com.github.pockethub.android.dagger.ActivityBuilder_CreateCommitCommentActivity;
import com.github.pockethub.android.dagger.ActivityBuilder_CreateGistActivity;
import com.github.pockethub.android.dagger.ActivityBuilder_CreateGistCommentActivity;
import com.github.pockethub.android.dagger.ActivityBuilder_CreateIssueCommentActivity;
import com.github.pockethub.android.dagger.ActivityBuilder_EditGistCommentActivity;
import com.github.pockethub.android.dagger.ActivityBuilder_EditIssueActivity;
import com.github.pockethub.android.dagger.ActivityBuilder_EditIssueCommentActivity;
import com.github.pockethub.android.dagger.ActivityBuilder_EditIssuesFilterActivity;
import com.github.pockethub.android.dagger.ActivityBuilder_FiltersViewActivity;
import com.github.pockethub.android.dagger.ActivityBuilder_GistFilesViewActivity;
import com.github.pockethub.android.dagger.ActivityBuilder_GistsViewActivity;
import com.github.pockethub.android.dagger.ActivityBuilder_IssueBrowseActivity;
import com.github.pockethub.android.dagger.ActivityBuilder_IssueSearchActivity;
import com.github.pockethub.android.dagger.ActivityBuilder_IssuesViewActivity;
import com.github.pockethub.android.dagger.ActivityBuilder_LoginActivity;
import com.github.pockethub.android.dagger.ActivityBuilder_LoginWebViewActivity;
import com.github.pockethub.android.dagger.ActivityBuilder_MainActivity;
import com.github.pockethub.android.dagger.ActivityBuilder_NotificationActivity;
import com.github.pockethub.android.dagger.ActivityBuilder_RepositoryContributorsActivity;
import com.github.pockethub.android.dagger.ActivityBuilder_RepositoryViewActivity;
import com.github.pockethub.android.dagger.ActivityBuilder_SearchActivity;
import com.github.pockethub.android.dagger.ActivityBuilder_UriLauncherActivity;
import com.github.pockethub.android.dagger.ActivityBuilder_UserViewActivity;
import com.github.pockethub.android.dagger.CommitCompareViewFragmentProvider_CommitCompareListFragment;
import com.github.pockethub.android.dagger.CommitViewFragmentProvider_CommitDiffListFragment;
import com.github.pockethub.android.dagger.CreateCommentFragmentProvider_RawCommentFragment;
import com.github.pockethub.android.dagger.CreateCommentFragmentProvider_RenderedCommentFragment;
import com.github.pockethub.android.dagger.DialogFragmentBuilder_AssigneeDialogFragment;
import com.github.pockethub.android.dagger.DialogFragmentBuilder_ConfirmDialogFragment;
import com.github.pockethub.android.dagger.DialogFragmentBuilder_LabelsDialogFragment;
import com.github.pockethub.android.dagger.DialogFragmentBuilder_MilestoneDialogFragment;
import com.github.pockethub.android.dagger.DialogFragmentBuilder_RefDialogFragment;
import com.github.pockethub.android.dagger.FiltersViewFragmentProvider_FilterListFragment;
import com.github.pockethub.android.dagger.GistFilesViewFragmentProvider_GistFileFragment;
import com.github.pockethub.android.dagger.GistsPagerFragmentProvider_MyGistsFragment;
import com.github.pockethub.android.dagger.GistsPagerFragmentProvider_PublicGistsFragment;
import com.github.pockethub.android.dagger.GistsPagerFragmentProvider_StarredGistsFragment;
import com.github.pockethub.android.dagger.GistsViewFragmentProvider_GistFragment;
import com.github.pockethub.android.dagger.HomePagerFragmentProvider_MembersFragment;
import com.github.pockethub.android.dagger.HomePagerFragmentProvider_MyFollowersFragment;
import com.github.pockethub.android.dagger.HomePagerFragmentProvider_MyFollowingFragment;
import com.github.pockethub.android.dagger.HomePagerFragmentProvider_OrganizationNewsFragment;
import com.github.pockethub.android.dagger.HomePagerFragmentProvider_RepositoryListFragment;
import com.github.pockethub.android.dagger.HomePagerFragmentProvider_UserReceivedNewsFragment;
import com.github.pockethub.android.dagger.IssueBrowseFragmentProvider_IssuesFragment;
import com.github.pockethub.android.dagger.IssueDashboardPagerFragmentProvider_DashboardIssueFragment;
import com.github.pockethub.android.dagger.IssueSearchFragmentProvider_SearchIssueListFragment;
import com.github.pockethub.android.dagger.IssuesViewFragmentProvider_IssueFragment;
import com.github.pockethub.android.dagger.MainFragmentProvider_FilterListFragment;
import com.github.pockethub.android.dagger.MainFragmentProvider_GistsPagerFragment;
import com.github.pockethub.android.dagger.MainFragmentProvider_HomePagerFragment;
import com.github.pockethub.android.dagger.MainFragmentProvider_IssueDashboardPagerFragment;
import com.github.pockethub.android.dagger.NotificationFragmentProvider_NotificationListFragment;
import com.github.pockethub.android.dagger.RepositoryContributorsFragmentProvider_RepositoryContributorsFragment;
import com.github.pockethub.android.dagger.RepositoryViewFragmentProvider_CommitListFragment;
import com.github.pockethub.android.dagger.RepositoryViewFragmentProvider_IssuesFragment;
import com.github.pockethub.android.dagger.RepositoryViewFragmentProvider_RepositoryCodeFragment;
import com.github.pockethub.android.dagger.RepositoryViewFragmentProvider_RepositoryNewsFragment;
import com.github.pockethub.android.dagger.RepositoryViewFragmentProvider_RepositoryReadmeFragment;
import com.github.pockethub.android.dagger.SearchActivityFragmentProvider_SearchRepositoryListFragment;
import com.github.pockethub.android.dagger.SearchActivityFragmentProvider_SearchUserListFragment;
import com.github.pockethub.android.dagger.ServiceBuilder_ProvideSyncAdapterService;
import com.github.pockethub.android.dagger.UserViewFragmentProvider_UserCreatedNewsFragment;
import com.github.pockethub.android.dagger.UserViewFragmentProvider_UserFollowersFragment;
import com.github.pockethub.android.dagger.UserViewFragmentProvider_UserFollowingFragment;
import com.github.pockethub.android.dagger.UserViewFragmentProvider_UserRepositoryListFragment;
import com.github.pockethub.android.persistence.AccountDataManager;
import com.github.pockethub.android.persistence.AccountDataManager_Factory;
import com.github.pockethub.android.persistence.AccountDataManager_MembersInjector;
import com.github.pockethub.android.persistence.DatabaseCache;
import com.github.pockethub.android.persistence.DatabaseCache_Factory;
import com.github.pockethub.android.persistence.DatabaseCache_MembersInjector;
import com.github.pockethub.android.persistence.OrganizationRepositoriesFactory;
import com.github.pockethub.android.persistence.OrganizationRepositoriesFactory_Factory;
import com.github.pockethub.android.persistence.OrganizationRepositoriesFactory_MembersInjector;
import com.github.pockethub.android.persistence.Organizations;
import com.github.pockethub.android.persistence.Organizations_Factory;
import com.github.pockethub.android.sync.SyncAdapter;
import com.github.pockethub.android.sync.SyncAdapterService;
import com.github.pockethub.android.sync.SyncAdapterService_MembersInjector;
import com.github.pockethub.android.sync.SyncAdapter_Factory;
import com.github.pockethub.android.sync.SyncCampaignFactory;
import com.github.pockethub.android.sync.SyncCampaignFactory_Factory;
import com.github.pockethub.android.ui.ConfirmDialogFragment;
import com.github.pockethub.android.ui.MainActivity;
import com.github.pockethub.android.ui.MainActivity_MembersInjector;
import com.github.pockethub.android.ui.NewsFragment_MembersInjector;
import com.github.pockethub.android.ui.code.RepositoryCodeFragment;
import com.github.pockethub.android.ui.comment.RawCommentFragment;
import com.github.pockethub.android.ui.comment.RenderedCommentFragment;
import com.github.pockethub.android.ui.comment.RenderedCommentFragment_MembersInjector;
import com.github.pockethub.android.ui.commit.CommitCompareListFragment;
import com.github.pockethub.android.ui.commit.CommitCompareListFragment_MembersInjector;
import com.github.pockethub.android.ui.commit.CommitCompareViewActivity;
import com.github.pockethub.android.ui.commit.CommitDiffListFragment;
import com.github.pockethub.android.ui.commit.CommitDiffListFragment_MembersInjector;
import com.github.pockethub.android.ui.commit.CommitFileViewActivity;
import com.github.pockethub.android.ui.commit.CommitFileViewActivity_MembersInjector;
import com.github.pockethub.android.ui.commit.CommitListFragment;
import com.github.pockethub.android.ui.commit.CommitListFragment_MembersInjector;
import com.github.pockethub.android.ui.commit.CommitViewActivity;
import com.github.pockethub.android.ui.gist.CreateCommentActivity;
import com.github.pockethub.android.ui.gist.CreateGistActivity;
import com.github.pockethub.android.ui.gist.EditCommentActivity;
import com.github.pockethub.android.ui.gist.GistFileFragment;
import com.github.pockethub.android.ui.gist.GistFileFragment_MembersInjector;
import com.github.pockethub.android.ui.gist.GistFilesViewActivity;
import com.github.pockethub.android.ui.gist.GistFilesViewActivity_MembersInjector;
import com.github.pockethub.android.ui.gist.GistFragment;
import com.github.pockethub.android.ui.gist.GistFragment_MembersInjector;
import com.github.pockethub.android.ui.gist.GistsFragment_MembersInjector;
import com.github.pockethub.android.ui.gist.GistsPagerFragment;
import com.github.pockethub.android.ui.gist.GistsViewActivity;
import com.github.pockethub.android.ui.gist.GistsViewActivity_MembersInjector;
import com.github.pockethub.android.ui.gist.MyGistsFragment;
import com.github.pockethub.android.ui.gist.MyGistsFragment_MembersInjector;
import com.github.pockethub.android.ui.gist.PublicGistsFragment;
import com.github.pockethub.android.ui.gist.StarredGistsFragment;
import com.github.pockethub.android.ui.issue.AssigneeDialogFragment;
import com.github.pockethub.android.ui.issue.AssigneeDialogFragment_MembersInjector;
import com.github.pockethub.android.ui.issue.DashboardIssueFragment;
import com.github.pockethub.android.ui.issue.DashboardIssueFragment_MembersInjector;
import com.github.pockethub.android.ui.issue.EditAssigneeTaskFactory;
import com.github.pockethub.android.ui.issue.EditIssueActivity;
import com.github.pockethub.android.ui.issue.EditIssueActivity_MembersInjector;
import com.github.pockethub.android.ui.issue.EditIssuesFilterActivity;
import com.github.pockethub.android.ui.issue.EditIssuesFilterActivity_MembersInjector;
import com.github.pockethub.android.ui.issue.EditLabelsTaskFactory;
import com.github.pockethub.android.ui.issue.EditMilestoneTaskFactory;
import com.github.pockethub.android.ui.issue.EditStateTaskFactory;
import com.github.pockethub.android.ui.issue.FilterListFragment;
import com.github.pockethub.android.ui.issue.FilterListFragment_MembersInjector;
import com.github.pockethub.android.ui.issue.FiltersViewActivity;
import com.github.pockethub.android.ui.issue.FiltersViewActivity_MembersInjector;
import com.github.pockethub.android.ui.issue.IssueBrowseActivity;
import com.github.pockethub.android.ui.issue.IssueDashboardPagerFragment;
import com.github.pockethub.android.ui.issue.IssueFragment;
import com.github.pockethub.android.ui.issue.IssueFragment_MembersInjector;
import com.github.pockethub.android.ui.issue.IssueSearchActivity;
import com.github.pockethub.android.ui.issue.IssuesFragment;
import com.github.pockethub.android.ui.issue.IssuesFragment_MembersInjector;
import com.github.pockethub.android.ui.issue.IssuesViewActivity;
import com.github.pockethub.android.ui.issue.IssuesViewActivity_MembersInjector;
import com.github.pockethub.android.ui.issue.LabelsDialogFragment;
import com.github.pockethub.android.ui.issue.MilestoneDialogFragment;
import com.github.pockethub.android.ui.issue.SearchIssueListFragment;
import com.github.pockethub.android.ui.issue.SearchIssueListFragment_MembersInjector;
import com.github.pockethub.android.ui.notification.NotificationActivity;
import com.github.pockethub.android.ui.notification.NotificationListFragment;
import com.github.pockethub.android.ui.notification.NotificationListFragment_MembersInjector;
import com.github.pockethub.android.ui.ref.BranchFileViewActivity;
import com.github.pockethub.android.ui.ref.BranchFileViewActivity_MembersInjector;
import com.github.pockethub.android.ui.ref.RefDialogFragment;
import com.github.pockethub.android.ui.repo.RepositoryContributorsActivity;
import com.github.pockethub.android.ui.repo.RepositoryContributorsFragment;
import com.github.pockethub.android.ui.repo.RepositoryContributorsFragment_MembersInjector;
import com.github.pockethub.android.ui.repo.RepositoryListFragment;
import com.github.pockethub.android.ui.repo.RepositoryListFragment_MembersInjector;
import com.github.pockethub.android.ui.repo.RepositoryNewsFragment;
import com.github.pockethub.android.ui.repo.RepositoryReadmeFragment;
import com.github.pockethub.android.ui.repo.RepositoryViewActivity;
import com.github.pockethub.android.ui.repo.UserRepositoryListFragment;
import com.github.pockethub.android.ui.repo.UserRepositoryListFragment_MembersInjector;
import com.github.pockethub.android.ui.search.SearchActivity;
import com.github.pockethub.android.ui.search.SearchRepositoryListFragment;
import com.github.pockethub.android.ui.search.SearchRepositoryListFragment_MembersInjector;
import com.github.pockethub.android.ui.search.SearchUserListFragment;
import com.github.pockethub.android.ui.search.SearchUserListFragment_MembersInjector;
import com.github.pockethub.android.ui.user.HomePagerFragment;
import com.github.pockethub.android.ui.user.MembersFragment;
import com.github.pockethub.android.ui.user.MyFollowersFragment;
import com.github.pockethub.android.ui.user.MyFollowingFragment;
import com.github.pockethub.android.ui.user.OrganizationNewsFragment;
import com.github.pockethub.android.ui.user.PagedUserFragment_MembersInjector;
import com.github.pockethub.android.ui.user.UriLauncherActivity;
import com.github.pockethub.android.ui.user.UserCreatedNewsFragment;
import com.github.pockethub.android.ui.user.UserFollowersFragment;
import com.github.pockethub.android.ui.user.UserFollowingFragment;
import com.github.pockethub.android.ui.user.UserReceivedNewsFragment;
import com.github.pockethub.android.ui.user.UserViewActivity;
import com.github.pockethub.android.util.AvatarLoader;
import com.github.pockethub.android.util.AvatarLoader_Factory;
import com.github.pockethub.android.util.HttpImageGetter;
import com.github.pockethub.android.util.HttpImageGetter_Factory;
import com.meisolsson.githubsdk.service.activity.NotificationService;
import com.meisolsson.githubsdk.service.gists.GistService;
import com.meisolsson.githubsdk.service.issues.IssueService;
import com.meisolsson.githubsdk.service.repositories.RepositoryCommentService;
import com.meisolsson.githubsdk.service.repositories.RepositoryCommitService;
import com.meisolsson.githubsdk.service.repositories.RepositoryService;
import com.meisolsson.githubsdk.service.search.SearchService;
import com.meisolsson.githubsdk.service.users.UserService;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication_MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.DispatchingAndroidInjector_Factory;
import dagger.android.support.DaggerAppCompatActivity_MembersInjector;
import dagger.android.support.DaggerAppCompatDialogFragment_MembersInjector;
import dagger.android.support.DaggerFragment_MembersInjector;
import dagger.internal.DoubleCheck;
import dagger.internal.InstanceFactory;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import java.io.File;
import java.util.Collections;
import java.util.Map;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DaggerApplicationComponent implements ApplicationComponent {
  private final PocketHubModule pocketHubModule;

  private Provider<ActivityBuilder_MainActivity.MainActivitySubcomponent.Factory> mainActivitySubcomponentFactoryProvider;

  private Provider<ActivityBuilder_RepositoryViewActivity.RepositoryViewActivitySubcomponent.Factory> repositoryViewActivitySubcomponentFactoryProvider;

  private Provider<ActivityBuilder_IssuesViewActivity.IssuesViewActivitySubcomponent.Factory> issuesViewActivitySubcomponentFactoryProvider;

  private Provider<ActivityBuilder_NotificationActivity.NotificationActivitySubcomponent.Factory> notificationActivitySubcomponentFactoryProvider;

  private Provider<ActivityBuilder_CreateGistActivity.CreateGistActivitySubcomponent.Factory> createGistActivitySubcomponentFactoryProvider;

  private Provider<ActivityBuilder_IssueBrowseActivity.IssueBrowseActivitySubcomponent.Factory> issueBrowseActivitySubcomponentFactoryProvider;

  private Provider<ActivityBuilder_EditIssuesFilterActivity.EditIssuesFilterActivitySubcomponent.Factory> editIssuesFilterActivitySubcomponentFactoryProvider;

  private Provider<ActivityBuilder_EditIssueActivity.EditIssueActivitySubcomponent.Factory> editIssueActivitySubcomponentFactoryProvider;

  private Provider<ActivityBuilder_SearchActivity.SearchActivitySubcomponent.Factory> searchActivitySubcomponentFactoryProvider;

  private Provider<ActivityBuilder_FiltersViewActivity.FiltersViewActivitySubcomponent.Factory> filtersViewActivitySubcomponentFactoryProvider;

  private Provider<ActivityBuilder_GistsViewActivity.GistsViewActivitySubcomponent.Factory> gistsViewActivitySubcomponentFactoryProvider;

  private Provider<ActivityBuilder_GistFilesViewActivity.GistFilesViewActivitySubcomponent.Factory> gistFilesViewActivitySubcomponentFactoryProvider;

  private Provider<ActivityBuilder_CreateGistCommentActivity.CreateCommentActivitySubcomponent.Factory> createCommentActivitySubcomponentFactoryProvider;

  private Provider<ActivityBuilder_CreateIssueCommentActivity.CreateCommentActivitySubcomponent.Factory> createCommentActivitySubcomponentFactoryProvider2;

  private Provider<ActivityBuilder_CreateCommitCommentActivity.CreateCommentActivitySubcomponent.Factory> createCommentActivitySubcomponentFactoryProvider3;

  private Provider<ActivityBuilder_EditGistCommentActivity.EditCommentActivitySubcomponent.Factory> editCommentActivitySubcomponentFactoryProvider;

  private Provider<ActivityBuilder_EditIssueCommentActivity.EditCommentActivitySubcomponent.Factory> editCommentActivitySubcomponentFactoryProvider2;

  private Provider<ActivityBuilder_RepositoryContributorsActivity.RepositoryContributorsActivitySubcomponent.Factory> repositoryContributorsActivitySubcomponentFactoryProvider;

  private Provider<ActivityBuilder_UserViewActivity.UserViewActivitySubcomponent.Factory> userViewActivitySubcomponentFactoryProvider;

  private Provider<ActivityBuilder_LoginActivity.LoginActivitySubcomponent.Factory> loginActivitySubcomponentFactoryProvider;

  private Provider<ActivityBuilder_UriLauncherActivity.UriLauncherActivitySubcomponent.Factory> uriLauncherActivitySubcomponentFactoryProvider;

  private Provider<ActivityBuilder_IssueSearchActivity.IssueSearchActivitySubcomponent.Factory> issueSearchActivitySubcomponentFactoryProvider;

  private Provider<ActivityBuilder_CommitCompareViewActivity.CommitCompareViewActivitySubcomponent.Factory> commitCompareViewActivitySubcomponentFactoryProvider;

  private Provider<ActivityBuilder_CommitViewActivity.CommitViewActivitySubcomponent.Factory> commitViewActivitySubcomponentFactoryProvider;

  private Provider<ActivityBuilder_CommitFileViewActivity.CommitFileViewActivitySubcomponent.Factory> commitFileViewActivitySubcomponentFactoryProvider;

  private Provider<ActivityBuilder_BranchFileViewActivity.BranchFileViewActivitySubcomponent.Factory> branchFileViewActivitySubcomponentFactoryProvider;

  private Provider<ActivityBuilder_LoginWebViewActivity.LoginWebViewActivitySubcomponent.Factory> loginWebViewActivitySubcomponentFactoryProvider;

  private Provider<ServiceBuilder_ProvideSyncAdapterService.SyncAdapterServiceSubcomponent.Factory> syncAdapterServiceSubcomponentFactoryProvider;

  private Provider<DialogFragmentBuilder_LabelsDialogFragment.LabelsDialogFragmentSubcomponent.Factory> labelsDialogFragmentSubcomponentFactoryProvider;

  private Provider<DialogFragmentBuilder_AssigneeDialogFragment.AssigneeDialogFragmentSubcomponent.Factory> assigneeDialogFragmentSubcomponentFactoryProvider;

  private Provider<DialogFragmentBuilder_MilestoneDialogFragment.MilestoneDialogFragmentSubcomponent.Factory> milestoneDialogFragmentSubcomponentFactoryProvider;

  private Provider<DialogFragmentBuilder_RefDialogFragment.RefDialogFragmentSubcomponent.Factory> refDialogFragmentSubcomponentFactoryProvider;

  private Provider<DialogFragmentBuilder_ConfirmDialogFragment.ConfirmDialogFragmentSubcomponent.Factory> confirmDialogFragmentSubcomponentFactoryProvider;

  private Provider<Application> applicationProvider;

  private Provider<Context> provideApplicationContextProvider;

  private Provider<GistService> providesGistServiceProvider;

  private Provider<GistStore> gistStoreProvider;

  private Provider<Database> provideDatabaseProvider;

  private Provider<Account> accountProvider;

  private Provider<AvatarLoader> avatarLoaderProvider;

  private Provider<IssueService> providesIssueServiceProvider;

  private Provider<IssueStore> issueStoreProvider;

  private Provider<RepositoryCommitService> providesRepositoryCommitServiceProvider;

  private Provider<RepositoryService> providesRepositoryServiceProvider;

  private Provider<CommitStore> commitStoreProvider;

  private Provider<NotificationService> providesNotificationServiceProvider;

  private Provider<SearchService> providesSearchServiceProvider;

  private Provider<UserService> providesUserServiceProvider;

  private Provider<RepositoryCommentService> providesRepositoryCommentServiceProvider;

  private Provider<DatabaseCache> databaseCacheProvider;

  private Provider<OrganizationRepositoriesFactory> organizationRepositoriesFactoryProvider;

  private Provider<Organizations> organizationsProvider;

  private Provider<SyncCampaignFactory> syncCampaignFactoryProvider;

  private Provider<SyncAdapter> syncAdapterProvider;

  private DaggerApplicationComponent(PocketHubModule pocketHubModuleParam,
      GitHubModule gitHubModuleParam, PocketHub seedInstance, Application applicationParam) {
    this.pocketHubModule = pocketHubModuleParam;
    initialize(pocketHubModuleParam, gitHubModuleParam, seedInstance, applicationParam);
  }

  public static ApplicationComponent.Builder builder() {
    return new Builder();
  }

  private Map<Class<?>, Provider<AndroidInjector.Factory<?>>> getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(
      ) {
    return MapBuilder.<Class<?>, Provider<AndroidInjector.Factory<?>>>newMapBuilder(33).put(MainActivity.class, (Provider) mainActivitySubcomponentFactoryProvider).put(RepositoryViewActivity.class, (Provider) repositoryViewActivitySubcomponentFactoryProvider).put(IssuesViewActivity.class, (Provider) issuesViewActivitySubcomponentFactoryProvider).put(NotificationActivity.class, (Provider) notificationActivitySubcomponentFactoryProvider).put(CreateGistActivity.class, (Provider) createGistActivitySubcomponentFactoryProvider).put(IssueBrowseActivity.class, (Provider) issueBrowseActivitySubcomponentFactoryProvider).put(EditIssuesFilterActivity.class, (Provider) editIssuesFilterActivitySubcomponentFactoryProvider).put(EditIssueActivity.class, (Provider) editIssueActivitySubcomponentFactoryProvider).put(SearchActivity.class, (Provider) searchActivitySubcomponentFactoryProvider).put(FiltersViewActivity.class, (Provider) filtersViewActivitySubcomponentFactoryProvider).put(GistsViewActivity.class, (Provider) gistsViewActivitySubcomponentFactoryProvider).put(GistFilesViewActivity.class, (Provider) gistFilesViewActivitySubcomponentFactoryProvider).put(CreateCommentActivity.class, (Provider) createCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.CreateCommentActivity.class, (Provider) createCommentActivitySubcomponentFactoryProvider2).put(com.github.pockethub.android.ui.commit.CreateCommentActivity.class, (Provider) createCommentActivitySubcomponentFactoryProvider3).put(EditCommentActivity.class, (Provider) editCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.EditCommentActivity.class, (Provider) editCommentActivitySubcomponentFactoryProvider2).put(RepositoryContributorsActivity.class, (Provider) repositoryContributorsActivitySubcomponentFactoryProvider).put(UserViewActivity.class, (Provider) userViewActivitySubcomponentFactoryProvider).put(LoginActivity.class, (Provider) loginActivitySubcomponentFactoryProvider).put(UriLauncherActivity.class, (Provider) uriLauncherActivitySubcomponentFactoryProvider).put(IssueSearchActivity.class, (Provider) issueSearchActivitySubcomponentFactoryProvider).put(CommitCompareViewActivity.class, (Provider) commitCompareViewActivitySubcomponentFactoryProvider).put(CommitViewActivity.class, (Provider) commitViewActivitySubcomponentFactoryProvider).put(CommitFileViewActivity.class, (Provider) commitFileViewActivitySubcomponentFactoryProvider).put(BranchFileViewActivity.class, (Provider) branchFileViewActivitySubcomponentFactoryProvider).put(LoginWebViewActivity.class, (Provider) loginWebViewActivitySubcomponentFactoryProvider).put(SyncAdapterService.class, (Provider) syncAdapterServiceSubcomponentFactoryProvider).put(LabelsDialogFragment.class, (Provider) labelsDialogFragmentSubcomponentFactoryProvider).put(AssigneeDialogFragment.class, (Provider) assigneeDialogFragmentSubcomponentFactoryProvider).put(MilestoneDialogFragment.class, (Provider) milestoneDialogFragmentSubcomponentFactoryProvider).put(RefDialogFragment.class, (Provider) refDialogFragmentSubcomponentFactoryProvider).put(ConfirmDialogFragment.class, (Provider) confirmDialogFragmentSubcomponentFactoryProvider).build();}

  private DispatchingAndroidInjector<Object> getDispatchingAndroidInjectorOfObject() {
    return DispatchingAndroidInjector_Factory.newInstance(getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(), Collections.<String, Provider<AndroidInjector.Factory<?>>>emptyMap());}

  private File getNamedFile() {
    return PocketHubModule_CacheDirFactory.cacheDir(pocketHubModule, provideApplicationContextProvider.get());}

  @SuppressWarnings("unchecked")
  private void initialize(final PocketHubModule pocketHubModuleParam,
      final GitHubModule gitHubModuleParam, final PocketHub seedInstance,
      final Application applicationParam) {
    this.mainActivitySubcomponentFactoryProvider = new Provider<ActivityBuilder_MainActivity.MainActivitySubcomponent.Factory>() {
      @Override
      public ActivityBuilder_MainActivity.MainActivitySubcomponent.Factory get() {
        return new MainActivitySubcomponentFactory();}
    };
    this.repositoryViewActivitySubcomponentFactoryProvider = new Provider<ActivityBuilder_RepositoryViewActivity.RepositoryViewActivitySubcomponent.Factory>() {
      @Override
      public ActivityBuilder_RepositoryViewActivity.RepositoryViewActivitySubcomponent.Factory get(
          ) {
        return new RepositoryViewActivitySubcomponentFactory();}
    };
    this.issuesViewActivitySubcomponentFactoryProvider = new Provider<ActivityBuilder_IssuesViewActivity.IssuesViewActivitySubcomponent.Factory>() {
      @Override
      public ActivityBuilder_IssuesViewActivity.IssuesViewActivitySubcomponent.Factory get() {
        return new IssuesViewActivitySubcomponentFactory();}
    };
    this.notificationActivitySubcomponentFactoryProvider = new Provider<ActivityBuilder_NotificationActivity.NotificationActivitySubcomponent.Factory>() {
      @Override
      public ActivityBuilder_NotificationActivity.NotificationActivitySubcomponent.Factory get() {
        return new NotificationActivitySubcomponentFactory();}
    };
    this.createGistActivitySubcomponentFactoryProvider = new Provider<ActivityBuilder_CreateGistActivity.CreateGistActivitySubcomponent.Factory>() {
      @Override
      public ActivityBuilder_CreateGistActivity.CreateGistActivitySubcomponent.Factory get() {
        return new CreateGistActivitySubcomponentFactory();}
    };
    this.issueBrowseActivitySubcomponentFactoryProvider = new Provider<ActivityBuilder_IssueBrowseActivity.IssueBrowseActivitySubcomponent.Factory>() {
      @Override
      public ActivityBuilder_IssueBrowseActivity.IssueBrowseActivitySubcomponent.Factory get() {
        return new IssueBrowseActivitySubcomponentFactory();}
    };
    this.editIssuesFilterActivitySubcomponentFactoryProvider = new Provider<ActivityBuilder_EditIssuesFilterActivity.EditIssuesFilterActivitySubcomponent.Factory>() {
      @Override
      public ActivityBuilder_EditIssuesFilterActivity.EditIssuesFilterActivitySubcomponent.Factory get(
          ) {
        return new EditIssuesFilterActivitySubcomponentFactory();}
    };
    this.editIssueActivitySubcomponentFactoryProvider = new Provider<ActivityBuilder_EditIssueActivity.EditIssueActivitySubcomponent.Factory>() {
      @Override
      public ActivityBuilder_EditIssueActivity.EditIssueActivitySubcomponent.Factory get() {
        return new EditIssueActivitySubcomponentFactory();}
    };
    this.searchActivitySubcomponentFactoryProvider = new Provider<ActivityBuilder_SearchActivity.SearchActivitySubcomponent.Factory>() {
      @Override
      public ActivityBuilder_SearchActivity.SearchActivitySubcomponent.Factory get() {
        return new SearchActivitySubcomponentFactory();}
    };
    this.filtersViewActivitySubcomponentFactoryProvider = new Provider<ActivityBuilder_FiltersViewActivity.FiltersViewActivitySubcomponent.Factory>() {
      @Override
      public ActivityBuilder_FiltersViewActivity.FiltersViewActivitySubcomponent.Factory get() {
        return new FiltersViewActivitySubcomponentFactory();}
    };
    this.gistsViewActivitySubcomponentFactoryProvider = new Provider<ActivityBuilder_GistsViewActivity.GistsViewActivitySubcomponent.Factory>() {
      @Override
      public ActivityBuilder_GistsViewActivity.GistsViewActivitySubcomponent.Factory get() {
        return new GistsViewActivitySubcomponentFactory();}
    };
    this.gistFilesViewActivitySubcomponentFactoryProvider = new Provider<ActivityBuilder_GistFilesViewActivity.GistFilesViewActivitySubcomponent.Factory>() {
      @Override
      public ActivityBuilder_GistFilesViewActivity.GistFilesViewActivitySubcomponent.Factory get() {
        return new GistFilesViewActivitySubcomponentFactory();}
    };
    this.createCommentActivitySubcomponentFactoryProvider = new Provider<ActivityBuilder_CreateGistCommentActivity.CreateCommentActivitySubcomponent.Factory>() {
      @Override
      public ActivityBuilder_CreateGistCommentActivity.CreateCommentActivitySubcomponent.Factory get(
          ) {
        return new AB_CGCA_CreateCommentActivitySubcomponentFactory();}
    };
    this.createCommentActivitySubcomponentFactoryProvider2 = new Provider<ActivityBuilder_CreateIssueCommentActivity.CreateCommentActivitySubcomponent.Factory>() {
      @Override
      public ActivityBuilder_CreateIssueCommentActivity.CreateCommentActivitySubcomponent.Factory get(
          ) {
        return new AB_CICA_CreateCommentActivitySubcomponentFactory();}
    };
    this.createCommentActivitySubcomponentFactoryProvider3 = new Provider<ActivityBuilder_CreateCommitCommentActivity.CreateCommentActivitySubcomponent.Factory>() {
      @Override
      public ActivityBuilder_CreateCommitCommentActivity.CreateCommentActivitySubcomponent.Factory get(
          ) {
        return new AB_CCCA_CreateCommentActivitySubcomponentFactory();}
    };
    this.editCommentActivitySubcomponentFactoryProvider = new Provider<ActivityBuilder_EditGistCommentActivity.EditCommentActivitySubcomponent.Factory>() {
      @Override
      public ActivityBuilder_EditGistCommentActivity.EditCommentActivitySubcomponent.Factory get() {
        return new AB_EGCA_EditCommentActivitySubcomponentFactory();}
    };
    this.editCommentActivitySubcomponentFactoryProvider2 = new Provider<ActivityBuilder_EditIssueCommentActivity.EditCommentActivitySubcomponent.Factory>() {
      @Override
      public ActivityBuilder_EditIssueCommentActivity.EditCommentActivitySubcomponent.Factory get(
          ) {
        return new AB_EICA_EditCommentActivitySubcomponentFactory();}
    };
    this.repositoryContributorsActivitySubcomponentFactoryProvider = new Provider<ActivityBuilder_RepositoryContributorsActivity.RepositoryContributorsActivitySubcomponent.Factory>() {
      @Override
      public ActivityBuilder_RepositoryContributorsActivity.RepositoryContributorsActivitySubcomponent.Factory get(
          ) {
        return new RepositoryContributorsActivitySubcomponentFactory();}
    };
    this.userViewActivitySubcomponentFactoryProvider = new Provider<ActivityBuilder_UserViewActivity.UserViewActivitySubcomponent.Factory>() {
      @Override
      public ActivityBuilder_UserViewActivity.UserViewActivitySubcomponent.Factory get() {
        return new UserViewActivitySubcomponentFactory();}
    };
    this.loginActivitySubcomponentFactoryProvider = new Provider<ActivityBuilder_LoginActivity.LoginActivitySubcomponent.Factory>() {
      @Override
      public ActivityBuilder_LoginActivity.LoginActivitySubcomponent.Factory get() {
        return new LoginActivitySubcomponentFactory();}
    };
    this.uriLauncherActivitySubcomponentFactoryProvider = new Provider<ActivityBuilder_UriLauncherActivity.UriLauncherActivitySubcomponent.Factory>() {
      @Override
      public ActivityBuilder_UriLauncherActivity.UriLauncherActivitySubcomponent.Factory get() {
        return new UriLauncherActivitySubcomponentFactory();}
    };
    this.issueSearchActivitySubcomponentFactoryProvider = new Provider<ActivityBuilder_IssueSearchActivity.IssueSearchActivitySubcomponent.Factory>() {
      @Override
      public ActivityBuilder_IssueSearchActivity.IssueSearchActivitySubcomponent.Factory get() {
        return new IssueSearchActivitySubcomponentFactory();}
    };
    this.commitCompareViewActivitySubcomponentFactoryProvider = new Provider<ActivityBuilder_CommitCompareViewActivity.CommitCompareViewActivitySubcomponent.Factory>() {
      @Override
      public ActivityBuilder_CommitCompareViewActivity.CommitCompareViewActivitySubcomponent.Factory get(
          ) {
        return new CommitCompareViewActivitySubcomponentFactory();}
    };
    this.commitViewActivitySubcomponentFactoryProvider = new Provider<ActivityBuilder_CommitViewActivity.CommitViewActivitySubcomponent.Factory>() {
      @Override
      public ActivityBuilder_CommitViewActivity.CommitViewActivitySubcomponent.Factory get() {
        return new CommitViewActivitySubcomponentFactory();}
    };
    this.commitFileViewActivitySubcomponentFactoryProvider = new Provider<ActivityBuilder_CommitFileViewActivity.CommitFileViewActivitySubcomponent.Factory>() {
      @Override
      public ActivityBuilder_CommitFileViewActivity.CommitFileViewActivitySubcomponent.Factory get(
          ) {
        return new CommitFileViewActivitySubcomponentFactory();}
    };
    this.branchFileViewActivitySubcomponentFactoryProvider = new Provider<ActivityBuilder_BranchFileViewActivity.BranchFileViewActivitySubcomponent.Factory>() {
      @Override
      public ActivityBuilder_BranchFileViewActivity.BranchFileViewActivitySubcomponent.Factory get(
          ) {
        return new BranchFileViewActivitySubcomponentFactory();}
    };
    this.loginWebViewActivitySubcomponentFactoryProvider = new Provider<ActivityBuilder_LoginWebViewActivity.LoginWebViewActivitySubcomponent.Factory>() {
      @Override
      public ActivityBuilder_LoginWebViewActivity.LoginWebViewActivitySubcomponent.Factory get() {
        return new LoginWebViewActivitySubcomponentFactory();}
    };
    this.syncAdapterServiceSubcomponentFactoryProvider = new Provider<ServiceBuilder_ProvideSyncAdapterService.SyncAdapterServiceSubcomponent.Factory>() {
      @Override
      public ServiceBuilder_ProvideSyncAdapterService.SyncAdapterServiceSubcomponent.Factory get() {
        return new SyncAdapterServiceSubcomponentFactory();}
    };
    this.labelsDialogFragmentSubcomponentFactoryProvider = new Provider<DialogFragmentBuilder_LabelsDialogFragment.LabelsDialogFragmentSubcomponent.Factory>() {
      @Override
      public DialogFragmentBuilder_LabelsDialogFragment.LabelsDialogFragmentSubcomponent.Factory get(
          ) {
        return new LabelsDialogFragmentSubcomponentFactory();}
    };
    this.assigneeDialogFragmentSubcomponentFactoryProvider = new Provider<DialogFragmentBuilder_AssigneeDialogFragment.AssigneeDialogFragmentSubcomponent.Factory>() {
      @Override
      public DialogFragmentBuilder_AssigneeDialogFragment.AssigneeDialogFragmentSubcomponent.Factory get(
          ) {
        return new AssigneeDialogFragmentSubcomponentFactory();}
    };
    this.milestoneDialogFragmentSubcomponentFactoryProvider = new Provider<DialogFragmentBuilder_MilestoneDialogFragment.MilestoneDialogFragmentSubcomponent.Factory>() {
      @Override
      public DialogFragmentBuilder_MilestoneDialogFragment.MilestoneDialogFragmentSubcomponent.Factory get(
          ) {
        return new MilestoneDialogFragmentSubcomponentFactory();}
    };
    this.refDialogFragmentSubcomponentFactoryProvider = new Provider<DialogFragmentBuilder_RefDialogFragment.RefDialogFragmentSubcomponent.Factory>() {
      @Override
      public DialogFragmentBuilder_RefDialogFragment.RefDialogFragmentSubcomponent.Factory get() {
        return new RefDialogFragmentSubcomponentFactory();}
    };
    this.confirmDialogFragmentSubcomponentFactoryProvider = new Provider<DialogFragmentBuilder_ConfirmDialogFragment.ConfirmDialogFragmentSubcomponent.Factory>() {
      @Override
      public DialogFragmentBuilder_ConfirmDialogFragment.ConfirmDialogFragmentSubcomponent.Factory get(
          ) {
        return new ConfirmDialogFragmentSubcomponentFactory();}
    };
    this.applicationProvider = InstanceFactory.create(applicationParam);
    this.provideApplicationContextProvider = DoubleCheck.provider((Provider) applicationProvider);
    this.providesGistServiceProvider = DoubleCheck.provider(GitHubModule_ProvidesGistServiceFactory.create(gitHubModuleParam, provideApplicationContextProvider));
    this.gistStoreProvider = DoubleCheck.provider(GistStore_Factory.create(providesGistServiceProvider));
    this.provideDatabaseProvider = DoubleCheck.provider(ApplicationModule_ProvideDatabaseFactory.create(provideApplicationContextProvider));
    this.accountProvider = PocketHubModule_AccountFactory.create(pocketHubModuleParam, provideApplicationContextProvider);
    this.avatarLoaderProvider = DoubleCheck.provider(AvatarLoader_Factory.create(provideApplicationContextProvider));
    this.providesIssueServiceProvider = DoubleCheck.provider(GitHubModule_ProvidesIssueServiceFactory.create(gitHubModuleParam, provideApplicationContextProvider));
    this.issueStoreProvider = DoubleCheck.provider(IssueStore_Factory.create(provideApplicationContextProvider, providesIssueServiceProvider));
    this.providesRepositoryCommitServiceProvider = DoubleCheck.provider(GitHubModule_ProvidesRepositoryCommitServiceFactory.create(gitHubModuleParam, provideApplicationContextProvider));
    this.providesRepositoryServiceProvider = DoubleCheck.provider(GitHubModule_ProvidesRepositoryServiceFactory.create(gitHubModuleParam, provideApplicationContextProvider));
    this.commitStoreProvider = DoubleCheck.provider(CommitStore_Factory.create(providesRepositoryCommitServiceProvider));
    this.providesNotificationServiceProvider = DoubleCheck.provider(GitHubModule_ProvidesNotificationServiceFactory.create(gitHubModuleParam, provideApplicationContextProvider));
    this.providesSearchServiceProvider = DoubleCheck.provider(GitHubModule_ProvidesSearchServiceFactory.create(gitHubModuleParam, provideApplicationContextProvider));
    this.providesUserServiceProvider = DoubleCheck.provider(GitHubModule_ProvidesUserServiceFactory.create(gitHubModuleParam, provideApplicationContextProvider));
    this.providesRepositoryCommentServiceProvider = DoubleCheck.provider(GitHubModule_ProvidesRepositoryCommentServiceFactory.create(gitHubModuleParam, provideApplicationContextProvider));
    this.databaseCacheProvider = DatabaseCache_Factory.create(provideDatabaseProvider);
    this.organizationRepositoriesFactoryProvider = OrganizationRepositoriesFactory_Factory.create(provideApplicationContextProvider, accountProvider);
    this.organizationsProvider = Organizations_Factory.create(provideApplicationContextProvider);
    this.syncCampaignFactoryProvider = SyncCampaignFactory_Factory.create(databaseCacheProvider, organizationRepositoriesFactoryProvider, organizationsProvider);
    this.syncAdapterProvider = DoubleCheck.provider(SyncAdapter_Factory.create(provideApplicationContextProvider, syncCampaignFactoryProvider));
  }

  @Override
  public void inject(PocketHub arg0) {
    injectPocketHub(arg0);}

  @Override
  public GistStore gistStore() {
    return gistStoreProvider.get();}

  private PocketHub injectPocketHub(PocketHub instance) {
    DaggerApplication_MembersInjector.injectAndroidInjector(instance, getDispatchingAndroidInjectorOfObject());
    return instance;
  }

  private static final class Builder extends ApplicationComponent.Builder {
    private PocketHub seedInstance;

    private Application application;

    @Override
    public void seedInstance(PocketHub arg0) {
      this.seedInstance = Preconditions.checkNotNull(arg0);
    }

    @Override
    Builder application(Application application) {
      this.application = Preconditions.checkNotNull(application);
      return this;
    }

    @Override
    public ApplicationComponent build() {
      Preconditions.checkBuilderRequirement(seedInstance, PocketHub.class);
      Preconditions.checkBuilderRequirement(application, Application.class);
      return new DaggerApplicationComponent(new PocketHubModule(), new GitHubModule(), seedInstance, application);
    }
  }

  private final class MainActivitySubcomponentFactory implements ActivityBuilder_MainActivity.MainActivitySubcomponent.Factory {
    @Override
    public ActivityBuilder_MainActivity.MainActivitySubcomponent create(MainActivity arg0) {
      Preconditions.checkNotNull(arg0);
      return new MainActivitySubcomponentImpl(arg0);
    }
  }

  private final class MainActivitySubcomponentImpl implements ActivityBuilder_MainActivity.MainActivitySubcomponent {
    private Provider<MainFragmentProvider_HomePagerFragment.HomePagerFragmentSubcomponent.Factory> homePagerFragmentSubcomponentFactoryProvider;

    private Provider<MainFragmentProvider_GistsPagerFragment.GistsPagerFragmentSubcomponent.Factory> gistsPagerFragmentSubcomponentFactoryProvider;

    private Provider<MainFragmentProvider_IssueDashboardPagerFragment.IssueDashboardPagerFragmentSubcomponent.Factory> issueDashboardPagerFragmentSubcomponentFactoryProvider;

    private Provider<MainFragmentProvider_FilterListFragment.FilterListFragmentSubcomponent.Factory> filterListFragmentSubcomponentFactoryProvider;

    private Provider<UserComparator> userComparatorProvider;

    private MainActivitySubcomponentImpl(MainActivity arg0) {

      initialize(arg0);
    }

    private Map<Class<?>, Provider<AndroidInjector.Factory<?>>> getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(
        ) {
      return MapBuilder.<Class<?>, Provider<AndroidInjector.Factory<?>>>newMapBuilder(37).put(MainActivity.class, (Provider) DaggerApplicationComponent.this.mainActivitySubcomponentFactoryProvider).put(RepositoryViewActivity.class, (Provider) DaggerApplicationComponent.this.repositoryViewActivitySubcomponentFactoryProvider).put(IssuesViewActivity.class, (Provider) DaggerApplicationComponent.this.issuesViewActivitySubcomponentFactoryProvider).put(NotificationActivity.class, (Provider) DaggerApplicationComponent.this.notificationActivitySubcomponentFactoryProvider).put(CreateGistActivity.class, (Provider) DaggerApplicationComponent.this.createGistActivitySubcomponentFactoryProvider).put(IssueBrowseActivity.class, (Provider) DaggerApplicationComponent.this.issueBrowseActivitySubcomponentFactoryProvider).put(EditIssuesFilterActivity.class, (Provider) DaggerApplicationComponent.this.editIssuesFilterActivitySubcomponentFactoryProvider).put(EditIssueActivity.class, (Provider) DaggerApplicationComponent.this.editIssueActivitySubcomponentFactoryProvider).put(SearchActivity.class, (Provider) DaggerApplicationComponent.this.searchActivitySubcomponentFactoryProvider).put(FiltersViewActivity.class, (Provider) DaggerApplicationComponent.this.filtersViewActivitySubcomponentFactoryProvider).put(GistsViewActivity.class, (Provider) DaggerApplicationComponent.this.gistsViewActivitySubcomponentFactoryProvider).put(GistFilesViewActivity.class, (Provider) DaggerApplicationComponent.this.gistFilesViewActivitySubcomponentFactoryProvider).put(CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider2).put(com.github.pockethub.android.ui.commit.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider3).put(EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider2).put(RepositoryContributorsActivity.class, (Provider) DaggerApplicationComponent.this.repositoryContributorsActivitySubcomponentFactoryProvider).put(UserViewActivity.class, (Provider) DaggerApplicationComponent.this.userViewActivitySubcomponentFactoryProvider).put(LoginActivity.class, (Provider) DaggerApplicationComponent.this.loginActivitySubcomponentFactoryProvider).put(UriLauncherActivity.class, (Provider) DaggerApplicationComponent.this.uriLauncherActivitySubcomponentFactoryProvider).put(IssueSearchActivity.class, (Provider) DaggerApplicationComponent.this.issueSearchActivitySubcomponentFactoryProvider).put(CommitCompareViewActivity.class, (Provider) DaggerApplicationComponent.this.commitCompareViewActivitySubcomponentFactoryProvider).put(CommitViewActivity.class, (Provider) DaggerApplicationComponent.this.commitViewActivitySubcomponentFactoryProvider).put(CommitFileViewActivity.class, (Provider) DaggerApplicationComponent.this.commitFileViewActivitySubcomponentFactoryProvider).put(BranchFileViewActivity.class, (Provider) DaggerApplicationComponent.this.branchFileViewActivitySubcomponentFactoryProvider).put(LoginWebViewActivity.class, (Provider) DaggerApplicationComponent.this.loginWebViewActivitySubcomponentFactoryProvider).put(SyncAdapterService.class, (Provider) DaggerApplicationComponent.this.syncAdapterServiceSubcomponentFactoryProvider).put(LabelsDialogFragment.class, (Provider) DaggerApplicationComponent.this.labelsDialogFragmentSubcomponentFactoryProvider).put(AssigneeDialogFragment.class, (Provider) DaggerApplicationComponent.this.assigneeDialogFragmentSubcomponentFactoryProvider).put(MilestoneDialogFragment.class, (Provider) DaggerApplicationComponent.this.milestoneDialogFragmentSubcomponentFactoryProvider).put(RefDialogFragment.class, (Provider) DaggerApplicationComponent.this.refDialogFragmentSubcomponentFactoryProvider).put(ConfirmDialogFragment.class, (Provider) DaggerApplicationComponent.this.confirmDialogFragmentSubcomponentFactoryProvider).put(HomePagerFragment.class, (Provider) homePagerFragmentSubcomponentFactoryProvider).put(GistsPagerFragment.class, (Provider) gistsPagerFragmentSubcomponentFactoryProvider).put(IssueDashboardPagerFragment.class, (Provider) issueDashboardPagerFragmentSubcomponentFactoryProvider).put(FilterListFragment.class, (Provider) filterListFragmentSubcomponentFactoryProvider).build();}

    private DispatchingAndroidInjector<Object> getDispatchingAndroidInjectorOfObject() {
      return DispatchingAndroidInjector_Factory.newInstance(getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(), Collections.<String, Provider<AndroidInjector.Factory<?>>>emptyMap());}

    private DatabaseCache getDatabaseCache() {
      return injectDatabaseCache(DatabaseCache_Factory.newInstance());}

    private OrganizationRepositoriesFactory getOrganizationRepositoriesFactory() {
      return injectOrganizationRepositoriesFactory(OrganizationRepositoriesFactory_Factory.newInstance());}

    private Organizations getOrganizations() {
      return new Organizations(DaggerApplicationComponent.this.provideApplicationContextProvider.get());}

    private AccountDataManager getAccountDataManager() {
      return injectAccountDataManager(AccountDataManager_Factory.newInstance());}

    @SuppressWarnings("unchecked")
    private void initialize(final MainActivity arg0) {
      this.homePagerFragmentSubcomponentFactoryProvider = new Provider<MainFragmentProvider_HomePagerFragment.HomePagerFragmentSubcomponent.Factory>() {
        @Override
        public MainFragmentProvider_HomePagerFragment.HomePagerFragmentSubcomponent.Factory get() {
          return new HomePagerFragmentSubcomponentFactory();}
      };
      this.gistsPagerFragmentSubcomponentFactoryProvider = new Provider<MainFragmentProvider_GistsPagerFragment.GistsPagerFragmentSubcomponent.Factory>() {
        @Override
        public MainFragmentProvider_GistsPagerFragment.GistsPagerFragmentSubcomponent.Factory get(
            ) {
          return new GistsPagerFragmentSubcomponentFactory();}
      };
      this.issueDashboardPagerFragmentSubcomponentFactoryProvider = new Provider<MainFragmentProvider_IssueDashboardPagerFragment.IssueDashboardPagerFragmentSubcomponent.Factory>() {
        @Override
        public MainFragmentProvider_IssueDashboardPagerFragment.IssueDashboardPagerFragmentSubcomponent.Factory get(
            ) {
          return new IssueDashboardPagerFragmentSubcomponentFactory();}
      };
      this.filterListFragmentSubcomponentFactoryProvider = new Provider<MainFragmentProvider_FilterListFragment.FilterListFragmentSubcomponent.Factory>() {
        @Override
        public MainFragmentProvider_FilterListFragment.FilterListFragmentSubcomponent.Factory get(
            ) {
          return new MFP_FLF_FilterListFragmentSubcomponentFactory();}
      };
      this.userComparatorProvider = UserComparator_Factory.create(DaggerApplicationComponent.this.accountProvider);
    }

    @Override
    public void inject(MainActivity arg0) {
      injectMainActivity(arg0);}

    private DatabaseCache injectDatabaseCache(DatabaseCache instance) {
      DatabaseCache_MembersInjector.injectDatabase(instance, DaggerApplicationComponent.this.provideDatabaseProvider.get());
      return instance;
    }

    private OrganizationRepositoriesFactory injectOrganizationRepositoriesFactory(
        OrganizationRepositoriesFactory instance) {
      OrganizationRepositoriesFactory_MembersInjector.injectContext(instance, DaggerApplicationComponent.this.provideApplicationContextProvider.get());
      OrganizationRepositoriesFactory_MembersInjector.injectAccountProvider(instance, DaggerApplicationComponent.this.accountProvider);
      return instance;
    }

    private AccountDataManager injectAccountDataManager(AccountDataManager instance) {
      AccountDataManager_MembersInjector.injectContext(instance, DaggerApplicationComponent.this.provideApplicationContextProvider.get());
      AccountDataManager_MembersInjector.injectDbCache(instance, getDatabaseCache());
      AccountDataManager_MembersInjector.injectAllRepos(instance, getOrganizationRepositoriesFactory());
      AccountDataManager_MembersInjector.injectUserAndOrgsResource(instance, getOrganizations());
      AccountDataManager_MembersInjector.injectRoot(instance, DaggerApplicationComponent.this.getNamedFile());
      return instance;
    }

    private MainActivity injectMainActivity(MainActivity instance) {
      DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, getDispatchingAndroidInjectorOfObject());
      MainActivity_MembersInjector.injectAccountDataManager(instance, getAccountDataManager());
      MainActivity_MembersInjector.injectDatabase(instance, DaggerApplicationComponent.this.provideDatabaseProvider.get());
      MainActivity_MembersInjector.injectUserComparatorProvider(instance, userComparatorProvider);
      MainActivity_MembersInjector.injectAvatars(instance, DaggerApplicationComponent.this.avatarLoaderProvider.get());
      return instance;
    }

    private final class HomePagerFragmentSubcomponentFactory implements MainFragmentProvider_HomePagerFragment.HomePagerFragmentSubcomponent.Factory {
      @Override
      public MainFragmentProvider_HomePagerFragment.HomePagerFragmentSubcomponent create(
          HomePagerFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new HomePagerFragmentSubcomponentImpl(arg0);
      }
    }

    private final class HomePagerFragmentSubcomponentImpl implements MainFragmentProvider_HomePagerFragment.HomePagerFragmentSubcomponent {
      private Provider<HomePagerFragmentProvider_UserReceivedNewsFragment.UserReceivedNewsFragmentSubcomponent.Factory> userReceivedNewsFragmentSubcomponentFactoryProvider;

      private Provider<HomePagerFragmentProvider_OrganizationNewsFragment.OrganizationNewsFragmentSubcomponent.Factory> organizationNewsFragmentSubcomponentFactoryProvider;

      private Provider<HomePagerFragmentProvider_RepositoryListFragment.RepositoryListFragmentSubcomponent.Factory> repositoryListFragmentSubcomponentFactoryProvider;

      private Provider<HomePagerFragmentProvider_MyFollowersFragment.MyFollowersFragmentSubcomponent.Factory> myFollowersFragmentSubcomponentFactoryProvider;

      private Provider<HomePagerFragmentProvider_MyFollowingFragment.MyFollowingFragmentSubcomponent.Factory> myFollowingFragmentSubcomponentFactoryProvider;

      private Provider<HomePagerFragmentProvider_MembersFragment.MembersFragmentSubcomponent.Factory> membersFragmentSubcomponentFactoryProvider;

      private HomePagerFragmentSubcomponentImpl(HomePagerFragment arg0) {

        initialize(arg0);
      }

      private Map<Class<?>, Provider<AndroidInjector.Factory<?>>> getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(
          ) {
        return MapBuilder.<Class<?>, Provider<AndroidInjector.Factory<?>>>newMapBuilder(43).put(MainActivity.class, (Provider) DaggerApplicationComponent.this.mainActivitySubcomponentFactoryProvider).put(RepositoryViewActivity.class, (Provider) DaggerApplicationComponent.this.repositoryViewActivitySubcomponentFactoryProvider).put(IssuesViewActivity.class, (Provider) DaggerApplicationComponent.this.issuesViewActivitySubcomponentFactoryProvider).put(NotificationActivity.class, (Provider) DaggerApplicationComponent.this.notificationActivitySubcomponentFactoryProvider).put(CreateGistActivity.class, (Provider) DaggerApplicationComponent.this.createGistActivitySubcomponentFactoryProvider).put(IssueBrowseActivity.class, (Provider) DaggerApplicationComponent.this.issueBrowseActivitySubcomponentFactoryProvider).put(EditIssuesFilterActivity.class, (Provider) DaggerApplicationComponent.this.editIssuesFilterActivitySubcomponentFactoryProvider).put(EditIssueActivity.class, (Provider) DaggerApplicationComponent.this.editIssueActivitySubcomponentFactoryProvider).put(SearchActivity.class, (Provider) DaggerApplicationComponent.this.searchActivitySubcomponentFactoryProvider).put(FiltersViewActivity.class, (Provider) DaggerApplicationComponent.this.filtersViewActivitySubcomponentFactoryProvider).put(GistsViewActivity.class, (Provider) DaggerApplicationComponent.this.gistsViewActivitySubcomponentFactoryProvider).put(GistFilesViewActivity.class, (Provider) DaggerApplicationComponent.this.gistFilesViewActivitySubcomponentFactoryProvider).put(CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider2).put(com.github.pockethub.android.ui.commit.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider3).put(EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider2).put(RepositoryContributorsActivity.class, (Provider) DaggerApplicationComponent.this.repositoryContributorsActivitySubcomponentFactoryProvider).put(UserViewActivity.class, (Provider) DaggerApplicationComponent.this.userViewActivitySubcomponentFactoryProvider).put(LoginActivity.class, (Provider) DaggerApplicationComponent.this.loginActivitySubcomponentFactoryProvider).put(UriLauncherActivity.class, (Provider) DaggerApplicationComponent.this.uriLauncherActivitySubcomponentFactoryProvider).put(IssueSearchActivity.class, (Provider) DaggerApplicationComponent.this.issueSearchActivitySubcomponentFactoryProvider).put(CommitCompareViewActivity.class, (Provider) DaggerApplicationComponent.this.commitCompareViewActivitySubcomponentFactoryProvider).put(CommitViewActivity.class, (Provider) DaggerApplicationComponent.this.commitViewActivitySubcomponentFactoryProvider).put(CommitFileViewActivity.class, (Provider) DaggerApplicationComponent.this.commitFileViewActivitySubcomponentFactoryProvider).put(BranchFileViewActivity.class, (Provider) DaggerApplicationComponent.this.branchFileViewActivitySubcomponentFactoryProvider).put(LoginWebViewActivity.class, (Provider) DaggerApplicationComponent.this.loginWebViewActivitySubcomponentFactoryProvider).put(SyncAdapterService.class, (Provider) DaggerApplicationComponent.this.syncAdapterServiceSubcomponentFactoryProvider).put(LabelsDialogFragment.class, (Provider) DaggerApplicationComponent.this.labelsDialogFragmentSubcomponentFactoryProvider).put(AssigneeDialogFragment.class, (Provider) DaggerApplicationComponent.this.assigneeDialogFragmentSubcomponentFactoryProvider).put(MilestoneDialogFragment.class, (Provider) DaggerApplicationComponent.this.milestoneDialogFragmentSubcomponentFactoryProvider).put(RefDialogFragment.class, (Provider) DaggerApplicationComponent.this.refDialogFragmentSubcomponentFactoryProvider).put(ConfirmDialogFragment.class, (Provider) DaggerApplicationComponent.this.confirmDialogFragmentSubcomponentFactoryProvider).put(HomePagerFragment.class, (Provider) MainActivitySubcomponentImpl.this.homePagerFragmentSubcomponentFactoryProvider).put(GistsPagerFragment.class, (Provider) MainActivitySubcomponentImpl.this.gistsPagerFragmentSubcomponentFactoryProvider).put(IssueDashboardPagerFragment.class, (Provider) MainActivitySubcomponentImpl.this.issueDashboardPagerFragmentSubcomponentFactoryProvider).put(FilterListFragment.class, (Provider) MainActivitySubcomponentImpl.this.filterListFragmentSubcomponentFactoryProvider).put(UserReceivedNewsFragment.class, (Provider) userReceivedNewsFragmentSubcomponentFactoryProvider).put(OrganizationNewsFragment.class, (Provider) organizationNewsFragmentSubcomponentFactoryProvider).put(RepositoryListFragment.class, (Provider) repositoryListFragmentSubcomponentFactoryProvider).put(MyFollowersFragment.class, (Provider) myFollowersFragmentSubcomponentFactoryProvider).put(MyFollowingFragment.class, (Provider) myFollowingFragmentSubcomponentFactoryProvider).put(MembersFragment.class, (Provider) membersFragmentSubcomponentFactoryProvider).build();}

      private DispatchingAndroidInjector<Object> getDispatchingAndroidInjectorOfObject() {
        return DispatchingAndroidInjector_Factory.newInstance(getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(), Collections.<String, Provider<AndroidInjector.Factory<?>>>emptyMap());}

      @SuppressWarnings("unchecked")
      private void initialize(final HomePagerFragment arg0) {
        this.userReceivedNewsFragmentSubcomponentFactoryProvider = new Provider<HomePagerFragmentProvider_UserReceivedNewsFragment.UserReceivedNewsFragmentSubcomponent.Factory>() {
          @Override
          public HomePagerFragmentProvider_UserReceivedNewsFragment.UserReceivedNewsFragmentSubcomponent.Factory get(
              ) {
            return new UserReceivedNewsFragmentSubcomponentFactory();}
        };
        this.organizationNewsFragmentSubcomponentFactoryProvider = new Provider<HomePagerFragmentProvider_OrganizationNewsFragment.OrganizationNewsFragmentSubcomponent.Factory>() {
          @Override
          public HomePagerFragmentProvider_OrganizationNewsFragment.OrganizationNewsFragmentSubcomponent.Factory get(
              ) {
            return new OrganizationNewsFragmentSubcomponentFactory();}
        };
        this.repositoryListFragmentSubcomponentFactoryProvider = new Provider<HomePagerFragmentProvider_RepositoryListFragment.RepositoryListFragmentSubcomponent.Factory>() {
          @Override
          public HomePagerFragmentProvider_RepositoryListFragment.RepositoryListFragmentSubcomponent.Factory get(
              ) {
            return new RepositoryListFragmentSubcomponentFactory();}
        };
        this.myFollowersFragmentSubcomponentFactoryProvider = new Provider<HomePagerFragmentProvider_MyFollowersFragment.MyFollowersFragmentSubcomponent.Factory>() {
          @Override
          public HomePagerFragmentProvider_MyFollowersFragment.MyFollowersFragmentSubcomponent.Factory get(
              ) {
            return new MyFollowersFragmentSubcomponentFactory();}
        };
        this.myFollowingFragmentSubcomponentFactoryProvider = new Provider<HomePagerFragmentProvider_MyFollowingFragment.MyFollowingFragmentSubcomponent.Factory>() {
          @Override
          public HomePagerFragmentProvider_MyFollowingFragment.MyFollowingFragmentSubcomponent.Factory get(
              ) {
            return new MyFollowingFragmentSubcomponentFactory();}
        };
        this.membersFragmentSubcomponentFactoryProvider = new Provider<HomePagerFragmentProvider_MembersFragment.MembersFragmentSubcomponent.Factory>() {
          @Override
          public HomePagerFragmentProvider_MembersFragment.MembersFragmentSubcomponent.Factory get(
              ) {
            return new MembersFragmentSubcomponentFactory();}
        };
      }

      @Override
      public void inject(HomePagerFragment arg0) {
        injectHomePagerFragment(arg0);}

      private HomePagerFragment injectHomePagerFragment(HomePagerFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, getDispatchingAndroidInjectorOfObject());
        return instance;
      }

      private final class UserReceivedNewsFragmentSubcomponentFactory implements HomePagerFragmentProvider_UserReceivedNewsFragment.UserReceivedNewsFragmentSubcomponent.Factory {
        @Override
        public HomePagerFragmentProvider_UserReceivedNewsFragment.UserReceivedNewsFragmentSubcomponent create(
            UserReceivedNewsFragment arg0) {
          Preconditions.checkNotNull(arg0);
          return new UserReceivedNewsFragmentSubcomponentImpl(arg0);
        }
      }

      private final class UserReceivedNewsFragmentSubcomponentImpl implements HomePagerFragmentProvider_UserReceivedNewsFragment.UserReceivedNewsFragmentSubcomponent {
        private UserReceivedNewsFragmentSubcomponentImpl(UserReceivedNewsFragment arg0) {

        }

        @Override
        public void inject(UserReceivedNewsFragment arg0) {
          injectUserReceivedNewsFragment(arg0);}

        private UserReceivedNewsFragment injectUserReceivedNewsFragment(
            UserReceivedNewsFragment instance) {
          DaggerFragment_MembersInjector.injectAndroidInjector(instance, HomePagerFragmentSubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
          NewsFragment_MembersInjector.injectAvatars(instance, DaggerApplicationComponent.this.avatarLoaderProvider.get());
          return instance;
        }
      }

      private final class OrganizationNewsFragmentSubcomponentFactory implements HomePagerFragmentProvider_OrganizationNewsFragment.OrganizationNewsFragmentSubcomponent.Factory {
        @Override
        public HomePagerFragmentProvider_OrganizationNewsFragment.OrganizationNewsFragmentSubcomponent create(
            OrganizationNewsFragment arg0) {
          Preconditions.checkNotNull(arg0);
          return new OrganizationNewsFragmentSubcomponentImpl(arg0);
        }
      }

      private final class OrganizationNewsFragmentSubcomponentImpl implements HomePagerFragmentProvider_OrganizationNewsFragment.OrganizationNewsFragmentSubcomponent {
        private OrganizationNewsFragmentSubcomponentImpl(OrganizationNewsFragment arg0) {

        }

        @Override
        public void inject(OrganizationNewsFragment arg0) {
          injectOrganizationNewsFragment(arg0);}

        private OrganizationNewsFragment injectOrganizationNewsFragment(
            OrganizationNewsFragment instance) {
          DaggerFragment_MembersInjector.injectAndroidInjector(instance, HomePagerFragmentSubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
          NewsFragment_MembersInjector.injectAvatars(instance, DaggerApplicationComponent.this.avatarLoaderProvider.get());
          return instance;
        }
      }

      private final class RepositoryListFragmentSubcomponentFactory implements HomePagerFragmentProvider_RepositoryListFragment.RepositoryListFragmentSubcomponent.Factory {
        @Override
        public HomePagerFragmentProvider_RepositoryListFragment.RepositoryListFragmentSubcomponent create(
            RepositoryListFragment arg0) {
          Preconditions.checkNotNull(arg0);
          return new RepositoryListFragmentSubcomponentImpl(arg0);
        }
      }

      private final class RepositoryListFragmentSubcomponentImpl implements HomePagerFragmentProvider_RepositoryListFragment.RepositoryListFragmentSubcomponent {
        private RepositoryListFragmentSubcomponentImpl(RepositoryListFragment arg0) {

        }

        @Override
        public void inject(RepositoryListFragment arg0) {
          injectRepositoryListFragment(arg0);}

        private RepositoryListFragment injectRepositoryListFragment(
            RepositoryListFragment instance) {
          DaggerFragment_MembersInjector.injectAndroidInjector(instance, HomePagerFragmentSubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
          RepositoryListFragment_MembersInjector.injectCache(instance, MainActivitySubcomponentImpl.this.getAccountDataManager());
          RepositoryListFragment_MembersInjector.injectAvatars(instance, DaggerApplicationComponent.this.avatarLoaderProvider.get());
          return instance;
        }
      }

      private final class MyFollowersFragmentSubcomponentFactory implements HomePagerFragmentProvider_MyFollowersFragment.MyFollowersFragmentSubcomponent.Factory {
        @Override
        public HomePagerFragmentProvider_MyFollowersFragment.MyFollowersFragmentSubcomponent create(
            MyFollowersFragment arg0) {
          Preconditions.checkNotNull(arg0);
          return new MyFollowersFragmentSubcomponentImpl(arg0);
        }
      }

      private final class MyFollowersFragmentSubcomponentImpl implements HomePagerFragmentProvider_MyFollowersFragment.MyFollowersFragmentSubcomponent {
        private MyFollowersFragmentSubcomponentImpl(MyFollowersFragment arg0) {

        }

        @Override
        public void inject(MyFollowersFragment arg0) {
          injectMyFollowersFragment(arg0);}

        private MyFollowersFragment injectMyFollowersFragment(MyFollowersFragment instance) {
          DaggerFragment_MembersInjector.injectAndroidInjector(instance, HomePagerFragmentSubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
          PagedUserFragment_MembersInjector.injectAvatars(instance, DaggerApplicationComponent.this.avatarLoaderProvider.get());
          return instance;
        }
      }

      private final class MyFollowingFragmentSubcomponentFactory implements HomePagerFragmentProvider_MyFollowingFragment.MyFollowingFragmentSubcomponent.Factory {
        @Override
        public HomePagerFragmentProvider_MyFollowingFragment.MyFollowingFragmentSubcomponent create(
            MyFollowingFragment arg0) {
          Preconditions.checkNotNull(arg0);
          return new MyFollowingFragmentSubcomponentImpl(arg0);
        }
      }

      private final class MyFollowingFragmentSubcomponentImpl implements HomePagerFragmentProvider_MyFollowingFragment.MyFollowingFragmentSubcomponent {
        private MyFollowingFragmentSubcomponentImpl(MyFollowingFragment arg0) {

        }

        @Override
        public void inject(MyFollowingFragment arg0) {
          injectMyFollowingFragment(arg0);}

        private MyFollowingFragment injectMyFollowingFragment(MyFollowingFragment instance) {
          DaggerFragment_MembersInjector.injectAndroidInjector(instance, HomePagerFragmentSubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
          PagedUserFragment_MembersInjector.injectAvatars(instance, DaggerApplicationComponent.this.avatarLoaderProvider.get());
          return instance;
        }
      }

      private final class MembersFragmentSubcomponentFactory implements HomePagerFragmentProvider_MembersFragment.MembersFragmentSubcomponent.Factory {
        @Override
        public HomePagerFragmentProvider_MembersFragment.MembersFragmentSubcomponent create(
            MembersFragment arg0) {
          Preconditions.checkNotNull(arg0);
          return new MembersFragmentSubcomponentImpl(arg0);
        }
      }

      private final class MembersFragmentSubcomponentImpl implements HomePagerFragmentProvider_MembersFragment.MembersFragmentSubcomponent {
        private MembersFragmentSubcomponentImpl(MembersFragment arg0) {

        }

        @Override
        public void inject(MembersFragment arg0) {
          injectMembersFragment(arg0);}

        private MembersFragment injectMembersFragment(MembersFragment instance) {
          DaggerFragment_MembersInjector.injectAndroidInjector(instance, HomePagerFragmentSubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
          PagedUserFragment_MembersInjector.injectAvatars(instance, DaggerApplicationComponent.this.avatarLoaderProvider.get());
          return instance;
        }
      }
    }

    private final class GistsPagerFragmentSubcomponentFactory implements MainFragmentProvider_GistsPagerFragment.GistsPagerFragmentSubcomponent.Factory {
      @Override
      public MainFragmentProvider_GistsPagerFragment.GistsPagerFragmentSubcomponent create(
          GistsPagerFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new GistsPagerFragmentSubcomponentImpl(arg0);
      }
    }

    private final class GistsPagerFragmentSubcomponentImpl implements MainFragmentProvider_GistsPagerFragment.GistsPagerFragmentSubcomponent {
      private Provider<GistsPagerFragmentProvider_MyGistsFragment.MyGistsFragmentSubcomponent.Factory> myGistsFragmentSubcomponentFactoryProvider;

      private Provider<GistsPagerFragmentProvider_PublicGistsFragment.PublicGistsFragmentSubcomponent.Factory> publicGistsFragmentSubcomponentFactoryProvider;

      private Provider<GistsPagerFragmentProvider_StarredGistsFragment.StarredGistsFragmentSubcomponent.Factory> starredGistsFragmentSubcomponentFactoryProvider;

      private GistsPagerFragmentSubcomponentImpl(GistsPagerFragment arg0) {

        initialize(arg0);
      }

      private Map<Class<?>, Provider<AndroidInjector.Factory<?>>> getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(
          ) {
        return MapBuilder.<Class<?>, Provider<AndroidInjector.Factory<?>>>newMapBuilder(40).put(MainActivity.class, (Provider) DaggerApplicationComponent.this.mainActivitySubcomponentFactoryProvider).put(RepositoryViewActivity.class, (Provider) DaggerApplicationComponent.this.repositoryViewActivitySubcomponentFactoryProvider).put(IssuesViewActivity.class, (Provider) DaggerApplicationComponent.this.issuesViewActivitySubcomponentFactoryProvider).put(NotificationActivity.class, (Provider) DaggerApplicationComponent.this.notificationActivitySubcomponentFactoryProvider).put(CreateGistActivity.class, (Provider) DaggerApplicationComponent.this.createGistActivitySubcomponentFactoryProvider).put(IssueBrowseActivity.class, (Provider) DaggerApplicationComponent.this.issueBrowseActivitySubcomponentFactoryProvider).put(EditIssuesFilterActivity.class, (Provider) DaggerApplicationComponent.this.editIssuesFilterActivitySubcomponentFactoryProvider).put(EditIssueActivity.class, (Provider) DaggerApplicationComponent.this.editIssueActivitySubcomponentFactoryProvider).put(SearchActivity.class, (Provider) DaggerApplicationComponent.this.searchActivitySubcomponentFactoryProvider).put(FiltersViewActivity.class, (Provider) DaggerApplicationComponent.this.filtersViewActivitySubcomponentFactoryProvider).put(GistsViewActivity.class, (Provider) DaggerApplicationComponent.this.gistsViewActivitySubcomponentFactoryProvider).put(GistFilesViewActivity.class, (Provider) DaggerApplicationComponent.this.gistFilesViewActivitySubcomponentFactoryProvider).put(CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider2).put(com.github.pockethub.android.ui.commit.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider3).put(EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider2).put(RepositoryContributorsActivity.class, (Provider) DaggerApplicationComponent.this.repositoryContributorsActivitySubcomponentFactoryProvider).put(UserViewActivity.class, (Provider) DaggerApplicationComponent.this.userViewActivitySubcomponentFactoryProvider).put(LoginActivity.class, (Provider) DaggerApplicationComponent.this.loginActivitySubcomponentFactoryProvider).put(UriLauncherActivity.class, (Provider) DaggerApplicationComponent.this.uriLauncherActivitySubcomponentFactoryProvider).put(IssueSearchActivity.class, (Provider) DaggerApplicationComponent.this.issueSearchActivitySubcomponentFactoryProvider).put(CommitCompareViewActivity.class, (Provider) DaggerApplicationComponent.this.commitCompareViewActivitySubcomponentFactoryProvider).put(CommitViewActivity.class, (Provider) DaggerApplicationComponent.this.commitViewActivitySubcomponentFactoryProvider).put(CommitFileViewActivity.class, (Provider) DaggerApplicationComponent.this.commitFileViewActivitySubcomponentFactoryProvider).put(BranchFileViewActivity.class, (Provider) DaggerApplicationComponent.this.branchFileViewActivitySubcomponentFactoryProvider).put(LoginWebViewActivity.class, (Provider) DaggerApplicationComponent.this.loginWebViewActivitySubcomponentFactoryProvider).put(SyncAdapterService.class, (Provider) DaggerApplicationComponent.this.syncAdapterServiceSubcomponentFactoryProvider).put(LabelsDialogFragment.class, (Provider) DaggerApplicationComponent.this.labelsDialogFragmentSubcomponentFactoryProvider).put(AssigneeDialogFragment.class, (Provider) DaggerApplicationComponent.this.assigneeDialogFragmentSubcomponentFactoryProvider).put(MilestoneDialogFragment.class, (Provider) DaggerApplicationComponent.this.milestoneDialogFragmentSubcomponentFactoryProvider).put(RefDialogFragment.class, (Provider) DaggerApplicationComponent.this.refDialogFragmentSubcomponentFactoryProvider).put(ConfirmDialogFragment.class, (Provider) DaggerApplicationComponent.this.confirmDialogFragmentSubcomponentFactoryProvider).put(HomePagerFragment.class, (Provider) MainActivitySubcomponentImpl.this.homePagerFragmentSubcomponentFactoryProvider).put(GistsPagerFragment.class, (Provider) MainActivitySubcomponentImpl.this.gistsPagerFragmentSubcomponentFactoryProvider).put(IssueDashboardPagerFragment.class, (Provider) MainActivitySubcomponentImpl.this.issueDashboardPagerFragmentSubcomponentFactoryProvider).put(FilterListFragment.class, (Provider) MainActivitySubcomponentImpl.this.filterListFragmentSubcomponentFactoryProvider).put(MyGistsFragment.class, (Provider) myGistsFragmentSubcomponentFactoryProvider).put(PublicGistsFragment.class, (Provider) publicGistsFragmentSubcomponentFactoryProvider).put(StarredGistsFragment.class, (Provider) starredGistsFragmentSubcomponentFactoryProvider).build();}

      private DispatchingAndroidInjector<Object> getDispatchingAndroidInjectorOfObject() {
        return DispatchingAndroidInjector_Factory.newInstance(getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(), Collections.<String, Provider<AndroidInjector.Factory<?>>>emptyMap());}

      @SuppressWarnings("unchecked")
      private void initialize(final GistsPagerFragment arg0) {
        this.myGistsFragmentSubcomponentFactoryProvider = new Provider<GistsPagerFragmentProvider_MyGistsFragment.MyGistsFragmentSubcomponent.Factory>() {
          @Override
          public GistsPagerFragmentProvider_MyGistsFragment.MyGistsFragmentSubcomponent.Factory get(
              ) {
            return new MyGistsFragmentSubcomponentFactory();}
        };
        this.publicGistsFragmentSubcomponentFactoryProvider = new Provider<GistsPagerFragmentProvider_PublicGistsFragment.PublicGistsFragmentSubcomponent.Factory>() {
          @Override
          public GistsPagerFragmentProvider_PublicGistsFragment.PublicGistsFragmentSubcomponent.Factory get(
              ) {
            return new PublicGistsFragmentSubcomponentFactory();}
        };
        this.starredGistsFragmentSubcomponentFactoryProvider = new Provider<GistsPagerFragmentProvider_StarredGistsFragment.StarredGistsFragmentSubcomponent.Factory>() {
          @Override
          public GistsPagerFragmentProvider_StarredGistsFragment.StarredGistsFragmentSubcomponent.Factory get(
              ) {
            return new StarredGistsFragmentSubcomponentFactory();}
        };
      }

      @Override
      public void inject(GistsPagerFragment arg0) {
        injectGistsPagerFragment(arg0);}

      private GistsPagerFragment injectGistsPagerFragment(GistsPagerFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, getDispatchingAndroidInjectorOfObject());
        return instance;
      }

      private final class MyGistsFragmentSubcomponentFactory implements GistsPagerFragmentProvider_MyGistsFragment.MyGistsFragmentSubcomponent.Factory {
        @Override
        public GistsPagerFragmentProvider_MyGistsFragment.MyGistsFragmentSubcomponent create(
            MyGistsFragment arg0) {
          Preconditions.checkNotNull(arg0);
          return new MyGistsFragmentSubcomponentImpl(arg0);
        }
      }

      private final class MyGistsFragmentSubcomponentImpl implements GistsPagerFragmentProvider_MyGistsFragment.MyGistsFragmentSubcomponent {
        private MyGistsFragmentSubcomponentImpl(MyGistsFragment arg0) {

        }

        @Override
        public void inject(MyGistsFragment arg0) {
          injectMyGistsFragment(arg0);}

        private MyGistsFragment injectMyGistsFragment(MyGistsFragment instance) {
          DaggerFragment_MembersInjector.injectAndroidInjector(instance, GistsPagerFragmentSubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
          GistsFragment_MembersInjector.injectAvatars(instance, DaggerApplicationComponent.this.avatarLoaderProvider.get());
          GistsFragment_MembersInjector.injectStore(instance, DaggerApplicationComponent.this.gistStoreProvider.get());
          MyGistsFragment_MembersInjector.injectAccountProvider(instance, DaggerApplicationComponent.this.accountProvider);
          return instance;
        }
      }

      private final class PublicGistsFragmentSubcomponentFactory implements GistsPagerFragmentProvider_PublicGistsFragment.PublicGistsFragmentSubcomponent.Factory {
        @Override
        public GistsPagerFragmentProvider_PublicGistsFragment.PublicGistsFragmentSubcomponent create(
            PublicGistsFragment arg0) {
          Preconditions.checkNotNull(arg0);
          return new PublicGistsFragmentSubcomponentImpl(arg0);
        }
      }

      private final class PublicGistsFragmentSubcomponentImpl implements GistsPagerFragmentProvider_PublicGistsFragment.PublicGistsFragmentSubcomponent {
        private PublicGistsFragmentSubcomponentImpl(PublicGistsFragment arg0) {

        }

        @Override
        public void inject(PublicGistsFragment arg0) {
          injectPublicGistsFragment(arg0);}

        private PublicGistsFragment injectPublicGistsFragment(PublicGistsFragment instance) {
          DaggerFragment_MembersInjector.injectAndroidInjector(instance, GistsPagerFragmentSubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
          GistsFragment_MembersInjector.injectAvatars(instance, DaggerApplicationComponent.this.avatarLoaderProvider.get());
          GistsFragment_MembersInjector.injectStore(instance, DaggerApplicationComponent.this.gistStoreProvider.get());
          return instance;
        }
      }

      private final class StarredGistsFragmentSubcomponentFactory implements GistsPagerFragmentProvider_StarredGistsFragment.StarredGistsFragmentSubcomponent.Factory {
        @Override
        public GistsPagerFragmentProvider_StarredGistsFragment.StarredGistsFragmentSubcomponent create(
            StarredGistsFragment arg0) {
          Preconditions.checkNotNull(arg0);
          return new StarredGistsFragmentSubcomponentImpl(arg0);
        }
      }

      private final class StarredGistsFragmentSubcomponentImpl implements GistsPagerFragmentProvider_StarredGistsFragment.StarredGistsFragmentSubcomponent {
        private StarredGistsFragmentSubcomponentImpl(StarredGistsFragment arg0) {

        }

        @Override
        public void inject(StarredGistsFragment arg0) {
          injectStarredGistsFragment(arg0);}

        private StarredGistsFragment injectStarredGistsFragment(StarredGistsFragment instance) {
          DaggerFragment_MembersInjector.injectAndroidInjector(instance, GistsPagerFragmentSubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
          GistsFragment_MembersInjector.injectAvatars(instance, DaggerApplicationComponent.this.avatarLoaderProvider.get());
          GistsFragment_MembersInjector.injectStore(instance, DaggerApplicationComponent.this.gistStoreProvider.get());
          return instance;
        }
      }
    }

    private final class IssueDashboardPagerFragmentSubcomponentFactory implements MainFragmentProvider_IssueDashboardPagerFragment.IssueDashboardPagerFragmentSubcomponent.Factory {
      @Override
      public MainFragmentProvider_IssueDashboardPagerFragment.IssueDashboardPagerFragmentSubcomponent create(
          IssueDashboardPagerFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new IssueDashboardPagerFragmentSubcomponentImpl(arg0);
      }
    }

    private final class IssueDashboardPagerFragmentSubcomponentImpl implements MainFragmentProvider_IssueDashboardPagerFragment.IssueDashboardPagerFragmentSubcomponent {
      private Provider<IssueDashboardPagerFragmentProvider_DashboardIssueFragment.DashboardIssueFragmentSubcomponent.Factory> dashboardIssueFragmentSubcomponentFactoryProvider;

      private IssueDashboardPagerFragmentSubcomponentImpl(IssueDashboardPagerFragment arg0) {

        initialize(arg0);
      }

      private Map<Class<?>, Provider<AndroidInjector.Factory<?>>> getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(
          ) {
        return MapBuilder.<Class<?>, Provider<AndroidInjector.Factory<?>>>newMapBuilder(38).put(MainActivity.class, (Provider) DaggerApplicationComponent.this.mainActivitySubcomponentFactoryProvider).put(RepositoryViewActivity.class, (Provider) DaggerApplicationComponent.this.repositoryViewActivitySubcomponentFactoryProvider).put(IssuesViewActivity.class, (Provider) DaggerApplicationComponent.this.issuesViewActivitySubcomponentFactoryProvider).put(NotificationActivity.class, (Provider) DaggerApplicationComponent.this.notificationActivitySubcomponentFactoryProvider).put(CreateGistActivity.class, (Provider) DaggerApplicationComponent.this.createGistActivitySubcomponentFactoryProvider).put(IssueBrowseActivity.class, (Provider) DaggerApplicationComponent.this.issueBrowseActivitySubcomponentFactoryProvider).put(EditIssuesFilterActivity.class, (Provider) DaggerApplicationComponent.this.editIssuesFilterActivitySubcomponentFactoryProvider).put(EditIssueActivity.class, (Provider) DaggerApplicationComponent.this.editIssueActivitySubcomponentFactoryProvider).put(SearchActivity.class, (Provider) DaggerApplicationComponent.this.searchActivitySubcomponentFactoryProvider).put(FiltersViewActivity.class, (Provider) DaggerApplicationComponent.this.filtersViewActivitySubcomponentFactoryProvider).put(GistsViewActivity.class, (Provider) DaggerApplicationComponent.this.gistsViewActivitySubcomponentFactoryProvider).put(GistFilesViewActivity.class, (Provider) DaggerApplicationComponent.this.gistFilesViewActivitySubcomponentFactoryProvider).put(CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider2).put(com.github.pockethub.android.ui.commit.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider3).put(EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider2).put(RepositoryContributorsActivity.class, (Provider) DaggerApplicationComponent.this.repositoryContributorsActivitySubcomponentFactoryProvider).put(UserViewActivity.class, (Provider) DaggerApplicationComponent.this.userViewActivitySubcomponentFactoryProvider).put(LoginActivity.class, (Provider) DaggerApplicationComponent.this.loginActivitySubcomponentFactoryProvider).put(UriLauncherActivity.class, (Provider) DaggerApplicationComponent.this.uriLauncherActivitySubcomponentFactoryProvider).put(IssueSearchActivity.class, (Provider) DaggerApplicationComponent.this.issueSearchActivitySubcomponentFactoryProvider).put(CommitCompareViewActivity.class, (Provider) DaggerApplicationComponent.this.commitCompareViewActivitySubcomponentFactoryProvider).put(CommitViewActivity.class, (Provider) DaggerApplicationComponent.this.commitViewActivitySubcomponentFactoryProvider).put(CommitFileViewActivity.class, (Provider) DaggerApplicationComponent.this.commitFileViewActivitySubcomponentFactoryProvider).put(BranchFileViewActivity.class, (Provider) DaggerApplicationComponent.this.branchFileViewActivitySubcomponentFactoryProvider).put(LoginWebViewActivity.class, (Provider) DaggerApplicationComponent.this.loginWebViewActivitySubcomponentFactoryProvider).put(SyncAdapterService.class, (Provider) DaggerApplicationComponent.this.syncAdapterServiceSubcomponentFactoryProvider).put(LabelsDialogFragment.class, (Provider) DaggerApplicationComponent.this.labelsDialogFragmentSubcomponentFactoryProvider).put(AssigneeDialogFragment.class, (Provider) DaggerApplicationComponent.this.assigneeDialogFragmentSubcomponentFactoryProvider).put(MilestoneDialogFragment.class, (Provider) DaggerApplicationComponent.this.milestoneDialogFragmentSubcomponentFactoryProvider).put(RefDialogFragment.class, (Provider) DaggerApplicationComponent.this.refDialogFragmentSubcomponentFactoryProvider).put(ConfirmDialogFragment.class, (Provider) DaggerApplicationComponent.this.confirmDialogFragmentSubcomponentFactoryProvider).put(HomePagerFragment.class, (Provider) MainActivitySubcomponentImpl.this.homePagerFragmentSubcomponentFactoryProvider).put(GistsPagerFragment.class, (Provider) MainActivitySubcomponentImpl.this.gistsPagerFragmentSubcomponentFactoryProvider).put(IssueDashboardPagerFragment.class, (Provider) MainActivitySubcomponentImpl.this.issueDashboardPagerFragmentSubcomponentFactoryProvider).put(FilterListFragment.class, (Provider) MainActivitySubcomponentImpl.this.filterListFragmentSubcomponentFactoryProvider).put(DashboardIssueFragment.class, (Provider) dashboardIssueFragmentSubcomponentFactoryProvider).build();}

      private DispatchingAndroidInjector<Object> getDispatchingAndroidInjectorOfObject() {
        return DispatchingAndroidInjector_Factory.newInstance(getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(), Collections.<String, Provider<AndroidInjector.Factory<?>>>emptyMap());}

      @SuppressWarnings("unchecked")
      private void initialize(final IssueDashboardPagerFragment arg0) {
        this.dashboardIssueFragmentSubcomponentFactoryProvider = new Provider<IssueDashboardPagerFragmentProvider_DashboardIssueFragment.DashboardIssueFragmentSubcomponent.Factory>() {
          @Override
          public IssueDashboardPagerFragmentProvider_DashboardIssueFragment.DashboardIssueFragmentSubcomponent.Factory get(
              ) {
            return new DashboardIssueFragmentSubcomponentFactory();}
        };
      }

      @Override
      public void inject(IssueDashboardPagerFragment arg0) {
        injectIssueDashboardPagerFragment(arg0);}

      private IssueDashboardPagerFragment injectIssueDashboardPagerFragment(
          IssueDashboardPagerFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, getDispatchingAndroidInjectorOfObject());
        return instance;
      }

      private final class DashboardIssueFragmentSubcomponentFactory implements IssueDashboardPagerFragmentProvider_DashboardIssueFragment.DashboardIssueFragmentSubcomponent.Factory {
        @Override
        public IssueDashboardPagerFragmentProvider_DashboardIssueFragment.DashboardIssueFragmentSubcomponent create(
            DashboardIssueFragment arg0) {
          Preconditions.checkNotNull(arg0);
          return new DashboardIssueFragmentSubcomponentImpl(arg0);
        }
      }

      private final class DashboardIssueFragmentSubcomponentImpl implements IssueDashboardPagerFragmentProvider_DashboardIssueFragment.DashboardIssueFragmentSubcomponent {
        private DashboardIssueFragmentSubcomponentImpl(DashboardIssueFragment arg0) {

        }

        @Override
        public void inject(DashboardIssueFragment arg0) {
          injectDashboardIssueFragment(arg0);}

        private DashboardIssueFragment injectDashboardIssueFragment(
            DashboardIssueFragment instance) {
          DaggerFragment_MembersInjector.injectAndroidInjector(instance, IssueDashboardPagerFragmentSubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
          DashboardIssueFragment_MembersInjector.injectService(instance, DaggerApplicationComponent.this.providesIssueServiceProvider.get());
          DashboardIssueFragment_MembersInjector.injectStore(instance, DaggerApplicationComponent.this.issueStoreProvider.get());
          DashboardIssueFragment_MembersInjector.injectAvatars(instance, DaggerApplicationComponent.this.avatarLoaderProvider.get());
          return instance;
        }
      }
    }

    private final class MFP_FLF_FilterListFragmentSubcomponentFactory implements MainFragmentProvider_FilterListFragment.FilterListFragmentSubcomponent.Factory {
      @Override
      public MainFragmentProvider_FilterListFragment.FilterListFragmentSubcomponent create(
          FilterListFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new MFP_FLF_FilterListFragmentSubcomponentImpl(arg0);
      }
    }

    private final class MFP_FLF_FilterListFragmentSubcomponentImpl implements MainFragmentProvider_FilterListFragment.FilterListFragmentSubcomponent {
      private MFP_FLF_FilterListFragmentSubcomponentImpl(FilterListFragment arg0) {

      }

      @Override
      public void inject(FilterListFragment arg0) {
        injectFilterListFragment(arg0);}

      private FilterListFragment injectFilterListFragment(FilterListFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, MainActivitySubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
        FilterListFragment_MembersInjector.injectCache(instance, MainActivitySubcomponentImpl.this.getAccountDataManager());
        FilterListFragment_MembersInjector.injectAvatars(instance, DaggerApplicationComponent.this.avatarLoaderProvider.get());
        return instance;
      }
    }
  }

  private final class RepositoryViewActivitySubcomponentFactory implements ActivityBuilder_RepositoryViewActivity.RepositoryViewActivitySubcomponent.Factory {
    @Override
    public ActivityBuilder_RepositoryViewActivity.RepositoryViewActivitySubcomponent create(
        RepositoryViewActivity arg0) {
      Preconditions.checkNotNull(arg0);
      return new RepositoryViewActivitySubcomponentImpl(arg0);
    }
  }

  private final class RepositoryViewActivitySubcomponentImpl implements ActivityBuilder_RepositoryViewActivity.RepositoryViewActivitySubcomponent {
    private Provider<RepositoryViewFragmentProvider_RepositoryReadmeFragment.RepositoryReadmeFragmentSubcomponent.Factory> repositoryReadmeFragmentSubcomponentFactoryProvider;

    private Provider<RepositoryViewFragmentProvider_RepositoryNewsFragment.RepositoryNewsFragmentSubcomponent.Factory> repositoryNewsFragmentSubcomponentFactoryProvider;

    private Provider<RepositoryViewFragmentProvider_RepositoryCodeFragment.RepositoryCodeFragmentSubcomponent.Factory> repositoryCodeFragmentSubcomponentFactoryProvider;

    private Provider<RepositoryViewFragmentProvider_CommitListFragment.CommitListFragmentSubcomponent.Factory> commitListFragmentSubcomponentFactoryProvider;

    private Provider<RepositoryViewFragmentProvider_IssuesFragment.IssuesFragmentSubcomponent.Factory> issuesFragmentSubcomponentFactoryProvider;

    private RepositoryViewActivitySubcomponentImpl(RepositoryViewActivity arg0) {

      initialize(arg0);
    }

    private Map<Class<?>, Provider<AndroidInjector.Factory<?>>> getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(
        ) {
      return MapBuilder.<Class<?>, Provider<AndroidInjector.Factory<?>>>newMapBuilder(38).put(MainActivity.class, (Provider) DaggerApplicationComponent.this.mainActivitySubcomponentFactoryProvider).put(RepositoryViewActivity.class, (Provider) DaggerApplicationComponent.this.repositoryViewActivitySubcomponentFactoryProvider).put(IssuesViewActivity.class, (Provider) DaggerApplicationComponent.this.issuesViewActivitySubcomponentFactoryProvider).put(NotificationActivity.class, (Provider) DaggerApplicationComponent.this.notificationActivitySubcomponentFactoryProvider).put(CreateGistActivity.class, (Provider) DaggerApplicationComponent.this.createGistActivitySubcomponentFactoryProvider).put(IssueBrowseActivity.class, (Provider) DaggerApplicationComponent.this.issueBrowseActivitySubcomponentFactoryProvider).put(EditIssuesFilterActivity.class, (Provider) DaggerApplicationComponent.this.editIssuesFilterActivitySubcomponentFactoryProvider).put(EditIssueActivity.class, (Provider) DaggerApplicationComponent.this.editIssueActivitySubcomponentFactoryProvider).put(SearchActivity.class, (Provider) DaggerApplicationComponent.this.searchActivitySubcomponentFactoryProvider).put(FiltersViewActivity.class, (Provider) DaggerApplicationComponent.this.filtersViewActivitySubcomponentFactoryProvider).put(GistsViewActivity.class, (Provider) DaggerApplicationComponent.this.gistsViewActivitySubcomponentFactoryProvider).put(GistFilesViewActivity.class, (Provider) DaggerApplicationComponent.this.gistFilesViewActivitySubcomponentFactoryProvider).put(CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider2).put(com.github.pockethub.android.ui.commit.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider3).put(EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider2).put(RepositoryContributorsActivity.class, (Provider) DaggerApplicationComponent.this.repositoryContributorsActivitySubcomponentFactoryProvider).put(UserViewActivity.class, (Provider) DaggerApplicationComponent.this.userViewActivitySubcomponentFactoryProvider).put(LoginActivity.class, (Provider) DaggerApplicationComponent.this.loginActivitySubcomponentFactoryProvider).put(UriLauncherActivity.class, (Provider) DaggerApplicationComponent.this.uriLauncherActivitySubcomponentFactoryProvider).put(IssueSearchActivity.class, (Provider) DaggerApplicationComponent.this.issueSearchActivitySubcomponentFactoryProvider).put(CommitCompareViewActivity.class, (Provider) DaggerApplicationComponent.this.commitCompareViewActivitySubcomponentFactoryProvider).put(CommitViewActivity.class, (Provider) DaggerApplicationComponent.this.commitViewActivitySubcomponentFactoryProvider).put(CommitFileViewActivity.class, (Provider) DaggerApplicationComponent.this.commitFileViewActivitySubcomponentFactoryProvider).put(BranchFileViewActivity.class, (Provider) DaggerApplicationComponent.this.branchFileViewActivitySubcomponentFactoryProvider).put(LoginWebViewActivity.class, (Provider) DaggerApplicationComponent.this.loginWebViewActivitySubcomponentFactoryProvider).put(SyncAdapterService.class, (Provider) DaggerApplicationComponent.this.syncAdapterServiceSubcomponentFactoryProvider).put(LabelsDialogFragment.class, (Provider) DaggerApplicationComponent.this.labelsDialogFragmentSubcomponentFactoryProvider).put(AssigneeDialogFragment.class, (Provider) DaggerApplicationComponent.this.assigneeDialogFragmentSubcomponentFactoryProvider).put(MilestoneDialogFragment.class, (Provider) DaggerApplicationComponent.this.milestoneDialogFragmentSubcomponentFactoryProvider).put(RefDialogFragment.class, (Provider) DaggerApplicationComponent.this.refDialogFragmentSubcomponentFactoryProvider).put(ConfirmDialogFragment.class, (Provider) DaggerApplicationComponent.this.confirmDialogFragmentSubcomponentFactoryProvider).put(RepositoryReadmeFragment.class, (Provider) repositoryReadmeFragmentSubcomponentFactoryProvider).put(RepositoryNewsFragment.class, (Provider) repositoryNewsFragmentSubcomponentFactoryProvider).put(RepositoryCodeFragment.class, (Provider) repositoryCodeFragmentSubcomponentFactoryProvider).put(CommitListFragment.class, (Provider) commitListFragmentSubcomponentFactoryProvider).put(IssuesFragment.class, (Provider) issuesFragmentSubcomponentFactoryProvider).build();}

    private DispatchingAndroidInjector<Object> getDispatchingAndroidInjectorOfObject() {
      return DispatchingAndroidInjector_Factory.newInstance(getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(), Collections.<String, Provider<AndroidInjector.Factory<?>>>emptyMap());}

    @SuppressWarnings("unchecked")
    private void initialize(final RepositoryViewActivity arg0) {
      this.repositoryReadmeFragmentSubcomponentFactoryProvider = new Provider<RepositoryViewFragmentProvider_RepositoryReadmeFragment.RepositoryReadmeFragmentSubcomponent.Factory>() {
        @Override
        public RepositoryViewFragmentProvider_RepositoryReadmeFragment.RepositoryReadmeFragmentSubcomponent.Factory get(
            ) {
          return new RepositoryReadmeFragmentSubcomponentFactory();}
      };
      this.repositoryNewsFragmentSubcomponentFactoryProvider = new Provider<RepositoryViewFragmentProvider_RepositoryNewsFragment.RepositoryNewsFragmentSubcomponent.Factory>() {
        @Override
        public RepositoryViewFragmentProvider_RepositoryNewsFragment.RepositoryNewsFragmentSubcomponent.Factory get(
            ) {
          return new RepositoryNewsFragmentSubcomponentFactory();}
      };
      this.repositoryCodeFragmentSubcomponentFactoryProvider = new Provider<RepositoryViewFragmentProvider_RepositoryCodeFragment.RepositoryCodeFragmentSubcomponent.Factory>() {
        @Override
        public RepositoryViewFragmentProvider_RepositoryCodeFragment.RepositoryCodeFragmentSubcomponent.Factory get(
            ) {
          return new RepositoryCodeFragmentSubcomponentFactory();}
      };
      this.commitListFragmentSubcomponentFactoryProvider = new Provider<RepositoryViewFragmentProvider_CommitListFragment.CommitListFragmentSubcomponent.Factory>() {
        @Override
        public RepositoryViewFragmentProvider_CommitListFragment.CommitListFragmentSubcomponent.Factory get(
            ) {
          return new CommitListFragmentSubcomponentFactory();}
      };
      this.issuesFragmentSubcomponentFactoryProvider = new Provider<RepositoryViewFragmentProvider_IssuesFragment.IssuesFragmentSubcomponent.Factory>() {
        @Override
        public RepositoryViewFragmentProvider_IssuesFragment.IssuesFragmentSubcomponent.Factory get(
            ) {
          return new RVFP_IF_IssuesFragmentSubcomponentFactory();}
      };
    }

    @Override
    public void inject(RepositoryViewActivity arg0) {
      injectRepositoryViewActivity(arg0);}

    private RepositoryViewActivity injectRepositoryViewActivity(RepositoryViewActivity instance) {
      DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, getDispatchingAndroidInjectorOfObject());
      return instance;
    }

    private final class RepositoryReadmeFragmentSubcomponentFactory implements RepositoryViewFragmentProvider_RepositoryReadmeFragment.RepositoryReadmeFragmentSubcomponent.Factory {
      @Override
      public RepositoryViewFragmentProvider_RepositoryReadmeFragment.RepositoryReadmeFragmentSubcomponent create(
          RepositoryReadmeFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new RepositoryReadmeFragmentSubcomponentImpl(arg0);
      }
    }

    private final class RepositoryReadmeFragmentSubcomponentImpl implements RepositoryViewFragmentProvider_RepositoryReadmeFragment.RepositoryReadmeFragmentSubcomponent {
      private RepositoryReadmeFragmentSubcomponentImpl(RepositoryReadmeFragment arg0) {

      }

      @Override
      public void inject(RepositoryReadmeFragment arg0) {
        injectRepositoryReadmeFragment(arg0);}

      private RepositoryReadmeFragment injectRepositoryReadmeFragment(
          RepositoryReadmeFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, RepositoryViewActivitySubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
        return instance;
      }
    }

    private final class RepositoryNewsFragmentSubcomponentFactory implements RepositoryViewFragmentProvider_RepositoryNewsFragment.RepositoryNewsFragmentSubcomponent.Factory {
      @Override
      public RepositoryViewFragmentProvider_RepositoryNewsFragment.RepositoryNewsFragmentSubcomponent create(
          RepositoryNewsFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new RepositoryNewsFragmentSubcomponentImpl(arg0);
      }
    }

    private final class RepositoryNewsFragmentSubcomponentImpl implements RepositoryViewFragmentProvider_RepositoryNewsFragment.RepositoryNewsFragmentSubcomponent {
      private RepositoryNewsFragmentSubcomponentImpl(RepositoryNewsFragment arg0) {

      }

      @Override
      public void inject(RepositoryNewsFragment arg0) {
        injectRepositoryNewsFragment(arg0);}

      private RepositoryNewsFragment injectRepositoryNewsFragment(RepositoryNewsFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, RepositoryViewActivitySubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
        NewsFragment_MembersInjector.injectAvatars(instance, DaggerApplicationComponent.this.avatarLoaderProvider.get());
        return instance;
      }
    }

    private final class RepositoryCodeFragmentSubcomponentFactory implements RepositoryViewFragmentProvider_RepositoryCodeFragment.RepositoryCodeFragmentSubcomponent.Factory {
      @Override
      public RepositoryViewFragmentProvider_RepositoryCodeFragment.RepositoryCodeFragmentSubcomponent create(
          RepositoryCodeFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new RepositoryCodeFragmentSubcomponentImpl(arg0);
      }
    }

    private final class RepositoryCodeFragmentSubcomponentImpl implements RepositoryViewFragmentProvider_RepositoryCodeFragment.RepositoryCodeFragmentSubcomponent {
      private RepositoryCodeFragmentSubcomponentImpl(RepositoryCodeFragment arg0) {

      }

      @Override
      public void inject(RepositoryCodeFragment arg0) {
        injectRepositoryCodeFragment(arg0);}

      private RepositoryCodeFragment injectRepositoryCodeFragment(RepositoryCodeFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, RepositoryViewActivitySubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
        return instance;
      }
    }

    private final class CommitListFragmentSubcomponentFactory implements RepositoryViewFragmentProvider_CommitListFragment.CommitListFragmentSubcomponent.Factory {
      @Override
      public RepositoryViewFragmentProvider_CommitListFragment.CommitListFragmentSubcomponent create(
          CommitListFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new CommitListFragmentSubcomponentImpl(arg0);
      }
    }

    private final class CommitListFragmentSubcomponentImpl implements RepositoryViewFragmentProvider_CommitListFragment.CommitListFragmentSubcomponent {
      private CommitListFragmentSubcomponentImpl(CommitListFragment arg0) {

      }

      @Override
      public void inject(CommitListFragment arg0) {
        injectCommitListFragment(arg0);}

      private CommitListFragment injectCommitListFragment(CommitListFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, RepositoryViewActivitySubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
        CommitListFragment_MembersInjector.injectService(instance, DaggerApplicationComponent.this.providesRepositoryCommitServiceProvider.get());
        CommitListFragment_MembersInjector.injectRepoService(instance, DaggerApplicationComponent.this.providesRepositoryServiceProvider.get());
        CommitListFragment_MembersInjector.injectAvatars(instance, DaggerApplicationComponent.this.avatarLoaderProvider.get());
        CommitListFragment_MembersInjector.injectStore(instance, DaggerApplicationComponent.this.commitStoreProvider.get());
        return instance;
      }
    }

    private final class RVFP_IF_IssuesFragmentSubcomponentFactory implements RepositoryViewFragmentProvider_IssuesFragment.IssuesFragmentSubcomponent.Factory {
      @Override
      public RepositoryViewFragmentProvider_IssuesFragment.IssuesFragmentSubcomponent create(
          IssuesFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new RVFP_IF_IssuesFragmentSubcomponentImpl(arg0);
      }
    }

    private final class RVFP_IF_IssuesFragmentSubcomponentImpl implements RepositoryViewFragmentProvider_IssuesFragment.IssuesFragmentSubcomponent {
      private RVFP_IF_IssuesFragmentSubcomponentImpl(IssuesFragment arg0) {

      }

      private DatabaseCache getDatabaseCache() {
        return injectDatabaseCache(DatabaseCache_Factory.newInstance());}

      private OrganizationRepositoriesFactory getOrganizationRepositoriesFactory() {
        return injectOrganizationRepositoriesFactory(OrganizationRepositoriesFactory_Factory.newInstance());}

      private Organizations getOrganizations() {
        return new Organizations(DaggerApplicationComponent.this.provideApplicationContextProvider.get());}

      private AccountDataManager getAccountDataManager() {
        return injectAccountDataManager(AccountDataManager_Factory.newInstance());}

      @Override
      public void inject(IssuesFragment arg0) {
        injectIssuesFragment(arg0);}

      private DatabaseCache injectDatabaseCache(DatabaseCache instance) {
        DatabaseCache_MembersInjector.injectDatabase(instance, DaggerApplicationComponent.this.provideDatabaseProvider.get());
        return instance;
      }

      private OrganizationRepositoriesFactory injectOrganizationRepositoriesFactory(
          OrganizationRepositoriesFactory instance) {
        OrganizationRepositoriesFactory_MembersInjector.injectContext(instance, DaggerApplicationComponent.this.provideApplicationContextProvider.get());
        OrganizationRepositoriesFactory_MembersInjector.injectAccountProvider(instance, DaggerApplicationComponent.this.accountProvider);
        return instance;
      }

      private AccountDataManager injectAccountDataManager(AccountDataManager instance) {
        AccountDataManager_MembersInjector.injectContext(instance, DaggerApplicationComponent.this.provideApplicationContextProvider.get());
        AccountDataManager_MembersInjector.injectDbCache(instance, getDatabaseCache());
        AccountDataManager_MembersInjector.injectAllRepos(instance, getOrganizationRepositoriesFactory());
        AccountDataManager_MembersInjector.injectUserAndOrgsResource(instance, getOrganizations());
        AccountDataManager_MembersInjector.injectRoot(instance, DaggerApplicationComponent.this.getNamedFile());
        return instance;
      }

      private IssuesFragment injectIssuesFragment(IssuesFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, RepositoryViewActivitySubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
        IssuesFragment_MembersInjector.injectCache(instance, getAccountDataManager());
        IssuesFragment_MembersInjector.injectStore(instance, DaggerApplicationComponent.this.issueStoreProvider.get());
        IssuesFragment_MembersInjector.injectAvatars(instance, DaggerApplicationComponent.this.avatarLoaderProvider.get());
        IssuesFragment_MembersInjector.injectService(instance, DaggerApplicationComponent.this.providesIssueServiceProvider.get());
        return instance;
      }
    }
  }

  private final class IssuesViewActivitySubcomponentFactory implements ActivityBuilder_IssuesViewActivity.IssuesViewActivitySubcomponent.Factory {
    @Override
    public ActivityBuilder_IssuesViewActivity.IssuesViewActivitySubcomponent create(
        IssuesViewActivity arg0) {
      Preconditions.checkNotNull(arg0);
      return new IssuesViewActivitySubcomponentImpl(arg0);
    }
  }

  private final class IssuesViewActivitySubcomponentImpl implements ActivityBuilder_IssuesViewActivity.IssuesViewActivitySubcomponent {
    private Provider<IssuesViewFragmentProvider_IssueFragment.IssueFragmentSubcomponent.Factory> issueFragmentSubcomponentFactoryProvider;

    private IssuesViewActivitySubcomponentImpl(IssuesViewActivity arg0) {

      initialize(arg0);
    }

    private Map<Class<?>, Provider<AndroidInjector.Factory<?>>> getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(
        ) {
      return MapBuilder.<Class<?>, Provider<AndroidInjector.Factory<?>>>newMapBuilder(34).put(MainActivity.class, (Provider) DaggerApplicationComponent.this.mainActivitySubcomponentFactoryProvider).put(RepositoryViewActivity.class, (Provider) DaggerApplicationComponent.this.repositoryViewActivitySubcomponentFactoryProvider).put(IssuesViewActivity.class, (Provider) DaggerApplicationComponent.this.issuesViewActivitySubcomponentFactoryProvider).put(NotificationActivity.class, (Provider) DaggerApplicationComponent.this.notificationActivitySubcomponentFactoryProvider).put(CreateGistActivity.class, (Provider) DaggerApplicationComponent.this.createGistActivitySubcomponentFactoryProvider).put(IssueBrowseActivity.class, (Provider) DaggerApplicationComponent.this.issueBrowseActivitySubcomponentFactoryProvider).put(EditIssuesFilterActivity.class, (Provider) DaggerApplicationComponent.this.editIssuesFilterActivitySubcomponentFactoryProvider).put(EditIssueActivity.class, (Provider) DaggerApplicationComponent.this.editIssueActivitySubcomponentFactoryProvider).put(SearchActivity.class, (Provider) DaggerApplicationComponent.this.searchActivitySubcomponentFactoryProvider).put(FiltersViewActivity.class, (Provider) DaggerApplicationComponent.this.filtersViewActivitySubcomponentFactoryProvider).put(GistsViewActivity.class, (Provider) DaggerApplicationComponent.this.gistsViewActivitySubcomponentFactoryProvider).put(GistFilesViewActivity.class, (Provider) DaggerApplicationComponent.this.gistFilesViewActivitySubcomponentFactoryProvider).put(CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider2).put(com.github.pockethub.android.ui.commit.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider3).put(EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider2).put(RepositoryContributorsActivity.class, (Provider) DaggerApplicationComponent.this.repositoryContributorsActivitySubcomponentFactoryProvider).put(UserViewActivity.class, (Provider) DaggerApplicationComponent.this.userViewActivitySubcomponentFactoryProvider).put(LoginActivity.class, (Provider) DaggerApplicationComponent.this.loginActivitySubcomponentFactoryProvider).put(UriLauncherActivity.class, (Provider) DaggerApplicationComponent.this.uriLauncherActivitySubcomponentFactoryProvider).put(IssueSearchActivity.class, (Provider) DaggerApplicationComponent.this.issueSearchActivitySubcomponentFactoryProvider).put(CommitCompareViewActivity.class, (Provider) DaggerApplicationComponent.this.commitCompareViewActivitySubcomponentFactoryProvider).put(CommitViewActivity.class, (Provider) DaggerApplicationComponent.this.commitViewActivitySubcomponentFactoryProvider).put(CommitFileViewActivity.class, (Provider) DaggerApplicationComponent.this.commitFileViewActivitySubcomponentFactoryProvider).put(BranchFileViewActivity.class, (Provider) DaggerApplicationComponent.this.branchFileViewActivitySubcomponentFactoryProvider).put(LoginWebViewActivity.class, (Provider) DaggerApplicationComponent.this.loginWebViewActivitySubcomponentFactoryProvider).put(SyncAdapterService.class, (Provider) DaggerApplicationComponent.this.syncAdapterServiceSubcomponentFactoryProvider).put(LabelsDialogFragment.class, (Provider) DaggerApplicationComponent.this.labelsDialogFragmentSubcomponentFactoryProvider).put(AssigneeDialogFragment.class, (Provider) DaggerApplicationComponent.this.assigneeDialogFragmentSubcomponentFactoryProvider).put(MilestoneDialogFragment.class, (Provider) DaggerApplicationComponent.this.milestoneDialogFragmentSubcomponentFactoryProvider).put(RefDialogFragment.class, (Provider) DaggerApplicationComponent.this.refDialogFragmentSubcomponentFactoryProvider).put(ConfirmDialogFragment.class, (Provider) DaggerApplicationComponent.this.confirmDialogFragmentSubcomponentFactoryProvider).put(IssueFragment.class, (Provider) issueFragmentSubcomponentFactoryProvider).build();}

    private DispatchingAndroidInjector<Object> getDispatchingAndroidInjectorOfObject() {
      return DispatchingAndroidInjector_Factory.newInstance(getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(), Collections.<String, Provider<AndroidInjector.Factory<?>>>emptyMap());}

    @SuppressWarnings("unchecked")
    private void initialize(final IssuesViewActivity arg0) {
      this.issueFragmentSubcomponentFactoryProvider = new Provider<IssuesViewFragmentProvider_IssueFragment.IssueFragmentSubcomponent.Factory>() {
        @Override
        public IssuesViewFragmentProvider_IssueFragment.IssueFragmentSubcomponent.Factory get() {
          return new IssueFragmentSubcomponentFactory();}
      };
    }

    @Override
    public void inject(IssuesViewActivity arg0) {
      injectIssuesViewActivity(arg0);}

    private IssuesViewActivity injectIssuesViewActivity(IssuesViewActivity instance) {
      DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, getDispatchingAndroidInjectorOfObject());
      IssuesViewActivity_MembersInjector.injectStore(instance, DaggerApplicationComponent.this.issueStoreProvider.get());
      return instance;
    }

    private final class IssueFragmentSubcomponentFactory implements IssuesViewFragmentProvider_IssueFragment.IssueFragmentSubcomponent.Factory {
      @Override
      public IssuesViewFragmentProvider_IssueFragment.IssueFragmentSubcomponent create(
          IssueFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new IssueFragmentSubcomponentImpl(arg0);
      }
    }

    private final class IssueFragmentSubcomponentImpl implements IssuesViewFragmentProvider_IssueFragment.IssueFragmentSubcomponent {
      private Provider<HttpImageGetter> httpImageGetterProvider;

      private IssueFragmentSubcomponentImpl(IssueFragment arg0) {

        initialize(arg0);
      }

      private RefreshIssueTaskFactory getRefreshIssueTaskFactory() {
        return new RefreshIssueTaskFactory(DaggerApplicationComponent.this.provideApplicationContextProvider, httpImageGetterProvider, DaggerApplicationComponent.this.issueStoreProvider);}

      private EditLabelsTaskFactory getEditLabelsTaskFactory() {
        return new EditLabelsTaskFactory(DaggerApplicationComponent.this.issueStoreProvider);}

      private EditMilestoneTaskFactory getEditMilestoneTaskFactory() {
        return new EditMilestoneTaskFactory(DaggerApplicationComponent.this.issueStoreProvider);}

      private EditAssigneeTaskFactory getEditAssigneeTaskFactory() {
        return new EditAssigneeTaskFactory(DaggerApplicationComponent.this.issueStoreProvider);}

      private EditStateTaskFactory getEditStateTaskFactory() {
        return new EditStateTaskFactory(DaggerApplicationComponent.this.issueStoreProvider);}

      private HttpImageGetter getHttpImageGetter() {
        return new HttpImageGetter(DaggerApplicationComponent.this.provideApplicationContextProvider.get());}

      @SuppressWarnings("unchecked")
      private void initialize(final IssueFragment arg0) {
        this.httpImageGetterProvider = HttpImageGetter_Factory.create(DaggerApplicationComponent.this.provideApplicationContextProvider);
      }

      @Override
      public void inject(IssueFragment arg0) {
        injectIssueFragment(arg0);}

      private IssueFragment injectIssueFragment(IssueFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, IssuesViewActivitySubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
        IssueFragment_MembersInjector.injectAvatars(instance, DaggerApplicationComponent.this.avatarLoaderProvider.get());
        IssueFragment_MembersInjector.injectStore(instance, DaggerApplicationComponent.this.issueStoreProvider.get());
        IssueFragment_MembersInjector.injectRefreshIssueTaskFactory(instance, getRefreshIssueTaskFactory());
        IssueFragment_MembersInjector.injectLabelsTaskFactory(instance, getEditLabelsTaskFactory());
        IssueFragment_MembersInjector.injectMilestoneTaskFactory(instance, getEditMilestoneTaskFactory());
        IssueFragment_MembersInjector.injectAssigneeTaskFactory(instance, getEditAssigneeTaskFactory());
        IssueFragment_MembersInjector.injectStateTaskFactory(instance, getEditStateTaskFactory());
        IssueFragment_MembersInjector.injectBodyImageGetter(instance, getHttpImageGetter());
        IssueFragment_MembersInjector.injectCommentImageGetter(instance, getHttpImageGetter());
        return instance;
      }
    }
  }

  private final class NotificationActivitySubcomponentFactory implements ActivityBuilder_NotificationActivity.NotificationActivitySubcomponent.Factory {
    @Override
    public ActivityBuilder_NotificationActivity.NotificationActivitySubcomponent create(
        NotificationActivity arg0) {
      Preconditions.checkNotNull(arg0);
      return new NotificationActivitySubcomponentImpl(arg0);
    }
  }

  private final class NotificationActivitySubcomponentImpl implements ActivityBuilder_NotificationActivity.NotificationActivitySubcomponent {
    private Provider<NotificationFragmentProvider_NotificationListFragment.NotificationListFragmentSubcomponent.Factory> notificationListFragmentSubcomponentFactoryProvider;

    private NotificationActivitySubcomponentImpl(NotificationActivity arg0) {

      initialize(arg0);
    }

    private Map<Class<?>, Provider<AndroidInjector.Factory<?>>> getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(
        ) {
      return MapBuilder.<Class<?>, Provider<AndroidInjector.Factory<?>>>newMapBuilder(34).put(MainActivity.class, (Provider) DaggerApplicationComponent.this.mainActivitySubcomponentFactoryProvider).put(RepositoryViewActivity.class, (Provider) DaggerApplicationComponent.this.repositoryViewActivitySubcomponentFactoryProvider).put(IssuesViewActivity.class, (Provider) DaggerApplicationComponent.this.issuesViewActivitySubcomponentFactoryProvider).put(NotificationActivity.class, (Provider) DaggerApplicationComponent.this.notificationActivitySubcomponentFactoryProvider).put(CreateGistActivity.class, (Provider) DaggerApplicationComponent.this.createGistActivitySubcomponentFactoryProvider).put(IssueBrowseActivity.class, (Provider) DaggerApplicationComponent.this.issueBrowseActivitySubcomponentFactoryProvider).put(EditIssuesFilterActivity.class, (Provider) DaggerApplicationComponent.this.editIssuesFilterActivitySubcomponentFactoryProvider).put(EditIssueActivity.class, (Provider) DaggerApplicationComponent.this.editIssueActivitySubcomponentFactoryProvider).put(SearchActivity.class, (Provider) DaggerApplicationComponent.this.searchActivitySubcomponentFactoryProvider).put(FiltersViewActivity.class, (Provider) DaggerApplicationComponent.this.filtersViewActivitySubcomponentFactoryProvider).put(GistsViewActivity.class, (Provider) DaggerApplicationComponent.this.gistsViewActivitySubcomponentFactoryProvider).put(GistFilesViewActivity.class, (Provider) DaggerApplicationComponent.this.gistFilesViewActivitySubcomponentFactoryProvider).put(CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider2).put(com.github.pockethub.android.ui.commit.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider3).put(EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider2).put(RepositoryContributorsActivity.class, (Provider) DaggerApplicationComponent.this.repositoryContributorsActivitySubcomponentFactoryProvider).put(UserViewActivity.class, (Provider) DaggerApplicationComponent.this.userViewActivitySubcomponentFactoryProvider).put(LoginActivity.class, (Provider) DaggerApplicationComponent.this.loginActivitySubcomponentFactoryProvider).put(UriLauncherActivity.class, (Provider) DaggerApplicationComponent.this.uriLauncherActivitySubcomponentFactoryProvider).put(IssueSearchActivity.class, (Provider) DaggerApplicationComponent.this.issueSearchActivitySubcomponentFactoryProvider).put(CommitCompareViewActivity.class, (Provider) DaggerApplicationComponent.this.commitCompareViewActivitySubcomponentFactoryProvider).put(CommitViewActivity.class, (Provider) DaggerApplicationComponent.this.commitViewActivitySubcomponentFactoryProvider).put(CommitFileViewActivity.class, (Provider) DaggerApplicationComponent.this.commitFileViewActivitySubcomponentFactoryProvider).put(BranchFileViewActivity.class, (Provider) DaggerApplicationComponent.this.branchFileViewActivitySubcomponentFactoryProvider).put(LoginWebViewActivity.class, (Provider) DaggerApplicationComponent.this.loginWebViewActivitySubcomponentFactoryProvider).put(SyncAdapterService.class, (Provider) DaggerApplicationComponent.this.syncAdapterServiceSubcomponentFactoryProvider).put(LabelsDialogFragment.class, (Provider) DaggerApplicationComponent.this.labelsDialogFragmentSubcomponentFactoryProvider).put(AssigneeDialogFragment.class, (Provider) DaggerApplicationComponent.this.assigneeDialogFragmentSubcomponentFactoryProvider).put(MilestoneDialogFragment.class, (Provider) DaggerApplicationComponent.this.milestoneDialogFragmentSubcomponentFactoryProvider).put(RefDialogFragment.class, (Provider) DaggerApplicationComponent.this.refDialogFragmentSubcomponentFactoryProvider).put(ConfirmDialogFragment.class, (Provider) DaggerApplicationComponent.this.confirmDialogFragmentSubcomponentFactoryProvider).put(NotificationListFragment.class, (Provider) notificationListFragmentSubcomponentFactoryProvider).build();}

    private DispatchingAndroidInjector<Object> getDispatchingAndroidInjectorOfObject() {
      return DispatchingAndroidInjector_Factory.newInstance(getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(), Collections.<String, Provider<AndroidInjector.Factory<?>>>emptyMap());}

    @SuppressWarnings("unchecked")
    private void initialize(final NotificationActivity arg0) {
      this.notificationListFragmentSubcomponentFactoryProvider = new Provider<NotificationFragmentProvider_NotificationListFragment.NotificationListFragmentSubcomponent.Factory>() {
        @Override
        public NotificationFragmentProvider_NotificationListFragment.NotificationListFragmentSubcomponent.Factory get(
            ) {
          return new NotificationListFragmentSubcomponentFactory();}
      };
    }

    @Override
    public void inject(NotificationActivity arg0) {
      injectNotificationActivity(arg0);}

    private NotificationActivity injectNotificationActivity(NotificationActivity instance) {
      DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, getDispatchingAndroidInjectorOfObject());
      return instance;
    }

    private final class NotificationListFragmentSubcomponentFactory implements NotificationFragmentProvider_NotificationListFragment.NotificationListFragmentSubcomponent.Factory {
      @Override
      public NotificationFragmentProvider_NotificationListFragment.NotificationListFragmentSubcomponent create(
          NotificationListFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new NotificationListFragmentSubcomponentImpl(arg0);
      }
    }

    private final class NotificationListFragmentSubcomponentImpl implements NotificationFragmentProvider_NotificationListFragment.NotificationListFragmentSubcomponent {
      private NotificationListFragmentSubcomponentImpl(NotificationListFragment arg0) {

      }

      @Override
      public void inject(NotificationListFragment arg0) {
        injectNotificationListFragment(arg0);}

      private NotificationListFragment injectNotificationListFragment(
          NotificationListFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, NotificationActivitySubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
        NotificationListFragment_MembersInjector.injectNotificationService(instance, DaggerApplicationComponent.this.providesNotificationServiceProvider.get());
        return instance;
      }
    }
  }

  private final class CreateGistActivitySubcomponentFactory implements ActivityBuilder_CreateGistActivity.CreateGistActivitySubcomponent.Factory {
    @Override
    public ActivityBuilder_CreateGistActivity.CreateGistActivitySubcomponent create(
        CreateGistActivity arg0) {
      Preconditions.checkNotNull(arg0);
      return new CreateGistActivitySubcomponentImpl(arg0);
    }
  }

  private final class CreateGistActivitySubcomponentImpl implements ActivityBuilder_CreateGistActivity.CreateGistActivitySubcomponent {
    private CreateGistActivitySubcomponentImpl(CreateGistActivity arg0) {

    }

    @Override
    public void inject(CreateGistActivity arg0) {
      injectCreateGistActivity(arg0);}

    private CreateGistActivity injectCreateGistActivity(CreateGistActivity instance) {
      DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, DaggerApplicationComponent.this.getDispatchingAndroidInjectorOfObject());
      return instance;
    }
  }

  private final class IssueBrowseActivitySubcomponentFactory implements ActivityBuilder_IssueBrowseActivity.IssueBrowseActivitySubcomponent.Factory {
    @Override
    public ActivityBuilder_IssueBrowseActivity.IssueBrowseActivitySubcomponent create(
        IssueBrowseActivity arg0) {
      Preconditions.checkNotNull(arg0);
      return new IssueBrowseActivitySubcomponentImpl(arg0);
    }
  }

  private final class IssueBrowseActivitySubcomponentImpl implements ActivityBuilder_IssueBrowseActivity.IssueBrowseActivitySubcomponent {
    private Provider<IssueBrowseFragmentProvider_IssuesFragment.IssuesFragmentSubcomponent.Factory> issuesFragmentSubcomponentFactoryProvider;

    private IssueBrowseActivitySubcomponentImpl(IssueBrowseActivity arg0) {

      initialize(arg0);
    }

    private Map<Class<?>, Provider<AndroidInjector.Factory<?>>> getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(
        ) {
      return MapBuilder.<Class<?>, Provider<AndroidInjector.Factory<?>>>newMapBuilder(34).put(MainActivity.class, (Provider) DaggerApplicationComponent.this.mainActivitySubcomponentFactoryProvider).put(RepositoryViewActivity.class, (Provider) DaggerApplicationComponent.this.repositoryViewActivitySubcomponentFactoryProvider).put(IssuesViewActivity.class, (Provider) DaggerApplicationComponent.this.issuesViewActivitySubcomponentFactoryProvider).put(NotificationActivity.class, (Provider) DaggerApplicationComponent.this.notificationActivitySubcomponentFactoryProvider).put(CreateGistActivity.class, (Provider) DaggerApplicationComponent.this.createGistActivitySubcomponentFactoryProvider).put(IssueBrowseActivity.class, (Provider) DaggerApplicationComponent.this.issueBrowseActivitySubcomponentFactoryProvider).put(EditIssuesFilterActivity.class, (Provider) DaggerApplicationComponent.this.editIssuesFilterActivitySubcomponentFactoryProvider).put(EditIssueActivity.class, (Provider) DaggerApplicationComponent.this.editIssueActivitySubcomponentFactoryProvider).put(SearchActivity.class, (Provider) DaggerApplicationComponent.this.searchActivitySubcomponentFactoryProvider).put(FiltersViewActivity.class, (Provider) DaggerApplicationComponent.this.filtersViewActivitySubcomponentFactoryProvider).put(GistsViewActivity.class, (Provider) DaggerApplicationComponent.this.gistsViewActivitySubcomponentFactoryProvider).put(GistFilesViewActivity.class, (Provider) DaggerApplicationComponent.this.gistFilesViewActivitySubcomponentFactoryProvider).put(CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider2).put(com.github.pockethub.android.ui.commit.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider3).put(EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider2).put(RepositoryContributorsActivity.class, (Provider) DaggerApplicationComponent.this.repositoryContributorsActivitySubcomponentFactoryProvider).put(UserViewActivity.class, (Provider) DaggerApplicationComponent.this.userViewActivitySubcomponentFactoryProvider).put(LoginActivity.class, (Provider) DaggerApplicationComponent.this.loginActivitySubcomponentFactoryProvider).put(UriLauncherActivity.class, (Provider) DaggerApplicationComponent.this.uriLauncherActivitySubcomponentFactoryProvider).put(IssueSearchActivity.class, (Provider) DaggerApplicationComponent.this.issueSearchActivitySubcomponentFactoryProvider).put(CommitCompareViewActivity.class, (Provider) DaggerApplicationComponent.this.commitCompareViewActivitySubcomponentFactoryProvider).put(CommitViewActivity.class, (Provider) DaggerApplicationComponent.this.commitViewActivitySubcomponentFactoryProvider).put(CommitFileViewActivity.class, (Provider) DaggerApplicationComponent.this.commitFileViewActivitySubcomponentFactoryProvider).put(BranchFileViewActivity.class, (Provider) DaggerApplicationComponent.this.branchFileViewActivitySubcomponentFactoryProvider).put(LoginWebViewActivity.class, (Provider) DaggerApplicationComponent.this.loginWebViewActivitySubcomponentFactoryProvider).put(SyncAdapterService.class, (Provider) DaggerApplicationComponent.this.syncAdapterServiceSubcomponentFactoryProvider).put(LabelsDialogFragment.class, (Provider) DaggerApplicationComponent.this.labelsDialogFragmentSubcomponentFactoryProvider).put(AssigneeDialogFragment.class, (Provider) DaggerApplicationComponent.this.assigneeDialogFragmentSubcomponentFactoryProvider).put(MilestoneDialogFragment.class, (Provider) DaggerApplicationComponent.this.milestoneDialogFragmentSubcomponentFactoryProvider).put(RefDialogFragment.class, (Provider) DaggerApplicationComponent.this.refDialogFragmentSubcomponentFactoryProvider).put(ConfirmDialogFragment.class, (Provider) DaggerApplicationComponent.this.confirmDialogFragmentSubcomponentFactoryProvider).put(IssuesFragment.class, (Provider) issuesFragmentSubcomponentFactoryProvider).build();}

    private DispatchingAndroidInjector<Object> getDispatchingAndroidInjectorOfObject() {
      return DispatchingAndroidInjector_Factory.newInstance(getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(), Collections.<String, Provider<AndroidInjector.Factory<?>>>emptyMap());}

    @SuppressWarnings("unchecked")
    private void initialize(final IssueBrowseActivity arg0) {
      this.issuesFragmentSubcomponentFactoryProvider = new Provider<IssueBrowseFragmentProvider_IssuesFragment.IssuesFragmentSubcomponent.Factory>() {
        @Override
        public IssueBrowseFragmentProvider_IssuesFragment.IssuesFragmentSubcomponent.Factory get() {
          return new IBFP_IF_IssuesFragmentSubcomponentFactory();}
      };
    }

    @Override
    public void inject(IssueBrowseActivity arg0) {
      injectIssueBrowseActivity(arg0);}

    private IssueBrowseActivity injectIssueBrowseActivity(IssueBrowseActivity instance) {
      DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, getDispatchingAndroidInjectorOfObject());
      return instance;
    }

    private final class IBFP_IF_IssuesFragmentSubcomponentFactory implements IssueBrowseFragmentProvider_IssuesFragment.IssuesFragmentSubcomponent.Factory {
      @Override
      public IssueBrowseFragmentProvider_IssuesFragment.IssuesFragmentSubcomponent create(
          IssuesFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new IBFP_IF_IssuesFragmentSubcomponentImpl(arg0);
      }
    }

    private final class IBFP_IF_IssuesFragmentSubcomponentImpl implements IssueBrowseFragmentProvider_IssuesFragment.IssuesFragmentSubcomponent {
      private IBFP_IF_IssuesFragmentSubcomponentImpl(IssuesFragment arg0) {

      }

      private DatabaseCache getDatabaseCache() {
        return injectDatabaseCache(DatabaseCache_Factory.newInstance());}

      private OrganizationRepositoriesFactory getOrganizationRepositoriesFactory() {
        return injectOrganizationRepositoriesFactory(OrganizationRepositoriesFactory_Factory.newInstance());}

      private Organizations getOrganizations() {
        return new Organizations(DaggerApplicationComponent.this.provideApplicationContextProvider.get());}

      private AccountDataManager getAccountDataManager() {
        return injectAccountDataManager(AccountDataManager_Factory.newInstance());}

      @Override
      public void inject(IssuesFragment arg0) {
        injectIssuesFragment(arg0);}

      private DatabaseCache injectDatabaseCache(DatabaseCache instance) {
        DatabaseCache_MembersInjector.injectDatabase(instance, DaggerApplicationComponent.this.provideDatabaseProvider.get());
        return instance;
      }

      private OrganizationRepositoriesFactory injectOrganizationRepositoriesFactory(
          OrganizationRepositoriesFactory instance) {
        OrganizationRepositoriesFactory_MembersInjector.injectContext(instance, DaggerApplicationComponent.this.provideApplicationContextProvider.get());
        OrganizationRepositoriesFactory_MembersInjector.injectAccountProvider(instance, DaggerApplicationComponent.this.accountProvider);
        return instance;
      }

      private AccountDataManager injectAccountDataManager(AccountDataManager instance) {
        AccountDataManager_MembersInjector.injectContext(instance, DaggerApplicationComponent.this.provideApplicationContextProvider.get());
        AccountDataManager_MembersInjector.injectDbCache(instance, getDatabaseCache());
        AccountDataManager_MembersInjector.injectAllRepos(instance, getOrganizationRepositoriesFactory());
        AccountDataManager_MembersInjector.injectUserAndOrgsResource(instance, getOrganizations());
        AccountDataManager_MembersInjector.injectRoot(instance, DaggerApplicationComponent.this.getNamedFile());
        return instance;
      }

      private IssuesFragment injectIssuesFragment(IssuesFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, IssueBrowseActivitySubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
        IssuesFragment_MembersInjector.injectCache(instance, getAccountDataManager());
        IssuesFragment_MembersInjector.injectStore(instance, DaggerApplicationComponent.this.issueStoreProvider.get());
        IssuesFragment_MembersInjector.injectAvatars(instance, DaggerApplicationComponent.this.avatarLoaderProvider.get());
        IssuesFragment_MembersInjector.injectService(instance, DaggerApplicationComponent.this.providesIssueServiceProvider.get());
        return instance;
      }
    }
  }

  private final class EditIssuesFilterActivitySubcomponentFactory implements ActivityBuilder_EditIssuesFilterActivity.EditIssuesFilterActivitySubcomponent.Factory {
    @Override
    public ActivityBuilder_EditIssuesFilterActivity.EditIssuesFilterActivitySubcomponent create(
        EditIssuesFilterActivity arg0) {
      Preconditions.checkNotNull(arg0);
      return new EditIssuesFilterActivitySubcomponentImpl(arg0);
    }
  }

  private final class EditIssuesFilterActivitySubcomponentImpl implements ActivityBuilder_EditIssuesFilterActivity.EditIssuesFilterActivitySubcomponent {
    private EditIssuesFilterActivitySubcomponentImpl(EditIssuesFilterActivity arg0) {

    }

    @Override
    public void inject(EditIssuesFilterActivity arg0) {
      injectEditIssuesFilterActivity(arg0);}

    private EditIssuesFilterActivity injectEditIssuesFilterActivity(
        EditIssuesFilterActivity instance) {
      DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, DaggerApplicationComponent.this.getDispatchingAndroidInjectorOfObject());
      EditIssuesFilterActivity_MembersInjector.injectAvatars(instance, DaggerApplicationComponent.this.avatarLoaderProvider.get());
      return instance;
    }
  }

  private final class EditIssueActivitySubcomponentFactory implements ActivityBuilder_EditIssueActivity.EditIssueActivitySubcomponent.Factory {
    @Override
    public ActivityBuilder_EditIssueActivity.EditIssueActivitySubcomponent create(
        EditIssueActivity arg0) {
      Preconditions.checkNotNull(arg0);
      return new EditIssueActivitySubcomponentImpl(arg0);
    }
  }

  private final class EditIssueActivitySubcomponentImpl implements ActivityBuilder_EditIssueActivity.EditIssueActivitySubcomponent {
    private EditIssueActivitySubcomponentImpl(EditIssueActivity arg0) {

    }

    @Override
    public void inject(EditIssueActivity arg0) {
      injectEditIssueActivity(arg0);}

    private EditIssueActivity injectEditIssueActivity(EditIssueActivity instance) {
      DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, DaggerApplicationComponent.this.getDispatchingAndroidInjectorOfObject());
      EditIssueActivity_MembersInjector.injectAvatars(instance, DaggerApplicationComponent.this.avatarLoaderProvider.get());
      return instance;
    }
  }

  private final class SearchActivitySubcomponentFactory implements ActivityBuilder_SearchActivity.SearchActivitySubcomponent.Factory {
    @Override
    public ActivityBuilder_SearchActivity.SearchActivitySubcomponent create(SearchActivity arg0) {
      Preconditions.checkNotNull(arg0);
      return new SearchActivitySubcomponentImpl(arg0);
    }
  }

  private final class SearchActivitySubcomponentImpl implements ActivityBuilder_SearchActivity.SearchActivitySubcomponent {
    private Provider<SearchActivityFragmentProvider_SearchRepositoryListFragment.SearchRepositoryListFragmentSubcomponent.Factory> searchRepositoryListFragmentSubcomponentFactoryProvider;

    private Provider<SearchActivityFragmentProvider_SearchUserListFragment.SearchUserListFragmentSubcomponent.Factory> searchUserListFragmentSubcomponentFactoryProvider;

    private SearchActivitySubcomponentImpl(SearchActivity arg0) {

      initialize(arg0);
    }

    private Map<Class<?>, Provider<AndroidInjector.Factory<?>>> getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(
        ) {
      return MapBuilder.<Class<?>, Provider<AndroidInjector.Factory<?>>>newMapBuilder(35).put(MainActivity.class, (Provider) DaggerApplicationComponent.this.mainActivitySubcomponentFactoryProvider).put(RepositoryViewActivity.class, (Provider) DaggerApplicationComponent.this.repositoryViewActivitySubcomponentFactoryProvider).put(IssuesViewActivity.class, (Provider) DaggerApplicationComponent.this.issuesViewActivitySubcomponentFactoryProvider).put(NotificationActivity.class, (Provider) DaggerApplicationComponent.this.notificationActivitySubcomponentFactoryProvider).put(CreateGistActivity.class, (Provider) DaggerApplicationComponent.this.createGistActivitySubcomponentFactoryProvider).put(IssueBrowseActivity.class, (Provider) DaggerApplicationComponent.this.issueBrowseActivitySubcomponentFactoryProvider).put(EditIssuesFilterActivity.class, (Provider) DaggerApplicationComponent.this.editIssuesFilterActivitySubcomponentFactoryProvider).put(EditIssueActivity.class, (Provider) DaggerApplicationComponent.this.editIssueActivitySubcomponentFactoryProvider).put(SearchActivity.class, (Provider) DaggerApplicationComponent.this.searchActivitySubcomponentFactoryProvider).put(FiltersViewActivity.class, (Provider) DaggerApplicationComponent.this.filtersViewActivitySubcomponentFactoryProvider).put(GistsViewActivity.class, (Provider) DaggerApplicationComponent.this.gistsViewActivitySubcomponentFactoryProvider).put(GistFilesViewActivity.class, (Provider) DaggerApplicationComponent.this.gistFilesViewActivitySubcomponentFactoryProvider).put(CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider2).put(com.github.pockethub.android.ui.commit.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider3).put(EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider2).put(RepositoryContributorsActivity.class, (Provider) DaggerApplicationComponent.this.repositoryContributorsActivitySubcomponentFactoryProvider).put(UserViewActivity.class, (Provider) DaggerApplicationComponent.this.userViewActivitySubcomponentFactoryProvider).put(LoginActivity.class, (Provider) DaggerApplicationComponent.this.loginActivitySubcomponentFactoryProvider).put(UriLauncherActivity.class, (Provider) DaggerApplicationComponent.this.uriLauncherActivitySubcomponentFactoryProvider).put(IssueSearchActivity.class, (Provider) DaggerApplicationComponent.this.issueSearchActivitySubcomponentFactoryProvider).put(CommitCompareViewActivity.class, (Provider) DaggerApplicationComponent.this.commitCompareViewActivitySubcomponentFactoryProvider).put(CommitViewActivity.class, (Provider) DaggerApplicationComponent.this.commitViewActivitySubcomponentFactoryProvider).put(CommitFileViewActivity.class, (Provider) DaggerApplicationComponent.this.commitFileViewActivitySubcomponentFactoryProvider).put(BranchFileViewActivity.class, (Provider) DaggerApplicationComponent.this.branchFileViewActivitySubcomponentFactoryProvider).put(LoginWebViewActivity.class, (Provider) DaggerApplicationComponent.this.loginWebViewActivitySubcomponentFactoryProvider).put(SyncAdapterService.class, (Provider) DaggerApplicationComponent.this.syncAdapterServiceSubcomponentFactoryProvider).put(LabelsDialogFragment.class, (Provider) DaggerApplicationComponent.this.labelsDialogFragmentSubcomponentFactoryProvider).put(AssigneeDialogFragment.class, (Provider) DaggerApplicationComponent.this.assigneeDialogFragmentSubcomponentFactoryProvider).put(MilestoneDialogFragment.class, (Provider) DaggerApplicationComponent.this.milestoneDialogFragmentSubcomponentFactoryProvider).put(RefDialogFragment.class, (Provider) DaggerApplicationComponent.this.refDialogFragmentSubcomponentFactoryProvider).put(ConfirmDialogFragment.class, (Provider) DaggerApplicationComponent.this.confirmDialogFragmentSubcomponentFactoryProvider).put(SearchRepositoryListFragment.class, (Provider) searchRepositoryListFragmentSubcomponentFactoryProvider).put(SearchUserListFragment.class, (Provider) searchUserListFragmentSubcomponentFactoryProvider).build();}

    private DispatchingAndroidInjector<Object> getDispatchingAndroidInjectorOfObject() {
      return DispatchingAndroidInjector_Factory.newInstance(getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(), Collections.<String, Provider<AndroidInjector.Factory<?>>>emptyMap());}

    @SuppressWarnings("unchecked")
    private void initialize(final SearchActivity arg0) {
      this.searchRepositoryListFragmentSubcomponentFactoryProvider = new Provider<SearchActivityFragmentProvider_SearchRepositoryListFragment.SearchRepositoryListFragmentSubcomponent.Factory>() {
        @Override
        public SearchActivityFragmentProvider_SearchRepositoryListFragment.SearchRepositoryListFragmentSubcomponent.Factory get(
            ) {
          return new SearchRepositoryListFragmentSubcomponentFactory();}
      };
      this.searchUserListFragmentSubcomponentFactoryProvider = new Provider<SearchActivityFragmentProvider_SearchUserListFragment.SearchUserListFragmentSubcomponent.Factory>() {
        @Override
        public SearchActivityFragmentProvider_SearchUserListFragment.SearchUserListFragmentSubcomponent.Factory get(
            ) {
          return new SearchUserListFragmentSubcomponentFactory();}
      };
    }

    @Override
    public void inject(SearchActivity arg0) {
      injectSearchActivity(arg0);}

    private SearchActivity injectSearchActivity(SearchActivity instance) {
      DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, getDispatchingAndroidInjectorOfObject());
      return instance;
    }

    private final class SearchRepositoryListFragmentSubcomponentFactory implements SearchActivityFragmentProvider_SearchRepositoryListFragment.SearchRepositoryListFragmentSubcomponent.Factory {
      @Override
      public SearchActivityFragmentProvider_SearchRepositoryListFragment.SearchRepositoryListFragmentSubcomponent create(
          SearchRepositoryListFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new SearchRepositoryListFragmentSubcomponentImpl(arg0);
      }
    }

    private final class SearchRepositoryListFragmentSubcomponentImpl implements SearchActivityFragmentProvider_SearchRepositoryListFragment.SearchRepositoryListFragmentSubcomponent {
      private SearchRepositoryListFragmentSubcomponentImpl(SearchRepositoryListFragment arg0) {

      }

      @Override
      public void inject(SearchRepositoryListFragment arg0) {
        injectSearchRepositoryListFragment(arg0);}

      private SearchRepositoryListFragment injectSearchRepositoryListFragment(
          SearchRepositoryListFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, SearchActivitySubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
        SearchRepositoryListFragment_MembersInjector.injectService(instance, DaggerApplicationComponent.this.providesSearchServiceProvider.get());
        return instance;
      }
    }

    private final class SearchUserListFragmentSubcomponentFactory implements SearchActivityFragmentProvider_SearchUserListFragment.SearchUserListFragmentSubcomponent.Factory {
      @Override
      public SearchActivityFragmentProvider_SearchUserListFragment.SearchUserListFragmentSubcomponent create(
          SearchUserListFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new SearchUserListFragmentSubcomponentImpl(arg0);
      }
    }

    private final class SearchUserListFragmentSubcomponentImpl implements SearchActivityFragmentProvider_SearchUserListFragment.SearchUserListFragmentSubcomponent {
      private SearchUserListFragmentSubcomponentImpl(SearchUserListFragment arg0) {

      }

      @Override
      public void inject(SearchUserListFragment arg0) {
        injectSearchUserListFragment(arg0);}

      private SearchUserListFragment injectSearchUserListFragment(SearchUserListFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, SearchActivitySubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
        SearchUserListFragment_MembersInjector.injectService(instance, DaggerApplicationComponent.this.providesSearchServiceProvider.get());
        SearchUserListFragment_MembersInjector.injectAvatars(instance, DaggerApplicationComponent.this.avatarLoaderProvider.get());
        return instance;
      }
    }
  }

  private final class FiltersViewActivitySubcomponentFactory implements ActivityBuilder_FiltersViewActivity.FiltersViewActivitySubcomponent.Factory {
    @Override
    public ActivityBuilder_FiltersViewActivity.FiltersViewActivitySubcomponent create(
        FiltersViewActivity arg0) {
      Preconditions.checkNotNull(arg0);
      return new FiltersViewActivitySubcomponentImpl(arg0);
    }
  }

  private final class FiltersViewActivitySubcomponentImpl implements ActivityBuilder_FiltersViewActivity.FiltersViewActivitySubcomponent {
    private Provider<FiltersViewFragmentProvider_FilterListFragment.FilterListFragmentSubcomponent.Factory> filterListFragmentSubcomponentFactoryProvider;

    private FiltersViewActivitySubcomponentImpl(FiltersViewActivity arg0) {

      initialize(arg0);
    }

    private Map<Class<?>, Provider<AndroidInjector.Factory<?>>> getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(
        ) {
      return MapBuilder.<Class<?>, Provider<AndroidInjector.Factory<?>>>newMapBuilder(34).put(MainActivity.class, (Provider) DaggerApplicationComponent.this.mainActivitySubcomponentFactoryProvider).put(RepositoryViewActivity.class, (Provider) DaggerApplicationComponent.this.repositoryViewActivitySubcomponentFactoryProvider).put(IssuesViewActivity.class, (Provider) DaggerApplicationComponent.this.issuesViewActivitySubcomponentFactoryProvider).put(NotificationActivity.class, (Provider) DaggerApplicationComponent.this.notificationActivitySubcomponentFactoryProvider).put(CreateGistActivity.class, (Provider) DaggerApplicationComponent.this.createGistActivitySubcomponentFactoryProvider).put(IssueBrowseActivity.class, (Provider) DaggerApplicationComponent.this.issueBrowseActivitySubcomponentFactoryProvider).put(EditIssuesFilterActivity.class, (Provider) DaggerApplicationComponent.this.editIssuesFilterActivitySubcomponentFactoryProvider).put(EditIssueActivity.class, (Provider) DaggerApplicationComponent.this.editIssueActivitySubcomponentFactoryProvider).put(SearchActivity.class, (Provider) DaggerApplicationComponent.this.searchActivitySubcomponentFactoryProvider).put(FiltersViewActivity.class, (Provider) DaggerApplicationComponent.this.filtersViewActivitySubcomponentFactoryProvider).put(GistsViewActivity.class, (Provider) DaggerApplicationComponent.this.gistsViewActivitySubcomponentFactoryProvider).put(GistFilesViewActivity.class, (Provider) DaggerApplicationComponent.this.gistFilesViewActivitySubcomponentFactoryProvider).put(CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider2).put(com.github.pockethub.android.ui.commit.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider3).put(EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider2).put(RepositoryContributorsActivity.class, (Provider) DaggerApplicationComponent.this.repositoryContributorsActivitySubcomponentFactoryProvider).put(UserViewActivity.class, (Provider) DaggerApplicationComponent.this.userViewActivitySubcomponentFactoryProvider).put(LoginActivity.class, (Provider) DaggerApplicationComponent.this.loginActivitySubcomponentFactoryProvider).put(UriLauncherActivity.class, (Provider) DaggerApplicationComponent.this.uriLauncherActivitySubcomponentFactoryProvider).put(IssueSearchActivity.class, (Provider) DaggerApplicationComponent.this.issueSearchActivitySubcomponentFactoryProvider).put(CommitCompareViewActivity.class, (Provider) DaggerApplicationComponent.this.commitCompareViewActivitySubcomponentFactoryProvider).put(CommitViewActivity.class, (Provider) DaggerApplicationComponent.this.commitViewActivitySubcomponentFactoryProvider).put(CommitFileViewActivity.class, (Provider) DaggerApplicationComponent.this.commitFileViewActivitySubcomponentFactoryProvider).put(BranchFileViewActivity.class, (Provider) DaggerApplicationComponent.this.branchFileViewActivitySubcomponentFactoryProvider).put(LoginWebViewActivity.class, (Provider) DaggerApplicationComponent.this.loginWebViewActivitySubcomponentFactoryProvider).put(SyncAdapterService.class, (Provider) DaggerApplicationComponent.this.syncAdapterServiceSubcomponentFactoryProvider).put(LabelsDialogFragment.class, (Provider) DaggerApplicationComponent.this.labelsDialogFragmentSubcomponentFactoryProvider).put(AssigneeDialogFragment.class, (Provider) DaggerApplicationComponent.this.assigneeDialogFragmentSubcomponentFactoryProvider).put(MilestoneDialogFragment.class, (Provider) DaggerApplicationComponent.this.milestoneDialogFragmentSubcomponentFactoryProvider).put(RefDialogFragment.class, (Provider) DaggerApplicationComponent.this.refDialogFragmentSubcomponentFactoryProvider).put(ConfirmDialogFragment.class, (Provider) DaggerApplicationComponent.this.confirmDialogFragmentSubcomponentFactoryProvider).put(FilterListFragment.class, (Provider) filterListFragmentSubcomponentFactoryProvider).build();}

    private DispatchingAndroidInjector<Object> getDispatchingAndroidInjectorOfObject() {
      return DispatchingAndroidInjector_Factory.newInstance(getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(), Collections.<String, Provider<AndroidInjector.Factory<?>>>emptyMap());}

    private DatabaseCache getDatabaseCache() {
      return injectDatabaseCache(DatabaseCache_Factory.newInstance());}

    private OrganizationRepositoriesFactory getOrganizationRepositoriesFactory() {
      return injectOrganizationRepositoriesFactory(OrganizationRepositoriesFactory_Factory.newInstance());}

    private Organizations getOrganizations() {
      return new Organizations(DaggerApplicationComponent.this.provideApplicationContextProvider.get());}

    private AccountDataManager getAccountDataManager() {
      return injectAccountDataManager(AccountDataManager_Factory.newInstance());}

    @SuppressWarnings("unchecked")
    private void initialize(final FiltersViewActivity arg0) {
      this.filterListFragmentSubcomponentFactoryProvider = new Provider<FiltersViewFragmentProvider_FilterListFragment.FilterListFragmentSubcomponent.Factory>() {
        @Override
        public FiltersViewFragmentProvider_FilterListFragment.FilterListFragmentSubcomponent.Factory get(
            ) {
          return new FVFP_FLF_FilterListFragmentSubcomponentFactory();}
      };
    }

    @Override
    public void inject(FiltersViewActivity arg0) {
      injectFiltersViewActivity(arg0);}

    private DatabaseCache injectDatabaseCache(DatabaseCache instance) {
      DatabaseCache_MembersInjector.injectDatabase(instance, DaggerApplicationComponent.this.provideDatabaseProvider.get());
      return instance;
    }

    private OrganizationRepositoriesFactory injectOrganizationRepositoriesFactory(
        OrganizationRepositoriesFactory instance) {
      OrganizationRepositoriesFactory_MembersInjector.injectContext(instance, DaggerApplicationComponent.this.provideApplicationContextProvider.get());
      OrganizationRepositoriesFactory_MembersInjector.injectAccountProvider(instance, DaggerApplicationComponent.this.accountProvider);
      return instance;
    }

    private AccountDataManager injectAccountDataManager(AccountDataManager instance) {
      AccountDataManager_MembersInjector.injectContext(instance, DaggerApplicationComponent.this.provideApplicationContextProvider.get());
      AccountDataManager_MembersInjector.injectDbCache(instance, getDatabaseCache());
      AccountDataManager_MembersInjector.injectAllRepos(instance, getOrganizationRepositoriesFactory());
      AccountDataManager_MembersInjector.injectUserAndOrgsResource(instance, getOrganizations());
      AccountDataManager_MembersInjector.injectRoot(instance, DaggerApplicationComponent.this.getNamedFile());
      return instance;
    }

    private FiltersViewActivity injectFiltersViewActivity(FiltersViewActivity instance) {
      DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, getDispatchingAndroidInjectorOfObject());
      FiltersViewActivity_MembersInjector.injectCache(instance, getAccountDataManager());
      return instance;
    }

    private final class FVFP_FLF_FilterListFragmentSubcomponentFactory implements FiltersViewFragmentProvider_FilterListFragment.FilterListFragmentSubcomponent.Factory {
      @Override
      public FiltersViewFragmentProvider_FilterListFragment.FilterListFragmentSubcomponent create(
          FilterListFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new FVFP_FLF_FilterListFragmentSubcomponentImpl(arg0);
      }
    }

    private final class FVFP_FLF_FilterListFragmentSubcomponentImpl implements FiltersViewFragmentProvider_FilterListFragment.FilterListFragmentSubcomponent {
      private FVFP_FLF_FilterListFragmentSubcomponentImpl(FilterListFragment arg0) {

      }

      @Override
      public void inject(FilterListFragment arg0) {
        injectFilterListFragment(arg0);}

      private FilterListFragment injectFilterListFragment(FilterListFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, FiltersViewActivitySubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
        FilterListFragment_MembersInjector.injectCache(instance, FiltersViewActivitySubcomponentImpl.this.getAccountDataManager());
        FilterListFragment_MembersInjector.injectAvatars(instance, DaggerApplicationComponent.this.avatarLoaderProvider.get());
        return instance;
      }
    }
  }

  private final class GistsViewActivitySubcomponentFactory implements ActivityBuilder_GistsViewActivity.GistsViewActivitySubcomponent.Factory {
    @Override
    public ActivityBuilder_GistsViewActivity.GistsViewActivitySubcomponent create(
        GistsViewActivity arg0) {
      Preconditions.checkNotNull(arg0);
      return new GistsViewActivitySubcomponentImpl(arg0);
    }
  }

  private final class GistsViewActivitySubcomponentImpl implements ActivityBuilder_GistsViewActivity.GistsViewActivitySubcomponent {
    private Provider<GistsViewFragmentProvider_GistFragment.GistFragmentSubcomponent.Factory> gistFragmentSubcomponentFactoryProvider;

    private GistsViewActivitySubcomponentImpl(GistsViewActivity arg0) {

      initialize(arg0);
    }

    private Map<Class<?>, Provider<AndroidInjector.Factory<?>>> getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(
        ) {
      return MapBuilder.<Class<?>, Provider<AndroidInjector.Factory<?>>>newMapBuilder(34).put(MainActivity.class, (Provider) DaggerApplicationComponent.this.mainActivitySubcomponentFactoryProvider).put(RepositoryViewActivity.class, (Provider) DaggerApplicationComponent.this.repositoryViewActivitySubcomponentFactoryProvider).put(IssuesViewActivity.class, (Provider) DaggerApplicationComponent.this.issuesViewActivitySubcomponentFactoryProvider).put(NotificationActivity.class, (Provider) DaggerApplicationComponent.this.notificationActivitySubcomponentFactoryProvider).put(CreateGistActivity.class, (Provider) DaggerApplicationComponent.this.createGistActivitySubcomponentFactoryProvider).put(IssueBrowseActivity.class, (Provider) DaggerApplicationComponent.this.issueBrowseActivitySubcomponentFactoryProvider).put(EditIssuesFilterActivity.class, (Provider) DaggerApplicationComponent.this.editIssuesFilterActivitySubcomponentFactoryProvider).put(EditIssueActivity.class, (Provider) DaggerApplicationComponent.this.editIssueActivitySubcomponentFactoryProvider).put(SearchActivity.class, (Provider) DaggerApplicationComponent.this.searchActivitySubcomponentFactoryProvider).put(FiltersViewActivity.class, (Provider) DaggerApplicationComponent.this.filtersViewActivitySubcomponentFactoryProvider).put(GistsViewActivity.class, (Provider) DaggerApplicationComponent.this.gistsViewActivitySubcomponentFactoryProvider).put(GistFilesViewActivity.class, (Provider) DaggerApplicationComponent.this.gistFilesViewActivitySubcomponentFactoryProvider).put(CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider2).put(com.github.pockethub.android.ui.commit.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider3).put(EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider2).put(RepositoryContributorsActivity.class, (Provider) DaggerApplicationComponent.this.repositoryContributorsActivitySubcomponentFactoryProvider).put(UserViewActivity.class, (Provider) DaggerApplicationComponent.this.userViewActivitySubcomponentFactoryProvider).put(LoginActivity.class, (Provider) DaggerApplicationComponent.this.loginActivitySubcomponentFactoryProvider).put(UriLauncherActivity.class, (Provider) DaggerApplicationComponent.this.uriLauncherActivitySubcomponentFactoryProvider).put(IssueSearchActivity.class, (Provider) DaggerApplicationComponent.this.issueSearchActivitySubcomponentFactoryProvider).put(CommitCompareViewActivity.class, (Provider) DaggerApplicationComponent.this.commitCompareViewActivitySubcomponentFactoryProvider).put(CommitViewActivity.class, (Provider) DaggerApplicationComponent.this.commitViewActivitySubcomponentFactoryProvider).put(CommitFileViewActivity.class, (Provider) DaggerApplicationComponent.this.commitFileViewActivitySubcomponentFactoryProvider).put(BranchFileViewActivity.class, (Provider) DaggerApplicationComponent.this.branchFileViewActivitySubcomponentFactoryProvider).put(LoginWebViewActivity.class, (Provider) DaggerApplicationComponent.this.loginWebViewActivitySubcomponentFactoryProvider).put(SyncAdapterService.class, (Provider) DaggerApplicationComponent.this.syncAdapterServiceSubcomponentFactoryProvider).put(LabelsDialogFragment.class, (Provider) DaggerApplicationComponent.this.labelsDialogFragmentSubcomponentFactoryProvider).put(AssigneeDialogFragment.class, (Provider) DaggerApplicationComponent.this.assigneeDialogFragmentSubcomponentFactoryProvider).put(MilestoneDialogFragment.class, (Provider) DaggerApplicationComponent.this.milestoneDialogFragmentSubcomponentFactoryProvider).put(RefDialogFragment.class, (Provider) DaggerApplicationComponent.this.refDialogFragmentSubcomponentFactoryProvider).put(ConfirmDialogFragment.class, (Provider) DaggerApplicationComponent.this.confirmDialogFragmentSubcomponentFactoryProvider).put(GistFragment.class, (Provider) gistFragmentSubcomponentFactoryProvider).build();}

    private DispatchingAndroidInjector<Object> getDispatchingAndroidInjectorOfObject() {
      return DispatchingAndroidInjector_Factory.newInstance(getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(), Collections.<String, Provider<AndroidInjector.Factory<?>>>emptyMap());}

    @SuppressWarnings("unchecked")
    private void initialize(final GistsViewActivity arg0) {
      this.gistFragmentSubcomponentFactoryProvider = new Provider<GistsViewFragmentProvider_GistFragment.GistFragmentSubcomponent.Factory>() {
        @Override
        public GistsViewFragmentProvider_GistFragment.GistFragmentSubcomponent.Factory get() {
          return new GistFragmentSubcomponentFactory();}
      };
    }

    @Override
    public void inject(GistsViewActivity arg0) {
      injectGistsViewActivity(arg0);}

    private GistsViewActivity injectGistsViewActivity(GistsViewActivity instance) {
      DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, getDispatchingAndroidInjectorOfObject());
      GistsViewActivity_MembersInjector.injectStore(instance, DaggerApplicationComponent.this.gistStoreProvider.get());
      return instance;
    }

    private final class GistFragmentSubcomponentFactory implements GistsViewFragmentProvider_GistFragment.GistFragmentSubcomponent.Factory {
      @Override
      public GistsViewFragmentProvider_GistFragment.GistFragmentSubcomponent create(
          GistFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new GistFragmentSubcomponentImpl(arg0);
      }
    }

    private final class GistFragmentSubcomponentImpl implements GistsViewFragmentProvider_GistFragment.GistFragmentSubcomponent {
      private Provider<HttpImageGetter> httpImageGetterProvider;

      private GistFragmentSubcomponentImpl(GistFragment arg0) {

        initialize(arg0);
      }

      private RefreshGistTaskFactory getRefreshGistTaskFactory() {
        return new RefreshGistTaskFactory(DaggerApplicationComponent.this.gistStoreProvider, httpImageGetterProvider);}

      private HttpImageGetter getHttpImageGetter() {
        return new HttpImageGetter(DaggerApplicationComponent.this.provideApplicationContextProvider.get());}

      @SuppressWarnings("unchecked")
      private void initialize(final GistFragment arg0) {
        this.httpImageGetterProvider = HttpImageGetter_Factory.create(DaggerApplicationComponent.this.provideApplicationContextProvider);
      }

      @Override
      public void inject(GistFragment arg0) {
        injectGistFragment(arg0);}

      private GistFragment injectGistFragment(GistFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, GistsViewActivitySubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
        GistFragment_MembersInjector.injectStore(instance, DaggerApplicationComponent.this.gistStoreProvider.get());
        GistFragment_MembersInjector.injectRefreshGistTaskFactory(instance, getRefreshGistTaskFactory());
        GistFragment_MembersInjector.injectImageGetter(instance, getHttpImageGetter());
        GistFragment_MembersInjector.injectAvatars(instance, DaggerApplicationComponent.this.avatarLoaderProvider.get());
        return instance;
      }
    }
  }

  private final class GistFilesViewActivitySubcomponentFactory implements ActivityBuilder_GistFilesViewActivity.GistFilesViewActivitySubcomponent.Factory {
    @Override
    public ActivityBuilder_GistFilesViewActivity.GistFilesViewActivitySubcomponent create(
        GistFilesViewActivity arg0) {
      Preconditions.checkNotNull(arg0);
      return new GistFilesViewActivitySubcomponentImpl(arg0);
    }
  }

  private final class GistFilesViewActivitySubcomponentImpl implements ActivityBuilder_GistFilesViewActivity.GistFilesViewActivitySubcomponent {
    private Provider<GistFilesViewFragmentProvider_GistFileFragment.GistFileFragmentSubcomponent.Factory> gistFileFragmentSubcomponentFactoryProvider;

    private Provider<HttpImageGetter> httpImageGetterProvider;

    private GistFilesViewActivitySubcomponentImpl(GistFilesViewActivity arg0) {

      initialize(arg0);
    }

    private Map<Class<?>, Provider<AndroidInjector.Factory<?>>> getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(
        ) {
      return MapBuilder.<Class<?>, Provider<AndroidInjector.Factory<?>>>newMapBuilder(34).put(MainActivity.class, (Provider) DaggerApplicationComponent.this.mainActivitySubcomponentFactoryProvider).put(RepositoryViewActivity.class, (Provider) DaggerApplicationComponent.this.repositoryViewActivitySubcomponentFactoryProvider).put(IssuesViewActivity.class, (Provider) DaggerApplicationComponent.this.issuesViewActivitySubcomponentFactoryProvider).put(NotificationActivity.class, (Provider) DaggerApplicationComponent.this.notificationActivitySubcomponentFactoryProvider).put(CreateGistActivity.class, (Provider) DaggerApplicationComponent.this.createGistActivitySubcomponentFactoryProvider).put(IssueBrowseActivity.class, (Provider) DaggerApplicationComponent.this.issueBrowseActivitySubcomponentFactoryProvider).put(EditIssuesFilterActivity.class, (Provider) DaggerApplicationComponent.this.editIssuesFilterActivitySubcomponentFactoryProvider).put(EditIssueActivity.class, (Provider) DaggerApplicationComponent.this.editIssueActivitySubcomponentFactoryProvider).put(SearchActivity.class, (Provider) DaggerApplicationComponent.this.searchActivitySubcomponentFactoryProvider).put(FiltersViewActivity.class, (Provider) DaggerApplicationComponent.this.filtersViewActivitySubcomponentFactoryProvider).put(GistsViewActivity.class, (Provider) DaggerApplicationComponent.this.gistsViewActivitySubcomponentFactoryProvider).put(GistFilesViewActivity.class, (Provider) DaggerApplicationComponent.this.gistFilesViewActivitySubcomponentFactoryProvider).put(CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider2).put(com.github.pockethub.android.ui.commit.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider3).put(EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider2).put(RepositoryContributorsActivity.class, (Provider) DaggerApplicationComponent.this.repositoryContributorsActivitySubcomponentFactoryProvider).put(UserViewActivity.class, (Provider) DaggerApplicationComponent.this.userViewActivitySubcomponentFactoryProvider).put(LoginActivity.class, (Provider) DaggerApplicationComponent.this.loginActivitySubcomponentFactoryProvider).put(UriLauncherActivity.class, (Provider) DaggerApplicationComponent.this.uriLauncherActivitySubcomponentFactoryProvider).put(IssueSearchActivity.class, (Provider) DaggerApplicationComponent.this.issueSearchActivitySubcomponentFactoryProvider).put(CommitCompareViewActivity.class, (Provider) DaggerApplicationComponent.this.commitCompareViewActivitySubcomponentFactoryProvider).put(CommitViewActivity.class, (Provider) DaggerApplicationComponent.this.commitViewActivitySubcomponentFactoryProvider).put(CommitFileViewActivity.class, (Provider) DaggerApplicationComponent.this.commitFileViewActivitySubcomponentFactoryProvider).put(BranchFileViewActivity.class, (Provider) DaggerApplicationComponent.this.branchFileViewActivitySubcomponentFactoryProvider).put(LoginWebViewActivity.class, (Provider) DaggerApplicationComponent.this.loginWebViewActivitySubcomponentFactoryProvider).put(SyncAdapterService.class, (Provider) DaggerApplicationComponent.this.syncAdapterServiceSubcomponentFactoryProvider).put(LabelsDialogFragment.class, (Provider) DaggerApplicationComponent.this.labelsDialogFragmentSubcomponentFactoryProvider).put(AssigneeDialogFragment.class, (Provider) DaggerApplicationComponent.this.assigneeDialogFragmentSubcomponentFactoryProvider).put(MilestoneDialogFragment.class, (Provider) DaggerApplicationComponent.this.milestoneDialogFragmentSubcomponentFactoryProvider).put(RefDialogFragment.class, (Provider) DaggerApplicationComponent.this.refDialogFragmentSubcomponentFactoryProvider).put(ConfirmDialogFragment.class, (Provider) DaggerApplicationComponent.this.confirmDialogFragmentSubcomponentFactoryProvider).put(GistFileFragment.class, (Provider) gistFileFragmentSubcomponentFactoryProvider).build();}

    private DispatchingAndroidInjector<Object> getDispatchingAndroidInjectorOfObject() {
      return DispatchingAndroidInjector_Factory.newInstance(getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(), Collections.<String, Provider<AndroidInjector.Factory<?>>>emptyMap());}

    private RefreshGistTaskFactory getRefreshGistTaskFactory() {
      return new RefreshGistTaskFactory(DaggerApplicationComponent.this.gistStoreProvider, httpImageGetterProvider);}

    @SuppressWarnings("unchecked")
    private void initialize(final GistFilesViewActivity arg0) {
      this.gistFileFragmentSubcomponentFactoryProvider = new Provider<GistFilesViewFragmentProvider_GistFileFragment.GistFileFragmentSubcomponent.Factory>() {
        @Override
        public GistFilesViewFragmentProvider_GistFileFragment.GistFileFragmentSubcomponent.Factory get(
            ) {
          return new GistFileFragmentSubcomponentFactory();}
      };
      this.httpImageGetterProvider = HttpImageGetter_Factory.create(DaggerApplicationComponent.this.provideApplicationContextProvider);
    }

    @Override
    public void inject(GistFilesViewActivity arg0) {
      injectGistFilesViewActivity(arg0);}

    private GistFilesViewActivity injectGistFilesViewActivity(GistFilesViewActivity instance) {
      DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, getDispatchingAndroidInjectorOfObject());
      GistFilesViewActivity_MembersInjector.injectStore(instance, DaggerApplicationComponent.this.gistStoreProvider.get());
      GistFilesViewActivity_MembersInjector.injectRefreshGistTaskFactory(instance, getRefreshGistTaskFactory());
      return instance;
    }

    private final class GistFileFragmentSubcomponentFactory implements GistFilesViewFragmentProvider_GistFileFragment.GistFileFragmentSubcomponent.Factory {
      @Override
      public GistFilesViewFragmentProvider_GistFileFragment.GistFileFragmentSubcomponent create(
          GistFileFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new GistFileFragmentSubcomponentImpl(arg0);
      }
    }

    private final class GistFileFragmentSubcomponentImpl implements GistFilesViewFragmentProvider_GistFileFragment.GistFileFragmentSubcomponent {
      private GistFileFragmentSubcomponentImpl(GistFileFragment arg0) {

      }

      @Override
      public void inject(GistFileFragment arg0) {
        injectGistFileFragment(arg0);}

      private GistFileFragment injectGistFileFragment(GistFileFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, GistFilesViewActivitySubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
        GistFileFragment_MembersInjector.injectStore(instance, DaggerApplicationComponent.this.gistStoreProvider.get());
        return instance;
      }
    }
  }

  private final class AB_CGCA_CreateCommentActivitySubcomponentFactory implements ActivityBuilder_CreateGistCommentActivity.CreateCommentActivitySubcomponent.Factory {
    @Override
    public ActivityBuilder_CreateGistCommentActivity.CreateCommentActivitySubcomponent create(
        CreateCommentActivity arg0) {
      Preconditions.checkNotNull(arg0);
      return new AB_CGCA_CreateCommentActivitySubcomponentImpl(arg0);
    }
  }

  private final class AB_CGCA_CreateCommentActivitySubcomponentImpl implements ActivityBuilder_CreateGistCommentActivity.CreateCommentActivitySubcomponent {
    private Provider<CreateCommentFragmentProvider_RawCommentFragment.RawCommentFragmentSubcomponent.Factory> rawCommentFragmentSubcomponentFactoryProvider;

    private Provider<CreateCommentFragmentProvider_RenderedCommentFragment.RenderedCommentFragmentSubcomponent.Factory> renderedCommentFragmentSubcomponentFactoryProvider;

    private AB_CGCA_CreateCommentActivitySubcomponentImpl(CreateCommentActivity arg0) {

      initialize(arg0);
    }

    private Map<Class<?>, Provider<AndroidInjector.Factory<?>>> getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(
        ) {
      return MapBuilder.<Class<?>, Provider<AndroidInjector.Factory<?>>>newMapBuilder(35).put(MainActivity.class, (Provider) DaggerApplicationComponent.this.mainActivitySubcomponentFactoryProvider).put(RepositoryViewActivity.class, (Provider) DaggerApplicationComponent.this.repositoryViewActivitySubcomponentFactoryProvider).put(IssuesViewActivity.class, (Provider) DaggerApplicationComponent.this.issuesViewActivitySubcomponentFactoryProvider).put(NotificationActivity.class, (Provider) DaggerApplicationComponent.this.notificationActivitySubcomponentFactoryProvider).put(CreateGistActivity.class, (Provider) DaggerApplicationComponent.this.createGistActivitySubcomponentFactoryProvider).put(IssueBrowseActivity.class, (Provider) DaggerApplicationComponent.this.issueBrowseActivitySubcomponentFactoryProvider).put(EditIssuesFilterActivity.class, (Provider) DaggerApplicationComponent.this.editIssuesFilterActivitySubcomponentFactoryProvider).put(EditIssueActivity.class, (Provider) DaggerApplicationComponent.this.editIssueActivitySubcomponentFactoryProvider).put(SearchActivity.class, (Provider) DaggerApplicationComponent.this.searchActivitySubcomponentFactoryProvider).put(FiltersViewActivity.class, (Provider) DaggerApplicationComponent.this.filtersViewActivitySubcomponentFactoryProvider).put(GistsViewActivity.class, (Provider) DaggerApplicationComponent.this.gistsViewActivitySubcomponentFactoryProvider).put(GistFilesViewActivity.class, (Provider) DaggerApplicationComponent.this.gistFilesViewActivitySubcomponentFactoryProvider).put(CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider2).put(com.github.pockethub.android.ui.commit.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider3).put(EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider2).put(RepositoryContributorsActivity.class, (Provider) DaggerApplicationComponent.this.repositoryContributorsActivitySubcomponentFactoryProvider).put(UserViewActivity.class, (Provider) DaggerApplicationComponent.this.userViewActivitySubcomponentFactoryProvider).put(LoginActivity.class, (Provider) DaggerApplicationComponent.this.loginActivitySubcomponentFactoryProvider).put(UriLauncherActivity.class, (Provider) DaggerApplicationComponent.this.uriLauncherActivitySubcomponentFactoryProvider).put(IssueSearchActivity.class, (Provider) DaggerApplicationComponent.this.issueSearchActivitySubcomponentFactoryProvider).put(CommitCompareViewActivity.class, (Provider) DaggerApplicationComponent.this.commitCompareViewActivitySubcomponentFactoryProvider).put(CommitViewActivity.class, (Provider) DaggerApplicationComponent.this.commitViewActivitySubcomponentFactoryProvider).put(CommitFileViewActivity.class, (Provider) DaggerApplicationComponent.this.commitFileViewActivitySubcomponentFactoryProvider).put(BranchFileViewActivity.class, (Provider) DaggerApplicationComponent.this.branchFileViewActivitySubcomponentFactoryProvider).put(LoginWebViewActivity.class, (Provider) DaggerApplicationComponent.this.loginWebViewActivitySubcomponentFactoryProvider).put(SyncAdapterService.class, (Provider) DaggerApplicationComponent.this.syncAdapterServiceSubcomponentFactoryProvider).put(LabelsDialogFragment.class, (Provider) DaggerApplicationComponent.this.labelsDialogFragmentSubcomponentFactoryProvider).put(AssigneeDialogFragment.class, (Provider) DaggerApplicationComponent.this.assigneeDialogFragmentSubcomponentFactoryProvider).put(MilestoneDialogFragment.class, (Provider) DaggerApplicationComponent.this.milestoneDialogFragmentSubcomponentFactoryProvider).put(RefDialogFragment.class, (Provider) DaggerApplicationComponent.this.refDialogFragmentSubcomponentFactoryProvider).put(ConfirmDialogFragment.class, (Provider) DaggerApplicationComponent.this.confirmDialogFragmentSubcomponentFactoryProvider).put(RawCommentFragment.class, (Provider) rawCommentFragmentSubcomponentFactoryProvider).put(RenderedCommentFragment.class, (Provider) renderedCommentFragmentSubcomponentFactoryProvider).build();}

    private DispatchingAndroidInjector<Object> getDispatchingAndroidInjectorOfObject() {
      return DispatchingAndroidInjector_Factory.newInstance(getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(), Collections.<String, Provider<AndroidInjector.Factory<?>>>emptyMap());}

    @SuppressWarnings("unchecked")
    private void initialize(final CreateCommentActivity arg0) {
      this.rawCommentFragmentSubcomponentFactoryProvider = new Provider<CreateCommentFragmentProvider_RawCommentFragment.RawCommentFragmentSubcomponent.Factory>() {
        @Override
        public CreateCommentFragmentProvider_RawCommentFragment.RawCommentFragmentSubcomponent.Factory get(
            ) {
          return new RawCommentFragmentSubcomponentFactory();}
      };
      this.renderedCommentFragmentSubcomponentFactoryProvider = new Provider<CreateCommentFragmentProvider_RenderedCommentFragment.RenderedCommentFragmentSubcomponent.Factory>() {
        @Override
        public CreateCommentFragmentProvider_RenderedCommentFragment.RenderedCommentFragmentSubcomponent.Factory get(
            ) {
          return new RenderedCommentFragmentSubcomponentFactory();}
      };
    }

    @Override
    public void inject(CreateCommentActivity arg0) {
      injectCreateCommentActivity(arg0);}

    private CreateCommentActivity injectCreateCommentActivity(CreateCommentActivity instance) {
      DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, getDispatchingAndroidInjectorOfObject());
      return instance;
    }

    private final class RawCommentFragmentSubcomponentFactory implements CreateCommentFragmentProvider_RawCommentFragment.RawCommentFragmentSubcomponent.Factory {
      @Override
      public CreateCommentFragmentProvider_RawCommentFragment.RawCommentFragmentSubcomponent create(
          RawCommentFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new RawCommentFragmentSubcomponentImpl(arg0);
      }
    }

    private final class RawCommentFragmentSubcomponentImpl implements CreateCommentFragmentProvider_RawCommentFragment.RawCommentFragmentSubcomponent {
      private RawCommentFragmentSubcomponentImpl(RawCommentFragment arg0) {

      }

      @Override
      public void inject(RawCommentFragment arg0) {
        injectRawCommentFragment(arg0);}

      private RawCommentFragment injectRawCommentFragment(RawCommentFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, AB_CGCA_CreateCommentActivitySubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
        return instance;
      }
    }

    private final class RenderedCommentFragmentSubcomponentFactory implements CreateCommentFragmentProvider_RenderedCommentFragment.RenderedCommentFragmentSubcomponent.Factory {
      @Override
      public CreateCommentFragmentProvider_RenderedCommentFragment.RenderedCommentFragmentSubcomponent create(
          RenderedCommentFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new RenderedCommentFragmentSubcomponentImpl(arg0);
      }
    }

    private final class RenderedCommentFragmentSubcomponentImpl implements CreateCommentFragmentProvider_RenderedCommentFragment.RenderedCommentFragmentSubcomponent {
      private RenderedCommentFragmentSubcomponentImpl(RenderedCommentFragment arg0) {

      }

      private HttpImageGetter getHttpImageGetter() {
        return new HttpImageGetter(DaggerApplicationComponent.this.provideApplicationContextProvider.get());}

      @Override
      public void inject(RenderedCommentFragment arg0) {
        injectRenderedCommentFragment(arg0);}

      private RenderedCommentFragment injectRenderedCommentFragment(
          RenderedCommentFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, AB_CGCA_CreateCommentActivitySubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
        RenderedCommentFragment_MembersInjector.injectImageGetter(instance, getHttpImageGetter());
        return instance;
      }
    }
  }

  private final class AB_CICA_CreateCommentActivitySubcomponentFactory implements ActivityBuilder_CreateIssueCommentActivity.CreateCommentActivitySubcomponent.Factory {
    @Override
    public ActivityBuilder_CreateIssueCommentActivity.CreateCommentActivitySubcomponent create(
        com.github.pockethub.android.ui.issue.CreateCommentActivity arg0) {
      Preconditions.checkNotNull(arg0);
      return new AB_CICA_CreateCommentActivitySubcomponentImpl(arg0);
    }
  }

  private final class AB_CICA_CreateCommentActivitySubcomponentImpl implements ActivityBuilder_CreateIssueCommentActivity.CreateCommentActivitySubcomponent {
    private Provider<CreateCommentFragmentProvider_RawCommentFragment.RawCommentFragmentSubcomponent.Factory> rawCommentFragmentSubcomponentFactoryProvider;

    private Provider<CreateCommentFragmentProvider_RenderedCommentFragment.RenderedCommentFragmentSubcomponent.Factory> renderedCommentFragmentSubcomponentFactoryProvider;

    private AB_CICA_CreateCommentActivitySubcomponentImpl(
        com.github.pockethub.android.ui.issue.CreateCommentActivity arg0) {

      initialize(arg0);
    }

    private Map<Class<?>, Provider<AndroidInjector.Factory<?>>> getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(
        ) {
      return MapBuilder.<Class<?>, Provider<AndroidInjector.Factory<?>>>newMapBuilder(35).put(MainActivity.class, (Provider) DaggerApplicationComponent.this.mainActivitySubcomponentFactoryProvider).put(RepositoryViewActivity.class, (Provider) DaggerApplicationComponent.this.repositoryViewActivitySubcomponentFactoryProvider).put(IssuesViewActivity.class, (Provider) DaggerApplicationComponent.this.issuesViewActivitySubcomponentFactoryProvider).put(NotificationActivity.class, (Provider) DaggerApplicationComponent.this.notificationActivitySubcomponentFactoryProvider).put(CreateGistActivity.class, (Provider) DaggerApplicationComponent.this.createGistActivitySubcomponentFactoryProvider).put(IssueBrowseActivity.class, (Provider) DaggerApplicationComponent.this.issueBrowseActivitySubcomponentFactoryProvider).put(EditIssuesFilterActivity.class, (Provider) DaggerApplicationComponent.this.editIssuesFilterActivitySubcomponentFactoryProvider).put(EditIssueActivity.class, (Provider) DaggerApplicationComponent.this.editIssueActivitySubcomponentFactoryProvider).put(SearchActivity.class, (Provider) DaggerApplicationComponent.this.searchActivitySubcomponentFactoryProvider).put(FiltersViewActivity.class, (Provider) DaggerApplicationComponent.this.filtersViewActivitySubcomponentFactoryProvider).put(GistsViewActivity.class, (Provider) DaggerApplicationComponent.this.gistsViewActivitySubcomponentFactoryProvider).put(GistFilesViewActivity.class, (Provider) DaggerApplicationComponent.this.gistFilesViewActivitySubcomponentFactoryProvider).put(CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider2).put(com.github.pockethub.android.ui.commit.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider3).put(EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider2).put(RepositoryContributorsActivity.class, (Provider) DaggerApplicationComponent.this.repositoryContributorsActivitySubcomponentFactoryProvider).put(UserViewActivity.class, (Provider) DaggerApplicationComponent.this.userViewActivitySubcomponentFactoryProvider).put(LoginActivity.class, (Provider) DaggerApplicationComponent.this.loginActivitySubcomponentFactoryProvider).put(UriLauncherActivity.class, (Provider) DaggerApplicationComponent.this.uriLauncherActivitySubcomponentFactoryProvider).put(IssueSearchActivity.class, (Provider) DaggerApplicationComponent.this.issueSearchActivitySubcomponentFactoryProvider).put(CommitCompareViewActivity.class, (Provider) DaggerApplicationComponent.this.commitCompareViewActivitySubcomponentFactoryProvider).put(CommitViewActivity.class, (Provider) DaggerApplicationComponent.this.commitViewActivitySubcomponentFactoryProvider).put(CommitFileViewActivity.class, (Provider) DaggerApplicationComponent.this.commitFileViewActivitySubcomponentFactoryProvider).put(BranchFileViewActivity.class, (Provider) DaggerApplicationComponent.this.branchFileViewActivitySubcomponentFactoryProvider).put(LoginWebViewActivity.class, (Provider) DaggerApplicationComponent.this.loginWebViewActivitySubcomponentFactoryProvider).put(SyncAdapterService.class, (Provider) DaggerApplicationComponent.this.syncAdapterServiceSubcomponentFactoryProvider).put(LabelsDialogFragment.class, (Provider) DaggerApplicationComponent.this.labelsDialogFragmentSubcomponentFactoryProvider).put(AssigneeDialogFragment.class, (Provider) DaggerApplicationComponent.this.assigneeDialogFragmentSubcomponentFactoryProvider).put(MilestoneDialogFragment.class, (Provider) DaggerApplicationComponent.this.milestoneDialogFragmentSubcomponentFactoryProvider).put(RefDialogFragment.class, (Provider) DaggerApplicationComponent.this.refDialogFragmentSubcomponentFactoryProvider).put(ConfirmDialogFragment.class, (Provider) DaggerApplicationComponent.this.confirmDialogFragmentSubcomponentFactoryProvider).put(RawCommentFragment.class, (Provider) rawCommentFragmentSubcomponentFactoryProvider).put(RenderedCommentFragment.class, (Provider) renderedCommentFragmentSubcomponentFactoryProvider).build();}

    private DispatchingAndroidInjector<Object> getDispatchingAndroidInjectorOfObject() {
      return DispatchingAndroidInjector_Factory.newInstance(getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(), Collections.<String, Provider<AndroidInjector.Factory<?>>>emptyMap());}

    @SuppressWarnings("unchecked")
    private void initialize(
        final com.github.pockethub.android.ui.issue.CreateCommentActivity arg0) {
      this.rawCommentFragmentSubcomponentFactoryProvider = new Provider<CreateCommentFragmentProvider_RawCommentFragment.RawCommentFragmentSubcomponent.Factory>() {
        @Override
        public CreateCommentFragmentProvider_RawCommentFragment.RawCommentFragmentSubcomponent.Factory get(
            ) {
          return new RawCommentFragmentSubcomponentFactory();}
      };
      this.renderedCommentFragmentSubcomponentFactoryProvider = new Provider<CreateCommentFragmentProvider_RenderedCommentFragment.RenderedCommentFragmentSubcomponent.Factory>() {
        @Override
        public CreateCommentFragmentProvider_RenderedCommentFragment.RenderedCommentFragmentSubcomponent.Factory get(
            ) {
          return new RenderedCommentFragmentSubcomponentFactory();}
      };
    }

    @Override
    public void inject(com.github.pockethub.android.ui.issue.CreateCommentActivity arg0) {
      injectCreateCommentActivity(arg0);}

    private com.github.pockethub.android.ui.issue.CreateCommentActivity injectCreateCommentActivity(
        com.github.pockethub.android.ui.issue.CreateCommentActivity instance) {
      DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, getDispatchingAndroidInjectorOfObject());
      return instance;
    }

    private final class RawCommentFragmentSubcomponentFactory implements CreateCommentFragmentProvider_RawCommentFragment.RawCommentFragmentSubcomponent.Factory {
      @Override
      public CreateCommentFragmentProvider_RawCommentFragment.RawCommentFragmentSubcomponent create(
          RawCommentFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new RawCommentFragmentSubcomponentImpl(arg0);
      }
    }

    private final class RawCommentFragmentSubcomponentImpl implements CreateCommentFragmentProvider_RawCommentFragment.RawCommentFragmentSubcomponent {
      private RawCommentFragmentSubcomponentImpl(RawCommentFragment arg0) {

      }

      @Override
      public void inject(RawCommentFragment arg0) {
        injectRawCommentFragment(arg0);}

      private RawCommentFragment injectRawCommentFragment(RawCommentFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, AB_CICA_CreateCommentActivitySubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
        return instance;
      }
    }

    private final class RenderedCommentFragmentSubcomponentFactory implements CreateCommentFragmentProvider_RenderedCommentFragment.RenderedCommentFragmentSubcomponent.Factory {
      @Override
      public CreateCommentFragmentProvider_RenderedCommentFragment.RenderedCommentFragmentSubcomponent create(
          RenderedCommentFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new RenderedCommentFragmentSubcomponentImpl(arg0);
      }
    }

    private final class RenderedCommentFragmentSubcomponentImpl implements CreateCommentFragmentProvider_RenderedCommentFragment.RenderedCommentFragmentSubcomponent {
      private RenderedCommentFragmentSubcomponentImpl(RenderedCommentFragment arg0) {

      }

      private HttpImageGetter getHttpImageGetter() {
        return new HttpImageGetter(DaggerApplicationComponent.this.provideApplicationContextProvider.get());}

      @Override
      public void inject(RenderedCommentFragment arg0) {
        injectRenderedCommentFragment(arg0);}

      private RenderedCommentFragment injectRenderedCommentFragment(
          RenderedCommentFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, AB_CICA_CreateCommentActivitySubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
        RenderedCommentFragment_MembersInjector.injectImageGetter(instance, getHttpImageGetter());
        return instance;
      }
    }
  }

  private final class AB_CCCA_CreateCommentActivitySubcomponentFactory implements ActivityBuilder_CreateCommitCommentActivity.CreateCommentActivitySubcomponent.Factory {
    @Override
    public ActivityBuilder_CreateCommitCommentActivity.CreateCommentActivitySubcomponent create(
        com.github.pockethub.android.ui.commit.CreateCommentActivity arg0) {
      Preconditions.checkNotNull(arg0);
      return new AB_CCCA_CreateCommentActivitySubcomponentImpl(arg0);
    }
  }

  private final class AB_CCCA_CreateCommentActivitySubcomponentImpl implements ActivityBuilder_CreateCommitCommentActivity.CreateCommentActivitySubcomponent {
    private Provider<CreateCommentFragmentProvider_RawCommentFragment.RawCommentFragmentSubcomponent.Factory> rawCommentFragmentSubcomponentFactoryProvider;

    private Provider<CreateCommentFragmentProvider_RenderedCommentFragment.RenderedCommentFragmentSubcomponent.Factory> renderedCommentFragmentSubcomponentFactoryProvider;

    private AB_CCCA_CreateCommentActivitySubcomponentImpl(
        com.github.pockethub.android.ui.commit.CreateCommentActivity arg0) {

      initialize(arg0);
    }

    private Map<Class<?>, Provider<AndroidInjector.Factory<?>>> getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(
        ) {
      return MapBuilder.<Class<?>, Provider<AndroidInjector.Factory<?>>>newMapBuilder(35).put(MainActivity.class, (Provider) DaggerApplicationComponent.this.mainActivitySubcomponentFactoryProvider).put(RepositoryViewActivity.class, (Provider) DaggerApplicationComponent.this.repositoryViewActivitySubcomponentFactoryProvider).put(IssuesViewActivity.class, (Provider) DaggerApplicationComponent.this.issuesViewActivitySubcomponentFactoryProvider).put(NotificationActivity.class, (Provider) DaggerApplicationComponent.this.notificationActivitySubcomponentFactoryProvider).put(CreateGistActivity.class, (Provider) DaggerApplicationComponent.this.createGistActivitySubcomponentFactoryProvider).put(IssueBrowseActivity.class, (Provider) DaggerApplicationComponent.this.issueBrowseActivitySubcomponentFactoryProvider).put(EditIssuesFilterActivity.class, (Provider) DaggerApplicationComponent.this.editIssuesFilterActivitySubcomponentFactoryProvider).put(EditIssueActivity.class, (Provider) DaggerApplicationComponent.this.editIssueActivitySubcomponentFactoryProvider).put(SearchActivity.class, (Provider) DaggerApplicationComponent.this.searchActivitySubcomponentFactoryProvider).put(FiltersViewActivity.class, (Provider) DaggerApplicationComponent.this.filtersViewActivitySubcomponentFactoryProvider).put(GistsViewActivity.class, (Provider) DaggerApplicationComponent.this.gistsViewActivitySubcomponentFactoryProvider).put(GistFilesViewActivity.class, (Provider) DaggerApplicationComponent.this.gistFilesViewActivitySubcomponentFactoryProvider).put(CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider2).put(com.github.pockethub.android.ui.commit.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider3).put(EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider2).put(RepositoryContributorsActivity.class, (Provider) DaggerApplicationComponent.this.repositoryContributorsActivitySubcomponentFactoryProvider).put(UserViewActivity.class, (Provider) DaggerApplicationComponent.this.userViewActivitySubcomponentFactoryProvider).put(LoginActivity.class, (Provider) DaggerApplicationComponent.this.loginActivitySubcomponentFactoryProvider).put(UriLauncherActivity.class, (Provider) DaggerApplicationComponent.this.uriLauncherActivitySubcomponentFactoryProvider).put(IssueSearchActivity.class, (Provider) DaggerApplicationComponent.this.issueSearchActivitySubcomponentFactoryProvider).put(CommitCompareViewActivity.class, (Provider) DaggerApplicationComponent.this.commitCompareViewActivitySubcomponentFactoryProvider).put(CommitViewActivity.class, (Provider) DaggerApplicationComponent.this.commitViewActivitySubcomponentFactoryProvider).put(CommitFileViewActivity.class, (Provider) DaggerApplicationComponent.this.commitFileViewActivitySubcomponentFactoryProvider).put(BranchFileViewActivity.class, (Provider) DaggerApplicationComponent.this.branchFileViewActivitySubcomponentFactoryProvider).put(LoginWebViewActivity.class, (Provider) DaggerApplicationComponent.this.loginWebViewActivitySubcomponentFactoryProvider).put(SyncAdapterService.class, (Provider) DaggerApplicationComponent.this.syncAdapterServiceSubcomponentFactoryProvider).put(LabelsDialogFragment.class, (Provider) DaggerApplicationComponent.this.labelsDialogFragmentSubcomponentFactoryProvider).put(AssigneeDialogFragment.class, (Provider) DaggerApplicationComponent.this.assigneeDialogFragmentSubcomponentFactoryProvider).put(MilestoneDialogFragment.class, (Provider) DaggerApplicationComponent.this.milestoneDialogFragmentSubcomponentFactoryProvider).put(RefDialogFragment.class, (Provider) DaggerApplicationComponent.this.refDialogFragmentSubcomponentFactoryProvider).put(ConfirmDialogFragment.class, (Provider) DaggerApplicationComponent.this.confirmDialogFragmentSubcomponentFactoryProvider).put(RawCommentFragment.class, (Provider) rawCommentFragmentSubcomponentFactoryProvider).put(RenderedCommentFragment.class, (Provider) renderedCommentFragmentSubcomponentFactoryProvider).build();}

    private DispatchingAndroidInjector<Object> getDispatchingAndroidInjectorOfObject() {
      return DispatchingAndroidInjector_Factory.newInstance(getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(), Collections.<String, Provider<AndroidInjector.Factory<?>>>emptyMap());}

    @SuppressWarnings("unchecked")
    private void initialize(
        final com.github.pockethub.android.ui.commit.CreateCommentActivity arg0) {
      this.rawCommentFragmentSubcomponentFactoryProvider = new Provider<CreateCommentFragmentProvider_RawCommentFragment.RawCommentFragmentSubcomponent.Factory>() {
        @Override
        public CreateCommentFragmentProvider_RawCommentFragment.RawCommentFragmentSubcomponent.Factory get(
            ) {
          return new RawCommentFragmentSubcomponentFactory();}
      };
      this.renderedCommentFragmentSubcomponentFactoryProvider = new Provider<CreateCommentFragmentProvider_RenderedCommentFragment.RenderedCommentFragmentSubcomponent.Factory>() {
        @Override
        public CreateCommentFragmentProvider_RenderedCommentFragment.RenderedCommentFragmentSubcomponent.Factory get(
            ) {
          return new RenderedCommentFragmentSubcomponentFactory();}
      };
    }

    @Override
    public void inject(com.github.pockethub.android.ui.commit.CreateCommentActivity arg0) {
      injectCreateCommentActivity(arg0);}

    private com.github.pockethub.android.ui.commit.CreateCommentActivity injectCreateCommentActivity(
        com.github.pockethub.android.ui.commit.CreateCommentActivity instance) {
      DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, getDispatchingAndroidInjectorOfObject());
      return instance;
    }

    private final class RawCommentFragmentSubcomponentFactory implements CreateCommentFragmentProvider_RawCommentFragment.RawCommentFragmentSubcomponent.Factory {
      @Override
      public CreateCommentFragmentProvider_RawCommentFragment.RawCommentFragmentSubcomponent create(
          RawCommentFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new RawCommentFragmentSubcomponentImpl(arg0);
      }
    }

    private final class RawCommentFragmentSubcomponentImpl implements CreateCommentFragmentProvider_RawCommentFragment.RawCommentFragmentSubcomponent {
      private RawCommentFragmentSubcomponentImpl(RawCommentFragment arg0) {

      }

      @Override
      public void inject(RawCommentFragment arg0) {
        injectRawCommentFragment(arg0);}

      private RawCommentFragment injectRawCommentFragment(RawCommentFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, AB_CCCA_CreateCommentActivitySubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
        return instance;
      }
    }

    private final class RenderedCommentFragmentSubcomponentFactory implements CreateCommentFragmentProvider_RenderedCommentFragment.RenderedCommentFragmentSubcomponent.Factory {
      @Override
      public CreateCommentFragmentProvider_RenderedCommentFragment.RenderedCommentFragmentSubcomponent create(
          RenderedCommentFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new RenderedCommentFragmentSubcomponentImpl(arg0);
      }
    }

    private final class RenderedCommentFragmentSubcomponentImpl implements CreateCommentFragmentProvider_RenderedCommentFragment.RenderedCommentFragmentSubcomponent {
      private RenderedCommentFragmentSubcomponentImpl(RenderedCommentFragment arg0) {

      }

      private HttpImageGetter getHttpImageGetter() {
        return new HttpImageGetter(DaggerApplicationComponent.this.provideApplicationContextProvider.get());}

      @Override
      public void inject(RenderedCommentFragment arg0) {
        injectRenderedCommentFragment(arg0);}

      private RenderedCommentFragment injectRenderedCommentFragment(
          RenderedCommentFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, AB_CCCA_CreateCommentActivitySubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
        RenderedCommentFragment_MembersInjector.injectImageGetter(instance, getHttpImageGetter());
        return instance;
      }
    }
  }

  private final class AB_EGCA_EditCommentActivitySubcomponentFactory implements ActivityBuilder_EditGistCommentActivity.EditCommentActivitySubcomponent.Factory {
    @Override
    public ActivityBuilder_EditGistCommentActivity.EditCommentActivitySubcomponent create(
        EditCommentActivity arg0) {
      Preconditions.checkNotNull(arg0);
      return new AB_EGCA_EditCommentActivitySubcomponentImpl(arg0);
    }
  }

  private final class AB_EGCA_EditCommentActivitySubcomponentImpl implements ActivityBuilder_EditGistCommentActivity.EditCommentActivitySubcomponent {
    private Provider<CreateCommentFragmentProvider_RawCommentFragment.RawCommentFragmentSubcomponent.Factory> rawCommentFragmentSubcomponentFactoryProvider;

    private Provider<CreateCommentFragmentProvider_RenderedCommentFragment.RenderedCommentFragmentSubcomponent.Factory> renderedCommentFragmentSubcomponentFactoryProvider;

    private AB_EGCA_EditCommentActivitySubcomponentImpl(EditCommentActivity arg0) {

      initialize(arg0);
    }

    private Map<Class<?>, Provider<AndroidInjector.Factory<?>>> getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(
        ) {
      return MapBuilder.<Class<?>, Provider<AndroidInjector.Factory<?>>>newMapBuilder(35).put(MainActivity.class, (Provider) DaggerApplicationComponent.this.mainActivitySubcomponentFactoryProvider).put(RepositoryViewActivity.class, (Provider) DaggerApplicationComponent.this.repositoryViewActivitySubcomponentFactoryProvider).put(IssuesViewActivity.class, (Provider) DaggerApplicationComponent.this.issuesViewActivitySubcomponentFactoryProvider).put(NotificationActivity.class, (Provider) DaggerApplicationComponent.this.notificationActivitySubcomponentFactoryProvider).put(CreateGistActivity.class, (Provider) DaggerApplicationComponent.this.createGistActivitySubcomponentFactoryProvider).put(IssueBrowseActivity.class, (Provider) DaggerApplicationComponent.this.issueBrowseActivitySubcomponentFactoryProvider).put(EditIssuesFilterActivity.class, (Provider) DaggerApplicationComponent.this.editIssuesFilterActivitySubcomponentFactoryProvider).put(EditIssueActivity.class, (Provider) DaggerApplicationComponent.this.editIssueActivitySubcomponentFactoryProvider).put(SearchActivity.class, (Provider) DaggerApplicationComponent.this.searchActivitySubcomponentFactoryProvider).put(FiltersViewActivity.class, (Provider) DaggerApplicationComponent.this.filtersViewActivitySubcomponentFactoryProvider).put(GistsViewActivity.class, (Provider) DaggerApplicationComponent.this.gistsViewActivitySubcomponentFactoryProvider).put(GistFilesViewActivity.class, (Provider) DaggerApplicationComponent.this.gistFilesViewActivitySubcomponentFactoryProvider).put(CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider2).put(com.github.pockethub.android.ui.commit.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider3).put(EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider2).put(RepositoryContributorsActivity.class, (Provider) DaggerApplicationComponent.this.repositoryContributorsActivitySubcomponentFactoryProvider).put(UserViewActivity.class, (Provider) DaggerApplicationComponent.this.userViewActivitySubcomponentFactoryProvider).put(LoginActivity.class, (Provider) DaggerApplicationComponent.this.loginActivitySubcomponentFactoryProvider).put(UriLauncherActivity.class, (Provider) DaggerApplicationComponent.this.uriLauncherActivitySubcomponentFactoryProvider).put(IssueSearchActivity.class, (Provider) DaggerApplicationComponent.this.issueSearchActivitySubcomponentFactoryProvider).put(CommitCompareViewActivity.class, (Provider) DaggerApplicationComponent.this.commitCompareViewActivitySubcomponentFactoryProvider).put(CommitViewActivity.class, (Provider) DaggerApplicationComponent.this.commitViewActivitySubcomponentFactoryProvider).put(CommitFileViewActivity.class, (Provider) DaggerApplicationComponent.this.commitFileViewActivitySubcomponentFactoryProvider).put(BranchFileViewActivity.class, (Provider) DaggerApplicationComponent.this.branchFileViewActivitySubcomponentFactoryProvider).put(LoginWebViewActivity.class, (Provider) DaggerApplicationComponent.this.loginWebViewActivitySubcomponentFactoryProvider).put(SyncAdapterService.class, (Provider) DaggerApplicationComponent.this.syncAdapterServiceSubcomponentFactoryProvider).put(LabelsDialogFragment.class, (Provider) DaggerApplicationComponent.this.labelsDialogFragmentSubcomponentFactoryProvider).put(AssigneeDialogFragment.class, (Provider) DaggerApplicationComponent.this.assigneeDialogFragmentSubcomponentFactoryProvider).put(MilestoneDialogFragment.class, (Provider) DaggerApplicationComponent.this.milestoneDialogFragmentSubcomponentFactoryProvider).put(RefDialogFragment.class, (Provider) DaggerApplicationComponent.this.refDialogFragmentSubcomponentFactoryProvider).put(ConfirmDialogFragment.class, (Provider) DaggerApplicationComponent.this.confirmDialogFragmentSubcomponentFactoryProvider).put(RawCommentFragment.class, (Provider) rawCommentFragmentSubcomponentFactoryProvider).put(RenderedCommentFragment.class, (Provider) renderedCommentFragmentSubcomponentFactoryProvider).build();}

    private DispatchingAndroidInjector<Object> getDispatchingAndroidInjectorOfObject() {
      return DispatchingAndroidInjector_Factory.newInstance(getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(), Collections.<String, Provider<AndroidInjector.Factory<?>>>emptyMap());}

    @SuppressWarnings("unchecked")
    private void initialize(final EditCommentActivity arg0) {
      this.rawCommentFragmentSubcomponentFactoryProvider = new Provider<CreateCommentFragmentProvider_RawCommentFragment.RawCommentFragmentSubcomponent.Factory>() {
        @Override
        public CreateCommentFragmentProvider_RawCommentFragment.RawCommentFragmentSubcomponent.Factory get(
            ) {
          return new RawCommentFragmentSubcomponentFactory();}
      };
      this.renderedCommentFragmentSubcomponentFactoryProvider = new Provider<CreateCommentFragmentProvider_RenderedCommentFragment.RenderedCommentFragmentSubcomponent.Factory>() {
        @Override
        public CreateCommentFragmentProvider_RenderedCommentFragment.RenderedCommentFragmentSubcomponent.Factory get(
            ) {
          return new RenderedCommentFragmentSubcomponentFactory();}
      };
    }

    @Override
    public void inject(EditCommentActivity arg0) {
      injectEditCommentActivity(arg0);}

    private EditCommentActivity injectEditCommentActivity(EditCommentActivity instance) {
      DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, getDispatchingAndroidInjectorOfObject());
      return instance;
    }

    private final class RawCommentFragmentSubcomponentFactory implements CreateCommentFragmentProvider_RawCommentFragment.RawCommentFragmentSubcomponent.Factory {
      @Override
      public CreateCommentFragmentProvider_RawCommentFragment.RawCommentFragmentSubcomponent create(
          RawCommentFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new RawCommentFragmentSubcomponentImpl(arg0);
      }
    }

    private final class RawCommentFragmentSubcomponentImpl implements CreateCommentFragmentProvider_RawCommentFragment.RawCommentFragmentSubcomponent {
      private RawCommentFragmentSubcomponentImpl(RawCommentFragment arg0) {

      }

      @Override
      public void inject(RawCommentFragment arg0) {
        injectRawCommentFragment(arg0);}

      private RawCommentFragment injectRawCommentFragment(RawCommentFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, AB_EGCA_EditCommentActivitySubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
        return instance;
      }
    }

    private final class RenderedCommentFragmentSubcomponentFactory implements CreateCommentFragmentProvider_RenderedCommentFragment.RenderedCommentFragmentSubcomponent.Factory {
      @Override
      public CreateCommentFragmentProvider_RenderedCommentFragment.RenderedCommentFragmentSubcomponent create(
          RenderedCommentFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new RenderedCommentFragmentSubcomponentImpl(arg0);
      }
    }

    private final class RenderedCommentFragmentSubcomponentImpl implements CreateCommentFragmentProvider_RenderedCommentFragment.RenderedCommentFragmentSubcomponent {
      private RenderedCommentFragmentSubcomponentImpl(RenderedCommentFragment arg0) {

      }

      private HttpImageGetter getHttpImageGetter() {
        return new HttpImageGetter(DaggerApplicationComponent.this.provideApplicationContextProvider.get());}

      @Override
      public void inject(RenderedCommentFragment arg0) {
        injectRenderedCommentFragment(arg0);}

      private RenderedCommentFragment injectRenderedCommentFragment(
          RenderedCommentFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, AB_EGCA_EditCommentActivitySubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
        RenderedCommentFragment_MembersInjector.injectImageGetter(instance, getHttpImageGetter());
        return instance;
      }
    }
  }

  private final class AB_EICA_EditCommentActivitySubcomponentFactory implements ActivityBuilder_EditIssueCommentActivity.EditCommentActivitySubcomponent.Factory {
    @Override
    public ActivityBuilder_EditIssueCommentActivity.EditCommentActivitySubcomponent create(
        com.github.pockethub.android.ui.issue.EditCommentActivity arg0) {
      Preconditions.checkNotNull(arg0);
      return new AB_EICA_EditCommentActivitySubcomponentImpl(arg0);
    }
  }

  private final class AB_EICA_EditCommentActivitySubcomponentImpl implements ActivityBuilder_EditIssueCommentActivity.EditCommentActivitySubcomponent {
    private Provider<CreateCommentFragmentProvider_RawCommentFragment.RawCommentFragmentSubcomponent.Factory> rawCommentFragmentSubcomponentFactoryProvider;

    private Provider<CreateCommentFragmentProvider_RenderedCommentFragment.RenderedCommentFragmentSubcomponent.Factory> renderedCommentFragmentSubcomponentFactoryProvider;

    private AB_EICA_EditCommentActivitySubcomponentImpl(
        com.github.pockethub.android.ui.issue.EditCommentActivity arg0) {

      initialize(arg0);
    }

    private Map<Class<?>, Provider<AndroidInjector.Factory<?>>> getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(
        ) {
      return MapBuilder.<Class<?>, Provider<AndroidInjector.Factory<?>>>newMapBuilder(35).put(MainActivity.class, (Provider) DaggerApplicationComponent.this.mainActivitySubcomponentFactoryProvider).put(RepositoryViewActivity.class, (Provider) DaggerApplicationComponent.this.repositoryViewActivitySubcomponentFactoryProvider).put(IssuesViewActivity.class, (Provider) DaggerApplicationComponent.this.issuesViewActivitySubcomponentFactoryProvider).put(NotificationActivity.class, (Provider) DaggerApplicationComponent.this.notificationActivitySubcomponentFactoryProvider).put(CreateGistActivity.class, (Provider) DaggerApplicationComponent.this.createGistActivitySubcomponentFactoryProvider).put(IssueBrowseActivity.class, (Provider) DaggerApplicationComponent.this.issueBrowseActivitySubcomponentFactoryProvider).put(EditIssuesFilterActivity.class, (Provider) DaggerApplicationComponent.this.editIssuesFilterActivitySubcomponentFactoryProvider).put(EditIssueActivity.class, (Provider) DaggerApplicationComponent.this.editIssueActivitySubcomponentFactoryProvider).put(SearchActivity.class, (Provider) DaggerApplicationComponent.this.searchActivitySubcomponentFactoryProvider).put(FiltersViewActivity.class, (Provider) DaggerApplicationComponent.this.filtersViewActivitySubcomponentFactoryProvider).put(GistsViewActivity.class, (Provider) DaggerApplicationComponent.this.gistsViewActivitySubcomponentFactoryProvider).put(GistFilesViewActivity.class, (Provider) DaggerApplicationComponent.this.gistFilesViewActivitySubcomponentFactoryProvider).put(CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider2).put(com.github.pockethub.android.ui.commit.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider3).put(EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider2).put(RepositoryContributorsActivity.class, (Provider) DaggerApplicationComponent.this.repositoryContributorsActivitySubcomponentFactoryProvider).put(UserViewActivity.class, (Provider) DaggerApplicationComponent.this.userViewActivitySubcomponentFactoryProvider).put(LoginActivity.class, (Provider) DaggerApplicationComponent.this.loginActivitySubcomponentFactoryProvider).put(UriLauncherActivity.class, (Provider) DaggerApplicationComponent.this.uriLauncherActivitySubcomponentFactoryProvider).put(IssueSearchActivity.class, (Provider) DaggerApplicationComponent.this.issueSearchActivitySubcomponentFactoryProvider).put(CommitCompareViewActivity.class, (Provider) DaggerApplicationComponent.this.commitCompareViewActivitySubcomponentFactoryProvider).put(CommitViewActivity.class, (Provider) DaggerApplicationComponent.this.commitViewActivitySubcomponentFactoryProvider).put(CommitFileViewActivity.class, (Provider) DaggerApplicationComponent.this.commitFileViewActivitySubcomponentFactoryProvider).put(BranchFileViewActivity.class, (Provider) DaggerApplicationComponent.this.branchFileViewActivitySubcomponentFactoryProvider).put(LoginWebViewActivity.class, (Provider) DaggerApplicationComponent.this.loginWebViewActivitySubcomponentFactoryProvider).put(SyncAdapterService.class, (Provider) DaggerApplicationComponent.this.syncAdapterServiceSubcomponentFactoryProvider).put(LabelsDialogFragment.class, (Provider) DaggerApplicationComponent.this.labelsDialogFragmentSubcomponentFactoryProvider).put(AssigneeDialogFragment.class, (Provider) DaggerApplicationComponent.this.assigneeDialogFragmentSubcomponentFactoryProvider).put(MilestoneDialogFragment.class, (Provider) DaggerApplicationComponent.this.milestoneDialogFragmentSubcomponentFactoryProvider).put(RefDialogFragment.class, (Provider) DaggerApplicationComponent.this.refDialogFragmentSubcomponentFactoryProvider).put(ConfirmDialogFragment.class, (Provider) DaggerApplicationComponent.this.confirmDialogFragmentSubcomponentFactoryProvider).put(RawCommentFragment.class, (Provider) rawCommentFragmentSubcomponentFactoryProvider).put(RenderedCommentFragment.class, (Provider) renderedCommentFragmentSubcomponentFactoryProvider).build();}

    private DispatchingAndroidInjector<Object> getDispatchingAndroidInjectorOfObject() {
      return DispatchingAndroidInjector_Factory.newInstance(getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(), Collections.<String, Provider<AndroidInjector.Factory<?>>>emptyMap());}

    @SuppressWarnings("unchecked")
    private void initialize(final com.github.pockethub.android.ui.issue.EditCommentActivity arg0) {
      this.rawCommentFragmentSubcomponentFactoryProvider = new Provider<CreateCommentFragmentProvider_RawCommentFragment.RawCommentFragmentSubcomponent.Factory>() {
        @Override
        public CreateCommentFragmentProvider_RawCommentFragment.RawCommentFragmentSubcomponent.Factory get(
            ) {
          return new RawCommentFragmentSubcomponentFactory();}
      };
      this.renderedCommentFragmentSubcomponentFactoryProvider = new Provider<CreateCommentFragmentProvider_RenderedCommentFragment.RenderedCommentFragmentSubcomponent.Factory>() {
        @Override
        public CreateCommentFragmentProvider_RenderedCommentFragment.RenderedCommentFragmentSubcomponent.Factory get(
            ) {
          return new RenderedCommentFragmentSubcomponentFactory();}
      };
    }

    @Override
    public void inject(com.github.pockethub.android.ui.issue.EditCommentActivity arg0) {
      injectEditCommentActivity(arg0);}

    private com.github.pockethub.android.ui.issue.EditCommentActivity injectEditCommentActivity(
        com.github.pockethub.android.ui.issue.EditCommentActivity instance) {
      DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, getDispatchingAndroidInjectorOfObject());
      return instance;
    }

    private final class RawCommentFragmentSubcomponentFactory implements CreateCommentFragmentProvider_RawCommentFragment.RawCommentFragmentSubcomponent.Factory {
      @Override
      public CreateCommentFragmentProvider_RawCommentFragment.RawCommentFragmentSubcomponent create(
          RawCommentFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new RawCommentFragmentSubcomponentImpl(arg0);
      }
    }

    private final class RawCommentFragmentSubcomponentImpl implements CreateCommentFragmentProvider_RawCommentFragment.RawCommentFragmentSubcomponent {
      private RawCommentFragmentSubcomponentImpl(RawCommentFragment arg0) {

      }

      @Override
      public void inject(RawCommentFragment arg0) {
        injectRawCommentFragment(arg0);}

      private RawCommentFragment injectRawCommentFragment(RawCommentFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, AB_EICA_EditCommentActivitySubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
        return instance;
      }
    }

    private final class RenderedCommentFragmentSubcomponentFactory implements CreateCommentFragmentProvider_RenderedCommentFragment.RenderedCommentFragmentSubcomponent.Factory {
      @Override
      public CreateCommentFragmentProvider_RenderedCommentFragment.RenderedCommentFragmentSubcomponent create(
          RenderedCommentFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new RenderedCommentFragmentSubcomponentImpl(arg0);
      }
    }

    private final class RenderedCommentFragmentSubcomponentImpl implements CreateCommentFragmentProvider_RenderedCommentFragment.RenderedCommentFragmentSubcomponent {
      private RenderedCommentFragmentSubcomponentImpl(RenderedCommentFragment arg0) {

      }

      private HttpImageGetter getHttpImageGetter() {
        return new HttpImageGetter(DaggerApplicationComponent.this.provideApplicationContextProvider.get());}

      @Override
      public void inject(RenderedCommentFragment arg0) {
        injectRenderedCommentFragment(arg0);}

      private RenderedCommentFragment injectRenderedCommentFragment(
          RenderedCommentFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, AB_EICA_EditCommentActivitySubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
        RenderedCommentFragment_MembersInjector.injectImageGetter(instance, getHttpImageGetter());
        return instance;
      }
    }
  }

  private final class RepositoryContributorsActivitySubcomponentFactory implements ActivityBuilder_RepositoryContributorsActivity.RepositoryContributorsActivitySubcomponent.Factory {
    @Override
    public ActivityBuilder_RepositoryContributorsActivity.RepositoryContributorsActivitySubcomponent create(
        RepositoryContributorsActivity arg0) {
      Preconditions.checkNotNull(arg0);
      return new RepositoryContributorsActivitySubcomponentImpl(arg0);
    }
  }

  private final class RepositoryContributorsActivitySubcomponentImpl implements ActivityBuilder_RepositoryContributorsActivity.RepositoryContributorsActivitySubcomponent {
    private Provider<RepositoryContributorsFragmentProvider_RepositoryContributorsFragment.RepositoryContributorsFragmentSubcomponent.Factory> repositoryContributorsFragmentSubcomponentFactoryProvider;

    private RepositoryContributorsActivitySubcomponentImpl(RepositoryContributorsActivity arg0) {

      initialize(arg0);
    }

    private Map<Class<?>, Provider<AndroidInjector.Factory<?>>> getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(
        ) {
      return MapBuilder.<Class<?>, Provider<AndroidInjector.Factory<?>>>newMapBuilder(34).put(MainActivity.class, (Provider) DaggerApplicationComponent.this.mainActivitySubcomponentFactoryProvider).put(RepositoryViewActivity.class, (Provider) DaggerApplicationComponent.this.repositoryViewActivitySubcomponentFactoryProvider).put(IssuesViewActivity.class, (Provider) DaggerApplicationComponent.this.issuesViewActivitySubcomponentFactoryProvider).put(NotificationActivity.class, (Provider) DaggerApplicationComponent.this.notificationActivitySubcomponentFactoryProvider).put(CreateGistActivity.class, (Provider) DaggerApplicationComponent.this.createGistActivitySubcomponentFactoryProvider).put(IssueBrowseActivity.class, (Provider) DaggerApplicationComponent.this.issueBrowseActivitySubcomponentFactoryProvider).put(EditIssuesFilterActivity.class, (Provider) DaggerApplicationComponent.this.editIssuesFilterActivitySubcomponentFactoryProvider).put(EditIssueActivity.class, (Provider) DaggerApplicationComponent.this.editIssueActivitySubcomponentFactoryProvider).put(SearchActivity.class, (Provider) DaggerApplicationComponent.this.searchActivitySubcomponentFactoryProvider).put(FiltersViewActivity.class, (Provider) DaggerApplicationComponent.this.filtersViewActivitySubcomponentFactoryProvider).put(GistsViewActivity.class, (Provider) DaggerApplicationComponent.this.gistsViewActivitySubcomponentFactoryProvider).put(GistFilesViewActivity.class, (Provider) DaggerApplicationComponent.this.gistFilesViewActivitySubcomponentFactoryProvider).put(CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider2).put(com.github.pockethub.android.ui.commit.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider3).put(EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider2).put(RepositoryContributorsActivity.class, (Provider) DaggerApplicationComponent.this.repositoryContributorsActivitySubcomponentFactoryProvider).put(UserViewActivity.class, (Provider) DaggerApplicationComponent.this.userViewActivitySubcomponentFactoryProvider).put(LoginActivity.class, (Provider) DaggerApplicationComponent.this.loginActivitySubcomponentFactoryProvider).put(UriLauncherActivity.class, (Provider) DaggerApplicationComponent.this.uriLauncherActivitySubcomponentFactoryProvider).put(IssueSearchActivity.class, (Provider) DaggerApplicationComponent.this.issueSearchActivitySubcomponentFactoryProvider).put(CommitCompareViewActivity.class, (Provider) DaggerApplicationComponent.this.commitCompareViewActivitySubcomponentFactoryProvider).put(CommitViewActivity.class, (Provider) DaggerApplicationComponent.this.commitViewActivitySubcomponentFactoryProvider).put(CommitFileViewActivity.class, (Provider) DaggerApplicationComponent.this.commitFileViewActivitySubcomponentFactoryProvider).put(BranchFileViewActivity.class, (Provider) DaggerApplicationComponent.this.branchFileViewActivitySubcomponentFactoryProvider).put(LoginWebViewActivity.class, (Provider) DaggerApplicationComponent.this.loginWebViewActivitySubcomponentFactoryProvider).put(SyncAdapterService.class, (Provider) DaggerApplicationComponent.this.syncAdapterServiceSubcomponentFactoryProvider).put(LabelsDialogFragment.class, (Provider) DaggerApplicationComponent.this.labelsDialogFragmentSubcomponentFactoryProvider).put(AssigneeDialogFragment.class, (Provider) DaggerApplicationComponent.this.assigneeDialogFragmentSubcomponentFactoryProvider).put(MilestoneDialogFragment.class, (Provider) DaggerApplicationComponent.this.milestoneDialogFragmentSubcomponentFactoryProvider).put(RefDialogFragment.class, (Provider) DaggerApplicationComponent.this.refDialogFragmentSubcomponentFactoryProvider).put(ConfirmDialogFragment.class, (Provider) DaggerApplicationComponent.this.confirmDialogFragmentSubcomponentFactoryProvider).put(RepositoryContributorsFragment.class, (Provider) repositoryContributorsFragmentSubcomponentFactoryProvider).build();}

    private DispatchingAndroidInjector<Object> getDispatchingAndroidInjectorOfObject() {
      return DispatchingAndroidInjector_Factory.newInstance(getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(), Collections.<String, Provider<AndroidInjector.Factory<?>>>emptyMap());}

    @SuppressWarnings("unchecked")
    private void initialize(final RepositoryContributorsActivity arg0) {
      this.repositoryContributorsFragmentSubcomponentFactoryProvider = new Provider<RepositoryContributorsFragmentProvider_RepositoryContributorsFragment.RepositoryContributorsFragmentSubcomponent.Factory>() {
        @Override
        public RepositoryContributorsFragmentProvider_RepositoryContributorsFragment.RepositoryContributorsFragmentSubcomponent.Factory get(
            ) {
          return new RepositoryContributorsFragmentSubcomponentFactory();}
      };
    }

    @Override
    public void inject(RepositoryContributorsActivity arg0) {
      injectRepositoryContributorsActivity(arg0);}

    private RepositoryContributorsActivity injectRepositoryContributorsActivity(
        RepositoryContributorsActivity instance) {
      DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, getDispatchingAndroidInjectorOfObject());
      return instance;
    }

    private final class RepositoryContributorsFragmentSubcomponentFactory implements RepositoryContributorsFragmentProvider_RepositoryContributorsFragment.RepositoryContributorsFragmentSubcomponent.Factory {
      @Override
      public RepositoryContributorsFragmentProvider_RepositoryContributorsFragment.RepositoryContributorsFragmentSubcomponent create(
          RepositoryContributorsFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new RepositoryContributorsFragmentSubcomponentImpl(arg0);
      }
    }

    private final class RepositoryContributorsFragmentSubcomponentImpl implements RepositoryContributorsFragmentProvider_RepositoryContributorsFragment.RepositoryContributorsFragmentSubcomponent {
      private RepositoryContributorsFragmentSubcomponentImpl(RepositoryContributorsFragment arg0) {

      }

      @Override
      public void inject(RepositoryContributorsFragment arg0) {
        injectRepositoryContributorsFragment(arg0);}

      private RepositoryContributorsFragment injectRepositoryContributorsFragment(
          RepositoryContributorsFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, RepositoryContributorsActivitySubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
        RepositoryContributorsFragment_MembersInjector.injectService(instance, DaggerApplicationComponent.this.providesRepositoryServiceProvider.get());
        RepositoryContributorsFragment_MembersInjector.injectAvatars(instance, DaggerApplicationComponent.this.avatarLoaderProvider.get());
        return instance;
      }
    }
  }

  private final class UserViewActivitySubcomponentFactory implements ActivityBuilder_UserViewActivity.UserViewActivitySubcomponent.Factory {
    @Override
    public ActivityBuilder_UserViewActivity.UserViewActivitySubcomponent create(
        UserViewActivity arg0) {
      Preconditions.checkNotNull(arg0);
      return new UserViewActivitySubcomponentImpl(arg0);
    }
  }

  private final class UserViewActivitySubcomponentImpl implements ActivityBuilder_UserViewActivity.UserViewActivitySubcomponent {
    private Provider<UserViewFragmentProvider_UserCreatedNewsFragment.UserCreatedNewsFragmentSubcomponent.Factory> userCreatedNewsFragmentSubcomponentFactoryProvider;

    private Provider<UserViewFragmentProvider_UserRepositoryListFragment.UserRepositoryListFragmentSubcomponent.Factory> userRepositoryListFragmentSubcomponentFactoryProvider;

    private Provider<UserViewFragmentProvider_UserFollowersFragment.UserFollowersFragmentSubcomponent.Factory> userFollowersFragmentSubcomponentFactoryProvider;

    private Provider<UserViewFragmentProvider_UserFollowingFragment.UserFollowingFragmentSubcomponent.Factory> userFollowingFragmentSubcomponentFactoryProvider;

    private UserViewActivitySubcomponentImpl(UserViewActivity arg0) {

      initialize(arg0);
    }

    private Map<Class<?>, Provider<AndroidInjector.Factory<?>>> getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(
        ) {
      return MapBuilder.<Class<?>, Provider<AndroidInjector.Factory<?>>>newMapBuilder(37).put(MainActivity.class, (Provider) DaggerApplicationComponent.this.mainActivitySubcomponentFactoryProvider).put(RepositoryViewActivity.class, (Provider) DaggerApplicationComponent.this.repositoryViewActivitySubcomponentFactoryProvider).put(IssuesViewActivity.class, (Provider) DaggerApplicationComponent.this.issuesViewActivitySubcomponentFactoryProvider).put(NotificationActivity.class, (Provider) DaggerApplicationComponent.this.notificationActivitySubcomponentFactoryProvider).put(CreateGistActivity.class, (Provider) DaggerApplicationComponent.this.createGistActivitySubcomponentFactoryProvider).put(IssueBrowseActivity.class, (Provider) DaggerApplicationComponent.this.issueBrowseActivitySubcomponentFactoryProvider).put(EditIssuesFilterActivity.class, (Provider) DaggerApplicationComponent.this.editIssuesFilterActivitySubcomponentFactoryProvider).put(EditIssueActivity.class, (Provider) DaggerApplicationComponent.this.editIssueActivitySubcomponentFactoryProvider).put(SearchActivity.class, (Provider) DaggerApplicationComponent.this.searchActivitySubcomponentFactoryProvider).put(FiltersViewActivity.class, (Provider) DaggerApplicationComponent.this.filtersViewActivitySubcomponentFactoryProvider).put(GistsViewActivity.class, (Provider) DaggerApplicationComponent.this.gistsViewActivitySubcomponentFactoryProvider).put(GistFilesViewActivity.class, (Provider) DaggerApplicationComponent.this.gistFilesViewActivitySubcomponentFactoryProvider).put(CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider2).put(com.github.pockethub.android.ui.commit.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider3).put(EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider2).put(RepositoryContributorsActivity.class, (Provider) DaggerApplicationComponent.this.repositoryContributorsActivitySubcomponentFactoryProvider).put(UserViewActivity.class, (Provider) DaggerApplicationComponent.this.userViewActivitySubcomponentFactoryProvider).put(LoginActivity.class, (Provider) DaggerApplicationComponent.this.loginActivitySubcomponentFactoryProvider).put(UriLauncherActivity.class, (Provider) DaggerApplicationComponent.this.uriLauncherActivitySubcomponentFactoryProvider).put(IssueSearchActivity.class, (Provider) DaggerApplicationComponent.this.issueSearchActivitySubcomponentFactoryProvider).put(CommitCompareViewActivity.class, (Provider) DaggerApplicationComponent.this.commitCompareViewActivitySubcomponentFactoryProvider).put(CommitViewActivity.class, (Provider) DaggerApplicationComponent.this.commitViewActivitySubcomponentFactoryProvider).put(CommitFileViewActivity.class, (Provider) DaggerApplicationComponent.this.commitFileViewActivitySubcomponentFactoryProvider).put(BranchFileViewActivity.class, (Provider) DaggerApplicationComponent.this.branchFileViewActivitySubcomponentFactoryProvider).put(LoginWebViewActivity.class, (Provider) DaggerApplicationComponent.this.loginWebViewActivitySubcomponentFactoryProvider).put(SyncAdapterService.class, (Provider) DaggerApplicationComponent.this.syncAdapterServiceSubcomponentFactoryProvider).put(LabelsDialogFragment.class, (Provider) DaggerApplicationComponent.this.labelsDialogFragmentSubcomponentFactoryProvider).put(AssigneeDialogFragment.class, (Provider) DaggerApplicationComponent.this.assigneeDialogFragmentSubcomponentFactoryProvider).put(MilestoneDialogFragment.class, (Provider) DaggerApplicationComponent.this.milestoneDialogFragmentSubcomponentFactoryProvider).put(RefDialogFragment.class, (Provider) DaggerApplicationComponent.this.refDialogFragmentSubcomponentFactoryProvider).put(ConfirmDialogFragment.class, (Provider) DaggerApplicationComponent.this.confirmDialogFragmentSubcomponentFactoryProvider).put(UserCreatedNewsFragment.class, (Provider) userCreatedNewsFragmentSubcomponentFactoryProvider).put(UserRepositoryListFragment.class, (Provider) userRepositoryListFragmentSubcomponentFactoryProvider).put(UserFollowersFragment.class, (Provider) userFollowersFragmentSubcomponentFactoryProvider).put(UserFollowingFragment.class, (Provider) userFollowingFragmentSubcomponentFactoryProvider).build();}

    private DispatchingAndroidInjector<Object> getDispatchingAndroidInjectorOfObject() {
      return DispatchingAndroidInjector_Factory.newInstance(getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(), Collections.<String, Provider<AndroidInjector.Factory<?>>>emptyMap());}

    @SuppressWarnings("unchecked")
    private void initialize(final UserViewActivity arg0) {
      this.userCreatedNewsFragmentSubcomponentFactoryProvider = new Provider<UserViewFragmentProvider_UserCreatedNewsFragment.UserCreatedNewsFragmentSubcomponent.Factory>() {
        @Override
        public UserViewFragmentProvider_UserCreatedNewsFragment.UserCreatedNewsFragmentSubcomponent.Factory get(
            ) {
          return new UserCreatedNewsFragmentSubcomponentFactory();}
      };
      this.userRepositoryListFragmentSubcomponentFactoryProvider = new Provider<UserViewFragmentProvider_UserRepositoryListFragment.UserRepositoryListFragmentSubcomponent.Factory>() {
        @Override
        public UserViewFragmentProvider_UserRepositoryListFragment.UserRepositoryListFragmentSubcomponent.Factory get(
            ) {
          return new UserRepositoryListFragmentSubcomponentFactory();}
      };
      this.userFollowersFragmentSubcomponentFactoryProvider = new Provider<UserViewFragmentProvider_UserFollowersFragment.UserFollowersFragmentSubcomponent.Factory>() {
        @Override
        public UserViewFragmentProvider_UserFollowersFragment.UserFollowersFragmentSubcomponent.Factory get(
            ) {
          return new UserFollowersFragmentSubcomponentFactory();}
      };
      this.userFollowingFragmentSubcomponentFactoryProvider = new Provider<UserViewFragmentProvider_UserFollowingFragment.UserFollowingFragmentSubcomponent.Factory>() {
        @Override
        public UserViewFragmentProvider_UserFollowingFragment.UserFollowingFragmentSubcomponent.Factory get(
            ) {
          return new UserFollowingFragmentSubcomponentFactory();}
      };
    }

    @Override
    public void inject(UserViewActivity arg0) {
      injectUserViewActivity(arg0);}

    private UserViewActivity injectUserViewActivity(UserViewActivity instance) {
      DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, getDispatchingAndroidInjectorOfObject());
      return instance;
    }

    private final class UserCreatedNewsFragmentSubcomponentFactory implements UserViewFragmentProvider_UserCreatedNewsFragment.UserCreatedNewsFragmentSubcomponent.Factory {
      @Override
      public UserViewFragmentProvider_UserCreatedNewsFragment.UserCreatedNewsFragmentSubcomponent create(
          UserCreatedNewsFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new UserCreatedNewsFragmentSubcomponentImpl(arg0);
      }
    }

    private final class UserCreatedNewsFragmentSubcomponentImpl implements UserViewFragmentProvider_UserCreatedNewsFragment.UserCreatedNewsFragmentSubcomponent {
      private UserCreatedNewsFragmentSubcomponentImpl(UserCreatedNewsFragment arg0) {

      }

      @Override
      public void inject(UserCreatedNewsFragment arg0) {
        injectUserCreatedNewsFragment(arg0);}

      private UserCreatedNewsFragment injectUserCreatedNewsFragment(
          UserCreatedNewsFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, UserViewActivitySubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
        NewsFragment_MembersInjector.injectAvatars(instance, DaggerApplicationComponent.this.avatarLoaderProvider.get());
        return instance;
      }
    }

    private final class UserRepositoryListFragmentSubcomponentFactory implements UserViewFragmentProvider_UserRepositoryListFragment.UserRepositoryListFragmentSubcomponent.Factory {
      @Override
      public UserViewFragmentProvider_UserRepositoryListFragment.UserRepositoryListFragmentSubcomponent create(
          UserRepositoryListFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new UserRepositoryListFragmentSubcomponentImpl(arg0);
      }
    }

    private final class UserRepositoryListFragmentSubcomponentImpl implements UserViewFragmentProvider_UserRepositoryListFragment.UserRepositoryListFragmentSubcomponent {
      private UserRepositoryListFragmentSubcomponentImpl(UserRepositoryListFragment arg0) {

      }

      @Override
      public void inject(UserRepositoryListFragment arg0) {
        injectUserRepositoryListFragment(arg0);}

      private UserRepositoryListFragment injectUserRepositoryListFragment(
          UserRepositoryListFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, UserViewActivitySubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
        UserRepositoryListFragment_MembersInjector.injectService(instance, DaggerApplicationComponent.this.providesRepositoryServiceProvider.get());
        return instance;
      }
    }

    private final class UserFollowersFragmentSubcomponentFactory implements UserViewFragmentProvider_UserFollowersFragment.UserFollowersFragmentSubcomponent.Factory {
      @Override
      public UserViewFragmentProvider_UserFollowersFragment.UserFollowersFragmentSubcomponent create(
          UserFollowersFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new UserFollowersFragmentSubcomponentImpl(arg0);
      }
    }

    private final class UserFollowersFragmentSubcomponentImpl implements UserViewFragmentProvider_UserFollowersFragment.UserFollowersFragmentSubcomponent {
      private UserFollowersFragmentSubcomponentImpl(UserFollowersFragment arg0) {

      }

      @Override
      public void inject(UserFollowersFragment arg0) {
        injectUserFollowersFragment(arg0);}

      private UserFollowersFragment injectUserFollowersFragment(UserFollowersFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, UserViewActivitySubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
        PagedUserFragment_MembersInjector.injectAvatars(instance, DaggerApplicationComponent.this.avatarLoaderProvider.get());
        return instance;
      }
    }

    private final class UserFollowingFragmentSubcomponentFactory implements UserViewFragmentProvider_UserFollowingFragment.UserFollowingFragmentSubcomponent.Factory {
      @Override
      public UserViewFragmentProvider_UserFollowingFragment.UserFollowingFragmentSubcomponent create(
          UserFollowingFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new UserFollowingFragmentSubcomponentImpl(arg0);
      }
    }

    private final class UserFollowingFragmentSubcomponentImpl implements UserViewFragmentProvider_UserFollowingFragment.UserFollowingFragmentSubcomponent {
      private UserFollowingFragmentSubcomponentImpl(UserFollowingFragment arg0) {

      }

      @Override
      public void inject(UserFollowingFragment arg0) {
        injectUserFollowingFragment(arg0);}

      private UserFollowingFragment injectUserFollowingFragment(UserFollowingFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, UserViewActivitySubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
        PagedUserFragment_MembersInjector.injectAvatars(instance, DaggerApplicationComponent.this.avatarLoaderProvider.get());
        return instance;
      }
    }
  }

  private final class LoginActivitySubcomponentFactory implements ActivityBuilder_LoginActivity.LoginActivitySubcomponent.Factory {
    @Override
    public ActivityBuilder_LoginActivity.LoginActivitySubcomponent create(LoginActivity arg0) {
      Preconditions.checkNotNull(arg0);
      return new LoginActivitySubcomponentImpl(arg0);
    }
  }

  private final class LoginActivitySubcomponentImpl implements ActivityBuilder_LoginActivity.LoginActivitySubcomponent {
    private LoginActivitySubcomponentImpl(LoginActivity arg0) {

    }

    @Override
    public void inject(LoginActivity arg0) {
      injectLoginActivity(arg0);}

    private LoginActivity injectLoginActivity(LoginActivity instance) {
      DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, DaggerApplicationComponent.this.getDispatchingAndroidInjectorOfObject());
      LoginActivity_MembersInjector.injectUserService(instance, DaggerApplicationComponent.this.providesUserServiceProvider.get());
      return instance;
    }
  }

  private final class UriLauncherActivitySubcomponentFactory implements ActivityBuilder_UriLauncherActivity.UriLauncherActivitySubcomponent.Factory {
    @Override
    public ActivityBuilder_UriLauncherActivity.UriLauncherActivitySubcomponent create(
        UriLauncherActivity arg0) {
      Preconditions.checkNotNull(arg0);
      return new UriLauncherActivitySubcomponentImpl(arg0);
    }
  }

  private final class UriLauncherActivitySubcomponentImpl implements ActivityBuilder_UriLauncherActivity.UriLauncherActivitySubcomponent {
    private UriLauncherActivitySubcomponentImpl(UriLauncherActivity arg0) {

    }

    @Override
    public void inject(UriLauncherActivity arg0) {
    }
  }

  private final class IssueSearchActivitySubcomponentFactory implements ActivityBuilder_IssueSearchActivity.IssueSearchActivitySubcomponent.Factory {
    @Override
    public ActivityBuilder_IssueSearchActivity.IssueSearchActivitySubcomponent create(
        IssueSearchActivity arg0) {
      Preconditions.checkNotNull(arg0);
      return new IssueSearchActivitySubcomponentImpl(arg0);
    }
  }

  private final class IssueSearchActivitySubcomponentImpl implements ActivityBuilder_IssueSearchActivity.IssueSearchActivitySubcomponent {
    private Provider<IssueSearchFragmentProvider_SearchIssueListFragment.SearchIssueListFragmentSubcomponent.Factory> searchIssueListFragmentSubcomponentFactoryProvider;

    private IssueSearchActivitySubcomponentImpl(IssueSearchActivity arg0) {

      initialize(arg0);
    }

    private Map<Class<?>, Provider<AndroidInjector.Factory<?>>> getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(
        ) {
      return MapBuilder.<Class<?>, Provider<AndroidInjector.Factory<?>>>newMapBuilder(34).put(MainActivity.class, (Provider) DaggerApplicationComponent.this.mainActivitySubcomponentFactoryProvider).put(RepositoryViewActivity.class, (Provider) DaggerApplicationComponent.this.repositoryViewActivitySubcomponentFactoryProvider).put(IssuesViewActivity.class, (Provider) DaggerApplicationComponent.this.issuesViewActivitySubcomponentFactoryProvider).put(NotificationActivity.class, (Provider) DaggerApplicationComponent.this.notificationActivitySubcomponentFactoryProvider).put(CreateGistActivity.class, (Provider) DaggerApplicationComponent.this.createGistActivitySubcomponentFactoryProvider).put(IssueBrowseActivity.class, (Provider) DaggerApplicationComponent.this.issueBrowseActivitySubcomponentFactoryProvider).put(EditIssuesFilterActivity.class, (Provider) DaggerApplicationComponent.this.editIssuesFilterActivitySubcomponentFactoryProvider).put(EditIssueActivity.class, (Provider) DaggerApplicationComponent.this.editIssueActivitySubcomponentFactoryProvider).put(SearchActivity.class, (Provider) DaggerApplicationComponent.this.searchActivitySubcomponentFactoryProvider).put(FiltersViewActivity.class, (Provider) DaggerApplicationComponent.this.filtersViewActivitySubcomponentFactoryProvider).put(GistsViewActivity.class, (Provider) DaggerApplicationComponent.this.gistsViewActivitySubcomponentFactoryProvider).put(GistFilesViewActivity.class, (Provider) DaggerApplicationComponent.this.gistFilesViewActivitySubcomponentFactoryProvider).put(CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider2).put(com.github.pockethub.android.ui.commit.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider3).put(EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider2).put(RepositoryContributorsActivity.class, (Provider) DaggerApplicationComponent.this.repositoryContributorsActivitySubcomponentFactoryProvider).put(UserViewActivity.class, (Provider) DaggerApplicationComponent.this.userViewActivitySubcomponentFactoryProvider).put(LoginActivity.class, (Provider) DaggerApplicationComponent.this.loginActivitySubcomponentFactoryProvider).put(UriLauncherActivity.class, (Provider) DaggerApplicationComponent.this.uriLauncherActivitySubcomponentFactoryProvider).put(IssueSearchActivity.class, (Provider) DaggerApplicationComponent.this.issueSearchActivitySubcomponentFactoryProvider).put(CommitCompareViewActivity.class, (Provider) DaggerApplicationComponent.this.commitCompareViewActivitySubcomponentFactoryProvider).put(CommitViewActivity.class, (Provider) DaggerApplicationComponent.this.commitViewActivitySubcomponentFactoryProvider).put(CommitFileViewActivity.class, (Provider) DaggerApplicationComponent.this.commitFileViewActivitySubcomponentFactoryProvider).put(BranchFileViewActivity.class, (Provider) DaggerApplicationComponent.this.branchFileViewActivitySubcomponentFactoryProvider).put(LoginWebViewActivity.class, (Provider) DaggerApplicationComponent.this.loginWebViewActivitySubcomponentFactoryProvider).put(SyncAdapterService.class, (Provider) DaggerApplicationComponent.this.syncAdapterServiceSubcomponentFactoryProvider).put(LabelsDialogFragment.class, (Provider) DaggerApplicationComponent.this.labelsDialogFragmentSubcomponentFactoryProvider).put(AssigneeDialogFragment.class, (Provider) DaggerApplicationComponent.this.assigneeDialogFragmentSubcomponentFactoryProvider).put(MilestoneDialogFragment.class, (Provider) DaggerApplicationComponent.this.milestoneDialogFragmentSubcomponentFactoryProvider).put(RefDialogFragment.class, (Provider) DaggerApplicationComponent.this.refDialogFragmentSubcomponentFactoryProvider).put(ConfirmDialogFragment.class, (Provider) DaggerApplicationComponent.this.confirmDialogFragmentSubcomponentFactoryProvider).put(SearchIssueListFragment.class, (Provider) searchIssueListFragmentSubcomponentFactoryProvider).build();}

    private DispatchingAndroidInjector<Object> getDispatchingAndroidInjectorOfObject() {
      return DispatchingAndroidInjector_Factory.newInstance(getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(), Collections.<String, Provider<AndroidInjector.Factory<?>>>emptyMap());}

    @SuppressWarnings("unchecked")
    private void initialize(final IssueSearchActivity arg0) {
      this.searchIssueListFragmentSubcomponentFactoryProvider = new Provider<IssueSearchFragmentProvider_SearchIssueListFragment.SearchIssueListFragmentSubcomponent.Factory>() {
        @Override
        public IssueSearchFragmentProvider_SearchIssueListFragment.SearchIssueListFragmentSubcomponent.Factory get(
            ) {
          return new SearchIssueListFragmentSubcomponentFactory();}
      };
    }

    @Override
    public void inject(IssueSearchActivity arg0) {
      injectIssueSearchActivity(arg0);}

    private IssueSearchActivity injectIssueSearchActivity(IssueSearchActivity instance) {
      DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, getDispatchingAndroidInjectorOfObject());
      return instance;
    }

    private final class SearchIssueListFragmentSubcomponentFactory implements IssueSearchFragmentProvider_SearchIssueListFragment.SearchIssueListFragmentSubcomponent.Factory {
      @Override
      public IssueSearchFragmentProvider_SearchIssueListFragment.SearchIssueListFragmentSubcomponent create(
          SearchIssueListFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new SearchIssueListFragmentSubcomponentImpl(arg0);
      }
    }

    private final class SearchIssueListFragmentSubcomponentImpl implements IssueSearchFragmentProvider_SearchIssueListFragment.SearchIssueListFragmentSubcomponent {
      private SearchIssueListFragmentSubcomponentImpl(SearchIssueListFragment arg0) {

      }

      @Override
      public void inject(SearchIssueListFragment arg0) {
        injectSearchIssueListFragment(arg0);}

      private SearchIssueListFragment injectSearchIssueListFragment(
          SearchIssueListFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, IssueSearchActivitySubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
        SearchIssueListFragment_MembersInjector.injectService(instance, DaggerApplicationComponent.this.providesSearchServiceProvider.get());
        SearchIssueListFragment_MembersInjector.injectAvatars(instance, DaggerApplicationComponent.this.avatarLoaderProvider.get());
        return instance;
      }
    }
  }

  private final class CommitCompareViewActivitySubcomponentFactory implements ActivityBuilder_CommitCompareViewActivity.CommitCompareViewActivitySubcomponent.Factory {
    @Override
    public ActivityBuilder_CommitCompareViewActivity.CommitCompareViewActivitySubcomponent create(
        CommitCompareViewActivity arg0) {
      Preconditions.checkNotNull(arg0);
      return new CommitCompareViewActivitySubcomponentImpl(arg0);
    }
  }

  private final class CommitCompareViewActivitySubcomponentImpl implements ActivityBuilder_CommitCompareViewActivity.CommitCompareViewActivitySubcomponent {
    private Provider<CommitCompareViewFragmentProvider_CommitCompareListFragment.CommitCompareListFragmentSubcomponent.Factory> commitCompareListFragmentSubcomponentFactoryProvider;

    private CommitCompareViewActivitySubcomponentImpl(CommitCompareViewActivity arg0) {

      initialize(arg0);
    }

    private Map<Class<?>, Provider<AndroidInjector.Factory<?>>> getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(
        ) {
      return MapBuilder.<Class<?>, Provider<AndroidInjector.Factory<?>>>newMapBuilder(34).put(MainActivity.class, (Provider) DaggerApplicationComponent.this.mainActivitySubcomponentFactoryProvider).put(RepositoryViewActivity.class, (Provider) DaggerApplicationComponent.this.repositoryViewActivitySubcomponentFactoryProvider).put(IssuesViewActivity.class, (Provider) DaggerApplicationComponent.this.issuesViewActivitySubcomponentFactoryProvider).put(NotificationActivity.class, (Provider) DaggerApplicationComponent.this.notificationActivitySubcomponentFactoryProvider).put(CreateGistActivity.class, (Provider) DaggerApplicationComponent.this.createGistActivitySubcomponentFactoryProvider).put(IssueBrowseActivity.class, (Provider) DaggerApplicationComponent.this.issueBrowseActivitySubcomponentFactoryProvider).put(EditIssuesFilterActivity.class, (Provider) DaggerApplicationComponent.this.editIssuesFilterActivitySubcomponentFactoryProvider).put(EditIssueActivity.class, (Provider) DaggerApplicationComponent.this.editIssueActivitySubcomponentFactoryProvider).put(SearchActivity.class, (Provider) DaggerApplicationComponent.this.searchActivitySubcomponentFactoryProvider).put(FiltersViewActivity.class, (Provider) DaggerApplicationComponent.this.filtersViewActivitySubcomponentFactoryProvider).put(GistsViewActivity.class, (Provider) DaggerApplicationComponent.this.gistsViewActivitySubcomponentFactoryProvider).put(GistFilesViewActivity.class, (Provider) DaggerApplicationComponent.this.gistFilesViewActivitySubcomponentFactoryProvider).put(CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider2).put(com.github.pockethub.android.ui.commit.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider3).put(EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider2).put(RepositoryContributorsActivity.class, (Provider) DaggerApplicationComponent.this.repositoryContributorsActivitySubcomponentFactoryProvider).put(UserViewActivity.class, (Provider) DaggerApplicationComponent.this.userViewActivitySubcomponentFactoryProvider).put(LoginActivity.class, (Provider) DaggerApplicationComponent.this.loginActivitySubcomponentFactoryProvider).put(UriLauncherActivity.class, (Provider) DaggerApplicationComponent.this.uriLauncherActivitySubcomponentFactoryProvider).put(IssueSearchActivity.class, (Provider) DaggerApplicationComponent.this.issueSearchActivitySubcomponentFactoryProvider).put(CommitCompareViewActivity.class, (Provider) DaggerApplicationComponent.this.commitCompareViewActivitySubcomponentFactoryProvider).put(CommitViewActivity.class, (Provider) DaggerApplicationComponent.this.commitViewActivitySubcomponentFactoryProvider).put(CommitFileViewActivity.class, (Provider) DaggerApplicationComponent.this.commitFileViewActivitySubcomponentFactoryProvider).put(BranchFileViewActivity.class, (Provider) DaggerApplicationComponent.this.branchFileViewActivitySubcomponentFactoryProvider).put(LoginWebViewActivity.class, (Provider) DaggerApplicationComponent.this.loginWebViewActivitySubcomponentFactoryProvider).put(SyncAdapterService.class, (Provider) DaggerApplicationComponent.this.syncAdapterServiceSubcomponentFactoryProvider).put(LabelsDialogFragment.class, (Provider) DaggerApplicationComponent.this.labelsDialogFragmentSubcomponentFactoryProvider).put(AssigneeDialogFragment.class, (Provider) DaggerApplicationComponent.this.assigneeDialogFragmentSubcomponentFactoryProvider).put(MilestoneDialogFragment.class, (Provider) DaggerApplicationComponent.this.milestoneDialogFragmentSubcomponentFactoryProvider).put(RefDialogFragment.class, (Provider) DaggerApplicationComponent.this.refDialogFragmentSubcomponentFactoryProvider).put(ConfirmDialogFragment.class, (Provider) DaggerApplicationComponent.this.confirmDialogFragmentSubcomponentFactoryProvider).put(CommitCompareListFragment.class, (Provider) commitCompareListFragmentSubcomponentFactoryProvider).build();}

    private DispatchingAndroidInjector<Object> getDispatchingAndroidInjectorOfObject() {
      return DispatchingAndroidInjector_Factory.newInstance(getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(), Collections.<String, Provider<AndroidInjector.Factory<?>>>emptyMap());}

    @SuppressWarnings("unchecked")
    private void initialize(final CommitCompareViewActivity arg0) {
      this.commitCompareListFragmentSubcomponentFactoryProvider = new Provider<CommitCompareViewFragmentProvider_CommitCompareListFragment.CommitCompareListFragmentSubcomponent.Factory>() {
        @Override
        public CommitCompareViewFragmentProvider_CommitCompareListFragment.CommitCompareListFragmentSubcomponent.Factory get(
            ) {
          return new CommitCompareListFragmentSubcomponentFactory();}
      };
    }

    @Override
    public void inject(CommitCompareViewActivity arg0) {
      injectCommitCompareViewActivity(arg0);}

    private CommitCompareViewActivity injectCommitCompareViewActivity(
        CommitCompareViewActivity instance) {
      DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, getDispatchingAndroidInjectorOfObject());
      return instance;
    }

    private final class CommitCompareListFragmentSubcomponentFactory implements CommitCompareViewFragmentProvider_CommitCompareListFragment.CommitCompareListFragmentSubcomponent.Factory {
      @Override
      public CommitCompareViewFragmentProvider_CommitCompareListFragment.CommitCompareListFragmentSubcomponent create(
          CommitCompareListFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new CommitCompareListFragmentSubcomponentImpl(arg0);
      }
    }

    private final class CommitCompareListFragmentSubcomponentImpl implements CommitCompareViewFragmentProvider_CommitCompareListFragment.CommitCompareListFragmentSubcomponent {
      private CommitCompareListFragmentSubcomponentImpl(CommitCompareListFragment arg0) {

      }

      @Override
      public void inject(CommitCompareListFragment arg0) {
        injectCommitCompareListFragment(arg0);}

      private CommitCompareListFragment injectCommitCompareListFragment(
          CommitCompareListFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, CommitCompareViewActivitySubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
        CommitCompareListFragment_MembersInjector.injectAvatars(instance, DaggerApplicationComponent.this.avatarLoaderProvider.get());
        return instance;
      }
    }
  }

  private final class CommitViewActivitySubcomponentFactory implements ActivityBuilder_CommitViewActivity.CommitViewActivitySubcomponent.Factory {
    @Override
    public ActivityBuilder_CommitViewActivity.CommitViewActivitySubcomponent create(
        CommitViewActivity arg0) {
      Preconditions.checkNotNull(arg0);
      return new CommitViewActivitySubcomponentImpl(arg0);
    }
  }

  private final class CommitViewActivitySubcomponentImpl implements ActivityBuilder_CommitViewActivity.CommitViewActivitySubcomponent {
    private Provider<CommitViewFragmentProvider_CommitDiffListFragment.CommitDiffListFragmentSubcomponent.Factory> commitDiffListFragmentSubcomponentFactoryProvider;

    private CommitViewActivitySubcomponentImpl(CommitViewActivity arg0) {

      initialize(arg0);
    }

    private Map<Class<?>, Provider<AndroidInjector.Factory<?>>> getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(
        ) {
      return MapBuilder.<Class<?>, Provider<AndroidInjector.Factory<?>>>newMapBuilder(34).put(MainActivity.class, (Provider) DaggerApplicationComponent.this.mainActivitySubcomponentFactoryProvider).put(RepositoryViewActivity.class, (Provider) DaggerApplicationComponent.this.repositoryViewActivitySubcomponentFactoryProvider).put(IssuesViewActivity.class, (Provider) DaggerApplicationComponent.this.issuesViewActivitySubcomponentFactoryProvider).put(NotificationActivity.class, (Provider) DaggerApplicationComponent.this.notificationActivitySubcomponentFactoryProvider).put(CreateGistActivity.class, (Provider) DaggerApplicationComponent.this.createGistActivitySubcomponentFactoryProvider).put(IssueBrowseActivity.class, (Provider) DaggerApplicationComponent.this.issueBrowseActivitySubcomponentFactoryProvider).put(EditIssuesFilterActivity.class, (Provider) DaggerApplicationComponent.this.editIssuesFilterActivitySubcomponentFactoryProvider).put(EditIssueActivity.class, (Provider) DaggerApplicationComponent.this.editIssueActivitySubcomponentFactoryProvider).put(SearchActivity.class, (Provider) DaggerApplicationComponent.this.searchActivitySubcomponentFactoryProvider).put(FiltersViewActivity.class, (Provider) DaggerApplicationComponent.this.filtersViewActivitySubcomponentFactoryProvider).put(GistsViewActivity.class, (Provider) DaggerApplicationComponent.this.gistsViewActivitySubcomponentFactoryProvider).put(GistFilesViewActivity.class, (Provider) DaggerApplicationComponent.this.gistFilesViewActivitySubcomponentFactoryProvider).put(CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider2).put(com.github.pockethub.android.ui.commit.CreateCommentActivity.class, (Provider) DaggerApplicationComponent.this.createCommentActivitySubcomponentFactoryProvider3).put(EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider).put(com.github.pockethub.android.ui.issue.EditCommentActivity.class, (Provider) DaggerApplicationComponent.this.editCommentActivitySubcomponentFactoryProvider2).put(RepositoryContributorsActivity.class, (Provider) DaggerApplicationComponent.this.repositoryContributorsActivitySubcomponentFactoryProvider).put(UserViewActivity.class, (Provider) DaggerApplicationComponent.this.userViewActivitySubcomponentFactoryProvider).put(LoginActivity.class, (Provider) DaggerApplicationComponent.this.loginActivitySubcomponentFactoryProvider).put(UriLauncherActivity.class, (Provider) DaggerApplicationComponent.this.uriLauncherActivitySubcomponentFactoryProvider).put(IssueSearchActivity.class, (Provider) DaggerApplicationComponent.this.issueSearchActivitySubcomponentFactoryProvider).put(CommitCompareViewActivity.class, (Provider) DaggerApplicationComponent.this.commitCompareViewActivitySubcomponentFactoryProvider).put(CommitViewActivity.class, (Provider) DaggerApplicationComponent.this.commitViewActivitySubcomponentFactoryProvider).put(CommitFileViewActivity.class, (Provider) DaggerApplicationComponent.this.commitFileViewActivitySubcomponentFactoryProvider).put(BranchFileViewActivity.class, (Provider) DaggerApplicationComponent.this.branchFileViewActivitySubcomponentFactoryProvider).put(LoginWebViewActivity.class, (Provider) DaggerApplicationComponent.this.loginWebViewActivitySubcomponentFactoryProvider).put(SyncAdapterService.class, (Provider) DaggerApplicationComponent.this.syncAdapterServiceSubcomponentFactoryProvider).put(LabelsDialogFragment.class, (Provider) DaggerApplicationComponent.this.labelsDialogFragmentSubcomponentFactoryProvider).put(AssigneeDialogFragment.class, (Provider) DaggerApplicationComponent.this.assigneeDialogFragmentSubcomponentFactoryProvider).put(MilestoneDialogFragment.class, (Provider) DaggerApplicationComponent.this.milestoneDialogFragmentSubcomponentFactoryProvider).put(RefDialogFragment.class, (Provider) DaggerApplicationComponent.this.refDialogFragmentSubcomponentFactoryProvider).put(ConfirmDialogFragment.class, (Provider) DaggerApplicationComponent.this.confirmDialogFragmentSubcomponentFactoryProvider).put(CommitDiffListFragment.class, (Provider) commitDiffListFragmentSubcomponentFactoryProvider).build();}

    private DispatchingAndroidInjector<Object> getDispatchingAndroidInjectorOfObject() {
      return DispatchingAndroidInjector_Factory.newInstance(getMapOfClassOfAndProviderOfAndroidInjectorFactoryOf(), Collections.<String, Provider<AndroidInjector.Factory<?>>>emptyMap());}

    @SuppressWarnings("unchecked")
    private void initialize(final CommitViewActivity arg0) {
      this.commitDiffListFragmentSubcomponentFactoryProvider = new Provider<CommitViewFragmentProvider_CommitDiffListFragment.CommitDiffListFragmentSubcomponent.Factory>() {
        @Override
        public CommitViewFragmentProvider_CommitDiffListFragment.CommitDiffListFragmentSubcomponent.Factory get(
            ) {
          return new CommitDiffListFragmentSubcomponentFactory();}
      };
    }

    @Override
    public void inject(CommitViewActivity arg0) {
      injectCommitViewActivity(arg0);}

    private CommitViewActivity injectCommitViewActivity(CommitViewActivity instance) {
      DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, getDispatchingAndroidInjectorOfObject());
      return instance;
    }

    private final class CommitDiffListFragmentSubcomponentFactory implements CommitViewFragmentProvider_CommitDiffListFragment.CommitDiffListFragmentSubcomponent.Factory {
      @Override
      public CommitViewFragmentProvider_CommitDiffListFragment.CommitDiffListFragmentSubcomponent create(
          CommitDiffListFragment arg0) {
        Preconditions.checkNotNull(arg0);
        return new CommitDiffListFragmentSubcomponentImpl(arg0);
      }
    }

    private final class CommitDiffListFragmentSubcomponentImpl implements CommitViewFragmentProvider_CommitDiffListFragment.CommitDiffListFragmentSubcomponent {
      private Provider<HttpImageGetter> httpImageGetterProvider;

      private CommitDiffListFragmentSubcomponentImpl(CommitDiffListFragment arg0) {

        initialize(arg0);
      }

      private RefreshCommitTaskFactory getRefreshCommitTaskFactory() {
        return new RefreshCommitTaskFactory(DaggerApplicationComponent.this.commitStoreProvider, httpImageGetterProvider, DaggerApplicationComponent.this.providesRepositoryCommentServiceProvider);}

      private HttpImageGetter getHttpImageGetter() {
        return new HttpImageGetter(DaggerApplicationComponent.this.provideApplicationContextProvider.get());}

      @SuppressWarnings("unchecked")
      private void initialize(final CommitDiffListFragment arg0) {
        this.httpImageGetterProvider = HttpImageGetter_Factory.create(DaggerApplicationComponent.this.provideApplicationContextProvider);
      }

      @Override
      public void inject(CommitDiffListFragment arg0) {
        injectCommitDiffListFragment(arg0);}

      private CommitDiffListFragment injectCommitDiffListFragment(CommitDiffListFragment instance) {
        DaggerFragment_MembersInjector.injectAndroidInjector(instance, CommitViewActivitySubcomponentImpl.this.getDispatchingAndroidInjectorOfObject());
        CommitDiffListFragment_MembersInjector.injectRefreshCommitTaskFactory(instance, getRefreshCommitTaskFactory());
        CommitDiffListFragment_MembersInjector.injectCommentImageGetter(instance, getHttpImageGetter());
        CommitDiffListFragment_MembersInjector.injectAvatars(instance, DaggerApplicationComponent.this.avatarLoaderProvider.get());
        CommitDiffListFragment_MembersInjector.injectStore(instance, DaggerApplicationComponent.this.commitStoreProvider.get());
        return instance;
      }
    }
  }

  private final class CommitFileViewActivitySubcomponentFactory implements ActivityBuilder_CommitFileViewActivity.CommitFileViewActivitySubcomponent.Factory {
    @Override
    public ActivityBuilder_CommitFileViewActivity.CommitFileViewActivitySubcomponent create(
        CommitFileViewActivity arg0) {
      Preconditions.checkNotNull(arg0);
      return new CommitFileViewActivitySubcomponentImpl(arg0);
    }
  }

  private final class CommitFileViewActivitySubcomponentImpl implements ActivityBuilder_CommitFileViewActivity.CommitFileViewActivitySubcomponent {
    private CommitFileViewActivitySubcomponentImpl(CommitFileViewActivity arg0) {

    }

    private HttpImageGetter getHttpImageGetter() {
      return new HttpImageGetter(DaggerApplicationComponent.this.provideApplicationContextProvider.get());}

    @Override
    public void inject(CommitFileViewActivity arg0) {
      injectCommitFileViewActivity(arg0);}

    private CommitFileViewActivity injectCommitFileViewActivity(CommitFileViewActivity instance) {
      DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, DaggerApplicationComponent.this.getDispatchingAndroidInjectorOfObject());
      CommitFileViewActivity_MembersInjector.injectImageGetter(instance, getHttpImageGetter());
      return instance;
    }
  }

  private final class BranchFileViewActivitySubcomponentFactory implements ActivityBuilder_BranchFileViewActivity.BranchFileViewActivitySubcomponent.Factory {
    @Override
    public ActivityBuilder_BranchFileViewActivity.BranchFileViewActivitySubcomponent create(
        BranchFileViewActivity arg0) {
      Preconditions.checkNotNull(arg0);
      return new BranchFileViewActivitySubcomponentImpl(arg0);
    }
  }

  private final class BranchFileViewActivitySubcomponentImpl implements ActivityBuilder_BranchFileViewActivity.BranchFileViewActivitySubcomponent {
    private BranchFileViewActivitySubcomponentImpl(BranchFileViewActivity arg0) {

    }

    private HttpImageGetter getHttpImageGetter() {
      return new HttpImageGetter(DaggerApplicationComponent.this.provideApplicationContextProvider.get());}

    @Override
    public void inject(BranchFileViewActivity arg0) {
      injectBranchFileViewActivity(arg0);}

    private BranchFileViewActivity injectBranchFileViewActivity(BranchFileViewActivity instance) {
      DaggerAppCompatActivity_MembersInjector.injectAndroidInjector(instance, DaggerApplicationComponent.this.getDispatchingAndroidInjectorOfObject());
      BranchFileViewActivity_MembersInjector.injectImageGetter(instance, getHttpImageGetter());
      return instance;
    }
  }

  private final class LoginWebViewActivitySubcomponentFactory implements ActivityBuilder_LoginWebViewActivity.LoginWebViewActivitySubcomponent.Factory {
    @Override
    public ActivityBuilder_LoginWebViewActivity.LoginWebViewActivitySubcomponent create(
        LoginWebViewActivity arg0) {
      Preconditions.checkNotNull(arg0);
      return new LoginWebViewActivitySubcomponentImpl(arg0);
    }
  }

  private final class LoginWebViewActivitySubcomponentImpl implements ActivityBuilder_LoginWebViewActivity.LoginWebViewActivitySubcomponent {
    private LoginWebViewActivitySubcomponentImpl(LoginWebViewActivity arg0) {

    }

    @Override
    public void inject(LoginWebViewActivity arg0) {
    }
  }

  private final class SyncAdapterServiceSubcomponentFactory implements ServiceBuilder_ProvideSyncAdapterService.SyncAdapterServiceSubcomponent.Factory {
    @Override
    public ServiceBuilder_ProvideSyncAdapterService.SyncAdapterServiceSubcomponent create(
        SyncAdapterService arg0) {
      Preconditions.checkNotNull(arg0);
      return new SyncAdapterServiceSubcomponentImpl(arg0);
    }
  }

  private final class SyncAdapterServiceSubcomponentImpl implements ServiceBuilder_ProvideSyncAdapterService.SyncAdapterServiceSubcomponent {
    private SyncAdapterServiceSubcomponentImpl(SyncAdapterService arg0) {

    }

    @Override
    public void inject(SyncAdapterService arg0) {
      injectSyncAdapterService(arg0);}

    private SyncAdapterService injectSyncAdapterService(SyncAdapterService instance) {
      SyncAdapterService_MembersInjector.injectSyncAdapterProvider(instance, DaggerApplicationComponent.this.syncAdapterProvider);
      return instance;
    }
  }

  private final class LabelsDialogFragmentSubcomponentFactory implements DialogFragmentBuilder_LabelsDialogFragment.LabelsDialogFragmentSubcomponent.Factory {
    @Override
    public DialogFragmentBuilder_LabelsDialogFragment.LabelsDialogFragmentSubcomponent create(
        LabelsDialogFragment arg0) {
      Preconditions.checkNotNull(arg0);
      return new LabelsDialogFragmentSubcomponentImpl(arg0);
    }
  }

  private final class LabelsDialogFragmentSubcomponentImpl implements DialogFragmentBuilder_LabelsDialogFragment.LabelsDialogFragmentSubcomponent {
    private LabelsDialogFragmentSubcomponentImpl(LabelsDialogFragment arg0) {

    }

    @Override
    public void inject(LabelsDialogFragment arg0) {
      injectLabelsDialogFragment(arg0);}

    private LabelsDialogFragment injectLabelsDialogFragment(LabelsDialogFragment instance) {
      DaggerAppCompatDialogFragment_MembersInjector.injectAndroidInjector(instance, DaggerApplicationComponent.this.getDispatchingAndroidInjectorOfObject());
      return instance;
    }
  }

  private final class AssigneeDialogFragmentSubcomponentFactory implements DialogFragmentBuilder_AssigneeDialogFragment.AssigneeDialogFragmentSubcomponent.Factory {
    @Override
    public DialogFragmentBuilder_AssigneeDialogFragment.AssigneeDialogFragmentSubcomponent create(
        AssigneeDialogFragment arg0) {
      Preconditions.checkNotNull(arg0);
      return new AssigneeDialogFragmentSubcomponentImpl(arg0);
    }
  }

  private final class AssigneeDialogFragmentSubcomponentImpl implements DialogFragmentBuilder_AssigneeDialogFragment.AssigneeDialogFragmentSubcomponent {
    private AssigneeDialogFragmentSubcomponentImpl(AssigneeDialogFragment arg0) {

    }

    @Override
    public void inject(AssigneeDialogFragment arg0) {
      injectAssigneeDialogFragment(arg0);}

    private AssigneeDialogFragment injectAssigneeDialogFragment(AssigneeDialogFragment instance) {
      DaggerAppCompatDialogFragment_MembersInjector.injectAndroidInjector(instance, DaggerApplicationComponent.this.getDispatchingAndroidInjectorOfObject());
      AssigneeDialogFragment_MembersInjector.injectAvatars(instance, DaggerApplicationComponent.this.avatarLoaderProvider.get());
      return instance;
    }
  }

  private final class MilestoneDialogFragmentSubcomponentFactory implements DialogFragmentBuilder_MilestoneDialogFragment.MilestoneDialogFragmentSubcomponent.Factory {
    @Override
    public DialogFragmentBuilder_MilestoneDialogFragment.MilestoneDialogFragmentSubcomponent create(
        MilestoneDialogFragment arg0) {
      Preconditions.checkNotNull(arg0);
      return new MilestoneDialogFragmentSubcomponentImpl(arg0);
    }
  }

  private final class MilestoneDialogFragmentSubcomponentImpl implements DialogFragmentBuilder_MilestoneDialogFragment.MilestoneDialogFragmentSubcomponent {
    private MilestoneDialogFragmentSubcomponentImpl(MilestoneDialogFragment arg0) {

    }

    @Override
    public void inject(MilestoneDialogFragment arg0) {
      injectMilestoneDialogFragment(arg0);}

    private MilestoneDialogFragment injectMilestoneDialogFragment(
        MilestoneDialogFragment instance) {
      DaggerAppCompatDialogFragment_MembersInjector.injectAndroidInjector(instance, DaggerApplicationComponent.this.getDispatchingAndroidInjectorOfObject());
      return instance;
    }
  }

  private final class RefDialogFragmentSubcomponentFactory implements DialogFragmentBuilder_RefDialogFragment.RefDialogFragmentSubcomponent.Factory {
    @Override
    public DialogFragmentBuilder_RefDialogFragment.RefDialogFragmentSubcomponent create(
        RefDialogFragment arg0) {
      Preconditions.checkNotNull(arg0);
      return new RefDialogFragmentSubcomponentImpl(arg0);
    }
  }

  private final class RefDialogFragmentSubcomponentImpl implements DialogFragmentBuilder_RefDialogFragment.RefDialogFragmentSubcomponent {
    private RefDialogFragmentSubcomponentImpl(RefDialogFragment arg0) {

    }

    @Override
    public void inject(RefDialogFragment arg0) {
      injectRefDialogFragment(arg0);}

    private RefDialogFragment injectRefDialogFragment(RefDialogFragment instance) {
      DaggerAppCompatDialogFragment_MembersInjector.injectAndroidInjector(instance, DaggerApplicationComponent.this.getDispatchingAndroidInjectorOfObject());
      return instance;
    }
  }

  private final class ConfirmDialogFragmentSubcomponentFactory implements DialogFragmentBuilder_ConfirmDialogFragment.ConfirmDialogFragmentSubcomponent.Factory {
    @Override
    public DialogFragmentBuilder_ConfirmDialogFragment.ConfirmDialogFragmentSubcomponent create(
        ConfirmDialogFragment arg0) {
      Preconditions.checkNotNull(arg0);
      return new ConfirmDialogFragmentSubcomponentImpl(arg0);
    }
  }

  private final class ConfirmDialogFragmentSubcomponentImpl implements DialogFragmentBuilder_ConfirmDialogFragment.ConfirmDialogFragmentSubcomponent {
    private ConfirmDialogFragmentSubcomponentImpl(ConfirmDialogFragment arg0) {

    }

    @Override
    public void inject(ConfirmDialogFragment arg0) {
      injectConfirmDialogFragment(arg0);}

    private ConfirmDialogFragment injectConfirmDialogFragment(ConfirmDialogFragment instance) {
      DaggerAppCompatDialogFragment_MembersInjector.injectAndroidInjector(instance, DaggerApplicationComponent.this.getDispatchingAndroidInjectorOfObject());
      return instance;
    }
  }
}
