package com.github.pockethub.android.ui.commit;

import com.github.pockethub.android.core.commit.CommitStore;
import com.github.pockethub.android.core.commit.RefreshCommitTaskFactory;
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
public final class CommitDiffListFragment_MembersInjector implements MembersInjector<CommitDiffListFragment> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<RefreshCommitTaskFactory> refreshCommitTaskFactoryProvider;

  private final Provider<HttpImageGetter> commentImageGetterProvider;

  private final Provider<AvatarLoader> avatarsProvider;

  private final Provider<CommitStore> storeProvider;

  public CommitDiffListFragment_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<RefreshCommitTaskFactory> refreshCommitTaskFactoryProvider,
      Provider<HttpImageGetter> commentImageGetterProvider, Provider<AvatarLoader> avatarsProvider,
      Provider<CommitStore> storeProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.refreshCommitTaskFactoryProvider = refreshCommitTaskFactoryProvider;
    this.commentImageGetterProvider = commentImageGetterProvider;
    this.avatarsProvider = avatarsProvider;
    this.storeProvider = storeProvider;
  }

  public static MembersInjector<CommitDiffListFragment> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<RefreshCommitTaskFactory> refreshCommitTaskFactoryProvider,
      Provider<HttpImageGetter> commentImageGetterProvider, Provider<AvatarLoader> avatarsProvider,
      Provider<CommitStore> storeProvider) {
    return new CommitDiffListFragment_MembersInjector(androidInjectorProvider, refreshCommitTaskFactoryProvider, commentImageGetterProvider, avatarsProvider, storeProvider);}

  @Override
  public void injectMembers(CommitDiffListFragment instance) {
    DaggerFragment_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectRefreshCommitTaskFactory(instance, refreshCommitTaskFactoryProvider.get());
    injectCommentImageGetter(instance, commentImageGetterProvider.get());
    injectAvatars(instance, avatarsProvider.get());
    injectStore(instance, storeProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.commit.CommitDiffListFragment.refreshCommitTaskFactory")
  public static void injectRefreshCommitTaskFactory(CommitDiffListFragment instance,
      RefreshCommitTaskFactory refreshCommitTaskFactory) {
    instance.refreshCommitTaskFactory = refreshCommitTaskFactory;
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.commit.CommitDiffListFragment.commentImageGetter")
  public static void injectCommentImageGetter(CommitDiffListFragment instance,
      HttpImageGetter commentImageGetter) {
    instance.commentImageGetter = commentImageGetter;
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.commit.CommitDiffListFragment.avatars")
  public static void injectAvatars(CommitDiffListFragment instance, AvatarLoader avatars) {
    instance.avatars = avatars;
  }

  @InjectedFieldSignature("com.github.pockethub.android.ui.commit.CommitDiffListFragment.store")
  public static void injectStore(CommitDiffListFragment instance, CommitStore store) {
    instance.store = store;
  }
}
