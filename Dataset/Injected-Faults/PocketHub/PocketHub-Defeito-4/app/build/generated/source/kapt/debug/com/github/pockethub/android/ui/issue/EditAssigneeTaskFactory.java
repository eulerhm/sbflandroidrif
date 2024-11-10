package com.github.pockethub.android.ui.issue;

import com.github.pockethub.android.core.issue.IssueStore;
import com.github.pockethub.android.ui.base.BaseActivity;
import com.meisolsson.githubsdk.model.Issue;
import com.meisolsson.githubsdk.model.Repository;
import io.reactivex.functions.Consumer;
import javax.annotation.Generated;
import javax.inject.Inject;
import javax.inject.Provider;

@Generated(
    value = "com.google.auto.factory.processor.AutoFactoryProcessor",
    comments = "https://github.com/google/auto/tree/master/factory")
public final class EditAssigneeTaskFactory {
  private final Provider<IssueStore> storeProvider;

  @Inject
  public EditAssigneeTaskFactory(Provider<IssueStore> storeProvider) {
    this.storeProvider = checkNotNull(storeProvider, 1);
  }

  public EditAssigneeTask create(
      BaseActivity activity, Repository repositoryId, int issueNumber, Consumer<Issue> observer) {
    return new EditAssigneeTask(
        checkNotNull(storeProvider.get(), 1),
        checkNotNull(activity, 2),
        checkNotNull(repositoryId, 3),
        issueNumber,
        checkNotNull(observer, 5));
  }

  private static <T> T checkNotNull(T reference, int argumentIndex) {
    if (reference == null) {
      throw new NullPointerException(
          "@AutoFactory method argument is null but is not marked @Nullable. Argument index: "
              + argumentIndex);
    }
    return reference;
  }
}
