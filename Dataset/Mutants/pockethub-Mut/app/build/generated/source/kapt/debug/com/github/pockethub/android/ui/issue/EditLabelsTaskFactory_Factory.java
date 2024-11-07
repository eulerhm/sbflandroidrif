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
public final class EditLabelsTaskFactory_Factory implements Factory<EditLabelsTaskFactory> {
  private final Provider<IssueStore> storeProvider;

  public EditLabelsTaskFactory_Factory(Provider<IssueStore> storeProvider) {
    this.storeProvider = storeProvider;
  }

  @Override
  public EditLabelsTaskFactory get() {
    return newInstance(storeProvider);
  }

  public static EditLabelsTaskFactory_Factory create(Provider<IssueStore> storeProvider) {
    return new EditLabelsTaskFactory_Factory(storeProvider);
  }

  public static EditLabelsTaskFactory newInstance(Provider<IssueStore> storeProvider) {
    return new EditLabelsTaskFactory(storeProvider);
  }
}
