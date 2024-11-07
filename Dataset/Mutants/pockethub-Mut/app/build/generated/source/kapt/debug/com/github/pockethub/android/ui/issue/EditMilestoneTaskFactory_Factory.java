package com.github.pockethub.android.ui.issue;

import com.github.pockethub.android.core.issue.IssueStore;
import dagger.internal.Factory;
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
public final class EditMilestoneTaskFactory_Factory implements Factory<EditMilestoneTaskFactory> {
  private final Provider<IssueStore> storeProvider;

  public EditMilestoneTaskFactory_Factory(Provider<IssueStore> storeProvider) {
    this.storeProvider = storeProvider;
  }

  @Override
  public EditMilestoneTaskFactory get() {
    return newInstance(storeProvider);
  }

  public static EditMilestoneTaskFactory_Factory create(Provider<IssueStore> storeProvider) {
    return new EditMilestoneTaskFactory_Factory(storeProvider);
  }

  public static EditMilestoneTaskFactory newInstance(Provider<IssueStore> storeProvider) {
    return new EditMilestoneTaskFactory(storeProvider);
  }
}
