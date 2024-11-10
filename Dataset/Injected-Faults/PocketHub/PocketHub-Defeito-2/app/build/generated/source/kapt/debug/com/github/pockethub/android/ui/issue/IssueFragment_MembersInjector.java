package com.github.pockethub.android.ui.issue;

import com.github.pockethub.android.core.issue.IssueStore;
import com.github.pockethub.android.core.issue.RefreshIssueTaskFactory;
import com.github.pockethub.android.util.AvatarLoader;
import com.github.pockethub.android.util.HttpImageGetter;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerFragment_MembersInjector;
import dagger.internal.InjectedFieldSignature;
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
public final class IssueFragment_MembersInjector implements MembersInjector<IssueFragment> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<AvatarLoader> avatarsProvider;

  private final Provider<IssueStore> storeProvider;

  private final Provider<RefreshIssueTaskFactory> refreshIssueTaskFactoryProvider;

  private final Provider<EditLabelsTaskFactory> labelsTaskFactoryProvider;

  private final Provider<EditMilestoneTaskFactory> milestoneTaskFactoryProvider;

  private final Provider<EditAssigneeTaskFactory> assigneeTaskFactoryProvider;

  private final Provider<EditStateTaskFactory> stateTaskFactoryProvider;

  private final Provider<HttpImageGetter> bodyImageGetterProvider;

  private final Provider<HttpImageGetter> commentImageGetterProvider;

  public IssueFragment_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<AvatarLoader> avatarsProvider, Provider<IssueStore> storeProvider,
      Provider<RefreshIssueTaskFactory> refreshIssueTaskFactoryProvider,
      Provider<EditLabelsTaskFactory> labelsTaskFactoryProvider,
      Provider<EditMilestoneTaskFactory> milestoneTaskFactoryProvider,
      Provider<EditAssigneeTaskFactory> assigneeTaskFactoryProvider,
      Provider<EditStateTaskFactory> stateTaskFactoryProvider,
      Provider<HttpImageGetter> bodyImageGetterProvider,
      Provider<HttpImageGetter> commentImageGetterProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.avatarsProvider = avatarsProvider;
    this.storeProvider = storeProvider;
    this.refreshIssueTaskFactoryProvider = refreshIssueTaskFactoryProvider;
    this.labelsTaskFactoryProvider = labelsTaskFactoryProvider;
    this.milestoneTaskFactoryProvider = milestoneTaskFactoryProvider;
    this.assigneeTaskFactoryProvider = assigneeTaskFactoryProvider;
    this.stateTaskFactoryProvider = stateTaskFactoryProvider;
    this.bodyImageGetterProvider = bodyImageGetterProvider;
    this.commentImageGetterProvider = commentImageGetterProvider;
  }

  public static MembersInjector<IssueFragment> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<AvatarLoader> avatarsProvider, Provider<IssueStore> storeProvider,
      Provider<RefreshIssueTaskFactory> refreshIssueTaskFactoryProvider,
      Provider<EditLabelsTaskFactory> labelsTaskFactoryProvider,
      Provider<EditMilestoneTaskFactory> milestoneTaskFactoryProvider,
      Provider<EditAssigneeTaskFactory> assigneeTaskFactoryProvider,
      Provider<EditStateTaskFactory> stateTaskFactoryProvider,
      Provider<HttpImageGetter> bodyImageGetterProvider,
      Provider<HttpImageGetter> commentImageGetterProvider) {
    return new IssueFragment_MembersInjector(androidInjectorProvider, avatarsProvider, storeProvider, refreshIssueTaskFactoryProvider, labelsTaskFactoryProvider, milestoneTaskFactoryProvider, assigneeTaskFactoryProvider, stateTaskFactoryProvider, bodyImageGetterProvider, commentImageGetterProvider);}

  @Override
  public void injectMembers(IssueFragment instance) {
    DaggerFragment_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectAvatars(instance, avatarsProvider.get());
    injectStore(instance, storeProvider.get());
    injectRefreshIssueTaskFactory(instance, refreshIssueTaskFactoryProvider.get());
    injectLabelsTaskFactory(instance, labelsTaskFactoryProvider.get());
    injectMilestoneTaskFactory(instance, milestoneTaskFactoryProvider.get());
    injectAssigneeTaskFactory(instance, assigneeTaskFactoryProvider.get());
    injectStateTaskFactory(instance, stateTaskFactoryProvider.get());
    injectBodyImageGetter(instance, bodyImageGetterProvider.get());
    injectCommentImageGetter(instance, commentImageGetterProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.issue.IssueFragment.avatars")
  public static void injectAvatars(IssueFragment instance, AvatarLoader avatars) {
    instance.avatars = avatars;
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.issue.IssueFragment.store")
  public static void injectStore(IssueFragment instance, IssueStore store) {
    instance.store = store;
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.issue.IssueFragment.refreshIssueTaskFactory")
  public static void injectRefreshIssueTaskFactory(IssueFragment instance,
      RefreshIssueTaskFactory refreshIssueTaskFactory) {
    instance.refreshIssueTaskFactory = refreshIssueTaskFactory;
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.issue.IssueFragment.labelsTaskFactory")
  public static void injectLabelsTaskFactory(IssueFragment instance,
      EditLabelsTaskFactory labelsTaskFactory) {
    instance.labelsTaskFactory = labelsTaskFactory;
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.issue.IssueFragment.milestoneTaskFactory")
  public static void injectMilestoneTaskFactory(IssueFragment instance,
      EditMilestoneTaskFactory milestoneTaskFactory) {
    instance.milestoneTaskFactory = milestoneTaskFactory;
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.issue.IssueFragment.assigneeTaskFactory")
  public static void injectAssigneeTaskFactory(IssueFragment instance,
      EditAssigneeTaskFactory assigneeTaskFactory) {
    instance.assigneeTaskFactory = assigneeTaskFactory;
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.issue.IssueFragment.stateTaskFactory")
  public static void injectStateTaskFactory(IssueFragment instance,
      EditStateTaskFactory stateTaskFactory) {
    instance.stateTaskFactory = stateTaskFactory;
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.issue.IssueFragment.bodyImageGetter")
  public static void injectBodyImageGetter(IssueFragment instance,
      HttpImageGetter bodyImageGetter) {
    instance.bodyImageGetter = bodyImageGetter;
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.issue.IssueFragment.commentImageGetter")
  public static void injectCommentImageGetter(IssueFragment instance,
      HttpImageGetter commentImageGetter) {
    instance.commentImageGetter = commentImageGetter;
  }
}
