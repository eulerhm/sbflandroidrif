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
public final class EditAssigneeTaskFactory_Factory implements Factory<EditAssigneeTaskFactory> {
  private final Provider<IssueStore> storeProvider;

  public EditAssigneeTaskFactory_Factory(Provider<IssueStore> storeProvider) {
    this.storeProvider = storeProvider;
  }

  @Override
  public EditAssigneeTaskFactory get() {
    return newInstance(storeProvider);
  }

  public static EditAssigneeTaskFactory_Factory create(Provider<IssueStore> storeProvider) {
    return new EditAssigneeTaskFactory_Factory(storeProvider);
  }

  public static EditAssigneeTaskFactory newInstance(Provider<IssueStore> storeProvider) {
    return new EditAssigneeTaskFactory(storeProvider);
  }
}
