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
public final class EditStateTaskFactory_Factory implements Factory<EditStateTaskFactory> {
  private final Provider<IssueStore> storeProvider;

  public EditStateTaskFactory_Factory(Provider<IssueStore> storeProvider) {
    this.storeProvider = storeProvider;
  }

  @Override
  public EditStateTaskFactory get() {
    return newInstance(storeProvider);
  }

  public static EditStateTaskFactory_Factory create(Provider<IssueStore> storeProvider) {
    return new EditStateTaskFactory_Factory(storeProvider);
  }

  public static EditStateTaskFactory newInstance(Provider<IssueStore> storeProvider) {
    return new EditStateTaskFactory(storeProvider);
  }
}
