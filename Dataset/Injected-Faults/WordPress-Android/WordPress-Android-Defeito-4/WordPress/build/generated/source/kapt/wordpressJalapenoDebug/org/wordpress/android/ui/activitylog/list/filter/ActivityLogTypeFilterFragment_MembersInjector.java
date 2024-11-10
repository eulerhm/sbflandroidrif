package org.wordpress.android.ui.activitylog.list.filter;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.utils.UiHelpers;

@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ActivityLogTypeFilterFragment_MembersInjector implements MembersInjector<ActivityLogTypeFilterFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  public ActivityLogTypeFilterFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.uiHelpersProvider = uiHelpersProvider;
  }

  public static MembersInjector<ActivityLogTypeFilterFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider) {
    return new ActivityLogTypeFilterFragment_MembersInjector(viewModelFactoryProvider, uiHelpersProvider);
  }

  @Override
  public void injectMembers(ActivityLogTypeFilterFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterFragment.viewModelFactory")
  public static void injectViewModelFactory(ActivityLogTypeFilterFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterFragment.uiHelpers")
  public static void injectUiHelpers(ActivityLogTypeFilterFragment instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }
}
